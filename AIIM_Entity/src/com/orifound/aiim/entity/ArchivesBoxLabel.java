/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * �����б�ǩ������<br>
 * �����˺��ڵ�����ֹ��Χ����Ϣ
 */
public class ArchivesBoxLabel {
	
	/**
	 * ���캯��
	 */
	public ArchivesBoxLabel() {
		
	}
	
	/**
	 * �������Ĺ��캯��
	 * @param minArchivesID ��ʼ����
	 * @param maxArchivesID ��ֹ����
	 */
	public ArchivesBoxLabel(String minArchivesID,String maxArchivesID) {
		this.minArchivesID=minArchivesID;
		this.maxArchivesID=maxArchivesID;
	}
	
	/**
	 * ������ʼ����
	 */
	private String minArchivesID = "";

	/**
	 * ��������ֵ��������ʼ����
	 * @param minArchivesID ������ʼ����
	 */
	public void setMinArchivesID(String minArchivesID) {
		this.minArchivesID = minArchivesID;
	}

	/**
	 * ��ȡ����ֵ��������ʼ����
	 * @return ������ʼ����
	 */
	public String getMinArchivesID() {
		return minArchivesID;
	}

	/**
	 * ���ڽ�ֹ����
	 */
	private String maxArchivesID = "";

	/**
	 * ��������ֵ�����ڽ�ֹ����
	 * @param maxArchivesID ���ڽ�ֹ����
	 */
	public void setMaxArchivesID(String maxArchivesID) {
		this.maxArchivesID = maxArchivesID;
	}

	/**
	 * ��ȡ����ֵ�����ڽ�ֹ����
	 * @return ���ڽ�ֹ����
	 */
	public String getMaxArchivesID() {
		return maxArchivesID;
	}
	
	/**
	 * ���ڵ�������
	 */
	private int archivesCount = 0;

	/**
	 * ��������ֵ�����ڵ�������
	 * @param archivesCount ���ڵ�������
	 */
	public void setArchivesCount(int archivesCount)
	{
		this.archivesCount = archivesCount;
	}

	/**
	 * ��ȡ����ֵ�����ڵ�������
	 * @return ���ڵ�������
	 */
	public int getArchivesCount()
	{
		return archivesCount;
	}

	

	
}
