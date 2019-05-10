/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.DataSource;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 数据源管理服务的接口定义
 *
 */
public interface IDataSourceManageService
{

	/**
	 * 添加一个新的数据源
	 * @param dataSource 新添加的数据源信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveDataSource(DataSource dataSource, ErrInfo pErrInfo);

	/**
	 * 删除指定的数据源
	 * @param dataSource 要删除的数据源，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteDataSource(DataSource dataSource, ErrInfo pErrInfo);

	/**
	 * 修改指定的数据源
	 * @param dataSource 修改后的数据源信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateDataSource(DataSource dataSource, ErrInfo pErrInfo);

	/**
	 * 查找所有的数据源信息
	 * @param dataSources 返回查找成功的数据源集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findDataSources(Map<Integer, DataSource> dataSources, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找数据源信息
	 * @param pID 指定的唯一标识
	 * @param dataSource 返回查找成功的数据源信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findDataSourceByID(int pID, DataSource dataSource, ErrInfo pErrInfo);

}
