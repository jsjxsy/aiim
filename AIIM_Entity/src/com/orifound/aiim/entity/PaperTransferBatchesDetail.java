package com.orifound.aiim.entity;

import java.util.Date;
    
/**
 * 纸质档案移交明细清单表的实体类
 */
public class PaperTransferBatchesDetail
{
    /**
     * 构造函数
     */
    public PaperTransferBatchesDetail()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* 
	* @param transferBatNo 移交批次号
	* @param archivesTypeID 档案分类编号
	* @param nBXH 档案内部序号
	* @param parentFlag 案卷标志
	* @param archivesID 档号
	* @param title 题名
	* @param subContentCount 卷内文件数量
	* @param pageSum 档案页数
	* @param retentionPeriodID 保管期限编号
	* @param secrecyID 档案密级编号
	* @param formationYear 档案形成年度
	* @param transferTime 添加至移交清单的时间
	* @param receiveCheckResult 接收审核结果
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
	* 带字段参数的构造函数
	* @param iD 编号
	* @param transferBatNo 移交批次号
	* @param archivesTypeID 档案分类编号
	* @param nBXH 档案内部序号
	* @param parentFlag 案卷标志
	* @param archivesID 档号
	* @param title 题名
	* @param subContentCount 卷内文件数量
	* @param pageSum 档案页数
	* @param retentionPeriodID 保管期限编号
	* @param secrecyID 档案密级编号
	* @param formationYear 档案形成年度
	* @param transferTime 添加至移交清单的时间
	* @param receiveCheckResult 接收审核结果
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
	 * 编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：编号
	 * @return 编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：编号
	 * @param iD 编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 移交批次号
	 */
	private String transferBatNo=null;

	/**
	 * 获取属性值：移交批次号
	 * @return 移交批次号
	 */
	public String getTransferBatNo()
	{
		return transferBatNo;
	}

	/**
	 * 设置属性值：移交批次号
	 * @param transferBatNo 移交批次号
	 */
	public void setTransferBatNo(String transferBatNo)
	{
		this.transferBatNo = transferBatNo;
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
	 * 案卷标志
	 */
	private boolean parentFlag=false;

	/**
	 * 获取属性值：案卷标志
	 * @return 案卷标志
	 */
	public boolean getParentFlag()
	{
		return parentFlag;
	}

	/**
	 * 设置属性值：案卷标志
	 * @param parentFlag 案卷标志
	 */
	public void setParentFlag(boolean parentFlag)
	{
		this.parentFlag = parentFlag;
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
	 * 卷内文件数量
	 */
	private int subContentCount=0;

	/**
	 * 获取属性值：卷内文件数量
	 * @return 卷内文件数量
	 */
	public int getSubContentCount()
	{
		return subContentCount;
	}

	/**
	 * 设置属性值：卷内文件数量
	 * @param subContentCount 卷内文件数量
	 */
	public void setSubContentCount(int subContentCount)
	{
		this.subContentCount = subContentCount;
	}

	/**
	 * 档案页数
	 */
	private int pageSum=0;

	/**
	 * 获取属性值：档案页数
	 * @return 档案页数
	 */
	public int getPageSum()
	{
		return pageSum;
	}

	/**
	 * 设置属性值：档案页数
	 * @param pageSum 档案页数
	 */
	public void setPageSum(int pageSum)
	{
		this.pageSum = pageSum;
	}

	/**
	 * 保管期限编号
	 */
	private int retentionPeriodID=0;

	/**
	 * 获取属性值：保管期限编号
	 * @return 保管期限编号
	 */
	public int getRetentionPeriodID()
	{
		return retentionPeriodID;
	}

	/**
	 * 设置属性值：保管期限编号
	 * @param retentionPeriodID 保管期限编号
	 */
	public void setRetentionPeriodID(int retentionPeriodID)
	{
		this.retentionPeriodID = retentionPeriodID;
	}

	/**
	 * 档案密级编号
	 */
	private int secrecyID=0;

	/**
	 * 获取属性值：档案密级编号
	 * @return 档案密级编号
	 */
	public int getSecrecyID()
	{
		return secrecyID;
	}

	/**
	 * 设置属性值：档案密级编号
	 * @param secrecyID 档案密级编号
	 */
	public void setSecrecyID(int secrecyID)
	{
		this.secrecyID = secrecyID;
	}

	/**
	 * 档案形成年度
	 */
	private int formationYear=0;

	/**
	 * 获取属性值：档案形成年度
	 * @return 档案形成年度
	 */
	public int getFormationYear()
	{
		return formationYear;
	}

	/**
	 * 设置属性值：档案形成年度
	 * @param formationYear 档案形成年度
	 */
	public void setFormationYear(int formationYear)
	{
		this.formationYear = formationYear;
	}

	/**
	 * 添加至移交清单的时间
	 */
	private Date transferTime;

	/**
	 * 获取属性值：添加至移交清单的时间
	 * @return 添加至移交清单的时间
	 */
	public Date getTransferTime()
	{
		return transferTime;
	}

	/**
	 * 设置属性值：添加至移交清单的时间
	 * @param transferTime 添加至移交清单的时间
	 */
	public void setTransferTime(Date transferTime)
	{
		this.transferTime = transferTime;
	}

	/**
	 * 接收审核结果
	 */
	private EnumCheckResult receiveCheckResult=EnumCheckResult.尚未审核;

	/**
	 * 获取属性值：接收审核结果
	 * @return 接收审核结果
	 */
	public EnumCheckResult getReceiveCheckResult()
	{
		return receiveCheckResult;
	}

	/**
	 * 设置属性值：接收审核结果
	 * @param receiveCheckResult 接收审核结果
	 */
	public void setReceiveCheckResult(EnumCheckResult receiveCheckResult)
	{
		this.receiveCheckResult = receiveCheckResult;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public PaperTransferBatchesDetail clone()
	{
		PaperTransferBatchesDetail item = new PaperTransferBatchesDetail(iD,transferBatNo,archivesTypeID,nBXH,parentFlag,archivesID,title,subContentCount,pageSum,retentionPeriodID,secrecyID,formationYear,transferTime,receiveCheckResult);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}


	/**
	 * 密级文本
	 */
	private String secrecyText;
	
	/**
	 * 保管期限文本
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
	* 从指定对象克隆，复制所有属性值
	* @param paperTransferBatchesDetail 指定的对象源
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



