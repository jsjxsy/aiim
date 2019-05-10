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
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IArchivesInfoTableDao;
import com.orifound.aiim.entity.ArchivesInfoTable;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ���������Ϣ��DD_ArchivesInfoTable����DAOʵ���ࣨSQL Serverƽ̨��
 *
 */
public class ArchivesInfoTableDaoImpl extends JdbcDaoSupport implements IArchivesInfoTableDao
{
	/**
	 * ��ѯ�������ʵ�����ӳ����
	 *
	 */
	private class ArchivesInfoTableMapper implements RowMapper<ArchivesInfoTable>
	{

		@Override
		public ArchivesInfoTable mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD=rs.getInt("ID");
			int archivesTypeID=rs.getInt("ArchivesTypeID");
			int tableType=rs.getInt("TableType");
			String tableName=rs.getString("TableName");
			boolean createdFlag=rs.getBoolean("CreatedFlag");
			Date createdTime=rs.getTimestamp("CreatedTime");
			String remark=rs.getString("Remark");

			return new ArchivesInfoTable(iD, archivesTypeID, tableType, tableName, 
					createdFlag, createdTime, remark);
		}
	}
	
	/**
	 * ���캯��
	 */
	public ArchivesInfoTableDaoImpl()
	{

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public ArchivesInfoTableDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	
	//SQL���
	private final String SQL_SELECT_BYTYPEID="SELECT * FROM DD_ArchivesInfoTable WHERE ArchivesTypeID=?";
	
	
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
			if (getDataSource()==null)
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
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoTableDao#save(com.orifound.aiim.entity.ArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoTableDao#delete(int, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteByArchivesTypeID(int pArchivesTypeID, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoTableDao#setCreatedFlag(com.orifound.aiim.entity.ArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean setCreatedFlag(ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoTableDao#findByArchivesTypeID(int, java.util.EnumMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByArchivesTypeID(int pArchivesTypeID, EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable> archivesInfoTables, ErrInfo pErrInfo)
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
			
			//ִ��SQL���
			if (pFlag)
			{
				pErrPos=1;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<ArchivesInfoTable> pArchivesInfoTables= jdbcTemplate.query(SQL_SELECT_BYTYPEID, new ArchivesInfoTableMapper(),pArchivesTypeID);
				
				//���ز�ѯ���
				if (pArchivesInfoTables.size()>0)
				{
					for (ArchivesInfoTable item : pArchivesInfoTables)
					{
						archivesInfoTables.put(item.getTableType(), item);
					}
				}
				
				//���پ߱�����
				pArchivesInfoTables=null;
				jdbcTemplate=null;
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
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoTableDao#findByID(int, com.orifound.aiim.entity.ArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
