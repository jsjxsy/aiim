package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 档案利用申请单信息
 */
public class ArchivesUseRequest
{
    /**
     * 构造函数
     */
    public ArchivesUseRequest()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 档案利用申请单编号
	* @param requestTime 申请时间
	* @param requestReason 申请理由
	* @param userDepartment 单位
	* @param userID 利用人编号
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
	 * 档案利用申请单编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：档案利用申请单编号
	 * @return 档案利用申请单编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：档案利用申请单编号
	 * @param iD 档案利用申请单编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 申请时间
	 */
	private Date requestTime;

	/**
	 * 获取属性值：申请时间
	 * @return 申请时间
	 */
	public Date getRequestTime()
	{
		return requestTime;
	}

	/**
	 * 设置属性值：申请时间
	 * @param requestTime 申请时间
	 */
	public void setRequestTime(Date requestTime)
	{
		this.requestTime = requestTime;
	}

	/**
	 * 申请理由
	 */
	private String requestReason=null;

	/**
	 * 获取属性值：申请理由
	 * @return 申请理由
	 */
	public String getRequestReason()
	{
		return requestReason;
	}

	/**
	 * 设置属性值：申请理由
	 * @param requestReason 申请理由
	 */
	public void setRequestReason(String requestReason)
	{
		this.requestReason = requestReason;
	}

	/**
	 * 单位
	 */
	private String userDepartment=null;

	/**
	 * 获取属性值：单位
	 * @return 单位
	 */
	public String getUserDepartment()
	{
		return userDepartment;
	}

	/**
	 * 设置属性值：单位
	 * @param userDepartment 单位
	 */
	public void setUserDepartment(String userDepartment)
	{
		this.userDepartment = userDepartment;
	}

	/**
	 * 用户编号
	 */
	private int userID=0;

	/**
	 * 获取属性值：利用人编号
	 * @return 利用人编号
	 */
	public int getUserID()
	{
		return userID;
	}

	/**
	 * 设置属性值：利用人编号
	 * @param userID 利用人编号
	 */
	public void setUserID(int userID)
	{
		this.userID = userID;
	}
	
	/**
	 * 用户信息
	 */
	private UserInfo userInfo =null;
	
	/**
	 * 设置用户信息
	 * @return
	 */
	public UserInfo getUserInfo() {
		return userInfo;
	}

	/**
	 * 获取用户信息
	 * @param userInfo
	 */
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
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
	* 从指定对象克隆，复制所有属性值
	* @param archivesUseRequest 指定的对象源
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



