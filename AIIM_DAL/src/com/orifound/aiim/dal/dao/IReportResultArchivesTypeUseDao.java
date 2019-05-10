/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultArchivesTypeUse;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 报表结果统计表档案分类资源利用情况表的DAO接口定义
 *
 */
public interface IReportResultArchivesTypeUseDao {

	/**
	 * Dao接口定义：添加ReportResultArchivesTypeUse
	 * @param pReportResultArchivesTypeUse 要添加的ReportResultArchivesTypeUse
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ReportResultArchivesTypeUse pReportResultArchivesTypeUse, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的ReportResultArchivesTypeUse
	 * @param pReportResultArchivesTypeUse 要删除的ReportResultArchivesTypeUse
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ReportResultArchivesTypeUse pReportResultArchivesTypeUse, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的ReportResultArchivesTypeUse
	 * @param pReportResultArchivesTypeUse 要更新的ReportResultArchivesTypeUse
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ReportResultArchivesTypeUse pReportResultArchivesTypeUse, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的ReportResultArchivesTypeUse
	 * @param pReportResultArchivesTypeUses 返回查找成功的ReportResultArchivesTypeUse集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ReportResultArchivesTypeUse> pReportResultArchivesTypeUses, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找ReportResultArchivesTypeUse
	 * @param pID 指定的唯一标识
	 * @param pReportResultArchivesTypeUse 返回查找成功的ReportResultArchivesTypeUse
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ReportResultArchivesTypeUse pReportResultArchivesTypeUse, ErrInfo pErrInfo);

}
