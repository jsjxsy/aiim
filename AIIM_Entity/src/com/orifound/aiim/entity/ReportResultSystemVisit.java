package com.orifound.aiim.entity;

    
/**
 * ����ͳ�ƽ����ϵͳ�������
 */
public class ReportResultSystemVisit extends StatReport
{
    /**
     * ���캯��
     */
    public ReportResultSystemVisit()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param systemFeatureID ϵͳ���ܱ��
	* @param systemFeatureName ϵͳ��������
	* @param visitTimeCount �ܷ��ʴ���
	*/
	public ReportResultSystemVisit(int iD,int reportID,int systemFeatureID,String systemFeatureName,int visitTimeCount)
	{
		// Table Name: ReportResult_SystemVisit
		// Columns List,Can Used in SELECT SQL: ID,ReportID,SystemFeatureID,SystemFeatureName,VisitTimeCount
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:SystemFeatureID,:SystemFeatureName,:VisitTimeCount
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,SystemFeatureID=:SystemFeatureID,SystemFeatureName=:SystemFeatureName,VisitTimeCount=:VisitTimeCount

		this.iD = iD;
		this.reportID = reportID;
		this.systemFeatureID = systemFeatureID;
		this.systemFeatureName = systemFeatureName;
		this.visitTimeCount = visitTimeCount;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param systemFeatureID ϵͳ���ܱ��
	* @param systemFeatureName ϵͳ��������
	* @param visitTimeCount �ܷ��ʴ���
	*/
	public ReportResultSystemVisit(int iD,int reportID,String reportTitle,int systemFeatureID,String systemFeatureName,int visitTimeCount)
	{
		// Table Name: ReportResult_SystemVisit
		// Columns List,Can Used in SELECT SQL: ID,ReportID,SystemFeatureID,SystemFeatureName,VisitTimeCount
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:SystemFeatureID,:SystemFeatureName,:VisitTimeCount
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,SystemFeatureID=:SystemFeatureID,SystemFeatureName=:SystemFeatureName,VisitTimeCount=:VisitTimeCount
		super.setReportTitle(reportTitle);
		this.iD = iD;
		this.reportID = reportID;
		this.systemFeatureID = systemFeatureID;
		this.systemFeatureName = systemFeatureName;
		this.visitTimeCount = visitTimeCount;
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
	 * ϵͳ���ܱ��
	 */
	private int systemFeatureID=0;

	/**
	 * ��ȡ����ֵ��ϵͳ���ܱ��
	 * @return ϵͳ���ܱ��
	 */
	public int getSystemFeatureID()
	{
		return systemFeatureID;
	}

	/**
	 * ��������ֵ��ϵͳ���ܱ��
	 * @param systemFeatureID ϵͳ���ܱ��
	 */
	public void setSystemFeatureID(int systemFeatureID)
	{
		this.systemFeatureID = systemFeatureID;
	}

	/**
	 * ϵͳ��������
	 */
	private String systemFeatureName=null;

	/**
	 * ��ȡ����ֵ��ϵͳ��������
	 * @return ϵͳ��������
	 */
	public String getSystemFeatureName()
	{
		return systemFeatureName;
	}

	/**
	 * ��������ֵ��ϵͳ��������
	 * @param systemFeatureName ϵͳ��������
	 */
	public void setSystemFeatureName(String systemFeatureName)
	{
		this.systemFeatureName = systemFeatureName;
	}

	/**
	 * �ܷ��ʴ���
	 */
	private int visitTimeCount=0;

	/**
	 * ��ȡ����ֵ���ܷ��ʴ���
	 * @return �ܷ��ʴ���
	 */
	public int getVisitTimeCount()
	{
		return visitTimeCount;
	}

	/**
	 * ��������ֵ���ܷ��ʴ���
	 * @param visitTimeCount �ܷ��ʴ���
	 */
	public void setVisitTimeCount(int visitTimeCount)
	{
		this.visitTimeCount = visitTimeCount;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ReportResultSystemVisit clone()
	{
		ReportResultSystemVisit item = new ReportResultSystemVisit(iD,reportID,systemFeatureID,systemFeatureName,visitTimeCount);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param reportResultSystemVisit ָ���Ķ���Դ
	*/
	public void cloneFrom(ReportResultSystemVisit reportResultSystemVisit)
	{
		this.iD = reportResultSystemVisit.getID();
		this.reportID = reportResultSystemVisit.getReportID();
		this.systemFeatureID = reportResultSystemVisit.getSystemFeatureID();
		this.systemFeatureName = reportResultSystemVisit.getSystemFeatureName();
		this.visitTimeCount = reportResultSystemVisit.getVisitTimeCount();
		this.keyInCol = reportResultSystemVisit.getKeyInCol();
		this.tag = reportResultSystemVisit.getTag();
	}

}



