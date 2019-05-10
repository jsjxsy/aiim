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
 * �û��Զ���������������ʵ����
 * @author Administrator
 *
 */
public class UserDefinedSearchManageServiceImpl implements IUserDefinedSearchManageService {
	
	
	/**
	 * �û��Զ�������������ݷ��ʶ���
	 */
	private IUserDefinedSearchDao userDefinedSearchDao = null;

	/**
	 * ��ȡ����ֵ���û��Զ�������������ݷ��ʶ���
	 * @return �û��Զ�������������ݷ��ʶ���
	 */
	public IUserDefinedSearchDao getUserDefinedSearchDao() {
		return userDefinedSearchDao;
	}

	/**
	 * ��������ֵ���û��Զ�������������ݷ��ʶ���
	 * @param userDefinedSearchDao �û��Զ�������������ݷ��ʶ���
	 */
	public void setUserDefinedSearchDao(IUserDefinedSearchDao userDefinedSearchDao) {
		this.userDefinedSearchDao = userDefinedSearchDao;
	}

	
	/**
	 * ����û��Զ���������ѯ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForUserDefinedSearch(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (userDefinedSearchDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("�û��Զ���������ѯ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IUserDefinedSearchManageService#addUserDefinedSearch(com.orifound.aiim.entity.UserDefinedSearch, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean addUserDefinedSearch(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserDefinedSearch(pErrInfo) == false) {
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag) {
				if (userDefinedSearchDao.addUserDefinedSearchs(userDefinedSearch, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�����Զ���������ѯʧ�ܣ� ");
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
	 * @see com.orifound.aiim.bll.service.IUserDefinedSearchManageService#deleteUserDefinedSearch(com.orifound.aiim.entity.UserDefinedSearch, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteUserDefinedSearch(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserDefinedSearch(pErrInfo) == false) {
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag) {
				if (userDefinedSearchDao.delete(userDefinedSearch, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "ɾ���û��Զ���������ѯʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IUserDefinedSearchManageService#findUserDefinedSearchByID(int, com.orifound.aiim.entity.UserDefinedSearch, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserDefinedSearchByID(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserDefinedSearch(pErrInfo) == false) {
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag) {
				if (userDefinedSearchDao.findUserDefinedSearchByID(userDefinedSearch, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ�û��Զ�����������ʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IUserDefinedSearchManageService#findUserDefinedSearchsByUserID(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserDefinedSearchsByUserID(int userID,List<UserDefinedSearch> userDefinedSearchs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserDefinedSearch(pErrInfo) == false) {
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag) {
				if (userDefinedSearchDao.findUserDefinedSearchsByUserID(userID,userDefinedSearchs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ�û��Զ�����������ʧ��: ");
				}
			}
System.out.println("BLL--->userDefinedSearchs:-->" + userDefinedSearchs.size());
			
			
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
	 * @see com.orifound.aiim.bll.service.IUserDefinedSearchManageService#findUserDefinedSearchsByUserID(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean checkQueryNameExist(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserDefinedSearch(pErrInfo) == false) {
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag) {
				if (userDefinedSearchDao.checkQueryNameExist(userDefinedSearch, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ�û��Զ�����������ʧ��: ");
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
