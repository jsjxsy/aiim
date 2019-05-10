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
 * 任务信息管理服务的接口定义
 *
 */
public interface ITaskInfoManageService {

	/**
	 * 添加一个新的任务信息
	 * @param taskinfo 新添加的任务信息信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveTaskInfo(TaskInfo taskinfo, ErrInfo pErrInfo);

	/**
	 * 删除指定的任务信息
	 * @param taskinfo 要删除的任务信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteTaskInfo(TaskInfo taskinfo, ErrInfo pErrInfo);

	/**
	 * 修改指定的任务信息
	 * @param taskinfo 修改后的任务信息信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateTaskInfo(TaskInfo taskinfo, ErrInfo pErrInfo);

	/**
	 * 查找所有的任务信息
	 * @param taskinfos 返回查找成功的任务信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findTaskInfos(List<TaskInfo> taskinfos,
			ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找任务信息
	 * @param pID 指定的唯一标识
	 * @param taskinfo 返回查找成功的任务信息信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findTaskInfoByID(int pID, TaskInfo taskinfo, ErrInfo pErrInfo);

	/**
	 * 分页查询任务信息
	 * @param params 参数列表 包含：是否发布publishFlag、任务发布人fromUserName、接收人receiveName、发布日期开始beginTime、结束endTime
	 * @param dataPageInfo 数据页信息对象类 
	 * @param taskInfos 返回任务信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findWithPage(Map<String, String> params, DataPageInfo dataPageInfo, List<TaskInfo> taskInfos, ErrInfo pErrInfo);
	
	/**
	 * 批量删除指定的任务信息
	 * @param taskInfoIds 要删除的任务id集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteBatch(List<Integer> taskInfoIds, ErrInfo pErrInfo);
	
	/**
	 * 根据任务id查找任务接收人信息
	 * @param pID 指定的唯一任务id标识
	 * @param taskPersons 返回查找成功的工作任务接收人信息集合(只包含任务接收人id属性)
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findTaskPersonByID(int pID, List<TaskPerson> taskPersons, ErrInfo pErrInfo);
	
	/**
	 * 根据唯一标识查找工作任务详细信息(包含回复信息)
	 * @param pID 指定的唯一标识
	 * @param taskInfo 返回查找成功的工作任务信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findDetailByID(int pID, TaskInfo taskInfo, ErrInfo pErrInfo);
}
