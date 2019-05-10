/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.MessageType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ϵͳ��Ϣ���͹������Ľӿڶ���
 *
 */
public interface IMessageTypeManageService {

	/**
	 * ���һ���µ�ϵͳ��Ϣ����
	 * @param pMessageType ����ӵ�ϵͳ��Ϣ������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveMessageType(MessageType pMessageType, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ����ϵͳ��Ϣ����
	 * @param pMessageType Ҫɾ����ϵͳ��Ϣ���ͣ���Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteMessageType(MessageType pMessageType, ErrInfo pErrInfo);

	/**
	 * �޸�ָ����ϵͳ��Ϣ����
	 * @param pMessageType �޸ĺ��ϵͳ��Ϣ������Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateMessageType(MessageType pMessageType, ErrInfo pErrInfo);

	/**
	 * �������е�ϵͳ��Ϣ������Ϣ
	 * @param pMessageTypes ���ز��ҳɹ���ϵͳ��Ϣ���ͼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findMessageTypes(List<MessageType> pMessageTypes, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ����ϵͳ��Ϣ������Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param pMessageType ���ز��ҳɹ���ϵͳ��Ϣ������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findMessageTypeByID(int pID, MessageType pMessageType, ErrInfo pErrInfo);

}
