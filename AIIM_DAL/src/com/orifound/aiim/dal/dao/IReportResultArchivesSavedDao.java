/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultArchivesSaved;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 报表统计结果档案保存情况表的DAO接口定义
 *
 */
public interface IReportResultArchivesSavedDao {

	/**
	 * Dao接口定义：添加ReportResultArchivesSaved
	 * @param pReportResultArchivesSaved 要添加的ReportResultArchivesSaved
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ReportResultArchivesSaved pReportResultArchivesSaved, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的ReportResultArchivesSaved
	 * @param pReportResultArchivesSaved 要删除的ReportResultArchivesSaved
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ReportResultArchivesSaved pReportResultArchivesSaved, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的ReportResultArchivesSaved
	 * @param pReportResultArchivesSaved 要更新的ReportResultArchivesSaved
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ReportResultArchivesSaved pReportResultArchivesSaved, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的ReportResultArchivesSaved
	 * @param pReportResultArchivesSaveds 返回查找成功的ReportResultArchivesSaved集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ReportResultArchivesSaved> pReportResultArchivesSaveds, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找ReportResultArchivesSaved
	 * @param pID 指定的唯一标识
	 * @param pReportResultArchivesSaved 返回查找成功的ReportResultArchivesSaved
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ReportResultArchivesSaved pReportResultArchivesSaved, ErrInfo pErrInfo);

}
