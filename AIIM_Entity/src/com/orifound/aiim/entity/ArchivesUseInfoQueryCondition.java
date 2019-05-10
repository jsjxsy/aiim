/**
 * 
 */
package com.orifound.aiim.entity;

import java.util.Date;

/**
 * 档案利用信息查询条件对象类
 *
 */
public class ArchivesUseInfoQueryCondition {

	/**
	 * 档号
	 */
	private String archivesID=null;

	/**
	 * 获取属性值：档号
	 * @return 档号
	 */
	public String getArchivesID()
	{
		return archivesID;
	}

	/**
	 * 设置属性值：档号
	 * @param archivesID 档号
	 */
	public void setArchivesID(String archivesID)
	{
		this.archivesID = archivesID;
	}

	/**
	 * 题名
	 */
	private String title=null;

	/**
	 * 获取属性值：题名
	 * @return 题名
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * 设置属性值：题名
	 * @param title 题名
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * 档案利用方式编号
	 */
	private int useWayID=0;

	/**
	 * 获取属性值：档案利用方式编号
	 * @return 档案利用方式编号
	 */
	public int getUseWayID()
	{
		return useWayID;
	}

	/**
	 * 设置属性值：档案利用方式编号
	 * @param useWayID 档案利用方式编号
	 */
	public void setUseWayID(int useWayID)
	{
		this.useWayID = useWayID;
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
	
	/**
	 * 利用目的编号
	 */
	private int purposeID=0;

	/**
	 * 获取属性值：利用目的编号
	 * @return 利用目的编号
	 */
	public int getPurposeID()
	{
		return purposeID;
	}

	/**
	 * 设置属性值：利用目的编号
	 * @param purposeID 利用目的编号
	 */
	public void setPurposeID(int purposeID)
	{
		this.purposeID = purposeID;
	}
	
	/**
	 * 起始利用日期
	 */
	private Date useDateBegin;

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
	private Date useDateEnd;

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

}
