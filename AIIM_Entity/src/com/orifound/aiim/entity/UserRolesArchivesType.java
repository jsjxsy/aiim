package com.orifound.aiim.entity;

    
/**
 * �û���ɫ�ĵ�������Ȩ�ޱ�ʵ����
 */
public class UserRolesArchivesType
{
    /**
     * ���캯��
     */
    public UserRolesArchivesType()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param rolesID �û���ɫ���
	* @param archivesTypeID ����������
	* @param rightFlag_ArchivesInfo �ɲ鿴����������Ϣ��Ȩ�ޱ�־
	* @param rightFlag_AttachedFile �ɲ���ԭ�ĵ����ļ���Ȩ�ޱ�־
	* @param rightFlag_PaperArchives ������ʵ�ﵵ����Ȩ�ޱ�־
	*/
	public UserRolesArchivesType(int iD,int rolesID,int archivesTypeID,boolean rightFlag_ArchivesInfo,boolean rightFlag_AttachedFile,boolean rightFlag_PaperArchives)
	{
		// Table Name: DDR_UserRoles_ArchivesType
		// Columns List,Can Used in SELECT SQL: ID,RolesID,ArchivesTypeID,RightFlag_ArchivesInfo,RightFlag_AttachedFile,RightFlag_PaperArchives
		// Columns List,Can Used in INSERT SQL: :ID,:RolesID,:ArchivesTypeID,:RightFlag_ArchivesInfo,:RightFlag_AttachedFile,:RightFlag_PaperArchives
		// Columns List,Can Used in UPDATE SQL: ID=:ID,RolesID=:RolesID,ArchivesTypeID=:ArchivesTypeID,RightFlag_ArchivesInfo=:RightFlag_ArchivesInfo,RightFlag_AttachedFile=:RightFlag_AttachedFile,RightFlag_PaperArchives=:RightFlag_PaperArchives

		this.iD = iD;
		this.rolesID = rolesID;
		this.archivesTypeID = archivesTypeID;
		this.rightFlag_ArchivesInfo = rightFlag_ArchivesInfo;
		this.rightFlag_AttachedFile = rightFlag_AttachedFile;
		this.rightFlag_PaperArchives = rightFlag_PaperArchives;
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
	 * �ɲ鿴����������Ϣ��Ȩ�ޱ�־
	 */
	private boolean rightFlag_ArchivesInfo=false;

	/**
	 * ��ȡ����ֵ���ɲ鿴����������Ϣ��Ȩ�ޱ�־
	 * @return �ɲ鿴����������Ϣ��Ȩ�ޱ�־
	 */
	public boolean getRightFlag_ArchivesInfo()
	{
		return rightFlag_ArchivesInfo;
	}

	/**
	 * ��������ֵ���ɲ鿴����������Ϣ��Ȩ�ޱ�־
	 * @param rightFlag_ArchivesInfo �ɲ鿴����������Ϣ��Ȩ�ޱ�־
	 */
	public void setRightFlag_ArchivesInfo(boolean rightFlag_ArchivesInfo)
	{
		this.rightFlag_ArchivesInfo = rightFlag_ArchivesInfo;
	}

	/**
	 * �ɲ���ԭ�ĵ����ļ���Ȩ�ޱ�־
	 */
	private boolean rightFlag_AttachedFile=false;

	/**
	 * ��ȡ����ֵ���ɲ���ԭ�ĵ����ļ���Ȩ�ޱ�־
	 * @return �ɲ���ԭ�ĵ����ļ���Ȩ�ޱ�־
	 */
	public boolean getRightFlag_AttachedFile()
	{
		return rightFlag_AttachedFile;
	}

	/**
	 * ��������ֵ���ɲ���ԭ�ĵ����ļ���Ȩ�ޱ�־
	 * @param rightFlag_AttachedFile �ɲ���ԭ�ĵ����ļ���Ȩ�ޱ�־
	 */
	public void setRightFlag_AttachedFile(boolean rightFlag_AttachedFile)
	{
		this.rightFlag_AttachedFile = rightFlag_AttachedFile;
	}

	/**
	 * ������ʵ�ﵵ����Ȩ�ޱ�־
	 */
	private boolean rightFlag_PaperArchives=false;

	/**
	 * ��ȡ����ֵ��������ʵ�ﵵ����Ȩ�ޱ�־
	 * @return ������ʵ�ﵵ����Ȩ�ޱ�־
	 */
	public boolean getRightFlag_PaperArchives()
	{
		return rightFlag_PaperArchives;
	}

	/**
	 * ��������ֵ��������ʵ�ﵵ����Ȩ�ޱ�־
	 * @param rightFlag_PaperArchives ������ʵ�ﵵ����Ȩ�ޱ�־
	 */
	public void setRightFlag_PaperArchives(boolean rightFlag_PaperArchives)
	{
		this.rightFlag_PaperArchives = rightFlag_PaperArchives;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public UserRolesArchivesType clone()
	{
		UserRolesArchivesType item = new UserRolesArchivesType(iD,rolesID,archivesTypeID,rightFlag_ArchivesInfo,rightFlag_AttachedFile,rightFlag_PaperArchives);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param userRolesArchivesType ָ���Ķ���Դ
	*/
	public void cloneFrom(UserRolesArchivesType userRolesArchivesType)
	{
		this.iD = userRolesArchivesType.getID();
		this.rolesID = userRolesArchivesType.getRolesID();
		this.archivesTypeID = userRolesArchivesType.getArchivesTypeID();
		this.rightFlag_ArchivesInfo = userRolesArchivesType.getRightFlag_ArchivesInfo();
		this.rightFlag_AttachedFile = userRolesArchivesType.getRightFlag_AttachedFile();
		this.rightFlag_PaperArchives = userRolesArchivesType.getRightFlag_PaperArchives();
		this.keyInCol = userRolesArchivesType.getKeyInCol();
		this.tag = userRolesArchivesType.getTag();
	}


//	/**
//	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
//	 * 
//	 */
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//	private class UserRolesArchivesTypeMapper implements RowMapper<UserRolesArchivesType>
//	{
//		
//		@Override
//		public UserRolesArchivesType mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			int iD = rs.getInt("ID");
//			int rolesID = rs.getInt("RolesID");
//			int archivesTypeID = rs.getInt("ArchivesTypeID");
//			boolean rightFlag_ArchivesInfo = rs.getBoolean("RightFlag_ArchivesInfo");
//			boolean rightFlag_AttachedFile = rs.getBoolean("RightFlag_AttachedFile");
//			boolean rightFlag_PaperArchives = rs.getBoolean("RightFlag_PaperArchives");
//			
//			return new UserRolesArchivesType(iD,rolesID,archivesTypeID,rightFlag_ArchivesInfo,rightFlag_AttachedFile,rightFlag_PaperArchives);
//		}
//	}


}
