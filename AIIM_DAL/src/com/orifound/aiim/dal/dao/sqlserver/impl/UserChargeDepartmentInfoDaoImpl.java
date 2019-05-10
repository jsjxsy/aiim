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

import com.orifound.aiim.dal.dao.IUserChargeDepartmentInfoDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserChargeDepartmentInfo;

/**
 * 专职人员负责的部门信息表的DAO实现类
 *
 */
public class UserChargeDepartmentInfoDaoImpl extends JdbcDaoSupport implements IUserChargeDepartmentInfoDao
{
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class UserChargeDepartmentInfoMapper implements RowMapper<UserChargeDepartmentInfo>
	{
		
		@Override
		public UserChargeDepartmentInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int userID = rs.getInt("UserID");
			int departmentID = rs.getInt("DepartmentID");
			String name = rs.getString("Name");
			String remark = rs.getString("Remark");
			
			return new UserChargeDepartmentInfo(iD, userID, departmentID, name, remark);
		}
	}
	
	private class UserChargeDepartmentInfoMapper2 implements RowMapper<UserChargeDepartmentInfo>
	{
		
		@Override
		public UserChargeDepartmentInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int departmentID = rs.getInt("DepartmentID");
			String name = rs.getString("Name");
			String remark = rs.getString("Remark");
			
			return new UserChargeDepartmentInfo(departmentID, name, remark);
		}
	}
	
	/**
	 * 构造函数
	 */
	public UserChargeDepartmentInfoDaoImpl()
	{

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public UserChargeDepartmentInfoDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	
	/**
	 * 查询指定业务专员所负责部门的SQL语句
	 */
	private final String SQL_SELECT_BYUSERID = "SELECT A.ID,UserID,DepartmentID,Name,Remark FROM UserChargeDepartment A,DD_DepartmentInfo B WHERE UserID=? AND A.DepartmentID=B.ID ORDER BY B.ID";
	
	/**
	 * 删除指定用户的所负责部门的SQL语句
	 */
	private final String SQL_DELETE = "DELETE FROM UserChargeDepartment WHERE ID = ?";
	
	/**
	 * 出入用户的所负责部门信息的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO UserChargeDepartment(UserID,DepartmentID) VALUES(:UserID,:DepartmentID)";
	
	/**
	 * 查询所有没有用户负责部门信息的SQL语句
	 */
	private final String SQL_SELECT_UnchargeDepartment = "SELECT ID AS DepartmentID,Name,Remark FROM DD_DepartmentInfo where ID NOT IN (SELECT DepartmentID FROM UserChargeDepartment ) ORDER BY ID";
	
	
	
	
	
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
	 * @see com.orifound.aiim.dal.dao.IUserChargeDepartmentInfoDao#save(com.orifound.aiim.entity.UserChargeDepartmentInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(UserChargeDepartmentInfo userChargeDepartmentInfo, ErrInfo pErrInfo)
	{
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
				//"INSERT INTO UserChargeDepartment(UserID,DepartmentID) VALUES(:UserID,:DepartmentID)
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
				mapSqlParameterSource.addValue("UserID", userChargeDepartmentInfo.getUserID(),Types.INTEGER);
				mapSqlParameterSource.addValue("DepartmentID", userChargeDepartmentInfo.getDepartmentID(),Types.INTEGER);
				namedParameterJdbcTemplate.update(SQL_INSERT, mapSqlParameterSource);
				//销毁局部变量
				namedParameterJdbcTemplate = null;
				mapSqlParameterSource = null;
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
	 * @see com.orifound.aiim.dal.dao.IUserChargeDepartmentInfoDao#delete(com.orifound.aiim.entity.UserChargeDepartmentInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(UserChargeDepartmentInfo userChargeDepartmentInfo, ErrInfo pErrInfo)
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
				pErrInfo.getContent().insert(0,"请检查JDBC数据源依赖注入,JDBC数据源依赖注入失败：");
				
			}

			//执行SQL语句
			if (pFlag)
			{

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE,userChargeDepartmentInfo.getID());

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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IUserChargeDepartmentInfoDao#update(com.orifound.aiim.entity.UserChargeDepartmentInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(UserChargeDepartmentInfo userChargeDepartmentInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IUserChargeDepartmentInfoDao#findByUserID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByUserID(int pUserID, LinkedHashMap<Integer, UserChargeDepartmentInfo> userChargeDepartmentInfos, ErrInfo pErrInfo)
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

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserChargeDepartmentInfo> pUserChargeDepartmentInfos=jdbcTemplate.query(SQL_SELECT_BYUSERID,new UserChargeDepartmentInfoMapper(),pUserID);

				//返回查询结果
				if (pUserChargeDepartmentInfos.size() > 0)
				{
					for (UserChargeDepartmentInfo item : pUserChargeDepartmentInfos)
					{
						userChargeDepartmentInfos.put(item.getDepartmentID(), item);
					}
				}

				//销毁局部变量
				pUserChargeDepartmentInfos=null;
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
	 * @see com.orifound.aiim.dal.dao.IUserChargeDepartmentInfoDao#findByUserID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAllUnchargeDepartment(List<UserChargeDepartmentInfo> userChargeDepartmentInfos, ErrInfo pErrInfo)
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

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserChargeDepartmentInfo> pUserChargeDepartmentInfos = jdbcTemplate.query(SQL_SELECT_UnchargeDepartment , new UserChargeDepartmentInfoMapper2());

				//返回查询结果
				if (pUserChargeDepartmentInfos.size() > 0)
				{
					userChargeDepartmentInfos.addAll(pUserChargeDepartmentInfos);
				}

				//销毁局部变量
				pUserChargeDepartmentInfos=null;
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
