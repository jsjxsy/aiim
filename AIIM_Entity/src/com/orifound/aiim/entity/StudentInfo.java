package com.orifound.aiim.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentInfo {

	/**
	 * 学生档案内部序号
	 */
	private int NBXH;
	
	/**
	 * 归档时间
	 */
	private Date saveDate;
	
	/**
	 * 档案管理工作流
	 */
	private int workFlowStatus;

	/**
	 * 档案条码
	 */
	private String barcode;
	
	/**
	 * 档案类型编号
	 */
	private int archivesTypeID;
	
	/**
	 * 档号
	 */
	private String archivesID; 

	/**
	 * 题名
	 */
	private String title;
	
	/**
	 * 转出单编号
	 */
	private int moveOutRegID;

	/**
	 * 学号
	 */
	private String studentId;
	
	/**
	 * 学生姓名
	 */
	private String studentName;
	
	/**
	 * 性别
	 */
	private String sex;
	
	/**
	 * 入学年度
	 */
	private String startSchoolYear;
	
	/**
	 * 毕业年度
	 */
	private String finishSchoolYear;
	
	/**
	 * 学院
	 */
	private String college;
	
	/**
	 * 专业
	 */
	private String specialty;
	
	/**
	 * 专业
	 */
	private String grade;
	
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
	 * 备注
	 */
	private String reMark;

	/**
	 * 转出单序列号
	 */
	private String SN;
	
	/**
	 * 转出方式
	 */
	private int moveOutWay; 
	
	private boolean moveOutedFlag ;
	
	/**
	 * @return the moveOutedFlag
	 */
	public boolean getMoveOutedFlag() {
		return moveOutedFlag;
	}

	/**
	 * @param moveOutedFlag the moveOutedFlag to set
	 */
	public void setMoveOutedFlag(boolean moveOutedFlag) {
		this.moveOutedFlag = moveOutedFlag;
	}

	/**
	 * @return the moveOutWay
	 */
	public int getMoveOutWay() {
		return moveOutWay;
	}

	/**
	 * @param moveOutWay the moveOutWay to set
	 */
	public void setMoveOutWay(int moveOutWay) {
		this.moveOutWay = moveOutWay;
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
	 * Excel和数据库的映射关系对象集合
	 */
	private Map<String, ImportDataitemsMapping> importDataitemsMappings = new HashMap<String, ImportDataitemsMapping>();
	
	/**
	 * 卷内文件
	 */
	private List<StudentDocsInfo> studentDocsInfos = new ArrayList<StudentDocsInfo>();
	
	public StudentInfo(){
		
	}
	
	public StudentInfo(int nBXH, Date saveDate, int workFlowStatus, String barcode, int archivesTypeID, String archivesID, String title, int moveOutRegID, String studentId,
			String studentName, String sex, String startSchoolYear, String finishSchoolYear, String college, String specialty, String grade, String companyAddr, String companyName, String phone, String mailingCompany, String receivePostcode, String mailingAddr,
			String reMark) {
		this.NBXH = nBXH;
		this.saveDate = saveDate;
		this.barcode = barcode;
		this.archivesTypeID = archivesTypeID;
		this.archivesID = archivesID;
		this.title = title;
		this.moveOutRegID = moveOutRegID;
		this.studentId = studentId;
		this.studentName = studentName;
		this.sex = sex;
		this.startSchoolYear = startSchoolYear;
		this.finishSchoolYear = finishSchoolYear;
		this.college = college;
		this.specialty = specialty;
		this.grade = grade;
		this.companyAddr = companyAddr;
		this.companyName = companyName;
		this.phone = phone;
		this.mailingCompany = mailingCompany;
		this.receivePostcode = receivePostcode;
		this.mailingAddr = mailingAddr;
		this.reMark = reMark;
	}

	public StudentInfo(int nBXH, Date saveDate, int workFlowStatus, String barcode, int archivesTypeID, String archivesID, String title, int moveOutRegID, String studentId,
			String studentName, String sex, String startSchoolYear, String finishSchoolYear, String college, String specialty, String grade, String companyAddr, String companyName, String phone, String mailingCompany, String receivePostcode, String mailingAddr,
			String reMark,String SN,int moveOutWay,boolean moveOutedFlag) {
		this.NBXH = nBXH;
		this.saveDate = saveDate;
		this.workFlowStatus = workFlowStatus;
		this.barcode = barcode;
		this.archivesTypeID = archivesTypeID;
		this.archivesID = archivesID;
		this.title = title;
		this.moveOutRegID = moveOutRegID;
		this.studentId = studentId;
		this.studentName = studentName;
		this.sex = sex;
		this.startSchoolYear = startSchoolYear;
		this.finishSchoolYear = finishSchoolYear;
		this.college = college;
		this.specialty = specialty;
		this.grade = grade;
		this.companyAddr = companyAddr;
		this.companyName = companyName;
		this.phone = phone;
		this.mailingCompany = mailingCompany;
		this.receivePostcode = receivePostcode;
		this.mailingAddr = mailingAddr;
		this.reMark = reMark;
		this.SN = SN;
		this.moveOutWay = moveOutWay;
		this.moveOutedFlag = moveOutedFlag;
	}
	
	/**
	 * 获得内部序号
	 * @return
	 */
	public int getNBXH() {
		return NBXH;
	}

	/**
	 * 设置内部序号
	 * @param nBXH
	 */
	public void setNBXH(int nBXH) {
		NBXH = nBXH;
	}

	/**
	 * 获得归档时间
	 * @return
	 */
	public Date getSaveDate() {
		return saveDate;
	}

	/**
	 * 设置归档时间
	 * @param saveDate
	 */
	public void setSaveDate(Date saveDate) {
		this.saveDate = saveDate;
	}

	/**
	 * 获得工作流
	 * @return
	 */
	public int getWorkFlowStatus() {
		return workFlowStatus;
	}

	/**
	 * 设置工作流
	 * @param workFlowStatus
	 */
	public void setWorkFlowStatus(int workFlowStatus) {
		this.workFlowStatus = workFlowStatus;
	}

	/**
	 * 获得档案条码
	 * @return
	 */
	public String getBarcode() {
		if(this.barcode == null){
			try{
				return this.importDataitemsMappings.get("xh").getValue();
			}catch(Exception e){
				return barcode;
			}
		}
		return barcode;
	}

	/**
	 * 设置档案条码
	 * @param barcode
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	/**
	 * 获得档案所属档案分类编号
	 * @return
	 */
	public int getArchivesTypeID() {
		return archivesTypeID;
	}

	/**
	 * 设置所属档案分类标号
	 * @param archivesTypeID
	 */
	public void setArchivesTypeID(int archivesTypeID) {
		this.archivesTypeID = archivesTypeID;
	}

	/**
	 * 获得档号
	 * @return
	 */
	public String getArchivesID() {
		if(this.archivesID == null){
			try{
				return this.importDataitemsMappings.get("xh").getValue();
			}catch(Exception e){
				return archivesID;
			}
		}
		return archivesID;
	}

	/**
	 * 设置档号
	 * @param archivesID
	 */
	public void setArchivesID(String archivesID) {
		this.archivesID = archivesID;
	}

	/**
	 * 获得题名
	 * @return
	 */
	public String getTitle() {
		if(this.title == null){
			try{
				return this.importDataitemsMappings.get("xm").getValue();
			}catch(Exception e){
				return title;
			}
		}
		return title;
	}

	/**
	 * 设置题名
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获得所在转出单编号
	 * @return
	 */
	public int getMoveOutRegID() {
		return moveOutRegID;
	}

	/**
	 * 设置所在转出单编号
	 * @param moveOutRegID
	 */
	public void setMoveOutRegID(int moveOutRegID) {
		this.moveOutRegID = moveOutRegID;
	}

	/**
	 * 获得学号
	 * @return
	 */
	public String getStudentId() {
		if(this.studentId == null){
			try{
				return this.importDataitemsMappings.get("xh").getValue();
			}catch(Exception e){
				return studentId;
			}
		}
		return studentId;
	}

	/**
	 * 摄者学号
	 * @param studentId
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 * 获得姓名
	 * @return
	 */
	public String getStudentName() {
		if(this.studentName == null){
			try{
				return this.importDataitemsMappings.get("xm").getValue();
			}catch(Exception e){
				return studentName;
			}
		}
		return studentName;
	}

	/**
	 * 设置姓名
	 * @param studentName
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * 获得性别
	 * @return
	 */
	public String getSex() {
		if(this.sex == null){
			try{
				return this.importDataitemsMappings.get("xb").getValue();
			}catch(Exception e){
				return sex;
			}
		}
		return sex;
	}

	/**
	 * 设置性别
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 获得入学年度
	 * @return
	 */
	public String getStartSchoolYear() {
		if(this.startSchoolYear == null){
			try{
				return this.importDataitemsMappings.get("rxnd").getValue();
			}catch(Exception e){
				return startSchoolYear;
			}
		}
		return startSchoolYear;
	}

	/**
	 * 设置入学年度
	 * @param startSchoolYear
	 */
	public void setStartSchoolYear(String startSchoolYear) {
		this.startSchoolYear = startSchoolYear;
	}

	/**
	 * 获得毕业时间
	 * @return
	 */
	public String getFinishSchoolYear() {
		if(this.finishSchoolYear == null){
			try{
				return this.importDataitemsMappings.get("bynd").getValue();
			}catch(Exception e){
				return finishSchoolYear;
			}
		}
		return finishSchoolYear;
	}

	/**
	 * 设置毕业时间
	 * @param finishSchoolYear
	 */
	public void setFinishSchoolYear(String finishSchoolYear) {
		this.finishSchoolYear = finishSchoolYear;
	}

	/**
	 * 获得学院名称
	 * @return
	 */
	public String getCollege() {
		if(this.college == null){
			try{
				return this.importDataitemsMappings.get("xy").getValue();
			}catch(Exception e){
				return college;
			}
		}
		return college;
	}

	/**
	 * 设置学院名称
	 * @param college
	 */
	public void setCollege(String college) {
		this.college = college;
	}

	/**
	 * 获得专业名称
	 * @return
	 */
	public String getSpecialty() {
		if(this.specialty == null){
			try{
				return this.importDataitemsMappings.get("zy").getValue();
			}catch(Exception e){
				return specialty;
			}
		}
		return specialty;
	}

	/**
	 * 设置专业名称
	 * @param specialty
	 */
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	/**
	 * 获得班级
	 * @return
	 */
	public String getGrade() {
		if(this.grade == null){
			try{
				return this.importDataitemsMappings.get("bj").getValue();
			}catch(Exception e){
				return grade;
			}
		}
		return grade;
	}

	/**
	 * 设置班级
	 * @param grade
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * 获得转出单位地址
	 * @return
	 */
	public String getCompanyAddr() {
		if(this.companyAddr == null){
			try{
				return this.importDataitemsMappings.get("dwszd").getValue();
			}catch(Exception e){
				return companyAddr;
			}
		}
		return companyAddr;
	}

	/**
	 * 设置转出单位地址
	 * @param companyAddr
	 */
	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}

	/**
	 * 获得转出单位名称
	 * @return
	 */
	public String getCompanyName() {
		if(this.companyName == null){
			try{
			    return this.importDataitemsMappings.get("dwmc").getValue();
			}catch(Exception e){
				return companyName;
			}
		}
		return companyName;
	}

	/**
	 * 设置转出单位名称
	 * @param companyName
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * 获得单位电话
	 * @return
	 */
	public String getPhone() {
		if(this.phone == null){
			try{
			    return this.importDataitemsMappings.get("lxdh").getValue();
			}catch(Exception e){
				return phone;
			}
		}
		return phone;
	}

	/**
	 * 设置单位电话
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获得邮寄单位名称
	 * @return
	 */
	public String getMailingCompany() {
		if(this.mailingCompany == null){
			try{
			    return this.importDataitemsMappings.get("dayjbm").getValue();
			}catch(Exception e){
				return mailingCompany;
			}
		}
		return mailingCompany;
	}

	/**
	 * 设置邮寄单位
	 * @param mailingCompany
	 */
	public void setMailingCompany(String mailingCompany) {
		this.mailingCompany = mailingCompany;
	}

	/**
	 * 获得接收地邮编
	 * @return
	 */
	public String getReceivePostcode() {
		if(this.receivePostcode == null){
			try{
				return this.importDataitemsMappings.get("yzbm").getValue();
			}catch(Exception e){
				return receivePostcode;
			}
		}
		return receivePostcode;
	}

	/**
	 * 设置接收地邮编
	 * @param receivePostcode
	 */
	public void setReceivePostcode(String receivePostcode) {
		this.receivePostcode = receivePostcode;
	}

	/**
	 * 获得邮寄地址
	 * @return
	 */
	public String getMailingAddr() {
		if(this.mailingAddr == null){
			try{
				return this.importDataitemsMappings.get("dayjdz").getValue();
			}catch(Exception e){
				return mailingAddr;
			}
		}
		return mailingAddr;
	}

	/**
	 * 设置邮寄地址
	 * @param mailingAddr
	 */
	public void setMailingAddr(String mailingAddr) {
		this.mailingAddr = mailingAddr;
	}

	/**
	 * 获得备注
	 * @return
	 */
	public String getReMark() {
		return reMark;
	}

	/**
	 * 设置备注
	 * @param reMark
	 */
	public void setReMark(String reMark) {
		this.reMark = reMark;
	}

	/**
	 * Excel和数据库的映射关系对象集合
	 */
	public Map<String, ImportDataitemsMapping> getImportDataitemsMappings() {
		return importDataitemsMappings;
	}

	/**
	 * Excel和数据库的映射关系对象集合
	 */
	public void setImportDataitemsMappings(Map<String, ImportDataitemsMapping> importDataitemsMappings) {
		this.importDataitemsMappings = importDataitemsMappings;
	}

	public List<StudentDocsInfo> getStudentDocsInfos() {
		return studentDocsInfos;
	}

	public void setStudentDocsInfos(List<StudentDocsInfo> studentDocsInfos) {
		this.studentDocsInfos = studentDocsInfos;
	}
	
	/**
	* 从指定对象克隆，复制所有属性值
	* @param studentInfo 指定的对象源
	*/
	public void cloneFrom(StudentInfo studentInfo)
	{
		this.NBXH = studentInfo.getNBXH();
		this.saveDate = studentInfo.getSaveDate();
		this.workFlowStatus = studentInfo.getWorkFlowStatus();
		this.barcode = studentInfo.getBarcode();
		this.archivesTypeID = studentInfo.getArchivesTypeID();
		this.archivesID = studentInfo.getArchivesID();
		this.title = studentInfo.getTitle();
		this.moveOutRegID = studentInfo.getMoveOutRegID();
		this.studentId = studentInfo.getStudentId();
		this.studentName = studentInfo.getStudentName();
		this.sex = studentInfo.getSex();
		this.startSchoolYear = studentInfo.getStartSchoolYear();
		this.finishSchoolYear = studentInfo.getFinishSchoolYear();
		this.college = studentInfo.getCollege();
		this.specialty = studentInfo.getSpecialty();
		this.grade = studentInfo.getGrade();
		this.companyAddr = studentInfo.getCompanyAddr();
		this.companyName = studentInfo.getCompanyName();
		this.phone = studentInfo.getPhone();
		this.mailingCompany = studentInfo.getMailingCompany();
		this.receivePostcode = studentInfo.getReceivePostcode();
		this.mailingAddr = studentInfo.getMailingAddr();
		this.reMark = studentInfo.getReMark();
	}

}
