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

import com.orifound.aiim.dal.dao.IDataTypeDao;
import com.orifound.aiim.entity.DataType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 数据项字段类型字典表的DAO实现类（SQL Server平台）
 *
 */
public class DataTypeDaoImpl extends JdbcDaoSupport implements IDataTypeDao
{
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class DataTypeMapper implements RowMapper<DataType>
	{
		
		@Override
		public DataType mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			String iD = rs.getString("ID");
			String name = rs.getString("Name");
			String remark = rs.getString("Remark");
			Object oio = new Object();
			
			DataType df =  new DataType(iD,name,remark);
			 df.setTag(oio);
			 return df;
			
		}
	}
	
	/**
	 * 构造函数
	 */
	public DataTypeDaoImpl()
	{

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public DataTypeDaoImpl(DataSource dataSource)
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
	 * @see com.orifound.aiim.dal.dao.IDataTypeDao#save(com.orifound.aiim.entity.DataType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(DataType dataType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDataTypeDao#delete(com.orifound.aiim.entity.DataType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(DataType dataType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDataTypeDao#update(com.orifound.aiim.entity.DataType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(DataType dataType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDataTypeDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<DataType> dataTypes, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDataTypeDao#findByID(int, com.orifound.aiim.entity.DataType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, DataType dataType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
