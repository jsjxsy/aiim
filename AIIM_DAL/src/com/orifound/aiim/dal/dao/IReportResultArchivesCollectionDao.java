/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultArchivesCollection;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案集合报表结果表的DAO接口定义
 *
 */
public interface IReportResultArchivesCollectionDao {

	/**
	 * Dao接口定义：添加ReportResultArchivesCollection
	 * @param pReportResultArchivesCollection 要添加的ReportResultArchivesCollection
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ReportResultArchivesCollection pReportResultArchivesCollection, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的ReportResultArchivesCollection
	 * @param pReportResultArchivesCollection 要删除的ReportResultArchivesCollection
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ReportResultArchivesCollection pReportResultArchivesCollection, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的ReportResultArchivesCollection
	 * @param pReportResultArchivesCollection 要更新的ReportResultArchivesCollection
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ReportResultArchivesCollection pReportResultArchivesCollection, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的ReportResultArchivesCollection
	 * @param pReportResultArchivesCollections 返回查找成功的ReportResultArchivesCollection集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ReportResultArchivesCollection> pReportResultArchivesCollections, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找ReportResultArchivesCollection
	 * @param pID 指定的唯一标识
	 * @param pReportResultArchivesCollection 返回查找成功的ReportResultArchivesCollection
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ReportResultArchivesCollection pReportResultArchivesCollection, ErrInfo pErrInfo);

}
