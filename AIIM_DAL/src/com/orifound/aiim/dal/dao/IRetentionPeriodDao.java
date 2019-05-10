/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.RetentionPeriod;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 保管期限字典表的DAO接口定义
 *
 */
public interface IRetentionPeriodDao
{

	/**
	 * Dao接口定义：添加保管期限
	 * @param retentionPeriod 要添加的保管期限
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(RetentionPeriod retentionPeriod, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的保管期限
	 * @param retentionPeriod 要删除的保管期限
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(RetentionPeriod retentionPeriod, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的保管期限
	 * @param retentionPeriod 要更新的保管期限
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(RetentionPeriod retentionPeriod, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的保管期限
	 * @param retentionPeriods 返回查找成功的保管期限集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(LinkedHashMap<Integer,RetentionPeriod> retentionPeriods, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找保管期限
	 * @param pID 指定的唯一标识
	 * @param retentionPeriod 返回查找成功的保管期限
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, RetentionPeriod retentionPeriod, ErrInfo pErrInfo);
}
