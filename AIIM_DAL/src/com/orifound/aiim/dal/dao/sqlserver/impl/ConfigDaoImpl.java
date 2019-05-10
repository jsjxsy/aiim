/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.sql.DataSource;



import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


import com.orifound.aiim.dal.dao.IConfigDao;
import com.orifound.aiim.entity.Config;
import com.orifound.aiim.entity.ErrInfo;

/**
 * Config表的DAO实现类（SQL Server平台）
 * 
 */
public class ConfigDaoImpl extends JdbcDaoSupport implements IConfigDao
{
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class ConfigMapper implements RowMapper<Config>
	{
		
		@Override
		public Config mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String configType = rs.getString("ConfigType");
			String configValue = rs.getString("ConfigValue");
			String minValue = rs.getString("MinValue");
			String maxValue = rs.getString("MaxValue");
			String ftpServer = rs.getString("FtpServer");
			String ftpUser = rs.getString("FtpUser");
			String ftpPassword = rs.getString("FtpPassword");
			String localPath = rs.getString("LocalPath");
			
			return new Config(iD,configType,configValue,minValue,maxValue,ftpServer,ftpUser,ftpPassword,localPath);
		}
	}
	
	/**
	 * 构造函数
	 */
	public ConfigDaoImpl()
	{

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public ConfigDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	//SQL语句
	
	private final String SELECTSQL_BYID = "SELECT * FROM Config WHERE ID=?";
	private final String SELECTSQL = "SELECT * FROM Config WHERE ConfigType=?";
	private final String UPDATESQL = "UPDATE Config SET ConfigValue=:ConfigValue WHERE ID=:ID";
	//private final String INSERTSQL = "INSERT INTO Config(ConfigType,ConfigValue) Values(?,?)";
	private final String DELETESQL = "DELETE FROM Config WHERE ID=?";

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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.dal.dao.IConfigDAO#delete(com.orifound.aiim.entity.
	 * Config, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(Config config, ErrInfo pErrInfo)
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
				JdbcTemplate jtp = this.getJdbcTemplate();
				jtp.update(DELETESQL, config.getID());
				
				jtp=null;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orifound.aiim.dal.dao.IConfigDAO#find(java.lang.String,
	 * com.orifound.aiim.entity.Config, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByConfigType(String pConfigType, List<Config> pConfigs, ErrInfo pErrInfo)
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
				JdbcTemplate jtp = this.getJdbcTemplate();
				List<Config> configs = jtp.query(SELECTSQL, new ConfigMapper(), pConfigType);
				
				if (configs.size()>0)
				{
					pConfigs.addAll(configs);
				}
				
				//销毁具备变量
				configs=null;
				jtp=null;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.dal.dao.IConfigDAO#save(com.orifound.aiim.entity.Config
	 * , com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(Config config, ErrInfo pErrInfo)
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
				SimpleJdbcInsert jinsertor = new SimpleJdbcInsert(getDataSource());
				jinsertor.withTableName("Config");
				jinsertor.usingColumns("ConfigType","ConfigValue");
				jinsertor.usingGeneratedKeyColumns("ID");
				SqlParameterSource ps=new MapSqlParameterSource("ConfigType",config.getConfigType())
												.addValue("ConfigValue", config.getConfigValue());
				
				int id=0;
				
				id=Integer.parseInt(jinsertor.executeAndReturnKey(ps).toString());
				
				//INSERTSQL = "INSERT INTO Config(ConfigType,ConfigValue) Values(?,?)";
				
				config.setID(id);
				
				ps=null;
				jinsertor=null;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.dal.dao.IConfigDAO#update(com.orifound.aiim.entity.
	 * Config, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(Config config, ErrInfo pErrInfo)
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
				//UPDATESQL = "UPDATE Config SET ConfigValue=:ConfigValue WHERE ID=:ID";
				NamedParameterJdbcTemplate njtp=new NamedParameterJdbcTemplate(getJdbcTemplate().getDataSource());
				SqlParameterSource params=new MapSqlParameterSource("ConfigValue", config.getConfigValue())
											.addValue("ID", 7, java.sql.Types.INTEGER);
				njtp.update(UPDATESQL, params);
				
				params=null;
				njtp=null;
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

	@Override
	public boolean findByID(int pID, Config config, ErrInfo pErrInfo)
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
				List<Config> configs=null;
				
				//执行SQL语句
				JdbcTemplate jtp = this.getJdbcTemplate();
				configs= jtp.query(SELECTSQL_BYID, new ConfigMapper(), pID);
				
				//返回查询结果
				if (configs.size()>0)
				{
					config.cloneFrom(configs.get(0));
				}
				
				//销毁局部变量
				configs=null;
				jtp=null;
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

}
