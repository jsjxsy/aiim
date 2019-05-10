package com.orifound.aiim.entity;

import java.util.Date;
    
/**
 * ֽ�ʵ����ƽ���ϸ�嵥���ʵ����
 */
public class PaperTransferBatchesDetail
{
    /**
     * ���캯��
     */
    public PaperTransferBatchesDetail()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* 
	* @param transferBatNo �ƽ����κ�
	* @param archivesTypeID ����������
	* @param nBXH �����ڲ����
	* @param parentFlag �����־
	* @param archivesID ����
	* @param title ����
	* @param subContentCount �����ļ�����
	* @param pageSum ����ҳ��
	* @param retentionPeriodID �������ޱ��
	* @param secrecyID �����ܼ����
	* @param formationYear �����γ����
	* @param transferTime ������ƽ��嵥��ʱ��
	* @param receiveCheckResult ������˽��
	*/
	public PaperTransferBatchesDetail(String transferBatNo,int archivesTypeID,int nBXH,boolean parentFlag,String archivesID,String title,int subContentCount,int pageSum,int retentionPeriodID,int secrecyID,int formationYear,Date transferTime,EnumCheckResult receiveCheckResult)
	{
		this.transferBatNo = transferBatNo;
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.parentFlag = parentFlag;
		this.archivesID = archivesID;
		this.title = title;
		this.subContentCount = subContentCount;
		this.pageSum = pageSum;
		this.retentionPeriodID = retentionPeriodID;
		this.secrecyID = secrecyID;
		this.formationYear = formationYear;
		this.transferTime = transferTime;
		this.receiveCheckResult = receiveCheckResult;
	}
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param transferBatNo �ƽ����κ�
	* @param archivesTypeID ����������
	* @param nBXH �����ڲ����
	* @param parentFlag �����־
	* @param archivesID ����
	* @param title ����
	* @param subContentCount �����ļ�����
	* @param pageSum ����ҳ��
	* @param retentionPeriodID �������ޱ��
	* @param secrecyID �����ܼ����
	* @param formationYear �����γ����
	* @param transferTime ������ƽ��嵥��ʱ��
	* @param receiveCheckResult ������˽��
	*/
	public PaperTransferBatchesDetail(int iD,String transferBatNo,int archivesTypeID,int nBXH,boolean parentFlag,String archivesID,String title,int subContentCount,int pageSum,int retentionPeriodID,int secrecyID,int formationYear,Date transferTime,EnumCheckResult receiveCheckResult)
	{                               
		// Columns List,Can Used in SELECT SQL: ID,TransferBatNo,ArchivesTypeID,NBXH,ParentFlag,ArchivesID,Title,SubContentCount,PageSum,RetentionPeriodID,SecrecyID,FormationYear,TransferTime,ReceiveCheckResult
		// Columns List,Can Used in INSERT SQL: pID,pTransferBatNo,pArchivesTypeID,pNBXH,pParentFlag,pArchivesID,pTitle,pSubContentCount,pPageSum,pRetentionPeriodID,pSecrecyID,pFormationYear,pTransferTime,pReceiveCheckResult
		// Columns List,Can Used in UPDATE SQL: ID=pID,TransferBatNo=pTransferBatNo,ArchivesTypeID=pArchivesTypeID,NBXH=pNBXH,ParentFlag=pParentFlag,ArchivesID=pArchivesID,Title=pTitle,SubContentCount=pSubContentCount,PageSum=pPageSum,RetentionPeriodID=pRetentionPeriodID,SecrecyID=pSecrecyID,FormationYear=pFormationYear,TransferTime=pTransferTime,ReceiveCheckResult=pReceiveCheckResult

		this(transferBatNo, archivesTypeID, nBXH, parentFlag, archivesID, title, subContentCount, pageSum, retentionPeriodID, secrecyID, formationYear, transferTime, receiveCheckResult);
		this.iD = iD;
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
	 * ���
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ�����
	 * @return ���
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ�����
	 * @param iD ���
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * �ƽ����κ�
	 */
	private String transferBatNo=null;

	/**
	 * ��ȡ����ֵ���ƽ����κ�
	 * @return �ƽ����κ�
	 */
	public String getTransferBatNo()
	{
		return transferBatNo;
	}

	/**
	 * ��������ֵ���ƽ����κ�
	 * @param transferBatNo �ƽ����κ�
	 */
	public void setTransferBatNo(String transferBatNo)
	{
		this.transferBatNo = transferBatNo;
	}

	/**
	 * ����������
	 */
	private int archivesTypeID=0;

	/**
	 * ��ȡ����ֵ������������
	 * @return ����������
	 */
	public int getArchivesTypeID()
	{
		return archivesTypeID;
	}

	/**
	 * ��������ֵ������������
	 * @param archivesTypeID ����������
	 */
	public void setArchivesTypeID(int archivesTypeID)
	{
		this.archivesTypeID = archivesTypeID;
	}

	/**
	 * �����ڲ����
	 */
	private int nBXH=0;

	/**
	 * ��ȡ����ֵ�������ڲ����
	 * @return �����ڲ����
	 */
	public int getNBXH()
	{
		return nBXH;
	}

	/**
	 * ��������ֵ�������ڲ����
	 * @param nBXH �����ڲ����
	 */
	public void setNBXH(int nBXH)
	{
		this.nBXH = nBXH;
	}

	/**
	 * �����־
	 */
	private boolean parentFlag=false;

	/**
	 * ��ȡ����ֵ�������־
	 * @return �����־
	 */
	public boolean getParentFlag()
	{
		return parentFlag;
	}

	/**
	 * ��������ֵ�������־
	 * @param parentFlag �����־
	 */
	public void setParentFlag(boolean parentFlag)
	{
		this.parentFlag = parentFlag;
	}

	/**
	 * ����
	 */
	private String archivesID=null;

	/**
	 * ��ȡ����ֵ������
	 * @return ����
	 */
	public String getArchivesID()
	{
		return archivesID;
	}

	/**
	 * ��������ֵ������
	 * @param archivesID ����
	 */
	public void setArchivesID(String archivesID)
	{
		this.archivesID = archivesID;
	}

	/**
	 * ����
	 */
	private String title=null;

	/**
	 * ��ȡ����ֵ������
	 * @return ����
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * ��������ֵ������
	 * @param title ����
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * �����ļ�����
	 */
	private int subContentCount=0;

	/**
	 * ��ȡ����ֵ�������ļ�����
	 * @return �����ļ�����
	 */
	public int getSubContentCount()
	{
		return subContentCount;
	}

	/**
	 * ��������ֵ�������ļ�����
	 * @param subContentCount �����ļ�����
	 */
	public void setSubContentCount(int subContentCount)
	{
		this.subContentCount = subContentCount;
	}

	/**
	 * ����ҳ��
	 */
	private int pageSum=0;

	/**
	 * ��ȡ����ֵ������ҳ��
	 * @return ����ҳ��
	 */
	public int getPageSum()
	{
		return pageSum;
	}

	/**
	 * ��������ֵ������ҳ��
	 * @param pageSum ����ҳ��
	 */
	public void setPageSum(int pageSum)
	{
		this.pageSum = pageSum;
	}

	/**
	 * �������ޱ��
	 */
	private int retentionPeriodID=0;

	/**
	 * ��ȡ����ֵ���������ޱ��
	 * @return �������ޱ��
	 */
	public int getRetentionPeriodID()
	{
		return retentionPeriodID;
	}

	/**
	 * ��������ֵ���������ޱ��
	 * @param retentionPeriodID �������ޱ��
	 */
	public void setRetentionPeriodID(int retentionPeriodID)
	{
		this.retentionPeriodID = retentionPeriodID;
	}

	/**
	 * �����ܼ����
	 */
	private int secrecyID=0;

	/**
	 * ��ȡ����ֵ�������ܼ����
	 * @return �����ܼ����
	 */
	public int getSecrecyID()
	{
		return secrecyID;
	}

	/**
	 * ��������ֵ�������ܼ����
	 * @param secrecyID �����ܼ����
	 */
	public void setSecrecyID(int secrecyID)
	{
		this.secrecyID = secrecyID;
	}

	/**
	 * �����γ����
	 */
	private int formationYear=0;

	/**
	 * ��ȡ����ֵ�������γ����
	 * @return �����γ����
	 */
	public int getFormationYear()
	{
		return formationYear;
	}

	/**
	 * ��������ֵ�������γ����
	 * @param formationYear �����γ����
	 */
	public void setFormationYear(int formationYear)
	{
		this.formationYear = formationYear;
	}

	/**
	 * ������ƽ��嵥��ʱ��
	 */
	private Date transferTime;

	/**
	 * ��ȡ����ֵ��������ƽ��嵥��ʱ��
	 * @return ������ƽ��嵥��ʱ��
	 */
	public Date getTransferTime()
	{
		return transferTime;
	}

	/**
	 * ��������ֵ��������ƽ��嵥��ʱ��
	 * @param transferTime ������ƽ��嵥��ʱ��
	 */
	public void setTransferTime(Date transferTime)
	{
		this.transferTime = transferTime;
	}

	/**
	 * ������˽��
	 */
	private EnumCheckResult receiveCheckResult=EnumCheckResult.��δ���;

	/**
	 * ��ȡ����ֵ��������˽��
	 * @return ������˽��
	 */
	public EnumCheckResult getReceiveCheckResult()
	{
		return receiveCheckResult;
	}

	/**
	 * ��������ֵ��������˽��
	 * @param receiveCheckResult ������˽��
	 */
	public void setReceiveCheckResult(EnumCheckResult receiveCheckResult)
	{
		this.receiveCheckResult = receiveCheckResult;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public PaperTransferBatchesDetail clone()
	{
		PaperTransferBatchesDetail item = new PaperTransferBatchesDetail(iD,transferBatNo,archivesTypeID,nBXH,parentFlag,archivesID,title,subContentCount,pageSum,retentionPeriodID,secrecyID,formationYear,transferTime,receiveCheckResult);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}


	/**
	 * �ܼ��ı�
	 */
	private String secrecyText;
	
	/**
	 * ���������ı�
	 */
	private String retentionPeriodText;

	public String getSecrecyText() {
		if(secrecyText == null || secrecyText == ""){
			if(this.secrecyID == 0){
				this.secrecyText = "";
			}else{
				this.secrecyText = SystemInitializer.getInstance().getArchivesSecrecys().get(this.secrecyID).getName();
			}
		}
		return secrecyText;
	}

	public void setSecrecyText(String secrecyText) {
		this.secrecyText = secrecyText;
	}

	public String getRetentionPeriodText() {
		if(retentionPeriodText == null || retentionPeriodText == ""){
			if(this.retentionPeriodID == 0){
				this.retentionPeriodText = "";
			}else{
				this.retentionPeriodText = SystemInitializer.getInstance().getRetentionPeriods().get(this.retentionPeriodID).getName();
			}	
		}
		return retentionPeriodText;
	}

	public void setRetentionPeriodText(String retentionPeriodText) {
		this.retentionPeriodText = retentionPeriodText;
	} 
	
	/**
	* ��ָ�������¡��������������ֵ
	* @param paperTransferBatchesDetail ָ���Ķ���Դ
	*/
	public void cloneFrom(PaperTransferBatchesDetail paperTransferBatchesDetail)
	{
		this.iD = paperTransferBatchesDetail.getID();
		this.transferBatNo = paperTransferBatchesDetail.getTransferBatNo();
		this.archivesTypeID = paperTransferBatchesDetail.getArchivesTypeID();
		this.nBXH = paperTransferBatchesDetail.getNBXH();
		this.parentFlag = paperTransferBatchesDetail.getParentFlag();
		this.archivesID = paperTransferBatchesDetail.getArchivesID();
		this.title = paperTransferBatchesDetail.getTitle();
		this.subContentCount = paperTransferBatchesDetail.getSubContentCount();
		this.pageSum = paperTransferBatchesDetail.getPageSum();
		this.retentionPeriodID = paperTransferBatchesDetail.getRetentionPeriodID();
		this.secrecyID = paperTransferBatchesDetail.getSecrecyID();
		this.formationYear = paperTransferBatchesDetail.getFormationYear();
		this.transferTime = paperTransferBatchesDetail.getTransferTime();
		this.receiveCheckResult = paperTransferBatchesDetail.getReceiveCheckResult();
		this.keyInCol = paperTransferBatchesDetail.getKeyInCol();
		this.tag = paperTransferBatchesDetail.getTag();
	}
}



