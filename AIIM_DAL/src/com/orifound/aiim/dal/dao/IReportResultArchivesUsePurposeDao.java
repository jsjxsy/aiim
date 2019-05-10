/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultArchivesUsePurpose;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 报表统计结果档案利用意图情况表的DAO接口定义
 *
 */
public interface IReportResultArchivesUsePurposeDao {

	/**
	 * Dao接口定义：添加ReportResultArchivesUsePurpose
	 * @param pReportResultArchivesUsePurpose 要添加的ReportResultArchivesUsePurpose
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ReportResultArchivesUsePurpose pReportResultArchivesUsePurpose, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的ReportResultArchivesUsePurpose
	 * @param pReportResultArchivesUsePurpose 要删除的ReportResultArchivesUsePurpose
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ReportResultArchivesUsePurpose pReportResultArchivesUsePurpose, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的ReportResultArchivesUsePurpose
	 * @param pReportResultArchivesUsePurpose 要更新的ReportResultArchivesUsePurpose
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ReportResultArchivesUsePurpose pReportResultArchivesUsePurpose, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的ReportResultArchivesUsePurpose
	 * @param pReportResultArchivesUsePurposes 返回查找成功的ReportResultArchivesUsePurpose集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ReportResultArchivesUsePurpose> pReportResultArchivesUsePurposes, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找ReportResultArchivesUsePurpose
	 * @param pID 指定的唯一标识
	 * @param pReportResultArchivesUsePurpose 返回查找成功的ReportResultArchivesUsePurpose
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ReportResultArchivesUsePurpose pReportResultArchivesUsePurpose, ErrInfo pErrInfo);

}
