package com.orifound.aiim.entity;

    
/**
 * 报表统计结果表－库房设施利用情况 
 */
public class ReportResultStoreroomUse extends StatReport
{
    /**
     * 构造函数
     */
    public ReportResultStoreroomUse()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 记录的唯一编号
	* @param reportID 报表编号
	* @param storeroomID 库房编号
	* @param storeroomName 库房名称
	* @param totalSize 总大小
	* @param usedSize 已用空间
	* @param usePercent 使用率
	*/
	public ReportResultStoreroomUse(int iD,int reportID,int storeroomID,String storeroomName,int totalSize,int usedSize,double usePercent)
	{
		// Table Name: ReportResult_StoreroomUse
		// Columns List,Can Used in SELECT SQL: ID,ReportID,StoreroomID,StoreroomName,TotalSize,UsedSize,UsePercent
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:StoreroomID,:StoreroomName,:TotalSize,:UsedSize,:UsePercent
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,StoreroomID=:StoreroomID,StoreroomName=:StoreroomName,TotalSize=:TotalSize,UsedSize=:UsedSize,UsePercent=:UsePercent

		this.iD = iD;
		this.reportID = reportID;
		this.storeroomID = storeroomID;
		this.storeroomName = storeroomName;
		this.totalSize = totalSize;
		this.usedSize = usedSize;
		this.usePercent = usePercent;
	}
	
	/**
	* 带字段参数的构造函数
	* @param iD 记录的唯一编号
	* @param reportID 报表编号
	* @param storeroomID 库房编号
	* @param storeroomName 库房名称
	* @param totalSize 总大小
	* @param usedSize 已用空间
	* @param usePercent 使用率
	*/
	public ReportResultStoreroomUse(int iD,int reportID,String reportTitle,int storeroomID,String storeroomName,int totalSize,int usedSize,double usePercent)
	{
		// Table Name: ReportResult_StoreroomUse
		// Columns List,Can Used in SELECT SQL: ID,ReportID,StoreroomID,StoreroomName,TotalSize,UsedSize,UsePercent
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:StoreroomID,:StoreroomName,:TotalSize,:UsedSize,:UsePercent
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,StoreroomID=:StoreroomID,StoreroomName=:StoreroomName,TotalSize=:TotalSize,UsedSize=:UsedSize,UsePercent=:UsePercent
		super.setReportTitle(reportTitle);
		this.iD = iD;
		this.reportID = reportID;
		this.storeroomID = storeroomID;
		this.storeroomName = storeroomName;
		this.totalSize = totalSize;
		this.usedSize = usedSize;
		this.usePercent = usePercent;
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
	 * 库房编号
	 */
	private int storeroomID=0;

	/**
	 * 获取属性值：库房编号
	 * @return 库房编号
	 */
	public int getStoreroomID()
	{
		return storeroomID;
	}

	/**
	 * 设置属性值：库房编号
	 * @param storeroomID 库房编号
	 */
	public void setStoreroomID(int storeroomID)
	{
		this.storeroomID = storeroomID;
	}

	/**
	 * 库房名称
	 */
	private String storeroomName=null;

	/**
	 * 获取属性值：库房名称
	 * @return 库房名称
	 */
	public String getStoreroomName()
	{
		return storeroomName;
	}

	/**
	 * 设置属性值：库房名称
	 * @param storeroomName 库房名称
	 */
	public void setStoreroomName(String storeroomName)
	{
		this.storeroomName = storeroomName;
	}

	/**
	 * 总大小
	 */
	private int totalSize=0;

	/**
	 * 获取属性值：总大小
	 * @return 总大小
	 */
	public int getTotalSize()
	{
		return totalSize;
	}

	/**
	 * 设置属性值：总大小
	 * @param totalSize 总大小
	 */
	public void setTotalSize(int totalSize)
	{
		this.totalSize = totalSize;
	}

	/**
	 * 已用空间
	 */
	private int usedSize=0;

	/**
	 * 获取属性值：已用空间
	 * @return 已用空间
	 */
	public int getUsedSize()
	{
		return usedSize;
	}

	/**
	 * 设置属性值：已用空间
	 * @param usedSize 已用空间
	 */
	public void setUsedSize(int usedSize)
	{
		this.usedSize = usedSize;
	}

	/**
	 * 使用率
	 */
	private double usePercent=0;

	/**
	 * 获取属性值：使用率
	 * @return 使用率
	 */
	public double getUsePercent()
	{
		return usePercent;
	}

	/**
	 * 设置属性值：使用率
	 * @param usePercent 使用率
	 */
	public void setUsePercent(double usePercent)
	{
		this.usePercent = usePercent;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ReportResultStoreroomUse clone()
	{
		ReportResultStoreroomUse item = new ReportResultStoreroomUse(iD,reportID,storeroomID,storeroomName,totalSize,usedSize,usePercent);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param reportResultStoreroomUse 指定的对象源
	*/
	public void cloneFrom(ReportResultStoreroomUse reportResultStoreroomUse)
	{
		this.iD = reportResultStoreroomUse.getID();
		this.reportID = reportResultStoreroomUse.getReportID();
		this.storeroomID = reportResultStoreroomUse.getStoreroomID();
		this.storeroomName = reportResultStoreroomUse.getStoreroomName();
		this.totalSize = reportResultStoreroomUse.getTotalSize();
		this.usedSize = reportResultStoreroomUse.getUsedSize();
		this.usePercent = reportResultStoreroomUse.getUsePercent();
		this.keyInCol = reportResultStoreroomUse.getKeyInCol();
		this.tag = reportResultStoreroomUse.getTag();
	}

    
}



