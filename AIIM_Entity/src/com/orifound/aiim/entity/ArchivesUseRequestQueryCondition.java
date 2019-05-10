/**
 * 
 */
package com.orifound.aiim.entity;

import java.util.Date;

/**
 * 档案利用申请单查询条件对象类
 *
 */
public class ArchivesUseRequestQueryCondition {
	
	/**
	 * 档案利用申请单编号
	 */
	private String iD=null;

	/**
	 * 获取属性值：档案利用申请单编号
	 * @return 档案利用申请单编号
	 */
	public String getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：档案利用申请单编号
	 * @param iD 档案利用申请单编号
	 */
	public void setID(String iD)
	{
		this.iD = iD;
	}

	/**
	 * 起始申请时间
	 */
	private Date requestTimeBegin;

	/**
	 * 获取属性值：起始申请时间
	 * @return 起始申请时间
	 */
	public Date getRequestTimeBegin()
	{
		return requestTimeBegin;
	}

	/**
	 * 设置属性值：起始申请时间
	 * @param requestTimeBegin 起始申请时间
	 */
	public void setRequestTimeBegin(Date requestTimeBegin)
	{
		this.requestTimeBegin = requestTimeBegin;
	}
	
	/**
	 * 截止申请时间
	 */
	private Date requestTimeEnd;

	/**
	 * 获取属性值：截止申请时间
	 * @return 截止申请时间
	 */
	public Date getRequestTimeEnd() {
		return requestTimeEnd;
	}

	/**
	 * 设置属性值：截止申请时间
	 * @param requestTimeEnd 截止申请时间
	 */
	public void setRequestTimeEnd(Date requestTimeEnd) {
		this.requestTimeEnd = requestTimeEnd;
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
	 * 真实姓名
	 */
	private String userRealName=null;

	/**
	 * 获取属性值：真实姓名
	 * @return 真实姓名
	 */
	public String getUserRealName()
	{
		return userRealName;
	}

	/**
	 * 设置属性值：真实姓名
	 * @param userRealName 真实姓名
	 */
	public void setUserRealName(String userRealName)
	{
		this.userRealName = userRealName;
	}
	
	/**
	 * 证件号码
	 */
	private String iDCardNo=null;

	/**
	 * 获取属性值：证件号码
	 * @return 证件号码
	 */
	public String getIDCardNo()
	{
		return iDCardNo;
	}

	/**
	 * 设置属性值：证件号码
	 * @param iDCardNo 证件号码
	 */
	public void setIDCardNo(String iDCardNo)
	{
		this.iDCardNo = iDCardNo;
	}
}
