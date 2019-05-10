/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;
import com.orifound.aiim.bll.service.IUserDefinedSearchManageService;
import com.orifound.aiim.dal.dao.IUserDefinedSearchDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserDefinedSearch;

/**
 * 用户自定义条件管理服务的实现类
 * @author Administrator
 *
 */
public class UserDefinedSearchManageServiceImpl implements IUserDefinedSearchManageService {
	
	
	/**
	 * 用户自定义条件表的数据访问对象
	 */
	private IUserDefinedSearchDao userDefinedSearchDao = null;

	/**
	 * 获取属性值：用户自定义条件表的数据访问对象
	 * @return 用户自定义条件表的数据访问对象
	 */
	public IUserDefinedSearchDao getUserDefinedSearchDao() {
		return userDefinedSearchDao;
	}

	/**
	 * 设置属性值：用户自定义条件表的数据访问对象
	 * @param userDefinedSearchDao 用户自定义条件表的数据访问对象
	 */
	public void setUserDefinedSearchDao(IUserDefinedSearchDao userDefinedSearchDao) {
		this.userDefinedSearchDao = userDefinedSearchDao;
	}

	
	/**
	 * 检查用户自定义条件查询的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForUserDefinedSearch(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (userDefinedSearchDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("用户自定义条件查询的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	
	

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IUserDefinedSearchManageService#addUserDefinedSearch(com.orifound.aiim.entity.UserDefinedSearch, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean addUserDefinedSearch(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserDefinedSearch(pErrInfo) == false) {
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag) {
				if (userDefinedSearchDao.addUserDefinedSearchs(userDefinedSearch, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "新增自定义条件查询失败！ ");
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
	 * @see com.orifound.aiim.bll.service.IUserDefinedSearchManageService#deleteUserDefinedSearch(com.orifound.aiim.entity.UserDefinedSearch, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteUserDefinedSearch(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserDefinedSearch(pErrInfo) == false) {
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag) {
				if (userDefinedSearchDao.delete(userDefinedSearch, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "删除用户自定义条件查询失败: ");
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
	 * @see com.orifound.aiim.bll.service.IUserDefinedSearchManageService#findUserDefinedSearchByID(int, com.orifound.aiim.entity.UserDefinedSearch, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserDefinedSearchByID(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserDefinedSearch(pErrInfo) == false) {
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag) {
				if (userDefinedSearchDao.findUserDefinedSearchByID(userDefinedSearch, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取用户自定义条件集合失败: ");
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
	 * @see com.orifound.aiim.bll.service.IUserDefinedSearchManageService#findUserDefinedSearchsByUserID(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserDefinedSearchsByUserID(int userID,List<UserDefinedSearch> userDefinedSearchs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserDefinedSearch(pErrInfo) == false) {
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag) {
				if (userDefinedSearchDao.findUserDefinedSearchsByUserID(userID,userDefinedSearchs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取用户自定义条件集合失败: ");
				}
			}
System.out.println("BLL--->userDefinedSearchs:-->" + userDefinedSearchs.size());
			
			
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
	 * @see com.orifound.aiim.bll.service.IUserDefinedSearchManageService#findUserDefinedSearchsByUserID(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean checkQueryNameExist(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForUserDefinedSearch(pErrInfo) == false) {
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag) {
				if (userDefinedSearchDao.checkQueryNameExist(userDefinedSearch, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取用户自定义条件集合失败: ");
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
