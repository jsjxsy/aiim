package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.IUserRolesArchivesTypeManageService;
import com.orifound.aiim.dal.dao.IUserRolesArchivesTypeDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesArchivesType;

public class UserRolesArchivesTypeManageServiceImpl implements IUserRolesArchivesTypeManageService {
	
	/**
	 * ���캯��
	 */
	public UserRolesArchivesTypeManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public UserRolesArchivesTypeManageServiceImpl(IUserRolesArchivesTypeDao userRolesArchivesTypeDao) {
		this.userRolesArchivesTypeDao = userRolesArchivesTypeDao;
	}
	
	/**
	 * UserRolesArchivesType������ݷ��ʶ���
	 */
	private IUserRolesArchivesTypeDao userRolesArchivesTypeDao = null;

	/**
	 * ��ȡ����ֵ��UserRolesArchivesType������ݷ��ʶ���
	 * @return UserRolesArchivesType������ݷ��ʶ���
	 */
	public IUserRolesArchivesTypeDao getUserRolesArchivesTypeDao() {
		return userRolesArchivesTypeDao;
	}

	/**
	 * ��������ֵ��UserRolesArchivesType������ݷ��ʶ���
	 * @param userRolesArchivesTypeDao UserRolesArchivesType������ݷ��ʶ���
	 */
	public void setUserRolesArchivesTypeDao(IUserRolesArchivesTypeDao userRolesArchivesTypeDao) {
		this.userRolesArchivesTypeDao = userRolesArchivesTypeDao;
	}
	
	/**
	 * ���UserRolesArchivesType��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForUserRolesArchivesType(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (userRolesArchivesTypeDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("UserRolesArchivesType��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if(checkDaoInjectionForUserRolesArchivesType(pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "�û���ɫ�ĵ�������Ȩ��ҵ���߼���Daoע��ʧ��:");
				}
			}
			
			if (pFlag) {
				if (pUserRolesArchivesType == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ�ĵ�������Ȩ�ޱ�ʵ����Ƿ�Ϊ��!");
				}
					
				if (pUserRolesArchivesType.getArchivesTypeID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ�ĵ�������Ȩ�ޱ�ʵ���࣬�������ͱ��Ϊ��!");
				}
				
				if (pUserRolesArchivesType.getRolesID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ�ĵ�������Ȩ�ޱ�ʵ���࣬��ɫ���Ϊ��!");
				}
			}
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if(userRolesArchivesTypeDao.save(pUserRolesArchivesType, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ݽ�ɫ��Ų�ѯ�û���ɫ�ĵ�������Ȩ��ʧ��:");
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
	public boolean saveUserRolesArchivesTypes(List<UserRolesArchivesType> pUserRolesArchivesTypes, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if(checkDaoInjectionForUserRolesArchivesType(pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "�û���ɫ�ĵ�������Ȩ��ҵ���߼���Daoע��ʧ��:");
				}
			}
			
			if (pFlag) {
				if (pUserRolesArchivesTypes == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ�ĵ�������Ȩ�ޱ�ʵ����Ƿ�Ϊ��!");
				}
			}
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				for (UserRolesArchivesType userRolesArchivesType : pUserRolesArchivesTypes) {
					if(userRolesArchivesTypeDao.save(userRolesArchivesType, pErrInfo) == false){
						pFlag = false;
						pErrInfo.getContent().insert(0, "���ݽ�ɫ��Ų�ѯ�û���ɫ�ĵ�������Ȩ��ʧ��:");
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
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if(checkDaoInjectionForUserRolesArchivesType(pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "�û���ɫ�ĵ�������Ȩ��ҵ���߼���Daoע��ʧ��:");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if(userRolesArchivesTypeDao.findByRoleID(pRoleID, pUserRolesArchivesTypes, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ݽ�ɫ��Ų�ѯ�û���ɫ�ĵ�������Ȩ��ʧ��:");
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
	public boolean deleteUserRolesArchivesTypeByRoleID(int pRoleID, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if(checkDaoInjectionForUserRolesArchivesType(pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "�û���ɫ�ĵ�������Ȩ��ҵ���߼���Daoע��ʧ��:");
				}
			}
			
			if (pFlag) {
				if(pRoleID <= 0){
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ�ı�ŷǷ�Ϊ��!");
				}
			}

			
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if(userRolesArchivesTypeDao.deleteByRoleID(pRoleID, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ݽ�ɫ��Ų�ѯ�û���ɫ�ĵ�������Ȩ��ʧ��:");
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
