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
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IAppraisalKeepDestroyDetailDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.dal.util.TimeTool;
import com.orifound.aiim.entity.AppraisalKeepDestroyDetail;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * 存毁鉴定明细情况表的DAO实现类 (SQL Server平台实现)
 * @author tyb
 *
 */
public class AppraisalKeepDestroyDetailDaoImpl extends JdbcDaoSupport implements IAppraisalKeepDestroyDetailDao {
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class AppraisalKeepDestroyDetailMapper implements RowMapper<AppraisalKeepDestroyDetail>
	{
		
		@Override
		public AppraisalKeepDestroyDetail mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			String archivesID = rs.getString("ArchivesID");
			String title = rs.getString("Title");
			String appraisalReason = rs.getString("AppraisalReason");
			Date appraisalDate = rs.getTimestamp("AppraisalDate");
			String appraisalOpinion = rs.getString("AppraisalOpinion");
			String appraisalPersion = rs.getString("AppraisalPersion");
			boolean appraisalDeletedFlag = rs.getBoolean("AppraisalDeletedFlag");
			int oldRetentionPeriodID = rs.getInt("OldRetentionPeriodID");
			int newRetentionPeriodID = rs.getInt("NewRetentionPeriodID");
			Date registerDate = rs.getTimestamp("RegisterDate");
			int managerUserID = rs.getInt("ManagerUserID");
			String remark = rs.getString("Remark");
			String FormationDepartment = rs.getString("FormationDepartment");
			String archivesTypeName = rs.getString("archivesTypeName");
			String retentionPeriodName = rs.getString("retentionPeriodName");
			String oldRetentionPeriodName = rs.getString("oldRetentionPeriodName");
			
			return new AppraisalKeepDestroyDetail(iD,archivesTypeID,nBXH,archivesID,title,appraisalReason,appraisalDate,appraisalOpinion,
					appraisalPersion,appraisalDeletedFlag,oldRetentionPeriodID,newRetentionPeriodID,registerDate,
					managerUserID,remark,FormationDepartment,archivesTypeName, retentionPeriodName, oldRetentionPeriodName);
		}
	}
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class AppraisalKeepDestroyDetailMapperOther implements RowMapper<AppraisalKeepDestroyDetail>
	{
		
		@Override
		public AppraisalKeepDestroyDetail mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			String archivesID = rs.getString("ArchivesID");
			String title = rs.getString("Title");
			String appraisalReason = rs.getString("AppraisalReason");
			Date appraisalDate = rs.getTimestamp("AppraisalDate");
			String appraisalOpinion = rs.getString("AppraisalOpinion");
			String appraisalPersion = rs.getString("AppraisalPersion");
			boolean appraisalDeletedFlag = rs.getBoolean("AppraisalDeletedFlag");
			int oldRetentionPeriodID = rs.getInt("OldRetentionPeriodID");
			int newRetentionPeriodID = rs.getInt("NewRetentionPeriodID");
			Date registerDate = rs.getTimestamp("RegisterDate");
			int managerUserID = rs.getInt("ManagerUserID");
			String remark = rs.getString("Remark");
			
			return new AppraisalKeepDestroyDetail(iD,archivesTypeID,nBXH,archivesID,title,appraisalReason,appraisalDate,appraisalOpinion,
					appraisalPersion,appraisalDeletedFlag,oldRetentionPeriodID,newRetentionPeriodID,registerDate,
					managerUserID,remark);
		}
	}
	
	/**
	 * 构造函数
	 */
	public AppraisalKeepDestroyDetailDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public AppraisalKeepDestroyDetailDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
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
	 * 鉴定管理->存毁鉴定	插入存毁鉴定明细情况的SQL语句
	 * %1$s 鉴定日期
	 * %2$s	鉴定原因
	 * %3$s 鉴定人
	 * %4$s 是否销毁
	 * %5$s 新保存期限ID
	 * %6$s 经办人编号
	 * %7$s 档案归档表名 
	 */
	private final String SQL_INSERT = "INSERT INTO AppraisalKeepDestroyDetails(ArchivesTypeID,NBXH,ArchivesID,Title,AppraisalDate," +
			" AppraisalReason, AppraisalPersion,AppraisalDeletedFlag,OldRetentionPeriodID,NewRetentionPeriodID,ManagerUserID,Remark,FormationDepartmentID)" +
			" SELECT ArchivesTypeID,NBXH,ArchivesID,Title,'%1$s', " +
			" '%2$s', '%3$s', %4$s , RetentionPeriodID, %5$s, %6$s, NULL,FormationDepartmentID" +
			" from %7$s where NBXH=?";
	
	/**
	 * 鉴定管理->鉴定登记  存毁鉴定  分页查询鉴定信息的SQL语句
	 * %1$s 页面查询条件
	 */
	private final String SQL_SELECT_WITHPAGE = "SELECT * FROM(SELECT kd.*,d.Name FormationDepartment,at.FullName archivesTypeName, rp.name retentionPeriodName, oldrp.Name oldRetentionPeriodName, ROW_NUMBER() OVER(ORDER BY kd.FormationDepartmentID ASC, kd.RegisterDate ASC) RowNumber " +
			" FROM AppraisalKeepDestroyDetails kd,DD_DepartmentInfo d, DD_ArchivesType at,DD_RetentionPeriod rp,DD_RetentionPeriod oldrp " +
			" WHERE kd.FormationDepartmentID=d.ID AND kd.ArchivesTypeID=at.ID AND rp.id=kd.NewRetentionPeriodID AND oldrp.id=kd.OldRetentionPeriodID %1$s)t WHERE RowNumber BETWEEN :beginRow AND :endRow";
	
	/**
	 * 鉴定管理->鉴定登记  存毁鉴定  查询登记信息记录总数的SQL语句
	 * %1$s 页面查询条件
	 */
	private final String SQL_SELECT_COUNT = "SELECT COUNT(ID) FROM AppraisalKeepDestroyDetails WHERE ID>=1 %1$s";
	
	/**
	 * 根据主键查询存毁鉴定信息的SQL语句
	 */
	private final String SQL_SELECT_BYID = "SELECT * FROM AppraisalKeepDestroyDetails WHERE ID=?";
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IAppraisalKeepDestroyDetailDao#delete(com.orifound.aiim.entity.AppraisalKeepDestroyDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(
			AppraisalKeepDestroyDetail appraisalKeepDestoryDetail,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IAppraisalKeepDestroyDetailDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(
			List<AppraisalKeepDestroyDetail> appraisalKeepDestoryDetails,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IAppraisalKeepDestroyDetailDao#findByID(int, com.orifound.aiim.entity.AppraisalKeepDestroyDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, AppraisalKeepDestroyDetail appraisalKeepDestoryDetail, ErrInfo pErrInfo) {
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
				List<AppraisalKeepDestroyDetail> pEntitys=jdbcTemplate.query(SQL_SELECT_BYID,new AppraisalKeepDestroyDetailMapperOther(),pID);

				//返回查询结果
				if (pEntitys.size() > 0) {
					appraisalKeepDestoryDetail.cloneFrom(pEntitys.get(0));
				}

				//销毁局部变量
				pEntitys = null;
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
	
	/**
	 * 用于保存构建好的插入存毁鉴定明细情况SQL语句
	 */
	private String localSQL ;

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IAppraisalKeepDestroyDetailDao#save(com.orifound.aiim.entity.AppraisalKeepDestroyDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveBatch(UserInfo userInfo, ArchivesType archivesType, Map<Integer, Map<String, String>> batchArchives, Map<String, String> opinion, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
					
			//检测经办人是否为空
			if (userInfo == null) {
				pFlag = false;
				pErrInfo.getContent().append("传入参数->经办人非法为空。");
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				
				//档案归档信息表名
				String archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
				
				int userId = userInfo.getUserID();
				
				//鉴定日期
				String AppraisalDate = opinion.get("AppraisalDate");
				if(StringTool.checkNull(AppraisalDate) == false) {
					AppraisalDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
				}
				
				//鉴定意见(可为空)
				String AppraisalReason = opinion.get("AppraisalReason"); 
				if(StringTool.checkNull(AppraisalReason) == false) {
					AppraisalReason = "";
				}
				//鉴定人(可为空)
				String AppraisalPersion = opinion.get("AppraisalPersion");
				if(StringTool.checkNull(AppraisalPersion) == false) {
					AppraisalPersion = "";
				}
				
				//循环	插入存毁鉴定明细
				for(Integer NBXH: batchArchives.keySet()) {
					Map<String, String> params = batchArchives.get(NBXH);
					
					//是否销毁标志
					String AppraisalDeletedFlag = params.get("AppraisalDeletedFlag");
					if(StringTool.checkNull(AppraisalDeletedFlag) == false) {
						AppraisalDeletedFlag = "0";
					}
					
					//新保存期限ID
					String NewRetentionPeriodID = params.get("NewRetentionPeriodID");
					//销毁档案
					if(NewRetentionPeriodID.equals("0")) {
						NewRetentionPeriodID = "RetentionPeriodID";
					}
					
					/**
					 * 鉴定管理->存毁鉴定	插入存毁鉴定明细情况的SQL语句
					 * %1$s 鉴定日期
					 * %2$s	鉴定原因
					 * %3$s 鉴定人
					 * %4$s 是否销毁
					 * %5$s 新保存期限ID
					 * %6$s 经办人编号
					 * %7$s 档案归档表名 
					 */
					localSQL = String.format(SQL_INSERT, AppraisalDate, AppraisalReason, AppraisalPersion, AppraisalDeletedFlag, 
									NewRetentionPeriodID, userId, archivesInfoWorkingTableName);
					
					jdbcTemplate.update(localSQL, NBXH);
				}

				//销毁局部变量
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
			System.out.println(e.getMessage());
			
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IAppraisalKeepDestroyDetailDao#update(com.orifound.aiim.entity.AppraisalKeepDestroyDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(
			AppraisalKeepDestroyDetail appraisalKeepDestoryDetail,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(AppraisalKeepDestroyDetail appraisalKeepDestroyDetail,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findWithPage(List<Integer> archivesTypeIds, Map<String, String> params, DataPageInfo dataPageInfo, List<AppraisalKeepDestroyDetail> appraisalKeepDestroyDetails,
				ErrInfo pErrInfo) {
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
				
				
//				params 参数列表 包含：档号archivesID、题名title、 档案类型archivesTypeId、档案形成部门formationDepartmentID、
//				 								鉴定开始日期AppraisalDate、 鉴定截止日期AppraisalDateEnd
				//保存查询条件
				StringBuffer buffer = new StringBuffer();
				
				//判断是否有档号条件
				String archivesID = params.get("archivesID");
				if(StringTool.checkNull(archivesID)) {
					buffer.append(" AND archivesID LIKE '%").append(archivesID).append("%' ");
				}
				
				//判断是否有题名条件
				String title = params.get("title");
				if(StringTool.checkNull(title)) {
					buffer.append(" AND title LIKE '%").append(title).append("%' ");
				}
				
				//判断是否有档案类型条件	查询该一级档案分类下的所有档案
				String archivesTypeId = params.get("archivesTypeId");
				if(StringTool.checkNull(archivesTypeId) && archivesTypeIds!=null && archivesTypeIds.size()>=1) {
					System.out.println("档案类型：");
					for(Integer i : archivesTypeIds) {
						System.out.print(i+"-");
					}
					System.out.println("");
					
					buffer.append(" AND archivesTypeId IN(:archivesTypeId)");
					paramSource.addValue("archivesTypeId", archivesTypeIds);
				}
				
				//判断是否有档案形成部门条件
				String formationDepartmentID = params.get("formationDepartmentID");
				if(StringTool.checkNull(formationDepartmentID)) {
					buffer.append(" AND formationDepartmentID=:formationDepartmentID");
					paramSource.addValue("formationDepartmentID", formationDepartmentID, Types.INTEGER);
				}
				
				//判断是否有鉴定开始日期条件
				String AppraisalDate = params.get("AppraisalDate");
				if(StringTool.checkNull(AppraisalDate)) {
					buffer.append(" AND AppraisalDate>=:AppraisalDate");
					paramSource.addValue("AppraisalDate", TimeTool.getMinTime(AppraisalDate),Types.TIMESTAMP);
				}
				
				//判断是否有鉴定截止日期条件
				String AppraisalDateEnd = params.get("AppraisalDateEnd");
				if(StringTool.checkNull(AppraisalDateEnd)) {
					buffer.append(" AND AppraisalDate<=:AppraisalDateEnd");
					paramSource.addValue("AppraisalDateEnd", TimeTool.getMaxTime(AppraisalDateEnd),Types.TIMESTAMP);
				}

				//查询档案的总记录数
				int count = namedParameterJdbcTemplate.queryForInt(String.format(SQL_SELECT_COUNT, buffer.toString()), paramSource);
				dataPageInfo.setRowCount(count);
				
				//分页参数
				paramSource.addValue("beginRow", dataPageInfo.getBeginRow() ,Types.INTEGER);
				paramSource.addValue("endRow", dataPageInfo.getEndRow() ,Types.INTEGER);
				//分页查询档案
				pErrPos = 3;
				List<AppraisalKeepDestroyDetail> results = namedParameterJdbcTemplate.query(String.format(SQL_SELECT_WITHPAGE, buffer.toString()), paramSource, new AppraisalKeepDestroyDetailMapper());
			
				System.out.println("存毁鉴定登记信息查询SQL："+String.format(SQL_SELECT_WITHPAGE, buffer.toString()));
				
				//判断是否存在记录
				if(results.size() >= 0) {
					appraisalKeepDestroyDetails.addAll(results);
				}
				
				//销毁局部变量
				results = null;
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
}