/**
 * 
 */
package com.orifound.aiim.bll.service;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemInitializer;

/**
 * ϵͳ��ʼ������Ľӿڶ���
 *
 */
public interface ISystemInitializeService {

	/**
	 * ϵͳ��ʼ������ȡϵͳ�еĸ������ü��ֵ���Ϣ��
	 * @param systemInitializer ��ʼ���ɹ��󷵻ص�ϵͳ��ʼ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean initialize(SystemInitializer systemInitializer,ErrInfo pErrInfo);
}
