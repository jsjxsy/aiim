package com.orifound.aiim.entity;

    
/**
 * ����ͳ�ƽ�����ⷿ��ʪ����ȱ仯���
 */
public class ReportResultTempratureHumidityForYear extends StatReport
{
    /**
     * ���캯��
     */
    public ReportResultTempratureHumidityForYear()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param storeroomID �ⷿ���
	* @param storeroomName �ⷿ����
	* @param month �·�
	* @param avgTemprature ƽ���¶�
	* @param avgHumidity ƽ��ʪ��
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
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param storeroomID �ⷿ���
	* @param storeroomName �ⷿ����
	* @param month �·�
	* @param avgTemprature ƽ���¶�
	* @param avgHumidity ƽ��ʪ��
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
	 * �·�
	 */
	private String month=null;

	/**
	 * ��ȡ����ֵ���·�
	 * @return �·�
	 */
	public String getMonth()
	{
		return month;
	}

	/**
	 * ��������ֵ���·�
	 * @param month �·�
	 */
	public void setMonth(String month)
	{
		this.month = month;
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
	public ReportResultTempratureHumidityForYear clone()
	{
		ReportResultTempratureHumidityForYear item = new ReportResultTempratureHumidityForYear(iD,reportID,storeroomID,storeroomName,month,avgTemprature,avgHumidity);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param reportResultTempratureHumidityForYear ָ���Ķ���Դ
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



