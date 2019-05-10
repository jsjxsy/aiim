package com.orifound.aiim.entity;

    
/**
 * 用户代工信息表的实体类
 */
public class UserChargeUserInfo
{
    /**
     * 构造函数
     */
    public UserChargeUserInfo()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param userID 代工用户编号
	* @param chargedUserID 被代工用户编号
	*/
	public UserChargeUserInfo(int iD,int userID,int chargedUserID,String chargedUserName)
	{
		// Table Name: UserChargeUser
		// Columns List,Can Used in SELECT SQL: ID,UserID,ChargedUserID
		// Columns List,Can Used in INSERT SQL: :ID,:UserID,:ChargedUserID
		// Columns List,Can Used in UPDATE SQL: ID=:ID,UserID=:UserID,ChargedUserID=:ChargedUserID

		this(userID, chargedUserID);
		this.iD = iD;
		this.chargedUserName = chargedUserName;
	}
	
	/**
	* 带字段参数的构造函数
	* @param userID 代工用户编号
	* @param chargedUserID 被代工用户编号
	*/
	public UserChargeUserInfo(int userID,int chargedUserID)
	{
		this.userID = userID;
		this.chargedUserID = chargedUserID;
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
	 * 代工用户编号
	 */
	private int userID=0;

	/**
	 * 获取属性值：代工用户编号
	 * @return 代工用户编号
	 */
	public int getUserID()
	{
		return userID;
	}

	/**
	 * 设置属性值：代工用户编号
	 * @param userID 代工用户编号
	 */
	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	/**
	 * 被代工用户编号
	 */
	private int chargedUserID=0;

	/**
	 * 获取属性值：被代工用户编号
	 * @return 被代工用户编号
	 */
	public int getChargedUserID()
	{
		return chargedUserID;
	}

	/**
	 * 设置属性值：被代工用户编号
	 * @param chargedUserID 被代工用户编号
	 */
	public void setChargedUserID(int chargedUserID)
	{
		this.chargedUserID = chargedUserID;
	}

	private String chargedUserName;
	
	public String getChargedUserName() {
		return chargedUserName;
	}

	public void setChargedUserName(String chargedUserName) {
		this.chargedUserName = chargedUserName;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public UserChargeUserInfo clone()
	{
		UserChargeUserInfo item = new UserChargeUserInfo(iD,userID,chargedUserID,chargedUserName);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param userChargeUserInfo 指定的对象源
	*/
	public void cloneFrom(UserChargeUserInfo userChargeUserInfo)
	{
		this.iD = userChargeUserInfo.getID();
		this.userID = userChargeUserInfo.getUserID();
		this.chargedUserID = userChargeUserInfo.getChargedUserID();
		this.chargedUserName = userChargeUserInfo.getChargedUserName();
	}



    
}



