package com.orifound.aiim.entity;

import java.util.Date;
    
/**
 * ԭ�ĵ����ļ�ɾ����Ϣ���ʵ����
 */
public class ArchivesInfoAttachedFileDeleted
{
    /**
     * ���캯��
     */
    public ArchivesInfoAttachedFileDeleted()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ԭ�ı��
	* @param archivesTypeID ����������
	* @param nBXH ԭ�������ļ����ڲ����
	* @param title ԭ�ı���
	* @param archivingFileName �鵵�ļ���
	* @param dealedFlag �����־
	* @param dealedDate ɾ������
	* @param remark ��ע
	*/
	public ArchivesInfoAttachedFileDeleted(int iD,int archivesTypeID,int nBXH,String title,String archivingFileName,boolean dealedFlag,Date dealedDate,String remark)
	{
		// Table Name: ArchivesInfoAttachedFileDeleted
		// Columns List,Can Used in SELECT SQL: ID,ArchivesTypeID,NBXH,Title,ArchivingFileName,DealedFlag,DealedDate,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:ArchivesTypeID,:NBXH,:Title,:ArchivingFileName,:DealedFlag,:DealedDate,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,Title=:Title,ArchivingFileName=:ArchivingFileName,DealedFlag=:DealedFlag,DealedDate=:DealedDate,Remark=:Remark

		this(archivesTypeID, nBXH, title, archivingFileName, remark);
		this.iD = iD;
		this.dealedFlag = dealedFlag;
		this.dealedDate = dealedDate;
	}
	
    /**
	* ���ֶβ����Ĺ��캯��
	* @param archivesTypeID ����������
	* @param nBXH ԭ�������ļ����ڲ����
	* @param title ԭ�ı���
	* @param archivingFileName �鵵�ļ���
	* @param remark ��ע
	*/
	public ArchivesInfoAttachedFileDeleted(int archivesTypeID,int nBXH,String title,String archivingFileName,String remark)
	{
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.title = title;
		this.archivingFileName = archivingFileName;
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
	 * ԭ�ı��
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ��ԭ�ı��
	 * @return ԭ�ı��
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ��ԭ�ı��
	 * @param iD ԭ�ı��
	 */
	public void setID(int iD)
	{
		this.iD = iD;
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
	 * ԭ�������ļ����ڲ����
	 */
	private int nBXH=0;

	/**
	 * ��ȡ����ֵ��ԭ�������ļ����ڲ����
	 * @return ԭ�������ļ����ڲ����
	 */
	public int getNBXH()
	{
		return nBXH;
	}

	/**
	 * ��������ֵ��ԭ�������ļ����ڲ����
	 * @param nBXH ԭ�������ļ����ڲ����
	 */
	public void setNBXH(int nBXH)
	{
		this.nBXH = nBXH;
	}

	/**
	 * ԭ�ı���
	 */
	private String title=null;

	/**
	 * ��ȡ����ֵ��ԭ�ı���
	 * @return ԭ�ı���
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * ��������ֵ��ԭ�ı���
	 * @param title ԭ�ı���
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * �鵵�ļ���
	 */
	private String archivingFileName=null;

	/**
	 * ��ȡ����ֵ���鵵�ļ���
	 * @return �鵵�ļ���
	 */
	public String getArchivingFileName()
	{
		return archivingFileName;
	}

	/**
	 * ��������ֵ���鵵�ļ���
	 * @param archivingFileName �鵵�ļ���
	 */
	public void setArchivingFileName(String archivingFileName)
	{
		this.archivingFileName = archivingFileName;
	}

	/**
	 * �����־
	 */
	private boolean dealedFlag=false;

	/**
	 * ��ȡ����ֵ�������־
	 * @return �����־
	 */
	public boolean getDealedFlag()
	{
		return dealedFlag;
	}

	/**
	 * ��������ֵ�������־
	 * @param dealedFlag �����־
	 */
	public void setDealedFlag(boolean dealedFlag)
	{
		this.dealedFlag = dealedFlag;
	}

	/**
	 * ɾ������
	 */
	private Date dealedDate;

	/**
	 * ��ȡ����ֵ��ɾ������
	 * @return ɾ������
	 */
	public Date getDealedDate()
	{
		return dealedDate;
	}

	/**
	 * ��������ֵ��ɾ������
	 * @param dealedDate ɾ������
	 */
	public void setDealedDate(Date dealedDate)
	{
		this.dealedDate = dealedDate;
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
	public ArchivesInfoAttachedFileDeleted clone()
	{
		ArchivesInfoAttachedFileDeleted item = new ArchivesInfoAttachedFileDeleted(iD,archivesTypeID,nBXH,title,archivingFileName,dealedFlag,dealedDate,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param archivesInfoAttachedFileDeleted ָ���Ķ���Դ
	*/
	public void cloneFrom(ArchivesInfoAttachedFileDeleted archivesInfoAttachedFileDeleted)
	{
		this.iD = archivesInfoAttachedFileDeleted.getID();
		this.archivesTypeID = archivesInfoAttachedFileDeleted.getArchivesTypeID();
		this.nBXH = archivesInfoAttachedFileDeleted.getNBXH();
		this.title = archivesInfoAttachedFileDeleted.getTitle();
		this.archivingFileName = archivesInfoAttachedFileDeleted.getArchivingFileName();
		this.dealedFlag = archivesInfoAttachedFileDeleted.getDealedFlag();
		this.dealedDate = archivesInfoAttachedFileDeleted.getDealedDate();
		this.remark = archivesInfoAttachedFileDeleted.getRemark();
		this.keyInCol = archivesInfoAttachedFileDeleted.getKeyInCol();
		this.tag = archivesInfoAttachedFileDeleted.getTag();
	}

//	/**
//	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
//	 * 
//	 */
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//	private class ArchivesInfoAttachedFileDeletedMapper implements RowMapper<ArchivesInfoAttachedFileDeleted>
//	{
//		
//		@Override
//		public ArchivesInfoAttachedFileDeleted mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			int iD = rs.getInt("ID");
//			int archivesTypeID = rs.getInt("ArchivesTypeID");
//			int nBXH = rs.getInt("NBXH");
//			String title = rs.getString("Title");
//			String archivingFileName = rs.getString("ArchivingFileName");
//			boolean dealedFlag = rs.getBoolean("DealedFlag");
//			Date dealedDate = rs.getTimestamp("DealedDate");
//			String remark = rs.getString("Remark");
//			
//			return new ArchivesInfoAttachedFileDeleted(iD,archivesTypeID,nBXH,title,archivingFileName,dealedFlag,dealedDate,remark);
//		}
//	}

    
}



