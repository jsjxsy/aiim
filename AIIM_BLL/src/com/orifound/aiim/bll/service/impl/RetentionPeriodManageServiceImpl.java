/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IRetentionPeriodManageService;
import com.orifound.aiim.dal.dao.IRetentionPeriodDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.RetentionPeriod;

/**
 * �������޹������ʵ����
 *
 */
public class RetentionPeriodManageServiceImpl implements IRetentionPeriodManageService
{
	/**
	 * ���캯��
	 */
	public RetentionPeriodManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public RetentionPeriodManageServiceImpl(IRetentionPeriodDao retentionPeriodDao)
	{
		this.retentionPeriodDao = retentionPeriodDao;
	}
	
	/**
	 * ���������ֵ������ݷ��ʶ���
	 */
	private IRetentionPeriodDao retentionPeriodDao = null;

	/**
	 * ��ȡ����ֵ�����������ֵ������ݷ��ʶ���
	 * @return ���������ֵ������ݷ��ʶ���
	 */
	public IRetentionPeriodDao getRetentionPeriodDao()
	{
		return retentionPeriodDao;
	}

	/**
	 * ��������ֵ�����������ֵ������ݷ��ʶ���
	 * @param retentionPeriodDao ���������ֵ������ݷ��ʶ���
	 */
	public void setRetentionPeriodDao(IRetentionPeriodDao retentionPeriodDao)
	{
		this.retentionPeriodDao = retentionPeriodDao;
	}
	
	/**
	 * ��鱣�����޵�DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForRetentionPeriod(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (retentionPeriodDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("�������޵�DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IRetentionPeriodManageService#saveRetentionPeriod(com.orifound.aiim.entity.RetentionPeriod, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveRetentionPeriod(RetentionPeriod retentionPeriod, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IRetentionPeriodManageService#deleteRetentionPeriod(com.orifound.aiim.entity.RetentionPeriod, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteRetentionPeriod(RetentionPeriod retentionPeriod, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IRetentionPeriodManageService#updateRetentionPeriod(com.orifound.aiim.entity.RetentionPeriod, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateRetentionPeriod(RetentionPeriod retentionPeriod, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IRetentionPeriodManageService#findRetentionPeriods(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRetentionPeriods(LinkedHashMap<Integer, RetentionPeriod> retentionPeriods, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForRetentionPeriod(pErrInfo) == false)
			{
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				if (retentionPeriodDao.findAll(retentionPeriods, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡ���б���������Ϣʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IRetentionPeriodManageService#findRetentionPeriodByID(int, com.orifound.aiim.entity.RetentionPeriod, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRetentionPeriodByID(int pID, RetentionPeriod retentionPeriod, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}
}