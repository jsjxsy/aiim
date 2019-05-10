/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.orifound.aiim.bll.service.ISystemUserRightInitializeService;
import com.orifound.aiim.bll.service.IUserChargeDepartmentInfoManageService;
import com.orifound.aiim.bll.service.IUserChargeUserInfoManageService;
import com.orifound.aiim.bll.service.IUserInfoManageService;
import com.orifound.aiim.bll.service.IUserRolesInfoManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.dal.dao.IUserInfoDao;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.DepartmentInfo;
import com.orifound.aiim.entity.EnumSystemRole;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.UserChargeDepartmentInfo;
import com.orifound.aiim.entity.UserChargeUserInfo;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRolesInfo;
import com.orifound.commons.acservice.IAccessControlService;
import com.orifound.commons.acservice.impl.AccessControlServiceImpl;

/**
 * 用户信息管理服务实现类
 * 
 */
public class UserInfoManageServiceImpl implements IUserInfoManageService
{

	/**
	 * 构造函数
	 */
	public UserInfoManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public UserInfoManageServiceImpl(IUserInfoDao userInfoDao)
	{
		this.userInfoDao = userInfoDao;
	}

	/**
	 * 用户信息表的数据访问对象
	 */
	private IUserInfoDao userInfoDao = null;

	/**
	 * 获取属性值：用户信息表的数据访问对象
	 * 
	 * @return 用户信息表的数据访问对象
	 */
	public IUserInfoDao getUserInfoDao()
	{
		return userInfoDao;
	}

	/**
	 * 设置属性值：用户信息表的数据访问对象
	 * 
	 * @param userInfoDao
	 *            用户信息表的数据访问对象
	 */
	public void setUserInfoDao(IUserInfoDao userInfoDao)
	{
		this.userInfoDao = userInfoDao;
	}

	/**
	 * 用户代工信息管理服务对象
	 */
	private IUserChargeUserInfoManageService userChargeUserInfoManageService = null;

	/**
	 * 设置属性值：用户代工信息管理服务对象
	 * 
	 * @param userChargeUserInfoManageService
	 *            用户代工信息管理服务对象
	 */
	public void setUserChargeUserInfoManageService(IUserChargeUserInfoManageService userChargeUserInfoManageService)
	{
		this.userChargeUserInfoManageService = userChargeUserInfoManageService;
	}

	/**
	 * 获取属性值：用户代工信息管理服务对象
	 * 
	 * @return 用户代工信息管理服务对象
	 */
	public IUserChargeUserInfoManageService getUserChargeUserInfoManageService()
	{
		return userChargeUserInfoManageService;
	}

	/**
	 * 业务专员负责部门信息管理服务对象
	 */
	private IUserChargeDepartmentInfoManageService userChargeDepartmentInfoManageService = null;

	/**
	 * 设置属性值：业务专员负责部门信息管理服务对象
	 * 
	 * @param userChargeDepartmentInfoManageService
	 *            业务专员负责部门信息管理服务对象
	 */
	public void setUserChargeDepartmentInfoManageService(IUserChargeDepartmentInfoManageService userChargeDepartmentInfoManageService)
	{
		this.userChargeDepartmentInfoManageService = userChargeDepartmentInfoManageService;
	}

	/**
	 * 获取属性值：业务专员负责部门信息管理服务对象
	 * 
	 * @return 业务专员负责部门信息管理服务对象
	 */
	public IUserChargeDepartmentInfoManageService getUserChargeDepartmentInfoManageService()
	{
		return userChargeDepartmentInfoManageService;
	}

	/**
	 * 用户角色信息管理服务对象
	 */
	private IUserRolesInfoManageService userRolesInfoManageService = null;

	/**
	 * 设置属性值：用户角色信息管理服务对象
	 * 
	 * @param userRoleManageService
	 *            用户角色信息管理服务对象
	 */
	public void setUserRolesInfoManageService(IUserRolesInfoManageService userRolesInfoManageService)
	{
		this.userRolesInfoManageService = userRolesInfoManageService;
	}

	/**
	 * 获取属性值：用户角色信息管理服务对象
	 * 
	 * @return 用户角色信息管理服务对象
	 */
	public IUserRolesInfoManageService getUserRolesInfoManageService()
	{
		return userRolesInfoManageService;
	}

	/**
	 * 系统用户权限初始化服务对象
	 */
	private ISystemUserRightInitializeService systemUserRightInitializeService = null;

	/**
	 * 设置属性值：系统用户权限初始化服务对象
	 * 
	 * @param entityNameManageService
	 *            系统用户权限初始化服务对象
	 */
	public void setSystemUserRightInitializeService(ISystemUserRightInitializeService systemUserRightInitializeService)
	{
		this.systemUserRightInitializeService = systemUserRightInitializeService;
	}

	/**
	 * 获取属性值：系统用户权限初始化服务对象
	 * 
	 * @return 系统用户权限初始化服务对象
	 */
	public ISystemUserRightInitializeService getSystemUserRightInitializeService()
	{
		return systemUserRightInitializeService;
	}

	/**
	 * 检查用户信息的DAO依赖注入（DAO Depandency Injection）
	 * 
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForUserInfo(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// 检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (userInfoDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("用户信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
		}
		catch (Exception e)
		{
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
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

	/**
	 * 检查相关的业务逻辑对象依赖注入（BLL Depandency Injection）
	 * 
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkBllInjection(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// 检查是否有进行用户代工信息的BLL业务逻辑对象的依赖注入
			pErrPos = 1;
			if (userChargeUserInfoManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("用户代工信息的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}

			// 检查业务专员负责部门的BLL业务逻辑对象是否有依赖注入
			if (userChargeDepartmentInfoManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("业务专员负责部门的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}

			// 检查是否有进行用户角色信息的BLL业务逻辑对象的依赖注入
			if (userRolesInfoManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("用户角色信息管理服务的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}

			// 检查是否有进行系统用户权限初始化服务的BLL业务逻辑对象的依赖注入
			if (systemUserRightInitializeService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("系统用户权限初始化服务的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}
		}
		catch (Exception e)
		{
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#addUserToDeparment
	 * (com.orifound.aiim.entity.UserInfo,
	 * com.orifound.aiim.entity.DepartmentInfo,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean addUserToDeparment(UserInfo userInfo, DepartmentInfo departmentInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#addUsersToDeparment
	 * (java.util.List, com.orifound.aiim.entity.DepartmentInfo,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean addUsersToDeparment(List<UserInfo> userInfos, DepartmentInfo departmentInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orifound.aiim.bll.service.IUserInfoManageService#
	 * deleteUserChargeDepartment(com.orifound.aiim.entity.UserInfo,
	 * com.orifound.aiim.entity.DepartmentInfo,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteUserChargeDepartment(UserInfo userInfo, DepartmentInfo departmentInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orifound.aiim.bll.service.IUserInfoManageService#
	 * deleteUserChargeDepartments(com.orifound.aiim.entity.UserInfo,
	 * java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteUserChargeDepartments(UserInfo userInfo, List<DepartmentInfo> departmentInfos, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#deleteUserInfo(com
	 * .orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteUserInfo(UserInfo userInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "userInfo的BLL业务逻辑层Dao注入失败:");
			}
			if (userInfo == null) {
				pFlag = false;
				pErrInfo.getContent().append("用户信息非法为空!");
			}else {
				if (userInfo.getUserID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为空！");
				}
			}
			
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.delete(userInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "删除用户失败！");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#findUsers(java.util
	 * .List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUsers(List<UserInfo> userInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "userInfo的BLL业务逻辑层Dao注入失败:");
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.findAll(userInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "userInfo的BLL业务逻辑层查询所有用户失败:");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#findAnonymousUser
	 * (com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAnonymousUser(UserInfo userInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			// 检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserInfo(pErrInfo) == false)
			{
				pFlag = false;
			}

			// 调用DAO接口
			if (pFlag)
			{
				if (userInfoDao.findAnonymous(userInfo, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找匿名用户信息失败: ");
				}
			}

		}
		catch (Exception e)
		{
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// 销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#findUserByIDCardNo
	 * (java.lang.String, com.orifound.aiim.entity.UserInfo,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserByIDCardNo(String pIDCardNo, UserInfo userInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#findUserByRealName
	 * (java.lang.String, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserByRealName(String realName, List<UserInfo> userInfos, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#findUserByUserID
	 * (java.lang.Integer, com.orifound.aiim.entity.UserInfo,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserByUserID(Integer pUserID, UserInfo userInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "userInfo业务逻辑层的DAO依赖注入失败!");
			}
			if (userInfo == null) {
				pFlag=false;
				pErrInfo.getContent().append("userInfo对象非法为空!");
			}
			if (userInfo.getUserID() == 0) {
				pFlag=false;
				pErrInfo.getContent().append("用户编号非法为空!");
			}
		
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.findUserInfoByUserID(pUserID, userInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新用户信息失败");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#findUsersByDepartmentID
	 * (int, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUsersByDepartmentID(int departmentID, List<UserInfo> userInfos, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#login(com.orifound
	 * .aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean login(UserInfo userInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		//访问控制服务对象
		IAccessControlService accessControlService = new AccessControlServiceImpl();
		
		try
		{
			pErrPos = 1;
			// 检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserInfo(pErrInfo) == false)
			{
				pFlag = false;
			}

			// 检查相关业务逻辑对象是否有依赖注入
			if (pFlag)
			{
				if (checkBllInjection(pErrInfo) == false)
				{
					pFlag = false;
				}
			}

			// 调用DAO接口验证用户是否存在
			if (pFlag)
			{
				pErrPos = 2;
				UserInfo pUserInfo = new UserInfo();
				if (userInfoDao.findByUserName(userInfo.getUserName().trim(), pUserInfo, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找用户（" + userInfo.getUserName().trim() + "）失败: ");
				}

				// 检查是否找到指定用户名的用户信息
				if (pFlag)
				{
					pErrPos = 3;
					if (pUserInfo.getUserID() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("用户不存在。");
					}
				}

				// 找到用户后检查密码是否匹配
				if (pFlag)
				{
					if (pUserInfo.getUserPWD().equals(userInfo.getUserPWD()) == false)
					{
						pFlag = false;
						pErrInfo.getContent().append("用户密码错误。");
					}
				}

				// 保存登录成功的完整用户基本信息
				if (pFlag)
				{
					userInfo.cloneFrom(pUserInfo);
				}

				// 销毁局部变量
				pUserInfo = null;
			}

			// 用户登录成功后获取其代工信息
			if (pFlag)
			{
				pErrPos = 4;
				List<UserChargeUserInfo> userChargeUserInfos = new ArrayList<UserChargeUserInfo>();
				if (userChargeUserInfoManageService.findUserChargeUserInfosByUserID(userInfo.getUserID(), userChargeUserInfos, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取用户代工信息失败: ");
				}
				else
				{
					// 保存获取的用户代工信息
					if (userChargeUserInfos.size() > 0)
					{
						userInfo.setChargeUserInfos(userChargeUserInfos);
					}
				}

				// 销毁局部变量
				userChargeUserInfos = null;
			}

			// 继续获取业务专员负责的部门信息
			if (pFlag)
			{
				pErrPos = 5;
				LinkedHashMap<Integer, UserChargeDepartmentInfo> userChargeDepartmentInfos = new LinkedHashMap<Integer, UserChargeDepartmentInfo>();
				if (userChargeDepartmentInfoManageService.findUserChargeDepartmentInfosByUserID(userInfo.getUserID(), userChargeDepartmentInfos, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取业务专员负责的部门信息失败: ");
				}
				else
				{
					// 保存获取的业务专员负责的部门信息
					if (userChargeDepartmentInfos.size() > 0)
					{
						userInfo.setChargeDepartmentInfos(userChargeDepartmentInfos);
					}
				}

				// 销毁局部变量
				userChargeDepartmentInfos = null;
			}

			// 获取登录用户的所有授权信息（包括其所属角色的授权信息）
			if (pFlag)
			{
				pErrPos = 6;
				if (getSystemUserRights(userInfo, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取登录用户的授权信息失败: ");
				}
			}
			
			//初始化访问控制服务参数
			if (pFlag) {	
				pErrPos = 7;
				Map<Integer, ArchivesType> endArchivesTypes = new HashMap<Integer, ArchivesType>();
				CommonUtil.getEndArchivesTypesByTree(userInfo.getArchivesTypes(), endArchivesTypes, pErrInfo);
				accessControlService.loadArchivesSecrecyACL(new ArrayList<Object>(userInfo.getArchivesSecrecys().keySet()));
				accessControlService.loadSystemFeatureACL(new ArrayList<Object>(userInfo.getUCL().keySet()));	
				accessControlService.loadArchivesTypeACL(new ArrayList<Object>(endArchivesTypes.keySet()));	
				//待添加原文电子文件的访问控制设置
				userInfo.setAccessControlService(accessControlService);
				
			}

		}
		catch (Exception e)
		{
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// 销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	/**
	 * 获取系统用户的授权信息（包括系统功能菜单授权、系统功能授权、档案资源授权以及档案密级授权信息）<br>
	 * 返回结果是包括针对用户直接授权和针对该用户所属角色直接授权在内的授权信息合集
	 * 
	 * @param userInfo
	 *            指定的用户信息
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean getSystemUserRights(UserInfo userInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		int[] pRolesID = null; // 用户所属角色的编号数组

		try
		{
			// 检查用户信息是否为空
			pErrPos = 1;
			if (userInfo == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("用户信息非法为空。");
			}
			else
			{
				if (userInfo.getUserID() <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为0");
				}
			}

			// 继续获取用户所属的角色
			if (pFlag)
			{
				pErrPos = 3;
				List<UserRolesInfo> userRolesInfos = new ArrayList<UserRolesInfo>();
				if (userRolesInfoManageService.findUserRolesInfosByUserID(userInfo.getUserID(), userRolesInfos, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取用户所属角色信息失败: ");
				}
				else
				{
					if (userRolesInfos.size() > 0)
					{						
						pRolesID = new int[userRolesInfos.size()];
						for (int i = 0; i < userRolesInfos.size(); i++)
						{
							pRolesID[i] = userRolesInfos.get(i).getRolesID();
							
						}
						userInfo.setRolesIDs(pRolesID);
					}
				}

				// 销毁局部变量
				userRolesInfos = null;
			}

			// 获取授权的系统功能菜单
			if (pFlag)
			{
				LinkedHashMap<String, SystemFeature> systemFeatureMenus = new LinkedHashMap<String, SystemFeature>();
				if (systemUserRightInitializeService.findRightSystemFeatureMenusByUserID(userInfo.getUserID(), pRolesID, systemFeatureMenus, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取用户系统功能菜单授权信息失败: ");
				}
				else
				{
					// 保存用户的系统功能菜单授权信息
					userInfo.setSystemMenus(systemFeatureMenus);
				}

				// 销毁局部变量
				systemFeatureMenus = null;
			}

			// 获取系统功能授权信息：UCL
			if (pFlag)
			{
				Map<String, SystemFeature> systemFeatures = new HashMap<String, SystemFeature>();
				if (systemUserRightInitializeService.findRightSystemFeaturesByUserID(userInfo.getUserID(), pRolesID, systemFeatures, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取用户系统功能授权信息失败: ");
				}
				else
				{
					// 保存用户访问控制列表UCL
					userInfo.setUCL(systemFeatures);
				}

				// 销毁局部变量
				systemFeatures = null;
			}

			// 获取档案资源授权信息
			if (pFlag)
			{
				LinkedHashMap<Integer, ArchivesType> archivesTypes = new LinkedHashMap<Integer, ArchivesType>();
				if (systemUserRightInitializeService.findRightArchivesTypesByUserID(userInfo.getUserID(), pRolesID, archivesTypes, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取档案资源授权信息失败: ");
				}
				else
				{
					// 保存档案资源授权信息
					userInfo.setArchivesTypes(archivesTypes);
				}

				// 销毁局部变量
				archivesTypes = null;
			}

			// 获取档案密级授权信息
			if (pFlag)
			{
				Map<Integer, ArchivesSecrecy> archivesSecrecys = new HashMap<Integer, ArchivesSecrecy>();
				if (systemUserRightInitializeService.findRightArchivesSecrecysByUserID(userInfo.getUserID(), pRolesID, archivesSecrecys, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取档案密级授权信息失败: ");
				}
				else
				{
					// 保存档案密级授权信息
					userInfo.setArchivesSecrecys(archivesSecrecys);
				}

				// 销毁局部变量
				archivesSecrecys = null;
			}
		}
		catch (Exception e)
		{
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// 销毁局部变量
			throwable = null;
			pRolesID = null;
		}

		return pFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#logout(com.orifound
	 * .aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean logout(UserInfo userInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#saveUserChargeDepartment
	 * (com.orifound.aiim.entity.UserInfo,
	 * com.orifound.aiim.entity.DepartmentInfo,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveUserChargeDepartment(UserInfo userInfo, DepartmentInfo departmentInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orifound.aiim.bll.service.IUserInfoManageService#
	 * saveUserChargeDepartments(com.orifound.aiim.entity.UserInfo,
	 * java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveUserChargeDepartments(UserInfo userInfo, List<DepartmentInfo> departmentInfos, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#saveUserInfo(com
	 * .orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveUserInfo(UserInfo userInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "userInfo业务逻辑层的DAO依赖注入失败!");
				
			}
			if (userInfo == null) {
				pFlag=false;
				pErrInfo.getContent().append("userInfo对象非法为空!");
			}
			
			if (userInfo.getUserName() == null || userInfo.equals("")) {
				pFlag=false;
				pErrInfo.getContent().append("用户名非法为空!");
			}
			if (userInfo.getRealName() == null || userInfo.equals("")) {
				pFlag=false;
				pErrInfo.getContent().append("用户真实姓名非法为空!");
			}
			if (userInfo.getUserPWD() == null || userInfo.getUserPWD().equals("")) {
				pFlag=false;
				pErrInfo.getContent().append("用户密码非法为空!");
			}
			
			if (pFlag) {
				pErrPos = 2;
			}
			//开始处理2...
			if (pFlag) {
				pErrPos = 3;
				if (userInfoDao.save(userInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"用户信息保存失败:");
				}

			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#updateUserInfo(com
	 * .orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateUserInfo(UserInfo userInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "userInfo业务逻辑层的DAO依赖注入失败!");
			}
			if (userInfo == null) {
				pFlag=false;
				pErrInfo.getContent().append("userInfo对象非法为空!");
			}
			if (userInfo.getUserID() == 0) {
				pFlag=false;
				pErrInfo.getContent().append("用户编号非法为空!");
			}
			if (userInfo.getUserName() == null || userInfo.equals("")) {
				pFlag=false;
				pErrInfo.getContent().append("用户名非法为空!");
			}
			if (userInfo.getRealName() == null || userInfo.equals("")) {
				pFlag=false;
				pErrInfo.getContent().append("用户真实姓名非法为空!");
			}
			if (userInfo.getUserPWD() == null || userInfo.getUserPWD().equals("")) {
				pFlag=false;
				pErrInfo.getContent().append("用户密码非法为空!");
			}
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.update(userInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新用户信息失败");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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
	public boolean modifyPassword(UserInfo userInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "userInfo业务逻辑层的DAO依赖注入失败!");
			}
			if (userInfo == null) {
				pFlag=false;
				pErrInfo.getContent().append("userInfo对象非法为空!");
			}
			if (userInfo.getUserID() <= 0) {
				pFlag=false;
				pErrInfo.getContent().append("用户编号非法为空!");
			}
			if (userInfo.getUserPWD() == null || userInfo.getUserPWD().equals("")) {
				pFlag=false;
				pErrInfo.getContent().append("用户密码非法为空!");
			}
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.updatePassword(userInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新用户信息失败");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#loginWithAnonymous
	 * (com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean loginWithAnonymous(UserInfo userInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			// 检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserInfo(pErrInfo) == false)
			{
				pFlag = false;
			}

			// 检查相关业务逻辑对象是否有依赖注入
			if (pFlag)
			{
				if (checkBllInjection(pErrInfo) == false)
				{
					pFlag = false;
				}
			}

			// 获取匿名用户
			if (pFlag)
			{
				if (findAnonymousUser(userInfo, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取匿名用户信息失败: ");
				}
			}

			// 获取匿名用户的所有授权信息（包括其所属角色的授权信息）
			if (pFlag)
			{
				if (getSystemUserRights(userInfo, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取匿名用户的授权信息失败: ");
				}
			}

		}
		catch (Exception e)
		{
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// 销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean findTaskPersons(List<UserInfo> userInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查 用户集合是否为空
			if (pFlag) {
				if (userInfos == null) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->用户集合非法为空。");
				}
			}

			//查询任务接收人集合
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.findTaskPersons(userInfos, pErrInfo) == false ) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询任务接收人集合 失败。");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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
	public boolean findUserInfosByCondition(Map<String,Object> userInfoQueryCondition,DataPageInfo dataPageInfo, List<UserInfo> userInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "用户信息BLL业务逻辑层的Dao注入失败：");
			}

			//检查数据分页对象是否为空
			if (pFlag)
			{
				if (dataPageInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("数据分页信息非法为空。");
				}
			}
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.findUserInfosByCondition(userInfoQueryCondition,dataPageInfo,userInfos,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("查询所有用户信息失败!");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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
	public boolean findUserBySystemRole(EnumSystemRole esnumSystemRole, List<UserInfo> userInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.findBusinessGuids(userInfos,esnumSystemRole, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "按角色查找用户信息失败：");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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
	public boolean findUserChargeUserInfosByUserID(int pID, List<UserInfo> userInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			
			if (pFlag) {
				if (pID == 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为空!");
				}
			}
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.findUserChargeUserInfoByUserID(pID, userInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "按用户编号查找被代工用户信息失败：");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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
	public boolean findAllUserUnchargeUserInfosByUserID(Map<String, Object> userInfoQueryCondition,DataPageInfo dataPageInfo,List<UserInfo> userInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.findAllUserUnchargeUserInfo(userInfoQueryCondition,dataPageInfo,userInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "按用户编号查找被代工用户信息失败：");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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
	public boolean findUserProxyInfosByCondition(Map<String, Object> userInfoQueryCondition, DataPageInfo dataPageInfo, List<UserInfo> userInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "用户信息BLL业务逻辑层的Dao注入失败：");
			}

			//检查数据分页对象是否为空
			if (pFlag)
			{
				if (dataPageInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("数据分页信息非法为空。");
				}
			}
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.findUserInfosByCondition(userInfoQueryCondition,dataPageInfo,userInfos,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("查询所有用户信息失败!");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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
	public  boolean checkUserNameExists(UserInfo userInfo,IntegerEx exists, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "用户信息BLL业务逻辑层的Dao注入失败：");
			}

			//检查数据分页对象是否为空
			if (pFlag)
			{
				if (userInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("用户信息非法为空。");
				}
			}
			if (pFlag) {
				if (userInfo.getUserName() == null || userInfo.getUserName().equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("用户名非法为空！");
				}
			}
			
			
			//开始处理2...
			if (pFlag) {
				if(userInfoDao.checkUserNameExists(userInfo, exists, pErrInfo)== false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "检查用户名是否存在失败：");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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
	public boolean checkIDCardNoExists(UserInfo userInfo,IntegerEx exists, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "用户信息BLL业务逻辑层的Dao注入失败：");
			}

			//检查数据分页对象是否为空
			if (pFlag)
			{
				if (userInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("用户信息非法为空。");
				}
			}
			
			if (pFlag) {
				if (userInfo.getIDCardNo() == null || userInfo.getIDCardNo().equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("用户将证件号非法为空！");
				}
			}
			
			//开始处理2...
			if (pFlag) {
				if(userInfoDao.checkIDCardNoExists(userInfo, exists, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "检查用户证件号是否存在失败：");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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
	public boolean findUseresNotInRoleID(int pRoleID, Map<String, Object> userInfoQueryCondition, DataPageInfo dataPageInfo, List<UserInfo> userInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "用户信息BLL业务逻辑层的Dao注入失败：");
			}

			//检查数据分页对象是否为空
			if (pFlag)
			{
				if (dataPageInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("数据分页信息非法为空。");
				}
			}
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.findUseresNotInRoleID(pRoleID,userInfoQueryCondition,dataPageInfo,userInfos,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("查询所有用户信息失败!");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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
