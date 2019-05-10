package com.orifound.aiim.entity;

import java.util.Map;

    
/**
 * ���ĵ�������Ĺ鵵ӳ���ϵ��ʵ����
 */
public class OfficialArchivesTypeSavedMapping
{
    /**
     * ���캯��
     */
    public OfficialArchivesTypeSavedMapping()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param officialArchivesTypeID ���ĵ���������
	* @param archivesTypeID �鵵ӳ���ʵ�嵵��������
	* @param remark ��ע
	*/
	public OfficialArchivesTypeSavedMapping(int iD,int officialArchivesTypeID,int archivesTypeID,String remark)
	{
		// Table Name: DD_OfiicialArchivesTypeSavedMapping
		// Columns List,Can Used in SELECT SQL: ID,OfficialArchivesTypeID,ArchivesTypeID,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:OfficialArchivesTypeID,:ArchivesTypeID,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,OfficialArchivesTypeID=:OfficialArchivesTypeID,ArchivesTypeID=:ArchivesTypeID,Remark=:Remark

		this.iD = iD;
		this.officialArchivesTypeID = officialArchivesTypeID;
		this.archivesTypeID = archivesTypeID;
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
	 * ���ĵ���������
	 */
	private int officialArchivesTypeID=0;

	/**
	 * ��ȡ����ֵ�����ĵ���������
	 * @return ���ĵ���������
	 */
	public int getOfficialArchivesTypeID()
	{
		return officialArchivesTypeID;
	}

	/**
	 * ��������ֵ�����ĵ���������
	 * @param officialArchivesTypeID ���ĵ���������
	 */
	public void setOfficialArchivesTypeID(int officialArchivesTypeID)
	{
		this.officialArchivesTypeID = officialArchivesTypeID;
	}

	/**
	 * �鵵ӳ���ʵ�嵵��������
	 */
	private int archivesTypeID=0;

	/**
	 * ��ȡ����ֵ���鵵ӳ���ʵ�嵵��������
	 * @return �鵵ӳ���ʵ�嵵��������
	 */
	public int getArchivesTypeID()
	{
		return archivesTypeID;
	}

	/**
	 * ��������ֵ���鵵ӳ���ʵ�嵵��������
	 * @param archivesTypeID �鵵ӳ���ʵ�嵵��������
	 */
	public void setArchivesTypeID(int archivesTypeID)
	{
		this.archivesTypeID = archivesTypeID;
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
	public OfficialArchivesTypeSavedMapping clone()
	{
		OfficialArchivesTypeSavedMapping item = new OfficialArchivesTypeSavedMapping(iD,officialArchivesTypeID,archivesTypeID,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param officialArchivesTypeSavedMapping ָ���Ķ���Դ
	*/
	public void cloneFrom(OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping)
	{
		this.iD = officialArchivesTypeSavedMapping.getID();
		this.officialArchivesTypeID = officialArchivesTypeSavedMapping.getOfficialArchivesTypeID();
		this.archivesTypeID = officialArchivesTypeSavedMapping.getArchivesTypeID();
		this.remark = officialArchivesTypeSavedMapping.getRemark();
		this.keyInCol = officialArchivesTypeSavedMapping.getKeyInCol();
		this.tag = officialArchivesTypeSavedMapping.getTag();
	}


	/**
	 * ���ĵ�������鵵������ʵ�����ʱ��������ӳ���ϵ<br>
	 * ��ʵ���������Ϊ���Ϲؼ���
	 * �ڶ���Map<Integer, OfficialArchivesDataItemSavedMapping>��װ�������ӳ��key--->DesDataItemID,value--->OfficialArchivesDataItemSavedMapping ����
	 */
	private Map<Integer, OfficialArchivesDataItemSavedMapping> officialArchivesDataItemSavedMapping;
	
	/**
	 * ���ĵ�������鵵������ʵ�����ʱ��������ӳ���ϵ<br>
	 * ��Դ����������Ϊ���Ϲؼ���
	 * ��װ�������ӳ��key--->DesDataItemID,value--->OfficialArchivesDataItemSavedMapping ����
	 */
	public Map<Integer, OfficialArchivesDataItemSavedMapping> getOfficialArchivesDataItemSavedMapping() {
		return officialArchivesDataItemSavedMapping;
	}
	
	/**
	 * ���ĵ�������鵵������ʵ�����ʱ��������ӳ���ϵ<br>
	 * ��ʵ���������Ϊ���Ϲؼ���
	 * �ڶ���Map<Integer, OfficialArchivesDataItemSavedMapping>��װ�������ӳ��key--->DesDataItemID,value--->OfficialArchivesDataItemSavedMapping ����
	 */
	public void setOfficialArchivesDataItemSavedMapping(Map<Integer, OfficialArchivesDataItemSavedMapping> officialArchivesDataItemSavedMapping) {
		this.officialArchivesDataItemSavedMapping = officialArchivesDataItemSavedMapping;
	}
    
	
}



