/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.UserChargeUserInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �û�������Ϣ�������Ľӿڶ���
 *
 */
public interface IUserChargeUserInfoManageService
{

	/**
	 * ���һ���µ��û�������Ϣ
	 * @param userChargeUserInfo ����ӵ��û�������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveUserChargeUserInfo(UserChargeUserInfo userChargeUserInfo, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ�����û�������Ϣ
	 * @param userChargeUserInfo Ҫɾ�����û�������Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteUserChargeUserInfo(UserChargeUserInfo userChargeUserInfo, ErrInfo pErrInfo);

	/**
	 * �޸�ָ�����û�������Ϣ
	 * @param userChargeUserInfo �޸ĺ���û�������Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateUserChargeUserInfo(UserChargeUserInfo userChargeUserInfo, ErrInfo pErrInfo);
	
	/**
	 * ����ָ���û������д�����Ϣ
	 * @param pUserID ָ�����û����
	 * @param userChargeUserInfo ���ز��ҳɹ����û�������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserChargeUserInfosByUserID(int pUserID, List<UserChargeUserInfo> userChargeUserInfos, ErrInfo pErrInfo);

}
