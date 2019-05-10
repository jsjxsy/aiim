package com.orifound.aiim.entity;


/**
 * 利用人所在地区字典表的实体类
 */
public class Area
{
    /**
     * 构造函数
     */
    public Area()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param name 地区名称
	* @param defaultSelectedFlag 缺省选择标志
	* @param areaFlag 地域标志
	*/
	public Area(String name,boolean defaultSelectedFlag,int areaFlag)
	{
		this.name = name;
		this.defaultSelectedFlag = defaultSelectedFlag;
		this.areaFlag = areaFlag;
	}
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param name 地区名称
	* @param defaultSelectedFlag 缺省选择标志
	* @param areaFlag 地域标志
	*/
	public Area(int iD,String name,boolean defaultSelectedFlag,int areaFlag)
	{
		// Columns List,Can Used in SELECT SQL: ID,Name,DefaultSelectedFlag,AreaFlag
		// Columns List,Can Used in INSERT SQL: pID,pName,pDefaultSelectedFlag,pAreaFlag
		// Columns List,Can Used in UPDATE SQL: ID=pID,Name=pName,DefaultSelectedFlag=pDefaultSelectedFlag,AreaFlag=pAreaFlag

		this(name, defaultSelectedFlag, areaFlag);
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
	 * 地区名称
	 */
	private String name=null;

	/**
	 * 获取属性值：地区名称
	 * @return 地区名称
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 设置属性值：地区名称
	 * @param name 地区名称
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * 缺省选择标志
	 */
	private boolean defaultSelectedFlag=false;

	/**
	 * 获取属性值：缺省选择标志
	 * @return 缺省选择标志
	 */
	public boolean getDefaultSelectedFlag()
	{
		return defaultSelectedFlag;
	}

	/**
	 * 设置属性值：缺省选择标志
	 * @param defaultSelectedFlag 缺省选择标志
	 */
	public void setDefaultSelectedFlag(boolean defaultSelectedFlag)
	{
		this.defaultSelectedFlag = defaultSelectedFlag;
	}

	/**
	 * 地域标志
	 */
	private int areaFlag=0;

	/**
	 * 获取属性值：地域标志
	 * @return 地域标志
	 */
	public int getAreaFlag()
	{
		return areaFlag;
	}

	/**
	 * 设置属性值：地域标志
	 * @param areaFlag 地域标志
	 */
	public void setAreaFlag(int areaFlag)
	{
		this.areaFlag = areaFlag;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public Area clone()
	{
		Area item = new Area(iD,name,defaultSelectedFlag,areaFlag);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	public void cloneFrom(Area area)
	{
		this.iD = area.getID();
		this.name = area.getName();
		this.defaultSelectedFlag = area.getDefaultSelectedFlag();
		this.areaFlag = area.getAreaFlag();
		this.keyInCol = area.getKeyInCol();
		this.tag = area.getTag();
	}
}



