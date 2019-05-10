/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IDepartmentInfoManageService;
import com.orifound.aiim.dal.dao.IDepartmentInfoDao;
import com.orifound.aiim.entity.DepartmentInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ������Ϣ�������ʵ����
 *
 */
public class DepartmentInfoManageServiceImpl implements
		IDepartmentInfoManageService
{
	
	/**
	 * ���캯��
	 */
	public DepartmentInfoManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public DepartmentInfoManageServiceImpl(IDepartmentInfoDao departmentInfoDao)
	{
		this.departmentInfoDao = departmentInfoDao;
	}
	
	/**
	 * ������Ϣ������ݷ��ʶ���
	 */
	private IDepartmentInfoDao departmentInfoDao = null;

	/**
	 * ��ȡ����ֵ��������Ϣ������ݷ��ʶ���
	 * @return ������Ϣ������ݷ��ʶ���
	 */
	public IDepartmentInfoDao getDepartmentInfoDao()
	{
		return departmentInfoDao;
	}

	/**
	 * ��������ֵ��������Ϣ������ݷ��ʶ���
	 * @param departmentInfoDao ������Ϣ������ݷ��ʶ���
	 */
	public void setDepartmentInfoDao(IDepartmentInfoDao departmentInfoDao)
	{
		this.departmentInfoDao = departmentInfoDao;
	}
	
	/**
	 * ��鲿����Ϣ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForDepartmentInfo(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (departmentInfoDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDepartmentInfoManageService#deleteDepartmentInfo(com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteDepartmentInfo(DepartmentInfo departmentInfo,
			ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDepartmentInfoManageService#findDepartmentInfos(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findDepartmentInfos(List<DepartmentInfo> departmentInfos,
			ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForDepartmentInfo(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��鲿����Ϣ�����Ƿ�Ϊ��
			if (pFlag) {
				if (departmentInfos == null) {
					pFlag = false;
					pErrInfo.getContent().append("�������:������Ϣ���ϷǷ�Ϊ�ա�");
				}
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (departmentInfoDao.findAll(departmentInfos, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ������Ϣʧ��: ");
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
				if (pErrInfo.getException() != null)
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
	 * @see com.orifound.aiim.bll.service.IDepartmentInfoManageService#findDepartmentInfoByID(int, com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findDepartmentInfoByID(int departmentID,
			DepartmentInfo departmentInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDepartmentInfoManageService#saveDepartmentInfo(com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveDepartmentInfo(DepartmentInfo departmentInfo,
			ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDepartmentInfoManageService#updateDepartmentInfo(com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateDepartmentInfo(DepartmentInfo departmentInfo,
			ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findFormationDepartments(
			List<DepartmentInfo> formationDepartments, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForDepartmentInfo(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				if (findDepartmentInfos(formationDepartments, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��������->��ټ����Ǽǣ�����ϵͳ�����е����γɲ�����Ϣ ʧ��");
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

}
