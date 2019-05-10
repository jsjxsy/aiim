/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.UserRolesArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * UserRolesArchivesSecrecy���DAO�ӿڶ���
 *
 */
public interface IUserRolesArchivesSecrecyDao {

	/**
	 * Dao�ӿڶ��壺���UserRolesArchivesSecrecy
	 * @param pUserRolesArchivesSecrecy Ҫ��ӵ�UserRolesArchivesSecrecy
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����UserRolesArchivesSecrecy
	 * @param pUserRolesArchivesSecrecy Ҫɾ����UserRolesArchivesSecrecy
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����UserRolesArchivesSecrecy
	 * @param pUserRolesArchivesSecrecy Ҫ���µ�UserRolesArchivesSecrecy
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�UserRolesArchivesSecrecy
	 * @param pUserRolesArchivesSecrecys ���ز��ҳɹ���UserRolesArchivesSecrecy����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<UserRolesArchivesSecrecy> pUserRolesArchivesSecrecys, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����UserRolesArchivesSecrecy
	 * @param pID ָ����Ψһ��ʶ
	 * @param pUserRolesArchivesSecrecy ���ز��ҳɹ���UserRolesArchivesSecrecy
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo);
	
	/**
	 * ���ݽ�ɫ��Ų����û���ɫ�ĵ����ܼ�Ȩ��
	 * @param pRoleID ��ɫ���
	 * @param pUserRolesArchivesSecrecys ���ز��ҳɹ���UserRolesArchivesSecrecy����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByRoleID(int pRoleID, List<UserRolesArchivesSecrecy> pUserRolesArchivesSecrecys, ErrInfo pErrInfo);
	
	/**
	 * ���ݽ�ɫ��Ų����û���ɫ�ĵ����ܼ�Ȩ��
	 * @param pRoleID ��ɫ���
	 * @param pUserRolesArchivesSecrecys ���ز��ҳɹ���UserRolesArchivesSecrecy����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteByRoleID(int pRoleID, ErrInfo pErrInfo);

}
