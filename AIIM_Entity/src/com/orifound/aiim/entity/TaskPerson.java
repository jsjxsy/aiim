package com.orifound.aiim.entity;

import java.util.List;


/**
 * ����������Ϣ��
 */
public class TaskPerson
{
    /**
     * ���캯��
     */
    public TaskPerson()
    {
        
    }
    
    /**
	* ���ֶβ����Ĺ��캯��
	* @param taskID ����������
	* @param chargeLevel ���𼶱�
	* @param userID �����˱��
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
	* ���ֶβ����Ĺ��캯��
	* @param taskID ����������
	* @param chargeLevel ���𼶱�
	* @param userID �����˱��
	* @param userName ����������
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
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param taskID ����������
	* @param chargeLevel ���𼶱�
	* @param userID �����˱��
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
	 * ���𼶱�
	 */
	private int chargeLevel=0;

	/**
	 * ��ȡ����ֵ�����𼶱�
	 * @return ���𼶱�
	 */
	public int getChargeLevel()
	{
		return chargeLevel;
	}

	/**
	 * ��������ֵ�����𼶱�
	 * @param chargeLevel ���𼶱�
	 */
	public void setChargeLevel(int chargeLevel)
	{
		this.chargeLevel = chargeLevel;
	}

	/**
	 * �����˱��
	 */
	private int userID=0;

	/**
	 * ��ȡ����ֵ�������˱��
	 * @return �����˱��
	 */
	public int getUserID()
	{
		return userID;
	}

	/**
	 * ��������ֵ�������˱��
	 * @param userID �����˱��
	 */
	public void setUserID(int userID)
	{
		this.userID = userID;
	}
	
	/**
	 * ����������
	 */
	private String userName = null;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * ��������˻ظ���Ϣ����
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
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public TaskPerson clone()
	{
		TaskPerson item = new TaskPerson(iD,taskID,userID,userName);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param taskPerson ָ���Ķ���Դ
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