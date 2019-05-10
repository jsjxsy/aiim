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

import com.orifound.aiim.dal.dao.IAppraisalPublicDetailDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.dal.util.TimeTool;
import com.orifound.aiim.entity.AppraisalPublicDetail;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserInfo;

/**
 * AppraisalPublicDetails(档案开放/公开鉴定表)表的DAO实现类(SQL Server平台实现)
 *
 */
public class AppraisalPublicDetailDaoImpl extends JdbcDaoSupport implements IAppraisalPublicDetailDao {
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class AppraisalPublicDetailMapper implements RowMapper<AppraisalPublicDetail>
	{
		
		@Override
		public AppraisalPublicDetail mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			boolean publicFlag = rs.getBoolean("PublicFlag");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			String archivesID = rs.getString("ArchivesID");
			String title = rs.getString("Title");
			String appraisalReason = rs.getString("AppraisalReason");
			Date appraisalDate = rs.getTimestamp("AppraisalDate");
			String appraisalOpinion = rs.getString("AppraisalOpinion");
			String appraisalPersion = rs.getString("AppraisalPersion");
			int oldSecrecyID = rs.getInt("OldSecrecyID");
			int newSecrecyID = rs.getInt("NewSecrecyID");
			Date registerDate = rs.getTimestamp("RegisterDate");
			int managerUserID = rs.getInt("ManagerUserID");
			String remark = rs.getString("Remark");
			String archivesTypeName = rs.getString("archivesTypeName");
			String formationDepartment = rs.getString("FormationDepartment");
			String oldSecrecyName = rs.getString("OldSecrecyName");
			
			return new AppraisalPublicDetail(iD,publicFlag,archivesTypeID,nBXH,archivesID,title,appraisalReason,appraisalDate,appraisalOpinion,
					appraisalPersion,oldSecrecyID,newSecrecyID,registerDate,managerUserID, remark,archivesTypeName, formationDepartment, oldSecrecyName);
		}
	}
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class AppraisalPublicDetailMapperOther implements RowMapper<AppraisalPublicDetail>
	{
		
		@Override
		public AppraisalPublicDetail mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			boolean publicFlag = rs.getBoolean("PublicFlag");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			String archivesID = rs.getString("ArchivesID");
			String title = rs.getString("Title");
			String appraisalReason = rs.getString("AppraisalReason");
			Date appraisalDate = rs.getTimestamp("AppraisalDate");
			String appraisalOpinion = rs.getString("AppraisalOpinion");
			String appraisalPersion = rs.getString("AppraisalPersion");
			int oldSecrecyID = rs.getInt("OldSecrecyID");
			int newSecrecyID = rs.getInt("NewSecrecyID");
			Date registerDate = rs.getTimestamp("RegisterDate");
			int managerUserID = rs.getInt("ManagerUserID");
			String remark = rs.getString("Remark");
			
			return new AppraisalPublicDetail(iD,publicFlag,archivesTypeID,nBXH,archivesID,title,appraisalReason,appraisalDate,appraisalOpinion,
					appraisalPersion,oldSecrecyID,newSecrecyID,registerDate,managerUserID, remark);
		}
	}
	
	
	/**
	 * 构造函数
	 */
	public AppraisalPublicDetailDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public AppraisalPublicDetailDaoImpl(DataSource dataSource) {
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
	 * 鉴定管理->开放鉴定	插入开放鉴定明细情况的SQL语句
	 * %1$s	开放标志
	 * %2$s	开放依据
	 * %3$s 鉴定日期
	 * %4$s 鉴定人
	 * %5$s 经办人编号 
	 * %6$s 备注：开放鉴定、取消开放鉴定
	 * %7$s 指定档案规定表
	 */
	private final String SQL_INSERT_PUBLIC = "INSERT INTO AppraisalPublicDetails(PublicFlag,ArchivesTypeID,NBXH,ArchivesID,Title,AppraisalReason," +
			"AppraisalDate,AppraisalPersion,OldSecrecyID,NewSecrecyID,ManagerUserID,Remark,FormationDepartmentID)" +
			"SELECT %1$s, ArchivesTypeID,NBXH,ArchivesID,Title, '%2$s', '%3$s', '%4$s', SecrecyID,SecrecyID, %5$s,'%6$s',FormationDepartmentID FROM %7$s WHERE NBXH=?";
	
	/**
	 * 鉴定管理->取消开放/公开鉴定		删除开放鉴定明细情况的SQL语句
	 */
	private final String SQL_DELETE = "DELETE FROM AppraisalPublicDetails WHERE PublicFlag=:PublicFlag AND ArchivesTypeID=:ArchivesTypeID AND NBXH=:NBXH";
	
	/**
	 * 还原档案的开放标志的SQL语句
	 * 
	 */
	private final String SQL_UPDATE_PUBLICFLAG = "UPDATE %1$s SET PublicFlag=0 WHERE NBXH=:NBXH AND ArchivesTypeID=:ArchivesTypeID";
	
	/**
	 * 还原档案的密级的SQL语句
	 * update ArchivesInfoSaved_JX set SecrecyID=p.OldSecrecyID from AppraisalPublicDetails p where ArchivesInfoSaved_JX.NBXH=p.NBXH and p.ArchivesTypeID=?
	 */
	private final String SQL_UPDATE_SECRECY = "UPDATE %1$s SET SecrecyID=p.OldSecrecyID,SecrecyText=s.Name FROM AppraisalPublicDetails p,DD_ArchivesSecrecy s WHERE %1$s.NBXH=p.NBXH AND p.NewSecrecyID=:NewSecrecyID AND p.OldSecrecyID=s.ID AND p.ArchivesTypeID=:ArchivesTypeID AND p.NBXH=:NBXH";
	
	/**
	 * 鉴定管理->公开鉴定	插入公开鉴定明细情况的SQL语句
	 * %1$s	公开依据
	 * %2$s 鉴定日期
	 * %3$s 鉴定人
	 * 
	 * %4$s	旧的密级
	 * %5$s	新的密级
	 * 
	 * %6$s 经办人编号 
	 * %7$s 备注：公开鉴定、取消公开鉴定
	 * %8$s 指定档案规定表
	 */
	private final String SQL_INSERT_OPEN = "INSERT INTO AppraisalPublicDetails(PublicFlag,ArchivesTypeID,NBXH,ArchivesID,Title,AppraisalReason," +
			"AppraisalDate,AppraisalPersion,OldSecrecyID,NewSecrecyID,ManagerUserID,Remark,FormationDepartmentID)" +
			"SELECT 0, ArchivesTypeID,NBXH,ArchivesID,Title, '%1$s', '%2$s', '%3$s', %4$s, %5$s, %6$s,'%7$s',FormationDepartmentID FROM %8$s WHERE NBXH=?";
	
	/**
	 * 鉴定管理->鉴定登记  公开/开放鉴定  分页查询鉴定信息的SQL语句
	 * %1$s 页面查询条件
	 */
	private final String SQL_SELECT_WITHPAGE = "SELECT * FROM(SELECT kd.*,d.Name FormationDepartment,at.FullName archivesTypeName,da.name OldSecrecyName, ROW_NUMBER() OVER(ORDER BY kd.FormationDepartmentID ASC, kd.RegisterDate ASC) RowNumber " +
			" FROM AppraisalPublicDetails kd,DD_DepartmentInfo d, DD_ArchivesType at,DD_ArchivesSecrecy da " +
			" WHERE kd.FormationDepartmentID=d.ID AND kd.ArchivesTypeID=at.ID AND kd.OldSecrecyID=da.ID %1$s)t WHERE RowNumber BETWEEN :beginRow AND :endRow";
	
	/**
	 * 鉴定管理->鉴定登记   公开/开放鉴定  查询登记信息记录总数的SQL语句
	 * %1$s 页面查询条件
	 */
	private final String SQL_SELECT_COUNT = "SELECT COUNT(ID) FROM AppraisalPublicDetails WHERE ID>=1 %1$s";
	
	/**
	 * 根据主键查询开放/公开详细信息的SQL语句
	 */
	private final String SQL_SELECT_BYID = "SELECT * FROM AppraisalPublicDetails WHERE PublicFlag=? AND ID=?";
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IAppraisalPublicDetailDao#delete(com.orifound.aiim.entity.AppraisalPublicDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(AppraisalPublicDetail appraisalPublicDetail,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IAppraisalPublicDetailDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<AppraisalPublicDetail> appraisalPublicDetails,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IAppraisalPublicDetailDao#findByID(int, com.orifound.aiim.entity.AppraisalPublicDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, int publicFlag, AppraisalPublicDetail appraisalPublicDetail, ErrInfo pErrInfo) {
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
				List<AppraisalPublicDetail> pEntitys=jdbcTemplate.query(SQL_SELECT_BYID, new AppraisalPublicDetailMapperOther(), publicFlag, pID);

				//返回查询结果
				if (pEntitys.size() > 0) {
					appraisalPublicDetail.cloneFrom(pEntitys.get(0));
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
	 * @see com.orifound.aiim.dal.dao.IAppraisalPublicDetailDao#save(com.orifound.aiim.entity.AppraisalPublicDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(AppraisalPublicDetail appraisalPublicDetail,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IAppraisalPublicDetailDao#update(com.orifound.aiim.entity.AppraisalPublicDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(AppraisalPublicDetail appraisalPublicDetail,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveBatchForPublicAppraisal(UserInfo userInfo, ArchivesType archivesType, List<Integer> archivesNBXHs, Map<String, String> opinion, ErrInfo pErrInfo) {
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
				
				String remark = "开放鉴定";
				int PublicFlag = 1;
				
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				String localSQL = null;
				//循环	插入开发鉴定明细
				for(Integer NBXH: archivesNBXHs) {
					/**
					 * 鉴定管理->开放鉴定	插入开放鉴定明细情况的SQL语句
					 * %1$s	开放标志
					 * %2$s	开放原因
					 * %3$s 开放日期
					 * %4$s 鉴定人
					 * %5$s 经办人编号 
					 * %6$s 备注：开放鉴定
					 * %7$s 指定档案归档表
					 */
					localSQL = String.format(SQL_INSERT_PUBLIC, PublicFlag, AppraisalReason, AppraisalDate, AppraisalPersion, 
										userInfo.getUserID(), remark, archivesInfoSavedTableName);
					
					System.out.println("鉴定管理->开放鉴定 批量添加档案开放鉴定信息 localSQL ="+localSQL);
					
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
	public boolean saveBatchForOpenAppraisal(UserInfo userInfo, ArchivesType archivesType, List<Integer> archivesNBXHs,
									Map<String, String> opinion, ErrInfo pErrInfo) {
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
				
				String OldSecrecyID = "SecrecyID";
				ArchivesSecrecy openArchivesSecrecy = SystemInitializer.getInstance().getOpenArchivesSecrecy();
				String NewSecrecyID = ""+openArchivesSecrecy.getID();
				String remark = "公开鉴定";
				
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				String localSQL = null;
				//循环	插入公开鉴定明细
				for(Integer NBXH: archivesNBXHs) {
					/**
					 * 鉴定管理->公开鉴定	插入公开鉴定明细情况的SQL语句
					 * %1$s	公开依据
					 * %2$s 鉴定日期
					 * %3$s 鉴定人
					 * 
					 * %4$s	旧的密级
					 * %5$s	新的密级
					 * 
					 * %6$s 经办人编号 
					 * %7$s 备注：公开鉴定
					 * %8$s 指定档案归档表
					 */
					localSQL = String.format(SQL_INSERT_OPEN, AppraisalReason, AppraisalDate, AppraisalPersion, 
										OldSecrecyID,NewSecrecyID,userInfo.getUserID(), remark, archivesInfoSavedTableName);
					
					System.out.println("鉴定管理->公开鉴定 批量添加档案开放鉴定信息 localSQL ="+localSQL);
					
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
	public boolean delete(ArchivesType archivesType, int NBXH, int publicFlag, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			
			//检查档案内部序号
			if (pFlag) {
				if (NBXH <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("档案内部序号非法为空！");
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("PublicFlag", publicFlag, Types.INTEGER);
				paramSource.addValue("ArchivesTypeID", archivesType.getID(), Types.INTEGER);
				paramSource.addValue("NBXH", NBXH, Types.INTEGER);

				pErrPos = 3;
				//DELETE FROM AppraisalPublicDetails WHERE NBXH=:NBXH AND ArchivesTypeID=:ArchivesTypeID AND PublicFlag=:PublicFlag
				namedParameterJdbcTemplate.update(SQL_DELETE, paramSource);
				
				//档案归档信息表名
				String archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
				
				//公开密级字典
				ArchivesSecrecy openArchivesSecrecy = SystemInitializer.getInstance().getOpenArchivesSecrecy();
				
				paramSource = new MapSqlParameterSource();
				paramSource.addValue("ArchivesTypeID", archivesType.getID(), Types.INTEGER);
				paramSource.addValue("NBXH", NBXH, Types.INTEGER);
				//取消开放鉴定
				if(publicFlag == 1) {
					pErrPos = 4;
					//UPDATE %1$s SET PublicFlag=0 WHERE NBXH=:NBXH AND ArchivesTypeID=:ArchivesTypeID
					namedParameterJdbcTemplate.update(String.format(SQL_UPDATE_PUBLICFLAG, archivesInfoSavedTableName), paramSource);
				}
				
				//取消公开鉴定
				if(publicFlag == 0) {
					pErrPos = 5;
					paramSource.addValue("NewSecrecyID", openArchivesSecrecy.getID(), Types.INTEGER);
					//UPDATE %1$s SET SecrecyID=p.OldSecrecyID FROM AppraisalPublicDetails p WHERE %1$s.NBXH=p.NBXH AND p.NewSecrecyID=:NewSecrecyID AND p.ArchivesTypeID=:ArchivesTypeID AND p.NBXH=:NBXH
					namedParameterJdbcTemplate.update(String.format(SQL_UPDATE_SECRECY, archivesInfoSavedTableName), paramSource);
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
	public boolean findWithPage(List<Integer> archivesTypeIds,
			Map<String, String> params, DataPageInfo dataPageInfo,
			List<AppraisalPublicDetail> appraisalPublicDetails, ErrInfo pErrInfo) {
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
//				 								鉴定开始日期AppraisalDate、 鉴定截止日期AppraisalDateEnd、公开标志PublicFlag
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
				
				String PublicFlag = params.get("PublicFlag");
				if (StringTool.checkNull(PublicFlag)) {
					buffer.append(" AND PublicFlag=:PublicFlag");
					paramSource.addValue("PublicFlag", PublicFlag, Types.INTEGER);
				}
				
				//查询档案的总记录数
				int count = namedParameterJdbcTemplate.queryForInt(String.format(SQL_SELECT_COUNT, buffer.toString()), paramSource);
				dataPageInfo.setRowCount(count);
				
				//分页参数
				paramSource.addValue("beginRow", dataPageInfo.getBeginRow() ,Types.INTEGER);
				paramSource.addValue("endRow", dataPageInfo.getEndRow() ,Types.INTEGER);
				//分页查询档案
				pErrPos = 3;
				List<AppraisalPublicDetail> results = namedParameterJdbcTemplate.query(String.format(SQL_SELECT_WITHPAGE, buffer.toString()), paramSource, new AppraisalPublicDetailMapper());
			
				System.out.println("开放鉴定登记信息查询SQL："+String.format(SQL_SELECT_WITHPAGE, buffer.toString()));
				
				//判断是否存在记录
				if(results.size() >= 0) {
					appraisalPublicDetails.addAll(results);
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
