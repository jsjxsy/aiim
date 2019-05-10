package com.orifound.aiim.entity;

   
/**
 * 纸质档案移交子批次信息表的实体类
 */
public class PaperTansferSubBatch
{
    /**
     * 构造函数
     */
    public PaperTansferSubBatch()
    {
    }
    
	/**
	* 带字段参数的构造函数
	* @param subBatNo 子批次号
	*/
	public PaperTansferSubBatch(String subBatNo)
	{
		this.subBatNo = subBatNo;
	}
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param batNo 移交批次号
	* @param subBatNo 子批次号
	*/
	public PaperTansferSubBatch(int iD,String batNo,String subBatNo)
	{
		// Columns List,Can Used in SELECT SQL: ID,BatNo,SubBatNo
		// Columns List,Can Used in INSERT SQL: pID,pBatNo,pSubBatNo
		// Columns List,Can Used in UPDATE SQL: ID=pID,BatNo=pBatNo,SubBatNo=pSubBatNo

		this.iD = iD;
		this.batNo = batNo;
		this.subBatNo = subBatNo;
	}

	/**
	 * 成员对象在集合中的关键字
	 */
	private Object keyInCol=null;

	/**
	 * 获取属性值：成员对象在集合中的关键字
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}
	/**
	 * 设置属性值：成员对象在集合中的关键字
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
	 */
	public Object getTag()
	{
		return tag;
	}
	/**
	 * 设置属性值：该数据项的附加对象，可以用来保存一些附加信息
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
	 */
	public int getID()
	{
		return iD;
	}
	/**
	 * 设置属性值：编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 移交批次号
	 */
	private String batNo=null;

	/**
	 * 获取属性值：移交批次号
	 */
	public String getBatNo()
	{
		return batNo;
	}
	/**
	 * 设置属性值：移交批次号
	 */
	public void setBatNo(String batNo)
	{
		this.batNo = batNo;
	}

	/**
	 * 子批次号
	 */
	private String subBatNo=null;

	/**
	 * 获取属性值：子批次号
	 */
	public String getSubBatNo()
	{
		return subBatNo;
	}
	/**
	 * 设置属性值：子批次号
	 */
	public void setSubBatNo(String subBatNo)
	{
		this.subBatNo = subBatNo;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public PaperTansferSubBatch clone()
	{
		PaperTansferSubBatch item = new PaperTansferSubBatch(iD,batNo,subBatNo);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}


    
}



