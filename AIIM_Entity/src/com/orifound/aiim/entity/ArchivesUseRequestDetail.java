package com.orifound.aiim.entity;

import java.util.Date;
    
/**
 * �����������뵥��ϸ���
 */
public class ArchivesUseRequestDetail
{
    /**
     * ���캯��
     */
    public ArchivesUseRequestDetail()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���뵥��ϸ���
	* @param requestID ���뵥���
	* @param archivesTypeID ����������
	* @param archivesTypeText ������������
	* @param nBXH �����ڲ����
	* @param secrecyID �ܼ����
	* @param secrecyText �ܼ��ı�
	* @param archivesID ����
	* @param title ����
	* @param useWayID �������÷�ʽ���
	* @param useWayText ���÷�ʽ
	* @param checkResult �������
	* @param backReason ���벵��ԭ��
	* @param checkTime ����ʱ��
	* @param checkUserID �����˱��
	* @param remark ��ע
	*/
    public ArchivesUseRequestDetail(int iD,int requestID,int archivesTypeID,String archivesTypeText,int nBXH,int secrecyID,String secrecyText,String archivesID,String title,int useWayID,String useWayText,int checkResult,String backReason,Date checkTime,int checkUserID,String remark)
	{
		// Table Name: ArchivesUseRequestDetails
		// Columns List,Can Used in SELECT SQL: ID,RequestID,ArchivesTypeID,NBXH,ArchivesID,Title,UseWayID,CheckResult,BackReason,CheckTime,CheckUserID,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:RequestID,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:UseWayID,:CheckResult,:BackReason,:CheckTime,:CheckUserID,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,RequestID=:RequestID,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,UseWayID=:UseWayID,CheckResult=:CheckResult,BackReason=:BackReason,CheckTime=:CheckTime,CheckUserID=:CheckUserID,Remark=:Remark

		this.iD = iD;
		this.requestID = requestID;
		this.archivesTypeID = archivesTypeID;
		this.archivesTypeText = archivesTypeText;
		this.nBXH = nBXH;
		this.secrecyID = secrecyID;
		this.secrecyText = secrecyText;
		this.archivesID = archivesID;
		this.title = title;
		this.useWayID = useWayID;
		this.useWayText = useWayText;
		this.checkResult = checkResult;
		this.backReason = backReason;
		this.checkTime = checkTime;
		this.checkUserID = checkUserID;
		this.remark = remark;
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
	 * ���뵥��ϸ���
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ�����뵥��ϸ���
	 * @return ���뵥��ϸ���
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ�����뵥��ϸ���
	 * @param iD ���뵥��ϸ���
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ���뵥���
	 */
	private int requestID=0;

	/**
	 * ��ȡ����ֵ�����뵥���
	 * @return ���뵥���
	 */
	public int getRequestID()
	{
		return requestID;
	}

	/**
	 * ��������ֵ�����뵥���
	 * @param requestID ���뵥���
	 */
	public void setRequestID(int requestID)
	{
		this.requestID = requestID;
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
	 * ������������
	 */
	private String archivesTypeText = null;
	
	/**
	 * ��ȡ���õ�����������
	 * @return
	 */
	public String getArchivesTypeText() {
		return archivesTypeText;
	}

	/**
	 * ���õ�����������
	 * @param archivesTypeText
	 */
	public void setArchivesTypeText(String archivesTypeText) {
		this.archivesTypeText = archivesTypeText;
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
	 * �����ܼ����
	 */
	private int secrecyID = 0;
	
	/**
	 * ��ȡ�����ܼ����
	 * @return
	 */
	public int getSecrecyID() {
		return secrecyID;
	}

	/**
	 * ���õ����ܼ����
	 * @return
	 */
	public void setSecrecyID(int secrecyID) {
		this.secrecyID = secrecyID;
	}
	
	/**
	 * �����ܼ��ı�
	 */
	private String secrecyText =null;
	

	/**
	 * ��ȡ�����ܼ��ı�
	 * @return
	 */
	public String getSecrecyText() {
		return secrecyText;
	}

	/**
	 * ���õ����ܼ��ı�
	 * @param secrecyText
	 */
	public void setSecrecyText(String secrecyText) {
		this.secrecyText = secrecyText;
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
	 * �������÷�ʽ���
	 */
	private int useWayID=0;

	/**
	 * ��ȡ����ֵ���������÷�ʽ���
	 * @return �������÷�ʽ���
	 */
	public int getUseWayID()
	{
		return useWayID;
	}

	/**
	 * ��������ֵ���������÷�ʽ���
	 * @param useWayID �������÷�ʽ���
	 */
	public void setUseWayID(int useWayID)
	{
		this.useWayID = useWayID;
	}
	
	/**
	 * ���÷�ʽ��ʾ�ı�
	 * @return
	 */
	private String useWayText = null;
	
	/**
	 * ��ȡ���÷�ʽ��ʾ�ı�
	 */
	public String getUseWayText() {
		return useWayText;
	}

	/**
	 * �������÷�ʽ��ʾ�ı�
	 * @param useWayText
	 */
	public void setUseWayText(String useWayText) {
		this.useWayText = useWayText;
	}

	

	/**
	 * �������
	 */
	private int checkResult=0;

	/**
	 * ��ȡ����ֵ���������
	 * @return �������
	 */
	public int getCheckResult()
	{
		return checkResult;
	}

	/**
	 * ��������ֵ���������
	 * @param checkResult �������
	 */
	public void setCheckResult(int checkResult)
	{
		this.checkResult = checkResult;
	}

	/**
	 * ���벵��ԭ��
	 */
	private String backReason=null;

	/**
	 * ��ȡ����ֵ�����벵��ԭ��
	 * @return ���벵��ԭ��
	 */
	public String getBackReason()
	{
		return backReason;
	}

	/**
	 * ��������ֵ�����벵��ԭ��
	 * @param backReason ���벵��ԭ��
	 */
	public void setBackReason(String backReason)
	{
		this.backReason = backReason;
	}

	/**
	 * ����ʱ��
	 */
	private Date checkTime;

	/**
	 * ��ȡ����ֵ������ʱ��
	 * @return ����ʱ��
	 */
	public Date getCheckTime()
	{
		return checkTime;
	}

	/**
	 * ��������ֵ������ʱ��
	 * @param checkTime ����ʱ��
	 */
	public void setCheckTime(Date checkTime)
	{
		this.checkTime = checkTime;
	}

	/**
	 * �����˱��
	 */
	private int checkUserID=0;

	/**
	 * ��ȡ����ֵ�������˱��
	 * @return �����˱��
	 */
	public int getCheckUserID()
	{
		return checkUserID;
	}

	/**
	 * ��������ֵ�������˱��
	 * @param checkUserID �����˱��
	 */
	public void setCheckUserID(int checkUserID)
	{
		this.checkUserID = checkUserID;
	}

	/**
	 * ��ע
	 */
	private String remark=null;

	/**
	 * ��ȡ����ֵ����ע
	 * @return ��ע
	 */
	public String getRemark()
	{
		return remark;
	}

	/**
	 * ��������ֵ����ע
	 * @param remark ��ע
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	
	
	/**
	 * �����������뵥��Ϣ
	 */
	private ArchivesUseRequest archivesUseRequest;
	
	/**
	 * ��ȡ�����������뵥��Ϣ
	 * @return
	 */
	public ArchivesUseRequest getArchivesUseRequest() {
		return archivesUseRequest;
	}

	/**
	 * ���õ����������뵥��Ϣ
	 * @param archivesUseRequest
	 */
	public void setArchivesUseRequest(ArchivesUseRequest archivesUseRequest) {
		this.archivesUseRequest = archivesUseRequest;
	}
	


	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ArchivesUseRequestDetail clone()
	{
		ArchivesUseRequestDetail item =new ArchivesUseRequestDetail(iD, requestID, archivesTypeID, archivesTypeText, nBXH, secrecyID, secrecyText, archivesID, title, useWayID, useWayText, checkResult, backReason, checkTime, checkUserID, remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);		
		item.setArchivesUseRequest(archivesUseRequest);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param archivesUseRequestDetail ָ���Ķ���Դ
	*/
	public void cloneFrom(ArchivesUseRequestDetail archivesUseRequestDetail)
	{
		this.iD = archivesUseRequestDetail.getID();
		this.requestID = archivesUseRequestDetail.getRequestID();
		this.archivesTypeID = archivesUseRequestDetail.getArchivesTypeID();
		this.archivesTypeText = archivesUseRequestDetail.getArchivesTypeText();
		this.nBXH = archivesUseRequestDetail.getNBXH();
		this.archivesID = archivesUseRequestDetail.getArchivesID();
		this.title = archivesUseRequestDetail.getTitle();
		this.useWayID = archivesUseRequestDetail.getUseWayID();
		this.useWayText = archivesUseRequestDetail.getUseWayText();
		this.checkResult = archivesUseRequestDetail.getCheckResult();
		this.backReason = archivesUseRequestDetail.getBackReason();
		this.checkTime = archivesUseRequestDetail.getCheckTime();
		this.checkUserID = archivesUseRequestDetail.getCheckUserID();
		this.remark = archivesUseRequestDetail.getRemark();
		this.keyInCol = archivesUseRequestDetail.getKeyInCol();
		this.tag = archivesUseRequestDetail.getTag();
		this.archivesUseRequest = archivesUseRequestDetail.getArchivesUseRequest();
	}


    
}



