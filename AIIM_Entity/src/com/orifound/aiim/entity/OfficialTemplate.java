package com.orifound.aiim.entity;

import java.util.*;

/**
 * ����ģ����Ϣ��ʵ����
 */
public class OfficialTemplate {
	/**
	 * ���캯��
	 */
	public OfficialTemplate() {

	}

	/**
	 * ���ֶβ����Ĺ��캯��
	 * 
	 * @param iD
	 *            ģ����
	 * @param title
	 *            ����ģ������
	 * @param docType
	 *            ��������
	 * @param provider
	 *            ģ���ṩ��
	 * @param createDate
	 *            ��������
	 * @param fileName
	 *            �ļ���
	 * @param remark
	 *            ��ע
	 */
	public OfficialTemplate(int iD, String title, int docType, String provider, Date createDate, String fileName, String remark) {
		// Table Name: OfficialTemplate
		// Columns List,Can Used in SELECT SQL:
		// ID,Title,DocType,Provider,CreateDate,FileName,Remark
		// Columns List,Can Used in INSERT SQL:
		// :ID,:Title,:DocType,:Provider,:CreateDate,:FileName,:Remark
		// Columns List,Can Used in UPDATE SQL:
		// ID=:ID,Title=:Title,DocType=:DocType,Provider=:Provider,CreateDate=:CreateDate,FileName=:FileName,Remark=:Remark

		this.iD = iD;
		this.title = title;
		this.docType = docType;
		this.provider = provider;
		this.createDate = createDate;
		this.fileName = fileName;
		this.remark = remark;
	}

	/**
	 * ��Ա�����ڼ����еĹؼ���
	 */
	private Object keyInCol = null;

	/**
	 * ��ȡ����ֵ����Ա�����ڼ����еĹؼ���
	 * 
	 * @return ��Ա�����ڼ����еĹؼ���
	 */
	public Object getKeyInCol() {
		return keyInCol;
	}

	/**
	 * ��������ֵ����Ա�����ڼ����еĹؼ���
	 * 
	 * @param keyInCol
	 *            ��Ա�����ڼ����еĹؼ���
	 */
	public void setKeyInCol(Object keyInCol) {
		this.keyInCol = keyInCol;
	}

	/**
	 * ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	private Object tag = null;

	/**
	 * ��ȡ����ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * 
	 * @return ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public Object getTag() {
		return tag;
	}

	/**
	 * ��������ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * 
	 * @param tag
	 *            ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public void setTag(Object tag) {
		this.tag = tag;
	}

	/**
	 * ģ����
	 */
	private int iD = 0;

	/**
	 * ��ȡ����ֵ��ģ����
	 * 
	 * @return ģ����
	 */
	public int getID() {
		return iD;
	}

	/**
	 * ��������ֵ��ģ����
	 * 
	 * @param iD
	 *            ģ����
	 */
	public void setID(int iD) {
		this.iD = iD;
	}

	/**
	 * ����ģ������
	 */
	private String title = null;

	/**
	 * ��ȡ����ֵ������ģ������
	 * 
	 * @return ����ģ������
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * ��������ֵ������ģ������
	 * 
	 * @param title
	 *            ����ģ������
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * ��������
	 */
	private int docType = 0;

	/**
	 * ��ȡ����ֵ����������
	 * 
	 * @return ��������
	 */
	public int getDocType() {
		return docType;
	}

	/**
	 * ��������ֵ����������
	 * 
	 * @param docType
	 *            ��������
	 */
	public void setDocType(int docType) {
		this.docType = docType;
	}

	/**
	 * ģ���ṩ��
	 */
	private String provider = null;

	/**
	 * ��ȡ����ֵ��ģ���ṩ��
	 * 
	 * @return ģ���ṩ��
	 */
	public String getProvider() {
		return provider;
	}

	/**
	 * ��������ֵ��ģ���ṩ��
	 * 
	 * @param provider
	 *            ģ���ṩ��
	 */
	public void setProvider(String provider) {
		this.provider = provider;
	}

	/**
	 * ��������
	 */
	private Date createDate;

	/**
	 * ��ȡ����ֵ����������
	 * 
	 * @return ��������
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * ��������ֵ����������
	 * 
	 * @param createDate
	 *            ��������
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * �ļ���
	 */
	private String fileName = null;

	/**
	 * ��ȡ����ֵ���ļ���
	 * 
	 * @return �ļ���
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * ��������ֵ���ļ���
	 * 
	 * @param fileName
	 *            �ļ���
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * ��ע
	 */
	private String remark = null;

	/**
	 * ��ȡ����ֵ����ע
	 * 
	 * @return ��ע
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * ��������ֵ����ע
	 * 
	 * @param remark
	 *            ��ע
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * clone
	 * 
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public OfficialTemplate clone() {
		OfficialTemplate item = new OfficialTemplate(iD, title, docType, provider, createDate, fileName, remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	 * ��ָ�������¡��������������ֵ
	 * 
	 * @param officialTemplate
	 *            ָ���Ķ���Դ
	 */
	public void cloneFrom(OfficialTemplate officialTemplate) {
		this.iD = officialTemplate.getID();
		this.title = officialTemplate.getTitle();
		this.docType = officialTemplate.getDocType();
		this.provider = officialTemplate.getProvider();
		this.createDate = officialTemplate.getCreateDate();
		this.fileName = officialTemplate.getFileName();
		this.remark = officialTemplate.getRemark();
		this.keyInCol = officialTemplate.getKeyInCol();
		this.tag = officialTemplate.getTag();
	}



}
