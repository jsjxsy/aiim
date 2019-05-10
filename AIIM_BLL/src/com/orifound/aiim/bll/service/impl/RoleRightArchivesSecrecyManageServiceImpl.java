/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IRoleRightArchivesSecrecyManageService;
import com.orifound.aiim.dal.dao.IRoleRightArchivesSecrecyDao;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.RoleRightArchivesSecrecy;
import com.orifound.aiim.entity.UserRole;

/**
 * ��ɫ�����ܼ���Ȩ�������ʵ����
 *
 */
public class RoleRightArchivesSecrecyManageServiceImpl implements IRoleRightArchivesSecrecyManageService
{
	/**
	 * ���캯��
	 */
	public RoleRightArchivesSecrecyManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public RoleRightArchivesSecrecyManageServiceImpl(IRoleRightArchivesSecrecyDao roleRightArchivesSecrecyDao)
	{
		this.roleRightArchivesSecrecyDao = roleRightArchivesSecrecyDao;
	}
	
	/**
	 * ��ɫ�����ܼ���Ȩ������ݷ��ʶ���
	 */
	private IRoleRightArchivesSecrecyDao roleRightArchivesSecrecyDao = null;

	/**
	 * ��ȡ����ֵ����ɫ�����ܼ���Ȩ������ݷ��ʶ���
	 * @return ��ɫ�����ܼ���Ȩ������ݷ��ʶ���
	 */
	public IRoleRightArchivesSecrecyDao getRoleRightArchivesSecrecyDao()
	{
		return roleRightArchivesSecrecyDao;
	}

	/**
	 * ��������ֵ����ɫ�����ܼ���Ȩ������ݷ��ʶ���
	 * @param roleRightArchivesSecrecyDao ��ɫ�����ܼ���Ȩ������ݷ��ʶ���
	 */
	public void setRoleRightArchivesSecrecyDao(IRoleRightArchivesSecrecyDao roleRightArchivesSecrecyDao)
	{
		this.roleRightArchivesSecrecyDao = roleRightArchivesSecrecyDao;
	}
	
	/**
	 * ����ɫ�����ܼ���Ȩ��Ϣ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForRoleRightArchivesSecrecy(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (roleRightArchivesSecrecyDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("��ɫ�����ܼ���Ȩ��Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IRoleRightArchivesSecrecyManageService#saveRightArchivesSecrecysForRole(com.orifound.aiim.entity.UserRole, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveRightArchivesSecrecysForRole(UserRole userRole, List<RoleRightArchivesSecrecy> roleRightArchivesSecrecies, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IRoleRightArchivesSecrecyManageService#deleteRightArchivesSecrecysForRole(com.orifound.aiim.entity.UserRole, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteRightArchivesSecrecysForRole(UserRole userRole, List<RoleRightArchivesSecrecy> roleRightArchivesSecrecies, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IRoleRightArchivesSecrecyManageService#findRightArchivesSecrecysByRolesID(int[], java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRightArchivesSecrecysByRolesID(int[] pRoleID, Map<Integer, ArchivesSecrecy> archivesSecrecys, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForRoleRightArchivesSecrecy(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//����ɫ����Ƿ�Ƿ�
			if (pFlag)
			{
				if (pRoleID.length==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("��ɫ��ŷǷ�Ϊ�ա�");
				}
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (roleRightArchivesSecrecyDao.findByRoleID(pRoleID, archivesSecrecys, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡ��ɫ�����: "+pRoleID+"���ĵ����ܼ���Ȩ��Ϣʧ��: ");
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
