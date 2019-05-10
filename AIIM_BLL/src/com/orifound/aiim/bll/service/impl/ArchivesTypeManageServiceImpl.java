/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IArchivesTypeManageService;
import com.orifound.aiim.dal.dao.IArchivesTypeDao;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����������Ϣ�������ʵ����
 *
 */
public class ArchivesTypeManageServiceImpl implements IArchivesTypeManageService
{
	/**
	 * ���캯��
	 */
	public ArchivesTypeManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public ArchivesTypeManageServiceImpl(IArchivesTypeDao archivesTypeDao)
	{
		this.archivesTypeDao = archivesTypeDao;
	}
	
	/**
	 * DD_ArchivesType������ݷ��ʶ���
	 */
	private IArchivesTypeDao archivesTypeDao = null;

	/**
	 * ��ȡ����ֵ��DD_ArchivesType������ݷ��ʶ���
	 * @return DD_ArchivesType������ݷ��ʶ���
	 */
	public IArchivesTypeDao getArchivesTypeDao()
	{
		return archivesTypeDao;
	}

	/**
	 * ��������ֵ��DD_ArchivesType������ݷ��ʶ���
	 * @param archivesTypeDao DD_ArchivesType������ݷ��ʶ���
	 */
	public void setArchivesTypeDao(IArchivesTypeDao archivesTypeDao)
	{
		this.archivesTypeDao = archivesTypeDao;
	}
	
	
	/**
	 * ��鵵��������Ϣ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForArchivesType(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (archivesTypeDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IArchivesTypeManageService#saveArchivesType(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveArchivesType(ArchivesType archivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesTypeManageService#deleteArchivesType(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteArchivesType(ArchivesType archivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesTypeManageService#updateArchivesType(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateArchivesType(ArchivesType archivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesTypeManageService#findArchivesTypes(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesTypes(LinkedHashMap<Integer, ArchivesType> archivesTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForArchivesType(pErrInfo) == false)
			{
				pFlag = false;
			}

			//����DAO�ӿڣ��Ȼ�ȡһ����Ŀ��Ϣ
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesTypeDao.findForLevel1(archivesTypes, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡ���㵵�����ࣨһ����Ŀ����Ϣʧ��: ");
				}
			}
			
			//����DAO�ӿڣ��ݹ��ȡÿ��һ����Ŀ���¼�����
			if (pFlag)
			{
				for (ArchivesType item : archivesTypes.values())
				{
					pErrPos = 3;
					LinkedHashMap<Integer, ArchivesType> childArchivesTypes = new LinkedHashMap<Integer, ArchivesType>();
					if (archivesTypeDao.findForChild(item.getID(), childArchivesTypes, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0,"��ȡ "+item.getFullCode()+" ���¼���������ʧ�ܣ� ");
						break;
					}
					
					//���ҳɹ���ҽӵ���ǰһ����Ŀ��
					if (pFlag)
					{
						pErrPos = 4;
						if (childArchivesTypes.size()>0)
						{
							item.setChildArchivesTypes(childArchivesTypes);
						}
					}
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
	 * @see com.orifound.aiim.bll.service.IArchivesTypeManageService#findArchivesTypeByID(int, com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesTypeByID(int pID, ArchivesType archivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
