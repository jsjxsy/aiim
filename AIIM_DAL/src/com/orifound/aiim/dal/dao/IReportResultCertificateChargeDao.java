/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultCertificateCharge;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����ͳ�ƽ����֤�շ�������DAO�ӿڶ���
 *
 */
public interface IReportResultCertificateChargeDao {

	/**
	 * Dao�ӿڶ��壺���ReportResultCertificateCharge
	 * @param pReportResultCertificateCharge Ҫ��ӵ�ReportResultCertificateCharge
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ReportResultCertificateCharge pReportResultCertificateCharge, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ReportResultCertificateCharge
	 * @param pReportResultCertificateCharge Ҫɾ����ReportResultCertificateCharge
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ReportResultCertificateCharge pReportResultCertificateCharge, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ReportResultCertificateCharge
	 * @param pReportResultCertificateCharge Ҫ���µ�ReportResultCertificateCharge
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ReportResultCertificateCharge pReportResultCertificateCharge, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ReportResultCertificateCharge
	 * @param pReportResultCertificateCharges ���ز��ҳɹ���ReportResultCertificateCharge����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ReportResultCertificateCharge> pReportResultCertificateCharges, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ReportResultCertificateCharge
	 * @param pID ָ����Ψһ��ʶ
	 * @param pReportResultCertificateCharge ���ز��ҳɹ���ReportResultCertificateCharge
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ReportResultCertificateCharge pReportResultCertificateCharge, ErrInfo pErrInfo);

}
