package com.orifound.aiim.entity;

import java.util.Map;

    
/**
 * 公文档案分类的归档映射关系表实体类
 */
public class OfficialArchivesTypeSavedMapping
{
    /**
     * 构造函数
     */
    public OfficialArchivesTypeSavedMapping()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param officialArchivesTypeID 公文档案分类编号
	* @param archivesTypeID 归档映射的实体档案分类编号
	* @param remark 备注
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
	 * 公文档案分类编号
	 */
	private int officialArchivesTypeID=0;

	/**
	 * 获取属性值：公文档案分类编号
	 * @return 公文档案分类编号
	 */
	public int getOfficialArchivesTypeID()
	{
		return officialArchivesTypeID;
	}

	/**
	 * 设置属性值：公文档案分类编号
	 * @param officialArchivesTypeID 公文档案分类编号
	 */
	public void setOfficialArchivesTypeID(int officialArchivesTypeID)
	{
		this.officialArchivesTypeID = officialArchivesTypeID;
	}

	/**
	 * 归档映射的实体档案分类编号
	 */
	private int archivesTypeID=0;

	/**
	 * 获取属性值：归档映射的实体档案分类编号
	 * @return 归档映射的实体档案分类编号
	 */
	public int getArchivesTypeID()
	{
		return archivesTypeID;
	}

	/**
	 * 设置属性值：归档映射的实体档案分类编号
	 * @param archivesTypeID 归档映射的实体档案分类编号
	 */
	public void setArchivesTypeID(int archivesTypeID)
	{
		this.archivesTypeID = archivesTypeID;
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
	public OfficialArchivesTypeSavedMapping clone()
	{
		OfficialArchivesTypeSavedMapping item = new OfficialArchivesTypeSavedMapping(iD,officialArchivesTypeID,archivesTypeID,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param officialArchivesTypeSavedMapping 指定的对象源
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
	 * 公文档案分类归档至具体实体分类时的数据项映射关系<br>
	 * 以实体分类编号作为集合关键字
	 * 第二个Map<Integer, OfficialArchivesDataItemSavedMapping>封装数据项保存映射key--->DesDataItemID,value--->OfficialArchivesDataItemSavedMapping 对象
	 */
	private Map<Integer, OfficialArchivesDataItemSavedMapping> officialArchivesDataItemSavedMapping;
	
	/**
	 * 公文档案分类归档至具体实体分类时的数据项映射关系<br>
	 * 以源数据项编号作为集合关键字
	 * 封装数据项保存映射key--->DesDataItemID,value--->OfficialArchivesDataItemSavedMapping 对象
	 */
	public Map<Integer, OfficialArchivesDataItemSavedMapping> getOfficialArchivesDataItemSavedMapping() {
		return officialArchivesDataItemSavedMapping;
	}
	
	/**
	 * 公文档案分类归档至具体实体分类时的数据项映射关系<br>
	 * 以实体分类编号作为集合关键字
	 * 第二个Map<Integer, OfficialArchivesDataItemSavedMapping>封装数据项保存映射key--->DesDataItemID,value--->OfficialArchivesDataItemSavedMapping 对象
	 */
	public void setOfficialArchivesDataItemSavedMapping(Map<Integer, OfficialArchivesDataItemSavedMapping> officialArchivesDataItemSavedMapping) {
		this.officialArchivesDataItemSavedMapping = officialArchivesDataItemSavedMapping;
	}
    
	
}



