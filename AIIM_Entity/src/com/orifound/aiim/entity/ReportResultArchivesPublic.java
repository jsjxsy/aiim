package com.orifound.aiim.entity;

    
/**
 * 报表统计结果表－档案开放情况
 */
public class ReportResultArchivesPublic extends StatReport
{
    /**
     * 构造函数
     */
    public ReportResultArchivesPublic()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 记录的唯一编号
	* @param reportID 报表编号
	* @param archivesTypeID 档案一级分类编号
	* @param archivesTypeName 一级类目名称
	* @param columeCount 年度总卷数
	* @param pieceCount 年度总件数
	* @param historyColumeCount 历史累计卷数
	* @param historyPieceCount 历史累计件数
	*/
	public ReportResultArchivesPublic(int iD,int reportID,int archivesTypeID,String archivesTypeName,int columeCount,int pieceCount,int historyColumeCount,int historyPieceCount)
	{
		// Table Name: ReportResult_ArchivesPublic
		// Columns List,Can Used in SELECT SQL: ID,ReportID,ArchivesTypeID,ArchivesTypeName,ColumeCount,PieceCount,HistoryColumeCount,HistoryPieceCount
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:ArchivesTypeID,:ArchivesTypeName,:ColumeCount,:PieceCount,:HistoryColumeCount,:HistoryPieceCount
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,ArchivesTypeID=:ArchivesTypeID,ArchivesTypeName=:ArchivesTypeName,ColumeCount=:ColumeCount,PieceCount=:PieceCount,HistoryColumeCount=:HistoryColumeCount,HistoryPieceCount=:HistoryPieceCount

		this.iD = iD;
		this.reportID = reportID;
		this.archivesTypeID = archivesTypeID;
		this.archivesTypeName = archivesTypeName;
		this.columeCount = columeCount;
		this.pieceCount = pieceCount;
		this.historyColumeCount = historyColumeCount;
		this.historyPieceCount = historyPieceCount;
	}
	
	
	/**
	* 带字段参数的构造函数
	* @param iD 记录的唯一编号
	* @param reportID 报表编号
	* @param archivesTypeID 档案一级分类编号
	* @param archivesTypeName 一级类目名称
	* @param columeCount 年度总卷数
	* @param pieceCount 年度总件数
	* @param historyColumeCount 历史累计卷数
	* @param historyPieceCount 历史累计件数
	*/
	public ReportResultArchivesPublic(int iD,int reportID,String reportTitle,int archivesTypeID,String archivesTypeName,int columeCount,int pieceCount,int historyColumeCount,int historyPieceCount)
	{
		// Table Name: ReportResult_ArchivesPublic
		// Columns List,Can Used in SELECT SQL: ID,ReportID,ArchivesTypeID,ArchivesTypeName,ColumeCount,PieceCount,HistoryColumeCount,HistoryPieceCount
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:ArchivesTypeID,:ArchivesTypeName,:ColumeCount,:PieceCount,:HistoryColumeCount,:HistoryPieceCount
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,ArchivesTypeID=:ArchivesTypeID,ArchivesTypeName=:ArchivesTypeName,ColumeCount=:ColumeCount,PieceCount=:PieceCount,HistoryColumeCount=:HistoryColumeCount,HistoryPieceCount=:HistoryPieceCount
		super.setReportTitle(reportTitle);
		this.iD = iD;
		this.reportID = reportID;
		this.archivesTypeID = archivesTypeID;
		this.archivesTypeName = archivesTypeName;
		this.columeCount = columeCount;
		this.pieceCount = pieceCount;
		this.historyColumeCount = historyColumeCount;
		this.historyPieceCount = historyPieceCount;
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
	 * 年度总卷数
	 */
	private int columeCount=0;

	/**
	 * 获取属性值：年度总卷数
	 * @return 年度总卷数
	 */
	public int getColumeCount()
	{
		return columeCount;
	}

	/**
	 * 设置属性值：年度总卷数
	 * @param columeCount 年度总卷数
	 */
	public void setColumeCount(int columeCount)
	{
		this.columeCount = columeCount;
	}

	/**
	 * 年度总件数
	 */
	private int pieceCount=0;

	/**
	 * 获取属性值：年度总件数
	 * @return 年度总件数
	 */
	public int getPieceCount()
	{
		return pieceCount;
	}

	/**
	 * 设置属性值：年度总件数
	 * @param pieceCount 年度总件数
	 */
	public void setPieceCount(int pieceCount)
	{
		this.pieceCount = pieceCount;
	}

	/**
	 * 历史累计卷数
	 */
	private int historyColumeCount=0;

	/**
	 * 获取属性值：历史累计卷数
	 * @return 历史累计卷数
	 */
	public int getHistoryColumeCount()
	{
		return historyColumeCount;
	}

	/**
	 * 设置属性值：历史累计卷数
	 * @param historyColumeCount 历史累计卷数
	 */
	public void setHistoryColumeCount(int historyColumeCount)
	{
		this.historyColumeCount = historyColumeCount;
	}

	/**
	 * 历史累计件数
	 */
	private int historyPieceCount=0;

	/**
	 * 获取属性值：历史累计件数
	 * @return 历史累计件数
	 */
	public int getHistoryPieceCount()
	{
		return historyPieceCount;
	}

	/**
	 * 设置属性值：历史累计件数
	 * @param historyPieceCount 历史累计件数
	 */
	public void setHistoryPieceCount(int historyPieceCount)
	{
		this.historyPieceCount = historyPieceCount;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ReportResultArchivesPublic clone()
	{
		ReportResultArchivesPublic item = new ReportResultArchivesPublic(iD,reportID,archivesTypeID,archivesTypeName,columeCount,pieceCount,historyColumeCount,historyPieceCount);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param reportResultArchivesPublic 指定的对象源
	*/
	public void cloneFrom(ReportResultArchivesPublic reportResultArchivesPublic)
	{
		this.iD = reportResultArchivesPublic.getID();
		this.reportID = reportResultArchivesPublic.getReportID();
		this.archivesTypeID = reportResultArchivesPublic.getArchivesTypeID();
		this.archivesTypeName = reportResultArchivesPublic.getArchivesTypeName();
		this.columeCount = reportResultArchivesPublic.getColumeCount();
		this.pieceCount = reportResultArchivesPublic.getPieceCount();
		this.historyColumeCount = reportResultArchivesPublic.getHistoryColumeCount();
		this.historyPieceCount = reportResultArchivesPublic.getHistoryPieceCount();
		this.keyInCol = reportResultArchivesPublic.getKeyInCol();
		this.tag = reportResultArchivesPublic.getTag();
	}


    
}



