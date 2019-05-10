/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultSystemVisit;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����ͳ�ƽ��ϵͳ����������DAO�ӿڶ���
 *
 */
public interface IReportResultSystemVisitDao {

	/**
	 * Dao�ӿڶ��壺���ReportResultSystemVisit
	 * @param pReportResultSystemVisit Ҫ��ӵ�ReportResultSystemVisit
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ReportResultSystemVisit pReportResultSystemVisit, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ReportResultSystemVisit
	 * @param pReportResultSystemVisit Ҫɾ����ReportResultSystemVisit
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ReportResultSystemVisit pReportResultSystemVisit, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ReportResultSystemVisit
	 * @param pReportResultSystemVisit Ҫ���µ�ReportResultSystemVisit
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ReportResultSystemVisit pReportResultSystemVisit, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ReportResultSystemVisit
	 * @param pReportResultSystemVisits ���ز��ҳɹ���ReportResultSystemVisit����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ReportResultSystemVisit> pReportResultSystemVisits, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ReportResultSystemVisit
	 * @param pID ָ����Ψһ��ʶ
	 * @param pReportResultSystemVisit ���ز��ҳɹ���ReportResultSystemVisit
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ReportResultSystemVisit pReportResultSystemVisit, ErrInfo pErrInfo);

}
