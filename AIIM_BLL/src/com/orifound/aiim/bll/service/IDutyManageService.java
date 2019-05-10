/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.Duty;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ְ����Ϣ�����ֵ�������Ľӿڶ���
 *
 */
public interface IDutyManageService {

	/**
	 * ���һ���µ�ְ����Ϣ�����ֵ�
	 * @param duty ����ӵ�ְ����Ϣ�����ֵ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveDuty(Duty duty, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ����ְ����Ϣ�����ֵ�
	 * @param duty Ҫɾ����ְ����Ϣ�����ֵ䣬��Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteDuty(Duty duty, ErrInfo pErrInfo);

	/**
	 * �޸�ָ����ְ����Ϣ�����ֵ�
	 * @param duty �޸ĺ��ְ����Ϣ�����ֵ���Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateDuty(Duty duty, ErrInfo pErrInfo);

	/**
	 * �������е�ְ����Ϣ�����ֵ���Ϣ
	 * @param dutys ���ز��ҳɹ���ְ����Ϣ�����ֵ伯��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findDutys(List<Duty> dutys,
			ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ����ְ����Ϣ�����ֵ���Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param duty ���ز��ҳɹ���ְ����Ϣ�����ֵ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findDutyByID(int pID, Duty duty,
			ErrInfo pErrInfo);

}
