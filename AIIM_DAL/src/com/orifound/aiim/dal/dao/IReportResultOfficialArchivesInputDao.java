/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultOfficialArchivesInput;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 报表统计结果公文档案登记情况表的DAO接口定义
 *
 */
public interface IReportResultOfficialArchivesInputDao {

	/**
	 * Dao接口定义：添加ReportResultOfficialArchivesInput
	 * @param pReportResultOfficialArchivesInput 要添加的ReportResultOfficialArchivesInput
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ReportResultOfficialArchivesInput pReportResultOfficialArchivesInput, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的ReportResultOfficialArchivesInput
	 * @param pReportResultOfficialArchivesInput 要删除的ReportResultOfficialArchivesInput
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ReportResultOfficialArchivesInput pReportResultOfficialArchivesInput, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的ReportResultOfficialArchivesInput
	 * @param pReportResultOfficialArchivesInput 要更新的ReportResultOfficialArchivesInput
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ReportResultOfficialArchivesInput pReportResultOfficialArchivesInput, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的ReportResultOfficialArchivesInput
	 * @param pReportResultOfficialArchivesInputs 返回查找成功的ReportResultOfficialArchivesInput集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ReportResultOfficialArchivesInput> pReportResultOfficialArchivesInputs, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找ReportResultOfficialArchivesInput
	 * @param pID 指定的唯一标识
	 * @param pReportResultOfficialArchivesInput 返回查找成功的ReportResultOfficialArchivesInput
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ReportResultOfficialArchivesInput pReportResultOfficialArchivesInput, ErrInfo pErrInfo);

}
