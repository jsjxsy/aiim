/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.*;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.orifound.aiim.dal.dao.IDataSourceItemDao;
import com.orifound.aiim.entity.DataSourceItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 数据源的数据项字典表的DAO实现类
 *
 */
public class DataSourceItemDaoImpl extends JdbcDaoSupport implements IDataSourceItemDao
{
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class DataSourceItemMapper implements RowMapper<DataSourceItem>
	{
		
		@Override
		public DataSourceItem mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int dataSourceID = rs.getInt("DataSourceID");
			int orderID = rs.getInt("OrderID");
			String name = rs.getString("Name");
			String remark = rs.getString("Remark");
			
			return new DataSourceItem(iD,dataSourceID,orderID,name,remark);
		}
	}
	
	/**
	 * 构造函数
	 */
	public DataSourceItemDaoImpl()
	{

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public DataSourceItemDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	//SQL语句
	
	/**
	 * 查询指定数据源包括的数据项信息的SQL语句
	 */
	private final String SQL_SELECT_BYDATASOURCEID = "SELECT * FROM DD_DataSourceItem WHERE DataSourceID=?";
	
	
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
	 * @see com.orifound.aiim.dal.dao.IDataSourceItemDao#save(com.orifound.aiim.entity.DataSourceItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(DataSourceItem dataSourceItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDataSourceItemDao#delete(com.orifound.aiim.entity.DataSourceItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(DataSourceItem dataSourceItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDataSourceItemDao#update(com.orifound.aiim.entity.DataSourceItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(DataSourceItem dataSourceItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDataSourceItemDao#findByDataSourceID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByDataSourceID(int pDataSourceID, LinkedHashMap<Integer, DataSourceItem> dataSourceItems, ErrInfo pErrInfo)
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
				//执行SQL语句
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<DataSourceItem> pDataSourceItems= jdbcTemplate.query(SQL_SELECT_BYDATASOURCEID,new DataSourceItemMapper(),pDataSourceID);
	
				//返回查询结果
				if (pDataSourceItems.size()>0)
				{
					for (DataSourceItem item : pDataSourceItems)
					{
						dataSourceItems.put(item.getID(), item);
					}
				}
				
				//销毁局部变量
				pDataSourceItems=null;
				jdbcTemplate = null;
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
			throwable = null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDataSourceItemDao#findByID(int, com.orifound.aiim.entity.DataSourceItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, DataSourceItem dataSourceItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
