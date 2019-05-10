/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.CheckRule;
import com.orifound.aiim.entity.ErrInfo;

/**
 * У�����������Ľӿڶ���
 *
 */
public interface ICheckRuleManageService
{

	/**
	 * ���һ���µ�У�����
	 * @param checkRule ����ӵ�У�������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveCheckRule(CheckRule checkRule, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ����У�����
	 * @param checkRule Ҫɾ����У�������Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteCheckRule(CheckRule checkRule, ErrInfo pErrInfo);

	/**
	 * �޸�ָ����У�����
	 * @param checkRule �޸ĺ��У�������Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateCheckRule(CheckRule checkRule, ErrInfo pErrInfo);

	/**
	 * �������е�У�������Ϣ
	 * @param checkRules ���ز��ҳɹ���У����򼯺�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findCheckRules(Map<Integer,CheckRule> checkRules, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ����У�������Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param checkRule ���ز��ҳɹ���У�������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findCheckRuleByID(int pID, CheckRule checkRule, ErrInfo pErrInfo);

}
