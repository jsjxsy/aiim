package com.orifound.aiim.entity;

    
/**
 * ��ǰ�������Ϣ���ʵ����
 */
public class CurrentContentID
{
    /**
     * ���캯��
     */
    public CurrentContentID()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param archivesIDPrefix ����ǰ׺
	* @param contentID ��ǰ�����
	*/
	public CurrentContentID(String archivesIDPrefix,int contentID)
	{
		// Table Name: CurrentContentID
		// Columns List,Can Used in SELECT SQL: ArchivesIDPrefix,ContentID
		// Columns List,Can Used in INSERT SQL: :ArchivesIDPrefix,:ContentID
		// Columns List,Can Used in UPDATE SQL: ArchivesIDPrefix=:ArchivesIDPrefix,ContentID=:ContentID

		this.archivesIDPrefix = archivesIDPrefix;
		this.contentID = contentID;
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
	 * ����ǰ׺
	 */
	private String archivesIDPrefix=null;

	/**
	 * ��ȡ����ֵ������ǰ׺
	 * @return ����ǰ׺
	 */
	public String getArchivesIDPrefix()
	{
		return archivesIDPrefix;
	}

	/**
	 * ��������ֵ������ǰ׺
	 * @param archivesIDPrefix ����ǰ׺
	 */
	public void setArchivesIDPrefix(String archivesIDPrefix)
	{
		this.archivesIDPrefix = archivesIDPrefix;
	}

	/**
	 * ��ǰ�����
	 */
	private int contentID=0;

	/**
	 * ��ȡ����ֵ����ǰ�����
	 * @return ��ǰ�����
	 */
	public int getContentID()
	{
		return contentID;
	}

	/**
	 * ��������ֵ����ǰ�����
	 * @param contentID ��ǰ�����
	 */
	public void setContentID(int contentID)
	{
		this.contentID = contentID;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public CurrentContentID clone()
	{
		CurrentContentID item = new CurrentContentID(archivesIDPrefix,contentID);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param currentContentID ָ���Ķ���Դ
	*/
	public void cloneFrom(CurrentContentID currentContentID)
	{
		this.archivesIDPrefix = currentContentID.getArchivesIDPrefix();
		this.contentID = currentContentID.getContentID();
		this.keyInCol = currentContentID.getKeyInCol();
		this.tag = currentContentID.getTag();
	}

//	/**
//	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
//	 * 
//	 */
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//	private class CurrentContentIDMapper implements RowMapper<CurrentContentID>
//	{
//		
//		@Override
//		public CurrentContentID mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			String archivesIDPrefix = rs.getString("ArchivesIDPrefix");
//			int contentID = rs.getInt("ContentID");
//			
//			return new CurrentContentID(archivesIDPrefix,contentID);
//		}
//	}

    
}



