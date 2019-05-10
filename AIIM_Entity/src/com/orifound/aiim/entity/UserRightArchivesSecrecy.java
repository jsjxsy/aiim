package com.orifound.aiim.entity;

    
/**
 * �û������ܼ�Ȩ�ޱ��ʵ����
 */
public class UserRightArchivesSecrecy extends ArchivesSecrecy
{
    /**
     * ���캯��
     */
    public UserRightArchivesSecrecy()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��Ȩ��¼���
	* @param userID �û����
	* @param secrecyID �����ܼ����
	* @param rightFlag_ArchivesInfo �ɲ鿴����������Ϣ��Ȩ�ޱ�־
	* @param rightFlag_AttachedFile �ɲ���ԭ�ĵ����ļ���Ȩ�ޱ�־
	* @param rightFlag_PaperArchives ������ʵ�ﵵ����Ȩ�ޱ�־
	*/
	public UserRightArchivesSecrecy(int iD,int userID,int secrecyID,boolean rightFlag_ArchivesInfo,boolean rightFlag_AttachedFile,boolean rightFlag_PaperArchives)
	{
		// Table Name: UserRight_ArchivesSecrecy
		// Columns List,Can Used in SELECT SQL: ID,UserID,SecrecyID,RightFlag_ArchivesInfo,RightFlag_AttachedFile,RightFlag_PaperArchives
		// Columns List,Can Used in INSERT SQL: :ID,:UserID,:SecrecyID,:RightFlag_ArchivesInfo,:RightFlag_AttachedFile,:RightFlag_PaperArchives
		// Columns List,Can Used in UPDATE SQL: ID=:ID,UserID=:UserID,SecrecyID=:SecrecyID,RightFlag_ArchivesInfo=:RightFlag_ArchivesInfo,RightFlag_AttachedFile=:RightFlag_AttachedFile,RightFlag_PaperArchives=:RightFlag_PaperArchives

		this(userID, secrecyID,rightFlag_ArchivesInfo,rightFlag_AttachedFile,rightFlag_PaperArchives);
		this.iD = iD;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param userID �û����
	* @param secrecyID �����ܼ����
	* @param rightFlag_ArchivesInfo �ɲ鿴����������Ϣ��Ȩ�ޱ�־
	* @param rightFlag_AttachedFile �ɲ���ԭ�ĵ����ļ���Ȩ�ޱ�־
	* @param rightFlag_PaperArchives ������ʵ�ﵵ����Ȩ�ޱ�־
	*/
	public UserRightArchivesSecrecy(int userID,int secrecyID,boolean rightFlag_ArchivesInfo,boolean rightFlag_AttachedFile,boolean rightFlag_PaperArchives)
	{
		this.userID = userID;
		this.secrecyID = secrecyID;
		setRightFlag_ArchivesInfo(rightFlag_ArchivesInfo);
		setRightFlag_AttachedFile(rightFlag_AttachedFile);
		setRightFlag_PaperArchives(rightFlag_PaperArchives);
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��Ȩ��¼���
	* @param userID �û����
	* @param secrecyID �����ܼ����
	* @param rightFlag_ArchivesInfo �ɲ鿴����������Ϣ��Ȩ�ޱ�־
	* @param rightFlag_AttachedFile �ɲ���ԭ�ĵ����ļ���Ȩ�ޱ�־
	* @param rightFlag_PaperArchives ������ʵ�ﵵ����Ȩ�ޱ�־
	* @param name �����ܼ�����
	* @param remark ��ע
	*/
	public UserRightArchivesSecrecy(int iD,int userID,int secrecyID,boolean rightFlag_ArchivesInfo,boolean rightFlag_AttachedFile,boolean rightFlag_PaperArchives,String name,String remark)
	{
		this(iD,userID, secrecyID,rightFlag_ArchivesInfo,rightFlag_AttachedFile,rightFlag_PaperArchives);
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
	 * �û����
	 */
	private int userID=0;

	/**
	 * ��ȡ����ֵ���û����
	 * @return �û����
	 */
	public int getUserID()
	{
		return userID;
	}

	/**
	 * ��������ֵ���û����
	 * @param userID �û����
	 */
	public void setUserID(int userID)
	{
		this.userID = userID;
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
	public UserRightArchivesSecrecy clone()
	{
		UserRightArchivesSecrecy item = new UserRightArchivesSecrecy(iD, userID, secrecyID,getRightFlag_ArchivesInfo(),getRightFlag_AttachedFile(),getRightFlag_PaperArchives(), getName(), getRemark());
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param userRightArchivesSecrecy ָ���Ķ���Դ
	*/
	public void cloneFrom(UserRightArchivesSecrecy userRightArchivesSecrecy)
	{
		super.cloneFrom(userRightArchivesSecrecy);
		this.iD = userRightArchivesSecrecy.getID();
		this.userID = userRightArchivesSecrecy.getUserID();
		this.secrecyID = userRightArchivesSecrecy.getSecrecyID();
		this.keyInCol = userRightArchivesSecrecy.getKeyInCol();
		this.tag = userRightArchivesSecrecy.getTag();
	}

    
}



