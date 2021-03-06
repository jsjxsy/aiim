package com.orifound.aiim.entity;

import java.util.Date;
import java.util.List;
    
/**
 * 档案划分控制鉴定明细情况表的实体类
 */
public class AppraisalUseScopesDetail
{
    /**
     * 构造函数
     */
    public AppraisalUseScopesDetail()
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
	* @param registerDate 登记日期
	* @param managerUserID 经办人编号
	* @param remark 备注
	*/
	public AppraisalUseScopesDetail(int iD,int archivesTypeID,int nBXH,String archivesID,String title,String appraisalReason,Date appraisalDate,String appraisalOpinion,String appraisalPersion,Date registerDate,int managerUserID,String remark)
	{
		// Table Name: AppraisalUseScopesDetails
		// Columns List,Can Used in SELECT SQL: ID,ArchivesTypeID,NBXH,ArchivesID,Title,AppraisalReason,AppraisalDate,AppraisalOpinion,AppraisalPersion,RegisterDate,ManagerUserID,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:AppraisalReason,:AppraisalDate,:AppraisalOpinion,:AppraisalPersion,:RegisterDate,:ManagerUserID,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,AppraisalReason=:AppraisalReason,AppraisalDate=:AppraisalDate,AppraisalOpinion=:AppraisalOpinion,AppraisalPersion=:AppraisalPersion,RegisterDate=:RegisterDate,ManagerUserID=:ManagerUserID,Remark=:Remark

		this(archivesTypeID, nBXH, archivesID, title, appraisalReason, appraisalDate, appraisalOpinion, appraisalPersion, managerUserID, remark);
		this.iD = iD;
		this.registerDate = registerDate;
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
	* @param registerDate 登记日期
	* @param managerUserID 经办人编号
	* @param remark 备注
	*/
	public AppraisalUseScopesDetail(int iD,int archivesTypeID,int nBXH,String archivesID,String title,String appraisalReason,Date appraisalDate,String appraisalOpinion,String appraisalPersion,Date registerDate,int managerUserID,String remark, String archivesTypeName, String formationDepartment)
	{
		// Table Name: AppraisalUseScopesDetails
		// Columns List,Can Used in SELECT SQL: ID,ArchivesTypeID,NBXH,ArchivesID,Title,AppraisalReason,AppraisalDate,AppraisalOpinion,AppraisalPersion,RegisterDate,ManagerUserID,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:AppraisalReason,:AppraisalDate,:AppraisalOpinion,:AppraisalPersion,:RegisterDate,:ManagerUserID,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,AppraisalReason=:AppraisalReason,AppraisalDate=:AppraisalDate,AppraisalOpinion=:AppraisalOpinion,AppraisalPersion=:AppraisalPersion,RegisterDate=:RegisterDate,ManagerUserID=:ManagerUserID,Remark=:Remark

		this(archivesTypeID, nBXH, archivesID, title, appraisalReason, appraisalDate, appraisalOpinion, appraisalPersion, managerUserID, remark);
		this.iD = iD;
		this.registerDate = registerDate;
		this.archivesTypeName = archivesTypeName;
		this.formationDepartment = formationDepartment;
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
	* @param managerUserID 经办人编号
	* @param remark 备注
	*/
	public AppraisalUseScopesDetail(int archivesTypeID,int nBXH,String archivesID,String title,String appraisalReason,Date appraisalDate,String appraisalOpinion,String appraisalPersion,int managerUserID,String remark)
	{
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.archivesID = archivesID;
		this.title = title;
		this.appraisalReason = appraisalReason;
		this.appraisalDate = appraisalDate;
		this.appraisalOpinion = appraisalOpinion;
		this.appraisalPersion = appraisalPersion;
		this.managerUserID = managerUserID;
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
	 * 划分控制鉴定的角色范围id集合
	 */
	private List<Integer> roleIds = null;

	public List<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}
	
	/**
	 * 档案形成部门ID
	 */
	private int formationDepartmentID;
	
	public int getFormationDepartmentID() {
		return formationDepartmentID;
	}

	public void setFormationDepartmentID(int formationDepartmentID) {
		this.formationDepartmentID = formationDepartmentID;
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
	 * 划控角色名称集合
	 */
	private List<String> roleNames;
	
	public List<String> getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(List<String> roleNames) {
		this.roleNames = roleNames;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public AppraisalUseScopesDetail clone()
	{
		AppraisalUseScopesDetail item = new AppraisalUseScopesDetail(iD,archivesTypeID,nBXH,archivesID,title,appraisalReason,appraisalDate,appraisalOpinion,appraisalPersion,registerDate,managerUserID,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param appraisalUseScopesDetail 指定的对象源
	*/
	public void cloneFrom(AppraisalUseScopesDetail appraisalUseScopesDetail)
	{
		this.iD = appraisalUseScopesDetail.getID();
		this.archivesTypeID = appraisalUseScopesDetail.getArchivesTypeID();
		this.nBXH = appraisalUseScopesDetail.getNBXH();
		this.archivesID = appraisalUseScopesDetail.getArchivesID();
		this.title = appraisalUseScopesDetail.getTitle();
		this.appraisalReason = appraisalUseScopesDetail.getAppraisalReason();
		this.appraisalDate = appraisalUseScopesDetail.getAppraisalDate();
		this.appraisalOpinion = appraisalUseScopesDetail.getAppraisalOpinion();
		this.appraisalPersion = appraisalUseScopesDetail.getAppraisalPersion();
		this.registerDate = appraisalUseScopesDetail.getRegisterDate();
		this.managerUserID = appraisalUseScopesDetail.getManagerUserID();
		this.remark = appraisalUseScopesDetail.getRemark();
		this.keyInCol = appraisalUseScopesDetail.getKeyInCol();
		this.tag = appraisalUseScopesDetail.getTag();
	}

    
}



