/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.UserRolesSystemFeature;
import com.orifound.aiim.entity.ErrInfo;

/**
 * UserRolesSystemFeature�������Ľӿڶ���
 *
 */
public interface IUserRolesSystemFeatureManageService {

	/**
	 * ���һ���µ�UserRolesSystemFeature
	 * @param pUserRolesSystemFeature ����ӵ�UserRolesSystemFeature��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveUserRolesSystemFeature(UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo);
	
	/**
	 * ��Ӷ���µ�UserRolesSystemFeature
	 * @param pUserRolesSystemFeatures ����ӵ�UserRolesSystemFeature��Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveUserRolesSystemFeatures(List<UserRolesSystemFeature> pUserRolesSystemFeatures, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ����UserRolesSystemFeature
	 * @param pUserRolesSystemFeature Ҫɾ����UserRolesSystemFeature����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteUserRolesSystemFeature(UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo);

	/**
	 * �޸�ָ����UserRolesSystemFeature
	 * @param pUserRolesSystemFeature �޸ĺ��UserRolesSystemFeature��Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateUserRolesSystemFeature(UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo);

	/**
	 * �������е�UserRolesSystemFeature��Ϣ
	 * @param pUserRolesSystemFeatures ���ز��ҳɹ���UserRolesSystemFeature����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserRolesSystemFeatures(List<UserRolesSystemFeature> pUserRolesSystemFeatures, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ����UserRolesSystemFeature��Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param pUserRolesSystemFeature ���ز��ҳɹ���UserRolesSystemFeature��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserRolesSystemFeatureByID(int pID, UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo);

	/**
	 * ���ݽ�ɫΨһ��ʶ����UserRolesSystemFeature��Ϣ
	 * @param pRoleID ָ���Ľ�ɫΨһ��ʶ
	 * @param pUserRolesSystemFeature ���ز��ҳɹ���UserRolesSystemFeature��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserRolesSystemFeatureByRoleID(int pRoleID,List<UserRolesSystemFeature> pUserRolesSystemFeatures, ErrInfo pErrInfo);
	
	/**
	 * ɾ��ָ����ɫ�����е�UserRolesSystemFeature��Ϣ
	 * @param pRoleID ָ���Ľ�ɫΨһ��ʶ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteUserRolesSystemFeaturesByRoleID(int  pRoleID, ErrInfo pErrInfo);
}
