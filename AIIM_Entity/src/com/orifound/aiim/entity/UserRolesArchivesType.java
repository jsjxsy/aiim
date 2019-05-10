package com.orifound.aiim.entity;

    
/**
 * 用户角色的档案类型权限表实体类
 */
public class UserRolesArchivesType
{
    /**
     * 构造函数
     */
    public UserRolesArchivesType()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param rolesID 用户角色编号
	* @param archivesTypeID 档案分类编号
	* @param rightFlag_ArchivesInfo 可查看档案基本信息的权限标志
	* @param rightFlag_AttachedFile 可查阅原文电子文件的权限标志
	* @param rightFlag_PaperArchives 可利用实物档案的权限标志
	*/
	public UserRolesArchivesType(int iD,int rolesID,int archivesTypeID,boolean rightFlag_ArchivesInfo,boolean rightFlag_AttachedFile,boolean rightFlag_PaperArchives)
	{
		// Table Name: DDR_UserRoles_ArchivesType
		// Columns List,Can Used in SELECT SQL: ID,RolesID,ArchivesTypeID,RightFlag_ArchivesInfo,RightFlag_AttachedFile,RightFlag_PaperArchives
		// Columns List,Can Used in INSERT SQL: :ID,:RolesID,:ArchivesTypeID,:RightFlag_ArchivesInfo,:RightFlag_AttachedFile,:RightFlag_PaperArchives
		// Columns List,Can Used in UPDATE SQL: ID=:ID,RolesID=:RolesID,ArchivesTypeID=:ArchivesTypeID,RightFlag_ArchivesInfo=:RightFlag_ArchivesInfo,RightFlag_AttachedFile=:RightFlag_AttachedFile,RightFlag_PaperArchives=:RightFlag_PaperArchives

		this.iD = iD;
		this.rolesID = rolesID;
		this.archivesTypeID = archivesTypeID;
		this.rightFlag_ArchivesInfo = rightFlag_ArchivesInfo;
		this.rightFlag_AttachedFile = rightFlag_AttachedFile;
		this.rightFlag_PaperArchives = rightFlag_PaperArchives;
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
	 * 可查看档案基本信息的权限标志
	 */
	private boolean rightFlag_ArchivesInfo=false;

	/**
	 * 获取属性值：可查看档案基本信息的权限标志
	 * @return 可查看档案基本信息的权限标志
	 */
	public boolean getRightFlag_ArchivesInfo()
	{
		return rightFlag_ArchivesInfo;
	}

	/**
	 * 设置属性值：可查看档案基本信息的权限标志
	 * @param rightFlag_ArchivesInfo 可查看档案基本信息的权限标志
	 */
	public void setRightFlag_ArchivesInfo(boolean rightFlag_ArchivesInfo)
	{
		this.rightFlag_ArchivesInfo = rightFlag_ArchivesInfo;
	}

	/**
	 * 可查阅原文电子文件的权限标志
	 */
	private boolean rightFlag_AttachedFile=false;

	/**
	 * 获取属性值：可查阅原文电子文件的权限标志
	 * @return 可查阅原文电子文件的权限标志
	 */
	public boolean getRightFlag_AttachedFile()
	{
		return rightFlag_AttachedFile;
	}

	/**
	 * 设置属性值：可查阅原文电子文件的权限标志
	 * @param rightFlag_AttachedFile 可查阅原文电子文件的权限标志
	 */
	public void setRightFlag_AttachedFile(boolean rightFlag_AttachedFile)
	{
		this.rightFlag_AttachedFile = rightFlag_AttachedFile;
	}

	/**
	 * 可利用实物档案的权限标志
	 */
	private boolean rightFlag_PaperArchives=false;

	/**
	 * 获取属性值：可利用实物档案的权限标志
	 * @return 可利用实物档案的权限标志
	 */
	public boolean getRightFlag_PaperArchives()
	{
		return rightFlag_PaperArchives;
	}

	/**
	 * 设置属性值：可利用实物档案的权限标志
	 * @param rightFlag_PaperArchives 可利用实物档案的权限标志
	 */
	public void setRightFlag_PaperArchives(boolean rightFlag_PaperArchives)
	{
		this.rightFlag_PaperArchives = rightFlag_PaperArchives;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public UserRolesArchivesType clone()
	{
		UserRolesArchivesType item = new UserRolesArchivesType(iD,rolesID,archivesTypeID,rightFlag_ArchivesInfo,rightFlag_AttachedFile,rightFlag_PaperArchives);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param userRolesArchivesType 指定的对象源
	*/
	public void cloneFrom(UserRolesArchivesType userRolesArchivesType)
	{
		this.iD = userRolesArchivesType.getID();
		this.rolesID = userRolesArchivesType.getRolesID();
		this.archivesTypeID = userRolesArchivesType.getArchivesTypeID();
		this.rightFlag_ArchivesInfo = userRolesArchivesType.getRightFlag_ArchivesInfo();
		this.rightFlag_AttachedFile = userRolesArchivesType.getRightFlag_AttachedFile();
		this.rightFlag_PaperArchives = userRolesArchivesType.getRightFlag_PaperArchives();
		this.keyInCol = userRolesArchivesType.getKeyInCol();
		this.tag = userRolesArchivesType.getTag();
	}


//	/**
//	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
//	 * 
//	 */
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//	private class UserRolesArchivesTypeMapper implements RowMapper<UserRolesArchivesType>
//	{
//		
//		@Override
//		public UserRolesArchivesType mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			int iD = rs.getInt("ID");
//			int rolesID = rs.getInt("RolesID");
//			int archivesTypeID = rs.getInt("ArchivesTypeID");
//			boolean rightFlag_ArchivesInfo = rs.getBoolean("RightFlag_ArchivesInfo");
//			boolean rightFlag_AttachedFile = rs.getBoolean("RightFlag_AttachedFile");
//			boolean rightFlag_PaperArchives = rs.getBoolean("RightFlag_PaperArchives");
//			
//			return new UserRolesArchivesType(iD,rolesID,archivesTypeID,rightFlag_ArchivesInfo,rightFlag_AttachedFile,rightFlag_PaperArchives);
//		}
//	}


}
