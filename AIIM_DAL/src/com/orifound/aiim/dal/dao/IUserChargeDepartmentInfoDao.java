/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.UserChargeDepartmentInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ҵ��רԱ����Ĳ�����Ϣ���DAO�ӿڶ���
 *
 */
public interface IUserChargeDepartmentInfoDao
{

	/**
	 * Dao�ӿڶ��壺���ҵ��רԱ����Ĳ���
	 * @param userChargeDepartmentInfo Ҫ��ӵ�ҵ��רԱ����Ĳ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(UserChargeDepartmentInfo userChargeDepartmentInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ҵ��רԱ����Ĳ���
	 * @param userChargeDepartmentInfo Ҫɾ����ҵ��רԱ����Ĳ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(UserChargeDepartmentInfo userChargeDepartmentInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ҵ��רԱ����Ĳ���
	 * @param userChargeDepartmentInfo Ҫ���µ�ҵ��רԱ����Ĳ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(UserChargeDepartmentInfo userChargeDepartmentInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ��ҵ��רԱ������Ĳ���
	 * @param pUserID ָ�����û����
	 * @param userChargeDepartmentInfo ���ز��ҳɹ���ҵ��רԱ����Ĳ��ţ��Բ��ű����Ϊ���Ϲؼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByUserID(int pUserID, LinkedHashMap<Integer,UserChargeDepartmentInfo> userChargeDepartmentInfos, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺��������û���˸���Ĳ���
	 * @param userChargeDepartmentInfos ���ز��ҳɹ���ҵ��רԱ����Ĳ��ţ��Բ��ű����Ϊ���Ϲؼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllUnchargeDepartment(List<UserChargeDepartmentInfo> userChargeDepartmentInfos, ErrInfo pErrInfo);

}
