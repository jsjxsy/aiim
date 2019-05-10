package com.orifound.aiim.entity;

import java.util.LinkedHashMap;
    
/**
 * ����Դ�ֵ���ʵ����
 */
public class DataSource
{
    /**
     * ���캯��
     */
    public DataSource()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ����Դ���
	* @param name ����Դ����
	* @param inherentType ��������־
	* @param remark ��ע
	*/
	public DataSource(int iD,String name,int inherentType,String remark)
	{
		// Table Name: DD_DataSource
		// Columns List,Can Used in SELECT SQL: ID,Name,InherentType,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:InherentType,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,InherentType=:InherentType,Remark=:Remark

		this(name, inherentType, remark);
		this.iD = iD;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param name ����Դ����
	* @param inherentType ��������־
	* @param remark ��ע
	*/
	public DataSource(String name,int inherentType,String remark)
	{
		this.name = name;
		this.inherentType =EnumDataSourceInherentType.getEnumElement(inherentType);
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
	 * ����Դ���
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ������Դ���
	 * @return ����Դ���
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ������Դ���
	 * @param iD ����Դ���
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ����Դ����
	 */
	private String name=null;

	/**
	 * ��ȡ����ֵ������Դ����
	 * @return ����Դ����
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * ��������ֵ������Դ����
	 * @param name ����Դ����
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * ��������־
	 */
	private EnumDataSourceInherentType inherentType=EnumDataSourceInherentType.NONE;

	/**
	 * ��ȡ����ֵ����������־
	 * @return ��������־
	 */
	public EnumDataSourceInherentType getInherentType()
	{
		return inherentType;
	}

	/**
	 * ��������ֵ����������־
	 * @param inherentType ��������־
	 */
	public void setInherentType(EnumDataSourceInherentType inherentType)
	{
		this.inherentType = inherentType;
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
	public DataSource clone()
	{
		DataSource item = new DataSource(iD,name,inherentType.getEnumValue(),remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param dataSource ָ���Ķ���Դ
	*/
	public void cloneFrom(DataSource dataSource)
	{
		this.iD = dataSource.getID();
		this.name = dataSource.getName();
		this.inherentType = dataSource.getInherentType();
		this.remark = dataSource.getRemark();
	}




	/**
	 * ����Դӵ�е��������
	 */
	private LinkedHashMap<Integer, DataSourceItem> dataSourceItems = null;

	/**
	 * ��������ֵ������Դӵ�е��������
	 * @param datasourceItems ����Դӵ�е��������
	 */
	public void setDataSourceItems(LinkedHashMap<Integer, DataSourceItem> dataSourceItems)
	{
		this.dataSourceItems = dataSourceItems;
	}

	/**
	 * ��ȡ����ֵ������Դӵ�е��������
	 * @return ����Դӵ�е��������
	 */
	public LinkedHashMap<Integer, DataSourceItem> getDataSourceItems()
	{
		return dataSourceItems;
	}

	

	

	
    
}



