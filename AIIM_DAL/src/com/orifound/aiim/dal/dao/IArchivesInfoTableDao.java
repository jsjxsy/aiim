/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;


import com.orifound.aiim.entity.ArchivesInfoTable;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * DD_ArchivesInfoTable表的DAO接口定义
 *
 */
public interface IArchivesInfoTableDao
{

	/**
	 * Dao接口定义：添加档案信息相关表
	 * @param archivesInfoTable 要添加的档案信息相关表
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的档案分类的所有相关主表定义记录
	 * @param pArchivesTypeID 指定的档案分类编号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteByArchivesTypeID(int pArchivesTypeID,ErrInfo pErrInfo);

	/**
	 * Dao接口定义：设置表创建标志
	 * @param archivesInfoTable 指定的档案信息相关表，编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean setCreatedFlag(ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据档案分类编号查找对应的档案信息相关表
	 * @param pArchivesTypeID 指定的档案分类编号
	 * @param archivesInfoTables 返回查找成功的档案信息相关表集合，以表类型枚举作为关键字
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByArchivesTypeID(int pArchivesTypeID, EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable> archivesInfoTables, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找档案信息相关表
	 * @param pID 指定的唯一标识
	 * @param archivesInfoTable 返回查找成功的档案信息相关表
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo);

}
