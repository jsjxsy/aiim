/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IUserRightSystemFeatureDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRightSystemFeature;

/**
 * 用户系统功能权限的DAO实现类
 *
 */
public class UserRightSystemFeatureDaoImpl extends JdbcDaoSupport implements IUserRightSystemFeatureDao
{
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class UserRightSystemFeatureMapper implements RowMapper<UserRightSystemFeature>
	{
		
		@Override
		public UserRightSystemFeature mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int userID = rs.getInt("UserID");
			int featureID = rs.getInt("FeatureID");
			String title = rs.getString("Title");
			String name = rs.getString("Name");
			int parentID = rs.getInt("ParentID");
			String uclKey = rs.getString("UclKey");
			boolean menuFlag = rs.getBoolean("MenuFlag");
			boolean topFlag = rs.getBoolean("TopFlag");
			String menuURI = rs.getString("MenuURI");
			String remark = rs.getString("Remark");
	
			return new UserRightSystemFeature(iD, userID, featureID, title, name, parentID, uclKey, menuFlag, topFlag, menuURI, remark);
		}
	}
	
	/**
	 * 构造函数
	 */
	public UserRightSystemFeatureDaoImpl()
	{

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public UserRightSystemFeatureDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	/**
	 * 查询指定用户的所有系统功能授权信息的SQL语句
	 */
	private final String SQL_SELECT_SystemFeatures_BY_UserID = "SELECT A.ID,UserID,FeatureID,Title,Name,ParentID,UclKey,MenuFlag,TopFlag,MenuURI,Remark FROM UserRight_SystemFeature A,DD_SystemFeature B WHERE A.UserID=? AND A.FeatureID=B.ID  ORDER BY B.ID";
	
	/**
	 * 查询指定用户的所有顶层菜单功能授权信息的SQL语句
	 */
	private final String SQL_SELECT_TopMenus_BY_UserID = "SELECT A.ID,UserID,FeatureID,Title,Name,ParentID,UclKey,MenuFlag,TopFlag,MenuURI,Remark FROM UserRight_SystemFeature A,DD_SystemFeature B WHERE A.UserID=? AND B.TopFlag=1 AND B.MenuFlag=1 AND A.FeatureID=B.ID ORDER BY B.ID";
	
	/**
	 * 查询指定用户具备的指定菜单项的所有下级菜单功能授权信息的SQL语句
	 */
	private final String SQL_SELECT_ChildMenus_BY_UserIDAndMenuID = "SELECT A.ID,UserID,FeatureID,Title,Name,ParentID,UclKey,MenuFlag,TopFlag,MenuURI,Remark FROM UserRight_SystemFeature A,DD_SystemFeature B WHERE A.UserID=:UserID AND B.ParentID=:ParentID AND B.MenuFlag=1 AND A.FeatureID=B.ID ORDER BY B.ID";
	
	/**
	 * 保存用户系统功能权限表的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO UserRight_SystemFeature(UserID,FeatureID) VALUES(:UserID,:FeatureID)";
	
	/**
	 * 为指定的用户保存用户系统功能权限表的SQL语句
	 */
	private final String SQL_INSERT_By_UserID = "INSERT INTO UserRight_SystemFeature(UserID,FeatureID) VALUES(:UserID,:FeatureID)";
	
	
	/**
	 * 删除用户系统功能权限表的SQL语句
	 */
	private final String SQL_DELETE	= "DELETE FROM UserRight_SystemFeature WHERE ID = ?";
	
	/**
	 * 删除指定用户下的指定的系统功能权限的SQL语句
	 */
	private final String SQL_DELETE_By_UserIDAndFeatureID = "DELETE FROM UserRight_SystemFeature WHERE UserID = ? and FeatureID = ?";
	
	/**
	 * 删除指定用户下的所有的系统功能权限的SQL语句
	 */
	private final String SQL_DELETE_By_UserID = "DELETE FROM UserRight_SystemFeature where UserID = ?";
	
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
				if (pErrInfo.getException()!=null)
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
	 * @see com.orifound.aiim.dal.dao.IUserRightSystemFeatureDao#save(com.orifound.aiim.entity.UserRightSystemFeature, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(UserRightSystemFeature userRightSystemFeature, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			pErrPos = 1;
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "用户系统功能权限表的JDBC数据源是否正确依赖注入失败:");
			}

			if (pFlag) {
				pErrPos = 2;
				if (userRightSystemFeature == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户系统功能权限对象非法为空!");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (userRightSystemFeature.getUserID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户系统功能权限表的用户编号非法为空!");
				}
			}
			if (pFlag) {
				pErrPos = 3;
				if (userRightSystemFeature.getFeatureID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户系统功能权限表的系统功能编号非法为空!");
				}
			}
			//执行SQL语句
			if (pFlag) {
				pErrPos = 4;
				//INSERT INTO UserRight_SystemFeature(UserID,FeatureID) VALUES(:UserID,:FeatureID)
				NamedParameterJdbcTemplate parameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
				sqlParameterSource.addValue("UserID", userRightSystemFeature.getID(), Types.INTEGER);
				sqlParameterSource.addValue("FeatureID", userRightSystemFeature.getFeatureID(), Types.INTEGER);

				parameterJdbcTemplate.update(SQL_INSERT, sqlParameterSource);
				//销毁局部变量
				parameterJdbcTemplate = null;
				sqlParameterSource = null;
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IUserRightSystemFeatureDao#delete(com.orifound.aiim.entity.UserRightSystemFeature, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(UserRightSystemFeature userRightSystemFeature, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "用户系统功能权限表的JDBC数据源是否正确依赖注入失败:");
			}

			if (pFlag) {
				pErrPos = 2;
				if (userRightSystemFeature == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户系统功能权限对象非法为空!");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (userRightSystemFeature.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户系统功能权限的编号非法为空!");
				}
			}
			//执行SQL语句
			if (pFlag) {
				pErrPos = 3;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE,userRightSystemFeature.getID());

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
	 * @see com.orifound.aiim.dal.dao.IUserRightSystemFeatureDao#findByUserID(int, java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByUserID(int pUserID, Map<String, SystemFeature> userRightSystemFeatures, ErrInfo pErrInfo)
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
			
			//检查用户编号是否非法
			if (pFlag)
			{
				if (pUserID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为0");
				}
			}

			//执行SQL语句
			if (pFlag)
			{
				
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserRightSystemFeature> pUserRightSystemFeatures=jdbcTemplate.query(SQL_SELECT_SystemFeatures_BY_UserID,new UserRightSystemFeatureMapper(),pUserID);

				//返回查询结果
				if (pUserRightSystemFeatures.size() > 0)
				{
					for (UserRightSystemFeature item : pUserRightSystemFeatures)
					{
						item.setID(item.getFeatureID());
						userRightSystemFeatures.put(item.getUclKey(), item);
					}
				}

				//销毁局部变量
				pUserRightSystemFeatures=null;
				jdbcTemplate = null;
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
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException()!=null)
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
	 * @see com.orifound.aiim.dal.dao.IUserRightSystemFeatureDao#findMenusByUserID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findMenusByUserID(int pUserID, LinkedHashMap<String, SystemFeature> userRightMenus, ErrInfo pErrInfo)
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
			
			//检查用户编号是否非法
			if (pFlag)
			{
				if (pUserID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为0");
				}
			}

			//执行SQL语句
			if (pFlag)
			{

				//先找到顶层菜单授权
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserRightSystemFeature> pUserRightSystemFeatures=jdbcTemplate.query(SQL_SELECT_TopMenus_BY_UserID,new UserRightSystemFeatureMapper(),pUserID);

				//返回查询结果
				if (pUserRightSystemFeatures.size() > 0)
				{
					for (UserRightSystemFeature topMenuItem : pUserRightSystemFeatures)
					{
						SystemFeature pFeature= topMenuItem;
						pFeature.setID(topMenuItem.getFeatureID());
						userRightMenus.put(pFeature.getUclKey(), pFeature);
						
						//查找其授权的下级菜单集合
						LinkedHashMap<String, SystemFeature> childMenus = new LinkedHashMap<String, SystemFeature>();
						if (findForChildMenus(pUserID, topMenuItem.getFeatureID(), childMenus, pErrInfo)==false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0,"查找用户（编号: "+pUserID+"）对 "+ topMenuItem.getName() +" 的下级授权菜单失败: ");
						}
						else
						{
							//保存获取的该用户授权的下级菜单集合
							if (childMenus.size()>0)
							{
								topMenuItem.setChildSystemFeatures(childMenus);
							}
						}
					}
				}

				//销毁局部变量
				pUserRightSystemFeatures=null;
				jdbcTemplate = null;
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
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException()!=null)
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
	
	/**
	 * 查找指定用户具备的指定菜单的下级菜单授权集合
	 * @param pUserID 指定的用户编号
	 * @param featureID 指定的菜单功能编号
	 * @param childMenus 返回查找成功的下级菜单集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean findForChildMenus(int pUserID, int featureID, LinkedHashMap<String, SystemFeature> childMenus, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo)==false)
			{
				pFlag = false;
				
			}
			
			if (pFlag)
			{
				pErrPos=1;
				//执行SQL语句
				//SQL_SELECT_ChildMenus_BY_UserIDAndMenuID = "SELECT ID,UserID,FeatureID,Title,Name,ParentID,UclKey,MenuFlag,TopFlag,MenuURI,Remark FROM UserRight_SystemFeature A,DD_SystemFeature B 
				//WHERE A.UserID=:UserID AND B.ParentID=:ParentID AND B.MenuFlag=1 AND A.FeatureID=B.ID ORDER BY B.ID";
				pErrPos = 1;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("UserID", pUserID, Types.INTEGER);
				paramSource.addValue("ParentID", featureID, Types.INTEGER);

				pErrPos =2;
				List<UserRightSystemFeature> pUserRightSystemFeatures= namedParameterJdbcTemplate.query(SQL_SELECT_ChildMenus_BY_UserIDAndMenuID, paramSource, new UserRightSystemFeatureMapper());
				
				//返回查询结果
				if (pUserRightSystemFeatures.size()>0)
				{
					for (UserRightSystemFeature item : pUserRightSystemFeatures)
					{
						//作为下级菜单添加至集合中返回
						SystemFeature pFeature=item;
						pFeature.setID(item.getFeatureID());
						childMenus.put(pFeature.getUclKey(), pFeature);
						
						pErrPos=2;
						//继续递归查找该下级菜单的再下一级菜单，直到没有下级菜单为止
						LinkedHashMap<String, SystemFeature> pChildren=new LinkedHashMap<String, SystemFeature>();
						if (findForChildMenus(pUserID,item.getFeatureID(), pChildren, pErrInfo)==false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0,"查找用户（编号: "+pUserID+"）对 "+ item.getName() +" 的下级授权菜单失败: ");
							break;
						}
						else
						{
							if (pChildren.size()>0)
							{
								item.setChildSystemFeatures(pChildren);
							}
						}
					}
				}
				
				//销毁局部变量
				pUserRightSystemFeatures=null;
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
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException()!=null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量

		}

		return pFlag;
	}

	@Override
	public boolean deleteUserRightSystemFeatureByUserIDAndFeatureID(UserInfo userInfo, UserRightSystemFeature userRightSystemFeature, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "用户系统功能权限表的JDBC数据源是否正确依赖注入失败:");
			}
			
			if (pFlag) {
				pErrPos = 2;
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("删除用户系统功能权限，用户信息非法为空!");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (userInfo.getUserID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("删除用户系统功能权限，用户编号非法为0!");
				}
			}
			
			if (pFlag) {
				pErrPos = 2;
				if (userRightSystemFeature == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户系统功能权限对象非法为空!");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (userRightSystemFeature.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户系统功能权限的编号非法为空!");
				}
			}
			
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 3;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE_By_UserIDAndFeatureID,userInfo.getUserID(),userRightSystemFeature.getID());

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
	public boolean saveUserRightSystemFeatureByUserID(UserInfo userInfo, UserRightSystemFeature userRightSystemFeature, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			pErrPos = 1;
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "用户系统功能权限表的JDBC数据源是否正确依赖注入失败:");
			}

			if (pFlag) {
				pErrPos = 2;
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("添加用户系统功能权限,用户信息非法为空!");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (userRightSystemFeature == null) {
					pFlag = false;
					pErrInfo.getContent().append("添加用户系统功能权限对象非法为空!");
				}
			}
			
			
			if (pFlag) {
				pErrPos = 4;
				if (userRightSystemFeature.getUserID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("添加用户系统功能权限表的用户编号非法为0!");
				}
			}
			if (pFlag) {
				pErrPos = 5;
				if (userRightSystemFeature.getFeatureID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("添加用户系统功能权限表的系统功能编号非法为0!");
				}
			}
			//执行SQL语句
			if (pFlag) {
				pErrPos = 4;
				//INSERT INTO UserRight_SystemFeature(UserID,FeatureID) VALUES(:UserID,:FeatureID)
				NamedParameterJdbcTemplate parameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
				sqlParameterSource.addValue("UserID", userInfo.getUserID(), Types.INTEGER);
				sqlParameterSource.addValue("FeatureID", userRightSystemFeature.getFeatureID(), Types.INTEGER);
				parameterJdbcTemplate.update(SQL_INSERT_By_UserID, sqlParameterSource);
				//销毁局部变量
				parameterJdbcTemplate = null;
				sqlParameterSource = null;
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
	
	
	public boolean deleteUserRightSystemFeatureByUserID(int pUserID, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "用户系统功能权限表的JDBC数据源是否正确依赖注入失败:");
			}
			
			if (pUserID <= 0) {
				pFlag = false;
				pErrInfo.getContent().append("用户系统功能权限表，用户编号非法为空!");
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 3;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE_By_UserID,pUserID);
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
