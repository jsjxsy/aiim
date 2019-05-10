/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.RowMapper;
import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IArchivesTypeDataItemDao;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ���������������ֵ���DAOʵ���ࣨSQL Serverƽ̨��
 *
 */
public class ArchivesTypeDataItemDaoImpl extends JdbcDaoSupport implements IArchivesTypeDataItemDao
{
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class ArchivesTypeDataItemMapper implements RowMapper<ArchivesTypeDataItem>
	{
		
		@Override
		public ArchivesTypeDataItem mapRow(ResultSet rs, int rowNum) throws SQLException
		{
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

			int archivesTypeDataItemID = rs.getInt("ID");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int dataItemID = rs.getInt("DataItemID");
			boolean inputFlag = rs.getBoolean("InputFlag");
			int inputOrderID = rs.getInt("InputOrderID");
			boolean listDisplayFlag = rs.getBoolean("ListDisplayFlag");
			int listDisplayOrderID = rs.getInt("ListDisplayOrderID");
			int length = rs.getInt("Length");
			boolean mandatory = rs.getBoolean("Mandatory");
			int dataSourceID = rs.getInt("DataSourceID");
			String defaultValue = rs.getString("DefaultValue");
			int editStyle = rs.getInt("EditStyle");
			int inputEditBoxWidth = rs.getInt("InputEditBoxWidth");
			int inputEditBoxHeight = rs.getInt("InputEditBoxHeight");
			int queryEditBoxWidth = rs.getInt("QueryEditBoxWidth");
			int queryEditBoxHeight = rs.getInt("QueryEditBoxHeight");
			int checkRuleID = rs.getInt("CheckRuleID");
			int listDisplayLength = rs.getInt("ListDisplayLength");
			boolean inputHoldFlag = rs.getBoolean("InputHoldFlag");
			
			return new ArchivesTypeDataItem(officialArchivesFlag,displayText, columnName, dataTypeID, systemItemFlag, inputQueryFlag, inputQueryOrderID, useQueryFlag, useQueryOrderID, rangeQueryFlag, associateTextColumnName, remark, archivesTypeDataItemID, archivesTypeID, dataItemID, inputFlag, inputOrderID, listDisplayFlag, listDisplayOrderID, length, mandatory, dataSourceID, defaultValue, editStyle, inputEditBoxWidth, inputEditBoxHeight, queryEditBoxWidth, queryEditBoxHeight, checkRuleID, listDisplayLength,inputHoldFlag);
		}
	}
	
	
	/**
	 * ���캯��
	 */
	public ArchivesTypeDataItemDaoImpl()
	{

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public ArchivesTypeDataItemDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	
	/**
	 * ��ѯָ�����ඨ��������������SQL��䣬ʵ����൵���͹��ĵ������������ѯ���ø����
	 */
	private final String SQL_SELECT_BY_ArchivesTypeID = "SELECT A.ID,ArchivesTypeID,DataItemID,InputFlag,InputOrderID,ListDisplayFlag,ListDisplayOrderID,Length,Mandatory,DataSourceID,DefaultValue,EditStyle,InputEditBoxWidth,InputEditBoxHeight,QueryEditBoxWidth,QueryEditBoxHeight,CheckRuleID,ListDisplayLength,InputHoldFlag,B.OfficialArchivesFlag,DisplayText,ColumnName,DataTypeID,SystemItemFlag,InputQueryFlag,InputQueryOrderID,UseQueryFlag,UseQueryOrderID,RangeQueryFlag,AssociateTextColumnName,Remark FROM DDR_ArchivesType_DataItem A,DD_DataItem B WHERE A.OfficialArchivesFlag=:OfficialArchivesFlag AND A.ArchivesTypeID=:ArchivesTypeID AND A.OfficialArchivesFlag=B.OfficialArchivesFlag AND A.DataItemID=B.ID ORDER BY A.ID";
	
	
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
	 * @see com.orifound.aiim.dal.dao.IArchivesTypeDataItemDao#save(com.orifound.aiim.entity.ArchivesTypeDataItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesTypeDataItemDao#delete(com.orifound.aiim.entity.ArchivesTypeDataItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesTypeDataItemDao#update(com.orifound.aiim.entity.ArchivesTypeDataItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesTypeDataItemDao#findByArchivesTypeID(Boolean, int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByArchivesTypeID(Boolean officialArchivesFlag, int pArchivesTypeID, LinkedHashMap<String, ArchivesTypeDataItem> archivesTypeDataItems, ErrInfo pErrInfo)
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
				pErrPos=1;
				//ִ��SQL���
				//SQL_SELECT_BY_ArchivesTypeID = "SELECT A.ID,ArchivesTypeID,DataItemID,InputFlag,InputOrderID,ListDisplayFlag,ListDisplayOrderID,Length,Mandatory,DataSourceID,DefaultValue,EditStyle,InputEditBoxWidth,InputEditBoxHeight,QueryEditBoxWidth,QueryEditBoxHeight,CheckRuleID,ListDisplayLength,OfficialArchivesFlag,DisplayText,ColumnName,DataTypeID,SystemItemFlag,InputQueryFlag,InputQueryOrderID,UseQueryFlag,UseQueryOrderID,RangeQueryFlag,AssociateTextColumnName,Remark FROM DDR_ArchivesType_DataItem A,DD_DataItem B 
				//WHERE A.OfficialArchivesFlag=:OfficialArchivesFlag AND A.ArchivesTypeID=:ArchivesTypeID AND A.OfficialArchivesFlag=B.OfficialArchivesFlag AND A.DataItemID=B.ID ORDER BY A.ID";
				pErrPos = 1;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("OfficialArchivesFlag", officialArchivesFlag, Types.BIT);
				paramSource.addValue("ArchivesTypeID", pArchivesTypeID, Types.INTEGER);

				pErrPos = 2;
				List<ArchivesTypeDataItem> pArchivesTypeDataItems=namedParameterJdbcTemplate.query(SQL_SELECT_BY_ArchivesTypeID, paramSource,new ArchivesTypeDataItemMapper());
				
	
				//���ز�ѯ���
				if (pArchivesTypeDataItems.size()>0)
				{
					for (ArchivesTypeDataItem item : pArchivesTypeDataItems)
					{
						archivesTypeDataItems.put(item.getColumnName(), item);
					}
				}
				
				//���پֲ�����
				pArchivesTypeDataItems=null;
				paramSource = null;
				namedParameterJdbcTemplate=null;
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

		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesTypeDataItemDao#findByID(int, com.orifound.aiim.entity.ArchivesTypeDataItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
