package com.orifound.aiim.entity;

/**
 * 库房档案信息表的实体类
 */
public class StoreroomArchivesInfo
{
    /**
     * 构造函数
     */
    public StoreroomArchivesInfo()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param archivesTypeID 档案分类编号
	* @param nBXH 档案内部序号
	* @param archivesID 档号
	* @param title 题名
	* @param archivesBarcode 档案条码
	* @param archivesBoxBarcode 盒条码
	* @param storeStatus 馆藏状态
	*/
	public StoreroomArchivesInfo(int archivesTypeID,int nBXH,String archivesID,String title,String archivesBarcode,String archivesBoxBarcode,EnumStoreStatus storeStatus)
	{
		// Table Name: StoreroomArchivesInfo
		// Columns List,Can Used in SELECT SQL: ArchivesTypeID,NBXH,ArchivesID,Title,ArchivesBarcode,ArchivesBoxBarcode,StoreStatus
		// Columns List,Can Used in INSERT SQL: :ArchivesTypeID,:NBXH,:ArchivesID,:Title,:ArchivesBarcode,:ArchivesBoxBarcode,:StoreStatus
		// Columns List,Can Used in UPDATE SQL: ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,ArchivesBarcode=:ArchivesBarcode,ArchivesBoxBarcode=:ArchivesBoxBarcode,StoreStatus=:StoreStatus

		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.archivesID = archivesID;
		this.title = title;
		this.archivesBarcode = archivesBarcode;
		this.archivesBoxBarcode = archivesBoxBarcode;
		this.storeStatus = storeStatus;
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
	 * 盒条码
	 */
	private String archivesBoxBarcode=null;

	/**
	 * 获取属性值：盒条码
	 * @return 盒条码
	 */
	public String getArchivesBoxBarcode()
	{
		return archivesBoxBarcode;
	}

	/**
	 * 设置属性值：盒条码
	 * @param archivesBoxBarcode 盒条码
	 */
	public void setArchivesBoxBarcode(String archivesBoxBarcode)
	{
		this.archivesBoxBarcode = archivesBoxBarcode;
	}
	
	/**
	 * 馆藏状态
	 */
	private EnumStoreStatus storeStatus = EnumStoreStatus.NONE;
	
	/**
	 * 获取属性值：馆藏状态
	 * @return 馆藏状态
	 */
	public EnumStoreStatus getStoreStatus() {
		return storeStatus;
	}
	
	/**
	 * 获取属性值：馆藏状态
	 * @return 馆藏状态
	 */
	public void setStoreStatus(EnumStoreStatus storeStatus) {
		this.storeStatus = storeStatus;
	}
	

//	/**
//	 * 馆藏状态
//	 */
//	private int storeStatus=0;
//
//	/**
//	 * 获取属性值：馆藏状态
//	 * @return 馆藏状态
//	 */
//	public int getStoreStatus()
//	{
//		return storeStatus;
//	}
//
//	/**
//	 * 设置属性值：馆藏状态
//	 * @param storeStatus 馆藏状态
//	 */
//	public void setStoreStatus(int storeStatus)
//	{
//		this.storeStatus = storeStatus;
//	}

	

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public StoreroomArchivesInfo clone()
	{
		StoreroomArchivesInfo item = new StoreroomArchivesInfo(archivesTypeID,nBXH,archivesID,title,archivesBarcode,archivesBoxBarcode,storeStatus);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param storeroomArchivesInfo 指定的对象源
	*/
	public void cloneFrom(StoreroomArchivesInfo storeroomArchivesInfo)
	{
		this.archivesTypeID = storeroomArchivesInfo.getArchivesTypeID();
		this.nBXH = storeroomArchivesInfo.getNBXH();
		this.archivesID = storeroomArchivesInfo.getArchivesID();
		this.title = storeroomArchivesInfo.getTitle();
		this.archivesBarcode = storeroomArchivesInfo.getArchivesBarcode();
		this.archivesBoxBarcode = storeroomArchivesInfo.getArchivesBoxBarcode();
		this.storeStatus = storeroomArchivesInfo.getStoreStatus();
		this.keyInCol = storeroomArchivesInfo.getKeyInCol();
		this.tag = storeroomArchivesInfo.getTag();
	}


    
}



