/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultArchivesUsePurpose;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����ͳ�ƽ������������ͼ������DAO�ӿڶ���
 *
 */
public interface IReportResultArchivesUsePurposeDao {

	/**
	 * Dao�ӿڶ��壺���ReportResultArchivesUsePurpose
	 * @param pReportResultArchivesUsePurpose Ҫ��ӵ�ReportResultArchivesUsePurpose
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ReportResultArchivesUsePurpose pReportResultArchivesUsePurpose, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ReportResultArchivesUsePurpose
	 * @param pReportResultArchivesUsePurpose Ҫɾ����ReportResultArchivesUsePurpose
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ReportResultArchivesUsePurpose pReportResultArchivesUsePurpose, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ReportResultArchivesUsePurpose
	 * @param pReportResultArchivesUsePurpose Ҫ���µ�ReportResultArchivesUsePurpose
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ReportResultArchivesUsePurpose pReportResultArchivesUsePurpose, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ReportResultArchivesUsePurpose
	 * @param pReportResultArchivesUsePurposes ���ز��ҳɹ���ReportResultArchivesUsePurpose����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ReportResultArchivesUsePurpose> pReportResultArchivesUsePurposes, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ReportResultArchivesUsePurpose
	 * @param pID ָ����Ψһ��ʶ
	 * @param pReportResultArchivesUsePurpose ���ز��ҳɹ���ReportResultArchivesUsePurpose
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ReportResultArchivesUsePurpose pReportResultArchivesUsePurpose, ErrInfo pErrInfo);

}
