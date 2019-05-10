package com.orifound.aiim.entity;

    
/**
 * 数据源的数据项字典表的实体类
 */
public class DataSourceItem
{
    /**
     * 构造函数
     */
    public DataSourceItem()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 项编号
	* @param dataSourceID 数据源编号
	* @param orderID 次序
	* @param name 名称
	* @param remark 备注
	*/
	public DataSourceItem(int iD,int dataSourceID,int orderID,String name,String remark)
	{
		// Table Name: DD_DataSourceItem
		// Columns List,Can Used in SELECT SQL: ID,DataSourceID,OrderID,Name,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:DataSourceID,:OrderID,:Name,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,DataSourceID=:DataSourceID,OrderID=:OrderID,Name=:Name,Remark=:Remark

		this(dataSourceID, orderID, name, remark);
		this.iD = iD;
	}
	
	/**
	* 带字段参数的构造函数
	* @param dataSourceID 数据源编号
	* @param orderID 次序
	* @param name 名称
	* @param remark 备注
	*/
	public DataSourceItem(int dataSourceID,int orderID,String name,String remark)
	{
		this.dataSourceID = dataSourceID;
		this.orderID = orderID;
		this.name = name;
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
	 * 项编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：项编号
	 * @return 项编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：项编号
	 * @param iD 项编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
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
	 * 次序
	 */
	private int orderID=0;

	/**
	 * 获取属性值：次序
	 * @return 次序
	 */
	public int getOrderID()
	{
		return orderID;
	}

	/**
	 * 设置属性值：次序
	 * @param orderID 次序
	 */
	public void setOrderID(int orderID)
	{
		this.orderID = orderID;
	}

	/**
	 * 名称
	 */
	private String name=null;

	/**
	 * 获取属性值：名称
	 * @return 名称
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 设置属性值：名称
	 * @param name 名称
	 */
	public void setName(String name)
	{
		this.name = name;
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
	public DataSourceItem clone()
	{
		DataSourceItem item = new DataSourceItem(iD,dataSourceID,orderID,name,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param dataSourceItem 指定的对象源
	*/
	public void cloneFrom(DataSourceItem dataSourceItem)
	{
		this.iD = dataSourceItem.getID();
		this.dataSourceID = dataSourceItem.getDataSourceID();
		this.orderID = dataSourceItem.getOrderID();
		this.name = dataSourceItem.getName();
		this.remark = dataSourceItem.getRemark();
	}

    
}



