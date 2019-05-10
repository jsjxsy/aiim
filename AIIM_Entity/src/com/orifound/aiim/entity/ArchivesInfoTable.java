package com.orifound.aiim.entity;

import java.util.Date;
    
/**
 * 档案信息相关表的字典表的实体类
 */
public class ArchivesInfoTable
{
    /**
     * 构造函数
     */
    public ArchivesInfoTable()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param archivesTypeID 档案分类编号
	* @param tableType 表类型
	* @param tableName 档案表名称
	* @param createdFlag 表创建标志
	* @param createdTime 创建时间
	* @param remark 备注
	*/
	public ArchivesInfoTable(int iD,int archivesTypeID,int tableType,String tableName,boolean createdFlag,Date createdTime,String remark)
	{
		// Columns List,Can Used in SELECT SQL: ID,ArchivesTypeID,TableType,TableName,CreatedFlag,CreatedTime,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:ArchivesTypeID,:TableType,:TableName,:CreatedFlag,:CreatedTime,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ArchivesTypeID=:ArchivesTypeID,TableType=:TableType,TableName=:TableName,CreatedFlag=:CreatedFlag,CreatedTime=:CreatedTime,Remark=:Remark

		this(archivesTypeID, tableType, tableName, createdFlag, createdTime, remark);
		this.iD = iD;
	}
    
	/**
	* 带字段参数的构造函数
	* @param archivesTypeID 档案分类编号
	* @param tableType 表类型
	* @param tableName 档案表名称
	* @param remark 备注
	*/
	public ArchivesInfoTable(int archivesTypeID,int tableType,String tableName,String remark)
	{
		this.archivesTypeID = archivesTypeID;
		this.tableType =EnumArchivesInfoTableType.getEnumElement(tableType);
		this.tableName = tableName;
		this.remark = remark;
	}
    
	/**
	* 带字段参数的构造函数
	* @param archivesTypeID 档案分类编号
	* @param tableType 表类型
	* @param tableName 档案表名称
	* @param createdFlag 表创建标志
	* @param createdTime 创建时间
	* @param remark 备注
	*/
	public ArchivesInfoTable(int archivesTypeID,int tableType,String tableName,boolean createdFlag,Date createdTime,String remark)
	{
		this(archivesTypeID, tableType, tableName, remark);
		this.createdFlag = createdFlag;
		this.createdTime = createdTime;
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
	 * 表类型
	 */
	private EnumArchivesInfoTableType tableType=EnumArchivesInfoTableType.NONE;

	/**
	 * 获取属性值：表类型
	 * @return 表类型
	 */
	public EnumArchivesInfoTableType getTableType()
	{
		return tableType;
	}

	/**
	 * 设置属性值：表类型
	 * @param tableType 表类型
	 */
	public void setTableType(EnumArchivesInfoTableType tableType)
	{
		this.tableType = tableType;
	}

	/**
	 * 档案表名称
	 */
	private String tableName=null;

	/**
	 * 获取属性值：档案表名称
	 * @return 档案表名称
	 */
	public String getTableName()
	{
		return tableName;
	}

	/**
	 * 设置属性值：档案表名称
	 * @param tableName 档案表名称
	 */
	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	/**
	 * 表创建标志
	 */
	private boolean createdFlag=false;

	/**
	 * 获取属性值：表创建标志
	 * @return 表创建标志
	 */
	public boolean getCreatedFlag()
	{
		return createdFlag;
	}

	/**
	 * 设置属性值：表创建标志
	 * @param createdFlag 表创建标志
	 */
	public void setCreatedFlag(boolean createdFlag)
	{
		this.createdFlag = createdFlag;
	}

	/**
	 * 创建时间
	 */
	private Date createdTime;

	/**
	 * 获取属性值：创建时间
	 * @return 创建时间
	 */
	public Date getCreatedTime()
	{
		return createdTime;
	}

	/**
	 * 设置属性值：创建时间
	 * @param createdTime 创建时间
	 */
	public void setCreatedTime(Date createdTime)
	{
		this.createdTime = createdTime;
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
	public ArchivesInfoTable clone()
	{
		ArchivesInfoTable item = new ArchivesInfoTable(iD,archivesTypeID,tableType.getEnumValue(),tableName,createdFlag,createdTime,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param archivesInfoTable 指定的对象源
	*/
	public void cloneFrom(ArchivesInfoTable archivesInfoTable)
	{
		this.iD = archivesInfoTable.getID();
		this.archivesTypeID = archivesInfoTable.getArchivesTypeID();
		this.tableType = archivesInfoTable.getTableType();
		this.tableName = archivesInfoTable.getTableName();
		this.createdFlag = archivesInfoTable.getCreatedFlag();
		this.createdTime = archivesInfoTable.getCreatedTime();
		this.remark = archivesInfoTable.getRemark();
	}
    
}



