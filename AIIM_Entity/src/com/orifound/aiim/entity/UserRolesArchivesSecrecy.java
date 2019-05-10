package com.orifound.aiim.entity;

    
/**
 * �û���ɫ�ĵ����ܼ�Ȩ�ޱ�ʵ����
 */
public class UserRolesArchivesSecrecy
{
    /**
     * ���캯��
     */
    public UserRolesArchivesSecrecy()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param rolesID �û���ɫ���
	* @param secrecyID �����ܼ����
	*/
	public UserRolesArchivesSecrecy(int iD,int rolesID,int secrecyID)
	{
		// Table Name: DDR_UserRoles_ArchivesSecrecy
		// Columns List,Can Used in SELECT SQL: ID,RolesID,SecrecyID
		// Columns List,Can Used in INSERT SQL: :ID,:RolesID,:SecrecyID
		// Columns List,Can Used in UPDATE SQL: ID=:ID,RolesID=:RolesID,SecrecyID=:SecrecyID

		this.iD = iD;
		this.rolesID = rolesID;
		this.secrecyID = secrecyID;
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
	 * �û���ɫ���
	 */
	private int rolesID=0;

	/**
	 * ��ȡ����ֵ���û���ɫ���
	 * @return �û���ɫ���
	 */
	public int getRolesID()
	{
		return rolesID;
	}

	/**
	 * ��������ֵ���û���ɫ���
	 * @param rolesID �û���ɫ���
	 */
	public void setRolesID(int rolesID)
	{
		this.rolesID = rolesID;
	}

	/**
	 * �����ܼ����
	 */
	private int secrecyID=0;

	/**
	 * ��ȡ����ֵ�������ܼ����
	 * @return �����ܼ����
	 */
	public int getSecrecyID()
	{
		return secrecyID;
	}

	/**
	 * ��������ֵ�������ܼ����
	 * @param secrecyID �����ܼ����
	 */
	public void setSecrecyID(int secrecyID)
	{
		this.secrecyID = secrecyID;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public UserRolesArchivesSecrecy clone()
	{
		UserRolesArchivesSecrecy item = new UserRolesArchivesSecrecy(iD,rolesID,secrecyID);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param userRolesArchivesSecrecy ָ���Ķ���Դ
	*/
	public void cloneFrom(UserRolesArchivesSecrecy userRolesArchivesSecrecy)
	{
		this.iD = userRolesArchivesSecrecy.getID();
		this.rolesID = userRolesArchivesSecrecy.getRolesID();
		this.secrecyID = userRolesArchivesSecrecy.getSecrecyID();
		this.keyInCol = userRolesArchivesSecrecy.getKeyInCol();
		this.tag = userRolesArchivesSecrecy.getTag();
	}

    
}