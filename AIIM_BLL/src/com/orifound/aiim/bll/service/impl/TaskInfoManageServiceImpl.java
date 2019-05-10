/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.ITaskInfoManageService;
import com.orifound.aiim.bll.util.StringTool;
import com.orifound.aiim.dal.dao.ITaskInfoDao;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.TaskInfo;
import com.orifound.aiim.entity.TaskPerson;

/**
 * ������Ϣ�������ʵ����
 * @author tyb
 *
 */
public class TaskInfoManageServiceImpl implements ITaskInfoManageService {
	
	/**
	 * ������Ϣ������ݷ��ʶ���
	 */
	@Autowired
	private ITaskInfoDao taskInfoDao = null;
	
	/**
	 * ���캯��
	 */
	public TaskInfoManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public TaskInfoManageServiceImpl(ITaskInfoDao taskInfoDao) {
		this.taskInfoDao = taskInfoDao;
	}

	/**
	 * ���������Ϣ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForTaskInfo(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (taskInfoDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.ITaskInfoManageService#deleteTaskInfo(com.orifound.aiim.entity.TaskInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteTaskInfo(TaskInfo taskinfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ITaskInfoManageService#findTaskInfoByID(int, com.orifound.aiim.entity.TaskInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findTaskInfoByID(int pID, TaskInfo taskInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForTaskInfo(pErrInfo) == false) {
				pFlag = false;
			}
			
			//�������id�Ƿ�Ϊ��
			if (pFlag) {
				if (pID <=0) {
					pFlag = false;
					pErrInfo.getContent().append("�������->����id�Ƿ�Ϊ�㡣");
				}
			}
			
			//�����������Ƿ�Ϊ��
			if (pFlag) {
				if (taskInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("�������->�������Ƿ�Ϊ�ա�");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				if (taskInfoDao.findByID(pID, taskInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service����Ψһ��ʶ���ҹ���������Ϣ ʧ�ܣ�");
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
	 * @see com.orifound.aiim.bll.service.ITaskInfoManageService#findTaskInfos(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findTaskInfos(List<TaskInfo> taskinfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ITaskInfoManageService#saveTaskInfo(com.orifound.aiim.entity.TaskInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveTaskInfo(TaskInfo taskInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForTaskInfo(pErrInfo) == false) {
				pFlag = false;
			}
			
			//���������Ϣ�Ƿ�Ϊ��
			if (pFlag) {
				if (taskInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//�������������Ƿ�Ϊ��
			if (pFlag && taskInfo.getPublishFlag()) {
				if (taskInfo.getTaskPersonIds()==null || taskInfo.getTaskPersonIds().size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("�������->��������˷Ƿ�Ϊ�ա�");
				}
			}
			
			//������������Ƿ�Ϊ��
			if (pFlag) {
				if (StringTool.checkNull(taskInfo.getTitle()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("�������->��������Ƿ�Ϊ�ա�");
				}
			}
			
			//�����������޸�ʱ���Ƿ�Ϊ��
			if (pFlag) {
				if (taskInfo.getLastModifyTime() == null) {
					pFlag = false;
					pErrInfo.getContent().append("�������->��������޸�ʱ��Ƿ�Ϊ�ա�");
				}
			}
			
			//������������Ƿ�Ϊ��
			if (pFlag) {
				if (StringTool.checkNull(taskInfo.getContent()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("�������->�������ݷǷ�Ϊ�ա�");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				if (taskInfoDao.save(taskInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service���������Ϣ ʧ�ܣ�");
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
	 * @see com.orifound.aiim.bll.service.ITaskInfoManageService#updateTaskInfo(com.orifound.aiim.entity.TaskInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateTaskInfo(TaskInfo taskInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForTaskInfo(pErrInfo) == false) {
				pFlag = false;
			}
			
			//���������Ϣ�Ƿ�Ϊ��
			if (pFlag) {
				if (taskInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//������������Ƿ�Ϊ��
			if (pFlag) {
				if (StringTool.checkNull(taskInfo.getTitle()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("�������->��������Ƿ�Ϊ�ա�");
				}
			}
			
			//�����������޸�ʱ���Ƿ�Ϊ��
			if (pFlag) {
				if (taskInfo.getLastModifyTime() == null) {
					pFlag = false;
					pErrInfo.getContent().append("�������->��������޸�ʱ��Ƿ�Ϊ�ա�");
				}
			}
			
			//������������Ƿ�Ϊ��
			if (pFlag) {
				if (StringTool.checkNull(taskInfo.getContent()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("�������->�������ݷǷ�Ϊ�ա�");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				if (taskInfoDao.update(taskInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service����ָ���Ĺ���������Ϣ ʧ�ܣ�");
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

	@Override
	public boolean findWithPage(Map<String, String> params, DataPageInfo dataPageInfo, List<TaskInfo> taskInfos,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForTaskInfo(pErrInfo) == false) {
				pFlag = false;
			}
			
			//���������Ϣ�����Ƿ�Ϊ��
			if (pFlag) {
				if (taskInfos == null) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������Ϣ���ϷǷ�Ϊ�ա�");
				}
			}
			
			if (pFlag) {
				pErrPos = 2;
				//����DAO ʵ�ַ�ҳ��ѯ������Ϣ
				if (taskInfoDao.findWithPage(params, dataPageInfo, taskInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Serviceִ�з�ҳ��ѯ������Ϣ ʧ�ܣ�");
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

	@Override
	public boolean deleteBatch(List<Integer> taskInfoIds, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForTaskInfo(pErrInfo) == false) {
				pFlag = false;
			}
			
			//�ж�����id�����Ƿ�Ϊ��
			if (pFlag) {
				if (taskInfoIds==null || taskInfoIds.size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("����id���ϷǷ�Ϊ�ա�");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				if (taskInfoDao.deleteBatch(taskInfoIds, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service����ɾ��ָ����������Ϣ ʧ�ܣ�");
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

	@Override
	public boolean findTaskPersonByID(int pID, List<TaskPerson> taskPersons,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForTaskInfo(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//��������id���������������Ϣ
				if (taskInfoDao.findTaskPersonByID(pID, taskPersons, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service��������id���������������Ϣ ʧ�ܣ�");
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

	@Override
	public boolean findDetailByID(int pID, TaskInfo taskInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForTaskInfo(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//����Ψһ��ʶ���ҹ���������ϸ��Ϣ(�����ظ���Ϣ)
				if (taskInfoDao.findDetailByID(pID, taskInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����Ψһ��ʶ���ҹ���������ϸ��Ϣ ʧ�ܣ�");
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

}
