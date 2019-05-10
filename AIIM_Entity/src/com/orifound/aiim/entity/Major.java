package com.orifound.aiim.entity;

    
/**
 * ѧ������רҵ�������ֵ��
 */
public class Major
{
    /**
     * ���캯��
     */
    public Major()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param majorNameCN רҵ����������
	* @param majorNameEN רҵ��Ӣ������
	* @param shortKey ��ݼ�
	* @param remark ��ע
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
	 * רҵ����������
	 */
	private String majorNameCN=null;

	/**
	 * ��ȡ����ֵ��רҵ����������
	 * @return רҵ����������
	 */
	public String getMajorNameCN()
	{
		return majorNameCN;
	}

	/**
	 * ��������ֵ��רҵ����������
	 * @param majorNameCN רҵ����������
	 */
	public void setMajorNameCN(String majorNameCN)
	{
		this.majorNameCN = majorNameCN;
	}

	/**
	 * רҵ��Ӣ������
	 */
	private String majorNameEN=null;

	/**
	 * ��ȡ����ֵ��רҵ��Ӣ������
	 * @return רҵ��Ӣ������
	 */
	public String getMajorNameEN()
	{
		return majorNameEN;
	}

	/**
	 * ��������ֵ��רҵ��Ӣ������
	 * @param majorNameEN רҵ��Ӣ������
	 */
	public void setMajorNameEN(String majorNameEN)
	{
		this.majorNameEN = majorNameEN;
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
	public Major clone()
	{
		Major item = new Major(majorNameCN,majorNameEN,shortKey,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param major ָ���Ķ���Դ
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