package com.orifound.aiim.entity;

    
/**
 * ���������ֵ���ʵ����
 */
public class RetentionPeriod
{
    /**
     * ���캯��
     */
    public RetentionPeriod()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD �������ޱ��
	* @param name ������������
	* @param totalYears ��������
	* @param remark ��ע
	*/
	public RetentionPeriod(int iD,String name,int totalYears,String remark)
	{
		// Table Name: DD_RetentionPeriod
		// Columns List,Can Used in SELECT SQL: ID,Name,TotalYears,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:TotalYears,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,TotalYears=:TotalYears,Remark=:Remark

		this(name, totalYears, remark);
		this.iD = iD;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param name ������������
	* @param totalYears ��������
	* @param remark ��ע
	*/
	public RetentionPeriod(String name,int totalYears,String remark)
	{
		this.name = name;
		this.totalYears = totalYears;
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
	 * �������ޱ��
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ���������ޱ��
	 * @return �������ޱ��
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ���������ޱ��
	 * @param iD �������ޱ��
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ������������
	 */
	private String name=null;

	/**
	 * ��ȡ����ֵ��������������
	 * @return ������������
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * ��������ֵ��������������
	 * @param name ������������
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * ��������
	 */
	private int totalYears=0;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public int getTotalYears()
	{
		return totalYears;
	}

	/**
	 * ��������ֵ����������
	 * @param totalYears ��������
	 */
	public void setTotalYears(int totalYears)
	{
		this.totalYears = totalYears;
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
	public RetentionPeriod clone()
	{
		RetentionPeriod item = new RetentionPeriod(iD,name,totalYears,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param retentionPeriod ָ���Ķ���Դ
	*/
	public void cloneFrom(RetentionPeriod retentionPeriod)
	{
		this.iD = retentionPeriod.getID();
		this.name = retentionPeriod.getName();
		this.totalYears = retentionPeriod.getTotalYears();
		this.remark = retentionPeriod.getRemark();
	}



    
}



