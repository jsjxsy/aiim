/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IUserRightSystemFeatureManageService;
import com.orifound.aiim.dal.dao.ISystemFeatureDao;
import com.orifound.aiim.dal.dao.IUserRightSystemFeatureDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRightSystemFeature;

/**
 * 用户系统功能授权管理服务实现类
 *
 */
public class UserRightSystemFeatureManageServiceImpl implements IUserRightSystemFeatureManageService
{
	
	/**
	 * 构造函数
	 */
	public UserRightSystemFeatureManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public UserRightSystemFeatureManageServiceImpl(IUserRightSystemFeatureDao userRightSystemFeatureDao)
	{
		this.userRightSystemFeatureDao = userRightSystemFeatureDao;
	}
	
	/**
	 * 用户系统功能授权信息表的数据访问对象
	 */
	private IUserRightSystemFeatureDao userRightSystemFeatureDao = null;

	/**
	 * 获取属性值：用户系统功能授权信息表的数据访问对象
	 * @return 用户系统功能授权信息表的数据访问对象
	 */
	public IUserRightSystemFeatureDao getUserRightSystemFeatureDao()
	{
		return userRightSystemFeatureDao;
	}

	/**
	 * 设置属性值：用户系统功能授权信息表的数据访问对象
	 * @param userRightSystemFeatureDao 用户系统功能授权信息表的数据访问对象
	 */
	public void setUserRightSystemFeatureDao(IUserRightSystemFeatureDao userRightSystemFeatureDao)
	{
		this.userRightSystemFeatureDao = userRightSystemFeatureDao;
	}
	
	private ISystemFeatureDao systemFeatureDao = null;
	
	public ISystemFeatureDao getSystemFeatureDao() {
		return systemFeatureDao;
	}

	public void setSystemFeatureDao(ISystemFeatureDao systemFeatureDao) {
		this.systemFeatureDao = systemFeatureDao;
	}

	/**
	 * 检查用户系统功能授权信息的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForUserRightSystemFeature(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (userRightSystemFeatureDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("用户系统功能授权信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.IUserRightSystemFeatureManageService#saveRightSystemFeatureForUser(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.UserRightSystemFeature, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveRightSystemFeatureForUser(UserInfo userInfo, UserRightSystemFeature userRightSystemFeature, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			
			if (pFlag) {
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("添加用户系统功能权限，用户信息非法为空! ");
				}
			}
			
			if (pFlag) {
				if (userInfo.getUserID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("添加用户系统功能权限，用户编号非法为0! ");
				}
			}
			if (pFlag) {
				if (userRightSystemFeature == null) {
					pFlag = false;
					pErrInfo.getContent().append("添加用户系统功能权限，系统权限信息非法为空! ");
				}
			}
			
			if (pFlag) {
				if (userRightSystemFeature.getID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("添加用户系统功能权限，用户信息非法为0! ");
				}
			}
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				 userRightSystemFeature.setUserID(userInfo.getUserID());
				if (userRightSystemFeatureDao.save(userRightSystemFeature, pErrInfo)) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"为指定用户添加系统功能权限失败: ");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IUserRightSystemFeatureManageService#saveRightSystemFeaturesForUser(com.orifound.aiim.entity.UserInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveRightSystemFeaturesForUser(UserInfo userInfo, List<UserRightSystemFeature> userRightSystemFeatures, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			
			if (pFlag) {
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("添加用户系统功能权限，用户信息非法为空! ");
				}
			}
			
			if (pFlag) {
				if (userInfo.getUserID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("添加用户系统功能权限，用户编号非法为0! ");
				}
			}
			if (pFlag) {
				if (userRightSystemFeatures == null) {
					pFlag = false;
					pErrInfo.getContent().append("添加用户系统功能权限，系统权限信息非法为空! ");
				}
			}
			
			if (pFlag) {
				if (userRightSystemFeatures.size() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("删除用户系统功能权限，系统权限信息非法为空! ");
				}else {
				}
			}
			
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				for (UserRightSystemFeature userRightSystemFeature2 : userRightSystemFeatures) {
					if (userRightSystemFeatureDao.saveUserRightSystemFeatureByUserID(userInfo, userRightSystemFeature2, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"为指定用户添加系统功能权限失败: ");
					}
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IUserRightSystemFeatureManageService#deleteRightSystemFeatureForUser(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.UserRightSystemFeature, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteRightSystemFeatureForUser(UserInfo userInfo, UserRightSystemFeature userRightSystemFeature, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			
			if (pFlag) {
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("添加用户系统功能权限，用户信息非法为空! ");
				}
			}
			
			if (pFlag) {
				if (userInfo.getUserID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("添加用户系统功能权限，用户编号非法为0! ");
				}
			}
			if (pFlag) {
				if (userRightSystemFeature == null) {
					pFlag = false;
					pErrInfo.getContent().append("添加用户系统功能权限，系统权限信息非法为空! ");
				}
			}
			
			if (pFlag) {
				if (userRightSystemFeature.getID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("添加用户系统功能权限，用户信息非法为0! ");
				}
			}
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (userRightSystemFeatureDao.deleteUserRightSystemFeatureByUserIDAndFeatureID(userInfo, userRightSystemFeature, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"为指定用户删除系统功能权限失败: ");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IUserRightSystemFeatureManageService#deleteRightSystemFeaturesForUser(com.orifound.aiim.entity.UserInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteRightSystemFeaturesForUser(UserInfo userInfo, List<UserRightSystemFeature> userRightSystemFeatures, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			
			if (pFlag) {
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("删除用户系统功能权限，用户信息非法为空! ");
				}
			}
			
			if (pFlag) {
				if (userInfo.getUserID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("删除用户系统功能权限，用户编号非法为0! ");
				}
			}
			if (pFlag) {
				if (userRightSystemFeatures == null) {
					pFlag = false;
					pErrInfo.getContent().append("删除用户系统功能权限，系统权限信息非法为空! ");
				}
			}
			
			if (pFlag) {
				if (userRightSystemFeatures.size() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("删除用户系统功能权限，系统权限信息非法为空! ");
				}else {
					for (UserRightSystemFeature userRightSystemFeature2 : userRightSystemFeatures) {
						if (userRightSystemFeature2.getID() <= 0) {
							pFlag = false;
							pErrInfo.getContent().append("删除用户系统功能权限，系统权限编号非法为0! ");
						}
					}
				}
			}
			
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				for (UserRightSystemFeature userRightSystemFeature2 : userRightSystemFeatures) {
					if (userRightSystemFeatureDao.deleteUserRightSystemFeatureByUserIDAndFeatureID(userInfo, userRightSystemFeature2, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"为指定用户删除系统功能权限失败: ");
					}
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IUserRightSystemFeatureManageService#findRightSystemFeatureMenusByUserID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRightSystemFeatureMenusByUserID(int pUserID, LinkedHashMap<String, SystemFeature> systemFeatures, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRightSystemFeature(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查用户编号是否非法
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
				if (userRightSystemFeatureDao.findMenusByUserID(pUserID, systemFeatures, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取用户（编号:"+pUserID+"）的系统功能菜单授权失败: ");
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
	 * @see com.orifound.aiim.bll.service.IUserRightSystemFeatureManageService#findRightSystemFeaturesByUserID(int, java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRightSystemFeaturesByUserID(int pUserID, Map<String, SystemFeature> systemFeatures, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRightSystemFeature(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查用户编号是否非法
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
				if (userRightSystemFeatureDao.findByUserID(pUserID, systemFeatures, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取用户（编号:"+pUserID+"）的系统功能授权失败: ");
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
	public boolean findAllSystemFeature(LinkedHashMap<String, SystemFeature> systemFeatures, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRightSystemFeature(pErrInfo) == false)
			{
				pFlag = false;
			}
			

			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (systemFeatureDao.findAllSystemFeature(systemFeatures, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取所有系统功能授权失败: ");
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
	public boolean deleteRightSystemFeaturesByUserID(int pUserID, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (pUserID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为空!");
				}
			}
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (userRightSystemFeatureDao.deleteUserRightSystemFeatureByUserID(pUserID, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"为指定用户删除系统功能权限失败: ");
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
