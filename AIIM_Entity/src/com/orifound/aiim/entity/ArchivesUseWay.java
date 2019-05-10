package com.orifound.aiim.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * �������÷�ʽ�ֵ���ʵ����
 */
public class ArchivesUseWay
{
    /**
     * ���캯��
     */
    public ArchivesUseWay()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD �������÷�ʽ���
	* @param name ���÷�ʽ������
	* @param remark ��ע
	*/
	public ArchivesUseWay(int iD,String name,String remark)
	{
		// Table Name: DD_ArchivesUseWay
		// Columns List,Can Used in SELECT SQL: ID,Name,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,Remark=:Remark

		this.iD = iD;
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
	 * �������÷�ʽ���
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ���������÷�ʽ���
	 * @return �������÷�ʽ���
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ���������÷�ʽ���
	 * @param iD �������÷�ʽ���
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ���÷�ʽ������
	 */
	private String name=null;

	/**
	 * ��ȡ����ֵ�����÷�ʽ������
	 * @return ���÷�ʽ������
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * ��������ֵ�����÷�ʽ������
	 * @param name ���÷�ʽ������
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
	public ArchivesUseWay clone()
	{
		ArchivesUseWay item = new ArchivesUseWay(iD,name,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param archivesUseWay ָ���Ķ���Դ
	*/
	public void cloneFrom(ArchivesUseWay archivesUseWay)
	{
		this.iD = archivesUseWay.getID();
		this.name = archivesUseWay.getName();
		this.remark = archivesUseWay.getRemark();
		this.keyInCol = archivesUseWay.getKeyInCol();
		this.tag = archivesUseWay.getTag();
	}

	/**
	 * ��ȡ���÷�ʽ���赵/�鵵
	 * @return
	 */
	public List<ArchivesUseWay> getAllArchivesUseWay(){
		List<ArchivesUseWay> archivesUseWays = new ArrayList<ArchivesUseWay>();
		ArchivesUseWay archivesUseWay = new ArchivesUseWay(1,"�赵","");
		archivesUseWays.add(archivesUseWay);
		archivesUseWay = new ArchivesUseWay(2,"�鵵","");
		archivesUseWays.add(archivesUseWay);		
		return archivesUseWays;
	}

	
	/**
	 * ��ȡ���÷�ʽ���赵/�鵵/�鿴ԭ��
	 * @return
	 */
	public List<ArchivesUseWay> getAllArchivesUseWayEx(){
		List<ArchivesUseWay> archivesUseWays = new ArrayList<ArchivesUseWay>();
		ArchivesUseWay archivesUseWay = new ArchivesUseWay(1,"�赵","");
		archivesUseWays.add(archivesUseWay);
		archivesUseWay = new ArchivesUseWay(2,"�鵵","");
		archivesUseWays.add(archivesUseWay);
	    archivesUseWay = new ArchivesUseWay(3,"�鿴ԭ��","");
	    archivesUseWays.add(archivesUseWay);
		
		return archivesUseWays;
	}
//	/**
//	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
//	 * 
//	 */
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//	private class ArchivesUseWayMapper implements RowMapper<ArchivesUseWay>
//	{
//		
//		@Override
//		public ArchivesUseWay mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			int iD = rs.getInt("ID");
//			String name = rs.getString("Name");
//			String remark = rs.getString("Remark");
//			
//			return new ArchivesUseWay(iD,name,remark);
//		}
//	}

    
}



