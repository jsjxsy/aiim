package com.orifound.aiim.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 档案转出信息表
 * @author Administrator
 *
 */
public class MoveOutInfo {

	/**
	 * 转出登记临时编号
	 */
	private int id;
	
	/**
	 * 转出方式
	 */
	private int moveOutWay;
	
	/**
	 * 转出档案总数量
	 */
	private int totalArchives;
	
	/**
	 * 单位所在地
	 */
	private String companyAddr;
	
	/**
	 * 单位名称
	 */
	private String companyName;
	
	/**
	 * 联系电话
	 */
	private String phone;
	
	/**
	 * 邮寄部门
	 */
	private String mailingCompany;
	
	/**
	 * 接收地邮编
	 */
	private String receivePostcode; 
	
	/**
	 * 邮寄地址
	 */
	private String mailingAddr;
	
	/**
	 * 转出单序列号
	 */
	private String SN;
	
	/**
	 * 转出登记日期
	 */
	private Date registerDate;
	
	/**
	 * 经办人编号
	 */
	private int managerUserID;
	
	/**
	 * 是否已经转出标志
	 */
	private boolean moveOutedFlag;
	
	private int archiveTypeId;
	
	public int getArchiveTypeId() {
		return archiveTypeId;
	}

	public void setArchiveTypeId(int archiveTypeId) {
		this.archiveTypeId = archiveTypeId;
	}

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 转出单的人员信息
	 */
	private List<StudentInfo> studentInfos = new ArrayList<StudentInfo>();
	
	/**
	 * 获得单的人员信息
	 */
	public List<StudentInfo> getStudentInfos() {
		return studentInfos;
	}

	/**
	 * 设置单的人员信息
	 */
	public void setStudentInfos(List<StudentInfo> studentInfos) {
		this.studentInfos = studentInfos;
	}

	/**
	 * 获得转出登记临时编号
	 */
	public int getId() {
		return id;
	}

	/**
	 * 设置转出登记临时编号
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 获得转出方式
	 */
	public int getMoveOutWay() {
		return moveOutWay;
	}

	/**
	 * 设置转出方式
	 */
	public void setMoveOutWay(int moveOutWay) {
		this.moveOutWay = moveOutWay;
	}

	/**
	 * 获得转出档案总数量
	 */
	public int getTotalArchives() {
		return totalArchives;
	}

	/**
	 * 设置档案总数量
	 */
	public void setTotalArchives(int totalArchives) {
		this.totalArchives = totalArchives;
	}

	/**
	 * 获得单位所在地
	 */
	public String getCompanyAddr() {
		return companyAddr;
	}

	/**
	 * 设置单位所在地
	 */
	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}

	/**
	 * 获得单位名称
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 设置单位名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * 获得联系电话
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获得邮寄部门
	 */
	public String getMailingCompany() {
		return mailingCompany;
	}

	/**
	 * 设置邮寄部门
	 */
	public void setMailingCompany(String mailingCompany) {
		this.mailingCompany = mailingCompany;
	}

	/**
	 * 获得接收地邮编
	 */
	public String getReceivePostcode() {
		return receivePostcode;
	}

	/**
	 * 设置接收地邮编
	 */
	public void setReceivePostcode(String receivePostcode) {
		this.receivePostcode = receivePostcode;
	}

	/**
	 * 获得邮寄地址
	 */
	public String getMailingAddr() {
		return mailingAddr;
	}

	/**
	 * 设置邮寄地址
	 */
	public void setMailingAddr(String mailingAddr) {
		this.mailingAddr = mailingAddr;
	}

	/**
	 * 获得转出单序列号
	 */
	public String getSN() {
		return SN;
	}

	/**
	 * 设置转出单序列号
	 */
	public void setSN(String sN) {
		SN = sN;
	}

	/**
	 * 获得转出登记日期
	 */
	public Date getRegisterDate() {
		return registerDate;
	}

	/**
	 * 设置转出登记日期
	 */
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	/**
	 * 获得经办人编号
	 */
	public int getManagerUserID() {
		return managerUserID;
	}

	/**
	 * 设置经办人编号
	 */
	public void setManagerUserID(int managerUserID) {
		this.managerUserID = managerUserID;
	}

	/**
	 * 获得是否已经转出标志
	 */
	public boolean getMoveOutedFlag() {
		return moveOutedFlag;
	}

	/**
	 * 设置是否已经转出标志
	 */
	public void setMoveOutedFlag(boolean moveOutedFlag) {
		this.moveOutedFlag = moveOutedFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public MoveOutInfo(){
		
	}
	
	public MoveOutInfo(int id,int moveOutWay,int totalArchives,String companyAddr, String companyName,String phone,String mailingCompany,String receivePostcode,String mailingAddr,String SN,Date registerDate,int managerUserID,boolean moveOutedFlag,String remark)
	{
		// Table Name: MoveOutRegister
		// Columns List,Can Used in SELECT SQL: ID,MoveOutWay,TotalArchives,dwszd,dwmc,lxdh,dayjbm,yzbm,dayjdz,SN,RegisterDate,ManagerUserID,MoveOutedFlag,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:MoveOutWay,:TotalArchives,:dwszd,:dwmc,:lxdh,:dayjbm,:yzbm,:dayjdz,:SN,:RegisterDate,:ManagerUserID,:MoveOutedFlag,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,MoveOutWay=:MoveOutWay,TotalArchives=:TotalArchives,dwszd=:dwszd,dwmc=:dwmc,lxdh=:lxdh,dayjbm=:dayjbm,yzbm=:yzbm,dayjdz=:dayjdz,SN=:SN,RegisterDate=:RegisterDate,ManagerUserID=:ManagerUserID,MoveOutedFlag=:MoveOutedFlag,Remark=:Remark

		this.id = id;
		this.moveOutWay = moveOutWay;
		this.totalArchives = totalArchives;
		this.companyAddr = companyAddr;
		this.companyName = companyName;
		this.phone = phone;
		this.mailingCompany = mailingCompany;
		this.receivePostcode = receivePostcode;
		this.mailingAddr = mailingAddr;
		this.SN = SN;
		this.registerDate = registerDate;
		this.managerUserID = managerUserID;
		this.moveOutedFlag = moveOutedFlag;
		this.remark = remark;
	}
	
	/**
	* 从指定对象克隆，复制所有属性值
	* @param moveOutRegister 指定的对象源
	*/
	public void cloneFrom(MoveOutInfo moveOutInfo)
	{
		this.id = moveOutInfo.getId();
		this.moveOutWay = moveOutInfo.getMoveOutWay();
		this.totalArchives = moveOutInfo.getTotalArchives();
		this.companyAddr = moveOutInfo.getCompanyAddr();
		this.companyName = moveOutInfo.getCompanyName();
		this.phone = moveOutInfo.getPhone();
		this.mailingCompany = moveOutInfo.getMailingCompany();
		this.receivePostcode = moveOutInfo.getReceivePostcode();
		this.mailingAddr = moveOutInfo.getMailingAddr();
		this.SN = moveOutInfo.getSN();
		this.registerDate = moveOutInfo.getRegisterDate();
		this.managerUserID = moveOutInfo.getManagerUserID();
		this.moveOutedFlag = moveOutInfo.getMoveOutedFlag();
		this.remark = moveOutInfo.getRemark();
	}
}
