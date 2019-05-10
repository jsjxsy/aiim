/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRightSystemFeature;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �û���ϵͳ������Ȩ���DAO�ӿڶ���
 *
 */
public interface IUserRightSystemFeatureDao
{

	/**
	 * Dao�ӿڶ��壺����û�ϵͳ������Ȩ
	 * @param userRightSystemFeature Ҫ��ӵ��û�ϵͳ������Ȩ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(UserRightSystemFeature userRightSystemFeature, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺����û�ϵͳ������Ȩ
	 * @param userRightSystemFeature Ҫ��ӵ��û�ϵͳ������Ȩ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveUserRightSystemFeatureByUserID(UserInfo userInfo,UserRightSystemFeature userRightSystemFeature, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺ɾ��ָ�����û�ϵͳ������Ȩ
	 * @param userRightSystemFeature Ҫɾ�����û�ϵͳ������Ȩ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(UserRightSystemFeature userRightSystemFeature, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���û���ϵͳ������Ȩ
	 * @param pUserID ָ�����û����
	 * @param userRightSystemFeatures ���ز��ҳɹ����û�ϵͳ������Ȩ���ϣ�ƽ��ṹ��ϵͳ���ܼ��ϣ���С���ܶ��ڸü����У���UclKey��Ϊ���ϼ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByUserID(int pUserID,Map<String,SystemFeature> userRightSystemFeatures, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���û���ϵͳ���ܲ˵���Ȩ
	 * @param pUserID ָ�����û����
	 * @param userRightMenus ���ز��ҳɹ����û�ϵͳһ�����ܲ˵���Ȩ���ϣ���״�ṹ��ϵͳ���ܲ˵����ϣ��ɷ����¼��˵����UclKey��Ϊ���ϼ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findMenusByUserID(int pUserID,LinkedHashMap<String,SystemFeature> userRightMenus, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺ɾ��ָ���û���ָ��ϵͳ���ܵ��û�ϵͳ������Ȩ
	 * @param userInfoָ�����û�
	 * @param userRightSystemFeature Ҫɾ��ָ����ϵͳ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteUserRightSystemFeatureByUserIDAndFeatureID(UserInfo userInfo,UserRightSystemFeature userRightSystemFeature, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺ɾ��ָ���û������е�ϵͳ���ܵ��û�ϵͳ������Ȩ
	 * @param pUserID �û����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean deleteUserRightSystemFeatureByUserID(int pUserID, ErrInfo pErrInfo);

	
}
