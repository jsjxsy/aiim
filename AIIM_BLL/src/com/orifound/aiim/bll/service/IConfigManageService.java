/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.Config;
import com.orifound.aiim.entity.ErrInfo;;

/**
 * ϵͳ���ù������Ľӿڶ���
 *
 */
public interface IConfigManageService {

	/**
	 * ���һ��������
	 * @param pConfig Ҫ��ӵ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveConfig(Config pConfig,ErrInfo pErrInfo);
	
	/**
	 * ɾ��ָ��������
	 * @param pConfig Ҫɾ����������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteConfig(Config pConfig,ErrInfo pErrInfo);
	
	/**
	 * ����ָ��������
	 * @param pConfig Ҫ���µ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateConfig(Config pConfig,ErrInfo pErrInfo);
	
	/**
	 * ��ȡָ��������
	 * @param pConfigType Ҫ���ҵ�����������
	 * @param pConfigs ���ҳɹ����ص��������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findConfigByConfigType(String pConfigType,List<Config> pConfigs,ErrInfo pErrInfo);
}
