/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultDepartmentSaved;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 报表统计结果部门保存情况表的DAO接口定义
 *
 */
public interface IReportResultDepartmentSavedDao {

	/**
	 * Dao接口定义：添加ReportResultDepartmentSaved 
	 * @param pReportResultDepartmentSaved 要添加的ReportResultDepartmentSaved 
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ReportResultDepartmentSaved pReportResultDepartmentSaved, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的ReportResultDepartmentSaved 
	 * @param pReportResultDepartmentSaved 要删除的ReportResultDepartmentSaved 
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ReportResultDepartmentSaved pReportResultDepartmentSaved, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的ReportResultDepartmentSaved 
	 * @param pReportResultDepartmentSaved 要更新的ReportResultDepartmentSaved 
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ReportResultDepartmentSaved pReportResultDepartmentSaved, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的ReportResultDepartmentSaved 
	 * @param pReportResultDepartmentSaveds 返回查找成功的ReportResultDepartmentSaved 集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ReportResultDepartmentSaved> pReportResultDepartmentSaveds, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找ReportResultDepartmentSaved 
	 * @param pID 指定的唯一标识
	 * @param pReportResultDepartmentSaved 返回查找成功的ReportResultDepartmentSaved 
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ReportResultDepartmentSaved pReportResultDepartmentSaved, ErrInfo pErrInfo);

}
