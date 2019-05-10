/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.IAppraisalUseScopesDetailDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.dal.util.TimeTool;
import com.orifound.aiim.entity.AppraisalUseScopesDetail;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;

/**
 * 划控鉴定的档案明细信息DAO实现类（SQL Server平台）
 *
 */
public class AppraisalUseScopesDetailDaoImpl extends JdbcDaoSupport implements IAppraisalUseScopesDetailDao
{
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class AppraisalUseScopesDetailMapper implements RowMapper<AppraisalUseScopesDetail>
	{
		
		@Override
		public AppraisalUseScopesDetail mapRow(ResultSet rs, int rowNum) throws SQLException
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
			Date registerDate = rs.getTimestamp("RegisterDate");
			int managerUserID = rs.getInt("ManagerUserID");
			String remark = rs.getString("Remark");
			String archivesTypeName = rs.getString("archivesTypeName");
			String formationDepartment = rs.getString("formationDepartment");
			
			return new AppraisalUseScopesDetail(iD,archivesTypeID,nBXH,archivesID,title,appraisalReason,appraisalDate,appraisalOpinion,
					appraisalPersion,registerDate,managerUserID,remark, archivesTypeName, formationDepartment);
		}
	}
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class AppraisalUseScopesDetailMapperOther implements RowMapper<AppraisalUseScopesDetail>
	{
		
		@Override
		public AppraisalUseScopesDetail mapRow(ResultSet rs, int rowNum) throws SQLException
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
			Date registerDate = rs.getTimestamp("RegisterDate");
			int managerUserID = rs.getInt("ManagerUserID");
			String remark = rs.getString("Remark");
			
			return new AppraisalUseScopesDetail(iD,archivesTypeID,nBXH,archivesID,title,appraisalReason,appraisalDate,appraisalOpinion,
					appraisalPersion,registerDate,managerUserID,remark);
		}
	}
	
	/**
	 * 构造函数
	 */
	public AppraisalUseScopesDetailDaoImpl()
	{

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public AppraisalUseScopesDetailDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	/**
	 * 查找指定的档案在指定用户无权访问的划控档案列表中出现的次数的SQL语句
	 */
	private final String SQL_SELECT_CountArchivesInfoNotInUseScopesACL = "SELECT COUNT(A.ID) FROM AppraisalUseScopesDetails A,AppraisalUseScopes_Roles B" +
			" WHERE A.ArchivesTypeID=:ArchivesTypeID AND A.NBXH=:NBXH AND A.ID=B.ID" +
			" AND NOT EXISTS (SELECT C.RolesID FROM UserRolesInfo C WHERE C.UserID=:UserID AND B.RolesID=C.RolesID)";
	
	/**
	 * 查找指定的档案在指定用户的划控档案列表中出现的次数的SQL语句
	 */
	private final String SQL_SELECT_CountArchivesInfoInUseScopesACL = "SELECT COUNT(A.ID) FROM AppraisalUseScopesDetails A,AppraisalUseScopes_Roles B" +
			" WHERE A.ArchivesTypeID=:ArchivesTypeID AND A.NBXH=:NBXH AND A.ID=B.ID" +
			" AND EXISTS (SELECT C.RolesID FROM UserRolesInfo C WHERE C.UserID=:UserID AND B.RolesID=C.RolesID)";
	
	/**
	 * 根据档案内部序号查找档案划分控制鉴定明细情况表的实体类信息的SQL语句
	 */
	private final String SQL_SELECT_ByNBXH = "SELECT * FROM AppraisalUseScopesDetails WHERE ArchivesTypeID=? AND NBXH=?";
	
	/**
	 * 通过档案内部序号查询划分控制鉴定的角色id范围的SQL语句
	 */
	private final String SQL_SELECT_RoleIds_ByNBXH = "SELECT r.ID RolesID,r.Name FROM AppraisalUseScopes_Roles us, DD_UserRoles r where us.RolesID=r.ID AND us.ID=? ORDER BY us.RolesID";
	
	
	/**
	 * 保存划控明细信息的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO AppraisalUseScopesDetails(ArchivesTypeID,NBXH,ArchivesID,Title,AppraisalReason,AppraisalDate,AppraisalPersion,ManagerUserID,Remark,FormationDepartmentID) VALUES(:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:AppraisalReason,:AppraisalDate,:AppraisalPersion,:ManagerUserID,:Remark,:FormationDepartmentID)";
	
	/**
	 * 更新划控明细信息的SQL语句
	 */
	private final String SQL_UPDATE = "UPDATE AppraisalUseScopesDetails SET AppraisalReason=:AppraisalReason,AppraisalDate=:AppraisalDate,AppraisalPersion=:AppraisalPersion,RegisterDate=DEFAULT,ManagerUserID=:ManagerUserID WHERE ID=:ID";
	
	/**
	 * 新增 档案划控鉴定角色的SQL语句
	 */
	private final String SQL_INSERT_Roles = "INSERT INTO AppraisalUseScopes_Roles(ID, RolesID) SELECT %1$s,ID FROM DD_UserRoles WHERE ID IN(:RolesID)";
	
	/**
	 * 删除 档案划控鉴定角色的SQL语句
	 */
	private final String SQL_DELETE_Roles = "DELETE FROM AppraisalUseScopes_Roles WHERE RolesID IN(:RolesID) AND ID=:ID";
	
	/**
	 * 鉴定管理->鉴定登记  划控鉴定  分页查询鉴定信息的SQL语句
	 * %1$s 页面查询条件
	 */
	private final String SQL_SELECT_WITHPAGE = "SELECT * FROM(SELECT kd.*,d.Name FormationDepartment,at.FullName archivesTypeName, ROW_NUMBER() OVER(ORDER BY kd.FormationDepartmentID ASC, kd.RegisterDate ASC) RowNumber " +
			" FROM AppraisalUseScopesDetails kd,DD_DepartmentInfo d, DD_ArchivesType at" +
			" WHERE kd.FormationDepartmentID=d.ID AND kd.ArchivesTypeID=at.ID %1$s)t WHERE RowNumber BETWEEN :beginRow AND :endRow";
	
	/**
	 * 鉴定管理->鉴定登记   划控鉴定  查询登记信息记录总数的SQL语句
	 * %1$s 页面查询条件
	 */
	private final String SQL_SELECT_COUNT = "SELECT COUNT(ID) FROM AppraisalUseScopesDetails WHERE ID>=1 %1$s";
	
	/**
	 * 鉴定管理：划控鉴定信息 根据id获取授权的所有角色名称的SQL语句
	 */
	private final String SQL_SELECT_RoleNames_ByID = "SELECT r.Name FROM AppraisalUseScopes_Roles us, DD_UserRoles r WHERE us.RolesID=r.ID AND us.ID=?";
	
	/**
	 * 划控鉴定：删除鉴定明细信息、同时删除控制鉴定的角色范围表 的SQL语句
	 */
	private final String SQL_DELETE = "DELETE FROM AppraisalUseScopes_Roles WHERE ID=%1$s;DELETE FROM AppraisalUseScopesDetails WHERE ID=%1$s;";
	
	/**
	 * 检查JDBC数据源的依赖注入（JDBC DataSource Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean CheckDataSourceInjection(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查数据源是否为空
			pErrPos = 1;
			if (getDataSource() == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("JDBC数据源非法为空，请检查是否有进行依赖注入或赋值。");
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	 * @see com.orifound.aiim.dal.dao.IAppraisalUseScopesDetailDao#findCountArchivesInfoNotInUseScopesACL(int, int, int, com.orifound.aiim.entity.IntegerEx, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findCountArchivesInfoNotInUseScopesACL(int pUserID, int pArchivesTypeID, int pNBXH, IntegerEx pACLCount, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			//执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;

//				String SQL_SELECT_CountArchivesInfoNotInUseScopesACL = "SELECT COUNT(A.ID) FROM AppraisalUseScopesDetails A,AppraisalUseScopes_Roles B" +
//				" WHERE A.ArchivesTypeID=:ArchivesTypeID AND A.NBXH=:NBXH AND A.ID=B.ID" +
//				" AND NOT EXISTS (SELECT C.RolesID FROM UserRolesInfo C WHERE C.UserID=:UserID AND B.RolesID=C.RolesID)";
		
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("ArchivesTypeID", pArchivesTypeID, Types.INTEGER);
				paramSource.addValue("NBXH", pNBXH, Types.INTEGER);
				paramSource.addValue("UserID", pUserID, Types.INTEGER);

				pErrPos = 3;
				int pCount= namedParameterJdbcTemplate.queryForInt(SQL_SELECT_CountArchivesInfoNotInUseScopesACL, paramSource);
				
				//返回查询结果
				pACLCount.setValue(pCount);

				//销毁局部变量
				paramSource=null;
				namedParameterJdbcTemplate = null;
			}
		}
		catch (BadSqlGrammarException e)
		{
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append("."+stackTraceElements[0].getMethodName()+"-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
					tempBuilder.append(" ErrPos: "+pErrPos+", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean findCountArchivesInfoInUseScopesACL(int pUserID, int pArchivesTypeID, int pNBXH, IntegerEx pACLCount, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			//执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;

//				String SQL_SELECT_CountArchivesInfoInUseScopesACL = "SELECT COUNT(A.ID) FROM AppraisalUseScopesDetails A,AppraisalUseScopes_Roles B" +
//				" WHERE A.ArchivesTypeID=:ArchivesTypeID AND A.NBXH=:NBXH AND A.ID=B.ID" +
//				" AND NOT (SELECT C.RolesID FROM UserRolesInfo C WHERE C.UserID=:UserID AND B.RolesID=C.RolesID)";
		
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("ArchivesTypeID", pArchivesTypeID, Types.INTEGER);
				paramSource.addValue("NBXH", pNBXH, Types.INTEGER);
				paramSource.addValue("UserID", pUserID, Types.INTEGER);

				pErrPos = 3;
				int pCount= namedParameterJdbcTemplate.queryForInt(SQL_SELECT_CountArchivesInfoInUseScopesACL, paramSource);
				
				//返回查询结果
				pACLCount.setValue(pCount);

				//销毁局部变量
				paramSource=null;
				namedParameterJdbcTemplate = null;
			}
		}
		catch (BadSqlGrammarException e)
		{
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append("."+stackTraceElements[0].getMethodName()+"-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
					tempBuilder.append(" ErrPos: "+pErrPos+", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}


	@Override
	public boolean findByByNBXH(int archivesTypeID, int NBXH, AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo) {
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
				List<AppraisalUseScopesDetail> pEntitys=jdbcTemplate.query(SQL_SELECT_ByNBXH,new AppraisalUseScopesDetailMapperOther(), archivesTypeID, NBXH);
				
				//查询档案划分控制鉴定明细情况
				if (pEntitys.size() > 0) {
					appraisalUseScopesDetail.cloneFrom(pEntitys.get(0));
					
					List<Map<String, Object>> results = jdbcTemplate.queryForList(SQL_SELECT_RoleIds_ByNBXH, appraisalUseScopesDetail.getID());
					
					//查询档案的划控范围角色id集合
					List<Integer> roleIds = new ArrayList<Integer>();
					List<String> roleNames = new ArrayList<String>();
					Object roleId = null;
					Object roleName = null;
					//循环获取角色id、角色名称
					for(Map<String, Object> result : results) {
						roleId = result.get("RolesID");
						roleName = result.get("Name");
						if(roleId!=null && roleName!=null) {
							roleIds.add(Integer.valueOf(roleId.toString()));
							roleNames.add(roleName.toString());
						}
					}
					appraisalUseScopesDetail.setRoleIds(roleIds);
					appraisalUseScopesDetail.setRoleNames(roleNames);
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
	public boolean save(AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo) {
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
				
				paramSource.addValue("ArchivesTypeID", appraisalUseScopesDetail.getArchivesTypeID(), Types.INTEGER);
				paramSource.addValue("NBXH", appraisalUseScopesDetail.getNBXH(), Types.INTEGER);
				paramSource.addValue("ArchivesID", appraisalUseScopesDetail.getArchivesID(), Types.VARCHAR);
				paramSource.addValue("Title", appraisalUseScopesDetail.getTitle(), Types.VARCHAR);
				paramSource.addValue("AppraisalReason", appraisalUseScopesDetail.getAppraisalReason(), Types.VARCHAR);
				paramSource.addValue("AppraisalDate", appraisalUseScopesDetail.getAppraisalDate(), Types.TIMESTAMP);
				paramSource.addValue("AppraisalPersion", appraisalUseScopesDetail.getAppraisalPersion(), Types.VARCHAR);
				paramSource.addValue("ManagerUserID", appraisalUseScopesDetail.getManagerUserID(), Types.INTEGER);
				paramSource.addValue("Remark", appraisalUseScopesDetail.getRemark(), Types.VARCHAR);
				paramSource.addValue("FormationDepartmentID", appraisalUseScopesDetail.getFormationDepartmentID(), Types.INTEGER);
				
				
				pErrPos = 3;
				PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(SQL_INSERT);
				//构造SQL执行成功后返回的主键
				GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
				//设置主键字段名
				pscFactory.setGeneratedKeysColumnNames(new String[] { "ID" });

				pErrPos = 4;
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource, keyHolder);

				//返回自动生成的内部序号
				appraisalUseScopesDetail.setID(keyHolder.getKey().intValue());

				//销毁局部变量
				namedParameterJdbcTemplate = null;
				paramSource = null;
				pscFactory = null;
				keyHolder = null;
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
	public boolean update(AppraisalUseScopesDetail appraisalUseScopesDetail,
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
				paramSource.addValue("ID", appraisalUseScopesDetail.getID(), Types.INTEGER);
				paramSource.addValue("AppraisalReason", appraisalUseScopesDetail.getAppraisalReason(), Types.VARCHAR);
				paramSource.addValue("AppraisalDate", appraisalUseScopesDetail.getAppraisalDate(), Types.TIMESTAMP);
				paramSource.addValue("AppraisalPersion", appraisalUseScopesDetail.getAppraisalPersion(), Types.VARCHAR);
				paramSource.addValue("ManagerUserID", appraisalUseScopesDetail.getManagerUserID(), Types.INTEGER);

				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_UPDATE, paramSource);
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
	public boolean deleteRoles(int appraisalUseScopesDetailId,
			List<Integer> roleIds, ErrInfo pErrInfo) {
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
				
//				"DELETE FROM AppraisalUseScopes_Roles WHERE RolesID IN(:RolesID) AND ID=:ID"
				paramSource.addValue("RolesID", roleIds);
				paramSource.addValue("ID", appraisalUseScopesDetailId, Types.INTEGER);

				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_DELETE_Roles, paramSource);
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
	public boolean saveRoles(int appraisalUseScopesDetailId,
			List<Integer> roleIds, ErrInfo pErrInfo) {
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
				
//				"INSERT INTO AppraisalUseScopes_Roles(ID, RolesID) SELECT %1$s,ID FROM DD_UserRoles WHERE ID IN(:RolesID)";
				paramSource.addValue("RolesID", roleIds);

				pErrPos = 3;
				namedParameterJdbcTemplate.update(String.format(SQL_INSERT_Roles, appraisalUseScopesDetailId), paramSource);
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
			List<AppraisalUseScopesDetail> appraisalUseScopesDetails,
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
				List<AppraisalUseScopesDetail> results = namedParameterJdbcTemplate.query(String.format(SQL_SELECT_WITHPAGE, buffer.toString()), 
							paramSource, new AppraisalUseScopesDetailMapper());
			
				System.out.println("划控鉴定登记信息查询SQL："+String.format(SQL_SELECT_WITHPAGE, buffer.toString()));
				
				//判断是否存在记录
				if(results.size() >= 0) {
					appraisalUseScopesDetails.addAll(results);
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

	@Override
	public boolean findRoleNamesById(int pId, List<String> roleNames, ErrInfo pErrInfo) {
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
				List<Map<String, Object>> pEntitys=jdbcTemplate.queryForList(SQL_SELECT_RoleNames_ByID, pId);
				//返回查询结果
				if (pEntitys.size() > 0) {
					Object roleName = null;
					for(Map<String, Object> pEntity : pEntitys) {
						roleName = pEntity.get("Name");
						//判断是否存在角色名称
						if(roleName != null) {
							roleNames.add(roleName.toString());
						}
					}
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

	@Override
	public boolean delete(AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo) {
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
				jdbcTemplate.update(String.format(SQL_DELETE, appraisalUseScopesDetail.getID()));

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
System.out.println(pErrInfo.toString());
		return pFlag;
	}
}