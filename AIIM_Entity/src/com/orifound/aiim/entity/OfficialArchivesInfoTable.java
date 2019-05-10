package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * ���ĵ�����Ϣ������ֵ���ʵ����
 */
public class OfficialArchivesInfoTable
{
    /**
     * ���캯��
     */
    public OfficialArchivesInfoTable()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param archivesTypeID ����������
	* @param tableType ������
	* @param tableName ����������
	* @param createdFlag ������־
	* @param createdTime ����ʱ��
	* @param remark ��ע
	*/
	public OfficialArchivesInfoTable(int iD,int archivesTypeID,int tableType,String tableName,boolean createdFlag,Date createdTime,String remark)
	{
		// Table Name: DD_OfficialArchivesInfoTable
		// Columns List,Can Used in SELECT SQL: ID,ArchivesTypeID,TableType,TableName,CreatedFlag,CreatedTime,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:ArchivesTypeID,:TableType,:TableName,:CreatedFlag,:CreatedTime,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ArchivesTypeID=:ArchivesTypeID,TableType=:TableType,TableName=:TableName,CreatedFlag=:CreatedFlag,CreatedTime=:CreatedTime,Remark=:Remark

		this(archivesTypeID, tableType, tableName, createdFlag, createdTime, remark);
		this.iD = iD;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param archivesTypeID ����������
	* @param tableType ������
	* @param tableName ����������
	* @param createdFlag ������־
	* @param createdTime ����ʱ��
	* @param remark ��ע
	*/
	public OfficialArchivesInfoTable(int archivesTypeID,int tableType,String tableName,boolean createdFlag,Date createdTime,String remark)
	{
		this.archivesTypeID = archivesTypeID;
		this.tableType =EnumOfficialArchivesInfoTableType.getEnumElement(tableType);
		this.tableName = tableName;
		this.createdFlag = createdFlag;
		this.createdTime = createdTime;
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
	 * ������
	 */
	private EnumOfficialArchivesInfoTableType tableType=EnumOfficialArchivesInfoTableType.NONE;

	/**
	 * ��ȡ����ֵ��������
	 * @return ������
	 */
	public EnumOfficialArchivesInfoTableType getTableType()
	{
		return tableType;
	}

	/**
	 * ��������ֵ��������
	 * @param tableType ������
	 */
	public void setTableType(EnumOfficialArchivesInfoTableType tableType)
	{
		this.tableType = tableType;
	}

	/**
	 * ����������
	 */
	private String tableName=null;

	/**
	 * ��ȡ����ֵ������������
	 * @return ����������
	 */
	public String getTableName()
	{
		return tableName;
	}

	/**
	 * ��������ֵ������������
	 * @param tableName ����������
	 */
	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	/**
	 * ������־
	 */
	private boolean createdFlag=false;

	/**
	 * ��ȡ����ֵ��������־
	 * @return ������־
	 */
	public boolean getCreatedFlag()
	{
		return createdFlag;
	}

	/**
	 * ��������ֵ��������־
	 * @param createdFlag ������־
	 */
	public void setCreatedFlag(boolean createdFlag)
	{
		this.createdFlag = createdFlag;
	}

	/**
	 * ����ʱ��
	 */
	private Date createdTime;

	/**
	 * ��ȡ����ֵ������ʱ��
	 * @return ����ʱ��
	 */
	public Date getCreatedTime()
	{
		return createdTime;
	}

	/**
	 * ��������ֵ������ʱ��
	 * @param createdTime ����ʱ��
	 */
	public void setCreatedTime(Date createdTime)
	{
		this.createdTime = createdTime;
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
	public OfficialArchivesInfoTable clone()
	{
		OfficialArchivesInfoTable item = new OfficialArchivesInfoTable(iD,archivesTypeID,tableType.getEnumValue(),tableName,createdFlag,createdTime,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param officialArchivesInfoTable ָ���Ķ���Դ
	*/
	public void cloneFrom(OfficialArchivesInfoTable officialArchivesInfoTable)
	{
		this.iD = officialArchivesInfoTable.getID();
		this.archivesTypeID = officialArchivesInfoTable.getArchivesTypeID();
		this.tableType = officialArchivesInfoTable.getTableType();
		this.tableName = officialArchivesInfoTable.getTableName();
		this.createdFlag = officialArchivesInfoTable.getCreatedFlag();
		this.createdTime = officialArchivesInfoTable.getCreatedTime();
		this.remark = officialArchivesInfoTable.getRemark();
		this.keyInCol = officialArchivesInfoTable.getKeyInCol();
		this.tag = officialArchivesInfoTable.getTag();
	}



    
}



