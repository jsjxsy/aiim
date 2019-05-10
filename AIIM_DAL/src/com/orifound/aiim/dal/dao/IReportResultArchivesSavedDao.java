/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultArchivesSaved;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����ͳ�ƽ����������������DAO�ӿڶ���
 *
 */
public interface IReportResultArchivesSavedDao {

	/**
	 * Dao�ӿڶ��壺���ReportResultArchivesSaved
	 * @param pReportResultArchivesSaved Ҫ��ӵ�ReportResultArchivesSaved
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ReportResultArchivesSaved pReportResultArchivesSaved, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ReportResultArchivesSaved
	 * @param pReportResultArchivesSaved Ҫɾ����ReportResultArchivesSaved
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ReportResultArchivesSaved pReportResultArchivesSaved, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ReportResultArchivesSaved
	 * @param pReportResultArchivesSaved Ҫ���µ�ReportResultArchivesSaved
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ReportResultArchivesSaved pReportResultArchivesSaved, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ReportResultArchivesSaved
	 * @param pReportResultArchivesSaveds ���ز��ҳɹ���ReportResultArchivesSaved����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ReportResultArchivesSaved> pReportResultArchivesSaveds, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ReportResultArchivesSaved
	 * @param pID ָ����Ψһ��ʶ
	 * @param pReportResultArchivesSaved ���ز��ҳɹ���ReportResultArchivesSaved
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ReportResultArchivesSaved pReportResultArchivesSaved, ErrInfo pErrInfo);

}
