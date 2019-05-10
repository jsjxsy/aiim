/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.ErrInfo;

/**
 * SystemFeature���DAO�ӿڶ���
 *
 */
public interface ISystemFeatureDao {

	/**
	 * Dao�ӿڶ��壺���ϵͳ�������ñ�
	 * @param pSystemFeature Ҫ��ӵ�ϵͳ�������ñ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(SystemFeature pSystemFeature, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ϵͳ�������ñ�
	 * @param pSystemFeature Ҫɾ����ϵͳ�������ñ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(SystemFeature pSystemFeature, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ϵͳ�������ñ�
	 * @param pSystemFeature Ҫ���µ�ϵͳ�������ñ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(SystemFeature pSystemFeature, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ϵͳ�������ñ�
	 * @param pSystemFeatures ���ز��ҳɹ���ϵͳ�������ñ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<SystemFeature> pSystemFeatures, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ϵͳ�������ñ�
	 * @param pID ָ����Ψһ��ʶ
	 * @param pSystemFeature ���ز��ҳɹ���ϵͳ�������ñ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, SystemFeature pSystemFeature, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺�������ж���ϵͳ����
	 * @param systemFeatures  ���ض���ϵͳ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllSystemFeature(Map<String,SystemFeature>systemFeatures, ErrInfo pErrInfo);

	/**
	 * �����Ʋ���ϵͳ���ܶ���
	 * @param systemFeature
	 * @param pErrInfo
	 * @return
	 */
	boolean findByUCLKey(SystemFeature systemFeature, ErrInfo pErrInfo);

}
