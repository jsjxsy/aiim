package com.orifound.aiim.bll.service.impl;

import java.util.Map;

import com.orifound.aiim.bll.service.ISystemFeatureManageService;
import com.orifound.aiim.dal.dao.ISystemFeatureDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;

public class SystemFeatureManageServiceImpl implements ISystemFeatureManageService {
	/**
	 * ���캯��
	 */
	public SystemFeatureManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public SystemFeatureManageServiceImpl(ISystemFeatureDao systemFeatureMDao) {
		this.systemFeatureDao = systemFeatureMDao;
	}
	
	/**
	 * SystemFeature������ݷ��ʶ���
	 */
	private ISystemFeatureDao systemFeatureDao = null;

	/**
	 * ��ȡ����ֵ��SystemFeature������ݷ��ʶ���
	 * @return SystemFeature������ݷ��ʶ���
	 */
	public ISystemFeatureDao getSystemFeatureDao() {
		return systemFeatureDao;
	}

	/**
	 * ��������ֵ��SystemFeature������ݷ��ʶ���
	 * @param ystemFeatureDao SystemFeature������ݷ��ʶ���
	 */
	public void setSystemFeatureDao(ISystemFeatureDao systemFeatureDao) {
		this.systemFeatureDao = systemFeatureDao;
	}
	
	/**
	 * ���SystemFeature��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForSystemFeature(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (systemFeatureDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("SystemFeature��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	public boolean deleteSystemFeature(SystemFeature pSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
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
	public boolean findSystemFeatureByID(int pID, SystemFeature pSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findSystemFeatures(Map<String,SystemFeature> pSystemFeatures, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForSystemFeature(pErrInfo) == false)
			{
				pFlag = false;
			}
			

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (systemFeatureDao.findAllSystemFeature(pSystemFeatures, pErrInfo)==false)
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
	public boolean saveSystemFeature(SystemFeature pSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateSystemFeature(SystemFeature pSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
