package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 工作任务汇报信息表
 */
public class TaskResponse
{
    /**
     * 构造函数
     */
    public TaskResponse()
    {
        
    }
    
    /**
	* 带字段参数的构造函数
	* @param taskID 工作任务编号
	* @param responseTime 汇报时间
	* @param responseUserID 汇报人
	* @param responseContent 汇报内容
	*/
	public TaskResponse(int taskID,Date responseTime,int responseUserID,String responseContent)
	{
		// Table Name: TaskResponse
		// Columns List,Can Used in SELECT SQL: ID,TaskID,ResponseTime,ResponseUserID,ResponseContent
		// Columns List,Can Used in INSERT SQL: :ID,:TaskID,:ResponseTime,:ResponseUserID,:ResponseContent
		// Columns List,Can Used in UPDATE SQL: ID=:ID,TaskID=:TaskID,ResponseTime=:ResponseTime,ResponseUserID=:ResponseUserID,ResponseContent=:ResponseContent

		this.taskID = taskID;
		this.responseTime = responseTime;
		this.responseUserID = responseUserID;
		this.responseContent = responseContent;
	}
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param taskID 工作任务编号
	* @param responseTime 汇报时间
	* @param responseUserID 汇报人
	* @param responseContent 汇报内容
	*/
	public TaskResponse(int iD,int taskID,Date responseTime,int responseUserID,String responseContent)
	{
		// Table Name: TaskResponse
		// Columns List,Can Used in SELECT SQL: ID,TaskID,ResponseTime,ResponseUserID,ResponseContent
		// Columns List,Can Used in INSERT SQL: :ID,:TaskID,:ResponseTime,:ResponseUserID,:ResponseContent
		// Columns List,Can Used in UPDATE SQL: ID=:ID,TaskID=:TaskID,ResponseTime=:ResponseTime,ResponseUserID=:ResponseUserID,ResponseContent=:ResponseContent

		this(taskID, responseTime, responseUserID, responseContent);
		this.iD = iD;
	}

	/**
	 * 成员对象在集合中的关键字
	 */
	private Object keyInCol=null;

	/**
	 * 获取属性值：成员对象在集合中的关键字
	 * @return 成员对象在集合中的关键字
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}

	/**
	 * 设置属性值：成员对象在集合中的关键字
	 * @param keyInCol 成员对象在集合中的关键字
	 */
	public void setKeyInCol(Object keyInCol)
	{
		this.keyInCol = keyInCol;
	}

	/**
	 * 该数据项的附加对象，可以用来保存一些附加信息
	 */
	private Object tag=null;

	/**
	 * 获取属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * @return 该数据项的附加对象，可以用来保存一些附加信息
	 */
	public Object getTag()
	{
		return tag;
	}

	/**
	 * 设置属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * @param tag 该数据项的附加对象，可以用来保存一些附加信息
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	/**
	 * 编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：编号
	 * @return 编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：编号
	 * @param iD 编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 工作任务编号
	 */
	private int taskID=0;

	/**
	 * 获取属性值：工作任务编号
	 * @return 工作任务编号
	 */
	public int getTaskID()
	{
		return taskID;
	}

	/**
	 * 设置属性值：工作任务编号
	 * @param taskID 工作任务编号
	 */
	public void setTaskID(int taskID)
	{
		this.taskID = taskID;
	}

	/**
	 * 汇报时间
	 */
	private Date responseTime;

	/**
	 * 获取属性值：汇报时间
	 * @return 汇报时间
	 */
	public Date getResponseTime()
	{
		return responseTime;
	}

	/**
	 * 设置属性值：汇报时间
	 * @param responseTime 汇报时间
	 */
	public void setResponseTime(Date responseTime)
	{
		this.responseTime = responseTime;
	}

	/**
	 * 汇报人
	 */
	private int responseUserID=0;

	/**
	 * 获取属性值：汇报人
	 * @return 汇报人
	 */
	public int getResponseUserID()
	{
		return responseUserID;
	}

	/**
	 * 设置属性值：汇报人
	 * @param responseUserID 汇报人
	 */
	public void setResponseUserID(int responseUserID)
	{
		this.responseUserID = responseUserID;
	}

	/**
	 * 汇报内容
	 */
	private String responseContent=null;

	/**
	 * 获取属性值：汇报内容
	 * @return 汇报内容
	 */
	public String getResponseContent()
	{
		return responseContent;
	}

	/**
	 * 设置属性值：汇报内容
	 * @param responseContent 汇报内容
	 */
	public void setResponseContent(String responseContent)
	{
		this.responseContent = responseContent;
	}
	
	/**
	 * 任务发布人对接收人回复信息的回复
	 */
	private List<TaskResponse> taskResponses = null;
	
	public List<TaskResponse> getTaskResponses() {
		return taskResponses;
	}

	public void setTaskResponses(List<TaskResponse> taskResponses) {
		this.taskResponses = taskResponses;
	}
	
	public String getLimitContent() {
		int len = 55;
		if(responseContent == null) {
			return "";
		} else if(responseContent.length()>=len) {
			return responseContent.substring(0, len)+"...";
		} else {
			return responseContent;
		}
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public TaskResponse clone()
	{
		TaskResponse item = new TaskResponse(iD,taskID,responseTime,responseUserID,responseContent);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param taskResponse 指定的对象源
	*/
	public void cloneFrom(TaskResponse taskResponse)
	{
		this.iD = taskResponse.getID();
		this.taskID = taskResponse.getTaskID();
		this.responseTime = taskResponse.getResponseTime();
		this.responseUserID = taskResponse.getResponseUserID();
		this.responseContent = taskResponse.getResponseContent();
		this.keyInCol = taskResponse.getKeyInCol();
		this.tag = taskResponse.getTag();
	}
}



