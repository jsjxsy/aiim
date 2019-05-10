package com.orifound.aiim.entity;

import java.util.*;

 
/**
 * �ⷿ�ṹ��Ϣ���ʵ����
 */

public class StoreroomStructure
{
    /**
     * ���캯��
     */
    public StoreroomStructure()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD �豸���
	* @param name �豸����
	* @param parentID �ϼ��豸���
	* @param barcode �ⷿλ������
	* @param fullName �豸��������
	* @param totalSize �ܴ�С
	* @param usedSize ���ÿռ�
	* @param roomFlag �ⷿ��־
	* @param roomID �����ⷿ���
	* @param endFlag ��ĩ���־
	* @param remark ��ע
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
	 * ��Ա�����ڼ����еĹؼ���
	 */
	private Object keyInCol=null;

	/**
	 * ��ȡ����ֵ����Ա�����ڼ����еĹؼ���
	 * @return ��Ա�����ڼ����еĹؼ���
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}

	/**
	 * ��������ֵ����Ա�����ڼ����еĹؼ���
	 * @param keyInCol ��Ա�����ڼ����еĹؼ���
	 */
	public void setKeyInCol(Object keyInCol)
	{
		this.keyInCol = keyInCol;
	}

	/**
	 * ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	private Object tag=null;

	/**
	 * ��ȡ����ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * @return ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public Object getTag()
	{
		return tag;
	}

	/**
	 * ��������ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * @param tag ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	/**
	 * �豸���
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ���豸���
	 * @return �豸���
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ���豸���
	 * @param iD �豸���
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * �豸����
	 */
	private String name=null;

	/**
	 * ��ȡ����ֵ���豸����
	 * @return �豸����
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * ��������ֵ���豸����
	 * @param name �豸����
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * �ϼ��豸���
	 */
	private int parentID=0;

	/**
	 * ��ȡ����ֵ���ϼ��豸���
	 * @return �ϼ��豸���
	 */
	public int getParentID()
	{
		return parentID;
	}

	/**
	 * ��������ֵ���ϼ��豸���
	 * @param parentID �ϼ��豸���
	 */
	public void setParentID(int parentID)
	{
		this.parentID = parentID;
	}

	/**
	 * ����λ������
	 */
	private String barcode=null;

	/**
	 * ��ȡ����ֵ������λ������
	 * @return ����λ������
	 */
	public String getBarcode()
	{
		return barcode;
	}

	/**
	 * ��������ֵ������λ������
	 * @param barcode ����λ������
	 */
	public void setBarcode(String barcode)
	{
		this.barcode = barcode;
	}

	/**
	 * �豸��������
	 */
	private String fullName=null;

	/**
	 * ��ȡ����ֵ���豸��������
	 * @return �豸��������
	 */
	public String getFullName()
	{
		return fullName;
	}

	/**
	 * ��������ֵ���豸��������
	 * @param fullName �豸��������
	 */
	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	/**
	 * �ܴ�С
	 */
	private int totalSize=0;

	/**
	 * ��ȡ����ֵ���ܴ�С
	 * @return �ܴ�С
	 */
	public int getTotalSize()
	{
		return totalSize;
	}

	/**
	 * ��������ֵ���ܴ�С
	 * @param totalSize �ܴ�С
	 */
	public void setTotalSize(int totalSize)
	{
		this.totalSize = totalSize;
	}

	/**
	 * ���ÿռ�
	 */
	private int usedSize=0;

	/**
	 * ��ȡ����ֵ�����ÿռ�
	 * @return ���ÿռ�
	 */
	public int getUsedSize()
	{
		return usedSize;
	}

	/**
	 * ��������ֵ�����ÿռ�
	 * @param usedSize ���ÿռ�
	 */
	public void setUsedSize(int usedSize)
	{
		this.usedSize = usedSize;
	}

	/**
	 * �ⷿ��־
	 */
	private boolean roomFlag=false;

	/**
	 * ��ȡ����ֵ���ⷿ��־
	 * @return �ⷿ��־
	 */
	public boolean getRoomFlag()
	{
		return roomFlag;
	}

	/**
	 * ��������ֵ���ⷿ��־
	 * @param roomFlag �ⷿ��־
	 */
	public void setRoomFlag(boolean roomFlag)
	{
		this.roomFlag = roomFlag;
	}
	
	/**
	 * �豸�����Ŀⷿ���
	 */
	private int roomID = 0;

	/**
	 * ��������ֵ:�豸�����Ŀⷿ���
	 * @return
	 */
	public int getRoomID() {
		return roomID;
	}

	/**
	 * ��������ֵ���豸�����Ŀⷿ���
	 * @param roomID
	 */
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	/**
	 * ��ĩ���־
	 */
	private boolean endFlag=false;

	/**
	 * ��ȡ����ֵ����ĩ���־
	 * @return ��ĩ���־
	 */
	public boolean getEndFlag()
	{
		return endFlag;
	}

	/**
	 * ��������ֵ����ĩ���־
	 * @param endFlag ��ĩ���־
	 */
	public void setEndFlag(boolean endFlag)
	{
		this.endFlag = endFlag;
	}

	/**
	 * ��ע
	 */
	private String remark=null;

	/**
	 * ��ȡ����ֵ����ע
	 * @return ��ע
	 */
	public String getRemark()
	{
		return remark;
	}

	/**
	 * ��������ֵ����ע
	 * @param remark ��ע
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	


	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public StoreroomStructure clone()
	{
		StoreroomStructure item = new StoreroomStructure(iD,name,parentID,barcode,fullName,totalSize,usedSize,roomFlag,roomID,endFlag,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param storeroomStructure ָ���Ķ���Դ
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
	 * �¼��豸�ṹ
	 */
	private List<StoreroomStructure> children = null;

	/**
	 * ��������ֵ���¼��豸�ṹ
	 * @param children �¼��豸�ṹ
	 */
	public void setChildren(List<StoreroomStructure> children) {
		this.children = children;
	}

	/**
	 * ��ȡ����ֵ���¼��豸�ṹ
	 * @return �¼��豸�ṹ
	 */
	public List<StoreroomStructure> getChildren() {
		return children;
	}
	
    
}








//public class StoreroomStructure
//{
//    /**
//     * ���캯��
//     */
//    public StoreroomStructure()
//    {
//        
//    }
//    
//    /**
//	* ���ֶβ����Ĺ��캯��
//	* @param name �豸����
//	* @param parentID �ϼ��豸���
//	* @param fullName �豸��������
//	* @param roomFlag �ⷿ��־
//	* @param endFlag ��ĩ���־
//	* @param remark ��ע
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
//	* ���ֶβ����Ĺ��캯��
//	* @param name �豸����
//	* @param parentID �ϼ��豸���
//	* @param fullName �豸��������
//	* @param totalSize �ܴ�С
//	* @param usedSize ���ÿռ�
//	* @param roomFlag �ⷿ��־
//	* @param endFlag ��ĩ���־
//	* @param remark ��ע
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
//	* ���ֶβ����Ĺ��캯��
//	* @param name �豸����
//	* @param parentID �ϼ��豸���
//	* @param barcode ����λ������
//	* @param fullName �豸��������
//	* @param totalSize �ܴ�С
//	* @param usedSize ���ÿռ�
//	* @param roomFlag �ⷿ��־
//	* @param endFlag ��ĩ���־
//	* @param remark ��ע
//	*/
//	public StoreroomStructure(String name,int parentID,String barcode,String fullName,int totalSize,int usedSize,boolean roomFlag,boolean endFlag,String remark)
//	{
//		this(name, parentID, fullName, totalSize, usedSize, roomFlag, endFlag, remark);
//		this.barcode = barcode;
//	}
//    
//	/**
//	* ���ֶβ����Ĺ��캯��
//	* @param iD �豸���
//	* @param name �豸����
//	* @param parentID �ϼ��豸���
//	* @param barcode ����λ������
//	* @param fullName �豸��������
//	* @param totalSize �ܴ�С
//	* @param usedSize ���ÿռ�
//	* @param roomFlag �ⷿ��־
//	* @param endFlag ��ĩ���־
//	* @param remark ��ע
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
//	 * ��Ա�����ڼ����еĹؼ���
//	 */
//	private Object keyInCol=null;
//
//	/**
//	 * ��ȡ����ֵ����Ա�����ڼ����еĹؼ���
//	 * @return ��Ա�����ڼ����еĹؼ���
//	 */
//	public Object getKeyInCol()
//	{
//		return keyInCol;
//	}
//
//	/**
//	 * ��������ֵ����Ա�����ڼ����еĹؼ���
//	 * @param keyInCol ��Ա�����ڼ����еĹؼ���
//	 */
//	public void setKeyInCol(Object keyInCol)
//	{
//		this.keyInCol = keyInCol;
//	}
//
//	/**
//	 * ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
//	 */
//	private Object tag=null;
//
//	/**
//	 * ��ȡ����ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
//	 * @return ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
//	 */
//	public Object getTag()
//	{
//		return tag;
//	}
//
//	/**
//	 * ��������ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
//	 * @param tag ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
//	 */
//	public void setTag(Object tag)
//	{
//		this.tag = tag;
//	}
//
//	/**
//	 * �豸���
//	 */
//	private int iD=0;
//
//	/**
//	 * ��ȡ����ֵ���豸���
//	 * @return �豸���
//	 */
//	public int getID()
//	{
//		return iD;
//	}
//
//	/**
//	 * ��������ֵ���豸���
//	 * @param iD �豸���
//	 */
//	public void setID(int iD)
//	{
//		this.iD = iD;
//	}
//
//	/**
//	 * �豸����
//	 */
//	private String name=null;
//
//	/**
//	 * ��ȡ����ֵ���豸����
//	 * @return �豸����
//	 */
//	public String getName()
//	{
//		return name;
//	}
//
//	/**
//	 * ��������ֵ���豸����
//	 * @param name �豸����
//	 */
//	public void setName(String name)
//	{
//		this.name = name;
//	}
//
//	/**
//	 * �ϼ��豸���
//	 */
//	private int parentID=0;
//
//	/**
//	 * ��ȡ����ֵ���ϼ��豸���
//	 * @return �ϼ��豸���
//	 */
//	public int getParentID()
//	{
//		return parentID;
//	}
//
//	/**
//	 * ��������ֵ���ϼ��豸���
//	 * @param parentID �ϼ��豸���
//	 */
//	public void setParentID(int parentID)
//	{
//		this.parentID = parentID;
//	}
//
//	/**
//	 * ����λ������
//	 */
//	private String barcode=null;
//
//	/**
//	 * ��ȡ����ֵ������λ������
//	 * @return ����λ������
//	 */
//	public String getBarcode()
//	{
//		return barcode;
//	}
//
//	/**
//	 * ��������ֵ������λ������
//	 * @param barcode ����λ������
//	 */
//	public void setBarcode(String barcode)
//	{
//		this.barcode = barcode;
//	}
//
//	/**
//	 * �豸��������
//	 */
//	private String fullName=null;
//
//	/**
//	 * ��ȡ����ֵ���豸��������
//	 * @return �豸��������
//	 */
//	public String getFullName()
//	{
//		return fullName;
//	}
//
//	/**
//	 * ��������ֵ���豸��������
//	 * @param fullName �豸��������
//	 */
//	public void setFullName(String fullName)
//	{
//		this.fullName = fullName;
//	}
//
//	/**
//	 * �ܴ�С
//	 */
//	private int totalSize=0;
//
//	/**
//	 * ��ȡ����ֵ���ܴ�С
//	 * @return �ܴ�С
//	 */
//	public int getTotalSize()
//	{
//		return totalSize;
//	}
//
//	/**
//	 * ��������ֵ���ܴ�С
//	 * @param totalSize �ܴ�С
//	 */
//	public void setTotalSize(int totalSize)
//	{
//		this.totalSize = totalSize;
//	}
//
//	/**
//	 * ���ÿռ�
//	 */
//	private int usedSize=0;
//
//	/**
//	 * ��ȡ����ֵ�����ÿռ�
//	 * @return ���ÿռ�
//	 */
//	public int getUsedSize()
//	{
//		return usedSize;
//	}
//
//	/**
//	 * ��������ֵ�����ÿռ�
//	 * @param usedSize ���ÿռ�
//	 */
//	public void setUsedSize(int usedSize)
//	{
//		this.usedSize = usedSize;
//	}
//
//	/**
//	 * �ⷿ��־
//	 */
//	private boolean roomFlag=false;
//
//	/**
//	 * ��ȡ����ֵ���ⷿ��־
//	 * @return �ⷿ��־
//	 */
//	public boolean getRoomFlag()
//	{
//		return roomFlag;
//	}
//
//	/**
//	 * ��������ֵ���ⷿ��־
//	 * @param roomFlag �ⷿ��־
//	 */
//	public void setRoomFlag(boolean roomFlag)
//	{
//		this.roomFlag = roomFlag;
//	}
//
//	/**
//	 * ��ĩ���־
//	 */
//	private boolean endFlag=false;
//
//	/**
//	 * ��ȡ����ֵ����ĩ���־
//	 * @return ��ĩ���־
//	 */
//	public boolean getEndFlag()
//	{
//		return endFlag;
//	}
//
//	/**
//	 * ��������ֵ����ĩ���־
//	 * @param endFlag ��ĩ���־
//	 */
//	public void setEndFlag(boolean endFlag)
//	{
//		this.endFlag = endFlag;
//	}
//
//	/**
//	 * ��ע
//	 */
//	private String remark=null;
//
//	/**
//	 * ��ȡ����ֵ����ע
//	 * @return ��ע
//	 */
//	public String getRemark()
//	{
//		return remark;
//	}
//
//	/**
//	 * ��������ֵ����ע
//	 * @param remark ��ע
//	 */
//	public void setRemark(String remark)
//	{
//		this.remark = remark;
//	}
//
//	/**
//	 * clone
//	 * @return ��¡��ǰ����ʵ����õ����¶���
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
//	 * �¼��豸�ṹ
//	 */
//	private List<StoreroomStructure> children = null;
//
//	/**
//	 * ��������ֵ���¼��豸�ṹ
//	 * @param children �¼��豸�ṹ
//	 */
//	public void setChildren(List<StoreroomStructure> children) {
//		this.children = children;
//	}
//
//	/**
//	 * ��ȡ����ֵ���¼��豸�ṹ
//	 * @return �¼��豸�ṹ
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



