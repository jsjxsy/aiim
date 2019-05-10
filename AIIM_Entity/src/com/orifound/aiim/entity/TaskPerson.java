package com.orifound.aiim.entity;

import java.util.List;


/**
 * 任务负责人信息表
 */
public class TaskPerson
{
    /**
     * 构造函数
     */
    public TaskPerson()
    {
        
    }
    
    /**
	* 带字段参数的构造函数
	* @param taskID 工作任务编号
	* @param chargeLevel 负责级别
	* @param userID 负责人编号
	*/
	public TaskPerson(int iD,int taskID,int userID)
	{
		// Table Name: TaskPerson
		// Columns List,Can Used in SELECT SQL: ID,TaskID,ChargeLevel,UserID
		// Columns List,Can Used in INSERT SQL: :ID,:TaskID,:ChargeLevel,:UserID
		// Columns List,Can Used in UPDATE SQL: ID=:ID,TaskID=:TaskID,ChargeLevel=:ChargeLevel,UserID=:UserID
		this.iD = iD;
		this.taskID = taskID;
		this.userID = userID;
	}
    
    /**
	* 带字段参数的构造函数
	* @param taskID 工作任务编号
	* @param chargeLevel 负责级别
	* @param userID 负责人编号
	* @param userName 负责人姓名
	*/
	public TaskPerson(int taskID, int userID,String userName)
	{
		// Table Name: TaskPerson
		// Columns List,Can Used in SELECT SQL: ID,TaskID,ChargeLevel,UserID
		// Columns List,Can Used in INSERT SQL: :ID,:TaskID,:ChargeLevel,:UserID
		// Columns List,Can Used in UPDATE SQL: ID=:ID,TaskID=:TaskID,ChargeLevel=:ChargeLevel,UserID=:UserID

		this.taskID = taskID;
		this.userID = userID;
		this.userName = userName;
	}
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param taskID 工作任务编号
	* @param chargeLevel 负责级别
	* @param userID 负责人编号
	*/
	public TaskPerson(int iD,int taskID,int userID, String userName)
	{
		// Table Name: TaskPerson
		// Columns List,Can Used in SELECT SQL: ID,TaskID,ChargeLevel,UserID
		// Columns List,Can Used in INSERT SQL: :ID,:TaskID,:ChargeLevel,:UserID
		// Columns List,Can Used in UPDATE SQL: ID=:ID,TaskID=:TaskID,ChargeLevel=:ChargeLevel,UserID=:UserID

		this(taskID, userID, userName);
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
	 * 负责级别
	 */
	private int chargeLevel=0;

	/**
	 * 获取属性值：负责级别
	 * @return 负责级别
	 */
	public int getChargeLevel()
	{
		return chargeLevel;
	}

	/**
	 * 设置属性值：负责级别
	 * @param chargeLevel 负责级别
	 */
	public void setChargeLevel(int chargeLevel)
	{
		this.chargeLevel = chargeLevel;
	}

	/**
	 * 负责人编号
	 */
	private int userID=0;

	/**
	 * 获取属性值：负责人编号
	 * @return 负责人编号
	 */
	public int getUserID()
	{
		return userID;
	}

	/**
	 * 设置属性值：负责人编号
	 * @param userID 负责人编号
	 */
	public void setUserID(int userID)
	{
		this.userID = userID;
	}
	
	/**
	 * 负责人姓名
	 */
	private String userName = null;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * 任务接收人回复信息集合
	 */
	private List<TaskResponse> taskResponses = null;

	public List<TaskResponse> getTaskResponses() {
		return taskResponses;
	}

	public void setTaskResponses(List<TaskResponse> taskResponses) {
		this.taskResponses = taskResponses;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public TaskPerson clone()
	{
		TaskPerson item = new TaskPerson(iD,taskID,userID,userName);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param taskPerson 指定的对象源
	*/
	public void cloneFrom(TaskPerson taskPerson)
	{
		this.iD = taskPerson.getID();
		this.taskID = taskPerson.getTaskID();
		this.userID = taskPerson.getUserID();
		this.userName = taskPerson.getUserName();
		this.keyInCol = taskPerson.getKeyInCol();
		this.tag = taskPerson.getTag();
	}
}