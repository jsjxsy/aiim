/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.EvaluateLevel;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �������ֵȼ��������Ľӿڶ���
 *
 */
public interface IEvaluateLevelManageService {

	/**
	 * ���һ���µĿ������ֵȼ�
	 * @param evaluateLevel ����ӵĿ������ֵȼ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveEvaluateLevel(EvaluateLevel evaluateLevel, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���Ŀ������ֵȼ�
	 * @param evaluateLevel Ҫɾ���Ŀ������ֵȼ�����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteEvaluateLevel(EvaluateLevel evaluateLevel, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���Ŀ������ֵȼ�
	 * @param evaluateLevel �޸ĺ�Ŀ������ֵȼ���Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateEvaluateLevel(EvaluateLevel evaluateLevel, ErrInfo pErrInfo);

	/**
	 * �������еĿ������ֵȼ���Ϣ
	 * @param evaluateLevels ���ز��ҳɹ��Ŀ������ֵȼ�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findEvaluateLevels(List<EvaluateLevel> evaluateLevels,
			ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҿ������ֵȼ���Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param evaluateLevel ���ز��ҳɹ��Ŀ������ֵȼ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findEvaluateLevelByID(int pID, EvaluateLevel evaluateLevel,
			ErrInfo pErrInfo);

}