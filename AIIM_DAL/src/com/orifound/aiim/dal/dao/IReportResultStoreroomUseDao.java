/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultStoreroomUse;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 报表统计结果表－库房设施利用情况表的DAO接口定义
 *
 */
public interface IReportResultStoreroomUseDao {

	/**
	 * Dao接口定义：添加ReportResultStoreroomUse
	 * @param pReportResultStoreroomUse 要添加的ReportResultStoreroomUse
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ReportResultStoreroomUse pReportResultStoreroomUse, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的ReportResultStoreroomUse
	 * @param pReportResultStoreroomUse 要删除的ReportResultStoreroomUse
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ReportResultStoreroomUse pReportResultStoreroomUse, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的ReportResultStoreroomUse
	 * @param pReportResultStoreroomUse 要更新的ReportResultStoreroomUse
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ReportResultStoreroomUse pReportResultStoreroomUse, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的ReportResultStoreroomUse
	 * @param pReportResultStoreroomUses 返回查找成功的ReportResultStoreroomUse集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ReportResultStoreroomUse> pReportResultStoreroomUses, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找ReportResultStoreroomUse
	 * @param pID 指定的唯一标识
	 * @param pReportResultStoreroomUse 返回查找成功的ReportResultStoreroomUse
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ReportResultStoreroomUse pReportResultStoreroomUse, ErrInfo pErrInfo);

}
