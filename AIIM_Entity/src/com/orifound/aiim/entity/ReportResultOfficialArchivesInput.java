package com.orifound.aiim.entity;

    
/**
 * ����ͳ�ƽ�������ĵǼ����
 */
public class ReportResultOfficialArchivesInput extends StatReport
{
    /**
     * ���캯��
     */
    public ReportResultOfficialArchivesInput()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param departmentID ���ű��
	* @param departmentName ��������
	* @param archivesTypeID ���ĵ���������
	* @param archivesTypeName ���ĵ�����������
	* @param pieceCount �ܼ���
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
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param departmentID ���ű��
	* @param departmentName ��������
	* @param archivesTypeID ���ĵ���������
	* @param archivesTypeName ���ĵ�����������
	* @param pieceCount �ܼ���
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
	 * ���ĵ���������
	 */
	private int archivesTypeID=0;

	/**
	 * ��ȡ����ֵ�����ĵ���������
	 * @return ���ĵ���������
	 */
	public int getArchivesTypeID()
	{
		return archivesTypeID;
	}

	/**
	 * ��������ֵ�����ĵ���������
	 * @param archivesTypeID ���ĵ���������
	 */
	public void setArchivesTypeID(int archivesTypeID)
	{
		this.archivesTypeID = archivesTypeID;
	}

	/**
	 * ���ĵ�����������
	 */
	private String archivesTypeName=null;

	/**
	 * ��ȡ����ֵ�����ĵ�����������
	 * @return ���ĵ�����������
	 */
	public String getArchivesTypeName()
	{
		return archivesTypeName;
	}

	/**
	 * ��������ֵ�����ĵ�����������
	 * @param archivesTypeName ���ĵ�����������
	 */
	public void setArchivesTypeName(String archivesTypeName)
	{
		this.archivesTypeName = archivesTypeName;
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
	public ReportResultOfficialArchivesInput clone()
	{
		ReportResultOfficialArchivesInput item = new ReportResultOfficialArchivesInput(iD,reportID,departmentID,departmentName,archivesTypeID,archivesTypeName,pieceCount);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param reportResultOfficialArchivesInput ָ���Ķ���Դ
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



