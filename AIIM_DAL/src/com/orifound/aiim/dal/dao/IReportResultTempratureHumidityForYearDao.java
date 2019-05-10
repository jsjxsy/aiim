/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultTempratureHumidityForYear;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 报表统计结果表－库房温湿度年度变化情况表的DAO接口定义
 *
 */
public interface IReportResultTempratureHumidityForYearDao {

	/**
	 * Dao接口定义：添加ReportResultTempratureHumidityForYear
	 * @param pReportResultTempratureHumidityForYear 要添加的ReportResultTempratureHumidityForYear
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ReportResultTempratureHumidityForYear pReportResultTempratureHumidityForYear, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的ReportResultTempratureHumidityForYear
	 * @param pReportResultTempratureHumidityForYear 要删除的ReportResultTempratureHumidityForYear
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ReportResultTempratureHumidityForYear pReportResultTempratureHumidityForYear, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的ReportResultTempratureHumidityForYear
	 * @param pReportResultTempratureHumidityForYear 要更新的ReportResultTempratureHumidityForYear
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ReportResultTempratureHumidityForYear pReportResultTempratureHumidityForYear, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的ReportResultTempratureHumidityForYear
	 * @param pReportResultTempratureHumidityForYears 返回查找成功的ReportResultTempratureHumidityForYear集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ReportResultTempratureHumidityForYear> pReportResultTempratureHumidityForYears, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找ReportResultTempratureHumidityForYear
	 * @param pID 指定的唯一标识
	 * @param pReportResultTempratureHumidityForYear 返回查找成功的ReportResultTempratureHumidityForYear
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ReportResultTempratureHumidityForYear pReportResultTempratureHumidityForYear, ErrInfo pErrInfo);

}

