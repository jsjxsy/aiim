package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IPaperTransferBatchesArchvTypeDetailsDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.PaperTransferBatch;
import com.orifound.aiim.entity.PaperTransferBatchesArchvTypeDetail;
/**
 * 批次信息分类明细表DAO实现类
 * @author Administrator
 *
 */
public class PaperTransferBatchesArchvTypeDetailsDaoImpl extends JdbcDaoSupport implements IPaperTransferBatchesArchvTypeDetailsDao {

	/**
	 * 按批次号和档案分类查找批次分类详细信息
	 */
	private static final String SQL_SELECT_findByArchivesTypeAndBatNO = "SELECT * FROM PaperTransferBatchesArchvTypeDetails WHERE TransferBatNo=? AND ArchivesTypeID=?";
	
	/**
	 * 更新批次分类详细信息中档案信息的总数量
	 */
	private static final String SQL_UPDATE_PaperTransferBatchesArchvTypeDetails= "UPDATE  PaperTransferBatchesArchvTypeDetails SET TransferTotal=" +
			"(SELECT COUNT(*) FROM PaperTransferBatchesDetails where TransferBatNo=:TransferBatNo AND ArchivesTypeID=:ArchivesTypeID) " +
			"WHERE TransferBatNo=:TransferBatNo AND ArchivesTypeID=:ArchivesTypeID";
	
	/**
	 * 更新批次分类详细信息中档案信息的总数量
	 */
	private static final String SQL_UPDATE_PaperTransferBatchesArchvTypeDetailsWithSubBat = "UPDATE PaperTransferBatchesArchvTypeDetails SET TransferTotal=" +
	"(SELECT COUNT(*) FROM PaperTransferBatchesDetails where (TransferBatNo=:TransferBatNo OR TransferBatNo IN (SELECT SubBatNo FROM PaperTransferSubBatches WHERE BatNo=:TransferBatNo)) AND ArchivesTypeID=:ArchivesTypeID AND ReceiveCheckResult<>2) " +
	"WHERE TransferBatNo=:TransferBatNo AND ArchivesTypeID=:ArchivesTypeID";
	
	/**
	 * 插入新的批次分类详细信息
	 */
	private static final String SQL_INSERT_PaperTransferBatchesArchvTypeDetail = "INSERT INTO PaperTransferBatchesArchvTypeDetails (TransferBatNo,ArchivesTypeID,TransferTotal) VALUES(?,?,?)";
	
	/**
	 * 根查找指定批次下所有的批次分类详细信息
	 */
	private static final String SQL_SELECT_PaperTransferBatchesArchvTypeDetails = "SELECT * FROM PaperTransferBatchesArchvTypeDetails WHERE TransferBatNo=?";
	
	
	
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class PaperTransferBatchesArchvTypeDetailMapper implements RowMapper<PaperTransferBatchesArchvTypeDetail>
	{
		@Override
		public PaperTransferBatchesArchvTypeDetail mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String transferBatNo = rs.getString("TransferBatNo");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int transferTotal = rs.getInt("TransferTotal");
			int receiveTotal = rs.getInt("ReceiveTotal");
			boolean archivesIDMaked  = rs.getBoolean("ArchivesIDMaked");
			
			return new PaperTransferBatchesArchvTypeDetail(iD,transferBatNo,archivesTypeID,transferTotal,receiveTotal,archivesIDMaked);
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

	@Override
	public boolean findByArchivesTypeAndBatNO(PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				if (paperTransferBatchesArchvTypeDetail == null) {
					pFlag = false;
					pErrInfo.getContent().append("批次详细信息对象非法为空");
				}else if(paperTransferBatchesArchvTypeDetail.getTransferBatNo() == null){
					pFlag = false;
					pErrInfo.getContent().append("批次详细信息对象非法为空");
				}else if("".equals(paperTransferBatchesArchvTypeDetail.getTransferBatNo())){
					pFlag = false;
					pErrInfo.getContent().append("批次详细信息对象批次号非法为空");
				}else if(paperTransferBatchesArchvTypeDetail.getArchivesTypeID() == 0){
					pFlag = false;
					pErrInfo.getContent().append("批次详细信息对象档案类别编号非法为空");
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				//String SQL_SELECT_findByArchivesTypeAndBatNO = "SELECT * FROM PaperTransferBatchesArchvTypeDetails WHERE TransferBatNo=? AND ArchivesTypeID=?";
				List<PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = getJdbcTemplate().query(SQL_SELECT_findByArchivesTypeAndBatNO, 
																								new PaperTransferBatchesArchvTypeDetailMapper(), 
																								paperTransferBatchesArchvTypeDetail.getTransferBatNo(),
																								paperTransferBatchesArchvTypeDetail.getArchivesTypeID());
				if(paperTransferBatchesArchvTypeDetails .size()>0){
					paperTransferBatchesArchvTypeDetail.cloneFrom(paperTransferBatchesArchvTypeDetails.get(0));
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
	public boolean update(PaperTransferBatchesArchvTypeDetail pPaperTransferBatchesArchvTypeDetail, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				if (pPaperTransferBatchesArchvTypeDetail == null) {
					pFlag = false;
					pErrInfo.getContent().append("批次详细信息对象非法为空");
				}else if(pPaperTransferBatchesArchvTypeDetail.getTransferBatNo() == null){
					pFlag = false;
					pErrInfo.getContent().append("批次详细信息对象非法为空");
				}else if("".equals(pPaperTransferBatchesArchvTypeDetail.getTransferBatNo())){
					pFlag = false;
					pErrInfo.getContent().append("批次详细信息对象批次号非法为空");
				}else if(pPaperTransferBatchesArchvTypeDetail.getArchivesTypeID() == 0){
					pFlag = false;
					pErrInfo.getContent().append("批次详细信息对象档案类别编号非法为空");
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				//private static final String SQL_UPDATE_PaperTransferBatchesArchvTypeDetails= "UPDATE  PaperTransferBatchesArchvTypeDetails SET TransferTotal=(SELECT COUNT(*) FROM PaperTransferBatchesDetails where TransferBatNo=:TransferBatNo AND ArchivesTypeID=:ArchivesTypeID) WHERE TransferBatNo=:TransferBatNo AND ArchivesTypeID=:ArchivesTypeID";
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("TransferBatNo", pPaperTransferBatchesArchvTypeDetail.getTransferBatNo());
				paramSource.addValue("ArchivesTypeID",pPaperTransferBatchesArchvTypeDetail.getArchivesTypeID());
				
				namedParameterJdbcTemplate.update(SQL_UPDATE_PaperTransferBatchesArchvTypeDetails, paramSource);
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
	public boolean add(PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				if (paperTransferBatchesArchvTypeDetail == null) {
					pFlag = false;
					pErrInfo.getContent().append("批次详细信息对象非法为空");
				}else if(paperTransferBatchesArchvTypeDetail.getTransferBatNo() == null){
					pFlag = false;
					pErrInfo.getContent().append("批次详细信息对象非法为空");
				}else if("".equals(paperTransferBatchesArchvTypeDetail.getTransferBatNo())){
					pFlag = false;
					pErrInfo.getContent().append("批次详细信息对象批次号非法为空");
				}else if(paperTransferBatchesArchvTypeDetail.getArchivesTypeID() == 0){
					pFlag = false;
					pErrInfo.getContent().append("批次详细信息对象档案类别编号非法为空");
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				//private static final String SQL_INSERT_PaperTransferBatchesArchvTypeDetail = 
				//"INSERT INTO PaperTransferBatchesArchvTypeDetails (TransferBatNo,ArchivesTypeID,TransferTotal) VALUES(?,?,?)";
				getJdbcTemplate().update(SQL_INSERT_PaperTransferBatchesArchvTypeDetail, 
						paperTransferBatchesArchvTypeDetail.getTransferBatNo(),
						paperTransferBatchesArchvTypeDetail.getArchivesTypeID(),
						paperTransferBatchesArchvTypeDetail.getTransferTotal());
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
	public boolean findCurrentPaperTransferBatchesArchvTypeDetails(PaperTransferBatch paperTransferBatch,Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				if (paperTransferBatch == null) {
					pFlag = false;
					pErrInfo.getContent().append("批次信息对象非法为空");
				}else if(paperTransferBatch.getBatNo() == null){
					pFlag = false;
					pErrInfo.getContent().append("批次信息对象批次号非法为空");
				}else if("".equals(paperTransferBatch.getBatNo())){
					pFlag = false;
					pErrInfo.getContent().append("批次信息对象批次号非法为空");
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				//private static final String SQL_SELECT_PaperTransferBatchesArchvTypeDetails = "SELECT * FROM PaperTransferBatchesArchvTypeDetails WHERE TransferBatNo=?";
				List<PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetailList = getJdbcTemplate().query(SQL_SELECT_PaperTransferBatchesArchvTypeDetails,new PaperTransferBatchesArchvTypeDetailMapper(), paperTransferBatch.getBatNo());
				for (PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail : paperTransferBatchesArchvTypeDetailList) {
					paperTransferBatchesArchvTypeDetails.put(paperTransferBatchesArchvTypeDetail.getID(), paperTransferBatchesArchvTypeDetail);
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
	public boolean updateArchivesIDMaked(String paperTransferBatNo, int archivesTypeID, ErrInfo pErrInfo) {
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
				String sql = "UPDATE PaperTransferBatchesArchvTypeDetails SET ArchivesIDMaked='true' WHERE TransferBatNo=? AND ArchivesTypeID=?";
				getJdbcTemplate().update(sql,paperTransferBatNo,archivesTypeID);
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
	public boolean findByArchivesIDMaked(String paperTransferBatNo, int archivesTypeID, List<PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails, ErrInfo pErrInfo) {
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
				String sql = "SELECT * FROM PaperTransferBatchesArchvTypeDetails  WHERE TransferBatNo=? AND ArchivesTypeID=? AND ArchivesIDMaked='false'";
				List<PaperTransferBatchesArchvTypeDetail> list = getJdbcTemplate().query(sql,new PaperTransferBatchesArchvTypeDetailMapper(),paperTransferBatNo,archivesTypeID);
				if (list.size()>0) {
					paperTransferBatchesArchvTypeDetails.addAll(list);
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
	public boolean updateWhithSubBat(PaperTransferBatchesArchvTypeDetail pPaperTransferBatchesArchvTypeDetail, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				if (pPaperTransferBatchesArchvTypeDetail == null) {
					pFlag = false;
					pErrInfo.getContent().append("批次详细信息对象非法为空");
				}else if(pPaperTransferBatchesArchvTypeDetail.getTransferBatNo() == null){
					pFlag = false;
					pErrInfo.getContent().append("批次详细信息对象非法为空");
				}else if("".equals(pPaperTransferBatchesArchvTypeDetail.getTransferBatNo())){
					pFlag = false;
					pErrInfo.getContent().append("批次详细信息对象批次号非法为空");
				}else if(pPaperTransferBatchesArchvTypeDetail.getArchivesTypeID() == 0){
					pFlag = false;
					pErrInfo.getContent().append("批次详细信息对象档案类别编号非法为空");
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				//private static final String SQL_UPDATE_PaperTransferBatchesArchvTypeDetails= "UPDATE  PaperTransferBatchesArchvTypeDetails SET TransferTotal=(SELECT COUNT(*) FROM PaperTransferBatchesDetails where TransferBatNo=:TransferBatNo AND ArchivesTypeID=:ArchivesTypeID) WHERE TransferBatNo=:TransferBatNo AND ArchivesTypeID=:ArchivesTypeID";
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("TransferBatNo", pPaperTransferBatchesArchvTypeDetail.getTransferBatNo());
				paramSource.addValue("ArchivesTypeID",pPaperTransferBatchesArchvTypeDetail.getArchivesTypeID());
				
				namedParameterJdbcTemplate.update(SQL_UPDATE_PaperTransferBatchesArchvTypeDetailsWithSubBat, paramSource);
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
