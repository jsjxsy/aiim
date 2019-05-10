/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.TaskResponse;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ��������㱨��Ϣ�� (TaskResponse)��DAO�ӿڶ���
 *
 */
public interface ITaskResponseDao {

	/**
	 * Dao�ӿڶ��壺��ӹ�������㱨��Ϣ
	 * @param taskResponse Ҫ��ӵĹ�������㱨��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(TaskResponse taskResponse, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ĺ�������㱨��Ϣ
	 * @param taskResponse Ҫɾ���Ĺ�������㱨��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(TaskResponse taskResponse, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���Ĺ�������㱨��Ϣ
	 * @param taskResponse Ҫ���µĹ�������㱨��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(TaskResponse taskResponse, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĹ�������㱨��Ϣ
	 * @param taskResponses ���ز��ҳɹ��Ĺ�������㱨��Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<TaskResponse> taskResponses, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҹ�������㱨��Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param taskResponse ���ز��ҳɹ��Ĺ�������㱨��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, TaskResponse taskResponse, ErrInfo pErrInfo);

}
