package com.orifound.aiim.entity;

    
/**
 * 证件类型字典表的实体类
 */
public class IDCardType
{
    /**
     * 构造函数
     */
    public IDCardType()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 证件类型编号
	* @param name 证件名称
	*/
	public IDCardType(int iD,String name)
	{
		// Table Name: DD_IDCardType
		// Columns List,Can Used in SELECT SQL: ID,Name
		// Columns List,Can Used in INSERT SQL: :ID,:Name
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name

		this(name);
		this.iD = iD;
	}
	
	/**
	* 带字段参数的构造函数
	* @param iD 证件类型编号
	* @param name 证件名称
	*/
	public IDCardType(String name)
	{
		this.name = name;
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
	 * 证件类型编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：证件类型编号
	 * @return 证件类型编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：证件类型编号
	 * @param iD 证件类型编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 证件名称
	 */
	private String name=null;

	/**
	 * 获取属性值：证件名称
	 * @return 证件名称
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 设置属性值：证件名称
	 * @param name 证件名称
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public IDCardType clone()
	{
		IDCardType item = new IDCardType(iD,name);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param iDCardType 指定的对象源
	*/
	public void cloneFrom(IDCardType iDCardType)
	{
		this.iD = iDCardType.getID();
		this.name = iDCardType.getName();
	}

//	/**
//	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
//	 * 
//	 */
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//	private class IDCardTypeMapper implements RowMapper<IDCardType>
//	{
//		
//		@Override
//		public IDCardType mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			int iD = rs.getInt("ID");
//			String name = rs.getString("Name");
//			
//			return new IDCardType(iD,name);
//		}
//	}

    
}



