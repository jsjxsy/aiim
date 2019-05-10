package com.orifound.aiim.entity;

    
/**
 * 职务信息数据字典表的实体类
 */
public class Duty
{
    /**
     * 构造函数
     */
    public Duty()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 职务编号
	* @param name 职务名称
	* @param remark 备注
	*/
	public Duty(int iD,String name,String remark)
	{
		// Table Name: DD_Duty
		// Columns List,Can Used in SELECT SQL: ID,Name,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,Remark=:Remark

		this.iD = iD;
		this.name = name;
		this.remark = remark;
	}
	
	/**
	* 带字段参数的构造函数
	* @param name 职务名称
	* @param remark 备注
	*/
	public Duty(String name,String remark)
	{
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
	 * 职务编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：职务编号
	 * @return 职务编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：职务编号
	 * @param iD 职务编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 职务名称
	 */
	private String name=null;

	/**
	 * 获取属性值：职务名称
	 * @return 职务名称
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 设置属性值：职务名称
	 * @param name 职务名称
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
	public Duty clone()
	{
		Duty item = new Duty(iD,name,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param duty 指定的对象源
	*/
	public void cloneFrom(Duty duty)
	{
		this.iD = duty.getID();
		this.name = duty.getName();
		this.remark = duty.getRemark();
	}



    
}



