/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.Config;
import com.orifound.aiim.entity.ErrInfo;

/**
 * Config���Dao�ӿڶ���
 *
 */
public interface IConfigDao {

	/**
	 * Dao�ӿڶ��壺���һ��������
	 * @param config Ҫ��ӵ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(Config config,ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺����ָ��������
	 * @param config Ҫ���µ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(Config config,ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺ɾ��ָ��������
	 * @param config Ҫɾ����������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(Config config,ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺�����������Ͳ�������������
	 * @param pConfigType ��������
	 * @param pConfigs ���ҳɹ��󷵻ص��������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByConfigType(String pConfigType,List<Config> pConfigs,ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺��������ID����������Ϣ
	 * @param pID ������Ϣ��Ψһ���
	 * @param config ���ز��ҳɹ�����������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID,Config config,ErrInfo pErrInfo);
}
