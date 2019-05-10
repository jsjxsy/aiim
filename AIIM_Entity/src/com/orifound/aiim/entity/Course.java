package com.orifound.aiim.entity;

    
/**
 * 课程信息数据字典表
 */
public class Course
{
    /**
     * 构造函数
     */
    public Course()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param courseNameCN 课程的中文名称
	* @param courseNameEN 课程的英文名称
	* @param electivesFlag 公共选修课标志
	* @param shortKey 快捷键
	* @param remark 备注
	*/
	public Course(String courseNameCN,String courseNameEN,boolean electivesFlag,String shortKey,String remark)
	{
		// Table Name: DD_Course
		// Columns List,Can Used in SELECT SQL: CourseNameCN,CourseNameEN,ElectivesFlag,ShortKey,Remark
		// Columns List,Can Used in INSERT SQL: :CourseNameCN,:CourseNameEN,:ElectivesFlag,:ShortKey,:Remark
		// Columns List,Can Used in UPDATE SQL: CourseNameCN=:CourseNameCN,CourseNameEN=:CourseNameEN,ElectivesFlag=:ElectivesFlag,ShortKey=:ShortKey,Remark=:Remark

		this.courseNameCN = courseNameCN;
		this.courseNameEN = courseNameEN;
		this.electivesFlag = electivesFlag;
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
	 * 课程的中文名称
	 */
	private String courseNameCN=null;

	/**
	 * 获取属性值：课程的中文名称
	 * @return 课程的中文名称
	 */
	public String getCourseNameCN()
	{
		return courseNameCN;
	}

	/**
	 * 设置属性值：课程的中文名称
	 * @param courseNameCN 课程的中文名称
	 */
	public void setCourseNameCN(String courseNameCN)
	{
		this.courseNameCN = courseNameCN;
	}

	/**
	 * 课程的英文名称
	 */
	private String courseNameEN=null;

	/**
	 * 获取属性值：课程的英文名称
	 * @return 课程的英文名称
	 */
	public String getCourseNameEN()
	{
		return courseNameEN;
	}

	/**
	 * 设置属性值：课程的英文名称
	 * @param courseNameEN 课程的英文名称
	 */
	public void setCourseNameEN(String courseNameEN)
	{
		this.courseNameEN = courseNameEN;
	}

	/**
	 * 公共选修课标志
	 */
	private boolean electivesFlag=false;

	/**
	 * 获取属性值：公共选修课标志
	 * @return 公共选修课标志
	 */
	public boolean getElectivesFlag()
	{
		return electivesFlag;
	}

	/**
	 * 设置属性值：公共选修课标志
	 * @param electivesFlag 公共选修课标志
	 */
	public void setElectivesFlag(boolean electivesFlag)
	{
		this.electivesFlag = electivesFlag;
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
	public Course clone()
	{
		Course item = new Course(courseNameCN,courseNameEN,electivesFlag,shortKey,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param course 指定的对象源
	*/
	public void cloneFrom(Course course)
	{
		this.courseNameCN = course.getCourseNameCN();
		this.courseNameEN = course.getCourseNameEN();
		this.electivesFlag = course.getElectivesFlag();
		this.shortKey = course.getShortKey();
		this.remark = course.getRemark();
		this.keyInCol = course.getKeyInCol();
		this.tag = course.getTag();
	}

	@Override
	public String toString() {
		return "Course [courseNameCN=" + courseNameCN + ", courseNameEN="
				+ courseNameEN + ", electivesFlag=" + electivesFlag
				+ ", remark=" + remark + ", shortKey=" + shortKey + "]";
	}
}