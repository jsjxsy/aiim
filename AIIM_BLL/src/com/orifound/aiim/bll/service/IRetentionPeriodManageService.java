/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.RetentionPeriod;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �������޹������Ľӿڶ���
 *
 */
public interface IRetentionPeriodManageService
{

	/**
	 * ���һ���µı�������
	 * @param retentionPeriod ����ӵı���������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveRetentionPeriod(RetentionPeriod retentionPeriod, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���ı�������
	 * @param retentionPeriod Ҫɾ���ı������ޣ���Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteRetentionPeriod(RetentionPeriod retentionPeriod, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���ı�������
	 * @param retentionPeriod �޸ĺ�ı���������Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateRetentionPeriod(RetentionPeriod retentionPeriod, ErrInfo pErrInfo);

	/**
	 * �������еı���������Ϣ
	 * @param retentionPeriods ���ز��ҳɹ��ı������޼��ϣ��Ա������ޱ����Ϊ�ؼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findRetentionPeriods(LinkedHashMap<Integer,RetentionPeriod> retentionPeriods, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ұ���������Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param retentionPeriod ���ز��ҳɹ��ı���������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findRetentionPeriodByID(int pID, RetentionPeriod retentionPeriod, ErrInfo pErrInfo);
}
