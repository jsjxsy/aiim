package com.orifound.aiim.entity;

import java.util.Date;
    
/**
 * 档案利用申请单明细情况
 */
public class ArchivesUseRequestDetail
{
    /**
     * 构造函数
     */
    public ArchivesUseRequestDetail()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 申请单明细编号
	* @param requestID 申请单编号
	* @param archivesTypeID 档案分类编号
	* @param archivesTypeText 档案分类名称
	* @param nBXH 档案内部序号
	* @param secrecyID 密级编号
	* @param secrecyText 密级文本
	* @param archivesID 档号
	* @param title 题名
	* @param useWayID 档案利用方式编号
	* @param useWayText 利用方式
	* @param checkResult 审批结果
	* @param backReason 申请驳回原因
	* @param checkTime 审批时间
	* @param checkUserID 审批人编号
	* @param remark 备注
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
	 * 申请单明细编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：申请单明细编号
	 * @return 申请单明细编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：申请单明细编号
	 * @param iD 申请单明细编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 申请单编号
	 */
	private int requestID=0;

	/**
	 * 获取属性值：申请单编号
	 * @return 申请单编号
	 */
	public int getRequestID()
	{
		return requestID;
	}

	/**
	 * 设置属性值：申请单编号
	 * @param requestID 申请单编号
	 */
	public void setRequestID(int requestID)
	{
		this.requestID = requestID;
	}

	/**
	 * 档案分类编号
	 */
	private int archivesTypeID=0;

	/**
	 * 获取属性值：档案分类编号
	 * @return 档案分类编号
	 */
	public int getArchivesTypeID()
	{
		return archivesTypeID;
	}

	/**
	 * 设置属性值：档案分类编号
	 * @param archivesTypeID 档案分类编号
	 */
	public void setArchivesTypeID(int archivesTypeID)
	{
		this.archivesTypeID = archivesTypeID;
	}
	
	/**
	 * 档案分类名称
	 */
	private String archivesTypeText = null;
	
	/**
	 * 获取设置档案分类名称
	 * @return
	 */
	public String getArchivesTypeText() {
		return archivesTypeText;
	}

	/**
	 * 设置档案分类名称
	 * @param archivesTypeText
	 */
	public void setArchivesTypeText(String archivesTypeText) {
		this.archivesTypeText = archivesTypeText;
	}
	

	/**
	 * 档案内部序号
	 */
	private int nBXH=0;

	/**
	 * 获取属性值：档案内部序号
	 * @return 档案内部序号
	 */
	public int getNBXH()
	{
		return nBXH;
	}

	/**
	 * 设置属性值：档案内部序号
	 * @param nBXH 档案内部序号
	 */
	public void setNBXH(int nBXH)
	{
		this.nBXH = nBXH;
	}
	
	/**
	 * 档案密级编号
	 */
	private int secrecyID = 0;
	
	/**
	 * 获取档案密级编号
	 * @return
	 */
	public int getSecrecyID() {
		return secrecyID;
	}

	/**
	 * 设置档案密级编号
	 * @return
	 */
	public void setSecrecyID(int secrecyID) {
		this.secrecyID = secrecyID;
	}
	
	/**
	 * 档案密级文本
	 */
	private String secrecyText =null;
	

	/**
	 * 获取档案密级文本
	 * @return
	 */
	public String getSecrecyText() {
		return secrecyText;
	}

	/**
	 * 设置档案密级文本
	 * @param secrecyText
	 */
	public void setSecrecyText(String secrecyText) {
		this.secrecyText = secrecyText;
	}

	/**
	 * 档号
	 */
	private String archivesID=null;

	/**
	 * 获取属性值：档号
	 * @return 档号
	 */
	public String getArchivesID()
	{
		return archivesID;
	}

	/**
	 * 设置属性值：档号
	 * @param archivesID 档号
	 */
	public void setArchivesID(String archivesID)
	{
		this.archivesID = archivesID;
	}

	/**
	 * 题名
	 */
	private String title=null;

	/**
	 * 获取属性值：题名
	 * @return 题名
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * 设置属性值：题名
	 * @param title 题名
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * 档案利用方式编号
	 */
	private int useWayID=0;

	/**
	 * 获取属性值：档案利用方式编号
	 * @return 档案利用方式编号
	 */
	public int getUseWayID()
	{
		return useWayID;
	}

	/**
	 * 设置属性值：档案利用方式编号
	 * @param useWayID 档案利用方式编号
	 */
	public void setUseWayID(int useWayID)
	{
		this.useWayID = useWayID;
	}
	
	/**
	 * 利用方式显示文本
	 * @return
	 */
	private String useWayText = null;
	
	/**
	 * 获取利用方式显示文本
	 */
	public String getUseWayText() {
		return useWayText;
	}

	/**
	 * 设置利用方式显示文本
	 * @param useWayText
	 */
	public void setUseWayText(String useWayText) {
		this.useWayText = useWayText;
	}

	

	/**
	 * 审批结果
	 */
	private int checkResult=0;

	/**
	 * 获取属性值：审批结果
	 * @return 审批结果
	 */
	public int getCheckResult()
	{
		return checkResult;
	}

	/**
	 * 设置属性值：审批结果
	 * @param checkResult 审批结果
	 */
	public void setCheckResult(int checkResult)
	{
		this.checkResult = checkResult;
	}

	/**
	 * 申请驳回原因
	 */
	private String backReason=null;

	/**
	 * 获取属性值：申请驳回原因
	 * @return 申请驳回原因
	 */
	public String getBackReason()
	{
		return backReason;
	}

	/**
	 * 设置属性值：申请驳回原因
	 * @param backReason 申请驳回原因
	 */
	public void setBackReason(String backReason)
	{
		this.backReason = backReason;
	}

	/**
	 * 审批时间
	 */
	private Date checkTime;

	/**
	 * 获取属性值：审批时间
	 * @return 审批时间
	 */
	public Date getCheckTime()
	{
		return checkTime;
	}

	/**
	 * 设置属性值：审批时间
	 * @param checkTime 审批时间
	 */
	public void setCheckTime(Date checkTime)
	{
		this.checkTime = checkTime;
	}

	/**
	 * 审批人编号
	 */
	private int checkUserID=0;

	/**
	 * 获取属性值：审批人编号
	 * @return 审批人编号
	 */
	public int getCheckUserID()
	{
		return checkUserID;
	}

	/**
	 * 设置属性值：审批人编号
	 * @param checkUserID 审批人编号
	 */
	public void setCheckUserID(int checkUserID)
	{
		this.checkUserID = checkUserID;
	}

	/**
	 * 备注
	 */
	private String remark=null;

	/**
	 * 获取属性值：备注
	 * @return 备注
	 */
	public String getRemark()
	{
		return remark;
	}

	/**
	 * 设置属性值：备注
	 * @param remark 备注
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	
	
	/**
	 * 档案利用申请单信息
	 */
	private ArchivesUseRequest archivesUseRequest;
	
	/**
	 * 获取档案利用申请单信息
	 * @return
	 */
	public ArchivesUseRequest getArchivesUseRequest() {
		return archivesUseRequest;
	}

	/**
	 * 设置档案利用申请单信息
	 * @param archivesUseRequest
	 */
	public void setArchivesUseRequest(ArchivesUseRequest archivesUseRequest) {
		this.archivesUseRequest = archivesUseRequest;
	}
	


	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
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
	* 从指定对象克隆，复制所有属性值
	* @param archivesUseRequestDetail 指定的对象源
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



