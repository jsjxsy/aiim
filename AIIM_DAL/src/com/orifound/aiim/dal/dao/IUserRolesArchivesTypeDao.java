/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.UserRolesArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * UserRolesArchivesType���DAO�ӿڶ���
 *
 */
public interface IUserRolesArchivesTypeDao {

	/**
	 * Dao�ӿڶ��壺���UserRolesArchivesType
	 * @param pUserRolesArchivesType Ҫ��ӵ�UserRolesArchivesType
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����UserRolesArchivesType
	 * @param pUserRolesArchivesType Ҫɾ����UserRolesArchivesType
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����UserRolesArchivesType
	 * @param pUserRolesArchivesType Ҫ���µ�UserRolesArchivesType
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�UserRolesArchivesType
	 * @param pUserRolesArchivesTypes ���ز��ҳɹ���UserRolesArchivesType����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<UserRolesArchivesType> pUserRolesArchivesTypes, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����UserRolesArchivesType
	 * @param pID ָ����Ψһ��ʶ
	 * @param pUserRolesArchivesType ���ز��ҳɹ���UserRolesArchivesType
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����UserRolesArchivesType����
	 * @param pRoleID ָ����ɫ��Ψһ��ʶ
	 * @param pUserRolesArchivesType ���ز��ҳɹ���UserRolesArchivesType
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByRoleID(int pRoleID, List<UserRolesArchivesType> pUserRolesArchivesTypes, ErrInfo pErrInfo);
	
	/**
	 * ɾ��ָ����ɫ�����е�UserRolesArchivesType����
	 * @param pRoleID ָ����ɫ��Ψһ��ʶ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteByRoleID(int pRoleID, ErrInfo pErrInfo) ;
}

