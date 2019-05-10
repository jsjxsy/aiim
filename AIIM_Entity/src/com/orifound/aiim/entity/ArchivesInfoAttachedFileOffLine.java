package com.orifound.aiim.entity;

import java.util.Date;
    
/**
 * �����ƽ���ԭ�ĵ����ļ���Ϣ���ʵ����
 */
public class ArchivesInfoAttachedFileOffLine
{
    /**
     * ���캯��
     */
    public ArchivesInfoAttachedFileOffLine()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param archivesTypeID ����������
	* @param nBXH ԭ�������ļ����ڲ����
	* @param oriFileName ԭʼ�ļ���
	* @param offLineFileSize �ļ���С
	* @param offLineTransDate �����ƽ�����
	* @param archivingFileName �鵵�ļ���
	* @param dealedFlag �����־
	* @param dealedDate ��������
	* @param remark ��ע
	*/
	public ArchivesInfoAttachedFileOffLine(int iD,int archivesTypeID,int nBXH,String oriFileName,long offLineFileSize,Date offLineTransDate,String archivingFileName,boolean dealedFlag,Date dealedDate,String remark)
	{
		// Table Name: ArchivesInfoAttachedFileOffLine
		// Columns List,Can Used in SELECT SQL: ID,ArchivesTypeID,NBXH,OriFileName,OffLineFileSize,OffLineTransDate,ArchivingFileName,DealedFlag,DealedDate,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:ArchivesTypeID,:NBXH,:OriFileName,:OffLineFileSize,:OffLineTransDate,:ArchivingFileName,:DealedFlag,:DealedDate,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,OriFileName=:OriFileName,OffLineFileSize=:OffLineFileSize,OffLineTransDate=:OffLineTransDate,ArchivingFileName=:ArchivingFileName,DealedFlag=:DealedFlag,DealedDate=:DealedDate,Remark=:Remark

		this(archivesTypeID, nBXH, oriFileName, offLineFileSize, archivingFileName);
		this.iD = iD;
		this.offLineTransDate = offLineTransDate;
		this.dealedFlag = dealedFlag;
		this.dealedDate = dealedDate;
		this.remark = remark;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param archivesTypeID ����������
	* @param nBXH ԭ�������ļ����ڲ����
	* @param oriFileName ԭʼ�ļ���
	* @param offLineFileSize �ļ���С
	* @param offLineTransDate �����ƽ�����
	* @param archivingFileName �鵵�ļ���
	* @param dealedFlag �����־
	* @param dealedDate ��������
	* @param remark ��ע
	*/
	public ArchivesInfoAttachedFileOffLine(int archivesTypeID,int nBXH,String oriFileName,long offLineFileSize,String archivingFileName)
	{
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.oriFileName = oriFileName;
		this.offLineFileSize = offLineFileSize;
		this.archivingFileName = archivingFileName;
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
	 * ԭʼ�ļ���
	 */
	private String oriFileName=null;

	/**
	 * ��ȡ����ֵ��ԭʼ�ļ���
	 * @return ԭʼ�ļ���
	 */
	public String getOriFileName()
	{
		return oriFileName;
	}

	/**
	 * ��������ֵ��ԭʼ�ļ���
	 * @param oriFileName ԭʼ�ļ���
	 */
	public void setOriFileName(String oriFileName)
	{
		this.oriFileName = oriFileName;
	}

	/**
	 * �ļ���С
	 */
	private long offLineFileSize;

	/**
	 * ��ȡ����ֵ���ļ���С
	 * @return �ļ���С
	 */
	public long getOffLineFileSize()
	{
		return offLineFileSize;
	}

	/**
	 * ��������ֵ���ļ���С
	 * @param offLineFileSize �ļ���С
	 */
	public void setOffLineFileSize(long offLineFileSize)
	{
		this.offLineFileSize = offLineFileSize;
	}

	/**
	 * �����ƽ�����
	 */
	private Date offLineTransDate;

	/**
	 * ��ȡ����ֵ�������ƽ�����
	 * @return �����ƽ�����
	 */
	public Date getOffLineTransDate()
	{
		return offLineTransDate;
	}

	/**
	 * ��������ֵ�������ƽ�����
	 * @param offLineTransDate �����ƽ�����
	 */
	public void setOffLineTransDate(Date offLineTransDate)
	{
		this.offLineTransDate = offLineTransDate;
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
	 * ��������
	 */
	private Date dealedDate;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public Date getDealedDate()
	{
		return dealedDate;
	}

	/**
	 * ��������ֵ����������
	 * @param dealedDate ��������
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
	public ArchivesInfoAttachedFileOffLine clone()
	{
		ArchivesInfoAttachedFileOffLine item = new ArchivesInfoAttachedFileOffLine(iD,archivesTypeID,nBXH,oriFileName,offLineFileSize,offLineTransDate,archivingFileName,dealedFlag,dealedDate,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param archivesInfoAttachedFileOffLine ָ���Ķ���Դ
	*/
	public void cloneFrom(ArchivesInfoAttachedFileOffLine archivesInfoAttachedFileOffLine)
	{
		this.iD = archivesInfoAttachedFileOffLine.getID();
		this.archivesTypeID = archivesInfoAttachedFileOffLine.getArchivesTypeID();
		this.nBXH = archivesInfoAttachedFileOffLine.getNBXH();
		this.oriFileName = archivesInfoAttachedFileOffLine.getOriFileName();
		this.offLineFileSize = archivesInfoAttachedFileOffLine.getOffLineFileSize();
		this.offLineTransDate = archivesInfoAttachedFileOffLine.getOffLineTransDate();
		this.archivingFileName = archivesInfoAttachedFileOffLine.getArchivingFileName();
		this.dealedFlag = archivesInfoAttachedFileOffLine.getDealedFlag();
		this.dealedDate = archivesInfoAttachedFileOffLine.getDealedDate();
		this.remark = archivesInfoAttachedFileOffLine.getRemark();
		this.keyInCol = archivesInfoAttachedFileOffLine.getKeyInCol();
		this.tag = archivesInfoAttachedFileOffLine.getTag();
	}

//	/**
//	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
//	 * 
//	 */
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//	private class ArchivesInfoAttachedFileOffLineMapper implements RowMapper<ArchivesInfoAttachedFileOffLine>
//	{
//		
//		@Override
//		public ArchivesInfoAttachedFileOffLine mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			int iD = rs.getInt("ID");
//			int archivesTypeID = rs.getInt("ArchivesTypeID");
//			int nBXH = rs.getInt("NBXH");
//			String oriFileName = rs.getString("OriFileName");
//			long offLineFileSize = rs.getLong("OffLineFileSize");
//			Date offLineTransDate = rs.getTimestamp("OffLineTransDate");
//			String archivingFileName = rs.getString("ArchivingFileName");
//			boolean dealedFlag = rs.getBoolean("DealedFlag");
//			Date dealedDate = rs.getTimestamp("DealedDate");
//			String remark = rs.getString("Remark");
//			
//			return new ArchivesInfoAttachedFileOffLine(iD,archivesTypeID,nBXH,oriFileName,offLineFileSize,offLineTransDate,archivingFileName,dealedFlag,dealedDate,remark);
//		}
//	}

    
}



