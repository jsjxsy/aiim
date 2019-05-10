/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.LinkedHashMap;

import com.orifound.aiim.bll.service.IOfficialDocTypeManageService;
import com.orifound.aiim.dal.dao.IOfficialDocTypeDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialDocType;

/**
 * ����������Ϣ�������ʵ����
 *
 */
public class OfficialDocTypeManageServiceImpl implements IOfficialDocTypeManageService
{
	/**
	 * ���캯��
	 */
	public OfficialDocTypeManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public OfficialDocTypeManageServiceImpl(IOfficialDocTypeDao officialDocTypeDao)
	{
		this.officialDocTypeDao = officialDocTypeDao;
	}
	
	/**
	 * ���������ֵ������ݷ��ʶ���
	 */
	private IOfficialDocTypeDao officialDocTypeDao = null;

	/**
	 * ��ȡ����ֵ�����������ֵ������ݷ��ʶ���
	 * @return ���������ֵ������ݷ��ʶ���
	 */
	public IOfficialDocTypeDao getOfficialDocTypeDao()
	{
		return officialDocTypeDao;
	}

	/**
	 * ��������ֵ�����������ֵ������ݷ��ʶ���
	 * @param officialDocTypeDao ���������ֵ������ݷ��ʶ���
	 */
	public void setOfficialDocTypeDao(IOfficialDocTypeDao officialDocTypeDao)
	{
		this.officialDocTypeDao = officialDocTypeDao;
	}
	
	/**
	 * ��鹫��������Ϣ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForOfficialDocType(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (officialDocTypeDao == null)
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
	 * @see com.orifound.aiim.bll.service.IOfficialDocTypeManageService#saveOfficialDocType(com.orifound.aiim.entity.OfficialDocType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveOfficialDocType(OfficialDocType officialDocType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IOfficialDocTypeManageService#deleteOfficialDocType(com.orifound.aiim.entity.OfficialDocType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteOfficialDocType(OfficialDocType officialDocType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IOfficialDocTypeManageService#updateOfficialDocType(com.orifound.aiim.entity.OfficialDocType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateOfficialDocType(OfficialDocType officialDocType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IOfficialDocTypeManageService#findOfficialDocTypes(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findOfficialDocTypes(LinkedHashMap<Integer,OfficialDocType> officialDocTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForOfficialDocType(pErrInfo) == false)
			{
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (officialDocTypeDao.findAll(officialDocTypes, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ����������Ϣʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IOfficialDocTypeManageService#findOfficialDocTypeByID(int, com.orifound.aiim.entity.OfficialDocType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findOfficialDocTypeByID(int pID, OfficialDocType officialDocType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
