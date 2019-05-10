package com.orifound.aiim.entity;


/**
 * ���������ڵ����ֵ���ʵ����
 */
public class Area
{
    /**
     * ���캯��
     */
    public Area()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param name ��������
	* @param defaultSelectedFlag ȱʡѡ���־
	* @param areaFlag �����־
	*/
	public Area(String name,boolean defaultSelectedFlag,int areaFlag)
	{
		this.name = name;
		this.defaultSelectedFlag = defaultSelectedFlag;
		this.areaFlag = areaFlag;
	}
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param name ��������
	* @param defaultSelectedFlag ȱʡѡ���־
	* @param areaFlag �����־
	*/
	public Area(int iD,String name,boolean defaultSelectedFlag,int areaFlag)
	{
		// Columns List,Can Used in SELECT SQL: ID,Name,DefaultSelectedFlag,AreaFlag
		// Columns List,Can Used in INSERT SQL: pID,pName,pDefaultSelectedFlag,pAreaFlag
		// Columns List,Can Used in UPDATE SQL: ID=pID,Name=pName,DefaultSelectedFlag=pDefaultSelectedFlag,AreaFlag=pAreaFlag

		this(name, defaultSelectedFlag, areaFlag);
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
	 * ���
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ�����
	 * @return ���
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ�����
	 * @param iD ���
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ��������
	 */
	private String name=null;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * ��������ֵ����������
	 * @param name ��������
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * ȱʡѡ���־
	 */
	private boolean defaultSelectedFlag=false;

	/**
	 * ��ȡ����ֵ��ȱʡѡ���־
	 * @return ȱʡѡ���־
	 */
	public boolean getDefaultSelectedFlag()
	{
		return defaultSelectedFlag;
	}

	/**
	 * ��������ֵ��ȱʡѡ���־
	 * @param defaultSelectedFlag ȱʡѡ���־
	 */
	public void setDefaultSelectedFlag(boolean defaultSelectedFlag)
	{
		this.defaultSelectedFlag = defaultSelectedFlag;
	}

	/**
	 * �����־
	 */
	private int areaFlag=0;

	/**
	 * ��ȡ����ֵ�������־
	 * @return �����־
	 */
	public int getAreaFlag()
	{
		return areaFlag;
	}

	/**
	 * ��������ֵ�������־
	 * @param areaFlag �����־
	 */
	public void setAreaFlag(int areaFlag)
	{
		this.areaFlag = areaFlag;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public Area clone()
	{
		Area item = new Area(iD,name,defaultSelectedFlag,areaFlag);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	public void cloneFrom(Area area)
	{
		this.iD = area.getID();
		this.name = area.getName();
		this.defaultSelectedFlag = area.getDefaultSelectedFlag();
		this.areaFlag = area.getAreaFlag();
		this.keyInCol = area.getKeyInCol();
		this.tag = area.getTag();
	}
}



