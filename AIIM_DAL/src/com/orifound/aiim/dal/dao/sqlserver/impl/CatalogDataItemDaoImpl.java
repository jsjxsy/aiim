/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import com.orifound.aiim.dal.dao.ICatalogDataItemDao;
import com.orifound.aiim.entity.CatalogDataItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * Ŀ¼��ӡģ�����������DAOʵ���ࣨSQL SERVERƽ̨��
 *
 */
public class CatalogDataItemDaoImpl extends JdbcDaoSupport implements ICatalogDataItemDao
{
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class CatalogDataItemMapper implements RowMapper<CatalogDataItem>
	{
		
		@Override
		public CatalogDataItem mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			boolean officialArchivesFlag = rs.getBoolean("OfficialArchivesFlag");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int catalogType = rs.getInt("CatalogType");
			int dataItemID = rs.getInt("DataItemID");
			String displayAlias = rs.getString("DisplayAlias");
			int displayOrderID = rs.getInt("DisplayOrderID");
			int displayLength = rs.getInt("DisplayLength");
			
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
			
			return new CatalogDataItem(iD,officialArchivesFlag,archivesTypeID,catalogType,dataItemID,displayAlias,displayOrderID,displayLength,displayText, columnName, dataTypeID, systemItemFlag, inputQueryFlag, inputQueryOrderID, useQueryFlag, useQueryOrderID, rangeQueryFlag, associateTextColumnName, remark);
		}
	}
	
	/**
	 * ���캯��
	 */
	public CatalogDataItemDaoImpl()
	{

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public CatalogDataItemDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	/**
	 * ��ѯָ�����������ָ��Ŀ¼��ӡģ������������������������SQL���
	 */
	private final String SQL_SELECT_By_ArchivesTypeID_CatalogType = "SELECT A.ID,A.OfficialArchivesFlag,ArchivesTypeID,CatalogType,DataItemID,DisplayAlias,DisplayOrderID,DisplayLength,DisplayText,ColumnName,DataTypeID,SystemItemFlag,InputQueryFlag,InputQueryOrderID,UseQueryFlag,UseQueryOrderID,RangeQueryFlag,AssociateTextColumnName,Remark FROM DDR_CatalogPrintTemplate_DataItem A,DD_DataItem B " +
			"WHERE A.OfficialArchivesFlag=:OfficialArchivesFlag AND A.ArchivesTypeID=:ArchivesTypeID AND A.CatalogType=:CatalogType AND A.OfficialArchivesFlag=B.OfficialArchivesFlag AND A.DataItemID=B.ID ORDER BY A.DisplayOrderID ASC";
	
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

	@Override
	public boolean findByArchivesTypeID(Boolean officialArchivesFlag, int pArchivesTypeID, Integer catalogType, LinkedHashMap<String, CatalogDataItem> catalogDataItems, ErrInfo pErrInfo)
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
				//SQL_SELECT_By_ArchivesTypeID_CatalogType = "SELECT A.ID,OfficialArchivesFlag,ArchivesTypeID,CatalogType,DataItemID,DisplayAlias,DisplayOrderID,DisplayLength,DisplayText,ColumnName,DataTypeID,SystemItemFlag,InputQueryFlag,InputQueryOrderID,UseQueryFlag,UseQueryOrderID,RangeQueryFlag,AssociateTextColumnName,Remark FROM DDR_CatalogPrintTemplate_DataItem A,DD_DataItem B " +
				//"WHERE A.OfficialArchivesFlag=:OfficialArchivesFlag AND A.ArchivesTypeID=:ArchivesTypeID AND A.CatalogType=:CatalogType AND A.OfficialArchivesFlag=B.OfficialArchivesFlag AND A.DataItemID=B.ID ORDER BY A.DisplayOrderID ASC";
		
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("OfficialArchivesFlag", officialArchivesFlag, Types.BIT);
				paramSource.addValue("ArchivesTypeID", pArchivesTypeID, Types.INTEGER);
				paramSource.addValue("CatalogType", catalogType, Types.INTEGER);

				pErrPos = 3;
				List<CatalogDataItem> pCatalogDataItems=namedParameterJdbcTemplate.query(SQL_SELECT_By_ArchivesTypeID_CatalogType, paramSource, new CatalogDataItemMapper());
				
				//���ز�ѯ���
				if (pCatalogDataItems.size()>0)
				{
					for (CatalogDataItem item : pCatalogDataItems)
					{
						catalogDataItems.put(item.getColumnName(), item);
					}
				}
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


}
