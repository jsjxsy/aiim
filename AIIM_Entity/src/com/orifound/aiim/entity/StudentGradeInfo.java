package com.orifound.aiim.entity;

    
/**
 * 学生成绩信息表
 */
public class StudentGradeInfo
{
    /**
     * 构造函数
     */
    public StudentGradeInfo()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 成绩记录编号
	* @param xH 学号
	* @param courseNameCN 课程的中文名称
	* @param term 学期
	* @param totalHour 学时
	* @param credit 学分
	* @param grade 成绩分数
	*/
	public StudentGradeInfo(int iD,String xH,String courseNameCN,String term,String totalHour,String credit,String grade)
	{
		// Table Name: StudentGradeInfo
		// Columns List,Can Used in SELECT SQL: ID,XH,CourseNameCN,Term,TotalHour,Credit,Grade
		// Columns List,Can Used in INSERT SQL: :ID,:XH,:CourseNameCN,:Term,:TotalHour,:Credit,:Grade
		// Columns List,Can Used in UPDATE SQL: ID=:ID,XH=:XH,CourseNameCN=:CourseNameCN,Term=:Term,TotalHour=:TotalHour,Credit=:Credit,Grade=:Grade

		this.iD = iD;
		this.xH = xH;
		this.courseNameCN = courseNameCN;
		this.term = term;
		this.totalHour = totalHour;
		this.credit = credit;
		this.grade = grade;
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
	 * 成绩记录编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：成绩记录编号
	 * @return 成绩记录编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：成绩记录编号
	 * @param iD 成绩记录编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 学号
	 */
	private String xH=null;

	/**
	 * 获取属性值：学号
	 * @return 学号
	 */
	public String getXH()
	{
		return xH;
	}

	/**
	 * 设置属性值：学号
	 * @param xH 学号
	 */
	public void setXH(String xH)
	{
		this.xH = xH;
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
	 * 学期
	 */
	private String term;

	/**
	 * 获取属性值：学期
	 * @return 学期
	 */
	public String getTerm()
	{
		return term;
	}

	/**
	 * 设置属性值：学期
	 * @param term 学期
	 */
	public void setTerm(String term)
	{
		this.term = term;
	}

	/**
	 * 学时
	 */
	private String totalHour;

	/**
	 * 获取属性值：学时
	 * @return 学时
	 */
	public String getTotalHour()
	{
		return totalHour;
	}

	/**
	 * 设置属性值：学时
	 * @param totalHour 学时
	 */
	public void setTotalHour(String totalHour)
	{
		this.totalHour = totalHour;
	}

	/**
	 * 学分
	 */
	private String credit;

	/**
	 * 获取属性值：学分
	 * @return 学分
	 */
	public String getCredit()
	{
		return credit;
	}

	/**
	 * 设置属性值：学分
	 * @param credit 学分
	 */
	public void setCredit(String credit)
	{
		this.credit = credit;
	}

	/**
	 * 成绩分数
	 */
	private String grade;

	/**
	 * 获取属性值：成绩分数
	 * @return 成绩分数
	 */
	public String getGrade()
	{
		return grade;
	}

	/**
	 * 设置属性值：成绩分数
	 * @param grade 成绩分数
	 */
	public void setGrade(String grade)
	{
		this.grade = grade;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public StudentGradeInfo clone()
	{
		StudentGradeInfo item = new StudentGradeInfo(iD,xH,courseNameCN,term,totalHour,credit,grade);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param studentGradeInfo 指定的对象源
	*/
	public void cloneFrom(StudentGradeInfo studentGradeInfo)
	{
		this.iD = studentGradeInfo.getID();
		this.xH = studentGradeInfo.getXH();
		this.courseNameCN = studentGradeInfo.getCourseNameCN();
		this.term = studentGradeInfo.getTerm();
		this.totalHour = studentGradeInfo.getTotalHour();
		this.credit = studentGradeInfo.getCredit();
		this.grade = studentGradeInfo.getGrade();
		this.keyInCol = studentGradeInfo.getKeyInCol();
		this.tag = studentGradeInfo.getTag();
	}

	@Override
	public String toString() {
		return "StudentGradeInfo [courseNameCN=" + courseNameCN + ", credit="
				+ credit + ", grade=" + grade + ", iD=" + iD + ", term=" + term
				+ ", totalHour=" + totalHour + ", xH=" + xH + "]";
	}
}



