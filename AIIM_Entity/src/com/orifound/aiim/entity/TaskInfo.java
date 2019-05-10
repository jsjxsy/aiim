package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 工作任务信息表
 */
public class TaskInfo
{
    /**
     * 构造函数
     */
    public TaskInfo()
    {
        
    }
    
    /**
	* 带字段参数的构造函数
	* @param title 任务标题
	* @param content 任务内容
	* @param fromUserID 任务下达者
	* @param fromDutyID 任务下达者职务
	* @param publishFlag 任务发布标志
	* @param publishTime 任务发布时间
	* @param lastModifyTime 最后修改时间
	*/
	public TaskInfo(String title,String content,int fromUserID,int fromDutyID,boolean publishFlag,Date publishTime,Date lastModifyTime)
	{
		// Table Name: TaskInfo
		// Columns List,Can Used in SELECT SQL: ID,Title,Content,FromUserID,FromDutyID,PublishFlag,PublishTime,LastModifyTime
		// Columns List,Can Used in INSERT SQL: :ID,:Title,:Content,:FromUserID,:FromDutyID,:PublishFlag,:PublishTime,:LastModifyTime
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Title=:Title,Content=:Content,FromUserID=:FromUserID,FromDutyID=:FromDutyID,PublishFlag=:PublishFlag,PublishTime=:PublishTime,LastModifyTime=:LastModifyTime
		this.title = title;
		this.content = content;
		this.fromUserID = fromUserID;
		this.fromDutyID = fromDutyID;
		this.publishFlag = publishFlag;
		this.publishTime = publishTime;
		this.lastModifyTime = lastModifyTime;
	}
    
    /**
	* 带字段参数的构造函数
	* @param title 任务标题
	* @param content 任务内容
	* @param fromUserID 任务下达者
	* @param fromDutyID 任务下达者职务
	* @param publishFlag 任务发布标志
	* @param publishTime 任务发布时间
	* @param lastModifyTime 最后修改时间
	* @param fromUserName 	发布人姓名
	*/
	public TaskInfo(String title,String content,int fromUserID,int fromDutyID,boolean publishFlag,Date publishTime,Date lastModifyTime,String fromUserName)
	{
		// Table Name: TaskInfo
		// Columns List,Can Used in SELECT SQL: ID,Title,Content,FromUserID,FromDutyID,PublishFlag,PublishTime,LastModifyTime
		// Columns List,Can Used in INSERT SQL: :ID,:Title,:Content,:FromUserID,:FromDutyID,:PublishFlag,:PublishTime,:LastModifyTime
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Title=:Title,Content=:Content,FromUserID=:FromUserID,FromDutyID=:FromDutyID,PublishFlag=:PublishFlag,PublishTime=:PublishTime,LastModifyTime=:LastModifyTime
		this(title, content, fromUserID, fromDutyID, publishFlag, publishTime, lastModifyTime);
		this.fromUserName = fromUserName;
	}
    
	/**
	* 带字段参数的构造函数
	* @param id 任务编号
	* @param title 任务标题
	* @param content 任务内容
	* @param fromUserID 任务下达者
	* @param fromDutyID 任务下达者职务
	* @param publishFlag 任务发布标志
	* @param publishTime 任务发布时间
	* @param lastModifyTime 最后修改时间
	* @param fromUserName 	发布人姓名
	*/
	public TaskInfo(int iD,String title,String content,int fromUserID,int fromDutyID,boolean publishFlag,Date publishTime,Date lastModifyTime,String fromUserName)
	{
		// Table Name: TaskInfo
		// Columns List,Can Used in SELECT SQL: ID,Title,Content,FromUserID,FromDutyID,PublishFlag,PublishTime,LastModifyTime
		// Columns List,Can Used in INSERT SQL: :ID,:Title,:Content,:FromUserID,:FromDutyID,:PublishFlag,:PublishTime,:LastModifyTime
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Title=:Title,Content=:Content,FromUserID=:FromUserID,FromDutyID=:FromDutyID,PublishFlag=:PublishFlag,PublishTime=:PublishTime,LastModifyTime=:LastModifyTime
		this(title, content, fromUserID, fromDutyID, publishFlag, publishTime, lastModifyTime,fromUserName);
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
	 * 任务编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：任务编号
	 * @return 任务编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：任务编号
	 * @param iD 任务编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 任务标题
	 */
	private String title=null;

	/**
	 * 获取属性值：任务标题
	 * @return 任务标题
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * 设置属性值：任务标题
	 * @param title 任务标题
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	/**
	 * 任务内容
	 */
	private String content=null;

	/**
	 * 获取属性值：任务内容
	 * @return 任务内容
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * 设置属性值：任务内容
	 * @param content 任务内容
	 */
	public void setContent(String content)
	{
		this.content = content;
	}

	/**
	 * 任务下达者
	 */
	private int fromUserID=0;

	/**
	 * 获取属性值：任务下达者
	 * @return 任务下达者
	 */
	public int getFromUserID()
	{
		return fromUserID;
	}

	/**
	 * 设置属性值：任务下达者
	 * @param fromUserID 任务下达者
	 */
	public void setFromUserID(int fromUserID)
	{
		this.fromUserID = fromUserID;
	}
	
	/**
	 * 任务发布人姓名
	 */
	private String fromUserName = null;

	/**
	 * 设置属性值：任务发布人姓名
	 * @param fromUserName 任务发布人姓名
	 */
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	/**
	 * 获取属性值：任务发布人姓名
	 * @return 任务发布人姓名
	 */
	public String getFromUserName() {
		return fromUserName;
	}

	/**
	 * 任务下达者职务
	 */
	private int fromDutyID=0;

	/**
	 * 获取属性值：任务下达者职务
	 * @return 任务下达者职务
	 */
	public int getFromDutyID()
	{
		return fromDutyID;
	}

	/**
	 * 设置属性值：任务下达者职务
	 * @param fromDutyID 任务下达者职务
	 */
	public void setFromDutyID(int fromDutyID)
	{
		this.fromDutyID = fromDutyID;
	}

	/**
	 * 任务发布标志
	 */
	private boolean publishFlag=false;

	/**
	 * 获取属性值：任务发布标志
	 * @return 任务发布标志
	 */
	public boolean getPublishFlag()
	{
		return publishFlag;
	}

	/**
	 * 设置属性值：任务发布标志
	 * @param publishFlag 任务发布标志
	 */
	public void setPublishFlag(boolean publishFlag)
	{
		this.publishFlag = publishFlag;
	}

	/**
	 * 任务发布时间
	 */
	private Date publishTime;

	/**
	 * 获取属性值：任务发布时间
	 * @return 任务发布时间
	 */
	public Date getPublishTime()
	{
		return publishTime;
	}

	/**
	 * 设置属性值：任务发布时间
	 * @param publishTime 任务发布时间
	 */
	public void setPublishTime(Date publishTime)
	{
		this.publishTime = publishTime;
	}

	/**
	 * 最后修改时间
	 */
	private Date lastModifyTime;

	/**
	 * 获取属性值：最后修改时间
	 * @return 最后修改时间
	 */
	public Date getLastModifyTime()
	{
		return lastModifyTime;
	}

	/**
	 * 设置属性值：最后修改时间
	 * @param lastModifyTime 最后修改时间
	 */
	public void setLastModifyTime(Date lastModifyTime)
	{
		this.lastModifyTime = lastModifyTime;
	}
	
	/**
	 * 任务回复集合
	 */
	private List<TaskResponse> taskResponses = null;
	
	public List<TaskResponse> getTaskResponses() {
		return taskResponses;
	}

	public void setTaskResponses(List<TaskResponse> taskResponses) {
		this.taskResponses = taskResponses;
	}
	
	/**
	 * 任务发布人回复信息
	 */
	private List<TaskResponse> ownTaskResponses = null;
	
	public List<TaskResponse> getOwnTaskResponses() {
		return ownTaskResponses;
	}

	public void setOwnTaskResponses(List<TaskResponse> ownTaskResponses) {
		this.ownTaskResponses = ownTaskResponses;
	}

	/**
	 * 任务回复数
	 */
	private int taskResponseCount;
	
	public int getTaskResponseCount() {
		return taskResponseCount;
	}

	public void setTaskResponseCount(int taskResponseCount) {
		this.taskResponseCount = taskResponseCount;
	}

	/**
	 * 任务接收人信息表
	 */
	private List<TaskPerson> taskPersons = null;

	public List<TaskPerson> getTaskPersons() {
		return taskPersons;
	}

	public void setTaskPersons(List<TaskPerson> taskPersons) {
		this.taskPersons = taskPersons;
	}
	
	/**
	 * 任务接收人Id集合
	 */
	private List<Integer> taskPersonIds = null;
	
	public List<Integer> getTaskPersonIds() {
		return taskPersonIds;
	}

	public void setTaskPersonIds(List<Integer> taskPersonIds) {
		this.taskPersonIds = taskPersonIds;
	}
	
	/**
	 * 需要删除的任务接收人Id集合
	 */
	private List<Integer> delTaskPersonIds = null;
	
	public List<Integer> getDelTaskPersonIds() {
		return delTaskPersonIds;
	}

	public void setDelTaskPersonIds(List<Integer> delTaskPersonIds) {
		this.delTaskPersonIds = delTaskPersonIds;
	}

	/**
	 * 接收人姓名集合字符串表示（;分隔）
	 */
	private String receiveNames;

	public String getReceiveNames() {
		return receiveNames;
	}

	public void setReceiveNames(String receiveNames) {
		this.receiveNames = receiveNames;
	}
	
	/**
	 * 接收人id集合字符串表示（;分隔）
	 */
	private String receiveIds;
	
	public String getReceiveIds() {
		return receiveIds;
	}

	public void setReceiveIds(String receiveIds) {
		this.receiveIds = receiveIds;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public TaskInfo clone()
	{
		TaskInfo item = new TaskInfo(iD,title,content,fromUserID,fromDutyID,publishFlag,publishTime,lastModifyTime,fromUserName);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param taskInfo 指定的对象源
	*/
	public void cloneFrom(TaskInfo taskInfo)
	{
		this.iD = taskInfo.getID();
		this.title = taskInfo.getTitle();
		this.content = taskInfo.getContent();
		this.fromUserID = taskInfo.getFromUserID();
		this.fromDutyID = taskInfo.getFromDutyID();
		this.publishFlag = taskInfo.getPublishFlag();
		this.publishTime = taskInfo.getPublishTime();
		this.lastModifyTime = taskInfo.getLastModifyTime();
		this.fromUserName = taskInfo.getFromUserName();
		this.keyInCol = taskInfo.getKeyInCol();
		this.tag = taskInfo.getTag();
	}
}