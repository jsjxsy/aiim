package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.ISystemFeatureDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class SystemFeatureDaoImpl extends JdbcDaoSupport implements ISystemFeatureDao {
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class SystemFeatureMapper implements RowMapper<SystemFeature>
	{
		
		@Override
		public SystemFeature mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String title = rs.getString("Title");
			String name = rs.getString("Name");
			int parentID = rs.getInt("ParentID");
			String uclKey = rs.getString("UclKey");
			boolean menuFlag = rs.getBoolean("MenuFlag");
			boolean topFlag = rs.getBoolean("TopFlag");
			String menuURI = rs.getString("MenuURI");
			int pageSize = rs.getInt("PageSize");
			String remark = rs.getString("Remark");
			
			return new SystemFeature(iD,title,name,parentID,uclKey,menuFlag,topFlag,menuURI,pageSize,remark);
		}
	}
	
	/**
	 * 构造函数
	 */
	public SystemFeatureDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public SystemFeatureDaoImpl(DataSource dataSource) {
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
	 * 查询所有的SystemFeature的SQL语句
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM DD_SystemFeature";
	
	/**
	 * 查询所有顶层系统功能信息的SQL语句
	 */
	private final String SQL_SELECT_TopSystemFeatures = "SELECT ID,ParentID,Title,Name,ParentID,UclKey,MenuFlag,TopFlag,MenuURI,PageSize,Remark FROM  DD_SystemFeature  WHERE TopFlag=1  ORDER BY ID ";
	
	/**
	 * 查询指定功能项的子系统功能信息的SQL语句
	 */
	private final String SQL_SELECT_ChildSystemFeatures = "SELECT ID,ParentID,Title,Name,ParentID,UclKey,MenuFlag,TopFlag,MenuURI,PageSize,Remark FROM DD_SystemFeature WHERE ParentID = ? ORDER BY ID ";
	
	@Override
	public boolean delete(SystemFeature pSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findAll(List<SystemFeature> pSystemFeatures, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "SystemFeature的JDBC数据源是否正确依赖注入");
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<SystemFeature> pEntitys=jdbcTemplate.query(SQL_SELECT_ALL,new SystemFeatureMapper());

				//返回查询结果
				if (pEntitys.size() > 0) {
					pSystemFeatures.addAll(pEntitys);
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
	public boolean findByID(int pID, SystemFeature pSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(SystemFeature pSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(SystemFeature pSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	

	@Override
	public boolean findAllSystemFeature(Map<String, SystemFeature> systemFeatures, ErrInfo pErrInfo) {
		
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

				//先找到顶层菜单授权
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<SystemFeature> pSystemFeatures=jdbcTemplate.query(SQL_SELECT_TopSystemFeatures,new SystemFeatureMapper());

				//返回查询结果
				if (pSystemFeatures.size() > 0)
				{
					for (SystemFeature systemFeature : pSystemFeatures)
					{
						systemFeatures.put(systemFeature.getUclKey(), systemFeature);
						
						//查找其授权的下级菜单集合
						LinkedHashMap<String, SystemFeature> childMenus = new LinkedHashMap<String, SystemFeature>();
						if (findForChildSystemFeature(systemFeature.getID(), childMenus, pErrInfo)==false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0,"查找功能模块"+systemFeature.getID()+"下级子功能模块失败: ");
						}
						else
						{
							//保存获取的该用户授权的下级菜单集合
							if (childMenus.size()>0)
							{
								systemFeature.setChildSystemFeatures(childMenus);
							}
						}
					}
				}

				//销毁局部变量
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
	 * 查找指定功能模块的下级菜单授权集合
	 * @param parentID 指定的菜单功能编号
	 * @param childMenus 返回查找成功的下级菜单集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean findForChildSystemFeature(int parentID, LinkedHashMap<String, SystemFeature> childMenus, ErrInfo pErrInfo)
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
				

				pErrPos =2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<SystemFeature> pSystemFeatures = jdbcTemplate.query(SQL_SELECT_ChildSystemFeatures,new SystemFeatureMapper(),parentID);
				
				//返回查询结果
				if (pSystemFeatures.size()>0)
				{
					for (SystemFeature item : pSystemFeatures)
					{
						childMenus.put(item.getUclKey(), item);
						
						pErrPos=2;
						//继续递归查找该下级菜单的再下一级菜单，直到没有下级菜单为止
						LinkedHashMap<String, SystemFeature> pChildren=new LinkedHashMap<String, SystemFeature>();
						if (findForChildSystemFeature(item.getID(), pChildren, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0,"查找模块 "+item.getParentID()+"）对 "+ item.getName() +" 的下级授权菜单失败: ");
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
				pSystemFeatures=null;
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
	public boolean findByUCLKey(SystemFeature systemFeature, ErrInfo pErrInfo) {
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
				String sql = "SELECT * FROM  DD_SystemFeature  WHERE UclKey=?";
				List<SystemFeature> systemFeatures = getJdbcTemplate().query(sql, new SystemFeatureMapper(), systemFeature.getUclKey());
				if (systemFeatures.size() > 0) {
					systemFeature.cloneFrom(systemFeatures.get(0));
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
}
