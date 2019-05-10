/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.EnumMap;

import com.orifound.aiim.entity.EnumOfficialArchivesInfoTableType;
import com.orifound.aiim.entity.OfficialArchivesInfoTable;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 公文档案信息相关表的DAO接口定义
 *
 */
public interface IOfficialArchivesInfoTableDao
{

	/**
	 * Dao接口定义：添加公文档案信息相关表
	 * @param officialArchivesInfoTable 要添加的公文档案信息相关表
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的公文档案信息相关表
	 * @param officialArchivesInfoTable 要删除的公文档案信息相关表
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的公文档案信息相关表
	 * @param officialArchivesInfoTable 要更新的公文档案信息相关表
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据公文档案分类编号查找对应的档案信息相关表
	 * @param pArchivesTypeID 指定的公文档案分类编号
	 * @param archivesInfoTables 返回查找成功的档案信息相关表集合，以表类型枚举作为关键字
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByArchivesTypeID(int pArchivesTypeID, EnumMap<EnumOfficialArchivesInfoTableType, OfficialArchivesInfoTable> officialArchivesInfoTables, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找公文档案信息相关表
	 * @param pID 指定的唯一标识
	 * @param officialArchivesInfoTable 返回查找成功的公文档案信息相关表
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo);

}
