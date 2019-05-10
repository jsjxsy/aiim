package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * ������Ϣԭ�ı��ʵ����
 */
public class ArchivesInfoAttachedFile
{
    /**
     * ���캯��
     */
    public ArchivesInfoAttachedFile()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ԭ�ı��
	* @param archivesTypeID ����������
	* @param nBXH ԭ�������ļ����ڲ����
	* @param orderID ����
	* @param title ԭ�ı���
	* @param oriFileName ԭʼ�ļ���
	* @param originalSize ԭʼ�ļ���С
	* @param originalType ԭʼ�ļ�����
	* @param attachedTime ����ԭ�ĵ�ʱ��
	* @param fetchFullTextRequest Ҫ����ȡȫ�ĵı�־
	* @param archivingFileName �鵵�ļ���
	* @param resaveFileName ����ļ���
	* @param resaveSize ����ļ���С
	* @param resaveType ����ļ�����
	* @param resaveTime ���ʱ��
	* @param deleteFlag ɾ����־
	* @param remark ��ע
	*/
	public ArchivesInfoAttachedFile(int iD,int archivesTypeID,int nBXH,int orderID,String title,String oriFileName,double originalSize,String originalType,Date attachedTime,int fetchFullTextRequest,String archivingFileName,String resaveFileName,double resaveSize,String resaveType,Date resaveTime,boolean deleteFlag,String remark)
	{
		// Table Name: ArchivesInfoAttachedFile_TypeCode
		// Columns List,Can Used in SELECT SQL: ID,ArchivesTypeID,NBXH,OrderID,Title,OriFileName,OriginalSize,OriginalType,AttachedTime,FetchFullTextRequest,ArchivingFileName,ResaveFileName,ResaveSize,ResaveType,ResaveTime,DeleteFlag,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:ArchivesTypeID,:NBXH,:OrderID,:Title,:OriFileName,:OriginalSize,:OriginalType,:AttachedTime,:FetchFullTextRequest,:ArchivingFileName,:ResaveFileName,:ResaveSize,:ResaveType,:ResaveTime,:DeleteFlag,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,OrderID=:OrderID,Title=:Title,OriFileName=:OriFileName,OriginalSize=:OriginalSize,OriginalType=:OriginalType,AttachedTime=:AttachedTime,FetchFullTextRequest=:FetchFullTextRequest,ArchivingFileName=:ArchivingFileName,ResaveFileName=:ResaveFileName,ResaveSize=:ResaveSize,ResaveType=:ResaveType,ResaveTime=:ResaveTime,DeleteFlag=:DeleteFlag,Remark=:Remark

		this.iD = iD;
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.orderID = orderID;
		this.title = title;
		this.oriFileName = oriFileName;
		this.originalSize = originalSize;
		this.originalType = originalType;
		this.attachedTime = attachedTime;
		this.fetchFullTextRequest = fetchFullTextRequest;
		this.archivingFileName = archivingFileName;
		this.resaveFileName = resaveFileName;
		this.resaveSize = resaveSize;
		this.resaveType = resaveType;
		this.resaveTime = resaveTime;
		this.deleteFlag = deleteFlag;
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
	 * ����
	 */
	private int orderID=0;

	/**
	 * ��ȡ����ֵ������
	 * @return ����
	 */
	public int getOrderID()
	{
		return orderID;
	}

	/**
	 * ��������ֵ������
	 * @param orderID ����
	 */
	public void setOrderID(int orderID)
	{
		this.orderID = orderID;
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
	 * ԭʼ�ļ���С
	 */
	private double originalSize;

	/**
	 * ��ȡ����ֵ��ԭʼ�ļ���С
	 * @return ԭʼ�ļ���С
	 */
	public double getOriginalSize()
	{
		return originalSize;
	}

	/**
	 * ��������ֵ��ԭʼ�ļ���С
	 * @param originalSize ԭʼ�ļ���С
	 */
	public void setOriginalSize(double originalSize)
	{
		this.originalSize = originalSize;
	}

	/**
	 * ԭʼ�ļ�����
	 */
	private String originalType=null;

	/**
	 * ��ȡ����ֵ��ԭʼ�ļ�����
	 * @return ԭʼ�ļ�����
	 */
	public String getOriginalType()
	{
		return originalType;
	}

	/**
	 * ��������ֵ��ԭʼ�ļ�����
	 * @param originalType ԭʼ�ļ�����
	 */
	public void setOriginalType(String originalType)
	{
		this.originalType = originalType;
	}

	/**
	 * ����ԭ�ĵ�ʱ��
	 */
	private Date attachedTime;

	/**
	 * ��ȡ����ֵ������ԭ�ĵ�ʱ��
	 * @return ����ԭ�ĵ�ʱ��
	 */
	public Date getAttachedTime()
	{
		return new java.sql.Date(attachedTime.getTime());
	}

	/**
	 * ��������ֵ������ԭ�ĵ�ʱ��
	 * @param attachedTime ����ԭ�ĵ�ʱ��
	 */
	public void setAttachedTime(Date attachedTime)
	{
		this.attachedTime = attachedTime;
	}

	/**
	 * Ҫ����ȡȫ�ĵı�־
	 */
	private int fetchFullTextRequest=0;

	/**
	 * ��ȡ����ֵ��Ҫ����ȡȫ�ĵı�־
	 * @return Ҫ����ȡȫ�ĵı�־
	 */
	public int getFetchFullTextRequest()
	{
		return fetchFullTextRequest;
	}

	/**
	 * ��������ֵ��Ҫ����ȡȫ�ĵı�־
	 * @param fetchFullTextRequest Ҫ����ȡȫ�ĵı�־
	 */
	public void setFetchFullTextRequest(int fetchFullTextRequest)
	{
		this.fetchFullTextRequest = fetchFullTextRequest;
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
	 * ����ļ���
	 */
	private String resaveFileName=null;

	/**
	 * ��ȡ����ֵ������ļ���
	 * @return ����ļ���
	 */
	public String getResaveFileName()
	{
		return resaveFileName;
	}

	/**
	 * ��������ֵ������ļ���
	 * @param resaveFileName ����ļ���
	 */
	public void setResaveFileName(String resaveFileName)
	{
		this.resaveFileName = resaveFileName;
	}

	/**
	 * ����ļ���С
	 */
	private double resaveSize;

	/**
	 * ��ȡ����ֵ������ļ���С
	 * @return ����ļ���С
	 */
	public double getResaveSize()
	{
		return resaveSize;
	}

	/**
	 * ��������ֵ������ļ���С
	 * @param resaveSize ����ļ���С
	 */
	public void setResaveSize(double resaveSize)
	{
		this.resaveSize = resaveSize;
	}

	/**
	 * ����ļ�����
	 */
	private String resaveType=null;

	/**
	 * ��ȡ����ֵ������ļ�����
	 * @return ����ļ�����
	 */
	public String getResaveType()
	{
		return resaveType;
	}

	/**
	 * ��������ֵ������ļ�����
	 * @param resaveType ����ļ�����
	 */
	public void setResaveType(String resaveType)
	{
		this.resaveType = resaveType;
	}

	/**
	 * ���ʱ��
	 */
	private Date resaveTime;

	/**
	 * ��ȡ����ֵ�����ʱ��
	 * @return ���ʱ��
	 */
	public Date getResaveTime()
	{
		
		return resaveTime;
	}

	/**
	 * ��������ֵ�����ʱ��
	 * @param resaveTime ���ʱ��
	 */
	public void setResaveTime(Date resaveTime)
	{
		this.resaveTime = resaveTime;
	}

	/**
	 * ɾ����־
	 */
	private boolean deleteFlag=false;

	/**
	 * ��ȡ����ֵ��ɾ����־
	 * @return ɾ����־
	 */
	public boolean getDeleteFlag()
	{
		return deleteFlag;
	}

	/**
	 * ��������ֵ��ɾ����־
	 * @param deleteFlag ɾ����־
	 */
	public void setDeleteFlag(boolean deleteFlag)
	{
		this.deleteFlag = deleteFlag;
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
	public ArchivesInfoAttachedFile clone()
	{
		ArchivesInfoAttachedFile item = new ArchivesInfoAttachedFile(iD,archivesTypeID,nBXH,orderID,title,oriFileName,originalSize,originalType,attachedTime,fetchFullTextRequest,archivingFileName,resaveFileName,resaveSize,resaveType,resaveTime,deleteFlag,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param archivesInfoAttachedFile ָ���Ķ���Դ
	*/
	public void cloneFrom(ArchivesInfoAttachedFile archivesInfoAttachedFile)
	{
		this.iD = archivesInfoAttachedFile.getID();
		this.archivesTypeID = archivesInfoAttachedFile.getArchivesTypeID();
		this.nBXH = archivesInfoAttachedFile.getNBXH();
		this.orderID = archivesInfoAttachedFile.getOrderID();
		this.title = archivesInfoAttachedFile.getTitle();
		this.oriFileName = archivesInfoAttachedFile.getOriFileName();
		this.originalSize = archivesInfoAttachedFile.getOriginalSize();
		this.originalType = archivesInfoAttachedFile.getOriginalType();
		this.attachedTime = archivesInfoAttachedFile.getAttachedTime();
		this.fetchFullTextRequest = archivesInfoAttachedFile.getFetchFullTextRequest();
		this.archivingFileName = archivesInfoAttachedFile.getArchivingFileName();
		this.resaveFileName = archivesInfoAttachedFile.getResaveFileName();
		this.resaveSize = archivesInfoAttachedFile.getResaveSize();
		this.resaveType = archivesInfoAttachedFile.getResaveType();
		this.resaveTime = archivesInfoAttachedFile.getResaveTime();
		this.deleteFlag = archivesInfoAttachedFile.getDeleteFlag();
		this.remark = archivesInfoAttachedFile.getRemark();
		this.keyInCol = archivesInfoAttachedFile.getKeyInCol();
		this.tag = archivesInfoAttachedFile.getTag();
	}

//	/**
//	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
//	 * 
//	 */
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//	private class ArchivesInfoAttachedFileMapper implements RowMapper<ArchivesInfoAttachedFile>
//	{
//		
//		@Override
//		public ArchivesInfoAttachedFile mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			int iD = rs.getInt("ID");
//			int archivesTypeID = rs.getInt("ArchivesTypeID");
//			int nBXH = rs.getInt("NBXH");
//			int orderID = rs.getInt("OrderID");
//			String title = rs.getString("Title");
//			String oriFileName = rs.getString("OriFileName");
//			long originalSize = rs.getLong("OriginalSize");
//			String originalType = rs.getString("OriginalType");
//			Date attachedTime = rs.getTimestamp("AttachedTime");
//			int fetchFullTextRequest = rs.getBoolean("FetchFullTextRequest");
//			String archivingFileName = rs.getString("ArchivingFileName");
//			String resaveFileName = rs.getString("ResaveFileName");
//			long resaveSize = rs.getLong("ResaveSize");
//			String resaveType = rs.getString("ResaveType");
//			Date resaveTime = rs.getTimestamp("ResaveTime");
//			boolean deleteFlag = rs.getBoolean("DeleteFlag");
//			String remark = rs.getString("Remark");
//			
//			return new ArchivesInfoAttachedFile(iD,archivesTypeID,nBXH,orderID,title,oriFileName,originalSize,originalType,attachedTime,fetchFullTextRequest,archivingFileName,resaveFileName,resaveSize,resaveType,resaveTime,deleteFlag,remark);
//		}
//	}

    
}



