package com.orifound.aiim.entity;

    
/**
 * 报表统计结果表－公文登记情况
 */
public class ReportResultOfficialArchivesInput extends StatReport
{
    /**
     * 构造函数
     */
    public ReportResultOfficialArchivesInput()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 记录的唯一编号
	* @param reportID 报表编号
	* @param departmentID 部门编号
	* @param departmentName 部门名称
	* @param archivesTypeID 公文档案分类编号
	* @param archivesTypeName 公文档案分类名称
	* @param pieceCount 总件数
	*/
	public ReportResultOfficialArchivesInput(int iD,int reportID,int departmentID,String departmentName,int archivesTypeID,String archivesTypeName,int pieceCount)
	{
		// Table Name: ReportResult_OfficialArchivesInput
		// Columns List,Can Used in SELECT SQL: ID,ReportID,DepartmentID,DepartmentName,ArchivesTypeID,ArchivesTypeName,PieceCount
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:DepartmentID,:DepartmentName,:ArchivesTypeID,:ArchivesTypeName,:PieceCount
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,DepartmentID=:DepartmentID,DepartmentName=:DepartmentName,ArchivesTypeID=:ArchivesTypeID,ArchivesTypeName=:ArchivesTypeName,PieceCount=:PieceCount

		this.iD = iD;
		this.reportID = reportID;
		this.departmentID = departmentID;
		this.departmentName = departmentName;
		this.archivesTypeID = archivesTypeID;
		this.archivesTypeName = archivesTypeName;
		this.pieceCount = pieceCount;
	}

	/**
	* 带字段参数的构造函数
	* @param iD 记录的唯一编号
	* @param reportID 报表编号
	* @param departmentID 部门编号
	* @param departmentName 部门名称
	* @param archivesTypeID 公文档案分类编号
	* @param archivesTypeName 公文档案分类名称
	* @param pieceCount 总件数
	*/
	public ReportResultOfficialArchivesInput(int iD,int reportID,String reportTitle,int departmentID,String departmentName,int archivesTypeID,String archivesTypeName,int pieceCount)
	{
		// Table Name: ReportResult_OfficialArchivesInput
		// Columns List,Can Used in SELECT SQL: ID,ReportID,DepartmentID,DepartmentName,ArchivesTypeID,ArchivesTypeName,PieceCount
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:DepartmentID,:DepartmentName,:ArchivesTypeID,:ArchivesTypeName,:PieceCount
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,DepartmentID=:DepartmentID,DepartmentName=:DepartmentName,ArchivesTypeID=:ArchivesTypeID,ArchivesTypeName=:ArchivesTypeName,PieceCount=:PieceCount
		super.setReportTitle(reportTitle);
		this.iD = iD;
		this.reportID = reportID;
		this.departmentID = departmentID;
		this.departmentName = departmentName;
		this.archivesTypeID = archivesTypeID;
		this.archivesTypeName = archivesTypeName;
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
	 * 部门编号
	 */
	private int departmentID=0;

	/**
	 * 获取属性值：部门编号
	 * @return 部门编号
	 */
	public int getDepartmentID()
	{
		return departmentID;
	}

	/**
	 * 设置属性值：部门编号
	 * @param departmentID 部门编号
	 */
	public void setDepartmentID(int departmentID)
	{
		this.departmentID = departmentID;
	}

	/**
	 * 部门名称
	 */
	private String departmentName=null;

	/**
	 * 获取属性值：部门名称
	 * @return 部门名称
	 */
	public String getDepartmentName()
	{
		return departmentName;
	}

	/**
	 * 设置属性值：部门名称
	 * @param departmentName 部门名称
	 */
	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}

	/**
	 * 公文档案分类编号
	 */
	private int archivesTypeID=0;

	/**
	 * 获取属性值：公文档案分类编号
	 * @return 公文档案分类编号
	 */
	public int getArchivesTypeID()
	{
		return archivesTypeID;
	}

	/**
	 * 设置属性值：公文档案分类编号
	 * @param archivesTypeID 公文档案分类编号
	 */
	public void setArchivesTypeID(int archivesTypeID)
	{
		this.archivesTypeID = archivesTypeID;
	}

	/**
	 * 公文档案分类名称
	 */
	private String archivesTypeName=null;

	/**
	 * 获取属性值：公文档案分类名称
	 * @return 公文档案分类名称
	 */
	public String getArchivesTypeName()
	{
		return archivesTypeName;
	}

	/**
	 * 设置属性值：公文档案分类名称
	 * @param archivesTypeName 公文档案分类名称
	 */
	public void setArchivesTypeName(String archivesTypeName)
	{
		this.archivesTypeName = archivesTypeName;
	}

	/**
	 * 总件数
	 */
	private int pieceCount=0;

	/**
	 * 获取属性值：总件数
	 * @return 总件数
	 */
	public int getPieceCount()
	{
		return pieceCount;
	}

	/**
	 * 设置属性值：总件数
	 * @param pieceCount 总件数
	 */
	public void setPieceCount(int pieceCount)
	{
		this.pieceCount = pieceCount;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ReportResultOfficialArchivesInput clone()
	{
		ReportResultOfficialArchivesInput item = new ReportResultOfficialArchivesInput(iD,reportID,departmentID,departmentName,archivesTypeID,archivesTypeName,pieceCount);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param reportResultOfficialArchivesInput 指定的对象源
	*/
	public void cloneFrom(ReportResultOfficialArchivesInput reportResultOfficialArchivesInput)
	{
		this.iD = reportResultOfficialArchivesInput.getID();
		this.reportID = reportResultOfficialArchivesInput.getReportID();
		this.departmentID = reportResultOfficialArchivesInput.getDepartmentID();
		this.departmentName = reportResultOfficialArchivesInput.getDepartmentName();
		this.archivesTypeID = reportResultOfficialArchivesInput.getArchivesTypeID();
		this.archivesTypeName = reportResultOfficialArchivesInput.getArchivesTypeName();
		this.pieceCount = reportResultOfficialArchivesInput.getPieceCount();
		this.keyInCol = reportResultOfficialArchivesInput.getKeyInCol();
		this.tag = reportResultOfficialArchivesInput.getTag();
	}

    
}



