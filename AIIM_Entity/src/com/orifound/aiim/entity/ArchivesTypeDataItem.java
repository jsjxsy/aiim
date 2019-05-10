package com.orifound.aiim.entity;


    
/**
 * ���������������ʵ����
 */
public class ArchivesTypeDataItem extends DataItem
{
    /**
     * ���캯��
     */
    public ArchivesTypeDataItem()
    {
        
    }
    
 	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param archivesTypeID ����������
	* @param dataItemID ��������
	* @param inputFlag ��¼���־
	* @param inputOrderID ��¼���д���
	* @param listDisplayFlag �б���ʾ��־
	* @param listDisplayOrderID �б���ʾ���д���
	* @param length �ֶγ���
	* @param mandatory �������
	* @param dataSourceID ����Դ���
	* @param defaultValue ȱʡֵ
	* @param editStyle �༭���
	* @param inputEditBoxWidth ��¼ʱ�������
	* @param inputEditBoxHeight ��¼ʱ�����߶�
	* @param queryEditBoxWidth ��ѯʱ�������
	* @param queryEditBoxHeight ��ѯʱ�����߶�
	* @param checkRuleID У�������
	* @param listDisplayLength �б���ʾ���
	* @param inputHoldFlag ��¼������־
	*/
	public ArchivesTypeDataItem(int iD,int archivesTypeID,int dataItemID,boolean inputFlag,int inputOrderID,boolean listDisplayFlag,int listDisplayOrderID,int length,boolean mandatory,int dataSourceID,String defaultValue,int editStyle,int inputEditBoxWidth,int inputEditBoxHeight,int queryEditBoxWidth,int queryEditBoxHeight,int checkRuleID,int listDisplayLength,boolean inputHoldFlag)
	{
		// Table Name: DDR_ArchivesType_DataItem
		// Columns List,Can Used in SELECT SQL: ID,OfficialArchivesFlag,ArchivesTypeID,DataItemID,InputFlag,InputOrderID,ListDisplayFlag,ListDisplayOrderID,Length,Mandatory,DataSourceID,DefaultValue,EditStyle,InputEditBoxWidth,InputEditBoxHeight,QueryEditBoxWidth,QueryEditBoxHeight,CheckRuleID,ListDisplayLength,InputHoldFlag
		// Columns List,Can Used in INSERT SQL: :ID,:OfficialArchivesFlag,:ArchivesTypeID,:DataItemID,:InputFlag,:InputOrderID,:ListDisplayFlag,:ListDisplayOrderID,:Length,:Mandatory,:DataSourceID,:DefaultValue,:EditStyle,:InputEditBoxWidth,:InputEditBoxHeight,:QueryEditBoxWidth,:QueryEditBoxHeight,:CheckRuleID,:ListDisplayLength,:InputHoldFlag
		// Columns List,Can Used in UPDATE SQL: ID=:ID,OfficialArchivesFlag=:OfficialArchivesFlag,ArchivesTypeID=:ArchivesTypeID,DataItemID=:DataItemID,InputFlag=:InputFlag,InputOrderID=:InputOrderID,ListDisplayFlag=:ListDisplayFlag,ListDisplayOrderID=:ListDisplayOrderID,Length=:Length,Mandatory=:Mandatory,DataSourceID=:DataSourceID,DefaultValue=:DefaultValue,EditStyle=:EditStyle,InputEditBoxWidth=:InputEditBoxWidth,InputEditBoxHeight=:InputEditBoxHeight,QueryEditBoxWidth=:QueryEditBoxWidth,QueryEditBoxHeight=:QueryEditBoxHeight,CheckRuleID=:CheckRuleID,ListDisplayLength=:ListDisplayLength,InputHoldFlag=:InputHoldFlag

		this(archivesTypeID, dataItemID, inputFlag, inputOrderID, listDisplayFlag, listDisplayOrderID, length, mandatory, dataSourceID, defaultValue, editStyle, inputEditBoxWidth, inputEditBoxHeight, queryEditBoxWidth, queryEditBoxHeight, checkRuleID, listDisplayLength,inputHoldFlag);
		this.iD = iD;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param archivesTypeID ����������
	* @param dataItemID ��������
	* @param inputFlag ��¼���־
	* @param inputOrderID ��¼���д���
	* @param listDisplayFlag �б���ʾ��־
	* @param listDisplayOrderID �б���ʾ���д���
	* @param length �ֶγ���
	* @param mandatory �������
	* @param dataSourceID ����Դ���
	* @param defaultValue ȱʡֵ
	* @param editStyle �༭���
	* @param inputEditBoxWidth ��¼ʱ�������
	* @param inputEditBoxHeight ��¼ʱ�����߶�
	* @param queryEditBoxWidth ��ѯʱ�������
	* @param queryEditBoxHeight ��ѯʱ�����߶�
	* @param checkRuleID У�������
	* @param listDisplayLength �б���ʾ���
	* @param inputHoldFlag ��¼������־
	*/
	public ArchivesTypeDataItem(int archivesTypeID,int dataItemID,boolean inputFlag,int inputOrderID,boolean listDisplayFlag,int listDisplayOrderID,int length,boolean mandatory,int dataSourceID,String defaultValue,int editStyle,int inputEditBoxWidth,int inputEditBoxHeight,int queryEditBoxWidth,int queryEditBoxHeight,int checkRuleID,int listDisplayLength,boolean inputHoldFlag)
	{
		this.archivesTypeID = archivesTypeID;
		this.dataItemID = dataItemID;
		this.inputFlag = inputFlag;
		this.inputOrderID = inputOrderID;
		this.listDisplayFlag = listDisplayFlag;
		this.listDisplayOrderID = listDisplayOrderID;
		this.length = length;
		this.mandatory = mandatory;
		this.dataSourceID = dataSourceID;
		this.defaultValue = defaultValue;
		this.editStyle =EnumEditStyle.getEnumElement(editStyle);
		this.inputEditBoxWidth = inputEditBoxWidth;
		this.inputEditBoxHeight = inputEditBoxHeight;
		this.queryEditBoxWidth = queryEditBoxWidth;
		this.queryEditBoxHeight = queryEditBoxHeight;
		this.checkRuleID = checkRuleID;
		this.listDisplayLength = listDisplayLength;
		this.inputHoldFlag = inputHoldFlag;
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
	* @param archivesTypeDataItemID ������������
	* @param archivesTypeID ����������
	* @param dataItemID ��������
	* @param inputFlag ��¼���־
	* @param inputOrderID ��¼���д���
	* @param listDisplayFlag �б���ʾ��־
	* @param listDisplayOrderID �б���ʾ���д���
	* @param length �ֶγ���
	* @param mandatory �������
	* @param dataSourceID ����Դ���
	* @param defaultValue ȱʡֵ
	* @param editStyle �༭���
	* @param inputEditBoxWidth ��¼ʱ�������
	* @param inputEditBoxHeight ��¼ʱ�����߶�
	* @param queryEditBoxWidth ��ѯʱ�������
	* @param queryEditBoxHeight ��ѯʱ�����߶�
	* @param checkRuleID У�������
	* @param listDisplayLength �б���ʾ���
	* @param inputHoldFlag ��¼������־
	*/
	public ArchivesTypeDataItem(boolean officialArchivesFlag,String displayText,String columnName,String dataTypeID,boolean systemItemFlag,boolean inputQueryFlag,int inputQueryOrderID,boolean useQueryFlag,int useQueryOrderID,boolean rangeQueryFlag,String associateTextColumnName,String remark,
			int archivesTypeDataItemID,int archivesTypeID,int dataItemID,boolean inputFlag,int inputOrderID,boolean listDisplayFlag,int listDisplayOrderID,int length,boolean mandatory,int dataSourceID,String defaultValue,int editStyle,int inputEditBoxWidth,int inputEditBoxHeight,int queryEditBoxWidth,int queryEditBoxHeight,int checkRuleID,int listDisplayLength,boolean inputHoldFlag)
	{

		this(archivesTypeDataItemID,archivesTypeID, dataItemID, inputFlag, inputOrderID, listDisplayFlag, listDisplayOrderID, length, mandatory, dataSourceID, defaultValue, editStyle, inputEditBoxWidth, inputEditBoxHeight, queryEditBoxWidth, queryEditBoxHeight, checkRuleID, listDisplayLength,inputHoldFlag);
		
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
	 * �ֶγ���
	 */
	private int length=0;

	/**
	 * ��ȡ����ֵ���ֶγ���
	 * @return �ֶγ���
	 */
	public int getLength()
	{
		return length;
	}

	/**
	 * ��������ֵ���ֶγ���
	 * @param length �ֶγ���
	 */
	public void setLength(int length)
	{
		this.length = length;
	}

	/**
	 * �������
	 */
	private boolean mandatory=false;

	/**
	 * ��ȡ����ֵ���������
	 * @return �������
	 */
	public boolean getMandatory()
	{
		return mandatory;
	}

	/**
	 * ��������ֵ���������
	 * @param mandatory �������
	 */
	public void setMandatory(boolean mandatory)
	{
		this.mandatory = mandatory;
	}

	/**
	 * ȱʡֵ
	 */
	private String defaultValue=null;

	/**
	 * ��ȡ����ֵ��ȱʡֵ
	 * @return ȱʡֵ
	 */
	public String getDefaultValue()
	{
		return defaultValue;
	}

	/**
	 * ��������ֵ��ȱʡֵ
	 * @param defaultValue ȱʡֵ
	 */
	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}

	/**
	 * �༭���
	 */
	private EnumEditStyle editStyle=EnumEditStyle.NONE;

	/**
	 * ��ȡ����ֵ���༭���
	 * @return �༭���
	 */
	public EnumEditStyle getEditStyle()
	{
		return editStyle;
	}

	/**
	 * ��������ֵ���༭���
	 * @param editStyle �༭���
	 */
	public void setEditStyle(EnumEditStyle editStyle)
	{
		this.editStyle = editStyle;
	}

	/**
	 * ��¼ʱ�������
	 */
	private int inputEditBoxWidth=0;

	/**
	 * ��ȡ����ֵ����¼ʱ�������
	 * @return ��¼ʱ�������
	 */
	public int getInputEditBoxWidth()
	{
		return inputEditBoxWidth;
	}

	/**
	 * ��������ֵ����¼ʱ�������
	 * @param inputEditBoxWidth ��¼ʱ�������
	 */
	public void setInputEditBoxWidth(int inputEditBoxWidth)
	{
		this.inputEditBoxWidth = inputEditBoxWidth;
	}

	/**
	 * ��¼ʱ�����߶�
	 */
	private int inputEditBoxHeight=0;

	/**
	 * ��ȡ����ֵ����¼ʱ�����߶�
	 * @return ��¼ʱ�����߶�
	 */
	public int getInputEditBoxHeight()
	{
		return inputEditBoxHeight;
	}

	/**
	 * ��������ֵ����¼ʱ�����߶�
	 * @param inputEditBoxHeight ��¼ʱ�����߶�
	 */
	public void setInputEditBoxHeight(int inputEditBoxHeight)
	{
		this.inputEditBoxHeight = inputEditBoxHeight;
	}

	/**
	 * ��ѯʱ�������
	 */
	private int queryEditBoxWidth=0;

	/**
	 * ��ȡ����ֵ����ѯʱ�������
	 * @return ��ѯʱ�������
	 */
	public int getQueryEditBoxWidth()
	{
		return queryEditBoxWidth;
	}

	/**
	 * ��������ֵ����ѯʱ�������
	 * @param queryEditBoxWidth ��ѯʱ�������
	 */
	public void setQueryEditBoxWidth(int queryEditBoxWidth)
	{
		this.queryEditBoxWidth = queryEditBoxWidth;
	}

	/**
	 * ��ѯʱ�����߶�
	 */
	private int queryEditBoxHeight=0;

	/**
	 * ��ȡ����ֵ����ѯʱ�����߶�
	 * @return ��ѯʱ�����߶�
	 */
	public int getQueryEditBoxHeight()
	{
		return queryEditBoxHeight;
	}

	/**
	 * ��������ֵ����ѯʱ�����߶�
	 * @param queryEditBoxHeight ��ѯʱ�����߶�
	 */
	public void setQueryEditBoxHeight(int queryEditBoxHeight)
	{
		this.queryEditBoxHeight = queryEditBoxHeight;
	}

	/**
	 * ����Դ���
	 */
	private int dataSourceID=0;

	/**
	 * ��ȡ����ֵ������Դ���
	 * @return ����Դ���
	 */
	public int getDataSourceID()
	{
		return dataSourceID;
	}

	/**
	 * ��������ֵ������Դ���
	 * @param dataSourceID ����Դ���
	 */
	public void setDataSourceID(int dataSourceID)
	{
		this.dataSourceID = dataSourceID;
	}

	/**
	 * У�������
	 */
	private int checkRuleID=0;

	/**
	 * ��ȡ����ֵ��У�������
	 * @return У�������
	 */
	public int getCheckRuleID()
	{
		return checkRuleID;
	}

	/**
	 * ��������ֵ��У�������
	 * @param checkRuleID У�������
	 */
	public void setCheckRuleID(int checkRuleID)
	{
		this.checkRuleID = checkRuleID;
	}
	
	/**
	 * ��¼���־
	 */
	private boolean inputFlag=false;

	/**
	 * ��ȡ����ֵ����¼���־
	 * @return ��¼���־
	 */
	public boolean getInputFlag()
	{
		return inputFlag;
	}

	/**
	 * ��������ֵ����¼���־
	 * @param inputFlag ��¼���־
	 */
	public void setInputFlag(boolean inputFlag)
	{
		this.inputFlag = inputFlag;
	}

	/**
	 * ��¼���д���
	 */
	private int inputOrderID=0;

	/**
	 * ��ȡ����ֵ����¼���д���
	 * @return ��¼���д���
	 */
	public int getInputOrderID()
	{
		return inputOrderID;
	}

	/**
	 * ��������ֵ����¼���д���
	 * @param inputOrderID ��¼���д���
	 */
	public void setInputOrderID(int inputOrderID)
	{
		this.inputOrderID = inputOrderID;
	}

	/**
	 * �б���ʾ��־
	 */
	private boolean listDisplayFlag=false;

	/**
	 * ��ȡ����ֵ���б���ʾ��־
	 * @return �б���ʾ��־
	 */
	public boolean getListDisplayFlag()
	{
		return listDisplayFlag;
	}

	/**
	 * ��������ֵ���б���ʾ��־
	 * @param listDisplayFlag �б���ʾ��־
	 */
	public void setListDisplayFlag(boolean listDisplayFlag)
	{
		this.listDisplayFlag = listDisplayFlag;
	}

	/**
	 * �б���ʾ���д���
	 */
	private int listDisplayOrderID=0;

	/**
	 * ��ȡ����ֵ���б���ʾ���д���
	 * @return �б���ʾ���д���
	 */
	public int getListDisplayOrderID()
	{
		return listDisplayOrderID;
	}

	/**
	 * ��������ֵ���б���ʾ���д���
	 * @param listDisplayOrderID �б���ʾ���д���
	 */
	public void setListDisplayOrderID(int listDisplayOrderID)
	{
		this.listDisplayOrderID = listDisplayOrderID;
	}
	
	/**
	 * �б���ʾ���
	 */
	private int listDisplayLength=0;

	/**
	 * ��ȡ����ֵ���б���ʾ���
	 * @return �б���ʾ���
	 */
	public int getListDisplayLength()
	{
		return listDisplayLength;
	}

	/**
	 * ��������ֵ���б���ʾ���
	 * @param listDisplayLength �б���ʾ���
	 */
	public void setListDisplayLength(int listDisplayLength)
	{
		this.listDisplayLength = listDisplayLength;
	}
	
	/**
	 * ��¼������־
	 */
	private boolean inputHoldFlag=false;

	/**
	 * ��ȡ����ֵ����¼������־
	 * @return ��¼������־
	 */
	public boolean getInputHoldFlag()
	{
		return inputHoldFlag;
	}

	/**
	 * ��������ֵ����¼������־
	 * @param inputHoldFlag ��¼������־
	 */
	public void setInputHoldFlag(boolean inputHoldFlag)
	{
		this.inputHoldFlag = inputHoldFlag;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ArchivesTypeDataItem clone()
	{
		ArchivesTypeDataItem item = new ArchivesTypeDataItem(getOfficialArchivesFlag(), getDisplayText(), getColumnName(), getDataTypeID(), getSystemItemFlag(), getInputQueryFlag(),getInputQueryOrderID(), getUseQueryFlag(),getUseQueryOrderID(), getRangeQueryFlag(),getAssociateTextColumnName(),getRemark(), iD, archivesTypeID, dataItemID, inputFlag, inputOrderID, listDisplayFlag, listDisplayOrderID, length, mandatory, dataSourceID, defaultValue, editStyle.getEnumValue(), inputEditBoxWidth, inputEditBoxHeight, queryEditBoxWidth, queryEditBoxHeight, checkRuleID, listDisplayLength,inputHoldFlag);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param archivesTypeDataItem ָ���Ķ���Դ
	*/
	public void cloneFrom(ArchivesTypeDataItem archivesTypeDataItem)
	{
		super.cloneFrom(archivesTypeDataItem);
		
		this.iD = archivesTypeDataItem.getID();
		this.archivesTypeID = archivesTypeDataItem.getArchivesTypeID();
		this.dataItemID = archivesTypeDataItem.getDataItemID();
		this.inputFlag = archivesTypeDataItem.getInputFlag();
		this.inputOrderID = archivesTypeDataItem.getInputOrderID();
		this.listDisplayFlag = archivesTypeDataItem.getListDisplayFlag();
		this.listDisplayOrderID = archivesTypeDataItem.getListDisplayOrderID();
		this.length = archivesTypeDataItem.getLength();
		this.mandatory = archivesTypeDataItem.getMandatory();
		this.dataSourceID = archivesTypeDataItem.getDataSourceID();
		this.defaultValue = archivesTypeDataItem.getDefaultValue();
		this.editStyle = archivesTypeDataItem.getEditStyle();
		this.inputEditBoxWidth = archivesTypeDataItem.getInputEditBoxWidth();
		this.inputEditBoxHeight = archivesTypeDataItem.getInputEditBoxHeight();
		this.queryEditBoxWidth = archivesTypeDataItem.getQueryEditBoxWidth();
		this.queryEditBoxHeight = archivesTypeDataItem.getQueryEditBoxHeight();
		this.checkRuleID = archivesTypeDataItem.getCheckRuleID();
		this.listDisplayLength = archivesTypeDataItem.getListDisplayLength();
		this.inputHoldFlag = archivesTypeDataItem.getInputHoldFlag();
		this.tag=archivesTypeDataItem.getTag();
		this.keyInCol=archivesTypeDataItem.getKeyInCol();
	}
	

	/**
	 * У�����
	 */
	private CheckRule checkRule = null;

	/**
	 * ��������ֵ��У�����
	 * @param checkRule У�����
	 */
	public void setCheckRule(CheckRule checkRule)
	{
		this.checkRule = checkRule;
	}

	/**
	 * ��ȡ����ֵ��У�����
	 * @return У�����
	 */
	public CheckRule getCheckRule()
	{
		return checkRule;
	}

	
	
	
}



