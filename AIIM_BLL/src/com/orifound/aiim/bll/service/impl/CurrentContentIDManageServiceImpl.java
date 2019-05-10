/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.ICurrentContentIDManageService;
import com.orifound.aiim.dal.dao.ICurrentContentIDDao;
import com.orifound.aiim.entity.CurrentContentID;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ��ǰ�������Ϣ�������ʵ����
 *
 */
public class CurrentContentIDManageServiceImpl implements ICurrentContentIDManageService
{
	
	/**
	 * ���캯��
	 */
	public CurrentContentIDManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public CurrentContentIDManageServiceImpl(ICurrentContentIDDao currentContentIDDao)
	{
		this.currentContentIDDao = currentContentIDDao;
	}
	
	/**
	 * ��ǰ�������Ϣ������ݷ��ʶ���
	 */
	private ICurrentContentIDDao currentContentIDDao = null;

	/**
	 * ��ȡ����ֵ����ǰ�������Ϣ������ݷ��ʶ���
	 * @return ��ǰ�������Ϣ������ݷ��ʶ���
	 */
	public ICurrentContentIDDao getCurrentContentIDDao()
	{
		return currentContentIDDao;
	}

	/**
	 * ��������ֵ����ǰ�������Ϣ������ݷ��ʶ���
	 * @param currentContentIDDao ��ǰ�������Ϣ������ݷ��ʶ���
	 */
	public void setCurrentContentIDDao(ICurrentContentIDDao currentContentIDDao)
	{
		this.currentContentIDDao = currentContentIDDao;
	}
	
	/**
	 * ��鵱ǰ�������Ϣ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForCurrentContentID(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (currentContentIDDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("��ǰ�������Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
				if (pErrInfo.getException() != null)
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
	 * @see com.orifound.aiim.bll.service.ICurrentContentIDManageService#saveCurrentContentID(com.orifound.aiim.entity.CurrentContentID, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveCurrentContentID(CurrentContentID currentContentID, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForCurrentContentID(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			
			//��鵱ǰ�������Ϣ��������
			if (pFlag)
			{
				if (checkCurrentContentID(currentContentID, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ǰ�������Ϣ������: ");
				}
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (currentContentIDDao.save(currentContentID, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���浱ǰ�������Ϣʧ��: ");
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
				if (pErrInfo.getException() != null)
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
	
	/**
	 * ��鵱ǰ�������Ϣ��������
	 * @param currentContentID ��ǰ�������Ϣ
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkCurrentContentID(CurrentContentID currentContentID, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//��鵱ǰ�������Ϣ�Ƿ�Ϊ��
			pErrPos = 1;
			if (currentContentID==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("��ǰ�������Ϣ�Ƿ�Ϊ�ա�");
			}

			//��鵵��ȫ�ڱ���Ƿ�Ϊ��
			if (pFlag)
			{
				if (currentContentID.getArchivesIDPrefix()==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����ȫ�ڱ�ŷǷ�Ϊ�ա�");
				}
				else 
				{
					if (currentContentID.getArchivesIDPrefix().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("����ȫ�ڱ�ŷǷ�Ϊ�ա�");
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
				if (pErrInfo.getException() != null)
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
	 * @see com.orifound.aiim.bll.service.ICurrentContentIDManageService#updateCurrentContentID(com.orifound.aiim.entity.CurrentContentID, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateCurrentContentID(CurrentContentID currentContentID, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForCurrentContentID(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			
			//��鵱ǰ�������Ϣ��������
			if (pFlag)
			{
				if (checkCurrentContentID(currentContentID, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ǰ�������Ϣ������: ");
				}
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (currentContentIDDao.update(currentContentID, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���µ�ǰ�������Ϣʧ��: ");
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
				if (pErrInfo.getException() != null)
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
	 * @see com.orifound.aiim.bll.service.ICurrentContentIDManageService#findCurrentContentIDByID(java.lang.String, int, com.orifound.aiim.entity.CurrentContentID, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findCurrentContentIDByPrefix(String archivesIDPrefix,  CurrentContentID currentContentID, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForCurrentContentID(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��鵵��ȫ�ڱ���Ƿ�Ϊ��
			if (pFlag)
			{
				if (archivesIDPrefix==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����ȫ�ڱ�ŷǷ�Ϊ�ա�");
				}
				else 
				{
					if (archivesIDPrefix.length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("����ȫ�ڱ�ŷǷ�Ϊ�ա�");
					}
				}
			}
			
			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (currentContentIDDao.findByPrefix(archivesIDPrefix, currentContentID, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ��ǰ�������Ϣʧ��: ");
				}
			}
			
			if (pFlag) {
				if (currentContentID.getContentID() == 0) {
					currentContentID.setArchivesIDPrefix(archivesIDPrefix);
					currentContentID.setContentID(1);
					if (currentContentIDDao.save(currentContentID, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "�����������Ϣʧ��: ");
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
				if (pErrInfo.getException() != null)
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
	public boolean findAll(List<CurrentContentID> currentContentIDs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForCurrentContentID(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			if (pFlag) {
				if (currentContentIDDao.findAll(currentContentIDs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ�������Ϣʧ��: ");
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
				if (pErrInfo.getException() != null)
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

}
