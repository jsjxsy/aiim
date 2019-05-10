/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import com.orifound.aiim.dal.dao.ICurrentContentIDDao;
import com.orifound.aiim.entity.CurrentContentID;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ��ǰ�������Ϣ���DAOʵ���ࣨSQL SERVERƽ̨��
 *
 */
public class CurrentContentIDDaoImpl extends JdbcDaoSupport implements ICurrentContentIDDao
{
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class CurrentContentIDMapper implements RowMapper<CurrentContentID>
	{
		
		@Override
		public CurrentContentID mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			String archivesIDPrefix = rs.getString("ArchivesIDPrefix");
			int contentID = rs.getInt("ContentID");
			
			return new CurrentContentID(archivesIDPrefix,contentID);
		}
	}
	
	/**
	 * ���캯��
	 */
	public CurrentContentIDDaoImpl()
	{

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public CurrentContentIDDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
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
				if (pErrInfo.getException() != null)
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
	
	/**
	 * ���뱣�������ĵ�ǰ�������Ϣ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO CurrentContentID(ArchivesIDPrefix,ContentID) VALUES(:ArchivesIDPrefix,1)";
	
	/**
	 * ����ָ���ĵ�ǰ�������Ϣ���ۼ�1����SQL���
	 */
	private final String SQL_UPDATE = "UPDATE CurrentContentID SET ContentID=ContentID+1 WHERE ArchivesIDPrefix=:ArchivesIDPrefix";
	
	/**
	 * ��ѯָ���ĵ�ǰ�������Ϣ��SQL���
	 */
	private final String SQL_SELECT = "SELECT * FROM CurrentContentID WHERE ArchivesIDPrefix=:ArchivesIDPrefix";

	/**
	 * ��ѯָ���ĵ�ǰ�������Ϣ��SQL���
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM CurrentContentID";
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.ICurrentContentIDDao#save(com.orifound.aiim.entity.CurrentContentID, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(CurrentContentID currentContentID, ErrInfo pErrInfo)
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
			
			//��鵱ǰ�������Ϣ��������
			if (pFlag)
			{
				pErrPos = 1;
				if (checkCurrentContentID(currentContentID, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ǰ�������Ϣ������: ");
				}
			}

			//ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("ArchivesIDPrefix", currentContentID.getArchivesIDPrefix(), Types.VARCHAR);

				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource);

				//���پֲ�����
				paramSource=null;
				namedParameterJdbcTemplate = null;
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
				if (pErrInfo.getException() != null)
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
	 * @see com.orifound.aiim.dal.dao.ICurrentContentIDDao#update(com.orifound.aiim.entity.CurrentContentID, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(CurrentContentID currentContentID, ErrInfo pErrInfo)
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
			
			//��鵱ǰ�������Ϣ��������
			if (pFlag)
			{
				if (checkCurrentContentID(currentContentID, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ǰ�������Ϣ������: ");
				}
			}

			//ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("ArchivesIDPrefix", currentContentID.getArchivesIDPrefix(), Types.VARCHAR);

				pErrPos = 4;
				namedParameterJdbcTemplate.update(SQL_UPDATE, paramSource);

				//���پֲ�����
				paramSource=null;
				namedParameterJdbcTemplate = null;
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
				if (pErrInfo.getException() != null)
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
	
	/**
	 * ��鵱ǰ�������Ϣ��������
	 * @param currentContentID ��ǰ�������Ϣ
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkCurrentContentID(CurrentContentID currentContentID, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//��鵱ǰ�������Ϣ�Ƿ�Ϊ��
			pErrPos = 1;
			if (currentContentID==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("��ǰ�������Ϣ�Ƿ�Ϊ�ա�");
			}

			//��鵵��ȫ�ڱ���Ƿ�Ϊ��
			if (pFlag)
			{
				if (currentContentID.getArchivesIDPrefix()==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����ȫ�ڱ�ŷǷ�Ϊ�ա�");
				}
				else 
				{
					if ("".equals(currentContentID.getArchivesIDPrefix()))
					{
						pFlag = false;
						pErrInfo.getContent().append("����ȫ�ڱ�ŷǷ�Ϊ�ա�");
					}
				}
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
				if (pErrInfo.getException() != null)
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
	 * @see com.orifound.aiim.dal.dao.ICurrentContentIDDao#findByID(java.lang.String, int, com.orifound.aiim.entity.CurrentContentID, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByPrefix(String archivesIDPrefix,CurrentContentID currentContentID, ErrInfo pErrInfo)
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
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("ArchivesIDPrefix", archivesIDPrefix, Types.VARCHAR);

				pErrPos = 4;
				List<CurrentContentID> pCurrentContentIDs=namedParameterJdbcTemplate.query(SQL_SELECT, paramSource,new CurrentContentIDMapper());

				//���ز�ѯ���
				if (pCurrentContentIDs.size()>0)
				{
					currentContentID.cloneFrom(pCurrentContentIDs.get(0));
				}
				
				//���پֲ�����
				pCurrentContentIDs=null;
				paramSource=null;
				namedParameterJdbcTemplate = null;
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
				if (pErrInfo.getException() != null)
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

	@Override
	public boolean findAll(List<CurrentContentID> pCurrentContentIDs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				List<CurrentContentID> currentContentIDs = getJdbcTemplate().query(SQL_SELECT_ALL,new CurrentContentIDMapper());

				//���ز�ѯ���
				if (currentContentIDs.size() > 0) {
					pCurrentContentIDs.addAll(currentContentIDs);
				}
			}
		} catch (BadSqlGrammarException e) {
			//SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//�����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {

			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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

}
