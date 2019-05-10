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
 * Config���DAOʵ���ࣨSQL Serverƽ̨��
 * 
 */
public class ConfigDaoImpl extends JdbcDaoSupport implements IConfigDao
{
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
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
	 * ���캯��
	 */
	public ConfigDaoImpl()
	{

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public ConfigDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	//SQL���
	
	private final String SELECTSQL_BYID = "SELECT * FROM Config WHERE ID=?";
	private final String SELECTSQL = "SELECT * FROM Config WHERE ConfigType=?";
	private final String UPDATESQL = "UPDATE Config SET ConfigValue=:ConfigValue WHERE ID=:ID";
	//private final String INSERTSQL = "INSERT INTO Config(ConfigType,ConfigValue) Values(?,?)";
	private final String DELETESQL = "DELETE FROM Config WHERE ID=?";

	/**
	 * ���JDBC����Դ������ע�루JDBC DataSource Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean CheckDataSourceInjection(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//�������Դ�Ƿ�Ϊ��
			pErrPos = 1;
			if (getDataSource() == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("JDBC����Դ�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		}
		catch (Exception e)
		{
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException()!=null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
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
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo)==false)
			{
				pFlag = false;
				
			}
			
			if (pFlag)
			{
				//ִ��SQL���
				JdbcTemplate jtp = this.getJdbcTemplate();
				jtp.update(DELETESQL, config.getID());
				
				jtp=null;
			}
		}
		catch (BadSqlGrammarException e)
		{
			//SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			//�����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException()!=null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			
			//���پֲ�����
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
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo)==false)
			{
				pFlag = false;
				
			}
			
			if (pFlag)
			{
				//ִ��SQL���
				JdbcTemplate jtp = this.getJdbcTemplate();
				List<Config> configs = jtp.query(SELECTSQL, new ConfigMapper(), pConfigType);
				
				if (configs.size()>0)
				{
					pConfigs.addAll(configs);
				}
				
				//���پ߱�����
				configs=null;
				jtp=null;
			}
		}
		catch (BadSqlGrammarException e)
		{
			//SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			//�����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{

			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException()!=null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			
			//���پֲ�����
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
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo)==false)
			{
				pFlag = false;
				
			}
			
			if (pFlag)
			{
				//ִ��SQL���
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
			//SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			//�����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
		
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException()!=null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			
			//���پֲ�����
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
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo)==false)
			{
				pFlag = false;
				
			}
			
			if (pFlag)
			{
				//ִ��SQL���
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
			//SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			//�����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{

			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException()!=null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			
			//���پֲ�����
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
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo)==false)
			{
				pFlag = false;
				
			}
			
			if (pFlag)
			{
				List<Config> configs=null;
				
				//ִ��SQL���
				JdbcTemplate jtp = this.getJdbcTemplate();
				configs= jtp.query(SELECTSQL_BYID, new ConfigMapper(), pID);
				
				//���ز�ѯ���
				if (configs.size()>0)
				{
					config.cloneFrom(configs.get(0));
				}
				
				//���پֲ�����
				configs=null;
				jtp=null;
			}
		}
		catch (BadSqlGrammarException e)
		{
			//SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			//�����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{

			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException()!=null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			
			//���پֲ�����
			throwable=null;
		}

		return pFlag;
	}

}
