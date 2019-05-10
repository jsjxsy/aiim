package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 实物档案利用登记表的实体类
 */
public class ArchivesUseRegister
{
    /**
     * 构造函数
     */
    public ArchivesUseRegister()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 借档登记编号
	* @param borrowFlag 借阅标志
	* @param purposeID 利用目的编号
	* @param usePersonsCount 利用人数
	* @param useArchivesCount 利用卷数
	* @param useDate 利用日期
	* @param usePersonID 利用人编号
	* @param managerUserID 经办人编号
	* @param remark 备注
	*/
	public ArchivesUseRegister(int iD,boolean borrowFlag,int purposeID,int usePersonsCount,int useArchivesCount,Date useDate,int usePersonID,int managerUserID,String remark)
	{
		// Table Name: ArchivesUseRegister
		// Columns List,Can Used in SELECT SQL: ID,BorrowFlag,PurposeID,UsePersonsCount,UseArchivesCount,UseDate,UsePersonID,ManagerUserID,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:BorrowFlag,:PurposeID,:UsePersonsCount,:UseArchivesCount,:UseDate,:UsePersonID,:ManagerUserID,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,BorrowFlag=:BorrowFlag,PurposeID=:PurposeID,UsePersonsCount=:UsePersonsCount,UseArchivesCount=:UseArchivesCount,UseDate=:UseDate,UsePersonID=:UsePersonID,ManagerUserID=:ManagerUserID,Remark=:Remark

		this.iD = iD;
		this.borrowFlag = borrowFlag;
		this.purposeID = purposeID;
		this.usePersonsCount = usePersonsCount;
		this.useArchivesCount = useArchivesCount;
		this.useDate = useDate;
		this.usePersonID = usePersonID;
		this.managerUserID = managerUserID;
		this.remark = remark;
	}
	/**
	* 带字段参数的构造函数
	* @param iD 借档登记编号
	* @param borrowFlag 借阅标志
	* @param purposeID 利用目的编号
	* @param usePersonsCount 利用人数
	* @param useArchivesCount 利用卷数
	* @param useDate 利用日期
	* @param usePersonID 利用人编号
	* @param managerUserID 经办人编号
	* @param remark 备注
	*/

	/**
	* 带字段参数的构造函数
	* @param iD 借档登记编号
	* @param borrowFlag 借阅标志
	* @param purposeID 利用目的编号
	* @param usePersonsCount 利用人数
	* @param useArchivesCount 利用卷数
	* @param useDate 利用日期
	* @param usePersonID 利用人编号
	 * @param managerUserID 经办人编号
	 * @param remark 备注
	 * @param managerUserName 经办人姓名
	 * @param purposeText 利用目的名称
	 */
	public ArchivesUseRegister(int iD,boolean borrowFlag,int purposeID,int usePersonsCount,int useArchivesCount,Date useDate,int usePersonID,int managerUserID,String remark,String managerUserName,String purposeText)
	{
		// Table Name: ArchivesUseRegister
		// Columns List,Can Used in SELECT SQL: ID,BorrowFlag,PurposeID,UsePersonsCount,UseArchivesCount,UseDate,UsePersonID,ManagerUserID,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:BorrowFlag,:PurposeID,:UsePersonsCount,:UseArchivesCount,:UseDate,:UsePersonID,:ManagerUserID,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,BorrowFlag=:BorrowFlag,PurposeID=:PurposeID,UsePersonsCount=:UsePersonsCount,UseArchivesCount=:UseArchivesCount,UseDate=:UseDate,UsePersonID=:UsePersonID,ManagerUserID=:ManagerUserID,Remark=:Remark

		this.iD = iD;
		this.borrowFlag = borrowFlag;
		this.purposeID = purposeID;
		this.usePersonsCount = usePersonsCount;
		this.useArchivesCount = useArchivesCount;
		this.useDate = useDate;
		this.usePersonID = usePersonID;
		this.managerUserID = managerUserID;
		this.remark = remark;
		this.managerUserName = managerUserName;
		this.purposeText = purposeText;
		
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
	 * 借档登记编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：借档登记编号
	 * @return 借档登记编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：借档登记编号
	 * @param iD 借档登记编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 借阅标志
	 */
	private boolean borrowFlag=false;

	/**
	 * 获取属性值：借阅标志
	 * @return 借阅标志
	 */
	public boolean getBorrowFlag()
	{
		return borrowFlag;
	}

	/**
	 * 设置属性值：借阅标志
	 * @param borrowFlag 借阅标志
	 */
	public void setBorrowFlag(boolean borrowFlag)
	{
		this.borrowFlag = borrowFlag;
	}

	/**
	 * 利用目的编号
	 */
	private int purposeID=0;

	/**
	 * 获取属性值：利用目的编号
	 * @return 利用目的编号
	 */
	public int getPurposeID()
	{
		return purposeID;
	}

	/**
	 * 设置属性值：利用目的编号
	 * @param purposeID 利用目的编号
	 */
	public void setPurposeID(int purposeID)
	{
		this.purposeID = purposeID;
	}

	/**
	 * 利用人数
	 */
	private int usePersonsCount=0;

	/**
	 * 获取属性值：利用人数
	 * @return 利用人数
	 */
	public int getUsePersonsCount()
	{
		return usePersonsCount;
	}

	/**
	 * 设置属性值：利用人数
	 * @param usePersonsCount 利用人数
	 */
	public void setUsePersonsCount(int usePersonsCount)
	{
		this.usePersonsCount = usePersonsCount;
	}

	/**
	 * 利用卷数
	 */
	private int useArchivesCount=0;

	/**
	 * 获取属性值：利用卷数
	 * @return 利用卷数
	 */
	public int getUseArchivesCount()
	{
		return useArchivesCount;
	}

	/**
	 * 设置属性值：利用卷数
	 * @param useArchivesCount 利用卷数
	 */
	public void setUseArchivesCount(int useArchivesCount)
	{
		this.useArchivesCount = useArchivesCount;
	}

	/**
	 * 利用日期
	 */
	private Date useDate;

	/**
	 * 获取属性值：利用日期
	 * @return 利用日期
	 */
	public Date getUseDate()
	{
		return useDate;
	}

	/**
	 * 设置属性值：利用日期
	 * @param useDate 利用日期
	 */
	public void setUseDate(Date useDate)
	{
		this.useDate = useDate;
	}
	
	/**
	 * 利用人编号
	 */
	private int usePersonID=0;

	/**
	 * 获取属性值：利用人编号
	 * @return 利用人编号
	 */
	public int getUsePersonID()
	{
		return usePersonID;
	}

	/**
	 * 设置属性值：利用人编号
	 * @param usePersonID 利用人编号
	 */
	public void setUsePersonID(int usePersonID)
	{
		this.usePersonID = usePersonID;
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
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ArchivesUseRegister clone()
	{
		ArchivesUseRegister item = new ArchivesUseRegister(iD,borrowFlag,purposeID,usePersonsCount,useArchivesCount,useDate,usePersonID,managerUserID,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);
		

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param archivesUseRegister 指定的对象源
	*/
	public void cloneFrom(ArchivesUseRegister archivesUseRegister)
	{
		this.iD = archivesUseRegister.getID();
		this.borrowFlag = archivesUseRegister.getBorrowFlag();
		this.purposeID = archivesUseRegister.getPurposeID();
		this.usePersonsCount = archivesUseRegister.getUsePersonsCount();
		this.useArchivesCount = archivesUseRegister.getUseArchivesCount();
		this.useDate = archivesUseRegister.getUseDate();
		this.usePersonID = archivesUseRegister.getUsePersonID();
		this.managerUserID = archivesUseRegister.getManagerUserID();
		this.remark = archivesUseRegister.getRemark();
		this.keyInCol = archivesUseRegister.getKeyInCol();		
		this.tag = archivesUseRegister.getTag();
		this.archivesUsePersonInfo = archivesUseRegister.getArchivesUsePersonInfo();
		this.purposeText = archivesUseRegister.getPurposeText();
		this.managerUserName = archivesUseRegister.getManagerUserName();
	}
	
	/**
	 * 档案利用人信息实体对象
	 */
	private ArchivesUsePersonInfo archivesUsePersonInfo = null;

	/**
	 * 获取档案利用人信息
	 * @return
	 */
	public ArchivesUsePersonInfo getArchivesUsePersonInfo() {
		return archivesUsePersonInfo;
	}

	/**
	 * 设置档案利用人信息
	 * @param archivesUsePersonInfo
	 */
	public void setArchivesUsePersonInfo(ArchivesUsePersonInfo archivesUsePersonInfo) {
		this.archivesUsePersonInfo = archivesUsePersonInfo;
	}
	
	/**
	 * 利用目的显示文本
	 */
	private String purposeText = null;

	/**
	 * 获取利用目的文本
	 * @return
	 */
	public String getPurposeText() {
		return purposeText;
	}

	/**
	 * 设置利用目的文本
	 * @param purposeText
	 */
	public void setPurposeText(String purposeText) {
		this.purposeText = purposeText;
	}
	
	/**
	 * 经办人姓名
	 */
	private String managerUserName = null;

	/**
	 * 设置属性值：经办人姓名
	 * @param manageUserName 经办人姓名
	 */
	public void setManagerUserName(String managerUserName) {
		this.managerUserName = managerUserName;
	}

	/**
	 * 获取属性值：经办人姓名
	 * @return 经办人姓名
	 */
	public String getManagerUserName() {
		return managerUserName;
	}


    
}










//
//	/**
//	 * 实物档案利用清单信息
//	 */
//	private List<ArchivesUseInfo> archivesUseOutInfos = null;
//
//	/**
//	 * 设置属性值：实物档案利用清单信息
//	 * @param archivesUseOutInfos 实物档案利用清单信息
//	 */
//	public void setArchivesUseOutInfos(List<ArchivesUseInfo> archivesUseOutInfos) {
//		this.archivesUseOutInfos = archivesUseOutInfos;
//	}
//
//	/**
//	 * 获取属性值：实物档案利用清单信息
//	 * @return 实物档案利用清单信息
//	 */
//	public List<ArchivesUseInfo> getArchivesUseOutInfos() {
//		return archivesUseOutInfos;
//	}
//
//	
//    
//}
//
//

