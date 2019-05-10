/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoAttachedFile;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 原文管理服务的接口定义
 *
 */
public interface IAttachedFileManageService {

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
	 * 向指定文件附加原文电子文件信息，附加成功后当前指定文件的ArchivesInfoAttachedFiles属性会同步更新
	 * @param archivesType 指定文件所属的档案分类
	 * @param archivesInfo 指定的文件
	 * @param archivesInfoAttachedFile 要附加的原文电子文件信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean addArchivesInfoAttachedFile(ArchivesType archivesType,ArchivesInfo archivesInfo,ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo);
	
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
	 * 删除指定的原文电子文件信息
	 * @param archivesType 所属档案分类
	 * @param archivesInfoAttachedFile 要删除的原文电子文件，其ID属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteArchivesInfoAttachedFile(ArchivesType archivesType,ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo);
	
	/**
	 * 更新指定的原文电子文件信息
	 * @param archivesType 所属档案分类
	 * @param archivesInfoAttachedFile 要更新的原文电子文件信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateArchivesInfoAttachedFile(ArchivesType archivesType,ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo);
	
	/**
	 * 按文件序号查找原文电子文件信息
	 * @param archivesType 所属档案分类
	 * @param archivesInfoAttachedFile 要查找的原文电子文件
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	 boolean findArchivesInfoAttachedFileById(ArchivesType archivesType,ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo);
	
	/**
	 * 调整指定原文电子文件在所属文件中的次序
	 * @param archivesType 指定文件所属的档案分类
	 * @param archivesInfoAttachedFiles 要调整次序的原文电子文件集合，其成员对象的OrderID属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean sortArchivesInfoAttachedFiles(ArchivesType archivesType,List<ArchivesInfoAttachedFile> archivesInfoAttachedFiles, ErrInfo pErrInfo);
	
	/**
	 * 获取归档后的原文电子文件的存放路径，将存放路径存放在备用属性tag中
	 * @param archivesType 档案分类
	 * @param archivesInfoAttachedFiles 要获取存储路径的原文电子文件集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean getSavedArchivesInfoAttachedFilesSavePath(ArchivesType archivesType,List<ArchivesInfoAttachedFile> archivesInfoAttachedFiles, ErrInfo pErrInfo);
	
}
