package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 纸质档案移交批次信息表的实体类
 */
public class PaperTransferBatch
{
    /**
     * 构造函数
     */
    public PaperTransferBatch()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param batNo 移交批次号
	* @param batNoCreateUserID 创建批次号的用户
	* @param transferDepartmentID 送出部门编号
	* @param batNoStatus 移交批次状态
	* @param insideFlag 馆内移交标志
	* @param transferTime 送出时间
	* @param transferTotal 送出档案总数量
	* @param transferUserID 送出用户编号
	* @param receiveDepartmentID 接收部门编号
	* @param receiveUserID 接收人员编号
	* @param receiveTime 接收时间
	* @param receiveCheckedFlag 接收审核完成标志
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
	 * 成员对象在集合中的关键字
	 */
	private Object keyInCol=null;

	/**
	 * 获取属性值：成员对象在集合中的关键字
	 * @return 成员对象在集合中的关键字
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}

	/**
	 * 设置属性值：成员对象在集合中的关键字
	 * @param keyInCol 成员对象在集合中的关键字
	 */
	public void setKeyInCol(Object keyInCol)
	{
		this.keyInCol = keyInCol;
	}

	/**
	 * 该数据项的附加对象，可以用来保存一些附加信息
	 */
	private Object tag=null;

	/**
	 * 获取属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * @return 该数据项的附加对象，可以用来保存一些附加信息
	 */
	public Object getTag()
	{
		return tag;
	}

	/**
	 * 设置属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * @param tag 该数据项的附加对象，可以用来保存一些附加信息
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	/**
	 * 移交批次号
	 */
	private String batNo=null;

	/**
	 * 获取属性值：移交批次号
	 * @return 移交批次号
	 */
	public String getBatNo()
	{
		return batNo;
	}

	/**
	 * 设置属性值：移交批次号
	 * @param batNo 移交批次号
	 */
	public void setBatNo(String batNo)
	{
		this.batNo = batNo;
	}

	/**
	 * 创建批次号的用户
	 */
	private int batNoCreateUserID=0;

	/**
	 * 获取属性值：创建批次号的用户
	 * @return 创建批次号的用户
	 */
	public int getBatNoCreateUserID()
	{
		return batNoCreateUserID;
	}

	/**
	 * 设置属性值：创建批次号的用户
	 * @param batNoCreateUserID 创建批次号的用户
	 */
	public void setBatNoCreateUserID(int batNoCreateUserID)
	{
		this.batNoCreateUserID = batNoCreateUserID;
	}

	/**
	 * 送出部门编号
	 */
	private int transferDepartmentID=0;

	/**
	 * 获取属性值：送出部门编号
	 * @return 送出部门编号
	 */
	public int getTransferDepartmentID()
	{
		return transferDepartmentID;
	}

	/**
	 * 设置属性值：送出部门编号
	 * @param transferDepartmentID 送出部门编号
	 */
	public void setTransferDepartmentID(int transferDepartmentID)
	{
		this.transferDepartmentID = transferDepartmentID;
	}

	/**
	 * 移交批次的工作状态
	 */
	private int batNoStatus=0;

	/**
	 * 获取属性值：移交批次的工作状态
	 * @return 移交批次的工作状态
	 */
	public int getBatNoStatus()
	{
		return batNoStatus;
	}

	/**
	 * 设置属性值：移交批次的工作状态
	 * @param batNoStatus 移交批次的工作状态
	 */
	public void setBatNoStatus(int batNoStatus)
	{
		this.batNoStatus = batNoStatus;
	}

	/**
	 * 馆内移交标志
	 */
	private boolean insideFlag=false;

	/**
	 * 获取属性值：馆内移交标志
	 * @return 馆内移交标志
	 */
	public boolean getInsideFlag()
	{
		return insideFlag;
	}

	/**
	 * 设置属性值：馆内移交标志
	 * @param insideFlag 馆内移交标志
	 */
	public void setInsideFlag(boolean insideFlag)
	{
		this.insideFlag = insideFlag;
	}

	/**
	 * 送出时间
	 */
	private Date transferTime;

	/**
	 * 获取属性值：送出时间
	 * @return 送出时间
	 */
	public Date getTransferTime()
	{
		return transferTime;
	}

	/**
	 * 设置属性值：送出时间
	 * @param transferTime 送出时间
	 */
	public void setTransferTime(Date transferTime)
	{
		this.transferTime = transferTime;
	}

	/**
	 * 送出档案总数量
	 */
	private int transferTotal=0;

	/**
	 * 获取属性值：送出档案总数量
	 * @return 送出档案总数量
	 */
	public int getTransferTotal()
	{
		return transferTotal;
	}

	/**
	 * 设置属性值：送出档案总数量
	 * @param transferTotal 送出档案总数量
	 */
	public void setTransferTotal(int transferTotal)
	{
		this.transferTotal = transferTotal;
	}

	/**
	 * 送出用户编号
	 */
	private int transferUserID=0;

	/**
	 * 获取属性值：送出用户编号
	 * @return 送出用户编号
	 */
	public int getTransferUserID()
	{
		return transferUserID;
	}

	/**
	 * 设置属性值：送出用户编号
	 * @param transferUserID 送出用户编号
	 */
	public void setTransferUserID(int transferUserID)
	{
		this.transferUserID = transferUserID;
	}

	/**
	 * 接收部门编号
	 */
	private int receiveDepartmentID=0;

	/**
	 * 获取属性值：接收部门编号
	 * @return 接收部门编号
	 */
	public int getReceiveDepartmentID()
	{
		return receiveDepartmentID;
	}

	/**
	 * 设置属性值：接收部门编号
	 * @param receiveDepartmentID 接收部门编号
	 */
	public void setReceiveDepartmentID(int receiveDepartmentID)
	{
		this.receiveDepartmentID = receiveDepartmentID;
	}

	/**
	 * 接收人员编号
	 */
	private int receiveUserID=0;

	/**
	 * 获取属性值：接收人员编号
	 * @return 接收人员编号
	 */
	public int getReceiveUserID()
	{
		return receiveUserID;
	}

	/**
	 * 设置属性值：接收人员编号
	 * @param receiveUserID 接收人员编号
	 */
	public void setReceiveUserID(int receiveUserID)
	{
		this.receiveUserID = receiveUserID;
	}

	/**
	 * 接收时间
	 */
	private Date receiveTime;

	/**
	 * 获取属性值：接收时间
	 * @return 接收时间
	 */
	public Date getReceiveTime()
	{
		return receiveTime;
	}

	/**
	 * 设置属性值：接收时间
	 * @param receiveTime 接收时间
	 */
	public void setReceiveTime(Date receiveTime)
	{
		this.receiveTime = receiveTime;
	}

	/**
	 * 接收审核完成标志
	 */
	private boolean receiveCheckedFlag=false;

	/**
	 * 获取属性值：接收审核完成标志
	 * @return 接收审核完成标志
	 */
	public boolean getReceiveCheckedFlag()
	{
		return receiveCheckedFlag;
	}

	/**
	 * 设置属性值：接收审核完成标志
	 * @param receiveCheckedFlag 接收审核完成标志
	 */
	public void setReceiveCheckedFlag(boolean receiveCheckedFlag)
	{
		this.receiveCheckedFlag = receiveCheckedFlag;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public PaperTransferBatch clone()
	{
		PaperTransferBatch item = new PaperTransferBatch(batNo,batNoCreateUserID,transferDepartmentID,batNoStatus,insideFlag,transferTime,transferTotal,transferUserID,receiveDepartmentID,receiveUserID,receiveTime,receiveCheckedFlag,batNoCreateUserName,transferDepartmentName,receiveUserName,receiveDepartmentName);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}
	

	/**
	 * 该移交批次的创建者姓名
	 */
	private String batNoCreateUserName = "";

	/**
	 * 设置属性值：该移交批次的创建者姓名
	 * @param batNoCreateUserName 该移交批次的创建者姓名
	 */
	public void setBatNoCreateUserName(String batNoCreateUserName) {
		this.batNoCreateUserName = batNoCreateUserName;
	}

	/**
	 * 获取属性值：该移交批次的创建者姓名
	 * @return 该移交批次的创建者姓名
	 */
	public String getBatNoCreateUserName() {
		return batNoCreateUserName;
	}

	/**
	 * 该移交批次的接收人姓名
	 */
	private String receiveUserName = "";

	/**
	 * 设置属性值：该移交批次的接收人姓名
	 * @param receiveUserName 该移交批次的接收人姓名
	 */
	public void setReceiveUserName(String receiveUserName) {
		this.receiveUserName = receiveUserName;
	}

	/**
	 * 获取属性值：该移交批次的接收人姓名
	 * @return 该移交批次的接收人姓名
	 */
	public String getReceiveUserName() {
		return receiveUserName;
	}

	/**
	 * 送出部门名称
	 */
	private String transferDepartmentName=null;

	/**
	 * 获取属性值：部门名称
	 * @return 部门名称
	 */
	public String getTransferDepartmentName()
	{
		return transferDepartmentName;
	}

	/**
	 * 设置属性值：部门名称
	 * @param name 部门名称
	 */
	public void setTransferDepartmentName(String transferDepartmentName)
	{
		this.transferDepartmentName = transferDepartmentName;
	}

	/**
	 * 接收部门名称
	 */
	private String receiveDepartmentName=null;

	/**
	 * 获取属性值：部门名称
	 * @return 部门名称
	 */
	public String getReceiveDepartmentName()
	{
		return receiveDepartmentName;
	}

	/**
	 * 设置属性值：部门名称
	 * @param name 部门名称
	 */
	public void setReceiveDepartmentName(String receiveDepartmentName)
	{
		this.receiveDepartmentName = receiveDepartmentName;
	}
	
	/**
	 * 纸质档案移交批次档案分类明细情况
	 */
	private Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = null;

	/**
	 * 设置属性值：纸质档案移交批次档案分类明细情况
	 * @param paperTransferBatchesArchvTypeDetails 纸质档案移交批次档案分类明细情况
	 */
	public void setPaperTransferBatchesArchvTypeDetails(Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails) {
		this.paperTransferBatchesArchvTypeDetails = paperTransferBatchesArchvTypeDetails;
	}

	/**
	 * 获取属性值：纸质档案移交批次档案分类明细情况
	 * @return 纸质档案移交批次档案分类明细情况
	 */
	public Map<Integer, PaperTransferBatchesArchvTypeDetail> getPaperTransferBatchesArchvTypeDetails() {
		return paperTransferBatchesArchvTypeDetails;
	}
    
	/**
	* 从指定对象克隆，复制所有属性值
	* @param paperTansferBatch 指定的对象源
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




	




