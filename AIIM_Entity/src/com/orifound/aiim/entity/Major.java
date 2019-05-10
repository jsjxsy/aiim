package com.orifound.aiim.entity;

    
/**
 * 学生所属专业的数据字典表
 */
public class Major
{
    /**
     * 构造函数
     */
    public Major()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param majorNameCN 专业的中文名称
	* @param majorNameEN 专业的英文名称
	* @param shortKey 快捷键
	* @param remark 备注
	*/
	public Major(String majorNameCN,String majorNameEN,String shortKey,String remark)
	{
		// Table Name: DD_Major
		// Columns List,Can Used in SELECT SQL: MajorNameCN,MajorNameEN,ShortKey,Remark
		// Columns List,Can Used in INSERT SQL: :MajorNameCN,:MajorNameEN,:ShortKey,:Remark
		// Columns List,Can Used in UPDATE SQL: MajorNameCN=:MajorNameCN,MajorNameEN=:MajorNameEN,ShortKey=:ShortKey,Remark=:Remark

		this.majorNameCN = majorNameCN;
		this.majorNameEN = majorNameEN;
		this.shortKey = shortKey;
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
	 * 专业的中文名称
	 */
	private String majorNameCN=null;

	/**
	 * 获取属性值：专业的中文名称
	 * @return 专业的中文名称
	 */
	public String getMajorNameCN()
	{
		return majorNameCN;
	}

	/**
	 * 设置属性值：专业的中文名称
	 * @param majorNameCN 专业的中文名称
	 */
	public void setMajorNameCN(String majorNameCN)
	{
		this.majorNameCN = majorNameCN;
	}

	/**
	 * 专业的英文名称
	 */
	private String majorNameEN=null;

	/**
	 * 获取属性值：专业的英文名称
	 * @return 专业的英文名称
	 */
	public String getMajorNameEN()
	{
		return majorNameEN;
	}

	/**
	 * 设置属性值：专业的英文名称
	 * @param majorNameEN 专业的英文名称
	 */
	public void setMajorNameEN(String majorNameEN)
	{
		this.majorNameEN = majorNameEN;
	}

	/**
	 * 快捷键
	 */
	private String shortKey=null;

	/**
	 * 获取属性值：快捷键
	 * @return 快捷键
	 */
	public String getShortKey()
	{
		return shortKey;
	}

	/**
	 * 设置属性值：快捷键
	 * @param shortKey 快捷键
	 */
	public void setShortKey(String shortKey)
	{
		this.shortKey = shortKey;
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
	public Major clone()
	{
		Major item = new Major(majorNameCN,majorNameEN,shortKey,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param major 指定的对象源
	*/
	public void cloneFrom(Major major)
	{
		this.majorNameCN = major.getMajorNameCN();
		this.majorNameEN = major.getMajorNameEN();
		this.shortKey = major.getShortKey();
		this.remark = major.getRemark();
		this.keyInCol = major.getKeyInCol();
		this.tag = major.getTag();
	}
}