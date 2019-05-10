package com.orifound.aiim.entity;

/**
 * ���˵ȼ��ֵ��	
 */
public class EvaluateLevel
{
    /**
     * ���캯��
     */
    public EvaluateLevel()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param name ���˵ȼ�����
	* @param remark ��ע
	*/
	public EvaluateLevel(int iD,String name,String remark)
	{
		// Table Name: DD_EvaluateLevel
		// Columns List,Can Used in SELECT SQL: ID,Name,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,Remark=:Remark

		this(name, remark);
		this.iD = iD;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param name ���˵ȼ�����
	* @param remark ��ע
	*/
	public EvaluateLevel(String name,String remark)
	{
		// Table Name: DD_EvaluateLevel
		// Columns List,Can Used in SELECT SQL: ID,Name,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,Remark=:Remark

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
	 * ���˵ȼ�����
	 */
	private String name=null;

	/**
	 * ��ȡ����ֵ�����˵ȼ�����
	 * @return ���˵ȼ�����
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * ��������ֵ�����˵ȼ�����
	 * @param name ���˵ȼ�����
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
	public EvaluateLevel clone()
	{
		EvaluateLevel item = new EvaluateLevel(iD,name,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param evaluateLevel ָ���Ķ���Դ
	*/
	public void cloneFrom(EvaluateLevel evaluateLevel)
	{
		this.iD = evaluateLevel.getID();
		this.name = evaluateLevel.getName();
		this.remark = evaluateLevel.getRemark();
		this.keyInCol = evaluateLevel.getKeyInCol();
		this.tag = evaluateLevel.getTag();
	}

//	/**
//	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
//	 * 
//	 */
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//	private class EvaluateLevelMapper implements RowMapper<EvaluateLevel>
//	{
//		
//		@Override
//		public EvaluateLevel mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			int iD = rs.getInt("ID");
//			String name = rs.getString("Name");
//			String remark = rs.getString("Remark");
//			
//			return new EvaluateLevel(iD,name,remark);
//		}
//	}

    
}



