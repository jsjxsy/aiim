/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.RoleRightSystemFeature;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;

/**
 * ��ɫϵͳ����Ȩ�ޱ��DAO�ӿڶ���
 *
 */
public interface IRoleRightSystemFeatureDao
{

	/**
	 * Dao�ӿڶ��壺��ӽ�ɫ��ϵͳ������Ȩ
	 * @param roleRightSystemFeature Ҫ��ӵĽ�ɫ��ϵͳ������Ȩ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(RoleRightSystemFeature roleRightSystemFeature, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ľ�ɫ��ϵͳ������Ȩ
	 * @param roleRightSystemFeature Ҫɾ���Ľ�ɫ��ϵͳ������Ȩ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(RoleRightSystemFeature roleRightSystemFeature, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ɫ��ϵͳ������Ȩ
	 * @param pRoleID ָ���Ľ�ɫ�������
	 * @param roleRightSystemFeature ���ز��ҳɹ��Ľ�ɫϵͳ������Ȩ��ƽ��ṹ��ϵͳ���ܼ��ϣ���С���ܶ��ڸü����У���UclKey��Ϊ���ϼ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByRoleID(int[] pRoleID, Map<String,SystemFeature> roleRightSystemFeatures, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺����ָ����ɫ��ϵͳ���ܲ˵���Ȩ
	 * @param pRoleID ָ���Ľ�ɫ�������
	 * @param roleRightMenus ���ز��ҳɹ��Ľ�ɫϵͳһ�����ܲ˵���Ȩ���ϣ���״�ṹ��ϵͳ���ܲ˵����ϣ��ɷ����¼��˵����UclKey��Ϊ���ϼ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findMenusByRoleID(int[] pRoleID,LinkedHashMap<String,SystemFeature> roleRightMenus, ErrInfo pErrInfo);

}
