/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.ICatalogPrintDao;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.EnumColumnDataType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.FieldValue;

/**
 * 目录打印dao接口实现类（SQL Server 平台实现）
 */
public class CatalogPrintDaoImpl extends JdbcDaoSupport implements ICatalogPrintDao {
	
	/**
	 * 构造函数
	 */
	public CatalogPrintDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public CatalogPrintDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	public class ArchivesInfoMapper implements RowMapper<ArchivesInfo>
	{

		/**
		 * 带参数的构造函数
		 * 
		 * @param archivesType
		 *            档案分类信息
		 */
		public ArchivesInfoMapper(ArchivesType archivesType)
		{
			this.archivesType = archivesType;
		}

		/**
		 * 档案分类信息
		 */
		private ArchivesType archivesType = null;

		/**
		 * 设置属性值：档案分类信息
		 * 
		 * @param archivesType
		 *            档案分类信息
		 */
		public void setArchivesType(ArchivesType archivesType)
		{
			this.archivesType = archivesType;
		}

		/**
		 * 获取属性值：档案分类信息
		 * 
		 * @return 档案分类信息
		 */
		public ArchivesType getArchivesType()
		{
			return archivesType;
		}
		
		@Override
		public ArchivesInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			String value = "";
			ArchivesInfo archivesInfo = new ArchivesInfo(archivesType);
			for (FieldValue item : archivesInfo.getRowFieldsValues().values())
			{
				if (item.getColumnDataType() == EnumColumnDataType.字符串)
				{
					value = rs.getString(item.getColumnName());
				}
				else if (item.getColumnDataType() == EnumColumnDataType.实数)
				{
					float tempValue = rs.getFloat(item.getColumnName());
					value = String.valueOf(tempValue);
				}
				else if (item.getColumnDataType() == EnumColumnDataType.布尔值)
				{
					boolean tempValue = rs.getBoolean(item.getColumnName());
					value = tempValue ? "1" : "0";
				}
				else if (item.getColumnDataType() == EnumColumnDataType.整数)
				{
					int tempValue = rs.getInt(item.getColumnName());
					value = String.valueOf(tempValue);
				}
				else if (item.getColumnDataType() == EnumColumnDataType.文本)
				{
					value = rs.getString(item.getColumnName());
				}
				else if (item.getColumnDataType() == EnumColumnDataType.日期时间)
				{
					Date tempValue = rs.getDate(item.getColumnName());
					SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					value = dateFormater.format(tempValue);
				}
				item.setValue(value);
			}
			
			return archivesInfo;
		}
	}
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	public class ArchivesInfoMapperOther implements RowMapper<ArchivesInfo>
	{

		/**
		 * 带参数的构造函数
		 * 
		 * @param archivesType
		 *            档案分类信息
		 */
		public ArchivesInfoMapperOther(ArchivesType archivesType)
		{
			this.archivesType = archivesType;
		}

		/**
		 * 档案分类信息
		 */
		private ArchivesType archivesType = null;

		/**
		 * 设置属性值：档案分类信息
		 * 
		 * @param archivesType
		 *            档案分类信息
		 */
		public void setArchivesType(ArchivesType archivesType)
		{
			this.archivesType = archivesType;
		}

		/**
		 * 获取属性值：档案分类信息
		 * 
		 * @return 档案分类信息
		 */
		public ArchivesType getArchivesType()
		{
			return archivesType;
		}
		
		@Override
		public ArchivesInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			String value = "";
			ArchivesInfo archivesInfo = new ArchivesInfo(archivesType);
			for (FieldValue item : archivesInfo.getRowFieldsValues().values())
			{
				if (item.getColumnDataType() == EnumColumnDataType.字符串)
				{
					value = rs.getString(item.getColumnName());
				}
				else if (item.getColumnDataType() == EnumColumnDataType.实数)
				{
					float tempValue = rs.getFloat(item.getColumnName());
					value = String.valueOf(tempValue);
				}
				else if (item.getColumnDataType() == EnumColumnDataType.布尔值)
				{
					boolean tempValue = rs.getBoolean(item.getColumnName());
					value = tempValue ? "1" : "0";
				}
				else if (item.getColumnDataType() == EnumColumnDataType.整数)
				{
					int tempValue = rs.getInt(item.getColumnName());
					value = String.valueOf(tempValue);
				}
				else if (item.getColumnDataType() == EnumColumnDataType.文本)
				{
					value = rs.getString(item.getColumnName());
				}
				else if (item.getColumnDataType() == EnumColumnDataType.日期时间)
				{
					Date tempValue = rs.getDate(item.getColumnName());
					SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					value = dateFormater.format(tempValue);
				}
				item.setValue(value);
			}
			
			//设置封皮 编制单位
			String departmentName = rs.getString("departmentName");
			if(departmentName != null) {
				archivesInfo.setDepartmentName(departmentName);
			}
			return archivesInfo;
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	 * 移交目录 打印查询
	 * UNION ALL查询两个表获取档案信息
	 * %1$s 档案归档表
	 * %2$s 档案工作表
	 * %3$s 接收审核结果条件
	 */
	private static final String SQL_SELECT_TransferCatalog = "SELECT * FROM(SELECT s.* FROM PaperTransferBatchesDetails pd,%1$s s" +
			" WHERE (pd.TransferBatNo=:BatNo OR pd.TransferBatNo IN (SELECT SubBatNo FROM PaperTransferSubBatches WHERE BatNo=:BatNo)) " +
			" AND pd.ArchivesTypeID=s.ArchivesTypeID AND pd.NBXH=s.NBXH AND s.ParentNBXH=0 AND s.ArchivesTypeID=:archivesTypeID %3$s" +
			" UNION ALL" +
			" SELECT s.* FROM PaperTransferBatchesDetails pd,%2$s s" +
			" WHERE (pd.TransferBatNo=:BatNo OR pd.TransferBatNo IN (SELECT SubBatNo FROM PaperTransferSubBatches WHERE BatNo=:BatNo)) " +
			" AND pd.ArchivesTypeID=s.ArchivesTypeID AND pd.NBXH=s.NBXH AND s.ParentNBXH=0 AND s.ArchivesTypeID=:archivesTypeID %3$s) t ORDER BY NBXH ASC";

	/**
	 * 盒内目录 打印查询
	 * %1$s 档案归档表
	 */
	private final String SQL_SELECT_BoxCatalog = "SELECT a.* FROM %1$s a,StoreroomArchivesInfo s " +
			"  WHERE s.ArchivesBoxBarcode=:boxBarcode AND s.StoreStatus<>:StoreStatus AND s.ArchivesTypeID=:archivesTypeID" +
			" AND a.ArchivesTypeID=s.ArchivesTypeID AND a.NBXH=s.NBXH AND a.ParentNBXH=0 ORDER BY a.ArchivesID ASC";
	
	
	/**
	 * 封皮打印 查询指定内部序号的档案信息的SQL语句
	 */
	private final String SQL_SELECT_ENVELOPE_ByNBXH = "SELECT w.*,d.Name departmentName FROM %1$s w,%2$s wp,UserInfo u,DD_DepartmentInfo d " +
			" WHERE w.ArchivesTypeID=wp.ArchivesTypeID AND w.NBXH=wp.NBXH AND wp.UserID=u.UserID AND u.DepartmentID=d.ID AND w.NBXH=?";
	
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.ICatalogPrintDao#findArchivesinfoForTransferCatalog(com.orifound.aiim.entity.ArchivesType, int, boolean, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesinfoForTransferCatalog(String depaermentName, String paperTransferBatNo,ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
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
				//档案归档信息表名
				String archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
				String archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("BatNo", paperTransferBatNo, Types.VARCHAR);
				paramSource.addValue("archivesTypeID", archivesType.getID(), Types.INTEGER);
				
				pErrPos = 3;
				String receiveCheckResult = " AND ";
				//档案形成部门打印 案卷目录
				if("DXBM".equals(depaermentName.toUpperCase())) {
					receiveCheckResult += "pd.ReceiveCheckResult in(1,3,4)";
				} else if("YWZD".equals(depaermentName.toUpperCase()) || "DAGL".equals(depaermentName.toUpperCase())){
					receiveCheckResult += "pd.ReceiveCheckResult=3";
				}
				List<ArchivesInfo> reaults = namedParameterJdbcTemplate.query(String.format(SQL_SELECT_TransferCatalog, archivesInfoSavedTableName, archivesInfoWorkingTableName, receiveCheckResult), paramSource, new ArchivesInfoMapper(archivesType));
				
				System.out.println("移交目录打印 查询档案信息SQL："+String.format(SQL_SELECT_TransferCatalog, archivesInfoSavedTableName, archivesInfoWorkingTableName, receiveCheckResult));
				if(reaults.size() >= 1) {
					archivesInfos.addAll(reaults);
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
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	public boolean findArchivesinfoForBoxCatalog(String boxBarcode, int storeStatus, ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
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
				//档案归档信息表名
				String archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("boxBarcode", boxBarcode, Types.VARCHAR);
				paramSource.addValue("StoreStatus", storeStatus, Types.INTEGER);
				paramSource.addValue("archivesTypeID", archivesType.getID(), Types.INTEGER);
				

				pErrPos = 3;
				List<ArchivesInfo> pEntitys = namedParameterJdbcTemplate.query(String.format(SQL_SELECT_BoxCatalog, archivesInfoSavedTableName), paramSource, new ArchivesInfoMapper(archivesType));
				
				System.out.println("盒内目录打印查询SQL="+String.format(SQL_SELECT_BoxCatalog, archivesInfoSavedTableName));
				//返回查询结果
				if (pEntitys.size() > 0) {
					archivesInfos.addAll(pEntitys);
				}

				//销毁局部变量
				paramSource = null;
				namedParameterJdbcTemplate = null;
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	public boolean findArchivesinfoForEnelope(int pNBXH, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			// 检查内部序号是否有值
			if (pFlag)
			{
				if (pNBXH <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案内部序号非法为0");
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				String archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				String archivesInfoWorkProcedureTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档过程表).getTableName();
				pErrPos = 2;
				// 将工作表名动态构建到SQL语句中去，注意jdbc不支持select top ? from
				// ?的动态构建，必须自行动态构建好SQL语句字符串
				String localSql = String.format(SQL_SELECT_ENVELOPE_ByNBXH, archivesInfoWorkingTableName, archivesInfoWorkProcedureTableName);

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<ArchivesInfo> pArchivesInfos = jdbcTemplate.query(localSql, new ArchivesInfoMapperOther(archivesType), pNBXH);
				
				System.out.println("封皮打印查询SQL="+localSql);

				// 返回查询结果
				if (pArchivesInfos.size() > 0)
				{
					archivesInfo.cloneFrom(pArchivesInfos.get(0));
					archivesInfo.setDepartmentName(pArchivesInfos.get(0).getDepartmentName());
				}

				// 销毁局部变量
				pArchivesInfos = null;
				jdbcTemplate = null;
			}
		}
		catch (BadSqlGrammarException e)
		{
			// SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			// 其他异常错误
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
}
