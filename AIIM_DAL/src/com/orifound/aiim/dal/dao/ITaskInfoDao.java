/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.TaskInfo;
import com.orifound.aiim.entity.TaskPerson;

/**
 * ����������Ϣ�� (TaskInfo)��DAO�ӿڶ���
 *
 */
public interface ITaskInfoDao {

	/**
	 * Dao�ӿڶ��壺��ӹ���������Ϣ
	 * @param taskInfo Ҫ��ӵĹ���������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(TaskInfo taskInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ĺ���������Ϣ
	 * @param taskInfo Ҫɾ���Ĺ���������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(TaskInfo taskInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���Ĺ���������Ϣ
	 * @param taskInfo Ҫ���µĹ���������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(TaskInfo taskInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĹ���������Ϣ
	 * @param taskInfos ���ز��ҳɹ��Ĺ���������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<TaskInfo> taskInfos, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҹ���������Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param taskInfo ���ز��ҳɹ��Ĺ���������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, TaskInfo taskInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҹ���������ϸ��Ϣ(�����ظ���Ϣ)
	 * @param pID ָ����Ψһ��ʶ
	 * @param taskInfo ���ز��ҳɹ��Ĺ���������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findDetailByID(int pID, TaskInfo taskInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺��������id���������������Ϣ
	 * @param pID ָ����Ψһ����id��ʶ
	 * @param taskPersons ���ز��ҳɹ��Ĺ��������������Ϣ����(ֻ�������������id����)
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findTaskPersonByID(int pID, List<TaskPerson> taskPersons, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺��ҳ��ѯ������Ϣ
	 * @param params �����б� ������������־publishFlag�����񷢲���fromUserName��������receiveName���������ڿ�ʼbeginTime������endTime
	 * @param dataPageInfo ����ҳ��Ϣ������ 
	 * @param taskInfos ����������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findWithPage(Map<String, String> params, DataPageInfo dataPageInfo, List<TaskInfo> taskInfos, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺����ɾ��ָ����������Ϣ
	 * @param taskInfoIds Ҫɾ��������id����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteBatch(List<Integer> taskInfoIds, ErrInfo pErrInfo);
}