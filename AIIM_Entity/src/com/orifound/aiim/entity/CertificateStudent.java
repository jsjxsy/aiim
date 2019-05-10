package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 出证学生信息表
 */
public class CertificateStudent
{
    /**
     * 构造函数
     */
    public CertificateStudent()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param xH 学号
	* @param nameCN 中文名
	* @param nameEN 英文名
	* @param majorNameCN 专业的中文名称
	* @param collegeNameCN 学院中文名称
	* @param entranceDate 入学日期
	* @param graduateDate 毕业日期
	* @param gradeRecordDate 成绩登记日期
	*/
	public CertificateStudent(String xH,String nameCN,String nameEN,String majorNameCN,String collegeNameCN,Date entranceDate,Date graduateDate,Date gradeRecordDate)
	{
		// Table Name: CertificateStudent
		// Columns List,Can Used in SELECT SQL: XH,NameCN,NameEN,MajorNameCN,CollegeNameCN,EntranceDate,GraduateDate,GradeRecordDate
		// Columns List,Can Used in INSERT SQL: :XH,:NameCN,:NameEN,:MajorNameCN,:CollegeNameCN,:EntranceDate,:GraduateDate,:GradeRecordDate
		// Columns List,Can Used in UPDATE SQL: XH=:XH,NameCN=:NameCN,NameEN=:NameEN,MajorNameCN=:MajorNameCN,CollegeNameCN=:CollegeNameCN,EntranceDate=:EntranceDate,GraduateDate=:GraduateDate,GradeRecordDate=:GradeRecordDate

		this.xH = xH;
		this.nameCN = nameCN;
		this.nameEN = nameEN;
		this.majorNameCN = majorNameCN;
		this.collegeNameCN = collegeNameCN;
		this.entranceDate = entranceDate;
		this.graduateDate = graduateDate;
		this.gradeRecordDate = gradeRecordDate;
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
	 * 中文名
	 */
	private String nameCN=null;

	/**
	 * 获取属性值：中文名
	 * @return 中文名
	 */
	public String getNameCN()
	{
		return nameCN;
	}

	/**
	 * 设置属性值：中文名
	 * @param nameCN 中文名
	 */
	public void setNameCN(String nameCN)
	{
		this.nameCN = nameCN;
	}

	/**
	 * 英文名
	 */
	private String nameEN=null;

	/**
	 * 获取属性值：英文名
	 * @return 英文名
	 */
	public String getNameEN()
	{
		return nameEN;
	}

	/**
	 * 设置属性值：英文名
	 * @param nameEN 英文名
	 */
	public void setNameEN(String nameEN)
	{
		this.nameEN = nameEN;
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
	 * 入学日期
	 */
	private Date entranceDate;

	/**
	 * 获取属性值：入学日期
	 * @return 入学日期
	 */
	public Date getEntranceDate()
	{
		return entranceDate;
	}

	/**
	 * 设置属性值：入学日期
	 * @param entranceDate 入学日期
	 */
	public void setEntranceDate(Date entranceDate)
	{
		this.entranceDate = entranceDate;
	}

	/**
	 * 毕业日期
	 */
	private Date graduateDate;

	/**
	 * 获取属性值：毕业日期
	 * @return 毕业日期
	 */
	public Date getGraduateDate()
	{
		return graduateDate;
	}

	/**
	 * 设置属性值：毕业日期
	 * @param graduateDate 毕业日期
	 */
	public void setGraduateDate(Date graduateDate)
	{
		this.graduateDate = graduateDate;
	}

	/**
	 * 成绩登记日期
	 */
	private Date gradeRecordDate;

	/**
	 * 获取属性值：成绩登记日期
	 * @return 成绩登记日期
	 */
	public Date getGradeRecordDate()
	{
		return gradeRecordDate;
	}

	/**
	 * 设置属性值：成绩登记日期
	 * @param gradeRecordDate 成绩登记日期
	 */
	public void setGradeRecordDate(Date gradeRecordDate)
	{
		this.gradeRecordDate = gradeRecordDate;
	}
	
	/**
	 * 出证信息id
	 */
	private int certificateInfoID;

	public int getCertificateInfoID() {
		return certificateInfoID;
	}

	public void setCertificateInfoID(int certificateInfoID) {
		this.certificateInfoID = certificateInfoID;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public CertificateStudent clone()
	{
		CertificateStudent item = new CertificateStudent(xH,nameCN,nameEN,majorNameCN,collegeNameCN,entranceDate,graduateDate,gradeRecordDate);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param certificateStudent 指定的对象源
	*/
	public void cloneFrom(CertificateStudent certificateStudent)
	{
		this.xH = certificateStudent.getXH();
		this.nameCN = certificateStudent.getNameCN();
		this.nameEN = certificateStudent.getNameEN();
		this.majorNameCN = certificateStudent.getMajorNameCN();
		this.collegeNameCN = certificateStudent.getCollegeNameCN();
		this.entranceDate = certificateStudent.getEntranceDate();
		this.graduateDate = certificateStudent.getGraduateDate();
		this.gradeRecordDate = certificateStudent.getGradeRecordDate();
		this.keyInCol = certificateStudent.getKeyInCol();
		this.tag = certificateStudent.getTag();
	}



    
}



