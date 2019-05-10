package com.orifound.aiim.entity;

    
/**
 * 报表统计结果表－档案分类利用情况
 */
public class ReportResultArchivesTypeUse extends StatReport
{
    /**
     * 构造函数
     */
    public ReportResultArchivesTypeUse()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 记录的唯一编号
	* @param reportID 报表编号
	* @param archivesTypeID 档案一级分类编号
	* @param archivesTypeName 一级类目名称
	* @param columeCount 总卷次
	* @param pieceCount 总件次
	*/
	public ReportResultArchivesTypeUse(int iD,int reportID,int archivesTypeID,String archivesTypeName,int columeCount,int pieceCount)
	{
		// Table Name: ReportResult_ArchivesTypeUse
		// Columns List,Can Used in SELECT SQL: ID,ReportID,ArchivesTypeID,ArchivesTypeName,ColumeCount,PieceCount
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:ArchivesTypeID,:ArchivesTypeName,:ColumeCount,:PieceCount
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,ArchivesTypeID=:ArchivesTypeID,ArchivesTypeName=:ArchivesTypeName,ColumeCount=:ColumeCount,PieceCount=:PieceCount
		this.iD = iD;
		this.reportID = reportID;
		this.archivesTypeID = archivesTypeID;
		this.archivesTypeName = archivesTypeName;
		this.columeCount = columeCount;
		this.pieceCount = pieceCount;
	}
	
	/**
	* 带字段参数的构造函数
	* @param iD 记录的唯一编号
	* @param reportID 报表编号
	* @param archivesTypeID 档案一级分类编号
	* @param archivesTypeName 一级类目名称
	* @param columeCount 总卷次
	* @param pieceCount 总件次
	*/
	public ReportResultArchivesTypeUse(int iD,int reportID,String reportTitle,int archivesTypeID,String archivesTypeName,int columeCount,int pieceCount)
	{
		// Table Name: ReportResult_ArchivesTypeUse
		// Columns List,Can Used in SELECT SQL: ID,ReportID,ArchivesTypeID,ArchivesTypeName,ColumeCount,PieceCount
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:ArchivesTypeID,:ArchivesTypeName,:ColumeCount,:PieceCount
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,ArchivesTypeID=:ArchivesTypeID,ArchivesTypeName=:ArchivesTypeName,ColumeCount=:ColumeCount,PieceCount=:PieceCount
		super.setReportTitle(reportTitle);
		this.iD = iD;
		this.reportID = reportID;
		this.archivesTypeID = archivesTypeID;
		this.archivesTypeName = archivesTypeName;
		this.columeCount = columeCount;
		this.pieceCount = pieceCount;
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
	 * 档案一级分类编号
	 */
	private int archivesTypeID=0;

	/**
	 * 获取属性值：档案一级分类编号
	 * @return 档案一级分类编号
	 */
	public int getArchivesTypeID()
	{
		return archivesTypeID;
	}

	/**
	 * 设置属性值：档案一级分类编号
	 * @param archivesTypeID 档案一级分类编号
	 */
	public void setArchivesTypeID(int archivesTypeID)
	{
		this.archivesTypeID = archivesTypeID;
	}

	/**
	 * 一级类目名称
	 */
	private String archivesTypeName=null;

	/**
	 * 获取属性值：一级类目名称
	 * @return 一级类目名称
	 */
	public String getArchivesTypeName()
	{
		return archivesTypeName;
	}

	/**
	 * 设置属性值：一级类目名称
	 * @param archivesTypeName 一级类目名称
	 */
	public void setArchivesTypeName(String archivesTypeName)
	{
		this.archivesTypeName = archivesTypeName;
	}

	/**
	 * 总卷次
	 */
	private int columeCount=0;

	/**
	 * 获取属性值：总卷次
	 * @return 总卷次
	 */
	public int getColumeCount()
	{
		return columeCount;
	}

	/**
	 * 设置属性值：总卷次
	 * @param columeCount 总卷次
	 */
	public void setColumeCount(int columeCount)
	{
		this.columeCount = columeCount;
	}

	/**
	 * 总件次
	 */
	private int pieceCount=0;

	/**
	 * 获取属性值：总件次
	 * @return 总件次
	 */
	public int getPieceCount()
	{
		return pieceCount;
	}

	/**
	 * 设置属性值：总件次
	 * @param pieceCount 总件次
	 */
	public void setPieceCount(int pieceCount)
	{
		this.pieceCount = pieceCount;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ReportResultArchivesTypeUse clone()
	{
		ReportResultArchivesTypeUse item = new ReportResultArchivesTypeUse(iD,reportID,archivesTypeID,archivesTypeName,columeCount,pieceCount);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param reportResultArchivesTypeUse 指定的对象源
	*/
	public void cloneFrom(ReportResultArchivesTypeUse reportResultArchivesTypeUse)
	{
		this.iD = reportResultArchivesTypeUse.getID();
		this.reportID = reportResultArchivesTypeUse.getReportID();
		this.archivesTypeID = reportResultArchivesTypeUse.getArchivesTypeID();
		this.archivesTypeName = reportResultArchivesTypeUse.getArchivesTypeName();
		this.columeCount = reportResultArchivesTypeUse.getColumeCount();
		this.pieceCount = reportResultArchivesTypeUse.getPieceCount();
		this.keyInCol = reportResultArchivesTypeUse.getKeyInCol();
		this.tag = reportResultArchivesTypeUse.getTag();
	}

}



