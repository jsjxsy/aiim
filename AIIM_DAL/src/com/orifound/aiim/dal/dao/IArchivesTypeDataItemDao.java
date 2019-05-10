/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案分类数据项字典表（DDR_ArchivesType_DataItem表）的DAO接口定义
 *
 */
public interface IArchivesTypeDataItemDao
{

	/**
	 * Dao接口定义：添加档案分类数据项
	 * @param archivesTypeDataItem 要添加的档案分类数据项
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的档案分类数据项
	 * @param archivesTypeDataItem 要删除的档案分类数据项
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的档案分类数据项
	 * @param archivesTypeDataItem 要更新的档案分类数据项
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找指定档案分类定义的数据项<br>
	 * 正常的实体分类档案和公文档案分类都适用
	 * @param officialArchivesFlag 公文档案标志
	 * @parma pArchivesTypeID 指定的档案分类编号
	 * @param archivesTypeDataItems 返回查找成功的档案分类数据项集合，以字段名作为关键字
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByArchivesTypeID(Boolean officialArchivesFlag,int pArchivesTypeID, LinkedHashMap<String,ArchivesTypeDataItem> archivesTypeDataItems, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找档案分类数据项
	 * @param pID 指定的唯一标识
	 * @param archivesTypeDataItem 返回查找成功的档案分类数据项
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo);

}
