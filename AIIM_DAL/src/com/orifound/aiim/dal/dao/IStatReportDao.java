/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.StatReport;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ͳ�Ʊ������ʵ������DAO�ӿڶ���
 *
 */
public interface IStatReportDao {

	/**
	 * Dao�ӿڶ��壺���ͳ�Ʊ������ʵ����
	 * @param pStatReport Ҫ��ӵ�ͳ�Ʊ������ʵ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(StatReport pStatReport, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ͳ�Ʊ������ʵ����
	 * @param pStatReport Ҫɾ����StatReport
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(StatReport pStatReport, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ͳ�Ʊ������ʵ����
	 * @param pStatReport Ҫ���µ�ͳ�Ʊ������ʵ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(StatReport pStatReport, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ͳ�Ʊ������ʵ����
	 * @param pStatReports ���ز��ҳɹ���ͳ�Ʊ������ʵ���༯��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<StatReport> pStatReports, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ͳ�Ʊ������ʵ����
	 * @param pID ָ����Ψһ��ʶ
	 * @param pStatReport ���ز��ҳɹ���ͳ�Ʊ������ʵ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, StatReport pStatReport, ErrInfo pErrInfo);

}
