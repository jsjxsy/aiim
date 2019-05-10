/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.RetentionPeriod;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 保管期限管理服务的接口定义
 *
 */
public interface IRetentionPeriodManageService
{

	/**
	 * 添加一个新的保管期限
	 * @param retentionPeriod 新添加的保管期限信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveRetentionPeriod(RetentionPeriod retentionPeriod, ErrInfo pErrInfo);

	/**
	 * 删除指定的保管期限
	 * @param retentionPeriod 要删除的保管期限，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteRetentionPeriod(RetentionPeriod retentionPeriod, ErrInfo pErrInfo);

	/**
	 * 修改指定的保管期限
	 * @param retentionPeriod 修改后的保管期限信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateRetentionPeriod(RetentionPeriod retentionPeriod, ErrInfo pErrInfo);

	/**
	 * 查找所有的保管期限信息
	 * @param retentionPeriods 返回查找成功的保管期限集合，以保管期限编号作为关键字
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findRetentionPeriods(LinkedHashMap<Integer,RetentionPeriod> retentionPeriods, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找保管期限信息
	 * @param pID 指定的唯一标识
	 * @param retentionPeriod 返回查找成功的保管期限信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findRetentionPeriodByID(int pID, RetentionPeriod retentionPeriod, ErrInfo pErrInfo);
}
