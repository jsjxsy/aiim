/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.DataSourceItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 数据源的数据项管理服务的接口定义
 *
 */
public interface IDataSourceItemManageService
{

	/**
	 * 添加一个新的数据源的数据项
	 * @param dataSourceItem 新添加的数据源的数据项信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveDataSourceItem(DataSourceItem dataSourceItem, ErrInfo pErrInfo);

	/**
	 * 删除指定的数据源的数据项
	 * @param dataSourceItem 要删除的数据源的数据项，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteDataSourceItem(DataSourceItem dataSourceItem, ErrInfo pErrInfo);

	/**
	 * 修改指定的数据源的数据项
	 * @param dataSourceItem 修改后的数据源的数据项信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateDataSourceItem(DataSourceItem dataSourceItem, ErrInfo pErrInfo);

	/**
	 * 查找指定数据源的所有数据项信息
	 * @param dataSourceItems 返回查找成功的数据源的数据项集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findDataSourceItemsByDataSourceID(Integer pDataSourceID, LinkedHashMap<Integer,DataSourceItem> dataSourceItems, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找数据源的数据项信息
	 * @param pID 指定的唯一标识
	 * @param dataSourceItem 返回查找成功的数据源的数据项信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findDataSourceItemByID(int pID, DataSourceItem dataSourceItem, ErrInfo pErrInfo);

}
