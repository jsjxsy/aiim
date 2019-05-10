package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * �����������뵥��Ϣ
 */
public class ArchivesUseRequest
{
    /**
     * ���캯��
     */
    public ArchivesUseRequest()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD �����������뵥���
	* @param requestTime ����ʱ��
	* @param requestReason ��������
	* @param userDepartment ��λ
	* @param userID �����˱��
	*/
	public ArchivesUseRequest(int iD,Date requestTime,String requestReason,String userDepartment,int userID)
	{
		// Table Name: ArchivesUseRequest
		// Columns List,Can Used in SELECT SQL: ID,RequestTime,RequestReason,UserDepartment,UserID
		// Columns List,Can Used in INSERT SQL: :ID,:RequestTime,:RequestReason,:UserDepartment,:UserID
		// Columns List,Can Used in UPDATE SQL: ID=:ID,RequestTime=:RequestTime,RequestReason=:RequestReason,UserDepartment=:UserDepartment,UserID=:UserID

		this.iD = iD;
		this.requestTime = requestTime;
		this.requestReason = requestReason;
		this.userDepartment = userDepartment;
		this.userID = userID;
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
	 * �����������뵥���
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ�������������뵥���
	 * @return �����������뵥���
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ�������������뵥���
	 * @param iD �����������뵥���
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ����ʱ��
	 */
	private Date requestTime;

	/**
	 * ��ȡ����ֵ������ʱ��
	 * @return ����ʱ��
	 */
	public Date getRequestTime()
	{
		return requestTime;
	}

	/**
	 * ��������ֵ������ʱ��
	 * @param requestTime ����ʱ��
	 */
	public void setRequestTime(Date requestTime)
	{
		this.requestTime = requestTime;
	}

	/**
	 * ��������
	 */
	private String requestReason=null;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public String getRequestReason()
	{
		return requestReason;
	}

	/**
	 * ��������ֵ����������
	 * @param requestReason ��������
	 */
	public void setRequestReason(String requestReason)
	{
		this.requestReason = requestReason;
	}

	/**
	 * ��λ
	 */
	private String userDepartment=null;

	/**
	 * ��ȡ����ֵ����λ
	 * @return ��λ
	 */
	public String getUserDepartment()
	{
		return userDepartment;
	}

	/**
	 * ��������ֵ����λ
	 * @param userDepartment ��λ
	 */
	public void setUserDepartment(String userDepartment)
	{
		this.userDepartment = userDepartment;
	}

	/**
	 * �û����
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
	 * �û���Ϣ
	 */
	private UserInfo userInfo =null;
	
	/**
	 * �����û���Ϣ
	 * @return
	 */
	public UserInfo getUserInfo() {
		return userInfo;
	}

	/**
	 * ��ȡ�û���Ϣ
	 * @param userInfo
	 */
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ArchivesUseRequest clone()
	{
		ArchivesUseRequest item = new ArchivesUseRequest(iD,requestTime,requestReason,userDepartment,userID);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);
		item.setUserInfo(userInfo);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param archivesUseRequest ָ���Ķ���Դ
	*/
	public void cloneFrom(ArchivesUseRequest archivesUseRequest)
	{
		this.iD = archivesUseRequest.getID();
		this.requestTime = archivesUseRequest.getRequestTime();
		this.requestReason = archivesUseRequest.getRequestReason();
		this.userDepartment = archivesUseRequest.getUserDepartment();
		this.userID = archivesUseRequest.getUserID();
		this.keyInCol = archivesUseRequest.getKeyInCol();
		this.tag = archivesUseRequest.getTag();
		this.userInfo = archivesUseRequest.getUserInfo();
	}



    
}



