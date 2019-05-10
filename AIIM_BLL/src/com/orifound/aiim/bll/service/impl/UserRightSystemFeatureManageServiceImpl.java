/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IUserRightSystemFeatureManageService;
import com.orifound.aiim.dal.dao.ISystemFeatureDao;
import com.orifound.aiim.dal.dao.IUserRightSystemFeatureDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRightSystemFeature;

/**
 * �û�ϵͳ������Ȩ�������ʵ����
 *
 */
public class UserRightSystemFeatureManageServiceImpl implements IUserRightSystemFeatureManageService
{
	
	/**
	 * ���캯��
	 */
	public UserRightSystemFeatureManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public UserRightSystemFeatureManageServiceImpl(IUserRightSystemFeatureDao userRightSystemFeatureDao)
	{
		this.userRightSystemFeatureDao = userRightSystemFeatureDao;
	}
	
	/**
	 * �û�ϵͳ������Ȩ��Ϣ������ݷ��ʶ���
	 */
	private IUserRightSystemFeatureDao userRightSystemFeatureDao = null;

	/**
	 * ��ȡ����ֵ���û�ϵͳ������Ȩ��Ϣ������ݷ��ʶ���
	 * @return �û�ϵͳ������Ȩ��Ϣ������ݷ��ʶ���
	 */
	public IUserRightSystemFeatureDao getUserRightSystemFeatureDao()
	{
		return userRightSystemFeatureDao;
	}

	/**
	 * ��������ֵ���û�ϵͳ������Ȩ��Ϣ������ݷ��ʶ���
	 * @param userRightSystemFeatureDao �û�ϵͳ������Ȩ��Ϣ������ݷ��ʶ���
	 */
	public void setUserRightSystemFeatureDao(IUserRightSystemFeatureDao userRightSystemFeatureDao)
	{
		this.userRightSystemFeatureDao = userRightSystemFeatureDao;
	}
	
	private ISystemFeatureDao systemFeatureDao = null;
	
	public ISystemFeatureDao getSystemFeatureDao() {
		return systemFeatureDao;
	}

	public void setSystemFeatureDao(ISystemFeatureDao systemFeatureDao) {
		this.systemFeatureDao = systemFeatureDao;
	}

	/**
	 * ����û�ϵͳ������Ȩ��Ϣ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForUserRightSystemFeature(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (userRightSystemFeatureDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("�û�ϵͳ������Ȩ��Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IUserRightSystemFeatureManageService#saveRightSystemFeatureForUser(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.UserRightSystemFeature, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveRightSystemFeatureForUser(UserInfo userInfo, UserRightSystemFeature userRightSystemFeature, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			
			if (pFlag) {
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("����û�ϵͳ����Ȩ�ޣ��û���Ϣ�Ƿ�Ϊ��! ");
				}
			}
			
			if (pFlag) {
				if (userInfo.getUserID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("����û�ϵͳ����Ȩ�ޣ��û���ŷǷ�Ϊ0! ");
				}
			}
			if (pFlag) {
				if (userRightSystemFeature == null) {
					pFlag = false;
					pErrInfo.getContent().append("����û�ϵͳ����Ȩ�ޣ�ϵͳȨ����Ϣ�Ƿ�Ϊ��! ");
				}
			}
			
			if (pFlag) {
				if (userRightSystemFeature.getID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("����û�ϵͳ����Ȩ�ޣ��û���Ϣ�Ƿ�Ϊ0! ");
				}
			}
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				 userRightSystemFeature.setUserID(userInfo.getUserID());
				if (userRightSystemFeatureDao.save(userRightSystemFeature, pErrInfo)) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"Ϊָ���û����ϵͳ����Ȩ��ʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IUserRightSystemFeatureManageService#saveRightSystemFeaturesForUser(com.orifound.aiim.entity.UserInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveRightSystemFeaturesForUser(UserInfo userInfo, List<UserRightSystemFeature> userRightSystemFeatures, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			
			if (pFlag) {
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("����û�ϵͳ����Ȩ�ޣ��û���Ϣ�Ƿ�Ϊ��! ");
				}
			}
			
			if (pFlag) {
				if (userInfo.getUserID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("����û�ϵͳ����Ȩ�ޣ��û���ŷǷ�Ϊ0! ");
				}
			}
			if (pFlag) {
				if (userRightSystemFeatures == null) {
					pFlag = false;
					pErrInfo.getContent().append("����û�ϵͳ����Ȩ�ޣ�ϵͳȨ����Ϣ�Ƿ�Ϊ��! ");
				}
			}
			
			if (pFlag) {
				if (userRightSystemFeatures.size() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("ɾ���û�ϵͳ����Ȩ�ޣ�ϵͳȨ����Ϣ�Ƿ�Ϊ��! ");
				}else {
				}
			}
			
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				for (UserRightSystemFeature userRightSystemFeature2 : userRightSystemFeatures) {
					if (userRightSystemFeatureDao.saveUserRightSystemFeatureByUserID(userInfo, userRightSystemFeature2, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"Ϊָ���û����ϵͳ����Ȩ��ʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IUserRightSystemFeatureManageService#deleteRightSystemFeatureForUser(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.UserRightSystemFeature, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteRightSystemFeatureForUser(UserInfo userInfo, UserRightSystemFeature userRightSystemFeature, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			
			if (pFlag) {
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("����û�ϵͳ����Ȩ�ޣ��û���Ϣ�Ƿ�Ϊ��! ");
				}
			}
			
			if (pFlag) {
				if (userInfo.getUserID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("����û�ϵͳ����Ȩ�ޣ��û���ŷǷ�Ϊ0! ");
				}
			}
			if (pFlag) {
				if (userRightSystemFeature == null) {
					pFlag = false;
					pErrInfo.getContent().append("����û�ϵͳ����Ȩ�ޣ�ϵͳȨ����Ϣ�Ƿ�Ϊ��! ");
				}
			}
			
			if (pFlag) {
				if (userRightSystemFeature.getID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("����û�ϵͳ����Ȩ�ޣ��û���Ϣ�Ƿ�Ϊ0! ");
				}
			}
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (userRightSystemFeatureDao.deleteUserRightSystemFeatureByUserIDAndFeatureID(userInfo, userRightSystemFeature, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"Ϊָ���û�ɾ��ϵͳ����Ȩ��ʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IUserRightSystemFeatureManageService#deleteRightSystemFeaturesForUser(com.orifound.aiim.entity.UserInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteRightSystemFeaturesForUser(UserInfo userInfo, List<UserRightSystemFeature> userRightSystemFeatures, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			
			if (pFlag) {
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("ɾ���û�ϵͳ����Ȩ�ޣ��û���Ϣ�Ƿ�Ϊ��! ");
				}
			}
			
			if (pFlag) {
				if (userInfo.getUserID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("ɾ���û�ϵͳ����Ȩ�ޣ��û���ŷǷ�Ϊ0! ");
				}
			}
			if (pFlag) {
				if (userRightSystemFeatures == null) {
					pFlag = false;
					pErrInfo.getContent().append("ɾ���û�ϵͳ����Ȩ�ޣ�ϵͳȨ����Ϣ�Ƿ�Ϊ��! ");
				}
			}
			
			if (pFlag) {
				if (userRightSystemFeatures.size() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("ɾ���û�ϵͳ����Ȩ�ޣ�ϵͳȨ����Ϣ�Ƿ�Ϊ��! ");
				}else {
					for (UserRightSystemFeature userRightSystemFeature2 : userRightSystemFeatures) {
						if (userRightSystemFeature2.getID() <= 0) {
							pFlag = false;
							pErrInfo.getContent().append("ɾ���û�ϵͳ����Ȩ�ޣ�ϵͳȨ�ޱ�ŷǷ�Ϊ0! ");
						}
					}
				}
			}
			
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				for (UserRightSystemFeature userRightSystemFeature2 : userRightSystemFeatures) {
					if (userRightSystemFeatureDao.deleteUserRightSystemFeatureByUserIDAndFeatureID(userInfo, userRightSystemFeature2, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"Ϊָ���û�ɾ��ϵͳ����Ȩ��ʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IUserRightSystemFeatureManageService#findRightSystemFeatureMenusByUserID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRightSystemFeatureMenusByUserID(int pUserID, LinkedHashMap<String, SystemFeature> systemFeatures, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRightSystemFeature(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//����û�����Ƿ�Ƿ�
			if (pFlag)
			{
				if (pUserID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ0");
				}
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (userRightSystemFeatureDao.findMenusByUserID(pUserID, systemFeatures, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡ�û������:"+pUserID+"����ϵͳ���ܲ˵���Ȩʧ��: ");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IUserRightSystemFeatureManageService#findRightSystemFeaturesByUserID(int, java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRightSystemFeaturesByUserID(int pUserID, Map<String, SystemFeature> systemFeatures, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRightSystemFeature(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//����û�����Ƿ�Ƿ�
			if (pFlag)
			{
				if (pUserID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ0");
				}
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (userRightSystemFeatureDao.findByUserID(pUserID, systemFeatures, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡ�û������:"+pUserID+"����ϵͳ������Ȩʧ��: ");
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

	@Override
	public boolean findAllSystemFeature(LinkedHashMap<String, SystemFeature> systemFeatures, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRightSystemFeature(pErrInfo) == false)
			{
				pFlag = false;
			}
			

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (systemFeatureDao.findAllSystemFeature(systemFeatures, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡ����ϵͳ������Ȩʧ��: ");
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

	@Override
	public boolean deleteRightSystemFeaturesByUserID(int pUserID, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (pUserID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ��!");
				}
			}
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (userRightSystemFeatureDao.deleteUserRightSystemFeatureByUserID(pUserID, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"Ϊָ���û�ɾ��ϵͳ����Ȩ��ʧ��: ");
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
