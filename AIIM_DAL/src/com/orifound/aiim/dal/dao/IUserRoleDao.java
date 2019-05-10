/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.UserRole;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ��ɫ��Ϣ�ֵ���DAO�ӿڶ���
 *
 */
public interface IUserRoleDao {

	/**
	 * Dao�ӿڶ��壺����û���ɫ
	 * @param userRole Ҫ��ӵ��û���ɫ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(UserRole userRole, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ�����û���ɫ
	 * @param userRole Ҫɾ�����û���ɫ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(UserRole userRole, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ�����û���ɫ
	 * @param userRole Ҫ���µ��û���ɫ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(UserRole userRole, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е��û���ɫ
	 * @param userRoles ���ز��ҳɹ����û���ɫ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<UserRole> userRoles, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ�����û���ɫ
	 * @param pID ָ����Ψһ��ʶ
	 * @param userRole ���ز��ҳɹ����û���ɫ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, UserRole userRole, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ϵͳ���н�ɫ��־�����û���ɫ
	 * @param systemRolesFlag ϵͳ���н�ɫ��־
	 * @param userRoles ���ز��ҳɹ����û���ɫ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findBySystemRolesFlag(int systemRolesFlag, List<UserRole> userRoles,ErrInfo pErrInfo);

	
	/**
	 * Dao�ӿڶ��壺��ȡ������ָ���û������н�ɫ��Ϣ
	 * @param pRoleID
	 * @param userRolesInfos
	 * @param pErrInfo
	 * @return
	 */
	boolean findRolesNotInUserID(int pUserID, List<UserRole> userRoles, ErrInfo pErrInfo);


}
