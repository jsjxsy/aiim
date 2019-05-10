/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.UserChargeUserInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �û�������Ϣ���DAO�ӿڶ���
 *
 */
public interface IUserChargeUserInfoDao
{

	/**
	 * Dao�ӿڶ��壺����û�������Ϣ
	 * @param userChargeUserInfo Ҫ��ӵ��û�������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(UserChargeUserInfo userChargeUserInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ�����û�������Ϣ
	 * @param userChargeUserInfo Ҫɾ�����û�������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(UserChargeUserInfo userChargeUserInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ�����û�������Ϣ
	 * @param userChargeUserInfo Ҫ���µ��û�������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(UserChargeUserInfo userChargeUserInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���û������д�����Ϣ
	 * @param pUserID ָ�����û����
	 * @param userChargeUserInfos ���ز��ҳɹ����û�������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByUserID(int pUserID, List<UserChargeUserInfo> userChargeUserInfos, ErrInfo pErrInfo);

}
