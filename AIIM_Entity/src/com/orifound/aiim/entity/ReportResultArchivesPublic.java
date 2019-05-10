package com.orifound.aiim.entity;

    
/**
 * ����ͳ�ƽ���������������
 */
public class ReportResultArchivesPublic extends StatReport
{
    /**
     * ���캯��
     */
    public ReportResultArchivesPublic()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param archivesTypeID ����һ��������
	* @param archivesTypeName һ����Ŀ����
	* @param columeCount ����ܾ���
	* @param pieceCount ����ܼ���
	* @param historyColumeCount ��ʷ�ۼƾ���
	* @param historyPieceCount ��ʷ�ۼƼ���
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
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param archivesTypeID ����һ��������
	* @param archivesTypeName һ����Ŀ����
	* @param columeCount ����ܾ���
	* @param pieceCount ����ܼ���
	* @param historyColumeCount ��ʷ�ۼƾ���
	* @param historyPieceCount ��ʷ�ۼƼ���
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
	 * ����ܾ���
	 */
	private int columeCount=0;

	/**
	 * ��ȡ����ֵ������ܾ���
	 * @return ����ܾ���
	 */
	public int getColumeCount()
	{
		return columeCount;
	}

	/**
	 * ��������ֵ������ܾ���
	 * @param columeCount ����ܾ���
	 */
	public void setColumeCount(int columeCount)
	{
		this.columeCount = columeCount;
	}

	/**
	 * ����ܼ���
	 */
	private int pieceCount=0;

	/**
	 * ��ȡ����ֵ������ܼ���
	 * @return ����ܼ���
	 */
	public int getPieceCount()
	{
		return pieceCount;
	}

	/**
	 * ��������ֵ������ܼ���
	 * @param pieceCount ����ܼ���
	 */
	public void setPieceCount(int pieceCount)
	{
		this.pieceCount = pieceCount;
	}

	/**
	 * ��ʷ�ۼƾ���
	 */
	private int historyColumeCount=0;

	/**
	 * ��ȡ����ֵ����ʷ�ۼƾ���
	 * @return ��ʷ�ۼƾ���
	 */
	public int getHistoryColumeCount()
	{
		return historyColumeCount;
	}

	/**
	 * ��������ֵ����ʷ�ۼƾ���
	 * @param historyColumeCount ��ʷ�ۼƾ���
	 */
	public void setHistoryColumeCount(int historyColumeCount)
	{
		this.historyColumeCount = historyColumeCount;
	}

	/**
	 * ��ʷ�ۼƼ���
	 */
	private int historyPieceCount=0;

	/**
	 * ��ȡ����ֵ����ʷ�ۼƼ���
	 * @return ��ʷ�ۼƼ���
	 */
	public int getHistoryPieceCount()
	{
		return historyPieceCount;
	}

	/**
	 * ��������ֵ����ʷ�ۼƼ���
	 * @param historyPieceCount ��ʷ�ۼƼ���
	 */
	public void setHistoryPieceCount(int historyPieceCount)
	{
		this.historyPieceCount = historyPieceCount;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ReportResultArchivesPublic clone()
	{
		ReportResultArchivesPublic item = new ReportResultArchivesPublic(iD,reportID,archivesTypeID,archivesTypeName,columeCount,pieceCount,historyColumeCount,historyPieceCount);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param reportResultArchivesPublic ָ���Ķ���Դ
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



