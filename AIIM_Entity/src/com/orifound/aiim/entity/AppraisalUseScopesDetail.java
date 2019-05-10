package com.orifound.aiim.entity;

import java.util.Date;
import java.util.List;
    
/**
 * �������ֿ��Ƽ�����ϸ������ʵ����
 */
public class AppraisalUseScopesDetail
{
    /**
     * ���캯��
     */
    public AppraisalUseScopesDetail()
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
	* @param registerDate �Ǽ�����
	* @param managerUserID �����˱��
	* @param remark ��ע
	*/
	public AppraisalUseScopesDetail(int iD,int archivesTypeID,int nBXH,String archivesID,String title,String appraisalReason,Date appraisalDate,String appraisalOpinion,String appraisalPersion,Date registerDate,int managerUserID,String remark)
	{
		// Table Name: AppraisalUseScopesDetails
		// Columns List,Can Used in SELECT SQL: ID,ArchivesTypeID,NBXH,ArchivesID,Title,AppraisalReason,AppraisalDate,AppraisalOpinion,AppraisalPersion,RegisterDate,ManagerUserID,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:AppraisalReason,:AppraisalDate,:AppraisalOpinion,:AppraisalPersion,:RegisterDate,:ManagerUserID,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,AppraisalReason=:AppraisalReason,AppraisalDate=:AppraisalDate,AppraisalOpinion=:AppraisalOpinion,AppraisalPersion=:AppraisalPersion,RegisterDate=:RegisterDate,ManagerUserID=:ManagerUserID,Remark=:Remark

		this(archivesTypeID, nBXH, archivesID, title, appraisalReason, appraisalDate, appraisalOpinion, appraisalPersion, managerUserID, remark);
		this.iD = iD;
		this.registerDate = registerDate;
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
	* @param registerDate �Ǽ�����
	* @param managerUserID �����˱��
	* @param remark ��ע
	*/
	public AppraisalUseScopesDetail(int iD,int archivesTypeID,int nBXH,String archivesID,String title,String appraisalReason,Date appraisalDate,String appraisalOpinion,String appraisalPersion,Date registerDate,int managerUserID,String remark, String archivesTypeName, String formationDepartment)
	{
		// Table Name: AppraisalUseScopesDetails
		// Columns List,Can Used in SELECT SQL: ID,ArchivesTypeID,NBXH,ArchivesID,Title,AppraisalReason,AppraisalDate,AppraisalOpinion,AppraisalPersion,RegisterDate,ManagerUserID,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:AppraisalReason,:AppraisalDate,:AppraisalOpinion,:AppraisalPersion,:RegisterDate,:ManagerUserID,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,AppraisalReason=:AppraisalReason,AppraisalDate=:AppraisalDate,AppraisalOpinion=:AppraisalOpinion,AppraisalPersion=:AppraisalPersion,RegisterDate=:RegisterDate,ManagerUserID=:ManagerUserID,Remark=:Remark

		this(archivesTypeID, nBXH, archivesID, title, appraisalReason, appraisalDate, appraisalOpinion, appraisalPersion, managerUserID, remark);
		this.iD = iD;
		this.registerDate = registerDate;
		this.archivesTypeName = archivesTypeName;
		this.formationDepartment = formationDepartment;
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
	* @param managerUserID �����˱��
	* @param remark ��ע
	*/
	public AppraisalUseScopesDetail(int archivesTypeID,int nBXH,String archivesID,String title,String appraisalReason,Date appraisalDate,String appraisalOpinion,String appraisalPersion,int managerUserID,String remark)
	{
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.archivesID = archivesID;
		this.title = title;
		this.appraisalReason = appraisalReason;
		this.appraisalDate = appraisalDate;
		this.appraisalOpinion = appraisalOpinion;
		this.appraisalPersion = appraisalPersion;
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
	 * ���ֿ��Ƽ����Ľ�ɫ��Χid����
	 */
	private List<Integer> roleIds = null;

	public List<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}
	
	/**
	 * �����γɲ���ID
	 */
	private int formationDepartmentID;
	
	public int getFormationDepartmentID() {
		return formationDepartmentID;
	}

	public void setFormationDepartmentID(int formationDepartmentID) {
		this.formationDepartmentID = formationDepartmentID;
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
	 * ���ؽ�ɫ���Ƽ���
	 */
	private List<String> roleNames;
	
	public List<String> getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(List<String> roleNames) {
		this.roleNames = roleNames;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public AppraisalUseScopesDetail clone()
	{
		AppraisalUseScopesDetail item = new AppraisalUseScopesDetail(iD,archivesTypeID,nBXH,archivesID,title,appraisalReason,appraisalDate,appraisalOpinion,appraisalPersion,registerDate,managerUserID,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param appraisalUseScopesDetail ָ���Ķ���Դ
	*/
	public void cloneFrom(AppraisalUseScopesDetail appraisalUseScopesDetail)
	{
		this.iD = appraisalUseScopesDetail.getID();
		this.archivesTypeID = appraisalUseScopesDetail.getArchivesTypeID();
		this.nBXH = appraisalUseScopesDetail.getNBXH();
		this.archivesID = appraisalUseScopesDetail.getArchivesID();
		this.title = appraisalUseScopesDetail.getTitle();
		this.appraisalReason = appraisalUseScopesDetail.getAppraisalReason();
		this.appraisalDate = appraisalUseScopesDetail.getAppraisalDate();
		this.appraisalOpinion = appraisalUseScopesDetail.getAppraisalOpinion();
		this.appraisalPersion = appraisalUseScopesDetail.getAppraisalPersion();
		this.registerDate = appraisalUseScopesDetail.getRegisterDate();
		this.managerUserID = appraisalUseScopesDetail.getManagerUserID();
		this.remark = appraisalUseScopesDetail.getRemark();
		this.keyInCol = appraisalUseScopesDetail.getKeyInCol();
		this.tag = appraisalUseScopesDetail.getTag();
	}

    
}



