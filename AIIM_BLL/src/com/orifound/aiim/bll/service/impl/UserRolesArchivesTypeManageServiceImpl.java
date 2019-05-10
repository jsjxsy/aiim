package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.IUserRolesArchivesTypeManageService;
import com.orifound.aiim.dal.dao.IUserRolesArchivesTypeDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesArchivesType;

public class UserRolesArchivesTypeManageServiceImpl implements IUserRolesArchivesTypeManageService {
	
	/**
	 * 构造函数
	 */
	public UserRolesArchivesTypeManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public UserRolesArchivesTypeManageServiceImpl(IUserRolesArchivesTypeDao userRolesArchivesTypeDao) {
		this.userRolesArchivesTypeDao = userRolesArchivesTypeDao;
	}
	
	/**
	 * UserRolesArchivesType表的数据访问对象
	 */
	private IUserRolesArchivesTypeDao userRolesArchivesTypeDao = null;

	/**
	 * 获取属性值：UserRolesArchivesType表的数据访问对象
	 * @return UserRolesArchivesType表的数据访问对象
	 */
	public IUserRolesArchivesTypeDao getUserRolesArchivesTypeDao() {
		return userRolesArchivesTypeDao;
	}

	/**
	 * 设置属性值：UserRolesArchivesType表的数据访问对象
	 * @param userRolesArchivesTypeDao UserRolesArchivesType表的数据访问对象
	 */
	public void setUserRolesArchivesTypeDao(IUserRolesArchivesTypeDao userRolesArchivesTypeDao) {
		this.userRolesArchivesTypeDao = userRolesArchivesTypeDao;
	}
	
	/**
	 * 检查UserRolesArchivesType的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForUserRolesArchivesType(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (userRolesArchivesTypeDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("UserRolesArchivesType的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	public boolean deleteUserRolesArchivesType(UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findUserRolesArchivesTypeByID(int pID, UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findUserRolesArchivesTypes(List<UserRolesArchivesType> pUserRolesArchivesTypes, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveUserRolesArchivesType(UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if(checkDaoInjectionForUserRolesArchivesType(pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "用户角色的档案类型权限业务逻辑层Dao注入失败:");
				}
			}
			
			if (pFlag) {
				if (pUserRolesArchivesType == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户角色的档案类型权限表实体类非法为空!");
				}
					
				if (pUserRolesArchivesType.getArchivesTypeID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户角色的档案类型权限表实体类，档案类型编号为空!");
				}
				
				if (pUserRolesArchivesType.getRolesID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户角色的档案类型权限表实体类，角色编号为空!");
				}
			}
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if(userRolesArchivesTypeDao.save(pUserRolesArchivesType, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据角色编号查询用户角色的档案类型权限失败:");
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
	public boolean saveUserRolesArchivesTypes(List<UserRolesArchivesType> pUserRolesArchivesTypes, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if(checkDaoInjectionForUserRolesArchivesType(pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "用户角色的档案类型权限业务逻辑层Dao注入失败:");
				}
			}
			
			if (pFlag) {
				if (pUserRolesArchivesTypes == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户角色的档案类型权限表实体类非法为空!");
				}
			}
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				for (UserRolesArchivesType userRolesArchivesType : pUserRolesArchivesTypes) {
					if(userRolesArchivesTypeDao.save(userRolesArchivesType, pErrInfo) == false){
						pFlag = false;
						pErrInfo.getContent().insert(0, "根据角色编号查询用户角色的档案类型权限失败:");
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
	
	@Override
	public boolean updateUserRolesArchivesType(UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findUserRolesArchivesTypeByRoleID(int pRoleID, List<UserRolesArchivesType> pUserRolesArchivesTypes, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if(checkDaoInjectionForUserRolesArchivesType(pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "用户角色的档案类型权限业务逻辑层Dao注入失败:");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if(userRolesArchivesTypeDao.findByRoleID(pRoleID, pUserRolesArchivesTypes, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据角色编号查询用户角色的档案类型权限失败:");
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
	public boolean deleteUserRolesArchivesTypeByRoleID(int pRoleID, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if(checkDaoInjectionForUserRolesArchivesType(pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "用户角色的档案类型权限业务逻辑层Dao注入失败:");
				}
			}
			
			if (pFlag) {
				if(pRoleID <= 0){
					pFlag = false;
					pErrInfo.getContent().append("用户角色的编号非法为空!");
				}
			}

			
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if(userRolesArchivesTypeDao.deleteByRoleID(pRoleID, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据角色编号查询用户角色的档案类型权限失败:");
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
