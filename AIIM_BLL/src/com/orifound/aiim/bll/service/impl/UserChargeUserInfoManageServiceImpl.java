/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IUserChargeUserInfoManageService;
import com.orifound.aiim.dal.dao.IUserChargeUserInfoDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserChargeUserInfo;

/**
 * 用户代工信息服务实现类
 *
 */
public class UserChargeUserInfoManageServiceImpl implements IUserChargeUserInfoManageService
{
	/**
	 * 构造函数
	 */
	public UserChargeUserInfoManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public UserChargeUserInfoManageServiceImpl(IUserChargeUserInfoDao userChargeUserInfoDao)
	{
		this.userChargeUserInfoDao = userChargeUserInfoDao;
	}
	
	/**
	 * 用户代工信息表的数据访问对象
	 */
	private IUserChargeUserInfoDao userChargeUserInfoDao = null;

	/**
	 * 获取属性值：用户代工信息表的数据访问对象
	 * @return 用户代工信息表的数据访问对象
	 */
	public IUserChargeUserInfoDao getUserChargeUserInfoDao()
	{
		return userChargeUserInfoDao;
	}

	/**
	 * 设置属性值：用户代工信息表的数据访问对象
	 * @param userChargeUserInfoDao 用户代工信息表的数据访问对象
	 */
	public void setUserChargeUserInfoDao(IUserChargeUserInfoDao userChargeUserInfoDao)
	{
		this.userChargeUserInfoDao = userChargeUserInfoDao;
	}
	
	/**
	 * 检查用户代工信息的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForUserChargeUserInfo(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (userChargeUserInfoDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("用户代工信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.IUserChargeUserInfoManageService#saveUserChargeUserInfo(com.orifound.aiim.entity.UserChargeUserInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveUserChargeUserInfo(UserChargeUserInfo userChargeUserInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserChargeUserInfo(pErrInfo) == false)
			{
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag)
			{
				if (userChargeUserInfoDao.save(userChargeUserInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取指定用户（编号："+userChargeUserInfo.getUserID()+"）代工信息失败: ");
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
	 * @see com.orifound.aiim.bll.service.IUserChargeUserInfoManageService#deleteUserChargeUserInfo(com.orifound.aiim.entity.UserChargeUserInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteUserChargeUserInfo(UserChargeUserInfo userChargeUserInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserChargeUserInfo(pErrInfo) == false)
			{
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag)
			{
				if (userChargeUserInfoDao.delete(userChargeUserInfo, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"删除用户代工信息失败: ");
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
	 * @see com.orifound.aiim.bll.service.IUserChargeUserInfoManageService#updateUserChargeUserInfo(com.orifound.aiim.entity.UserChargeUserInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateUserChargeUserInfo(UserChargeUserInfo userChargeUserInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IUserChargeUserInfoManageService#findUserChargeUserInfoByID(int, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserChargeUserInfosByUserID(int pUserID, List<UserChargeUserInfo> userChargeUserInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserChargeUserInfo(pErrInfo) == false)
			{
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag)
			{
				if (userChargeUserInfoDao.findByUserID(pUserID, userChargeUserInfos, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取指定用户（编号："+pUserID+"）代工信息失败: ");
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

}
