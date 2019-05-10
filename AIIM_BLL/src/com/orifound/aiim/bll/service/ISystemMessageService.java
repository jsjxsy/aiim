/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemMessage;
import com.orifound.aiim.entity.SystemMessageQueryCondition;

/**
 * ϵͳ��Ϣ����Ľӿڶ���
 *
 */
public interface ISystemMessageService {
	
	/**
	 * ���ҷ��͸�ָ���û�������ϵͳ��Ϣ����ҳ��ȡ��
	 * @param userID �û��ı������
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param systemMessages ���ز��ҳɹ����ϵͳ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findSystemMessages(int[] userID,DataPageInfo dataPageInfo, List<SystemMessage> systemMessages,ErrInfo pErrInfo);
	
	/**
	 * ���ҷ��͸�ָ���û������������ѯ������ϵͳ��Ϣ����ҳ��ȡ��
	 * @param userID �û��ı������
	 * @param systemMessageQueryCondition ��ѯ����
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param systemMessages ���ز��ҳɹ����ϵͳ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findSystemMessages(int[] userID,SystemMessageQueryCondition systemMessageQueryCondition,DataPageInfo dataPageInfo,List<SystemMessage> systemMessages,ErrInfo pErrInfo);
	
	/**
	 * �Ķ�ָ����ŵ�ϵͳ��Ϣ<br>
	 * ����ָ����ŵ�ϵͳ��Ϣ����ͬʱ������Ϣ���Ķ���־����Ϊ�Ѷ�
	 * @param systemMessageID ָ����ϵͳ��Ϣ���
	 * @param systemMessage ����ָ����ŵ�ϵͳ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean readSystemMessage(int systemMessageID,SystemMessage systemMessage,ErrInfo pErrInfo);
	
	/**
	 * ����ϵͳ��Ϣ
	 * @param systemMessage Ҫ���͵�ϵͳ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean sendSystemMessage(SystemMessage systemMessage,ErrInfo pErrInfo);
	
	/**
	 * ��ָ�����û���ɫȺ��ϵͳ��Ϣ<br>
	 * ���ڸý�ɫ���û������յ���ϵͳ��Ϣ
	 * @param userRolesID ָ���Ľ�ɫ�������
	 * @param systemMessage Ҫ���͵�ϵͳ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean sendSystemMessage(int[] userRolesID, SystemMessage systemMessage,ErrInfo pErrInfo);
	
	/**
	 * ��������<br>
	 * �����е�ǰ��ϵͳ�û�������һ��ϵͳ��Ϣ����Ϣ���ݾ��ǹ������ݡ�
	 * @param systemMessage ��������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean givePublicNotices(SystemMessage systemMessage,ErrInfo pErrInfo);
	
	/**
	 * ɾ��ָ����ϵͳ��Ϣ
	 * @param systemMessages Ҫɾ����ϵͳ��Ϣ���ϣ�����Ϣ��Ա�ı�����Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteSystemMessage(List<SystemMessage> systemMessages,ErrInfo pErrInfo);
	
	/**
	 * ���ָ���û�������ϵͳ��Ϣ
	 * @param userID �û��ı������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean clearSystemMessages(int[] userID,ErrInfo pErrInfo);
	
	/**
	 * ͳ��ָ���û���δ����Ϣ����
	 * @param userID
	 * @return ����δ����Ϣ��������
	 */
	int countUnread(int[] userID);
	
	/**
	 * ���ҷ��͸�ָ���û������������ѯ������ϵͳ��Ϣ����ҳ��ȡ��
	 * @param pUserID �û��ı��
	 * @param systemMessageQueryCondition ��ѯ����
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param systemMessages ���ز��ҳɹ����ϵͳ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findSystemMessagesByUserID(int pUserID,SystemMessageQueryCondition systemMessageQueryCondition,DataPageInfo dataPageInfo,List<SystemMessage> systemMessages,ErrInfo pErrInfo);
	
	/**
	 * ����ϵͳ��ϢΨһ��Ų���ϵͳ��Ϣ
	 * @param pID ��Ϣ���
	 * @param systemMessage ���ز��ҳɹ����ϵͳ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findSystemMessageByID(int pID,SystemMessage systemMessage,ErrInfo pErrInfo);
	
	/**
	 * ����ID�Ÿ���ϵͳ��Ϣ�ֶ�
	 * @param systemMessage ���ز��ҳɹ����ϵͳ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateSystemMessage(SystemMessage systemMessage,ErrInfo pErrInfo);
}
