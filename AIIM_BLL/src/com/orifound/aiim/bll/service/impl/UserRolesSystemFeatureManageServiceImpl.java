package com.orifound.aiim.bll.service.impl;

import java.util.List;


import com.orifound.aiim.bll.service.IUserRolesSystemFeatureManageService;
import com.orifound.aiim.dal.dao.IUserRolesSystemFeatureDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesSystemFeature;

public class UserRolesSystemFeatureManageServiceImpl implements IUserRolesSystemFeatureManageService{
   
	
	/**
	 * UserRolesSystemFeature������ݷ��ʶ���
	 */
	private IUserRolesSystemFeatureDao userRolesSystemFeatureDao = null;

	/**
	 * ��ȡ����ֵ��UserRolesSystemFeature������ݷ��ʶ���
	 * @return UserRolesSystemFeature������ݷ��ʶ���
	 */
	public IUserRolesSystemFeatureDao getUserRolesSystemFeatureDao() {
		return userRolesSystemFeatureDao;
	}

	/**
	 * ��������ֵ��UserRolesSystemFeature������ݷ��ʶ���
	 * @param userRolesSystemFeatureDao UserRolesSystemFeature������ݷ��ʶ���
	 */
	public void setUserRolesSystemFeatureDao(IUserRolesSystemFeatureDao userRolesSystemFeatureDao) {
		this.userRolesSystemFeatureDao = userRolesSystemFeatureDao;
	}
	
	/**
	 * ���캯��
	 */
	public UserRolesSystemFeatureManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public UserRolesSystemFeatureManageServiceImpl(IUserRolesSystemFeatureDao userRolesSystemFeatureDao) {
		this.userRolesSystemFeatureDao = userRolesSystemFeatureDao;
	}
	
	/**
	 * ���UserRolesSystemFeature��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForUserRolesSystemFeature(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (userRolesSystemFeatureDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("UserRolesSystemFeature��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
			//��ʼ���� 1...
			pErrPos = 1;
			if (checkDaoInjectionForUserRolesSystemFeature(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"�û���ɫ��ϵͳ����ҵ���߼���Daoע��ʧ��:");
			}
			
			if (pFlag) {
				if(pRoleID <= 0){
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ��ϵͳ���ܣ���ɫ��ŷǷ�Ϊ0��");
				}
			}
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				if (userRolesSystemFeatureDao.deleteByRoleID(pRoleID, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���ݽ�ɫ��Ų�ѯ�û���ɫ��ϵͳ����ʧ�ܣ�");
				}
			}
				//���ز�ѯ���

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
			//��ʼ���� 1...
			pErrPos = 1;
			if (checkDaoInjectionForUserRolesSystemFeature(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"�û���ɫ��ϵͳ����ҵ���߼���Daoע��ʧ��:");
			}
			
			if (pFlag) {
				if(pUserRolesSystemFeature == null){
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ��ϵͳ����Ȩ�ޱ�ʵ����Ƿ�Ϊ�գ�");
				}
			}
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				if (userRolesSystemFeatureDao.save(pUserRolesSystemFeature, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���ݽ�ɫ��Ų�ѯ�û���ɫ��ϵͳ����ʧ�ܣ�");
				}
			}
				//���ز�ѯ���

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
	public boolean saveUserRolesSystemFeatures(List<UserRolesSystemFeature> pUserRolesSystemFeatures, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (checkDaoInjectionForUserRolesSystemFeature(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"�û���ɫ��ϵͳ����ҵ���߼���Daoע��ʧ��:");
			}
			
			if (pFlag) {
				if(pUserRolesSystemFeatures == null){
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ��ϵͳ����Ȩ�ޱ�ʵ����Ƿ�Ϊ�գ�");
				}
			}
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				for (UserRolesSystemFeature userRolesSystemFeature : pUserRolesSystemFeatures) {
					if (userRolesSystemFeatureDao.save(userRolesSystemFeature, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"���ݽ�ɫ��Ų�ѯ�û���ɫ��ϵͳ����ʧ�ܣ�");
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
			//��ʼ���� 1...
			pErrPos = 1;
			if (checkDaoInjectionForUserRolesSystemFeature(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"�û���ɫ��ϵͳ����ҵ���߼���Daoע��ʧ��:");
			}
			
			if (pFlag) {
				if(pRoleID <= 0){
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ��ϵͳ���ܣ���ɫ��ŷǷ�Ϊ0��");
				}
			}
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				if (userRolesSystemFeatureDao.findByRoleID(pRoleID, pUserRolesSystemFeatures, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���ݽ�ɫ��Ų�ѯ�û���ɫ��ϵͳ����ʧ�ܣ�");
				}
			}
				//���ز�ѯ���

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
