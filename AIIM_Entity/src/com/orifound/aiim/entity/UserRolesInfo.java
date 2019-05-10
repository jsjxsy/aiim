package com.orifound.aiim.entity;


    
/**
 * 用户角色信息表的实体类
 */
public class UserRolesInfo extends UserRole
{
    /**
     * 构造函数
     */
    public UserRolesInfo()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param rolesID 用户角色编号
	* @param userID 用户编号
	*/
	public UserRolesInfo(int iD,int rolesID,int userID)
	{
		// Table Name: UserRolesInfo
		// Columns List,Can Used in SELECT SQL: ID,RolesID,UserID
		// Columns List,Can Used in INSERT SQL: :ID,:RolesID,:UserID
		// Columns List,Can Used in UPDATE SQL: ID=:ID,RolesID=:RolesID,UserID=:UserID

		this(rolesID, userID);
		this.iD = iD;
	}
	
	/**
	* 带字段参数的构造函数
	* @param rolesID 用户角色编号
	* @param userID 用户编号
	*/
	public UserRolesInfo(int rolesID,int userID)
	{
		this.rolesID = rolesID;
		this.userID = userID;
	}
	
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param rolesID 用户角色编号
	* @param userID 用户编号
	* @param name 角色名称
	* @param remark 备注
	*/
	public UserRolesInfo(int iD,int rolesID,int userID,String name,String remark)
	{
		this(iD,rolesID, userID);
		super.setName(name);
		super.setRemark(remark);
	}
	
	/**
	 * 
	 * @param iD
	 * @param rolesID
	 * @param userID
	 * @param UserName
	 * @param RealName
	 * @param iDCardNo
	 */
	public UserRolesInfo(int iD,int rolesID,int userID,String UserName,String RealName,String iDCardNo)
	{
		this(iD,rolesID, userID);
		this.setUserName(UserName);
		this.setRealName(RealName);
		this.setIDCardNo(iDCardNo);
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
	 * 用户编号
	 */
	private int userID=0;

	/**
	 * 获取属性值：用户编号
	 * @return 用户编号
	 */
	public int getUserID()
	{
		return userID;
	}

	/**
	 * 设置属性值：用户编号
	 * @param userID 用户编号
	 */
	public void setUserID(int userID)
	{
		this.userID = userID;
	}
	/**
	 * 真实姓名
	 */
	private String realName;
	
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	/**
	 * 用户名
	 */
	private String userName;
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * 证件号码
	 */
	private String iDCardNo; 
	
	

	public String getIDCardNo() {
		return iDCardNo;
	}

	public void setIDCardNo(String iDCardNo) {
		this.iDCardNo = iDCardNo;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public UserRolesInfo clone()
	{
		UserRolesInfo item = new UserRolesInfo(iD,rolesID,userID);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param userRolesInfo 指定的对象源
	*/
	public void cloneFrom(UserRolesInfo userRolesInfo)
	{
		super.cloneFrom(userRolesInfo);
		this.iD = userRolesInfo.getID();
		this.rolesID = userRolesInfo.getRolesID();
		this.userID = userRolesInfo.getUserID();
	}



	
    
}



