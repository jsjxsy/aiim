/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * �ⷿ������Ϣ��ѯ����������
 *
 */
public class StoreroomArchivesInfoQueryCondition {

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
	 * ��������
	 */
	private String archivesBarcode=null;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public String getArchivesBarcode()
	{
		return archivesBarcode;
	}

	/**
	 * ��������ֵ����������
	 * @param archivesBarcode ��������
	 */
	public void setArchivesBarcode(String archivesBarcode)
	{
		this.archivesBarcode = archivesBarcode;
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
}
