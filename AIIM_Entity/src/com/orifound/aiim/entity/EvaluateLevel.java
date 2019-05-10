package com.orifound.aiim.entity;

/**
 * 考核等级字典表	
 */
public class EvaluateLevel
{
    /**
     * 构造函数
     */
    public EvaluateLevel()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param name 考核等级名称
	* @param remark 备注
	*/
	public EvaluateLevel(int iD,String name,String remark)
	{
		// Table Name: DD_EvaluateLevel
		// Columns List,Can Used in SELECT SQL: ID,Name,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,Remark=:Remark

		this(name, remark);
		this.iD = iD;
	}
	
	/**
	* 带字段参数的构造函数
	* @param name 考核等级名称
	* @param remark 备注
	*/
	public EvaluateLevel(String name,String remark)
	{
		// Table Name: DD_EvaluateLevel
		// Columns List,Can Used in SELECT SQL: ID,Name,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,Remark=:Remark

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
	 * 考核等级名称
	 */
	private String name=null;

	/**
	 * 获取属性值：考核等级名称
	 * @return 考核等级名称
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 设置属性值：考核等级名称
	 * @param name 考核等级名称
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
	public EvaluateLevel clone()
	{
		EvaluateLevel item = new EvaluateLevel(iD,name,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param evaluateLevel 指定的对象源
	*/
	public void cloneFrom(EvaluateLevel evaluateLevel)
	{
		this.iD = evaluateLevel.getID();
		this.name = evaluateLevel.getName();
		this.remark = evaluateLevel.getRemark();
		this.keyInCol = evaluateLevel.getKeyInCol();
		this.tag = evaluateLevel.getTag();
	}

//	/**
//	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
//	 * 
//	 */
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//	private class EvaluateLevelMapper implements RowMapper<EvaluateLevel>
//	{
//		
//		@Override
//		public EvaluateLevel mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			int iD = rs.getInt("ID");
//			String name = rs.getString("Name");
//			String remark = rs.getString("Remark");
//			
//			return new EvaluateLevel(iD,name,remark);
//		}
//	}

    
}



