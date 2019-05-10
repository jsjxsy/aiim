package com.orifound.aiim.entity;

import java.util.Date;
    
/**
 * �����鵵������Ϣ���ʵ����
 */
public class ArchivesInfoWorkProcedure
{
    /**
     * ���캯��
     */
    public ArchivesInfoWorkProcedure()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param nBXH �����ڲ����
	* @param archivesTypeID ����������
	* @param userID �û����
	* @param workFlowStatus ������������״̬
	* @param operateTime ����ʱ��
	* @param sendBackReason ���ԭ��
	*/
	public ArchivesInfoWorkProcedure(int iD,int nBXH,int archivesTypeID,int userID,int workFlowStatus,Date operateTime,String sendBackReason)
	{
		// Table Name: ArchivesInfoWorkProcedure_TypeCode
		// Columns List,Can Used in SELECT SQL: ID,NBXH,ArchivesTypeID,UserID,WorkFlowStatus,OperateTime,SendBackReason
		// Columns List,Can Used in INSERT SQL: :ID,:NBXH,:ArchivesTypeID,:UserID,:WorkFlowStatus,:OperateTime,:SendBackReason
		// Columns List,Can Used in UPDATE SQL: ID=:ID,NBXH=:NBXH,ArchivesTypeID=:ArchivesTypeID,UserID=:UserID,WorkFlowStatus=:WorkFlowStatus,OperateTime=:OperateTime,SendBackReason=:SendBackReason

		this(nBXH, archivesTypeID, userID, workFlowStatus, sendBackReason);
		this.iD = iD;
		this.operateTime = operateTime;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param nBXH �����ڲ����
	* @param archivesTypeID ����������
	* @param userID �û����
	* @param workFlowStatus ������������״̬
	* @param sendBackReason ���ԭ��
	*/
	public ArchivesInfoWorkProcedure(int nBXH,int archivesTypeID,int userID,int workFlowStatus,String sendBackReason)
	{
		this(nBXH, archivesTypeID, userID, workFlowStatus);
		this.sendBackReason = sendBackReason;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param nBXH �����ڲ����
	* @param archivesTypeID ����������
	* @param userID �û����
	* @param workFlowStatus ������������״̬
	*/
	public ArchivesInfoWorkProcedure(int nBXH,int archivesTypeID,int userID,int workFlowStatus)
	{
		this.nBXH = nBXH;
		this.archivesTypeID = archivesTypeID;
		this.userID = userID;
		this.workFlowStatus =EnumWorkFlowStatus.getEnumElement(workFlowStatus);
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
	 * �û����
	 */
	private int userID=0;

	/**
	 * ��ȡ����ֵ���û����
	 * @return �û����
	 */
	public int getUserID()
	{
		return userID;
	}

	/**
	 * ��������ֵ���û����
	 * @param userID �û����
	 */
	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	/**
	 * ������������״̬
	 */
	private EnumWorkFlowStatus workFlowStatus=EnumWorkFlowStatus.NONE;

	/**
	 * ��ȡ����ֵ��������������״̬
	 * @return ������������״̬
	 */
	public EnumWorkFlowStatus getWorkFlowStatus()
	{
		return workFlowStatus;
	}

	/**
	 * ��������ֵ��������������״̬
	 * @param workFlowStatus ������������״̬
	 */
	public void setWorkFlowStatus(EnumWorkFlowStatus workFlowStatus)
	{
		this.workFlowStatus = workFlowStatus;
	}

	/**
	 * ����ʱ��
	 */
	private Date operateTime;

	/**
	 * ��ȡ����ֵ������ʱ��
	 * @return ����ʱ��
	 */
	public Date getOperateTime()
	{
		return operateTime;
	}

	/**
	 * ��������ֵ������ʱ��
	 * @param operateTime ����ʱ��
	 */
	public void setOperateTime(Date operateTime)
	{
		this.operateTime = operateTime;
	}

	/**
	 * ���ԭ��
	 */
	private String sendBackReason=null;

	/**
	 * ��ȡ����ֵ�����ԭ��
	 * @return ���ԭ��
	 */
	public String getSendBackReason()
	{
		return sendBackReason;
	}

	/**
	 * ��������ֵ�����ԭ��
	 * @param sendBackReason ���ԭ��
	 */
	public void setSendBackReason(String sendBackReason)
	{
		this.sendBackReason = sendBackReason;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ArchivesInfoWorkProcedure clone()
	{
		ArchivesInfoWorkProcedure item = new ArchivesInfoWorkProcedure(iD,nBXH,archivesTypeID,userID,workFlowStatus.getEnumValue(),operateTime,sendBackReason);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param archivesInfoWorkProcedure ָ���Ķ���Դ
	*/
	public void cloneFrom(ArchivesInfoWorkProcedure archivesInfoWorkProcedure)
	{
		this.iD = archivesInfoWorkProcedure.getID();
		this.nBXH = archivesInfoWorkProcedure.getNBXH();
		this.archivesTypeID = archivesInfoWorkProcedure.getArchivesTypeID();
		this.userID = archivesInfoWorkProcedure.getUserID();
		this.workFlowStatus = archivesInfoWorkProcedure.getWorkFlowStatus();
		this.operateTime = archivesInfoWorkProcedure.getOperateTime();
		this.sendBackReason = archivesInfoWorkProcedure.getSendBackReason();
		this.keyInCol = archivesInfoWorkProcedure.getKeyInCol();
		this.tag = archivesInfoWorkProcedure.getTag();
	}

    
}



