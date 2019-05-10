/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.*;

import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.orifound.aiim.dal.dao.IDataItemDao;
import com.orifound.aiim.entity.DataItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �������ֵ���DAOʵ���ࣨSQL Serverƽ̨��
 *
 */
public class DataItemDaoImpl extends JdbcDaoSupport implements IDataItemDao
{
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class DataItemMapper implements RowMapper<DataItem>
	{
		
		@Override
		public DataItem mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			boolean officialArchivesFlag = rs.getBoolean("OfficialArchivesFlag");
			String displayText = rs.getString("DisplayText");
			String columnName = rs.getString("ColumnName");
			String dataTypeID = rs.getString("DataTypeID");
			boolean systemItemFlag = rs.getBoolean("SystemItemFlag");
			boolean inputQueryFlag = rs.getBoolean("InputQueryFlag");
			int inputQueryOrderID = rs.getInt("InputQueryOrderID");
			boolean useQueryFlag = rs.getBoolean("UseQueryFlag");
			int useQueryOrderID = rs.getInt("UseQueryOrderID");
			boolean rangeQueryFlag = rs.getBoolean("RangeQueryFlag");
			String associateTextColumnName = rs.getString("AssociateTextColumnName");
			String remark = rs.getString("Remark");
			
			return new DataItem(iD,officialArchivesFlag,displayText,columnName,dataTypeID,systemItemFlag,inputQueryFlag,inputQueryOrderID,useQueryFlag,useQueryOrderID,rangeQueryFlag,associateTextColumnName,remark);
		}
	}
	
	/**
	 * ���캯��
	 */
	public DataItemDaoImpl()
	{

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public DataItemDaoImpl(DataSource dataSource)
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
	 * @see com.orifound.aiim.dal.dao.IDataItemDao#save(com.orifound.aiim.entity.DataItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(DataItem dataItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDataItemDao#delete(com.orifound.aiim.entity.DataItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(DataItem dataItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDataItemDao#update(com.orifound.aiim.entity.DataItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(DataItem dataItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDataItemDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<DataItem> dataItems, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDataItemDao#findByID(int, com.orifound.aiim.entity.DataItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, DataItem dataItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
