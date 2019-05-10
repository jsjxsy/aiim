package com.orifound.aiim.entity;

    
/**
 * ����ͳ�ƽ�������������������
 */
public class ReportResultArchivesTypeUse extends StatReport
{
    /**
     * ���캯��
     */
    public ReportResultArchivesTypeUse()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param archivesTypeID ����һ��������
	* @param archivesTypeName һ����Ŀ����
	* @param columeCount �ܾ��
	* @param pieceCount �ܼ���
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
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param archivesTypeID ����һ��������
	* @param archivesTypeName һ����Ŀ����
	* @param columeCount �ܾ��
	* @param pieceCount �ܼ���
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
	 * �ܾ��
	 */
	private int columeCount=0;

	/**
	 * ��ȡ����ֵ���ܾ��
	 * @return �ܾ��
	 */
	public int getColumeCount()
	{
		return columeCount;
	}

	/**
	 * ��������ֵ���ܾ��
	 * @param columeCount �ܾ��
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
	public ReportResultArchivesTypeUse clone()
	{
		ReportResultArchivesTypeUse item = new ReportResultArchivesTypeUse(iD,reportID,archivesTypeID,archivesTypeName,columeCount,pieceCount);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param reportResultArchivesTypeUse ָ���Ķ���Դ
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



