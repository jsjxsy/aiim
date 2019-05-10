package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.IUserRolesArchivesSecrecyManageService;
import com.orifound.aiim.dal.dao.IUserRolesArchivesSecrecyDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesArchivesSecrecy;

public class UserRolesArchivesSecrecyManageServiceImpl implements IUserRolesArchivesSecrecyManageService {

	/**
	 * ���캯��
	 */
	public UserRolesArchivesSecrecyManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public UserRolesArchivesSecrecyManageServiceImpl(IUserRolesArchivesSecrecyDao userRolesArchivesSecrecyDao) {
		this.userRolesArchivesSecrecyDao = userRolesArchivesSecrecyDao;
	}
	
	/**
	 * UserRolesArchivesSecrecy������ݷ��ʶ���
	 */
	private IUserRolesArchivesSecrecyDao userRolesArchivesSecrecyDao = null;

	/**
	 * ��ȡ����ֵ��UserRolesArchivesSecrecy������ݷ��ʶ���
	 * @return UserRolesArchivesSecrecy������ݷ��ʶ���
	 */
	public IUserRolesArchivesSecrecyDao getUserRolesArchivesSecrecyDao() {
		return userRolesArchivesSecrecyDao;
	}

	/**
	 * ��������ֵ��UserRolesArchivesSecrecy������ݷ��ʶ���
	 * @param userRolesArchivesSecrecyDao UserRolesArchivesSecrecy������ݷ��ʶ���
	 */
	public void setUserRolesArchivesSecrecyDao(IUserRolesArchivesSecrecyDao userRolesArchivesSecrecyDao) {
		this.userRolesArchivesSecrecyDao = userRolesArchivesSecrecyDao;
	}
	
	
	/**
	 * ���UserRolesArchivesSecrecy��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForUserRolesArchivesSecrecy(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (userRolesArchivesSecrecyDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("UserRolesArchivesSecrecy��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
			//��ʼ���� 1...
			pErrPos = 1;
			

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;

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
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForUserRolesArchivesSecrecy(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�û���ɫ�����ܼ�Ȩ��BLL��Daoע��ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				if (pRoleID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ�����ܼ�Ȩ�ޣ���ɫ��ŷǷ�Ϊ�գ�");
				}
			}
			
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (userRolesArchivesSecrecyDao.findByRoleID(pRoleID, pUserRolesArchivesSecrecys, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ݽ�ɫ���RoleID��"+pRoleID+"��ѯ�û���ɫ�����ܼ�Ȩ��ʧ��:");
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
	public boolean deleteUserRolesArchivesSecrecyByRoleID(int pRoleID, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForUserRolesArchivesSecrecy(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�û���ɫ�����ܼ�Ȩ��BLL��Daoע��ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				if (pRoleID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ�����ܼ�Ȩ�ޣ���ɫ��ŷǷ�Ϊ�գ�");
				}
			}
			
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (userRolesArchivesSecrecyDao.deleteByRoleID(pRoleID,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ݽ�ɫ���RoleID��"+pRoleID+"ɾ���û���ɫ�����ܼ�Ȩ��ʧ��:");
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
	public boolean saveUserRolesArchivesSecrecyS(List<UserRolesArchivesSecrecy> pUserRolesArchivesSecrecys, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForUserRolesArchivesSecrecy(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�û���ɫ�����ܼ�Ȩ��BLL��Daoע��ʧ�ܣ�");
				}
			}
			
			
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				for (UserRolesArchivesSecrecy userRolesArchivesSecrecy : pUserRolesArchivesSecrecys) {
					if (userRolesArchivesSecrecyDao.save(userRolesArchivesSecrecy, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "�����û���ɫ�����ܼ�Ȩ����Ϣʧ��:");
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

}
