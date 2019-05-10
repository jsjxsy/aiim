package com.orifound.aiim.entity;

import java.util.Date;
    
/**
 * 离线移交的原文电子文件信息表的实体类
 */
public class ArchivesInfoAttachedFileOffLine
{
    /**
     * 构造函数
     */
    public ArchivesInfoAttachedFileOffLine()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param archivesTypeID 档案分类编号
	* @param nBXH 原文所属文件的内部序号
	* @param oriFileName 原始文件名
	* @param offLineFileSize 文件大小
	* @param offLineTransDate 离线移交日期
	* @param archivingFileName 归档文件名
	* @param dealedFlag 处理标志
	* @param dealedDate 处理日期
	* @param remark 备注
	*/
	public ArchivesInfoAttachedFileOffLine(int iD,int archivesTypeID,int nBXH,String oriFileName,long offLineFileSize,Date offLineTransDate,String archivingFileName,boolean dealedFlag,Date dealedDate,String remark)
	{
		// Table Name: ArchivesInfoAttachedFileOffLine
		// Columns List,Can Used in SELECT SQL: ID,ArchivesTypeID,NBXH,OriFileName,OffLineFileSize,OffLineTransDate,ArchivingFileName,DealedFlag,DealedDate,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:ArchivesTypeID,:NBXH,:OriFileName,:OffLineFileSize,:OffLineTransDate,:ArchivingFileName,:DealedFlag,:DealedDate,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,OriFileName=:OriFileName,OffLineFileSize=:OffLineFileSize,OffLineTransDate=:OffLineTransDate,ArchivingFileName=:ArchivingFileName,DealedFlag=:DealedFlag,DealedDate=:DealedDate,Remark=:Remark

		this(archivesTypeID, nBXH, oriFileName, offLineFileSize, archivingFileName);
		this.iD = iD;
		this.offLineTransDate = offLineTransDate;
		this.dealedFlag = dealedFlag;
		this.dealedDate = dealedDate;
		this.remark = remark;
	}
	
	/**
	* 带字段参数的构造函数
	* @param archivesTypeID 档案分类编号
	* @param nBXH 原文所属文件的内部序号
	* @param oriFileName 原始文件名
	* @param offLineFileSize 文件大小
	* @param offLineTransDate 离线移交日期
	* @param archivingFileName 归档文件名
	* @param dealedFlag 处理标志
	* @param dealedDate 处理日期
	* @param remark 备注
	*/
	public ArchivesInfoAttachedFileOffLine(int archivesTypeID,int nBXH,String oriFileName,long offLineFileSize,String archivingFileName)
	{
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.oriFileName = oriFileName;
		this.offLineFileSize = offLineFileSize;
		this.archivingFileName = archivingFileName;
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
	 * 原始文件名
	 */
	private String oriFileName=null;

	/**
	 * 获取属性值：原始文件名
	 * @return 原始文件名
	 */
	public String getOriFileName()
	{
		return oriFileName;
	}

	/**
	 * 设置属性值：原始文件名
	 * @param oriFileName 原始文件名
	 */
	public void setOriFileName(String oriFileName)
	{
		this.oriFileName = oriFileName;
	}

	/**
	 * 文件大小
	 */
	private long offLineFileSize;

	/**
	 * 获取属性值：文件大小
	 * @return 文件大小
	 */
	public long getOffLineFileSize()
	{
		return offLineFileSize;
	}

	/**
	 * 设置属性值：文件大小
	 * @param offLineFileSize 文件大小
	 */
	public void setOffLineFileSize(long offLineFileSize)
	{
		this.offLineFileSize = offLineFileSize;
	}

	/**
	 * 离线移交日期
	 */
	private Date offLineTransDate;

	/**
	 * 获取属性值：离线移交日期
	 * @return 离线移交日期
	 */
	public Date getOffLineTransDate()
	{
		return offLineTransDate;
	}

	/**
	 * 设置属性值：离线移交日期
	 * @param offLineTransDate 离线移交日期
	 */
	public void setOffLineTransDate(Date offLineTransDate)
	{
		this.offLineTransDate = offLineTransDate;
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
	 * 处理日期
	 */
	private Date dealedDate;

	/**
	 * 获取属性值：处理日期
	 * @return 处理日期
	 */
	public Date getDealedDate()
	{
		return dealedDate;
	}

	/**
	 * 设置属性值：处理日期
	 * @param dealedDate 处理日期
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
	public ArchivesInfoAttachedFileOffLine clone()
	{
		ArchivesInfoAttachedFileOffLine item = new ArchivesInfoAttachedFileOffLine(iD,archivesTypeID,nBXH,oriFileName,offLineFileSize,offLineTransDate,archivingFileName,dealedFlag,dealedDate,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param archivesInfoAttachedFileOffLine 指定的对象源
	*/
	public void cloneFrom(ArchivesInfoAttachedFileOffLine archivesInfoAttachedFileOffLine)
	{
		this.iD = archivesInfoAttachedFileOffLine.getID();
		this.archivesTypeID = archivesInfoAttachedFileOffLine.getArchivesTypeID();
		this.nBXH = archivesInfoAttachedFileOffLine.getNBXH();
		this.oriFileName = archivesInfoAttachedFileOffLine.getOriFileName();
		this.offLineFileSize = archivesInfoAttachedFileOffLine.getOffLineFileSize();
		this.offLineTransDate = archivesInfoAttachedFileOffLine.getOffLineTransDate();
		this.archivingFileName = archivesInfoAttachedFileOffLine.getArchivingFileName();
		this.dealedFlag = archivesInfoAttachedFileOffLine.getDealedFlag();
		this.dealedDate = archivesInfoAttachedFileOffLine.getDealedDate();
		this.remark = archivesInfoAttachedFileOffLine.getRemark();
		this.keyInCol = archivesInfoAttachedFileOffLine.getKeyInCol();
		this.tag = archivesInfoAttachedFileOffLine.getTag();
	}

//	/**
//	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
//	 * 
//	 */
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//	private class ArchivesInfoAttachedFileOffLineMapper implements RowMapper<ArchivesInfoAttachedFileOffLine>
//	{
//		
//		@Override
//		public ArchivesInfoAttachedFileOffLine mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			int iD = rs.getInt("ID");
//			int archivesTypeID = rs.getInt("ArchivesTypeID");
//			int nBXH = rs.getInt("NBXH");
//			String oriFileName = rs.getString("OriFileName");
//			long offLineFileSize = rs.getLong("OffLineFileSize");
//			Date offLineTransDate = rs.getTimestamp("OffLineTransDate");
//			String archivingFileName = rs.getString("ArchivingFileName");
//			boolean dealedFlag = rs.getBoolean("DealedFlag");
//			Date dealedDate = rs.getTimestamp("DealedDate");
//			String remark = rs.getString("Remark");
//			
//			return new ArchivesInfoAttachedFileOffLine(iD,archivesTypeID,nBXH,oriFileName,offLineFileSize,offLineTransDate,archivingFileName,dealedFlag,dealedDate,remark);
//		}
//	}

    
}



