package com.orifound.aiim.entity;

    
/**
 * ��ɫ����������Ȩ���ʵ����
 */
public class RoleRightArchivesType extends ArchivesType
{
    /**
     * ���캯��
     */
    public RoleRightArchivesType()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��Ȩ��¼���
	* @param rolesID �û���ɫ���
	* @param archivesTypeID ����������
	* @param rightFlag_ArchivesInfo �ɲ鿴����������Ϣ��Ȩ�ޱ�־
	* @param rightFlag_AttachedFile �ɲ���ԭ�ĵ����ļ���Ȩ�ޱ�־
	* @param rightFlag_PaperArchives ������ʵ�ﵵ����Ȩ�ޱ�־
	*/
	public RoleRightArchivesType(int iD,int rolesID,int archivesTypeID,boolean rightFlag_ArchivesInfo,boolean rightFlag_AttachedFile,boolean rightFlag_PaperArchives)
	{
		// Table Name: DDR_UserRoles_ArchivesType
		// Columns List,Can Used in SELECT SQL: ID,RolesID,ArchivesTypeID,RightFlag_ArchivesInfo,RightFlag_AttachedFile,RightFlag_PaperArchives
		// Columns List,Can Used in INSERT SQL: :ID,:RolesID,:ArchivesTypeID,:RightFlag_ArchivesInfo,:RightFlag_AttachedFile,:RightFlag_PaperArchives
		// Columns List,Can Used in UPDATE SQL: ID=:ID,RolesID=:RolesID,ArchivesTypeID=:ArchivesTypeID,RightFlag_ArchivesInfo=:RightFlag_ArchivesInfo,RightFlag_AttachedFile=:RightFlag_AttachedFile,RightFlag_PaperArchives=:RightFlag_PaperArchives

		this(rolesID, archivesTypeID, rightFlag_ArchivesInfo, rightFlag_AttachedFile, rightFlag_PaperArchives);
		this.iD = iD;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param rolesID �û���ɫ���
	* @param archivesTypeID ����������
	* @param rightFlag_ArchivesInfo �ɲ鿴����������Ϣ��Ȩ�ޱ�־
	* @param rightFlag_AttachedFile �ɲ���ԭ�ĵ����ļ���Ȩ�ޱ�־
	* @param rightFlag_PaperArchives ������ʵ�ﵵ����Ȩ�ޱ�־
	*/
	public RoleRightArchivesType(int rolesID,int archivesTypeID,boolean rightFlag_ArchivesInfo,boolean rightFlag_AttachedFile,boolean rightFlag_PaperArchives)
	{
		this.rolesID = rolesID;
		this.archivesTypeID = archivesTypeID;
		setRightFlag_ArchivesInfo(rightFlag_ArchivesInfo);
		setRightFlag_AttachedFile(rightFlag_AttachedFile);
		setRightFlag_PaperArchives(rightFlag_PaperArchives);
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��Ȩ��¼���
	* @param rolesID �û���ɫ���
	* @param archivesTypeID ����������
	* @param rightFlag_ArchivesInfo �ɲ鿴����������Ϣ��Ȩ�ޱ�־
	* @param rightFlag_AttachedFile �ɲ���ԭ�ĵ����ļ���Ȩ�ޱ�־
	* @param rightFlag_PaperArchives ������ʵ�ﵵ����Ȩ�ޱ�־
	* @param topFlag һ����Ŀ��־
	* @param name ������������
	* @param code �������
	* @param parentID �ϼ�����������
	* @param topTypeID ���������
	* @param fullCode ��������
	* @param fullName ��������
	* @param endFlag ��ײ��־
	* @param contentIDFormatLength ����ŵĸ�ʽ������
	* @param subContentIDFormatLength �����ļ���ŵĸ�ʽ������
	* @param archivesIDExpression ������ɱ��ʽ
	* @param attachedFileSavePath ԭ�Ĵ洢·��
	* @param renewPeriod ��������
	* @param remark ��ע
	*/
	public RoleRightArchivesType(int iD,int rolesID,int archivesTypeID,boolean rightFlag_ArchivesInfo,boolean rightFlag_AttachedFile,boolean rightFlag_PaperArchives,
			boolean topFlag,String name,String code,int parentID,int topTypeID,String fullCode,
			String fullName,boolean endFlag,int contentIDFormatLength,int subContentIDFormatLength,
			String archivesIDExpressionPrefix,String archivesIDExpression,String subArchivesIDExpression,
			String attachedFileSavePath,int renewPeriod,String remark)
	{
		this(iD,rolesID, archivesTypeID, rightFlag_ArchivesInfo, rightFlag_AttachedFile, rightFlag_PaperArchives);
		super.setTopFlag(topFlag);
		super.setName(name);
		super.setCode(code);
		super.setParentID(parentID);
		super.setTopTypeID(topTypeID);
		super.setFullCode(fullCode);
		super.setFullName(fullName);
		super.setEndFlag(endFlag);
		super.setContentIDFormatLength(contentIDFormatLength);
		super.setSubContentIDFormatLength(subContentIDFormatLength);
		super.setArchivesIDExpressionPrefix(archivesIDExpressionPrefix);
		super.setArchivesIDExpression(archivesIDExpression);
		super.setSubArchivesIDExpression(subArchivesIDExpression);
		super.setAttachedFileSavePath(attachedFileSavePath);
		super.setRenewPeriod(renewPeriod);
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
	 * ��Ȩ��¼���
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ����Ȩ��¼���
	 * @return ��Ȩ��¼���
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ����Ȩ��¼���
	 * @param iD ��Ȩ��¼���
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
	 * ����������
	 */
	private int archivesTypeID=0;

	/**
	 * ��ȡ����ֵ������������
	 * @return ����������
	 */
	public int getArchivesTypeID()
	{
		return archivesTypeID;
	}

	/**
	 * ��������ֵ������������
	 * @param archivesTypeID ����������
	 */
	public void setArchivesTypeID(int archivesTypeID)
	{
		this.archivesTypeID = archivesTypeID;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public RoleRightArchivesType clone()
	{
		RoleRightArchivesType item = new RoleRightArchivesType(iD,rolesID,archivesTypeID,getRightFlag_ArchivesInfo(),getRightFlag_AttachedFile(),getRightFlag_PaperArchives());
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param roleRightArchivesType ָ���Ķ���Դ
	*/
	public void cloneFrom(RoleRightArchivesType roleRightArchivesType)
	{
		this.iD = roleRightArchivesType.getID();
		this.rolesID = roleRightArchivesType.getRolesID();
		this.archivesTypeID = roleRightArchivesType.getArchivesTypeID();
	}

    
}



