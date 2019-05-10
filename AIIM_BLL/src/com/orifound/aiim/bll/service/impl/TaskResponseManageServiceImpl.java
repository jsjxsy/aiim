/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.ITaskResponseManageService;
import com.orifound.aiim.dal.dao.ITaskResponseDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.TaskResponse;

/**
 * ����ظ���Ϣ�������ʵ����
 * @author tyb
 *
 */
public class TaskResponseManageServiceImpl implements ITaskResponseManageService {
	
	/**
	 * ���캯��
	 */
	public TaskResponseManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public TaskResponseManageServiceImpl(ITaskResponseDao taskResponseDao) {
		this.taskResponseDao = taskResponseDao;
	}
	
	/**
	 * ע������ظ���ϢDAO
	 */
	@Autowired
	private ITaskResponseDao taskResponseDao;
	
	/**
	 * �������ظ���DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForTaskResponse(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (taskResponseDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("����ظ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}

		return pFlag;
	}
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ITaskResponseManageService#deleteTaskResponse(com.orifound.aiim.entity.TaskResponse, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteTaskResponse(TaskResponse taskResponse,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ITaskResponseManageService#findTaskResponseByID(int, com.orifound.aiim.entity.TaskResponse, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findTaskResponseByID(int pID, TaskResponse taskResponse,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ITaskResponseManageService#findTaskResponses(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findTaskResponses(List<TaskResponse> taskResponses,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ITaskResponseManageService#saveTaskResponse(com.orifound.aiim.entity.TaskResponse, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveTaskResponse(TaskResponse taskResponse, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForTaskResponse(pErrInfo) == false) {
				pFlag = false;
			}
			
			//�������ظ���Ϣ�Ƿ�Ϊ��
			if (pFlag) {
				if (taskResponse == null) {
					pFlag = false;
					pErrInfo.getContent().append("����ظ���Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//�������ظ���Ϣ������id
			if (pFlag) {
				if (taskResponse.getTaskID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("����ظ���Ϣ������id�Ƿ���");
				}
			}
			
			//�������ظ���Ϣ�Ļظ�ʱ��
			if (pFlag) {
				if (taskResponse.getResponseTime() == null) {
					pFlag = false;
					pErrInfo.getContent().append("����ظ���Ϣ�Ļظ�ʱ��Ƿ�Ϊ�ա�");
				}
			}
			
			//��� ����ظ���Ϣ�Ļظ�����
			if (pFlag) {
				if (StringTool.checkNull(taskResponse.getResponseContent()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("����ظ���Ϣ�Ļظ����ݷǷ�Ϊ�ա�");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//��ӹ�������㱨��Ϣ
				if (taskResponseDao.save(taskResponse, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ӹ�������ظ���Ϣ ʧ�ܣ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ITaskResponseManageService#updateTaskResponse(com.orifound.aiim.entity.TaskResponse, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateTaskResponse(TaskResponse taskResponse,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
