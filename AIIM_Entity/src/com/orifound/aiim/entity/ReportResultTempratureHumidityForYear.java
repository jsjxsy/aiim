package com.orifound.aiim.entity;

    
/**
 * 报表统计结果表－库房温湿度年度变化情况
 */
public class ReportResultTempratureHumidityForYear extends StatReport
{
    /**
     * 构造函数
     */
    public ReportResultTempratureHumidityForYear()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 记录的唯一编号
	* @param reportID 报表编号
	* @param storeroomID 库房编号
	* @param storeroomName 库房名称
	* @param month 月份
	* @param avgTemprature 平均温度
	* @param avgHumidity 平均湿度
	*/
	public ReportResultTempratureHumidityForYear(int iD,int reportID,int storeroomID,String storeroomName,String month,int avgTemprature,int avgHumidity)
	{
		// Table Name: ReportResult_TempratureHumidityForYear
		// Columns List,Can Used in SELECT SQL: ID,ReportID,StoreroomID,StoreroomName,Month,AvgTemprature,AvgHumidity
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:StoreroomID,:StoreroomName,:Month,:AvgTemprature,:AvgHumidity
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,StoreroomID=:StoreroomID,StoreroomName=:StoreroomName,Month=:Month,AvgTemprature=:AvgTemprature,AvgHumidity=:AvgHumidity

		this.iD = iD;
		this.reportID = reportID;
		this.storeroomID = storeroomID;
		this.storeroomName = storeroomName;
		this.month = month;
		this.avgTemprature = avgTemprature;
		this.avgHumidity = avgHumidity;
	}
	
	/**
	* 带字段参数的构造函数
	* @param iD 记录的唯一编号
	* @param reportID 报表编号
	* @param storeroomID 库房编号
	* @param storeroomName 库房名称
	* @param month 月份
	* @param avgTemprature 平均温度
	* @param avgHumidity 平均湿度
	*/
	public ReportResultTempratureHumidityForYear(int iD,int reportID,String reportTitle,int storeroomID,String storeroomName,String month,int avgTemprature,int avgHumidity)
	{
		// Table Name: ReportResult_TempratureHumidityForYear
		// Columns List,Can Used in SELECT SQL: ID,ReportID,StoreroomID,StoreroomName,Month,AvgTemprature,AvgHumidity
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:StoreroomID,:StoreroomName,:Month,:AvgTemprature,:AvgHumidity
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,StoreroomID=:StoreroomID,StoreroomName=:StoreroomName,Month=:Month,AvgTemprature=:AvgTemprature,AvgHumidity=:AvgHumidity
		super.setReportTitle(reportTitle);
		this.iD = iD;
		this.reportID = reportID;
		this.storeroomID = storeroomID;
		this.storeroomName = storeroomName;
		this.month = month;
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
	 * 月份
	 */
	private String month=null;

	/**
	 * 获取属性值：月份
	 * @return 月份
	 */
	public String getMonth()
	{
		return month;
	}

	/**
	 * 设置属性值：月份
	 * @param month 月份
	 */
	public void setMonth(String month)
	{
		this.month = month;
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
	public ReportResultTempratureHumidityForYear clone()
	{
		ReportResultTempratureHumidityForYear item = new ReportResultTempratureHumidityForYear(iD,reportID,storeroomID,storeroomName,month,avgTemprature,avgHumidity);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param reportResultTempratureHumidityForYear 指定的对象源
	*/
	public void cloneFrom(ReportResultTempratureHumidityForYear reportResultTempratureHumidityForYear)
	{
		this.iD = reportResultTempratureHumidityForYear.getID();
		this.reportID = reportResultTempratureHumidityForYear.getReportID();
		this.storeroomID = reportResultTempratureHumidityForYear.getStoreroomID();
		this.storeroomName = reportResultTempratureHumidityForYear.getStoreroomName();
		this.month = reportResultTempratureHumidityForYear.getMonth();
		this.avgTemprature = reportResultTempratureHumidityForYear.getAvgTemprature();
		this.avgHumidity = reportResultTempratureHumidityForYear.getAvgHumidity();
		this.keyInCol = reportResultTempratureHumidityForYear.getKeyInCol();
		this.tag = reportResultTempratureHumidityForYear.getTag();
	}

}



