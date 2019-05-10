package com.orifound.aiim.bll.service.impl;

import com.orifound.aiim.bll.service.IAttachedFileUseRequestPassInfoManageService;
import com.orifound.aiim.dal.dao.IAttachedFileUseRequestPassInfoDao;
import com.orifound.aiim.entity.AttachedFileUseRequestPassInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;

/**
 * ԭ����������ͨ����Ϣҵ���߼�ʵ����
 * @author Administrator
 *
 */
public class AttachedFileUseRequestPassInfoManageServiceImpl implements IAttachedFileUseRequestPassInfoManageService {
	/**
	 * ���캯��
	 */
	public AttachedFileUseRequestPassInfoManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public AttachedFileUseRequestPassInfoManageServiceImpl(IAttachedFileUseRequestPassInfoDao attachedFileUseRequestPassInfoDao) {
		this.attachedFileUseRequestPassInfoDao = attachedFileUseRequestPassInfoDao;
	}
	
	/**
	 * ԭ����������ͨ����Ϣ��DAO�ӿ�
	 */
	private IAttachedFileUseRequestPassInfoDao attachedFileUseRequestPassInfoDao = null;	

	public IAttachedFileUseRequestPassInfoDao getAttachedFileUseRequestPassInfoDao() {
		return attachedFileUseRequestPassInfoDao;
	}

	public void setAttachedFileUseRequestPassInfoDao(IAttachedFileUseRequestPassInfoDao attachedFileUseRequestPassInfoDao) {
		this.attachedFileUseRequestPassInfoDao = attachedFileUseRequestPassInfoDao;
	}
	
	/**
	 * ���ҵ�����������ݷ������Ƿ�ע��ɹ���DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkInjection(ErrInfo pErrInfo) {

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (attachedFileUseRequestPassInfoDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("ԭ����������ͨ����Ϣ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	public boolean addAttachedFileUseRequestPassInfo(AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag) {
				if (attachedFileUseRequestPassInfoDao.add(attachedFileUseRequestPassInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ԭ��������ϸ��Ϣʧ��: ");
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
	public boolean deleteAttachedFileUseRequestPassInfo(AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findAttachedFileUseRequestPassInfoByID(AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findCountByRequestPassInfo(int pUserID, int pArchivesTypeID, int pNBXH, IntegerEx pCount, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
