/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.DataSourceItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * DD_DataSourceItem表的DAO接口定义
 *
 */
public interface IDataSourceItemDao
{

	/**
	 * Dao接口定义：添加数据源的数据项
	 * @param dataSourceItem 要添加的数据源的数据项
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(DataSourceItem dataSourceItem, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的数据源的数据项
	 * @param dataSourceItem 要删除的数据源的数据项
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(DataSourceItem dataSourceItem, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的数据源的数据项
	 * @param dataSourceItem 要更新的数据源的数据项
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(DataSourceItem dataSourceItem, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据数据源编号查找数据源的数据项
	 * @param pDataSourceID 指定的数据源编号
	 * @param dataSourceItems 返回查找成功的数据源的数据项集合，以数据源的数据项编号作为关键字
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByDataSourceID(int pDataSourceID, LinkedHashMap<Integer,DataSourceItem> dataSourceItems, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：根据唯一标识查找数据源的数据项
	 * @param pID 指定的唯一标识
	 * @param dataSourceItem 返回查找成功的数据源的数据项
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, DataSourceItem dataSourceItem, ErrInfo pErrInfo);

}
