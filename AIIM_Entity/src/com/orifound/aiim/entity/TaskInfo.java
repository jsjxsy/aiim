package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * ����������Ϣ��
 */
public class TaskInfo
{
    /**
     * ���캯��
     */
    public TaskInfo()
    {
        
    }
    
    /**
	* ���ֶβ����Ĺ��캯��
	* @param title �������
	* @param content ��������
	* @param fromUserID �����´���
	* @param fromDutyID �����´���ְ��
	* @param publishFlag ���񷢲���־
	* @param publishTime ���񷢲�ʱ��
	* @param lastModifyTime ����޸�ʱ��
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
	* ���ֶβ����Ĺ��캯��
	* @param title �������
	* @param content ��������
	* @param fromUserID �����´���
	* @param fromDutyID �����´���ְ��
	* @param publishFlag ���񷢲���־
	* @param publishTime ���񷢲�ʱ��
	* @param lastModifyTime ����޸�ʱ��
	* @param fromUserName 	����������
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
	* ���ֶβ����Ĺ��캯��
	* @param id ������
	* @param title �������
	* @param content ��������
	* @param fromUserID �����´���
	* @param fromDutyID �����´���ְ��
	* @param publishFlag ���񷢲���־
	* @param publishTime ���񷢲�ʱ��
	* @param lastModifyTime ����޸�ʱ��
	* @param fromUserName 	����������
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
	 * ��Ա�����ڼ����еĹؼ���
	 */
	private Object keyInCol=null;

	/**
	 * ��ȡ����ֵ����Ա�����ڼ����еĹؼ���
	 * @return ��Ա�����ڼ����еĹؼ���
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}

	/**
	 * ��������ֵ����Ա�����ڼ����еĹؼ���
	 * @param keyInCol ��Ա�����ڼ����еĹؼ���
	 */
	public void setKeyInCol(Object keyInCol)
	{
		this.keyInCol = keyInCol;
	}

	/**
	 * ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	private Object tag=null;

	/**
	 * ��ȡ����ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * @return ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public Object getTag()
	{
		return tag;
	}

	/**
	 * ��������ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * @param tag ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	/**
	 * ������
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ��������
	 * @return ������
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ��������
	 * @param iD ������
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * �������
	 */
	private String title=null;

	/**
	 * ��ȡ����ֵ���������
	 * @return �������
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * ��������ֵ���������
	 * @param title �������
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	/**
	 * ��������
	 */
	private String content=null;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * ��������ֵ����������
	 * @param content ��������
	 */
	public void setContent(String content)
	{
		this.content = content;
	}

	/**
	 * �����´���
	 */
	private int fromUserID=0;

	/**
	 * ��ȡ����ֵ�������´���
	 * @return �����´���
	 */
	public int getFromUserID()
	{
		return fromUserID;
	}

	/**
	 * ��������ֵ�������´���
	 * @param fromUserID �����´���
	 */
	public void setFromUserID(int fromUserID)
	{
		this.fromUserID = fromUserID;
	}
	
	/**
	 * ���񷢲�������
	 */
	private String fromUserName = null;

	/**
	 * ��������ֵ�����񷢲�������
	 * @param fromUserName ���񷢲�������
	 */
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	/**
	 * ��ȡ����ֵ�����񷢲�������
	 * @return ���񷢲�������
	 */
	public String getFromUserName() {
		return fromUserName;
	}

	/**
	 * �����´���ְ��
	 */
	private int fromDutyID=0;

	/**
	 * ��ȡ����ֵ�������´���ְ��
	 * @return �����´���ְ��
	 */
	public int getFromDutyID()
	{
		return fromDutyID;
	}

	/**
	 * ��������ֵ�������´���ְ��
	 * @param fromDutyID �����´���ְ��
	 */
	public void setFromDutyID(int fromDutyID)
	{
		this.fromDutyID = fromDutyID;
	}

	/**
	 * ���񷢲���־
	 */
	private boolean publishFlag=false;

	/**
	 * ��ȡ����ֵ�����񷢲���־
	 * @return ���񷢲���־
	 */
	public boolean getPublishFlag()
	{
		return publishFlag;
	}

	/**
	 * ��������ֵ�����񷢲���־
	 * @param publishFlag ���񷢲���־
	 */
	public void setPublishFlag(boolean publishFlag)
	{
		this.publishFlag = publishFlag;
	}

	/**
	 * ���񷢲�ʱ��
	 */
	private Date publishTime;

	/**
	 * ��ȡ����ֵ�����񷢲�ʱ��
	 * @return ���񷢲�ʱ��
	 */
	public Date getPublishTime()
	{
		return publishTime;
	}

	/**
	 * ��������ֵ�����񷢲�ʱ��
	 * @param publishTime ���񷢲�ʱ��
	 */
	public void setPublishTime(Date publishTime)
	{
		this.publishTime = publishTime;
	}

	/**
	 * ����޸�ʱ��
	 */
	private Date lastModifyTime;

	/**
	 * ��ȡ����ֵ������޸�ʱ��
	 * @return ����޸�ʱ��
	 */
	public Date getLastModifyTime()
	{
		return lastModifyTime;
	}

	/**
	 * ��������ֵ������޸�ʱ��
	 * @param lastModifyTime ����޸�ʱ��
	 */
	public void setLastModifyTime(Date lastModifyTime)
	{
		this.lastModifyTime = lastModifyTime;
	}
	
	/**
	 * ����ظ�����
	 */
	private List<TaskResponse> taskResponses = null;
	
	public List<TaskResponse> getTaskResponses() {
		return taskResponses;
	}

	public void setTaskResponses(List<TaskResponse> taskResponses) {
		this.taskResponses = taskResponses;
	}
	
	/**
	 * ���񷢲��˻ظ���Ϣ
	 */
	private List<TaskResponse> ownTaskResponses = null;
	
	public List<TaskResponse> getOwnTaskResponses() {
		return ownTaskResponses;
	}

	public void setOwnTaskResponses(List<TaskResponse> ownTaskResponses) {
		this.ownTaskResponses = ownTaskResponses;
	}

	/**
	 * ����ظ���
	 */
	private int taskResponseCount;
	
	public int getTaskResponseCount() {
		return taskResponseCount;
	}

	public void setTaskResponseCount(int taskResponseCount) {
		this.taskResponseCount = taskResponseCount;
	}

	/**
	 * �����������Ϣ��
	 */
	private List<TaskPerson> taskPersons = null;

	public List<TaskPerson> getTaskPersons() {
		return taskPersons;
	}

	public void setTaskPersons(List<TaskPerson> taskPersons) {
		this.taskPersons = taskPersons;
	}
	
	/**
	 * ���������Id����
	 */
	private List<Integer> taskPersonIds = null;
	
	public List<Integer> getTaskPersonIds() {
		return taskPersonIds;
	}

	public void setTaskPersonIds(List<Integer> taskPersonIds) {
		this.taskPersonIds = taskPersonIds;
	}
	
	/**
	 * ��Ҫɾ�������������Id����
	 */
	private List<Integer> delTaskPersonIds = null;
	
	public List<Integer> getDelTaskPersonIds() {
		return delTaskPersonIds;
	}

	public void setDelTaskPersonIds(List<Integer> delTaskPersonIds) {
		this.delTaskPersonIds = delTaskPersonIds;
	}

	/**
	 * ���������������ַ�����ʾ��;�ָ���
	 */
	private String receiveNames;

	public String getReceiveNames() {
		return receiveNames;
	}

	public void setReceiveNames(String receiveNames) {
		this.receiveNames = receiveNames;
	}
	
	/**
	 * ������id�����ַ�����ʾ��;�ָ���
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
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public TaskInfo clone()
	{
		TaskInfo item = new TaskInfo(iD,title,content,fromUserID,fromDutyID,publishFlag,publishTime,lastModifyTime,fromUserName);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param taskInfo ָ���Ķ���Դ
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