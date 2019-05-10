package com.orifound.aiim.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentInfo {

	/**
	 * ѧ�������ڲ����
	 */
	private int NBXH;
	
	/**
	 * �鵵ʱ��
	 */
	private Date saveDate;
	
	/**
	 * ������������
	 */
	private int workFlowStatus;

	/**
	 * ��������
	 */
	private String barcode;
	
	/**
	 * �������ͱ��
	 */
	private int archivesTypeID;
	
	/**
	 * ����
	 */
	private String archivesID; 

	/**
	 * ����
	 */
	private String title;
	
	/**
	 * ת�������
	 */
	private int moveOutRegID;

	/**
	 * ѧ��
	 */
	private String studentId;
	
	/**
	 * ѧ������
	 */
	private String studentName;
	
	/**
	 * �Ա�
	 */
	private String sex;
	
	/**
	 * ��ѧ���
	 */
	private String startSchoolYear;
	
	/**
	 * ��ҵ���
	 */
	private String finishSchoolYear;
	
	/**
	 * ѧԺ
	 */
	private String college;
	
	/**
	 * רҵ
	 */
	private String specialty;
	
	/**
	 * רҵ
	 */
	private String grade;
	
	/**
	 * ��λ���ڵ�
	 */
	private String companyAddr;
	
	/**
	 * ��λ����
	 */
	private String companyName;
	
	/**
	 * ��ϵ�绰
	 */
	private String phone;
	
	/**
	 * �ʼĲ���
	 */
	private String mailingCompany;
	
	/**
	 * ���յ��ʱ�
	 */
	private String receivePostcode; 
	
	/**
	 * �ʼĵ�ַ
	 */
	private String mailingAddr;
	
	/**
	 * ��ע
	 */
	private String reMark;

	/**
	 * ת�������к�
	 */
	private String SN;
	
	/**
	 * ת����ʽ
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
	 * ���ת�������к�
	 */
	public String getSN() {
		return SN;
	}

	/**
	 * ����ת�������к�
	 */
	public void setSN(String sN) {
		SN = sN;
	}

	/**
	 * Excel�����ݿ��ӳ���ϵ���󼯺�
	 */
	private Map<String, ImportDataitemsMapping> importDataitemsMappings = new HashMap<String, ImportDataitemsMapping>();
	
	/**
	 * �����ļ�
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
	 * ����ڲ����
	 * @return
	 */
	public int getNBXH() {
		return NBXH;
	}

	/**
	 * �����ڲ����
	 * @param nBXH
	 */
	public void setNBXH(int nBXH) {
		NBXH = nBXH;
	}

	/**
	 * ��ù鵵ʱ��
	 * @return
	 */
	public Date getSaveDate() {
		return saveDate;
	}

	/**
	 * ���ù鵵ʱ��
	 * @param saveDate
	 */
	public void setSaveDate(Date saveDate) {
		this.saveDate = saveDate;
	}

	/**
	 * ��ù�����
	 * @return
	 */
	public int getWorkFlowStatus() {
		return workFlowStatus;
	}

	/**
	 * ���ù�����
	 * @param workFlowStatus
	 */
	public void setWorkFlowStatus(int workFlowStatus) {
		this.workFlowStatus = workFlowStatus;
	}

	/**
	 * ��õ�������
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
	 * ���õ�������
	 * @param barcode
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	/**
	 * ��õ�����������������
	 * @return
	 */
	public int getArchivesTypeID() {
		return archivesTypeID;
	}

	/**
	 * ������������������
	 * @param archivesTypeID
	 */
	public void setArchivesTypeID(int archivesTypeID) {
		this.archivesTypeID = archivesTypeID;
	}

	/**
	 * ��õ���
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
	 * ���õ���
	 * @param archivesID
	 */
	public void setArchivesID(String archivesID) {
		this.archivesID = archivesID;
	}

	/**
	 * �������
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
	 * ��������
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * �������ת�������
	 * @return
	 */
	public int getMoveOutRegID() {
		return moveOutRegID;
	}

	/**
	 * ��������ת�������
	 * @param moveOutRegID
	 */
	public void setMoveOutRegID(int moveOutRegID) {
		this.moveOutRegID = moveOutRegID;
	}

	/**
	 * ���ѧ��
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
	 * ����ѧ��
	 * @param studentId
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 * �������
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
	 * ��������
	 * @param studentName
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * ����Ա�
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
	 * �����Ա�
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * �����ѧ���
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
	 * ������ѧ���
	 * @param startSchoolYear
	 */
	public void setStartSchoolYear(String startSchoolYear) {
		this.startSchoolYear = startSchoolYear;
	}

	/**
	 * ��ñ�ҵʱ��
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
	 * ���ñ�ҵʱ��
	 * @param finishSchoolYear
	 */
	public void setFinishSchoolYear(String finishSchoolYear) {
		this.finishSchoolYear = finishSchoolYear;
	}

	/**
	 * ���ѧԺ����
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
	 * ����ѧԺ����
	 * @param college
	 */
	public void setCollege(String college) {
		this.college = college;
	}

	/**
	 * ���רҵ����
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
	 * ����רҵ����
	 * @param specialty
	 */
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	/**
	 * ��ð༶
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
	 * ���ð༶
	 * @param grade
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * ���ת����λ��ַ
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
	 * ����ת����λ��ַ
	 * @param companyAddr
	 */
	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}

	/**
	 * ���ת����λ����
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
	 * ����ת����λ����
	 * @param companyName
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * ��õ�λ�绰
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
	 * ���õ�λ�绰
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * ����ʼĵ�λ����
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
	 * �����ʼĵ�λ
	 * @param mailingCompany
	 */
	public void setMailingCompany(String mailingCompany) {
		this.mailingCompany = mailingCompany;
	}

	/**
	 * ��ý��յ��ʱ�
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
	 * ���ý��յ��ʱ�
	 * @param receivePostcode
	 */
	public void setReceivePostcode(String receivePostcode) {
		this.receivePostcode = receivePostcode;
	}

	/**
	 * ����ʼĵ�ַ
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
	 * �����ʼĵ�ַ
	 * @param mailingAddr
	 */
	public void setMailingAddr(String mailingAddr) {
		this.mailingAddr = mailingAddr;
	}

	/**
	 * ��ñ�ע
	 * @return
	 */
	public String getReMark() {
		return reMark;
	}

	/**
	 * ���ñ�ע
	 * @param reMark
	 */
	public void setReMark(String reMark) {
		this.reMark = reMark;
	}

	/**
	 * Excel�����ݿ��ӳ���ϵ���󼯺�
	 */
	public Map<String, ImportDataitemsMapping> getImportDataitemsMappings() {
		return importDataitemsMappings;
	}

	/**
	 * Excel�����ݿ��ӳ���ϵ���󼯺�
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
	* ��ָ�������¡��������������ֵ
	* @param studentInfo ָ���Ķ���Դ
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
