package com.orifound.aiim.entity;

    
/**
 * Ŀ¼��ӡģ�����������ʵ����
 */
public class CatalogDataItem extends DataItem
{
    /**
     * ���캯��
     */
    public CatalogDataItem()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param officialArchivesFlag ���ĵ�����־
	* @param archivesTypeID ����������
	* @param catalogType Ŀ¼����
	* @param dataItemID ��������
	* @param displayAlias ��ʾ����
	* @param displayOrderID ��ʾ����
	* @param displayLength ��ʾ����
	*/
	public CatalogDataItem(int iD,boolean officialArchivesFlag,int archivesTypeID,int catalogType,int dataItemID,String displayAlias,int displayOrderID,int displayLength)
	{
		// Table Name: DDR_CatalogPrintTemplate_DataItem
		// Columns List,Can Used in SELECT SQL: ID,OfficialArchivesFlag,ArchivesTypeID,CatalogType,DataItemID,DisplayAlias,DisplayOrderID,DisplayLength
		// Columns List,Can Used in INSERT SQL: :ID,:OfficialArchivesFlag,:ArchivesTypeID,:CatalogType,:DataItemID,:DisplayAlias,:DisplayOrderID,:DisplayLength
		// Columns List,Can Used in UPDATE SQL: ID=:ID,OfficialArchivesFlag=:OfficialArchivesFlag,ArchivesTypeID=:ArchivesTypeID,CatalogType=:CatalogType,DataItemID=:DataItemID,DisplayAlias=:DisplayAlias,DisplayOrderID=:DisplayOrderID,DisplayLength=:DisplayLength

		this(officialArchivesFlag, archivesTypeID, catalogType, dataItemID, displayAlias, displayOrderID, displayLength);
		this.iD = iD;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param officialArchivesFlag ���ĵ�����־
	* @param archivesTypeID ����������
	* @param catalogType Ŀ¼����
	* @param dataItemID ��������
	* @param displayAlias ��ʾ����
	* @param displayOrderID ��ʾ����
	* @param displayLength ��ʾ����
	*/
	public CatalogDataItem(boolean officialArchivesFlag,int archivesTypeID,int catalogType,int dataItemID,String displayAlias,int displayOrderID,int displayLength)
	{
		this.officialArchivesFlag = officialArchivesFlag;
		this.archivesTypeID = archivesTypeID;
		this.catalogType =EnumCatalogType.getEnumElement(catalogType);
		this.dataItemID = dataItemID;
		this.displayAlias = displayAlias;
		this.displayOrderID = displayOrderID;
		this.displayLength = displayLength;
	}
	
	/**
	* ���ֶβ����Ĺ��캯�����������������
	* @param iD ���
	* @param officialArchivesFlag ���ĵ�����־
	* @param archivesTypeID ����������
	* @param catalogType Ŀ¼����
	* @param dataItemID ��������
	* @param displayAlias ��ʾ����
	* @param displayOrderID ��ʾ����
	* @param displayLength ��ʾ����
	* @param displayText ��ʾ�ı�
	* @param columnName �ֶ�����
	* @param dataTypeID �������ͱ��
	* @param systemItemFlag ϵͳ�������־
	* @param inputQueryFlag ��¼��ѯ��־
	* @param inputQueryOrderID ��¼��ѯ��ʾ����
	* @param useQueryFlag ���ò�ѯ��־
	* @param useQueryOrderID ���ò�ѯ��ʾ����
	* @param rangeQueryFlag ��Χ��ѯ��־
	* @param associateTextColumnName �����ı��ֶ���
	* @param remark ��ע
	*/
	public CatalogDataItem(int iD,boolean officialArchivesFlag,int archivesTypeID,int catalogType,int dataItemID,String displayAlias,int displayOrderID,int displayLength,
			String displayText,String columnName,String dataTypeID,boolean systemItemFlag,boolean inputQueryFlag,int inputQueryOrderID,boolean useQueryFlag,int useQueryOrderID,boolean rangeQueryFlag,String associateTextColumnName,String remark)
	{
		this(iD,officialArchivesFlag, archivesTypeID, catalogType, dataItemID, displayAlias, displayOrderID, displayLength);

		super.setOfficialArchivesFlag(officialArchivesFlag);
		super.setDisplayText(displayText);
		super.setColumnName(columnName);
		super.setDataTypeID(dataTypeID);
		super.setSystemItemFlag(systemItemFlag);
		super.setInputQueryFlag(inputQueryFlag);
		super.setInputQueryOrderID(inputQueryOrderID);
		super.setUseQueryFlag(useQueryFlag);
		super.setUseQueryOrderID(useQueryOrderID);
		super.setRangeQueryFlag(rangeQueryFlag);
		super.setAssociateTextColumnName(associateTextColumnName);
		super.setRemark(remark);
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
	 * ���ĵ�����־
	 */
	private boolean officialArchivesFlag=false;

	/**
	 * ��ȡ����ֵ�����ĵ�����־
	 * @return ���ĵ�����־
	 */
	public boolean getOfficialArchivesFlag()
	{
		return officialArchivesFlag;
	}

	/**
	 * ��������ֵ�����ĵ�����־
	 * @param officialArchivesFlag ���ĵ�����־
	 */
	public void setOfficialArchivesFlag(boolean officialArchivesFlag)
	{
		this.officialArchivesFlag = officialArchivesFlag;
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
	 * Ŀ¼����
	 */
	private EnumCatalogType catalogType=EnumCatalogType.NONE;

	/**
	 * ��ȡ����ֵ��Ŀ¼����
	 * @return Ŀ¼����
	 */
	public EnumCatalogType getCatalogType()
	{
		return catalogType;
	}

	/**
	 * ��������ֵ��Ŀ¼����
	 * @param catalogType Ŀ¼����
	 */
	public void setCatalogType(EnumCatalogType catalogType)
	{
		this.catalogType = catalogType;
	}

	/**
	 * ��������
	 */
	private int dataItemID=0;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public int getDataItemID()
	{
		return dataItemID;
	}

	/**
	 * ��������ֵ����������
	 * @param dataItemID ��������
	 */
	public void setDataItemID(int dataItemID)
	{
		this.dataItemID = dataItemID;
	}

	/**
	 * ��ʾ����
	 */
	private String displayAlias=null;

	/**
	 * ��ȡ����ֵ����ʾ����
	 * @return ��ʾ����
	 */
	public String getDisplayAlias()
	{
		return displayAlias;
	}

	/**
	 * ��������ֵ����ʾ����
	 * @param displayAlias ��ʾ����
	 */
	public void setDisplayAlias(String displayAlias)
	{
		this.displayAlias = displayAlias;
	}

	/**
	 * ��ʾ����
	 */
	private int displayOrderID=0;

	/**
	 * ��ȡ����ֵ����ʾ����
	 * @return ��ʾ����
	 */
	public int getDisplayOrderID()
	{
		return displayOrderID;
	}

	/**
	 * ��������ֵ����ʾ����
	 * @param displayOrderID ��ʾ����
	 */
	public void setDisplayOrderID(int displayOrderID)
	{
		this.displayOrderID = displayOrderID;
	}

	/**
	 * ��ʾ����
	 */
	private int displayLength=0;

	/**
	 * ��ȡ����ֵ����ʾ����
	 * @return ��ʾ����
	 */
	public int getDisplayLength()
	{
		return displayLength;
	}

	/**
	 * ��������ֵ����ʾ����
	 * @param displayLength ��ʾ����
	 */
	public void setDisplayLength(int displayLength)
	{
		this.displayLength = displayLength;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public CatalogDataItem clone()
	{
		CatalogDataItem item = new CatalogDataItem(iD,officialArchivesFlag,archivesTypeID,catalogType.getEnumValue(),dataItemID,displayAlias,displayOrderID,displayLength,getDisplayText(), getColumnName(), getDataTypeID(), getSystemItemFlag(), getInputQueryFlag(),getInputQueryOrderID(), getUseQueryFlag(),getUseQueryOrderID(), getRangeQueryFlag(),getAssociateTextColumnName(),getRemark());
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param catalogDataItem ָ���Ķ���Դ
	*/
	public void cloneFrom(CatalogDataItem catalogDataItem)
	{
		super.cloneFrom(catalogDataItem);
		
		this.iD = catalogDataItem.getID();
		this.officialArchivesFlag = catalogDataItem.getOfficialArchivesFlag();
		this.archivesTypeID = catalogDataItem.getArchivesTypeID();
		this.catalogType = catalogDataItem.getCatalogType();
		this.dataItemID = catalogDataItem.getDataItemID();
		this.displayAlias = catalogDataItem.getDisplayAlias();
		this.displayOrderID = catalogDataItem.getDisplayOrderID();
		this.displayLength = catalogDataItem.getDisplayLength();
		this.keyInCol = catalogDataItem.getKeyInCol();
		this.tag = catalogDataItem.getTag();
	}



    
}



