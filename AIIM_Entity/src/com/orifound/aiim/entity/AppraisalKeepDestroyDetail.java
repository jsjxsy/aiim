package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 档案存毁鉴定明细情况表的实体类
 */
public class AppraisalKeepDestroyDetail
{
    /**
     * 构造函数
     */
    public AppraisalKeepDestroyDetail()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param archivesTypeID 档案分类编号
	* @param nBXH 档案内部序号
	* @param archivesID 档号
	* @param title 题名
	* @param appraisalReason 鉴定原因
	* @param appraisalDate 鉴定日期
	* @param appraisalOpinion 鉴定意见
	* @param appraisalPersion 鉴定人
	* @param appraisalDeletedFlag 鉴定删除标志
	* @param oldRetentionPeriodID 鉴定前的保管期限编号
	* @param newRetentionPeriodID 鉴定后的保管期限编号
	* @param registerDate 登记日期
	* @param managerUserID 经办人编号
	* @param remark 备注
	*/
	public AppraisalKeepDestroyDetail(int iD,int archivesTypeID,int nBXH,String archivesID,String title,String appraisalReason,Date appraisalDate,String appraisalOpinion,String appraisalPersion,boolean appraisalDeletedFlag,int oldRetentionPeriodID,int newRetentionPeriodID,Date registerDate,int managerUserID,String remark)
	{
		// Table Name: AppraisalKeepDestroyDetails
		// Columns List,Can Used in SELECT SQL: ID,ArchivesTypeID,NBXH,ArchivesID,Title,AppraisalReason,AppraisalDate,AppraisalOpinion,AppraisalPersion,AppraisalDeletedFlag,OldRetentionPeriodID,NewRetentionPeriodID,RegisterDate,ManagerUserID,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:AppraisalReason,:AppraisalDate,:AppraisalOpinion,:AppraisalPersion,:AppraisalDeletedFlag,:OldRetentionPeriodID,:NewRetentionPeriodID,:RegisterDate,:ManagerUserID,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,AppraisalReason=:AppraisalReason,AppraisalDate=:AppraisalDate,AppraisalOpinion=:AppraisalOpinion,AppraisalPersion=:AppraisalPersion,AppraisalDeletedFlag=:AppraisalDeletedFlag,OldRetentionPeriodID=:OldRetentionPeriodID,NewRetentionPeriodID=:NewRetentionPeriodID,RegisterDate=:RegisterDate,ManagerUserID=:ManagerUserID,Remark=:Remark

		this(archivesTypeID, nBXH, archivesID, title, appraisalReason, appraisalDate, appraisalOpinion, appraisalPersion, appraisalDeletedFlag, oldRetentionPeriodID, newRetentionPeriodID, managerUserID, remark);
		this.iD = iD;
		this.registerDate = registerDate;
	}
	

	/**
	* 带字段参数的构造函数
	* @param archivesTypeID 档案分类编号
	* @param nBXH 档案内部序号
	* @param archivesID 档号
	* @param title 题名
	* @param appraisalReason 鉴定原因
	* @param appraisalDate 鉴定日期
	* @param appraisalOpinion 鉴定意见
	* @param appraisalPersion 鉴定人
	* @param appraisalDeletedFlag 鉴定删除标志
	* @param oldRetentionPeriodID 鉴定前的保管期限编号
	* @param newRetentionPeriodID 鉴定后的保管期限编号
	* @param registerDate 登记日期
	* @param managerUserID 经办人编号
	* @param remark 备注
	*/
	public AppraisalKeepDestroyDetail(int archivesTypeID,int nBXH,String archivesID,String title,String appraisalReason,Date appraisalDate,String appraisalOpinion,String appraisalPersion,boolean appraisalDeletedFlag,int oldRetentionPeriodID,int newRetentionPeriodID,int managerUserID,String remark)
	{
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.archivesID = archivesID;
		this.title = title;
		this.appraisalReason = appraisalReason;
		this.appraisalDate = appraisalDate;
		this.appraisalOpinion = appraisalOpinion;
		this.appraisalPersion = appraisalPersion;
		this.appraisalDeletedFlag = appraisalDeletedFlag;
		this.oldRetentionPeriodID = oldRetentionPeriodID;
		this.newRetentionPeriodID = newRetentionPeriodID;
		this.managerUserID = managerUserID;
		this.remark = remark;
	}
	
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param archivesTypeID 档案分类编号
	* @param nBXH 档案内部序号
	* @param archivesID 档号
	* @param title 题名
	* @param appraisalReason 鉴定原因
	* @param appraisalDate 鉴定日期
	* @param appraisalOpinion 鉴定意见
	* @param appraisalPersion 鉴定人
	* @param appraisalDeletedFlag 鉴定删除标志
	* @param oldRetentionPeriodID 鉴定前的保管期限编号
	* @param newRetentionPeriodID 鉴定后的保管期限编号
	* @param registerDate 登记日期
	* @param managerUserID 经办人编号
	* @param remark 备注
	* @param formationDepartment 档案形成部门名称
	* @param archivesTypeName 档案类型名称
	* @param retentionPeriodName 保管期限
	* @param oldRetentionPeriodName 鉴定前保管期限
	*/
	public AppraisalKeepDestroyDetail(int iD,int archivesTypeID,int nBXH,String archivesID,String title,String appraisalReason,Date appraisalDate,String appraisalOpinion,String appraisalPersion,boolean appraisalDeletedFlag,int oldRetentionPeriodID,int newRetentionPeriodID,Date registerDate,int managerUserID,String remark, String formationDepartment, String archivesTypeName, String retentionPeriodName, String oldRetentionPeriodName)
	{
		// Table Name: AppraisalKeepDestroyDetails
		// Columns List,Can Used in SELECT SQL: ID,ArchivesTypeID,NBXH,ArchivesID,Title,AppraisalReason,AppraisalDate,AppraisalOpinion,AppraisalPersion,AppraisalDeletedFlag,OldRetentionPeriodID,NewRetentionPeriodID,RegisterDate,ManagerUserID,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:AppraisalReason,:AppraisalDate,:AppraisalOpinion,:AppraisalPersion,:AppraisalDeletedFlag,:OldRetentionPeriodID,:NewRetentionPeriodID,:RegisterDate,:ManagerUserID,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,AppraisalReason=:AppraisalReason,AppraisalDate=:AppraisalDate,AppraisalOpinion=:AppraisalOpinion,AppraisalPersion=:AppraisalPersion,AppraisalDeletedFlag=:AppraisalDeletedFlag,OldRetentionPeriodID=:OldRetentionPeriodID,NewRetentionPeriodID=:NewRetentionPeriodID,RegisterDate=:RegisterDate,ManagerUserID=:ManagerUserID,Remark=:Remark

		this(iD, archivesTypeID, nBXH, archivesID, title, appraisalReason, appraisalDate, appraisalOpinion, appraisalPersion, appraisalDeletedFlag, oldRetentionPeriodID, newRetentionPeriodID, registerDate, managerUserID, remark);
		this.formationDepartment = formationDepartment;
		this.archivesTypeName = archivesTypeName;
		this.retentionPeriodName = retentionPeriodName;
		this.oldRetentionPeriodName = oldRetentionPeriodName;
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
	 * 鉴定原因
	 */
	private String appraisalReason=null;

	/**
	 * 获取属性值：鉴定原因
	 * @return 鉴定原因
	 */
	public String getAppraisalReason()
	{
		return appraisalReason;
	}

	/**
	 * 设置属性值：鉴定原因
	 * @param appraisalReason 鉴定原因
	 */
	public void setAppraisalReason(String appraisalReason)
	{
		this.appraisalReason = appraisalReason;
	}

	/**
	 * 鉴定日期
	 */
	private Date appraisalDate;

	/**
	 * 获取属性值：鉴定日期
	 * @return 鉴定日期
	 */
	public Date getAppraisalDate()
	{
		return appraisalDate;
	}

	/**
	 * 设置属性值：鉴定日期
	 * @param appraisalDate 鉴定日期
	 */
	public void setAppraisalDate(Date appraisalDate)
	{
		this.appraisalDate = appraisalDate;
	}

	/**
	 * 鉴定意见
	 */
	private String appraisalOpinion=null;

	/**
	 * 获取属性值：鉴定意见
	 * @return 鉴定意见
	 */
	public String getAppraisalOpinion()
	{
		return appraisalOpinion;
	}

	/**
	 * 设置属性值：鉴定意见
	 * @param appraisalOpinion 鉴定意见
	 */
	public void setAppraisalOpinion(String appraisalOpinion)
	{
		this.appraisalOpinion = appraisalOpinion;
	}

	/**
	 * 鉴定人
	 */
	private String appraisalPersion=null;

	/**
	 * 获取属性值：鉴定人
	 * @return 鉴定人
	 */
	public String getAppraisalPersion()
	{
		return appraisalPersion;
	}

	/**
	 * 设置属性值：鉴定人
	 * @param appraisalPersion 鉴定人
	 */
	public void setAppraisalPersion(String appraisalPersion)
	{
		this.appraisalPersion = appraisalPersion;
	}

	/**
	 * 鉴定删除标志
	 */
	private boolean appraisalDeletedFlag=false;

	/**
	 * 获取属性值：鉴定删除标志
	 * @return 鉴定删除标志
	 */
	public boolean getAppraisalDeletedFlag()
	{
		return appraisalDeletedFlag;
	}

	/**
	 * 设置属性值：鉴定删除标志
	 * @param appraisalDeletedFlag 鉴定删除标志
	 */
	public void setAppraisalDeletedFlag(boolean appraisalDeletedFlag)
	{
		this.appraisalDeletedFlag = appraisalDeletedFlag;
	}

	/**
	 * 鉴定前的保管期限编号
	 */
	private int oldRetentionPeriodID=0;

	/**
	 * 获取属性值：鉴定前的保管期限编号
	 * @return 鉴定前的保管期限编号
	 */
	public int getOldRetentionPeriodID()
	{
		return oldRetentionPeriodID;
	}

	/**
	 * 设置属性值：鉴定前的保管期限编号
	 * @param oldRetentionPeriodID 鉴定前的保管期限编号
	 */
	public void setOldRetentionPeriodID(int oldRetentionPeriodID)
	{
		this.oldRetentionPeriodID = oldRetentionPeriodID;
	}

	/**
	 * 鉴定后的保管期限编号
	 */
	private int newRetentionPeriodID=0;

	/**
	 * 获取属性值：鉴定后的保管期限编号
	 * @return 鉴定后的保管期限编号
	 */
	public int getNewRetentionPeriodID()
	{
		return newRetentionPeriodID;
	}

	/**
	 * 设置属性值：鉴定后的保管期限编号
	 * @param newRetentionPeriodID 鉴定后的保管期限编号
	 */
	public void setNewRetentionPeriodID(int newRetentionPeriodID)
	{
		this.newRetentionPeriodID = newRetentionPeriodID;
	}

	/**
	 * 登记日期
	 */
	private Date registerDate;

	/**
	 * 获取属性值：登记日期
	 * @return 登记日期
	 */
	public Date getRegisterDate()
	{
		return registerDate;
	}

	/**
	 * 设置属性值：登记日期
	 * @param registerDate 登记日期
	 */
	public void setRegisterDate(Date registerDate)
	{
		this.registerDate = registerDate;
	}

	/**
	 * 经办人编号
	 */
	private int managerUserID=0;

	/**
	 * 获取属性值：经办人编号
	 * @return 经办人编号
	 */
	public int getManagerUserID()
	{
		return managerUserID;
	}

	/**
	 * 设置属性值：经办人编号
	 * @param managerUserID 经办人编号
	 */
	public void setManagerUserID(int managerUserID)
	{
		this.managerUserID = managerUserID;
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
	 * 档案形成部门名称
	 */
	private String formationDepartment;
	
	/**
	 * 获取属性值：档案形成部门名称
	 * @return 档案形成部门名称
	 */
	public String getFormationDepartment()
	{
		return formationDepartment;
	}

	/**
	 * 设置属性值：档案形成部门名称
	 * @param formationDepartment 档案形成部门名称
	 */
	public void setFormationDepartment(String formationDepartment)
	{
		this.formationDepartment = formationDepartment;
	}
	
	/**
	 * 档案类型名称
	 */
	private String archivesTypeName;
	
	/**
	 * 获取属性值： 档案类型名称
	 * @return  档案类型名称
	 */
	public String getArchivesTypeName() {
		return archivesTypeName;
	}
	
	/**
	 * 设置属性值： 档案类型名称
	 * @param formationDepartment  档案类型名称
	 */
	public void setArchivesTypeName(String archivesTypeName) {
		this.archivesTypeName = archivesTypeName;
	}
	
	/**
	 * 保管期限名称
	 */
	public String retentionPeriodName ;

	public String getRetentionPeriodName() {
		return retentionPeriodName;
	}

	public void setRetentionPeriodName(String retentionPeriodName) {
		this.retentionPeriodName = retentionPeriodName;
	}

	/**
	 * 鉴定前保管期限名称
	 */
	public String oldRetentionPeriodName ;
	
	public String getOldRetentionPeriodName() {
		return oldRetentionPeriodName;
	}

	public void setOldRetentionPeriodName(String oldRetentionPeriodName) {
		this.oldRetentionPeriodName = oldRetentionPeriodName;
	}
	

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public AppraisalKeepDestroyDetail clone()
	{
		AppraisalKeepDestroyDetail item = new AppraisalKeepDestroyDetail(iD,archivesTypeID,nBXH,archivesID,title,appraisalReason,appraisalDate,appraisalOpinion,appraisalPersion,appraisalDeletedFlag,oldRetentionPeriodID,newRetentionPeriodID,registerDate,managerUserID,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param appraisalKeepDestroyDetail 指定的对象源
	*/
	public void cloneFrom(AppraisalKeepDestroyDetail appraisalKeepDestroyDetail)
	{
		this.iD = appraisalKeepDestroyDetail.getID();
		this.archivesTypeID = appraisalKeepDestroyDetail.getArchivesTypeID();
		this.nBXH = appraisalKeepDestroyDetail.getNBXH();
		this.archivesID = appraisalKeepDestroyDetail.getArchivesID();
		this.title = appraisalKeepDestroyDetail.getTitle();
		this.appraisalReason = appraisalKeepDestroyDetail.getAppraisalReason();
		this.appraisalDate = appraisalKeepDestroyDetail.getAppraisalDate();
		this.appraisalOpinion = appraisalKeepDestroyDetail.getAppraisalOpinion();
		this.appraisalPersion = appraisalKeepDestroyDetail.getAppraisalPersion();
		this.appraisalDeletedFlag = appraisalKeepDestroyDetail.getAppraisalDeletedFlag();
		this.oldRetentionPeriodID = appraisalKeepDestroyDetail.getOldRetentionPeriodID();
		this.newRetentionPeriodID = appraisalKeepDestroyDetail.getNewRetentionPeriodID();
		this.registerDate = appraisalKeepDestroyDetail.getRegisterDate();
		this.managerUserID = appraisalKeepDestroyDetail.getManagerUserID();
		this.remark = appraisalKeepDestroyDetail.getRemark();
		this.keyInCol = appraisalKeepDestroyDetail.getKeyInCol();
		this.tag = appraisalKeepDestroyDetail.getTag();
	}
}



