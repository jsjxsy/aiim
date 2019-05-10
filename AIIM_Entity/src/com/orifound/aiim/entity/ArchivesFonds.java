package com.orifound.aiim.entity;

/**
 * 档案全宗数据字典表的实体类
 */
public class ArchivesFonds
{
    /**
     * 构造函数
     */
    public ArchivesFonds()
    {
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param code 全宗号
	* @param name 全宗名称
	* @param defaultFlag 缺省标志
	* @param remark 备注
	*/
	public ArchivesFonds(int iD,String code,String name,boolean defaultFlag,String remark)
	{
		// Table Name: DD_ArchivesFonds
		// Columns List,Can Used in SELECT SQL: ID,Code,Name,DefaultFlag,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Code,:Name,:DefaultFlag,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Code=:Code,Name=:Name,DefaultFlag=:DefaultFlag,Remark=:Remark

		this(code, name, defaultFlag, remark);
		this.iD = iD;
	}
	
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param code 全宗号
	* @param name 全宗名称
	* @param defaultFlag 缺省标志
	* @param remark 备注
	*/
	public ArchivesFonds(String code,String name,boolean defaultFlag,String remark)
	{
		this.code = code;
		this.name = name;
		this.defaultFlag = defaultFlag;
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
	 * 全宗号
	 */
	private String code=null;

	/**
	 * 获取属性值：全宗号
	 * @return 全宗号
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * 设置属性值：全宗号
	 * @param code 全宗号
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * 全宗名称
	 */
	private String name=null;

	/**
	 * 获取属性值：全宗名称
	 * @return 全宗名称
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 设置属性值：全宗名称
	 * @param name 全宗名称
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * 缺省标志
	 */
	private boolean defaultFlag=false;

	/**
	 * 获取属性值：缺省标志
	 * @return 缺省标志
	 */
	public boolean getDefaultFlag()
	{
		return defaultFlag;
	}

	/**
	 * 设置属性值：缺省标志
	 * @param defaultFlag 缺省标志
	 */
	public void setDefaultFlag(boolean defaultFlag)
	{
		this.defaultFlag = defaultFlag;
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
	public ArchivesFonds clone()
	{
		ArchivesFonds item = new ArchivesFonds(iD,code,name,defaultFlag,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param archivesFonds 指定的对象源
	*/
	public void cloneFrom(ArchivesFonds archivesFonds)
	{
		this.iD = archivesFonds.getID();
		this.code = archivesFonds.getCode();
		this.name = archivesFonds.getName();
		this.defaultFlag = archivesFonds.getDefaultFlag();
		this.remark = archivesFonds.getRemark();
		this.keyInCol = archivesFonds.getKeyInCol();
		this.tag = archivesFonds.getTag();
	}

}



