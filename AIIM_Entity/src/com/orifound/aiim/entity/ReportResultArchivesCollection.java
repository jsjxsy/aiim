package com.orifound.aiim.entity;

    
/**
 * ����ͳ�ƽ�����������ݲ����
 */
public class ReportResultArchivesCollection extends StatReport
{
    /**
     * ���캯��
     */
    public ReportResultArchivesCollection()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param archivesFondsCode ����ȫ�ڱ��
	* @param archivesFondsName ȫ������
	* @param archivesTypeID ����һ��������
	* @param archivesTypeName һ����Ŀ����
	* @param columeCount �ܾ���
	* @param pieceCount �ܼ���
	*/
	public ReportResultArchivesCollection(int iD,int reportID,String archivesFondsCode,String archivesFondsName,int archivesTypeID,String archivesTypeName,int columeCount,int pieceCount)
	{
		// Table Name: ReportResult_ArchivesCollection
		// Columns List,Can Used in SELECT SQL: ID,ReportID,ArchivesFondsCode,ArchivesFondsName,ArchivesTypeID,ArchivesTypeName,ColumeCount,PieceCount
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:ArchivesFondsCode,:ArchivesFondsName,:ArchivesTypeID,:ArchivesTypeName,:ColumeCount,:PieceCount
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,ArchivesFondsCode=:ArchivesFondsCode,ArchivesFondsName=:ArchivesFondsName,ArchivesTypeID=:ArchivesTypeID,ArchivesTypeName=:ArchivesTypeName,ColumeCount=:ColumeCount,PieceCount=:PieceCount

		this.iD = iD;
		this.reportID = reportID;
		this.archivesFondsCode = archivesFondsCode;
		this.archivesFondsName = archivesFondsName;
		this.archivesTypeID = archivesTypeID;
		this.archivesTypeName = archivesTypeName;
		this.columeCount = columeCount;
		this.pieceCount = pieceCount;
	}
	
	public ReportResultArchivesCollection(int iD,String reportTitle, int reportID,String archivesFondsCode,String archivesFondsName,int archivesTypeID,String archivesTypeName,int columeCount,int pieceCount)
	{
		// Table Name: ReportResult_ArchivesCollection
		// Columns List,Can Used in SELECT SQL: ID,ReportID,ArchivesFondsCode,ArchivesFondsName,ArchivesTypeID,ArchivesTypeName,ColumeCount,PieceCount
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:ArchivesFondsCode,:ArchivesFondsName,:ArchivesTypeID,:ArchivesTypeName,:ColumeCount,:PieceCount
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,ArchivesFondsCode=:ArchivesFondsCode,ArchivesFondsName=:ArchivesFondsName,ArchivesTypeID=:ArchivesTypeID,ArchivesTypeName=:ArchivesTypeName,ColumeCount=:ColumeCount,PieceCount=:PieceCount
		
		super.setReportTitle(reportTitle);
		this.iD = iD;
		this.reportID = reportID;
		this.archivesFondsCode = archivesFondsCode;
		this.archivesFondsName = archivesFondsName;
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
	 * ����ȫ�ڱ��
	 */
	private String archivesFondsCode=null;

	/**
	 * ��ȡ����ֵ������ȫ�ڱ��
	 * @return ����ȫ�ڱ��
	 */
	public String getArchivesFondsCode()
	{
		return archivesFondsCode;
	}

	/**
	 * ��������ֵ������ȫ�ڱ��
	 * @param archivesFondsCode ����ȫ�ڱ��
	 */
	public void setArchivesFondsCode(String archivesFondsCode)
	{
		this.archivesFondsCode = archivesFondsCode;
	}

	/**
	 * ȫ������
	 */
	private String archivesFondsName=null;

	/**
	 * ��ȡ����ֵ��ȫ������
	 * @return ȫ������
	 */
	public String getArchivesFondsName()
	{
		return archivesFondsName;
	}

	/**
	 * ��������ֵ��ȫ������
	 * @param archivesFondsName ȫ������
	 */
	public void setArchivesFondsName(String archivesFondsName)
	{
		this.archivesFondsName = archivesFondsName;
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
	public ReportResultArchivesCollection clone()
	{
		ReportResultArchivesCollection item = new ReportResultArchivesCollection(iD,reportID,archivesFondsCode,archivesFondsName,archivesTypeID,archivesTypeName,columeCount,pieceCount);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param reportResultArchivesCollection ָ���Ķ���Դ
	*/
	public void cloneFrom(ReportResultArchivesCollection reportResultArchivesCollection)
	{
		this.iD = reportResultArchivesCollection.getID();
		this.reportID = reportResultArchivesCollection.getReportID();
		this.archivesFondsCode = reportResultArchivesCollection.getArchivesFondsCode();
		this.archivesFondsName = reportResultArchivesCollection.getArchivesFondsName();
		this.archivesTypeID = reportResultArchivesCollection.getArchivesTypeID();
		this.archivesTypeName = reportResultArchivesCollection.getArchivesTypeName();
		this.columeCount = reportResultArchivesCollection.getColumeCount();
		this.pieceCount = reportResultArchivesCollection.getPieceCount();
		this.keyInCol = reportResultArchivesCollection.getKeyInCol();
		this.tag = reportResultArchivesCollection.getTag();
	}

}



