package com.orifound.aiim.entity;

    
/**
 * 用户角色的档案密级权限表实体类
 */
public class UserRolesArchivesSecrecy
{
    /**
     * 构造函数
     */
    public UserRolesArchivesSecrecy()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param rolesID 用户角色编号
	* @param secrecyID 档案密级编号
	*/
	public UserRolesArchivesSecrecy(int iD,int rolesID,int secrecyID)
	{
		// Table Name: DDR_UserRoles_ArchivesSecrecy
		// Columns List,Can Used in SELECT SQL: ID,RolesID,SecrecyID
		// Columns List,Can Used in INSERT SQL: :ID,:RolesID,:SecrecyID
		// Columns List,Can Used in UPDATE SQL: ID=:ID,RolesID=:RolesID,SecrecyID=:SecrecyID

		this.iD = iD;
		this.rolesID = rolesID;
		this.secrecyID = secrecyID;
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
	 * 用户角色编号
	 */
	private int rolesID=0;

	/**
	 * 获取属性值：用户角色编号
	 * @return 用户角色编号
	 */
	public int getRolesID()
	{
		return rolesID;
	}

	/**
	 * 设置属性值：用户角色编号
	 * @param rolesID 用户角色编号
	 */
	public void setRolesID(int rolesID)
	{
		this.rolesID = rolesID;
	}

	/**
	 * 档案密级编号
	 */
	private int secrecyID=0;

	/**
	 * 获取属性值：档案密级编号
	 * @return 档案密级编号
	 */
	public int getSecrecyID()
	{
		return secrecyID;
	}

	/**
	 * 设置属性值：档案密级编号
	 * @param secrecyID 档案密级编号
	 */
	public void setSecrecyID(int secrecyID)
	{
		this.secrecyID = secrecyID;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public UserRolesArchivesSecrecy clone()
	{
		UserRolesArchivesSecrecy item = new UserRolesArchivesSecrecy(iD,rolesID,secrecyID);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param userRolesArchivesSecrecy 指定的对象源
	*/
	public void cloneFrom(UserRolesArchivesSecrecy userRolesArchivesSecrecy)
	{
		this.iD = userRolesArchivesSecrecy.getID();
		this.rolesID = userRolesArchivesSecrecy.getRolesID();
		this.secrecyID = userRolesArchivesSecrecy.getSecrecyID();
		this.keyInCol = userRolesArchivesSecrecy.getKeyInCol();
		this.tag = userRolesArchivesSecrecy.getTag();
	}

    
}