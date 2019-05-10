/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultStoreroomUse;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����ͳ�ƽ�����ⷿ��ʩ����������DAO�ӿڶ���
 *
 */
public interface IReportResultStoreroomUseDao {

	/**
	 * Dao�ӿڶ��壺���ReportResultStoreroomUse
	 * @param pReportResultStoreroomUse Ҫ��ӵ�ReportResultStoreroomUse
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ReportResultStoreroomUse pReportResultStoreroomUse, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ReportResultStoreroomUse
	 * @param pReportResultStoreroomUse Ҫɾ����ReportResultStoreroomUse
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ReportResultStoreroomUse pReportResultStoreroomUse, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ReportResultStoreroomUse
	 * @param pReportResultStoreroomUse Ҫ���µ�ReportResultStoreroomUse
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ReportResultStoreroomUse pReportResultStoreroomUse, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ReportResultStoreroomUse
	 * @param pReportResultStoreroomUses ���ز��ҳɹ���ReportResultStoreroomUse����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ReportResultStoreroomUse> pReportResultStoreroomUses, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ReportResultStoreroomUse
	 * @param pID ָ����Ψһ��ʶ
	 * @param pReportResultStoreroomUse ���ز��ҳɹ���ReportResultStoreroomUse
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ReportResultStoreroomUse pReportResultStoreroomUse, ErrInfo pErrInfo);

}
