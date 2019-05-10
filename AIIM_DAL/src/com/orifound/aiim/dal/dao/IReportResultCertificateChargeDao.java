/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultCertificateCharge;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 报表统计结果出证收费情况表的DAO接口定义
 *
 */
public interface IReportResultCertificateChargeDao {

	/**
	 * Dao接口定义：添加ReportResultCertificateCharge
	 * @param pReportResultCertificateCharge 要添加的ReportResultCertificateCharge
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ReportResultCertificateCharge pReportResultCertificateCharge, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的ReportResultCertificateCharge
	 * @param pReportResultCertificateCharge 要删除的ReportResultCertificateCharge
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ReportResultCertificateCharge pReportResultCertificateCharge, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的ReportResultCertificateCharge
	 * @param pReportResultCertificateCharge 要更新的ReportResultCertificateCharge
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ReportResultCertificateCharge pReportResultCertificateCharge, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的ReportResultCertificateCharge
	 * @param pReportResultCertificateCharges 返回查找成功的ReportResultCertificateCharge集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ReportResultCertificateCharge> pReportResultCertificateCharges, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找ReportResultCertificateCharge
	 * @param pID 指定的唯一标识
	 * @param pReportResultCertificateCharge 返回查找成功的ReportResultCertificateCharge
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ReportResultCertificateCharge pReportResultCertificateCharge, ErrInfo pErrInfo);

}
