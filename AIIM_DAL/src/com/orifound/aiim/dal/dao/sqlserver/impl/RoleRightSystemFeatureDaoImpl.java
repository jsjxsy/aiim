/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.*;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.RowMapper;
import com.orifound.aiim.dal.dao.IRoleRightSystemFeatureDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.RoleRightSystemFeature;
import com.orifound.aiim.entity.SystemFeature;


/**
 * 角色系统功能授权信息的DAO实现类
 *
 */
public class RoleRightSystemFeatureDaoImpl extends JdbcDaoSupport implements IRoleRightSystemFeatureDao
{
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class RoleRightSystemFeatureMapper implements RowMapper<RoleRightSystemFeature>
	{
		
		@Override
		public RoleRightSystemFeature mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int rolesID = rs.getInt("RolesID");
			int featureID = rs.getInt("FeatureID");
			String title = rs.getString("Title");
			String name = rs.getString("Name");
			int parentID = rs.getInt("ParentID");
			String uclKey = rs.getString("UclKey");
			boolean menuFlag = rs.getBoolean("MenuFlag");
			boolean topFlag = rs.getBoolean("TopFlag");
			String menuURI = rs.getString("MenuURI");
			String remark = rs.getString("Remark");
			
			return new RoleRightSystemFeature(iD, rolesID, featureID, title, name, parentID, uclKey, menuFlag, topFlag, menuURI, remark);
		}
	}
	
	/**
	 * 构造函数
	 */
	public RoleRightSystemFeatureDaoImpl()
	{

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public RoleRightSystemFeatureDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	
	/**
	 * 查询指定角色的所有系统功能授权信息的SQL语句
	 */
	private final String SQL_SELECT_SystemFeatures_BY_RoleID = "SELECT A.ID,RolesID,FeatureID,Title,Name,ParentID,UclKey,MenuFlag,TopFlag,MenuURI,Remark FROM DDR_UserRoles_SystemFeature A,DD_SystemFeature B WHERE A.RolesID IN (:RolesID) AND A.FeatureID=B.ID ORDER BY B.ID";
	
	/**
	 * 查询指定角色的所有顶层菜单功能授权信息的SQL语句
	 */
	private final String SQL_SELECT_TopMenus_BY_RoleID = "SELECT A.ID,RolesID,FeatureID,Title,Name,ParentID,UclKey,MenuFlag,TopFlag,MenuURI,Remark FROM DDR_UserRoles_SystemFeature A,DD_SystemFeature B WHERE A.RolesID IN (:RolesID) AND B.TopFlag=1 AND B.MenuFlag=1 AND A.FeatureID=B.ID ORDER BY B.ID";
	
	/**
	 * 查询指定角色具备的指定菜单项的所有下级菜单功能授权信息的SQL语句
	 */
	private final String SQL_SELECT_ChildMenus_BY_RoleIDAndMenuID = "SELECT A.ID,RolesID,FeatureID,Title,Name,ParentID,UclKey,MenuFlag,TopFlag,MenuURI,Remark FROM DDR_UserRoles_SystemFeature A,DD_SystemFeature B WHERE A.RolesID IN (:RolesID) AND B.ParentID=:ParentID AND B.MenuFlag=1 AND A.FeatureID=B.ID ORDER BY B.ID";
	
	
	
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
	 * @see com.orifound.aiim.dal.dao.IRoleRightSystemFeatureDao#save(com.orifound.aiim.entity.RoleRightSystemFeature, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(RoleRightSystemFeature roleRightSystemFeature, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IRoleRightSystemFeatureDao#delete(com.orifound.aiim.entity.RoleRightSystemFeature, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(RoleRightSystemFeature roleRightSystemFeature, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IRoleRightSystemFeatureDao#findByRoleID(int[], java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByRoleID(int[] pRoleID, Map<String, SystemFeature> roleRightSystemFeatures, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		List<Integer> pRoleIDs=new ArrayList<Integer>();
		
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
				if (pRoleID.length==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("角色编号非法为空。");
				}
			}
			
			//转换角色编号数组为list结构，以便SQL查询使用
			if (pFlag)
			{
				for (int i = 0; i < pRoleID.length; i++)
				{
					pRoleIDs.add(pRoleID[i]);
				}
				
				if (pRoleIDs.size()==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("角色编号非法为空。");
				}
			}

			//执行SQL语句
			if (pFlag)
			{
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("RolesID", pRoleIDs);
				List<RoleRightSystemFeature> pRoleRightSystemFeatures=namedParameterJdbcTemplate.query(SQL_SELECT_SystemFeatures_BY_RoleID,paramSource,new RoleRightSystemFeatureMapper());

				//返回查询结果
				if (pRoleRightSystemFeatures.size() > 0)
				{
					for (RoleRightSystemFeature item : pRoleRightSystemFeatures)
					{
						roleRightSystemFeatures.put(item.getUclKey(), item);
					}
				}

				//销毁局部变量
				pRoleRightSystemFeatures=null;
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
			pRoleIDs=null;
			throwable = null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IRoleRightSystemFeatureDao#findMenusByRoleID(int[], java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findMenusByRoleID(int[] pRoleID, LinkedHashMap<String, SystemFeature> roleRightMenus, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		List<Integer> pRoleIDs=new ArrayList<Integer>();
		
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
				if (pRoleID.length==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("角色编号非法为空。");
				}
			}
			
			//转换角色编号数组
			if (pFlag)
			{
				
				for (int i = 0; i < pRoleID.length; i++)
				{
					pRoleIDs.add(pRoleID[i]);
				}
				
				if (pRoleIDs.size()==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("角色编号非法为空。");
				}
			}

			//执行SQL语句
			if (pFlag)
			{

				//先找到顶层菜单授权
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("RolesID", pRoleIDs);
				List<RoleRightSystemFeature> pRoleRightSystemFeatures=namedParameterJdbcTemplate.query(SQL_SELECT_TopMenus_BY_RoleID,paramSource,new RoleRightSystemFeatureMapper());

				//返回查询结果
				if (pRoleRightSystemFeatures.size() > 0)
				{
					for (RoleRightSystemFeature topMenuItem : pRoleRightSystemFeatures)
					{
						roleRightMenus.put(topMenuItem.getUclKey(), topMenuItem);
						
						//查找其授权的下级菜单集合
						LinkedHashMap<String, SystemFeature> childMenus = new LinkedHashMap<String, SystemFeature>();
						if (findForChildMenus(pRoleID, topMenuItem.getFeatureID(), childMenus, pErrInfo)==false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0,"查找角色对 "+ topMenuItem.getName() +" 的下级授权菜单失败: ");
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
				pRoleRightSystemFeatures=null;
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
			pRoleIDs=null;
			throwable = null;
		}

		return pFlag;
	}

	/**
	 * 查找指定用户具备的指定菜单的下级菜单授权集合
	 * @param pRoleID 指定的角色编号数组
	 * @param featureID 指定的菜单功能编号
	 * @param childMenus 返回查找成功的下级菜单集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean findForChildMenus(int[] pRoleID, int featureID, LinkedHashMap<String, SystemFeature> childMenus, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		List<Integer> pRoleIDs=new ArrayList<Integer>();
		
		try
		{
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo)==false)
			{
				pFlag = false;
				
			}
			
			//转换角色编号数组
			if (pFlag)
			{
				for (int i = 0; i < pRoleID.length; i++)
				{
					pRoleIDs.add(pRoleID[i]);
				}
				
				if (pRoleIDs.size()==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("角色编号非法为空。");
				}
			}
			
			if (pFlag)
			{
				pErrPos=1;
				//执行SQL语句
				//SQL_SELECT_ChildMenus_BY_RoleIDAndMenuID = "SELECT A.ID,RolesID,FeatureID,Title,Name,ParentID,UclKey,MenuFlag,TopFlag,MenuURI,Remark FROM UserRight_SystemFeature A,DD_SystemFeature B 
				//WHERE A.RolesID IN (:RolesID) AND B.ParentID=:ParentID AND B.MenuFlag=1 AND A.FeatureID=B.ID ORDER BY B.ID"; 

				pErrPos = 1;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("RolesID", pRoleIDs);
				paramSource.addValue("ParentID", featureID, Types.INTEGER);

				pErrPos =2;
				List<RoleRightSystemFeature> pRoleRightSystemFeatures= namedParameterJdbcTemplate.query(SQL_SELECT_ChildMenus_BY_RoleIDAndMenuID, paramSource, new RoleRightSystemFeatureMapper());
				
				//返回查询结果
				if (pRoleRightSystemFeatures.size()>0)
				{
					for (RoleRightSystemFeature item : pRoleRightSystemFeatures)
					{
						//作为下级菜单添加至集合中返回
						childMenus.put(item.getUclKey(), item);
						
						pErrPos=2;
						//继续递归查找该下级菜单的再下一级菜单，直到没有下级菜单为止
						LinkedHashMap<String, SystemFeature> pChildren=new LinkedHashMap<String, SystemFeature>();
						if (findForChildMenus(pRoleID,item.getFeatureID(), pChildren, pErrInfo)==false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0,"查找角色对 "+ item.getName() +" 的下级授权菜单失败: ");
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
				pRoleRightSystemFeatures=null;
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
			pRoleIDs=null;
			throwable=null;
		}

		return pFlag;
	}
	
}
