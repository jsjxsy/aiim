package com.orifound.aiim.entity;

    
/**
 * ֤�������ֵ���ʵ����
 */
public class IDCardType
{
    /**
     * ���캯��
     */
    public IDCardType()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ֤�����ͱ��
	* @param name ֤������
	*/
	public IDCardType(int iD,String name)
	{
		// Table Name: DD_IDCardType
		// Columns List,Can Used in SELECT SQL: ID,Name
		// Columns List,Can Used in INSERT SQL: :ID,:Name
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name

		this(name);
		this.iD = iD;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ֤�����ͱ��
	* @param name ֤������
	*/
	public IDCardType(String name)
	{
		this.name = name;
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
	 * ֤�����ͱ��
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ��֤�����ͱ��
	 * @return ֤�����ͱ��
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ��֤�����ͱ��
	 * @param iD ֤�����ͱ��
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ֤������
	 */
	private String name=null;

	/**
	 * ��ȡ����ֵ��֤������
	 * @return ֤������
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * ��������ֵ��֤������
	 * @param name ֤������
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public IDCardType clone()
	{
		IDCardType item = new IDCardType(iD,name);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param iDCardType ָ���Ķ���Դ
	*/
	public void cloneFrom(IDCardType iDCardType)
	{
		this.iD = iDCardType.getID();
		this.name = iDCardType.getName();
	}

//	/**
//	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
//	 * 
//	 */
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//	private class IDCardTypeMapper implements RowMapper<IDCardType>
//	{
//		
//		@Override
//		public IDCardType mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			int iD = rs.getInt("ID");
//			String name = rs.getString("Name");
//			
//			return new IDCardType(iD,name);
//		}
//	}

    
}



