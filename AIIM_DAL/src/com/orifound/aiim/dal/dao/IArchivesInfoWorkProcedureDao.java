/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.ArchivesInfoWorkProcedure;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �����������̼�¼���DAO�ӿڶ���
 *
 */
public interface IArchivesInfoWorkProcedureDao
{

	/**
	 * Dao�ӿڶ��壺��ӵ�������������Ϣ
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param pEntity Ҫ��ӵĵ�������������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ArchivesType archivesType,ArchivesInfoWorkProcedure archivesInfoWorkProcedure, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���ڲ���ŵ��������й���������Ϣ
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param pNBXH ָ�����ڲ����
	 * @param archivesInfoWorkProcedures ���ز��ҳɹ��ĵ�������������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByNBXH(ArchivesType archivesType,int pNBXH, List<ArchivesInfoWorkProcedure> archivesInfoWorkProcedures, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ�����ļ��������Ĺ鵵���̼�¼��Ϣ
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param archivesWorkProcedure Ҫɾ���ĵ����鵵���̼�¼��Ϣ�����ڲ�������Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteForSingleArchives(ArchivesType archivesType,ArchivesInfoWorkProcedure archivesInfoWorkProcedure, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺ɾ��ָ���İ��������鵵���̼�¼��Ϣ<br>
	 * ������ļ���Ӧ�ĵ����鵵���̼�¼��ϢҲһ��ɾ��
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param archivesWorkProcedure Ҫɾ���ĵ����鵵���̼�¼��Ϣ�����ڲ�������Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteForParentArchives(ArchivesType archivesType,ArchivesInfoWorkProcedure archivesInfoWorkProcedure, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺��⵵���γɲ��š�ҵ��ָ���ҵ���Ա�ض�ʱ�������¼����������<br>
	 * @param recordCondition map���ϱ���:��Ա��ʵ����-��¼���� ��ֵ��
	 * @param userIds ��Աid����
	 * @param beginTime ��⿪ʼʱ��	
	 * @param endTime	������ʱ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	 boolean recordCondition(Map<String, Integer> recordCondition, List<Integer> userIds, String beginTime, String endTime, ErrInfo pErrInfo);
	 
	 /**
	 * Dao�ӿڶ��壺���ҵ��ָ������Ա�ض�ʱ�������ˡ�����˵���������<br>
	 * @param recordAudit ��װ��Map<String, Integer[]>������Ա��˵�������,String������Ա��ʵ����,Integer[0]��������ˡ�Integer[1]�����
	 * @param userIds ��Աid����
	 * @param beginTime ��⿪ʼʱ��
	 * @param endTime	������ʱ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	 boolean recordAuditCondition(Map<String, Integer[]> recordAudit, List<Integer> userIds, String beginTime, String endTime, ErrInfo pErrInfo);
	 
	 /**
	 * ��Ч����->��⹤������ѯ
	 * @param inStorageCondition ��װ��Map<String, Integer>���浵����������⹤�����
	 * @param beginTime ��⿪ʼʱ��
	 * @param endTime ������ʱ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return boolean ����ɹ�����true�����򷵻�false
	 */
	boolean findInStorageCondition(Map<String, Integer> inStorageCondition, List<Integer> userIds, String beginTime, String endTime, ErrInfo pErrInfo);
}
