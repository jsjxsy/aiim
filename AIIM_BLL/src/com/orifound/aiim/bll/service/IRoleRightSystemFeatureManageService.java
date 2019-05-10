/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.RoleRightSystemFeature;
import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.UserRole;

/**
 * ��ɫϵͳ������Ȩ�������ӿ�
 *
 */
public interface IRoleRightSystemFeatureManageService
{
	/**
	 * Ϊָ����ɫ���ϵͳ����Ȩ��<br>
	 * ������һ�����һ��ϵͳ����Ȩ��
	 * @param userRole �û���ɫ��Ϣ�������ֶα��븳ֵ
	 * @param roleRightSystemFeature ��ɫ��ϵͳ����Ȩ�ޣ���ϵͳ���ܱ���ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean saveRightSystemFeatureForRole(UserRole userRole,RoleRightSystemFeature roleRightSystemFeature,ErrInfo pErrInfo);
	
	/**
	 * Ϊָ����ɫ���ϵͳ����Ȩ��<br>
	 * ������һ����Ӷ��ϵͳ����Ȩ��
	 * @param userRole �û���ɫ��Ϣ�������ֶα��븳ֵ
	 * @param roleRightSystemFeatures ��ɫ��ϵͳ����Ȩ�޼��ϣ����Ա�����ϵͳ���ܱ���ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean saveRightSystemFeaturesForRole(UserRole userRole,List<RoleRightSystemFeature> roleRightSystemFeatures,ErrInfo pErrInfo);
	
	/**
	 * Ϊָ����ɫ�Ƴ�ϵͳ����Ȩ��<br>
	 * ������һ���Ƴ�һ��ϵͳ����Ȩ��
	 * @param userRole �û���ɫ��Ϣ�������ֶα��븳ֵ
	 * @param roleRightSystemFeature ��ɫ��ϵͳ����Ȩ�ޣ���ϵͳ���ܱ���ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean deleteRightSystemFeatureForRole(UserRole userRole,RoleRightSystemFeature roleRightSystemFeature,ErrInfo pErrInfo);
	
	/**
	 * Ϊָ����ɫ�Ƴ�ϵͳ����Ȩ��<br>
	 * ������һ���Ƴ����ϵͳ����Ȩ��
	 * @param userRole �û���ɫ��Ϣ�������ֶα��븳ֵ
	 * @param roleRightSystemFeatures ��ɫ��ϵͳ����Ȩ�޼��ϣ����Ա�����ϵͳ���ܱ���ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean deleteRightSystemFeaturesForRole(UserRole userRole,List<RoleRightSystemFeature> roleRightSystemFeatures,ErrInfo pErrInfo);
	
	/**
	 * ��ȡָ����ɫ��ϵͳ���ܲ˵�Ȩ��<br>
	 * @param pRoleID ָ���Ľ�ɫ�������
	 * @param systemFeatures ���ضԸý�ɫֱ����Ȩ�Ķ���ϵͳ���ܲ˵����ϣ�һ���˵���ϣ�����UclKeyΪ���Ϲؼ��֣��¼����ܲ˵����ͨ��ChildSystemFeatures���Է���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findRightSystemFeatureMenusByRolesID(int[] pRoleID,LinkedHashMap<String,SystemFeature> systemFeatures,ErrInfo pErrInfo);
	
	/**
	 * ��ȡָ����ɫ��ϵͳ����Ȩ�ޣ�UCL:�û����ʿ����б�
	 * @param pRoleID ָ���Ľ�ɫ�������
	 * @param systemFeatures ���ضԸý�ɫֱ����Ȩ��ϵͳ���ܼ��ϣ���UclKeyΪ���Ϲؼ��֣�ע��ù��ܼ�����ƽ���͵ģ�û�в�νṹ������С����ȫ�����ڸü�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findRightSystemFeaturesByRolesID(int[] pRoleID,Map<String,SystemFeature> systemFeatures,ErrInfo pErrInfo);
	
}
