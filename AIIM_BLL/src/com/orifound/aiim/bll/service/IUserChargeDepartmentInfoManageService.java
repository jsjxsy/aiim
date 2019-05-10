/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.UserChargeDepartmentInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ҵ��רԱ�����Ź������Ľӿڶ���
 *
 */
public interface IUserChargeDepartmentInfoManageService
{

	/**
	 * ���һ���µ�ҵ��רԱ������
	 * @param userChargeDepartmentInfo ����ӵ�ҵ��רԱ��������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveUserChargeDepartmentInfo(UserChargeDepartmentInfo userChargeDepartmentInfo, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ����ҵ��רԱ������
	 * @param userChargeDepartmentInfo Ҫɾ����ҵ��רԱ�����ţ���Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteUserChargeDepartmentInfo(UserChargeDepartmentInfo userChargeDepartmentInfo, ErrInfo pErrInfo);

	/**
	 * �޸�ָ����ҵ��רԱ������
	 * @param userChargeDepartmentInfo �޸ĺ��ҵ��רԱ��������Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateUserChargeDepartmentInfo(UserChargeDepartmentInfo userChargeDepartmentInfo, ErrInfo pErrInfo);

	/**
	 * ����ָ��ҵ��רԱ������Ĳ�����Ϣ
	 * @param pUserID ָ�����û����
	 * @param userChargeDepartmentInfo ���ز��ҳɹ���ҵ��רԱ��������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserChargeDepartmentInfosByUserID(int pUserID, LinkedHashMap<Integer,UserChargeDepartmentInfo> userChargeDepartmentInfos, ErrInfo pErrInfo);

	/**
	 * ����ָ��ҵ��רԱδ����Ĳ�����Ϣ
	 * @param userChargeDepartmentInfo ���ز��ҳɹ���ҵ��רԱ��������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllUserUnChargeDepartmentInfos(List<UserChargeDepartmentInfo> userChargeDepartmentInfos, ErrInfo pErrInfo);
}
