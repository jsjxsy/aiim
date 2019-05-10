/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRightSystemFeature;

/**
 * �û�ϵͳ������Ȩ�������ӿ�
 *
 */
public interface IUserRightSystemFeatureManageService
{
	/**
	 * Ϊָ���û����ϵͳ����Ȩ��<br>
	 * ������һ�����һ��ϵͳ����Ȩ��
	 * @param userInfo �û���Ϣ�������ֶα��븳ֵ
	 * @param userRightSystemFeature �û���ϵͳ����Ȩ�ޣ���ϵͳ���ܱ���ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveRightSystemFeatureForUser(UserInfo userInfo,UserRightSystemFeature userRightSystemFeature,ErrInfo pErrInfo);
	
	/**
	 * Ϊָ���û����ϵͳ����Ȩ��<br>
	 * ������һ����Ӷ��ϵͳ����Ȩ��
	 * @param userInfo �û���Ϣ�������ֶα��븳ֵ
	 * @param userRightSystemFeatures �û���ϵͳ����Ȩ�޼��ϣ����Ա�����ϵͳ���ܱ���ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveRightSystemFeaturesForUser(UserInfo userInfo,List<UserRightSystemFeature> userRightSystemFeatures,ErrInfo pErrInfo);
	
	/**
	 * Ϊָ���û��Ƴ�ϵͳ����Ȩ��
	 * @param userInfo �û���Ϣ�������ֶα��븳ֵ
	 * @param userRightSystemFeature �û���ϵͳ����Ȩ�ޣ���ϵͳ���ܱ���ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteRightSystemFeatureForUser(UserInfo userInfo,UserRightSystemFeature userRightSystemFeature,ErrInfo pErrInfo);
	
	/**
	 * Ϊָ���û��Ƴ�ϵͳ����Ȩ��
	 * @param userInfo �û���Ϣ�������ֶα��븳ֵ
	 * @param userRightSystemFeatures �û���ϵͳ����Ȩ�޼��ϣ����Ա�����ϵͳ���ܱ���ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteRightSystemFeaturesForUser(UserInfo userInfo,List<UserRightSystemFeature> userRightSystemFeatures,ErrInfo pErrInfo);
	
	/**
	 * ��ȡ�û�ֱ����Ȩ��ϵͳ���ܲ˵�Ȩ��<br>
	 * ���صĲ˵����ܼ��Ͼ߱���״�ṹ
	 * @param pUserID ָ�����û����
	 * @param systemFeatures ���ضԸ��û�ֱ����Ȩ�Ķ���ϵͳ���ܲ˵����ϣ�һ���˵���ϣ�����UclKeyΪ���Ϲؼ��֣��¼����ܲ˵����ͨ��ChildSystemFeatures���Է���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findRightSystemFeatureMenusByUserID(int pUserID,LinkedHashMap<String,SystemFeature> systemFeatures,ErrInfo pErrInfo);
	
	/**
	 * ��ȡ�û�ֱ����Ȩ��ϵͳ����Ȩ�ޣ�UCL:�û����ʿ����б�
	 * @param pUserID ָ�����û����
	 * @param systemFeatures ���ضԸ��û�ֱ����Ȩ��ϵͳ���ܼ��ϣ���UclKeyΪ���Ϲؼ��֣�ע��ù��ܼ�����ƽ���͵ģ�û�в�νṹ������С����ȫ�����ڸü�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findRightSystemFeaturesByUserID(int pUserID,Map<String,SystemFeature> systemFeatures,ErrInfo pErrInfo);
	
	/**
	 * ��ȡ���е���Ȩ��ϵͳ���ܲ˵�Ȩ��<br>
	 * ���صĲ˵����ܼ��Ͼ߱���״�ṹ
	 * @param pUserID ָ�����û����
	 * @param systemFeatures ���ضԸ��û�ֱ����Ȩ�Ķ���ϵͳ���ܲ˵����ϣ�һ���˵���ϣ�����UclKeyΪ���Ϲؼ��֣��¼����ܲ˵����ͨ��ChildSystemFeatures���Է���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findAllSystemFeature(LinkedHashMap<String,SystemFeature> systemFeatures,ErrInfo pErrInfo);
	
	/**
	 * Ϊָ���û��Ƴ�ϵͳ����Ȩ��
	 * @param pUserID �û����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteRightSystemFeaturesByUserID(int pUserID,ErrInfo pErrInfo);
	
}
