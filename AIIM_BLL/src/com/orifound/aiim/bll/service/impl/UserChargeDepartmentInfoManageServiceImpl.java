/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IUserChargeDepartmentInfoManageService;
import com.orifound.aiim.dal.dao.IUserChargeDepartmentInfoDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserChargeDepartmentInfo;

/**
 * 业务专员负责部门信息管理服务实现类
 *
 */
public class UserChargeDepartmentInfoManageServiceImpl implements IUserChargeDepartmentInfoManageService
{
	
	/**
	 * 构造函数
	 */
	public UserChargeDepartmentInfoManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public UserChargeDepartmentInfoManageServiceImpl(IUserChargeDepartmentInfoDao userChargeDepartmentInfoDao)
	{
		this.userChargeDepartmentInfoDao = userChargeDepartmentInfoDao;
	}
	
	
	/**
	 * 业务专员负责部门信息表的数据访问对象
	 */
	private IUserChargeDepartmentInfoDao userChargeDepartmentInfoDao = null;

	/**
	 * 获取属性值：业务专员负责部门信息表的数据访问对象
	 * @return 业务专员负责部门信息表的数据访问对象
	 */
	public IUserChargeDepartmentInfoDao getUserChargeDepartmentInfoDao()
	{
		return userChargeDepartmentInfoDao;
	}

	/**
	 * 设置属性值：业务专员负责部门信息表的数据访问对象
	 * @param userChargeDepartmentInfoDao 业务专员负责部门信息表的数据访问对象
	 */
	public void setUserChargeDepartmentInfoDao(IUserChargeDepartmentInfoDao userChargeDepartmentInfoDao)
	{
		this.userChargeDepartmentInfoDao = userChargeDepartmentInfoDao;
	}
	
	/**
	 * 检查业务专员负责部门信息的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForUserChargeDepartmentInfo(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (userChargeDepartmentInfoDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("业务专员负责部门信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.IUserChargeDepartmentInfoManageService#saveUserChargeDepartmentInfo(com.orifound.aiim.entity.UserChargeDepartmentInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveUserChargeDepartmentInfo(UserChargeDepartmentInfo userChargeDepartmentInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserChargeDepartmentInfo(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			if (pFlag) {
				if (userChargeDepartmentInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户代理部门信息非法为空！");
				}
			}
			
			if (pFlag) {
				if (userChargeDepartmentInfo.getUserID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为空！");
				}
			}
			
			if (pFlag) {
				if (userChargeDepartmentInfo.getDepartmentID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("部门编号非法为空！");
				}
			}
			
			//调用DAO接口
			if (pFlag)
			{
				if (userChargeDepartmentInfoDao.save(userChargeDepartmentInfo, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"添加指定用户"+userChargeDepartmentInfo.getUserID()+"负责的部门信息失败: ");
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
	 * @see com.orifound.aiim.bll.service.IUserChargeDepartmentInfoManageService#deleteUserChargeDepartmentInfo(com.orifound.aiim.entity.UserChargeDepartmentInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteUserChargeDepartmentInfo(UserChargeDepartmentInfo userChargeDepartmentInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserChargeDepartmentInfo(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			
			if (pFlag) {
				if (userChargeDepartmentInfo.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("部门编号非法为空！");
				}
			}
			//调用DAO接口
			if (pFlag)
			{
				if (userChargeDepartmentInfoDao.delete(userChargeDepartmentInfo, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"删除指定用户"+userChargeDepartmentInfo.getUserID()+"负责的部门信息失败: ");
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
	 * @see com.orifound.aiim.bll.service.IUserChargeDepartmentInfoManageService#updateUserChargeDepartmentInfo(com.orifound.aiim.entity.UserChargeDepartmentInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateUserChargeDepartmentInfo(UserChargeDepartmentInfo userChargeDepartmentInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IUserChargeDepartmentInfoManageService#findUserChargeDepartmentInfosByUserID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserChargeDepartmentInfosByUserID(int pUserID, LinkedHashMap<Integer, UserChargeDepartmentInfo> userChargeDepartmentInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserChargeDepartmentInfo(pErrInfo) == false)
			{
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag)
			{
				if (userChargeDepartmentInfoDao.findByUserID(pUserID, userChargeDepartmentInfos, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取指定用户（编号："+pUserID+"）所负责的部门信息失败: ");
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

	@Override
	public boolean findAllUserUnChargeDepartmentInfos(List<UserChargeDepartmentInfo> userChargeDepartmentInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserChargeDepartmentInfo(pErrInfo) == false)
			{
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag)
			{
				if (userChargeDepartmentInfoDao.findAllUnchargeDepartment(userChargeDepartmentInfos, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取所有负责的部门信息失败: ");
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
