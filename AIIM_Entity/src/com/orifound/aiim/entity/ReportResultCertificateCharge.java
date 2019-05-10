package com.orifound.aiim.entity;

    
/**
 * ����ͳ�ƽ������֤�շ����
 */
public class ReportResultCertificateCharge extends StatReport
{
    /**
     * ���캯��
     */
    public ReportResultCertificateCharge()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param month �·�
	* @param personTimeCount �Ӵ��˴�
	* @param copysSum ֤���ܷ���
	* @param chargeSum �շ��ܽ��
	*/
	public ReportResultCertificateCharge(int iD,int reportID,String month,int personTimeCount,int copysSum,double chargeSum)
	{
		// Table Name: ReportResult_CertificateCharge
		// Columns List,Can Used in SELECT SQL: ID,ReportID,Month,PersonTimeCount,CopysSum,ChargeSum
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:Month,:PersonTimeCount,:CopysSum,:ChargeSum
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,Month=:Month,PersonTimeCount=:PersonTimeCount,CopysSum=:CopysSum,ChargeSum=:ChargeSum

		this.iD = iD;
		this.reportID = reportID;
		this.month = month;
		this.personTimeCount = personTimeCount;
		this.copysSum = copysSum;
		this.chargeSum = chargeSum;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param month �·�
	* @param personTimeCount �Ӵ��˴�
	* @param copysSum ֤���ܷ���
	* @param chargeSum �շ��ܽ��
	*/
	public ReportResultCertificateCharge(int iD,int reportID,String reportTitle,String month,int personTimeCount,int copysSum,double chargeSum)
	{
		// Table Name: ReportResult_CertificateCharge
		// Columns List,Can Used in SELECT SQL: ID,ReportID,Month,PersonTimeCount,CopysSum,ChargeSum
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:Month,:PersonTimeCount,:CopysSum,:ChargeSum
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,Month=:Month,PersonTimeCount=:PersonTimeCount,CopysSum=:CopysSum,ChargeSum=:ChargeSum
		super.setReportTitle(reportTitle);
		this.iD = iD;
		this.reportID = reportID;
		this.month = month;
		this.personTimeCount = personTimeCount;
		this.copysSum = copysSum;
		this.chargeSum = chargeSum;
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
	 * �Ӵ��˴�
	 */
	private int personTimeCount=0;

	/**
	 * ��ȡ����ֵ���Ӵ��˴�
	 * @return �Ӵ��˴�
	 */
	public int getPersonTimeCount()
	{
		return personTimeCount;
	}

	/**
	 * ��������ֵ���Ӵ��˴�
	 * @param personTimeCount �Ӵ��˴�
	 */
	public void setPersonTimeCount(int personTimeCount)
	{
		this.personTimeCount = personTimeCount;
	}

	/**
	 * ֤���ܷ���
	 */
	private int copysSum=0;

	/**
	 * ��ȡ����ֵ��֤���ܷ���
	 * @return ֤���ܷ���
	 */
	public int getCopysSum()
	{
		return copysSum;
	}

	/**
	 * ��������ֵ��֤���ܷ���
	 * @param copysSum ֤���ܷ���
	 */
	public void setCopysSum(int copysSum)
	{
		this.copysSum = copysSum;
	}

	/**
	 * �շ��ܽ��
	 */
	private double chargeSum=0;

	/**
	 * ��ȡ����ֵ���շ��ܽ��
	 * @return �շ��ܽ��
	 */
	public double getChargeSum()
	{
		return chargeSum;
	}

	/**
	 * ��������ֵ���շ��ܽ��
	 * @param chargeSum �շ��ܽ��
	 */
	public void setChargeSum(double chargeSum)
	{
		this.chargeSum = chargeSum;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ReportResultCertificateCharge clone()
	{
		ReportResultCertificateCharge item = new ReportResultCertificateCharge(iD,reportID,month,personTimeCount,copysSum,chargeSum);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param reportResultCertificateCharge ָ���Ķ���Դ
	*/
	public void cloneFrom(ReportResultCertificateCharge reportResultCertificateCharge)
	{
		this.iD = reportResultCertificateCharge.getID();
		this.reportID = reportResultCertificateCharge.getReportID();
		this.month = reportResultCertificateCharge.getMonth();
		this.personTimeCount = reportResultCertificateCharge.getPersonTimeCount();
		this.copysSum = reportResultCertificateCharge.getCopysSum();
		this.chargeSum = reportResultCertificateCharge.getChargeSum();
		this.keyInCol = reportResultCertificateCharge.getKeyInCol();
		this.tag = reportResultCertificateCharge.getTag();
	}


    
}



