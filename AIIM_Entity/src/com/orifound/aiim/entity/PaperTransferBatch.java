package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * ֽ�ʵ����ƽ�������Ϣ���ʵ����
 */
public class PaperTransferBatch
{
    /**
     * ���캯��
     */
    public PaperTransferBatch()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param batNo �ƽ����κ�
	* @param batNoCreateUserID �������κŵ��û�
	* @param transferDepartmentID �ͳ����ű��
	* @param batNoStatus �ƽ�����״̬
	* @param insideFlag �����ƽ���־
	* @param transferTime �ͳ�ʱ��
	* @param transferTotal �ͳ�����������
	* @param transferUserID �ͳ��û����
	* @param receiveDepartmentID ���ղ��ű��
	* @param receiveUserID ������Ա���
	* @param receiveTime ����ʱ��
	* @param receiveCheckedFlag ���������ɱ�־
	*/
	public PaperTransferBatch(String batNo,int batNoCreateUserID,int transferDepartmentID,int batNoStatus,boolean insideFlag,Date transferTime,int transferTotal,int transferUserID,int receiveDepartmentID,int receiveUserID,Date receiveTime,boolean receiveCheckedFlag,String batNoCreateUserName,String transferDepartmentName,String receiveUserName,String receiveDepartmentName)
	{
		// Table Name: PaperTransferBatches
		// Columns List,Can Used in SELECT SQL: BatNo,BatNoCreateUserID,TransferDepartmentID,ConfirmFlag,InsideFlag,TransferTime,TransferTotal,TransferUserID,ReceiveDepartmentID,ReceiveUserID,ReceiveTime,ReceiveCheckedFlag
		// Columns List,Can Used in INSERT SQL: :BatNo,:BatNoCreateUserID,:TransferDepartmentID,:ConfirmFlag,:InsideFlag,:TransferTime,:TransferTotal,:TransferUserID,:ReceiveDepartmentID,:ReceiveUserID,:ReceiveTime,:ReceiveCheckedFlag
		// Columns List,Can Used in UPDATE SQL: BatNo=:BatNo,BatNoCreateUserID=:BatNoCreateUserID,TransferDepartmentID=:TransferDepartmentID,ConfirmFlag=:ConfirmFlag,InsideFlag=:InsideFlag,TransferTime=:TransferTime,TransferTotal=:TransferTotal,TransferUserID=:TransferUserID,ReceiveDepartmentID=:ReceiveDepartmentID,ReceiveUserID=:ReceiveUserID,ReceiveTime=:ReceiveTime,ReceiveCheckedFlag=:ReceiveCheckedFlag

		this.batNo = batNo;
		this.batNoCreateUserID = batNoCreateUserID;
		this.transferDepartmentID = transferDepartmentID;
		this.batNoStatus = batNoStatus;
		this.insideFlag = insideFlag;
		this.transferTime = transferTime;
		this.transferTotal = transferTotal;
		this.transferUserID = transferUserID;
		this.receiveDepartmentID = receiveDepartmentID;
		this.receiveUserID = receiveUserID;
		this.receiveTime = receiveTime;
		this.receiveCheckedFlag = receiveCheckedFlag;
		this.batNoCreateUserName = batNoCreateUserName;
		this.transferDepartmentName = transferDepartmentName;
		this.receiveUserName = receiveUserName;
		this.receiveDepartmentName = receiveDepartmentName;
	}

	public PaperTransferBatch(String batNo,int batNoCreateUserID,int transferDepartmentID,int batNoStatus,boolean insideFlag,Date transferTime,int transferTotal,int transferUserID,int receiveDepartmentID,int receiveUserID,Date receiveTime,boolean receiveCheckedFlag)
	{
		// Table Name: PaperTransferBatches
		// Columns List,Can Used in SELECT SQL: BatNo,BatNoCreateUserID,TransferDepartmentID,ConfirmFlag,InsideFlag,TransferTime,TransferTotal,TransferUserID,ReceiveDepartmentID,ReceiveUserID,ReceiveTime,ReceiveCheckedFlag
		// Columns List,Can Used in INSERT SQL: :BatNo,:BatNoCreateUserID,:TransferDepartmentID,:ConfirmFlag,:InsideFlag,:TransferTime,:TransferTotal,:TransferUserID,:ReceiveDepartmentID,:ReceiveUserID,:ReceiveTime,:ReceiveCheckedFlag
		// Columns List,Can Used in UPDATE SQL: BatNo=:BatNo,BatNoCreateUserID=:BatNoCreateUserID,TransferDepartmentID=:TransferDepartmentID,ConfirmFlag=:ConfirmFlag,InsideFlag=:InsideFlag,TransferTime=:TransferTime,TransferTotal=:TransferTotal,TransferUserID=:TransferUserID,ReceiveDepartmentID=:ReceiveDepartmentID,ReceiveUserID=:ReceiveUserID,ReceiveTime=:ReceiveTime,ReceiveCheckedFlag=:ReceiveCheckedFlag

		this.batNo = batNo;
		this.batNoCreateUserID = batNoCreateUserID;
		this.transferDepartmentID = transferDepartmentID;
		this.batNoStatus = batNoStatus;
		this.insideFlag = insideFlag;
		this.transferTime = transferTime;
		this.transferTotal = transferTotal;
		this.transferUserID = transferUserID;
		this.receiveDepartmentID = receiveDepartmentID;
		this.receiveUserID = receiveUserID;
		this.receiveTime = receiveTime;
		this.receiveCheckedFlag = receiveCheckedFlag;
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
	 * �ƽ����κ�
	 */
	private String batNo=null;

	/**
	 * ��ȡ����ֵ���ƽ����κ�
	 * @return �ƽ����κ�
	 */
	public String getBatNo()
	{
		return batNo;
	}

	/**
	 * ��������ֵ���ƽ����κ�
	 * @param batNo �ƽ����κ�
	 */
	public void setBatNo(String batNo)
	{
		this.batNo = batNo;
	}

	/**
	 * �������κŵ��û�
	 */
	private int batNoCreateUserID=0;

	/**
	 * ��ȡ����ֵ���������κŵ��û�
	 * @return �������κŵ��û�
	 */
	public int getBatNoCreateUserID()
	{
		return batNoCreateUserID;
	}

	/**
	 * ��������ֵ���������κŵ��û�
	 * @param batNoCreateUserID �������κŵ��û�
	 */
	public void setBatNoCreateUserID(int batNoCreateUserID)
	{
		this.batNoCreateUserID = batNoCreateUserID;
	}

	/**
	 * �ͳ����ű��
	 */
	private int transferDepartmentID=0;

	/**
	 * ��ȡ����ֵ���ͳ����ű��
	 * @return �ͳ����ű��
	 */
	public int getTransferDepartmentID()
	{
		return transferDepartmentID;
	}

	/**
	 * ��������ֵ���ͳ����ű��
	 * @param transferDepartmentID �ͳ����ű��
	 */
	public void setTransferDepartmentID(int transferDepartmentID)
	{
		this.transferDepartmentID = transferDepartmentID;
	}

	/**
	 * �ƽ����εĹ���״̬
	 */
	private int batNoStatus=0;

	/**
	 * ��ȡ����ֵ���ƽ����εĹ���״̬
	 * @return �ƽ����εĹ���״̬
	 */
	public int getBatNoStatus()
	{
		return batNoStatus;
	}

	/**
	 * ��������ֵ���ƽ����εĹ���״̬
	 * @param batNoStatus �ƽ����εĹ���״̬
	 */
	public void setBatNoStatus(int batNoStatus)
	{
		this.batNoStatus = batNoStatus;
	}

	/**
	 * �����ƽ���־
	 */
	private boolean insideFlag=false;

	/**
	 * ��ȡ����ֵ�������ƽ���־
	 * @return �����ƽ���־
	 */
	public boolean getInsideFlag()
	{
		return insideFlag;
	}

	/**
	 * ��������ֵ�������ƽ���־
	 * @param insideFlag �����ƽ���־
	 */
	public void setInsideFlag(boolean insideFlag)
	{
		this.insideFlag = insideFlag;
	}

	/**
	 * �ͳ�ʱ��
	 */
	private Date transferTime;

	/**
	 * ��ȡ����ֵ���ͳ�ʱ��
	 * @return �ͳ�ʱ��
	 */
	public Date getTransferTime()
	{
		return transferTime;
	}

	/**
	 * ��������ֵ���ͳ�ʱ��
	 * @param transferTime �ͳ�ʱ��
	 */
	public void setTransferTime(Date transferTime)
	{
		this.transferTime = transferTime;
	}

	/**
	 * �ͳ�����������
	 */
	private int transferTotal=0;

	/**
	 * ��ȡ����ֵ���ͳ�����������
	 * @return �ͳ�����������
	 */
	public int getTransferTotal()
	{
		return transferTotal;
	}

	/**
	 * ��������ֵ���ͳ�����������
	 * @param transferTotal �ͳ�����������
	 */
	public void setTransferTotal(int transferTotal)
	{
		this.transferTotal = transferTotal;
	}

	/**
	 * �ͳ��û����
	 */
	private int transferUserID=0;

	/**
	 * ��ȡ����ֵ���ͳ��û����
	 * @return �ͳ��û����
	 */
	public int getTransferUserID()
	{
		return transferUserID;
	}

	/**
	 * ��������ֵ���ͳ��û����
	 * @param transferUserID �ͳ��û����
	 */
	public void setTransferUserID(int transferUserID)
	{
		this.transferUserID = transferUserID;
	}

	/**
	 * ���ղ��ű��
	 */
	private int receiveDepartmentID=0;

	/**
	 * ��ȡ����ֵ�����ղ��ű��
	 * @return ���ղ��ű��
	 */
	public int getReceiveDepartmentID()
	{
		return receiveDepartmentID;
	}

	/**
	 * ��������ֵ�����ղ��ű��
	 * @param receiveDepartmentID ���ղ��ű��
	 */
	public void setReceiveDepartmentID(int receiveDepartmentID)
	{
		this.receiveDepartmentID = receiveDepartmentID;
	}

	/**
	 * ������Ա���
	 */
	private int receiveUserID=0;

	/**
	 * ��ȡ����ֵ��������Ա���
	 * @return ������Ա���
	 */
	public int getReceiveUserID()
	{
		return receiveUserID;
	}

	/**
	 * ��������ֵ��������Ա���
	 * @param receiveUserID ������Ա���
	 */
	public void setReceiveUserID(int receiveUserID)
	{
		this.receiveUserID = receiveUserID;
	}

	/**
	 * ����ʱ��
	 */
	private Date receiveTime;

	/**
	 * ��ȡ����ֵ������ʱ��
	 * @return ����ʱ��
	 */
	public Date getReceiveTime()
	{
		return receiveTime;
	}

	/**
	 * ��������ֵ������ʱ��
	 * @param receiveTime ����ʱ��
	 */
	public void setReceiveTime(Date receiveTime)
	{
		this.receiveTime = receiveTime;
	}

	/**
	 * ���������ɱ�־
	 */
	private boolean receiveCheckedFlag=false;

	/**
	 * ��ȡ����ֵ�����������ɱ�־
	 * @return ���������ɱ�־
	 */
	public boolean getReceiveCheckedFlag()
	{
		return receiveCheckedFlag;
	}

	/**
	 * ��������ֵ�����������ɱ�־
	 * @param receiveCheckedFlag ���������ɱ�־
	 */
	public void setReceiveCheckedFlag(boolean receiveCheckedFlag)
	{
		this.receiveCheckedFlag = receiveCheckedFlag;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public PaperTransferBatch clone()
	{
		PaperTransferBatch item = new PaperTransferBatch(batNo,batNoCreateUserID,transferDepartmentID,batNoStatus,insideFlag,transferTime,transferTotal,transferUserID,receiveDepartmentID,receiveUserID,receiveTime,receiveCheckedFlag,batNoCreateUserName,transferDepartmentName,receiveUserName,receiveDepartmentName);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}
	

	/**
	 * ���ƽ����εĴ���������
	 */
	private String batNoCreateUserName = "";

	/**
	 * ��������ֵ�����ƽ����εĴ���������
	 * @param batNoCreateUserName ���ƽ����εĴ���������
	 */
	public void setBatNoCreateUserName(String batNoCreateUserName) {
		this.batNoCreateUserName = batNoCreateUserName;
	}

	/**
	 * ��ȡ����ֵ�����ƽ����εĴ���������
	 * @return ���ƽ����εĴ���������
	 */
	public String getBatNoCreateUserName() {
		return batNoCreateUserName;
	}

	/**
	 * ���ƽ����εĽ���������
	 */
	private String receiveUserName = "";

	/**
	 * ��������ֵ�����ƽ����εĽ���������
	 * @param receiveUserName ���ƽ����εĽ���������
	 */
	public void setReceiveUserName(String receiveUserName) {
		this.receiveUserName = receiveUserName;
	}

	/**
	 * ��ȡ����ֵ�����ƽ����εĽ���������
	 * @return ���ƽ����εĽ���������
	 */
	public String getReceiveUserName() {
		return receiveUserName;
	}

	/**
	 * �ͳ���������
	 */
	private String transferDepartmentName=null;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public String getTransferDepartmentName()
	{
		return transferDepartmentName;
	}

	/**
	 * ��������ֵ����������
	 * @param name ��������
	 */
	public void setTransferDepartmentName(String transferDepartmentName)
	{
		this.transferDepartmentName = transferDepartmentName;
	}

	/**
	 * ���ղ�������
	 */
	private String receiveDepartmentName=null;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public String getReceiveDepartmentName()
	{
		return receiveDepartmentName;
	}

	/**
	 * ��������ֵ����������
	 * @param name ��������
	 */
	public void setReceiveDepartmentName(String receiveDepartmentName)
	{
		this.receiveDepartmentName = receiveDepartmentName;
	}
	
	/**
	 * ֽ�ʵ����ƽ����ε���������ϸ���
	 */
	private Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = null;

	/**
	 * ��������ֵ��ֽ�ʵ����ƽ����ε���������ϸ���
	 * @param paperTransferBatchesArchvTypeDetails ֽ�ʵ����ƽ����ε���������ϸ���
	 */
	public void setPaperTransferBatchesArchvTypeDetails(Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails) {
		this.paperTransferBatchesArchvTypeDetails = paperTransferBatchesArchvTypeDetails;
	}

	/**
	 * ��ȡ����ֵ��ֽ�ʵ����ƽ����ε���������ϸ���
	 * @return ֽ�ʵ����ƽ����ε���������ϸ���
	 */
	public Map<Integer, PaperTransferBatchesArchvTypeDetail> getPaperTransferBatchesArchvTypeDetails() {
		return paperTransferBatchesArchvTypeDetails;
	}
    
	/**
	* ��ָ�������¡��������������ֵ
	* @param paperTansferBatch ָ���Ķ���Դ
	*/
	public void cloneFrom(PaperTransferBatch paperTransferBatch)
	{
		this.batNo = paperTransferBatch.getBatNo();
		this.batNoCreateUserID = paperTransferBatch.getBatNoCreateUserID();
		this.batNoCreateUserName = paperTransferBatch.getBatNoCreateUserName();
		this.transferDepartmentID = paperTransferBatch.getTransferDepartmentID();
		this.transferDepartmentName = paperTransferBatch.getTransferDepartmentName();
		this.batNoStatus = paperTransferBatch.getBatNoStatus();
		this.insideFlag = paperTransferBatch.getInsideFlag();
		this.transferTime = paperTransferBatch.getTransferTime();
		this.transferTotal = paperTransferBatch.getTransferTotal();
		this.transferUserID = paperTransferBatch.getTransferUserID();
		this.receiveDepartmentID = paperTransferBatch.getReceiveDepartmentID();
		this.receiveDepartmentName = paperTransferBatch.getReceiveDepartmentName();
		this.receiveUserID = paperTransferBatch.getReceiveUserID();
		this.receiveUserName = paperTransferBatch.getReceiveUserName();
		this.receiveTime = paperTransferBatch.getReceiveTime();
		this.receiveCheckedFlag = paperTransferBatch.getReceiveCheckedFlag();
		this.keyInCol = paperTransferBatch.getKeyInCol();
		this.tag = paperTransferBatch.getTag();
	}

	@Override
	public boolean equals(Object obj) {
		return this.batNo == ((PaperTransferBatch)obj).getBatNo();
	}
}




	




