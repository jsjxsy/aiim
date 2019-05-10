/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案分类数据项管理服务的接口定义
 *
 */
public interface IArchivesTypeDataItemManageService
{

	/**
	 * 添加一个新的档案分类数据项
	 * @param archivesTypeDataItem 新添加的档案分类数据项信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveArchivesTypeDataItem(ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo);

	/**
	 * 删除指定的档案分类数据项
	 * @param archivesTypeDataItem 要删除的档案分类数据项，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteArchivesTypeDataItem(ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo);

	/**
	 * 修改指定的档案分类数据项
	 * @param archivesTypeDataItem 修改后的档案分类数据项信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateArchivesTypeDataItem(ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo);

	/**
	 * 查找指定档案分类定义的数据项<br>
	 * 正常的实体分类档案和公文档案分类都适用
	 * @param officialArchivesFlag 公文档案标志
	 * @parma pArchivesTypeID 指定的档案分类编号
	 * @param archivesTypeDataItems 返回查找成功的档案分类数据项集合，以字段名作为关键字
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByArchivesTypeID(Boolean officialArchivesFlag,int pArchivesTypeID, LinkedHashMap<String,ArchivesTypeDataItem> archivesTypeDataItems, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找档案分类数据项信息
	 * @param pID 指定的唯一标识
	 * @param archivesTypeDataItem 返回查找成功的档案分类数据项信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesTypeDataItemByID(int pID, ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo);

}
