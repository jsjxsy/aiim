package com.orifound.aiim.bll.service.impl;

import java.util.List;


import com.orifound.aiim.bll.service.IUserRolesSystemFeatureManageService;
import com.orifound.aiim.dal.dao.IUserRolesSystemFeatureDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesSystemFeature;

public class UserRolesSystemFeatureManageServiceImpl implements IUserRolesSystemFeatureManageService{
   
	
	/**
	 * UserRolesSystemFeature表的数据访问对象
	 */
	private IUserRolesSystemFeatureDao userRolesSystemFeatureDao = null;

	/**
	 * 获取属性值：UserRolesSystemFeature表的数据访问对象
	 * @return UserRolesSystemFeature表的数据访问对象
	 */
	public IUserRolesSystemFeatureDao getUserRolesSystemFeatureDao() {
		return userRolesSystemFeatureDao;
	}

	/**
	 * 设置属性值：UserRolesSystemFeature表的数据访问对象
	 * @param userRolesSystemFeatureDao UserRolesSystemFeature表的数据访问对象
	 */
	public void setUserRolesSystemFeatureDao(IUserRolesSystemFeatureDao userRolesSystemFeatureDao) {
		this.userRolesSystemFeatureDao = userRolesSystemFeatureDao;
	}
	
	/**
	 * 构造函数
	 */
	public UserRolesSystemFeatureManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public UserRolesSystemFeatureManageServiceImpl(IUserRolesSystemFeatureDao userRolesSystemFeatureDao) {
		this.userRolesSystemFeatureDao = userRolesSystemFeatureDao;
	}
	
	/**
	 * 检查UserRolesSystemFeature的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForUserRolesSystemFeature(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (userRolesSystemFeatureDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("UserRolesSystemFeature的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	public boolean deleteUserRolesSystemFeature(UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean deleteUserRolesSystemFeaturesByRoleID(int  pRoleID, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (checkDaoInjectionForUserRolesSystemFeature(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"用户角色的系统功能业务逻辑层Dao注入失败:");
			}
			
			if (pFlag) {
				if(pRoleID <= 0){
					pFlag = false;
					pErrInfo.getContent().append("用户角色的系统功能，角色编号非法为0！");
				}
			}
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				if (userRolesSystemFeatureDao.deleteByRoleID(pRoleID, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据角色标号查询用户角色的系统功能失败！");
				}
			}
				//返回查询结果

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
	public boolean findUserRolesSystemFeatureByID(int pID, UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findUserRolesSystemFeatures(List<UserRolesSystemFeature> pUserRolesSystemFeatures, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveUserRolesSystemFeature(UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (checkDaoInjectionForUserRolesSystemFeature(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"用户角色的系统功能业务逻辑层Dao注入失败:");
			}
			
			if (pFlag) {
				if(pUserRolesSystemFeature == null){
					pFlag = false;
					pErrInfo.getContent().append("用户角色的系统功能权限表实体类非法为空！");
				}
			}
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				if (userRolesSystemFeatureDao.save(pUserRolesSystemFeature, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据角色标号查询用户角色的系统功能失败！");
				}
			}
				//返回查询结果

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
	public boolean saveUserRolesSystemFeatures(List<UserRolesSystemFeature> pUserRolesSystemFeatures, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (checkDaoInjectionForUserRolesSystemFeature(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"用户角色的系统功能业务逻辑层Dao注入失败:");
			}
			
			if (pFlag) {
				if(pUserRolesSystemFeatures == null){
					pFlag = false;
					pErrInfo.getContent().append("用户角色的系统功能权限表实体类非法为空！");
				}
			}
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				for (UserRolesSystemFeature userRolesSystemFeature : pUserRolesSystemFeatures) {
					if (userRolesSystemFeatureDao.save(userRolesSystemFeature, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"根据角色标号查询用户角色的系统功能失败！");
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
	public boolean updateUserRolesSystemFeature(UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findUserRolesSystemFeatureByRoleID(int pRoleID, List<UserRolesSystemFeature> pUserRolesSystemFeatures, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (checkDaoInjectionForUserRolesSystemFeature(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"用户角色的系统功能业务逻辑层Dao注入失败:");
			}
			
			if (pFlag) {
				if(pRoleID <= 0){
					pFlag = false;
					pErrInfo.getContent().append("用户角色的系统功能，角色编号非法为0！");
				}
			}
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				if (userRolesSystemFeatureDao.findByRoleID(pRoleID, pUserRolesSystemFeatures, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据角色标号查询用户角色的系统功能失败！");
				}
			}
				//返回查询结果

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
