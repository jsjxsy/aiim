/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.*;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.RowMapper;

import com.orifound.aiim.dal.dao.IUserRightArchivesSecrecyDao;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRightArchivesSecrecy;

/**
 * 用户档案密级授权信息DAO实现类
 *
 */
public class UserRightArchivesSecrecyDaoImpl extends JdbcDaoSupport implements IUserRightArchivesSecrecyDao
{
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class UserRightArchivesSecrecyMapper implements RowMapper<UserRightArchivesSecrecy>
	{
		
		@Override
		public UserRightArchivesSecrecy mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int userID = rs.getInt("UserID");
			int secrecyID = rs.getInt("SecrecyID");
			boolean rightFlag_ArchivesInfo = rs.getBoolean("RightFlag_ArchivesInfo");
			boolean rightFlag_AttachedFile = rs.getBoolean("RightFlag_AttachedFile");
			boolean rightFlag_PaperArchives = rs.getBoolean("RightFlag_PaperArchives");
			String name = rs.getString("Name");
	        String remark = rs.getString("Remark");
			return new UserRightArchivesSecrecy(iD, userID, secrecyID,rightFlag_ArchivesInfo,rightFlag_AttachedFile,rightFlag_PaperArchives,name, remark);
		}
	}
	
	private class UserRightArchivesSecrecyMapper2 implements RowMapper<UserRightArchivesSecrecy>
	{
		
		@Override
		public UserRightArchivesSecrecy mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int userID = rs.getInt("UserID");
			int secrecyID = rs.getInt("SecrecyID");
			boolean rightFlag_ArchivesInfo = rs.getBoolean("RightFlag_ArchivesInfo");
			boolean rightFlag_AttachedFile = rs.getBoolean("RightFlag_AttachedFile");
			boolean rightFlag_PaperArchives = rs.getBoolean("RightFlag_PaperArchives");
			return new UserRightArchivesSecrecy(iD, userID, secrecyID,rightFlag_ArchivesInfo,rightFlag_AttachedFile,rightFlag_PaperArchives);
		}
	}
	
	/**
	 * 构造函数
	 */
	public UserRightArchivesSecrecyDaoImpl()
	{

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public UserRightArchivesSecrecyDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	
	/**
	 * 查询指定用户的档案密级授权信息的SQL语句
	 */
	private final String SQL_SELECT_By_UserID = "SELECT A.ID,UserID,SecrecyID,RightFlag_ArchivesInfo,RightFlag_AttachedFile,RightFlag_PaperArchives,Name,Remark FROM UserRight_ArchivesSecrecy A,DD_ArchivesSecrecy B WHERE UserID=? AND A.SecrecyID=B.ID ORDER BY B.ID";
	
	/**
	 * 查询指定用户的档案密级授权信息的SQL语句
	 */
	private final String SQL_SELECT_UserRight_By_UserID = "SELECT ID,UserID,SecrecyID,RightFlag_ArchivesInfo,RightFlag_AttachedFile,RightFlag_PaperArchives FROM UserRight_ArchivesSecrecy WHERE UserID = ?  ORDER BY ID";
	
	
	/**
	 * 增加对指定用户的档案密级授权的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO UserRight_ArchivesSecrecy(UserID,SecrecyID) VALUES(:UserID,:SecrecyID)";
	
	/**
	 * 删除指定用户的档案密级授权信息的的SQL语句
	 */
	private final String SQL_DELETE = "DELETE FROM UserRight_ArchivesSecrecy where ID = ?";
	
	
	/**
	 * 删除指定用户的档案密级授权信息的的SQL语句
	 */
	private final String SQL_DELETE_By_UserID = "DELETE FROM UserRight_ArchivesSecrecy WHERE UserID = ?";
	
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
	 * @see com.orifound.aiim.dal.dao.IUserRightArchivesSecrecyDao#save(com.orifound.aiim.entity.UserRightArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(UserRightArchivesSecrecy userRightArchivesSecrecy, ErrInfo pErrInfo)
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
				if (userRightArchivesSecrecy == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户档案密级权限表的实体类对象非法为空!");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (userRightArchivesSecrecy.getUserID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户档案密级权限的用户编号非法为空!");
				}
			}
			if (pFlag) {
				pErrPos = 3;
				if (userRightArchivesSecrecy.getSecrecyID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户档案密级权限的档案密级编号非法为空!");
				}
			}
			//执行SQL语句
			if (pFlag) {
				pErrPos = 4;
				//INSERT INTO UserRight_ArchivesSecrecy(ID,UserID,SecrecyID) VALUES(:ID,:UserID,:SecrecyID)
				NamedParameterJdbcTemplate parameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
				sqlParameterSource.addValue("UserID", userRightArchivesSecrecy.getUserID(), Types.INTEGER);
				sqlParameterSource.addValue("SecrecyID", userRightArchivesSecrecy.getSecrecyID(), Types.INTEGER);

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
	 * @see com.orifound.aiim.dal.dao.IUserRightArchivesSecrecyDao#delete(com.orifound.aiim.entity.UserRightArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(UserRightArchivesSecrecy userRightArchivesSecrecy, ErrInfo pErrInfo)
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
				if ( userRightArchivesSecrecy == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户档案密级权限对象非法为空!");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (userRightArchivesSecrecy.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户档案密级权限的编号非法为空!");
				}
			}
			//执行SQL语句
			if (pFlag) {
				pErrPos = 3;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE,userRightArchivesSecrecy.getID());

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
	 * @see com.orifound.aiim.dal.dao.IUserRightArchivesSecrecyDao#findByUserID(int, java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByUserID(int pUserID, Map<Integer, ArchivesSecrecy> userRightArchivesSecrecys, ErrInfo pErrInfo)
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
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserRightArchivesSecrecy> pUserRightArchivesSecrecys=jdbcTemplate.query(SQL_SELECT_By_UserID,new UserRightArchivesSecrecyMapper(),pUserID);
				
				//返回查询结果
				if (pUserRightArchivesSecrecys.size() > 0)
				{
					for (UserRightArchivesSecrecy item : pUserRightArchivesSecrecys)
					{
						item.setID(item.getSecrecyID());
						userRightArchivesSecrecys.put(item.getSecrecyID(), item);
					}
				}

				//销毁局部变量
				pUserRightArchivesSecrecys=null;
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

	public boolean findByUserID(int pUserID, List<UserRightArchivesSecrecy> userRightArchivesSecrecys, ErrInfo pErrInfo)
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
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserRightArchivesSecrecy> pUserRightArchivesSecrecys=jdbcTemplate.query(SQL_SELECT_UserRight_By_UserID,new UserRightArchivesSecrecyMapper2(),pUserID);
				
				//返回查询结果
				if (pUserRightArchivesSecrecys.size() > 0)
				{
					userRightArchivesSecrecys.addAll(pUserRightArchivesSecrecys);
				}

				//销毁局部变量
				pUserRightArchivesSecrecys=null;
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

	@Override
	public boolean deleteByUserID(int pUserID, ErrInfo pErrInfo) {
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
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE_By_UserID,pUserID);
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
}
