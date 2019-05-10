/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IUserRolesInfoManageService;
import com.orifound.aiim.dal.dao.IUserRolesInfoDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesInfo;

/**
 * �û�������ɫ��Ϣ�������ʵ����
 *
 */
public class UserRolesInfoManageServiceImpl implements IUserRolesInfoManageService
{
	
	/**
	 * ���캯��
	 */
	public UserRolesInfoManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public UserRolesInfoManageServiceImpl(IUserRolesInfoDao userRolesInfoDao)
	{
		this.userRolesInfoDao = userRolesInfoDao;
	}
	
	/**
	 * �û�������ɫ��Ϣ������ݷ��ʶ���
	 */
	private IUserRolesInfoDao userRolesInfoDao = null;

	/**
	 * ��ȡ����ֵ���û�������ɫ��Ϣ������ݷ��ʶ���
	 * @return �û�������ɫ��Ϣ������ݷ��ʶ���
	 */
	public IUserRolesInfoDao getUserRolesInfoDao()
	{
		return userRolesInfoDao;
	}

	/**
	 * ��������ֵ���û�������ɫ��Ϣ������ݷ��ʶ���
	 * @param userRolesInfoDao �û�������ɫ��Ϣ������ݷ��ʶ���
	 */
	public void setUserRolesInfoDao(IUserRolesInfoDao userRolesInfoDao)
	{
		this.userRolesInfoDao = userRolesInfoDao;
	}
	
	/**
	 * ����û�������ɫ��Ϣ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForUserRolesInfo(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (userRolesInfoDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("�û�������ɫ��Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IUserRolesInfoManageService#saveUserRolesInfo(com.orifound.aiim.entity.UserRolesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveUserRolesInfo(UserRolesInfo userRolesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRolesInfo(pErrInfo) == false)
			{
				pFlag = false;
				pErrInfo.getContent().insert(0, "��ɫ��ϢBLLҵ���߼����DAO����ע��");
			}
			

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (userRolesInfoDao.save(userRolesInfo, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"�������н�ɫ��Ϣʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IUserRolesInfoManageService#deleteUserRolesInfo(com.orifound.aiim.entity.UserRolesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteUserRolesInfo(UserRolesInfo userRolesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
 
		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRolesInfo(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//����û�����Ƿ�Ϸ�
			if (pFlag)
			{
				if (userRolesInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ��Ϣ�Ƿ�Ϊ�գ�");
				}
			}
			
			
			//����û�����Ƿ�Ϸ�
			if (pFlag)
			{
				if (userRolesInfo.getID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ��ŷǷ�Ϊ0");
				}
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (userRolesInfoDao.delete(userRolesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"ɾ��ָ���û���ɫ��Ϣ��"+userRolesInfo.getID()+"��ʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IUserRolesInfoManageService#findUserRolesInfosByUserID(int, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserRolesInfosByUserID(int pUserID, List<UserRolesInfo> userRolesInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
 
		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRolesInfo(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//����û�����Ƿ�Ϸ�
			if (pFlag)
			{
				if (pUserID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ0");
				}
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (userRolesInfoDao.findByUserID(pUserID, userRolesInfos, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"����ָ���û���"+pUserID+"��������ɫ��Ϣʧ��: ");
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
	public boolean findUserRolesInfos(List<UserRolesInfo> userRolesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRolesInfo(pErrInfo) == false)
			{
				pFlag = false;
				pErrInfo.getContent().insert(0,"�û���ɫ��Ϣ,����DAO����ע��ʧ��: ");
			}
			

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (userRolesInfoDao.findAll(userRolesInfos, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"�������н�ɫ��Ϣʧ��: ");
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
	public boolean findUserRoleByID(int pID,UserRolesInfo userRolesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRolesInfo(pErrInfo) == false)
			{
				pFlag = false;
				pErrInfo.getContent().insert(0, "��ɫ��ϢBLLҵ���߼����DAO����ע��");
			}
			
			if (pFlag) {
				if (pID == 0) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DAO����ע��");
				}
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (userRolesInfoDao.findByID(pID, userRolesInfo, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"�������н�ɫ��Ϣʧ��: ");
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
	public boolean findUserRolesInfosByRoleID(int pRoleID, List<UserRolesInfo> userRolesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
 
		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRolesInfo(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//����û�����Ƿ�Ϸ�
			if (pFlag)
			{
				if (pRoleID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ0");
				}
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (userRolesInfoDao.findByRoleID(pRoleID, userRolesInfos, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"����ָ����ɫ��"+pRoleID+"�������û���Ϣʧ��: ");
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
