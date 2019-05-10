package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.IArchivesUseWayManageService;
import com.orifound.aiim.dal.dao.IArchivesUseWayDao;
import com.orifound.aiim.entity.ArchivesUseWay;
import com.orifound.aiim.entity.ErrInfo;
/**
 * �������÷�ʽ�ֵ��������ʵ����
 * @author Administrator
 *
 */
public class ArchivesUseWayManageServiceImpl implements IArchivesUseWayManageService {
	
	/**
	 * ���캯��
	 */
	public ArchivesUseWayManageServiceImpl() {

	}

	/**
	 * �������÷�ʽ�ֵ��DAO
	 */
	private IArchivesUseWayDao archivesUseWayDao = null;	
	
	public IArchivesUseWayDao getArchivesUseWayDao() {
		return archivesUseWayDao;
	}

	public void setArchivesUseWayDao(IArchivesUseWayDao archivesUseWayDao) {
		this.archivesUseWayDao = archivesUseWayDao;
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
			if (archivesUseWayDao==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("�������÷�ʽ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	public boolean findAllArchivesUseWay(List<ArchivesUseWay> archivesUseWays, ErrInfo pErrInfo) {
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
				if (archivesUseWayDao.findAll(archivesUseWays, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ���е������÷�ʽʧ�ܣ� ");
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
	public boolean findArchivesUseWayByID(ArchivesUseWay archivesUseWay, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
