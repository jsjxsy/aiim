/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.OfficialArchivesInfoAttachedFile;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesType;

/**
 * 公文档案原文信息表的DAO接口定义
 *
 */
public interface IOfficialArchivesInfoAttachedFileDao {

	/**
	 * Dao接口定义：添加公文档案信息
	 * @param officialArchivesInfoAttachedFile 要添加的公文档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(OfficialArchivesType officialArchivesType,OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的公文档案信息
	 * @param officialArchivesInfoAttachedFile 要删除的公文档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(OfficialArchivesType officialArchivesType,int officialArchivesInfoAttachedFileID, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的公文档案信息
	 * @param officialArchivesInfoAttachedFile 要更新的公文档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(OfficialArchivesType officialArchivesType,OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的公文档案信息
	 * @paramofficialArchivesType
	 * @param officialArchivesInfoAttachedFiles 返回查找成功的公文档案信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(OfficialArchivesType officialArchivesType,int pNBXH,List<OfficialArchivesInfoAttachedFile> officialArchivesInfoAttachedFiles, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找公文档案信息
	 * @param pID 指定的唯一标识
	 * @param officialArchivesInfoAttachedFile 返回查找成功的公文档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(OfficialArchivesType officialArchivesType,
			OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile,
			ErrInfo pErrInfo);
	/**
	 * 
	 * @param officialArchivesType
	 * @param officialArchivesInfoAttachedFile
	 * @param pErrInfo
	 * @return
	 */
	boolean findOfficialArchivesInfoAttachedFileByName(OfficialArchivesType officialArchivesType, OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile, ErrInfo pErrInfo);

}
