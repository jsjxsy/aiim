package com.orifound.aiim.entity;

import java.util.Date;
    
/**
 * 原文电子文件删除信息表的实体类
 */
public class ArchivesInfoAttachedFileDeleted
{
    /**
     * 构造函数
     */
    public ArchivesInfoAttachedFileDeleted()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 原文编号
	* @param archivesTypeID 档案分类编号
	* @param nBXH 原文所属文件的内部序号
	* @param title 原文标题
	* @param archivingFileName 归档文件名
	* @param dealedFlag 处理标志
	* @param dealedDate 删除日期
	* @param remark 备注
	*/
	public ArchivesInfoAttachedFileDeleted(int iD,int archivesTypeID,int nBXH,String title,String archivingFileName,boolean dealedFlag,Date dealedDate,String remark)
	{
		// Table Name: ArchivesInfoAttachedFileDeleted
		// Columns List,Can Used in SELECT SQL: ID,ArchivesTypeID,NBXH,Title,ArchivingFileName,DealedFlag,DealedDate,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:ArchivesTypeID,:NBXH,:Title,:ArchivingFileName,:DealedFlag,:DealedDate,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,Title=:Title,ArchivingFileName=:ArchivingFileName,DealedFlag=:DealedFlag,DealedDate=:DealedDate,Remark=:Remark

		this(archivesTypeID, nBXH, title, archivingFileName, remark);
		this.iD = iD;
		this.dealedFlag = dealedFlag;
		this.dealedDate = dealedDate;
	}
	
    /**
	* 带字段参数的构造函数
	* @param archivesTypeID 档案分类编号
	* @param nBXH 原文所属文件的内部序号
	* @param title 原文标题
	* @param archivingFileName 归档文件名
	* @param remark 备注
	*/
	public ArchivesInfoAttachedFileDeleted(int archivesTypeID,int nBXH,String title,String archivingFileName,String remark)
	{
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.title = title;
		this.archivingFileName = archivingFileName;
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
	 * 原文编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：原文编号
	 * @return 原文编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：原文编号
	 * @param iD 原文编号
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
	 * 原文所属文件的内部序号
	 */
	private int nBXH=0;

	/**
	 * 获取属性值：原文所属文件的内部序号
	 * @return 原文所属文件的内部序号
	 */
	public int getNBXH()
	{
		return nBXH;
	}

	/**
	 * 设置属性值：原文所属文件的内部序号
	 * @param nBXH 原文所属文件的内部序号
	 */
	public void setNBXH(int nBXH)
	{
		this.nBXH = nBXH;
	}

	/**
	 * 原文标题
	 */
	private String title=null;

	/**
	 * 获取属性值：原文标题
	 * @return 原文标题
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * 设置属性值：原文标题
	 * @param title 原文标题
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * 归档文件名
	 */
	private String archivingFileName=null;

	/**
	 * 获取属性值：归档文件名
	 * @return 归档文件名
	 */
	public String getArchivingFileName()
	{
		return archivingFileName;
	}

	/**
	 * 设置属性值：归档文件名
	 * @param archivingFileName 归档文件名
	 */
	public void setArchivingFileName(String archivingFileName)
	{
		this.archivingFileName = archivingFileName;
	}

	/**
	 * 处理标志
	 */
	private boolean dealedFlag=false;

	/**
	 * 获取属性值：处理标志
	 * @return 处理标志
	 */
	public boolean getDealedFlag()
	{
		return dealedFlag;
	}

	/**
	 * 设置属性值：处理标志
	 * @param dealedFlag 处理标志
	 */
	public void setDealedFlag(boolean dealedFlag)
	{
		this.dealedFlag = dealedFlag;
	}

	/**
	 * 删除日期
	 */
	private Date dealedDate;

	/**
	 * 获取属性值：删除日期
	 * @return 删除日期
	 */
	public Date getDealedDate()
	{
		return dealedDate;
	}

	/**
	 * 设置属性值：删除日期
	 * @param dealedDate 删除日期
	 */
	public void setDealedDate(Date dealedDate)
	{
		this.dealedDate = dealedDate;
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
	public ArchivesInfoAttachedFileDeleted clone()
	{
		ArchivesInfoAttachedFileDeleted item = new ArchivesInfoAttachedFileDeleted(iD,archivesTypeID,nBXH,title,archivingFileName,dealedFlag,dealedDate,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param archivesInfoAttachedFileDeleted 指定的对象源
	*/
	public void cloneFrom(ArchivesInfoAttachedFileDeleted archivesInfoAttachedFileDeleted)
	{
		this.iD = archivesInfoAttachedFileDeleted.getID();
		this.archivesTypeID = archivesInfoAttachedFileDeleted.getArchivesTypeID();
		this.nBXH = archivesInfoAttachedFileDeleted.getNBXH();
		this.title = archivesInfoAttachedFileDeleted.getTitle();
		this.archivingFileName = archivesInfoAttachedFileDeleted.getArchivingFileName();
		this.dealedFlag = archivesInfoAttachedFileDeleted.getDealedFlag();
		this.dealedDate = archivesInfoAttachedFileDeleted.getDealedDate();
		this.remark = archivesInfoAttachedFileDeleted.getRemark();
		this.keyInCol = archivesInfoAttachedFileDeleted.getKeyInCol();
		this.tag = archivesInfoAttachedFileDeleted.getTag();
	}

//	/**
//	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
//	 * 
//	 */
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//	private class ArchivesInfoAttachedFileDeletedMapper implements RowMapper<ArchivesInfoAttachedFileDeleted>
//	{
//		
//		@Override
//		public ArchivesInfoAttachedFileDeleted mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			int iD = rs.getInt("ID");
//			int archivesTypeID = rs.getInt("ArchivesTypeID");
//			int nBXH = rs.getInt("NBXH");
//			String title = rs.getString("Title");
//			String archivingFileName = rs.getString("ArchivingFileName");
//			boolean dealedFlag = rs.getBoolean("DealedFlag");
//			Date dealedDate = rs.getTimestamp("DealedDate");
//			String remark = rs.getString("Remark");
//			
//			return new ArchivesInfoAttachedFileDeleted(iD,archivesTypeID,nBXH,title,archivingFileName,dealedFlag,dealedDate,remark);
//		}
//	}

    
}



