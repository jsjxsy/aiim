package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoAttachedFile;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案信息原文表的Dao接口
 * @author Administrator
 *
 */
public interface IArchivesInfoAttachedFileTypeCodeDao {

	/**
	 * 获取指定文件的原文电子文件信息，获取成功后直接作为属性挂在指定的文件对象下面。
	 * @param archivesType 指定文件所属的档案分类
	 * @param archivesInfo 指定的文件，该对象下的ArchivesInfoAttachedFiles属性保存了获取结果
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findArchivesInfoAttachedFiles(ArchivesType archivesType,ArchivesInfo archivesInfo, ErrInfo pErrInfo);
	
	/**
	 * 获取指定文件的原文电子文件信息
	 * @param archivesType 指定文件所属的档案分类
	 * @param pNBXH 指定文件的内部序号
	 * @param archivesInfoAttachedFiles 获取成功后返回的原文电子文件集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findArchivesInfoAttachedFiles(ArchivesType archivesType,int pNBXH, List<ArchivesInfoAttachedFile> archivesInfoAttachedFiles, ErrInfo pErrInfo);
	
	/**
	 * 添加原文电子文件信息
	 * @param archivesType 所属档案分类
	 * @param archivesInfoAttachedFile 要添加的原文电子文件信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean addArchivesInfoAttachedFile(ArchivesType archivesType,ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo);
	
	/**
	 * 删除指定的原文电子文件信息
	 * @param archivesType 所属档案分类
	 * @param archivesInfoAttachedFileID 要删除的原文电子文件编号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean deleteArchivesInfoAttachedFile(ArchivesType archivesType,int archivesInfoAttachedFileID, ErrInfo pErrInfo);
	
	/**
	 * 按文件名查找原文电子文件信息
	 * @param archivesType 所属档案分类
	 * @param archivesInfoAttachedFile 要查找的原文电子文件
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findArchivesInfoAttachedFileByName(ArchivesType archivesType,ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo);
	
	/**
	 * 按文件序号查找原文电子文件信息
	 * @param archivesType 所属档案分类
	 * @param archivesInfoAttachedFile 要查找的原文电子文件
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findArchivesInfoAttachedFileById(ArchivesType archivesType,ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo);
	
	/**
	 * 更新原文电子文件信息
	 * @param archivesType 所属档案分类
	 * @param archivesInfoAttachedFile 要更新的原文电子文件信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean updateArchivesInfoAttachedFile(ArchivesType archivesType,ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo);
}
