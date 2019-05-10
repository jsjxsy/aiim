/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.RetentionPeriod;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ���������ֵ���DAO�ӿڶ���
 *
 */
public interface IRetentionPeriodDao
{

	/**
	 * Dao�ӿڶ��壺��ӱ�������
	 * @param retentionPeriod Ҫ��ӵı�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(RetentionPeriod retentionPeriod, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���ı�������
	 * @param retentionPeriod Ҫɾ���ı�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(RetentionPeriod retentionPeriod, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���ı�������
	 * @param retentionPeriod Ҫ���µı�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(RetentionPeriod retentionPeriod, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еı�������
	 * @param retentionPeriods ���ز��ҳɹ��ı������޼���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(LinkedHashMap<Integer,RetentionPeriod> retentionPeriods, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ұ�������
	 * @param pID ָ����Ψһ��ʶ
	 * @param retentionPeriod ���ز��ҳɹ��ı�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, RetentionPeriod retentionPeriod, ErrInfo pErrInfo);
}
