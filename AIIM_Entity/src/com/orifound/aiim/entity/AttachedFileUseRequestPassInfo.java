package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * ԭ����������ͨ����Ϣ���ʵ����
 */
public class AttachedFileUseRequestPassInfo
{
    /**
     * ���캯��
     */
    public AttachedFileUseRequestPassInfo()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���뵥��ϸ���
	* @param archivesTypeID ����������
	* @param nBXH �����ڲ����
	* @param archivesID ����
	* @param title ����
	* @param userID �����˱��
	* @param expirationDate ��Ч��ֹ����
	*/
	public AttachedFileUseRequestPassInfo(int iD,int archivesTypeID,int nBXH,String archivesID,String title,int userID,Date expirationDate)
	{
		// Table Name: AttachedFileUseRequestPassInfo
		// Columns List,Can Used in SELECT SQL: ID,ArchivesTypeID,NBXH,ArchivesID,Title,UserID,ExpirationDate
		// Columns List,Can Used in INSERT SQL: :ID,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:UserID,:ExpirationDate
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,UserID=:UserID,ExpirationDate=:ExpirationDate

		this.iD = iD;
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.archivesID = archivesID;
		this.title = title;
		this.userID = userID;
		this.expirationDate = expirationDate;
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
	 * ���뵥��ϸ���
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ�����뵥��ϸ���
	 * @return ���뵥��ϸ���
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ�����뵥��ϸ���
	 * @param iD ���뵥��ϸ���
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
	 * �����ڲ����
	 */
	private int nBXH=0;

	/**
	 * ��ȡ����ֵ�������ڲ����
	 * @return �����ڲ����
	 */
	public int getNBXH()
	{
		return nBXH;
	}

	/**
	 * ��������ֵ�������ڲ����
	 * @param nBXH �����ڲ����
	 */
	public void setNBXH(int nBXH)
	{
		this.nBXH = nBXH;
	}

	/**
	 * ����
	 */
	private String archivesID=null;

	/**
	 * ��ȡ����ֵ������
	 * @return ����
	 */
	public String getArchivesID()
	{
		return archivesID;
	}

	/**
	 * ��������ֵ������
	 * @param archivesID ����
	 */
	public void setArchivesID(String archivesID)
	{
		this.archivesID = archivesID;
	}

	/**
	 * ����
	 */
	private String title=null;

	/**
	 * ��ȡ����ֵ������
	 * @return ����
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * ��������ֵ������
	 * @param title ����
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * �����˱��
	 */
	private int userID=0;

	/**
	 * ��ȡ����ֵ�������˱��
	 * @return �����˱��
	 */
	public int getUserID()
	{
		return userID;
	}

	/**
	 * ��������ֵ�������˱��
	 * @param userID �����˱��
	 */
	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	/**
	 * ��Ч��ֹ����
	 */
	private Date expirationDate;

	/**
	 * ��ȡ����ֵ����Ч��ֹ����
	 * @return ��Ч��ֹ����
	 */
	public Date getExpirationDate()
	{
		return expirationDate;
	}

	/**
	 * ��������ֵ����Ч��ֹ����
	 * @param expirationDate ��Ч��ֹ����
	 */
	public void setExpirationDate(Date expirationDate)
	{
		this.expirationDate = expirationDate;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public AttachedFileUseRequestPassInfo clone()
	{
		AttachedFileUseRequestPassInfo item = new AttachedFileUseRequestPassInfo(iD,archivesTypeID,nBXH,archivesID,title,userID,expirationDate);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param attachedFileUseRequestPassInfo ָ���Ķ���Դ
	*/
	public void cloneFrom(AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo)
	{
		this.iD = attachedFileUseRequestPassInfo.getID();
		this.archivesTypeID = attachedFileUseRequestPassInfo.getArchivesTypeID();
		this.nBXH = attachedFileUseRequestPassInfo.getNBXH();
		this.archivesID = attachedFileUseRequestPassInfo.getArchivesID();
		this.title = attachedFileUseRequestPassInfo.getTitle();
		this.userID = attachedFileUseRequestPassInfo.getUserID();
		this.expirationDate = attachedFileUseRequestPassInfo.getExpirationDate();
		this.keyInCol = attachedFileUseRequestPassInfo.getKeyInCol();
		this.tag = attachedFileUseRequestPassInfo.getTag();
	}


    
}



