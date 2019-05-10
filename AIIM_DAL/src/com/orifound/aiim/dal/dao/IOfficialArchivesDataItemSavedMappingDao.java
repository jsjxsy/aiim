/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.Map;

import com.orifound.aiim.entity.OfficialArchivesDataItemSavedMapping;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 公文档案数据项的归档关系映射表的DAO接口定义
 *
 */
public interface IOfficialArchivesDataItemSavedMappingDao {

	/**
	 * Dao接口定义：添加公文档案数据项的归档映射关系表
	 * @param officialArchivesDataItemSavedMapping 要添加的公文档案数据项的归档映射关系表
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的公文档案数据项的归档映射关系表
	 * @param officialArchivesDataItemSavedMapping 要删除的公文档案数据项的归档映射关系表
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的公文档案数据项的归档映射关系表
	 * @param officialArchivesDataItemSavedMapping 要更新的公文档案数据项的归档映射关系表
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的公文档案数据项的归档映射关系表
	 * @param archivesTypeSavedMappingID 公文档案分类归档映射关系编号
	 * @param officialArchivesDataItemSavedMappings 返回查找成功的公文档案数据项的归档映射关系表集合，以公文档案分类数据项作为集合关键字
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByArchivesTypeSavedMappingID (Integer archivesTypeSavedMappingID , Map<Integer,OfficialArchivesDataItemSavedMapping> officialArchivesDataItemSavedMappings, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找公文档案数据项的归档映射关系表
	 * @param pID 指定的唯一标识
	 * @param officialArchivesDataItemSavedMapping 返回查找成功的公文档案数据项的归档映射关系表
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping, ErrInfo pErrInfo);

}
