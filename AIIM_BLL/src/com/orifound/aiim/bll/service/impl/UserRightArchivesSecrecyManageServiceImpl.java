/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IUserRightArchivesSecrecyManageService;
import com.orifound.aiim.dal.dao.IUserRightArchivesSecrecyDao;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRightArchivesSecrecy;

/**
 * �û������ܼ���Ȩ�������ʵ����
 *
 */
public class UserRightArchivesSecrecyManageServiceImpl implements IUserRightArchivesSecrecyManageService
{
	
	/**
	 * ���캯��
	 */
	public UserRightArchivesSecrecyManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public UserRightArchivesSecrecyManageServiceImpl(IUserRightArchivesSecrecyDao userRightArchivesSecrecyDao)
	{
		this.userRightArchivesSecrecyDao = userRightArchivesSecrecyDao;
	}
	
	/**
	 * �û������ܼ�Ȩ�ޱ�����ݷ��ʶ���
	 */
	private IUserRightArchivesSecrecyDao userRightArchivesSecrecyDao = null;

	/**
	 * ��ȡ����ֵ���û������ܼ�Ȩ�ޱ�����ݷ��ʶ���
	 * @return �û������ܼ�Ȩ�ޱ�����ݷ��ʶ���
	 */
	public IUserRightArchivesSecrecyDao getUserRightArchivesSecrecyDao()
	{
		return userRightArchivesSecrecyDao;
	}

	/**
	 * ��������ֵ���û������ܼ�Ȩ�ޱ�����ݷ��ʶ���
	 * @param userRightArchivesSecrecyDao �û������ܼ�Ȩ�ޱ�����ݷ��ʶ���
	 */
	public void setUserRightArchivesSecrecyDao(IUserRightArchivesSecrecyDao userRightArchivesSecrecyDao)
	{
		this.userRightArchivesSecrecyDao = userRightArchivesSecrecyDao;
	}
	
	/**
	 * ����û������ܼ���Ȩ��Ϣ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForUserRightArchivesSecrecy(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (userRightArchivesSecrecyDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("�û������ܼ���Ȩ��Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IUserRightArchivesSecrecyManageService#saveRightArchivesSecrecysForUser(com.orifound.aiim.entity.UserInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean saveRightArchivesSecrecysForUser(UserInfo userInfo, List<UserRightArchivesSecrecy> userRightArchivesSecrecies, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRightArchivesSecrecy(pErrInfo) == false)
			{
				pFlag = false;
				pErrInfo.getContent().insert(0, "�����ܼ�Ȩ��BLL��DAO����ע��ʧ�ܣ�");
			}
			
			if (pFlag)
			{
				if (userInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û���Ϣ�Ƿ�Ϊ��");
				}
			}
			
			//����û�����Ƿ�Ƿ�
			if (pFlag)
			{
				if (userInfo.getUserID() <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ0");
				}
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				for (Iterator iterator = userRightArchivesSecrecies.iterator(); iterator.hasNext();) {
					UserRightArchivesSecrecy userRightArchivesSecrecy = (UserRightArchivesSecrecy) iterator.next();
					if (userRightArchivesSecrecyDao.save(userRightArchivesSecrecy, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0," �����û������ܼ�Ȩ��ʧ�ܣ�");
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
	 * @see com.orifound.aiim.bll.service.IUserRightArchivesSecrecyManageService#deleteRightArchivesSecrecysForUser(com.orifound.aiim.entity.UserInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteRightArchivesSecrecysForUser(UserInfo userInfo, List<UserRightArchivesSecrecy> userRightArchivesSecrecies, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IUserRightArchivesSecrecyManageService#findRightArchivesSecrecysByUserID(int, java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRightArchivesSecrecysByUserID(int pUserID, Map<Integer, ArchivesSecrecy> archivesSecrecys, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRightArchivesSecrecy(pErrInfo) == false)
			{
				pFlag = false;
				pErrInfo.getContent().insert(0, "�����ܼ�Ȩ��BLL��DAO����ע��ʧ�ܣ�");
			}
			
			//����û�����Ƿ�Ƿ�
			if (pFlag)
			{
				if (pUserID <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ0");
				}
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (userRightArchivesSecrecyDao.findByUserID(pUserID, archivesSecrecys, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡ�û���"+pUserID+"�������ܼ���Ȩ��Ϣʧ��: ");
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
	
	
	public boolean findRightArchivesSecrecysByUserID(int pUserID,List<UserRightArchivesSecrecy> userRightArchivesSecrecys, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRightArchivesSecrecy(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//����û�����Ƿ�Ƿ�
			if (pFlag)
			{
				if (pUserID <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ0");
				}
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (userRightArchivesSecrecyDao.findByUserID(pUserID, userRightArchivesSecrecys, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡ�û���"+pUserID+"�������ܼ���Ȩ��Ϣʧ��: ");
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
	
	public boolean deleteUserRightArchivesSecrecyByUserID(int pUserID, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRightArchivesSecrecy(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//����û�����Ƿ�Ƿ�
			if (pFlag)
			{
				if (pUserID <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ0");
				}
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (userRightArchivesSecrecyDao.deleteByUserID(pUserID, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡ�û���"+pUserID+"�������ܼ���Ȩ��Ϣʧ��: ");
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
