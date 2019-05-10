package com.orifound.aiim.entity; 
/**
 * ��������Ŀ��
 */
public class ArchivesUsePurpose
{
    /**
     * ���캯��
     */
    public ArchivesUsePurpose()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param purpose �鵵Ŀ��
	*/
	public ArchivesUsePurpose(int iD,String purpose)
	{
		// Table Name: DD_ArchivesUsePurpose
		// Columns List,Can Used in SELECT SQL: ID,Purpose
		// Columns List,Can Used in INSERT SQL: :ID,:Purpose
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Purpose=:Purpose

		this.iD = iD;
		this.purpose = purpose;
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
	 * �鵵Ŀ��
	 */
	private String purpose=null;

	/**
	 * ��ȡ����ֵ���鵵Ŀ��
	 * @return �鵵Ŀ��
	 */
	public String getPurpose()
	{
		return purpose;
	}

	/**
	 * ��������ֵ���鵵Ŀ��
	 * @param purpose �鵵Ŀ��
	 */
	public void setPurpose(String purpose)
	{
		this.purpose = purpose;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ArchivesUsePurpose clone()
	{
		ArchivesUsePurpose item = new ArchivesUsePurpose(iD,purpose);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param archivesUsePurpose ָ���Ķ���Դ
	*/
	public void cloneFrom(ArchivesUsePurpose archivesUsePurpose)
	{
		this.iD = archivesUsePurpose.getID();
		this.purpose = archivesUsePurpose.getPurpose();
		this.keyInCol = archivesUsePurpose.getKeyInCol();
		this.tag = archivesUsePurpose.getTag();
	}

//	/**
//	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
//	 * 
//	 */
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//	private class ArchivesUsePurposeMapper implements RowMapper<ArchivesUsePurpose>
//	{
//		
//		@Override
//		public ArchivesUsePurpose mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			int iD = rs.getInt("ID");
//			String purpose = rs.getString("Purpose");
//			
//			return new ArchivesUsePurpose(iD,purpose);
//		}
//	}

    
}



