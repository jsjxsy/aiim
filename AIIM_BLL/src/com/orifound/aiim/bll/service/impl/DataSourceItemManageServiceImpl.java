/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IDataSourceItemManageService;
import com.orifound.aiim.dal.dao.IDataSourceItemDao;
import com.orifound.aiim.entity.DataSourceItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����Դ��������������ʵ����
 *
 */
public class DataSourceItemManageServiceImpl implements IDataSourceItemManageService
{
	/**
	 * ���캯��
	 */
	public DataSourceItemManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public DataSourceItemManageServiceImpl(IDataSourceItemDao dataSourceItemDao)
	{
		this.dataSourceItemDao = dataSourceItemDao;
	}
	
	/**
	 * ����Դ���������ֵ������ݷ��ʶ���
	 */
	private IDataSourceItemDao dataSourceItemDao = null;

	/**
	 * ��ȡ����ֵ������Դ���������ֵ������ݷ��ʶ���
	 * @return ����Դ���������ֵ������ݷ��ʶ���
	 */
	public IDataSourceItemDao getDataSourceItemDao()
	{
		return dataSourceItemDao;
	}

	/**
	 * ��������ֵ������Դ���������ֵ������ݷ��ʶ���
	 * @param dataSourceItemDao ����Դ���������ֵ������ݷ��ʶ���
	 */
	public void setDataSourceItemDao(IDataSourceItemDao dataSourceItemDao)
	{
		this.dataSourceItemDao = dataSourceItemDao;
	}
	
	/**
	 * �������Դ����������Ϣ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForDataSourceItem(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (dataSourceItemDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("����Դ����������Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IDataSourceItemManageService#saveDataSourceItem(com.orifound.aiim.entity.DataSourceItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveDataSourceItem(DataSourceItem dataSourceItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDataSourceItemManageService#deleteDataSourceItem(com.orifound.aiim.entity.DataSourceItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteDataSourceItem(DataSourceItem dataSourceItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDataSourceItemManageService#updateDataSourceItem(com.orifound.aiim.entity.DataSourceItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateDataSourceItem(DataSourceItem dataSourceItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDataSourceItemManageService#findDataSourceItemsByDataSourceID(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findDataSourceItemsByDataSourceID(Integer pDataSourceID, LinkedHashMap<Integer, DataSourceItem> dataSourceItems, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForDataSourceItem(pErrInfo) == false)
			{
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				if (dataSourceItemDao.findByDataSourceID(pDataSourceID, dataSourceItems, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"����ָ������Դ����ţ�"+pDataSourceID+"������������Ϣʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IDataSourceItemManageService#findDataSourceItemByID(int, com.orifound.aiim.entity.DataSourceItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findDataSourceItemByID(int pID, DataSourceItem dataSourceItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
