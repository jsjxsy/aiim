/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IRetentionPeriodManageService;
import com.orifound.aiim.dal.dao.IRetentionPeriodDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.RetentionPeriod;

/**
 * 保管期限管理服务实现类
 *
 */
public class RetentionPeriodManageServiceImpl implements IRetentionPeriodManageService
{
	/**
	 * 构造函数
	 */
	public RetentionPeriodManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public RetentionPeriodManageServiceImpl(IRetentionPeriodDao retentionPeriodDao)
	{
		this.retentionPeriodDao = retentionPeriodDao;
	}
	
	/**
	 * 保管期限字典表的数据访问对象
	 */
	private IRetentionPeriodDao retentionPeriodDao = null;

	/**
	 * 获取属性值：保管期限字典表的数据访问对象
	 * @return 保管期限字典表的数据访问对象
	 */
	public IRetentionPeriodDao getRetentionPeriodDao()
	{
		return retentionPeriodDao;
	}

	/**
	 * 设置属性值：保管期限字典表的数据访问对象
	 * @param retentionPeriodDao 保管期限字典表的数据访问对象
	 */
	public void setRetentionPeriodDao(IRetentionPeriodDao retentionPeriodDao)
	{
		this.retentionPeriodDao = retentionPeriodDao;
	}
	
	/**
	 * 检查保管期限的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForRetentionPeriod(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (retentionPeriodDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("保管期限的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.IRetentionPeriodManageService#saveRetentionPeriod(com.orifound.aiim.entity.RetentionPeriod, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveRetentionPeriod(RetentionPeriod retentionPeriod, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IRetentionPeriodManageService#deleteRetentionPeriod(com.orifound.aiim.entity.RetentionPeriod, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteRetentionPeriod(RetentionPeriod retentionPeriod, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IRetentionPeriodManageService#updateRetentionPeriod(com.orifound.aiim.entity.RetentionPeriod, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateRetentionPeriod(RetentionPeriod retentionPeriod, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IRetentionPeriodManageService#findRetentionPeriods(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRetentionPeriods(LinkedHashMap<Integer, RetentionPeriod> retentionPeriods, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForRetentionPeriod(pErrInfo) == false)
			{
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag)
			{
				if (retentionPeriodDao.findAll(retentionPeriods, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取所有保管期限信息失败: ");
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
	 * @see com.orifound.aiim.bll.service.IRetentionPeriodManageService#findRetentionPeriodByID(int, com.orifound.aiim.entity.RetentionPeriod, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRetentionPeriodByID(int pID, RetentionPeriod retentionPeriod, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}
}