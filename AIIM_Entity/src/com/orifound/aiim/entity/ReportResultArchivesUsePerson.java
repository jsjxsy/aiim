package com.orifound.aiim.entity;

    
/**
 * 报表统计结果表－档案利用人情况
 */
public class ReportResultArchivesUsePerson extends StatReport
{
    /**
     * 构造函数
     */
    public ReportResultArchivesUsePerson()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 记录的唯一编号
	* @param reportID 报表编号
	* @param areaFlag 地域标志
	* @param useArea 利用人区域信息
	* @param personTimeCount 人次总数量
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
	* 带字段参数的构造函数
	* @param iD 记录的唯一编号
	* @param reportName 报表名称
	* @param reportID 报表编号
	* @param areaFlag 地域标志
	* @param useArea 利用人区域信息
	* @param personTimeCount 人次总数量
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
	 * 地域标志
	 */
	private int areaFlag=0;

	/**
	 * 获取属性值：地域标志
	 * @return 地域标志
	 */
	public int getAreaFlag()
	{
		return areaFlag;
	}

	/**
	 * 设置属性值：地域标志
	 * @param areaFlag 地域标志
	 */
	public void setAreaFlag(int areaFlag)
	{
		this.areaFlag = areaFlag;
	}

	/**
	 * 利用人区域信息
	 */
	private String useArea=null;

	/**
	 * 获取属性值：利用人区域信息
	 * @return 利用人区域信息
	 */
	public String getUseArea()
	{
		return useArea;
	}

	/**
	 * 设置属性值：利用人区域信息
	 * @param useArea 利用人区域信息
	 */
	public void setUseArea(String useArea)
	{
		this.useArea = useArea;
	}

	/**
	 * 人次总数量
	 */
	private int personTimeCount=0;

	/**
	 * 获取属性值：人次总数量
	 * @return 人次总数量
	 */
	public int getPersonTimeCount()
	{
		return personTimeCount;
	}

	/**
	 * 设置属性值：人次总数量
	 * @param personTimeCount 人次总数量
	 */
	public void setPersonTimeCount(int personTimeCount)
	{
		this.personTimeCount = personTimeCount;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ReportResultArchivesUsePerson clone()
	{
		ReportResultArchivesUsePerson item = new ReportResultArchivesUsePerson(iD,reportID,areaFlag,useArea,personTimeCount);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param reportResultArchivesUsePerson 指定的对象源
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



