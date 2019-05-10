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
 * 任务信息管理服务实现类
 * @author tyb
 *
 */
public class TaskInfoManageServiceImpl implements ITaskInfoManageService {
	
	/**
	 * 任务信息表的数据访问对象
	 */
	@Autowired
	private ITaskInfoDao taskInfoDao = null;
	
	/**
	 * 构造函数
	 */
	public TaskInfoManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public TaskInfoManageServiceImpl(ITaskInfoDao taskInfoDao) {
		this.taskInfoDao = taskInfoDao;
	}

	/**
	 * 检查任务信息的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForTaskInfo(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (taskInfoDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("任务信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForTaskInfo(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查任务id是否为零
			if (pFlag) {
				if (pID <=0) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->任务id非法为零。");
				}
			}
			
			//检查任务对象是否为空
			if (pFlag) {
				if (taskInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->任务对象非法为空。");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				if (taskInfoDao.findByID(pID, taskInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service根据唯一标识查找工作任务信息 失败：");
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
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForTaskInfo(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查任务信息是否为空
			if (pFlag) {
				if (taskInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->任务信息非法为空。");
				}
			}
			
			//检查任务接收人是否为空
			if (pFlag && taskInfo.getPublishFlag()) {
				if (taskInfo.getTaskPersonIds()==null || taskInfo.getTaskPersonIds().size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->任务接收人非法为空。");
				}
			}
			
			//检查任务主题是否为空
			if (pFlag) {
				if (StringTool.checkNull(taskInfo.getTitle()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->任务主题非法为空。");
				}
			}
			
			//检查任务最后修改时间是否为空
			if (pFlag) {
				if (taskInfo.getLastModifyTime() == null) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->任务最后修改时间非法为空。");
				}
			}
			
			//检查任务内容是否为空
			if (pFlag) {
				if (StringTool.checkNull(taskInfo.getContent()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->任务内容非法为空。");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				if (taskInfoDao.save(taskInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service添加任务信息 失败：");
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
	 * @see com.orifound.aiim.bll.service.ITaskInfoManageService#updateTaskInfo(com.orifound.aiim.entity.TaskInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateTaskInfo(TaskInfo taskInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForTaskInfo(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查任务信息是否为空
			if (pFlag) {
				if (taskInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->任务信息非法为空。");
				}
			}
			
			//检查任务主题是否为空
			if (pFlag) {
				if (StringTool.checkNull(taskInfo.getTitle()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->任务主题非法为空。");
				}
			}
			
			//检查任务最后修改时间是否为空
			if (pFlag) {
				if (taskInfo.getLastModifyTime() == null) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->任务最后修改时间非法为空。");
				}
			}
			
			//检查任务内容是否为空
			if (pFlag) {
				if (StringTool.checkNull(taskInfo.getContent()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->任务内容非法为空。");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				if (taskInfoDao.update(taskInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service更新指定的工作任务信息 失败：");
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

	@Override
	public boolean findWithPage(Map<String, String> params, DataPageInfo dataPageInfo, List<TaskInfo> taskInfos,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForTaskInfo(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查任务信息集合是否为空
			if (pFlag) {
				if (taskInfos == null) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->任务信息集合非法为空。");
				}
			}
			
			if (pFlag) {
				pErrPos = 2;
				//调用DAO 实现分页查询任务信息
				if (taskInfoDao.findWithPage(params, dataPageInfo, taskInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service执行分页查询任务信息 失败：");
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

	@Override
	public boolean deleteBatch(List<Integer> taskInfoIds, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForTaskInfo(pErrInfo) == false) {
				pFlag = false;
			}
			
			//判断任务id集合是否为空
			if (pFlag) {
				if (taskInfoIds==null || taskInfoIds.size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("任务id集合非法为空。");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				if (taskInfoDao.deleteBatch(taskInfoIds, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service批量删除指定的任务信息 失败：");
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

	@Override
	public boolean findTaskPersonByID(int pID, List<TaskPerson> taskPersons,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForTaskInfo(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//根据任务id查找任务接收人信息
				if (taskInfoDao.findTaskPersonByID(pID, taskPersons, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service根据任务id查找任务接收人信息 失败：");
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

	@Override
	public boolean findDetailByID(int pID, TaskInfo taskInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForTaskInfo(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//根据唯一标识查找工作任务详细信息(包含回复信息)
				if (taskInfoDao.findDetailByID(pID, taskInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据唯一标识查找工作任务详细信息 失败：");
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

}
