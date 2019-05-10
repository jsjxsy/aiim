package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * ����ͳ�ƽ�����ⷿ��ʪ���¶ȱ仯��� 
 */
public class ReportResultTempratureHumidityForMonth extends StatReport
{
    /**
     * ���캯��
     */
    public ReportResultTempratureHumidityForMonth()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param storeroomID �ⷿ���
	* @param storeroomName �ⷿ����
	* @param date ����
	* @param avgTemprature ƽ���¶�
	* @param avgHumidity ƽ��ʪ��
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
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param storeroomID �ⷿ���
	* @param storeroomName �ⷿ����
	* @param date ����
	* @param avgTemprature ƽ���¶�
	* @param avgHumidity ƽ��ʪ��
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
	 * ��¼��Ψһ���
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ����¼��Ψһ���
	 * @return ��¼��Ψһ���
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ����¼��Ψһ���
	 * @param iD ��¼��Ψһ���
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ������
	 */
	private int reportID=0;

	/**
	 * ��ȡ����ֵ��������
	 * @return ������
	 */
	public int getReportID()
	{
		return reportID;
	}

	/**
	 * ��������ֵ��������
	 * @param reportID ������
	 */
	public void setReportID(int reportID)
	{
		this.reportID = reportID;
	}

	/**
	 * �ⷿ���
	 */
	private int storeroomID=0;

	/**
	 * ��ȡ����ֵ���ⷿ���
	 * @return �ⷿ���
	 */
	public int getStoreroomID()
	{
		return storeroomID;
	}

	/**
	 * ��������ֵ���ⷿ���
	 * @param storeroomID �ⷿ���
	 */
	public void setStoreroomID(int storeroomID)
	{
		this.storeroomID = storeroomID;
	}

	/**
	 * �ⷿ����
	 */
	private String storeroomName=null;

	/**
	 * ��ȡ����ֵ���ⷿ����
	 * @return �ⷿ����
	 */
	public String getStoreroomName()
	{
		return storeroomName;
	}

	/**
	 * ��������ֵ���ⷿ����
	 * @param storeroomName �ⷿ����
	 */
	public void setStoreroomName(String storeroomName)
	{
		this.storeroomName = storeroomName;
	}

	/**
	 * ����
	 */
	private Date date;

	/**
	 * ��ȡ����ֵ������
	 * @return ����
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * ��������ֵ������
	 * @param date ����
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}

	/**
	 * ƽ���¶�
	 */
	private int avgTemprature=0;

	/**
	 * ��ȡ����ֵ��ƽ���¶�
	 * @return ƽ���¶�
	 */
	public int getAvgTemprature()
	{
		return avgTemprature;
	}

	/**
	 * ��������ֵ��ƽ���¶�
	 * @param avgTemprature ƽ���¶�
	 */
	public void setAvgTemprature(int avgTemprature)
	{
		this.avgTemprature = avgTemprature;
	}

	/**
	 * ƽ��ʪ��
	 */
	private int avgHumidity=0;

	/**
	 * ��ȡ����ֵ��ƽ��ʪ��
	 * @return ƽ��ʪ��
	 */
	public int getAvgHumidity()
	{
		return avgHumidity;
	}

	/**
	 * ��������ֵ��ƽ��ʪ��
	 * @param avgHumidity ƽ��ʪ��
	 */
	public void setAvgHumidity(int avgHumidity)
	{
		this.avgHumidity = avgHumidity;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ReportResultTempratureHumidityForMonth clone()
	{
		ReportResultTempratureHumidityForMonth item = new ReportResultTempratureHumidityForMonth(iD,reportID,storeroomID,storeroomName,date,avgTemprature,avgHumidity);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param reportResultTempratureHumidityForMonth ָ���Ķ���Դ
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



