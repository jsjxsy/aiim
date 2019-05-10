package com.orifound.aiim.entity;

/**
 * 档案原文电子文件全文信息表的实体类
 */
public class ArchivesInfoAttachedFileFullText {
	/**
	 * 构造函数
	 */
	public ArchivesInfoAttachedFileFullText() {
	}

	/**
	 * 带字段参数的构造函数
	 * 
	 * @param attachedFileID
	 *            原文电子文件编号
	 * @param fullText
	 *            全文数据
	 * @param remark
	 *            备注
	 */
	public ArchivesInfoAttachedFileFullText(int attachedFileID,
			String fullText, String remark) {
		// Columns List,Can Used in SELECT SQL: AttachedFileID,FullText,Remark
		// Columns List,Can Used in INSERT SQL:
		// pAttachedFileID,pFullText,pRemark
		// Columns List,Can Used in UPDATE SQL:
		// AttachedFileID=pAttachedFileID,FullText=pFullText,Remark=pRemark

		this.attachedFileID = attachedFileID;
		this.fullText = fullText;
		this.remark = remark;
	}

	/**
	 * 成员对象在集合中的关键字
	 */
	private Object keyInCol = null;

	/**
	 * 获取属性值：成员对象在集合中的关键字
	 */
	public Object getKeyInCol() {
		return keyInCol;
	}

	/**
	 * 设置属性值：成员对象在集合中的关键字
	 */
	public void setKeyInCol(Object keyInCol) {
		this.keyInCol = keyInCol;
	}

	/**
	 * 附加对象，可以用来保存一些附加信息
	 */
	private Object tag = null;

	/**
	 * 获取属性值：附加对象，可以用来保存一些附加信息
	 */
	public Object getTag() {
		return tag;
	}

	/**
	 * 设置属性值：附加对象，可以用来保存一些附加信息
	 */
	public void setTag(Object tag) {
		this.tag = tag;
	}

	/**
	 * 原文电子文件编号
	 */
	private int attachedFileID = 0;

	/**
	 * 获取属性值：原文电子文件编号
	 */
	public int getAttachedFileID() {
		return attachedFileID;
	}

	/**
	 * 设置属性值：原文电子文件编号
	 */
	public void setAttachedFileID(int attachedFileID) {
		this.attachedFileID = attachedFileID;
	}

	/**
	 * 全文数据
	 */
	private String fullText = "";

	/**
	 * 获取属性值：全文数据
	 */
	public String getFullText() {
		return fullText;
	}

	/**
	 * 设置属性值：全文数据
	 */
	public void setFullText(String fullText) {
		this.fullText = fullText;
	}

	/**
	 * 备注
	 */
	private String remark = "";

	/**
	 * 获取属性值：备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置属性值：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * clone
	 * 
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ArchivesInfoAttachedFileFullText clone() {
		ArchivesInfoAttachedFileFullText item = new ArchivesInfoAttachedFileFullText(
				attachedFileID, fullText, remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

}
