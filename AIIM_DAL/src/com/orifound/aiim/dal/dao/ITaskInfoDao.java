/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.TaskInfo;
import com.orifound.aiim.entity.TaskPerson;

/**
 * 工作任务信息表 (TaskInfo)的DAO接口定义
 *
 */
public interface ITaskInfoDao {

	/**
	 * Dao接口定义：添加工作任务信息
	 * @param taskInfo 要添加的工作任务信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(TaskInfo taskInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的工作任务信息
	 * @param taskInfo 要删除的工作任务信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(TaskInfo taskInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的工作任务信息
	 * @param taskInfo 要更新的工作任务信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(TaskInfo taskInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的工作任务信息
	 * @param taskInfos 返回查找成功的工作任务信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<TaskInfo> taskInfos, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找工作任务信息
	 * @param pID 指定的唯一标识
	 * @param taskInfo 返回查找成功的工作任务信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, TaskInfo taskInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：根据唯一标识查找工作任务详细信息(包含回复信息)
	 * @param pID 指定的唯一标识
	 * @param taskInfo 返回查找成功的工作任务信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findDetailByID(int pID, TaskInfo taskInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：根据任务id查找任务接收人信息
	 * @param pID 指定的唯一任务id标识
	 * @param taskPersons 返回查找成功的工作任务接收人信息集合(只包含任务接收人id属性)
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findTaskPersonByID(int pID, List<TaskPerson> taskPersons, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：分页查询任务信息
	 * @param params 参数列表 包含：发布标志publishFlag、任务发布人fromUserName、接收人receiveName、发布日期开始beginTime、结束endTime
	 * @param dataPageInfo 数据页信息对象类 
	 * @param taskInfos 返回任务信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findWithPage(Map<String, String> params, DataPageInfo dataPageInfo, List<TaskInfo> taskInfos, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：批量删除指定的任务信息
	 * @param taskInfoIds 要删除的任务id集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteBatch(List<Integer> taskInfoIds, ErrInfo pErrInfo);
}