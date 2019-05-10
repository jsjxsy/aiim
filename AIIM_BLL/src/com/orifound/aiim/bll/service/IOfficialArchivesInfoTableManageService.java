/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.EnumMap;
import java.util.List;

import com.orifound.aiim.entity.ArchivesInfoTable;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.EnumOfficialArchivesInfoTableType;
import com.orifound.aiim.entity.OfficialArchivesInfoTable;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 公文档案相关表信息管理服务的接口定义
 *
 */
public interface IOfficialArchivesInfoTableManageService
{

	/**
	 * 添加一个新的公文档案相关表信息
	 * @param officialArchivesInfoTable 新添加的公文档案相关表信息信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveOfficialArchivesInfoTable(OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo);

	/**
	 * 删除指定的公文档案相关表信息
	 * @param officialArchivesInfoTable 要删除的公文档案相关表信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteOfficialArchivesInfoTable(OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo);

	/**
	 * 修改指定的公文档案相关表信息
	 * @param officialArchivesInfoTable 修改后的公文档案相关表信息信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateOfficialArchivesInfoTable(OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo);

	/**
	 * 查找指定公文档案分类编号的公文档案相关表信息信息
	 * @param pArchivesTypeID 指定的档案分类编号
	 * @param officialArchivesInfoTables 返回查找成功的公文档案相关表信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findOfficialArchivesInfoTables(int pArchivesTypeID, EnumMap<EnumOfficialArchivesInfoTableType, OfficialArchivesInfoTable> officialArchivesInfoTables, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找公文档案相关表信息信息
	 * @param pID 指定的唯一标识
	 * @param officialArchivesInfoTable 返回查找成功的公文档案相关表信息信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findOfficialArchivesInfoTableByID(int pID, OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo);

}
