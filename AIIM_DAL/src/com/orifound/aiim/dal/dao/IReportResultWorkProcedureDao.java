/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultWorkProcedure;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ��������������������DAO�ӿڶ���
 *
 */
public interface IReportResultWorkProcedureDao {

	/**
	 * Dao�ӿڶ��壺���ReportResultWorkProcedure
	 * @param pReportResultWorkProcedure Ҫ��ӵ�ReportResultWorkProcedure
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ReportResultWorkProcedure pReportResultWorkProcedure, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ReportResultWorkProcedure
	 * @param pReportResultWorkProcedure Ҫɾ����ReportResultWorkProcedure
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ReportResultWorkProcedure pReportResultWorkProcedure, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ReportResultWorkProcedure
	 * @param pReportResultWorkProcedure Ҫ���µ�ReportResultWorkProcedure
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ReportResultWorkProcedure pReportResultWorkProcedure, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ReportResultWorkProcedure
	 * @param pReportResultWorkProcedures ���ز��ҳɹ���ReportResultWorkProcedure����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ReportResultWorkProcedure> pReportResultWorkProcedures, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ReportResultWorkProcedure
	 * @param pID ָ����Ψһ��ʶ
	 * @param pReportResultWorkProcedure ���ز��ҳɹ���ReportResultWorkProcedure
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ReportResultWorkProcedure pReportResultWorkProcedure, ErrInfo pErrInfo);

}
