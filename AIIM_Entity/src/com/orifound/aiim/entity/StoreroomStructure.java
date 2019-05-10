package com.orifound.aiim.entity;

import java.util.*;

 
/**
 * 库房结构信息表的实体类
 */

public class StoreroomStructure
{
    /**
     * 构造函数
     */
    public StoreroomStructure()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 设备编号
	* @param name 设备名称
	* @param parentID 上级设备编号
	* @param barcode 库房位置条码
	* @param fullName 设备完整名称
	* @param totalSize 总大小
	* @param usedSize 已用空间
	* @param roomFlag 库房标志
	* @param roomID 所属库房编号
	* @param endFlag 最末层标志
	* @param remark 备注
	*/
	public StoreroomStructure(int iD,String name,int parentID,String barcode,String fullName,int totalSize,int usedSize,boolean roomFlag,int roomID,boolean endFlag,String remark)
	{
		// Table Name: DD_StoreroomStructure
		// Columns List,Can Used in SELECT SQL: ID,Name,ParentID,Barcode,FullName,TotalSize,UsedSize,RoomFlag,RoomID,EndFlag,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:ParentID,:Barcode,:FullName,:TotalSize,:UsedSize,:RoomFlag,:RoomID,:EndFlag,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,ParentID=:ParentID,Barcode=:Barcode,FullName=:FullName,TotalSize=:TotalSize,UsedSize=:UsedSize,RoomFlag=:RoomFlag,RoomID=:RoomID,EndFlag=:EndFlag,Remark=:Remark

		this.iD = iD;
		this.name = name;
		this.parentID = parentID;
		this.barcode = barcode;
		this.fullName = fullName;
		this.totalSize = totalSize;
		this.usedSize = usedSize;
		this.roomFlag = roomFlag;
		this.roomID = roomID;
		this.endFlag = endFlag;
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
	 * 设备编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：设备编号
	 * @return 设备编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：设备编号
	 * @param iD 设备编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 设备名称
	 */
	private String name=null;

	/**
	 * 获取属性值：设备名称
	 * @return 设备名称
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 设置属性值：设备名称
	 * @param name 设备名称
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * 上级设备编号
	 */
	private int parentID=0;

	/**
	 * 获取属性值：上级设备编号
	 * @return 上级设备编号
	 */
	public int getParentID()
	{
		return parentID;
	}

	/**
	 * 设置属性值：上级设备编号
	 * @param parentID 上级设备编号
	 */
	public void setParentID(int parentID)
	{
		this.parentID = parentID;
	}

	/**
	 * 档案位置条码
	 */
	private String barcode=null;

	/**
	 * 获取属性值：档案位置条码
	 * @return 档案位置条码
	 */
	public String getBarcode()
	{
		return barcode;
	}

	/**
	 * 设置属性值：档案位置条码
	 * @param barcode 档案位置条码
	 */
	public void setBarcode(String barcode)
	{
		this.barcode = barcode;
	}

	/**
	 * 设备完整名称
	 */
	private String fullName=null;

	/**
	 * 获取属性值：设备完整名称
	 * @return 设备完整名称
	 */
	public String getFullName()
	{
		return fullName;
	}

	/**
	 * 设置属性值：设备完整名称
	 * @param fullName 设备完整名称
	 */
	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	/**
	 * 总大小
	 */
	private int totalSize=0;

	/**
	 * 获取属性值：总大小
	 * @return 总大小
	 */
	public int getTotalSize()
	{
		return totalSize;
	}

	/**
	 * 设置属性值：总大小
	 * @param totalSize 总大小
	 */
	public void setTotalSize(int totalSize)
	{
		this.totalSize = totalSize;
	}

	/**
	 * 已用空间
	 */
	private int usedSize=0;

	/**
	 * 获取属性值：已用空间
	 * @return 已用空间
	 */
	public int getUsedSize()
	{
		return usedSize;
	}

	/**
	 * 设置属性值：已用空间
	 * @param usedSize 已用空间
	 */
	public void setUsedSize(int usedSize)
	{
		this.usedSize = usedSize;
	}

	/**
	 * 库房标志
	 */
	private boolean roomFlag=false;

	/**
	 * 获取属性值：库房标志
	 * @return 库房标志
	 */
	public boolean getRoomFlag()
	{
		return roomFlag;
	}

	/**
	 * 设置属性值：库房标志
	 * @param roomFlag 库房标志
	 */
	public void setRoomFlag(boolean roomFlag)
	{
		this.roomFlag = roomFlag;
	}
	
	/**
	 * 设备所属的库房编号
	 */
	private int roomID = 0;

	/**
	 * 设置属性值:设备所属的库房编号
	 * @return
	 */
	public int getRoomID() {
		return roomID;
	}

	/**
	 * 设置属性值：设备所属的库房编号
	 * @param roomID
	 */
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	/**
	 * 最末层标志
	 */
	private boolean endFlag=false;

	/**
	 * 获取属性值：最末层标志
	 * @return 最末层标志
	 */
	public boolean getEndFlag()
	{
		return endFlag;
	}

	/**
	 * 设置属性值：最末层标志
	 * @param endFlag 最末层标志
	 */
	public void setEndFlag(boolean endFlag)
	{
		this.endFlag = endFlag;
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
	public StoreroomStructure clone()
	{
		StoreroomStructure item = new StoreroomStructure(iD,name,parentID,barcode,fullName,totalSize,usedSize,roomFlag,roomID,endFlag,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param storeroomStructure 指定的对象源
	*/
	public void cloneFrom(StoreroomStructure storeroomStructure)
	{
		this.iD = storeroomStructure.getID();
		this.name = storeroomStructure.getName();
		this.parentID = storeroomStructure.getParentID();
		this.barcode = storeroomStructure.getBarcode();
		this.fullName = storeroomStructure.getFullName();
		this.totalSize = storeroomStructure.getTotalSize();
		this.usedSize = storeroomStructure.getUsedSize();
		this.roomFlag = storeroomStructure.getRoomFlag();
		this.roomID = storeroomStructure.getRoomID();
		this.endFlag = storeroomStructure.getEndFlag();
		this.remark = storeroomStructure.getRemark();
		this.keyInCol = storeroomStructure.getKeyInCol();
		this.tag = storeroomStructure.getTag();
		this.children = storeroomStructure.getChildren();
	}

	
	
	/**
	 * 下级设备结构
	 */
	private List<StoreroomStructure> children = null;

	/**
	 * 设置属性值：下级设备结构
	 * @param children 下级设备结构
	 */
	public void setChildren(List<StoreroomStructure> children) {
		this.children = children;
	}

	/**
	 * 获取属性值：下级设备结构
	 * @return 下级设备结构
	 */
	public List<StoreroomStructure> getChildren() {
		return children;
	}
	
    
}








//public class StoreroomStructure
//{
//    /**
//     * 构造函数
//     */
//    public StoreroomStructure()
//    {
//        
//    }
//    
//    /**
//	* 带字段参数的构造函数
//	* @param name 设备名称
//	* @param parentID 上级设备编号
//	* @param fullName 设备完整名称
//	* @param roomFlag 库房标志
//	* @param endFlag 最末层标志
//	* @param remark 备注
//	*/
//	public StoreroomStructure(String name,int parentID,String fullName,boolean roomFlag,boolean endFlag,String remark)
//	{
//		this.name = name;
//		this.parentID = parentID;
//		this.fullName = fullName;
//		this.roomFlag = roomFlag;
//		this.endFlag = endFlag;
//		this.remark = remark;
//	}
//    
//    /**
//	* 带字段参数的构造函数
//	* @param name 设备名称
//	* @param parentID 上级设备编号
//	* @param fullName 设备完整名称
//	* @param totalSize 总大小
//	* @param usedSize 已用空间
//	* @param roomFlag 库房标志
//	* @param endFlag 最末层标志
//	* @param remark 备注
//	*/
//	public StoreroomStructure(String name,int parentID,String fullName,int totalSize,int usedSize,boolean roomFlag,boolean endFlag,String remark)
//	{
//
//		this(name, parentID, fullName, roomFlag, endFlag, remark);
//		this.totalSize = totalSize;
//		this.usedSize = usedSize;
//	}
//    
//    /**
//	* 带字段参数的构造函数
//	* @param name 设备名称
//	* @param parentID 上级设备编号
//	* @param barcode 档案位置条码
//	* @param fullName 设备完整名称
//	* @param totalSize 总大小
//	* @param usedSize 已用空间
//	* @param roomFlag 库房标志
//	* @param endFlag 最末层标志
//	* @param remark 备注
//	*/
//	public StoreroomStructure(String name,int parentID,String barcode,String fullName,int totalSize,int usedSize,boolean roomFlag,boolean endFlag,String remark)
//	{
//		this(name, parentID, fullName, totalSize, usedSize, roomFlag, endFlag, remark);
//		this.barcode = barcode;
//	}
//    
//	/**
//	* 带字段参数的构造函数
//	* @param iD 设备编号
//	* @param name 设备名称
//	* @param parentID 上级设备编号
//	* @param barcode 档案位置条码
//	* @param fullName 设备完整名称
//	* @param totalSize 总大小
//	* @param usedSize 已用空间
//	* @param roomFlag 库房标志
//	* @param endFlag 最末层标志
//	* @param remark 备注
//	*/
//	public StoreroomStructure(int iD,String name,int parentID,String barcode,String fullName,int totalSize,int usedSize,boolean roomFlag,boolean endFlag,String remark)
//	{
//		// Columns List,Can Used in SELECT SQL: ID,Name,ParentID,Barcode,FullName,TotalSize,UsedSize,RoomFlag,EndFlag,Remark
//		// Columns List,Can Used in INSERT SQL: pID,pName,pParentID,pBarcode,pFullName,pTotalSize,pUsedSize,pRoomFlag,pEndFlag,pRemark
//		// Columns List,Can Used in UPDATE SQL: ID=pID,Name=pName,ParentID=pParentID,Barcode=pBarcode,FullName=pFullName,TotalSize=pTotalSize,UsedSize=pUsedSize,RoomFlag=pRoomFlag,EndFlag=pEndFlag,Remark=pRemark
//
//		this(name, parentID, barcode, fullName, totalSize, usedSize, roomFlag, endFlag, remark);
//		this.iD = iD;
//	}
//
//	/**
//	 * 成员对象在集合中的关键字
//	 */
//	private Object keyInCol=null;
//
//	/**
//	 * 获取属性值：成员对象在集合中的关键字
//	 * @return 成员对象在集合中的关键字
//	 */
//	public Object getKeyInCol()
//	{
//		return keyInCol;
//	}
//
//	/**
//	 * 设置属性值：成员对象在集合中的关键字
//	 * @param keyInCol 成员对象在集合中的关键字
//	 */
//	public void setKeyInCol(Object keyInCol)
//	{
//		this.keyInCol = keyInCol;
//	}
//
//	/**
//	 * 该数据项的附加对象，可以用来保存一些附加信息
//	 */
//	private Object tag=null;
//
//	/**
//	 * 获取属性值：该数据项的附加对象，可以用来保存一些附加信息
//	 * @return 该数据项的附加对象，可以用来保存一些附加信息
//	 */
//	public Object getTag()
//	{
//		return tag;
//	}
//
//	/**
//	 * 设置属性值：该数据项的附加对象，可以用来保存一些附加信息
//	 * @param tag 该数据项的附加对象，可以用来保存一些附加信息
//	 */
//	public void setTag(Object tag)
//	{
//		this.tag = tag;
//	}
//
//	/**
//	 * 设备编号
//	 */
//	private int iD=0;
//
//	/**
//	 * 获取属性值：设备编号
//	 * @return 设备编号
//	 */
//	public int getID()
//	{
//		return iD;
//	}
//
//	/**
//	 * 设置属性值：设备编号
//	 * @param iD 设备编号
//	 */
//	public void setID(int iD)
//	{
//		this.iD = iD;
//	}
//
//	/**
//	 * 设备名称
//	 */
//	private String name=null;
//
//	/**
//	 * 获取属性值：设备名称
//	 * @return 设备名称
//	 */
//	public String getName()
//	{
//		return name;
//	}
//
//	/**
//	 * 设置属性值：设备名称
//	 * @param name 设备名称
//	 */
//	public void setName(String name)
//	{
//		this.name = name;
//	}
//
//	/**
//	 * 上级设备编号
//	 */
//	private int parentID=0;
//
//	/**
//	 * 获取属性值：上级设备编号
//	 * @return 上级设备编号
//	 */
//	public int getParentID()
//	{
//		return parentID;
//	}
//
//	/**
//	 * 设置属性值：上级设备编号
//	 * @param parentID 上级设备编号
//	 */
//	public void setParentID(int parentID)
//	{
//		this.parentID = parentID;
//	}
//
//	/**
//	 * 档案位置条码
//	 */
//	private String barcode=null;
//
//	/**
//	 * 获取属性值：档案位置条码
//	 * @return 档案位置条码
//	 */
//	public String getBarcode()
//	{
//		return barcode;
//	}
//
//	/**
//	 * 设置属性值：档案位置条码
//	 * @param barcode 档案位置条码
//	 */
//	public void setBarcode(String barcode)
//	{
//		this.barcode = barcode;
//	}
//
//	/**
//	 * 设备完整名称
//	 */
//	private String fullName=null;
//
//	/**
//	 * 获取属性值：设备完整名称
//	 * @return 设备完整名称
//	 */
//	public String getFullName()
//	{
//		return fullName;
//	}
//
//	/**
//	 * 设置属性值：设备完整名称
//	 * @param fullName 设备完整名称
//	 */
//	public void setFullName(String fullName)
//	{
//		this.fullName = fullName;
//	}
//
//	/**
//	 * 总大小
//	 */
//	private int totalSize=0;
//
//	/**
//	 * 获取属性值：总大小
//	 * @return 总大小
//	 */
//	public int getTotalSize()
//	{
//		return totalSize;
//	}
//
//	/**
//	 * 设置属性值：总大小
//	 * @param totalSize 总大小
//	 */
//	public void setTotalSize(int totalSize)
//	{
//		this.totalSize = totalSize;
//	}
//
//	/**
//	 * 已用空间
//	 */
//	private int usedSize=0;
//
//	/**
//	 * 获取属性值：已用空间
//	 * @return 已用空间
//	 */
//	public int getUsedSize()
//	{
//		return usedSize;
//	}
//
//	/**
//	 * 设置属性值：已用空间
//	 * @param usedSize 已用空间
//	 */
//	public void setUsedSize(int usedSize)
//	{
//		this.usedSize = usedSize;
//	}
//
//	/**
//	 * 库房标志
//	 */
//	private boolean roomFlag=false;
//
//	/**
//	 * 获取属性值：库房标志
//	 * @return 库房标志
//	 */
//	public boolean getRoomFlag()
//	{
//		return roomFlag;
//	}
//
//	/**
//	 * 设置属性值：库房标志
//	 * @param roomFlag 库房标志
//	 */
//	public void setRoomFlag(boolean roomFlag)
//	{
//		this.roomFlag = roomFlag;
//	}
//
//	/**
//	 * 最末层标志
//	 */
//	private boolean endFlag=false;
//
//	/**
//	 * 获取属性值：最末层标志
//	 * @return 最末层标志
//	 */
//	public boolean getEndFlag()
//	{
//		return endFlag;
//	}
//
//	/**
//	 * 设置属性值：最末层标志
//	 * @param endFlag 最末层标志
//	 */
//	public void setEndFlag(boolean endFlag)
//	{
//		this.endFlag = endFlag;
//	}
//
//	/**
//	 * 备注
//	 */
//	private String remark=null;
//
//	/**
//	 * 获取属性值：备注
//	 * @return 备注
//	 */
//	public String getRemark()
//	{
//		return remark;
//	}
//
//	/**
//	 * 设置属性值：备注
//	 * @param remark 备注
//	 */
//	public void setRemark(String remark)
//	{
//		this.remark = remark;
//	}
//
//	/**
//	 * clone
//	 * @return 克隆当前对象实例后得到的新对象
//	 */
//	public StoreroomStructure clone()
//	{
//		StoreroomStructure item = new StoreroomStructure(iD,name,parentID,barcode,fullName,totalSize,usedSize,roomFlag,endFlag,remark);
//		item.setKeyInCol(keyInCol);
//		item.setTag(tag);
//
//		return item;
//	}
//
//	/**
//	 * 下级设备结构
//	 */
//	private List<StoreroomStructure> children = null;
//
//	/**
//	 * 设置属性值：下级设备结构
//	 * @param children 下级设备结构
//	 */
//	public void setChildren(List<StoreroomStructure> children) {
//		this.children = children;
//	}
//
//	/**
//	 * 获取属性值：下级设备结构
//	 * @return 下级设备结构
//	 */
//	public List<StoreroomStructure> getChildren() {
//		return children;
//	}
//
//	
//
//	
//    
//}



