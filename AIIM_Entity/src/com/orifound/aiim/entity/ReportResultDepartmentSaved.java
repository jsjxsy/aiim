package com.orifound.aiim.entity;

    
/**
 * ����ͳ�ƽ�������Ź鵵���
 */
public class ReportResultDepartmentSaved extends StatReport
{
    /**
     * ���캯��
     */
    public ReportResultDepartmentSaved()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param departmentID ���ű��
	* @param departmentName ��������
	* @param archivesTypeID ����һ��������
	* @param archivesTypeName һ����Ŀ����
	* @param columeCount �ܾ���
	* @param pieceCount �ܼ���
	*/
	public ReportResultDepartmentSaved(int iD,int reportID,int departmentID,String departmentName,int archivesTypeID,String archivesTypeName,int columeCount,int pieceCount)
	{
		// Table Name: ReportResult_DepartmentSaved
		// Columns List,Can Used in SELECT SQL: ID,ReportID,DepartmentID,DepartmentName,ArchivesTypeID,ArchivesTypeName,ColumeCount,PieceCount
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:DepartmentID,:DepartmentName,:ArchivesTypeID,:ArchivesTypeName,:ColumeCount,:PieceCount
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,DepartmentID=:DepartmentID,DepartmentName=:DepartmentName,ArchivesTypeID=:ArchivesTypeID,ArchivesTypeName=:ArchivesTypeName,ColumeCount=:ColumeCount,PieceCount=:PieceCount

		this.iD = iD;
		this.reportID = reportID;
		this.departmentID = departmentID;
		this.departmentName = departmentName;
		this.archivesTypeID = archivesTypeID;
		this.archivesTypeName = archivesTypeName;
		this.columeCount = columeCount;
		this.pieceCount = pieceCount;
	}

	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param departmentID ���ű��
	* @param departmentName ��������
	* @param archivesTypeID ����һ��������
	* @param archivesTypeName һ����Ŀ����
	* @param columeCount �ܾ���
	* @param pieceCount �ܼ���
	*/
	public ReportResultDepartmentSaved(int iD,int reportID,String reportTitle,int departmentID,String departmentName,int archivesTypeID,String archivesTypeName,int columeCount,int pieceCount)
	{
		// Table Name: ReportResult_DepartmentSaved
		// Columns List,Can Used in SELECT SQL: ID,ReportID,DepartmentID,DepartmentName,ArchivesTypeID,ArchivesTypeName,ColumeCount,PieceCount
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:DepartmentID,:DepartmentName,:ArchivesTypeID,:ArchivesTypeName,:ColumeCount,:PieceCount
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,DepartmentID=:DepartmentID,DepartmentName=:DepartmentName,ArchivesTypeID=:ArchivesTypeID,ArchivesTypeName=:ArchivesTypeName,ColumeCount=:ColumeCount,PieceCount=:PieceCount
		super.setReportTitle(reportTitle);
		this.iD = iD;
		this.reportID = reportID;
		this.departmentID = departmentID;
		this.departmentName = departmentName;
		this.archivesTypeID = archivesTypeID;
		this.archivesTypeName = archivesTypeName;
		this.columeCount = columeCount;
		this.pieceCount = pieceCount;
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
	 * ���ű��
	 */
	private int departmentID=0;

	/**
	 * ��ȡ����ֵ�����ű��
	 * @return ���ű��
	 */
	public int getDepartmentID()
	{
		return departmentID;
	}

	/**
	 * ��������ֵ�����ű��
	 * @param departmentID ���ű��
	 */
	public void setDepartmentID(int departmentID)
	{
		this.departmentID = departmentID;
	}

	/**
	 * ��������
	 */
	private String departmentName=null;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public String getDepartmentName()
	{
		return departmentName;
	}

	/**
	 * ��������ֵ����������
	 * @param departmentName ��������
	 */
	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}

	/**
	 * ����һ��������
	 */
	private int archivesTypeID=0;

	/**
	 * ��ȡ����ֵ������һ��������
	 * @return ����һ��������
	 */
	public int getArchivesTypeID()
	{
		return archivesTypeID;
	}

	/**
	 * ��������ֵ������һ��������
	 * @param archivesTypeID ����һ��������
	 */
	public void setArchivesTypeID(int archivesTypeID)
	{
		this.archivesTypeID = archivesTypeID;
	}

	/**
	 * һ����Ŀ����
	 */
	private String archivesTypeName=null;

	/**
	 * ��ȡ����ֵ��һ����Ŀ����
	 * @return һ����Ŀ����
	 */
	public String getArchivesTypeName()
	{
		return archivesTypeName;
	}

	/**
	 * ��������ֵ��һ����Ŀ����
	 * @param archivesTypeName һ����Ŀ����
	 */
	public void setArchivesTypeName(String archivesTypeName)
	{
		this.archivesTypeName = archivesTypeName;
	}

	/**
	 * �ܾ���
	 */
	private int columeCount=0;

	/**
	 * ��ȡ����ֵ���ܾ���
	 * @return �ܾ���
	 */
	public int getColumeCount()
	{
		return columeCount;
	}

	/**
	 * ��������ֵ���ܾ���
	 * @param columeCount �ܾ���
	 */
	public void setColumeCount(int columeCount)
	{
		this.columeCount = columeCount;
	}

	/**
	 * �ܼ���
	 */
	private int pieceCount=0;

	/**
	 * ��ȡ����ֵ���ܼ���
	 * @return �ܼ���
	 */
	public int getPieceCount()
	{
		return pieceCount;
	}

	/**
	 * ��������ֵ���ܼ���
	 * @param pieceCount �ܼ���
	 */
	public void setPieceCount(int pieceCount)
	{
		this.pieceCount = pieceCount;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ReportResultDepartmentSaved clone()
	{
		ReportResultDepartmentSaved item = new ReportResultDepartmentSaved(iD,reportID,departmentID,departmentName,archivesTypeID,archivesTypeName,columeCount,pieceCount);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param reportResultDepartmentSaved ָ���Ķ���Դ
	*/
	public void cloneFrom(ReportResultDepartmentSaved reportResultDepartmentSaved)
	{
		this.iD = reportResultDepartmentSaved.getID();
		this.reportID = reportResultDepartmentSaved.getReportID();
		this.departmentID = reportResultDepartmentSaved.getDepartmentID();
		this.departmentName = reportResultDepartmentSaved.getDepartmentName();
		this.archivesTypeID = reportResultDepartmentSaved.getArchivesTypeID();
		this.archivesTypeName = reportResultDepartmentSaved.getArchivesTypeName();
		this.columeCount = reportResultDepartmentSaved.getColumeCount();
		this.pieceCount = reportResultDepartmentSaved.getPieceCount();
		this.keyInCol = reportResultDepartmentSaved.getKeyInCol();
		this.tag = reportResultDepartmentSaved.getTag();
	}


    
}



