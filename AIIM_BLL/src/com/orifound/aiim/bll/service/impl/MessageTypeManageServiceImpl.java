package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.IMessageTypeManageService;
import com.orifound.aiim.dal.dao.IMessageTypeDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.MessageType;

public  class MessageTypeManageServiceImpl implements IMessageTypeManageService {
	
	/**
	 * ���캯��
	 */
	public MessageTypeManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public MessageTypeManageServiceImpl(IMessageTypeDao messageTypeDao) {
		this.messageTypeDao = messageTypeDao;
	}
	
	/**
	 * ��Ϣ���ͱ�����ݷ��ʶ���
	 */
	private IMessageTypeDao messageTypeDao = null;

	/**
	 * ��ȡ����ֵ����Ϣ���ͱ�����ݷ��ʶ���
	 * @return ��Ϣ���ͱ�����ݷ��ʶ���
	 */
	public IMessageTypeDao getMessageTypeDao() {
		return messageTypeDao;
	}

	/**
	 * ��������ֵ����Ϣ���ͱ�����ݷ��ʶ���
	 * @param messageTypeDao ��Ϣ���ͱ�����ݷ��ʶ���
	 */
	public void setMessageTypeDao(IMessageTypeDao messageTypeDao) {
		this.messageTypeDao = messageTypeDao;
	}
	
	/**
	 * ���MessageType��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForMessageType(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (messageTypeDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("MessageType��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
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
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForMessageType(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ��Ϣ����BLL��Daoע��ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (messageTypeDao.findByID(pID, pMessageType, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ID������Ϣ����ʧ�ܣ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
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
