/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultTempratureHumidityForMonth;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 报表结果统计库房温湿度月度变化情况表的DAO接口定义
 *
 */
public interface IReportResultTempratureHumidityForMonthDao {

	/**
	 * Dao接口定义：添加ReportResultTempratureHumidityForMonth
	 * @param pReportResultTempratureHumidityForMonth 要添加的ReportResultTempratureHumidityForMonth
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ReportResultTempratureHumidityForMonth pReportResultTempratureHumidityForMonth, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的ReportResultTempratureHumidityForMonth
	 * @param pReportResultTempratureHumidityForMonth 要删除的ReportResultTempratureHumidityForMonth
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ReportResultTempratureHumidityForMonth pReportResultTempratureHumidityForMonth, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的ReportResultTempratureHumidityForMonth
	 * @param pReportResultTempratureHumidityForMonth 要更新的ReportResultTempratureHumidityForMonth
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ReportResultTempratureHumidityForMonth pReportResultTempratureHumidityForMonth, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的ReportResultTempratureHumidityForMonth
	 * @param pReportResultTempratureHumidityForMonths 返回查找成功的ReportResultTempratureHumidityForMonth集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ReportResultTempratureHumidityForMonth> pReportResultTempratureHumidityForMonths, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找ReportResultTempratureHumidityForMonth
	 * @param pID 指定的唯一标识
	 * @param pReportResultTempratureHumidityForMonth 返回查找成功的ReportResultTempratureHumidityForMonth
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ReportResultTempratureHumidityForMonth pReportResultTempratureHumidityForMonth, ErrInfo pErrInfo);

}
