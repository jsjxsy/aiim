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
 * 目录打印模板数据项定义管理服务实现类
 *
 */
public class CatalogDataItemManageServiceImpl implements ICatalogDataItemManageService
{
	/**
	 * 构造函数
	 */
	public CatalogDataItemManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public CatalogDataItemManageServiceImpl(ICatalogDataItemDao catalogDataItemDao)
	{
		this.catalogDataItemDao = catalogDataItemDao;
	}
	
	/**
	 * 目录打印模板数据项定义表的数据访问对象
	 */
	private ICatalogDataItemDao catalogDataItemDao = null;

	/**
	 * 获取属性值：目录打印模板数据项定义表的数据访问对象
	 * @return 目录打印模板数据项定义表的数据访问对象
	 */
	public ICatalogDataItemDao getCatalogDataItemDao()
	{
		return catalogDataItemDao;
	}

	/**
	 * 设置属性值：目录打印模板数据项定义表的数据访问对象
	 * @param catalogDataItemDao 目录打印模板数据项定义表的数据访问对象
	 */
	public void setCatalogDataItemDao(ICatalogDataItemDao catalogDataItemDao)
	{
		this.catalogDataItemDao = catalogDataItemDao;
	}
	
	/**
	 * 检查目录打印模板数据项定义表的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForCatalogDataItem(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (catalogDataItemDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("目录打印模板数据项定义表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
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
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForCatalogDataItem(pErrInfo) == false)
			{
				pFlag = false;
			}

			//检查档案分类是否为空
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
				else 
				{
					if (archivesType.getID()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//调用DAO接口获取各种目录打印模板所包含的数据项
			if (pFlag)
			{
				for (EnumCatalogType catalogType : EnumCatalogType.values())
				{
					LinkedHashMap<String, CatalogDataItem> catalogDataItems=new LinkedHashMap<String, CatalogDataItem>();
					if (catalogDataItemDao.findByArchivesTypeID(false, archivesType.getID(), catalogType.getEnumValue(), catalogDataItems, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "获取目录打印模板的数据项信息失败: ");
					}
					else 
					{
						//保存获取的目录打印模板数据项信息，没有定义的不予保存
						if (catalogDataItems.size()>0)
						{
							catalogPrintTemplates.put(catalogType, catalogDataItems);
						}
					}
				}
			}
			
			//返回目录打印模板的查询结果
			if (pFlag)
			{
				archivesType.setCatalogPrintTemplates(catalogPrintTemplates);
			}
			
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
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
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForCatalogDataItem(pErrInfo) == false)
			{
				pFlag = false;
			}

			//检查档案分类是否为空
			if (pFlag)
			{
				pErrPos = 2;
				if (officialArchivesType==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("公文档案分类信息非法为空。");
				}
				else 
				{
					if (officialArchivesType.getID()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("公文档案分类编号非法为0");
					}
				}
			}
			
			//调用DAO接口获取各种目录打印模板所包含的数据项
			//公文目前只有一个：公文目录，它不存在其他的案卷目录、盒内目录、卷内目录等等
			if (pFlag)
			{
				LinkedHashMap<String, CatalogDataItem> catalogDataItems=new LinkedHashMap<String, CatalogDataItem>();
				if (catalogDataItemDao.findByArchivesTypeID(true, officialArchivesType.getID(), EnumCatalogType.公文目录.getEnumValue(), catalogDataItems, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取公文目录打印模板的数据项信息失败: ");
				}
				else 
				{
					//保存获取的目录打印模板数据项信息，没有定义的不予保存
					if (catalogDataItems.size()>0)
					{
						catalogPrintTemplates.put(EnumCatalogType.公文目录, catalogDataItems);
					}
				}
			}
			
			//返回目录打印模板的查询结果
			if (pFlag)
			{
				officialArchivesType.setCatalogPrintTemplates(catalogPrintTemplates);
			}
			
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
			catalogPrintTemplates=null;
		}

		return pFlag;
	}

}
