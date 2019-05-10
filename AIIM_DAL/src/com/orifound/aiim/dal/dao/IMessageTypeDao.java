/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.MessageType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ϵͳ��Ϣ���ͱ��DAO�ӿڶ���
 *
 */
public interface IMessageTypeDao {

	/**
	 * Dao�ӿڶ��壺���MessageType
	 * @param pMessageType Ҫ��ӵ�MessageType
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(MessageType pMessageType, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����MessageType
	 * @param pMessageType Ҫɾ����MessageType
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(MessageType pMessageType, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����MessageType
	 * @param pMessageType Ҫ���µ�MessageType
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(MessageType pMessageType, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�MessageType
	 * @param pMessageTypes ���ز��ҳɹ���MessageType����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<MessageType> pMessageTypes, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����MessageType
	 * @param pID ָ����Ψһ��ʶ
	 * @param pMessageType ���ز��ҳɹ���MessageType
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, MessageType pMessageType, ErrInfo pErrInfo);

}
