/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultPersionalArchivesMoveOut;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 报表统计结果个人档案转出情况表的DAO接口定义
 *
 */
public interface IReportResultPersionalArchivesMoveOutDao {

	/**
	 * Dao接口定义：添加ReportResultPersionalArchivesMoveOut
	 * @param pReportResultPersionalArchivesMoveOut 要添加的ReportResultPersionalArchivesMoveOut
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ReportResultPersionalArchivesMoveOut pReportResultPersionalArchivesMoveOut, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的ReportResultPersionalArchivesMoveOut
	 * @param pReportResultPersionalArchivesMoveOut 要删除的ReportResultPersionalArchivesMoveOut
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ReportResultPersionalArchivesMoveOut pReportResultPersionalArchivesMoveOut, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的ReportResultPersionalArchivesMoveOut
	 * @param pReportResultPersionalArchivesMoveOut 要更新的ReportResultPersionalArchivesMoveOut
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ReportResultPersionalArchivesMoveOut pReportResultPersionalArchivesMoveOut, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的ReportResultPersionalArchivesMoveOut
	 * @param pReportResultPersionalArchivesMoveOuts 返回查找成功的ReportResultPersionalArchivesMoveOut集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ReportResultPersionalArchivesMoveOut> pReportResultPersionalArchivesMoveOuts, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找ReportResultPersionalArchivesMoveOut
	 * @param pID 指定的唯一标识
	 * @param pReportResultPersionalArchivesMoveOut 返回查找成功的ReportResultPersionalArchivesMoveOut
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ReportResultPersionalArchivesMoveOut pReportResultPersionalArchivesMoveOut, ErrInfo pErrInfo);

}
