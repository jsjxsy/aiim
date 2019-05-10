/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.OfficialArchivesTypeSavedMapping;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 公文档案分类的归档映射关系表管理服务的接口定义
 *
 */
public interface IOfficialArchivesTypeSavedMappingManageService {

	/**
	 * 添加一个新的公文档案分类的归档映射关系表
	 * @param officialArchivesTypeSavedMapping 新添加的公文档案分类的归档映射关系表信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveOfficialArchivesTypeSavedMapping(OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping, ErrInfo pErrInfo);

	/**
	 * 删除指定的公文档案分类的归档映射关系表
	 * @param officialArchivesTypeSavedMapping 要删除的公文档案分类的归档映射关系表，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteOfficialArchivesTypeSavedMapping(OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping, ErrInfo pErrInfo);

	/**
	 * 修改指定的公文档案分类的归档映射关系表
	 * @param officialArchivesTypeSavedMapping 修改后的公文档案分类的归档映射关系表信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateOfficialArchivesTypeSavedMapping(OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping, ErrInfo pErrInfo);

	/**
	 * 查找所有的公文档案分类的归档映射关系表信息
	 * @param officialArchivesTypeSavedMappings 返回查找成功的公文档案分类的归档映射关系表集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findOfficialArchivesTypeSavedMappings(Map<Integer,OfficialArchivesTypeSavedMapping> officialArchivesTypeSavedMappings,
			ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找公文档案分类的归档映射关系表信息
	 * @param pID 指定的唯一标识
	 * @param officialArchivesTypeSavedMapping 返回查找成功的公文档案分类的归档映射关系表信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findOfficialArchivesTypeSavedMappingByID(int pID, OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping,
			ErrInfo pErrInfo);
	/**
	 * 根据公文档案类型编号查询出档案类型集合
	 * @param officialArchivesTypeID 公文档案类型编号
	 * @param archivesTypes 档案类型集合
	 * @param pErrInfo 
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesTypesByOfficialArchivesTypeID(int officialArchivesTypeID,Map<Integer,OfficialArchivesTypeSavedMapping> officialArchivesTypeSavedMappings,ErrInfo pErrInfo );

}
