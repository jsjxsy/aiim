/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultTempratureHumidityForYear;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����ͳ�ƽ�����ⷿ��ʪ����ȱ仯������DAO�ӿڶ���
 *
 */
public interface IReportResultTempratureHumidityForYearDao {

	/**
	 * Dao�ӿڶ��壺���ReportResultTempratureHumidityForYear
	 * @param pReportResultTempratureHumidityForYear Ҫ��ӵ�ReportResultTempratureHumidityForYear
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ReportResultTempratureHumidityForYear pReportResultTempratureHumidityForYear, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ReportResultTempratureHumidityForYear
	 * @param pReportResultTempratureHumidityForYear Ҫɾ����ReportResultTempratureHumidityForYear
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ReportResultTempratureHumidityForYear pReportResultTempratureHumidityForYear, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ReportResultTempratureHumidityForYear
	 * @param pReportResultTempratureHumidityForYear Ҫ���µ�ReportResultTempratureHumidityForYear
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ReportResultTempratureHumidityForYear pReportResultTempratureHumidityForYear, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ReportResultTempratureHumidityForYear
	 * @param pReportResultTempratureHumidityForYears ���ز��ҳɹ���ReportResultTempratureHumidityForYear����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ReportResultTempratureHumidityForYear> pReportResultTempratureHumidityForYears, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ReportResultTempratureHumidityForYear
	 * @param pID ָ����Ψһ��ʶ
	 * @param pReportResultTempratureHumidityForYear ���ز��ҳɹ���ReportResultTempratureHumidityForYear
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ReportResultTempratureHumidityForYear pReportResultTempratureHumidityForYear, ErrInfo pErrInfo);

}

