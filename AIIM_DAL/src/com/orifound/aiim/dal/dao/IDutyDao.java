/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.Duty;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ְ����Ϣ�����ֵ���DAO�ӿڶ���
 *
 */
public interface IDutyDao {

	/**
	 * Dao�ӿڶ��壺���ְ����Ϣ�����ֵ�
	 * @param duty Ҫ��ӵ�ְ����Ϣ�����ֵ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(Duty duty, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ְ����Ϣ�����ֵ�
	 * @param duty Ҫɾ����ְ����Ϣ�����ֵ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(Duty duty, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ְ����Ϣ�����ֵ�
	 * @param duty Ҫ���µ�ְ����Ϣ�����ֵ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(Duty duty, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ְ����Ϣ�����ֵ伯��
	 * @param dutys ���ز��ҳɹ���ְ����Ϣ�����ֵ伯��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<Duty> dutys, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ְ����Ϣ�����ֵ�
	 * @param pID ָ����Ψһ��ʶ
	 * @param duty ���ز��ҳɹ���ְ����Ϣ�����ֵ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, Duty duty, ErrInfo pErrInfo);

}
