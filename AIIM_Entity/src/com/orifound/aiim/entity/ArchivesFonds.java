package com.orifound.aiim.entity;

/**
 * ����ȫ�������ֵ���ʵ����
 */
public class ArchivesFonds
{
    /**
     * ���캯��
     */
    public ArchivesFonds()
    {
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param code ȫ�ں�
	* @param name ȫ������
	* @param defaultFlag ȱʡ��־
	* @param remark ��ע
	*/
	public ArchivesFonds(int iD,String code,String name,boolean defaultFlag,String remark)
	{
		// Table Name: DD_ArchivesFonds
		// Columns List,Can Used in SELECT SQL: ID,Code,Name,DefaultFlag,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Code,:Name,:DefaultFlag,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Code=:Code,Name=:Name,DefaultFlag=:DefaultFlag,Remark=:Remark

		this(code, name, defaultFlag, remark);
		this.iD = iD;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param code ȫ�ں�
	* @param name ȫ������
	* @param defaultFlag ȱʡ��־
	* @param remark ��ע
	*/
	public ArchivesFonds(String code,String name,boolean defaultFlag,String remark)
	{
		this.code = code;
		this.name = name;
		this.defaultFlag = defaultFlag;
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
	 * ȫ�ں�
	 */
	private String code=null;

	/**
	 * ��ȡ����ֵ��ȫ�ں�
	 * @return ȫ�ں�
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * ��������ֵ��ȫ�ں�
	 * @param code ȫ�ں�
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * ȫ������
	 */
	private String name=null;

	/**
	 * ��ȡ����ֵ��ȫ������
	 * @return ȫ������
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * ��������ֵ��ȫ������
	 * @param name ȫ������
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * ȱʡ��־
	 */
	private boolean defaultFlag=false;

	/**
	 * ��ȡ����ֵ��ȱʡ��־
	 * @return ȱʡ��־
	 */
	public boolean getDefaultFlag()
	{
		return defaultFlag;
	}

	/**
	 * ��������ֵ��ȱʡ��־
	 * @param defaultFlag ȱʡ��־
	 */
	public void setDefaultFlag(boolean defaultFlag)
	{
		this.defaultFlag = defaultFlag;
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
	public ArchivesFonds clone()
	{
		ArchivesFonds item = new ArchivesFonds(iD,code,name,defaultFlag,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param archivesFonds ָ���Ķ���Դ
	*/
	public void cloneFrom(ArchivesFonds archivesFonds)
	{
		this.iD = archivesFonds.getID();
		this.code = archivesFonds.getCode();
		this.name = archivesFonds.getName();
		this.defaultFlag = archivesFonds.getDefaultFlag();
		this.remark = archivesFonds.getRemark();
		this.keyInCol = archivesFonds.getKeyInCol();
		this.tag = archivesFonds.getTag();
	}

}



