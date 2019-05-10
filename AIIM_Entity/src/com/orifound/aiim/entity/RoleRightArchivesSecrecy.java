package com.orifound.aiim.entity;

    
/**
 * �û���ɫ�����ܼ�Ȩ�ޱ��ʵ����
 */
public class RoleRightArchivesSecrecy extends ArchivesSecrecy
{
    /**
     * ���캯��
     */
    public RoleRightArchivesSecrecy()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��Ȩ��¼���
	* @param rolesID �û���ɫ���
	* @param secrecyID �����ܼ����
	* @param rightFlag_ArchivesInfo �ɲ鿴����������Ϣ��Ȩ�ޱ�־
	* @param rightFlag_AttachedFile �ɲ���ԭ�ĵ����ļ���Ȩ�ޱ�־
	* @param rightFlag_PaperArchives ������ʵ�ﵵ����Ȩ�ޱ�־
	*/
	public RoleRightArchivesSecrecy(int iD,int rolesID,int secrecyID,boolean rightFlag_ArchivesInfo,boolean rightFlag_AttachedFile,boolean rightFlag_PaperArchives)
	{
		// Table Name: DDR_UserRoles_ArchivesSecrecy
		// Columns List,Can Used in SELECT SQL: ID,RolesID,SecrecyID,RightFlag_ArchivesInfo,RightFlag_AttachedFile,RightFlag_PaperArchives
		// Columns List,Can Used in INSERT SQL: :ID,:RolesID,:SecrecyID,:RightFlag_ArchivesInfo,:RightFlag_AttachedFile,:RightFlag_PaperArchives
		// Columns List,Can Used in UPDATE SQL: ID=:ID,RolesID=:RolesID,SecrecyID=:SecrecyID,RightFlag_ArchivesInfo=:RightFlag_ArchivesInfo,RightFlag_AttachedFile=:RightFlag_AttachedFile,RightFlag_PaperArchives=:RightFlag_PaperArchives

		this(rolesID, secrecyID,rightFlag_ArchivesInfo,rightFlag_AttachedFile,rightFlag_PaperArchives);
		this.iD = iD;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param rolesID �û���ɫ���
	* @param secrecyID �����ܼ����
	* @param rightFlag_ArchivesInfo �ɲ鿴����������Ϣ��Ȩ�ޱ�־
	* @param rightFlag_AttachedFile �ɲ���ԭ�ĵ����ļ���Ȩ�ޱ�־
	* @param rightFlag_PaperArchives ������ʵ�ﵵ����Ȩ�ޱ�־
	*/
	public RoleRightArchivesSecrecy(int rolesID,int secrecyID,boolean rightFlag_ArchivesInfo,boolean rightFlag_AttachedFile,boolean rightFlag_PaperArchives)
	{
		this.rolesID = rolesID;
		this.secrecyID = secrecyID;
		setRightFlag_ArchivesInfo(rightFlag_ArchivesInfo);
		setRightFlag_AttachedFile(rightFlag_AttachedFile);
		setRightFlag_PaperArchives(rightFlag_PaperArchives);
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��Ȩ��¼���
	* @param rolesID �û���ɫ���
	* @param secrecyID �����ܼ����
	* @param rightFlag_ArchivesInfo �ɲ鿴����������Ϣ��Ȩ�ޱ�־
	* @param rightFlag_AttachedFile �ɲ���ԭ�ĵ����ļ���Ȩ�ޱ�־
	* @param rightFlag_PaperArchives ������ʵ�ﵵ����Ȩ�ޱ�־
	* @param name �����ܼ�����
	* @param remark ��ע
	*/
	public RoleRightArchivesSecrecy(int iD,int rolesID,int secrecyID,boolean rightFlag_ArchivesInfo,boolean rightFlag_AttachedFile,boolean rightFlag_PaperArchives,String name,String remark)
	{
		this(iD,rolesID, secrecyID,rightFlag_ArchivesInfo,rightFlag_AttachedFile,rightFlag_PaperArchives);
		super.setName(name);
		super.setRemark(remark);
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
	public RoleRightArchivesSecrecy clone()
	{
		RoleRightArchivesSecrecy item = new RoleRightArchivesSecrecy(iD, rolesID, secrecyID,getRightFlag_ArchivesInfo(),getRightFlag_AttachedFile(),getRightFlag_PaperArchives(),  getName(), getRemark());
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param roleRightArchivesSecrecy ָ���Ķ���Դ
	*/
	public void cloneFrom(RoleRightArchivesSecrecy roleRightArchivesSecrecy)
	{
		super.cloneFrom(roleRightArchivesSecrecy);
		this.iD = roleRightArchivesSecrecy.getID();
		this.rolesID = roleRightArchivesSecrecy.getRolesID();
		this.secrecyID = roleRightArchivesSecrecy.getSecrecyID();
	}

    
}



