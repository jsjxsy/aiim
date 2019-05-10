/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IRoleRightArchivesTypeManageService;
import com.orifound.aiim.dal.dao.IRoleRightArchivesTypeDao;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRightArchivesType;
import com.orifound.aiim.entity.UserRole;

/**
 * ��ɫ����������Ȩ�������ʵ����
 *
 */
public class RoleRightArchivesTypeManageServiceImpl implements IRoleRightArchivesTypeManageService
{
	/**
	 * ���캯��
	 */
	public RoleRightArchivesTypeManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public RoleRightArchivesTypeManageServiceImpl(IRoleRightArchivesTypeDao roleRightArchivesTypeDao)
	{
		this.roleRightArchivesTypeDao = roleRightArchivesTypeDao;
	}
	
	/**
	 * ��ɫ����������Ȩ������ݷ��ʶ���
	 */
	private IRoleRightArchivesTypeDao roleRightArchivesTypeDao = null;

	/**
	 * ��ȡ����ֵ����ɫ����������Ȩ������ݷ��ʶ���
	 * @return ��ɫ����������Ȩ������ݷ��ʶ���
	 */
	public IRoleRightArchivesTypeDao getRoleRightArchivesTypeDao()
	{
		return roleRightArchivesTypeDao;
	}

	/**
	 * ��������ֵ����ɫ����������Ȩ������ݷ��ʶ���
	 * @param roleRightArchivesTypeDao ��ɫ����������Ȩ������ݷ��ʶ���
	 */
	public void setRoleRightArchivesTypeDao(IRoleRightArchivesTypeDao roleRightArchivesTypeDao)
	{
		this.roleRightArchivesTypeDao = roleRightArchivesTypeDao;
	}
	
	/**
	 * ����ɫ����������Ȩ��Ϣ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForRoleRightArchivesType(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (roleRightArchivesTypeDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("��ɫ����������Ȩ��Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IRoleRightArchivesTypeManageService#saveRightArchivesTypeForRole(com.orifound.aiim.entity.UserRole, com.orifound.aiim.entity.UserRightArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveRightArchivesTypeForRole(UserRole userRole, UserRightArchivesType userRightArchivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IRoleRightArchivesTypeManageService#saveRightArchivesTypesForRole(com.orifound.aiim.entity.UserRole, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveRightArchivesTypesForRole(UserRole userRole, List<UserRightArchivesType> userRightArchivesTypes, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IRoleRightArchivesTypeManageService#deleteRightArchivesTypesForRole(com.orifound.aiim.entity.UserRole, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteRightArchivesTypesForRole(UserRole userRole, List<UserRightArchivesType> userRightArchivesTypes, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IRoleRightArchivesTypeManageService#findRightArchivesTypeByRolesID(int[], java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRightArchivesTypeByRolesID(int[] pRoleID, LinkedHashMap<Integer, ArchivesType> archivestypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForRoleRightArchivesType(pErrInfo) == false)
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
				if (roleRightArchivesTypeDao.findByRoleID(pRoleID, archivestypes, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡָ����ɫ��"+pRoleID+"���ĵ���������Ȩ��Ϣʧ��: ");
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
