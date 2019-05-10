/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportPrintSetting;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �����ӡ���ñ���DAO�ӿڶ���
 *
 */
public interface IReportPrintSettingDao {

	/**
	 * Dao�ӿڶ��壺���ReportPrintSetting
	 * @param pReportPrintSetting Ҫ��ӵ�ReportPrintSetting
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ReportPrintSetting pReportPrintSetting, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ReportPrintSetting
	 * @param pReportPrintSetting Ҫɾ����ReportPrintSetting
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ReportPrintSetting pReportPrintSetting, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ReportPrintSetting
	 * @param pReportPrintSetting Ҫ���µ�ReportPrintSetting
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ReportPrintSetting pReportPrintSetting, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ReportPrintSetting
	 * @param pReportPrintSettings ���ز��ҳɹ���ReportPrintSetting����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ReportPrintSetting> pReportPrintSettings, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ReportPrintSetting
	 * @param pID ָ����Ψһ��ʶ
	 * @param pReportPrintSetting ���ز��ҳɹ���ReportPrintSetting
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ReportPrintSetting pReportPrintSetting, ErrInfo pErrInfo);

}
