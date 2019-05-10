/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.TaskInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.TaskPerson;

/**
 * ������Ϣ�������Ľӿڶ���
 *
 */
public interface ITaskInfoManageService {

	/**
	 * ���һ���µ�������Ϣ
	 * @param taskinfo ����ӵ�������Ϣ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveTaskInfo(TaskInfo taskinfo, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ����������Ϣ
	 * @param taskinfo Ҫɾ����������Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteTaskInfo(TaskInfo taskinfo, ErrInfo pErrInfo);

	/**
	 * �޸�ָ����������Ϣ
	 * @param taskinfo �޸ĺ��������Ϣ��Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateTaskInfo(TaskInfo taskinfo, ErrInfo pErrInfo);

	/**
	 * �������е�������Ϣ
	 * @param taskinfos ���ز��ҳɹ���������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findTaskInfos(List<TaskInfo> taskinfos,
			ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ����������Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param taskinfo ���ز��ҳɹ���������Ϣ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findTaskInfoByID(int pID, TaskInfo taskinfo, ErrInfo pErrInfo);

	/**
	 * ��ҳ��ѯ������Ϣ
	 * @param params �����б� �������Ƿ񷢲�publishFlag�����񷢲���fromUserName��������receiveName���������ڿ�ʼbeginTime������endTime
	 * @param dataPageInfo ����ҳ��Ϣ������ 
	 * @param taskInfos ����������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findWithPage(Map<String, String> params, DataPageInfo dataPageInfo, List<TaskInfo> taskInfos, ErrInfo pErrInfo);
	
	/**
	 * ����ɾ��ָ����������Ϣ
	 * @param taskInfoIds Ҫɾ��������id����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteBatch(List<Integer> taskInfoIds, ErrInfo pErrInfo);
	
	/**
	 * ��������id���������������Ϣ
	 * @param pID ָ����Ψһ����id��ʶ
	 * @param taskPersons ���ز��ҳɹ��Ĺ��������������Ϣ����(ֻ�������������id����)
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findTaskPersonByID(int pID, List<TaskPerson> taskPersons, ErrInfo pErrInfo);
	
	/**
	 * ����Ψһ��ʶ���ҹ���������ϸ��Ϣ(�����ظ���Ϣ)
	 * @param pID ָ����Ψһ��ʶ
	 * @param taskInfo ���ز��ҳɹ��Ĺ���������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findDetailByID(int pID, TaskInfo taskInfo, ErrInfo pErrInfo);
}
