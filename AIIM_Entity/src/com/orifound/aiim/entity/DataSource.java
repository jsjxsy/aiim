package com.orifound.aiim.entity;

import java.util.LinkedHashMap;
    
/**
 * 数据源字典表的实体类
 */
public class DataSource
{
    /**
     * 构造函数
     */
    public DataSource()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 数据源编号
	* @param name 数据源名称
	* @param inherentType 固有类别标志
	* @param remark 备注
	*/
	public DataSource(int iD,String name,int inherentType,String remark)
	{
		// Table Name: DD_DataSource
		// Columns List,Can Used in SELECT SQL: ID,Name,InherentType,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:InherentType,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,InherentType=:InherentType,Remark=:Remark

		this(name, inherentType, remark);
		this.iD = iD;
	}
	
	/**
	* 带字段参数的构造函数
	* @param name 数据源名称
	* @param inherentType 固有类别标志
	* @param remark 备注
	*/
	public DataSource(String name,int inherentType,String remark)
	{
		this.name = name;
		this.inherentType =EnumDataSourceInherentType.getEnumElement(inherentType);
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
	 * 数据源编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：数据源编号
	 * @return 数据源编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：数据源编号
	 * @param iD 数据源编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 数据源名称
	 */
	private String name=null;

	/**
	 * 获取属性值：数据源名称
	 * @return 数据源名称
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 设置属性值：数据源名称
	 * @param name 数据源名称
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * 固有类别标志
	 */
	private EnumDataSourceInherentType inherentType=EnumDataSourceInherentType.NONE;

	/**
	 * 获取属性值：固有类别标志
	 * @return 固有类别标志
	 */
	public EnumDataSourceInherentType getInherentType()
	{
		return inherentType;
	}

	/**
	 * 设置属性值：固有类别标志
	 * @param inherentType 固有类别标志
	 */
	public void setInherentType(EnumDataSourceInherentType inherentType)
	{
		this.inherentType = inherentType;
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
	public DataSource clone()
	{
		DataSource item = new DataSource(iD,name,inherentType.getEnumValue(),remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param dataSource 指定的对象源
	*/
	public void cloneFrom(DataSource dataSource)
	{
		this.iD = dataSource.getID();
		this.name = dataSource.getName();
		this.inherentType = dataSource.getInherentType();
		this.remark = dataSource.getRemark();
	}




	/**
	 * 数据源拥有的数据项集合
	 */
	private LinkedHashMap<Integer, DataSourceItem> dataSourceItems = null;

	/**
	 * 设置属性值：数据源拥有的数据项集合
	 * @param datasourceItems 数据源拥有的数据项集合
	 */
	public void setDataSourceItems(LinkedHashMap<Integer, DataSourceItem> dataSourceItems)
	{
		this.dataSourceItems = dataSourceItems;
	}

	/**
	 * 获取属性值：数据源拥有的数据项集合
	 * @return 数据源拥有的数据项集合
	 */
	public LinkedHashMap<Integer, DataSourceItem> getDataSourceItems()
	{
		return dataSourceItems;
	}

	

	

	
    
}



