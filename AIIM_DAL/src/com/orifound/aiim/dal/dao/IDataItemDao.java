/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.DataItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * DD_DataItem表的DAO接口定义
 *
 */
public interface IDataItemDao
{

	/**
	 * Dao接口定义：添加数据项
	 * @param dataItem 要添加的数据项
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(DataItem dataItem, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的数据项
	 * @param dataItem 要删除的数据项
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(DataItem dataItem, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的数据项
	 * @param dataItem 要更新的数据项
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(DataItem dataItem, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的数据项
	 * @param dataItems 返回查找成功的数据项集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<DataItem> dataItems, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找数据项
	 * @param pID 指定的唯一标识
	 * @param dataItem 返回查找成功的数据项
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, DataItem dataItem, ErrInfo pErrInfo);

}
