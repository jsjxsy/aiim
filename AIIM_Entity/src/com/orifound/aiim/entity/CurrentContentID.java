package com.orifound.aiim.entity;

    
/**
 * 当前案卷号信息表的实体类
 */
public class CurrentContentID
{
    /**
     * 构造函数
     */
    public CurrentContentID()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param archivesIDPrefix 档号前缀
	* @param contentID 当前案卷号
	*/
	public CurrentContentID(String archivesIDPrefix,int contentID)
	{
		// Table Name: CurrentContentID
		// Columns List,Can Used in SELECT SQL: ArchivesIDPrefix,ContentID
		// Columns List,Can Used in INSERT SQL: :ArchivesIDPrefix,:ContentID
		// Columns List,Can Used in UPDATE SQL: ArchivesIDPrefix=:ArchivesIDPrefix,ContentID=:ContentID

		this.archivesIDPrefix = archivesIDPrefix;
		this.contentID = contentID;
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
	 * 档号前缀
	 */
	private String archivesIDPrefix=null;

	/**
	 * 获取属性值：档号前缀
	 * @return 档号前缀
	 */
	public String getArchivesIDPrefix()
	{
		return archivesIDPrefix;
	}

	/**
	 * 设置属性值：档号前缀
	 * @param archivesIDPrefix 档号前缀
	 */
	public void setArchivesIDPrefix(String archivesIDPrefix)
	{
		this.archivesIDPrefix = archivesIDPrefix;
	}

	/**
	 * 当前案卷号
	 */
	private int contentID=0;

	/**
	 * 获取属性值：当前案卷号
	 * @return 当前案卷号
	 */
	public int getContentID()
	{
		return contentID;
	}

	/**
	 * 设置属性值：当前案卷号
	 * @param contentID 当前案卷号
	 */
	public void setContentID(int contentID)
	{
		this.contentID = contentID;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public CurrentContentID clone()
	{
		CurrentContentID item = new CurrentContentID(archivesIDPrefix,contentID);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param currentContentID 指定的对象源
	*/
	public void cloneFrom(CurrentContentID currentContentID)
	{
		this.archivesIDPrefix = currentContentID.getArchivesIDPrefix();
		this.contentID = currentContentID.getContentID();
		this.keyInCol = currentContentID.getKeyInCol();
		this.tag = currentContentID.getTag();
	}

//	/**
//	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
//	 * 
//	 */
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//	private class CurrentContentIDMapper implements RowMapper<CurrentContentID>
//	{
//		
//		@Override
//		public CurrentContentID mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			String archivesIDPrefix = rs.getString("ArchivesIDPrefix");
//			int contentID = rs.getInt("ContentID");
//			
//			return new CurrentContentID(archivesIDPrefix,contentID);
//		}
//	}

    
}



