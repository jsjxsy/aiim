/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateDetails;

/**
 * ������ϸ��(EvaluateDetails)��DAO�ӿڶ���
 *
 */
public interface IEvaluateDetailsDao {

	/**
	 * Dao�ӿڶ��壺��ӿ�����ϸ��Ϣ
	 * @param EvaluateDetails Ҫ��ӵĿ�����ϸ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(EvaluateDetails evaluateDetails, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ŀ�����ϸ��Ϣ
	 * @param EvaluateDetails Ҫɾ���Ŀ�����ϸ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(EvaluateDetails evaluateDetails, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���Ŀ�����ϸ��Ϣ
	 * @param EvaluateDetails Ҫ���µĿ�����ϸ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(EvaluateDetails evaluateDetails, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĿ�����ϸ��Ϣ
	 * @param EvaluateDetailss ���ز��ҳɹ��Ŀ�����ϸ��Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<EvaluateDetails> evaluateDetailss, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҿ�����ϸ��Ϣ
	 * @param evaluateRegID ���˵Ǽ���ϢID
	 * @param evaluateDetailss ���ز��ҳɹ��Ŀ�����ϸ��Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByEvaluateRegID(int evaluateRegID, List<EvaluateDetails> evaluateDetailss, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺���뵱�ض��ȵĿ�����ϸ��¼
	 * @param year �������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean insertByYear(String year, ErrInfo pErrInfo);
}