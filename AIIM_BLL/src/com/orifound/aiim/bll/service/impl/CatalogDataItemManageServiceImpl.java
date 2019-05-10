/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.EnumMap;
import java.util.LinkedHashMap;

import com.orifound.aiim.bll.service.ICatalogDataItemManageService;
import com.orifound.aiim.dal.dao.ICatalogDataItemDao;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.CatalogDataItem;
import com.orifound.aiim.entity.EnumCatalogType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesType;

/**
 * Ŀ¼��ӡģ���������������ʵ����
 *
 */
public class CatalogDataItemManageServiceImpl implements ICatalogDataItemManageService
{
	/**
	 * ���캯��
	 */
	public CatalogDataItemManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public CatalogDataItemManageServiceImpl(ICatalogDataItemDao catalogDataItemDao)
	{
		this.catalogDataItemDao = catalogDataItemDao;
	}
	
	/**
	 * Ŀ¼��ӡģ��������������ݷ��ʶ���
	 */
	private ICatalogDataItemDao catalogDataItemDao = null;

	/**
	 * ��ȡ����ֵ��Ŀ¼��ӡģ��������������ݷ��ʶ���
	 * @return Ŀ¼��ӡģ��������������ݷ��ʶ���
	 */
	public ICatalogDataItemDao getCatalogDataItemDao()
	{
		return catalogDataItemDao;
	}

	/**
	 * ��������ֵ��Ŀ¼��ӡģ��������������ݷ��ʶ���
	 * @param catalogDataItemDao Ŀ¼��ӡģ��������������ݷ��ʶ���
	 */
	public void setCatalogDataItemDao(ICatalogDataItemDao catalogDataItemDao)
	{
		this.catalogDataItemDao = catalogDataItemDao;
	}
	
	/**
	 * ���Ŀ¼��ӡģ�����������DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForCatalogDataItem(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (catalogDataItemDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("Ŀ¼��ӡģ�����������DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.ICatalogDataItemManageService#findCatalogDataItems(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findCatalogDataItems(ArchivesType archivesType, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		EnumMap<EnumCatalogType, LinkedHashMap<String, CatalogDataItem>> catalogPrintTemplates = new EnumMap<EnumCatalogType, LinkedHashMap<String,CatalogDataItem>>(EnumCatalogType.class);

		try
		{
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForCatalogDataItem(pErrInfo) == false)
			{
				pFlag = false;
			}

			//��鵵�������Ƿ�Ϊ��
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}
				else 
				{
					if (archivesType.getID()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
					}
				}
			}
			
			//����DAO�ӿڻ�ȡ����Ŀ¼��ӡģ����������������
			if (pFlag)
			{
				for (EnumCatalogType catalogType : EnumCatalogType.values())
				{
					LinkedHashMap<String, CatalogDataItem> catalogDataItems=new LinkedHashMap<String, CatalogDataItem>();
					if (catalogDataItemDao.findByArchivesTypeID(false, archivesType.getID(), catalogType.getEnumValue(), catalogDataItems, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ȡĿ¼��ӡģ�����������Ϣʧ��: ");
					}
					else 
					{
						//�����ȡ��Ŀ¼��ӡģ����������Ϣ��û�ж���Ĳ��豣��
						if (catalogDataItems.size()>0)
						{
							catalogPrintTemplates.put(catalogType, catalogDataItems);
						}
					}
				}
			}
			
			//����Ŀ¼��ӡģ��Ĳ�ѯ���
			if (pFlag)
			{
				archivesType.setCatalogPrintTemplates(catalogPrintTemplates);
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
			catalogPrintTemplates=null;
		}

		return pFlag;
	}

	@Override
	public boolean findCatalogDataItemsForOfficial(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		EnumMap<EnumCatalogType, LinkedHashMap<String, CatalogDataItem>> catalogPrintTemplates = new EnumMap<EnumCatalogType, LinkedHashMap<String,CatalogDataItem>>(EnumCatalogType.class);

		try
		{
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForCatalogDataItem(pErrInfo) == false)
			{
				pFlag = false;
			}

			//��鵵�������Ƿ�Ϊ��
			if (pFlag)
			{
				pErrPos = 2;
				if (officialArchivesType==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ĵ���������Ϣ�Ƿ�Ϊ�ա�");
				}
				else 
				{
					if (officialArchivesType.getID()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���ĵ��������ŷǷ�Ϊ0");
					}
				}
			}
			
			//����DAO�ӿڻ�ȡ����Ŀ¼��ӡģ����������������
			//����Ŀǰֻ��һ��������Ŀ¼���������������İ���Ŀ¼������Ŀ¼������Ŀ¼�ȵ�
			if (pFlag)
			{
				LinkedHashMap<String, CatalogDataItem> catalogDataItems=new LinkedHashMap<String, CatalogDataItem>();
				if (catalogDataItemDao.findByArchivesTypeID(true, officialArchivesType.getID(), EnumCatalogType.����Ŀ¼.getEnumValue(), catalogDataItems, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ����Ŀ¼��ӡģ�����������Ϣʧ��: ");
				}
				else 
				{
					//�����ȡ��Ŀ¼��ӡģ����������Ϣ��û�ж���Ĳ��豣��
					if (catalogDataItems.size()>0)
					{
						catalogPrintTemplates.put(EnumCatalogType.����Ŀ¼, catalogDataItems);
					}
				}
			}
			
			//����Ŀ¼��ӡģ��Ĳ�ѯ���
			if (pFlag)
			{
				officialArchivesType.setCatalogPrintTemplates(catalogPrintTemplates);
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
			catalogPrintTemplates=null;
		}

		return pFlag;
	}

}
