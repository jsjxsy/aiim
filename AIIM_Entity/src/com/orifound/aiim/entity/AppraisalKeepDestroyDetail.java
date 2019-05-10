package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * ������ټ�����ϸ������ʵ����
 */
public class AppraisalKeepDestroyDetail
{
    /**
     * ���캯��
     */
    public AppraisalKeepDestroyDetail()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param archivesTypeID ����������
	* @param nBXH �����ڲ����
	* @param archivesID ����
	* @param title ����
	* @param appraisalReason ����ԭ��
	* @param appraisalDate ��������
	* @param appraisalOpinion �������
	* @param appraisalPersion ������
	* @param appraisalDeletedFlag ����ɾ����־
	* @param oldRetentionPeriodID ����ǰ�ı������ޱ��
	* @param newRetentionPeriodID ������ı������ޱ��
	* @param registerDate �Ǽ�����
	* @param managerUserID �����˱��
	* @param remark ��ע
	*/
	public AppraisalKeepDestroyDetail(int iD,int archivesTypeID,int nBXH,String archivesID,String title,String appraisalReason,Date appraisalDate,String appraisalOpinion,String appraisalPersion,boolean appraisalDeletedFlag,int oldRetentionPeriodID,int newRetentionPeriodID,Date registerDate,int managerUserID,String remark)
	{
		// Table Name: AppraisalKeepDestroyDetails
		// Columns List,Can Used in SELECT SQL: ID,ArchivesTypeID,NBXH,ArchivesID,Title,AppraisalReason,AppraisalDate,AppraisalOpinion,AppraisalPersion,AppraisalDeletedFlag,OldRetentionPeriodID,NewRetentionPeriodID,RegisterDate,ManagerUserID,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:AppraisalReason,:AppraisalDate,:AppraisalOpinion,:AppraisalPersion,:AppraisalDeletedFlag,:OldRetentionPeriodID,:NewRetentionPeriodID,:RegisterDate,:ManagerUserID,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,AppraisalReason=:AppraisalReason,AppraisalDate=:AppraisalDate,AppraisalOpinion=:AppraisalOpinion,AppraisalPersion=:AppraisalPersion,AppraisalDeletedFlag=:AppraisalDeletedFlag,OldRetentionPeriodID=:OldRetentionPeriodID,NewRetentionPeriodID=:NewRetentionPeriodID,RegisterDate=:RegisterDate,ManagerUserID=:ManagerUserID,Remark=:Remark

		this(archivesTypeID, nBXH, archivesID, title, appraisalReason, appraisalDate, appraisalOpinion, appraisalPersion, appraisalDeletedFlag, oldRetentionPeriodID, newRetentionPeriodID, managerUserID, remark);
		this.iD = iD;
		this.registerDate = registerDate;
	}
	

	/**
	* ���ֶβ����Ĺ��캯��
	* @param archivesTypeID ����������
	* @param nBXH �����ڲ����
	* @param archivesID ����
	* @param title ����
	* @param appraisalReason ����ԭ��
	* @param appraisalDate ��������
	* @param appraisalOpinion �������
	* @param appraisalPersion ������
	* @param appraisalDeletedFlag ����ɾ����־
	* @param oldRetentionPeriodID ����ǰ�ı������ޱ��
	* @param newRetentionPeriodID ������ı������ޱ��
	* @param registerDate �Ǽ�����
	* @param managerUserID �����˱��
	* @param remark ��ע
	*/
	public AppraisalKeepDestroyDetail(int archivesTypeID,int nBXH,String archivesID,String title,String appraisalReason,Date appraisalDate,String appraisalOpinion,String appraisalPersion,boolean appraisalDeletedFlag,int oldRetentionPeriodID,int newRetentionPeriodID,int managerUserID,String remark)
	{
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.archivesID = archivesID;
		this.title = title;
		this.appraisalReason = appraisalReason;
		this.appraisalDate = appraisalDate;
		this.appraisalOpinion = appraisalOpinion;
		this.appraisalPersion = appraisalPersion;
		this.appraisalDeletedFlag = appraisalDeletedFlag;
		this.oldRetentionPeriodID = oldRetentionPeriodID;
		this.newRetentionPeriodID = newRetentionPeriodID;
		this.managerUserID = managerUserID;
		this.remark = remark;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param archivesTypeID ����������
	* @param nBXH �����ڲ����
	* @param archivesID ����
	* @param title ����
	* @param appraisalReason ����ԭ��
	* @param appraisalDate ��������
	* @param appraisalOpinion �������
	* @param appraisalPersion ������
	* @param appraisalDeletedFlag ����ɾ����־
	* @param oldRetentionPeriodID ����ǰ�ı������ޱ��
	* @param newRetentionPeriodID ������ı������ޱ��
	* @param registerDate �Ǽ�����
	* @param managerUserID �����˱��
	* @param remark ��ע
	* @param formationDepartment �����γɲ�������
	* @param archivesTypeName ������������
	* @param retentionPeriodName ��������
	* @param oldRetentionPeriodName ����ǰ��������
	*/
	public AppraisalKeepDestroyDetail(int iD,int archivesTypeID,int nBXH,String archivesID,String title,String appraisalReason,Date appraisalDate,String appraisalOpinion,String appraisalPersion,boolean appraisalDeletedFlag,int oldRetentionPeriodID,int newRetentionPeriodID,Date registerDate,int managerUserID,String remark, String formationDepartment, String archivesTypeName, String retentionPeriodName, String oldRetentionPeriodName)
	{
		// Table Name: AppraisalKeepDestroyDetails
		// Columns List,Can Used in SELECT SQL: ID,ArchivesTypeID,NBXH,ArchivesID,Title,AppraisalReason,AppraisalDate,AppraisalOpinion,AppraisalPersion,AppraisalDeletedFlag,OldRetentionPeriodID,NewRetentionPeriodID,RegisterDate,ManagerUserID,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:AppraisalReason,:AppraisalDate,:AppraisalOpinion,:AppraisalPersion,:AppraisalDeletedFlag,:OldRetentionPeriodID,:NewRetentionPeriodID,:RegisterDate,:ManagerUserID,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,AppraisalReason=:AppraisalReason,AppraisalDate=:AppraisalDate,AppraisalOpinion=:AppraisalOpinion,AppraisalPersion=:AppraisalPersion,AppraisalDeletedFlag=:AppraisalDeletedFlag,OldRetentionPeriodID=:OldRetentionPeriodID,NewRetentionPeriodID=:NewRetentionPeriodID,RegisterDate=:RegisterDate,ManagerUserID=:ManagerUserID,Remark=:Remark

		this(iD, archivesTypeID, nBXH, archivesID, title, appraisalReason, appraisalDate, appraisalOpinion, appraisalPersion, appraisalDeletedFlag, oldRetentionPeriodID, newRetentionPeriodID, registerDate, managerUserID, remark);
		this.formationDepartment = formationDepartment;
		this.archivesTypeName = archivesTypeName;
		this.retentionPeriodName = retentionPeriodName;
		this.oldRetentionPeriodName = oldRetentionPeriodName;
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
	 * ����ɾ����־
	 */
	private boolean appraisalDeletedFlag=false;

	/**
	 * ��ȡ����ֵ������ɾ����־
	 * @return ����ɾ����־
	 */
	public boolean getAppraisalDeletedFlag()
	{
		return appraisalDeletedFlag;
	}

	/**
	 * ��������ֵ������ɾ����־
	 * @param appraisalDeletedFlag ����ɾ����־
	 */
	public void setAppraisalDeletedFlag(boolean appraisalDeletedFlag)
	{
		this.appraisalDeletedFlag = appraisalDeletedFlag;
	}

	/**
	 * ����ǰ�ı������ޱ��
	 */
	private int oldRetentionPeriodID=0;

	/**
	 * ��ȡ����ֵ������ǰ�ı������ޱ��
	 * @return ����ǰ�ı������ޱ��
	 */
	public int getOldRetentionPeriodID()
	{
		return oldRetentionPeriodID;
	}

	/**
	 * ��������ֵ������ǰ�ı������ޱ��
	 * @param oldRetentionPeriodID ����ǰ�ı������ޱ��
	 */
	public void setOldRetentionPeriodID(int oldRetentionPeriodID)
	{
		this.oldRetentionPeriodID = oldRetentionPeriodID;
	}

	/**
	 * ������ı������ޱ��
	 */
	private int newRetentionPeriodID=0;

	/**
	 * ��ȡ����ֵ��������ı������ޱ��
	 * @return ������ı������ޱ��
	 */
	public int getNewRetentionPeriodID()
	{
		return newRetentionPeriodID;
	}

	/**
	 * ��������ֵ��������ı������ޱ��
	 * @param newRetentionPeriodID ������ı������ޱ��
	 */
	public void setNewRetentionPeriodID(int newRetentionPeriodID)
	{
		this.newRetentionPeriodID = newRetentionPeriodID;
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
	 * ������������
	 */
	public String retentionPeriodName ;

	public String getRetentionPeriodName() {
		return retentionPeriodName;
	}

	public void setRetentionPeriodName(String retentionPeriodName) {
		this.retentionPeriodName = retentionPeriodName;
	}

	/**
	 * ����ǰ������������
	 */
	public String oldRetentionPeriodName ;
	
	public String getOldRetentionPeriodName() {
		return oldRetentionPeriodName;
	}

	public void setOldRetentionPeriodName(String oldRetentionPeriodName) {
		this.oldRetentionPeriodName = oldRetentionPeriodName;
	}
	

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public AppraisalKeepDestroyDetail clone()
	{
		AppraisalKeepDestroyDetail item = new AppraisalKeepDestroyDetail(iD,archivesTypeID,nBXH,archivesID,title,appraisalReason,appraisalDate,appraisalOpinion,appraisalPersion,appraisalDeletedFlag,oldRetentionPeriodID,newRetentionPeriodID,registerDate,managerUserID,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param appraisalKeepDestroyDetail ָ���Ķ���Դ
	*/
	public void cloneFrom(AppraisalKeepDestroyDetail appraisalKeepDestroyDetail)
	{
		this.iD = appraisalKeepDestroyDetail.getID();
		this.archivesTypeID = appraisalKeepDestroyDetail.getArchivesTypeID();
		this.nBXH = appraisalKeepDestroyDetail.getNBXH();
		this.archivesID = appraisalKeepDestroyDetail.getArchivesID();
		this.title = appraisalKeepDestroyDetail.getTitle();
		this.appraisalReason = appraisalKeepDestroyDetail.getAppraisalReason();
		this.appraisalDate = appraisalKeepDestroyDetail.getAppraisalDate();
		this.appraisalOpinion = appraisalKeepDestroyDetail.getAppraisalOpinion();
		this.appraisalPersion = appraisalKeepDestroyDetail.getAppraisalPersion();
		this.appraisalDeletedFlag = appraisalKeepDestroyDetail.getAppraisalDeletedFlag();
		this.oldRetentionPeriodID = appraisalKeepDestroyDetail.getOldRetentionPeriodID();
		this.newRetentionPeriodID = appraisalKeepDestroyDetail.getNewRetentionPeriodID();
		this.registerDate = appraisalKeepDestroyDetail.getRegisterDate();
		this.managerUserID = appraisalKeepDestroyDetail.getManagerUserID();
		this.remark = appraisalKeepDestroyDetail.getRemark();
		this.keyInCol = appraisalKeepDestroyDetail.getKeyInCol();
		this.tag = appraisalKeepDestroyDetail.getTag();
	}
}



