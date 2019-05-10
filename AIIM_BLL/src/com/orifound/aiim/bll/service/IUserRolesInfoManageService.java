/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesInfo;

/**
 * �û�������ɫ�������Ľӿڶ���
 *
 */
public interface IUserRolesInfoManageService
{

	/**
	 * ���һ���µ��û�������ɫ
	 * @param userRolesInfo ����ӵ��û�������ɫ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveUserRolesInfo(UserRolesInfo userRolesInfo, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ�����û�������ɫ
	 * @param userRolesInfo Ҫɾ�����û�������ɫ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteUserRolesInfo(UserRolesInfo userRolesInfo, ErrInfo pErrInfo);

	/**
	 * ����ָ���û������Ľ�ɫ��Ϣ
	 * @param pUserID ָ�����û����
	 * @param userRolesInfo ���ز��ҳɹ����û�������ɫ��Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserRolesInfosByUserID(int pUserID,List<UserRolesInfo> userRolesInfos, ErrInfo pErrInfo);
	
	/**
	 * �������н�ɫ
	 * @param pUserID
	 * @param userRolesInfos
	 * @param pErrInfo
	 * @return
	 */
	boolean findUserRolesInfos(List<UserRolesInfo> userRolesInfos, ErrInfo pErrInfo);
	
	/**
	 * �޸�ָ������ɫ��Ϣ
	 * @param userRolesInfo
	 * @param pErrInfo
	 * @return
	 */
	boolean findUserRoleByID(int pID,UserRolesInfo userRolesInfo, ErrInfo pErrInfo);
	
	/**
	 * 
	 * ����ָ����ɫӵ�е��û��û���Ϣ
	 * @param pUserID ָ���Ľ�ɫ���
	 * @param userRolesInfo ���ز��ҳɹ����û�������ɫ��Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserRolesInfosByRoleID(int pRoleID,List<UserRolesInfo> userRolesInfos, ErrInfo pErrInfo);
	
	
}
