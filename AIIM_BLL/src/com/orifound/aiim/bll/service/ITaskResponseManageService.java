/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.TaskResponse;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����ظ���Ϣ�������Ľӿڶ���
 *
 */
public interface ITaskResponseManageService {

	/**
	 * ���һ���µ�����ظ���Ϣ
	 * @param taskResponse ����ӵ�����ظ���Ϣ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveTaskResponse(TaskResponse taskResponse, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ��������ظ���Ϣ
	 * @param taskResponse Ҫɾ��������ظ���Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteTaskResponse(TaskResponse taskResponse, ErrInfo pErrInfo);

	/**
	 * �޸�ָ��������ظ���Ϣ
	 * @param taskResponse �޸ĺ������ظ���Ϣ��Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateTaskResponse(TaskResponse taskResponse, ErrInfo pErrInfo);

	/**
	 * �������е�����ظ���Ϣ��Ϣ
	 * @param taskResponses ���ز��ҳɹ�������ظ���Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findTaskResponses(List<TaskResponse> taskResponses,
			ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ��������ظ���Ϣ��Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param taskResponse ���ز��ҳɹ�������ظ���Ϣ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findTaskResponseByID(int pID, TaskResponse taskResponse,
			ErrInfo pErrInfo);

}
