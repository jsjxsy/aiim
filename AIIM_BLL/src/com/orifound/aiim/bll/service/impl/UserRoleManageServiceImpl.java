/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IUserRoleManageService;
import com.orifound.aiim.dal.dao.IUserRoleDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRole;

/**
 * ��ɫ��Ϣ�ֵ��������ʵ����
 * 
 */
public class UserRoleManageServiceImpl implements IUserRoleManageService {
	
	/**
	 * ע���ɫ��Ϣ�ֵ���DAO
	 */
	@Autowired
	private IUserRoleDao userRoleDao;
	
	/**
	 * ���캯��
	 */
	public UserRoleManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public UserRoleManageServiceImpl(IUserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}
	
	/**
	 * ����û���ɫ�ֵ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForUserRole(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (userRoleDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("�û���ɫ�ֵ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	 * @see com.orifound.aiim.bll.service.IUserRoleManageService#deleteUserRole(com.orifound.aiim.entity.UserRole, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteUserRole(UserRole userRole, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		//����û���ɫ�Ƿ�Ϊ��
		if (pFlag) {
			if (userRole == null) {
				pFlag = false;
				pErrInfo.getContent().append("�û���ɫ���ϷǷ�Ϊ�ա�");
			}
		}

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRole(pErrInfo) == false) {
				pFlag = false;
			}

			//�������е��û���ɫ
			if (pFlag) {
				pErrPos = 2;
				if (userRoleDao.delete(userRole, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service �������е��û���ɫ ʧ�ܣ�");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	 * @see com.orifound.aiim.bll.service.IUserRoleManageService#findUserRoleByID(int, com.orifound.aiim.entity.UserRole, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserRoleByID(int pID, UserRole userRole, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		//����û���ɫ�Ƿ�Ϊ��
		if (pFlag) {
			if (userRole == null) {
				pFlag = false;
				pErrInfo.getContent().append("�û���ɫ���ϷǷ�Ϊ�ա�");
			}
		}

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRole(pErrInfo) == false) {
				pFlag = false;
			}

			//�������е��û���ɫ
			if (pFlag) {
				pErrPos = 2;
				if (userRoleDao.findByID(pID, userRole, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service �������е��û���ɫ ʧ�ܣ�");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	 * @see com.orifound.aiim.bll.service.IUserRoleManageService#findUserRoles(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserRoles(List<UserRole> userRoles, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		//����û���ɫ�Ƿ�Ϊ��
		if (pFlag) {
			if (userRoles == null) {
				pFlag = false;
				pErrInfo.getContent().append("�û���ɫ���ϷǷ�Ϊ�ա�");
			}
		}

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRole(pErrInfo) == false) {
				pFlag = false;
			}

			//�������е��û���ɫ
			if (pFlag) {
				pErrPos = 2;
				if (userRoleDao.findAll(userRoles, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service �������е��û���ɫ ʧ�ܣ�");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	 * @see com.orifound.aiim.bll.service.IUserRoleManageService#saveUserRole(com.orifound.aiim.entity.UserRole, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveUserRole(UserRole userRole, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRole(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "�û���ɫBLL��daoע��ʧ��:");
			}
			//����û���ɫ�Ƿ�Ϊ��
			if (pFlag) {
				if (userRole == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ���ϷǷ�Ϊ�ա�");
				}
			}
			//�������е��û���ɫ
			if (pFlag) {
				pErrPos = 2;
				if (userRoleDao.save(userRole, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service �������е��û���ɫ ʧ�ܣ�");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	 * @see com.orifound.aiim.bll.service.IUserRoleManageService#updateUserRole(com.orifound.aiim.entity.UserRole, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateUserRole(UserRole userRole, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		//����û���ɫ�Ƿ�Ϊ��
		if (pFlag) {
			if (userRole == null) {
				pFlag = false;
				pErrInfo.getContent().append("�û���ɫ���ϷǷ�Ϊ�ա�");
			}
		}

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRole(pErrInfo) == false) {
				pFlag = false;
			}

			//�������е��û���ɫ
			if (pFlag) {
				pErrPos = 2;
				if (userRoleDao.update(userRole, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service �޸��û���ɫ ʧ�ܣ�");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	public boolean findUserRolesBySystemRolesFlag(int systemRolesFlag, List<UserRole> userRoles, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		//����û���ɫ�Ƿ�Ϊ��
		if (pFlag) {
			if (userRoles == null) {
				pFlag = false;
				pErrInfo.getContent().append("�û���ɫ���ϷǷ�Ϊ�ա�");
			}
		}

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRole(pErrInfo) == false) {
				pFlag = false;
			}

			//�������е��û���ɫ
			if (pFlag) {
				pErrPos = 2;
				if (userRoleDao.findBySystemRolesFlag(systemRolesFlag, userRoles, pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ϵͳ���н�ɫ��־�����û���ɫʧ�ܣ�");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	public boolean findRoleInfosNotInUserID(int pUserID, List<UserRole> userRoles, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
 
		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRole(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//����û�����Ƿ�Ϸ�
			if (pFlag)
			{
				if (pUserID <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("��ɫ��ŷǷ�Ϊ0");
				}
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if(userRoleDao.findRolesNotInUserID(pUserID, userRoles, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"���Ҳ�����ָ����ɫ��"+pUserID+"�����û���Ϣʧ��: ");
				}
			}
		}
		catch (Exception e)
		{
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException()!=null)
				{
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
