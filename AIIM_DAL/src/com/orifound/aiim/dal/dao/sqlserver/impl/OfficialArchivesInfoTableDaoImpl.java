/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.Date;
import java.util.EnumMap;
import java.util.List;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.orifound.aiim.dal.dao.IOfficialArchivesInfoTableDao;
import com.orifound.aiim.entity.EnumOfficialArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesInfoTable;

/**
 * ���ĵ�����Ϣ��ر���Ϣ��DAOʵ���ࣨSQL SERVER ƽ̨��
 *
 */
public class OfficialArchivesInfoTableDaoImpl extends JdbcDaoSupport implements IOfficialArchivesInfoTableDao
{
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class OfficialArchivesInfoTableMapper implements RowMapper<OfficialArchivesInfoTable>
	{
		
		@Override
		public OfficialArchivesInfoTable mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int tableType = rs.getInt("TableType");
			String tableName = rs.getString("TableName");
			boolean createdFlag = rs.getBoolean("CreatedFlag");
			Date createdTime = rs.getTimestamp("CreatedTime");
			String remark = rs.getString("Remark");
			
			return new OfficialArchivesInfoTable(iD,archivesTypeID,tableType,tableName,createdFlag,createdTime,remark);
		}
	}
	
	/**
	 * ���캯��
	 */
	public OfficialArchivesInfoTableDaoImpl()
	{

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public OfficialArchivesInfoTableDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	/**
	 * ��ѯָ�����ĵ��������������ر���Ϣ��SQL���
	 */
	private final String SQL_SELECT_By_ArchivesTypeID = "SELECT * FROM DD_OfficialArchivesInfoTable WHERE ArchivesTypeID=?";
	
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IOfficialArchivesInfoTableDao#save(com.orifound.aiim.entity.OfficialArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(com.orifound.aiim.entity.OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IOfficialArchivesInfoTableDao#delete(com.orifound.aiim.entity.OfficialArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(com.orifound.aiim.entity.OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IOfficialArchivesInfoTableDao#update(com.orifound.aiim.entity.OfficialArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(com.orifound.aiim.entity.OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IOfficialArchivesInfoTableDao#findByArchivesTypeID(int, java.util.EnumMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByArchivesTypeID(int pArchivesTypeID,
			EnumMap<EnumOfficialArchivesInfoTableType, com.orifound.aiim.entity.OfficialArchivesInfoTable> officialArchivesInfoTables, ErrInfo pErrInfo)
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
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<OfficialArchivesInfoTable> pOfficialArchivesInfoTables=jdbcTemplate.query(SQL_SELECT_By_ArchivesTypeID,new OfficialArchivesInfoTableMapper(),pArchivesTypeID);

				//���ز�ѯ���
				if (pOfficialArchivesInfoTables.size() > 0)
				{
					for (OfficialArchivesInfoTable item : pOfficialArchivesInfoTables)
					{
						officialArchivesInfoTables.put(item.getTableType(), item);
					}
				}

				//���پֲ�����
				pOfficialArchivesInfoTables=null;
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
	 * @see com.orifound.aiim.dal.dao.IOfficialArchivesInfoTableDao#findByID(int, com.orifound.aiim.entity.OfficialArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, com.orifound.aiim.entity.OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
