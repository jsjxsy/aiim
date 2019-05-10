package com.orifound.aiim.entity;

    
/**
 * �������ֵ���ʵ����
 */
public class DataItem
{
    /**
     * ���캯��
     */
    public DataItem()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��������
	* @param officialArchivesFlag ���ĵ�����־
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
	public DataItem(int iD,boolean officialArchivesFlag,String displayText,String columnName,String dataTypeID,boolean systemItemFlag,boolean inputQueryFlag,int inputQueryOrderID,boolean useQueryFlag,int useQueryOrderID,boolean rangeQueryFlag,String associateTextColumnName,String remark)
	{
		// Table Name: DD_DataItem
		// Columns List,Can Used in SELECT SQL: ID,OfficialArchivesFlag,DisplayText,ColumnName,DataTypeID,SystemItemFlag,InputQueryFlag,InputQueryOrderID,UseQueryFlag,UseQueryOrderID,RangeQueryFlag,AssociateTextColumnName,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:OfficialArchivesFlag,:DisplayText,:ColumnName,:DataTypeID,:SystemItemFlag,:InputQueryFlag,:InputQueryOrderID,:UseQueryFlag,:UseQueryOrderID,:RangeQueryFlag,:AssociateTextColumnName,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,OfficialArchivesFlag=:OfficialArchivesFlag,DisplayText=:DisplayText,ColumnName=:ColumnName,DataTypeID=:DataTypeID,SystemItemFlag=:SystemItemFlag,InputQueryFlag=:InputQueryFlag,InputQueryOrderID=:InputQueryOrderID,UseQueryFlag=:UseQueryFlag,UseQueryOrderID=:UseQueryOrderID,RangeQueryFlag=:RangeQueryFlag,AssociateTextColumnName=:AssociateTextColumnName,Remark=:Remark

		this(officialArchivesFlag, displayText, columnName, dataTypeID, systemItemFlag, inputQueryFlag, inputQueryOrderID, useQueryFlag, useQueryOrderID, rangeQueryFlag, associateTextColumnName, remark);
		this.iD = iD;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param officialArchivesFlag ���ĵ�����־
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
	public DataItem(boolean officialArchivesFlag,String displayText,String columnName,String dataTypeID,boolean systemItemFlag,boolean inputQueryFlag,int inputQueryOrderID,boolean useQueryFlag,int useQueryOrderID,boolean rangeQueryFlag,String associateTextColumnName,String remark)
	{
		this.officialArchivesFlag = officialArchivesFlag;
		this.displayText = displayText;
		this.columnName = columnName;
		this.dataTypeID = dataTypeID;
		this.systemItemFlag = systemItemFlag;
		this.inputQueryFlag = inputQueryFlag;
		this.inputQueryOrderID = inputQueryOrderID;
		this.useQueryFlag = useQueryFlag;
		this.useQueryOrderID = useQueryOrderID;
		this.rangeQueryFlag = rangeQueryFlag;
		this.associateTextColumnName = associateTextColumnName;
		this.remark = remark;
	}
	
	/**
	 * ��ȡ�ֶε���������ö��ֵ
	 * @return �ֶε���������ö��ֵ
	 */
	public EnumColumnDataType getColumnDataType()
	{
		return EnumColumnDataType.getEnumElement(dataTypeID);
	}
	
	/**
	 * ��ȡ�ֶε�ϵͳ����������ö��ֵ
	 * @return �ֶε�ϵͳ����������ö��ֵ
	 */
	public EnumSystemDataItem getSystemDataItemType()
	{
		return EnumSystemDataItem.getEnumElement(columnName);
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
	 * ��������
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ����������
	 * @param iD ��������
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
	 * ��ʾ�ı�
	 */
	private String displayText=null;

	/**
	 * ��ȡ����ֵ����ʾ�ı�
	 * @return ��ʾ�ı�
	 */
	public String getDisplayText()
	{
		return displayText;
	}

	/**
	 * ��������ֵ����ʾ�ı�
	 * @param displayText ��ʾ�ı�
	 */
	public void setDisplayText(String displayText)
	{
		this.displayText = displayText;
	}

	/**
	 * �ֶ�����
	 */
	private String columnName=null;

	/**
	 * ��ȡ����ֵ���ֶ�����
	 * @return �ֶ�����
	 */
	public String getColumnName()
	{
		return columnName;
	}

	/**
	 * ��������ֵ���ֶ�����
	 * @param columnName �ֶ�����
	 */
	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}

	/**
	 * �������ͱ��
	 */
	private String dataTypeID=null;

	/**
	 * ��ȡ����ֵ���������ͱ��
	 * @return �������ͱ��
	 */
	public String getDataTypeID()
	{
		return dataTypeID;
	}

	/**
	 * ��������ֵ���������ͱ��
	 * @param dataTypeID �������ͱ��
	 */
	public void setDataTypeID(String dataTypeID)
	{
		this.dataTypeID = dataTypeID;
	}

	/**
	 * ϵͳ�������־
	 */
	private boolean systemItemFlag=false;

	/**
	 * ��ȡ����ֵ��ϵͳ�������־
	 * @return ϵͳ�������־
	 */
	public boolean getSystemItemFlag()
	{
		return systemItemFlag;
	}

	/**
	 * ��������ֵ��ϵͳ�������־
	 * @param systemItemFlag ϵͳ�������־
	 */
	public void setSystemItemFlag(boolean systemItemFlag)
	{
		this.systemItemFlag = systemItemFlag;
	}

	/**
	 * ��¼��ѯ��־
	 */
	private boolean inputQueryFlag=false;

	/**
	 * ��ȡ����ֵ����¼��ѯ��־
	 * @return ��¼��ѯ��־
	 */
	public boolean getInputQueryFlag()
	{
		return inputQueryFlag;
	}

	/**
	 * ��������ֵ����¼��ѯ��־
	 * @param inputQueryFlag ��¼��ѯ��־
	 */
	public void setInputQueryFlag(boolean inputQueryFlag)
	{
		this.inputQueryFlag = inputQueryFlag;
	}

	/**
	 * ��¼��ѯ��ʾ����
	 */
	private int inputQueryOrderID=0;

	/**
	 * ��ȡ����ֵ����¼��ѯ��ʾ����
	 * @return ��¼��ѯ��ʾ����
	 */
	public int getInputQueryOrderID()
	{
		return inputQueryOrderID;
	}

	/**
	 * ��������ֵ����¼��ѯ��ʾ����
	 * @param inputQueryOrderID ��¼��ѯ��ʾ����
	 */
	public void setInputQueryOrderID(int inputQueryOrderID)
	{
		this.inputQueryOrderID = inputQueryOrderID;
	}

	/**
	 * ���ò�ѯ��־
	 */
	private boolean useQueryFlag=false;

	/**
	 * ��ȡ����ֵ�����ò�ѯ��־
	 * @return ���ò�ѯ��־
	 */
	public boolean getUseQueryFlag()
	{
		return useQueryFlag;
	}

	/**
	 * ��������ֵ�����ò�ѯ��־
	 * @param useQueryFlag ���ò�ѯ��־
	 */
	public void setUseQueryFlag(boolean useQueryFlag)
	{
		this.useQueryFlag = useQueryFlag;
	}

	/**
	 * ���ò�ѯ��ʾ����
	 */
	private int useQueryOrderID=0;

	/**
	 * ��ȡ����ֵ�����ò�ѯ��ʾ����
	 * @return ���ò�ѯ��ʾ����
	 */
	public int getUseQueryOrderID()
	{
		return useQueryOrderID;
	}

	/**
	 * ��������ֵ�����ò�ѯ��ʾ����
	 * @param useQueryOrderID ���ò�ѯ��ʾ����
	 */
	public void setUseQueryOrderID(int useQueryOrderID)
	{
		this.useQueryOrderID = useQueryOrderID;
	}

	/**
	 * ��Χ��ѯ��־
	 */
	private boolean rangeQueryFlag=false;

	/**
	 * ��ȡ����ֵ����Χ��ѯ��־
	 * @return ��Χ��ѯ��־
	 */
	public boolean getRangeQueryFlag()
	{
		return rangeQueryFlag;
	}

	/**
	 * ��������ֵ����Χ��ѯ��־
	 * @param rangeQueryFlag ��Χ��ѯ��־
	 */
	public void setRangeQueryFlag(boolean rangeQueryFlag)
	{
		this.rangeQueryFlag = rangeQueryFlag;
	}

	/**
	 * �����ı��ֶ���
	 */
	private String associateTextColumnName=null;

	/**
	 * ��ȡ����ֵ�������ı��ֶ���
	 * @return �����ı��ֶ���
	 */
	public String getAssociateTextColumnName()
	{
		return associateTextColumnName;
	}

	/**
	 * ��������ֵ�������ı��ֶ���
	 * @param associateTextColumnName �����ı��ֶ���
	 */
	public void setAssociateTextColumnName(String associateTextColumnName)
	{
		this.associateTextColumnName = associateTextColumnName;
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
	public DataItem clone()
	{
		DataItem item = new DataItem(iD,officialArchivesFlag,displayText,columnName,dataTypeID,systemItemFlag,inputQueryFlag,inputQueryOrderID,useQueryFlag,useQueryOrderID,rangeQueryFlag,associateTextColumnName,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param dataItem ָ���Ķ���Դ
	*/
	public void cloneFrom(DataItem dataItem)
	{
		this.iD = dataItem.getID();
		this.officialArchivesFlag = dataItem.getOfficialArchivesFlag();
		this.displayText = dataItem.getDisplayText();
		this.columnName = dataItem.getColumnName();
		this.dataTypeID = dataItem.getDataTypeID();
		this.systemItemFlag = dataItem.getSystemItemFlag();
		this.inputQueryFlag = dataItem.getInputQueryFlag();
		this.inputQueryOrderID = dataItem.getInputQueryOrderID();
		this.useQueryFlag = dataItem.getUseQueryFlag();
		this.useQueryOrderID = dataItem.getUseQueryOrderID();
		this.rangeQueryFlag = dataItem.getRangeQueryFlag();
		this.associateTextColumnName = dataItem.getAssociateTextColumnName();
		this.remark = dataItem.getRemark();
		this.keyInCol = dataItem.getKeyInCol();
		this.tag = dataItem.getTag();
	}


}



