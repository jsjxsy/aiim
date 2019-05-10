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
 * 任务回复信息管理服务实现类
 * @author tyb
 *
 */
public class TaskResponseManageServiceImpl implements ITaskResponseManageService {
	
	/**
	 * 构造函数
	 */
	public TaskResponseManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public TaskResponseManageServiceImpl(ITaskResponseDao taskResponseDao) {
		this.taskResponseDao = taskResponseDao;
	}
	
	/**
	 * 注入任务回复信息DAO
	 */
	@Autowired
	private ITaskResponseDao taskResponseDao;
	
	/**
	 * 检查任务回复的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForTaskResponse(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (taskResponseDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("任务回复的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
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
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForTaskResponse(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查任务回复信息是否为空
			if (pFlag) {
				if (taskResponse == null) {
					pFlag = false;
					pErrInfo.getContent().append("任务回复信息非法为空。");
				}
			}
			
			//检查任务回复信息的任务id
			if (pFlag) {
				if (taskResponse.getTaskID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("任务回复信息的任务id非法。");
				}
			}
			
			//检查任务回复信息的回复时间
			if (pFlag) {
				if (taskResponse.getResponseTime() == null) {
					pFlag = false;
					pErrInfo.getContent().append("任务回复信息的回复时间非法为空。");
				}
			}
			
			//检查 任务回复信息的回复内容
			if (pFlag) {
				if (StringTool.checkNull(taskResponse.getResponseContent()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("任务回复信息的回复内容非法为空。");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//添加工作任务汇报信息
				if (taskResponseDao.save(taskResponse, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "添加工作任务回复信息 失败：");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
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
