/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.OfficialArchivesInfoAttachedFile;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesType;

/**
 * 公文档案原文信息表管理服务的接口定义
 *
 */
public interface IOfficialArchivesInfoAttachedFileManageService {

	/**
	 * 添加一个新的公文档案原文信息表
	 * @param officialArchivesInfoAttachedFile 新添加的公文档案原文信息表信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveOfficialArchivesInfoAttachedFile(OfficialArchivesType officialArchivesType,OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile, ErrInfo pErrInfo);

	/**
	 * 删除指定的公文档案原文信息表
	 * @param officialArchivesInfoAttachedFile 要删除的公文档案原文信息表，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteOfficialArchivesInfoAttachedFile(OfficialArchivesType officialArchivesType,
			int officialArchivesInfoAttachedFileID,
			ErrInfo pErrInfo);

	/**
	 * 修改指定的公文档案原文信息表
	 * @param officialArchivesInfoAttachedFile 修改后的公文档案原文信息表信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateOfficialArchivesInfoAttachedFile(OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile, ErrInfo pErrInfo);

	/**
	 * 查找所有的公文档案原文信息表信息
	 * @param officialArchivesInfoAttachedFiles 返回查找成功的公文档案原文信息表集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findOfficialArchivesInfoAttachedFiles(OfficialArchivesType officialArchivesType,int pNBXH,List<OfficialArchivesInfoAttachedFile> officialArchivesInfoAttachedFiles,
			ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找公文档案原文信息表信息
	 * @param pID 指定的唯一标识
	 * @param officialArchivesInfoAttachedFile 返回查找成功的公文档案原文信息表信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findOfficialArchivesInfoAttachedFileByID(OfficialArchivesType officialArchivesType, OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile,
			ErrInfo pErrInfo);

}
