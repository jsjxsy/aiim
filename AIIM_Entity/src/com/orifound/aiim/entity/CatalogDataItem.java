package com.orifound.aiim.entity;

    
/**
 * 目录打印模板数据项定义表的实体类
 */
public class CatalogDataItem extends DataItem
{
    /**
     * 构造函数
     */
    public CatalogDataItem()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param officialArchivesFlag 公文档案标志
	* @param archivesTypeID 档案分类编号
	* @param catalogType 目录类型
	* @param dataItemID 数据项编号
	* @param displayAlias 显示别名
	* @param displayOrderID 显示次序
	* @param displayLength 显示长度
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
	* 带字段参数的构造函数
	* @param officialArchivesFlag 公文档案标志
	* @param archivesTypeID 档案分类编号
	* @param catalogType 目录类型
	* @param dataItemID 数据项编号
	* @param displayAlias 显示别名
	* @param displayOrderID 显示次序
	* @param displayLength 显示长度
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
	* 带字段参数的构造函数（包括父类数据项）
	* @param iD 编号
	* @param officialArchivesFlag 公文档案标志
	* @param archivesTypeID 档案分类编号
	* @param catalogType 目录类型
	* @param dataItemID 数据项编号
	* @param displayAlias 显示别名
	* @param displayOrderID 显示次序
	* @param displayLength 显示长度
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
	 * 编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：编号
	 * @return 编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：编号
	 * @param iD 编号
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
	 * 档案分类编号
	 */
	private int archivesTypeID=0;

	/**
	 * 获取属性值：档案分类编号
	 * @return 档案分类编号
	 */
	public int getArchivesTypeID()
	{
		return archivesTypeID;
	}

	/**
	 * 设置属性值：档案分类编号
	 * @param archivesTypeID 档案分类编号
	 */
	public void setArchivesTypeID(int archivesTypeID)
	{
		this.archivesTypeID = archivesTypeID;
	}

	/**
	 * 目录类型
	 */
	private EnumCatalogType catalogType=EnumCatalogType.NONE;

	/**
	 * 获取属性值：目录类型
	 * @return 目录类型
	 */
	public EnumCatalogType getCatalogType()
	{
		return catalogType;
	}

	/**
	 * 设置属性值：目录类型
	 * @param catalogType 目录类型
	 */
	public void setCatalogType(EnumCatalogType catalogType)
	{
		this.catalogType = catalogType;
	}

	/**
	 * 数据项编号
	 */
	private int dataItemID=0;

	/**
	 * 获取属性值：数据项编号
	 * @return 数据项编号
	 */
	public int getDataItemID()
	{
		return dataItemID;
	}

	/**
	 * 设置属性值：数据项编号
	 * @param dataItemID 数据项编号
	 */
	public void setDataItemID(int dataItemID)
	{
		this.dataItemID = dataItemID;
	}

	/**
	 * 显示别名
	 */
	private String displayAlias=null;

	/**
	 * 获取属性值：显示别名
	 * @return 显示别名
	 */
	public String getDisplayAlias()
	{
		return displayAlias;
	}

	/**
	 * 设置属性值：显示别名
	 * @param displayAlias 显示别名
	 */
	public void setDisplayAlias(String displayAlias)
	{
		this.displayAlias = displayAlias;
	}

	/**
	 * 显示次序
	 */
	private int displayOrderID=0;

	/**
	 * 获取属性值：显示次序
	 * @return 显示次序
	 */
	public int getDisplayOrderID()
	{
		return displayOrderID;
	}

	/**
	 * 设置属性值：显示次序
	 * @param displayOrderID 显示次序
	 */
	public void setDisplayOrderID(int displayOrderID)
	{
		this.displayOrderID = displayOrderID;
	}

	/**
	 * 显示长度
	 */
	private int displayLength=0;

	/**
	 * 获取属性值：显示长度
	 * @return 显示长度
	 */
	public int getDisplayLength()
	{
		return displayLength;
	}

	/**
	 * 设置属性值：显示长度
	 * @param displayLength 显示长度
	 */
	public void setDisplayLength(int displayLength)
	{
		this.displayLength = displayLength;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public CatalogDataItem clone()
	{
		CatalogDataItem item = new CatalogDataItem(iD,officialArchivesFlag,archivesTypeID,catalogType.getEnumValue(),dataItemID,displayAlias,displayOrderID,displayLength,getDisplayText(), getColumnName(), getDataTypeID(), getSystemItemFlag(), getInputQueryFlag(),getInputQueryOrderID(), getUseQueryFlag(),getUseQueryOrderID(), getRangeQueryFlag(),getAssociateTextColumnName(),getRemark());
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param catalogDataItem 指定的对象源
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



