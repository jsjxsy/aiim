package com.orifound.aiim.entity;

    
/**
 * ����ͳ�ƽ�������µ���ת����� 
 */
public class ReportResultPersionalArchivesMoveOut extends StatReport
{
    /**
     * ���캯��
     */
    public ReportResultPersionalArchivesMoveOut()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param archivesTypeID ���µ�������������
	* @param archivesTypeName ���µ���������������
	* @param columeCount �ܾ���
	*/
	public ReportResultPersionalArchivesMoveOut(int iD,int reportID,int archivesTypeID,String archivesTypeName,int columeCount)
	{
		// Table Name: ReportResult_PersionalArchivesMoveOut
		// Columns List,Can Used in SELECT SQL: ID,ReportID,ArchivesTypeID,ArchivesTypeName,ColumeCount
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:ArchivesTypeID,:ArchivesTypeName,:ColumeCount
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,ArchivesTypeID=:ArchivesTypeID,ArchivesTypeName=:ArchivesTypeName,ColumeCount=:ColumeCount

		this.iD = iD;
		this.reportID = reportID;
		this.archivesTypeID = archivesTypeID;
		this.archivesTypeName = archivesTypeName;
		this.columeCount = columeCount;
	}

	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param archivesTypeID ���µ�������������
	* @param archivesTypeName ���µ���������������
	* @param columeCount �ܾ���
	*/
	public ReportResultPersionalArchivesMoveOut(int iD,int reportID,String reportTitle,int archivesTypeID,String archivesTypeName,int columeCount)
	{
		// Table Name: ReportResult_PersionalArchivesMoveOut
		// Columns List,Can Used in SELECT SQL: ID,ReportID,ArchivesTypeID,ArchivesTypeName,ColumeCount
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:ArchivesTypeID,:ArchivesTypeName,:ColumeCount
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,ArchivesTypeID=:ArchivesTypeID,ArchivesTypeName=:ArchivesTypeName,ColumeCount=:ColumeCount
		super.setReportTitle(reportTitle);
		this.iD = iD;
		this.reportID = reportID;
		this.archivesTypeID = archivesTypeID;
		this.archivesTypeName = archivesTypeName;
		this.columeCount = columeCount;
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
	 * ���µ�������������
	 */
	private int archivesTypeID=0;

	/**
	 * ��ȡ����ֵ�����µ�������������
	 * @return ���µ�������������
	 */
	public int getArchivesTypeID()
	{
		return archivesTypeID;
	}

	/**
	 * ��������ֵ�����µ�������������
	 * @param archivesTypeID ���µ�������������
	 */
	public void setArchivesTypeID(int archivesTypeID)
	{
		this.archivesTypeID = archivesTypeID;
	}

	/**
	 * ���µ���������������
	 */
	private String archivesTypeName=null;

	/**
	 * ��ȡ����ֵ�����µ���������������
	 * @return ���µ���������������
	 */
	public String getArchivesTypeName()
	{
		return archivesTypeName;
	}

	/**
	 * ��������ֵ�����µ���������������
	 * @param archivesTypeName ���µ���������������
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
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ReportResultPersionalArchivesMoveOut clone()
	{
		ReportResultPersionalArchivesMoveOut item = new ReportResultPersionalArchivesMoveOut(iD,reportID,archivesTypeID,archivesTypeName,columeCount);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param reportResultPersionalArchivesMoveOut ָ���Ķ���Դ
	*/
	public void cloneFrom(ReportResultPersionalArchivesMoveOut reportResultPersionalArchivesMoveOut)
	{
		this.iD = reportResultPersionalArchivesMoveOut.getID();
		this.reportID = reportResultPersionalArchivesMoveOut.getReportID();
		this.archivesTypeID = reportResultPersionalArchivesMoveOut.getArchivesTypeID();
		this.archivesTypeName = reportResultPersionalArchivesMoveOut.getArchivesTypeName();
		this.columeCount = reportResultPersionalArchivesMoveOut.getColumeCount();
		this.keyInCol = reportResultPersionalArchivesMoveOut.getKeyInCol();
		this.tag = reportResultPersionalArchivesMoveOut.getTag();
	}    
}



