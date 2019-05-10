package com.orifound.aiim.entity;

    
/**
 * 保管期限字典表的实体类
 */
public class RetentionPeriod
{
    /**
     * 构造函数
     */
    public RetentionPeriod()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 保管期限编号
	* @param name 保管期限名称
	* @param totalYears 保管年限
	* @param remark 备注
	*/
	public RetentionPeriod(int iD,String name,int totalYears,String remark)
	{
		// Table Name: DD_RetentionPeriod
		// Columns List,Can Used in SELECT SQL: ID,Name,TotalYears,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:TotalYears,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,TotalYears=:TotalYears,Remark=:Remark

		this(name, totalYears, remark);
		this.iD = iD;
	}
	
	/**
	* 带字段参数的构造函数
	* @param name 保管期限名称
	* @param totalYears 保管年限
	* @param remark 备注
	*/
	public RetentionPeriod(String name,int totalYears,String remark)
	{
		this.name = name;
		this.totalYears = totalYears;
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
	 * 保管期限编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：保管期限编号
	 * @return 保管期限编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：保管期限编号
	 * @param iD 保管期限编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 保管期限名称
	 */
	private String name=null;

	/**
	 * 获取属性值：保管期限名称
	 * @return 保管期限名称
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 设置属性值：保管期限名称
	 * @param name 保管期限名称
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * 保管年限
	 */
	private int totalYears=0;

	/**
	 * 获取属性值：保管年限
	 * @return 保管年限
	 */
	public int getTotalYears()
	{
		return totalYears;
	}

	/**
	 * 设置属性值：保管年限
	 * @param totalYears 保管年限
	 */
	public void setTotalYears(int totalYears)
	{
		this.totalYears = totalYears;
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
	public RetentionPeriod clone()
	{
		RetentionPeriod item = new RetentionPeriod(iD,name,totalYears,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param retentionPeriod 指定的对象源
	*/
	public void cloneFrom(RetentionPeriod retentionPeriod)
	{
		this.iD = retentionPeriod.getID();
		this.name = retentionPeriod.getName();
		this.totalYears = retentionPeriod.getTotalYears();
		this.remark = retentionPeriod.getRemark();
	}



    
}



