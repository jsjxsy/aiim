/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.ISystemMessageService;
import com.orifound.aiim.dal.dao.ISystemMessageDao;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemMessage;
import com.orifound.aiim.entity.SystemMessageQueryCondition;

/**
 * 系统消息服务实现类
 *
 */
public class SystemMessageServiceImpl implements ISystemMessageService {
	
	/**
	 * 构造函数
	 */
	public SystemMessageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public SystemMessageServiceImpl(ISystemMessageDao systemMessageDao) {
		this.systemMessageDao = systemMessageDao;
	}
	/**
	 * 系统消息表的数据访问对象
	 */
	private ISystemMessageDao systemMessageDao = null;

	/**
	 * 获取属性值：系统消息表的数据访问对象
	 * @return 系统消息表的数据访问对象
	 */
	public ISystemMessageDao getSystemMessageDao() {
		return systemMessageDao;
	}

	/**
	 * 设置属性值：系统消息表的数据访问对象
	 * @param systemMessageDao 系统消息表的数据访问对象
	 */
	public void setSystemMessageDao(ISystemMessageDao systemMessageDao) {
		this.systemMessageDao = systemMessageDao;
	}
	
	/**
	 * 检查系统消息的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForSystemMessage(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (systemMessageDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("系统消息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.ISystemMessageService#clearSystemMessages(int[], com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean clearSystemMessages(int[] userID, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ISystemMessageService#countUnread(int[])
	 */
	@Override
	public int countUnread(int[] userID) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ISystemMessageService#deleteSystemMessage(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteSystemMessage(List<SystemMessage> systemMessages,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForSystemMessage(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "系统消息的DAO依赖注入失败：");
				}
			}
			if (pFlag) {
				if (systemMessages == null) {
					pFlag = false;
					pErrInfo.getContent().append("系统消息对象非法为空！");
				}
			}
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				for (SystemMessage pSystemMessage : systemMessages) {
					if (systemMessageDao.delete(pSystemMessage, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "删除指定的系统消息ID:["+pSystemMessage.getID()+"]失败:");
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
	 * @see com.orifound.aiim.bll.service.ISystemMessageService#findSystemMessages(int[], com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findSystemMessages(int[] userID, DataPageInfo dataPageInfo,
			List<SystemMessage> systemMessages, ErrInfo pErrInfo) {
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ISystemMessageService#findSystemMessages(int[], com.orifound.aiim.entity.SystemMessageQueryCondition, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findSystemMessages(int[] userID,
			SystemMessageQueryCondition systemMessageQueryCondition,
			DataPageInfo dataPageInfo, List<SystemMessage> systemMessages,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ISystemMessageService#givePublicNotices(com.orifound.aiim.entity.SystemMessage, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean givePublicNotices(SystemMessage systemMessage,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ISystemMessageService#readSystemMessage(int, com.orifound.aiim.entity.SystemMessage, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean readSystemMessage(int systemMessageID,
			SystemMessage systemMessage, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ISystemMessageService#sendSystemMessage(com.orifound.aiim.entity.SystemMessage, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean sendSystemMessage(SystemMessage systemMessage,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ISystemMessageService#sendSystemMessage(int[], com.orifound.aiim.entity.SystemMessage, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean sendSystemMessage(int[] userRolesID,
			SystemMessage systemMessage, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean findSystemMessagesByUserID(int pUserID, SystemMessageQueryCondition systemMessageQueryCondition, DataPageInfo dataPageInfo, List<SystemMessage> systemMessages,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForSystemMessage(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"系统消息BLL层Dao注入失败：");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (pUserID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为空！");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (systemMessageDao.findByUserID(pUserID, systemMessageQueryCondition, dataPageInfo, systemMessages, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据用户编号:["+pUserID+"]查询系统消息集合失败！");
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
	public boolean findSystemMessageByID(int pID, SystemMessage pSystemMessage, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForSystemMessage(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "系统消息的DAO依赖注入失败：");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (pID <= 0 ) {
					pFlag = false;
					pErrInfo.getContent().append("系统消息编号非法为空！");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (systemMessageDao.findByID(pID, pSystemMessage, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据唯一标识查找系统消息失败：");
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
	public boolean updateSystemMessage(SystemMessage systemMessage, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForSystemMessage(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "系统消息的DAO依赖注入失败：");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (systemMessage.getID() <= 0 ) {
					pFlag = false;
					pErrInfo.getContent().append("系统消息编号非法为空！");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (systemMessageDao.update(systemMessage, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据唯一标识更新系统消息失败：");
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
