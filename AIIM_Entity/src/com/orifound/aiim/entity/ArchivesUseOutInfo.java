package com.orifound.aiim.entity;

import java.util.*;
//注：此类在自动生成  代码的基础上加了一个archivesUseRegister
/**
 * 实物档案利用出去明细情况
 */
public class ArchivesUseOutInfo
{
    /**
     * 构造函数
     */
    public ArchivesUseOutInfo()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 档案利用编号
	* @param useRegID 借档登记编号
	* @param borrowFlag 借阅标志
	* @param archivesTypeID 档案分类编号
	* @param nBXH 档案内部序号
	* @param archivesID 档号
	* @param archivesBarcode 档案条码
	* @param title 题名
	* @param pageSum 档案页数
	* @param shouldReturnDate 最迟归还日期
	* @param archivesUseRegister 附加：利用登记信息
	*/
	public ArchivesUseOutInfo(int iD,int useRegID,boolean borrowFlag,int archivesTypeID,int nBXH,String archivesID,String archivesBarcode,String title,int pageSum,Date shouldReturnDate)
	{
		// Table Name: ArchivesUseOutInfo
		// Columns List,Can Used in SELECT SQL: ID,UseRegID,BorrowFlag,ArchivesTypeID,NBXH,ArchivesID,ArchivesBarcode,Title,PageSum,ShouldReturnDate
		// Columns List,Can Used in INSERT SQL: :ID,:UseRegID,:BorrowFlag,:ArchivesTypeID,:NBXH,:ArchivesID,:ArchivesBarcode,:Title,:PageSum,:ShouldReturnDate
		// Columns List,Can Used in UPDATE SQL: ID=:ID,UseRegID=:UseRegID,BorrowFlag=:BorrowFlag,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,ArchivesBarcode=:ArchivesBarcode,Title=:Title,PageSum=:PageSum,ShouldReturnDate=:ShouldReturnDate

		this.iD = iD;
		this.useRegID = useRegID;
		this.borrowFlag = borrowFlag;
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.archivesID = archivesID;
		this.archivesBarcode = archivesBarcode;
		this.title = title;
		this.pageSum = pageSum;
		this.shouldReturnDate = shouldReturnDate;
	}

	
	/**
	* 带字段参数的构造函数
	* @param iD 档案利用编号
	* @param useRegID 借档登记编号
	* @param borrowFlag 借阅标志
	* @param archivesTypeID 档案分类编号
	* @param nBXH 档案内部序号
	* @param archivesID 档号
	* @param archivesBarcode 档案条码
	* @param title 题名
	* @param pageSum 档案页数
	* @param shouldReturnDate 最迟归还日期
	* @param returnDate 归还日期
	*/
	public ArchivesUseOutInfo(int iD,int useRegID,boolean borrowFlag,int archivesTypeID,int nBXH,String archivesID,String archivesBarcode,String title,int pageSum,Date shouldReturnDate,Date returnDate)
	{
		// Table Name: ArchivesUseReturnInfo
		// Columns List,Can Used in SELECT SQL: ID,UseRegID,BorrowFlag,ArchivesTypeID,NBXH,ArchivesID,ArchivesBarcode,Title,PageSum,ShouldReturnDate,ReturnDate
		// Columns List,Can Used in INSERT SQL: :ID,:UseRegID,:BorrowFlag,:ArchivesTypeID,:NBXH,:ArchivesID,:ArchivesBarcode,:Title,:PageSum,:ShouldReturnDate,:ReturnDate
		// Columns List,Can Used in UPDATE SQL: ID=:ID,UseRegID=:UseRegID,BorrowFlag=:BorrowFlag,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,ArchivesBarcode=:ArchivesBarcode,Title=:Title,PageSum=:PageSum,ShouldReturnDate=:ShouldReturnDate,ReturnDate=:ReturnDate

		this.iD = iD;
		this.useRegID = useRegID;
		this.borrowFlag = borrowFlag;
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.archivesID = archivesID;
		this.archivesBarcode = archivesBarcode;
		this.title = title;
		this.pageSum = pageSum;
		this.shouldReturnDate = shouldReturnDate;
		this.returnDate = returnDate;
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
	 * 档案利用编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：档案利用编号
	 * @return 档案利用编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：档案利用编号
	 * @param iD 档案利用编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 借档登记编号
	 */
	private int useRegID=0;

	/**
	 * 获取属性值：借档登记编号
	 * @return 借档登记编号
	 */
	public int getUseRegID()
	{
		return useRegID;
	}

	/**
	 * 设置属性值：借档登记编号
	 * @param useRegID 借档登记编号
	 */
	public void setUseRegID(int useRegID)
	{
		this.useRegID = useRegID;
	}

	/**
	 * 借阅标志
	 */
	private boolean borrowFlag=false;

	/**
	 * 获取属性值：借阅标志
	 * @return 借阅标志
	 */
	public boolean getBorrowFlag()
	{
		return borrowFlag;
	}

	/**
	 * 设置属性值：借阅标志
	 * @param borrowFlag 借阅标志
	 */
	public void setBorrowFlag(boolean borrowFlag)
	{
		this.borrowFlag = borrowFlag;
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
	 * 档案条码
	 */
	private String archivesBarcode=null;

	/**
	 * 获取属性值：档案条码
	 * @return 档案条码
	 */
	public String getArchivesBarcode()
	{
		return archivesBarcode;
	}

	/**
	 * 设置属性值：档案条码
	 * @param archivesBarcode 档案条码
	 */
	public void setArchivesBarcode(String archivesBarcode)
	{
		this.archivesBarcode = archivesBarcode;
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
	 * 档案页数
	 */
	private int pageSum=0;

	/**
	 * 获取属性值：档案页数
	 * @return 档案页数
	 */
	public int getPageSum()
	{
		return pageSum;
	}

	/**
	 * 设置属性值：档案页数
	 * @param pageSum 档案页数
	 */
	public void setPageSum(int pageSum)
	{
		this.pageSum = pageSum;
	}

	/**
	 * 最迟归还日期
	 */
	private Date shouldReturnDate;

	/**
	 * 获取属性值：最迟归还日期
	 * @return 最迟归还日期
	 */
	public Date getShouldReturnDate()
	{
		return shouldReturnDate;
	}

	/**
	 * 设置属性值：最迟归还日期
	 * @param shouldReturnDate 最迟归还日期
	 */
	public void setShouldReturnDate(Date shouldReturnDate)
	{
		this.shouldReturnDate = shouldReturnDate;
	}
	
	
	/**
	 * 归还日期
	 */
	private Date returnDate;

	/**
	 * 获取属性值：归还日期
	 * @return 归还日期
	 */
	public Date getReturnDate()
	{
		return returnDate;
	}

	/**
	 * 设置属性值：归还日期
	 * @param returnDate 归还日期
	 */
	public void setReturnDate(Date returnDate)
	{
		this.returnDate = returnDate;
	}

	
	
	/**
	 * 实物档案利用登记
	 */
	private ArchivesUseRegister archivesUseRegister;
		
	public ArchivesUseRegister getArchivesUseRegister() {
		return archivesUseRegister;
	}

	public void setArchivesUseRegister(ArchivesUseRegister archivesUseRegister) {
		this.archivesUseRegister = archivesUseRegister;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ArchivesUseOutInfo clone()
	{
		ArchivesUseOutInfo item = new ArchivesUseOutInfo(iD,useRegID,borrowFlag,archivesTypeID,nBXH,archivesID,archivesBarcode,title,pageSum,shouldReturnDate,returnDate);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);
		item.setArchivesUseRegister(archivesUseRegister);

		return item;
	}
	


	/**
	* 从指定对象克隆，复制所有属性值
	* @param archivesUseOutInfo 指定的对象源
	*/
	public void cloneFrom(ArchivesUseOutInfo archivesUseOutInfo)
	{
		this.iD = archivesUseOutInfo.getID();
		this.useRegID = archivesUseOutInfo.getUseRegID();
		this.borrowFlag = archivesUseOutInfo.getBorrowFlag();
		this.archivesTypeID = archivesUseOutInfo.getArchivesTypeID();
		this.nBXH = archivesUseOutInfo.getNBXH();
		this.archivesID = archivesUseOutInfo.getArchivesID();
		this.archivesBarcode = archivesUseOutInfo.getArchivesBarcode();
		this.title = archivesUseOutInfo.getTitle();
		this.pageSum = archivesUseOutInfo.getPageSum();
		this.shouldReturnDate = archivesUseOutInfo.getShouldReturnDate();
		this.keyInCol = archivesUseOutInfo.getKeyInCol();
		this.tag = archivesUseOutInfo.getTag();
		this.returnDate = archivesUseOutInfo.returnDate;
		this.archivesUseRegister = archivesUseOutInfo.getArchivesUseRegister();
	}


    
}



