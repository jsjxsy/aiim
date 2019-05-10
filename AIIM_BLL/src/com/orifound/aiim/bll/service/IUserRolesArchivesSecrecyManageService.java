/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.UserRolesArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * UserRolesArchivesSecrecy�������Ľӿڶ���
 *
 */
public interface IUserRolesArchivesSecrecyManageService {

	/**
	 * ���һ���µ�UserRolesArchivesSecrecy
	 * @param pUserRolesArchivesSecrecy ����ӵ�UserRolesArchivesSecrecy��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveUserRolesArchivesSecrecy(UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ����UserRolesArchivesSecrecy
	 * @param pUserRolesArchivesSecrecy Ҫɾ����UserRolesArchivesSecrecy����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteUserRolesArchivesSecrecy(UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo);

	/**
	 * �޸�ָ����UserRolesArchivesSecrecy
	 * @param pUserRolesArchivesSecrecy �޸ĺ��UserRolesArchivesSecrecy��Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateUserRolesArchivesSecrecy(UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo);

	/**
	 * �������е�UserRolesArchivesSecrecy��Ϣ
	 * @param pUserRolesArchivesSecrecys ���ز��ҳɹ���UserRolesArchivesSecrecy����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserRolesArchivesSecrecys(List<UserRolesArchivesSecrecy> pUserRolesArchivesSecrecys, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ����UserRolesArchivesSecrecy��Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param pUserRolesArchivesSecrecy ���ز��ҳɹ���UserRolesArchivesSecrecy��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserRolesArchivesSecrecyByID(int pID, UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo);
	
	/**
	 * ���ݽ�ɫΨһ��Ų����û���ɫ��Ϣ�����ܼ���Ϣ
	 * @param pRoleID ��ɫΨһ���
	 * @param pUserRolesArchivesSecrecys ���ز��ҳɹ���UserRolesArchivesSecrecy����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserRolesArchivesSecrecyByRoleID(int pRoleID, List<UserRolesArchivesSecrecy> pUserRolesArchivesSecrecys, ErrInfo pErrInfo); 
	
	/**
	 * ɾ��ָ����ɫ�����еĽ�ɫ�����ܼ�Ȩ��
	 * @param pRoleID ��ɫΨһ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteUserRolesArchivesSecrecyByRoleID(int pRoleID,ErrInfo pErrInfo);
	
	/**
	 * ��Ӷ���µ�UserRolesArchivesSecrecy
	 * @param pUserRolesArchivesSecrecys ����ӵ�UserRolesArchivesSecrecy��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveUserRolesArchivesSecrecyS(List<UserRolesArchivesSecrecy> pUserRolesArchivesSecrecys, ErrInfo pErrInfo);
}
