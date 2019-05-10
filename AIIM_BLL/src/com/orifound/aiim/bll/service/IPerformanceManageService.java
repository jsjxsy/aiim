/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;



/**
 * ��Ч�������Ľӿڶ���
 * @author tyb
 */
public interface IPerformanceManageService {
	/**
	 * ��⵵���γɲ��š�ҵ��ָ���ҵ���Ա�ض�ʱ�������¼����������<br>
	 * @param recordCondition map���ϱ���:��Ա��ʵ����-��¼���� ��ֵ��
	 * @param userIds ��Աid����
	 * @param beginTime ��⿪ʼʱ��	
	 * @param endTime	������ʱ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	 boolean findRecordCondition(Map<String, Integer> recordCondition, List<Integer> userIds, String beginTime, String endTime, ErrInfo pErrInfo);
	
	 /**
	 * ���ҵ��ָ������Ա�ض�ʱ�������ˡ�����˵���������<br>
	 * @param recordAudit ��װ��Map<String, Integer[]>������Ա��˵�������,String������Ա��ʵ����,Integer[0]��������ˡ�Integer[1]�����
	 * @param userIds ��Աid����
	 * @param beginTime ��⿪ʼʱ��
	 * @param endTime	������ʱ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	 boolean findRecordAuditCondition(Map<String, Integer[]> recordAudit, List<Integer> userIds, String beginTime, String endTime, ErrInfo pErrInfo);
	
	 /**
	 * ��ѯ���о���ҵ��ָ���ҽ�ɫ����Ա��Ϣ����
	 * @param userInfos ���ҳɹ��󷵻ص��û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findBusinessGuids(List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	/**
	 * ��ѯҵ��ָ����ָ�������е�����ְ��Ա
	 * @param businessGuidIds ҵ��ָ������Աid����
	 * @param userIds ������ְ��ԱId����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findPartTimePersons(List<Integer> businessGuidIds, List<Integer> userIds, ErrInfo pErrInfo);
	
	/**
	 * ��Ч����->��⹤������ѯ
	 * @param inStorageCondition ��װ��Map<String, Integer>���浵����������⹤�����
	 * @param beginTime ��⿪ʼʱ��
	 * @param endTime ������ʱ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return boolean ����ɹ�����true�����򷵻�false
	 */
	boolean findInStorageCondition(Map<String, Integer> inStorageCondition, String beginTime, String endTime, ErrInfo pErrInfo);
	
	/**
	 * ��ѯ���о��е��������ҽ�ɫ����Ա��Ϣ����
	 * @param userInfos ���ҳɹ��󷵻ص��û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesinfoManagers(List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	/**
	 * ��Ч����->��������ѯ ���������ҽ������
	 * @param counts  counts[0]δ��������counts[1]�ܽ�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return boolean ����ɹ�����true�����򷵻�false
	 */
	boolean receiverCondition(List<Integer> counts, ErrInfo pErrInfo);
	
}
