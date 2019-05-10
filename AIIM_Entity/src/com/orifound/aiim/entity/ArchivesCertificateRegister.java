package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 档案出证登记表的实体类
 */
public class ArchivesCertificateRegister 
{
    /**
     * 构造函数
     */
    public ArchivesCertificateRegister()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 出证业务登记编号
	* @param personID 利用人编号
	* @param shouldCharge 应缴金额
	* @param realCharge 实际收费
	* @param registerDate 登记日期
	* @param invoiceSN 发票代码
	* @param managerUserID 经办人编号
	* @param remark 备注
	*/
	public ArchivesCertificateRegister(int iD,int personID,double shouldCharge,double realCharge,Date registerDate,String invoiceSN,int managerUserID,String remark)
	{
		// Table Name: ArchivesCertificateRegister
		// Columns List,Can Used in SELECT SQL: ID,PersonID,ShouldCharge,RealCharge,RegisterDate,InvoiceSN,ManagerUserID,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:PersonID,:ShouldCharge,:RealCharge,:RegisterDate,:InvoiceSN,:ManagerUserID,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,PersonID=:PersonID,ShouldCharge=:ShouldCharge,RealCharge=:RealCharge,RegisterDate=:RegisterDate,InvoiceSN=:InvoiceSN,ManagerUserID=:ManagerUserID,Remark=:Remark

		this.iD = iD;
		this.personID = personID;
		this.shouldCharge = shouldCharge;
		this.realCharge = realCharge;
		this.registerDate = registerDate;
		this.invoiceSN = invoiceSN;
		this.managerUserID = managerUserID;
		this.remark = remark;
	}
	
    
	/**
	* 带字段参数的构造函数
	* @param iD 出证业务登记编号
	* @param personID 利用人编号
	* @param shouldCharge 应缴金额
	* @param realCharge 实际收费
	* @param registerDate 登记日期
	* @param invoiceSN 发票代码
	* @param managerUserID 经办人编号
	* @param remark 备注
	*/
	public ArchivesCertificateRegister(int iD,int personID,double shouldCharge,double realCharge,Date registerDate,String invoiceSN,int managerUserID,String realName,String remark)
	{
		// Table Name: ArchivesCertificateRegister
		// Columns List,Can Used in SELECT SQL: ID,PersonID,ShouldCharge,RealCharge,RegisterDate,InvoiceSN,ManagerUserID,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:PersonID,:ShouldCharge,:RealCharge,:RegisterDate,:InvoiceSN,:ManagerUserID,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,PersonID=:PersonID,ShouldCharge=:ShouldCharge,RealCharge=:RealCharge,RegisterDate=:RegisterDate,InvoiceSN=:InvoiceSN,ManagerUserID=:ManagerUserID,Remark=:Remark

		this.iD = iD;
		this.personID = personID;
		this.shouldCharge = shouldCharge;
		this.realCharge = realCharge;
		this.registerDate = registerDate;
		this.invoiceSN = invoiceSN;
		this.managerUserID = managerUserID;
		this.realName = realName;
		this.remark = remark;
	}
	/**
	* 带字段参数的构造函数
	* @param iD 出证业务登记编号
	* @param personID 利用人编号
	* @param shouldCharge 应缴金额
	* @param realCharge 实际收费
	* @param registerDate 登记日期
	* @param invoiceSN 发票代码
	* @param managerUserID 经办人编号
	* @param remark 备注
	*/
	public ArchivesCertificateRegister(double shouldCharge,double realCharge,int managerUserID,String realName)
	{
		// Table Name: ArchivesCertificateRegister
		// Columns List,Can Used in SELECT SQL: ID,PersonID,ShouldCharge,RealCharge,RegisterDate,InvoiceSN,ManagerUserID,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:PersonID,:ShouldCharge,:RealCharge,:RegisterDate,:InvoiceSN,:ManagerUserID,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,PersonID=:PersonID,ShouldCharge=:ShouldCharge,RealCharge=:RealCharge,RegisterDate=:RegisterDate,InvoiceSN=:InvoiceSN,ManagerUserID=:ManagerUserID,Remark=:Remark

		this.shouldCharge = shouldCharge;
		this.realCharge = realCharge;
		this.managerUserID = managerUserID;
		this.realName = realName;
	}
	
	/**
	* 带字段参数的构造函数
	* @param shouldCharge 应缴金额
	* @param realCharge 实际收费
	*/
	public ArchivesCertificateRegister(double shouldCharge,double realCharge)
	{
		// Table Name: ArchivesCertificateRegister
		// Columns List,Can Used in SELECT SQL: ID,PersonID,ShouldCharge,RealCharge,RegisterDate,InvoiceSN,ManagerUserID,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:PersonID,:ShouldCharge,:RealCharge,:RegisterDate,:InvoiceSN,:ManagerUserID,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,PersonID=:PersonID,ShouldCharge=:ShouldCharge,RealCharge=:RealCharge,RegisterDate=:RegisterDate,InvoiceSN=:InvoiceSN,ManagerUserID=:ManagerUserID,Remark=:Remark

		this.shouldCharge = shouldCharge;
		this.realCharge = realCharge;
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
	 * 出证业务登记编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：出证业务登记编号
	 * @return 出证业务登记编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：出证业务登记编号
	 * @param iD 出证业务登记编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 利用人编号
	 */
	private int personID=0;

	/**
	 * 获取属性值：利用人编号
	 * @return 利用人编号
	 */
	public int getPersonID()
	{
		return personID;
	}

	/**
	 * 设置属性值：利用人编号
	 * @param personID 利用人编号
	 */
	public void setPersonID(int personID)
	{
		this.personID = personID;
	}

	/**
	 * 应缴金额
	 */
	private double shouldCharge=0;

	/**
	 * 获取属性值：应缴金额
	 * @return 应缴金额
	 */
	public double getShouldCharge()
	{
		return shouldCharge;
	}

	/**
	 * 设置属性值：应缴金额
	 * @param shouldCharge 应缴金额
	 */
	public void setShouldCharge(double shouldCharge)
	{
		this.shouldCharge = shouldCharge;
	}

	/**
	 * 实际收费
	 */
	private double realCharge=0;

	/**
	 * 获取属性值：实际收费
	 * @return 实际收费
	 */
	public double getRealCharge()
	{
		return realCharge;
	}

	/**
	 * 设置属性值：实际收费
	 * @param realCharge 实际收费
	 */
	public void setRealCharge(double realCharge)
	{
		this.realCharge = realCharge;
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
	 * 发票代码
	 */
	private String invoiceSN=null;

	/**
	 * 获取属性值：发票代码
	 * @return 发票代码
	 */
	public String getInvoiceSN()
	{
		return invoiceSN;
	}

	/**
	 * 设置属性值：发票代码
	 * @param invoiceSN 发票代码
	 */
	public void setInvoiceSN(String invoiceSN)
	{
		this.invoiceSN = invoiceSN;
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
	 * 真实名
	 */
	private String realName = null;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
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
	 * 利用人姓名
	 */
	private String personName;
	
	/**
	 * 经办人姓名
	 */
	private String managerUserName;
	
	/**
	 * 证件号
	 */
	private String cardNo;

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getManagerUserName() {
		return managerUserName;
	}

	public void setManagerUserName(String managerUserName) {
		this.managerUserName = managerUserName;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ArchivesCertificateRegister clone()
	{
		ArchivesCertificateRegister item = new ArchivesCertificateRegister(iD,personID,shouldCharge,realCharge,registerDate,invoiceSN,managerUserID,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param archivesCertificateRegister 指定的对象源
	*/
	public void cloneFrom(ArchivesCertificateRegister archivesCertificateRegister)
	{
		this.iD = archivesCertificateRegister.getID();
		this.personID = archivesCertificateRegister.getPersonID();
		this.shouldCharge = archivesCertificateRegister.getShouldCharge();
		this.realCharge = archivesCertificateRegister.getRealCharge();
		this.registerDate = archivesCertificateRegister.getRegisterDate();
		this.invoiceSN = archivesCertificateRegister.getInvoiceSN();
		this.managerUserID = archivesCertificateRegister.getManagerUserID();
		this.remark = archivesCertificateRegister.getRemark();
		this.keyInCol = archivesCertificateRegister.getKeyInCol();
		this.tag = archivesCertificateRegister.getTag();
	}

}



