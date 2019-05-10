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
 * 目录打印模板数据项定义表的DAO实现类（SQL SERVER平台）
 *
 */
public class CatalogDataItemDaoImpl extends JdbcDaoSupport implements ICatalogDataItemDao
{
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
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
	 * 构造函数
	 */
	public CatalogDataItemDaoImpl()
	{

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public CatalogDataItemDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	/**
	 * 查询指定档案分类的指定目录打印模板类别所包含的所有数据项的SQL语句
	 */
	private final String SQL_SELECT_By_ArchivesTypeID_CatalogType = "SELECT A.ID,A.OfficialArchivesFlag,ArchivesTypeID,CatalogType,DataItemID,DisplayAlias,DisplayOrderID,DisplayLength,DisplayText,ColumnName,DataTypeID,SystemItemFlag,InputQueryFlag,InputQueryOrderID,UseQueryFlag,UseQueryOrderID,RangeQueryFlag,AssociateTextColumnName,Remark FROM DDR_CatalogPrintTemplate_DataItem A,DD_DataItem B " +
			"WHERE A.OfficialArchivesFlag=:OfficialArchivesFlag AND A.ArchivesTypeID=:ArchivesTypeID AND A.CatalogType=:CatalogType AND A.OfficialArchivesFlag=B.OfficialArchivesFlag AND A.DataItemID=B.ID ORDER BY A.DisplayOrderID ASC";
	
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
				if (pErrInfo.getException() != null)
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

	@Override
	public boolean findByArchivesTypeID(Boolean officialArchivesFlag, int pArchivesTypeID, Integer catalogType, LinkedHashMap<String, CatalogDataItem> catalogDataItems, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			//执行SQL语句
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
				
				//返回查询结果
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
				if (pErrInfo.getException() != null)
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


}
