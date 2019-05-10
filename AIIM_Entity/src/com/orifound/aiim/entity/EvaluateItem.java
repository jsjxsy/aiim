package com.orifound.aiim.entity;

/**
 * ��Ч������Ŀ�ֵ��
 */
public class EvaluateItem
{
    /**
     * ���캯��
     */
    public EvaluateItem()
    {
        
    }
    
    /**
	* ���ֶβ����Ĺ��캯��
	* @param name ����������
	* @param score �������ֵ
	* @param remark ��ע
	*/
	public EvaluateItem(String name,int score,String remark)
	{
		// Table Name: DD_EvaluateItem
		// Columns List,Can Used in SELECT SQL: ID,Name,Score,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:Score,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,Score=:Score,Remark=:Remark

		this.name = name;
		this.score = score;
		this.remark = remark;
	}
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��������
	* @param name ����������
	* @param score �������ֵ
	* @param remark ��ע
	*/
	public EvaluateItem(int iD,String name,int score,String remark)
	{
		// Table Name: DD_EvaluateItem
		// Columns List,Can Used in SELECT SQL: ID,Name,Score,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:Score,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,Score=:Score,Remark=:Remark

		this(name, score, remark);
		this.iD = iD;
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
	 * ��������
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ����������
	 * @param iD ��������
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ����������
	 */
	private String name=null;

	/**
	 * ��ȡ����ֵ������������
	 * @return ����������
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * ��������ֵ������������
	 * @param name ����������
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * �������ֵ
	 */
	private int score=0;

	/**
	 * ��ȡ����ֵ���������ֵ
	 * @return �������ֵ
	 */
	public int getScore()
	{
		return score;
	}

	/**
	 * ��������ֵ���������ֵ
	 * @param score �������ֵ
	 */
	public void setScore(int score)
	{
		this.score = score;
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
	public EvaluateItem clone()
	{
		EvaluateItem item = new EvaluateItem(iD,name,score,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param evaluateItem ָ���Ķ���Դ
	*/
	public void cloneFrom(EvaluateItem evaluateItem)
	{
		this.iD = evaluateItem.getID();
		this.name = evaluateItem.getName();
		this.score = evaluateItem.getScore();
		this.remark = evaluateItem.getRemark();
		this.keyInCol = evaluateItem.getKeyInCol();
		this.tag = evaluateItem.getTag();
	}
}