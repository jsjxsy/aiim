/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultArchivesPublic;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����ͳ�ƽ����������������DAO�ӿڶ���
 *
 */
public interface IReportResultArchivesPublicDao {

	/**
	 * Dao�ӿڶ��壺���ReportResultArchivesPublic
	 * @param pReportResultArchivesPublic Ҫ��ӵ�ReportResultArchivesPublic
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ReportResultArchivesPublic pReportResultArchivesPublic, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ReportResultArchivesPublic
	 * @param pReportResultArchivesPublic Ҫɾ����ReportResultArchivesPublic
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ReportResultArchivesPublic pReportResultArchivesPublic, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ReportResultArchivesPublic
	 * @param pReportResultArchivesPublic Ҫ���µ�ReportResultArchivesPublic
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ReportResultArchivesPublic pReportResultArchivesPublic, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ReportResultArchivesPublic
	 * @param pReportResultArchivesPublics ���ز��ҳɹ���ReportResultArchivesPublic����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ReportResultArchivesPublic> pReportResultArchivesPublics, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ReportResultArchivesPublic
	 * @param pID ָ����Ψһ��ʶ
	 * @param pReportResultArchivesPublic ���ز��ҳɹ���ReportResultArchivesPublic
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ReportResultArchivesPublic pReportResultArchivesPublic, ErrInfo pErrInfo);

}

