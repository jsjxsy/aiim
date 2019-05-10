/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.OfficialArchivesTypeSavedMapping;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 公文档案分类的归档映射关系表表的DAO接口定义
 *
 */
public interface IOfficialArchivesTypeSavedMappingDao {

	/**
	 * Dao接口定义：添加公文档案分类的归档映射关系表
	 * @param officialArchivesTypeSavedMapping 要添加的公文档案分类的归档映射关系表
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的公文档案分类的归档映射关系表
	 * @param officialArchivesTypeSavedMapping 要删除的公文档案分类的归档映射关系表
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的公文档案分类的归档映射关系表
	 * @param officialArchivesTypeSavedMapping 要更新的公文档案分类的归档映射关系表
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的公文档案分类的归档映射关系表
	 * @param officialArchivesTypeSavedMappings 返回查找成功的公文档案分类的归档映射关系表集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<OfficialArchivesTypeSavedMapping> officialArchivesTypeSavedMappings, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找公文档案分类的归档映射关系表
	 * @param pID 指定的唯一标识
	 * @param officialArchivesTypeSavedMapping 返回查找成功的公文档案分类的归档映射关系表
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping, ErrInfo pErrInfo);
	
	/**
	 * 公文档案类型编号查询
	 * @param officialArchivesTypeID 公文档案类型编号
	 * @param officialArchivesTypeSavedMappings 档案类型集合ID
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return	处理成功返回true，否则返回false
	 */
	boolean findByOfficialArchivesTypeID(int officialArchivesTypeID, Map<Integer,OfficialArchivesTypeSavedMapping> officialArchivesTypeSavedMappings, ErrInfo pErrInfo);
}

