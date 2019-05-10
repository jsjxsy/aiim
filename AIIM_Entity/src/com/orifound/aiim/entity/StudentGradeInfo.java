package com.orifound.aiim.entity;

    
/**
 * ѧ���ɼ���Ϣ��
 */
public class StudentGradeInfo
{
    /**
     * ���캯��
     */
    public StudentGradeInfo()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD �ɼ���¼���
	* @param xH ѧ��
	* @param courseNameCN �γ̵���������
	* @param term ѧ��
	* @param totalHour ѧʱ
	* @param credit ѧ��
	* @param grade �ɼ�����
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
	 * �ɼ���¼���
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ���ɼ���¼���
	 * @return �ɼ���¼���
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ���ɼ���¼���
	 * @param iD �ɼ���¼���
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ѧ��
	 */
	private String xH=null;

	/**
	 * ��ȡ����ֵ��ѧ��
	 * @return ѧ��
	 */
	public String getXH()
	{
		return xH;
	}

	/**
	 * ��������ֵ��ѧ��
	 * @param xH ѧ��
	 */
	public void setXH(String xH)
	{
		this.xH = xH;
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
	 * ѧ��
	 */
	private String term;

	/**
	 * ��ȡ����ֵ��ѧ��
	 * @return ѧ��
	 */
	public String getTerm()
	{
		return term;
	}

	/**
	 * ��������ֵ��ѧ��
	 * @param term ѧ��
	 */
	public void setTerm(String term)
	{
		this.term = term;
	}

	/**
	 * ѧʱ
	 */
	private String totalHour;

	/**
	 * ��ȡ����ֵ��ѧʱ
	 * @return ѧʱ
	 */
	public String getTotalHour()
	{
		return totalHour;
	}

	/**
	 * ��������ֵ��ѧʱ
	 * @param totalHour ѧʱ
	 */
	public void setTotalHour(String totalHour)
	{
		this.totalHour = totalHour;
	}

	/**
	 * ѧ��
	 */
	private String credit;

	/**
	 * ��ȡ����ֵ��ѧ��
	 * @return ѧ��
	 */
	public String getCredit()
	{
		return credit;
	}

	/**
	 * ��������ֵ��ѧ��
	 * @param credit ѧ��
	 */
	public void setCredit(String credit)
	{
		this.credit = credit;
	}

	/**
	 * �ɼ�����
	 */
	private String grade;

	/**
	 * ��ȡ����ֵ���ɼ�����
	 * @return �ɼ�����
	 */
	public String getGrade()
	{
		return grade;
	}

	/**
	 * ��������ֵ���ɼ�����
	 * @param grade �ɼ�����
	 */
	public void setGrade(String grade)
	{
		this.grade = grade;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public StudentGradeInfo clone()
	{
		StudentGradeInfo item = new StudentGradeInfo(iD,xH,courseNameCN,term,totalHour,credit,grade);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param studentGradeInfo ָ���Ķ���Դ
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



