/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultWorkProcedure;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 报表结果工作过程情况表的DAO接口定义
 *
 */
public interface IReportResultWorkProcedureDao {

	/**
	 * Dao接口定义：添加ReportResultWorkProcedure
	 * @param pReportResultWorkProcedure 要添加的ReportResultWorkProcedure
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ReportResultWorkProcedure pReportResultWorkProcedure, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的ReportResultWorkProcedure
	 * @param pReportResultWorkProcedure 要删除的ReportResultWorkProcedure
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ReportResultWorkProcedure pReportResultWorkProcedure, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的ReportResultWorkProcedure
	 * @param pReportResultWorkProcedure 要更新的ReportResultWorkProcedure
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ReportResultWorkProcedure pReportResultWorkProcedure, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的ReportResultWorkProcedure
	 * @param pReportResultWorkProcedures 返回查找成功的ReportResultWorkProcedure集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ReportResultWorkProcedure> pReportResultWorkProcedures, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找ReportResultWorkProcedure
	 * @param pID 指定的唯一标识
	 * @param pReportResultWorkProcedure 返回查找成功的ReportResultWorkProcedure
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ReportResultWorkProcedure pReportResultWorkProcedure, ErrInfo pErrInfo);

}
