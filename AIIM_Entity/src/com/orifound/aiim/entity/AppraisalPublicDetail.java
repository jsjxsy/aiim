package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * ��������/���ż�����ϸ������ʵ����
 */
public class AppraisalPublicDetail
{
    /**
     * ���캯��
     */
    public AppraisalPublicDetail()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param publicFlag ���ż�����־
	* @param archivesTypeID ����������
	* @param nBXH �����ڲ����
	* @param archivesID ����
	* @param title ����
	* @param appraisalReason ����ԭ��
	* @param appraisalDate ��������
	* @param appraisalOpinion �������
	* @param appraisalPersion ������
	* @param oldSecrecyID ����ǰ�ĵ����ܼ�
	* @param newSecrecyID ������ĵ����ܼ����
	* @param registerDate �Ǽ�����
	* @param managerUserID �����˱��
	* @param remark ��ע
	*/
	public AppraisalPublicDetail(int iD,boolean publicFlag,int archivesTypeID,int nBXH,String archivesID,String title,String appraisalReason,Date appraisalDate,String appraisalOpinion,String appraisalPersion,int oldSecrecyID,int newSecrecyID,Date registerDate,int managerUserID,String remark)
	{
		// Table Name: AppraisalPublicDetails
		// Columns List,Can Used in SELECT SQL: ID,PublicFlag,ArchivesTypeID,NBXH,ArchivesID,Title,AppraisalReason,AppraisalDate,AppraisalOpinion,AppraisalPersion,OldSecrecyID,NewSecrecyID,RegisterDate,ManagerUserID,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:PublicFlag,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:AppraisalReason,:AppraisalDate,:AppraisalOpinion,:AppraisalPersion,:OldSecrecyID,:NewSecrecyID,:RegisterDate,:ManagerUserID,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,PublicFlag=:PublicFlag,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,AppraisalReason=:AppraisalReason,AppraisalDate=:AppraisalDate,AppraisalOpinion=:AppraisalOpinion,AppraisalPersion=:AppraisalPersion,OldSecrecyID=:OldSecrecyID,NewSecrecyID=:NewSecrecyID,RegisterDate=:RegisterDate,ManagerUserID=:ManagerUserID,Remark=:Remark

		this(publicFlag, archivesTypeID, nBXH, archivesID, title, appraisalReason, appraisalDate, appraisalOpinion, appraisalPersion, oldSecrecyID, newSecrecyID, managerUserID, remark);
		this.iD = iD;
		this.registerDate = registerDate;
	}
	
	
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param publicFlag ���ż�����־
	* @param archivesTypeID ����������
	* @param nBXH �����ڲ����
	* @param archivesID ����
	* @param title ����
	* @param appraisalReason ����ԭ��
	* @param appraisalDate ��������
	* @param appraisalOpinion �������
	* @param appraisalPersion ������
	* @param oldSecrecyID ����ǰ�ĵ����ܼ�
	* @param newSecrecyID ������ĵ����ܼ����
	* @param registerDate �Ǽ�����
	* @param managerUserID �����˱��
	* @param remark ��ע
	* @param archivesTypeName ������������
	* @param formationDepartment �����γɲ������� 
	*/
	public AppraisalPublicDetail(int iD,boolean publicFlag,int archivesTypeID,int nBXH,String archivesID,String title,String appraisalReason,Date appraisalDate,String appraisalOpinion,String appraisalPersion,int oldSecrecyID,int newSecrecyID,Date registerDate,int managerUserID,String remark, String archivesTypeName, String formationDepartment, String oldSecrecyName)
	{
		// Table Name: AppraisalPublicDetails
		// Columns List,Can Used in SELECT SQL: ID,PublicFlag,ArchivesTypeID,NBXH,ArchivesID,Title,AppraisalReason,AppraisalDate,AppraisalOpinion,AppraisalPersion,OldSecrecyID,NewSecrecyID,RegisterDate,ManagerUserID,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:PublicFlag,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:AppraisalReason,:AppraisalDate,:AppraisalOpinion,:AppraisalPersion,:OldSecrecyID,:NewSecrecyID,:RegisterDate,:ManagerUserID,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,PublicFlag=:PublicFlag,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,AppraisalReason=:AppraisalReason,AppraisalDate=:AppraisalDate,AppraisalOpinion=:AppraisalOpinion,AppraisalPersion=:AppraisalPersion,OldSecrecyID=:OldSecrecyID,NewSecrecyID=:NewSecrecyID,RegisterDate=:RegisterDate,ManagerUserID=:ManagerUserID,Remark=:Remark

		this(publicFlag, archivesTypeID, nBXH, archivesID, title, appraisalReason, appraisalDate, appraisalOpinion, appraisalPersion, oldSecrecyID, newSecrecyID, managerUserID, remark);
		this.iD = iD;
		this.registerDate = registerDate;
		this.archivesTypeName = archivesTypeName;
		this.formationDepartment = formationDepartment;
		this.oldSecrecyName = oldSecrecyName;
	}
	

	/**
	* ���ֶβ����Ĺ��캯��
	* @param publicFlag ���ż�����־
	* @param archivesTypeID ����������
	* @param nBXH �����ڲ����
	* @param archivesID ����
	* @param title ����
	* @param appraisalReason ����ԭ��
	* @param appraisalDate ��������
	* @param appraisalOpinion �������
	* @param appraisalPersion ������
	* @param oldSecrecyID ����ǰ�ĵ����ܼ�
	* @param newSecrecyID ������ĵ����ܼ����
	* @param managerUserID �����˱��
	* @param remark ��ע
	*/
	public AppraisalPublicDetail(boolean publicFlag,int archivesTypeID,int nBXH,String archivesID,String title,String appraisalReason,Date appraisalDate,String appraisalOpinion,String appraisalPersion,int oldSecrecyID,int newSecrecyID,int managerUserID,String remark)
	{
		this.publicFlag = publicFlag;
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.archivesID = archivesID;
		this.title = title;
		this.appraisalReason = appraisalReason;
		this.appraisalDate = appraisalDate;
		this.appraisalOpinion = appraisalOpinion;
		this.appraisalPersion = appraisalPersion;
		this.oldSecrecyID = oldSecrecyID;
		this.newSecrecyID = newSecrecyID;
		this.managerUserID = managerUserID;
		this.remark = remark;
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
	 * ���
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ�����
	 * @return ���
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ�����
	 * @param iD ���
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ���ż�����־
	 */
	private boolean publicFlag=false;

	/**
	 * ��ȡ����ֵ�����ż�����־
	 * @return ���ż�����־
	 */
	public boolean getPublicFlag()
	{
		return publicFlag;
	}

	/**
	 * ��������ֵ�����ż�����־
	 * @param publicFlag ���ż�����־
	 */
	public void setPublicFlag(boolean publicFlag)
	{
		this.publicFlag = publicFlag;
	}

	/**
	 * ����������
	 */
	private int archivesTypeID=0;

	/**
	 * ��ȡ����ֵ������������
	 * @return ����������
	 */
	public int getArchivesTypeID()
	{
		return archivesTypeID;
	}

	/**
	 * ��������ֵ������������
	 * @param archivesTypeID ����������
	 */
	public void setArchivesTypeID(int archivesTypeID)
	{
		this.archivesTypeID = archivesTypeID;
	}

	/**
	 * �����ڲ����
	 */
	private int nBXH=0;

	/**
	 * ��ȡ����ֵ�������ڲ����
	 * @return �����ڲ����
	 */
	public int getNBXH()
	{
		return nBXH;
	}

	/**
	 * ��������ֵ�������ڲ����
	 * @param nBXH �����ڲ����
	 */
	public void setNBXH(int nBXH)
	{
		this.nBXH = nBXH;
	}

	/**
	 * ����
	 */
	private String archivesID=null;

	/**
	 * ��ȡ����ֵ������
	 * @return ����
	 */
	public String getArchivesID()
	{
		return archivesID;
	}

	/**
	 * ��������ֵ������
	 * @param archivesID ����
	 */
	public void setArchivesID(String archivesID)
	{
		this.archivesID = archivesID;
	}

	/**
	 * ����
	 */
	private String title=null;

	/**
	 * ��ȡ����ֵ������
	 * @return ����
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * ��������ֵ������
	 * @param title ����
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * ����ԭ��
	 */
	private String appraisalReason=null;

	/**
	 * ��ȡ����ֵ������ԭ��
	 * @return ����ԭ��
	 */
	public String getAppraisalReason()
	{
		return appraisalReason;
	}

	/**
	 * ��������ֵ������ԭ��
	 * @param appraisalReason ����ԭ��
	 */
	public void setAppraisalReason(String appraisalReason)
	{
		this.appraisalReason = appraisalReason;
	}

	/**
	 * ��������
	 */
	private Date appraisalDate;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public Date getAppraisalDate()
	{
		return appraisalDate;
	}

	/**
	 * ��������ֵ����������
	 * @param appraisalDate ��������
	 */
	public void setAppraisalDate(Date appraisalDate)
	{
		this.appraisalDate = appraisalDate;
	}

	/**
	 * �������
	 */
	private String appraisalOpinion=null;

	/**
	 * ��ȡ����ֵ���������
	 * @return �������
	 */
	public String getAppraisalOpinion()
	{
		return appraisalOpinion;
	}

	/**
	 * ��������ֵ���������
	 * @param appraisalOpinion �������
	 */
	public void setAppraisalOpinion(String appraisalOpinion)
	{
		this.appraisalOpinion = appraisalOpinion;
	}

	/**
	 * ������
	 */
	private String appraisalPersion=null;

	/**
	 * ��ȡ����ֵ��������
	 * @return ������
	 */
	public String getAppraisalPersion()
	{
		return appraisalPersion;
	}

	/**
	 * ��������ֵ��������
	 * @param appraisalPersion ������
	 */
	public void setAppraisalPersion(String appraisalPersion)
	{
		this.appraisalPersion = appraisalPersion;
	}

	/**
	 * ����ǰ�ĵ����ܼ�
	 */
	private int oldSecrecyID=0;

	/**
	 * ��ȡ����ֵ������ǰ�ĵ����ܼ�
	 * @return ����ǰ�ĵ����ܼ�
	 */
	public int getOldSecrecyID()
	{
		return oldSecrecyID;
	}

	/**
	 * ��������ֵ������ǰ�ĵ����ܼ�
	 * @param oldSecrecyID ����ǰ�ĵ����ܼ�
	 */
	public void setOldSecrecyID(int oldSecrecyID)
	{
		this.oldSecrecyID = oldSecrecyID;
	}

	/**
	 * ������ĵ����ܼ����
	 */
	private int newSecrecyID=0;

	/**
	 * ��ȡ����ֵ��������ĵ����ܼ����
	 * @return ������ĵ����ܼ����
	 */
	public int getNewSecrecyID()
	{
		return newSecrecyID;
	}

	/**
	 * ��������ֵ��������ĵ����ܼ����
	 * @param newSecrecyID ������ĵ����ܼ����
	 */
	public void setNewSecrecyID(int newSecrecyID)
	{
		this.newSecrecyID = newSecrecyID;
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
	 * �����γɲ�������
	 */
	private String formationDepartment;
	
	/**
	 * ��ȡ����ֵ�������γɲ�������
	 * @return �����γɲ�������
	 */
	public String getFormationDepartment()
	{
		return formationDepartment;
	}

	/**
	 * ��������ֵ�������γɲ�������
	 * @param formationDepartment �����γɲ�������
	 */
	public void setFormationDepartment(String formationDepartment)
	{
		this.formationDepartment = formationDepartment;
	}
	
	/**
	 * ������������
	 */
	private String archivesTypeName;
	
	/**
	 * ��ȡ����ֵ�� ������������
	 * @return  ������������
	 */
	public String getArchivesTypeName() {
		return archivesTypeName;
	}
	
	/**
	 * ��������ֵ�� ������������
	 * @param formationDepartment  ������������
	 */
	public void setArchivesTypeName(String archivesTypeName) {
		this.archivesTypeName = archivesTypeName;
	}
	
	/**
	 * ԭ�����ܼ�����
	 */
	public String oldSecrecyName;

	public String getOldSecrecyName() {
		return oldSecrecyName;
	}

	public void setOldSecrecyName(String oldSecrecyName) {
		this.oldSecrecyName = oldSecrecyName;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public AppraisalPublicDetail clone()
	{
		AppraisalPublicDetail item = new AppraisalPublicDetail(iD,publicFlag,archivesTypeID,nBXH,archivesID,title,appraisalReason,appraisalDate,appraisalOpinion,appraisalPersion,oldSecrecyID,newSecrecyID,registerDate,managerUserID,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param appraisalPublicDetail ָ���Ķ���Դ
	*/
	public void cloneFrom(AppraisalPublicDetail appraisalPublicDetail)
	{
		this.iD = appraisalPublicDetail.getID();
		this.publicFlag = appraisalPublicDetail.getPublicFlag();
		this.archivesTypeID = appraisalPublicDetail.getArchivesTypeID();
		this.nBXH = appraisalPublicDetail.getNBXH();
		this.archivesID = appraisalPublicDetail.getArchivesID();
		this.title = appraisalPublicDetail.getTitle();
		this.appraisalReason = appraisalPublicDetail.getAppraisalReason();
		this.appraisalDate = appraisalPublicDetail.getAppraisalDate();
		this.appraisalOpinion = appraisalPublicDetail.getAppraisalOpinion();
		this.appraisalPersion = appraisalPublicDetail.getAppraisalPersion();
		this.oldSecrecyID = appraisalPublicDetail.getOldSecrecyID();
		this.newSecrecyID = appraisalPublicDetail.getNewSecrecyID();
		this.registerDate = appraisalPublicDetail.getRegisterDate();
		this.managerUserID = appraisalPublicDetail.getManagerUserID();
		this.remark = appraisalPublicDetail.getRemark();
		this.keyInCol = appraisalPublicDetail.getKeyInCol();
		this.tag = appraisalPublicDetail.getTag();
	}



    
}



