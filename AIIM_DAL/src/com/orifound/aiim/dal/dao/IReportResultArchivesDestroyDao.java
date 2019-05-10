/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultArchivesDestroy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����ͳ�ƽ����������������DAO�ӿڶ���
 *
 */
public interface IReportResultArchivesDestroyDao {

	/**
	 * Dao�ӿڶ��壺���ReportResultArchivesDestroy
	 * @param pReportResultArchivesDestroy Ҫ��ӵ�ReportResultArchivesDestroy
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ReportResultArchivesDestroy pReportResultArchivesDestroy, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ReportResultArchivesDestroy
	 * @param pReportResultArchivesDestroy Ҫɾ����ReportResultArchivesDestroy
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ReportResultArchivesDestroy pReportResultArchivesDestroy, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ReportResultArchivesDestroy
	 * @param pReportResultArchivesDestroy Ҫ���µ�ReportResultArchivesDestroy
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ReportResultArchivesDestroy pReportResultArchivesDestroy, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ReportResultArchivesDestroy
	 * @param pReportResultArchivesDestroys ���ز��ҳɹ���ReportResultArchivesDestroy����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ReportResultArchivesDestroy> pReportResultArchivesDestroys, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ReportResultArchivesDestroy
	 * @param pID ָ����Ψһ��ʶ
	 * @param pReportResultArchivesDestroy ���ز��ҳɹ���ReportResultArchivesDestroy
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ReportResultArchivesDestroy pReportResultArchivesDestroy, ErrInfo pErrInfo);

}
