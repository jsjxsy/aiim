package com.orifound.aiim.entity;


    
/**
 * 档案分类的数据项实体类
 */
public class ArchivesTypeDataItem extends DataItem
{
    /**
     * 构造函数
     */
    public ArchivesTypeDataItem()
    {
        
    }
    
 	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param archivesTypeID 档案分类编号
	* @param dataItemID 数据项编号
	* @param inputFlag 著录项标志
	* @param inputOrderID 著录排列次序
	* @param listDisplayFlag 列表显示标志
	* @param listDisplayOrderID 列表显示排列次序
	* @param length 字段长度
	* @param mandatory 不允许空
	* @param dataSourceID 数据源编号
	* @param defaultValue 缺省值
	* @param editStyle 编辑风格
	* @param inputEditBoxWidth 著录时输入框宽度
	* @param inputEditBoxHeight 著录时输入框高度
	* @param queryEditBoxWidth 查询时输入框宽度
	* @param queryEditBoxHeight 查询时输入框高度
	* @param checkRuleID 校验规则编号
	* @param listDisplayLength 列表显示宽度
	* @param inputHoldFlag 著录保留标志
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
	* 带字段参数的构造函数
	* @param archivesTypeID 档案分类编号
	* @param dataItemID 数据项编号
	* @param inputFlag 著录项标志
	* @param inputOrderID 著录排列次序
	* @param listDisplayFlag 列表显示标志
	* @param listDisplayOrderID 列表显示排列次序
	* @param length 字段长度
	* @param mandatory 不允许空
	* @param dataSourceID 数据源编号
	* @param defaultValue 缺省值
	* @param editStyle 编辑风格
	* @param inputEditBoxWidth 著录时输入框宽度
	* @param inputEditBoxHeight 著录时输入框高度
	* @param queryEditBoxWidth 查询时输入框宽度
	* @param queryEditBoxHeight 查询时输入框高度
	* @param checkRuleID 校验规则编号
	* @param listDisplayLength 列表显示宽度
	* @param inputHoldFlag 著录保留标志
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
	* @param archivesTypeDataItemID 分类数据项编号
	* @param archivesTypeID 档案分类编号
	* @param dataItemID 数据项编号
	* @param inputFlag 著录项标志
	* @param inputOrderID 著录排列次序
	* @param listDisplayFlag 列表显示标志
	* @param listDisplayOrderID 列表显示排列次序
	* @param length 字段长度
	* @param mandatory 不允许空
	* @param dataSourceID 数据源编号
	* @param defaultValue 缺省值
	* @param editStyle 编辑风格
	* @param inputEditBoxWidth 著录时输入框宽度
	* @param inputEditBoxHeight 著录时输入框高度
	* @param queryEditBoxWidth 查询时输入框宽度
	* @param queryEditBoxHeight 查询时输入框高度
	* @param checkRuleID 校验规则编号
	* @param listDisplayLength 列表显示宽度
	* @param inputHoldFlag 著录保留标志
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
	 * 字段长度
	 */
	private int length=0;

	/**
	 * 获取属性值：字段长度
	 * @return 字段长度
	 */
	public int getLength()
	{
		return length;
	}

	/**
	 * 设置属性值：字段长度
	 * @param length 字段长度
	 */
	public void setLength(int length)
	{
		this.length = length;
	}

	/**
	 * 不允许空
	 */
	private boolean mandatory=false;

	/**
	 * 获取属性值：不允许空
	 * @return 不允许空
	 */
	public boolean getMandatory()
	{
		return mandatory;
	}

	/**
	 * 设置属性值：不允许空
	 * @param mandatory 不允许空
	 */
	public void setMandatory(boolean mandatory)
	{
		this.mandatory = mandatory;
	}

	/**
	 * 缺省值
	 */
	private String defaultValue=null;

	/**
	 * 获取属性值：缺省值
	 * @return 缺省值
	 */
	public String getDefaultValue()
	{
		return defaultValue;
	}

	/**
	 * 设置属性值：缺省值
	 * @param defaultValue 缺省值
	 */
	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}

	/**
	 * 编辑风格
	 */
	private EnumEditStyle editStyle=EnumEditStyle.NONE;

	/**
	 * 获取属性值：编辑风格
	 * @return 编辑风格
	 */
	public EnumEditStyle getEditStyle()
	{
		return editStyle;
	}

	/**
	 * 设置属性值：编辑风格
	 * @param editStyle 编辑风格
	 */
	public void setEditStyle(EnumEditStyle editStyle)
	{
		this.editStyle = editStyle;
	}

	/**
	 * 著录时输入框宽度
	 */
	private int inputEditBoxWidth=0;

	/**
	 * 获取属性值：著录时输入框宽度
	 * @return 著录时输入框宽度
	 */
	public int getInputEditBoxWidth()
	{
		return inputEditBoxWidth;
	}

	/**
	 * 设置属性值：著录时输入框宽度
	 * @param inputEditBoxWidth 著录时输入框宽度
	 */
	public void setInputEditBoxWidth(int inputEditBoxWidth)
	{
		this.inputEditBoxWidth = inputEditBoxWidth;
	}

	/**
	 * 著录时输入框高度
	 */
	private int inputEditBoxHeight=0;

	/**
	 * 获取属性值：著录时输入框高度
	 * @return 著录时输入框高度
	 */
	public int getInputEditBoxHeight()
	{
		return inputEditBoxHeight;
	}

	/**
	 * 设置属性值：著录时输入框高度
	 * @param inputEditBoxHeight 著录时输入框高度
	 */
	public void setInputEditBoxHeight(int inputEditBoxHeight)
	{
		this.inputEditBoxHeight = inputEditBoxHeight;
	}

	/**
	 * 查询时输入框宽度
	 */
	private int queryEditBoxWidth=0;

	/**
	 * 获取属性值：查询时输入框宽度
	 * @return 查询时输入框宽度
	 */
	public int getQueryEditBoxWidth()
	{
		return queryEditBoxWidth;
	}

	/**
	 * 设置属性值：查询时输入框宽度
	 * @param queryEditBoxWidth 查询时输入框宽度
	 */
	public void setQueryEditBoxWidth(int queryEditBoxWidth)
	{
		this.queryEditBoxWidth = queryEditBoxWidth;
	}

	/**
	 * 查询时输入框高度
	 */
	private int queryEditBoxHeight=0;

	/**
	 * 获取属性值：查询时输入框高度
	 * @return 查询时输入框高度
	 */
	public int getQueryEditBoxHeight()
	{
		return queryEditBoxHeight;
	}

	/**
	 * 设置属性值：查询时输入框高度
	 * @param queryEditBoxHeight 查询时输入框高度
	 */
	public void setQueryEditBoxHeight(int queryEditBoxHeight)
	{
		this.queryEditBoxHeight = queryEditBoxHeight;
	}

	/**
	 * 数据源编号
	 */
	private int dataSourceID=0;

	/**
	 * 获取属性值：数据源编号
	 * @return 数据源编号
	 */
	public int getDataSourceID()
	{
		return dataSourceID;
	}

	/**
	 * 设置属性值：数据源编号
	 * @param dataSourceID 数据源编号
	 */
	public void setDataSourceID(int dataSourceID)
	{
		this.dataSourceID = dataSourceID;
	}

	/**
	 * 校验规则编号
	 */
	private int checkRuleID=0;

	/**
	 * 获取属性值：校验规则编号
	 * @return 校验规则编号
	 */
	public int getCheckRuleID()
	{
		return checkRuleID;
	}

	/**
	 * 设置属性值：校验规则编号
	 * @param checkRuleID 校验规则编号
	 */
	public void setCheckRuleID(int checkRuleID)
	{
		this.checkRuleID = checkRuleID;
	}
	
	/**
	 * 著录项标志
	 */
	private boolean inputFlag=false;

	/**
	 * 获取属性值：著录项标志
	 * @return 著录项标志
	 */
	public boolean getInputFlag()
	{
		return inputFlag;
	}

	/**
	 * 设置属性值：著录项标志
	 * @param inputFlag 著录项标志
	 */
	public void setInputFlag(boolean inputFlag)
	{
		this.inputFlag = inputFlag;
	}

	/**
	 * 著录排列次序
	 */
	private int inputOrderID=0;

	/**
	 * 获取属性值：著录排列次序
	 * @return 著录排列次序
	 */
	public int getInputOrderID()
	{
		return inputOrderID;
	}

	/**
	 * 设置属性值：著录排列次序
	 * @param inputOrderID 著录排列次序
	 */
	public void setInputOrderID(int inputOrderID)
	{
		this.inputOrderID = inputOrderID;
	}

	/**
	 * 列表显示标志
	 */
	private boolean listDisplayFlag=false;

	/**
	 * 获取属性值：列表显示标志
	 * @return 列表显示标志
	 */
	public boolean getListDisplayFlag()
	{
		return listDisplayFlag;
	}

	/**
	 * 设置属性值：列表显示标志
	 * @param listDisplayFlag 列表显示标志
	 */
	public void setListDisplayFlag(boolean listDisplayFlag)
	{
		this.listDisplayFlag = listDisplayFlag;
	}

	/**
	 * 列表显示排列次序
	 */
	private int listDisplayOrderID=0;

	/**
	 * 获取属性值：列表显示排列次序
	 * @return 列表显示排列次序
	 */
	public int getListDisplayOrderID()
	{
		return listDisplayOrderID;
	}

	/**
	 * 设置属性值：列表显示排列次序
	 * @param listDisplayOrderID 列表显示排列次序
	 */
	public void setListDisplayOrderID(int listDisplayOrderID)
	{
		this.listDisplayOrderID = listDisplayOrderID;
	}
	
	/**
	 * 列表显示宽度
	 */
	private int listDisplayLength=0;

	/**
	 * 获取属性值：列表显示宽度
	 * @return 列表显示宽度
	 */
	public int getListDisplayLength()
	{
		return listDisplayLength;
	}

	/**
	 * 设置属性值：列表显示宽度
	 * @param listDisplayLength 列表显示宽度
	 */
	public void setListDisplayLength(int listDisplayLength)
	{
		this.listDisplayLength = listDisplayLength;
	}
	
	/**
	 * 著录保留标志
	 */
	private boolean inputHoldFlag=false;

	/**
	 * 获取属性值：著录保留标志
	 * @return 著录保留标志
	 */
	public boolean getInputHoldFlag()
	{
		return inputHoldFlag;
	}

	/**
	 * 设置属性值：著录保留标志
	 * @param inputHoldFlag 著录保留标志
	 */
	public void setInputHoldFlag(boolean inputHoldFlag)
	{
		this.inputHoldFlag = inputHoldFlag;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ArchivesTypeDataItem clone()
	{
		ArchivesTypeDataItem item = new ArchivesTypeDataItem(getOfficialArchivesFlag(), getDisplayText(), getColumnName(), getDataTypeID(), getSystemItemFlag(), getInputQueryFlag(),getInputQueryOrderID(), getUseQueryFlag(),getUseQueryOrderID(), getRangeQueryFlag(),getAssociateTextColumnName(),getRemark(), iD, archivesTypeID, dataItemID, inputFlag, inputOrderID, listDisplayFlag, listDisplayOrderID, length, mandatory, dataSourceID, defaultValue, editStyle.getEnumValue(), inputEditBoxWidth, inputEditBoxHeight, queryEditBoxWidth, queryEditBoxHeight, checkRuleID, listDisplayLength,inputHoldFlag);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param archivesTypeDataItem 指定的对象源
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
	 * 校验规则
	 */
	private CheckRule checkRule = null;

	/**
	 * 设置属性值：校验规则
	 * @param checkRule 校验规则
	 */
	public void setCheckRule(CheckRule checkRule)
	{
		this.checkRule = checkRule;
	}

	/**
	 * 获取属性值：校验规则
	 * @return 校验规则
	 */
	public CheckRule getCheckRule()
	{
		return checkRule;
	}

	
	
	
}



