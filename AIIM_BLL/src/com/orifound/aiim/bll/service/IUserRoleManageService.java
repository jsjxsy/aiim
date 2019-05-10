/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.UserRole;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ��ɫ��Ϣ�ֵ��������Ľӿڶ���
 *
 */
public interface IUserRoleManageService {

	/**
	 * ���һ���µĽ�ɫ��Ϣ�ֵ��
	 * @param userRole ����ӵĽ�ɫ��Ϣ�ֵ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveUserRole(UserRole userRole, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���Ľ�ɫ��Ϣ�ֵ��
	 * @param userRole Ҫɾ���Ľ�ɫ��Ϣ�ֵ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteUserRole(UserRole userRole, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���Ľ�ɫ��Ϣ�ֵ��
	 * @param userRole �޸ĺ�Ľ�ɫ��Ϣ�ֵ����Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateUserRole(UserRole userRole, ErrInfo pErrInfo);

	/**
	 * �������еĽ�ɫ��Ϣ�ֵ����Ϣ
	 * @param userRoles ���ز��ҳɹ��Ľ�ɫ��Ϣ�ֵ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserRoles(List<UserRole> userRoles,
			ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҽ�ɫ��Ϣ�ֵ����Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param userRole ���ز��ҳɹ��Ľ�ɫ��Ϣ�ֵ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserRoleByID(int pID, UserRole userRole,
			ErrInfo pErrInfo);

	
	/**
	 * ����ϵͳ���н�ɫ��־�����û���ɫ
	 * @param systemRolesFlag ϵͳ���н�ɫ��־
	 * @param userRoles ���ز��ҳɹ����û���ɫ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserRolesBySystemRolesFlag(int systemRolesFlag, List<UserRole> userRoles,ErrInfo pErrInfo);

	   
	/**
	 * ���Ҳ�����ָ���û������н�ɫ��Ϣ
	 * @param pRoleID ָ���Ľ�ɫ���
	 * @param userRoles ���ز��ҳɹ����û�������ɫ��Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	 boolean findRoleInfosNotInUserID(int pUserID, List<UserRole> userRoles, ErrInfo pErrInfo);



}
