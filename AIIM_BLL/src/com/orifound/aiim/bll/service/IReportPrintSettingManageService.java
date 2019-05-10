/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ReportPrintSetting;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �����ӡ���ñ�������Ľӿڶ���
 *
 */
public interface IReportPrintSettingManageService {

	/**
	 * ���һ���µ�Entity
	 * @param ReportPrintSetting ����ӵ�Entity��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveReportPrintSetting(ReportPrintSetting ReportPrintSetting, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ����Entity
	 * @param ReportPrintSetting Ҫɾ����Entity����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteReportPrintSetting(ReportPrintSetting ReportPrintSetting, ErrInfo pErrInfo);

	/**
	 * �޸�ָ����Entity
	 * @param ReportPrintSetting �޸ĺ��Entity��Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateReportPrintSetting(ReportPrintSetting ReportPrintSetting, ErrInfo pErrInfo);

	/**
	 * �������е�Entity��Ϣ
	 * @param ReportPrintSettings ���ز��ҳɹ���Entity����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findReportPrintSettings(List<ReportPrintSetting> ReportPrintSettings, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ����Entity��Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param ReportPrintSetting ���ز��ҳɹ���Entity��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findReportPrintSettingByID(int pID, ReportPrintSetting ReportPrintSetting, ErrInfo pErrInfo);

}
