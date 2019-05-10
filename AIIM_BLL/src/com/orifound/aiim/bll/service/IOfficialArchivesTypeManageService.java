/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.orifound.aiim.entity.OfficialArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 公文档案分类字典表管理服务的接口定义
 *
 */
public interface IOfficialArchivesTypeManageService
{

	/**
	 * 添加一个新的公文档案分类字典表
	 * @param officialArchivesType 新添加的公文档案分类字典表信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveOfficialArchivesType(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo);

	/**
	 * 删除指定的公文档案分类字典表
	 * @param officialArchivesType 要删除的公文档案分类字典表，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteOfficialArchivesType(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo);

	/**
	 * 修改指定的公文档案分类字典表
	 * @param officialArchivesType 修改后的公文档案分类字典表信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateOfficialArchivesType(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo);

	/**
	 * 查找所有的公文档案分类字典表信息
	 * @param officialArchivesTypes 返回查找成功的公文档案分类字典表集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findOfficialArchivesTypes(LinkedHashMap<Integer,OfficialArchivesType> officialArchivesTypes, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找公文档案分类字典表信息
	 * @param pOfficialArchivesTypeID 指定的公文档案分类编号
	 * @param officialArchivesType 返回查找成功的公文档案分类字典表信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findOfficialArchivesTypeByID(int pOfficialArchivesTypeID, OfficialArchivesType officialArchivesType, ErrInfo pErrInfo);

}
