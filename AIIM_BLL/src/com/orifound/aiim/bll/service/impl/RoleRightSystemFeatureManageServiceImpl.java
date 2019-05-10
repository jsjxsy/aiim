/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IRoleRightSystemFeatureManageService;
import com.orifound.aiim.dal.dao.IRoleRightSystemFeatureDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.RoleRightSystemFeature;
import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.UserRole;

/**
 * @author EAGLE
 *
 */
public class RoleRightSystemFeatureManageServiceImpl implements IRoleRightSystemFeatureManageService
{

	/**
	 * ���캯��
	 */
	public RoleRightSystemFeatureManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public RoleRightSystemFeatureManageServiceImpl(IRoleRightSystemFeatureDao roleRightSystemFeatureDao)
	{
		this.roleRightSystemFeatureDao = roleRightSystemFeatureDao;
	}
	
	/**
	 * ��ɫϵͳ������Ȩ��Ϣ������ݷ��ʶ���
	 */
	private IRoleRightSystemFeatureDao roleRightSystemFeatureDao = null;

	/**
	 * ��ȡ����ֵ����ɫϵͳ������Ȩ��Ϣ������ݷ��ʶ���
	 * @return ��ɫϵͳ������Ȩ��Ϣ������ݷ��ʶ���
	 */
	public IRoleRightSystemFeatureDao getRoleRightSystemFeatureDao()
	{
		return roleRightSystemFeatureDao;
	}

	/**
	 * ��������ֵ����ɫϵͳ������Ȩ��Ϣ������ݷ��ʶ���
	 * @param roleRightSystemFeatureDao ��ɫϵͳ������Ȩ��Ϣ������ݷ��ʶ���
	 */
	public void setRoleRightSystemFeatureDao(IRoleRightSystemFeatureDao roleRightSystemFeatureDao)
	{
		this.roleRightSystemFeatureDao = roleRightSystemFeatureDao;
	}
	
	/**
	 * ����ɫϵͳ������Ȩ��Ϣ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForRoleRightSystemFeature(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (roleRightSystemFeatureDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("��ɫϵͳ������Ȩ��Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IRoleRightSystemFeatureManageService#saveRightSystemFeatureForRole(com.orifound.aiim.entity.UserRole, com.orifound.aiim.entity.RoleRightSystemFeature, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveRightSystemFeatureForRole(UserRole userRole, RoleRightSystemFeature roleRightSystemFeature, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IRoleRightSystemFeatureManageService#saveRightSystemFeaturesForRole(com.orifound.aiim.entity.UserRole, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveRightSystemFeaturesForRole(UserRole userRole, List<RoleRightSystemFeature> roleRightSystemFeatures, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IRoleRightSystemFeatureManageService#deleteRightSystemFeatureForRole(com.orifound.aiim.entity.UserRole, com.orifound.aiim.entity.RoleRightSystemFeature, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteRightSystemFeatureForRole(UserRole userRole, RoleRightSystemFeature roleRightSystemFeature, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IRoleRightSystemFeatureManageService#deleteRightSystemFeaturesForRole(com.orifound.aiim.entity.UserRole, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteRightSystemFeaturesForRole(UserRole userRole, List<RoleRightSystemFeature> roleRightSystemFeatures, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IRoleRightSystemFeatureManageService#findRightSystemFeatureMenusByRolesID(int[], java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRightSystemFeatureMenusByRolesID(int[] pRoleID, LinkedHashMap<String, SystemFeature> systemFeatures, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForRoleRightSystemFeature(pErrInfo) == false)
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
				if (roleRightSystemFeatureDao.findMenusByRoleID(pRoleID, systemFeatures, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡ��ɫ�����:"+pRoleID+"����ϵͳ���ܲ˵���Ȩʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IRoleRightSystemFeatureManageService#findRightSystemFeaturesByRolesID(int[], java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRightSystemFeaturesByRolesID(int[] pRoleID, Map<String, SystemFeature> systemFeatures, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForRoleRightSystemFeature(pErrInfo) == false)
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
				if (roleRightSystemFeatureDao.findByRoleID(pRoleID, systemFeatures, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡ��ɫ�����:"+pRoleID+"����ϵͳ������Ȩʧ��: ");
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
