/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultArchivesUsePerson;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����ͳ�ƽ������������������DAO�ӿڶ���
 *
 */
public interface IReportResultArchivesUsePersonDao {

	/**
	 * Dao�ӿڶ��壺���ReportResultArchivesUsePerson
	 * @param pReportResultArchivesUsePerson Ҫ��ӵ�ReportResultArchivesUsePerson
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ReportResultArchivesUsePerson pReportResultArchivesUsePerson, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ReportResultArchivesUsePerson
	 * @param pReportResultArchivesUsePerson Ҫɾ����ReportResultArchivesUsePerson
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ReportResultArchivesUsePerson pReportResultArchivesUsePerson, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ReportResultArchivesUsePerson
	 * @param pReportResultArchivesUsePerson Ҫ���µ�ReportResultArchivesUsePerson
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ReportResultArchivesUsePerson pReportResultArchivesUsePerson, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ReportResultArchivesUsePerson
	 * @param pReportResultArchivesUsePersons ���ز��ҳɹ���ReportResultArchivesUsePerson����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ReportResultArchivesUsePerson> pReportResultArchivesUsePersons, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ReportResultArchivesUsePerson
	 * @param pID ָ����Ψһ��ʶ
	 * @param pReportResultArchivesUsePerson ���ز��ҳɹ���ReportResultArchivesUsePerson
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ReportResultArchivesUsePerson pReportResultArchivesUsePerson, ErrInfo pErrInfo);

}
