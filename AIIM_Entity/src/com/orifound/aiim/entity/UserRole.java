package com.orifound.aiim.entity;

import java.util.List;

    
/**
 * 用户角色字典表的实体类
 */
public class UserRole
{
    /**
     * 构造函数
     */
    public UserRole()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 角色编号
	* @param name 角色名称
	* @param systemRolesFlag 系统固有角色标志
	* @param remark 备注
	*/
	public UserRole(int iD,String name,int systemRolesFlag,String remark)
	{
		// Table Name: DD_UserRoles
		// Columns List,Can Used in SELECT SQL: ID,Name,SystemRolesFlag,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:SystemRolesFlag,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,SystemRolesFlag=:SystemRolesFlag,Remark=:Remark

		this.iD = iD;
		this.name = name;
		this.systemRolesFlag = EnumSystemRole.getEnumElement(systemRolesFlag);
		this.remark = remark;
	}
	
	/**
	* 带字段参数的构造函数
	* @param iD 角色编号
	* @param name 角色名称
	* @param systemRole 系统固有角色枚举类
	* @param remark 备注
	*/
	public UserRole(int iD,String name,EnumSystemRole systemRole,String remark)
	{
		// Table Name: DD_UserRoles
		// Columns List,Can Used in SELECT SQL: ID,Name,SystemRolesFlag,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:SystemRolesFlag,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,SystemRolesFlag=:SystemRolesFlag,Remark=:Remark

		this.iD = iD;
		this.name = name;
		this.systemRolesFlag = systemRole;
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
	 * 角色编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：角色编号
	 * @return 角色编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：角色编号
	 * @param iD 角色编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 角色名称
	 */
	private String name=null;

	/**
	 * 获取属性值：角色名称
	 * @return 角色名称
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 设置属性值：角色名称
	 * @param name 角色名称
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * 系统固有角色标志
	 */
	private EnumSystemRole systemRolesFlag= EnumSystemRole.NONE;

	/**
	 * 获取属性值：系统固有角色标志
	 * @return 系统固有角色标志
	 */
	public EnumSystemRole getSystemRolesFlag()
	{
		return systemRolesFlag;
	}

	/**
	 * 设置属性值：系统固有角色标志
	 * @param systemRolesFlag 系统固有角色标志
	 */
	public void setSystemRolesFlag(EnumSystemRole systemRolesFlag)
	{
		this.systemRolesFlag = systemRolesFlag;
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
	public UserRole clone()
	{
		UserRole item = new UserRole(iD,name,systemRolesFlag,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param userRole 指定的对象源
	*/
	public void cloneFrom(UserRole userRole)
	{
		this.iD = userRole.getID();
		this.name = userRole.getName();
		this.systemRolesFlag = userRole.getSystemRolesFlag();
		this.remark = userRole.getRemark();
		this.keyInCol = userRole.getKeyInCol();
		this.tag = userRole.getTag();
	}
    
	/**
	 * 当前角色拥有的用户信息集合
	 */
	private List<UserRolesInfo> hasUsers;

	/**
	 * 设置属性值：当前角色拥有的用户信息集合
	 * @param hasUsers 当前角色拥有的用户信息集合
	 */
	public void setHasUsers(List<UserRolesInfo> hasUsers) {
		this.hasUsers = hasUsers;
	}

	/**
	 * 获取属性值：当前角色拥有的用户信息集合
	 * @return 当前角色拥有的用户信息集合
	 */
	public List<UserRolesInfo> getHasUsers() {
		return hasUsers;
	}
	
	
}



