/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IArchivesSecrecyManageService;
import com.orifound.aiim.dal.dao.IArchivesSecrecyDao;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �����ܼ��������ʵ����
 *
 */
public class ArchivesSecrecyManageServiceImpl implements IArchivesSecrecyManageService
{
	
	/**
	 * ���캯��
	 */
	public ArchivesSecrecyManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public ArchivesSecrecyManageServiceImpl(IArchivesSecrecyDao archivesSecrecyDao)
	{
		this.archivesSecrecyDao = archivesSecrecyDao;
	}

	/**
	 * �����ܼ��ֵ������ݷ��ʶ���
	 */
	private IArchivesSecrecyDao archivesSecrecyDao = null;

	/**
	 * ��ȡ����ֵ�������ܼ��ֵ������ݷ��ʶ���
	 * @return �����ܼ��ֵ������ݷ��ʶ���
	 */
	public IArchivesSecrecyDao getArchivesSecrecyDao()
	{
		return archivesSecrecyDao;
	}

	/**
	 * ��������ֵ�������ܼ��ֵ������ݷ��ʶ���
	 * @param archivesSecrecyDao �����ܼ��ֵ������ݷ��ʶ���
	 */
	public void setArchivesSecrecyDao(IArchivesSecrecyDao archivesSecrecyDao)
	{
		this.archivesSecrecyDao = archivesSecrecyDao;
	}
	
	/**
	 * ��鵵���ܼ���DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForArchivesSecrecy(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (archivesSecrecyDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("�����ܼ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IArchivesSecrecyManageService#saveArchivesSecrecy(com.orifound.aiim.entity.ArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveArchivesSecrecy(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesSecrecyManageService#deleteArchivesSecrecy(com.orifound.aiim.entity.ArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteArchivesSecrecy(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesSecrecyManageService#updateArchivesSecrecy(com.orifound.aiim.entity.ArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateArchivesSecrecy(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesSecrecyManageService#findArchivesSecrecys(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesSecrecys(LinkedHashMap<Integer, ArchivesSecrecy> archivesSecrecys, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForArchivesSecrecy(pErrInfo) == false)
			{
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				if (archivesSecrecyDao.findAll(archivesSecrecys, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡ���е����ܼ���Ϣʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IArchivesSecrecyManageService#findArchivesSecrecyByID(int, com.orifound.aiim.entity.ArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesSecrecyByID(int pID, ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findByOpenSecrecy(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForArchivesSecrecy(pErrInfo) == false) {
				pFlag = false;
			}
			
			//�жϵ����ܼ���Ϣ�Ƿ�Ϊ��
			if (pFlag) {
				if (archivesSecrecy == null) {
					pFlag = false;
					pErrInfo.getContent().append("�����ܼ���Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				if (archivesSecrecyDao.findByOpenSecrecy(archivesSecrecy, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ�����ܼ��ĵ����ܼ������ֵ���Ϣ ʧ�ܣ�");
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

}
