/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.CheckRule;
import com.orifound.aiim.entity.ErrInfo;

/**
 * DD_CheckRule���DAO�ӿڶ���
 *
 */
public interface ICheckRuleDao
{

	/**
	 * Dao�ӿڶ��壺���У�����
	 * @param checkRule Ҫ��ӵ�У�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(CheckRule checkRule, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����У�����
	 * @param checkRule Ҫɾ����У�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(CheckRule checkRule, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����У�����
	 * @param checkRule Ҫ���µ�У�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(CheckRule checkRule, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�У�����
	 * @param checkRules ���ز��ҳɹ���У����򼯺�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(Map<Integer,CheckRule> checkRules, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����У�����
	 * @param pID ָ����Ψһ��ʶ
	 * @param checkRule ���ز��ҳɹ���У�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, CheckRule checkRule, ErrInfo pErrInfo);

}
