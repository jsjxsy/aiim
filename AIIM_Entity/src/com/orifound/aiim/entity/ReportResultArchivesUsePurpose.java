package com.orifound.aiim.entity;

    
/**
 * 报表统计结果表－档案利用目的情况
 */
public class ReportResultArchivesUsePurpose extends StatReport
{
    /**
     * 构造函数
     */
    public ReportResultArchivesUsePurpose()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 记录的唯一编号
	* @param reportID 报表编号
	* @param purposeID 利用目的编号
	* @param purposeName 档案利用目的
	* @param columeCount 总卷数
	* @param pieceCount 总件次
	*/
	public ReportResultArchivesUsePurpose(int iD,int reportID,int purposeID,String purposeName,int columeCount,int pieceCount)
	{
		// Table Name: ReportResult_ArchivesUsePurpose
		// Columns List,Can Used in SELECT SQL: ID,ReportID,PurposeID,PurposeName,ColumeCount,PieceCount
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:PurposeID,:PurposeName,:ColumeCount,:PieceCount
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,PurposeID=:PurposeID,PurposeName=:PurposeName,ColumeCount=:ColumeCount,PieceCount=:PieceCount

		this.iD = iD;
		this.reportID = reportID;
		this.purposeID = purposeID;
		this.purposeName = purposeName;
		this.columeCount = columeCount;
		this.pieceCount = pieceCount;
	}
	
	 
	/**
	* 带字段参数的构造函数
	* @param iD 记录的唯一编号
	* @param reportID 报表编号
	* @param purposeID 利用目的编号
	* @param purposeName 档案利用目的
	* @param columeCount 总卷数
	* @param pieceCount 总件次
	*/
	public ReportResultArchivesUsePurpose(int iD,int reportID,String reportTitle,int purposeID,String purposeName,int columeCount,int pieceCount)
	{
		// Table Name: ReportResult_ArchivesUsePurpose
		// Columns List,Can Used in SELECT SQL: ID,ReportID,PurposeID,PurposeName,ColumeCount,PieceCount
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:PurposeID,:PurposeName,:ColumeCount,:PieceCount
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,PurposeID=:PurposeID,PurposeName=:PurposeName,ColumeCount=:ColumeCount,PieceCount=:PieceCount
		super.setReportTitle(reportTitle);
		this.iD = iD;
		this.reportID = reportID;
		this.purposeID = purposeID;
		this.purposeName = purposeName;
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
	 * 利用目的编号
	 */
	private int purposeID=0;

	/**
	 * 获取属性值：利用目的编号
	 * @return 利用目的编号
	 */
	public int getPurposeID()
	{
		return purposeID;
	}

	/**
	 * 设置属性值：利用目的编号
	 * @param purposeID 利用目的编号
	 */
	public void setPurposeID(int purposeID)
	{
		this.purposeID = purposeID;
	}

	/**
	 * 档案利用目的
	 */
	private String purposeName=null;

	/**
	 * 获取属性值：档案利用目的
	 * @return 档案利用目的
	 */
	public String getPurposeName()
	{
		return purposeName;
	}

	/**
	 * 设置属性值：档案利用目的
	 * @param purposeName 档案利用目的
	 */
	public void setPurposeName(String purposeName)
	{
		this.purposeName = purposeName;
	}

	/**
	 * 总卷数
	 */
	private int columeCount=0;

	/**
	 * 获取属性值：总卷数
	 * @return 总卷数
	 */
	public int getColumeCount()
	{
		return columeCount;
	}

	/**
	 * 设置属性值：总卷数
	 * @param columeCount 总卷数
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
	public ReportResultArchivesUsePurpose clone()
	{
		ReportResultArchivesUsePurpose item = new ReportResultArchivesUsePurpose(iD,reportID,purposeID,purposeName,columeCount,pieceCount);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param reportResultArchivesUsePurpose 指定的对象源
	*/
	public void cloneFrom(ReportResultArchivesUsePurpose reportResultArchivesUsePurpose)
	{
		this.iD = reportResultArchivesUsePurpose.getID();
		this.reportID = reportResultArchivesUsePurpose.getReportID();
		this.purposeID = reportResultArchivesUsePurpose.getPurposeID();
		this.purposeName = reportResultArchivesUsePurpose.getPurposeName();
		this.columeCount = reportResultArchivesUsePurpose.getColumeCount();
		this.pieceCount = reportResultArchivesUsePurpose.getPieceCount();
		this.keyInCol = reportResultArchivesUsePurpose.getKeyInCol();
		this.tag = reportResultArchivesUsePurpose.getTag();
	}


    
}



