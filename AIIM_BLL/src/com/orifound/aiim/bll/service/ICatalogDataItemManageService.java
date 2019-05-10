/**
 * 
 */
package com.orifound.aiim.bll.service;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesType;

/**
 * 目录打印模板的数据项定义管理服务接口
 *
 */
public interface ICatalogDataItemManageService
{
	/**
	 * 查找指定实体档案分类下的各种目录打印模板所包含的数据项<br>
	 * 适用于正常的实体分类档案，公文档案分类不适用<br>
	 * 查找成功的目录打印模板数据项集合，以字段名作为关键字，直接挂接在档案分类对象下的目录打印模板集合中
	 * @parma archivesType 指定的档案分类对象
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findCatalogDataItems(ArchivesType archivesType, ErrInfo pErrInfo);
	
	/**
	 * 查找指公文档案分类下的各种目录打印模板所包含的数据项<br>
	 * 适用于公文档案分类，正常的实体分类档案不适用<br>
	 * 查找成功的目录打印模板数据项集合，以字段名作为关键字，直接挂接在档案分类对象下的目录打印模板集合中
	 * @parma officialArchivesType 指定的公文档案分类对象
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findCatalogDataItemsForOfficial(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo);

}
