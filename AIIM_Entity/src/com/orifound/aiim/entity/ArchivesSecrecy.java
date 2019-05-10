package com.orifound.aiim.entity;

    
/**
 * �����ܼ������ֵ���ʵ����
 */
public class ArchivesSecrecy extends BaseRightArchivesResource
{
    /**
     * ���캯��
     */
    public ArchivesSecrecy()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD �����ܼ����
	* @param name �����ܼ�����
	* @param remark ��ע
	*/
	public ArchivesSecrecy(int iD,String name,String remark)
	{
		// Table Name: DD_ArchivesSecrecy
		// Columns List,Can Used in SELECT SQL: ID,Name,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,Remark=:Remark

		this(name, remark);
		this.iD = iD;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param name �����ܼ�����
	* @param remark ��ע
	*/
	public ArchivesSecrecy(String name,String remark)
	{
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
	 * �����ܼ����
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ�������ܼ����
	 * @return �����ܼ����
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ�������ܼ����
	 * @param iD �����ܼ����
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * �����ܼ�����
	 */
	private String name=null;

	/**
	 * ��ȡ����ֵ�������ܼ�����
	 * @return �����ܼ�����
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * ��������ֵ�������ܼ�����
	 * @param name �����ܼ�����
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
	public ArchivesSecrecy clone()
	{
		ArchivesSecrecy item = new ArchivesSecrecy(iD,name,remark);
		item.setRightFlag_ArchivesInfo(getRightFlag_ArchivesInfo());
		item.setRightFlag_AttachedFile(getRightFlag_AttachedFile());
		item.setRightFlag_PaperArchives(getRightFlag_PaperArchives());
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param archivesSecrecy ָ���Ķ���Դ
	*/
	public void cloneFrom(ArchivesSecrecy archivesSecrecy)
	{
		super.cloneFrom(archivesSecrecy);
		this.iD = archivesSecrecy.getID();
		this.name = archivesSecrecy.getName();
		this.remark = archivesSecrecy.getRemark();
	}



    
}



