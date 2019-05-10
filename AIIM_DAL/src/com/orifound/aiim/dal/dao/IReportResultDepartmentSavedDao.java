/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultDepartmentSaved;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����ͳ�ƽ�����ű���������DAO�ӿڶ���
 *
 */
public interface IReportResultDepartmentSavedDao {

	/**
	 * Dao�ӿڶ��壺���ReportResultDepartmentSaved 
	 * @param pReportResultDepartmentSaved Ҫ��ӵ�ReportResultDepartmentSaved 
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ReportResultDepartmentSaved pReportResultDepartmentSaved, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ReportResultDepartmentSaved 
	 * @param pReportResultDepartmentSaved Ҫɾ����ReportResultDepartmentSaved 
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ReportResultDepartmentSaved pReportResultDepartmentSaved, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ReportResultDepartmentSaved 
	 * @param pReportResultDepartmentSaved Ҫ���µ�ReportResultDepartmentSaved 
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ReportResultDepartmentSaved pReportResultDepartmentSaved, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ReportResultDepartmentSaved 
	 * @param pReportResultDepartmentSaveds ���ز��ҳɹ���ReportResultDepartmentSaved ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ReportResultDepartmentSaved> pReportResultDepartmentSaveds, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ReportResultDepartmentSaved 
	 * @param pID ָ����Ψһ��ʶ
	 * @param pReportResultDepartmentSaved ���ز��ҳɹ���ReportResultDepartmentSaved 
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ReportResultDepartmentSaved pReportResultDepartmentSaved, ErrInfo pErrInfo);

}
