/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultPersionalArchivesMoveOut;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����ͳ�ƽ�����˵���ת��������DAO�ӿڶ���
 *
 */
public interface IReportResultPersionalArchivesMoveOutDao {

	/**
	 * Dao�ӿڶ��壺���ReportResultPersionalArchivesMoveOut
	 * @param pReportResultPersionalArchivesMoveOut Ҫ��ӵ�ReportResultPersionalArchivesMoveOut
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ReportResultPersionalArchivesMoveOut pReportResultPersionalArchivesMoveOut, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ReportResultPersionalArchivesMoveOut
	 * @param pReportResultPersionalArchivesMoveOut Ҫɾ����ReportResultPersionalArchivesMoveOut
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ReportResultPersionalArchivesMoveOut pReportResultPersionalArchivesMoveOut, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ReportResultPersionalArchivesMoveOut
	 * @param pReportResultPersionalArchivesMoveOut Ҫ���µ�ReportResultPersionalArchivesMoveOut
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ReportResultPersionalArchivesMoveOut pReportResultPersionalArchivesMoveOut, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ReportResultPersionalArchivesMoveOut
	 * @param pReportResultPersionalArchivesMoveOuts ���ز��ҳɹ���ReportResultPersionalArchivesMoveOut����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ReportResultPersionalArchivesMoveOut> pReportResultPersionalArchivesMoveOuts, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ReportResultPersionalArchivesMoveOut
	 * @param pID ָ����Ψһ��ʶ
	 * @param pReportResultPersionalArchivesMoveOut ���ز��ҳɹ���ReportResultPersionalArchivesMoveOut
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ReportResultPersionalArchivesMoveOut pReportResultPersionalArchivesMoveOut, ErrInfo pErrInfo);

}
