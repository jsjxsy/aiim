package com.orifound.aiim.entity; 
/**
 * 档案利用目的
 */
public class ArchivesUsePurpose
{
    /**
     * 构造函数
     */
    public ArchivesUsePurpose()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param purpose 查档目的
	*/
	public ArchivesUsePurpose(int iD,String purpose)
	{
		// Table Name: DD_ArchivesUsePurpose
		// Columns List,Can Used in SELECT SQL: ID,Purpose
		// Columns List,Can Used in INSERT SQL: :ID,:Purpose
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Purpose=:Purpose

		this.iD = iD;
		this.purpose = purpose;
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
	 * 查档目的
	 */
	private String purpose=null;

	/**
	 * 获取属性值：查档目的
	 * @return 查档目的
	 */
	public String getPurpose()
	{
		return purpose;
	}

	/**
	 * 设置属性值：查档目的
	 * @param purpose 查档目的
	 */
	public void setPurpose(String purpose)
	{
		this.purpose = purpose;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ArchivesUsePurpose clone()
	{
		ArchivesUsePurpose item = new ArchivesUsePurpose(iD,purpose);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param archivesUsePurpose 指定的对象源
	*/
	public void cloneFrom(ArchivesUsePurpose archivesUsePurpose)
	{
		this.iD = archivesUsePurpose.getID();
		this.purpose = archivesUsePurpose.getPurpose();
		this.keyInCol = archivesUsePurpose.getKeyInCol();
		this.tag = archivesUsePurpose.getTag();
	}

//	/**
//	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
//	 * 
//	 */
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//	private class ArchivesUsePurposeMapper implements RowMapper<ArchivesUsePurpose>
//	{
//		
//		@Override
//		public ArchivesUsePurpose mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			int iD = rs.getInt("ID");
//			String purpose = rs.getString("Purpose");
//			
//			return new ArchivesUsePurpose(iD,purpose);
//		}
//	}

    
}



