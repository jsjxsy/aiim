/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.TaskResponse;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 工作任务汇报信息表 (TaskResponse)的DAO接口定义
 *
 */
public interface ITaskResponseDao {

	/**
	 * Dao接口定义：添加工作任务汇报信息
	 * @param taskResponse 要添加的工作任务汇报信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(TaskResponse taskResponse, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的工作任务汇报信息
	 * @param taskResponse 要删除的工作任务汇报信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(TaskResponse taskResponse, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的工作任务汇报信息
	 * @param taskResponse 要更新的工作任务汇报信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(TaskResponse taskResponse, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的工作任务汇报信息
	 * @param taskResponses 返回查找成功的工作任务汇报信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<TaskResponse> taskResponses, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找工作任务汇报信息
	 * @param pID 指定的唯一标识
	 * @param taskResponse 返回查找成功的工作任务汇报信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, TaskResponse taskResponse, ErrInfo pErrInfo);

}
