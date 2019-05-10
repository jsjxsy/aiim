package com.orifound.aiim.entity;

    
/**
 * 用户角色与系统功能字典关系表的实体类
 */
public class RoleRightSystemFeature extends SystemFeature
{
    /**
     * 构造函数
     */
    public RoleRightSystemFeature()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param rolesID 用户角色编号
	* @param featureID 系统功能编号
	*/
	public RoleRightSystemFeature(int iD,int rolesID,int featureID)
	{
		// Table Name: DDR_UserRoles_SystemFeature
		// Columns List,Can Used in SELECT SQL: ID,RolesID,FeatureID
		// Columns List,Can Used in INSERT SQL: :ID,:RolesID,:FeatureID
		// Columns List,Can Used in UPDATE SQL: ID=:ID,RolesID=:RolesID,FeatureID=:FeatureID

		this(rolesID, featureID);
		this.iD = iD;
	}
	
	/**
	* 带字段参数的构造函数
	* @param rolesID 用户角色编号
	* @param featureID 系统功能编号
	*/
	public RoleRightSystemFeature(int rolesID,int featureID)
	{
		this.rolesID = rolesID;
		this.featureID = featureID;
	}
	
	/**
	* 带字段参数的构造函数
	* @param iD 授权编号
	* @param userID 用户编号
	* @param featureID 系统功能编号
	* @param title 功能标题
	* @param name 名称
	* @param parentID 上级编号
	* @param uclKey 用户访问控制列表中的Key值
	* @param menuFlag 菜单标志
	* @param topFlag 顶层功能标志
	* @param menuURI 菜单功能URI地址
	* @param remark 备注
	*/
	public RoleRightSystemFeature(int iD,int rolesID,int featureID,String title,String name,int parentID,
			String uclKey,boolean menuFlag,boolean topFlag,String menuURI,String remark)
	{
		this(iD,rolesID, featureID);
		super.setTitle(title);
		super.setName(name);
		super.setParentID(parentID);
		super.setUclKey(uclKey);
		super.setMenuFlag(menuFlag);
		super.setTopFlag(topFlag);
		super.setMenuURI(menuURI);
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
	 * 系统功能编号
	 */
	private int featureID=0;

	/**
	 * 获取属性值：系统功能编号
	 * @return 系统功能编号
	 */
	public int getFeatureID()
	{
		return featureID;
	}

	/**
	 * 设置属性值：系统功能编号
	 * @param featureID 系统功能编号
	 */
	public void setFeatureID(int featureID)
	{
		this.featureID = featureID;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public RoleRightSystemFeature clone()
	{
		RoleRightSystemFeature item = new RoleRightSystemFeature(featureID, rolesID, featureID, getTitle(), getName(), getParentID(), getUclKey(), getMenuFlag(), getTopFlag(), getMenuURI(), getRemark());
		item.setKeyInCol(keyInCol);
		item.setTag(tag);
		
		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param roleRightSystemFeature 指定的对象源
	*/
	public void cloneFrom(RoleRightSystemFeature roleRightSystemFeature)
	{
		super.cloneFrom(roleRightSystemFeature);
		this.iD = roleRightSystemFeature.getID();
		this.rolesID = roleRightSystemFeature.getRolesID();
		this.featureID = roleRightSystemFeature.getFeatureID();
	}

    
}



