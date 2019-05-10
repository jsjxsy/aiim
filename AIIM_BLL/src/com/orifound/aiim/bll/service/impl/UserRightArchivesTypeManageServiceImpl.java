/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IUserRightArchivesTypeManageService;
import com.orifound.aiim.dal.dao.IUserRightArchivesTypeDao;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRightArchivesType;

/**
 * 用户档案分类授权管理服务实现类
 *
 */
public class UserRightArchivesTypeManageServiceImpl implements IUserRightArchivesTypeManageService
{
	/**
	 * 构造函数
	 */
	public UserRightArchivesTypeManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public UserRightArchivesTypeManageServiceImpl(IUserRightArchivesTypeDao userRightArchivesTypeDao)
	{
		this.userRightArchivesTypeDao = userRightArchivesTypeDao;
	}
	
	/**
	 * 用户档案分类授权表的数据访问对象
	 */
	private IUserRightArchivesTypeDao userRightArchivesTypeDao = null;

	/**
	 * 获取属性值：用户档案分类授权表的数据访问对象
	 * @return 用户档案分类授权表的数据访问对象
	 */
	public IUserRightArchivesTypeDao getUserRightArchivesTypeDao()
	{
		return userRightArchivesTypeDao;
	}

	/**
	 * 设置属性值：用户档案分类授权表的数据访问对象
	 * @param userRightArchivesTypeDao 用户档案分类授权表的数据访问对象
	 */
	public void setUserRightArchivesTypeDao(IUserRightArchivesTypeDao userRightArchivesTypeDao)
	{
		this.userRightArchivesTypeDao = userRightArchivesTypeDao;
	}
	
	/**
	 * 检查用户档案分类授权信息的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForUserRightArchivesType(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (userRightArchivesTypeDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("用户档案分类授权信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.IUserRightArchivesTypeManageService#saveRightArchivesTypeForUser(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.UserRightArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveRightArchivesTypeForUser(UserInfo userInfo, UserRightArchivesType userRightArchivesType, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRightArchivesType(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查用户编号是否非法
			if (pFlag)
			{
				if (userInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("用户非法为空");
				}
			}
			
			//检查用户编号是否非法
			if (pFlag)
			{
				if (userInfo.getUserID() <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为0");
				}
			}
			
			if (pFlag) {
				if (userRightArchivesType == null) {
					pFlag = false;
					pErrInfo.getContent().append("档案分类对象非法为空!");
				}else {
					if (userRightArchivesType.getArchivesTypeID() <= 0) {
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
			
					if (userRightArchivesTypeDao.save(userRightArchivesType, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0,"获取指定用户（"+userRightArchivesType.getUserID()+"）的档案分类授权信息失败: ");
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
	 * @see com.orifound.aiim.bll.service.IUserRightArchivesTypeManageService#saveRightArchivesTypesForUser(com.orifound.aiim.entity.UserInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveRightArchivesTypesForUser(UserInfo userInfo, List<UserRightArchivesType> userRightArchivesTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRightArchivesType(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查用户编号是否非法
			if (pFlag)
			{
				if (userInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("用户非法为空");
				}
			}
			
			//检查用户编号是否非法
			if (pFlag)
			{
				if (userInfo.getUserID() <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为0");
				}
			}
			
			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				for (UserRightArchivesType userRightArchivesType : userRightArchivesTypes) {
					if (userRightArchivesTypeDao.save(userRightArchivesType, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0,"获取指定用户（"+userRightArchivesType.getUserID()+"）的档案分类授权信息失败: ");
					}
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
	 * @see com.orifound.aiim.bll.service.IUserRightArchivesTypeManageService#deleteRightArchivesTypesForUser(com.orifound.aiim.entity.UserInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteRightArchivesTypesForUser(UserInfo userInfo, List<UserRightArchivesType> userRightArchivesTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRightArchivesType(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查用户编号是否非法
			if (pFlag)
			{
			
				if (userInfo.getUserID() <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为0");
				}
			}

			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				for (UserRightArchivesType userRightArchivesType : userRightArchivesTypes) {
					if (userRightArchivesTypeDao.delete(userRightArchivesType, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0,"获取指定用户（"+userInfo.getUserID()+"）的档案分类授权信息失败: ");
					}			
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
	 * @see com.orifound.aiim.bll.service.IUserRightArchivesTypeManageService#deleteRightArchivesTypesForUser(com.orifound.aiim.entity.UserInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteRightArchivesTypeForUser(UserInfo userInfo, UserRightArchivesType userRightArchivesType, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRightArchivesType(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查用户编号是否非法
			if (pFlag)
			{
				if (userInfo.getUserID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为0");
				}
			}
			
			
			if (pFlag) {
				if (userRightArchivesType == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户档案分类授权信息非法为空");
				}else {
					if (userRightArchivesType.getArchivesTypeID() <= 0) {
						pFlag = false;
						pErrInfo.getContent().append("用户档案分类授权信息档案编号非法为0");
					}
				}
			}
			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (userRightArchivesTypeDao.delete(userRightArchivesType, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取指定用户（"+userRightArchivesType.getID()+"）的档案分类授权信息失败: ");
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
	 * @see com.orifound.aiim.bll.service.IUserRightArchivesTypeManageService#findRightArchivesTypeByUserID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRightArchivesTypeByUserID(int pUserID, LinkedHashMap<Integer, ArchivesType> archivestypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRightArchivesType(pErrInfo) == false)
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
				if (userRightArchivesTypeDao.findByUserID(pUserID, archivestypes, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取指定用户（"+pUserID+"）的档案分类授权信息失败: ");
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
	public boolean deleteRightArchivesTypeByUserID(int pUserID, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRightArchivesType(pErrInfo) == false)
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
				if (userRightArchivesTypeDao.deleteByUserID(pUserID, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取指定用户（"+pUserID+"）的档案分类授权信息失败: ");
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
