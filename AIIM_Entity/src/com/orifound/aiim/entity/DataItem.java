package com.orifound.aiim.entity;

    
/**
 * 数据项字典表的实体类
 */
public class DataItem
{
    /**
     * 构造函数
     */
    public DataItem()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 数据项编号
	* @param officialArchivesFlag 公文档案标志
	* @param displayText 显示文本
	* @param columnName 字段名称
	* @param dataTypeID 数据类型编号
	* @param systemItemFlag 系统固有项标志
	* @param inputQueryFlag 著录查询标志
	* @param inputQueryOrderID 著录查询显示次序
	* @param useQueryFlag 利用查询标志
	* @param useQueryOrderID 利用查询显示次序
	* @param rangeQueryFlag 范围查询标志
	* @param associateTextColumnName 关联文本字段名
	* @param remark 备注
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
	* 带字段参数的构造函数
	* @param officialArchivesFlag 公文档案标志
	* @param displayText 显示文本
	* @param columnName 字段名称
	* @param dataTypeID 数据类型编号
	* @param systemItemFlag 系统固有项标志
	* @param inputQueryFlag 著录查询标志
	* @param inputQueryOrderID 著录查询显示次序
	* @param useQueryFlag 利用查询标志
	* @param useQueryOrderID 利用查询显示次序
	* @param rangeQueryFlag 范围查询标志
	* @param associateTextColumnName 关联文本字段名
	* @param remark 备注
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
	 * 获取字段的数据类型枚举值
	 * @return 字段的数据类型枚举值
	 */
	public EnumColumnDataType getColumnDataType()
	{
		return EnumColumnDataType.getEnumElement(dataTypeID);
	}
	
	/**
	 * 获取字段的系统固有数据项枚举值
	 * @return 字段的系统固有数据项枚举值
	 */
	public EnumSystemDataItem getSystemDataItemType()
	{
		return EnumSystemDataItem.getEnumElement(columnName);
	}

	/**
	 * 成员对象在集合中的关键字
	 */
	private Object keyInCol=null;

	/**
	 * 获取属性值：成员对象在集合中的关键字
	 * @return 成员对象在集合中的关键字
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}

	/**
	 * 设置属性值：成员对象在集合中的关键字
	 * @param keyInCol 成员对象在集合中的关键字
	 */
	public void setKeyInCol(Object keyInCol)
	{
		this.keyInCol = keyInCol;
	}

	/**
	 * 该数据项的附加对象，可以用来保存一些附加信息
	 */
	private Object tag=null;

	/**
	 * 获取属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * @return 该数据项的附加对象，可以用来保存一些附加信息
	 */
	public Object getTag()
	{
		return tag;
	}

	/**
	 * 设置属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * @param tag 该数据项的附加对象，可以用来保存一些附加信息
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	/**
	 * 数据项编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：数据项编号
	 * @return 数据项编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：数据项编号
	 * @param iD 数据项编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 公文档案标志
	 */
	private boolean officialArchivesFlag=false;

	/**
	 * 获取属性值：公文档案标志
	 * @return 公文档案标志
	 */
	public boolean getOfficialArchivesFlag()
	{
		return officialArchivesFlag;
	}

	/**
	 * 设置属性值：公文档案标志
	 * @param officialArchivesFlag 公文档案标志
	 */
	public void setOfficialArchivesFlag(boolean officialArchivesFlag)
	{
		this.officialArchivesFlag = officialArchivesFlag;
	}
	
	/**
	 * 显示文本
	 */
	private String displayText=null;

	/**
	 * 获取属性值：显示文本
	 * @return 显示文本
	 */
	public String getDisplayText()
	{
		return displayText;
	}

	/**
	 * 设置属性值：显示文本
	 * @param displayText 显示文本
	 */
	public void setDisplayText(String displayText)
	{
		this.displayText = displayText;
	}

	/**
	 * 字段名称
	 */
	private String columnName=null;

	/**
	 * 获取属性值：字段名称
	 * @return 字段名称
	 */
	public String getColumnName()
	{
		return columnName;
	}

	/**
	 * 设置属性值：字段名称
	 * @param columnName 字段名称
	 */
	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}

	/**
	 * 数据类型编号
	 */
	private String dataTypeID=null;

	/**
	 * 获取属性值：数据类型编号
	 * @return 数据类型编号
	 */
	public String getDataTypeID()
	{
		return dataTypeID;
	}

	/**
	 * 设置属性值：数据类型编号
	 * @param dataTypeID 数据类型编号
	 */
	public void setDataTypeID(String dataTypeID)
	{
		this.dataTypeID = dataTypeID;
	}

	/**
	 * 系统固有项标志
	 */
	private boolean systemItemFlag=false;

	/**
	 * 获取属性值：系统固有项标志
	 * @return 系统固有项标志
	 */
	public boolean getSystemItemFlag()
	{
		return systemItemFlag;
	}

	/**
	 * 设置属性值：系统固有项标志
	 * @param systemItemFlag 系统固有项标志
	 */
	public void setSystemItemFlag(boolean systemItemFlag)
	{
		this.systemItemFlag = systemItemFlag;
	}

	/**
	 * 著录查询标志
	 */
	private boolean inputQueryFlag=false;

	/**
	 * 获取属性值：著录查询标志
	 * @return 著录查询标志
	 */
	public boolean getInputQueryFlag()
	{
		return inputQueryFlag;
	}

	/**
	 * 设置属性值：著录查询标志
	 * @param inputQueryFlag 著录查询标志
	 */
	public void setInputQueryFlag(boolean inputQueryFlag)
	{
		this.inputQueryFlag = inputQueryFlag;
	}

	/**
	 * 著录查询显示次序
	 */
	private int inputQueryOrderID=0;

	/**
	 * 获取属性值：著录查询显示次序
	 * @return 著录查询显示次序
	 */
	public int getInputQueryOrderID()
	{
		return inputQueryOrderID;
	}

	/**
	 * 设置属性值：著录查询显示次序
	 * @param inputQueryOrderID 著录查询显示次序
	 */
	public void setInputQueryOrderID(int inputQueryOrderID)
	{
		this.inputQueryOrderID = inputQueryOrderID;
	}

	/**
	 * 利用查询标志
	 */
	private boolean useQueryFlag=false;

	/**
	 * 获取属性值：利用查询标志
	 * @return 利用查询标志
	 */
	public boolean getUseQueryFlag()
	{
		return useQueryFlag;
	}

	/**
	 * 设置属性值：利用查询标志
	 * @param useQueryFlag 利用查询标志
	 */
	public void setUseQueryFlag(boolean useQueryFlag)
	{
		this.useQueryFlag = useQueryFlag;
	}

	/**
	 * 利用查询显示次序
	 */
	private int useQueryOrderID=0;

	/**
	 * 获取属性值：利用查询显示次序
	 * @return 利用查询显示次序
	 */
	public int getUseQueryOrderID()
	{
		return useQueryOrderID;
	}

	/**
	 * 设置属性值：利用查询显示次序
	 * @param useQueryOrderID 利用查询显示次序
	 */
	public void setUseQueryOrderID(int useQueryOrderID)
	{
		this.useQueryOrderID = useQueryOrderID;
	}

	/**
	 * 范围查询标志
	 */
	private boolean rangeQueryFlag=false;

	/**
	 * 获取属性值：范围查询标志
	 * @return 范围查询标志
	 */
	public boolean getRangeQueryFlag()
	{
		return rangeQueryFlag;
	}

	/**
	 * 设置属性值：范围查询标志
	 * @param rangeQueryFlag 范围查询标志
	 */
	public void setRangeQueryFlag(boolean rangeQueryFlag)
	{
		this.rangeQueryFlag = rangeQueryFlag;
	}

	/**
	 * 关联文本字段名
	 */
	private String associateTextColumnName=null;

	/**
	 * 获取属性值：关联文本字段名
	 * @return 关联文本字段名
	 */
	public String getAssociateTextColumnName()
	{
		return associateTextColumnName;
	}

	/**
	 * 设置属性值：关联文本字段名
	 * @param associateTextColumnName 关联文本字段名
	 */
	public void setAssociateTextColumnName(String associateTextColumnName)
	{
		this.associateTextColumnName = associateTextColumnName;
	}

	/**
	 * 备注
	 */
	private String remark=null;

	/**
	 * 获取属性值：备注
	 * @return 备注
	 */
	public String getRemark()
	{
		return remark;
	}

	/**
	 * 设置属性值：备注
	 * @param remark 备注
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public DataItem clone()
	{
		DataItem item = new DataItem(iD,officialArchivesFlag,displayText,columnName,dataTypeID,systemItemFlag,inputQueryFlag,inputQueryOrderID,useQueryFlag,useQueryOrderID,rangeQueryFlag,associateTextColumnName,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param dataItem 指定的对象源
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



