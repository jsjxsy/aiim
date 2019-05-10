/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IUserRoleManageService;
import com.orifound.aiim.dal.dao.IUserRoleDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRole;

/**
 * 角色信息字典表管理服务实现类
 * 
 */
public class UserRoleManageServiceImpl implements IUserRoleManageService {
	
	/**
	 * 注入角色信息字典表的DAO
	 */
	@Autowired
	private IUserRoleDao userRoleDao;
	
	/**
	 * 构造函数
	 */
	public UserRoleManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public UserRoleManageServiceImpl(IUserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}
	
	/**
	 * 检查用户角色字典的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForUserRole(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (userRoleDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("用户角色字典的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IUserRoleManageService#deleteUserRole(com.orifound.aiim.entity.UserRole, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteUserRole(UserRole userRole, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		//检测用户角色是否为空
		if (pFlag) {
			if (userRole == null) {
				pFlag = false;
				pErrInfo.getContent().append("用户角色集合非法为空。");
			}
		}

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRole(pErrInfo) == false) {
				pFlag = false;
			}

			//查找所有的用户角色
			if (pFlag) {
				pErrPos = 2;
				if (userRoleDao.delete(userRole, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service 查找所有的用户角色 失败：");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IUserRoleManageService#findUserRoleByID(int, com.orifound.aiim.entity.UserRole, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserRoleByID(int pID, UserRole userRole, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		//检测用户角色是否为空
		if (pFlag) {
			if (userRole == null) {
				pFlag = false;
				pErrInfo.getContent().append("用户角色集合非法为空。");
			}
		}

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRole(pErrInfo) == false) {
				pFlag = false;
			}

			//查找所有的用户角色
			if (pFlag) {
				pErrPos = 2;
				if (userRoleDao.findByID(pID, userRole, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service 查找所有的用户角色 失败：");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IUserRoleManageService#findUserRoles(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserRoles(List<UserRole> userRoles, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		//检测用户角色是否为空
		if (pFlag) {
			if (userRoles == null) {
				pFlag = false;
				pErrInfo.getContent().append("用户角色集合非法为空。");
			}
		}

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRole(pErrInfo) == false) {
				pFlag = false;
			}

			//查找所有的用户角色
			if (pFlag) {
				pErrPos = 2;
				if (userRoleDao.findAll(userRoles, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service 查找所有的用户角色 失败：");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IUserRoleManageService#saveUserRole(com.orifound.aiim.entity.UserRole, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveUserRole(UserRole userRole, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRole(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "用户角色BLL的dao注入失败:");
			}
			//检测用户角色是否为空
			if (pFlag) {
				if (userRole == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户角色集合非法为空。");
				}
			}
			//查找所有的用户角色
			if (pFlag) {
				pErrPos = 2;
				if (userRoleDao.save(userRole, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service 查找所有的用户角色 失败：");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IUserRoleManageService#updateUserRole(com.orifound.aiim.entity.UserRole, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateUserRole(UserRole userRole, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		//检测用户角色是否为空
		if (pFlag) {
			if (userRole == null) {
				pFlag = false;
				pErrInfo.getContent().append("用户角色集合非法为空。");
			}
		}

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRole(pErrInfo) == false) {
				pFlag = false;
			}

			//查找所有的用户角色
			if (pFlag) {
				pErrPos = 2;
				if (userRoleDao.update(userRole, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service 修改用户角色 失败：");
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
	public boolean findUserRolesBySystemRolesFlag(int systemRolesFlag, List<UserRole> userRoles, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		//检测用户角色是否为空
		if (pFlag) {
			if (userRoles == null) {
				pFlag = false;
				pErrInfo.getContent().append("用户角色集合非法为空。");
			}
		}

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRole(pErrInfo) == false) {
				pFlag = false;
			}

			//查找所有的用户角色
			if (pFlag) {
				pErrPos = 2;
				if (userRoleDao.findBySystemRolesFlag(systemRolesFlag, userRoles, pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据系统固有角色标志查找用户角色失败：");
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
	public boolean findRoleInfosNotInUserID(int pUserID, List<UserRole> userRoles, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
 
		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserRole(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查用户编号是否合法
			if (pFlag)
			{
				if (pUserID <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("角色编号非法为0");
				}
			}

			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if(userRoleDao.findRolesNotInUserID(pUserID, userRoles, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"查找不属于指定角色（"+pUserID+"）的用户信息失败: ");
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
