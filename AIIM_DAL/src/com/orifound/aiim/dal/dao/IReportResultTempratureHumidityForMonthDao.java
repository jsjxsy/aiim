/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultTempratureHumidityForMonth;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ������ͳ�ƿⷿ��ʪ���¶ȱ仯������DAO�ӿڶ���
 *
 */
public interface IReportResultTempratureHumidityForMonthDao {

	/**
	 * Dao�ӿڶ��壺���ReportResultTempratureHumidityForMonth
	 * @param pReportResultTempratureHumidityForMonth Ҫ��ӵ�ReportResultTempratureHumidityForMonth
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ReportResultTempratureHumidityForMonth pReportResultTempratureHumidityForMonth, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ReportResultTempratureHumidityForMonth
	 * @param pReportResultTempratureHumidityForMonth Ҫɾ����ReportResultTempratureHumidityForMonth
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ReportResultTempratureHumidityForMonth pReportResultTempratureHumidityForMonth, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ReportResultTempratureHumidityForMonth
	 * @param pReportResultTempratureHumidityForMonth Ҫ���µ�ReportResultTempratureHumidityForMonth
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ReportResultTempratureHumidityForMonth pReportResultTempratureHumidityForMonth, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ReportResultTempratureHumidityForMonth
	 * @param pReportResultTempratureHumidityForMonths ���ز��ҳɹ���ReportResultTempratureHumidityForMonth����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ReportResultTempratureHumidityForMonth> pReportResultTempratureHumidityForMonths, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ReportResultTempratureHumidityForMonth
	 * @param pID ָ����Ψһ��ʶ
	 * @param pReportResultTempratureHumidityForMonth ���ز��ҳɹ���ReportResultTempratureHumidityForMonth
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ReportResultTempratureHumidityForMonth pReportResultTempratureHumidityForMonth, ErrInfo pErrInfo);

}
