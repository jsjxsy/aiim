/**
 * 
 */
package com.orifound.aiim.entity;

import java.util.Date;

/**
 * 档案利用登记信息查询条件对象类
 *
 */
public class ArchivesUseRegisterQueryCondition {
	
	/**
	 * 借阅标志
	 */
	private boolean borrowFlag=false;

	/**
	 * 获取属性值：借阅标志
	 * @return 借阅标志
	 */
	public boolean getBorrowFlag()
	{
		return borrowFlag;
	}

	/**
	 * 设置属性值：借阅标志
	 * @param borrowFlag 借阅标志
	 */
	public void setBorrowFlag(boolean borrowFlag)
	{
		this.borrowFlag = borrowFlag;
	}
	
	/**
	 * 起始利用日期
	 */
	private Date useDateBegin = null;

	/**
	 * 获取属性值：利用日期
	 * @return 利用日期
	 */
	public Date getUseDateBegin()
	{
		return useDateBegin;
	}

	/**
	 * 设置属性值：利用日期
	 * @param useDate 利用日期
	 */
	public void setUseDateBegin(Date useDateBegin)
	{
		this.useDateBegin = useDateBegin;
	}
	
	/**
	 * 截止利用日期
	 */
	private Date useDateEnd = null;

	/**
	 * 获取属性值：截止利用日期
	 * @return 截止利用日期
	 */
	public Date getUseDateEnd()
	{
		return useDateEnd;
	}

	/**
	 * 设置属性值：截止利用日期
	 * @param useDate 截止利用日期
	 */
	public void setUseDateEnd(Date useDateEnd)
	{
		this.useDateEnd = useDateEnd;
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
	
	
}
