package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * ��������㱨��Ϣ��
 */
public class TaskResponse
{
    /**
     * ���캯��
     */
    public TaskResponse()
    {
        
    }
    
    /**
	* ���ֶβ����Ĺ��캯��
	* @param taskID ����������
	* @param responseTime �㱨ʱ��
	* @param responseUserID �㱨��
	* @param responseContent �㱨����
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
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param taskID ����������
	* @param responseTime �㱨ʱ��
	* @param responseUserID �㱨��
	* @param responseContent �㱨����
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
	 * ���
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ�����
	 * @return ���
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ�����
	 * @param iD ���
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ����������
	 */
	private int taskID=0;

	/**
	 * ��ȡ����ֵ������������
	 * @return ����������
	 */
	public int getTaskID()
	{
		return taskID;
	}

	/**
	 * ��������ֵ������������
	 * @param taskID ����������
	 */
	public void setTaskID(int taskID)
	{
		this.taskID = taskID;
	}

	/**
	 * �㱨ʱ��
	 */
	private Date responseTime;

	/**
	 * ��ȡ����ֵ���㱨ʱ��
	 * @return �㱨ʱ��
	 */
	public Date getResponseTime()
	{
		return responseTime;
	}

	/**
	 * ��������ֵ���㱨ʱ��
	 * @param responseTime �㱨ʱ��
	 */
	public void setResponseTime(Date responseTime)
	{
		this.responseTime = responseTime;
	}

	/**
	 * �㱨��
	 */
	private int responseUserID=0;

	/**
	 * ��ȡ����ֵ���㱨��
	 * @return �㱨��
	 */
	public int getResponseUserID()
	{
		return responseUserID;
	}

	/**
	 * ��������ֵ���㱨��
	 * @param responseUserID �㱨��
	 */
	public void setResponseUserID(int responseUserID)
	{
		this.responseUserID = responseUserID;
	}

	/**
	 * �㱨����
	 */
	private String responseContent=null;

	/**
	 * ��ȡ����ֵ���㱨����
	 * @return �㱨����
	 */
	public String getResponseContent()
	{
		return responseContent;
	}

	/**
	 * ��������ֵ���㱨����
	 * @param responseContent �㱨����
	 */
	public void setResponseContent(String responseContent)
	{
		this.responseContent = responseContent;
	}
	
	/**
	 * ���񷢲��˶Խ����˻ظ���Ϣ�Ļظ�
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
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public TaskResponse clone()
	{
		TaskResponse item = new TaskResponse(iD,taskID,responseTime,responseUserID,responseContent);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param taskResponse ָ���Ķ���Դ
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



