/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.LinkedHashMap;

import com.orifound.aiim.entity.CatalogDataItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 目录数据项定义表的DAO接口定义
 *
 */
public interface ICatalogDataItemDao
{
	/**
	 * Dao接口定义：查找指定档案分类下指定目录打印模板所包含的数据项<br>
	 * 正常的实体分类档案和公文档案分类都适用
	 * @param officialArchivesFlag 公文档案标志
	 * @parma pArchivesTypeID 指定的档案分类编号
	 * @param catalogType 指定的目录类别
	 * @param catalogDataItems 返回查找成功的目录打印模板的数据项定义集合，以字段名作为关键字
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByArchivesTypeID(Boolean officialArchivesFlag,int pArchivesTypeID,Integer catalogType,LinkedHashMap<String,CatalogDataItem> catalogDataItems, ErrInfo pErrInfo);

}
