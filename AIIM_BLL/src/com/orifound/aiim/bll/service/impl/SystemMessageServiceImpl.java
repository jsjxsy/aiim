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
 * ϵͳ��Ϣ����ʵ����
 *
 */
public class SystemMessageServiceImpl implements ISystemMessageService {
	
	/**
	 * ���캯��
	 */
	public SystemMessageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public SystemMessageServiceImpl(ISystemMessageDao systemMessageDao) {
		this.systemMessageDao = systemMessageDao;
	}
	/**
	 * ϵͳ��Ϣ������ݷ��ʶ���
	 */
	private ISystemMessageDao systemMessageDao = null;

	/**
	 * ��ȡ����ֵ��ϵͳ��Ϣ������ݷ��ʶ���
	 * @return ϵͳ��Ϣ������ݷ��ʶ���
	 */
	public ISystemMessageDao getSystemMessageDao() {
		return systemMessageDao;
	}

	/**
	 * ��������ֵ��ϵͳ��Ϣ������ݷ��ʶ���
	 * @param systemMessageDao ϵͳ��Ϣ������ݷ��ʶ���
	 */
	public void setSystemMessageDao(ISystemMessageDao systemMessageDao) {
		this.systemMessageDao = systemMessageDao;
	}
	
	/**
	 * ���ϵͳ��Ϣ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForSystemMessage(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (systemMessageDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("ϵͳ��Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForSystemMessage(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "ϵͳ��Ϣ��DAO����ע��ʧ�ܣ�");
				}
			}
			if (pFlag) {
				if (systemMessages == null) {
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ��Ϣ����Ƿ�Ϊ�գ�");
				}
			}
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				for (SystemMessage pSystemMessage : systemMessages) {
					if (systemMessageDao.delete(pSystemMessage, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "ɾ��ָ����ϵͳ��ϢID:["+pSystemMessage.getID()+"]ʧ��:");
					}
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
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForSystemMessage(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"ϵͳ��ϢBLL��Daoע��ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (pUserID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ�գ�");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (systemMessageDao.findByUserID(pUserID, systemMessageQueryCondition, dataPageInfo, systemMessages, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"�����û����:["+pUserID+"]��ѯϵͳ��Ϣ����ʧ�ܣ�");
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
	public boolean findSystemMessageByID(int pID, SystemMessage pSystemMessage, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForSystemMessage(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "ϵͳ��Ϣ��DAO����ע��ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (pID <= 0 ) {
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ��Ϣ��ŷǷ�Ϊ�գ�");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (systemMessageDao.findByID(pID, pSystemMessage, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"����Ψһ��ʶ����ϵͳ��Ϣʧ�ܣ�");
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
	public boolean updateSystemMessage(SystemMessage systemMessage, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForSystemMessage(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "ϵͳ��Ϣ��DAO����ע��ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (systemMessage.getID() <= 0 ) {
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ��Ϣ��ŷǷ�Ϊ�գ�");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (systemMessageDao.update(systemMessage, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"����Ψһ��ʶ����ϵͳ��Ϣʧ�ܣ�");
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

}
