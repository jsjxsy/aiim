/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IDataSourceManageService;
import com.orifound.aiim.dal.dao.IDataSourceDao;
import com.orifound.aiim.entity.DataSource;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 数据源管理服务实现类
 *
 */
public class DataSourceManageServiceImpl implements IDataSourceManageService
{
	/**
	 * 构造函数
	 */
	public DataSourceManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public DataSourceManageServiceImpl(IDataSourceDao dataSourceDao)
	{
		this.dataSourceDao = dataSourceDao;
	}
	
	/**
	 * 数据源字典表的数据访问对象
	 */
	private IDataSourceDao dataSourceDao = null;

	/**
	 * 获取属性值：数据源字典表的数据访问对象
	 * @return 数据源字典表的数据访问对象
	 */
	public IDataSourceDao getDataSourceDao()
	{
		return dataSourceDao;
	}

	/**
	 * 设置属性值：数据源字典表的数据访问对象
	 * @param dataSourceDao 数据源字典表的数据访问对象
	 */
	public void setDataSourceDao(IDataSourceDao dataSourceDao)
	{
		this.dataSourceDao = dataSourceDao;
	}
	
	/**
	 * 检查数据源的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForDataSource(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (dataSourceDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("数据源的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDataSourceManageService#saveDataSource(com.orifound.aiim.entity.DataSource, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveDataSource(DataSource dataSource, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDataSourceManageService#deleteDataSource(com.orifound.aiim.entity.DataSource, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteDataSource(DataSource dataSource, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDataSourceManageService#updateDataSource(com.orifound.aiim.entity.DataSource, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateDataSource(DataSource dataSource, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDataSourceManageService#findDataSources(java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findDataSources(Map<Integer, DataSource> dataSources, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForDataSource(pErrInfo) == false)
			{
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag)
			{
				if (dataSourceDao.findAll(dataSources, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取数据源字典信息失败: ");
				}
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
			throwable=null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDataSourceManageService#findDataSourceByID(int, com.orifound.aiim.entity.DataSource, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findDataSourceByID(int pID, DataSource dataSource, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
