/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultSystemVisit;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 报表统计结果系统访问情况表的DAO接口定义
 *
 */
public interface IReportResultSystemVisitDao {

	/**
	 * Dao接口定义：添加ReportResultSystemVisit
	 * @param pReportResultSystemVisit 要添加的ReportResultSystemVisit
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ReportResultSystemVisit pReportResultSystemVisit, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的ReportResultSystemVisit
	 * @param pReportResultSystemVisit 要删除的ReportResultSystemVisit
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ReportResultSystemVisit pReportResultSystemVisit, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的ReportResultSystemVisit
	 * @param pReportResultSystemVisit 要更新的ReportResultSystemVisit
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ReportResultSystemVisit pReportResultSystemVisit, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的ReportResultSystemVisit
	 * @param pReportResultSystemVisits 返回查找成功的ReportResultSystemVisit集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ReportResultSystemVisit> pReportResultSystemVisits, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找ReportResultSystemVisit
	 * @param pID 指定的唯一标识
	 * @param pReportResultSystemVisit 返回查找成功的ReportResultSystemVisit
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ReportResultSystemVisit pReportResultSystemVisit, ErrInfo pErrInfo);

}
