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
 * 数据项字典表的DAO实现类（SQL Server平台）
 *
 */
public class DataItemDaoImpl extends JdbcDaoSupport implements IDataItemDao
{
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
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
	 * 构造函数
	 */
	public DataItemDaoImpl()
	{

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public DataItemDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
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
