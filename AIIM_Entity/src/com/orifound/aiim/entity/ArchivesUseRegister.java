package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * ʵ�ﵵ�����õǼǱ��ʵ����
 */
public class ArchivesUseRegister
{
    /**
     * ���캯��
     */
    public ArchivesUseRegister()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD �赵�ǼǱ��
	* @param borrowFlag ���ı�־
	* @param purposeID ����Ŀ�ı��
	* @param usePersonsCount ��������
	* @param useArchivesCount ���þ���
	* @param useDate ��������
	* @param usePersonID �����˱��
	* @param managerUserID �����˱��
	* @param remark ��ע
	*/
	public ArchivesUseRegister(int iD,boolean borrowFlag,int purposeID,int usePersonsCount,int useArchivesCount,Date useDate,int usePersonID,int managerUserID,String remark)
	{
		// Table Name: ArchivesUseRegister
		// Columns List,Can Used in SELECT SQL: ID,BorrowFlag,PurposeID,UsePersonsCount,UseArchivesCount,UseDate,UsePersonID,ManagerUserID,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:BorrowFlag,:PurposeID,:UsePersonsCount,:UseArchivesCount,:UseDate,:UsePersonID,:ManagerUserID,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,BorrowFlag=:BorrowFlag,PurposeID=:PurposeID,UsePersonsCount=:UsePersonsCount,UseArchivesCount=:UseArchivesCount,UseDate=:UseDate,UsePersonID=:UsePersonID,ManagerUserID=:ManagerUserID,Remark=:Remark

		this.iD = iD;
		this.borrowFlag = borrowFlag;
		this.purposeID = purposeID;
		this.usePersonsCount = usePersonsCount;
		this.useArchivesCount = useArchivesCount;
		this.useDate = useDate;
		this.usePersonID = usePersonID;
		this.managerUserID = managerUserID;
		this.remark = remark;
	}
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD �赵�ǼǱ��
	* @param borrowFlag ���ı�־
	* @param purposeID ����Ŀ�ı��
	* @param usePersonsCount ��������
	* @param useArchivesCount ���þ���
	* @param useDate ��������
	* @param usePersonID �����˱��
	* @param managerUserID �����˱��
	* @param remark ��ע
	*/

	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD �赵�ǼǱ��
	* @param borrowFlag ���ı�־
	* @param purposeID ����Ŀ�ı��
	* @param usePersonsCount ��������
	* @param useArchivesCount ���þ���
	* @param useDate ��������
	* @param usePersonID �����˱��
	 * @param managerUserID �����˱��
	 * @param remark ��ע
	 * @param managerUserName ����������
	 * @param purposeText ����Ŀ������
	 */
	public ArchivesUseRegister(int iD,boolean borrowFlag,int purposeID,int usePersonsCount,int useArchivesCount,Date useDate,int usePersonID,int managerUserID,String remark,String managerUserName,String purposeText)
	{
		// Table Name: ArchivesUseRegister
		// Columns List,Can Used in SELECT SQL: ID,BorrowFlag,PurposeID,UsePersonsCount,UseArchivesCount,UseDate,UsePersonID,ManagerUserID,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:BorrowFlag,:PurposeID,:UsePersonsCount,:UseArchivesCount,:UseDate,:UsePersonID,:ManagerUserID,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,BorrowFlag=:BorrowFlag,PurposeID=:PurposeID,UsePersonsCount=:UsePersonsCount,UseArchivesCount=:UseArchivesCount,UseDate=:UseDate,UsePersonID=:UsePersonID,ManagerUserID=:ManagerUserID,Remark=:Remark

		this.iD = iD;
		this.borrowFlag = borrowFlag;
		this.purposeID = purposeID;
		this.usePersonsCount = usePersonsCount;
		this.useArchivesCount = useArchivesCount;
		this.useDate = useDate;
		this.usePersonID = usePersonID;
		this.managerUserID = managerUserID;
		this.remark = remark;
		this.managerUserName = managerUserName;
		this.purposeText = purposeText;
		
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
	 * �赵�ǼǱ��
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ���赵�ǼǱ��
	 * @return �赵�ǼǱ��
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ���赵�ǼǱ��
	 * @param iD �赵�ǼǱ��
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ���ı�־
	 */
	private boolean borrowFlag=false;

	/**
	 * ��ȡ����ֵ�����ı�־
	 * @return ���ı�־
	 */
	public boolean getBorrowFlag()
	{
		return borrowFlag;
	}

	/**
	 * ��������ֵ�����ı�־
	 * @param borrowFlag ���ı�־
	 */
	public void setBorrowFlag(boolean borrowFlag)
	{
		this.borrowFlag = borrowFlag;
	}

	/**
	 * ����Ŀ�ı��
	 */
	private int purposeID=0;

	/**
	 * ��ȡ����ֵ������Ŀ�ı��
	 * @return ����Ŀ�ı��
	 */
	public int getPurposeID()
	{
		return purposeID;
	}

	/**
	 * ��������ֵ������Ŀ�ı��
	 * @param purposeID ����Ŀ�ı��
	 */
	public void setPurposeID(int purposeID)
	{
		this.purposeID = purposeID;
	}

	/**
	 * ��������
	 */
	private int usePersonsCount=0;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public int getUsePersonsCount()
	{
		return usePersonsCount;
	}

	/**
	 * ��������ֵ����������
	 * @param usePersonsCount ��������
	 */
	public void setUsePersonsCount(int usePersonsCount)
	{
		this.usePersonsCount = usePersonsCount;
	}

	/**
	 * ���þ���
	 */
	private int useArchivesCount=0;

	/**
	 * ��ȡ����ֵ�����þ���
	 * @return ���þ���
	 */
	public int getUseArchivesCount()
	{
		return useArchivesCount;
	}

	/**
	 * ��������ֵ�����þ���
	 * @param useArchivesCount ���þ���
	 */
	public void setUseArchivesCount(int useArchivesCount)
	{
		this.useArchivesCount = useArchivesCount;
	}

	/**
	 * ��������
	 */
	private Date useDate;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public Date getUseDate()
	{
		return useDate;
	}

	/**
	 * ��������ֵ����������
	 * @param useDate ��������
	 */
	public void setUseDate(Date useDate)
	{
		this.useDate = useDate;
	}
	
	/**
	 * �����˱��
	 */
	private int usePersonID=0;

	/**
	 * ��ȡ����ֵ�������˱��
	 * @return �����˱��
	 */
	public int getUsePersonID()
	{
		return usePersonID;
	}

	/**
	 * ��������ֵ�������˱��
	 * @param usePersonID �����˱��
	 */
	public void setUsePersonID(int usePersonID)
	{
		this.usePersonID = usePersonID;
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
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ArchivesUseRegister clone()
	{
		ArchivesUseRegister item = new ArchivesUseRegister(iD,borrowFlag,purposeID,usePersonsCount,useArchivesCount,useDate,usePersonID,managerUserID,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);
		

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param archivesUseRegister ָ���Ķ���Դ
	*/
	public void cloneFrom(ArchivesUseRegister archivesUseRegister)
	{
		this.iD = archivesUseRegister.getID();
		this.borrowFlag = archivesUseRegister.getBorrowFlag();
		this.purposeID = archivesUseRegister.getPurposeID();
		this.usePersonsCount = archivesUseRegister.getUsePersonsCount();
		this.useArchivesCount = archivesUseRegister.getUseArchivesCount();
		this.useDate = archivesUseRegister.getUseDate();
		this.usePersonID = archivesUseRegister.getUsePersonID();
		this.managerUserID = archivesUseRegister.getManagerUserID();
		this.remark = archivesUseRegister.getRemark();
		this.keyInCol = archivesUseRegister.getKeyInCol();		
		this.tag = archivesUseRegister.getTag();
		this.archivesUsePersonInfo = archivesUseRegister.getArchivesUsePersonInfo();
		this.purposeText = archivesUseRegister.getPurposeText();
		this.managerUserName = archivesUseRegister.getManagerUserName();
	}
	
	/**
	 * ������������Ϣʵ�����
	 */
	private ArchivesUsePersonInfo archivesUsePersonInfo = null;

	/**
	 * ��ȡ������������Ϣ
	 * @return
	 */
	public ArchivesUsePersonInfo getArchivesUsePersonInfo() {
		return archivesUsePersonInfo;
	}

	/**
	 * ���õ�����������Ϣ
	 * @param archivesUsePersonInfo
	 */
	public void setArchivesUsePersonInfo(ArchivesUsePersonInfo archivesUsePersonInfo) {
		this.archivesUsePersonInfo = archivesUsePersonInfo;
	}
	
	/**
	 * ����Ŀ����ʾ�ı�
	 */
	private String purposeText = null;

	/**
	 * ��ȡ����Ŀ���ı�
	 * @return
	 */
	public String getPurposeText() {
		return purposeText;
	}

	/**
	 * ��������Ŀ���ı�
	 * @param purposeText
	 */
	public void setPurposeText(String purposeText) {
		this.purposeText = purposeText;
	}
	
	/**
	 * ����������
	 */
	private String managerUserName = null;

	/**
	 * ��������ֵ������������
	 * @param manageUserName ����������
	 */
	public void setManagerUserName(String managerUserName) {
		this.managerUserName = managerUserName;
	}

	/**
	 * ��ȡ����ֵ������������
	 * @return ����������
	 */
	public String getManagerUserName() {
		return managerUserName;
	}


    
}










//
//	/**
//	 * ʵ�ﵵ�������嵥��Ϣ
//	 */
//	private List<ArchivesUseInfo> archivesUseOutInfos = null;
//
//	/**
//	 * ��������ֵ��ʵ�ﵵ�������嵥��Ϣ
//	 * @param archivesUseOutInfos ʵ�ﵵ�������嵥��Ϣ
//	 */
//	public void setArchivesUseOutInfos(List<ArchivesUseInfo> archivesUseOutInfos) {
//		this.archivesUseOutInfos = archivesUseOutInfos;
//	}
//
//	/**
//	 * ��ȡ����ֵ��ʵ�ﵵ�������嵥��Ϣ
//	 * @return ʵ�ﵵ�������嵥��Ϣ
//	 */
//	public List<ArchivesUseInfo> getArchivesUseOutInfos() {
//		return archivesUseOutInfos;
//	}
//
//	
//    
//}
//
//

