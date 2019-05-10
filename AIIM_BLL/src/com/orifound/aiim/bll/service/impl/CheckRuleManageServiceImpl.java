/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.ICheckRuleManageService;
import com.orifound.aiim.dal.dao.ICheckRuleDao;
import com.orifound.aiim.entity.CheckRule;
import com.orifound.aiim.entity.ErrInfo;

/**
 * У�����������ʵ����
 *
 */
public class CheckRuleManageServiceImpl implements ICheckRuleManageService
{
	
	/**
	 * ���캯��
	 */
	public CheckRuleManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public CheckRuleManageServiceImpl(ICheckRuleDao checkRuleDao)
	{
		this.checkRuleDao = checkRuleDao;
	}

	/**
	 * У������ֵ������ݷ��ʶ���
	 */
	private ICheckRuleDao checkRuleDao = null;

	/**
	 * ��ȡ����ֵ��У������ֵ������ݷ��ʶ���
	 * @return У������ֵ������ݷ��ʶ���
	 */
	public ICheckRuleDao getCheckRuleDao()
	{
		return checkRuleDao;
	}

	/**
	 * ��������ֵ��У������ֵ������ݷ��ʶ���
	 * @param checkRuleDao У������ֵ������ݷ��ʶ���
	 */
	public void setCheckRuleDao(ICheckRuleDao checkRuleDao)
	{
		this.checkRuleDao = checkRuleDao;
	}
	
	/**
	 * ���У������DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForCheckRule(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (checkRuleDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("У������DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.ICheckRuleManageService#saveCheckRule(com.orifound.aiim.entity.CheckRule, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveCheckRule(CheckRule checkRule, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ICheckRuleManageService#deleteCheckRule(com.orifound.aiim.entity.CheckRule, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteCheckRule(CheckRule checkRule, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ICheckRuleManageService#updateCheckRule(com.orifound.aiim.entity.CheckRule, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateCheckRule(CheckRule checkRule, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ICheckRuleManageService#findCheckRules(java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findCheckRules(Map<Integer, CheckRule> checkRules, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForCheckRule(pErrInfo) == false)
			{
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				if (checkRuleDao.findAll(checkRules, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡ����У�������Ϣʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.ICheckRuleManageService#findCheckRuleByID(int, com.orifound.aiim.entity.CheckRule, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findCheckRuleByID(int pID, CheckRule checkRule, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
