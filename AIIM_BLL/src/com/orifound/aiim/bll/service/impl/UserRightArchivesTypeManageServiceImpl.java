/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IUserRightArchivesTypeManageService;
import com.orifound.aiim.dal.dao.IUserRightArchivesTypeDao;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRightArchivesType;

/**
 * �û�����������Ȩ�������ʵ����
 *
 */
public class UserRightArchivesTypeManageServiceImpl implements IUserRightArchivesTypeManageService
{
	/**
	 * ���캯��
	 */
	public UserRightArchivesTypeManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public UserRightArchivesTypeManageServiceImpl(IUserRightArchivesTypeDao userRightArchivesTypeDao)
	{
		this.userRightArchivesTypeDao = userRightArchivesTypeDao;
	}
	
	/**
	 * �û�����������Ȩ������ݷ��ʶ���
	 */
	private IUserRightArchivesTypeDao userRightArchivesTypeDao = null;

	/**
	 * ��ȡ����ֵ���û�����������Ȩ������ݷ��ʶ���
	 * @return �û�����������Ȩ������ݷ��ʶ���
	 */
	public IUserRightArchivesTypeDao getUserRightArchivesTypeDao()
	{
		return userRightArchivesTypeDao;
	}

	/**
	 * ��������ֵ���û�����������Ȩ������ݷ��ʶ���
	 * @param userRightArchivesTypeDao �û�����������Ȩ������ݷ��ʶ���
	 */
	public void setUserRightArchivesTypeDao(IUserRightArchivesTypeDao userRightArchivesTypeDao)
	{
		this.userRightArchivesTypeDao = userRightArchivesTypeDao;
	}
	
	/**
	 * ����û�����������Ȩ��Ϣ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForUserRightArchivesType(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (userRightArchivesTypeDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("�û�����������Ȩ��Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IUserRightArchivesTypeManageService#saveRightArchivesTypeForUser(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.UserRightArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveRightArchivesTypeForUser(UserInfo userInfo, UserRightArchivesType userRightArchivesType, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRightArchivesType(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//����û�����Ƿ�Ƿ�
			if (pFlag)
			{
				if (userInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û��Ƿ�Ϊ��");
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
			
			if (pFlag) {
				if (userRightArchivesType == null) {
					pFlag = false;
					pErrInfo.getContent().append("�����������Ƿ�Ϊ��!");
				}else {
					if (userRightArchivesType.getArchivesTypeID() <= 0) {
						pFlag = false;
						pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
					}
				}
			}
			
			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
			
					if (userRightArchivesTypeDao.save(userRightArchivesType, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0,"��ȡָ���û���"+userRightArchivesType.getUserID()+"���ĵ���������Ȩ��Ϣʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IUserRightArchivesTypeManageService#saveRightArchivesTypesForUser(com.orifound.aiim.entity.UserInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveRightArchivesTypesForUser(UserInfo userInfo, List<UserRightArchivesType> userRightArchivesTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRightArchivesType(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//����û�����Ƿ�Ƿ�
			if (pFlag)
			{
				if (userInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û��Ƿ�Ϊ��");
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
				for (UserRightArchivesType userRightArchivesType : userRightArchivesTypes) {
					if (userRightArchivesTypeDao.save(userRightArchivesType, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0,"��ȡָ���û���"+userRightArchivesType.getUserID()+"���ĵ���������Ȩ��Ϣʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IUserRightArchivesTypeManageService#deleteRightArchivesTypesForUser(com.orifound.aiim.entity.UserInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteRightArchivesTypesForUser(UserInfo userInfo, List<UserRightArchivesType> userRightArchivesTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRightArchivesType(pErrInfo) == false)
			{
				pFlag = false;
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
				for (UserRightArchivesType userRightArchivesType : userRightArchivesTypes) {
					if (userRightArchivesTypeDao.delete(userRightArchivesType, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0,"��ȡָ���û���"+userInfo.getUserID()+"���ĵ���������Ȩ��Ϣʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IUserRightArchivesTypeManageService#deleteRightArchivesTypesForUser(com.orifound.aiim.entity.UserInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteRightArchivesTypeForUser(UserInfo userInfo, UserRightArchivesType userRightArchivesType, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRightArchivesType(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//����û�����Ƿ�Ƿ�
			if (pFlag)
			{
				if (userInfo.getUserID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ0");
				}
			}
			
			
			if (pFlag) {
				if (userRightArchivesType == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û�����������Ȩ��Ϣ�Ƿ�Ϊ��");
				}else {
					if (userRightArchivesType.getArchivesTypeID() <= 0) {
						pFlag = false;
						pErrInfo.getContent().append("�û�����������Ȩ��Ϣ������ŷǷ�Ϊ0");
					}
				}
			}
			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (userRightArchivesTypeDao.delete(userRightArchivesType, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡָ���û���"+userRightArchivesType.getID()+"���ĵ���������Ȩ��Ϣʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IUserRightArchivesTypeManageService#findRightArchivesTypeByUserID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRightArchivesTypeByUserID(int pUserID, LinkedHashMap<Integer, ArchivesType> archivestypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRightArchivesType(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//����û�����Ƿ�Ƿ�
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
				if (userRightArchivesTypeDao.findByUserID(pUserID, archivestypes, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡָ���û���"+pUserID+"���ĵ���������Ȩ��Ϣʧ��: ");
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
	public boolean deleteRightArchivesTypeByUserID(int pUserID, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserRightArchivesType(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//����û�����Ƿ�Ƿ�
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
				if (userRightArchivesTypeDao.deleteByUserID(pUserID, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡָ���û���"+pUserID+"���ĵ���������Ȩ��Ϣʧ��: ");
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
