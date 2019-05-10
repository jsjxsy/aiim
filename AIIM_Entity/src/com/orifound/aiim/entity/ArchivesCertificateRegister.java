package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * ������֤�ǼǱ��ʵ����
 */
public class ArchivesCertificateRegister 
{
    /**
     * ���캯��
     */
    public ArchivesCertificateRegister()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��֤ҵ��ǼǱ��
	* @param personID �����˱��
	* @param shouldCharge Ӧ�ɽ��
	* @param realCharge ʵ���շ�
	* @param registerDate �Ǽ�����
	* @param invoiceSN ��Ʊ����
	* @param managerUserID �����˱��
	* @param remark ��ע
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
	* ���ֶβ����Ĺ��캯��
	* @param iD ��֤ҵ��ǼǱ��
	* @param personID �����˱��
	* @param shouldCharge Ӧ�ɽ��
	* @param realCharge ʵ���շ�
	* @param registerDate �Ǽ�����
	* @param invoiceSN ��Ʊ����
	* @param managerUserID �����˱��
	* @param remark ��ע
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
	* ���ֶβ����Ĺ��캯��
	* @param iD ��֤ҵ��ǼǱ��
	* @param personID �����˱��
	* @param shouldCharge Ӧ�ɽ��
	* @param realCharge ʵ���շ�
	* @param registerDate �Ǽ�����
	* @param invoiceSN ��Ʊ����
	* @param managerUserID �����˱��
	* @param remark ��ע
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
	* ���ֶβ����Ĺ��캯��
	* @param shouldCharge Ӧ�ɽ��
	* @param realCharge ʵ���շ�
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
	 * ��Ա�����ڼ����еĹؼ���
	 */
	private Object keyInCol=null;

	/**
	 * ��ȡ����ֵ����Ա�����ڼ����еĹؼ���
	 * @return ��Ա�����ڼ����еĹؼ���
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}

	/**
	 * ��������ֵ����Ա�����ڼ����еĹؼ���
	 * @param keyInCol ��Ա�����ڼ����еĹؼ���
	 */
	public void setKeyInCol(Object keyInCol)
	{
		this.keyInCol = keyInCol;
	}

	/**
	 * ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	private Object tag=null;

	/**
	 * ��ȡ����ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * @return ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public Object getTag()
	{
		return tag;
	}

	/**
	 * ��������ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * @param tag ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	/**
	 * ��֤ҵ��ǼǱ��
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ����֤ҵ��ǼǱ��
	 * @return ��֤ҵ��ǼǱ��
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ����֤ҵ��ǼǱ��
	 * @param iD ��֤ҵ��ǼǱ��
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * �����˱��
	 */
	private int personID=0;

	/**
	 * ��ȡ����ֵ�������˱��
	 * @return �����˱��
	 */
	public int getPersonID()
	{
		return personID;
	}

	/**
	 * ��������ֵ�������˱��
	 * @param personID �����˱��
	 */
	public void setPersonID(int personID)
	{
		this.personID = personID;
	}

	/**
	 * Ӧ�ɽ��
	 */
	private double shouldCharge=0;

	/**
	 * ��ȡ����ֵ��Ӧ�ɽ��
	 * @return Ӧ�ɽ��
	 */
	public double getShouldCharge()
	{
		return shouldCharge;
	}

	/**
	 * ��������ֵ��Ӧ�ɽ��
	 * @param shouldCharge Ӧ�ɽ��
	 */
	public void setShouldCharge(double shouldCharge)
	{
		this.shouldCharge = shouldCharge;
	}

	/**
	 * ʵ���շ�
	 */
	private double realCharge=0;

	/**
	 * ��ȡ����ֵ��ʵ���շ�
	 * @return ʵ���շ�
	 */
	public double getRealCharge()
	{
		return realCharge;
	}

	/**
	 * ��������ֵ��ʵ���շ�
	 * @param realCharge ʵ���շ�
	 */
	public void setRealCharge(double realCharge)
	{
		this.realCharge = realCharge;
	}

	/**
	 * �Ǽ�����
	 */
	private Date registerDate;

	/**
	 * ��ȡ����ֵ���Ǽ�����
	 * @return �Ǽ�����
	 */
	public Date getRegisterDate()
	{
		return registerDate;
	}

	/**
	 * ��������ֵ���Ǽ�����
	 * @param registerDate �Ǽ�����
	 */
	public void setRegisterDate(Date registerDate)
	{
		this.registerDate = registerDate;
	}

	/**
	 * ��Ʊ����
	 */
	private String invoiceSN=null;

	/**
	 * ��ȡ����ֵ����Ʊ����
	 * @return ��Ʊ����
	 */
	public String getInvoiceSN()
	{
		return invoiceSN;
	}

	/**
	 * ��������ֵ����Ʊ����
	 * @param invoiceSN ��Ʊ����
	 */
	public void setInvoiceSN(String invoiceSN)
	{
		this.invoiceSN = invoiceSN;
	}

	/**
	 * �����˱��
	 */
	private int managerUserID=0;

	/**
	 * ��ȡ����ֵ�������˱��
	 * @return �����˱��
	 */
	public int getManagerUserID()
	{
		return managerUserID;
	}

	/**
	 * ��������ֵ�������˱��
	 * @param managerUserID �����˱��
	 */
	public void setManagerUserID(int managerUserID)
	{
		this.managerUserID = managerUserID;
	}
	
	/**
	 * ��ʵ��
	 */
	private String realName = null;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * ��ע
	 */
	private String remark=null;

	/**
	 * ��ȡ����ֵ����ע
	 * @return ��ע
	 */
	public String getRemark()
	{
		return remark;
	}

	/**
	 * ��������ֵ����ע
	 * @param remark ��ע
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	
	/**
	 * ����������
	 */
	private String personName;
	
	/**
	 * ����������
	 */
	private String managerUserName;
	
	/**
	 * ֤����
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
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ArchivesCertificateRegister clone()
	{
		ArchivesCertificateRegister item = new ArchivesCertificateRegister(iD,personID,shouldCharge,realCharge,registerDate,invoiceSN,managerUserID,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param archivesCertificateRegister ָ���Ķ���Դ
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



