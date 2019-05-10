/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.EnumMap;

import com.orifound.aiim.bll.service.IOfficialArchivesInfoTableManageService;
import com.orifound.aiim.dal.dao.IOfficialArchivesInfoTableDao;
import com.orifound.aiim.entity.EnumOfficialArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesInfoTable;

/**
 * ���ĵ�����Ϣ��ر�Ĺ������ʵ����
 *
 */
public class OfficialArchivesInfoTableManageServiceImpl implements IOfficialArchivesInfoTableManageService
{
	/**
	 * ���캯��
	 */
	public OfficialArchivesInfoTableManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public OfficialArchivesInfoTableManageServiceImpl(IOfficialArchivesInfoTableDao officialArchivesInfoTableDao)
	{
		this.officialArchivesInfoTableDao = officialArchivesInfoTableDao;
	}
	
	/**
	 * ���ĵ�����Ϣ��ر�����ݷ��ʶ���
	 */
	private IOfficialArchivesInfoTableDao officialArchivesInfoTableDao = null;

	/**
	 * ��ȡ����ֵ�����ĵ�����Ϣ��ر�����ݷ��ʶ���
	 * @return ���ĵ�����Ϣ��ر�����ݷ��ʶ���
	 */
	public IOfficialArchivesInfoTableDao getOfficialArchivesInfoTableDao()
	{
		return officialArchivesInfoTableDao;
	}

	/**
	 * ��������ֵ�����ĵ�����Ϣ��ر�����ݷ��ʶ���
	 * @param officialArchivesInfoTableDao ���ĵ�����Ϣ��ر�����ݷ��ʶ���
	 */
	public void setOfficialArchivesInfoTableDao(IOfficialArchivesInfoTableDao officialArchivesInfoTableDao)
	{
		this.officialArchivesInfoTableDao = officialArchivesInfoTableDao;
	}
	
	/**
	 * ��鹫�ĵ�����Ϣ��ر��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionFor(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (officialArchivesInfoTableDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("���ĵ�����Ϣ��ر��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IOfficialArchivesInfoTableManageService#saveOfficialArchivesInfoTable(com.orifound.aiim.entity.OfficialArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveOfficialArchivesInfoTable(OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IOfficialArchivesInfoTableManageService#deleteOfficialArchivesInfoTable(com.orifound.aiim.entity.OfficialArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteOfficialArchivesInfoTable(OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IOfficialArchivesInfoTableManageService#updateOfficialArchivesInfoTable(com.orifound.aiim.entity.OfficialArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateOfficialArchivesInfoTable(OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IOfficialArchivesInfoTableManageService#findOfficialArchivesInfoTables(int, java.util.EnumMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findOfficialArchivesInfoTables(int pArchivesTypeID, EnumMap<EnumOfficialArchivesInfoTableType, OfficialArchivesInfoTable> officialArchivesInfoTables,
			ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionFor(pErrInfo) == false)
			{
				pFlag = false;
			}

			//��鵵���������Ƿ�Ϊ0
			if (pFlag)
			{
				pErrPos = 2;
				if (pArchivesTypeID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ĵ��������ŷǷ�Ϊ0");
				}
			}
			
			//����DAO�ӿ�
			if (pFlag)
			{
				if (officialArchivesInfoTableDao.findByArchivesTypeID(pArchivesTypeID, officialArchivesInfoTables, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡָ�����ĵ����������ر���Ϣʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IOfficialArchivesInfoTableManageService#findOfficialArchivesInfoTableByID(int, com.orifound.aiim.entity.OfficialArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findOfficialArchivesInfoTableByID(int pID, OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
