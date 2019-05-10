/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.UserRolesSystemFeature;
import com.orifound.aiim.entity.ErrInfo;

/**
 * UserRolesSystemFeature���DAO�ӿڶ���
 *
 */
public interface IUserRolesSystemFeatureDao {

	/**
	 * Dao�ӿڶ��壺���UserRolesSystemFeature
	 * @param pUserRolesSystemFeature Ҫ��ӵ�UserRolesSystemFeature
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����UserRolesSystemFeature
	 * @param pUserRolesSystemFeature Ҫɾ����UserRolesSystemFeature
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����UserRolesSystemFeature
	 * @param pUserRolesSystemFeature Ҫ���µ�UserRolesSystemFeature
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�UserRolesSystemFeature
	 * @param pUserRolesSystemFeatures ���ز��ҳɹ���UserRolesSystemFeature����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<UserRolesSystemFeature> pUserRolesSystemFeatures, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����UserRolesSystemFeature
	 * @param pID ָ����Ψһ��ʶ
	 * @param pUserRolesSystemFeature ���ز��ҳɹ���UserRolesSystemFeature
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺���ݽ�ɫΨһ��ʶ����UserRolesSystemFeature
	 * @param pRoleID �û���ɫ
	 * @param userRolesMenus ���صĽ�ɫϵͳ���ܲ˵�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findByRoleID(int pRoleID,List<UserRolesSystemFeature> pUserRolesSystemFeatures, ErrInfo pErrInfo);
	
	/**
	 *  Dao�ӿڶ��壺ɾ��ָ����ɫ�����е�serRolesSystemFeature
	 * @param pRoleID ��ɫ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteByRoleID(int pRoleID, ErrInfo pErrInfo);

}
