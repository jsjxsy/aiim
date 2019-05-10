package com.orifound.aiim.entity;

    
/**
 * 用户档案密级权限表的实体类
 */
public class UserRightArchivesSecrecy extends ArchivesSecrecy
{
    /**
     * 构造函数
     */
    public UserRightArchivesSecrecy()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 授权记录编号
	* @param userID 用户编号
	* @param secrecyID 档案密级编号
	* @param rightFlag_ArchivesInfo 可查看档案基本信息的权限标志
	* @param rightFlag_AttachedFile 可查阅原文电子文件的权限标志
	* @param rightFlag_PaperArchives 可利用实物档案的权限标志
	*/
	public UserRightArchivesSecrecy(int iD,int userID,int secrecyID,boolean rightFlag_ArchivesInfo,boolean rightFlag_AttachedFile,boolean rightFlag_PaperArchives)
	{
		// Table Name: UserRight_ArchivesSecrecy
		// Columns List,Can Used in SELECT SQL: ID,UserID,SecrecyID,RightFlag_ArchivesInfo,RightFlag_AttachedFile,RightFlag_PaperArchives
		// Columns List,Can Used in INSERT SQL: :ID,:UserID,:SecrecyID,:RightFlag_ArchivesInfo,:RightFlag_AttachedFile,:RightFlag_PaperArchives
		// Columns List,Can Used in UPDATE SQL: ID=:ID,UserID=:UserID,SecrecyID=:SecrecyID,RightFlag_ArchivesInfo=:RightFlag_ArchivesInfo,RightFlag_AttachedFile=:RightFlag_AttachedFile,RightFlag_PaperArchives=:RightFlag_PaperArchives

		this(userID, secrecyID,rightFlag_ArchivesInfo,rightFlag_AttachedFile,rightFlag_PaperArchives);
		this.iD = iD;
	}
	
	/**
	* 带字段参数的构造函数
	* @param userID 用户编号
	* @param secrecyID 档案密级编号
	* @param rightFlag_ArchivesInfo 可查看档案基本信息的权限标志
	* @param rightFlag_AttachedFile 可查阅原文电子文件的权限标志
	* @param rightFlag_PaperArchives 可利用实物档案的权限标志
	*/
	public UserRightArchivesSecrecy(int userID,int secrecyID,boolean rightFlag_ArchivesInfo,boolean rightFlag_AttachedFile,boolean rightFlag_PaperArchives)
	{
		this.userID = userID;
		this.secrecyID = secrecyID;
		setRightFlag_ArchivesInfo(rightFlag_ArchivesInfo);
		setRightFlag_AttachedFile(rightFlag_AttachedFile);
		setRightFlag_PaperArchives(rightFlag_PaperArchives);
	}
	
	/**
	* 带字段参数的构造函数
	* @param iD 授权记录编号
	* @param userID 用户编号
	* @param secrecyID 档案密级编号
	* @param rightFlag_ArchivesInfo 可查看档案基本信息的权限标志
	* @param rightFlag_AttachedFile 可查阅原文电子文件的权限标志
	* @param rightFlag_PaperArchives 可利用实物档案的权限标志
	* @param name 档案密级名称
	* @param remark 备注
	*/
	public UserRightArchivesSecrecy(int iD,int userID,int secrecyID,boolean rightFlag_ArchivesInfo,boolean rightFlag_AttachedFile,boolean rightFlag_PaperArchives,String name,String remark)
	{
		this(iD,userID, secrecyID,rightFlag_ArchivesInfo,rightFlag_AttachedFile,rightFlag_PaperArchives);
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
	public UserRightArchivesSecrecy clone()
	{
		UserRightArchivesSecrecy item = new UserRightArchivesSecrecy(iD, userID, secrecyID,getRightFlag_ArchivesInfo(),getRightFlag_AttachedFile(),getRightFlag_PaperArchives(), getName(), getRemark());
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param userRightArchivesSecrecy 指定的对象源
	*/
	public void cloneFrom(UserRightArchivesSecrecy userRightArchivesSecrecy)
	{
		super.cloneFrom(userRightArchivesSecrecy);
		this.iD = userRightArchivesSecrecy.getID();
		this.userID = userRightArchivesSecrecy.getUserID();
		this.secrecyID = userRightArchivesSecrecy.getSecrecyID();
		this.keyInCol = userRightArchivesSecrecy.getKeyInCol();
		this.tag = userRightArchivesSecrecy.getTag();
	}

    
}



