package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.IArchivesUsePurposeManageService;
import com.orifound.aiim.dal.dao.IArchivesUsePurposeDao;
import com.orifound.aiim.entity.ArchivesUsePurpose;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ��������Ŀ��ҵ���߼���ʵ����
 * @author Administrator
 *
 */
public class ArchivesUsePurposeManageServiceImpl implements IArchivesUsePurposeManageService {

	/**
	 * ��������Ŀ�ı�����ݷ��ʶ���
	 */
	private IArchivesUsePurposeDao archivesUsePurposeDao = null;

	/**
	 * ��ȡ����ֵ��TableName������ݷ��ʶ���
	 * @return TableName������ݷ��ʶ���
	 */
	public IArchivesUsePurposeDao getArchivesUsePurposeDao() {
		return archivesUsePurposeDao;
	}

	/**
	 * ��������ֵ��TableName������ݷ��ʶ���
	 * @param entityDao TableName������ݷ��ʶ���
	 */
	public void setArchivesUsePurposeDao(IArchivesUsePurposeDao archivesUsePurposeDao) {
		this.archivesUsePurposeDao = archivesUsePurposeDao;
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
		
		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (archivesUsePurposeDao==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("��������Ŀ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	
	
	@Override
	public boolean findAllArchivesUsePurpose(List<ArchivesUsePurpose> archivesUsePurposes, ErrInfo pErrInfo) {
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
				if (archivesUsePurposeDao.findAll(archivesUsePurposes, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���е�������Ŀ��ʧ��: ");
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
	public boolean findByID(ArchivesUsePurpose archivesUsePurpose, ErrInfo pErrInfo) {
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
				if (archivesUsePurposeDao.findByID( archivesUsePurpose, pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "ͨ��ID��������Ŀ��ʧ��: ");
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
