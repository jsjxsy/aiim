package com.orifound.aiim.entity;

import java.util.Date;
    
/**
 * 档案归档过程信息表的实体类
 */
public class ArchivesInfoWorkProcedure
{
    /**
     * 构造函数
     */
    public ArchivesInfoWorkProcedure()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param nBXH 档案内部序号
	* @param archivesTypeID 档案分类编号
	* @param userID 用户编号
	* @param workFlowStatus 档案管理工作流状态
	* @param operateTime 操作时间
	* @param sendBackReason 打回原因
	*/
	public ArchivesInfoWorkProcedure(int iD,int nBXH,int archivesTypeID,int userID,int workFlowStatus,Date operateTime,String sendBackReason)
	{
		// Table Name: ArchivesInfoWorkProcedure_TypeCode
		// Columns List,Can Used in SELECT SQL: ID,NBXH,ArchivesTypeID,UserID,WorkFlowStatus,OperateTime,SendBackReason
		// Columns List,Can Used in INSERT SQL: :ID,:NBXH,:ArchivesTypeID,:UserID,:WorkFlowStatus,:OperateTime,:SendBackReason
		// Columns List,Can Used in UPDATE SQL: ID=:ID,NBXH=:NBXH,ArchivesTypeID=:ArchivesTypeID,UserID=:UserID,WorkFlowStatus=:WorkFlowStatus,OperateTime=:OperateTime,SendBackReason=:SendBackReason

		this(nBXH, archivesTypeID, userID, workFlowStatus, sendBackReason);
		this.iD = iD;
		this.operateTime = operateTime;
	}
	
	/**
	* 带字段参数的构造函数
	* @param nBXH 档案内部序号
	* @param archivesTypeID 档案分类编号
	* @param userID 用户编号
	* @param workFlowStatus 档案管理工作流状态
	* @param sendBackReason 打回原因
	*/
	public ArchivesInfoWorkProcedure(int nBXH,int archivesTypeID,int userID,int workFlowStatus,String sendBackReason)
	{
		this(nBXH, archivesTypeID, userID, workFlowStatus);
		this.sendBackReason = sendBackReason;
	}
	
	/**
	* 带字段参数的构造函数
	* @param nBXH 档案内部序号
	* @param archivesTypeID 档案分类编号
	* @param userID 用户编号
	* @param workFlowStatus 档案管理工作流状态
	*/
	public ArchivesInfoWorkProcedure(int nBXH,int archivesTypeID,int userID,int workFlowStatus)
	{
		this.nBXH = nBXH;
		this.archivesTypeID = archivesTypeID;
		this.userID = userID;
		this.workFlowStatus =EnumWorkFlowStatus.getEnumElement(workFlowStatus);
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
	 * 用户编号
	 */
	private int userID=0;

	/**
	 * 获取属性值：用户编号
	 * @return 用户编号
	 */
	public int getUserID()
	{
		return userID;
	}

	/**
	 * 设置属性值：用户编号
	 * @param userID 用户编号
	 */
	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	/**
	 * 档案管理工作流状态
	 */
	private EnumWorkFlowStatus workFlowStatus=EnumWorkFlowStatus.NONE;

	/**
	 * 获取属性值：档案管理工作流状态
	 * @return 档案管理工作流状态
	 */
	public EnumWorkFlowStatus getWorkFlowStatus()
	{
		return workFlowStatus;
	}

	/**
	 * 设置属性值：档案管理工作流状态
	 * @param workFlowStatus 档案管理工作流状态
	 */
	public void setWorkFlowStatus(EnumWorkFlowStatus workFlowStatus)
	{
		this.workFlowStatus = workFlowStatus;
	}

	/**
	 * 操作时间
	 */
	private Date operateTime;

	/**
	 * 获取属性值：操作时间
	 * @return 操作时间
	 */
	public Date getOperateTime()
	{
		return operateTime;
	}

	/**
	 * 设置属性值：操作时间
	 * @param operateTime 操作时间
	 */
	public void setOperateTime(Date operateTime)
	{
		this.operateTime = operateTime;
	}

	/**
	 * 打回原因
	 */
	private String sendBackReason=null;

	/**
	 * 获取属性值：打回原因
	 * @return 打回原因
	 */
	public String getSendBackReason()
	{
		return sendBackReason;
	}

	/**
	 * 设置属性值：打回原因
	 * @param sendBackReason 打回原因
	 */
	public void setSendBackReason(String sendBackReason)
	{
		this.sendBackReason = sendBackReason;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ArchivesInfoWorkProcedure clone()
	{
		ArchivesInfoWorkProcedure item = new ArchivesInfoWorkProcedure(iD,nBXH,archivesTypeID,userID,workFlowStatus.getEnumValue(),operateTime,sendBackReason);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param archivesInfoWorkProcedure 指定的对象源
	*/
	public void cloneFrom(ArchivesInfoWorkProcedure archivesInfoWorkProcedure)
	{
		this.iD = archivesInfoWorkProcedure.getID();
		this.nBXH = archivesInfoWorkProcedure.getNBXH();
		this.archivesTypeID = archivesInfoWorkProcedure.getArchivesTypeID();
		this.userID = archivesInfoWorkProcedure.getUserID();
		this.workFlowStatus = archivesInfoWorkProcedure.getWorkFlowStatus();
		this.operateTime = archivesInfoWorkProcedure.getOperateTime();
		this.sendBackReason = archivesInfoWorkProcedure.getSendBackReason();
		this.keyInCol = archivesInfoWorkProcedure.getKeyInCol();
		this.tag = archivesInfoWorkProcedure.getTag();
	}

    
}



