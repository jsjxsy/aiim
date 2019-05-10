/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IArchivesInfoSavedDao;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.EnumColumnDataType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.FieldValue;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserInfo;

/**
 * 档案归档信息的DAO实现类 （SQL Server平台）
 *
 */
public class ArchivesInfoSavedDaoImpl extends JdbcDaoSupport implements IArchivesInfoSavedDao {
	
	/**
	 * 构造函数
	 */
	public ArchivesInfoSavedDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public ArchivesInfoSavedDaoImpl(DataSource dataSource) {
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
			//设置鉴定前保管期限年度
			archivesInfo.setTotalYears(rs.getInt("TotalYears"));
			archivesInfo.setStoreAddressFullName(rs.getString("StoreAddressFullName"));
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
	 * 鉴定管理->存毁鉴定 	分页查询过期档案信息的SQL语句(过滤卷内文件)
	 * %1$s 档案信息归档表名
	 * %2$s 档案形成部门条件
	 */
	private final String SQL_SELECT_SaveDestroyAppraisal_WithPage = "SELECT * FROM( SELECT t.* ,rp.TotalYears, '' StoreAddressFullName,ROW_NUMBER() OVER(ORDER BY t.FormationDepartmentID ASC, t.SaveDate ASC, t.ArchivesID ASC) RowNumber FROM %1$s t,DD_RetentionPeriod rp WHERE t.ParentNBXH=0 AND DATEDIFF(YEAR,t.SaveDate,GETDATE())>rp.TotalYears AND t.RetentionPeriodID=rp.ID AND t.RetentionPeriodID<>:RetentionPeriodID AND t.ArchivesTypeID=:ArchivesTypeID AND t.DestroyFlag=0 %2$s) t WHERE RowNumber BETWEEN :beginRow AND :endRow ORDER BY t.FormationDepartmentID ASC, t.SaveDate ASC";

	/**
	 * 鉴定管理->存毁鉴定	 查询所有过期档案信息的SQL语句(过滤卷内文件)
	 * %1$s 档案信息归档表名
	 * %2$s 档案形成部门条件
	 */
	private final String SQL_SELECT_SaveDestroyAppraisal_WithNoPage = "SELECT t.*,sai.StoreAddressFullName, 0 TotalYears FROM %1$s t,DD_RetentionPeriod rp,StoreroomArchivesInfo srai,StoreAddressInfo sai WHERE srai.ArchivesTypeID=t.ArchivesTypeID AND srai.NBXH=t.NBXH AND srai.ArchivesBoxBarcode=sai.ArchivesBoxBarcode AND DATEDIFF(YEAR,t.SaveDate,GETDATE())>rp.TotalYears AND t.RetentionPeriodID=rp.ID AND t.RetentionPeriodID<>:RetentionPeriodID AND t.ArchivesTypeID=:ArchivesTypeID AND t.DestroyFlag=0 %2$s  ORDER BY t.FormationDepartmentID ASC, t.SaveDate ASC, t.ArchivesID ASC";
	
	/**
	 * 鉴定管理->存毁鉴定	查询过期档案数量的SQL语句
	 * %1$s 档案信息归档表名
	 * %2$s 档案形成部门条件
	 */
	private final String SQL_SELECT_COUNT_SaveDestroyAppraisal = "SELECT COUNT(1) FROM %1$s t,DD_RetentionPeriod rp WHERE DATEDIFF(YEAR,t.SaveDate,GETDATE())>rp.TotalYears AND t.RetentionPeriodID=rp.ID AND t.RetentionPeriodID<>:RetentionPeriodID AND t.ArchivesTypeID=:ArchivesTypeID AND t.DestroyFlag=0 %2$s ";
	
	/**
	 * 鉴定管理->存毁鉴定	批量更新档案信息的保存期限 的SQL语句
	 * %1$s 更新的表名
	 */
	private final String SQL_BATCHUPDATE_RetentionPeriod = "UPDATE %1$s SET RetentionPeriodID=r.ID, RetentionPeriodText=r.Name FROM DD_RetentionPeriod r WHERE r.ID=? AND %1$s.NBXH=? AND %1$s.ArchivesTypeID=? ";
	
	/**
	 * 鉴定管理->存毁鉴定	批量销毁档案 的SQL语句
	 * %1$s 更新的表名
	 */
	private final String SQL_BATCHUPDATE_DESTORY = "UPDATE %1$s SET DestroyFlag=1 WHERE NBXH=? AND ArchivesTypeID=?";
	

	/**
<<<<<<< .mine
	 * 鉴定管理->存毁鉴定	批量修改库房档案信息表的馆藏状态	的SQL语句
	 */
	private final String SQL_BATCHUPDATE_StoreroomArchivesInfo = "UPDATE StoreroomArchivesInfo SET StoreStatus=4 WHERE NBXH=? AND ArchivesTypeID=?";
	
	/**
=======
	 * 查询指定内部序号的档案信息的SQL语句
	 */
	private final String SQL_SELECT_ByNBXH = "SELECT * FROM %1$s WHERE NBXH=?";

	/**
>>>>>>> .r815
	 * 鉴定管理->开放鉴定 	分页查询为开放的档案信息的SQL语句
	 * %1$s 档案信息归档表名
	 * %2$s 是否需要关联开放鉴定明细表
	 * %3$s 页面查询参数
	 */
	private final String SQL_SELECT_PublicAppraisal_WithPage = "SELECT * FROM ( SELECT t.*,ROW_NUMBER() OVER(ORDER BY t.NBXH DESC) RowNumber FROM %1$s t %2$s %3$s )t2 WHERE RowNumber BETWEEN :beginRow AND :endRow ORDER BY NBXH DESC";
	
	/**
	 * 鉴定管理->开放鉴定	查询档案数量的SQL语句
	 * %1$s 档案信息归档表名
	 * %2$s 是否需要关联开放鉴定明细表
	 * %3$s 页面查询参数
	 */
	private final String SQL_SELECT_COUNT_PublicAppraisal = "SELECT COUNT(DISTINCT t.NBXH) FROM %1$s t %2$s %3$s";
	
	/**
	 * 鉴定管理->开放鉴定	批量更新档案信息的开放标志 的SQL语句
	 * %1$s 更新的表名
	 * %2$s 开放标志
	 */
	private final String SQL_BATCHUPDATE_PublicAppraisal = "UPDATE %1$s SET PublicFlag=%2$s WHERE NBXH=? ";
	
	/**
	 * 鉴定管理->公开鉴定 	分页查询为公开的档案信息的SQL语句
	 * %1$s 档案信息归档表名
	 * %2$s 是否需要关联开放鉴定明细表
	 * %3$s 页面查询参数
	 */
	private final String SQL_SELECT_OpenAppraisal_WithPage = "SELECT * FROM( SELECT t.*,ROW_NUMBER() OVER(ORDER BY t.NBXH DESC) RowNumber FROM %1$s t %2$s %3$s )t1 WHERE RowNumber BETWEEN :beginRow AND :endRow ORDER BY NBXH DESC";
	
	/**
	 * 鉴定管理->公开鉴定	查询档案数量的SQL语句
	 * %1$s 档案信息归档表名
	 * %2$s 是否需要关联开放鉴定明细表
	 * %3$s 页面查询参数
	 */
	private final String SQL_SELECT_COUNT_OpenAppraisal = "select COUNT(DISTINCT t.NBXH) FROM %1$s t %2$s %3$s";
	
	/**
	 * UPDATE %1$s SET SecrecyID=%2$s WHERE NBXH=? 
	 * 	%2$s 取消公开时：(select distinct OldSecrecyID from AppraisalPublicDetails p,%1$s t
			where p.NBXH=t.NBXH and p.OldSecrecyID>1 and p.NewSecrecyID=1)
	 	
	 * 鉴定管理->公开鉴定	批量还原档案信息的密级信息 的SQL语句
	 * %1$s 更新的表名
	 * %2$s 档案密级
	 * %3$s 档案密级文本
	 */
	private final String SQL_BATCHUPDATE_OpenAppraisal = "UPDATE %1$s SET SecrecyID=%2$s,SecrecyText='%3$s' WHERE NBXH=? ";
	
	/**
	 * 鉴定管理->划控鉴定	分页查询为开放的档案信息的SQL语句
	 * %1$s 档案信息归档表名
	 * %2$s 页面查询参数
	 */
	private final String SQL_SELECT_ControlArea_WithPage = "SELECT * FROM (SELECT *,ROW_NUMBER() OVER(ORDER BY NBXH DESC) RowNumber FROM %1$s WHERE PublicFlag=0 %2$s )t WHERE RowNumber BETWEEN :beginRow AND :endRow ORDER BY NBXH DESC";
	
	/**
	 * 鉴定管理->划控鉴定	查询档案数量的SQL语句
	 * %1$s 档案信息归档表名
	 * %2$s 页面查询参数
	 */
	private final String SQL_SELECT_COUNT_ControlArea = "select COUNT(DISTINCT NBXH) FROM %1$s WHERE PublicFlag=0 %2$s ";
	

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#batchDelArchives(com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean batchDelArchives(ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#batchUpdateParentFlag(com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean batchUpdateParentFlag(ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#batchUpdateParentNBXH(int, com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean batchUpdateParentNBXH(int parentNBXH, ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#delete(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#deleteParentAndChild(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteParentAndChild(ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#findByNBXH(int, com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */

	@Override	
	public boolean findByNBXH(int pNBXH, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String archivesInfosavedTableName = "";
		String localSql = "";// 最终提交的SQL语句

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
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档信息表
					archivesInfosavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				// 将工作表名动态构建到SQL语句中去，注意jdbc不支持select top ? from
				// ?的动态构建，必须自行动态构建好SQL语句字符串
				localSql = String.format(SQL_SELECT_ByNBXH, archivesInfosavedTableName);

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<ArchivesInfo> pArchivesInfos = jdbcTemplate.query(localSql, new ArchivesInfoMapper(archivesType), pNBXH);

				// 返回查询结果
				if (pArchivesInfos.size() > 0)
				{
					archivesInfo.cloneFrom(pArchivesInfos.get(0));
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
	
	/**
	 * 根据案卷的内部序号查找其所有卷内文件信息的SQL语句
	 */
	private final String SQL_SELECT_ChildArchivesInfo_ByParentNBXH = "SELECT * FROM %1$s WHERE ParentNBXH=? ORDER BY SubContentID ASC";

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#findChildrenByNBXH(int, com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findChildrenByNBXH(int pNBXH, ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String archivesInfoSavedTableName = "";
		String localSql = "";// 最终提交的SQL语句

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
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档信息表
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				// 将工作表名动态构建到SQL语句中去，注意jdbc不支持select top ? from
				// ?的动态构建，必须自行动态构建好SQL语句字符串
				localSql = String.format(SQL_SELECT_ChildArchivesInfo_ByParentNBXH, archivesInfoSavedTableName);

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<ArchivesInfo> pArchivesInfos = jdbcTemplate.query(localSql, new ArchivesInfoMapper(archivesType), pNBXH);

				// 返回查询结果
				if (pArchivesInfos.size() > 0)
				{
					archivesInfos.addAll(pArchivesInfos);
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#findMaxSubContentID(com.orifound.aiim.entity.ArchivesType, int, com.orifound.aiim.entity.IntegerEx, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findMaxSubContentID(ArchivesType archivesType, int parentNBXH, IntegerEx maxSubContentID, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#queryClassified(com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
/*
	@Override
	public boolean queryClassified(UserInfo userInfo, List<ArchivesType> archivesTypes, List<ArchivesInfoQueryCondition> archivesInfoQueryConditions, DataPageInfo dataPageInfo,
			List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String archivesInfoSavedTableName = "";	// 具体档案分类对应的档案信息工作表名
		StringBuilder querySQL = new StringBuilder();// 查询条件的SQL片段
		String localSql = "";	// 最终提交的查询SQL语句
		MapSqlParameterSource paramSource = new MapSqlParameterSource(); // SQL的参数源对象
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		List<Integer> secrecyIDs = new ArrayList<Integer>(); //用户可访问的密级
		List<Integer> rolesIDs = new ArrayList<Integer>();//用户所属角色		
		
		try
		{
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查userInfo对象是否赋值
			if(userInfo == null){
				pFlag = false;
				pErrInfo.getContent().append("用户未初始化。");
			}else{
				secrecyIDs = new ArrayList<Integer>(userInfo.getArchivesSecrecys().keySet());
				if(secrecyIDs.size()==0){//如果没有值时，向列表加入一个0，以避免SQL出错
					secrecyIDs.add(new Integer(0));
				}
System.out.println("secrecyIDs" + userInfo.getArchivesSecrecys().keySet().toString());
				
				//获取角色信息
				for(int i=0;i<userInfo.getRolesIDs().length;i++){
					rolesIDs.add(userInfo.getRolesIDs()[i]);
				}
				//如没有角色信息加入默认值0，防止SQL异常
				if(rolesIDs.size()==0){
					rolesIDs.add(0);
				}
				
			}

			// 检查档案类型是否有赋值			
			if (archivesTypes.get(0) == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息未初始化。");
			}
			

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesTypes.get(0), pErrInfo) == false){
					pFlag = false;
				}else{
					// 保存当前分类的档案归档信息表
<<<<<<< .mine
					archivesInfoWorkingTableName = archivesTypes.get(0).getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
=======
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
>>>>>>> .r765
				}
			}

			// 检查数据分页对象是否为空
			if (pFlag)
			{
				if (dataPageInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("数据分页信息非法为空。");
				}
			}

			// 构造查询条件的SQL片段
			if (pFlag)
			{
				if (archivesInfoQueryConditions != null)
				{
					if (archivesInfoQueryConditions.size() > 0)
					{
						pErrPos = 2;
						if (getSqlForArchivesInfoInputQueryConditions(archivesInfoQueryConditions, querySQL, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "构造查询条件SQL片段失败; ");
						}
					}
				}
			}
			
			// 构造SQL的参数源
			if (pFlag)
			{
				paramSource.addValue("ArchivesTypeID", archivesTypes.get(0).getID(), Types.INTEGER);
				paramSource.addValue("SecrecyID", secrecyIDs);
				paramSource.addValue("RolesID",rolesIDs );
			}
		
			
//			 String SQL_SELECT_COUNT_By_Classified1 = "SELECT COUNT(NBXH) FROM %1$s"
//				+ " WHERE ArchivesTypeID=:ArchivesTypeID AND SecrecyID IN (:SecrecyID) AND  ParentNBXH=0 %2$s";
			 
				//%1$s:数据库表名;  %2$s：查询条件
				String SQL_SELECT_COUNT_By_Classified = "SELECT COUNT(NBXH) FROM  " +
					"(SELECT NBXH FROM %1$s WHERE PublicFlag=1 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"SELECT NBXH FROM %1$s  WHERE PublicFlag=0 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID AND SecrecyID IN (:SecrecyID) %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"  SELECT NBXH FROM %1$s A where ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s AND EXISTS  (" +
					"  SELECT NBXH FROM AppraisalUseScopesDetails B WHERE  ArchivesTypeID=:ArchivesTypeID AND A.NBXH=B.NBXH " +
					"  AND EXISTS ( SELECT * FROM AppraisalUseScopes_Roles D WHERE D.RolesID in(:RolesID) and D.ID=B.ID)" +
					"   ) ) AS BaseTable ";

			// 查询总记录数
			if (pFlag)
			{
				pErrPos = 3;

				// 工作表名以及查询条件动态构建到SQL语句中去，注意jdbc不支持select top ? from
				// ?的动态构建，必须自行动态构建好SQL语句字符串
				localSql = String.format(SQL_SELECT_COUNT_By_Classified, archivesInfoSavedTableName, querySQL.toString());
				
				long beginTime = new Date().getTime();	
				// 执行查询总记录数的SQL
				int pRowCount = namedParameterJdbcTemplate.queryForInt(localSql, paramSource);
				
				System.out.println("count_SQL use Time:" + (new Date().getTime()-beginTime)+"ms");
				// 设置分页对象中的总记录数
				dataPageInfo.setRowCount(pRowCount);
			}


//			String SQL_SELECT_By_Classified_WithPage1 = "SELECT TOP %1$s * FROM %3$s"
//				+ " WHERE ArchivesTypeID=:ArchivesTypeID AND SecrecyID IN (:SecrecyID) AND ParentNBXH=0 %4$s AND NBXH < "
//				+ "(SELECT ISNULL(MIN(NBXH),2147483647) FROM (SELECT TOP %2$s NBXH FROM %3$s"
//				+ " WHERE ArchivesTypeID=:ArchivesTypeID  AND SecrecyID IN (:SecrecyID) AND ParentNBXH=0 %4$s ORDER BY NBXH DESC) AS T) "
//				+ "ORDER BY NBXH DESC";
//			

			//%1$s:数据库表名;  %2$s:查询条件(以and开头 )；%3$s：页大小；<br>%4$s：已经读取过的记录数量<br> 
			String SQL_SELECT_By_Classified_WithPage =  "SELECT TOP %3$s * FROM  %1$s WHERE ParentNBXH=0 AND NBXH IN "
				+
					"(SELECT NBXH FROM %1$s WHERE PublicFlag=1 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"SELECT NBXH FROM %1$s  WHERE PublicFlag=0 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID AND SecrecyID IN (:SecrecyID) %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"  SELECT NBXH FROM %1$s A where ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s AND EXISTS  (" +
					"  SELECT NBXH FROM AppraisalUseScopesDetails B WHERE  ArchivesTypeID=:ArchivesTypeID AND A.NBXH=B.NBXH " +
					"  AND EXISTS ( SELECT * FROM AppraisalUseScopes_Roles D WHERE D.RolesID in(:RolesID) and D.ID=B.ID)" +
					"   ) )  "
				+
				" AND NBXH > (SELECT ISNULL(MAX(NBXH),0) FROM (SELECT TOP %4$s NBXH FROM %1$s WHERE NBXH IN "
					+
					"(SELECT NBXH FROM %1$s WHERE PublicFlag=1 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"SELECT NBXH FROM %1$s  WHERE PublicFlag=0 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID AND SecrecyID IN (:SecrecyID) %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"  SELECT NBXH FROM %1$s A where ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s AND EXISTS  (" +
					"  SELECT NBXH FROM AppraisalUseScopesDetails B WHERE  ArchivesTypeID=:ArchivesTypeID AND A.NBXH=B.NBXH " +
					"  AND EXISTS ( SELECT * FROM AppraisalUseScopes_Roles D WHERE D.RolesID in(:RolesID) and D.ID=B.ID)" +
					"   ) )  "
					+
				" ORDER BY NBXH ASC) AS T) "
				+ "ORDER BY NBXH ASC";			

			// 构造查询档案信息的SQL语句
			if (pFlag){
				pErrPos = 4;
				// 将分页大小、工作表名以及查询条件动态构建到SQL语句中去，注意jdbc不支持select top ? from
				// ?的动态构建，必须自行动态构建好SQL语句字符串
				localSql = String.format(SQL_SELECT_By_Classified_WithPage,archivesInfoSavedTableName, querySQL.toString(), dataPageInfo.getPageSize(), (dataPageInfo.getCurrentPage() - 1) * dataPageInfo.getPageSize());
System.out.println("localSql:"+ localSql);
			}

			// 执行查询档案信息的SQL语句并返回结果
			if (pFlag){
				pErrPos = 5;
				
long beginTime = new Date().getTime();				
				List<ArchivesInfo> pArchivesInfos = namedParameterJdbcTemplate.query(localSql, paramSource, new ArchivesInfoMapper(archivesTypes.get(0)));
System.out.println("find_SQL use Time:" + (new Date().getTime()-beginTime)+"ms");
				// 返回查询结果
				if (pArchivesInfos.size() > 0)
				{
					archivesInfos.addAll(pArchivesInfos);
				}
				// 销毁局部变量
				pArchivesInfos = null;
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
			paramSource = null;
			namedParameterJdbcTemplate = null;
		}
		return pFlag;
	}
	*/
	
	
	/**
	 * 分类查询<br>
	 * 根据档案分类+用户可访问的密级+查询条件+已鉴定使用范围
	 * @param archivesType
	 * @param archivesInfoQueryConditions_SQL 传入查询条件
	 * @param dataPageInfo
	 * @param archivesInfos
	 * @param pErrInfo
	 * @return
	 */
	public boolean queryClassified(UserInfo userInfo, List<ArchivesType> archivesTypes, String archivesInfoQueryConditions_SQL, DataPageInfo dataPageInfo,
			List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String archivesInfoSavedTableName = "";	// 具体档案分类对应的档案信息工作表名
		String localSql = "";	// 最终提交的查询SQL语句
		MapSqlParameterSource paramSource = new MapSqlParameterSource(); // SQL的参数源对象
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		List<Integer> secrecyIDs = new ArrayList<Integer>(); 
		List<Integer> rolesIDs = new ArrayList<Integer>();
		
		try
		{
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			// 检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesTypes.get(0) == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息未初始化。");
				}
			}
			
			//检查userInfo对象是否赋值
			if(userInfo == null){
				pFlag = false;
				pErrInfo.getContent().append("用户未初始化");
			}else{
				//获取密级和角色信息
				//密级
				secrecyIDs = new ArrayList<Integer>(userInfo.getArchivesSecrecys().keySet());
				if(secrecyIDs.size()==0){//如果没有值时，向列表加入一个0，以避免SQL出错
						secrecyIDs.add(0);
				}
				//获取角色信息
				for(int i=0;i<userInfo.getRolesIDs().length;i++){
					rolesIDs.add(userInfo.getRolesIDs()[i]);
				}
				//如没有角色信息加入默认值0，防止SQL异常
				if(rolesIDs.size()==0){
					rolesIDs.add(0);
				}				
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesTypes.get(0), pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表

					archivesInfoSavedTableName = archivesTypes.get(0).getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();


				}
			}

			// 检查数据分页对象是否为空
			if (pFlag)
			{
				if (dataPageInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("数据分页信息非法为空。");
				}
			}

				
			// 构造SQL的参数源
			if (pFlag)
			{
				List<Integer> types = new ArrayList<Integer>();
				for (ArchivesType item : archivesTypes) {
					types.add(item.getID());
				}
				
				paramSource.addValue("ArchivesTypeID", types);
				paramSource.addValue("SecrecyID", secrecyIDs);
				paramSource.addValue("RolesID",rolesIDs);
			}

			
	
			//%1$s:数据库表名;  %2$s：查询条件
			String SQL_SELECT_COUNT_By_Classified = "SELECT COUNT(NBXH) FROM  " +
				"(" +
					"SELECT NBXH FROM %1$s WHERE PublicFlag=1 AND ParentNBXH=0 AND ArchivesTypeID IN (:ArchivesTypeID) %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"SELECT NBXH FROM %1$s  WHERE PublicFlag=0 AND ParentNBXH=0 AND ArchivesTypeID IN (:ArchivesTypeID) AND SecrecyID IN (:SecrecyID) %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"  SELECT NBXH FROM %1$s A where ParentNBXH=0 AND ArchivesTypeID IN (:ArchivesTypeID) %2$s AND EXISTS  (" +
					"  SELECT NBXH FROM AppraisalUseScopesDetails B WHERE  ArchivesTypeID IN (:ArchivesTypeID) AND A.NBXH=B.NBXH " +
					"  AND EXISTS ( SELECT * FROM AppraisalUseScopes_Roles D WHERE D.RolesID IN(:RolesID) AND D.ID=B.ID))" +
				" ) AS BaseTable ";
			

			//%1$s:数据库表名;  %2$s:查询条件(以and开头 )；%3$s：起始值；<br>%4$s：结束值<br> 
			String SQL_SELECT_By_Classified_WithPage =" SELECT * FROM ("+
						"SELECT * ,ROW_NUMBER() over(order by NBXH) AS RowNum FROM %1$s WHERE NBXH IN  "+
							"(" +
								"SELECT NBXH FROM %1$s WHERE PublicFlag=1 AND ParentNBXH=0 AND ArchivesTypeID IN (:ArchivesTypeID) %2$s" +
								" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
								" UNION " +
								"SELECT NBXH FROM %1$s  WHERE PublicFlag=0 AND ParentNBXH=0 AND ArchivesTypeID IN (:ArchivesTypeID) AND SecrecyID IN (:SecrecyID) %2$s" +
								" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
								" UNION " +
								"  SELECT NBXH FROM %1$s A where ParentNBXH=0 AND ArchivesTypeID IN (:ArchivesTypeID) %2$s AND EXISTS  (" +
								"  SELECT NBXH FROM AppraisalUseScopesDetails B WHERE  ArchivesTypeID IN (:ArchivesTypeID) AND A.NBXH=B.NBXH " +
								"  AND EXISTS ( SELECT * FROM AppraisalUseScopes_Roles D WHERE D.RolesID IN(:RolesID) AND D.ID=B.ID))" +
							")"+
						"  )TEMPTABLE WHERE RowNum BETWEEN %3$s AND %4$s ";
			
			
			
		/*	
=======
			// 查询总记录数
			if (pFlag)
			{
				pErrPos = 3;
				// ?的动态构建，必须自行动态构建好SQL语句字符串
				localSql = String.format(SQL_SELECT_COUNT_By_Classified, archivesInfoSavedTableName, archivesInfoQueryConditions_SQL);

long beginTime = new Date().getTime();				
				// 执行查询总记录数的SQL
				int pRowCount = namedParameterJdbcTemplate.queryForInt(localSql, paramSource);
System.out.println("find_SQL use Time:" + (new Date().getTime()-beginTime)+"ms");	

				// 设置分页对象中的总记录数
				dataPageInfo.setRowCount(pRowCount);
			}

>>>>>>> .r765
			//%1$s:数据库表名;  %2$s:查询条件(以and开头 )；%3$s：页大小；<br>%4$s：已经读取过的记录数量<br> 
			String SQL_SELECT_By_Classified_WithPage_TOP =  "SELECT TOP %3$s * FROM  %1$s WHERE ParentNBXH=0 AND NBXH IN "
				+
					"(SELECT NBXH FROM %1$s WHERE PublicFlag=1 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"SELECT NBXH FROM %1$s  WHERE PublicFlag=0 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID AND SecrecyID IN (:SecrecyID) %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"  SELECT NBXH FROM %1$s A where ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s AND EXISTS  (" +
					"  SELECT NBXH FROM AppraisalUseScopesDetails B WHERE  ArchivesTypeID=:ArchivesTypeID AND A.NBXH=B.NBXH " +
					"  AND EXISTS ( SELECT * FROM AppraisalUseScopes_Roles D WHERE D.RolesID in(:RolesID) and D.ID=B.ID)" +
					"   ) )  "
				+
				" AND NBXH > (SELECT ISNULL(MAX(NBXH),0) FROM (SELECT TOP %4$s NBXH FROM %1$s WHERE NBXH IN "
					+
					"(SELECT NBXH FROM %1$s WHERE PublicFlag=1 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"SELECT NBXH FROM %1$s  WHERE PublicFlag=0 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID AND SecrecyID IN (:SecrecyID) %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"  SELECT NBXH FROM %1$s A where ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s AND EXISTS  (" +
					"  SELECT NBXH FROM AppraisalUseScopesDetails B WHERE  ArchivesTypeID=:ArchivesTypeID AND A.NBXH=B.NBXH " +
					"  AND EXISTS ( SELECT * FROM AppraisalUseScopes_Roles D WHERE D.RolesID in(:RolesID) and D.ID=B.ID)" +
					"   ) )  "
					+
				" ORDER BY NBXH ASC) AS T) "
				+ "ORDER BY NBXH ASC";
					
			*/
			
			// 查询总记录数
			if (pFlag)
			{
				pErrPos = 3;			
				localSql = String.format(SQL_SELECT_COUNT_By_Classified, archivesInfoSavedTableName, archivesInfoQueryConditions_SQL);
long beginTime = new Date().getTime();				
				// 执行查询总记录数的SQL
				int pRowCount = namedParameterJdbcTemplate.queryForInt(localSql, paramSource);
System.out.println("queryForInt use Time:" + (new Date().getTime()-beginTime)+"ms");	

				// 设置分页对象中的总记录数
				dataPageInfo.setRowCount(pRowCount);
			}

			
			
			
			// 构造查询档案信息的SQL语句
			if (pFlag)
			{
				pErrPos = 4;			

				// ?的动态构建，必须自行动态构建好SQL语句字符串(1-1)*5+1 AND 1*5
				localSql = String.format(SQL_SELECT_By_Classified_WithPage,archivesInfoSavedTableName, archivesInfoQueryConditions_SQL, (dataPageInfo.getCurrentPage() - 1) * dataPageInfo.getPageSize()+1, dataPageInfo.getCurrentPage()* dataPageInfo.getPageSize());

			}

			// 执行查询档案信息的SQL语句并返回结果
			if (pFlag)
			{
				pErrPos = 5;
				System.out.println("localSql-2: " + localSql);
long beginTime1 = new Date().getTime();	
				List<ArchivesInfo> pArchivesInfos = namedParameterJdbcTemplate.query(localSql, paramSource, new ArchivesInfoMapper(archivesTypes.get(0)));
System.out.println("query Info use Time:" + (new Date().getTime()-beginTime1)+"ms");	
				// 返回查询结果
				if (pArchivesInfos.size() > 0)
				{
					archivesInfos.addAll(pArchivesInfos);
				}
				// 销毁局部变量
				pArchivesInfos = null;
				namedParameterJdbcTemplate = null;
				paramSource = null;
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
			paramSource = null;
			namedParameterJdbcTemplate = null;
		}
		return pFlag;	
	}
	
	@Override
	public boolean queryCountByClassified(UserInfo userInfo,ArchivesType archivesType,StringBuilder querySQL,IntegerEx countNum,ErrInfo pErrInfo){
	
			boolean pFlag = true;
			int pErrPos = 0;
			Throwable throwable = new Throwable();
			String archivesInfoSavedTableName = "";	// 具体档案分类对应的档案信息工作表名			
			String localSql = "";	// 最终提交的查询SQL语句
			MapSqlParameterSource paramSource = new MapSqlParameterSource(); // SQL的参数源对象
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
			List<Integer> secrecyIDs = new ArrayList<Integer>(); 
			List<Integer> rolesIDs = new ArrayList<Integer>();//用户所属角色列表 
					
			try
			{
				pErrPos = 1;
				// 检查JDBC数据源是否正确依赖注入
				if (CheckDataSourceInjection(pErrInfo) == false)
				{
					pFlag = false;
				}

				
				// 检查档案类型是否有赋值
				if (pFlag)
				{pErrPos = 2;
					if (archivesType == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类信息未初始化。");
					}
				}
				
				if (pFlag) {
					pErrPos = 3;
					//检查userInfo对象是否赋值
					if(userInfo == null){
						pFlag = false;
						pErrInfo.getContent().append("用户未初始化");
					}
				}
				
				if (pFlag) {
					pErrPos = 4;
					//获取用户可访问的密级信息
					secrecyIDs = new ArrayList<Integer>(userInfo.getArchivesSecrecys().keySet());
					if(secrecyIDs.size()==0){//如果没有值时，向列表加入一个0，以避免SQL出错
						secrecyIDs.add(new Integer(0));
					}
					
					//获取用户角色信息
					rolesIDs = new ArrayList<Integer>();
					rolesIDs.add(0);//默认加一个0，保证当用户不属于角色时不会出SQL异常
					for(int i = 0;i<userInfo.getRolesIDs().length;i++){
						rolesIDs.add(userInfo.getRolesIDs()[i]);
					}
				}

				
				// 检查档案分类对应的工作表名称是否有值
				if (pFlag){
					pErrPos = 4;
					if (checkTableName(archivesType, pErrInfo) == false){
						pFlag = false;
					}else{
						// 保存当前分类的档案归档工作表
						archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
					}
				}
					
				pErrPos = 5;
				// 构造SQL的参数源
				if (pFlag)
				{
					paramSource.addValue("ArchivesTypeID", archivesType.getID(), Types.INTEGER);
					paramSource.addValue("SecrecyID", secrecyIDs);	
					paramSource.addValue("RolesID", rolesIDs);
				}
				

			   
//			   String SQL_SELECT_COUNT_By_Classified1 = "SELECT COUNT(NBXH) FROM (SELECT NBXH FROM %1$s WHERE PublicFlag=1 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s" +
//				" UNION  " +
//				"SELECT NBXH FROM %1$s  WHERE PublicFlag=0 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID AND SecrecyID IN (:SecrecyID) %2$s" +
//				" UNION  " +
//				"  SELECT NBXH FROM %1$s A where ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s AND EXISTS  (" +
//				"  SELECT NBXH FROM AppraisalUseScopesDetails B WHERE A.NBXH=B.NBXH" +
//				"  AND EXISTS ( SELECT * FROM AppraisalUseScopes_Roles D WHERE D.RolesID in(:RolesID) and D.ID=B.ID)" +
//				"   ) ) AS BaseTable ";
			 //%1$s:数据库表名;  %2$s:查询条件(以and开头 )
//				String SQL_SELECT_COUNT_By_Classified = "SELECT COUNT(NBXH) FROM (SELECT NBXH FROM %1$s WHERE PublicFlag=1 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s" +
//				" UNION  " +
//				"SELECT NBXH FROM %1$s  WHERE PublicFlag=0 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID AND SecrecyID IN (:SecrecyID) %2$s" +
//				" UNION  " +
//				"  SELECT NBXH FROM %1$s A where ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s AND EXISTS  (" +
//				"  SELECT NBXH FROM AppraisalUseScopesDetails B WHERE  ArchivesTypeID=:ArchivesTypeID AND A.NBXH=B.NBXH " +
//				"  AND EXISTS ( SELECT * FROM AppraisalUseScopes_Roles D WHERE D.RolesID in(:RolesID) and D.ID=B.ID)" +
//				"   ) ) AS BaseTable ";
			   
				//%1$s:数据库表名;  %2$s：查询条件
				String SQL_SELECT_COUNT_By_Classified = "SELECT COUNT(NBXH) FROM  " +
					"(SELECT NBXH FROM %1$s WHERE PublicFlag=1 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"SELECT NBXH FROM %1$s  WHERE PublicFlag=0 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID AND SecrecyID IN (:SecrecyID) %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"  SELECT NBXH FROM %1$s A where ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s AND EXISTS  (" +
					"  SELECT NBXH FROM AppraisalUseScopesDetails B WHERE  ArchivesTypeID=:ArchivesTypeID AND A.NBXH=B.NBXH " +
					"  AND EXISTS ( SELECT * FROM AppraisalUseScopes_Roles D WHERE D.RolesID in(:RolesID) and D.ID=B.ID)" +
					"   ) ) AS BaseTable ";
			   
				// 查询总记录数
				if (pFlag){
					pErrPos = 6;					
					// ?的动态构建，必须自行动态构建好SQL语句字符串
					localSql = String.format(SQL_SELECT_COUNT_By_Classified, archivesInfoSavedTableName, querySQL);
System.out.println("localSql:" + localSql);
					// 执行查询总记录数的SQL
					int pRowCount = namedParameterJdbcTemplate.queryForInt(localSql, paramSource);	
System.out.println("通过NBXH来取分类的"+ archivesType.getName()+"档案数量"+pRowCount);
					countNum.setValue(pRowCount);					
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
				paramSource = null;
				namedParameterJdbcTemplate = null;
			}
			return pFlag;	
		
	}
	

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#save(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(ArchivesType archivesType, int NBXH, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoSavedTableName = "";
		String archivesInfoWorkingTableName = "";
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag){
				pErrPos = 4;
				if (checkTableName(archivesType, pErrInfo) == false){
					pFlag = false;
				}else{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				String sql = "INSERT INTO %1$s SELECT * FROM %2$s T1 WHERE T1.NBXH=?";
				sql = String.format(sql, archivesInfoSavedTableName,archivesInfoWorkingTableName);
				getJdbcTemplate().update(sql,NBXH);
				
				sql = "INSERT INTO %1$s SELECT * FROM %2$s T1 WHERE T1.ParentNBXH=?";
				sql = String.format(sql, archivesInfoSavedTableName,archivesInfoWorkingTableName);
				getJdbcTemplate().update(sql,NBXH);
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#update(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#updateSubContentCountAndPageSum(com.orifound.aiim.entity.ArchivesType, int, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateSubContentCountAndPageSum(ArchivesType archivesType, int parentNBXH, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
////////////////// 工具方法   ///////////////////////////	
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
	
	
	/**
	 * 构造档案信息著录查询条件的SQL片段
	 * 
	 * @param archivesInfoQueryConditions
	 *            档案著录查询条件集合
	 * @param querySQL
	 *            返回构造好的查询条件SQL片段
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private static boolean getSqlForArchivesInfoInputQueryConditions(List<ArchivesInfoQueryCondition> archivesInfoQueryConditions, StringBuilder querySQL, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//如果没有数据就返回空串
			if(archivesInfoQueryConditions== null ){
				System.out.println("archivesInfoQueryConditions--null");
				return true;
			}else if(archivesInfoQueryConditions.size()==0){
				System.out.println("archivesInfoQueryConditions--size=0;");
				return true;
			}else{
				for (ArchivesInfoQueryCondition item : archivesInfoQueryConditions)
				{
					// 如果是范围查询，则构造最小值和最大值条件
					if (item.getDataItem().getRangeQueryFlag())
					{
						// 无需单引号的情况
						if (item.getDataItem().getColumnDataType() == EnumColumnDataType.实数 || item.getDataItem().getColumnDataType() == EnumColumnDataType.整数
								|| item.getDataItem().getColumnDataType() == EnumColumnDataType.布尔值)
						{
							// 拼接逻辑运行符 AND OR
							querySQL.append(item.getIsAND() ? " AND " : " OR ");
							// 拼接字段名
							querySQL.append(item.getDataItem().getColumnName());
							// 拼接条件范围
							querySQL.append(" BETWEEN ");
							querySQL.append(item.getMinValue());
							querySQL.append(" AND ");
							querySQL.append(item.getMaxValue());
						}
						// 需要单引号的情况
						else if (item.getDataItem().getColumnDataType() == EnumColumnDataType.日期时间 || item.getDataItem().getColumnDataType() == EnumColumnDataType.字符串
								|| item.getDataItem().getColumnDataType() == EnumColumnDataType.文本)
						{
							// 拼接逻辑运行符 AND OR
							querySQL.append(item.getIsAND() ? " AND " : " OR ");
							// 拼接字段名
							querySQL.append(item.getDataItem().getColumnName());
							// 拼接条件范围
							querySQL.append(" BETWEEN ");
							querySQL.append("'");
							querySQL.append(item.getMinValue());
							querySQL.append("'");
							querySQL.append(" AND ");
							querySQL.append("'");
							querySQL.append(item.getMaxValue());
							querySQL.append("'");
						}
					}
					// 如果不是范围查询，则构造值条件
					else
					{
						// 无需单引号的情况
						if (item.getDataItem().getColumnDataType() == EnumColumnDataType.实数 || item.getDataItem().getColumnDataType() == EnumColumnDataType.整数
								|| item.getDataItem().getColumnDataType() == EnumColumnDataType.布尔值)
						{
							// 拼接逻辑运行符 AND OR
							querySQL.append(item.getIsAND() ? " AND " : " OR ");
							// 拼接字段名
							querySQL.append(item.getDataItem().getColumnName());
							// 拼接条件
							querySQL.append(" = "); // 数字类型缺省使用精确匹配
							querySQL.append(item.getValue());
						}
						// 需要单引号的情况
						else if (item.getDataItem().getColumnDataType() == EnumColumnDataType.日期时间 || item.getDataItem().getColumnDataType() == EnumColumnDataType.字符串
								|| item.getDataItem().getColumnDataType() == EnumColumnDataType.文本)
						{
							// 拼接逻辑运行符 AND OR
							querySQL.append(item.getIsAND() ? " AND " : " OR ");
							// 拼接字段名
							querySQL.append(item.getDataItem().getColumnName());
							// 拼接条件
							if (item.getDataItem().getColumnDataType() == EnumColumnDataType.日期时间)
							{
								querySQL.append(" = "); // 日期类型缺省使用精确匹配
								querySQL.append("'");
								querySQL.append(item.getValue());
								querySQL.append("'");
							}
							else
							{
								querySQL.append(" LIKE "); // 字符类型缺省使用模糊匹配
								querySQL.append("'%");
								querySQL.append(item.getValue());
								querySQL.append("%'");
							}
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
	public boolean queryClassifiedForSaveDestroyAppraisal(ArchivesType archivesType, int formationDepartmentID,
										DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
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
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				
				//档案归档信息表名
				String archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
				
				String formationDepartmentIDParam = "";
				if(formationDepartmentID >= 1) {
					formationDepartmentIDParam = "AND t.FormationDepartmentID=:FormationDepartmentID";
					paramSource.addValue("FormationDepartmentID", formationDepartmentID);
				}
				
				//过滤永久保管期限不过期
				paramSource.addValue("RetentionPeriodID", SystemInitializer.getInstance().getForeverRetentionPeriod().getID(),Types.INTEGER);
				paramSource.addValue("ArchivesTypeID", archivesType.getID(),Types.INTEGER);
				
//				SELECT t.* FROM %1$s t,DD_RetentionPeriod rp WHERE DATEDIFF(YEAR,t.SaveDate,GETDATE())>rp.TotalYears AND t.RetentionPeriodID=rp.ID AND t.RetentionPeriodID<>:RetentionPeriodID AND t.ArchivesTypeID=:ArchivesTypeID AND t.DestroyFlag=0 %2$s  ORDER BY t.FormationDepartmentID ASC, t.SaveDate ASC
				
				List<ArchivesInfo> pArchivesInfos = null;
				if(dataPageInfo == null || dataPageInfo.getPageSize()<=0) {	//不支持分页查询
					
					pArchivesInfos = namedParameterJdbcTemplate.query(String.format(SQL_SELECT_SaveDestroyAppraisal_WithNoPage, archivesInfoSavedTableName, formationDepartmentIDParam), paramSource, new ArchivesInfoMapperOther(archivesType));
					
					System.out.println("存毁鉴定查询："+String.format(SQL_SELECT_SaveDestroyAppraisal_WithNoPage, archivesInfoSavedTableName, formationDepartmentIDParam));
				
				} else {	//支持分页查询
					
					//查询过期档案的总数
					int count = namedParameterJdbcTemplate.queryForInt(String.format(SQL_SELECT_COUNT_SaveDestroyAppraisal, archivesInfoSavedTableName, formationDepartmentIDParam), paramSource);
					dataPageInfo.setRowCount(count);
					
					//分页参数
					paramSource.addValue("beginRow", dataPageInfo.getBeginRow() ,Types.INTEGER);
					paramSource.addValue("endRow", dataPageInfo.getEndRow() ,Types.INTEGER);
					//分页查询过期档案
					pArchivesInfos = namedParameterJdbcTemplate.query(String.format(SQL_SELECT_SaveDestroyAppraisal_WithPage, archivesInfoSavedTableName, formationDepartmentIDParam), paramSource, new ArchivesInfoMapperOther(archivesType));
				
					System.out.println("存毁鉴定->查询过期档案："+String.format(SQL_SELECT_SaveDestroyAppraisal_WithPage, archivesInfoSavedTableName, formationDepartmentIDParam));
				}
				
				//判断是否有记录集
				if (pArchivesInfos.size() >= 1) {
					archivesInfos.addAll(pArchivesInfos);
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
	public boolean updateBatchRetentionPeriod(ArchivesType archivesType, final Map<Integer[], Integer> saveArchives, final List<Integer[]> destoryArchives, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String archivesInfoSavedTableName = null;
		
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//档案归档信息表名
			archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				BatchPreparedStatementSetter setter = null;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				
				//判断是否有需要延长保存期限的档案
				if(saveArchives !=null && saveArchives.keySet().size()>=1 && saveArchives.values().size()>=1) {
					
					setter = new BatchPreparedStatementSetter() {
						List<Integer[]> listInteger = new ArrayList<Integer[]>(saveArchives.keySet());
						@Override
						public void setValues(PreparedStatement ps, int i) throws SQLException {
							ps.setInt(1, saveArchives.get(listInteger.get(i)));
							ps.setInt(2, listInteger.get(i)[1]);
							ps.setInt(3, listInteger.get(i)[0]);
						}

						@Override
						public int getBatchSize() {
							return saveArchives.keySet().size();
						}
					};
					//批量更新 档案的保存期限
					jdbcTemplate.batchUpdate(String.format(SQL_BATCHUPDATE_RetentionPeriod, archivesInfoSavedTableName), setter);
				}
				
				//判断是否存在 档案进行销毁
				if(destoryArchives!=null && destoryArchives.size()>=1) {
					setter = new BatchPreparedStatementSetter() {
						
						@Override
						public void setValues(PreparedStatement ps, int i) throws SQLException {
							ps.setInt(1, destoryArchives.get(i)[1]);
							ps.setInt(2, destoryArchives.get(i)[0]);
						}

						@Override
						public int getBatchSize() {
							return destoryArchives.size();
						}
					};
					//批量更新档案销毁标志
					//UPDATE %1$s SET DestroyFlag=1 WHERE NBXH=? AND ArchivesTypeID=?
					jdbcTemplate.batchUpdate(String.format(SQL_BATCHUPDATE_DESTORY, archivesInfoSavedTableName), setter);
					
					System.out.println("批量更新档案销毁标志="+String.format(SQL_BATCHUPDATE_DESTORY, archivesInfoSavedTableName));
					
					//批量更新库房档案信息馆藏状态 
					//UPDATE StoreroomArchivesInfo SET StoreStatus=4 WHERE NBXH=? AND ArchivesTypeID=?
					jdbcTemplate.batchUpdate(SQL_BATCHUPDATE_StoreroomArchivesInfo, setter);
					
					System.out.println("批量更新库房档案信息馆藏状态 ="+SQL_BATCHUPDATE_StoreroomArchivesInfo);
				}

				//销毁局部变量
				setter = null;
				jdbcTemplate = null;
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
	public boolean findByBarcode(ArchivesType archivesType, String barcode, ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoSavedTableName = "";
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag){
				pErrPos = 4;
				if (checkTableName(archivesType, pErrInfo) == false){
					pFlag = false;
				}else{
					// 保存当前分类的档案归档工作表
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				String sql = "SELECT * FROM %1$s WHERE Barcode=?";
				sql = String.format(sql, archivesInfoSavedTableName);
				List<ArchivesInfo> archivesInfos = getJdbcTemplate().query(sql, new ArchivesInfoMapper(archivesType),barcode);
				if(archivesInfos.size()>0){
					archivesInfo.cloneFrom(archivesInfos.get(0));
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
	public boolean updateArchivesBarcode(String archivesID, ArchivesType archivesType, String barcode, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoSavedTableName = "";
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag){
				pErrPos = 4;
				if (checkTableName(archivesType, pErrInfo) == false){
					pFlag = false;
				}else{
					// 保存当前分类的档案归档工作表
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				String sql = "UPDATE %1$s SET Barcode=? WHERE ArchivesID=?";
				sql = String.format(sql, archivesInfoSavedTableName);
				int rowCount = getJdbcTemplate().update(sql,barcode,archivesID);
				if(rowCount == 0){
					pFlag = false;
					pErrInfo.getContent().append("没有这卷档案");
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
	public boolean findByArchivesID(String archivesID, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoSavedTableName = "";
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag){
				pErrPos = 4;
				if (checkTableName(archivesType, pErrInfo) == false){
					pFlag = false;
				}else{
					// 保存当前分类的档案归档工作表
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				String sql = "SELECT * FROM %1$s WHERE ArchivesID=?";
				sql = String.format(sql, archivesInfoSavedTableName);
				List<ArchivesInfo> archivesInfos = getJdbcTemplate().query(sql,new Object[]{archivesID},new ArchivesInfoMapper(archivesType));
				if(archivesInfos.size() > 0){
					archivesInfo.cloneFrom(archivesInfos.get(0));
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
	public boolean queryClassifiedForPublicAppraisal(UserInfo userInfo, ArchivesType archivesType,
			List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,String isPublic,
			DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String archivesInfoSavedTableName = null;
		StringBuilder querySQL = new StringBuilder();
		
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			// 检查档案类型是否有赋值			
			if (archivesType == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息未初始化。");
			}
			

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false){
					pFlag = false;
				}else{
					// 保存当前分类的档案归档信息表
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
				}
			}

			// 检查数据分页对象是否为空
			if (pFlag)
			{
				if (dataPageInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("数据分页信息非法为空。");
				}
			}

			// 构造查询条件的SQL片段
			if (pFlag)
			{
				if (archivesInfoQueryConditions != null)
				{
					if (archivesInfoQueryConditions.size() > 0)
					{
						pErrPos = 2;
						if (getSqlForArchivesInfoInputQueryConditions(archivesInfoQueryConditions, querySQL , pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "构造查询条件SQL片段失败：");
						}
					}
				}
			}
			
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			
			//设置 档案密级为公开条件
			ArchivesSecrecy openArchivesSecrecy = SystemInitializer.getInstance().getOpenArchivesSecrecy();
			
			//关联公开鉴定明细表 字符串
			String associateTable = "";
			//判断是否关联公开鉴定明细表
			if("0".equals(isPublic)) {
				//查询密级是公开、未开放的档案
				associateTable = " WHERE SecrecyID=:SecrecyID AND PublicFlag=0 ";
				paramSource.addValue("SecrecyID", openArchivesSecrecy.getID());
			} else {
				//查询当前用户开放鉴定、新的密级是公开的、已经开放档案
				associateTable = " ,AppraisalPublicDetails p WHERE p.NBXH=t.NBXH  AND p.NewSecrecyID=:NewSecrecyID AND p.PublicFlag=1 AND t.PublicFlag=1 ";
				paramSource.addValue("NewSecrecyID", openArchivesSecrecy.getID());
			}
			
			//执行查询
			if (pFlag) {
				List<ArchivesInfo> pArchivesInfos = null;
					
				//查询档案的总数
				int count = namedParameterJdbcTemplate.queryForInt(String.format(SQL_SELECT_COUNT_PublicAppraisal, archivesInfoSavedTableName, associateTable, querySQL.toString()), paramSource);
				
				System.out.println("COUNT="+String.format(SQL_SELECT_COUNT_PublicAppraisal, archivesInfoSavedTableName, associateTable, querySQL.toString()));
				
				dataPageInfo.setRowCount(count);
				
				//分页参数
				paramSource.addValue("beginRow", dataPageInfo.getBeginRow() ,Types.INTEGER);
				paramSource.addValue("endRow", dataPageInfo.getEndRow() ,Types.INTEGER);
				//分页查询档案
				pArchivesInfos = namedParameterJdbcTemplate.query(String.format(SQL_SELECT_PublicAppraisal_WithPage, archivesInfoSavedTableName, associateTable, querySQL.toString()), paramSource, new ArchivesInfoMapper(archivesType));
				
				System.out.println("list="+String.format(SQL_SELECT_PublicAppraisal_WithPage, archivesInfoSavedTableName, associateTable, querySQL.toString()));
				
				//判断是否有记录集
				if (pArchivesInfos.size() >= 1) {
					archivesInfos.addAll(pArchivesInfos);
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
	public boolean queryClassifiedForOpenAppraisal(UserInfo userInfo,
			ArchivesType archivesType,
			List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,
			String isOpen, DataPageInfo dataPageInfo,
			List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String archivesInfoSavedTableName = null;
		StringBuilder querySQL = new StringBuilder();
		
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			// 检查档案类型是否有赋值			
			if (archivesType == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息未初始化。");
			}
			

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false){
					pFlag = false;
				}else{
					// 保存当前分类的档案归档信息表
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
				}
			}

			// 检查数据分页对象是否为空
			if (pFlag)
			{
				if (dataPageInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("数据分页信息非法为空。");
				}
			}

			// 构造查询条件的SQL片段
			if (pFlag)
			{
				if (archivesInfoQueryConditions != null)
				{
					if (archivesInfoQueryConditions.size() > 0)
					{
						pErrPos = 2;
						if (getSqlForArchivesInfoInputQueryConditions(archivesInfoQueryConditions, querySQL , pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "构造查询条件SQL片段失败; ");
						}
					}
				}
			}
			
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			
			//设置 档案密级为公开条件
			ArchivesSecrecy openArchivesSecrecy = SystemInitializer.getInstance().getOpenArchivesSecrecy();
			
			//关联公开鉴定明细表 字符串
			String associateTable = "";
			//判断是否关联公开鉴定明细表
			if("0".equals(isOpen)) {
				//密级公开以上、未开放的档案
				associateTable = " WHERE SecrecyID>:SecrecyID AND PublicFlag=0 ";
				paramSource.addValue("SecrecyID", openArchivesSecrecy.getID());
			} else {
				//查询当前用户公开鉴定、新的密级为公开的、未开放的档案
				associateTable = " ,AppraisalPublicDetails p WHERE p.NBXH=t.NBXH AND p.NewSecrecyID=:NewSecrecyID AND p.OldSecrecyID>:NewSecrecyID AND p.PublicFlag=0 AND t.SecrecyID=:NewSecrecyID ";
				paramSource.addValue("ManagerUserID", userInfo.getUserID());
				paramSource.addValue("NewSecrecyID", openArchivesSecrecy.getID());
			}
			
			//执行查询
			if (pFlag) {
				List<ArchivesInfo> pArchivesInfos = null;
				//查询档案的总数
				int count = namedParameterJdbcTemplate.queryForInt(String.format(SQL_SELECT_COUNT_OpenAppraisal, archivesInfoSavedTableName, associateTable, querySQL.toString()), paramSource);
				dataPageInfo.setRowCount(count);
				
				//分页参数
				paramSource.addValue("beginRow", dataPageInfo.getBeginRow() ,Types.INTEGER);
				paramSource.addValue("endRow", dataPageInfo.getEndRow() ,Types.INTEGER);
				//分页查询档案
				pArchivesInfos = namedParameterJdbcTemplate.query(String.format(SQL_SELECT_OpenAppraisal_WithPage, archivesInfoSavedTableName, associateTable, querySQL.toString()), paramSource, new ArchivesInfoMapper(archivesType));
				
				System.out.println("SQL_SELECT_OpenAppraisal_WithPage="+String.format(SQL_SELECT_OpenAppraisal_WithPage, archivesInfoSavedTableName, associateTable, querySQL.toString()));
				
				//判断是否有记录集
				if (pArchivesInfos.size() >= 1) {
					archivesInfos.addAll(pArchivesInfos);
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
	public boolean updateBatchForPublicAppraisal(UserInfo userInfo,
			ArchivesType archivesType, final List<Integer> archivesNBXHs,
			String isPublic, ErrInfo pErrInfo) {
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
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				
				//定义批量更新
				BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setInt(1, archivesNBXHs.get(i));
					}

					@Override
					public int getBatchSize() {
						return archivesNBXHs.size();
					}
				};
				
				//档案归档信息表名
				String archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
				
				int publicFlag = 1;
				if("1".equals(isPublic)) {
					publicFlag = 0;
				}
				
				
				/**
				 * 鉴定管理->开放鉴定	批量更新档案信息的开放标志 的SQL语句
				 * %1$s 更新的表名
				 * %2$s 开放标志
				 */
				//批量更新 档案的开发标志
				jdbcTemplate.batchUpdate(String.format(SQL_BATCHUPDATE_PublicAppraisal, archivesInfoSavedTableName, publicFlag), setter);
				
				System.out.println("SQL_BATCHUPDATE_PublicAppraisal="+String.format(SQL_BATCHUPDATE_PublicAppraisal, archivesInfoSavedTableName, publicFlag));

				//销毁局部变量
				setter = null;
				jdbcTemplate = null;
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
	public boolean updateBatchForOpenAppraisal(UserInfo userInfo,
			ArchivesType archivesType, final List<Integer> archivesNBXHs,
			String isPublic, ErrInfo pErrInfo) {
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
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				
				//定义批量更新
				BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setInt(1, archivesNBXHs.get(i));
					}

					@Override
					public int getBatchSize() {
						return archivesNBXHs.size();
					}
				};
				
				//档案归档信息表名
				String archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
				//公开密级字典
				ArchivesSecrecy openArchivesSecrecy = SystemInitializer.getInstance().getOpenArchivesSecrecy();
				
				String localSQL = String.format(SQL_BATCHUPDATE_OpenAppraisal, archivesInfoSavedTableName, openArchivesSecrecy.getID(), openArchivesSecrecy.getName());
				
				//批量更新 档案的开发标志
				jdbcTemplate.batchUpdate(localSQL, setter);
				
				System.out.println("SQL_BATCHUPDATE_OpenAppraisal="+localSQL);

				//销毁局部变量
				setter = null;
				jdbcTemplate = null;
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
	public boolean queryClassifiedForControlAreaAppraisal(UserInfo userInfo,
			ArchivesType archivesType,
			List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,
			DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			// 检查档案类型是否有赋值			
			if (archivesType == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息未初始化。");
			}
			
			// 检查档案分类对应的工作表名称是否有值
			String archivesInfoSavedTableName = null;
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false){
					pFlag = false;
				}else{
					// 保存当前分类的档案归档信息表
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
				}
			}

			// 检查数据分页对象是否为空
			if (pFlag)
			{
				if (dataPageInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("数据分页信息非法为空。");
				}
			}

			// 构造查询条件的SQL片段
			StringBuilder querySQL = new StringBuilder();
			if (pFlag)
			{
				if (archivesInfoQueryConditions != null)
				{
					if (archivesInfoQueryConditions.size() > 0)
					{
						pErrPos = 2;
						if (getSqlForArchivesInfoInputQueryConditions(archivesInfoQueryConditions, querySQL , pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "构造查询条件SQL片段失败：");
						}
					}
				}
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				
				
				//执行查询
				if (pFlag) {
					List<ArchivesInfo> pArchivesInfos = null;
						
					//查询档案的总数
					int count = namedParameterJdbcTemplate.queryForInt(String.format(SQL_SELECT_COUNT_ControlArea, archivesInfoSavedTableName, querySQL.toString()), paramSource);
					dataPageInfo.setRowCount(count);
					
					System.out.println("SQL_SELECT_COUNT_ControlArea="+String.format(SQL_SELECT_COUNT_ControlArea, archivesInfoSavedTableName, querySQL.toString()));
					
					//分页参数
					paramSource.addValue("beginRow", dataPageInfo.getBeginRow() ,Types.INTEGER);
					paramSource.addValue("endRow", dataPageInfo.getEndRow() ,Types.INTEGER);
					
					//分页查询档案信息
					pArchivesInfos = namedParameterJdbcTemplate.query(String.format(SQL_SELECT_ControlArea_WithPage, archivesInfoSavedTableName, querySQL.toString()), paramSource, new ArchivesInfoMapper(archivesType));
					
					System.out.println("SQL_SELECT_ControlArea_WithPage="+String.format(SQL_SELECT_ControlArea_WithPage, archivesInfoSavedTableName, querySQL.toString()));
					
					//判断是否有记录集
					if (pArchivesInfos.size() >= 1) {
						archivesInfos.addAll(pArchivesInfos);
					}
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
	public boolean deleteByNBXH(ArchivesType archivesType, int[] nbxhs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		List<Integer> NBXHS = new ArrayList<Integer>();
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				for (int i = 0; i < nbxhs.length; i++) {
					NBXHS.add(nbxhs[i]);
				}
			}
			
			// 检查档案分类对应的工作表名称是否有值
			String archivesInfoSavedTableName = null;
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false){
					pFlag = false;
				}else{
					// 保存当前分类的档案归档信息表
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("nbxhs", NBXHS);
				
				String sql = "DELETE FROM %1$s WHERE NBXH IN (:nbxhs)";
				sql = String.format(sql, archivesInfoSavedTableName);
				new NamedParameterJdbcTemplate(getDataSource()).update(sql, parameterSource);
				
				sql = "DELETE FROM %1$s WHERE ParentNBXH IN (:nbxhs)";
                sql = String.format(sql, archivesInfoSavedTableName);
                new NamedParameterJdbcTemplate(getDataSource()).update(sql, parameterSource);
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
	public boolean checkOutArchivesInfo(ArchivesType archivesType, int[] nbxhs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoSavedTableName = "";
		String archivesInfoWorkingTableName = "";
		String strCondition = "";
		List<Integer> NBXHS = new ArrayList<Integer>();
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag){
				pErrPos = 4;
				if (checkTableName(archivesType, pErrInfo) == false){
					pFlag = false;
				}else{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
				}
			}
			
			if (pFlag) {
				Map<String, ArchivesTypeDataItem> dataItems =  archivesType.getDataItemsForAll();
				ArchivesTypeDataItem dataItem = null;
				List<ArchivesTypeDataItem> dataItemList = new ArrayList<ArchivesTypeDataItem>(dataItems.values());
				for (int i = 0;i<dataItemList.size();i++) {
					dataItem = dataItemList.get(i);
				    if(i == dataItemList.size()-1){
					   strCondition += dataItem.getColumnName();
					}else{
					   strCondition += dataItem.getColumnName()+",";
					}
				}
			}
			
			if (pFlag) {
				for (Integer NBXH : nbxhs) {
					NBXHS.add(NBXH);
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("nbxhs", NBXHS);
				
				String sql = "SET IDENTITY_INSERT %1$s ON INSERT INTO %1$s (%3$s) SELECT %3$s FROM %2$s T1 WHERE T1.NBXH IN (:nbxhs) SET IDENTITY_INSERT %1$s off";
				//String sql = "SET IDENTITY_INSERT %1$s ON INSERT INTO %1$s (%3$s) SELECT %3$s FROM %2$s T1 WHERE T1.NBXH IN (5) SET IDENTITY_INSERT %1$s off";
				sql = String.format(sql, archivesInfoWorkingTableName,archivesInfoSavedTableName,strCondition);
				System.out.println(sql);
				//new NamedParameterJdbcTemplate(getDataSource()).update(sql, parameterSource);
				new NamedParameterJdbcTemplate(getDataSource()).update(sql, parameterSource);
				
				sql = "SET IDENTITY_INSERT %1$s ON INSERT INTO %1$s (%3$s) SELECT %3$s FROM %2$s T1 WHERE T1.ParentNBXH IN (:nbxhs) SET IDENTITY_INSERT %1$s off";
				sql = String.format(sql, archivesInfoWorkingTableName, archivesInfoSavedTableName,strCondition);
				System.out.println(sql);
				new NamedParameterJdbcTemplate(getDataSource()).update(sql, parameterSource);
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
