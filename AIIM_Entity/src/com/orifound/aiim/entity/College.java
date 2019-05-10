package com.orifound.aiim.entity;

    
/**
 * 学院信息数据字典表
 */
public class College
{
    /**
     * 构造函数
     */
    public College()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param collegeNameCN 学院中文名称
	* @param collegeNameEN 学院英文名称
	* @param remark 备注
	*/
	public College(String collegeNameCN,String collegeNameEN,String remark)
	{
		// Table Name: DD_College
		// Columns List,Can Used in SELECT SQL: CollegeNameCN,CollegeNameEN,Remark
		// Columns List,Can Used in INSERT SQL: :CollegeNameCN,:CollegeNameEN,:Remark
		// Columns List,Can Used in UPDATE SQL: CollegeNameCN=:CollegeNameCN,CollegeNameEN=:CollegeNameEN,Remark=:Remark

		this.collegeNameCN = collegeNameCN;
		this.collegeNameEN = collegeNameEN;
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
	 * 学院中文名称
	 */
	private String collegeNameCN=null;

	/**
	 * 获取属性值：学院中文名称
	 * @return 学院中文名称
	 */
	public String getCollegeNameCN()
	{
		return collegeNameCN;
	}

	/**
	 * 设置属性值：学院中文名称
	 * @param collegeNameCN 学院中文名称
	 */
	public void setCollegeNameCN(String collegeNameCN)
	{
		this.collegeNameCN = collegeNameCN;
	}

	/**
	 * 学院英文名称
	 */
	private String collegeNameEN=null;

	/**
	 * 获取属性值：学院英文名称
	 * @return 学院英文名称
	 */
	public String getCollegeNameEN()
	{
		return collegeNameEN;
	}

	/**
	 * 设置属性值：学院英文名称
	 * @param collegeNameEN 学院英文名称
	 */
	public void setCollegeNameEN(String collegeNameEN)
	{
		this.collegeNameEN = collegeNameEN;
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
	public College clone()
	{
		College item = new College(collegeNameCN,collegeNameEN,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param college 指定的对象源
	*/
	public void cloneFrom(College college)
	{
		this.collegeNameCN = college.getCollegeNameCN();
		this.collegeNameEN = college.getCollegeNameEN();
		this.remark = college.getRemark();
		this.keyInCol = college.getKeyInCol();
		this.tag = college.getTag();
	}
}