package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IPaperTransferBatchesDetailsDao;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.EnumCheckResult;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.PaperTransferBatch;
import com.orifound.aiim.entity.PaperTransferBatchesArchvTypeDetail;
import com.orifound.aiim.entity.PaperTransferBatchesDetail;
import com.orifound.aiim.entity.SystemInitializer;

/**
 * 档案移交批次明细表DAO实现类
 * @author Administrator
 *
 */
public class PaperTransferBatchesDetailsDaoImpl extends JdbcDaoSupport implements  IPaperTransferBatchesDetailsDao{

	/**
	 * 添加移交批次信息详细的SQL
	 * %1$s : 常量批次号
	 * %2$s : working表名
	 */
	private static final String SQL_INSERT_PaperTransferBatchesDetails = "insert into PaperTransferBatchesDetails  (TransferBatNo,ArchivesTypeID,NBXH,ParentFlag,ArchivesID,Title,SubContentCount,PageSum,RetentionPeriodID,SecrecyID,FormationYear)"+   
         "select '%1$s',ArchivesTypeID,NBXH,ParentFlag,ArchivesID,Title,SubContentCount,PageSum,RetentionPeriodID,SecrecyID,FormationYear from %2$s where NBXH IN (:NBXHS)";

	/**
	 * 按批次号和分类查找批次详细信息的SQL
	 */
	private static final String SQL_SELECT_findByBatNoAndArchivesType = "SELECT * FROM PaperTransferBatchesDetails WHERE (TransferBatNo=:TransferBatNo OR TransferBatNo IN (SELECT SubBatNo FROM PaperTransferSubBatches WHERE BatNo=:TransferBatNo)) AND ArchivesTypeID=:ArchivesTypeID AND ReceiveCheckResult IN (:ReceiveCheckResults) ORDER BY ReceiveCheckResult,TransferTime";
	
	/**
	 * 删除批次详细信息的SQL
	 */
	private static final String SQL_DELETE_PaperTransferBatchesDetails = "DELETE FROM PaperTransferBatchesDetails WHERE TransferBatNo=? AND ArchivesTypeID=? AND NBXH=?";
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class PaperTransferBatchesDetailMapper implements RowMapper<PaperTransferBatchesDetail>
	{
		
		@Override
		public PaperTransferBatchesDetail mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String transferBatNo = rs.getString("TransferBatNo");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			boolean parentFlag = rs.getBoolean("ParentFlag");
			String archivesID = rs.getString("ArchivesID");
			String title = rs.getString("Title");
			int subContentCount = rs.getInt("SubContentCount");
			int pageSum = rs.getInt("PageSum");
			int retentionPeriodID = rs.getInt("RetentionPeriodID");
			int secrecyID = rs.getInt("SecrecyID");
			int formationYear = rs.getInt("FormationYear");
			Timestamp transferTime = rs.getTimestamp("TransferTime");
			int receiveCheckResult = rs.getInt("ReceiveCheckResult");
			
			return new PaperTransferBatchesDetail(iD,transferBatNo,archivesTypeID,nBXH,parentFlag,archivesID,title,subContentCount,pageSum,retentionPeriodID,secrecyID,formationYear,transferTime,EnumCheckResult.getEnumElement(receiveCheckResult));
		}
	}
	
	/**
	 * 检查JDBC数据源的依赖注入（JDBC DataSource Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean CheckDataSourceInjection(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查数据源是否为空
			pErrPos = 1;
			if (getDataSource() == null) {
				pFlag = false;
				pErrInfo.getContent().append("JDBC数据源非法为空，请检查是否有进行依赖注入或赋值。");
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}
	
	/**
	 * 检查指定的档案分类信息下其档案归档工作表信息是否正确赋值了
	 * 
	 * @param archivesType
	 *            档案分类信息
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkTableName(ArchivesType archivesType, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			if (archivesType.getArchivesInfoTables() == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案相关信息表非法为空");
			}
			else
			{
				if (archivesType.getArchivesInfoTables().containsKey(EnumArchivesInfoTableType.档案归档工作表) == false)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类（" + archivesType.getFullCode() + "）的档案归档工作表信息非法为空。");
				}
				else
				{
					String tableName = "";
					tableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
					if (tableName == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类（" + archivesType.getFullCode() + "）的档案归档工作表信息非法为空。");
					}
					else
					{
						if (tableName.length() == 0)
						{
							pFlag = false;
							pErrInfo.getContent().append("档案分类（" + archivesType.getFullCode() + "）的档案归档工作表信息非法为空。");
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// 销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	
	@Override
	public boolean addPaperTransferBatchesDetails(ArchivesType archivesType, List<ArchivesInfo> archivesInfos, PaperTransferBatch paperTransferBatch, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoWorkingTableName = "";
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// 检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}
			
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				
				//private static final String SQL_INSERT_PaperTransferBatchesDetails = "insert into PaperTransferBatchesDetails  (TransferBatNo,ArchivesTypeID,NBXH,ParentFlag,ArchivesID,Title,SubContentCount,PageSum,RetentionPeriodID,SecrecyID,FormationYear)"+   
		      //   "select '%1$s',ArchivesTypeID,NBXH,ParentFlag,ArchivesID,Title,SubContentCount,PageSum,RetentionPeriodID,SecrecyID,FormationYear from %2$s where NBXH IN (:NBXHS)";
				String localSql = String.format(SQL_INSERT_PaperTransferBatchesDetails, paperTransferBatch.getBatNo(),archivesInfoWorkingTableName);
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				
				List<Integer> NBXHS = new ArrayList<Integer>();
				for (ArchivesInfo archivesInfo : archivesInfos) {
					NBXHS.add(archivesInfo.getNBXH());
				}
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("NBXHS", NBXHS);
				
				namedParameterJdbcTemplate.update(localSql, parameterSource);
			}
		} catch (BadSqlGrammarException e) {
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}

	@Override
	public boolean update(PaperTransferBatchesDetail pPaperTransferBatchesDetail, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String paperTransferBatNo, int archivesTypeID,int nBXH, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				 //String SQL_DELETE_PaperTransferBatchesDetails = "DELETE FROM PaperTransferBatchesDetails WHERE TransferBatNo=?,ArchivesTypeID=?, NBXH=?";
				getJdbcTemplate().update(SQL_DELETE_PaperTransferBatchesDetails,paperTransferBatNo,archivesTypeID, nBXH);
			}
		} catch (BadSqlGrammarException e) {
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}

	@Override
	public boolean setFlagForWorkFlow(ArchivesType archivesType, PaperTransferBatch paperTransferBatch, int userID, EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		String archivesInfoWorkingTableName = "";
		String archivesInfoWorkingProcedureTableName = "";
		try {
			pErrPos = 1;
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				if (paperTransferBatch == null) {
					pFlag = false;
					pErrInfo.getContent().append("批次信息没有初始化");
				}
			}
			
			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}
			
			archivesInfoWorkingProcedureTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档过程表).getTableName();
			String sql1 = "update %1$s  set WorkFlowStatus=? where NBXH in (select p.NBXH from PaperTransferBatchesDetails p where p.TransferBatNo=? and p.ArchivesTypeID=?)";
			
			String sql2 = "insert into %1$s (ArchivesTypeID,NBXH,UserID,WorkFlowStatus) " +
					"select T1.ArchivesTypeID,T1.NBXH,T1.UserID1,T1.WorkFlowStatus from %2$s T1 where NBXH in (" +
					"select p.NBXH from PaperTransferBatchesDetails p where p.TransferBatNo=? and p.ArchivesTypeID=?)";
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				getJdbcTemplate().update(String.format(sql1, archivesInfoWorkingTableName),enumWorkFlowStatus.getEnumValue(), paperTransferBatch.getBatNo(),archivesType.getID());
				getJdbcTemplate().update(String.format(sql2, archivesInfoWorkingProcedureTableName,archivesInfoWorkingTableName), paperTransferBatch.getBatNo(),archivesType.getID());
			}
		} catch (BadSqlGrammarException e) {
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean updateReceiveCheckResult(int archivesTypeID, String batNo,int NBXH, EnumCheckResult enumCheckResult, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				 String SQL_UPDATE_updateReceiveCheckResult = "UPDATE PaperTransferBatchesDetails SET ReceiveCheckResult=? WHERE (TransferBatNo=? OR TransferBatNo IN (SELECT SubBatNo FROM PaperTransferSubBatches WHERE BatNo=?)) AND ArchivesTypeID=? AND NBXH=?";
				getJdbcTemplate().update(SQL_UPDATE_updateReceiveCheckResult,enumCheckResult.getEnumValue(),batNo,batNo,archivesTypeID, NBXH);
			}
		} catch (BadSqlGrammarException e) {
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}

	@Override
	public boolean findArchivesFondsByBatNoAndArchivesType(String batNo, ArchivesType archivesType, List<String> pArchivesFondIDs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoWorkingTableName = "";
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// 检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				String sql = "SELECT DISTINCT T1.ArchivesFondsID AS ArchivesFondsID FROM %1$S T1 ," +
						"(SELECT NBXH FROM PaperTransferBatchesDetails T2 WHERE T2.TransferBatNo=? AND T2.ArchivesTypeID=?) T3 WHERE T1.NBXH=T3.NBXH";
				sql = String.format(sql, archivesInfoWorkingTableName);
				List<String> archivesFondIDs =  getJdbcTemplate().queryForList(sql, String.class,batNo,archivesType.getID());
				pArchivesFondIDs.addAll(archivesFondIDs);
			}
		} catch (BadSqlGrammarException e) {
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean findNBXHSByArchivesFonds(String paperTransferBatNo, ArchivesType archivesType, String archivesFondID, List<Integer> pNBXHS, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoWorkingTableName = "";
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// 检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				String sql = "SELECT T1.NBXH AS NBXH FROM %1$s T1 , (SELECT NBXH FROM PaperTransferBatchesDetails T2 " +
						"WHERE T2.TransferBatNo=? AND T2.ArchivesTypeID=? AND T2.ReceiveCheckResult=1) T3 WHERE T1.NBXH=T3.NBXH AND T1.ArchivesFondsID=?";
				sql = String.format(sql, archivesInfoWorkingTableName);
				List<Integer> NBXHS =  getJdbcTemplate().queryForList(sql, Integer.class,paperTransferBatNo,archivesType.getID(),archivesFondID);
				pNBXHS.addAll(NBXHS);
			}
		} catch (BadSqlGrammarException e) {
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean updateArchivesID(String paperTransferBatNo, ArchivesType archivesType,ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoWorkingTableName = "";
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// 检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				//更新案卷档号
				String sql = "UPDATE %1$s  SET ArchivesID=%2$s WHERE NBXH IN (SELECT T1.NBXH AS NBXH FROM %1$s T1 ," +
						"(SELECT NBXH FROM PaperTransferBatchesDetails T2 WHERE T2.TransferBatNo=? AND T2.ArchivesTypeID=? AND T2.ReceiveCheckResult=1) T3 " +
						"WHERE T1.NBXH=T3.NBXH)";
				sql = String.format(sql, archivesInfoWorkingTableName,archivesType.getArchivesIDExpression());
				getJdbcTemplate().update(sql,paperTransferBatNo,archivesType.getID());
				
				//更新卷内文件档号
				sql = "UPDATE %1$s  SET ArchivesID=%2$s WHERE ParentNBXH IN (SELECT T1.NBXH AS NBXH FROM %1$s T1 ," +
				"(SELECT NBXH FROM PaperTransferBatchesDetails T2 WHERE T2.TransferBatNo=? AND T2.ArchivesTypeID=? AND T2.ReceiveCheckResult=1) T3 " +
				"WHERE T1.NBXH=T3.NBXH)";
				sql = String.format(sql, archivesInfoWorkingTableName,archivesType.getSubArchivesIDExpression());
				getJdbcTemplate().update(sql,paperTransferBatNo,archivesType.getID());
				
				//更新批次详细信息案卷档号
				sql = "UPDATE PaperTransferBatchesDetails SET ArchivesID=(select T1.ArchivesID from %1$s T1 where PaperTransferBatchesDetails.ArchivesTypeID=T1.ArchivesTypeID and PaperTransferBatchesDetails.NBXH=T1.NBXH) WHERE ReceiveCheckResult=1 AND TransferBatNo=?";
				sql = String.format(sql, archivesInfoWorkingTableName);
				getJdbcTemplate().update(sql,paperTransferBatNo);
			}
		} catch (BadSqlGrammarException e) {
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean addToPaperTransferBatchaddToPaperTransferBatch(final String[] pPaperTransferBatchNos, final PaperTransferBatch paperTransferBatch, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		//List<String> paperTransferBatchNos = new ArrayList<String>();
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				String sql = "INSERT INTO PaperTransferSubBatches VALUES(?,?)";
				getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, paperTransferBatch.getBatNo());
						ps.setString(2, pPaperTransferBatchNos[i]);
					}
					@Override
					public int getBatchSize() {
						return pPaperTransferBatchNos.length;
					}
				});
				
				
				/*for (String paperTransferBatchNo : pPaperTransferBatchNos) {
					paperTransferBatchNos.add(paperTransferBatchNo);
				}
				
				sql ="UPDATE PaperTransferBatchesDetails SET ReceiveCheckResult=0 WHERE TransferBatNo IN (:TransferBatNos) AND ReceiveCheckResult<>2";
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("TransferBatNos", paperTransferBatchNos);
				
				namedParameterJdbcTemplate.update(sql, parameterSource);*/
			}
		} catch (BadSqlGrammarException e) {
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean staArchivesInfosSumByTransferBat(IntegerEx archivesInfosSum, PaperTransferBatch paperTransferBatch, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				String sql = "SELECT COUNT(*) FROM PaperTransferBatchesDetails T1 WHERE T1.TransferBatNo=? OR T1.TransferBatNo IN(" +
						"SELECT T2.SubBatNo FROM PaperTransferSubBatches T2 WHERE T2.BatNo=?) AND (T1.ReceiveCheckResult=1 OR T1.ReceiveCheckResult=3)";
				archivesInfosSum.setValue(getJdbcTemplate().queryForInt(sql,paperTransferBatch.getBatNo(),paperTransferBatch.getBatNo()));
			}
		} catch (BadSqlGrammarException e) {
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}

	@Override
	public boolean staArchivesInfosSumByTransferBatArchvType(final PaperTransferBatch paperTransferBatch, List<PaperTransferBatchesArchvTypeDetail> pPaperTransferBatchesArchvTypeDetails, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				String sql = "SELECT COUNT(*) AS TransferTotal, T2.ArchivesTypeID AS ArchivesTypeID FROM PaperTransferBatchesDetails T2 WHERE (T2.TransferBatNo=? OR T2.TransferBatNo IN (SELECT T1.SubBatNo AS SubBatNo FROM PaperTransferSubBatches T1 WHERE BatNo=?)) AND ReceiveCheckResult<>2 GROUP BY T2.ArchivesTypeID";
				
				//数据库查询字段映射器
				RowMapper<PaperTransferBatchesArchvTypeDetail> mapper = new RowMapper<PaperTransferBatchesArchvTypeDetail>() {
					PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail = null;
					
					@Override
					public PaperTransferBatchesArchvTypeDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
						paperTransferBatchesArchvTypeDetail = new PaperTransferBatchesArchvTypeDetail();
						
						paperTransferBatchesArchvTypeDetail.setTransferBatNo(paperTransferBatch.getBatNo());
						paperTransferBatchesArchvTypeDetail.setArchivesTypeID(rs.getInt("ArchivesTypeID"));
						paperTransferBatchesArchvTypeDetail.setTransferTotal(rs.getInt("TransferTotal"));
						
						return paperTransferBatchesArchvTypeDetail;
					}
				};
				
				//执行SQL
				List<PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = getJdbcTemplate().query(sql, new Object[]{paperTransferBatch.getBatNo(),paperTransferBatch.getBatNo()}, mapper);
				if (paperTransferBatchesArchvTypeDetails.size()>0) {
					pPaperTransferBatchesArchvTypeDetails.addAll(paperTransferBatchesArchvTypeDetails);
				}
			}
		} catch (BadSqlGrammarException e) {
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}
	
	@Override
	public boolean setArchivesFlagForWorkFlow(PaperTransferBatch paperTransferBatch, ArchivesType archivesType, EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoWorkingTableName = "";
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}

			//执行SQL语句
			if (pFlag) {
				String sql = "UPDATE %1$s SET WorkFlowStatus=? WHERE NBXH IN (" +
						"SELECT NBXH FROM PaperTransferBatchesDetails T1 WHERE T1.TransferBatNo=? OR T1.TransferBatNo IN(" +
						"SELECT T2.SubBatNo FROM PaperTransferSubBatches T2 WHERE T2.BatNo=?) AND T1.ReceiveCheckResult<>2 AND T1.ReceiveCheckResult<>4)";
				sql = String.format(sql, archivesInfoWorkingTableName);
				getJdbcTemplate().update(sql,enumWorkFlowStatus.getEnumValue(), paperTransferBatch.getBatNo(),paperTransferBatch.getBatNo());
			}
		} catch (BadSqlGrammarException e) {
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean findByBatNoAndArchivesType(String paperTransferBatNo, int archivesTypeID, List<PaperTransferBatchesDetail> paperTransferBatchesDetails, List<EnumCheckResult> enumCheckResults, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		List<Integer> receiveCheckResults = new ArrayList<Integer>();
		
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				for (EnumCheckResult enumCheckResult : enumCheckResults) {
					receiveCheckResults.add(enumCheckResult.getEnumValue());
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				//TransferBatNo=:TransferBatNo AND ArchivesTypeID=:ArchivesTypeID AND ReceiveCheckResult IN (:ReceiveCheckResults)";
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("TransferBatNo", paperTransferBatNo);
				parameterSource.addValue("ArchivesTypeID", archivesTypeID);
				parameterSource.addValue("ReceiveCheckResults", receiveCheckResults);
				
				List<PaperTransferBatchesDetail> pPaperTransferBatchesDetails = namedParameterJdbcTemplate.query(SQL_SELECT_findByBatNoAndArchivesType, parameterSource, new PaperTransferBatchesDetailMapper());
				
				paperTransferBatchesDetails.addAll(pPaperTransferBatchesDetails);
			}
		} catch (BadSqlGrammarException e) {
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}

	@Override
	public boolean setFlagForWorkFlow(PaperTransferBatch paperTransferBatch, ArchivesType archivesType, int userID, EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		String archivesInfoWorkingTableName = "";
		String archivesInfoWorkingProcedureTableName = "";
		try {
			pErrPos = 1;
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				if (paperTransferBatch == null) {
					pFlag = false;
					pErrInfo.getContent().append("批次信息没有初始化");
				}
			}
			
			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}
			
			archivesInfoWorkingProcedureTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档过程表).getTableName();
			String sql1 = "update %1$s  set WorkFlowStatus=? where NBXH in (select p.NBXH from PaperTransferBatchesDetails p where p.TransferBatNo=? OR p.TransferBatNo IN (SELECT SubBatNo FROM PaperTransferSubBatches WHERE BatNo=?)  and p.ArchivesTypeID=?)";
			
			String sql2 = "insert into %1$s (ArchivesTypeID,NBXH,UserID,WorkFlowStatus) " +
					"select T1.ArchivesTypeID,T1.NBXH,T1.UserID2,T1.WorkFlowStatus from %2$s T1 where NBXH in (" +
					"select p.NBXH from PaperTransferBatchesDetails p where p.TransferBatNo=? OR p.TransferBatNo IN (SELECT SubBatNo FROM PaperTransferSubBatches WHERE BatNo=?) and p.ArchivesTypeID=?)";
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				getJdbcTemplate().update(String.format(sql1, archivesInfoWorkingTableName),enumWorkFlowStatus.getEnumValue(),paperTransferBatch.getBatNo(), paperTransferBatch.getBatNo(),archivesType.getID());
				getJdbcTemplate().update(String.format(sql2, archivesInfoWorkingProcedureTableName,archivesInfoWorkingTableName), paperTransferBatch.getBatNo(),paperTransferBatch.getBatNo(),archivesType.getID());
			}
		} catch (BadSqlGrammarException e) {
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean findArchivesIDPrefixWhitOutArchivesID(String paperTransferBatNo, ArchivesType archivesType, final Map<Integer, String> archivesIDPrefixs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoWorkingTableName = "";
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				String sql = "SELECT NBXH,%2$s AS ArchivesIDPrefix FROM %1$s WHERE NBXH IN " +
						"(SELECT NBXH FROM PaperTransferBatchesDetails WHERE (TransferBatNo=? OR TransferBatNo IN (SELECT SubBatNo FROM PaperTransferSubBatches WHERE BatNo=?)) AND ReceiveCheckResult=1)";
				
				sql = String.format(sql, archivesInfoWorkingTableName,archivesType.getArchivesIDExpressionPrefix());
				
				//记录集行处理类
				RowCallbackHandler rowCallbackHandler = new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						archivesIDPrefixs.put(rs.getInt("NBXH"), rs.getString("ArchivesIDPrefix"));
					}
				};
				
				//条件
				Object [] args = new Object[]{paperTransferBatNo,paperTransferBatNo};
				
				//执行sql
				getJdbcTemplate().query(sql, args, rowCallbackHandler);
				
			}
		} catch (BadSqlGrammarException e) {
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}

	@Override
	public boolean findTopArchivesByBatNoWhitOutBarcode(String batNo, int archivesTypeID,final ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoSavedTableName = "";
		
		ArchivesType archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				String sql= "SELECT TOP 1 * FROM"+
						  "(SELECT * FROM PaperTransferBatchesDetails P WHERE (P.TransferBatNo=:TransferBatNo OR P.TransferBatNo IN (SELECT SubBatNo FROM PaperTransferSubBatches WHERE BatNo=:TransferBatNo)) AND P.ArchivesTypeID=:ArchivesTypeID) T1,"+
						  "(SELECT * FROM %1$s A WHERE A.Barcode IS NULL) T2 WHERE T1.NBXH=T2.NBXH ORDER BY T2.ArchivesID";
				sql = String.format(sql, archivesInfoSavedTableName);
				
				//行处理类
				RowCallbackHandler rowCallbackHandler = new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						archivesInfo.setNBXH(rs.getInt("NBXH"));
						archivesInfo.setArchivesID(rs.getString("ArchivesID"));
						archivesInfo.setTitle(rs.getString("Title"));
						archivesInfo.setBarcode(rs.getString("Barcode"));
					}
				};
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("TransferBatNo", batNo);
				parameterSource.addValue("ArchivesTypeID", archivesTypeID);
				
				NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				jdbcTemplate.query(sql, parameterSource, rowCallbackHandler);
			}
		} catch (BadSqlGrammarException e) {
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}
}
