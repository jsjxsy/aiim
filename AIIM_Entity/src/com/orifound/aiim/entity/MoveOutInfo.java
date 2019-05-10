package com.orifound.aiim.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ����ת����Ϣ��
 * @author Administrator
 *
 */
public class MoveOutInfo {

	/**
	 * ת���Ǽ���ʱ���
	 */
	private int id;
	
	/**
	 * ת����ʽ
	 */
	private int moveOutWay;
	
	/**
	 * ת������������
	 */
	private int totalArchives;
	
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
	 * ת�������к�
	 */
	private String SN;
	
	/**
	 * ת���Ǽ�����
	 */
	private Date registerDate;
	
	/**
	 * �����˱��
	 */
	private int managerUserID;
	
	/**
	 * �Ƿ��Ѿ�ת����־
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
	 * ��ע
	 */
	private String remark;

	/**
	 * ת��������Ա��Ϣ
	 */
	private List<StudentInfo> studentInfos = new ArrayList<StudentInfo>();
	
	/**
	 * ��õ�����Ա��Ϣ
	 */
	public List<StudentInfo> getStudentInfos() {
		return studentInfos;
	}

	/**
	 * ���õ�����Ա��Ϣ
	 */
	public void setStudentInfos(List<StudentInfo> studentInfos) {
		this.studentInfos = studentInfos;
	}

	/**
	 * ���ת���Ǽ���ʱ���
	 */
	public int getId() {
		return id;
	}

	/**
	 * ����ת���Ǽ���ʱ���
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * ���ת����ʽ
	 */
	public int getMoveOutWay() {
		return moveOutWay;
	}

	/**
	 * ����ת����ʽ
	 */
	public void setMoveOutWay(int moveOutWay) {
		this.moveOutWay = moveOutWay;
	}

	/**
	 * ���ת������������
	 */
	public int getTotalArchives() {
		return totalArchives;
	}

	/**
	 * ���õ���������
	 */
	public void setTotalArchives(int totalArchives) {
		this.totalArchives = totalArchives;
	}

	/**
	 * ��õ�λ���ڵ�
	 */
	public String getCompanyAddr() {
		return companyAddr;
	}

	/**
	 * ���õ�λ���ڵ�
	 */
	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}

	/**
	 * ��õ�λ����
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * ���õ�λ����
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * �����ϵ�绰
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * ������ϵ�绰
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * ����ʼĲ���
	 */
	public String getMailingCompany() {
		return mailingCompany;
	}

	/**
	 * �����ʼĲ���
	 */
	public void setMailingCompany(String mailingCompany) {
		this.mailingCompany = mailingCompany;
	}

	/**
	 * ��ý��յ��ʱ�
	 */
	public String getReceivePostcode() {
		return receivePostcode;
	}

	/**
	 * ���ý��յ��ʱ�
	 */
	public void setReceivePostcode(String receivePostcode) {
		this.receivePostcode = receivePostcode;
	}

	/**
	 * ����ʼĵ�ַ
	 */
	public String getMailingAddr() {
		return mailingAddr;
	}

	/**
	 * �����ʼĵ�ַ
	 */
	public void setMailingAddr(String mailingAddr) {
		this.mailingAddr = mailingAddr;
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
	 * ���ת���Ǽ�����
	 */
	public Date getRegisterDate() {
		return registerDate;
	}

	/**
	 * ����ת���Ǽ�����
	 */
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	/**
	 * ��þ����˱��
	 */
	public int getManagerUserID() {
		return managerUserID;
	}

	/**
	 * ���þ����˱��
	 */
	public void setManagerUserID(int managerUserID) {
		this.managerUserID = managerUserID;
	}

	/**
	 * ����Ƿ��Ѿ�ת����־
	 */
	public boolean getMoveOutedFlag() {
		return moveOutedFlag;
	}

	/**
	 * �����Ƿ��Ѿ�ת����־
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
	* ��ָ�������¡��������������ֵ
	* @param moveOutRegister ָ���Ķ���Դ
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
