/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.DataSource;
import com.orifound.aiim.entity.ErrInfo;

/**
 * DD_DataSource表的DAO接口定义
 *
 */
public interface IDataSourceDao
{

	/**
	 * Dao接口定义：添加数据源
	 * @param dataSource 要添加的数据源
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(DataSource dataSource, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的数据源
	 * @param dataSource 要删除的数据源
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(DataSource dataSource, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的数据源
	 * @param dataSource 要更新的数据源
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(DataSource dataSource, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的数据源
	 * @param dataSources 返回查找成功的数据源集合，以数据源编号作为关键字
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(Map<Integer,DataSource> dataSources, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找数据源
	 * @param pID 指定的唯一标识
	 * @param dataSource 返回查找成功的数据源
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, DataSource dataSource, ErrInfo pErrInfo);

}
