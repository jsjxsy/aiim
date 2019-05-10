/**
 * 
 */
package com.orifound.aiim.bll.service;


import java.util.*;

import com.orifound.aiim.entity.ArchivesInfoTable;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案信息相关表管理服务的接口定义
 *
 */
public interface IArchivesInfoTableManageService
{
	/**
	 * 根据唯一标识查找档案信息相关表信息
	 * @param pID 指定的唯一标识
	 * @param archivesInfoTable 返回查找成功的档案信息相关表信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesInfoTableByID(int pID, ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo);

	/**
	 * 查找指定档案分类的所有相关表信息
	 * @param pArchivesTypeID 指定的档案分类编号
	 * @param archivesInfoTable 返回查找成功的档案信息相关表信息集合，以表类型枚举作为关键字
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesTypeTables(int pArchivesTypeID, EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable> archivesInfoTables, ErrInfo pErrInfo);
	
	/**
	 * 添加一个新的档案信息相关表记录
	 * @param archivesInfoTable 新添加的档案信息相关表信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveArchivesInfoTable(ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo);

	/**
	 * 设置表创建标志<br>
	 * 适用于物理数据库表结构创建完毕后调用
	 * @param archivesInfoTable 指定的档案信息相关表，编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean setCreatedFlag(ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo);

	/**
	 * 删除指定的档案分类的所有相关表定义记录
	 * @param pArchivesTypeID 指定的档案分类编号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteArchivesTypeTables(int pArchivesTypeID, ErrInfo pErrInfo);

	/**
	 * 修改指定的档案信息相关表记录
	 * @param archivesInfoTable 修改后的档案信息相关表信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateArchivesInfoTable(ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo);

}
