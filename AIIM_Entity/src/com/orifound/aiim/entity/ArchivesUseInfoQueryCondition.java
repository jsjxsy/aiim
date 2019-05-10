/**
 * 
 */
package com.orifound.aiim.entity;

import java.util.Date;

/**
 * ����������Ϣ��ѯ����������
 *
 */
public class ArchivesUseInfoQueryCondition {

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
	 * �������÷�ʽ���
	 */
	private int useWayID=0;

	/**
	 * ��ȡ����ֵ���������÷�ʽ���
	 * @return �������÷�ʽ���
	 */
	public int getUseWayID()
	{
		return useWayID;
	}

	/**
	 * ��������ֵ���������÷�ʽ���
	 * @param useWayID �������÷�ʽ���
	 */
	public void setUseWayID(int useWayID)
	{
		this.useWayID = useWayID;
	}
	
	/**
	 * ��λ
	 */
	private String userDepartment=null;

	/**
	 * ��ȡ����ֵ����λ
	 * @return ��λ
	 */
	public String getUserDepartment()
	{
		return userDepartment;
	}

	/**
	 * ��������ֵ����λ
	 * @param userDepartment ��λ
	 */
	public void setUserDepartment(String userDepartment)
	{
		this.userDepartment = userDepartment;
	}

	/**
	 * ��ʵ����
	 */
	private String userRealName=null;

	/**
	 * ��ȡ����ֵ����ʵ����
	 * @return ��ʵ����
	 */
	public String getUserRealName()
	{
		return userRealName;
	}

	/**
	 * ��������ֵ����ʵ����
	 * @param userRealName ��ʵ����
	 */
	public void setUserRealName(String userRealName)
	{
		this.userRealName = userRealName;
	}
	
	/**
	 * ֤������
	 */
	private String iDCardNo=null;

	/**
	 * ��ȡ����ֵ��֤������
	 * @return ֤������
	 */
	public String getIDCardNo()
	{
		return iDCardNo;
	}

	/**
	 * ��������ֵ��֤������
	 * @param iDCardNo ֤������
	 */
	public void setIDCardNo(String iDCardNo)
	{
		this.iDCardNo = iDCardNo;
	}
	
	/**
	 * ����Ŀ�ı��
	 */
	private int purposeID=0;

	/**
	 * ��ȡ����ֵ������Ŀ�ı��
	 * @return ����Ŀ�ı��
	 */
	public int getPurposeID()
	{
		return purposeID;
	}

	/**
	 * ��������ֵ������Ŀ�ı��
	 * @param purposeID ����Ŀ�ı��
	 */
	public void setPurposeID(int purposeID)
	{
		this.purposeID = purposeID;
	}
	
	/**
	 * ��ʼ��������
	 */
	private Date useDateBegin;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public Date getUseDateBegin()
	{
		return useDateBegin;
	}

	/**
	 * ��������ֵ����������
	 * @param useDate ��������
	 */
	public void setUseDateBegin(Date useDateBegin)
	{
		this.useDateBegin = useDateBegin;
	}
	
	/**
	 * ��ֹ��������
	 */
	private Date useDateEnd;

	/**
	 * ��ȡ����ֵ����ֹ��������
	 * @return ��ֹ��������
	 */
	public Date getUseDateEnd()
	{
		return useDateEnd;
	}

	/**
	 * ��������ֵ����ֹ��������
	 * @param useDate ��ֹ��������
	 */
	public void setUseDateEnd(Date useDateEnd)
	{
		this.useDateEnd = useDateEnd;
	}

}
