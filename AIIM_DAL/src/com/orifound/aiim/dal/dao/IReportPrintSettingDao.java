/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportPrintSetting;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 报表打印设置表表的DAO接口定义
 *
 */
public interface IReportPrintSettingDao {

	/**
	 * Dao接口定义：添加ReportPrintSetting
	 * @param pReportPrintSetting 要添加的ReportPrintSetting
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ReportPrintSetting pReportPrintSetting, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的ReportPrintSetting
	 * @param pReportPrintSetting 要删除的ReportPrintSetting
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ReportPrintSetting pReportPrintSetting, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的ReportPrintSetting
	 * @param pReportPrintSetting 要更新的ReportPrintSetting
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ReportPrintSetting pReportPrintSetting, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的ReportPrintSetting
	 * @param pReportPrintSettings 返回查找成功的ReportPrintSetting集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ReportPrintSetting> pReportPrintSettings, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找ReportPrintSetting
	 * @param pID 指定的唯一标识
	 * @param pReportPrintSetting 返回查找成功的ReportPrintSetting
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ReportPrintSetting pReportPrintSetting, ErrInfo pErrInfo);

}
