package com.orifound.aiim.entity;

    
/**
 * 公文档案数据项的归档映射关系表实体类
 */
public class OfficialArchivesDataItemSavedMapping
{
    /**
     * 构造函数
     */
    public OfficialArchivesDataItemSavedMapping()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param archivesTypeSavedMappingID 档案分类归档映射编号
	* @param srcOfficialArchivesTypeDataItemID 源公文档案分类数据项编号
	* @param desArchivesTypeDataItemID 映射的目标实体档案分类数据项
	*/
	public OfficialArchivesDataItemSavedMapping(int iD,int archivesTypeSavedMappingID,int srcOfficialArchivesTypeDataItemID,int desArchivesTypeDataItemID,String columnName)
	{
		// Table Name: DD_OfficialArchivesDataItemSavedMapping
		// Columns List,Can Used in SELECT SQL: ID,ArchivesTypeSavedMappingID,SrcOfficialArchivesTypeDataItemID,DesArchivesTypeDataItemID
		// Columns List,Can Used in INSERT SQL: :ID,:ArchivesTypeSavedMappingID,:SrcOfficialArchivesTypeDataItemID,:DesArchivesTypeDataItemID
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ArchivesTypeSavedMappingID=:ArchivesTypeSavedMappingID,SrcOfficialArchivesTypeDataItemID=:SrcOfficialArchivesTypeDataItemID,DesArchivesTypeDataItemID=:DesArchivesTypeDataItemID

		this.iD = iD;
		this.archivesTypeSavedMappingID = archivesTypeSavedMappingID;
		this.srcOfficialArchivesTypeDataItemID = srcOfficialArchivesTypeDataItemID;
		this.desArchivesTypeDataItemID = desArchivesTypeDataItemID;
		this.columnName=columnName;
	}
	
	

	/**
	* 带字段参数的构造函数
	* @param archivesTypeSavedMappingID 档案分类归档映射编号
	* @param srcOfficialArchivesTypeDataItemID 源公文档案分类数据项编号
	* @param desArchivesTypeDataItemID 映射的目标实体档案分类数据项
	*/
	public OfficialArchivesDataItemSavedMapping(int archivesTypeSavedMappingID,int srcOfficialArchivesTypeDataItemID,int desArchivesTypeDataItemID,String columnName)
	{
		// Table Name: DD_OfficialArchivesDataItemSavedMapping
		// Columns List,Can Used in SELECT SQL: ArchivesTypeSavedMappingID,SrcOfficialArchivesTypeDataItemID,DesArchivesTypeDataItemID
		// Columns List,Can Used in INSERT SQL: :ArchivesTypeSavedMappingID,:SrcOfficialArchivesTypeDataItemID,:DesArchivesTypeDataItemID
		// Columns List,Can Used in UPDATE SQL: ID=:ArchivesTypeSavedMappingID=:ArchivesTypeSavedMappingID,SrcOfficialArchivesTypeDataItemID=:SrcOfficialArchivesTypeDataItemID,DesArchivesTypeDataItemID=:DesArchivesTypeDataItemID

		this.archivesTypeSavedMappingID = archivesTypeSavedMappingID;
		this.srcOfficialArchivesTypeDataItemID = srcOfficialArchivesTypeDataItemID;
		this.desArchivesTypeDataItemID = desArchivesTypeDataItemID;
		this.columnName=columnName;
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
	 * 档案分类归档映射编号
	 */
	private int archivesTypeSavedMappingID=0;

	/**
	 * 获取属性值：档案分类归档映射编号
	 * @return 档案分类归档映射编号
	 */
	public int getArchivesTypeSavedMappingID()
	{
		return archivesTypeSavedMappingID;
	}

	/**
	 * 设置属性值：档案分类归档映射编号
	 * @param archivesTypeSavedMappingID 档案分类归档映射编号
	 */
	public void setArchivesTypeSavedMappingID(int archivesTypeSavedMappingID)
	{
		this.archivesTypeSavedMappingID = archivesTypeSavedMappingID;
	}

	/**
	 * 源公文档案分类数据项编号
	 */
	private int srcOfficialArchivesTypeDataItemID=0;

	/**
	 * 获取属性值：源公文档案分类数据项编号
	 * @return 源公文档案分类数据项编号
	 */
	public int getSrcOfficialArchivesTypeDataItemID()
	{
		return srcOfficialArchivesTypeDataItemID;
	}

	/**
	 * 设置属性值：源公文档案分类数据项编号
	 * @param srcOfficialArchivesTypeDataItemID 源公文档案分类数据项编号
	 */
	public void setSrcOfficialArchivesTypeDataItemID(int srcOfficialArchivesTypeDataItemID)
	{
		this.srcOfficialArchivesTypeDataItemID = srcOfficialArchivesTypeDataItemID;
	}

	/**
	 * 映射的目标实体档案分类数据项
	 */
	private int desArchivesTypeDataItemID=0;

	/**
	 * 获取属性值：映射的目标实体档案分类数据项
	 * @return 映射的目标实体档案分类数据项
	 */
	public int getDesArchivesTypeDataItemID()
	{
		return desArchivesTypeDataItemID;
	}

	/**
	 * 设置属性值：映射的目标实体档案分类数据项
	 * @param desArchivesTypeDataItemID 映射的目标实体档案分类数据项
	 */
	public void setDesArchivesTypeDataItemID(int desArchivesTypeDataItemID)
	{
		this.desArchivesTypeDataItemID = desArchivesTypeDataItemID;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public OfficialArchivesDataItemSavedMapping clone()
	{
		OfficialArchivesDataItemSavedMapping item = new OfficialArchivesDataItemSavedMapping(iD,archivesTypeSavedMappingID,srcOfficialArchivesTypeDataItemID,desArchivesTypeDataItemID,columnName);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param officialArchivesDataItemSavedMapping 指定的对象源
	*/
	public void cloneFrom(OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping)
	{
		this.iD = officialArchivesDataItemSavedMapping.getID();
		this.archivesTypeSavedMappingID = officialArchivesDataItemSavedMapping.getArchivesTypeSavedMappingID();
		this.srcOfficialArchivesTypeDataItemID = officialArchivesDataItemSavedMapping.getSrcOfficialArchivesTypeDataItemID();
		this.desArchivesTypeDataItemID = officialArchivesDataItemSavedMapping.getDesArchivesTypeDataItemID();
		this.columnName=officialArchivesDataItemSavedMapping.getColumnName();
		this.keyInCol = officialArchivesDataItemSavedMapping.getKeyInCol();
		this.tag = officialArchivesDataItemSavedMapping.getTag();
	}
	
	private String columnName;//添加列名属性

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

    
}



