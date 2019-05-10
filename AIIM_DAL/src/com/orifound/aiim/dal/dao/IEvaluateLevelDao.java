/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.EvaluateLevel;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ���˵ȼ��ֵ��(DD_EvaluateLevel)��DAO�ӿڶ���
 *
 */
public interface IEvaluateLevelDao {

	/**
	 * Dao�ӿڶ��壺��ӿ��˵ȼ��ֵ���Ϣ
	 * @param evaluateLevel Ҫ��ӵĿ��˵ȼ��ֵ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(EvaluateLevel evaluateLevel, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ŀ��˵ȼ��ֵ���Ϣ
	 * @param evaluateLevel Ҫɾ���Ŀ��˵ȼ��ֵ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(EvaluateLevel evaluateLevel, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���Ŀ��˵ȼ��ֵ���Ϣ
	 * @param evaluateLevel Ҫ���µĿ��˵ȼ��ֵ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(EvaluateLevel evaluateLevel, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĿ��˵ȼ��ֵ���Ϣ
	 * @param evaluateLevels ���ز��ҳɹ��Ŀ��˵ȼ��ֵ���Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<EvaluateLevel> evaluateLevels, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҿ��˵ȼ��ֵ���Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param evaluateLevel ���ز��ҳɹ��Ŀ��˵ȼ��ֵ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, EvaluateLevel evaluateLevel, ErrInfo pErrInfo);

}
