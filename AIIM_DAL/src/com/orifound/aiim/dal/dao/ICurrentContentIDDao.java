/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.CurrentContentID;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 当前案卷号信息表的DAO接口定义
 *
 */
public interface ICurrentContentIDDao
{

	/**
	 * Dao接口定义：添加当前案卷号信息
	 * @param currentContentID 要添加的当前案卷号信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(CurrentContentID currentContentID, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的当前案卷号信息累加1
	 * @param currentContentID 要更新的当前案卷号信息，其档案全宗编号和档案分类编号属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(CurrentContentID currentContentID, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据档案全宗编号和档案分类编号查找当前案卷号信息
	 * @param archivesIDPrefix 
	 * @param currentContentID 返回查找成功的当前案卷号信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByPrefix(String archivesIDPrefix,CurrentContentID currentContentID, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：根据档案全宗编号和档案分类编号查找当前案卷号信息
	 * @param currentContentIDs 返回查找成功的当前案卷号信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<CurrentContentID> currentContentIDs, ErrInfo pErrInfo);

}
