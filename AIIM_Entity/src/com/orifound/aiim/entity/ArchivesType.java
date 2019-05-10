package com.orifound.aiim.entity;

import java.util.EnumMap;
import java.util.LinkedHashMap;
    
/**
 * 档案分类字典表的实体类
 */
public class ArchivesType extends BaseRightArchivesResource
{
    /**
     * 构造函数
     */
    public ArchivesType()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 档案分类编号
	* @param topFlag 一级类目标志
	* @param name 档案分类名称
	* @param code 分类代码
	* @param parentID 上级档案分类编号
	* @param topTypeID 顶层分类编号
	* @param fullCode 完整代码
	* @param fullName 完整名称
	* @param endFlag 最底层标志
	* @param contentIDFormatLength 案卷号的格式化长度
	* @param subContentIDFormatLength 卷内文件序号的格式化长度
	* @param archivesIDExpressionPrefix 档号前缀组成表达式
	* @param archivesIDExpression 档号组成表达式
	* @param subArchivesIDExpression 卷内文件的档号组成表达式
	* @param attachedFileSavePath 原文存储路径
	* @param renewPeriod 续借周期
	* @param personalArchivesFlag 人事档案标志
	* @param remark 备注
	*/
	public ArchivesType(int iD,boolean topFlag,String name,String code,int parentID,int topTypeID,String fullCode,String fullName,boolean endFlag,int contentIDFormatLength,int subContentIDFormatLength,String archivesIDExpressionPrefix,String archivesIDExpression,String subArchivesIDExpression,String attachedFileSavePath,int renewPeriod,int personalArchivesFlag,String remark)
	{
		// Table Name: DD_ArchivesType
		// Columns List,Can Used in SELECT SQL: ID,TopFlag,Name,Code,ParentID,TopTypeID,FullCode,FullName,EndFlag,ContentIDFormatLength,SubContentIDFormatLength,ArchivesIDExpressionPrefix,ArchivesIDExpression,SubArchivesIDExpression,AttachedFileSavePath,RenewPeriod,PersonalArchivesFlag,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:TopFlag,:Name,:Code,:ParentID,:TopTypeID,:FullCode,:FullName,:EndFlag,:ContentIDFormatLength,:SubContentIDFormatLength,:ArchivesIDExpressionPrefix,:ArchivesIDExpression,:SubArchivesIDExpression,:AttachedFileSavePath,:RenewPeriod,:PersonalArchivesFlag,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,TopFlag=:TopFlag,Name=:Name,Code=:Code,ParentID=:ParentID,TopTypeID=:TopTypeID,FullCode=:FullCode,FullName=:FullName,EndFlag=:EndFlag,ContentIDFormatLength=:ContentIDFormatLength,SubContentIDFormatLength=:SubContentIDFormatLength,ArchivesIDExpressionPrefix=:ArchivesIDExpressionPrefix,ArchivesIDExpression=:ArchivesIDExpression,SubArchivesIDExpression=:SubArchivesIDExpression,AttachedFileSavePath=:AttachedFileSavePath,RenewPeriod=:RenewPeriod,PersonalArchivesFlag=:PersonalArchivesFlag,Remark=:Remark

		this(topFlag, name, code, parentID, topTypeID, fullCode, fullName, endFlag, contentIDFormatLength, subContentIDFormatLength, archivesIDExpressionPrefix,archivesIDExpression,subArchivesIDExpression, attachedFileSavePath, renewPeriod,personalArchivesFlag, remark);
		this.iD = iD;
	}
	
	/**
	* 带字段参数的构造函数
	* @param topFlag 一级类目标志
	* @param name 档案分类名称
	* @param code 分类代码
	* @param parentID 上级档案分类编号
	* @param topTypeID 顶层分类编号
	* @param fullCode 完整代码
	* @param fullName 完整名称
	* @param endFlag 最底层标志
	* @param contentIDFormatLength 案卷号的格式化长度
	* @param subContentIDFormatLength 卷内文件序号的格式化长度
	* @param archivesIDExpressionPrefix 档号前缀组成表达式
	* @param archivesIDExpression 档号组成表达式
	* @param subArchivesIDExpression 卷内文件的档号组成表达式
	* @param attachedFileSavePath 原文存储路径
	* @param renewPeriod 续借周期
	* @param personalArchivesFlag 人事档案标志
	* @param remark 备注
	*/
	public ArchivesType(boolean topFlag,String name,String code,int parentID,int topTypeID,String fullCode,String fullName,boolean endFlag,int contentIDFormatLength,int subContentIDFormatLength,String archivesIDExpressionPrefix,String archivesIDExpression,String subArchivesIDExpression,String attachedFileSavePath,int renewPeriod,int personalArchivesFlag,String remark)
	{
		this.topFlag = topFlag;
		this.name = name;
		this.code = code;
		this.parentID = parentID;
		this.topTypeID = topTypeID;
		this.fullCode = fullCode;
		this.fullName = fullName;
		this.endFlag = endFlag;
		this.contentIDFormatLength = contentIDFormatLength;
		this.subContentIDFormatLength = subContentIDFormatLength;
		this.archivesIDExpressionPrefix = archivesIDExpressionPrefix;
		this.archivesIDExpression = archivesIDExpression;
		this.subArchivesIDExpression = subArchivesIDExpression;
		this.attachedFileSavePath = attachedFileSavePath;
		this.renewPeriod = renewPeriod;
		this.personalArchivesFlag =EnumPersonalArchivesType.getEnumElement(personalArchivesFlag);
		this.remark = remark;
	}
	
	/**
	* 带字段参数的构造函数
	* @param iD 档案分类编号
	*/
	public ArchivesType(int iD)
	{
		this.iD = iD;
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
	 * 档案分类编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：档案分类编号
	 * @return 档案分类编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：档案分类编号
	 * @param iD 档案分类编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 一级类目标志
	 */
	private boolean topFlag=false;

	/**
	 * 获取属性值：一级类目标志
	 * @return 一级类目标志
	 */
	public boolean getTopFlag()
	{
		return topFlag;
	}

	/**
	 * 设置属性值：一级类目标志
	 * @param topFlag 一级类目标志
	 */
	public void setTopFlag(boolean topFlag)
	{
		this.topFlag = topFlag;
	}

	/**
	 * 档案分类名称
	 */
	private String name=null;

	/**
	 * 获取属性值：档案分类名称
	 * @return 档案分类名称
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 设置属性值：档案分类名称
	 * @param name 档案分类名称
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * 分类代码
	 */
	private String code=null;

	/**
	 * 获取属性值：分类代码
	 * @return 分类代码
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * 设置属性值：分类代码
	 * @param code 分类代码
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * 上级档案分类编号
	 */
	private int parentID=0;

	/**
	 * 获取属性值：上级档案分类编号
	 * @return 上级档案分类编号
	 */
	public int getParentID()
	{
		return parentID;
	}

	/**
	 * 设置属性值：上级档案分类编号
	 * @param parentID 上级档案分类编号
	 */
	public void setParentID(int parentID)
	{
		this.parentID = parentID;
	}

	/**
	 * 顶层分类编号
	 */
	private int topTypeID=0;

	/**
	 * 获取属性值：顶层分类编号
	 * @return 顶层分类编号
	 */
	public int getTopTypeID()
	{
		return topTypeID;
	}

	/**
	 * 设置属性值：顶层分类编号
	 * @param topTypeID 顶层分类编号
	 */
	public void setTopTypeID(int topTypeID)
	{
		this.topTypeID = topTypeID;
	}

	/**
	 * 完整代码
	 */
	private String fullCode=null;

	/**
	 * 获取属性值：完整代码
	 * @return 完整代码
	 */
	public String getFullCode()
	{
		return fullCode;
	}

	/**
	 * 设置属性值：完整代码
	 * @param fullCode 完整代码
	 */
	public void setFullCode(String fullCode)
	{
		this.fullCode = fullCode;
	}

	/**
	 * 完整名称
	 */
	private String fullName=null;

	/**
	 * 获取属性值：完整名称
	 * @return 完整名称
	 */
	public String getFullName()
	{
		return fullName;
	}

	/**
	 * 设置属性值：完整名称
	 * @param fullName 完整名称
	 */
	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	/**
	 * 最底层标志
	 */
	private boolean endFlag=false;

	/**
	 * 获取属性值：最底层标志
	 * @return 最底层标志
	 */
	public boolean getEndFlag()
	{
		return endFlag;
	}

	/**
	 * 设置属性值：最底层标志
	 * @param endFlag 最底层标志
	 */
	public void setEndFlag(boolean endFlag)
	{
		this.endFlag = endFlag;
	}

	/**
	 * 案卷号的格式化长度
	 */
	private int contentIDFormatLength=0;

	/**
	 * 获取属性值：案卷号的格式化长度
	 * @return 案卷号的格式化长度
	 */
	public int getContentIDFormatLength()
	{
		return contentIDFormatLength;
	}

	/**
	 * 设置属性值：案卷号的格式化长度
	 * @param contentIDFormatLength 案卷号的格式化长度
	 */
	public void setContentIDFormatLength(int contentIDFormatLength)
	{
		this.contentIDFormatLength = contentIDFormatLength;
	}

	/**
	 * 卷内文件序号的格式化长度
	 */
	private int subContentIDFormatLength=0;

	/**
	 * 获取属性值：卷内文件序号的格式化长度
	 * @return 卷内文件序号的格式化长度
	 */
	public int getSubContentIDFormatLength()
	{
		return subContentIDFormatLength;
	}

	/**
	 * 设置属性值：卷内文件序号的格式化长度
	 * @param subContentIDFormatLength 卷内文件序号的格式化长度
	 */
	public void setSubContentIDFormatLength(int subContentIDFormatLength)
	{
		this.subContentIDFormatLength = subContentIDFormatLength;
	}
	
	/**
	 * 档号前缀组成表达式
	 */
	private String archivesIDExpressionPrefix=null;

	/**
	 * 获取属性值：档号前缀组成表达式
	 * @return 档号前缀组成表达式
	 */
	public String getArchivesIDExpressionPrefix()
	{
		return archivesIDExpressionPrefix;
	}

	/**
	 * 设置属性值：档号前缀组成表达式
	 * @param archivesIDExpressionPrefix 档号前缀组成表达式
	 */
	public void setArchivesIDExpressionPrefix(String archivesIDExpressionPrefix)
	{
		this.archivesIDExpressionPrefix = archivesIDExpressionPrefix;
	}

	/**
	 * 档号组成表达式
	 */
	private String archivesIDExpression=null;

	/**
	 * 获取属性值：档号组成表达式
	 * @return 档号组成表达式
	 */
	public String getArchivesIDExpression()
	{
		return archivesIDExpression;
	}

	/**
	 * 设置属性值：档号组成表达式
	 * @param archivesIDExpression 档号组成表达式
	 */
	public void setArchivesIDExpression(String archivesIDExpression)
	{
		this.archivesIDExpression = archivesIDExpression;
	}
	
	/**
	 * 卷内文件的档号组成表达式
	 */
	private String subArchivesIDExpression=null;

	/**
	 * 获取属性值：卷内文件的档号组成表达式
	 * @return 卷内文件的档号组成表达式
	 */
	public String getSubArchivesIDExpression()
	{
		return subArchivesIDExpression;
	}

	/**
	 * 设置属性值：卷内文件的档号组成表达式
	 * @param subArchivesIDExpression 卷内文件的档号组成表达式
	 */
	public void setSubArchivesIDExpression(String subArchivesIDExpression)
	{
		this.subArchivesIDExpression = subArchivesIDExpression;
	}

	/**
	 * 原文存储路径
	 */
	private String attachedFileSavePath=null;

	/**
	 * 获取属性值：原文存储路径
	 * @return 原文存储路径
	 */
	public String getAttachedFileSavePath()
	{
		return attachedFileSavePath;
	}

	/**
	 * 设置属性值：原文存储路径
	 * @param attachedFileSavePath 原文存储路径
	 */
	public void setAttachedFileSavePath(String attachedFileSavePath)
	{
		this.attachedFileSavePath = attachedFileSavePath;
	}

	/**
	 * 续借周期
	 */
	private int renewPeriod=0;

	/**
	 * 获取属性值：续借周期
	 * @return 续借周期
	 */
	public int getRenewPeriod()
	{
		return renewPeriod;
	}

	/**
	 * 设置属性值：续借周期
	 * @param renewPeriod 续借周期
	 */
	public void setRenewPeriod(int renewPeriod)
	{
		this.renewPeriod = renewPeriod;
	}
	
	/**
	 * 人事档案标志
	 */
	private EnumPersonalArchivesType personalArchivesFlag=EnumPersonalArchivesType.NONE;

	/**
	 * 获取属性值：人事档案标志
	 * @return 人事档案标志
	 */
	public EnumPersonalArchivesType getPersonalArchivesFlag()
	{
		return personalArchivesFlag;
	}

	/**
	 * 设置属性值：人事档案标志
	 * @param personalArchivesFlag 人事档案标志
	 */
	public void setPersonalArchivesFlag(EnumPersonalArchivesType personalArchivesFlag)
	{
		this.personalArchivesFlag = personalArchivesFlag;
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
	public ArchivesType clone()
	{
		ArchivesType item = new ArchivesType(iD,topFlag,name,code,parentID,topTypeID,fullCode,fullName,endFlag,contentIDFormatLength,subContentIDFormatLength,archivesIDExpressionPrefix,archivesIDExpression,subArchivesIDExpression,attachedFileSavePath,renewPeriod,personalArchivesFlag.getEnumValue(),remark);
		item.setRightFlag_ArchivesInfo(getRightFlag_ArchivesInfo());
		item.setRightFlag_AttachedFile(getRightFlag_AttachedFile());
		item.setRightFlag_PaperArchives(getRightFlag_PaperArchives());
		item.setKeyInCol(keyInCol);
		item.setTag(tag);
		
		//一些只读用途的字典属性可以无需严格克隆，直接挂接其对象引用即可
		item.setArchivesInfoTables(archivesInfoTables);
		item.setDataItemsForAll(dataItemsForAll);
		item.setDataItemsForInput(dataItemsForInput);
		item.setDataItemsForInputQuery(dataItemsForInputQuery);
		item.setDataItemsForUseQuery(dataItemsForUseQuery);
		item.setDataItemsForListDisplay(dataItemsForListDisplay);
		item.setCatalogPrintTemplates(catalogPrintTemplates);
		
		//其子分类需要严格克隆下来，因为可能会改变它
		if (childArchivesTypes!=null)
		{
			if (childArchivesTypes.size()>0)
			{
				LinkedHashMap<Integer, ArchivesType> children=new LinkedHashMap<Integer, ArchivesType>();
				for (ArchivesType childItem : childArchivesTypes.values())
				{
					children.put(childItem.getID(), childItem.clone());
				}
				
				item.setChildArchivesTypes(children);
			}
		}

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param archivesType 指定的对象源
	*/
	public void cloneFrom(ArchivesType archivesType)
	{
		super.cloneFrom(archivesType);
		this.iD = archivesType.getID();
		this.topFlag = archivesType.getTopFlag();
		this.name = archivesType.getName();
		this.code = archivesType.getCode();
		this.parentID = archivesType.getParentID();
		this.topTypeID = archivesType.getTopTypeID();
		this.fullCode = archivesType.getFullCode();
		this.fullName = archivesType.getFullName();
		this.endFlag = archivesType.getEndFlag();
		this.contentIDFormatLength = archivesType.getContentIDFormatLength();
		this.subContentIDFormatLength = archivesType.getSubContentIDFormatLength();
		this.archivesIDExpressionPrefix = archivesType.getArchivesIDExpressionPrefix();
		this.archivesIDExpression = archivesType.getArchivesIDExpression();
		this.subArchivesIDExpression = archivesType.getSubArchivesIDExpression();
		this.attachedFileSavePath = archivesType.getAttachedFileSavePath();
		this.renewPeriod = archivesType.getRenewPeriod();
		this.personalArchivesFlag = archivesType.getPersonalArchivesFlag();
		this.remark = archivesType.getRemark();
		this.tag=archivesType.getTag();
		this.keyInCol=archivesType.getKeyInCol();
		
		this.parentArchivesType=archivesType.getParentArchivesType();
		this.childArchivesTypes=archivesType.getChildArchivesTypes();
		this.archivesInfoTables=archivesType.getArchivesInfoTables();
		this.dataItemsForAll=archivesType.getDataItemsForAll();
		this.dataItemsForInput=archivesType.getDataItemsForInput();
		this.dataItemsForInputQuery=archivesType.getDataItemsForInputQuery();
		this.dataItemsForUseQuery=archivesType.getDataItemsForUseQuery();
		this.dataItemsForListDisplay=archivesType.getDataItemsForListDisplay();
		this.catalogPrintTemplates=archivesType.getCatalogPrintTemplates();
	}

	/**
	* 从指定对象克隆，复制基本的档案资源权限属性
	* @param baseRightArchivesResource 指定的对象源
	*/
	public void cloneBaseRightArchivesResourceFrom(BaseRightArchivesResource baseRightArchivesResource)
	{
		this.setRightFlag_ArchivesInfo(baseRightArchivesResource.getRightFlag_ArchivesInfo());
		this.setRightFlag_AttachedFile(baseRightArchivesResource.getRightFlag_AttachedFile());
		this.setRightFlag_PaperArchives(baseRightArchivesResource.getRightFlag_PaperArchives());
	}



	/**
	 * 上级档案分类
	 */
	private ArchivesType parentArchivesType = null;

	/**
	 * 获取属性值：上级档案分类
	 */
	public ArchivesType getParentArchivesType() {
		return parentArchivesType;
	}

	/**
	 * 设置属性值：上级档案分类
	 */
	public void setParentArchivesType(ArchivesType parentArchivesType) {
		this.parentArchivesType = parentArchivesType;
	}

	/**
	 * 下级档案分类集合
	 */
	private LinkedHashMap<Integer, ArchivesType> childArchivesTypes=null;

	/**
	 * 设置属性值：下级档案分类集合
	 * 
	 * @param childArchivesTypes
	 *            下级档案分类集合
	 */
	public void setChildArchivesTypes(
			LinkedHashMap<Integer, ArchivesType> childArchivesTypes) {
		this.childArchivesTypes = childArchivesTypes;
		
		//把其下属档案分类的父档案分类属性都进行统一赋值
		if (childArchivesTypes!=null)
		{
			if (childArchivesTypes.size()>0)
			{
				for (ArchivesType item : childArchivesTypes.values())
				{
					item.setParentArchivesType(this);
				}
			}
		}
	}

	/**
	 * 获取属性值：下级档案分类集合
	 */
	public LinkedHashMap<Integer, ArchivesType> getChildArchivesTypes() {
		return childArchivesTypes;
	}


	/**
	 * 档案主体的相关信息表集合
	 */
	private EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable> archivesInfoTables =null;

	/**
	 * 设置属性值：档案主体的相关信息表集合
	 * 
	 * @param archivesInfoTables
	 *            档案主体的相关信息表集合
	 */
	public void setArchivesInfoTables(
			EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable> archivesInfoTables) {
		this.archivesInfoTables = archivesInfoTables;
		
		//把其下属档案分类都进行统一赋值，因为同种一级类目下的所有档案相关信息表都完全一样
		if (childArchivesTypes!=null)
		{
			if (childArchivesTypes.size()>0)
			{
				for (ArchivesType item : childArchivesTypes.values())
				{
					item.setArchivesInfoTables(archivesInfoTables);
				}
			}
		}
	}

	/**
	 * 获取属性值：档案主体的相关信息表集合
	 * 
	 * @return 档案主体的相关信息表集合
	 */
	public EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable> getArchivesInfoTables() {
		return archivesInfoTables;
	}
	
	/**
	 * 当前档案分类定义的所有数据项，以字段名作为关键字
	 */
	private LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForAll = null;

	/**
	 * 设置属性值：当前档案分类定义的所有数据项，以字段名作为关键字
	 * @param dataItemsForAll 当前档案分类定义的所有数据项，以字段名作为关键字
	 */
	public void setDataItemsForAll(LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForAll)
	{
		this.dataItemsForAll = dataItemsForAll;
		
		//把其下属档案分类都进行统一赋值，因为同种一级类目下的所有档案分类的数据项信息都完全一样
		if (childArchivesTypes!=null)
		{
			if (childArchivesTypes.size()>0)
			{
				for (ArchivesType item : childArchivesTypes.values())
				{
					item.setDataItemsForAll(dataItemsForAll);
				}
			}
		}
	}

	/**
	 * 获取属性值：当前档案分类定义的所有数据项，以字段名作为关键字
	 * @return 当前档案分类定义的所有数据项，以字段名作为关键字
	 */
	public LinkedHashMap<String, ArchivesTypeDataItem> getDataItemsForAll()
	{
		return dataItemsForAll;
	}

	/**
	 * 当前档案分类需要著录的数据项集合，以字段名作为关键字
	 */
	private LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInput = null;

	/**
	 * 设置属性值：当前档案分类需要著录的数据项集合，以字段名作为关键字
	 * @param dataItemsForInput 当前档案分类需要著录的数据项集合，以字段名作为关键字
	 */
	public void setDataItemsForInput(LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInput)
	{
		this.dataItemsForInput = dataItemsForInput;
		
		//把其下属档案分类都进行统一赋值，因为同种一级类目下的所有档案分类的数据项信息都完全一样
		if (childArchivesTypes!=null)
		{
			if (childArchivesTypes.size()>0)
			{
				for (ArchivesType item : childArchivesTypes.values())
				{
					item.setDataItemsForInput(dataItemsForInput);
				}
			}
		}
	}

	/**
	 * 获取属性值：当前档案分类需要著录的数据项集合，以字段名作为关键字
	 * @return 当前档案分类需要著录的数据项集合，以字段名作为关键字
	 */
	public LinkedHashMap<String, ArchivesTypeDataItem> getDataItemsForInput()
	{
		return dataItemsForInput;
	}

	/**
	 * 当前档案分类可作为著录查询条件的数据项集合，以字段名作为关键字
	 */
	private LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInputQuery = null;

	/**
	 * 设置属性值：当前档案分类可作为查询条件的数据项集合，以字段名作为关键字
	 * @param dataItemsForQuery 当前档案分类可作为查询条件的数据项集合，以字段名作为关键字
	 */
	public void setDataItemsForInputQuery(LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInputQuery)
	{
		this.dataItemsForInputQuery = dataItemsForInputQuery;
		
		//把其下属档案分类都进行统一赋值，因为同种一级类目下的所有档案分类的数据项信息都完全一样
		if (childArchivesTypes!=null)
		{
			if (childArchivesTypes.size()>0)
			{
				for (ArchivesType item : childArchivesTypes.values())
				{
					item.setDataItemsForInputQuery(dataItemsForInputQuery);
				}
			}
		}
	}

	/**
	 * 获取属性值：当前档案分类可作为查询条件的数据项集合，以字段名作为关键字
	 * @return 当前档案分类可作为查询条件的数据项集合，以字段名作为关键字
	 */
	public LinkedHashMap<String, ArchivesTypeDataItem> getDataItemsForInputQuery()
	{
		return dataItemsForInputQuery;
	}
	
	/**
	 * 当前档案分类可作为档案利用查询条件的数据项集合，以字段名作为关键字
	 */
	private LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForUseQuery = null;

	/**
	 * 设置属性值：当前档案分类可作为档案利用查询条件的数据项集合，以字段名作为关键字
	 * @param dataItemsForUseQuery 当前档案分类可作为档案利用查询条件的数据项集合，以字段名作为关键字
	 */
	public void setDataItemsForUseQuery(LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForUseQuery)
	{
		this.dataItemsForUseQuery = dataItemsForUseQuery;
		
		//把其下属档案分类都进行统一赋值，因为同种一级类目下的所有档案分类的数据项信息都完全一样
		if (childArchivesTypes!=null)
		{
			if (childArchivesTypes.size()>0)
			{
				for (ArchivesType item : childArchivesTypes.values())
				{
					item.setDataItemsForUseQuery(dataItemsForUseQuery);
				}
			}
		}
	}

	/**
	 * 获取属性值：当前档案分类可作为档案利用查询条件的数据项集合，以字段名作为关键字
	 * @return 当前档案分类可作为档案利用查询条件的数据项集合，以字段名作为关键字
	 */
	public LinkedHashMap<String, ArchivesTypeDataItem> getDataItemsForUseQuery()
	{
		return dataItemsForUseQuery;
	}

	/**
	 * 当前档案分类查询结果集列表显示的数据项集合，以字段名作为关键字
	 */
	private LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForListDisplay = null;

	/**
	 * 设置属性值：当前档案分类查询结果集列表显示的数据项集合，以字段名作为关键字
	 * @param dataItemsForListDisplay 当前档案分类查询结果集列表显示的数据项集合，以字段名作为关键字
	 */
	public void setDataItemsForListDisplay(LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForListDisplay)
	{
		this.dataItemsForListDisplay = dataItemsForListDisplay;
		
		//把其下属档案分类都进行统一赋值，因为同种一级类目下的所有档案分类的数据项信息都完全一样
		if (childArchivesTypes!=null)
		{
			if (childArchivesTypes.size()>0)
			{
				for (ArchivesType item : childArchivesTypes.values())
				{
					item.setDataItemsForListDisplay(dataItemsForListDisplay);
				}
			}
		}
	}

	/**
	 * 获取属性值：当前档案分类查询结果集列表显示的数据项集合，以字段名作为关键字
	 * @return 当前档案分类查询结果集列表显示的数据项集合，以字段名作为关键字
	 */
	public LinkedHashMap<String, ArchivesTypeDataItem> getDataItemsForListDisplay()
	{
		return dataItemsForListDisplay;
	}

	/**
	 * 当前档案分类下的各种目录打印模板集合
	 */
	private EnumMap<EnumCatalogType, LinkedHashMap<String, CatalogDataItem>> catalogPrintTemplates = null;

	/**
	 * 设置属性值：当前档案分类下的各种目录打印模板集合
	 * @param catalogPrintTemplates 当前档案分类下的各种目录打印模板集合
	 */
	public void setCatalogPrintTemplates(EnumMap<EnumCatalogType, LinkedHashMap<String, CatalogDataItem>> catalogPrintTemplates)
	{
		this.catalogPrintTemplates = catalogPrintTemplates;
		
		//把其下属档案分类都进行统一赋值，因为同种一级类目下的所有目录打印模板都完全一样
		if (childArchivesTypes!=null)
		{
			if (childArchivesTypes.size()>0)
			{
				for (ArchivesType item : childArchivesTypes.values())
				{
					item.setCatalogPrintTemplates(catalogPrintTemplates);
				}
			}
		}
	}

	/**
	 * 获取属性值：当前档案分类下的各种目录打印模板集合
	 * @return 当前档案分类下的各种目录打印模板集合
	 */
	public EnumMap<EnumCatalogType, LinkedHashMap<String, CatalogDataItem>> getCatalogPrintTemplates()
	{
		return catalogPrintTemplates;
	}


	

}
