/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IArchivesInfoTableManageService;
import com.orifound.aiim.dal.dao.IArchivesInfoTableDao;
import com.orifound.aiim.entity.ArchivesInfoTable;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ������Ϣ��ر�������ʵ����
 *
 */
public class ArchivesInfoTableManageServiceImpl implements IArchivesInfoTableManageService
{
	/**
	 * ���캯��
	 */
	public ArchivesInfoTableManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public ArchivesInfoTableManageServiceImpl(IArchivesInfoTableDao archivesInfoTableDao)
	{
		this.archivesInfoTableDao = archivesInfoTableDao;
	}
	
	/**
	 * DD_ArchivesInfoTable������ݷ��ʶ���
	 */
	private IArchivesInfoTableDao archivesInfoTableDao = null;

	/**
	 * ��ȡ����ֵ��DD_ArchivesInfoTable������ݷ��ʶ���
	 * @return DD_ArchivesInfoTable������ݷ��ʶ���
	 */
	public IArchivesInfoTableDao getArchivesInfoTableDao()
	{
		return archivesInfoTableDao;
	}

	/**
	 * ��������ֵ��DD_ArchivesInfoTable������ݷ��ʶ���
	 * @param archivesInfoTableDao DD_ArchivesInfoTable������ݷ��ʶ���
	 */
	public void setArchivesInfoTableDao(IArchivesInfoTableDao archivesInfoTableDao)
	{
		this.archivesInfoTableDao = archivesInfoTableDao;
	}
	
	
	/**
	 * ��鵵����Ϣ��ر����ֵ���DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForArchivesInfoTable(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (archivesInfoTableDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ��ر����ֵ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoTableManageService#findArchivesInfoTableByID(int, com.orifound.aiim.entity.ArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesInfoTableByID(int pID, ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoTableManageService#findArchivesTypeTables(int, java.util.EnumMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesTypeTables(int pArchivesTypeID, EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable> archivesInfoTables, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForArchivesInfoTable(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��鵵���������Ƿ�Ƿ�Ϊ��
			if (pFlag)
			{
				pErrPos = 2;
				if (pArchivesTypeID==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������ŷǷ�Ϊ0");
				}
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 3;
				if (archivesInfoTableDao.findByArchivesTypeID(pArchivesTypeID, archivesInfoTables, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡ�������������Ϣ��ʧ��: ");
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
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoTableManageService#saveArchivesInfoTable(com.orifound.aiim.entity.ArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveArchivesInfoTable(ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoTableManageService#setCreatedFlag(com.orifound.aiim.entity.ArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean setCreatedFlag(ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoTableManageService#deleteArchivesTypeTables(int, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteArchivesTypeTables(int pArchivesTypeID, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoTableManageService#updateArchivesInfoTable(com.orifound.aiim.entity.ArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateArchivesInfoTable(ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
