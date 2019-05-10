package com.orifound.aiim.entity;

    
/**
 * ѧԺ��Ϣ�����ֵ��
 */
public class College
{
    /**
     * ���캯��
     */
    public College()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param collegeNameCN ѧԺ��������
	* @param collegeNameEN ѧԺӢ������
	* @param remark ��ע
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
	 * ѧԺ��������
	 */
	private String collegeNameCN=null;

	/**
	 * ��ȡ����ֵ��ѧԺ��������
	 * @return ѧԺ��������
	 */
	public String getCollegeNameCN()
	{
		return collegeNameCN;
	}

	/**
	 * ��������ֵ��ѧԺ��������
	 * @param collegeNameCN ѧԺ��������
	 */
	public void setCollegeNameCN(String collegeNameCN)
	{
		this.collegeNameCN = collegeNameCN;
	}

	/**
	 * ѧԺӢ������
	 */
	private String collegeNameEN=null;

	/**
	 * ��ȡ����ֵ��ѧԺӢ������
	 * @return ѧԺӢ������
	 */
	public String getCollegeNameEN()
	{
		return collegeNameEN;
	}

	/**
	 * ��������ֵ��ѧԺӢ������
	 * @param collegeNameEN ѧԺӢ������
	 */
	public void setCollegeNameEN(String collegeNameEN)
	{
		this.collegeNameEN = collegeNameEN;
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
	public College clone()
	{
		College item = new College(collegeNameCN,collegeNameEN,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param college ָ���Ķ���Դ
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