package com.orifound.aiim.entity;

    
/**
 * ����ͳ�ƽ�����ⷿ��ʩ������� 
 */
public class ReportResultStoreroomUse extends StatReport
{
    /**
     * ���캯��
     */
    public ReportResultStoreroomUse()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param storeroomID �ⷿ���
	* @param storeroomName �ⷿ����
	* @param totalSize �ܴ�С
	* @param usedSize ���ÿռ�
	* @param usePercent ʹ����
	*/
	public ReportResultStoreroomUse(int iD,int reportID,int storeroomID,String storeroomName,int totalSize,int usedSize,double usePercent)
	{
		// Table Name: ReportResult_StoreroomUse
		// Columns List,Can Used in SELECT SQL: ID,ReportID,StoreroomID,StoreroomName,TotalSize,UsedSize,UsePercent
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:StoreroomID,:StoreroomName,:TotalSize,:UsedSize,:UsePercent
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,StoreroomID=:StoreroomID,StoreroomName=:StoreroomName,TotalSize=:TotalSize,UsedSize=:UsedSize,UsePercent=:UsePercent

		this.iD = iD;
		this.reportID = reportID;
		this.storeroomID = storeroomID;
		this.storeroomName = storeroomName;
		this.totalSize = totalSize;
		this.usedSize = usedSize;
		this.usePercent = usePercent;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param storeroomID �ⷿ���
	* @param storeroomName �ⷿ����
	* @param totalSize �ܴ�С
	* @param usedSize ���ÿռ�
	* @param usePercent ʹ����
	*/
	public ReportResultStoreroomUse(int iD,int reportID,String reportTitle,int storeroomID,String storeroomName,int totalSize,int usedSize,double usePercent)
	{
		// Table Name: ReportResult_StoreroomUse
		// Columns List,Can Used in SELECT SQL: ID,ReportID,StoreroomID,StoreroomName,TotalSize,UsedSize,UsePercent
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:StoreroomID,:StoreroomName,:TotalSize,:UsedSize,:UsePercent
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,StoreroomID=:StoreroomID,StoreroomName=:StoreroomName,TotalSize=:TotalSize,UsedSize=:UsedSize,UsePercent=:UsePercent
		super.setReportTitle(reportTitle);
		this.iD = iD;
		this.reportID = reportID;
		this.storeroomID = storeroomID;
		this.storeroomName = storeroomName;
		this.totalSize = totalSize;
		this.usedSize = usedSize;
		this.usePercent = usePercent;
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
	 * �ⷿ���
	 */
	private int storeroomID=0;

	/**
	 * ��ȡ����ֵ���ⷿ���
	 * @return �ⷿ���
	 */
	public int getStoreroomID()
	{
		return storeroomID;
	}

	/**
	 * ��������ֵ���ⷿ���
	 * @param storeroomID �ⷿ���
	 */
	public void setStoreroomID(int storeroomID)
	{
		this.storeroomID = storeroomID;
	}

	/**
	 * �ⷿ����
	 */
	private String storeroomName=null;

	/**
	 * ��ȡ����ֵ���ⷿ����
	 * @return �ⷿ����
	 */
	public String getStoreroomName()
	{
		return storeroomName;
	}

	/**
	 * ��������ֵ���ⷿ����
	 * @param storeroomName �ⷿ����
	 */
	public void setStoreroomName(String storeroomName)
	{
		this.storeroomName = storeroomName;
	}

	/**
	 * �ܴ�С
	 */
	private int totalSize=0;

	/**
	 * ��ȡ����ֵ���ܴ�С
	 * @return �ܴ�С
	 */
	public int getTotalSize()
	{
		return totalSize;
	}

	/**
	 * ��������ֵ���ܴ�С
	 * @param totalSize �ܴ�С
	 */
	public void setTotalSize(int totalSize)
	{
		this.totalSize = totalSize;
	}

	/**
	 * ���ÿռ�
	 */
	private int usedSize=0;

	/**
	 * ��ȡ����ֵ�����ÿռ�
	 * @return ���ÿռ�
	 */
	public int getUsedSize()
	{
		return usedSize;
	}

	/**
	 * ��������ֵ�����ÿռ�
	 * @param usedSize ���ÿռ�
	 */
	public void setUsedSize(int usedSize)
	{
		this.usedSize = usedSize;
	}

	/**
	 * ʹ����
	 */
	private double usePercent=0;

	/**
	 * ��ȡ����ֵ��ʹ����
	 * @return ʹ����
	 */
	public double getUsePercent()
	{
		return usePercent;
	}

	/**
	 * ��������ֵ��ʹ����
	 * @param usePercent ʹ����
	 */
	public void setUsePercent(double usePercent)
	{
		this.usePercent = usePercent;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ReportResultStoreroomUse clone()
	{
		ReportResultStoreroomUse item = new ReportResultStoreroomUse(iD,reportID,storeroomID,storeroomName,totalSize,usedSize,usePercent);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param reportResultStoreroomUse ָ���Ķ���Դ
	*/
	public void cloneFrom(ReportResultStoreroomUse reportResultStoreroomUse)
	{
		this.iD = reportResultStoreroomUse.getID();
		this.reportID = reportResultStoreroomUse.getReportID();
		this.storeroomID = reportResultStoreroomUse.getStoreroomID();
		this.storeroomName = reportResultStoreroomUse.getStoreroomName();
		this.totalSize = reportResultStoreroomUse.getTotalSize();
		this.usedSize = reportResultStoreroomUse.getUsedSize();
		this.usePercent = reportResultStoreroomUse.getUsePercent();
		this.keyInCol = reportResultStoreroomUse.getKeyInCol();
		this.tag = reportResultStoreroomUse.getTag();
	}

    
}



