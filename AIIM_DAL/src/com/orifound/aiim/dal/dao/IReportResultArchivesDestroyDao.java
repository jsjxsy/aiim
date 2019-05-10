/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultArchivesDestroy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 报表统计结果档案销毁情况表的DAO接口定义
 *
 */
public interface IReportResultArchivesDestroyDao {

	/**
	 * Dao接口定义：添加ReportResultArchivesDestroy
	 * @param pReportResultArchivesDestroy 要添加的ReportResultArchivesDestroy
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ReportResultArchivesDestroy pReportResultArchivesDestroy, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的ReportResultArchivesDestroy
	 * @param pReportResultArchivesDestroy 要删除的ReportResultArchivesDestroy
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ReportResultArchivesDestroy pReportResultArchivesDestroy, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的ReportResultArchivesDestroy
	 * @param pReportResultArchivesDestroy 要更新的ReportResultArchivesDestroy
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ReportResultArchivesDestroy pReportResultArchivesDestroy, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的ReportResultArchivesDestroy
	 * @param pReportResultArchivesDestroys 返回查找成功的ReportResultArchivesDestroy集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ReportResultArchivesDestroy> pReportResultArchivesDestroys, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找ReportResultArchivesDestroy
	 * @param pID 指定的唯一标识
	 * @param pReportResultArchivesDestroy 返回查找成功的ReportResultArchivesDestroy
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ReportResultArchivesDestroy pReportResultArchivesDestroy, ErrInfo pErrInfo);

}
