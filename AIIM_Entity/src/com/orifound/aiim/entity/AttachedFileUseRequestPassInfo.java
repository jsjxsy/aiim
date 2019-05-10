package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 原文利用申请通过信息表的实体类
 */
public class AttachedFileUseRequestPassInfo
{
    /**
     * 构造函数
     */
    public AttachedFileUseRequestPassInfo()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 申请单明细编号
	* @param archivesTypeID 档案分类编号
	* @param nBXH 档案内部序号
	* @param archivesID 档号
	* @param title 题名
	* @param userID 利用人编号
	* @param expirationDate 有效截止日期
	*/
	public AttachedFileUseRequestPassInfo(int iD,int archivesTypeID,int nBXH,String archivesID,String title,int userID,Date expirationDate)
	{
		// Table Name: AttachedFileUseRequestPassInfo
		// Columns List,Can Used in SELECT SQL: ID,ArchivesTypeID,NBXH,ArchivesID,Title,UserID,ExpirationDate
		// Columns List,Can Used in INSERT SQL: :ID,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:UserID,:ExpirationDate
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,UserID=:UserID,ExpirationDate=:ExpirationDate

		this.iD = iD;
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.archivesID = archivesID;
		this.title = title;
		this.userID = userID;
		this.expirationDate = expirationDate;
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
	 * 申请单明细编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：申请单明细编号
	 * @return 申请单明细编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：申请单明细编号
	 * @param iD 申请单明细编号
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
	 * 档案内部序号
	 */
	private int nBXH=0;

	/**
	 * 获取属性值：档案内部序号
	 * @return 档案内部序号
	 */
	public int getNBXH()
	{
		return nBXH;
	}

	/**
	 * 设置属性值：档案内部序号
	 * @param nBXH 档案内部序号
	 */
	public void setNBXH(int nBXH)
	{
		this.nBXH = nBXH;
	}

	/**
	 * 档号
	 */
	private String archivesID=null;

	/**
	 * 获取属性值：档号
	 * @return 档号
	 */
	public String getArchivesID()
	{
		return archivesID;
	}

	/**
	 * 设置属性值：档号
	 * @param archivesID 档号
	 */
	public void setArchivesID(String archivesID)
	{
		this.archivesID = archivesID;
	}

	/**
	 * 题名
	 */
	private String title=null;

	/**
	 * 获取属性值：题名
	 * @return 题名
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * 设置属性值：题名
	 * @param title 题名
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * 利用人编号
	 */
	private int userID=0;

	/**
	 * 获取属性值：利用人编号
	 * @return 利用人编号
	 */
	public int getUserID()
	{
		return userID;
	}

	/**
	 * 设置属性值：利用人编号
	 * @param userID 利用人编号
	 */
	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	/**
	 * 有效截止日期
	 */
	private Date expirationDate;

	/**
	 * 获取属性值：有效截止日期
	 * @return 有效截止日期
	 */
	public Date getExpirationDate()
	{
		return expirationDate;
	}

	/**
	 * 设置属性值：有效截止日期
	 * @param expirationDate 有效截止日期
	 */
	public void setExpirationDate(Date expirationDate)
	{
		this.expirationDate = expirationDate;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public AttachedFileUseRequestPassInfo clone()
	{
		AttachedFileUseRequestPassInfo item = new AttachedFileUseRequestPassInfo(iD,archivesTypeID,nBXH,archivesID,title,userID,expirationDate);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param attachedFileUseRequestPassInfo 指定的对象源
	*/
	public void cloneFrom(AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo)
	{
		this.iD = attachedFileUseRequestPassInfo.getID();
		this.archivesTypeID = attachedFileUseRequestPassInfo.getArchivesTypeID();
		this.nBXH = attachedFileUseRequestPassInfo.getNBXH();
		this.archivesID = attachedFileUseRequestPassInfo.getArchivesID();
		this.title = attachedFileUseRequestPassInfo.getTitle();
		this.userID = attachedFileUseRequestPassInfo.getUserID();
		this.expirationDate = attachedFileUseRequestPassInfo.getExpirationDate();
		this.keyInCol = attachedFileUseRequestPassInfo.getKeyInCol();
		this.tag = attachedFileUseRequestPassInfo.getTag();
	}


    
}



