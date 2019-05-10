package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.IUserRolesArchivesSecrecyManageService;
import com.orifound.aiim.dal.dao.IUserRolesArchivesSecrecyDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesArchivesSecrecy;

public class UserRolesArchivesSecrecyManageServiceImpl implements IUserRolesArchivesSecrecyManageService {

	/**
	 * 构造函数
	 */
	public UserRolesArchivesSecrecyManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public UserRolesArchivesSecrecyManageServiceImpl(IUserRolesArchivesSecrecyDao userRolesArchivesSecrecyDao) {
		this.userRolesArchivesSecrecyDao = userRolesArchivesSecrecyDao;
	}
	
	/**
	 * UserRolesArchivesSecrecy表的数据访问对象
	 */
	private IUserRolesArchivesSecrecyDao userRolesArchivesSecrecyDao = null;

	/**
	 * 获取属性值：UserRolesArchivesSecrecy表的数据访问对象
	 * @return UserRolesArchivesSecrecy表的数据访问对象
	 */
	public IUserRolesArchivesSecrecyDao getUserRolesArchivesSecrecyDao() {
		return userRolesArchivesSecrecyDao;
	}

	/**
	 * 设置属性值：UserRolesArchivesSecrecy表的数据访问对象
	 * @param userRolesArchivesSecrecyDao UserRolesArchivesSecrecy表的数据访问对象
	 */
	public void setUserRolesArchivesSecrecyDao(IUserRolesArchivesSecrecyDao userRolesArchivesSecrecyDao) {
		this.userRolesArchivesSecrecyDao = userRolesArchivesSecrecyDao;
	}
	
	
	/**
	 * 检查UserRolesArchivesSecrecy的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForUserRolesArchivesSecrecy(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (userRolesArchivesSecrecyDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("UserRolesArchivesSecrecy的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
		}

		return pFlag;
	}

	@Override
	public boolean deleteUserRolesArchivesSecrecy(UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findUserRolesArchivesSecrecyByID(int pID, UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;

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
	public boolean findUserRolesArchivesSecrecys(List<UserRolesArchivesSecrecy> pUserRolesArchivesSecrecys, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveUserRolesArchivesSecrecy(UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserRolesArchivesSecrecy(UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean findUserRolesArchivesSecrecyByRoleID(int pRoleID, List<UserRolesArchivesSecrecy> pUserRolesArchivesSecrecys, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForUserRolesArchivesSecrecy(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "用户角色档案密级权限BLL层Dao注入失败：");
				}
			}
			
			if (pFlag) {
				if (pRoleID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户角色档案密级权限，角色编号非法为空！");
				}
			}
			
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (userRolesArchivesSecrecyDao.findByRoleID(pRoleID, pUserRolesArchivesSecrecys, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据角色编号RoleID："+pRoleID+"查询用户角色档案密级权限失败:");
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
	public boolean deleteUserRolesArchivesSecrecyByRoleID(int pRoleID, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForUserRolesArchivesSecrecy(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "用户角色档案密级权限BLL层Dao注入失败：");
				}
			}
			
			if (pFlag) {
				if (pRoleID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户角色档案密级权限，角色编号非法为空！");
				}
			}
			
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (userRolesArchivesSecrecyDao.deleteByRoleID(pRoleID,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据角色编号RoleID："+pRoleID+"删除用户角色档案密级权限失败:");
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
	public boolean saveUserRolesArchivesSecrecyS(List<UserRolesArchivesSecrecy> pUserRolesArchivesSecrecys, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForUserRolesArchivesSecrecy(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "用户角色档案密级权限BLL层Dao注入失败：");
				}
			}
			
			
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				for (UserRolesArchivesSecrecy userRolesArchivesSecrecy : pUserRolesArchivesSecrecys) {
					if (userRolesArchivesSecrecyDao.save(userRolesArchivesSecrecy, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "保存用户角色档案密级权限信息失败:");
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

}
