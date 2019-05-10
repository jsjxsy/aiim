package com.orifound.aiim.entity;

    
/**
 * ����Դ���������ֵ���ʵ����
 */
public class DataSourceItem
{
    /**
     * ���캯��
     */
    public DataSourceItem()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ����
	* @param dataSourceID ����Դ���
	* @param orderID ����
	* @param name ����
	* @param remark ��ע
	*/
	public DataSourceItem(int iD,int dataSourceID,int orderID,String name,String remark)
	{
		// Table Name: DD_DataSourceItem
		// Columns List,Can Used in SELECT SQL: ID,DataSourceID,OrderID,Name,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:DataSourceID,:OrderID,:Name,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,DataSourceID=:DataSourceID,OrderID=:OrderID,Name=:Name,Remark=:Remark

		this(dataSourceID, orderID, name, remark);
		this.iD = iD;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param dataSourceID ����Դ���
	* @param orderID ����
	* @param name ����
	* @param remark ��ע
	*/
	public DataSourceItem(int dataSourceID,int orderID,String name,String remark)
	{
		this.dataSourceID = dataSourceID;
		this.orderID = orderID;
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
	 * ����
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ������
	 * @return ����
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ������
	 * @param iD ����
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ����Դ���
	 */
	private int dataSourceID=0;

	/**
	 * ��ȡ����ֵ������Դ���
	 * @return ����Դ���
	 */
	public int getDataSourceID()
	{
		return dataSourceID;
	}

	/**
	 * ��������ֵ������Դ���
	 * @param dataSourceID ����Դ���
	 */
	public void setDataSourceID(int dataSourceID)
	{
		this.dataSourceID = dataSourceID;
	}

	/**
	 * ����
	 */
	private int orderID=0;

	/**
	 * ��ȡ����ֵ������
	 * @return ����
	 */
	public int getOrderID()
	{
		return orderID;
	}

	/**
	 * ��������ֵ������
	 * @param orderID ����
	 */
	public void setOrderID(int orderID)
	{
		this.orderID = orderID;
	}

	/**
	 * ����
	 */
	private String name=null;

	/**
	 * ��ȡ����ֵ������
	 * @return ����
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * ��������ֵ������
	 * @param name ����
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
	public DataSourceItem clone()
	{
		DataSourceItem item = new DataSourceItem(iD,dataSourceID,orderID,name,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param dataSourceItem ָ���Ķ���Դ
	*/
	public void cloneFrom(DataSourceItem dataSourceItem)
	{
		this.iD = dataSourceItem.getID();
		this.dataSourceID = dataSourceItem.getDataSourceID();
		this.orderID = dataSourceItem.getOrderID();
		this.name = dataSourceItem.getName();
		this.remark = dataSourceItem.getRemark();
	}

    
}



