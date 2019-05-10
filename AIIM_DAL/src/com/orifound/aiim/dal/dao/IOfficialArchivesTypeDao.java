/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.LinkedHashMap;

import com.orifound.aiim.entity.OfficialArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 公文档案分类字典表的DAO接口定义
 *
 */
public interface IOfficialArchivesTypeDao
{

	/**
	 * Dao接口定义：添加公文档案分类
	 * @param officialArchivesType 要添加的公文档案分类
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的公文档案分类
	 * @param officialArchivesType 要删除的公文档案分类
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的公文档案分类
	 * @param officialArchivesType 要更新的公文档案分类
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的公文档案分类
	 * @param officialArchivesTypes 返回查找成功的公文档案分类集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(LinkedHashMap<Integer,OfficialArchivesType> officialArchivesTypes, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找公文档案分类
	 * @param pOfficialArchivesTypeID 指定的公文档案分类编号
	 * @param officialArchivesType 返回查找成功的公文档案分类
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pOfficialArchivesTypeID, OfficialArchivesType officialArchivesType, ErrInfo pErrInfo);

}
