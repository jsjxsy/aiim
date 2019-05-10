package com.orifound.aiim.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 档案利用方式字典表的实体类
 */
public class ArchivesUseWay
{
    /**
     * 构造函数
     */
    public ArchivesUseWay()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 档案利用方式编号
	* @param name 利用方式的名称
	* @param remark 备注
	*/
	public ArchivesUseWay(int iD,String name,String remark)
	{
		// Table Name: DD_ArchivesUseWay
		// Columns List,Can Used in SELECT SQL: ID,Name,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,Remark=:Remark

		this.iD = iD;
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
	 * 档案利用方式编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：档案利用方式编号
	 * @return 档案利用方式编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：档案利用方式编号
	 * @param iD 档案利用方式编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 利用方式的名称
	 */
	private String name=null;

	/**
	 * 获取属性值：利用方式的名称
	 * @return 利用方式的名称
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 设置属性值：利用方式的名称
	 * @param name 利用方式的名称
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
	public ArchivesUseWay clone()
	{
		ArchivesUseWay item = new ArchivesUseWay(iD,name,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param archivesUseWay 指定的对象源
	*/
	public void cloneFrom(ArchivesUseWay archivesUseWay)
	{
		this.iD = archivesUseWay.getID();
		this.name = archivesUseWay.getName();
		this.remark = archivesUseWay.getRemark();
		this.keyInCol = archivesUseWay.getKeyInCol();
		this.tag = archivesUseWay.getTag();
	}

	/**
	 * 获取利用方式：借档/查档
	 * @return
	 */
	public List<ArchivesUseWay> getAllArchivesUseWay(){
		List<ArchivesUseWay> archivesUseWays = new ArrayList<ArchivesUseWay>();
		ArchivesUseWay archivesUseWay = new ArchivesUseWay(1,"借档","");
		archivesUseWays.add(archivesUseWay);
		archivesUseWay = new ArchivesUseWay(2,"查档","");
		archivesUseWays.add(archivesUseWay);		
		return archivesUseWays;
	}

	
	/**
	 * 获取利用方式：借档/查档/查看原文
	 * @return
	 */
	public List<ArchivesUseWay> getAllArchivesUseWayEx(){
		List<ArchivesUseWay> archivesUseWays = new ArrayList<ArchivesUseWay>();
		ArchivesUseWay archivesUseWay = new ArchivesUseWay(1,"借档","");
		archivesUseWays.add(archivesUseWay);
		archivesUseWay = new ArchivesUseWay(2,"查档","");
		archivesUseWays.add(archivesUseWay);
	    archivesUseWay = new ArchivesUseWay(3,"查看原文","");
	    archivesUseWays.add(archivesUseWay);
		
		return archivesUseWays;
	}
//	/**
//	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
//	 * 
//	 */
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//	private class ArchivesUseWayMapper implements RowMapper<ArchivesUseWay>
//	{
//		
//		@Override
//		public ArchivesUseWay mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			int iD = rs.getInt("ID");
//			String name = rs.getString("Name");
//			String remark = rs.getString("Remark");
//			
//			return new ArchivesUseWay(iD,name,remark);
//		}
//	}

    
}



