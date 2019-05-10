package com.orifound.aiim.entity;
import java.util.*;
    
/**
 * �ⷿ��ʪ�ȵǼ���Ϣ
 */
public class TempratureHumidityInfo
{
    /**
     * ���캯��
     */
    public TempratureHumidityInfo()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param storeroomID �ⷿ���
	* @param measureDate ��������
	* @param measureTime ����ʱ��
	* @param temperature �¶�
	* @param humidity ʪ��
	* @param recordTime �Ǽ�ʱ��
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
	* ���ֶβ����Ĺ��캯����ע������˿ⷿ����
	* @param iD ���
	* @param storeroomID �ⷿ���
	* @param storeroomName �ⷿ����
	* @param measureDate ��������
	* @param measureTime ����ʱ��
	* @param temperature �¶�
	* @param humidity ʪ��
	* @param recordTime �Ǽ�ʱ��
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
	 * �ⷿ���
	 */
	private String storeroomID=null;

	/**
	 * ��ȡ����ֵ���ⷿ���
	 * @return �ⷿ���
	 */
	public String getStoreroomID()
	{
		return storeroomID;
	}

	/**
	 * ��������ֵ���ⷿ���
	 * @param storeroomID �ⷿ���
	 */
	public void setStoreroomID(String storeroomID)
	{
		this.storeroomID = storeroomID;
	}
	
	/**
	 * �ⷿ����
	 */
	private String storeroomName = null;

	/**
	 * ��ȡ����ֵ���ⷿ����
	 * @return
	 */
	public String getStoreroomName() {
		return storeroomName;
	}

	/**
	 * �������ԣ��ⷿ����
	 * @param storeroomName �ⷿ���� 
	 */
	public void setStoreroomName(String storeroomName) {
		this.storeroomName = storeroomName;
	}

	/**
	 * ��������
	 */
	private Date measureDate;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public Date getMeasureDate()
	{
		return measureDate;
	}

	/**
	 * ��������ֵ����������
	 * @param measureDate ��������
	 */
	public void setMeasureDate(Date measureDate)
	{
		this.measureDate = measureDate;
	}

	/**
	 * ����ʱ��
	 */
	private String measureTime=null;

	/**
	 * ��ȡ����ֵ������ʱ��
	 * @return ����ʱ��
	 */
	public String getMeasureTime()
	{
		return measureTime;
	}

	/**
	 * ��������ֵ������ʱ��
	 * @param measureTime ����ʱ��
	 */
	public void setMeasureTime(String measureTime)
	{
		this.measureTime = measureTime;
	}

	/**
	 * �¶�
	 */
	private double temperature=0;

	/**
	 * ��ȡ����ֵ���¶�
	 * @return �¶�
	 */
	public double getTemperature()
	{
		return temperature;
	}

	/**
	 * ��������ֵ���¶�
	 * @param temperature �¶�
	 */
	public void setTemperature(double temperature)
	{
		this.temperature = temperature;
	}

	/**
	 * ʪ��
	 */
	private double humidity=0;

	/**
	 * ��ȡ����ֵ��ʪ��
	 * @return ʪ��
	 */
	public double getHumidity()
	{
		return humidity;
	}

	/**
	 * ��������ֵ��ʪ��
	 * @param humidity ʪ��
	 */
	public void setHumidity(double humidity)
	{
		this.humidity = humidity;
	}

	/**
	 * �Ǽ�ʱ��
	 */
	private Date recordTime;

	/**
	 * ��ȡ����ֵ���Ǽ�ʱ��
	 * @return �Ǽ�ʱ��
	 */
	public Date getRecordTime()
	{
		return recordTime;
	}

	/**
	 * ��������ֵ���Ǽ�ʱ��
	 * @param recordTime �Ǽ�ʱ��
	 */
	public void setRecordTime(Date recordTime)
	{
		this.recordTime = recordTime;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public TempratureHumidityInfo clone()
	{
		TempratureHumidityInfo item = new TempratureHumidityInfo(iD,storeroomID,measureDate,measureTime,temperature,humidity,recordTime);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param tempratureHumidityInfo ָ���Ķ���Դ
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



