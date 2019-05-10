package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.IMessageTypeManageService;
import com.orifound.aiim.dal.dao.IMessageTypeDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.MessageType;

public  class MessageTypeManageServiceImpl implements IMessageTypeManageService {
	
	/**
	 * 构造函数
	 */
	public MessageTypeManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public MessageTypeManageServiceImpl(IMessageTypeDao messageTypeDao) {
		this.messageTypeDao = messageTypeDao;
	}
	
	/**
	 * 消息类型表的数据访问对象
	 */
	private IMessageTypeDao messageTypeDao = null;

	/**
	 * 获取属性值：消息类型表的数据访问对象
	 * @return 消息类型表的数据访问对象
	 */
	public IMessageTypeDao getMessageTypeDao() {
		return messageTypeDao;
	}

	/**
	 * 设置属性值：消息类型表的数据访问对象
	 * @param messageTypeDao 消息类型表的数据访问对象
	 */
	public void setMessageTypeDao(IMessageTypeDao messageTypeDao) {
		this.messageTypeDao = messageTypeDao;
	}
	
	/**
	 * 检查MessageType的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForMessageType(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (messageTypeDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("MessageType的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	public boolean deleteMessageType(MessageType pMessageType, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findMessageTypeByID(int pID, MessageType pMessageType, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForMessageType(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("系统消息类型BLL层Dao注入失败：");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (messageTypeDao.findByID(pID, pMessageType, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据ID查找消息类型失败：");
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
	public boolean findMessageTypes(List<MessageType> pMessageTypes, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveMessageType(MessageType pMessageType, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateMessageType(MessageType pMessageType, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
