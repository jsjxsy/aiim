package com.orifound.aiim.entity;

    
/**
 * 专职人员负责的部门信息表的实体类
 */
public class UserChargeDepartmentInfo extends DepartmentInfo
{
    /**
     * 构造函数
     */
    public UserChargeDepartmentInfo()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param userID 用户编号
	* @param departmentID 部门编号
	*/
	public UserChargeDepartmentInfo(int iD,int userID,int departmentID)
	{
		// Table Name: UserChargeDepartment
		// Columns List,Can Used in SELECT SQL: ID,UserID,DepartmentID
		// Columns List,Can Used in INSERT SQL: :ID,:UserID,:DepartmentID
		// Columns List,Can Used in UPDATE SQL: ID=:ID,UserID=:UserID,DepartmentID=:DepartmentID

		this(userID, departmentID);
		this.iD = iD;
	}
	
	/**
	* 带字段参数的构造函数
	* @param userID 用户编号
	* @param departmentID 部门编号
	*/
	public UserChargeDepartmentInfo(int userID,int departmentID)
	{
		this.userID = userID;
		this.departmentID = departmentID;
	}
	
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param userID 用户编号
	* @param departmentID 部门编号
	* @param name 部门名称
	* @param remark 备注
	*/
	public UserChargeDepartmentInfo(int iD,int userID,int departmentID,String name,String remark)
	{
		this(userID, departmentID);
		this.iD = iD;
		super.setName(name);
		super.setRemark(remark);
	}
	
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param userID 用户编号
	* @param departmentID 部门编号
	* @param name 部门名称
	* @param remark 备注
	*/
	public UserChargeDepartmentInfo(int departmentID,String name,String remark)
	{
		this.departmentID = departmentID;
		super.setName(name);
		super.setRemark(remark);
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
	 * 部门编号
	 */
	private int departmentID=0;

	/**
	 * 获取属性值：部门编号
	 * @return 部门编号
	 */
	public int getDepartmentID()
	{
		return departmentID;
	}

	/**
	 * 设置属性值：部门编号
	 * @param departmentID 部门编号
	 */
	public void setDepartmentID(int departmentID)
	{
		this.departmentID = departmentID;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public UserChargeDepartmentInfo clone()
	{
		UserChargeDepartmentInfo item = new UserChargeDepartmentInfo(iD,userID,departmentID);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param userChargeDepartmentInfo 指定的对象源
	*/
	public void cloneFrom(UserChargeDepartmentInfo userChargeDepartmentInfo)
	{
		this.iD = userChargeDepartmentInfo.getID();
		this.userID = userChargeDepartmentInfo.getUserID();
		this.departmentID = userChargeDepartmentInfo.getDepartmentID();
	}



    
}



