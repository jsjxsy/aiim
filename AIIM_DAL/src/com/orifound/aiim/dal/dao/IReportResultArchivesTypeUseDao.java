/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultArchivesTypeUse;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ������ͳ�Ʊ���������Դ����������DAO�ӿڶ���
 *
 */
public interface IReportResultArchivesTypeUseDao {

	/**
	 * Dao�ӿڶ��壺���ReportResultArchivesTypeUse
	 * @param pReportResultArchivesTypeUse Ҫ��ӵ�ReportResultArchivesTypeUse
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ReportResultArchivesTypeUse pReportResultArchivesTypeUse, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ReportResultArchivesTypeUse
	 * @param pReportResultArchivesTypeUse Ҫɾ����ReportResultArchivesTypeUse
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ReportResultArchivesTypeUse pReportResultArchivesTypeUse, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ReportResultArchivesTypeUse
	 * @param pReportResultArchivesTypeUse Ҫ���µ�ReportResultArchivesTypeUse
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ReportResultArchivesTypeUse pReportResultArchivesTypeUse, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ReportResultArchivesTypeUse
	 * @param pReportResultArchivesTypeUses ���ز��ҳɹ���ReportResultArchivesTypeUse����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ReportResultArchivesTypeUse> pReportResultArchivesTypeUses, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ReportResultArchivesTypeUse
	 * @param pID ָ����Ψһ��ʶ
	 * @param pReportResultArchivesTypeUse ���ز��ҳɹ���ReportResultArchivesTypeUse
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ReportResultArchivesTypeUse pReportResultArchivesTypeUse, ErrInfo pErrInfo);

}
