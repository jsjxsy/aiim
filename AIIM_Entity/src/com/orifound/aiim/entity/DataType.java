package com.orifound.aiim.entity;

    
/**
 * ���������ֵ���ʵ����
 */
public class DataType
{
    /**
     * ���캯��
     */
    public DataType()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD �������ͱ��
	* @param name ������������
	* @param remark ��ע
	*/
	public DataType(String iD,String name,String remark)
	{
		// Table Name: DD_DataType
		// Columns List,Can Used in SELECT SQL: ID,Name,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,Remark=:Remark

		this.iD = iD;
		this.name = name;
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
	 * �������ͱ��
	 */
	private String iD=null;

	/**
	 * ��ȡ����ֵ���������ͱ��
	 * @return �������ͱ��
	 */
	public String getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ���������ͱ��
	 * @param iD �������ͱ��
	 */
	public void setID(String iD)
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
	public DataType clone()
	{
		DataType item = new DataType(iD,name,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param dataType ָ���Ķ���Դ
	*/
	public void cloneFrom(DataType dataType)
	{
		this.iD = dataType.getID();
		this.name = dataType.getName();
		this.remark = dataType.getRemark();
	}

    
}



