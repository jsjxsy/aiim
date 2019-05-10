package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 报表统计结果表－库房温湿度月度变化情况 
 */
public class ReportResultTempratureHumidityForMonth extends StatReport
{
    /**
     * 构造函数
     */
    public ReportResultTempratureHumidityForMonth()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 记录的唯一编号
	* @param reportID 报表编号
	* @param storeroomID 库房编号
	* @param storeroomName 库房名称
	* @param date 日期
	* @param avgTemprature 平均温度
	* @param avgHumidity 平均湿度
	*/
	public ReportResultTempratureHumidityForMonth(int iD,int reportID,int storeroomID,String storeroomName,Date date,int avgTemprature,int avgHumidity)
	{
		// Table Name: ReportResult_TempratureHumidityForMonth
		// Columns List,Can Used in SELECT SQL: ID,ReportID,StoreroomID,StoreroomName,Date,AvgTemprature,AvgHumidity
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:StoreroomID,:StoreroomName,:Date,:AvgTemprature,:AvgHumidity
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,StoreroomID=:StoreroomID,StoreroomName=:StoreroomName,Date=:Date,AvgTemprature=:AvgTemprature,AvgHumidity=:AvgHumidity

		this.iD = iD;
		this.reportID = reportID;
		this.storeroomID = storeroomID;
		this.storeroomName = storeroomName;
		this.date = date;
		this.avgTemprature = avgTemprature;
		this.avgHumidity = avgHumidity;
	}

	/**
	* 带字段参数的构造函数
	* @param iD 记录的唯一编号
	* @param reportID 报表编号
	* @param storeroomID 库房编号
	* @param storeroomName 库房名称
	* @param date 日期
	* @param avgTemprature 平均温度
	* @param avgHumidity 平均湿度
	*/
	public ReportResultTempratureHumidityForMonth(int iD,int reportID,String reportTitle,int storeroomID,String storeroomName,Date date,int avgTemprature,int avgHumidity)
	{
		// Table Name: ReportResult_TempratureHumidityForMonth
		// Columns List,Can Used in SELECT SQL: ID,ReportID,StoreroomID,StoreroomName,Date,AvgTemprature,AvgHumidity
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:StoreroomID,:StoreroomName,:Date,:AvgTemprature,:AvgHumidity
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,StoreroomID=:StoreroomID,StoreroomName=:StoreroomName,Date=:Date,AvgTemprature=:AvgTemprature,AvgHumidity=:AvgHumidity
		super.setReportTitle(reportTitle);
		this.iD = iD;
		this.reportID = reportID;
		this.storeroomID = storeroomID;
		this.storeroomName = storeroomName;
		this.date = date;
		this.avgTemprature = avgTemprature;
		this.avgHumidity = avgHumidity;
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
	 * 记录的唯一编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：记录的唯一编号
	 * @return 记录的唯一编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：记录的唯一编号
	 * @param iD 记录的唯一编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 报表编号
	 */
	private int reportID=0;

	/**
	 * 获取属性值：报表编号
	 * @return 报表编号
	 */
	public int getReportID()
	{
		return reportID;
	}

	/**
	 * 设置属性值：报表编号
	 * @param reportID 报表编号
	 */
	public void setReportID(int reportID)
	{
		this.reportID = reportID;
	}

	/**
	 * 库房编号
	 */
	private int storeroomID=0;

	/**
	 * 获取属性值：库房编号
	 * @return 库房编号
	 */
	public int getStoreroomID()
	{
		return storeroomID;
	}

	/**
	 * 设置属性值：库房编号
	 * @param storeroomID 库房编号
	 */
	public void setStoreroomID(int storeroomID)
	{
		this.storeroomID = storeroomID;
	}

	/**
	 * 库房名称
	 */
	private String storeroomName=null;

	/**
	 * 获取属性值：库房名称
	 * @return 库房名称
	 */
	public String getStoreroomName()
	{
		return storeroomName;
	}

	/**
	 * 设置属性值：库房名称
	 * @param storeroomName 库房名称
	 */
	public void setStoreroomName(String storeroomName)
	{
		this.storeroomName = storeroomName;
	}

	/**
	 * 日期
	 */
	private Date date;

	/**
	 * 获取属性值：日期
	 * @return 日期
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * 设置属性值：日期
	 * @param date 日期
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}

	/**
	 * 平均温度
	 */
	private int avgTemprature=0;

	/**
	 * 获取属性值：平均温度
	 * @return 平均温度
	 */
	public int getAvgTemprature()
	{
		return avgTemprature;
	}

	/**
	 * 设置属性值：平均温度
	 * @param avgTemprature 平均温度
	 */
	public void setAvgTemprature(int avgTemprature)
	{
		this.avgTemprature = avgTemprature;
	}

	/**
	 * 平均湿度
	 */
	private int avgHumidity=0;

	/**
	 * 获取属性值：平均湿度
	 * @return 平均湿度
	 */
	public int getAvgHumidity()
	{
		return avgHumidity;
	}

	/**
	 * 设置属性值：平均湿度
	 * @param avgHumidity 平均湿度
	 */
	public void setAvgHumidity(int avgHumidity)
	{
		this.avgHumidity = avgHumidity;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ReportResultTempratureHumidityForMonth clone()
	{
		ReportResultTempratureHumidityForMonth item = new ReportResultTempratureHumidityForMonth(iD,reportID,storeroomID,storeroomName,date,avgTemprature,avgHumidity);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param reportResultTempratureHumidityForMonth 指定的对象源
	*/
	public void cloneFrom(ReportResultTempratureHumidityForMonth reportResultTempratureHumidityForMonth)
	{
		this.iD = reportResultTempratureHumidityForMonth.getID();
		this.reportID = reportResultTempratureHumidityForMonth.getReportID();
		this.storeroomID = reportResultTempratureHumidityForMonth.getStoreroomID();
		this.storeroomName = reportResultTempratureHumidityForMonth.getStoreroomName();
		this.date = reportResultTempratureHumidityForMonth.getDate();
		this.avgTemprature = reportResultTempratureHumidityForMonth.getAvgTemprature();
		this.avgHumidity = reportResultTempratureHumidityForMonth.getAvgHumidity();
		this.keyInCol = reportResultTempratureHumidityForMonth.getKeyInCol();
		this.tag = reportResultTempratureHumidityForMonth.getTag();
	}

    
}



