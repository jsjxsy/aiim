/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.Map;

import com.orifound.aiim.entity.OfficialArchivesDataItemSavedMapping;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 公文档案数据项的归档关系映射管理服务的接口定义
 *
 */
public interface IOfficialArchivesDataItemSavedMappingManageService {

	/**
	 * 添加一个新的公文档案数据项的归档关系映射
	 * @param officialArchivesDataItemSavedMapping 新添加的公文档案数据项的归档关系映射信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveOfficialArchivesDataItemSavedMapping(OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping, ErrInfo pErrInfo);

	/**
	 * 删除指定的公文档案数据项的归档关系映射
	 * @param officialArchivesDataItemSavedMapping 要删除的公文档案数据项的归档关系映射，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteOfficialArchivesDataItemSavedMapping(OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping, ErrInfo pErrInfo);

	/**
	 * 修改指定的公文档案数据项的归档关系映射
	 * @param officialArchivesDataItemSavedMapping 修改后的公文档案数据项的归档关系映射信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateOfficialArchivesDataItemSavedMapping(OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping, ErrInfo pErrInfo);

	/**
	 * 查找指定公文档案分类映射关系对应的所有数据项的归档关系映射信息
	 * @param officialArchivesDataItemSavedMappings 返回查找成功的公文档案数据项的归档关系映射集合，以公文档案分类数据项编号作为集合关键字
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByArchivesTypeSavedMappingID(Integer archivesTypeSavedMappingID , Map<Integer,OfficialArchivesDataItemSavedMapping> officialArchivesDataItemSavedMappings, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找公文档案数据项的归档关系映射信息
	 * @param pID 指定的唯一标识
	 * @param officialArchivesDataItemSavedMapping 返回查找成功的公文档案数据项的归档关系映射信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findOfficialArchivesDataItemSavedMappingByID(int pID, OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping,
			ErrInfo pErrInfo);

}
