package com.orifound.aiim.entity;

    
/**
 * 报表统计结果表－出证收费情况
 */
public class ReportResultCertificateCharge extends StatReport
{
    /**
     * 构造函数
     */
    public ReportResultCertificateCharge()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 记录的唯一编号
	* @param reportID 报表编号
	* @param month 月份
	* @param personTimeCount 接待人次
	* @param copysSum 证明总份数
	* @param chargeSum 收费总金额
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
	* 带字段参数的构造函数
	* @param iD 记录的唯一编号
	* @param reportID 报表编号
	* @param month 月份
	* @param personTimeCount 接待人次
	* @param copysSum 证明总份数
	* @param chargeSum 收费总金额
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
	 * 接待人次
	 */
	private int personTimeCount=0;

	/**
	 * 获取属性值：接待人次
	 * @return 接待人次
	 */
	public int getPersonTimeCount()
	{
		return personTimeCount;
	}

	/**
	 * 设置属性值：接待人次
	 * @param personTimeCount 接待人次
	 */
	public void setPersonTimeCount(int personTimeCount)
	{
		this.personTimeCount = personTimeCount;
	}

	/**
	 * 证明总份数
	 */
	private int copysSum=0;

	/**
	 * 获取属性值：证明总份数
	 * @return 证明总份数
	 */
	public int getCopysSum()
	{
		return copysSum;
	}

	/**
	 * 设置属性值：证明总份数
	 * @param copysSum 证明总份数
	 */
	public void setCopysSum(int copysSum)
	{
		this.copysSum = copysSum;
	}

	/**
	 * 收费总金额
	 */
	private double chargeSum=0;

	/**
	 * 获取属性值：收费总金额
	 * @return 收费总金额
	 */
	public double getChargeSum()
	{
		return chargeSum;
	}

	/**
	 * 设置属性值：收费总金额
	 * @param chargeSum 收费总金额
	 */
	public void setChargeSum(double chargeSum)
	{
		this.chargeSum = chargeSum;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ReportResultCertificateCharge clone()
	{
		ReportResultCertificateCharge item = new ReportResultCertificateCharge(iD,reportID,month,personTimeCount,copysSum,chargeSum);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param reportResultCertificateCharge 指定的对象源
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



