package com.orifound.aiim.entity;

import java.util.*;

/**
 * 公文模板信息表实体类
 */
public class OfficialTemplate {
	/**
	 * 构造函数
	 */
	public OfficialTemplate() {

	}

	/**
	 * 带字段参数的构造函数
	 * 
	 * @param iD
	 *            模板编号
	 * @param title
	 *            公文模板名称
	 * @param docType
	 *            所属文种
	 * @param provider
	 *            模板提供者
	 * @param createDate
	 *            创建日期
	 * @param fileName
	 *            文件名
	 * @param remark
	 *            备注
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
	 * 成员对象在集合中的关键字
	 */
	private Object keyInCol = null;

	/**
	 * 获取属性值：成员对象在集合中的关键字
	 * 
	 * @return 成员对象在集合中的关键字
	 */
	public Object getKeyInCol() {
		return keyInCol;
	}

	/**
	 * 设置属性值：成员对象在集合中的关键字
	 * 
	 * @param keyInCol
	 *            成员对象在集合中的关键字
	 */
	public void setKeyInCol(Object keyInCol) {
		this.keyInCol = keyInCol;
	}

	/**
	 * 该数据项的附加对象，可以用来保存一些附加信息
	 */
	private Object tag = null;

	/**
	 * 获取属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * 
	 * @return 该数据项的附加对象，可以用来保存一些附加信息
	 */
	public Object getTag() {
		return tag;
	}

	/**
	 * 设置属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * 
	 * @param tag
	 *            该数据项的附加对象，可以用来保存一些附加信息
	 */
	public void setTag(Object tag) {
		this.tag = tag;
	}

	/**
	 * 模板编号
	 */
	private int iD = 0;

	/**
	 * 获取属性值：模板编号
	 * 
	 * @return 模板编号
	 */
	public int getID() {
		return iD;
	}

	/**
	 * 设置属性值：模板编号
	 * 
	 * @param iD
	 *            模板编号
	 */
	public void setID(int iD) {
		this.iD = iD;
	}

	/**
	 * 公文模板名称
	 */
	private String title = null;

	/**
	 * 获取属性值：公文模板名称
	 * 
	 * @return 公文模板名称
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置属性值：公文模板名称
	 * 
	 * @param title
	 *            公文模板名称
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 所属文种
	 */
	private int docType = 0;

	/**
	 * 获取属性值：所属文种
	 * 
	 * @return 所属文种
	 */
	public int getDocType() {
		return docType;
	}

	/**
	 * 设置属性值：所属文种
	 * 
	 * @param docType
	 *            所属文种
	 */
	public void setDocType(int docType) {
		this.docType = docType;
	}

	/**
	 * 模板提供者
	 */
	private String provider = null;

	/**
	 * 获取属性值：模板提供者
	 * 
	 * @return 模板提供者
	 */
	public String getProvider() {
		return provider;
	}

	/**
	 * 设置属性值：模板提供者
	 * 
	 * @param provider
	 *            模板提供者
	 */
	public void setProvider(String provider) {
		this.provider = provider;
	}

	/**
	 * 创建日期
	 */
	private Date createDate;

	/**
	 * 获取属性值：创建日期
	 * 
	 * @return 创建日期
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 设置属性值：创建日期
	 * 
	 * @param createDate
	 *            创建日期
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 文件名
	 */
	private String fileName = null;

	/**
	 * 获取属性值：文件名
	 * 
	 * @return 文件名
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 设置属性值：文件名
	 * 
	 * @param fileName
	 *            文件名
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 备注
	 */
	private String remark = null;

	/**
	 * 获取属性值：备注
	 * 
	 * @return 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置属性值：备注
	 * 
	 * @param remark
	 *            备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * clone
	 * 
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public OfficialTemplate clone() {
		OfficialTemplate item = new OfficialTemplate(iD, title, docType, provider, createDate, fileName, remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	 * 从指定对象克隆，复制所有属性值
	 * 
	 * @param officialTemplate
	 *            指定的对象源
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
