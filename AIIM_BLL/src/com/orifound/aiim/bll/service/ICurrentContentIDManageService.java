/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.CurrentContentID;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 当前案卷号管理服务的接口定义
 *
 */
public interface ICurrentContentIDManageService
{

	/**
	 * 添加一个新的当前案卷号
	 * @param currentContentID 新添加的当前案卷号信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveCurrentContentID(CurrentContentID currentContentID, ErrInfo pErrInfo);

	/**
	 * 修改指定的当前案卷号计数器累加1
	 * @param currentContentID 指定的当前案卷号信息，其档案全宗编号和档案分类编号属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateCurrentContentID(CurrentContentID currentContentID, ErrInfo pErrInfo);

	/**
	 * 根据档案全宗编号和档案分类编号查找当前案卷号信息
	 * @param pArchivesFondsID 指定的档案全宗编号
	 * @param pArchivesTypeID 指定的档案分类编号
	 * @param currentContentID 返回查找成功的当前案卷号信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findCurrentContentIDByPrefix(String archivesIDPrefix, CurrentContentID currentContentID, ErrInfo pErrInfo);

	/**
	 * 查找所有全宗的案卷号信息
	 * @param currentContentIDs 返回查找成功的案卷号信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<CurrentContentID> currentContentIDs, ErrInfo pErrInfo);
}
