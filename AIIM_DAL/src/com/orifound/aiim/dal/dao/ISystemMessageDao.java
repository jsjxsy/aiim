/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.SystemMessage;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemMessageQueryCondition;

/**
 * ϵͳ��Ϣ���DAO�ӿڶ���
 *
 */
public interface ISystemMessageDao {

	/**
	 * Dao�ӿڶ��壺���ϵͳ��Ϣ
	 * @param pSystemMessage Ҫ��ӵ�ϵͳ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(SystemMessage pSystemMessage, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ϵͳ��Ϣ
	 * @param pSystemMessage Ҫɾ����ϵͳ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(SystemMessage pSystemMessage, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ϵͳ��Ϣ
	 * @param pSystemMessage Ҫ���µ�ϵͳ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(SystemMessage pSystemMessage, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ϵͳ��Ϣ
	 * @param pSystemMessages ���ز��ҳɹ���ϵͳ��Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<SystemMessage> pSystemMessages, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ϵͳ��Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param pSystemMessage ���ز��ҳɹ���ϵͳ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, SystemMessage pSystemMessage, ErrInfo pErrInfo);
	
	/**
	 * �����û���Ų�ѯϵͳ��Ϣ����
	 * @param pUserID �û����
	 * @param systemMessageQueryCondition ϵͳ��Ϣ��ѯ����
	 * @param dataPageInfo ��ҳ����
	 * @param systemMessages ���ز��ҳɹ���ϵͳ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByUserID(int pUserID,SystemMessageQueryCondition systemMessageQueryCondition,DataPageInfo dataPageInfo, List<SystemMessage> systemMessages,ErrInfo pErrInfo);

}
