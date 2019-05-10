package com.orifound.aiim.entity;

    
/**
 * ����ͳ�ƽ������������Ŀ�����
 */
public class ReportResultArchivesUsePurpose extends StatReport
{
    /**
     * ���캯��
     */
    public ReportResultArchivesUsePurpose()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param purposeID ����Ŀ�ı��
	* @param purposeName ��������Ŀ��
	* @param columeCount �ܾ���
	* @param pieceCount �ܼ���
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
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param purposeID ����Ŀ�ı��
	* @param purposeName ��������Ŀ��
	* @param columeCount �ܾ���
	* @param pieceCount �ܼ���
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
	 * ����Ŀ�ı��
	 */
	private int purposeID=0;

	/**
	 * ��ȡ����ֵ������Ŀ�ı��
	 * @return ����Ŀ�ı��
	 */
	public int getPurposeID()
	{
		return purposeID;
	}

	/**
	 * ��������ֵ������Ŀ�ı��
	 * @param purposeID ����Ŀ�ı��
	 */
	public void setPurposeID(int purposeID)
	{
		this.purposeID = purposeID;
	}

	/**
	 * ��������Ŀ��
	 */
	private String purposeName=null;

	/**
	 * ��ȡ����ֵ����������Ŀ��
	 * @return ��������Ŀ��
	 */
	public String getPurposeName()
	{
		return purposeName;
	}

	/**
	 * ��������ֵ����������Ŀ��
	 * @param purposeName ��������Ŀ��
	 */
	public void setPurposeName(String purposeName)
	{
		this.purposeName = purposeName;
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
	public ReportResultArchivesUsePurpose clone()
	{
		ReportResultArchivesUsePurpose item = new ReportResultArchivesUsePurpose(iD,reportID,purposeID,purposeName,columeCount,pieceCount);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param reportResultArchivesUsePurpose ָ���Ķ���Դ
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



