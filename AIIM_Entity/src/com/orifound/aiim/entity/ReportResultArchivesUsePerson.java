package com.orifound.aiim.entity;

    
/**
 * ����ͳ�ƽ�����������������
 */
public class ReportResultArchivesUsePerson extends StatReport
{
    /**
     * ���캯��
     */
    public ReportResultArchivesUsePerson()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param areaFlag �����־
	* @param useArea ������������Ϣ
	* @param personTimeCount �˴�������
	*/
	public ReportResultArchivesUsePerson(int iD,int reportID,int areaFlag,String useArea,int personTimeCount)
	{
		// Table Name: ReportResult_ArchivesUsePerson
		// Columns List,Can Used in SELECT SQL: ID,ReportID,AreaFlag,UseArea,PersonTimeCount
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:AreaFlag,:UseArea,:PersonTimeCount
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,AreaFlag=:AreaFlag,UseArea=:UseArea,PersonTimeCount=:PersonTimeCount

		this.iD = iD;
		this.reportID = reportID;
		this.areaFlag = areaFlag;
		this.useArea = useArea;
		this.personTimeCount = personTimeCount;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportName ��������
	* @param reportID ������
	* @param areaFlag �����־
	* @param useArea ������������Ϣ
	* @param personTimeCount �˴�������
	*/
	public ReportResultArchivesUsePerson(int iD,int reportID,String reportTitle,int areaFlag,String useArea,int personTimeCount)
	{
		// Table Name: ReportResult_ArchivesUsePerson
		// Columns List,Can Used in SELECT SQL: ID,ReportID,AreaFlag,UseArea,PersonTimeCount
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:AreaFlag,:UseArea,:PersonTimeCount
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,AreaFlag=:AreaFlag,UseArea=:UseArea,PersonTimeCount=:PersonTimeCount
		super.setReportTitle(reportTitle);
		this.iD = iD;
		this.reportID = reportID;
		this.areaFlag = areaFlag;
		this.useArea = useArea;
		this.personTimeCount = personTimeCount;
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
	 * �����־
	 */
	private int areaFlag=0;

	/**
	 * ��ȡ����ֵ�������־
	 * @return �����־
	 */
	public int getAreaFlag()
	{
		return areaFlag;
	}

	/**
	 * ��������ֵ�������־
	 * @param areaFlag �����־
	 */
	public void setAreaFlag(int areaFlag)
	{
		this.areaFlag = areaFlag;
	}

	/**
	 * ������������Ϣ
	 */
	private String useArea=null;

	/**
	 * ��ȡ����ֵ��������������Ϣ
	 * @return ������������Ϣ
	 */
	public String getUseArea()
	{
		return useArea;
	}

	/**
	 * ��������ֵ��������������Ϣ
	 * @param useArea ������������Ϣ
	 */
	public void setUseArea(String useArea)
	{
		this.useArea = useArea;
	}

	/**
	 * �˴�������
	 */
	private int personTimeCount=0;

	/**
	 * ��ȡ����ֵ���˴�������
	 * @return �˴�������
	 */
	public int getPersonTimeCount()
	{
		return personTimeCount;
	}

	/**
	 * ��������ֵ���˴�������
	 * @param personTimeCount �˴�������
	 */
	public void setPersonTimeCount(int personTimeCount)
	{
		this.personTimeCount = personTimeCount;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ReportResultArchivesUsePerson clone()
	{
		ReportResultArchivesUsePerson item = new ReportResultArchivesUsePerson(iD,reportID,areaFlag,useArea,personTimeCount);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param reportResultArchivesUsePerson ָ���Ķ���Դ
	*/
	public void cloneFrom(ReportResultArchivesUsePerson reportResultArchivesUsePerson)
	{
		this.iD = reportResultArchivesUsePerson.getID();
		this.reportID = reportResultArchivesUsePerson.getReportID();
		this.areaFlag = reportResultArchivesUsePerson.getAreaFlag();
		this.useArea = reportResultArchivesUsePerson.getUseArea();
		this.personTimeCount = reportResultArchivesUsePerson.getPersonTimeCount();
		this.keyInCol = reportResultArchivesUsePerson.getKeyInCol();
		this.tag = reportResultArchivesUsePerson.getTag();
	}


    
}



