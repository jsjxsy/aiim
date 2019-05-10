/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.Map;

import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.ErrInfo;

/**
 * SystemFeature�������Ľӿڶ���
 *
 */
public interface ISystemFeatureManageService {

	/**
	 * ���һ���µ�SystemFeature
	 * @param pSystemFeature ����ӵ�SystemFeature��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveSystemFeature(SystemFeature pSystemFeature, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ����SystemFeature
	 * @param pSystemFeature Ҫɾ����SystemFeature����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteSystemFeature(SystemFeature pSystemFeature, ErrInfo pErrInfo);

	/**
	 * �޸�ָ����SystemFeature
	 * @param pSystemFeature �޸ĺ��SystemFeature��Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateSystemFeature(SystemFeature pSystemFeature, ErrInfo pErrInfo);

	/**
	 * �������е�SystemFeature��Ϣ
	 * @param pSystemFeatures ���ز��ҳɹ���SystemFeature����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findSystemFeatures(Map<String,SystemFeature> pSystemFeatures, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ����SystemFeature��Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param pSystemFeature ���ز��ҳɹ���SystemFeature��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findSystemFeatureByID(int pID, SystemFeature pSystemFeature, ErrInfo pErrInfo);

}

