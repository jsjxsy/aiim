package com.orifound.aiim.entity;

    
/**
 * �γ���Ϣ�����ֵ��
 */
public class Course
{
    /**
     * ���캯��
     */
    public Course()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param courseNameCN �γ̵���������
	* @param courseNameEN �γ̵�Ӣ������
	* @param electivesFlag ����ѡ�޿α�־
	* @param shortKey ��ݼ�
	* @param remark ��ע
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
	 * �γ̵���������
	 */
	private String courseNameCN=null;

	/**
	 * ��ȡ����ֵ���γ̵���������
	 * @return �γ̵���������
	 */
	public String getCourseNameCN()
	{
		return courseNameCN;
	}

	/**
	 * ��������ֵ���γ̵���������
	 * @param courseNameCN �γ̵���������
	 */
	public void setCourseNameCN(String courseNameCN)
	{
		this.courseNameCN = courseNameCN;
	}

	/**
	 * �γ̵�Ӣ������
	 */
	private String courseNameEN=null;

	/**
	 * ��ȡ����ֵ���γ̵�Ӣ������
	 * @return �γ̵�Ӣ������
	 */
	public String getCourseNameEN()
	{
		return courseNameEN;
	}

	/**
	 * ��������ֵ���γ̵�Ӣ������
	 * @param courseNameEN �γ̵�Ӣ������
	 */
	public void setCourseNameEN(String courseNameEN)
	{
		this.courseNameEN = courseNameEN;
	}

	/**
	 * ����ѡ�޿α�־
	 */
	private boolean electivesFlag=false;

	/**
	 * ��ȡ����ֵ������ѡ�޿α�־
	 * @return ����ѡ�޿α�־
	 */
	public boolean getElectivesFlag()
	{
		return electivesFlag;
	}

	/**
	 * ��������ֵ������ѡ�޿α�־
	 * @param electivesFlag ����ѡ�޿α�־
	 */
	public void setElectivesFlag(boolean electivesFlag)
	{
		this.electivesFlag = electivesFlag;
	}

	/**
	 * ��ݼ�
	 */
	private String shortKey=null;

	/**
	 * ��ȡ����ֵ����ݼ�
	 * @return ��ݼ�
	 */
	public String getShortKey()
	{
		return shortKey;
	}

	/**
	 * ��������ֵ����ݼ�
	 * @param shortKey ��ݼ�
	 */
	public void setShortKey(String shortKey)
	{
		this.shortKey = shortKey;
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
	public Course clone()
	{
		Course item = new Course(courseNameCN,courseNameEN,electivesFlag,shortKey,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param course ָ���Ķ���Դ
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