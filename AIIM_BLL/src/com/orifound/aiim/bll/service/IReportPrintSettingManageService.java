/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ReportPrintSetting;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 报表打印设置表管理服务的接口定义
 *
 */
public interface IReportPrintSettingManageService {

	/**
	 * 添加一个新的Entity
	 * @param ReportPrintSetting 新添加的Entity信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveReportPrintSetting(ReportPrintSetting ReportPrintSetting, ErrInfo pErrInfo);

	/**
	 * 删除指定的Entity
	 * @param ReportPrintSetting 要删除的Entity，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteReportPrintSetting(ReportPrintSetting ReportPrintSetting, ErrInfo pErrInfo);

	/**
	 * 修改指定的Entity
	 * @param ReportPrintSetting 修改后的Entity信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateReportPrintSetting(ReportPrintSetting ReportPrintSetting, ErrInfo pErrInfo);

	/**
	 * 查找所有的Entity信息
	 * @param ReportPrintSettings 返回查找成功的Entity集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findReportPrintSettings(List<ReportPrintSetting> ReportPrintSettings, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找Entity信息
	 * @param pID 指定的唯一标识
	 * @param ReportPrintSetting 返回查找成功的Entity信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findReportPrintSettingByID(int pID, ReportPrintSetting ReportPrintSetting, ErrInfo pErrInfo);

}
