/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IRetentionPeriodDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.RetentionPeriod;

/**
 * ���������ֵ���DAOʵ���ࣨSQL Serverƽ̨��
 *
 */
public class RetentionPeriodDaoImpl extends JdbcDaoSupport implements IRetentionPeriodDao
{
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class RetentionPeriodMapper implements RowMapper<RetentionPeriod>
	{
		
		@Override
		public RetentionPeriod mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String name = rs.getString("Name");
			int totalYears = rs.getInt("TotalYears");
			String remark = rs.getString("Remark");
			
			return new RetentionPeriod(iD,name,totalYears,remark);
		}
	}
	
	/**
	 * ���캯��
	 */
	public RetentionPeriodDaoImpl()
	{

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public RetentionPeriodDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	/**
	 * ��ѯ���б������޵�SQL���
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM DD_RetentionPeriod ORDER BY ID";
	
	/**
	 * ���������ֵ������ݷ��ʶ���
	 */
	private IRetentionPeriodDao retentionPeriodDao = null;

	/**
	 * ��ȡ����ֵ�����������ֵ������ݷ��ʶ���
	 * @return ���������ֵ������ݷ��ʶ���
	 */
	public IRetentionPeriodDao getRetentionPeriodDao()
	{
		return retentionPeriodDao;
	}

	/**
	 * ��������ֵ�����������ֵ������ݷ��ʶ���
	 * @param retentionPeriodDao ���������ֵ������ݷ��ʶ���
	 */
	public void setRetentionPeriodDao(IRetentionPeriodDao retentionPeriodDao)
	{
		this.retentionPeriodDao = retentionPeriodDao;
	}
	
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
	
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IRetentionPeriodDao#save(com.orifound.aiim.entity.RetentionPeriod, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(RetentionPeriod retentionPeriod, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IRetentionPeriodDao#delete(com.orifound.aiim.entity.RetentionPeriod, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(RetentionPeriod retentionPeriod, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IRetentionPeriodDao#update(com.orifound.aiim.entity.RetentionPeriod, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(RetentionPeriod retentionPeriod, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IRetentionPeriodDao#findAll(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(LinkedHashMap<Integer,RetentionPeriod> retentionPeriods, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
				
			}

			//ִ��SQL���
			if (pFlag)
			{

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<RetentionPeriod> pRetentionPeriods=jdbcTemplate.query(SQL_SELECT_ALL,new RetentionPeriodMapper());

				//���ز�ѯ���
				if (pRetentionPeriods.size() > 0)
				{
					for (RetentionPeriod item : pRetentionPeriods)
					{
						retentionPeriods.put(item.getID(), item);
					}
				}

				//���پֲ�����
				pRetentionPeriods=null;
				jdbcTemplate = null;
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
			throwable = null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IRetentionPeriodDao#findByID(int, com.orifound.aiim.entity.RetentionPeriod, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, RetentionPeriod retentionPeriod, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}
}