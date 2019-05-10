/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IDataSourceItemManageService;
import com.orifound.aiim.dal.dao.IDataSourceItemDao;
import com.orifound.aiim.entity.DataSourceItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 数据源的数据项管理服务实现类
 *
 */
public class DataSourceItemManageServiceImpl implements IDataSourceItemManageService
{
	/**
	 * 构造函数
	 */
	public DataSourceItemManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public DataSourceItemManageServiceImpl(IDataSourceItemDao dataSourceItemDao)
	{
		this.dataSourceItemDao = dataSourceItemDao;
	}
	
	/**
	 * 数据源的数据项字典表的数据访问对象
	 */
	private IDataSourceItemDao dataSourceItemDao = null;

	/**
	 * 获取属性值：数据源的数据项字典表的数据访问对象
	 * @return 数据源的数据项字典表的数据访问对象
	 */
	public IDataSourceItemDao getDataSourceItemDao()
	{
		return dataSourceItemDao;
	}

	/**
	 * 设置属性值：数据源的数据项字典表的数据访问对象
	 * @param dataSourceItemDao 数据源的数据项字典表的数据访问对象
	 */
	public void setDataSourceItemDao(IDataSourceItemDao dataSourceItemDao)
	{
		this.dataSourceItemDao = dataSourceItemDao;
	}
	
	/**
	 * 检查数据源的数据项信息的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForDataSourceItem(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (dataSourceItemDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("数据源的数据项信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.IDataSourceItemManageService#saveDataSourceItem(com.orifound.aiim.entity.DataSourceItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveDataSourceItem(DataSourceItem dataSourceItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDataSourceItemManageService#deleteDataSourceItem(com.orifound.aiim.entity.DataSourceItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteDataSourceItem(DataSourceItem dataSourceItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDataSourceItemManageService#updateDataSourceItem(com.orifound.aiim.entity.DataSourceItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateDataSourceItem(DataSourceItem dataSourceItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDataSourceItemManageService#findDataSourceItemsByDataSourceID(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findDataSourceItemsByDataSourceID(Integer pDataSourceID, LinkedHashMap<Integer, DataSourceItem> dataSourceItems, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForDataSourceItem(pErrInfo) == false)
			{
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag)
			{
				if (dataSourceItemDao.findByDataSourceID(pDataSourceID, dataSourceItems, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"查找指定数据源（编号："+pDataSourceID+"）的数据项信息失败: ");
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
			throwable = null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDataSourceItemManageService#findDataSourceItemByID(int, com.orifound.aiim.entity.DataSourceItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findDataSourceItemByID(int pID, DataSourceItem dataSourceItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
