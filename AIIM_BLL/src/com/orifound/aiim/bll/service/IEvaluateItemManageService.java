/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.EvaluateItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ������������Ľӿڶ���
 *
 */
public interface IEvaluateItemManageService {

	/**
	 * ���һ���µĿ�����
	 * @param evaluateItem ����ӵĿ�������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveEvaluateItem(EvaluateItem evaluateItem, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���Ŀ�����
	 * @param evaluateItem Ҫɾ���Ŀ������Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteEvaluateItem(EvaluateItem evaluateItem, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���Ŀ�����
	 * @param evaluateItem �޸ĺ�Ŀ�������Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateEvaluateItem(EvaluateItem evaluateItem, ErrInfo pErrInfo);

	/**
	 * �������еĿ�������Ϣ
	 * @param evaluateItems ���ز��ҳɹ��Ŀ������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findEvaluateItems(List<EvaluateItem> evaluateItems,
			ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҿ�������Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param evaluateItem ���ز��ҳɹ��Ŀ�������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findEvaluateItemByID(int pID, EvaluateItem evaluateItem,
			ErrInfo pErrInfo);

}
