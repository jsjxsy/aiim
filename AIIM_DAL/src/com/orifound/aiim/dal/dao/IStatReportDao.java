/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.StatReport;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 统计报表定义表实体类表的DAO接口定义
 *
 */
public interface IStatReportDao {

	/**
	 * Dao接口定义：添加统计报表定义表实体类
	 * @param pStatReport 要添加的统计报表定义表实体类
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(StatReport pStatReport, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的统计报表定义表实体类
	 * @param pStatReport 要删除的StatReport
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(StatReport pStatReport, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的统计报表定义表实体类
	 * @param pStatReport 要更新的统计报表定义表实体类
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(StatReport pStatReport, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的统计报表定义表实体类
	 * @param pStatReports 返回查找成功的统计报表定义表实体类集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<StatReport> pStatReports, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找统计报表定义表实体类
	 * @param pID 指定的唯一标识
	 * @param pStatReport 返回查找成功的统计报表定义表实体类
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, StatReport pStatReport, ErrInfo pErrInfo);

}
