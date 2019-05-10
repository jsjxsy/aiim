package com.orifound.aiim.entity;

    
/**
 * 用户档案分类权限表的实体类
 */
public class UserRightArchivesType extends ArchivesType
{
    /**
     * 构造函数
     */
    public UserRightArchivesType()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 授权记录编号
	* @param userID 用户编号
	* @param archivesTypeID 档案分类编号
	* @param rightFlag_ArchivesInfo 可查看档案基本信息的权限标志
	* @param rightFlag_AttachedFile 可查阅原文电子文件的权限标志
	* @param rightFlag_PaperArchives 可利用实物档案的权限标志
	*/
	public UserRightArchivesType(int iD,int userID,int archivesTypeID,boolean rightFlag_ArchivesInfo,boolean rightFlag_AttachedFile,boolean rightFlag_PaperArchives)
	{
		// Table Name: UserRight_ArchivesType
		// Columns List,Can Used in SELECT SQL: ID,UserID,ArchivesTypeID,RightFlag_ArchivesInfo,RightFlag_AttachedFile,RightFlag_PaperArchives
		// Columns List,Can Used in INSERT SQL: :ID,:UserID,:ArchivesTypeID,:RightFlag_ArchivesInfo,:RightFlag_AttachedFile,:RightFlag_PaperArchives
		// Columns List,Can Used in UPDATE SQL: ID=:ID,UserID=:UserID,ArchivesTypeID=:ArchivesTypeID,RightFlag_ArchivesInfo=:RightFlag_ArchivesInfo,RightFlag_AttachedFile=:RightFlag_AttachedFile,RightFlag_PaperArchives=:RightFlag_PaperArchives

		this(userID, archivesTypeID, rightFlag_ArchivesInfo, rightFlag_AttachedFile, rightFlag_PaperArchives);
		this.iD = iD;
	}
	
	/**
	* 带字段参数的构造函数
	* @param userID 用户编号
	* @param archivesTypeID 档案分类编号
	* @param rightFlag_ArchivesInfo 可查看档案基本信息的权限标志
	* @param rightFlag_AttachedFile 可查阅原文电子文件的权限标志
	* @param rightFlag_PaperArchives 可利用实物档案的权限标志
	*/
	public UserRightArchivesType(int userID,int archivesTypeID,boolean rightFlag_ArchivesInfo,boolean rightFlag_AttachedFile,boolean rightFlag_PaperArchives)
	{
		this.userID = userID;
		this.archivesTypeID = archivesTypeID;
		setRightFlag_ArchivesInfo(rightFlag_ArchivesInfo);
		setRightFlag_AttachedFile(rightFlag_AttachedFile);
		setRightFlag_PaperArchives(rightFlag_PaperArchives);
	}
	
	/**
	* 带字段参数的构造函数
	* @param iD 授权记录编号
	* @param userID 用户编号
	* @param archivesTypeID 档案分类编号
	* @param rightFlag_ArchivesInfo 可查看档案基本信息的权限标志
	* @param rightFlag_AttachedFile 可查阅原文电子文件的权限标志
	* @param rightFlag_PaperArchives 可利用实物档案的权限标志
	* @param topFlag 一级类目标志
	* @param name 档案分类名称
	* @param code 分类代码
	* @param parentID 上级档案分类编号
	* @param topTypeID 顶层分类编号
	* @param fullCode 完整代码
	* @param fullName 完整名称
	* @param endFlag 最底层标志
	* @param contentIDFormatLength 案卷号的格式化长度
	* @param subContentIDFormatLength 卷内文件序号的格式化长度
	* @param archivesIDExpressionPrefix 档号前缀组成表达式
	* @param archivesIDExpression 档号组成表达式
	* @param subArchivesIDExpression 卷内文件的档号组成表达式
	* @param attachedFileSavePath 原文存储路径
	* @param renewPeriod 续借周期
	* @param remark 备注
	*/
	public UserRightArchivesType(int iD,int userID,int archivesTypeID,boolean rightFlag_ArchivesInfo,
			boolean rightFlag_AttachedFile,boolean rightFlag_PaperArchives,
			boolean topFlag,String name,String code,int parentID,int topTypeID,String fullCode,
			String fullName,boolean endFlag,int contentIDFormatLength,int subContentIDFormatLength,
			String archivesIDExpressionPrefix,String archivesIDExpression,String subArchivesIDExpression,
			String attachedFileSavePath,int renewPeriod,String remark)
	{
		this(iD,userID, archivesTypeID, rightFlag_ArchivesInfo, rightFlag_AttachedFile, rightFlag_PaperArchives);
		super.setTopFlag(topFlag);
		super.setName(name);
		super.setCode(code);
		super.setParentID(parentID);
		super.setTopTypeID(topTypeID);
		super.setFullCode(fullCode);
		super.setFullName(fullName);
		super.setEndFlag(endFlag);
		super.setContentIDFormatLength(contentIDFormatLength);
		super.setSubContentIDFormatLength(subContentIDFormatLength);
		super.setArchivesIDExpressionPrefix(archivesIDExpressionPrefix);
		super.setArchivesIDExpression(archivesIDExpression);
		super.setSubArchivesIDExpression(subArchivesIDExpression);
		super.setAttachedFileSavePath(attachedFileSavePath);
		super.setRenewPeriod(renewPeriod);
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
	 * 授权记录编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：授权记录编号
	 * @return 授权记录编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：授权记录编号
	 * @param iD 授权记录编号
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
	 * 档案分类编号
	 */
	private int archivesTypeID=0;

	/**
	 * 获取属性值：档案分类编号
	 * @return 档案分类编号
	 */
	public int getArchivesTypeID()
	{
		return archivesTypeID;
	}

	/**
	 * 设置属性值：档案分类编号
	 * @param archivesTypeID 档案分类编号
	 */
	public void setArchivesTypeID(int archivesTypeID)
	{
		this.archivesTypeID = archivesTypeID;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public UserRightArchivesType clone()
	{
		UserRightArchivesType item = new UserRightArchivesType(iD,userID,archivesTypeID,getRightFlag_ArchivesInfo(),getRightFlag_AttachedFile(),getRightFlag_PaperArchives());
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param userRightArchivesType 指定的对象源
	*/
	public void cloneFrom(UserRightArchivesType userRightArchivesType)
	{
		super.cloneFrom(userRightArchivesType);
		this.iD = userRightArchivesType.getID();
		this.userID = userRightArchivesType.getUserID();
		this.archivesTypeID = userRightArchivesType.getArchivesTypeID();
	}

    
}



