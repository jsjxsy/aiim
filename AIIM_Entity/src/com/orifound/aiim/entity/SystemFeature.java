package com.orifound.aiim.entity;

import java.util.LinkedHashMap;

    
/**
 * 系统功能数据字典表的实体类
 */
public class SystemFeature
{
    /**
     * 构造函数
     */
    public SystemFeature()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param title 功能标题
	* @param name 名称
	* @param parentID 上级编号
	* @param uclKey 用户访问控制列表中的Key值
	* @param menuFlag 菜单标志
	* @param topFlag 顶层功能标志
	* @param menuURI 菜单功能URI地址
	* @param pageSize 分页显示的大小
	* @param remark 备注
	*/
	public SystemFeature(int iD,String title,String name,int parentID,String uclKey,boolean menuFlag,boolean topFlag,String menuURI,int pageSize,String remark)
	{
		// Table Name: DD_SystemFeature
		// Columns List,Can Used in SELECT SQL: ID,Title,Name,ParentID,UclKey,MenuFlag,TopFlag,MenuURI,PageSize,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Title,:Name,:ParentID,:UclKey,:MenuFlag,:TopFlag,:MenuURI,:PageSize,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Title=:Title,Name=:Name,ParentID=:ParentID,UclKey=:UclKey,MenuFlag=:MenuFlag,TopFlag=:TopFlag,MenuURI=:MenuURI,PageSize=:PageSize,Remark=:Remark

		this(title, name, parentID, uclKey, menuFlag, topFlag, menuURI,pageSize, remark);
		this.iD = iD;
	}
	
	/**
	* 带字段参数的构造函数
	* @param title 功能标题
	* @param name 名称
	* @param parentID 上级编号
	* @param uclKey 用户访问控制列表中的Key值
	* @param menuFlag 菜单标志
	* @param topFlag 顶层功能标志
	* @param menuURI 菜单功能URI地址
	* @param pageSize 分页显示的大小
	* @param remark 备注
	*/
	public SystemFeature(String title,String name,int parentID,String uclKey,boolean menuFlag,boolean topFlag,String menuURI,int pageSize,String remark)
	{
		this.title = title;
		this.name = name;
		this.parentID = parentID;
		this.uclKey = uclKey;
		this.menuFlag = menuFlag;
		this.topFlag = topFlag;
		this.menuURI = menuURI;
		this.pageSize = pageSize;
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
	 * 功能标题
	 */
	private String title=null;

	/**
	 * 获取属性值：功能标题
	 * @return 
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * 设置属性值：功能标题
	 * @param title 
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * 名称
	 */
	private String name=null;

	/**
	 * 获取属性值：名称
	 * @return 名称
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 设置属性值：名称
	 * @param name 名称
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * 上级编号
	 */
	private int parentID=0;

	/**
	 * 获取属性值：上级编号
	 * @return 上级编号
	 */
	public int getParentID()
	{
		return parentID;
	}

	/**
	 * 设置属性值：上级编号
	 * @param parentID 上级编号
	 */
	public void setParentID(int parentID)
	{
		this.parentID = parentID;
	}

	/**
	 * 用户访问控制列表中的Key值
	 */
	private String uclKey=null;

	/**
	 * 获取属性值：用户访问控制列表中的Key值
	 * @return 用户访问控制列表中的Key值
	 */
	public String getUclKey()
	{
		return uclKey;
	}

	/**
	 * 设置属性值：用户访问控制列表中的Key值
	 * @param uclKey 用户访问控制列表中的Key值
	 */
	public void setUclKey(String uclKey)
	{
		this.uclKey = uclKey;
	}

	/**
	 * 菜单标志
	 */
	private boolean menuFlag=false;

	/**
	 * 获取属性值：菜单标志
	 * @return 菜单标志
	 */
	public boolean getMenuFlag()
	{
		return menuFlag;
	}

	/**
	 * 设置属性值：菜单标志
	 * @param menuFlag 菜单标志
	 */
	public void setMenuFlag(boolean menuFlag)
	{
		this.menuFlag = menuFlag;
	}

	/**
	 * 顶层功能标志
	 */
	private boolean topFlag=false;

	/**
	 * 获取属性值：顶层功能标志
	 * @return 顶层功能标志
	 */
	public boolean getTopFlag()
	{
		return topFlag;
	}

	/**
	 * 设置属性值：顶层功能标志
	 * @param topFlag 顶层功能标志
	 */
	public void setTopFlag(boolean topFlag)
	{
		this.topFlag = topFlag;
	}

	/**
	 * 菜单功能URI地址
	 */
	private String menuURI=null;

	/**
	 * 获取属性值：菜单功能URI地址
	 * @return 菜单功能URI地址
	 */
	public String getMenuURI()
	{
		return menuURI;
	}

	/**
	 * 设置属性值：菜单功能URI地址
	 * @param menuURI 菜单功能URI地址
	 */
	public void setMenuURI(String menuURI)
	{
		this.menuURI = menuURI;
	}

	/**
	 * 分页显示的大小
	 */
	private int pageSize=0;

	/**
	 * 获取属性值：分页显示的大小
	 * @return 分页显示的大小
	 */
	public int getPageSize()
	{
		return pageSize;
	}

	/**
	 * 设置属性值：分页显示的大小
	 * @param pageSize 分页显示的大小
	 */
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
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
	public SystemFeature clone()
	{
		SystemFeature item = new SystemFeature(iD,title,name,parentID,uclKey,menuFlag,topFlag,menuURI,pageSize,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param systemFeature 指定的对象源
	*/
	public void cloneFrom(SystemFeature systemFeature)
	{
		this.iD = systemFeature.getID();
		this.title = systemFeature.getTitle();
		this.name = systemFeature.getName();
		this.parentID = systemFeature.getParentID();
		this.uclKey = systemFeature.getUclKey();
		this.menuFlag = systemFeature.getMenuFlag();
		this.topFlag = systemFeature.getTopFlag();
		this.menuURI = systemFeature.getMenuURI();
		this.pageSize = systemFeature.getPageSize();
		this.remark = systemFeature.getRemark();
	}
	
	/**
	 * 子功能集合
	 */
	private LinkedHashMap<String, SystemFeature> childSystemFeatures = null;

	/**
	 * 设置属性值：子功能集合
	 * @param childSystemFeatures 子功能集合
	 */
	public void setChildSystemFeatures(LinkedHashMap<String, SystemFeature> childSystemFeatures)
	{
		this.childSystemFeatures = childSystemFeatures;
	}

	/**
	 * 获取属性值：子功能集合
	 * @return 子功能集合
	 */
	public LinkedHashMap<String, SystemFeature> getChildSystemFeatures()
	{
		return childSystemFeatures;
	}

}



