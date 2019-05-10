/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.LinkedHashMap;

import com.orifound.aiim.bll.service.IOfficialArchivesTypeManageService;
import com.orifound.aiim.dal.dao.IOfficialArchivesTypeDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesType;

/**
 * ���ĵ���������Ϣ�������
 *
 */
public class OfficialArchivesTypeManageServiceImpl implements IOfficialArchivesTypeManageService
{
	
	/**
	 * ���캯��
	 */
	public OfficialArchivesTypeManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public OfficialArchivesTypeManageServiceImpl(IOfficialArchivesTypeDao officialArchivesTypeDao)
	{
		this.officialArchivesTypeDao = officialArchivesTypeDao;
	}
	
	/**
	 * ���ĵ���������Ϣ������ݷ��ʶ���
	 */
	private IOfficialArchivesTypeDao officialArchivesTypeDao = null;

	/**
	 * ��ȡ����ֵ�����ĵ���������Ϣ������ݷ��ʶ���
	 * @return ���ĵ���������Ϣ������ݷ��ʶ���
	 */
	public IOfficialArchivesTypeDao getOfficialArchivesTypeDao()
	{
		return officialArchivesTypeDao;
	}

	/**
	 * ��������ֵ�����ĵ���������Ϣ������ݷ��ʶ���
	 * @param officialArchivesTypeDao ���ĵ���������Ϣ������ݷ��ʶ���
	 */
	public void setOfficialArchivesTypeDao(IOfficialArchivesTypeDao officialArchivesTypeDao)
	{
		this.officialArchivesTypeDao = officialArchivesTypeDao;
	}
	
	/**
	 * ��鹫�ĵ��������ֵ���DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjection(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (officialArchivesTypeDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("���ĵ��������ֵ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IOfficialArchivesTypeManageService#saveOfficialArchivesType(com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveOfficialArchivesType(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��鵵�����������Ƿ�Ϊ��
			if (pFlag)
			{
				if (officialArchivesType.getName()==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ĵ����������ƷǷ�Ϊ�ա�");
				}
				else 
				{
					if (officialArchivesType.getName().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���ĵ����������ƷǷ�Ϊ�ա�");
					}
				}
			}
			
			//��鵵����������Ƿ�Ϊ��
			if (pFlag)
			{
				if (officialArchivesType.getCode()==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ĵ����������Ƿ�Ϊ�ա�");
				}
				else 
				{
					if (officialArchivesType.getCode().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���ĵ����������Ƿ�Ϊ�ա�");
					}
				}
			}
			
			//��鵵������ԭ�Ĵ洢·���Ƿ�Ϊ��
			if (pFlag)
			{
				if (officialArchivesType.getAttachedFileSavePath()==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ĵ�������ԭ�Ĵ洢·���Ƿ�Ϊ�ա�");
				}
				else 
				{
					if (officialArchivesType.getAttachedFileSavePath().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���ĵ�������ԭ�Ĵ洢·���Ƿ�Ϊ�ա�");
					}
				}
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (officialArchivesTypeDao.save(officialArchivesType, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ĵ���������Ϣ����ʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IOfficialArchivesTypeManageService#deleteOfficialArchivesType(com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteOfficialArchivesType(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IOfficialArchivesTypeManageService#updateOfficialArchivesType(com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateOfficialArchivesType(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IOfficialArchivesTypeManageService#findOfficialArchivesTypes(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findOfficialArchivesTypes(LinkedHashMap<Integer, OfficialArchivesType> officialArchivesTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (officialArchivesTypeDao.findAll(officialArchivesTypes, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ���ĵ���������Ϣʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IOfficialArchivesTypeManageService#findOfficialArchivesTypeByID(int, com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findOfficialArchivesTypeByID(int pOfficialArchivesTypeID, OfficialArchivesType officialArchivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
