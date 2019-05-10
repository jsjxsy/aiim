/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.*;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.orifound.aiim.dal.dao.IDataSourceDao;
import com.orifound.aiim.entity.DataSource;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 数据源字典表的DAO实现类（SQL Server平台）
 *
 */
public class DataSourceDaoImpl extends JdbcDaoSupport implements IDataSourceDao
{
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class DataSourceMapper implements RowMapper<DataSource>
	{
		
		@Override
		public DataSource mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String name = rs.getString("Name");
			int inherentType = rs.getInt("InherentType");
			String remark = rs.getString("Remark");
			
			return new DataSource(iD,name,inherentType,remark);
		}
	}
	
	/**
	 * 构造函数
	 */
	public DataSourceDaoImpl()
	{

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public DataSourceDaoImpl(javax.sql.DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	
	//SQL语句
	
	/**
	 * 查询所有数据源信息的SQL语句
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM DD_DataSource ORDER BY ID";
	

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
	 * @see com.orifound.aiim.dal.dao.IDataSourceDao#save(com.orifound.aiim.entity.DataSource, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(DataSource dataSource, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDataSourceDao#delete(com.orifound.aiim.entity.DataSource, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(DataSource dataSource, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDataSourceDao#update(com.orifound.aiim.entity.DataSource, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(DataSource dataSource, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDataSourceDao#findAll(java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(Map<Integer, DataSource> dataSources, ErrInfo pErrInfo)
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
				//执行SQL语句
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<DataSource> pDataSources= jdbcTemplate.query(SQL_SELECT_ALL, new DataSourceMapper());
	
				//返回查询结果
				if (pDataSources.size()>0)
				{
					for (DataSource item : pDataSources)
					{
						dataSources.put(item.getID(), item);
					}
				}
				
				//销毁局部变量
				pDataSources=null;
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
			throwable=null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDataSourceDao#findByID(int, com.orifound.aiim.entity.DataSource, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, DataSource dataSource, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
