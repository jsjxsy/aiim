/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.EvaluateItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ��Ч������Ŀ�ֵ�� (DD_EvaluateItem)��DAO�ӿڶ���
 *
 */
public interface IEvaluateItemDao {

	/**
	 * Dao�ӿڶ��壺��ӿ�����Ŀ�ֵ���Ϣ
	 * @param EvaluateItem Ҫ��ӵĿ�����Ŀ�ֵ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(EvaluateItem EvaluateItem, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ŀ�����Ŀ�ֵ���Ϣ
	 * @param EvaluateItem Ҫɾ���Ŀ�����Ŀ�ֵ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(EvaluateItem EvaluateItem, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���Ŀ�����Ŀ�ֵ���Ϣ
	 * @param EvaluateItem Ҫ���µĿ�����Ŀ�ֵ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(EvaluateItem EvaluateItem, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĿ�����Ŀ�ֵ���Ϣ
	 * @param EvaluateItems ���ز��ҳɹ��Ŀ�����Ŀ�ֵ���Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<EvaluateItem> EvaluateItems, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҿ�����Ŀ�ֵ���Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param EvaluateItem ���ز��ҳɹ��Ŀ�����Ŀ�ֵ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, EvaluateItem EvaluateItem, ErrInfo pErrInfo);

}
