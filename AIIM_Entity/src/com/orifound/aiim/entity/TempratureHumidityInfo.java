package com.orifound.aiim.entity;
import java.util.*;
    
/**
 * 库房温湿度登记信息
 */
public class TempratureHumidityInfo
{
    /**
     * 构造函数
     */
    public TempratureHumidityInfo()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param storeroomID 库房编号
	* @param measureDate 测量日期
	* @param measureTime 测量时间
	* @param temperature 温度
	* @param humidity 湿度
	* @param recordTime 登记时间
	*/
	public TempratureHumidityInfo(int iD,String storeroomID,Date measureDate,String measureTime,double temperature,double humidity,Date recordTime)
	{
		// Table Name: TempratureHumidityInfo
		// Columns List,Can Used in SELECT SQL: ID,StoreroomID,MeasureDate,MeasureTime,Temperature,Humidity,RecordTime
		// Columns List,Can Used in INSERT SQL: :ID,:StoreroomID,:MeasureDate,:MeasureTime,:Temperature,:Humidity,:RecordTime
		// Columns List,Can Used in UPDATE SQL: ID=:ID,StoreroomID=:StoreroomID,MeasureDate=:MeasureDate,MeasureTime=:MeasureTime,Temperature=:Temperature,Humidity=:Humidity,RecordTime=:RecordTime

		this.iD = iD;
		this.storeroomID = storeroomID;
		this.measureDate = measureDate;
		this.measureTime = measureTime;
		this.temperature = temperature;
		this.humidity = humidity;
		this.recordTime = recordTime;
	}
	
	 
	/**
	* 带字段参数的构造函数。注：添加了库房名称
	* @param iD 编号
	* @param storeroomID 库房编号
	* @param storeroomName 库房名称
	* @param measureDate 测量日期
	* @param measureTime 测量时间
	* @param temperature 温度
	* @param humidity 湿度
	* @param recordTime 登记时间
	*/
	public TempratureHumidityInfo(int iD,String storeroomID,String storeroomName, Date measureDate,String measureTime,double temperature,double humidity,Date recordTime)
	{
		// Table Name: TempratureHumidityInfo
		// Columns List,Can Used in SELECT SQL: ID,StoreroomID,MeasureDate,MeasureTime,Temperature,Humidity,RecordTime
		// Columns List,Can Used in INSERT SQL: :ID,:StoreroomID,:MeasureDate,:MeasureTime,:Temperature,:Humidity,:RecordTime
		// Columns List,Can Used in UPDATE SQL: ID=:ID,StoreroomID=:StoreroomID,MeasureDate=:MeasureDate,MeasureTime=:MeasureTime,Temperature=:Temperature,Humidity=:Humidity,RecordTime=:RecordTime

		this.iD = iD;
		this.storeroomID = storeroomID;
		this.storeroomName = storeroomName;
		this.measureDate = measureDate;
		this.measureTime = measureTime;
		this.temperature = temperature;
		this.humidity = humidity;
		this.recordTime = recordTime;
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
	 * 编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：编号
	 * @return 编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：编号
	 * @param iD 编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 库房编号
	 */
	private String storeroomID=null;

	/**
	 * 获取属性值：库房编号
	 * @return 库房编号
	 */
	public String getStoreroomID()
	{
		return storeroomID;
	}

	/**
	 * 设置属性值：库房编号
	 * @param storeroomID 库房编号
	 */
	public void setStoreroomID(String storeroomID)
	{
		this.storeroomID = storeroomID;
	}
	
	/**
	 * 库房名称
	 */
	private String storeroomName = null;

	/**
	 * 获取属性值：库房名称
	 * @return
	 */
	public String getStoreroomName() {
		return storeroomName;
	}

	/**
	 * 设置属性：库房名称
	 * @param storeroomName 库房名称 
	 */
	public void setStoreroomName(String storeroomName) {
		this.storeroomName = storeroomName;
	}

	/**
	 * 测量日期
	 */
	private Date measureDate;

	/**
	 * 获取属性值：测量日期
	 * @return 测量日期
	 */
	public Date getMeasureDate()
	{
		return measureDate;
	}

	/**
	 * 设置属性值：测量日期
	 * @param measureDate 测量日期
	 */
	public void setMeasureDate(Date measureDate)
	{
		this.measureDate = measureDate;
	}

	/**
	 * 测量时间
	 */
	private String measureTime=null;

	/**
	 * 获取属性值：测量时间
	 * @return 测量时间
	 */
	public String getMeasureTime()
	{
		return measureTime;
	}

	/**
	 * 设置属性值：测量时间
	 * @param measureTime 测量时间
	 */
	public void setMeasureTime(String measureTime)
	{
		this.measureTime = measureTime;
	}

	/**
	 * 温度
	 */
	private double temperature=0;

	/**
	 * 获取属性值：温度
	 * @return 温度
	 */
	public double getTemperature()
	{
		return temperature;
	}

	/**
	 * 设置属性值：温度
	 * @param temperature 温度
	 */
	public void setTemperature(double temperature)
	{
		this.temperature = temperature;
	}

	/**
	 * 湿度
	 */
	private double humidity=0;

	/**
	 * 获取属性值：湿度
	 * @return 湿度
	 */
	public double getHumidity()
	{
		return humidity;
	}

	/**
	 * 设置属性值：湿度
	 * @param humidity 湿度
	 */
	public void setHumidity(double humidity)
	{
		this.humidity = humidity;
	}

	/**
	 * 登记时间
	 */
	private Date recordTime;

	/**
	 * 获取属性值：登记时间
	 * @return 登记时间
	 */
	public Date getRecordTime()
	{
		return recordTime;
	}

	/**
	 * 设置属性值：登记时间
	 * @param recordTime 登记时间
	 */
	public void setRecordTime(Date recordTime)
	{
		this.recordTime = recordTime;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public TempratureHumidityInfo clone()
	{
		TempratureHumidityInfo item = new TempratureHumidityInfo(iD,storeroomID,measureDate,measureTime,temperature,humidity,recordTime);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param tempratureHumidityInfo 指定的对象源
	*/
	public void cloneFrom(TempratureHumidityInfo tempratureHumidityInfo)
	{
		this.iD = tempratureHumidityInfo.getID();
		this.storeroomID = tempratureHumidityInfo.getStoreroomID();
		this.measureDate = tempratureHumidityInfo.getMeasureDate();
		this.measureTime = tempratureHumidityInfo.getMeasureTime();
		this.temperature = tempratureHumidityInfo.getTemperature();
		this.humidity = tempratureHumidityInfo.getHumidity();
		this.recordTime = tempratureHumidityInfo.getRecordTime();
		this.keyInCol = tempratureHumidityInfo.getKeyInCol();
		this.tag = tempratureHumidityInfo.getTag();
	}


    
}



