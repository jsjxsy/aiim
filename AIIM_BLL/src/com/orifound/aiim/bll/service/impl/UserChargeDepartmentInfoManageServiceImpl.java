/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IUserChargeDepartmentInfoManageService;
import com.orifound.aiim.dal.dao.IUserChargeDepartmentInfoDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserChargeDepartmentInfo;

/**
 * ҵ��רԱ��������Ϣ�������ʵ����
 *
 */
public class UserChargeDepartmentInfoManageServiceImpl implements IUserChargeDepartmentInfoManageService
{
	
	/**
	 * ���캯��
	 */
	public UserChargeDepartmentInfoManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public UserChargeDepartmentInfoManageServiceImpl(IUserChargeDepartmentInfoDao userChargeDepartmentInfoDao)
	{
		this.userChargeDepartmentInfoDao = userChargeDepartmentInfoDao;
	}
	
	
	/**
	 * ҵ��רԱ��������Ϣ������ݷ��ʶ���
	 */
	private IUserChargeDepartmentInfoDao userChargeDepartmentInfoDao = null;

	/**
	 * ��ȡ����ֵ��ҵ��רԱ��������Ϣ������ݷ��ʶ���
	 * @return ҵ��רԱ��������Ϣ������ݷ��ʶ���
	 */
	public IUserChargeDepartmentInfoDao getUserChargeDepartmentInfoDao()
	{
		return userChargeDepartmentInfoDao;
	}

	/**
	 * ��������ֵ��ҵ��רԱ��������Ϣ������ݷ��ʶ���
	 * @param userChargeDepartmentInfoDao ҵ��רԱ��������Ϣ������ݷ��ʶ���
	 */
	public void setUserChargeDepartmentInfoDao(IUserChargeDepartmentInfoDao userChargeDepartmentInfoDao)
	{
		this.userChargeDepartmentInfoDao = userChargeDepartmentInfoDao;
	}
	
	/**
	 * ���ҵ��רԱ��������Ϣ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForUserChargeDepartmentInfo(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (userChargeDepartmentInfoDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("ҵ��רԱ��������Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IUserChargeDepartmentInfoManageService#saveUserChargeDepartmentInfo(com.orifound.aiim.entity.UserChargeDepartmentInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveUserChargeDepartmentInfo(UserChargeDepartmentInfo userChargeDepartmentInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserChargeDepartmentInfo(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			if (pFlag) {
				if (userChargeDepartmentInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û���������Ϣ�Ƿ�Ϊ�գ�");
				}
			}
			
			if (pFlag) {
				if (userChargeDepartmentInfo.getUserID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ�գ�");
				}
			}
			
			if (pFlag) {
				if (userChargeDepartmentInfo.getDepartmentID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("���ű�ŷǷ�Ϊ�գ�");
				}
			}
			
			//����DAO�ӿ�
			if (pFlag)
			{
				if (userChargeDepartmentInfoDao.save(userChargeDepartmentInfo, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"���ָ���û�"+userChargeDepartmentInfo.getUserID()+"����Ĳ�����Ϣʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IUserChargeDepartmentInfoManageService#deleteUserChargeDepartmentInfo(com.orifound.aiim.entity.UserChargeDepartmentInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteUserChargeDepartmentInfo(UserChargeDepartmentInfo userChargeDepartmentInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserChargeDepartmentInfo(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			
			if (pFlag) {
				if (userChargeDepartmentInfo.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("���ű�ŷǷ�Ϊ�գ�");
				}
			}
			//����DAO�ӿ�
			if (pFlag)
			{
				if (userChargeDepartmentInfoDao.delete(userChargeDepartmentInfo, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"ɾ��ָ���û�"+userChargeDepartmentInfo.getUserID()+"����Ĳ�����Ϣʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IUserChargeDepartmentInfoManageService#updateUserChargeDepartmentInfo(com.orifound.aiim.entity.UserChargeDepartmentInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateUserChargeDepartmentInfo(UserChargeDepartmentInfo userChargeDepartmentInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IUserChargeDepartmentInfoManageService#findUserChargeDepartmentInfosByUserID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserChargeDepartmentInfosByUserID(int pUserID, LinkedHashMap<Integer, UserChargeDepartmentInfo> userChargeDepartmentInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserChargeDepartmentInfo(pErrInfo) == false)
			{
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				if (userChargeDepartmentInfoDao.findByUserID(pUserID, userChargeDepartmentInfos, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡָ���û�����ţ�"+pUserID+"��������Ĳ�����Ϣʧ��: ");
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

	@Override
	public boolean findAllUserUnChargeDepartmentInfos(List<UserChargeDepartmentInfo> userChargeDepartmentInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserChargeDepartmentInfo(pErrInfo) == false)
			{
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				if (userChargeDepartmentInfoDao.findAllUnchargeDepartment(userChargeDepartmentInfos, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡ���и���Ĳ�����Ϣʧ��: ");
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

}
