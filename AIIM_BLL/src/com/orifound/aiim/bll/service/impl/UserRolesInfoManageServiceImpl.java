/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IUserRolesInfoManageService;
import com.orifound.aiim.dal.dao.IUserRolesInfoDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesInfo;

/**
 * 用户所属角色信息管理服务实现类
 *
 */
public class UserRolesInfoManageServiceImpl implements IUserRolesInfoManageService
{
	
	/**
	 * 构造函数
	 */
	public UserRolesInfoManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public UserRolesInfoManageServiceImpl(IUserRolesInfoDao userRolesInfoDao)
	{
		this.userRolesInfoDao = userRolesInfoDao;
	}
	
	/**
	 * 用户所属角色信息表的数据访问对象
	 */
	private IUserRolesInfoDao userRolesInfoDao = null;

	/**
	 * 获取属性值：用户所属角色信息表的数据访问对象
	 * @return 用户所属角色信息表的数据访问对象
	 */
	public IUserRolesInfoDao getUserRolesInfoDao()
	{
		return userRolesInfoDao;
	}

	/**
	 * 设置属性值：用户所属角色信息表的数据访问对象
	 * @param userRolesInfoDao 用户所属角色信息表的数据访问对象
	 */
	public void setUserRolesInfoDao(IUserRolesInfoDao userRolesInfoDao)
	{
		this.userRolesInfoDao = userRolesInfoDao;
	}
	
	/**
	 * 检查用户所属角色信息的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForUserRolesInfo(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (userRolesInfoDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("用户所属角色信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.IUserRolesInfoManageService#saveUserRolesInfo(com.orifound.aiim.entity.UserRolesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveUserRolesInfo(UserRolesInfo userRolesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRolesInfo(pErrInfo) == false)
			{
				pFlag = false;
				pErrInfo.getContent().insert(0, "角色信息BLL业务逻辑层的DAO依赖注入");
			}
			

			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (userRolesInfoDao.save(userRolesInfo, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"查找所有角色信息失败: ");
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
	 * @see com.orifound.aiim.bll.service.IUserRolesInfoManageService#deleteUserRolesInfo(com.orifound.aiim.entity.UserRolesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteUserRolesInfo(UserRolesInfo userRolesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
 
		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRolesInfo(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查用户编号是否合法
			if (pFlag)
			{
				if (userRolesInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("用户角色信息非法为空！");
				}
			}
			
			
			//检查用户编号是否合法
			if (pFlag)
			{
				if (userRolesInfo.getID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("用户角色编号非法为0");
				}
			}

			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (userRolesInfoDao.delete(userRolesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"删除指定用户角色信息（"+userRolesInfo.getID()+"）失败: ");
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
	 * @see com.orifound.aiim.bll.service.IUserRolesInfoManageService#findUserRolesInfosByUserID(int, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserRolesInfosByUserID(int pUserID, List<UserRolesInfo> userRolesInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
 
		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRolesInfo(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查用户编号是否合法
			if (pFlag)
			{
				if (pUserID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为0");
				}
			}

			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (userRolesInfoDao.findByUserID(pUserID, userRolesInfos, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"查找指定用户（"+pUserID+"）所属角色信息失败: ");
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
	public boolean findUserRolesInfos(List<UserRolesInfo> userRolesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRolesInfo(pErrInfo) == false)
			{
				pFlag = false;
				pErrInfo.getContent().insert(0,"用户角色信息,进行DAO依赖注入失败: ");
			}
			

			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (userRolesInfoDao.findAll(userRolesInfos, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"查找所有角色信息失败: ");
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
	public boolean findUserRoleByID(int pID,UserRolesInfo userRolesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRolesInfo(pErrInfo) == false)
			{
				pFlag = false;
				pErrInfo.getContent().insert(0, "角色信息BLL业务逻辑层的DAO依赖注入");
			}
			
			if (pFlag) {
				if (pID == 0) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DAO依赖注入");
				}
			}

			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (userRolesInfoDao.findByID(pID, userRolesInfo, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"查找所有角色信息失败: ");
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
	public boolean findUserRolesInfosByRoleID(int pRoleID, List<UserRolesInfo> userRolesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
 
		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRolesInfo(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查用户编号是否合法
			if (pFlag)
			{
				if (pRoleID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为0");
				}
			}

			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (userRolesInfoDao.findByRoleID(pRoleID, userRolesInfos, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"查找指定角色（"+pRoleID+"）所属用户信息失败: ");
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
