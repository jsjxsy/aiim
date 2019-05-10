package com.orifound.aiim.entity;

import java.util.Date;
    
/**
 * 档案上架位置信息表的实体类
 */
public class StoreAddressInfo
{
    /**
     * 构造函数
     */
    public StoreAddressInfo()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param storeAddressID 上架位置的库房设备编号
	* @param archivesBoxBarcode 盒条码
	* @param storeAddressFullName 上架位置完整名称
	* @param putTime 上架时间
	*/
	public StoreAddressInfo(int storeAddressID,String archivesBoxBarcode,String storeAddressFullName,Date putTime)
	{
		// Table Name: StoreAddressInfo
		// Columns List,Can Used in SELECT SQL: StoreAddressID,ArchivesBoxBarcode,StoreAddressFullName,PutTime
		// Columns List,Can Used in INSERT SQL: :StoreAddressID,:ArchivesBoxBarcode,:StoreAddressFullName,:PutTime
		// Columns List,Can Used in UPDATE SQL: StoreAddressID=:StoreAddressID,ArchivesBoxBarcode=:ArchivesBoxBarcode,StoreAddressFullName=:StoreAddressFullName,PutTime=:PutTime

		this.storeAddressID = storeAddressID;
		this.archivesBoxBarcode = archivesBoxBarcode;
		this.storeAddressFullName = storeAddressFullName;
		this.putTime = putTime;
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
	 * 上架位置的库房设备编号
	 */
	private int storeAddressID=0;

	/**
	 * 获取属性值：上架位置的库房设备编号
	 * @return 上架位置的库房设备编号
	 */
	public int getStoreAddressID()
	{
		return storeAddressID;
	}

	/**
	 * 设置属性值：上架位置的库房设备编号
	 * @param storeAddressID 上架位置的库房设备编号
	 */
	public void setStoreAddressID(int storeAddressID)
	{
		this.storeAddressID = storeAddressID;
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
	 * 上架位置完整名称
	 */
	private String storeAddressFullName=null;

	/**
	 * 获取属性值：上架位置完整名称
	 * @return 上架位置完整名称
	 */
	public String getStoreAddressFullName()
	{
		return storeAddressFullName;
	}

	/**
	 * 设置属性值：上架位置完整名称
	 * @param storeAddressFullName 上架位置完整名称
	 */
	public void setStoreAddressFullName(String storeAddressFullName)
	{
		this.storeAddressFullName = storeAddressFullName;
	}

	/**
	 * 上架时间
	 */
	private Date putTime;

	/**
	 * 获取属性值：上架时间
	 * @return 上架时间
	 */
	public Date getPutTime()
	{
		return putTime;
	}

	/**
	 * 设置属性值：上架时间
	 * @param putTime 上架时间
	 */
	public void setPutTime(Date putTime)
	{
		this.putTime = putTime;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public StoreAddressInfo clone()
	{
		StoreAddressInfo item = new StoreAddressInfo(storeAddressID,archivesBoxBarcode,storeAddressFullName,putTime);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param storeAddressInfo 指定的对象源
	*/
	public void cloneFrom(StoreAddressInfo storeAddressInfo)
	{
		this.storeAddressID = storeAddressInfo.getStoreAddressID();
		this.archivesBoxBarcode = storeAddressInfo.getArchivesBoxBarcode();
		this.storeAddressFullName = storeAddressInfo.getStoreAddressFullName();
		this.putTime = storeAddressInfo.getPutTime();
		this.keyInCol = storeAddressInfo.getKeyInCol();
		this.tag = storeAddressInfo.getTag();
	}



    
}



