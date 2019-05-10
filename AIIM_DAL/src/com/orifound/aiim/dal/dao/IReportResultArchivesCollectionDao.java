/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultArchivesCollection;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �������ϱ��������DAO�ӿڶ���
 *
 */
public interface IReportResultArchivesCollectionDao {

	/**
	 * Dao�ӿڶ��壺���ReportResultArchivesCollection
	 * @param pReportResultArchivesCollection Ҫ��ӵ�ReportResultArchivesCollection
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ReportResultArchivesCollection pReportResultArchivesCollection, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ReportResultArchivesCollection
	 * @param pReportResultArchivesCollection Ҫɾ����ReportResultArchivesCollection
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ReportResultArchivesCollection pReportResultArchivesCollection, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ReportResultArchivesCollection
	 * @param pReportResultArchivesCollection Ҫ���µ�ReportResultArchivesCollection
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ReportResultArchivesCollection pReportResultArchivesCollection, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ReportResultArchivesCollection
	 * @param pReportResultArchivesCollections ���ز��ҳɹ���ReportResultArchivesCollection����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ReportResultArchivesCollection> pReportResultArchivesCollections, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ReportResultArchivesCollection
	 * @param pID ָ����Ψһ��ʶ
	 * @param pReportResultArchivesCollection ���ز��ҳɹ���ReportResultArchivesCollection
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ReportResultArchivesCollection pReportResultArchivesCollection, ErrInfo pErrInfo);

}
