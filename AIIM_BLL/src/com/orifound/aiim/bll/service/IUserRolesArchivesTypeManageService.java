/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.UserRolesArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * UserRolesArchivesType�������Ľӿڶ���
 *
 */
public interface IUserRolesArchivesTypeManageService {

	/**
	 * ���һ���µ�UserRolesArchivesType
	 * @param pUserRolesArchivesType ����ӵ�UserRolesArchivesType��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveUserRolesArchivesType(UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ����UserRolesArchivesType
	 * @param pUserRolesArchivesType Ҫɾ����UserRolesArchivesType����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteUserRolesArchivesType(UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo);

	/**
	 * �޸�ָ����UserRolesArchivesType
	 * @param pUserRolesArchivesType �޸ĺ��UserRolesArchivesType��Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateUserRolesArchivesType(UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo);

	/**
	 * �������е�UserRolesArchivesType��Ϣ
	 * @param pUserRolesArchivesTypes ���ز��ҳɹ���UserRolesArchivesType����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserRolesArchivesTypes(List<UserRolesArchivesType> pUserRolesArchivesTypes, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ����UserRolesArchivesType��Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param pUserRolesArchivesType ���ز��ҳɹ���UserRolesArchivesType��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserRolesArchivesTypeByID(int pID, UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ����UserRolesArchivesType��Ϣ
	 * @param RoleID ָ���Ľ�ɫΨһ��ʶ
	 * @param pUserRolesArchivesType ���ز��ҳɹ���UserRolesArchivesType��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserRolesArchivesTypeByRoleID(int RoleID,List<UserRolesArchivesType> pUserRolesArchivesTypes, ErrInfo pErrInfo);
	
	/**
	 * ɾ��ָ����ɫ�����еĵ���������Դ
	 * @param RoleID ָ���Ľ�ɫΨһ��ʶ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteUserRolesArchivesTypeByRoleID(int RoleID, ErrInfo pErrInfo);
	
	/**
	 * ��Ӷ���µ�UserRolesArchivesType
	 * @param pUserRolesArchivesTypes Ҫ��ӵ�UserRolesArchivesType����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean saveUserRolesArchivesTypes(List<UserRolesArchivesType> pUserRolesArchivesTypes, ErrInfo pErrInfo);
}
