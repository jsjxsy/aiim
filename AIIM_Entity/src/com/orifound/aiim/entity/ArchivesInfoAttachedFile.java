package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 档案信息原文表的实体类
 */
public class ArchivesInfoAttachedFile
{
    /**
     * 构造函数
     */
    public ArchivesInfoAttachedFile()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 原文编号
	* @param archivesTypeID 档案分类编号
	* @param nBXH 原文所属文件的内部序号
	* @param orderID 次序
	* @param title 原文标题
	* @param oriFileName 原始文件名
	* @param originalSize 原始文件大小
	* @param originalType 原始文件类型
	* @param attachedTime 附加原文的时间
	* @param fetchFullTextRequest 要求提取全文的标志
	* @param archivingFileName 归档文件名
	* @param resaveFileName 另存文件名
	* @param resaveSize 另存文件大小
	* @param resaveType 另存文件类型
	* @param resaveTime 另存时间
	* @param deleteFlag 删除标志
	* @param remark 备注
	*/
	public ArchivesInfoAttachedFile(int iD,int archivesTypeID,int nBXH,int orderID,String title,String oriFileName,double originalSize,String originalType,Date attachedTime,int fetchFullTextRequest,String archivingFileName,String resaveFileName,double resaveSize,String resaveType,Date resaveTime,boolean deleteFlag,String remark)
	{
		// Table Name: ArchivesInfoAttachedFile_TypeCode
		// Columns List,Can Used in SELECT SQL: ID,ArchivesTypeID,NBXH,OrderID,Title,OriFileName,OriginalSize,OriginalType,AttachedTime,FetchFullTextRequest,ArchivingFileName,ResaveFileName,ResaveSize,ResaveType,ResaveTime,DeleteFlag,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:ArchivesTypeID,:NBXH,:OrderID,:Title,:OriFileName,:OriginalSize,:OriginalType,:AttachedTime,:FetchFullTextRequest,:ArchivingFileName,:ResaveFileName,:ResaveSize,:ResaveType,:ResaveTime,:DeleteFlag,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,OrderID=:OrderID,Title=:Title,OriFileName=:OriFileName,OriginalSize=:OriginalSize,OriginalType=:OriginalType,AttachedTime=:AttachedTime,FetchFullTextRequest=:FetchFullTextRequest,ArchivingFileName=:ArchivingFileName,ResaveFileName=:ResaveFileName,ResaveSize=:ResaveSize,ResaveType=:ResaveType,ResaveTime=:ResaveTime,DeleteFlag=:DeleteFlag,Remark=:Remark

		this.iD = iD;
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.orderID = orderID;
		this.title = title;
		this.oriFileName = oriFileName;
		this.originalSize = originalSize;
		this.originalType = originalType;
		this.attachedTime = attachedTime;
		this.fetchFullTextRequest = fetchFullTextRequest;
		this.archivingFileName = archivingFileName;
		this.resaveFileName = resaveFileName;
		this.resaveSize = resaveSize;
		this.resaveType = resaveType;
		this.resaveTime = resaveTime;
		this.deleteFlag = deleteFlag;
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
	 * 次序
	 */
	private int orderID=0;

	/**
	 * 获取属性值：次序
	 * @return 次序
	 */
	public int getOrderID()
	{
		return orderID;
	}

	/**
	 * 设置属性值：次序
	 * @param orderID 次序
	 */
	public void setOrderID(int orderID)
	{
		this.orderID = orderID;
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
	 * 原始文件大小
	 */
	private double originalSize;

	/**
	 * 获取属性值：原始文件大小
	 * @return 原始文件大小
	 */
	public double getOriginalSize()
	{
		return originalSize;
	}

	/**
	 * 设置属性值：原始文件大小
	 * @param originalSize 原始文件大小
	 */
	public void setOriginalSize(double originalSize)
	{
		this.originalSize = originalSize;
	}

	/**
	 * 原始文件类型
	 */
	private String originalType=null;

	/**
	 * 获取属性值：原始文件类型
	 * @return 原始文件类型
	 */
	public String getOriginalType()
	{
		return originalType;
	}

	/**
	 * 设置属性值：原始文件类型
	 * @param originalType 原始文件类型
	 */
	public void setOriginalType(String originalType)
	{
		this.originalType = originalType;
	}

	/**
	 * 附加原文的时间
	 */
	private Date attachedTime;

	/**
	 * 获取属性值：附加原文的时间
	 * @return 附加原文的时间
	 */
	public Date getAttachedTime()
	{
		return new java.sql.Date(attachedTime.getTime());
	}

	/**
	 * 设置属性值：附加原文的时间
	 * @param attachedTime 附加原文的时间
	 */
	public void setAttachedTime(Date attachedTime)
	{
		this.attachedTime = attachedTime;
	}

	/**
	 * 要求提取全文的标志
	 */
	private int fetchFullTextRequest=0;

	/**
	 * 获取属性值：要求提取全文的标志
	 * @return 要求提取全文的标志
	 */
	public int getFetchFullTextRequest()
	{
		return fetchFullTextRequest;
	}

	/**
	 * 设置属性值：要求提取全文的标志
	 * @param fetchFullTextRequest 要求提取全文的标志
	 */
	public void setFetchFullTextRequest(int fetchFullTextRequest)
	{
		this.fetchFullTextRequest = fetchFullTextRequest;
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
	 * 另存文件名
	 */
	private String resaveFileName=null;

	/**
	 * 获取属性值：另存文件名
	 * @return 另存文件名
	 */
	public String getResaveFileName()
	{
		return resaveFileName;
	}

	/**
	 * 设置属性值：另存文件名
	 * @param resaveFileName 另存文件名
	 */
	public void setResaveFileName(String resaveFileName)
	{
		this.resaveFileName = resaveFileName;
	}

	/**
	 * 另存文件大小
	 */
	private double resaveSize;

	/**
	 * 获取属性值：另存文件大小
	 * @return 另存文件大小
	 */
	public double getResaveSize()
	{
		return resaveSize;
	}

	/**
	 * 设置属性值：另存文件大小
	 * @param resaveSize 另存文件大小
	 */
	public void setResaveSize(double resaveSize)
	{
		this.resaveSize = resaveSize;
	}

	/**
	 * 另存文件类型
	 */
	private String resaveType=null;

	/**
	 * 获取属性值：另存文件类型
	 * @return 另存文件类型
	 */
	public String getResaveType()
	{
		return resaveType;
	}

	/**
	 * 设置属性值：另存文件类型
	 * @param resaveType 另存文件类型
	 */
	public void setResaveType(String resaveType)
	{
		this.resaveType = resaveType;
	}

	/**
	 * 另存时间
	 */
	private Date resaveTime;

	/**
	 * 获取属性值：另存时间
	 * @return 另存时间
	 */
	public Date getResaveTime()
	{
		
		return resaveTime;
	}

	/**
	 * 设置属性值：另存时间
	 * @param resaveTime 另存时间
	 */
	public void setResaveTime(Date resaveTime)
	{
		this.resaveTime = resaveTime;
	}

	/**
	 * 删除标志
	 */
	private boolean deleteFlag=false;

	/**
	 * 获取属性值：删除标志
	 * @return 删除标志
	 */
	public boolean getDeleteFlag()
	{
		return deleteFlag;
	}

	/**
	 * 设置属性值：删除标志
	 * @param deleteFlag 删除标志
	 */
	public void setDeleteFlag(boolean deleteFlag)
	{
		this.deleteFlag = deleteFlag;
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
	public ArchivesInfoAttachedFile clone()
	{
		ArchivesInfoAttachedFile item = new ArchivesInfoAttachedFile(iD,archivesTypeID,nBXH,orderID,title,oriFileName,originalSize,originalType,attachedTime,fetchFullTextRequest,archivingFileName,resaveFileName,resaveSize,resaveType,resaveTime,deleteFlag,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param archivesInfoAttachedFile 指定的对象源
	*/
	public void cloneFrom(ArchivesInfoAttachedFile archivesInfoAttachedFile)
	{
		this.iD = archivesInfoAttachedFile.getID();
		this.archivesTypeID = archivesInfoAttachedFile.getArchivesTypeID();
		this.nBXH = archivesInfoAttachedFile.getNBXH();
		this.orderID = archivesInfoAttachedFile.getOrderID();
		this.title = archivesInfoAttachedFile.getTitle();
		this.oriFileName = archivesInfoAttachedFile.getOriFileName();
		this.originalSize = archivesInfoAttachedFile.getOriginalSize();
		this.originalType = archivesInfoAttachedFile.getOriginalType();
		this.attachedTime = archivesInfoAttachedFile.getAttachedTime();
		this.fetchFullTextRequest = archivesInfoAttachedFile.getFetchFullTextRequest();
		this.archivingFileName = archivesInfoAttachedFile.getArchivingFileName();
		this.resaveFileName = archivesInfoAttachedFile.getResaveFileName();
		this.resaveSize = archivesInfoAttachedFile.getResaveSize();
		this.resaveType = archivesInfoAttachedFile.getResaveType();
		this.resaveTime = archivesInfoAttachedFile.getResaveTime();
		this.deleteFlag = archivesInfoAttachedFile.getDeleteFlag();
		this.remark = archivesInfoAttachedFile.getRemark();
		this.keyInCol = archivesInfoAttachedFile.getKeyInCol();
		this.tag = archivesInfoAttachedFile.getTag();
	}

//	/**
//	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
//	 * 
//	 */
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//	private class ArchivesInfoAttachedFileMapper implements RowMapper<ArchivesInfoAttachedFile>
//	{
//		
//		@Override
//		public ArchivesInfoAttachedFile mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			int iD = rs.getInt("ID");
//			int archivesTypeID = rs.getInt("ArchivesTypeID");
//			int nBXH = rs.getInt("NBXH");
//			int orderID = rs.getInt("OrderID");
//			String title = rs.getString("Title");
//			String oriFileName = rs.getString("OriFileName");
//			long originalSize = rs.getLong("OriginalSize");
//			String originalType = rs.getString("OriginalType");
//			Date attachedTime = rs.getTimestamp("AttachedTime");
//			int fetchFullTextRequest = rs.getBoolean("FetchFullTextRequest");
//			String archivingFileName = rs.getString("ArchivingFileName");
//			String resaveFileName = rs.getString("ResaveFileName");
//			long resaveSize = rs.getLong("ResaveSize");
//			String resaveType = rs.getString("ResaveType");
//			Date resaveTime = rs.getTimestamp("ResaveTime");
//			boolean deleteFlag = rs.getBoolean("DeleteFlag");
//			String remark = rs.getString("Remark");
//			
//			return new ArchivesInfoAttachedFile(iD,archivesTypeID,nBXH,orderID,title,oriFileName,originalSize,originalType,attachedTime,fetchFullTextRequest,archivingFileName,resaveFileName,resaveSize,resaveType,resaveTime,deleteFlag,remark);
//		}
//	}

    
}



