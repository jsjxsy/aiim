/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IDataSourceManageService;
import com.orifound.aiim.dal.dao.IDataSourceDao;
import com.orifound.aiim.entity.DataSource;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����Դ�������ʵ����
 *
 */
public class DataSourceManageServiceImpl implements IDataSourceManageService
{
	/**
	 * ���캯��
	 */
	public DataSourceManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public DataSourceManageServiceImpl(IDataSourceDao dataSourceDao)
	{
		this.dataSourceDao = dataSourceDao;
	}
	
	/**
	 * ����Դ�ֵ������ݷ��ʶ���
	 */
	private IDataSourceDao dataSourceDao = null;

	/**
	 * ��ȡ����ֵ������Դ�ֵ������ݷ��ʶ���
	 * @return ����Դ�ֵ������ݷ��ʶ���
	 */
	public IDataSourceDao getDataSourceDao()
	{
		return dataSourceDao;
	}

	/**
	 * ��������ֵ������Դ�ֵ������ݷ��ʶ���
	 * @param dataSourceDao ����Դ�ֵ������ݷ��ʶ���
	 */
	public void setDataSourceDao(IDataSourceDao dataSourceDao)
	{
		this.dataSourceDao = dataSourceDao;
	}
	
	/**
	 * �������Դ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForDataSource(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (dataSourceDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("����Դ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IDataSourceManageService#saveDataSource(com.orifound.aiim.entity.DataSource, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveDataSource(DataSource dataSource, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDataSourceManageService#deleteDataSource(com.orifound.aiim.entity.DataSource, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteDataSource(DataSource dataSource, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDataSourceManageService#updateDataSource(com.orifound.aiim.entity.DataSource, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateDataSource(DataSource dataSource, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDataSourceManageService#findDataSources(java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findDataSources(Map<Integer, DataSource> dataSources, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForDataSource(pErrInfo) == false)
			{
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag)
			{
				if (dataSourceDao.findAll(dataSources, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡ����Դ�ֵ���Ϣʧ��: ");
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
			throwable=null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDataSourceManageService#findDataSourceByID(int, com.orifound.aiim.entity.DataSource, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findDataSourceByID(int pID, DataSource dataSource, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
