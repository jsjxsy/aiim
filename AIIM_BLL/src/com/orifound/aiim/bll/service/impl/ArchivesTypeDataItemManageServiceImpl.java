/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IArchivesTypeDataItemManageService;
import com.orifound.aiim.dal.dao.IArchivesTypeDataItemDao;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ��������������������ʵ����
 *
 */
public class ArchivesTypeDataItemManageServiceImpl implements IArchivesTypeDataItemManageService
{
	/**
	 * ���캯��
	 */
	public ArchivesTypeDataItemManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public ArchivesTypeDataItemManageServiceImpl(IArchivesTypeDataItemDao archivesTypeDataItemDao)
	{
		this.archivesTypeDataItemDao = archivesTypeDataItemDao;
	}
	
	/**
	 * ���������������ֵ������ݷ��ʶ���
	 */
	private IArchivesTypeDataItemDao archivesTypeDataItemDao = null;

	/**
	 * ��ȡ����ֵ�����������������ֵ������ݷ��ʶ���
	 * @return ���������������ֵ������ݷ��ʶ���
	 */
	public IArchivesTypeDataItemDao getArchivesTypeDataItemDao()
	{
		return archivesTypeDataItemDao;
	}

	/**
	 * ��������ֵ�����������������ֵ������ݷ��ʶ���
	 * @param archivesTypeDataItemDao ���������������ֵ������ݷ��ʶ���
	 */
	public void setArchivesTypeDataItemDao(IArchivesTypeDataItemDao archivesTypeDataItemDao)
	{
		this.archivesTypeDataItemDao = archivesTypeDataItemDao;
	}
	
	/**
	 * ��鵵�������������DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForArchivesTypeDataItem(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (archivesTypeDataItemDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("���������������DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IArchivesTypeDataItemManageService#saveArchivesTypeDataItem(com.orifound.aiim.entity.ArchivesTypeDataItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveArchivesTypeDataItem(ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesTypeDataItemManageService#deleteArchivesTypeDataItem(com.orifound.aiim.entity.ArchivesTypeDataItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteArchivesTypeDataItem(ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesTypeDataItemManageService#updateArchivesTypeDataItem(com.orifound.aiim.entity.ArchivesTypeDataItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateArchivesTypeDataItem(ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesTypeDataItemManageService#findByArchivesTypeID(Boolean, int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByArchivesTypeID(Boolean officialArchivesFlag,int pArchivesTypeID, LinkedHashMap<String, ArchivesTypeDataItem> archivesTypeDataItems, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForArchivesTypeDataItem(pErrInfo) == false)
			{
				pFlag = false;
			}

			//����Ƿ�ָ���˵���������
			if (pFlag)
			{
				if (pArchivesTypeID==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
				}
			}
			
			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesTypeDataItemDao.findByArchivesTypeID(officialArchivesFlag,pArchivesTypeID, archivesTypeDataItems, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡ�������ࣨ��ţ�"+pArchivesTypeID+"���¶������������Ϣʧ��: ");
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

		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesTypeDataItemManageService#findArchivesTypeDataItemByID(int, com.orifound.aiim.entity.ArchivesTypeDataItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesTypeDataItemByID(int pID, ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
