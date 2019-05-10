/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.IArchivesInfoWorkProcedureManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.dal.dao.IArchivesInfoWorkProcedureDao;
import com.orifound.aiim.entity.ArchivesInfoWorkProcedure;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.EnumArchivesInfoType;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ��������������Ϣ�������ʵ����
 *
 */
public class ArchivesInfoWorkProcedureManageServiceImpl implements IArchivesInfoWorkProcedureManageService
{
	/**
	 * ���캯��
	 */
	public ArchivesInfoWorkProcedureManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public ArchivesInfoWorkProcedureManageServiceImpl(IArchivesInfoWorkProcedureDao archivesInfoWorkProcedureDao)
	{
		this.archivesInfoWorkProcedureDao = archivesInfoWorkProcedureDao;
	}
	
	/**
	 * ��������������Ϣ������ݷ��ʶ���
	 */
	private IArchivesInfoWorkProcedureDao archivesInfoWorkProcedureDao = null;

	/**
	 * ��ȡ����ֵ����������������Ϣ������ݷ��ʶ���
	 * @return ��������������Ϣ������ݷ��ʶ���
	 */
	public IArchivesInfoWorkProcedureDao getArchivesInfoWorkProcedureDao()
	{
		return archivesInfoWorkProcedureDao;
	}

	/**
	 * ��������ֵ����������������Ϣ������ݷ��ʶ���
	 * @param archiveInfoWorkProcedureDao ��������������Ϣ������ݷ��ʶ���
	 */
	public void setArchivesInfoWorkProcedureDao(IArchivesInfoWorkProcedureDao archivesInfoWorkProcedureDao)
	{
		this.archivesInfoWorkProcedureDao = archivesInfoWorkProcedureDao;
	}
	
	
	
	/**
	 * ��鵵������������Ϣ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForArchivesInfoWorkProcedure(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (archivesInfoWorkProcedureDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("��������������Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkProcedureManageService#saveArchivesInfoWorkProcedure(com.orifound.aiim.entity.ArchivesInfoWorkProcedure, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveArchivesInfoWorkProcedure(ArchivesInfoWorkProcedure archivesInfoWorkProcedure, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ArchivesType archivesType=null;//��������������Ϣ

		try
		{
			pErrPos = 1;
			//����Ƿ��н��е�������������Ϣ��BLL����ע��
			if (checkDaoInjectionForArchivesInfoWorkProcedure(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��鵵������������Ϣ��������
			if (pFlag)
			{
				if (archivesInfoWorkProcedure==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("��������������Ϣ�Ƿ�Ϊ�ա�");
				}
				else
				{
					if (archivesInfoWorkProcedure.getNBXH()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("��������������Ϣ���ڲ���ŷǷ�Ϊ0");
					}
					else if (archivesInfoWorkProcedure.getArchivesTypeID()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("��������������Ϣ�ĵ��������ŷǷ�Ϊ0");
					}
					else if (archivesInfoWorkProcedure.getUserID()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("��������������Ϣ���û���ŷǷ�Ϊ0");
					}
					else if (archivesInfoWorkProcedure.getWorkFlowStatus()==EnumWorkFlowStatus.NONE)
					{
						pFlag = false;
						pErrInfo.getContent().append("��������������Ϣ�Ĺ�����״̬�Ƿ�Ϊ0");
					}
				}
			}
			
			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.checkArchivesType(archivesInfoWorkProcedure.getArchivesTypeID(), pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���������źϷ��Լ��ʧ��: ");
				}
				else
				{
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesInfoWorkProcedure.getArchivesTypeID());
				}
			}

			//����DAO�ӿڱ��浱ǰ�����鵵������Ϣ
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesInfoWorkProcedureDao.save(archivesType,archivesInfoWorkProcedure, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���浵���鵵������Ϣʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkProcedureManageService#findArchivesInfoWorkProcedureByNBXH(com.orifound.aiim.entity.ArchivesType,int, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesInfoWorkProcedureByNBXH(ArchivesType archivesType,int pNBXH, List<ArchivesInfoWorkProcedure> archivesInfoWorkProcedures, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н��е�������������Ϣ��BLL����ע��
			if (checkDaoInjectionForArchivesInfoWorkProcedure(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//����ڲ�����Ƿ�Ϊ0
			if (pFlag)
			{
				if (pNBXH<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�����ڲ���ŷǷ�Ϊ0");
				}
			}
			
			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 2;
				if (CommonUtil.checkArchivesType(archivesType.getID(), pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���������źϷ��Լ��ʧ��: ");
				}
				else
				{
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}

			//����DAO�ӿڱ��浱ǰ�����鵵������Ϣ
			if (pFlag)
			{
				pErrPos = 3;
				if (archivesInfoWorkProcedureDao.findByNBXH(archivesType, pNBXH, archivesInfoWorkProcedures, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���浵���鵵������Ϣʧ��: ");
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
	public boolean deleteArchivesInfoWorkProcedure(EnumArchivesInfoType enumArchivesInfoType,ArchivesInfoWorkProcedure archivesInfoWorkProcedure, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ArchivesType archivesType=null;//��������������Ϣ

		try
		{
			pErrPos = 1;
			//����Ƿ��н��е�������������Ϣ��BLL����ע��
			if (checkDaoInjectionForArchivesInfoWorkProcedure(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��鵵����Ϣ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (enumArchivesInfoType==EnumArchivesInfoType.NONE)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ����Ƿ�Ϊ�ա�");
				}
			}
			
			//��鵵������������Ϣ��������
			if (pFlag)
			{
				if (archivesInfoWorkProcedure==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("��������������Ϣ�Ƿ�Ϊ�ա�");
				}
				else
				{
					if (archivesInfoWorkProcedure.getNBXH()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("��������������Ϣ���ڲ���ŷǷ�Ϊ0");
					}
					else if (archivesInfoWorkProcedure.getArchivesTypeID()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("��������������Ϣ�ĵ��������ŷǷ�Ϊ0");
					}
				}
			}
			
			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 2;
				if (CommonUtil.checkArchivesType(archivesInfoWorkProcedure.getArchivesTypeID(), pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���������źϷ��Լ��ʧ��: ");
				}
				else
				{
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesInfoWorkProcedure.getArchivesTypeID());
				}
			}

			//����DAO�ӿڽ���ɾ������
			if (pFlag)
			{
				//����ǰ�����������ð�����ͬ�����ļ�����������Ϣһ��ɾ����DAO�ӿ�
				pErrPos = 3;
				if (enumArchivesInfoType==EnumArchivesInfoType.��������)
				{
					if (archivesInfoWorkProcedureDao.deleteForParentArchives(archivesType, archivesInfoWorkProcedure, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "ɾ���������Ĺ鵵������Ϣʧ��: ");
					}
				}
				//������ļ��������������ɾ����һ�ļ��Ĺ���������Ϣ��DAO�ӿ�
				else
				{
					if (archivesInfoWorkProcedureDao.deleteForSingleArchives(archivesType, archivesInfoWorkProcedure, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "ɾ���ļ������Ĺ鵵������Ϣʧ��: ");
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
}