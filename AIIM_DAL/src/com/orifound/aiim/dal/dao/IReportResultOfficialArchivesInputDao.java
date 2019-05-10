/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultOfficialArchivesInput;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����ͳ�ƽ�����ĵ����Ǽ�������DAO�ӿڶ���
 *
 */
public interface IReportResultOfficialArchivesInputDao {

	/**
	 * Dao�ӿڶ��壺���ReportResultOfficialArchivesInput
	 * @param pReportResultOfficialArchivesInput Ҫ��ӵ�ReportResultOfficialArchivesInput
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ReportResultOfficialArchivesInput pReportResultOfficialArchivesInput, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ReportResultOfficialArchivesInput
	 * @param pReportResultOfficialArchivesInput Ҫɾ����ReportResultOfficialArchivesInput
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ReportResultOfficialArchivesInput pReportResultOfficialArchivesInput, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ReportResultOfficialArchivesInput
	 * @param pReportResultOfficialArchivesInput Ҫ���µ�ReportResultOfficialArchivesInput
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ReportResultOfficialArchivesInput pReportResultOfficialArchivesInput, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ReportResultOfficialArchivesInput
	 * @param pReportResultOfficialArchivesInputs ���ز��ҳɹ���ReportResultOfficialArchivesInput����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ReportResultOfficialArchivesInput> pReportResultOfficialArchivesInputs, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ReportResultOfficialArchivesInput
	 * @param pID ָ����Ψһ��ʶ
	 * @param pReportResultOfficialArchivesInput ���ز��ҳɹ���ReportResultOfficialArchivesInput
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ReportResultOfficialArchivesInput pReportResultOfficialArchivesInput, ErrInfo pErrInfo);

}
