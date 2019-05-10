/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.UserRolesInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �û���ɫ��Ϣ���DAO�ӿڶ���
 *
 */
public interface IUserRolesInfoDao
{

	/**
	 * Dao�ӿڶ��壺����û�������ɫ����
	 * @param userRolesInfo Ҫ��ӵ��û�������ɫ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(UserRolesInfo userRolesInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ�����û�������ɫ����
	 * @param userRolesInfo Ҫɾ�����û�������ɫ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(UserRolesInfo userRolesInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���û������Ľ�ɫ����
	 * @param pUserID ָ�����û����
	 * @param userRolesInfo ���ز��ҳɹ����û�������ɫ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByUserID(int pUserID, List<UserRolesInfo> userRolesInfos, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺����ָ����ɫ���������û���Ϣ
	 * @param pRoleID ָ���Ľ�ɫ���
	 * @param userRolesInfo ���ز��ҳɹ����û�������ɫ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByRoleID(int pRoleID, List<UserRolesInfo> userRolesInfos, ErrInfo pErrInfo);
	
	/**
	 *  Dao�ӿڶ��壺�������н�ɫ����
	 * @param userRolesInfos ���ز��ҳɹ����û�������ɫ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return
	 */
	boolean findAll(List<UserRolesInfo> userRolesInfos, ErrInfo pErrInfo);
	
	/**
	 * 
	 * @param pID
	 * @param userRolesInfo
	 * @param pErrInfo
	 * @return
	 */
	boolean findByID(int pID,UserRolesInfo userRolesInfo,ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺�޸Ľ�ɫ����
	 * @param userRolesInfo ���ز��ҳɹ����û�������ɫ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return
	 */
	boolean updateUserRole(UserRolesInfo userRolesInfo, ErrInfo pErrInfo);
	
}

