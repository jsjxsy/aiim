package com.orifound.aiim.entity;
 
/**
 * 用户自定义条件查询类
 */
public class UserDefinedSearch
{
    /**
     * 构造函数
     */
    public UserDefinedSearch()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 
	* @param userID 
	* @param name 
	* @param archivesIDString 
	* @param whereString 
	*/
	public UserDefinedSearch(int iD,int userID,String name,String archivesIDString,String whereString)
	{
		// Table Name: UserDefinedSearch
		// Columns List,Can Used in SELECT SQL: ID,UserID,Name,ArchivesIDString,WhereString
		// Columns List,Can Used in INSERT SQL: :ID,:UserID,:Name,:ArchivesIDString,:WhereString
		// Columns List,Can Used in UPDATE SQL: ID=:ID,UserID=:UserID,Name=:Name,ArchivesIDString=:ArchivesIDString,WhereString=:WhereString

		this.iD = iD;
		this.userID = userID;
		this.name = name;
		this.archivesIDString = archivesIDString;
		this.whereString = whereString;
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
	 * 
	 */
	private int iD=0;

	/**
	 * 获取属性值：
	 * @return 
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：
	 * @param iD 
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 
	 */
	private int userID=0;

	/**
	 * 获取属性值：
	 * @return 
	 */
	public int getUserID()
	{
		return userID;
	}

	/**
	 * 设置属性值：
	 * @param userID 
	 */
	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	/**
	 * 
	 */
	private String name=null;

	/**
	 * 获取属性值：
	 * @return 
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 设置属性值：
	 * @param name 
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * 
	 */
	private String archivesIDString=null;

	/**
	 * 获取属性值：
	 * @return 
	 */
	public String getArchivesIDString()
	{
		return archivesIDString;
	}

	/**
	 * 设置属性值：
	 * @param archivesIDString 
	 */
	public void setArchivesIDString(String archivesIDString)
	{
		this.archivesIDString = archivesIDString;
	}

	/**
	 * 
	 */
	private String whereString=null;

	/**
	 * 获取属性值：
	 * @return 
	 */
	public String getWhereString()
	{
		return whereString;
	}

	/**
	 * 设置属性值：
	 * @param whereString 
	 */
	public void setWhereString(String whereString)
	{
		this.whereString = whereString;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public UserDefinedSearch clone()
	{
		UserDefinedSearch item = new UserDefinedSearch(iD,userID,name,archivesIDString,whereString);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param userDefinedSearch 指定的对象源
	*/
	public void cloneFrom(UserDefinedSearch userDefinedSearch)
	{
		this.iD = userDefinedSearch.getID();
		this.userID = userDefinedSearch.getUserID();
		this.name = userDefinedSearch.getName();
		this.archivesIDString = userDefinedSearch.getArchivesIDString();
		this.whereString = userDefinedSearch.getWhereString();
		this.keyInCol = userDefinedSearch.getKeyInCol();
		this.tag = userDefinedSearch.getTag();
	}


    
}



