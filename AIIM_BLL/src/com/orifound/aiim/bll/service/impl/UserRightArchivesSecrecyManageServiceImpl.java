/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IUserRightArchivesSecrecyManageService;
import com.orifound.aiim.dal.dao.IUserRightArchivesSecrecyDao;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRightArchivesSecrecy;

/**
 * 用户档案密级授权管理服务实现类
 *
 */
public class UserRightArchivesSecrecyManageServiceImpl implements IUserRightArchivesSecrecyManageService
{
	
	/**
	 * 构造函数
	 */
	public UserRightArchivesSecrecyManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public UserRightArchivesSecrecyManageServiceImpl(IUserRightArchivesSecrecyDao userRightArchivesSecrecyDao)
	{
		this.userRightArchivesSecrecyDao = userRightArchivesSecrecyDao;
	}
	
	/**
	 * 用户档案密级权限表的数据访问对象
	 */
	private IUserRightArchivesSecrecyDao userRightArchivesSecrecyDao = null;

	/**
	 * 获取属性值：用户档案密级权限表的数据访问对象
	 * @return 用户档案密级权限表的数据访问对象
	 */
	public IUserRightArchivesSecrecyDao getUserRightArchivesSecrecyDao()
	{
		return userRightArchivesSecrecyDao;
	}

	/**
	 * 设置属性值：用户档案密级权限表的数据访问对象
	 * @param userRightArchivesSecrecyDao 用户档案密级权限表的数据访问对象
	 */
	public void setUserRightArchivesSecrecyDao(IUserRightArchivesSecrecyDao userRightArchivesSecrecyDao)
	{
		this.userRightArchivesSecrecyDao = userRightArchivesSecrecyDao;
	}
	
	/**
	 * 检查用户档案密级授权信息的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForUserRightArchivesSecrecy(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (userRightArchivesSecrecyDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("用户档案密级授权信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.IUserRightArchivesSecrecyManageService#saveRightArchivesSecrecysForUser(com.orifound.aiim.entity.UserInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean saveRightArchivesSecrecysForUser(UserInfo userInfo, List<UserRightArchivesSecrecy> userRightArchivesSecrecies, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRightArchivesSecrecy(pErrInfo) == false)
			{
				pFlag = false;
				pErrInfo.getContent().insert(0, "档案密级权限BLL层DAO依赖注入失败：");
			}
			
			if (pFlag)
			{
				if (userInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("用户信息非法为空");
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
				for (Iterator iterator = userRightArchivesSecrecies.iterator(); iterator.hasNext();) {
					UserRightArchivesSecrecy userRightArchivesSecrecy = (UserRightArchivesSecrecy) iterator.next();
					if (userRightArchivesSecrecyDao.save(userRightArchivesSecrecy, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0," 保存用户档案密级权限失败：");
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
	 * @see com.orifound.aiim.bll.service.IUserRightArchivesSecrecyManageService#deleteRightArchivesSecrecysForUser(com.orifound.aiim.entity.UserInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteRightArchivesSecrecysForUser(UserInfo userInfo, List<UserRightArchivesSecrecy> userRightArchivesSecrecies, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IUserRightArchivesSecrecyManageService#findRightArchivesSecrecysByUserID(int, java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRightArchivesSecrecysByUserID(int pUserID, Map<Integer, ArchivesSecrecy> archivesSecrecys, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRightArchivesSecrecy(pErrInfo) == false)
			{
				pFlag = false;
				pErrInfo.getContent().insert(0, "档案密级权限BLL层DAO依赖注入失败：");
			}
			
			//检查用户编号是否非法
			if (pFlag)
			{
				if (pUserID <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为0");
				}
			}

			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (userRightArchivesSecrecyDao.findByUserID(pUserID, archivesSecrecys, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取用户（"+pUserID+"）档案密级授权信息失败: ");
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
	
	
	public boolean findRightArchivesSecrecysByUserID(int pUserID,List<UserRightArchivesSecrecy> userRightArchivesSecrecys, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRightArchivesSecrecy(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查用户编号是否非法
			if (pFlag)
			{
				if (pUserID <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为0");
				}
			}

			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (userRightArchivesSecrecyDao.findByUserID(pUserID, userRightArchivesSecrecys, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取用户（"+pUserID+"）档案密级授权信息失败: ");
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
	
	public boolean deleteUserRightArchivesSecrecyByUserID(int pUserID, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRightArchivesSecrecy(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查用户编号是否非法
			if (pFlag)
			{
				if (pUserID <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为0");
				}
			}

			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (userRightArchivesSecrecyDao.deleteByUserID(pUserID, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取用户（"+pUserID+"）档案密级授权信息失败: ");
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
