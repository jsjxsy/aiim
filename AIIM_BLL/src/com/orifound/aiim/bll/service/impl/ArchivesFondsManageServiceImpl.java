/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IArchivesFondsManageService;
import com.orifound.aiim.dal.dao.IArchivesFondsDao;
import com.orifound.aiim.entity.ArchivesFonds;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ȫ�ڹ������ʵ����
 *
 */
public class ArchivesFondsManageServiceImpl implements IArchivesFondsManageService
{
	/**
	 * ���캯��
	 */
	public ArchivesFondsManageServiceImpl()
	{
	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public ArchivesFondsManageServiceImpl(IArchivesFondsDao archivesFondsDao)
	{
		this.archivesFondsDao = archivesFondsDao;
	}
	
	/**
	 * DD_ArchivesFonds������ݷ��ʶ���
	 */
	private IArchivesFondsDao archivesFondsDao = null;

	/**
	 * ��ȡ����ֵ��DD_ArchivesFonds������ݷ��ʶ���
	 * @return DD_ArchivesFonds������ݷ��ʶ���
	 */
	public IArchivesFondsDao getArchivesFondsDao()
	{
		return archivesFondsDao;
	}

	/**
	 * ��������ֵ��DD_ArchivesFonds������ݷ��ʶ���
	 * @param archivesFondsDao DD_ArchivesFonds������ݷ��ʶ���
	 */
	public void setArchivesFondsDao(IArchivesFondsDao archivesFondsDao)
	{
		this.archivesFondsDao = archivesFondsDao;
	}
	
	/**
	 * ��鵵��ȫ����Ϣ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForArchivesFonds(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (archivesFondsDao==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("����ȫ����Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.ISystemManageService#findArchivesFondss(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesFondss(List<ArchivesFonds> archivesFondss, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO����ע��
			pErrPos = 1;
			if (checkDaoInjectionForArchivesFonds(pErrInfo)==false)
			{
				pFlag=false;
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				if (archivesFondsDao.findAll(archivesFondss, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ѯ����ȫ����Ϣʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.ISystemManageService#findArchivesFondsByID(java.lang.String, com.orifound.aiim.entity.ArchivesFonds, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesFondsByID(int pID, ArchivesFonds archivesFonds, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO����ע��
			pErrPos = 1;
			if (checkDaoInjectionForArchivesFonds(pErrInfo)==false)
			{
				pFlag=false;
			}
			
			//���ID�Ƿ�ֵ
			if (pFlag)
			{
				pErrPos = 2;
				if (pID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("ȫ�����ֱ�ŷǷ�Ϊ�ա�");
				}
			}
			
			//����DAO�ӿ�
			if (pFlag)
			{
				if (archivesFondsDao.findByID(pID, archivesFonds, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ѯָ��ȫ��("+pID+")��Ϣʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.ISystemManageService#saveArchivesFonds(com.orifound.aiim.entity.ArchivesFonds, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveArchivesFonds(ArchivesFonds archivesFonds, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO����ע��
			pErrPos = 1;
			if (checkDaoInjectionForArchivesFonds(pErrInfo)==false)
			{
				pFlag=false;
			}
			
			// ���ȫ�ڱ���Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (archivesFonds.getCode() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("ȫ�ںŷǷ�Ϊ�ա�");
				}
				else
				{
					if (archivesFonds.getCode().trim().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("ȫ�ںŷǷ�Ϊ�ա�");
					}
				}
			}
			
			//���ȫ�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos =2;
				if (archivesFonds.getName() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("ȫ�����ƷǷ�Ϊ�ա�");
				}
				else
				{
					if (archivesFonds.getName().trim().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("ȫ�����ƷǷ�Ϊ�ա�");
					}
				}
			}
			
			//����DAO�ӿ�
			if (pFlag)
			{
				if (archivesFondsDao.save(archivesFonds, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("����ȫ����Ϣʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.ISystemManageService#deleteArchivesFonds(com.orifound.aiim.entity.ArchivesFonds, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteArchivesFonds(ArchivesFonds archivesFonds, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO����ע��
			pErrPos = 1;
			if (checkDaoInjectionForArchivesFonds(pErrInfo)==false)
			{
				pFlag=false;
			}
			
			//���ȫ�ڱ���Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesFonds.getID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("ȫ�����ֱ�ŷǷ�Ϊ�ա�");
				}
			}
			
			//����DAO�ӿ�
			if (pFlag)
			{
				if (archivesFondsDao.delete(archivesFonds, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"ɾ��ȫ����Ϣʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.ISystemManageService#updateArchivesFonds(com.orifound.aiim.entity.ArchivesFonds, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateArchivesFonds(ArchivesFonds archivesFonds, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO����ע��
			pErrPos = 1;
			if (checkDaoInjectionForArchivesFonds(pErrInfo)==false)
			{
				pFlag=false;
			}
			
			// ���ȫ�ڱ���Ƿ��и�ֵ
			{
				pErrPos = 2;
				if (archivesFonds.getID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("ȫ�����ֱ�ŷǷ�Ϊ�ա�");
				}
			}
			
			//���ȫ�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos =2;
				if (archivesFonds.getName() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("ȫ�����ƷǷ�Ϊ�ա�");
				}
				else
				{
					if (archivesFonds.getName().trim().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("ȫ�����ƷǷ�Ϊ�ա�");
					}
				}
			}
			
			//����DAO�ӿ�
			if (pFlag)
			{
				if (archivesFondsDao.update(archivesFonds, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"����ȫ����Ϣʧ��: ");
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

}
