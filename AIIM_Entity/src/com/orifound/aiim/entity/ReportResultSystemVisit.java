package com.orifound.aiim.entity;

    
/**
 * 报表统计结果表－系统访问情况
 */
public class ReportResultSystemVisit extends StatReport
{
    /**
     * 构造函数
     */
    public ReportResultSystemVisit()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 记录的唯一编号
	* @param reportID 报表编号
	* @param systemFeatureID 系统功能编号
	* @param systemFeatureName 系统功能名称
	* @param visitTimeCount 总访问次数
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
	* 带字段参数的构造函数
	* @param iD 记录的唯一编号
	* @param reportID 报表编号
	* @param systemFeatureID 系统功能编号
	* @param systemFeatureName 系统功能名称
	* @param visitTimeCount 总访问次数
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
	 * 系统功能编号
	 */
	private int systemFeatureID=0;

	/**
	 * 获取属性值：系统功能编号
	 * @return 系统功能编号
	 */
	public int getSystemFeatureID()
	{
		return systemFeatureID;
	}

	/**
	 * 设置属性值：系统功能编号
	 * @param systemFeatureID 系统功能编号
	 */
	public void setSystemFeatureID(int systemFeatureID)
	{
		this.systemFeatureID = systemFeatureID;
	}

	/**
	 * 系统功能名称
	 */
	private String systemFeatureName=null;

	/**
	 * 获取属性值：系统功能名称
	 * @return 系统功能名称
	 */
	public String getSystemFeatureName()
	{
		return systemFeatureName;
	}

	/**
	 * 设置属性值：系统功能名称
	 * @param systemFeatureName 系统功能名称
	 */
	public void setSystemFeatureName(String systemFeatureName)
	{
		this.systemFeatureName = systemFeatureName;
	}

	/**
	 * 总访问次数
	 */
	private int visitTimeCount=0;

	/**
	 * 获取属性值：总访问次数
	 * @return 总访问次数
	 */
	public int getVisitTimeCount()
	{
		return visitTimeCount;
	}

	/**
	 * 设置属性值：总访问次数
	 * @param visitTimeCount 总访问次数
	 */
	public void setVisitTimeCount(int visitTimeCount)
	{
		this.visitTimeCount = visitTimeCount;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ReportResultSystemVisit clone()
	{
		ReportResultSystemVisit item = new ReportResultSystemVisit(iD,reportID,systemFeatureID,systemFeatureName,visitTimeCount);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param reportResultSystemVisit 指定的对象源
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



