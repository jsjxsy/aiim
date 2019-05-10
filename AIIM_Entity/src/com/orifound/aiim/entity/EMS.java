package com.orifound.aiim.entity;

public class EMS {

	/**
	 * �ռľ�
	 */
	private String acceptanceOffice;
	
	/**
	 * �ռ�������
	 */
	private String acceptancePersonName;
	
	/**
	 * �ļ�������
	 */
	private String fromPersonName;
	
	/**
	 * �ļ��˵绰
	 */
	private String fromPersonPhone;
	
	/**
	 * �ļ���λ����
	 */
	private String fromCompanyName;
	
	/**
	 * �ļ���λ��ַ
	 */
	private String fromCompanyAddr;
	
	/**
	 * �ĳ����ʱ�
	 */
	private String fromPostCode;
	
	/**
	 * �ڼ�Ʒ��
	 */
	private String contents; 

	/**
	 * �ڼ�����
	 */
	private int contentTol;
	
	/**
	 * ������ǩ��
	 */
	private String remailPersonName;
		
	/**
	 * �ռ�������
	 */
	private String receivePersonName;
	
	/**
	 * �ռ��˵绰
	 */
	private String receivePersonPhone;
	
	/**
	 * �ռ���λ����
	 */
	private String receiveCompanyName;
	
	/**
	 * �ռ���λ��ַ
	 */
	private String receiveCompanyAddr;
	
	/**
	 * �ռ���λ���ڳ���
	 */
	private String receiveCityName;
	
	/**
	 * �ռ���λ�ʱ�
	 */
	private String receivePostCode;

	public EMS(){}
	
	public EMS(String acceptanceOffice,String acceptancePersonName,String fromPersonName,String fromPersonPhone,String fromCompanyName, String fromCompanyAddr, String fromPostCode, 
			String contents,int contentTol,String remailPersonName,String receivePersonName,String receivePersonPhone, 
			String receiveCompanyName,String receiveCompanyAddr, String receiveCityName,String receivePostCode){
		this.acceptanceOffice = acceptanceOffice;
		this.acceptancePersonName = acceptancePersonName;
		this.fromPersonName = fromPersonName;
		this.fromPersonPhone = fromPersonPhone;
		this.fromCompanyName = fromCompanyName;
		this.fromCompanyAddr = fromCompanyAddr;
		this.fromPostCode = fromPostCode;
		this.contents = contents;
		this.contentTol = contentTol;
		this.remailPersonName = remailPersonName;
		
		this.receivePersonName = receivePersonName;
		this.receivePersonPhone = receivePersonPhone;
		this.receiveCompanyName = receiveCompanyName;
		this.receiveCompanyAddr = receiveCompanyAddr;
		this.receiveCityName = receiveCityName;
		this.receivePostCode = receivePostCode;
	}
	
	public String getAcceptanceOffice() {
		return acceptanceOffice;
	}

	public void setAcceptanceOffice(String acceptanceOffice) {
		this.acceptanceOffice = acceptanceOffice;
	}
	
	public String getAcceptancePersonName() {
		return acceptancePersonName;
	}

	public void setAcceptancePersonName(String acceptancePersonName) {
		this.acceptancePersonName = acceptancePersonName;
	}

	public String getFromPersonName() {
		return fromPersonName;
	}

	public void setFromPersonName(String fromPersonName) {
		this.fromPersonName = fromPersonName;
	}

	public String getFromPersonPhone() {
		return fromPersonPhone;
	}

	public void setFromPersonPhone(String fromPersonPhone) {
		this.fromPersonPhone = fromPersonPhone;
	}

	public String getFromCompanyName() {
		return fromCompanyName;
	}

	public void setFromCompanyName(String fromCompanyName) {
		this.fromCompanyName = fromCompanyName;
	}

	public String getFromCompanyAddr() {
		return fromCompanyAddr;
	}

	public void setFromCompanyAddr(String fromCompanyAddr) {
		this.fromCompanyAddr = fromCompanyAddr;
	}

	public String getFromPostCode() {
		return fromPostCode;
	}

	public void setFromPostCode(String fromPostCode) {
		this.fromPostCode = fromPostCode;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getContentTol() {
		return contentTol;
	}

	public void setContentTol(int contentTol) {
		this.contentTol = contentTol;
	}

	public String getRemailPersonName() {
		return remailPersonName;
	}

	public void setRemailPersonName(String remailPersonName) {
		this.remailPersonName = remailPersonName;
	}

	public String getReceivePersonName() {
		return receivePersonName;
	}

	public void setReceivePersonName(String receivePersonName) {
		this.receivePersonName = receivePersonName;
	}

	public String getReceivePersonPhone() {
		return receivePersonPhone;
	}

	public void setReceivePersonPhone(String receivePersonPhone) {
		this.receivePersonPhone = receivePersonPhone;
	}

	public String getReceiveCompanyName() {
		return receiveCompanyName;
	}

	public void setReceiveCompanyName(String receiveCompanyName) {
		this.receiveCompanyName = receiveCompanyName;
	}

	public String getReceiveCompanyAddr() {
		return receiveCompanyAddr;
	}

	public void setReceiveCompanyAddr(String receiveCompanyAddr) {
		this.receiveCompanyAddr = receiveCompanyAddr;
	}

	public String getReceiveCityName() {
		return receiveCityName;
	}

	public void setReceiveCityName(String receiveCityName) {
		this.receiveCityName = receiveCityName;
	}

	public String getReceivePostCode() {
		return receivePostCode;
	}

	public void setReceivePostCode(String receivePostCode) {
		this.receivePostCode = receivePostCode;
	}	
}
