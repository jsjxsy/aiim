/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.orifound.aiim.bll.service.ISystemUserRightInitializeService;
import com.orifound.aiim.bll.service.IUserChargeDepartmentInfoManageService;
import com.orifound.aiim.bll.service.IUserChargeUserInfoManageService;
import com.orifound.aiim.bll.service.IUserInfoManageService;
import com.orifound.aiim.bll.service.IUserRolesInfoManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.dal.dao.IUserInfoDao;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.DepartmentInfo;
import com.orifound.aiim.entity.EnumSystemRole;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.UserChargeDepartmentInfo;
import com.orifound.aiim.entity.UserChargeUserInfo;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRolesInfo;
import com.orifound.commons.acservice.IAccessControlService;
import com.orifound.commons.acservice.impl.AccessControlServiceImpl;

/**
 * �û���Ϣ�������ʵ����
 * 
 */
public class UserInfoManageServiceImpl implements IUserInfoManageService
{

	/**
	 * ���캯��
	 */
	public UserInfoManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public UserInfoManageServiceImpl(IUserInfoDao userInfoDao)
	{
		this.userInfoDao = userInfoDao;
	}

	/**
	 * �û���Ϣ������ݷ��ʶ���
	 */
	private IUserInfoDao userInfoDao = null;

	/**
	 * ��ȡ����ֵ���û���Ϣ������ݷ��ʶ���
	 * 
	 * @return �û���Ϣ������ݷ��ʶ���
	 */
	public IUserInfoDao getUserInfoDao()
	{
		return userInfoDao;
	}

	/**
	 * ��������ֵ���û���Ϣ������ݷ��ʶ���
	 * 
	 * @param userInfoDao
	 *            �û���Ϣ������ݷ��ʶ���
	 */
	public void setUserInfoDao(IUserInfoDao userInfoDao)
	{
		this.userInfoDao = userInfoDao;
	}

	/**
	 * �û�������Ϣ����������
	 */
	private IUserChargeUserInfoManageService userChargeUserInfoManageService = null;

	/**
	 * ��������ֵ���û�������Ϣ����������
	 * 
	 * @param userChargeUserInfoManageService
	 *            �û�������Ϣ����������
	 */
	public void setUserChargeUserInfoManageService(IUserChargeUserInfoManageService userChargeUserInfoManageService)
	{
		this.userChargeUserInfoManageService = userChargeUserInfoManageService;
	}

	/**
	 * ��ȡ����ֵ���û�������Ϣ����������
	 * 
	 * @return �û�������Ϣ����������
	 */
	public IUserChargeUserInfoManageService getUserChargeUserInfoManageService()
	{
		return userChargeUserInfoManageService;
	}

	/**
	 * ҵ��רԱ��������Ϣ����������
	 */
	private IUserChargeDepartmentInfoManageService userChargeDepartmentInfoManageService = null;

	/**
	 * ��������ֵ��ҵ��רԱ��������Ϣ����������
	 * 
	 * @param userChargeDepartmentInfoManageService
	 *            ҵ��רԱ��������Ϣ����������
	 */
	public void setUserChargeDepartmentInfoManageService(IUserChargeDepartmentInfoManageService userChargeDepartmentInfoManageService)
	{
		this.userChargeDepartmentInfoManageService = userChargeDepartmentInfoManageService;
	}

	/**
	 * ��ȡ����ֵ��ҵ��רԱ��������Ϣ����������
	 * 
	 * @return ҵ��רԱ��������Ϣ����������
	 */
	public IUserChargeDepartmentInfoManageService getUserChargeDepartmentInfoManageService()
	{
		return userChargeDepartmentInfoManageService;
	}

	/**
	 * �û���ɫ��Ϣ����������
	 */
	private IUserRolesInfoManageService userRolesInfoManageService = null;

	/**
	 * ��������ֵ���û���ɫ��Ϣ����������
	 * 
	 * @param userRoleManageService
	 *            �û���ɫ��Ϣ����������
	 */
	public void setUserRolesInfoManageService(IUserRolesInfoManageService userRolesInfoManageService)
	{
		this.userRolesInfoManageService = userRolesInfoManageService;
	}

	/**
	 * ��ȡ����ֵ���û���ɫ��Ϣ����������
	 * 
	 * @return �û���ɫ��Ϣ����������
	 */
	public IUserRolesInfoManageService getUserRolesInfoManageService()
	{
		return userRolesInfoManageService;
	}

	/**
	 * ϵͳ�û�Ȩ�޳�ʼ���������
	 */
	private ISystemUserRightInitializeService systemUserRightInitializeService = null;

	/**
	 * ��������ֵ��ϵͳ�û�Ȩ�޳�ʼ���������
	 * 
	 * @param entityNameManageService
	 *            ϵͳ�û�Ȩ�޳�ʼ���������
	 */
	public void setSystemUserRightInitializeService(ISystemUserRightInitializeService systemUserRightInitializeService)
	{
		this.systemUserRightInitializeService = systemUserRightInitializeService;
	}

	/**
	 * ��ȡ����ֵ��ϵͳ�û�Ȩ�޳�ʼ���������
	 * 
	 * @return ϵͳ�û�Ȩ�޳�ʼ���������
	 */
	public ISystemUserRightInitializeService getSystemUserRightInitializeService()
	{
		return systemUserRightInitializeService;
	}

	/**
	 * ����û���Ϣ��DAO����ע�루DAO Depandency Injection��
	 * 
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForUserInfo(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// ����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (userInfoDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("�û���Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		}
		catch (Exception e)
		{
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
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

	/**
	 * �����ص�ҵ���߼���������ע�루BLL Depandency Injection��
	 * 
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkBllInjection(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// ����Ƿ��н����û�������Ϣ��BLLҵ���߼����������ע��
			pErrPos = 1;
			if (userChargeUserInfoManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("�û�������Ϣ��BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}

			// ���ҵ��רԱ�����ŵ�BLLҵ���߼������Ƿ�������ע��
			if (userChargeDepartmentInfoManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("ҵ��רԱ�����ŵ�BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}

			// ����Ƿ��н����û���ɫ��Ϣ��BLLҵ���߼����������ע��
			if (userRolesInfoManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("�û���ɫ��Ϣ��������BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}

			// ����Ƿ��н���ϵͳ�û�Ȩ�޳�ʼ�������BLLҵ���߼����������ע��
			if (systemUserRightInitializeService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("ϵͳ�û�Ȩ�޳�ʼ�������BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		}
		catch (Exception e)
		{
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#addUserToDeparment
	 * (com.orifound.aiim.entity.UserInfo,
	 * com.orifound.aiim.entity.DepartmentInfo,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean addUserToDeparment(UserInfo userInfo, DepartmentInfo departmentInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#addUsersToDeparment
	 * (java.util.List, com.orifound.aiim.entity.DepartmentInfo,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean addUsersToDeparment(List<UserInfo> userInfos, DepartmentInfo departmentInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orifound.aiim.bll.service.IUserInfoManageService#
	 * deleteUserChargeDepartment(com.orifound.aiim.entity.UserInfo,
	 * com.orifound.aiim.entity.DepartmentInfo,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteUserChargeDepartment(UserInfo userInfo, DepartmentInfo departmentInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orifound.aiim.bll.service.IUserInfoManageService#
	 * deleteUserChargeDepartments(com.orifound.aiim.entity.UserInfo,
	 * java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteUserChargeDepartments(UserInfo userInfo, List<DepartmentInfo> departmentInfos, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#deleteUserInfo(com
	 * .orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteUserInfo(UserInfo userInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "userInfo��BLLҵ���߼���Daoע��ʧ��:");
			}
			if (userInfo == null) {
				pFlag = false;
				pErrInfo.getContent().append("�û���Ϣ�Ƿ�Ϊ��!");
			}else {
				if (userInfo.getUserID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ�գ�");
				}
			}
			
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.delete(userInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "ɾ���û�ʧ�ܣ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#findUsers(java.util
	 * .List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUsers(List<UserInfo> userInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "userInfo��BLLҵ���߼���Daoע��ʧ��:");
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.findAll(userInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "userInfo��BLLҵ���߼����ѯ�����û�ʧ��:");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#findAnonymousUser
	 * (com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAnonymousUser(UserInfo userInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			// ����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserInfo(pErrInfo) == false)
			{
				pFlag = false;
			}

			// ����DAO�ӿ�
			if (pFlag)
			{
				if (userInfoDao.findAnonymous(userInfo, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���������û���Ϣʧ��: ");
				}
			}

		}
		catch (Exception e)
		{
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#findUserByIDCardNo
	 * (java.lang.String, com.orifound.aiim.entity.UserInfo,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserByIDCardNo(String pIDCardNo, UserInfo userInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#findUserByRealName
	 * (java.lang.String, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserByRealName(String realName, List<UserInfo> userInfos, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#findUserByUserID
	 * (java.lang.Integer, com.orifound.aiim.entity.UserInfo,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserByUserID(Integer pUserID, UserInfo userInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "userInfoҵ���߼����DAO����ע��ʧ��!");
			}
			if (userInfo == null) {
				pFlag=false;
				pErrInfo.getContent().append("userInfo����Ƿ�Ϊ��!");
			}
			if (userInfo.getUserID() == 0) {
				pFlag=false;
				pErrInfo.getContent().append("�û���ŷǷ�Ϊ��!");
			}
		
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.findUserInfoByUserID(pUserID, userInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�����û���Ϣʧ��");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#findUsersByDepartmentID
	 * (int, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUsersByDepartmentID(int departmentID, List<UserInfo> userInfos, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#login(com.orifound
	 * .aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean login(UserInfo userInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		//���ʿ��Ʒ������
		IAccessControlService accessControlService = new AccessControlServiceImpl();
		
		try
		{
			pErrPos = 1;
			// ����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserInfo(pErrInfo) == false)
			{
				pFlag = false;
			}

			// ������ҵ���߼������Ƿ�������ע��
			if (pFlag)
			{
				if (checkBllInjection(pErrInfo) == false)
				{
					pFlag = false;
				}
			}

			// ����DAO�ӿ���֤�û��Ƿ����
			if (pFlag)
			{
				pErrPos = 2;
				UserInfo pUserInfo = new UserInfo();
				if (userInfoDao.findByUserName(userInfo.getUserName().trim(), pUserInfo, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "�����û���" + userInfo.getUserName().trim() + "��ʧ��: ");
				}

				// ����Ƿ��ҵ�ָ���û������û���Ϣ
				if (pFlag)
				{
					pErrPos = 3;
					if (pUserInfo.getUserID() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("�û������ڡ�");
					}
				}

				// �ҵ��û����������Ƿ�ƥ��
				if (pFlag)
				{
					if (pUserInfo.getUserPWD().equals(userInfo.getUserPWD()) == false)
					{
						pFlag = false;
						pErrInfo.getContent().append("�û��������");
					}
				}

				// �����¼�ɹ��������û�������Ϣ
				if (pFlag)
				{
					userInfo.cloneFrom(pUserInfo);
				}

				// ���پֲ�����
				pUserInfo = null;
			}

			// �û���¼�ɹ����ȡ�������Ϣ
			if (pFlag)
			{
				pErrPos = 4;
				List<UserChargeUserInfo> userChargeUserInfos = new ArrayList<UserChargeUserInfo>();
				if (userChargeUserInfoManageService.findUserChargeUserInfosByUserID(userInfo.getUserID(), userChargeUserInfos, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ�û�������Ϣʧ��: ");
				}
				else
				{
					// �����ȡ���û�������Ϣ
					if (userChargeUserInfos.size() > 0)
					{
						userInfo.setChargeUserInfos(userChargeUserInfos);
					}
				}

				// ���پֲ�����
				userChargeUserInfos = null;
			}

			// ������ȡҵ��רԱ����Ĳ�����Ϣ
			if (pFlag)
			{
				pErrPos = 5;
				LinkedHashMap<Integer, UserChargeDepartmentInfo> userChargeDepartmentInfos = new LinkedHashMap<Integer, UserChargeDepartmentInfo>();
				if (userChargeDepartmentInfoManageService.findUserChargeDepartmentInfosByUserID(userInfo.getUserID(), userChargeDepartmentInfos, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡҵ��רԱ����Ĳ�����Ϣʧ��: ");
				}
				else
				{
					// �����ȡ��ҵ��רԱ����Ĳ�����Ϣ
					if (userChargeDepartmentInfos.size() > 0)
					{
						userInfo.setChargeDepartmentInfos(userChargeDepartmentInfos);
					}
				}

				// ���پֲ�����
				userChargeDepartmentInfos = null;
			}

			// ��ȡ��¼�û���������Ȩ��Ϣ��������������ɫ����Ȩ��Ϣ��
			if (pFlag)
			{
				pErrPos = 6;
				if (getSystemUserRights(userInfo, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ��¼�û�����Ȩ��Ϣʧ��: ");
				}
			}
			
			//��ʼ�����ʿ��Ʒ������
			if (pFlag) {	
				pErrPos = 7;
				Map<Integer, ArchivesType> endArchivesTypes = new HashMap<Integer, ArchivesType>();
				CommonUtil.getEndArchivesTypesByTree(userInfo.getArchivesTypes(), endArchivesTypes, pErrInfo);
				accessControlService.loadArchivesSecrecyACL(new ArrayList<Object>(userInfo.getArchivesSecrecys().keySet()));
				accessControlService.loadSystemFeatureACL(new ArrayList<Object>(userInfo.getUCL().keySet()));	
				accessControlService.loadArchivesTypeACL(new ArrayList<Object>(endArchivesTypes.keySet()));	
				//�����ԭ�ĵ����ļ��ķ��ʿ�������
				userInfo.setAccessControlService(accessControlService);
				
			}

		}
		catch (Exception e)
		{
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	/**
	 * ��ȡϵͳ�û�����Ȩ��Ϣ������ϵͳ���ܲ˵���Ȩ��ϵͳ������Ȩ��������Դ��Ȩ�Լ������ܼ���Ȩ��Ϣ��<br>
	 * ���ؽ���ǰ�������û�ֱ����Ȩ����Ը��û�������ɫֱ����Ȩ���ڵ���Ȩ��Ϣ�ϼ�
	 * 
	 * @param userInfo
	 *            ָ�����û���Ϣ
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean getSystemUserRights(UserInfo userInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		int[] pRolesID = null; // �û�������ɫ�ı������

		try
		{
			// ����û���Ϣ�Ƿ�Ϊ��
			pErrPos = 1;
			if (userInfo == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("�û���Ϣ�Ƿ�Ϊ�ա�");
			}
			else
			{
				if (userInfo.getUserID() <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ0");
				}
			}

			// ������ȡ�û������Ľ�ɫ
			if (pFlag)
			{
				pErrPos = 3;
				List<UserRolesInfo> userRolesInfos = new ArrayList<UserRolesInfo>();
				if (userRolesInfoManageService.findUserRolesInfosByUserID(userInfo.getUserID(), userRolesInfos, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ�û�������ɫ��Ϣʧ��: ");
				}
				else
				{
					if (userRolesInfos.size() > 0)
					{						
						pRolesID = new int[userRolesInfos.size()];
						for (int i = 0; i < userRolesInfos.size(); i++)
						{
							pRolesID[i] = userRolesInfos.get(i).getRolesID();
							
						}
						userInfo.setRolesIDs(pRolesID);
					}
				}

				// ���پֲ�����
				userRolesInfos = null;
			}

			// ��ȡ��Ȩ��ϵͳ���ܲ˵�
			if (pFlag)
			{
				LinkedHashMap<String, SystemFeature> systemFeatureMenus = new LinkedHashMap<String, SystemFeature>();
				if (systemUserRightInitializeService.findRightSystemFeatureMenusByUserID(userInfo.getUserID(), pRolesID, systemFeatureMenus, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ�û�ϵͳ���ܲ˵���Ȩ��Ϣʧ��: ");
				}
				else
				{
					// �����û���ϵͳ���ܲ˵���Ȩ��Ϣ
					userInfo.setSystemMenus(systemFeatureMenus);
				}

				// ���پֲ�����
				systemFeatureMenus = null;
			}

			// ��ȡϵͳ������Ȩ��Ϣ��UCL
			if (pFlag)
			{
				Map<String, SystemFeature> systemFeatures = new HashMap<String, SystemFeature>();
				if (systemUserRightInitializeService.findRightSystemFeaturesByUserID(userInfo.getUserID(), pRolesID, systemFeatures, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ�û�ϵͳ������Ȩ��Ϣʧ��: ");
				}
				else
				{
					// �����û����ʿ����б�UCL
					userInfo.setUCL(systemFeatures);
				}

				// ���پֲ�����
				systemFeatures = null;
			}

			// ��ȡ������Դ��Ȩ��Ϣ
			if (pFlag)
			{
				LinkedHashMap<Integer, ArchivesType> archivesTypes = new LinkedHashMap<Integer, ArchivesType>();
				if (systemUserRightInitializeService.findRightArchivesTypesByUserID(userInfo.getUserID(), pRolesID, archivesTypes, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ������Դ��Ȩ��Ϣʧ��: ");
				}
				else
				{
					// ���浵����Դ��Ȩ��Ϣ
					userInfo.setArchivesTypes(archivesTypes);
				}

				// ���پֲ�����
				archivesTypes = null;
			}

			// ��ȡ�����ܼ���Ȩ��Ϣ
			if (pFlag)
			{
				Map<Integer, ArchivesSecrecy> archivesSecrecys = new HashMap<Integer, ArchivesSecrecy>();
				if (systemUserRightInitializeService.findRightArchivesSecrecysByUserID(userInfo.getUserID(), pRolesID, archivesSecrecys, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ�����ܼ���Ȩ��Ϣʧ��: ");
				}
				else
				{
					// ���浵���ܼ���Ȩ��Ϣ
					userInfo.setArchivesSecrecys(archivesSecrecys);
				}

				// ���پֲ�����
				archivesSecrecys = null;
			}
		}
		catch (Exception e)
		{
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
			pRolesID = null;
		}

		return pFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#logout(com.orifound
	 * .aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean logout(UserInfo userInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#saveUserChargeDepartment
	 * (com.orifound.aiim.entity.UserInfo,
	 * com.orifound.aiim.entity.DepartmentInfo,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveUserChargeDepartment(UserInfo userInfo, DepartmentInfo departmentInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orifound.aiim.bll.service.IUserInfoManageService#
	 * saveUserChargeDepartments(com.orifound.aiim.entity.UserInfo,
	 * java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveUserChargeDepartments(UserInfo userInfo, List<DepartmentInfo> departmentInfos, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#saveUserInfo(com
	 * .orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveUserInfo(UserInfo userInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "userInfoҵ���߼����DAO����ע��ʧ��!");
				
			}
			if (userInfo == null) {
				pFlag=false;
				pErrInfo.getContent().append("userInfo����Ƿ�Ϊ��!");
			}
			
			if (userInfo.getUserName() == null || userInfo.equals("")) {
				pFlag=false;
				pErrInfo.getContent().append("�û����Ƿ�Ϊ��!");
			}
			if (userInfo.getRealName() == null || userInfo.equals("")) {
				pFlag=false;
				pErrInfo.getContent().append("�û���ʵ�����Ƿ�Ϊ��!");
			}
			if (userInfo.getUserPWD() == null || userInfo.getUserPWD().equals("")) {
				pFlag=false;
				pErrInfo.getContent().append("�û�����Ƿ�Ϊ��!");
			}
			
			if (pFlag) {
				pErrPos = 2;
			}
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 3;
				if (userInfoDao.save(userInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"�û���Ϣ����ʧ��:");
				}

			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#updateUserInfo(com
	 * .orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateUserInfo(UserInfo userInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "userInfoҵ���߼����DAO����ע��ʧ��!");
			}
			if (userInfo == null) {
				pFlag=false;
				pErrInfo.getContent().append("userInfo����Ƿ�Ϊ��!");
			}
			if (userInfo.getUserID() == 0) {
				pFlag=false;
				pErrInfo.getContent().append("�û���ŷǷ�Ϊ��!");
			}
			if (userInfo.getUserName() == null || userInfo.equals("")) {
				pFlag=false;
				pErrInfo.getContent().append("�û����Ƿ�Ϊ��!");
			}
			if (userInfo.getRealName() == null || userInfo.equals("")) {
				pFlag=false;
				pErrInfo.getContent().append("�û���ʵ�����Ƿ�Ϊ��!");
			}
			if (userInfo.getUserPWD() == null || userInfo.getUserPWD().equals("")) {
				pFlag=false;
				pErrInfo.getContent().append("�û�����Ƿ�Ϊ��!");
			}
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.update(userInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�����û���Ϣʧ��");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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
	public boolean modifyPassword(UserInfo userInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "userInfoҵ���߼����DAO����ע��ʧ��!");
			}
			if (userInfo == null) {
				pFlag=false;
				pErrInfo.getContent().append("userInfo����Ƿ�Ϊ��!");
			}
			if (userInfo.getUserID() <= 0) {
				pFlag=false;
				pErrInfo.getContent().append("�û���ŷǷ�Ϊ��!");
			}
			if (userInfo.getUserPWD() == null || userInfo.getUserPWD().equals("")) {
				pFlag=false;
				pErrInfo.getContent().append("�û�����Ƿ�Ϊ��!");
			}
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.updatePassword(userInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�����û���Ϣʧ��");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.IUserInfoManageService#loginWithAnonymous
	 * (com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean loginWithAnonymous(UserInfo userInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			// ����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserInfo(pErrInfo) == false)
			{
				pFlag = false;
			}

			// ������ҵ���߼������Ƿ�������ע��
			if (pFlag)
			{
				if (checkBllInjection(pErrInfo) == false)
				{
					pFlag = false;
				}
			}

			// ��ȡ�����û�
			if (pFlag)
			{
				if (findAnonymousUser(userInfo, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ�����û���Ϣʧ��: ");
				}
			}

			// ��ȡ�����û���������Ȩ��Ϣ��������������ɫ����Ȩ��Ϣ��
			if (pFlag)
			{
				if (getSystemUserRights(userInfo, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ�����û�����Ȩ��Ϣʧ��: ");
				}
			}

		}
		catch (Exception e)
		{
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean findTaskPersons(List<UserInfo> userInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��� �û������Ƿ�Ϊ��
			if (pFlag) {
				if (userInfos == null) {
					pFlag = false;
					pErrInfo.getContent().append("�������->�û����ϷǷ�Ϊ�ա�");
				}
			}

			//��ѯ��������˼���
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.findTaskPersons(userInfos, pErrInfo) == false ) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ��������˼��� ʧ�ܡ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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
	public boolean findUserInfosByCondition(Map<String,Object> userInfoQueryCondition,DataPageInfo dataPageInfo, List<UserInfo> userInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "�û���ϢBLLҵ���߼����Daoע��ʧ�ܣ�");
			}

			//������ݷ�ҳ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (dataPageInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ݷ�ҳ��Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.findUserInfosByCondition(userInfoQueryCondition,dataPageInfo,userInfos,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("��ѯ�����û���Ϣʧ��!");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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
	public boolean findUserBySystemRole(EnumSystemRole esnumSystemRole, List<UserInfo> userInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.findBusinessGuids(userInfos,esnumSystemRole, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ɫ�����û���Ϣʧ�ܣ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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
	public boolean findUserChargeUserInfosByUserID(int pID, List<UserInfo> userInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			
			if (pFlag) {
				if (pID == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ��!");
				}
			}
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.findUserChargeUserInfoByUserID(pID, userInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���û���Ų��ұ������û���Ϣʧ�ܣ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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
	public boolean findAllUserUnchargeUserInfosByUserID(Map<String, Object> userInfoQueryCondition,DataPageInfo dataPageInfo,List<UserInfo> userInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.findAllUserUnchargeUserInfo(userInfoQueryCondition,dataPageInfo,userInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���û���Ų��ұ������û���Ϣʧ�ܣ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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
	public boolean findUserProxyInfosByCondition(Map<String, Object> userInfoQueryCondition, DataPageInfo dataPageInfo, List<UserInfo> userInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "�û���ϢBLLҵ���߼����Daoע��ʧ�ܣ�");
			}

			//������ݷ�ҳ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (dataPageInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ݷ�ҳ��Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.findUserInfosByCondition(userInfoQueryCondition,dataPageInfo,userInfos,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("��ѯ�����û���Ϣʧ��!");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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
	public  boolean checkUserNameExists(UserInfo userInfo,IntegerEx exists, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "�û���ϢBLLҵ���߼����Daoע��ʧ�ܣ�");
			}

			//������ݷ�ҳ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (userInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û���Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			if (pFlag) {
				if (userInfo.getUserName() == null || userInfo.getUserName().equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("�û����Ƿ�Ϊ�գ�");
				}
			}
			
			
			//��ʼ����2...
			if (pFlag) {
				if(userInfoDao.checkUserNameExists(userInfo, exists, pErrInfo)== false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "����û����Ƿ����ʧ�ܣ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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
	public boolean checkIDCardNoExists(UserInfo userInfo,IntegerEx exists, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "�û���ϢBLLҵ���߼����Daoע��ʧ�ܣ�");
			}

			//������ݷ�ҳ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (userInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û���Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			if (pFlag) {
				if (userInfo.getIDCardNo() == null || userInfo.getIDCardNo().equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("�û���֤���ŷǷ�Ϊ�գ�");
				}
			}
			
			//��ʼ����2...
			if (pFlag) {
				if(userInfoDao.checkIDCardNoExists(userInfo, exists, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "����û�֤�����Ƿ����ʧ�ܣ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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
	public boolean findUseresNotInRoleID(int pRoleID, Map<String, Object> userInfoQueryCondition, DataPageInfo dataPageInfo, List<UserInfo> userInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (checkDaoInjectionForUserInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "�û���ϢBLLҵ���߼����Daoע��ʧ�ܣ�");
			}

			//������ݷ�ҳ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (dataPageInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ݷ�ҳ��Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoDao.findUseresNotInRoleID(pRoleID,userInfoQueryCondition,dataPageInfo,userInfos,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("��ѯ�����û���Ϣʧ��!");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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
