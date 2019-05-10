package com.orifound.aiim.entity;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

    
/**
 * 公文档案分类字典表的实体类
 */
public class OfficialArchivesType
{
    /**
     * 构造函数
     */
    public OfficialArchivesType()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 档案分类编号
	* @param name 档案分类名称
	* @param code 代码
	* @param attachedFileSavePath 原文存储路径
	* @param remark 备注
	*/
	public OfficialArchivesType(int iD,String name,String code,String attachedFileSavePath,String remark)
	{
		// Table Name: DD_OfficialArchivesType
		// Columns List,Can Used in SELECT SQL: ID,Name,Code,AttachedFileSavePath,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:Code,:AttachedFileSavePath,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,Code=:Code,AttachedFileSavePath=:AttachedFileSavePath,Remark=:Remark

		this(name, code, attachedFileSavePath, remark);
		this.iD = iD;
	}
	
    /**
     * 构造函数
     */
    public OfficialArchivesType(int iD)
    {
     this.iD=iD;   
    }
    
	/**
	* 带字段参数的构造函数
	* @param name 档案分类名称
	* @param code 代码
	* @param attachedFileSavePath 原文存储路径
	* @param remark 备注
	*/
	public OfficialArchivesType(String name,String code,String attachedFileSavePath,String remark)
	{
		this.name = name;
		this.code = code;
		this.attachedFileSavePath = attachedFileSavePath;
		this.remark = remark;
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
	 * 代码
	 */
	private String code=null;

	/**
	 * 获取属性值：代码
	 * @return 代码
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * 设置属性值：代码
	 * @param code 代码
	 */
	public void setCode(String code)
	{
		this.code = code;
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
	public OfficialArchivesType clone()
	{
		OfficialArchivesType item = new OfficialArchivesType(iD,name,code,attachedFileSavePath,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		//一些只读用途的字典属性可以无需严格克隆，直接挂接其对象引用即可
		item.setOfficialArchivesInfoTables(officialArchivesInfoTables);
		item.setOfficialArchivesTypeSavedMappings(officialArchivesTypeSavedMappings);
		item.setDataItemsForAll(dataItemsForAll);
		item.setDataItemsForInput(dataItemsForInput);
		item.setDataItemsForInputQuery(dataItemsForInputQuery);
		item.setDataItemsForUseQuery(dataItemsForUseQuery);
		item.setDataItemsForListDisplay(dataItemsForListDisplay);
		item.setCatalogPrintTemplates(catalogPrintTemplates);
		
		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param officialArchivesType 指定的对象源
	*/
	public void cloneFrom(OfficialArchivesType officialArchivesType)
	{
		this.iD = officialArchivesType.getID();
		this.name = officialArchivesType.getName();
		this.code = officialArchivesType.getCode();
		this.attachedFileSavePath = officialArchivesType.getAttachedFileSavePath();
		this.remark = officialArchivesType.getRemark();
		this.keyInCol = officialArchivesType.getKeyInCol();
		this.tag = officialArchivesType.getTag();
		
		this.officialArchivesInfoTables=officialArchivesType.getOfficialArchivesInfoTables();
		this.officialArchivesTypeSavedMappings=officialArchivesType.getOfficialArchivesTypeSavedMappings();
		this.dataItemsForAll=officialArchivesType.getDataItemsForAll();
		this.dataItemsForInput=officialArchivesType.getDataItemsForInput();
		this.dataItemsForInputQuery=officialArchivesType.getDataItemsForInputQuery();
		this.dataItemsForUseQuery=officialArchivesType.getDataItemsForUseQuery();
		this.dataItemsForListDisplay=officialArchivesType.getDataItemsForListDisplay();
		this.catalogPrintTemplates=officialArchivesType.getCatalogPrintTemplates();
	}



	/**
	 * 公文档案的相关信息表集合
	 */
	private EnumMap<EnumOfficialArchivesInfoTableType, OfficialArchivesInfoTable> officialArchivesInfoTables =null;

	/**
	 * 设置属性值：公文档案的相关信息表集合
	 * 
	 * @param archivesInfoTables
	 *            公文档案的相关信息表集合
	 */
	public void setOfficialArchivesInfoTables(
			EnumMap<EnumOfficialArchivesInfoTableType, OfficialArchivesInfoTable> officialArchivesInfoTables) {
		this.officialArchivesInfoTables = officialArchivesInfoTables;
	}

	/**
	 * 获取属性值：公文档案的相关信息表集合
	 * 
	 * @return 公文档案的相关信息表集合
	 */
	public EnumMap<EnumOfficialArchivesInfoTableType, OfficialArchivesInfoTable> getOfficialArchivesInfoTables() {
		return officialArchivesInfoTables;
	}
	
	/**
	 * 当前公文档案分类定义的所有数据项，以字段名作为关键字
	 */
	private LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForAll = null;

	/**
	 * 设置属性值：当前公文档案分类定义的所有数据项，以字段名作为关键字
	 * @param dataItemsForAll 当前公文档案分类定义的所有数据项，以字段名作为关键字
	 */
	public void setDataItemsForAll(LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForAll)
	{
		this.dataItemsForAll = dataItemsForAll;
	}

	/**
	 * 获取属性值：当前公文档案分类定义的所有数据项，以字段名作为关键字
	 * @return 当前公文档案分类定义的所有数据项，以字段名作为关键字
	 */
	public LinkedHashMap<String, ArchivesTypeDataItem> getDataItemsForAll()
	{
		return dataItemsForAll;
	}

	/**
	 * 当前公文档案分类需要著录的数据项集合，以字段名作为关键字
	 */
	private LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInput = null;

	/**
	 * 设置属性值：当前公文档案分类需要著录的数据项集合，以字段名作为关键字
	 * @param dataItemsForInput 当前公文档案分类需要著录的数据项集合，以字段名作为关键字
	 */
	public void setDataItemsForInput(LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInput)
	{
		this.dataItemsForInput = dataItemsForInput;
	}

	/**
	 * 获取属性值：当前公文档案分类需要著录的数据项集合，以字段名作为关键字
	 * @return 当前公文档案分类需要著录的数据项集合，以字段名作为关键字
	 */
	public LinkedHashMap<String, ArchivesTypeDataItem> getDataItemsForInput()
	{
		return dataItemsForInput;
	}

	/**
	 * 当前公文档案分类可作为著录查询条件的数据项集合，以字段名作为关键字
	 */
	private LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInputQuery = null;

	/**
	 * 设置属性值：当前公文档案分类可作为查询条件的数据项集合，以字段名作为关键字
	 * @param dataItemsForQuery 当前公文档案分类可作为查询条件的数据项集合，以字段名作为关键字
	 */
	public void setDataItemsForInputQuery(LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInputQuery)
	{
		this.dataItemsForInputQuery = dataItemsForInputQuery;
	}

	/**
	 * 获取属性值：当前公文档案分类可作为查询条件的数据项集合，以字段名作为关键字
	 * @return 当前公文档案分类可作为查询条件的数据项集合，以字段名作为关键字
	 */
	public LinkedHashMap<String, ArchivesTypeDataItem> getDataItemsForInputQuery()
	{
		return dataItemsForInputQuery;
	}
	
	/**
	 * 当前公文档案分类可作为档案利用查询条件的数据项集合，以字段名作为关键字
	 */
	private LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForUseQuery = null;

	/**
	 * 设置属性值：当前公文档案分类可作为档案利用查询条件的数据项集合，以字段名作为关键字
	 * @param dataItemsForUseQuery 当前公文档案分类可作为档案利用查询条件的数据项集合，以字段名作为关键字
	 */
	public void setDataItemsForUseQuery(LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForUseQuery)
	{
		this.dataItemsForUseQuery = dataItemsForUseQuery;
	}

	/**
	 * 获取属性值：当前公文档案分类可作为档案利用查询条件的数据项集合，以字段名作为关键字
	 * @return 当前公文档案分类可作为档案利用查询条件的数据项集合，以字段名作为关键字
	 */
	public LinkedHashMap<String, ArchivesTypeDataItem> getDataItemsForUseQuery()
	{
		return dataItemsForUseQuery;
	}

	/**
	 * 当前公文档案分类查询结果集列表显示的数据项集合，以字段名作为关键字
	 */
	private LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForListDisplay = null;

	/**
	 * 设置属性值：当前公文档案分类查询结果集列表显示的数据项集合，以字段名作为关键字
	 * @param dataItemsForListDisplay 当前公文档案分类查询结果集列表显示的数据项集合，以字段名作为关键字
	 */
	public void setDataItemsForListDisplay(LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForListDisplay)
	{
		this.dataItemsForListDisplay = dataItemsForListDisplay;
	}

	/**
	 * 获取属性值：当前公文档案分类查询结果集列表显示的数据项集合，以字段名作为关键字
	 * @return 当前公文档案分类查询结果集列表显示的数据项集合，以字段名作为关键字
	 */
	public LinkedHashMap<String, ArchivesTypeDataItem> getDataItemsForListDisplay()
	{
		return dataItemsForListDisplay;
	}

	/**
	 * 公文档案类型保存映射集合
	 */
	private Map<Integer,OfficialArchivesTypeSavedMapping> officialArchivesTypeSavedMappings=null;

	/**
	 * 获取公文档案类型保存映射集合
	 * @return
	 */
	public Map<Integer, OfficialArchivesTypeSavedMapping> getOfficialArchivesTypeSavedMappings() {
		return officialArchivesTypeSavedMappings;
	}
	
	/**
	 * 设置公文档案类型保存映射集合
	 * @param oSavedMappingfficialArchivesTypeSavedMappings
	 */
	public void setOfficialArchivesTypeSavedMappings(Map<Integer, OfficialArchivesTypeSavedMapping> officialArchivesTypeSavedMappings) {
		this.officialArchivesTypeSavedMappings = officialArchivesTypeSavedMappings;
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



