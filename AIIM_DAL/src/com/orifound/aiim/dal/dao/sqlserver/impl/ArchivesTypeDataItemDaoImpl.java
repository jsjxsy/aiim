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
 * 档案分类数据项字典表的DAO实现类（SQL Server平台）
 *
 */
public class ArchivesTypeDataItemDaoImpl extends JdbcDaoSupport implements IArchivesTypeDataItemDao
{
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
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
	 * 构造函数
	 */
	public ArchivesTypeDataItemDaoImpl()
	{

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public ArchivesTypeDataItemDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	
	/**
	 * 查询指定分类定义的所有数据项的SQL语句，实体分类档案和公文档案的数据项查询都用该语句
	 */
	private final String SQL_SELECT_BY_ArchivesTypeID = "SELECT A.ID,ArchivesTypeID,DataItemID,InputFlag,InputOrderID,ListDisplayFlag,ListDisplayOrderID,Length,Mandatory,DataSourceID,DefaultValue,EditStyle,InputEditBoxWidth,InputEditBoxHeight,QueryEditBoxWidth,QueryEditBoxHeight,CheckRuleID,ListDisplayLength,InputHoldFlag,B.OfficialArchivesFlag,DisplayText,ColumnName,DataTypeID,SystemItemFlag,InputQueryFlag,InputQueryOrderID,UseQueryFlag,UseQueryOrderID,RangeQueryFlag,AssociateTextColumnName,Remark FROM DDR_ArchivesType_DataItem A,DD_DataItem B WHERE A.OfficialArchivesFlag=:OfficialArchivesFlag AND A.ArchivesTypeID=:ArchivesTypeID AND A.OfficialArchivesFlag=B.OfficialArchivesFlag AND A.DataItemID=B.ID ORDER BY A.ID";
	
	
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
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo)==false)
			{
				pFlag = false;
				
			}
			
			if (pFlag)
			{
				pErrPos=1;
				//执行SQL语句
				//SQL_SELECT_BY_ArchivesTypeID = "SELECT A.ID,ArchivesTypeID,DataItemID,InputFlag,InputOrderID,ListDisplayFlag,ListDisplayOrderID,Length,Mandatory,DataSourceID,DefaultValue,EditStyle,InputEditBoxWidth,InputEditBoxHeight,QueryEditBoxWidth,QueryEditBoxHeight,CheckRuleID,ListDisplayLength,OfficialArchivesFlag,DisplayText,ColumnName,DataTypeID,SystemItemFlag,InputQueryFlag,InputQueryOrderID,UseQueryFlag,UseQueryOrderID,RangeQueryFlag,AssociateTextColumnName,Remark FROM DDR_ArchivesType_DataItem A,DD_DataItem B 
				//WHERE A.OfficialArchivesFlag=:OfficialArchivesFlag AND A.ArchivesTypeID=:ArchivesTypeID AND A.OfficialArchivesFlag=B.OfficialArchivesFlag AND A.DataItemID=B.ID ORDER BY A.ID";
				pErrPos = 1;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("OfficialArchivesFlag", officialArchivesFlag, Types.BIT);
				paramSource.addValue("ArchivesTypeID", pArchivesTypeID, Types.INTEGER);

				pErrPos = 2;
				List<ArchivesTypeDataItem> pArchivesTypeDataItems=namedParameterJdbcTemplate.query(SQL_SELECT_BY_ArchivesTypeID, paramSource,new ArchivesTypeDataItemMapper());
				
	
				//返回查询结果
				if (pArchivesTypeDataItems.size()>0)
				{
					for (ArchivesTypeDataItem item : pArchivesTypeDataItems)
					{
						archivesTypeDataItems.put(item.getColumnName(), item);
					}
				}
				
				//销毁局部变量
				pArchivesTypeDataItems=null;
				paramSource = null;
				namedParameterJdbcTemplate=null;
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
