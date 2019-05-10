/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.TaskResponse;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 任务回复信息管理服务的接口定义
 *
 */
public interface ITaskResponseManageService {

	/**
	 * 添加一个新的任务回复信息
	 * @param taskResponse 新添加的任务回复信息信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveTaskResponse(TaskResponse taskResponse, ErrInfo pErrInfo);

	/**
	 * 删除指定的任务回复信息
	 * @param taskResponse 要删除的任务回复信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteTaskResponse(TaskResponse taskResponse, ErrInfo pErrInfo);

	/**
	 * 修改指定的任务回复信息
	 * @param taskResponse 修改后的任务回复信息信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateTaskResponse(TaskResponse taskResponse, ErrInfo pErrInfo);

	/**
	 * 查找所有的任务回复信息信息
	 * @param taskResponses 返回查找成功的任务回复信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findTaskResponses(List<TaskResponse> taskResponses,
			ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找任务回复信息信息
	 * @param pID 指定的唯一标识
	 * @param taskResponse 返回查找成功的任务回复信息信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findTaskResponseByID(int pID, TaskResponse taskResponse,
			ErrInfo pErrInfo);

}
