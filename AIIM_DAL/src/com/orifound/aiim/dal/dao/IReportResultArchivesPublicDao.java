/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultArchivesPublic;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 报表统计结果档案开放情况表的DAO接口定义
 *
 */
public interface IReportResultArchivesPublicDao {

	/**
	 * Dao接口定义：添加ReportResultArchivesPublic
	 * @param pReportResultArchivesPublic 要添加的ReportResultArchivesPublic
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ReportResultArchivesPublic pReportResultArchivesPublic, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的ReportResultArchivesPublic
	 * @param pReportResultArchivesPublic 要删除的ReportResultArchivesPublic
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ReportResultArchivesPublic pReportResultArchivesPublic, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的ReportResultArchivesPublic
	 * @param pReportResultArchivesPublic 要更新的ReportResultArchivesPublic
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ReportResultArchivesPublic pReportResultArchivesPublic, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的ReportResultArchivesPublic
	 * @param pReportResultArchivesPublics 返回查找成功的ReportResultArchivesPublic集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ReportResultArchivesPublic> pReportResultArchivesPublics, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找ReportResultArchivesPublic
	 * @param pID 指定的唯一标识
	 * @param pReportResultArchivesPublic 返回查找成功的ReportResultArchivesPublic
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ReportResultArchivesPublic pReportResultArchivesPublic, ErrInfo pErrInfo);

}

