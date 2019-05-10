package com.orifound.aiim.entity;

/**
 * 档案格式信息表的实体类
 */
public class ArchivesIDFormat {
	/**
	 * 构造函数
	 */
	public ArchivesIDFormat() {
	}
 
	/**
	 * 带字段参数的构造函数
	 * 
	 * @param iD
	 *            编号
	 * @param archivesTypeID
	 *            档案分类编号
	 * @param segmentColumnName
	 *            字段名称
	 * @param segmentDisplayText
	 *            显示名称
	 * @param segmentOrderID
	 *            先后次序
	 * @param segmentSeparator
	 *            分隔符
	 */
	public ArchivesIDFormat(int iD, int archivesTypeID,
			String segmentColumnName, String segmentDisplayText,
			int segmentOrderID, String segmentSeparator) {
		// Columns List,Can Used in SELECT SQL:
		// ID,ArchivesTypeID,SegmentColumnName,SegmentDisplayText,SegmentOrderID,SegmentSeparator
		// Columns List,Can Used in INSERT SQL:
		// pID,pArchivesTypeID,pSegmentColumnName,pSegmentDisplayText,pSegmentOrderID,pSegmentSeparator
		// Columns List,Can Used in UPDATE SQL:
		// ID=pID,ArchivesTypeID=pArchivesTypeID,SegmentColumnName=pSegmentColumnName,SegmentDisplayText=pSegmentDisplayText,SegmentOrderID=pSegmentOrderID,SegmentSeparator=pSegmentSeparator

		this.iD = iD;
		this.archivesTypeID = archivesTypeID;
		this.segmentColumnName = segmentColumnName;
		this.segmentDisplayText = segmentDisplayText;
		this.segmentOrderID = segmentOrderID;
		this.segmentSeparator = segmentSeparator;
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
	 * 编号
	 */
	private int iD = 0;

	/**
	 * 获取属性值：编号
	 */
	public int getID() {
		return iD;
	}

	/**
	 * 设置属性值：编号
	 */
	public void setID(int iD) {
		this.iD = iD;
	}

	/**
	 * 档案分类编号
	 */
	private int archivesTypeID = 0;

	/**
	 * 获取属性值：档案分类编号
	 */
	public int getArchivesTypeID() {
		return archivesTypeID;
	}

	/**
	 * 设置属性值：档案分类编号
	 */
	public void setArchivesTypeID(int archivesTypeID) {
		this.archivesTypeID = archivesTypeID;
	}

	/**
	 * 字段名称
	 */
	private String segmentColumnName = "";

	/**
	 * 获取属性值：字段名称
	 */
	public String getSegmentColumnName() {
		return segmentColumnName;
	}

	/**
	 * 设置属性值：字段名称
	 */
	public void setSegmentColumnName(String segmentColumnName) {
		this.segmentColumnName = segmentColumnName;
	}

	/**
	 * 显示名称
	 */
	private String segmentDisplayText = "";

	/**
	 * 获取属性值：显示名称
	 */
	public String getSegmentDisplayText() {
		return segmentDisplayText;
	}

	/**
	 * 设置属性值：显示名称
	 */
	public void setSegmentDisplayText(String segmentDisplayText) {
		this.segmentDisplayText = segmentDisplayText;
	}

	/**
	 * 先后次序
	 */
	private int segmentOrderID = 0;

	/**
	 * 获取属性值：先后次序
	 */
	public int getSegmentOrderID() {
		return segmentOrderID;
	}

	/**
	 * 设置属性值：先后次序
	 */
	public void setSegmentOrderID(int segmentOrderID) {
		this.segmentOrderID = segmentOrderID;
	}

	/**
	 * 分隔符
	 */
	private String segmentSeparator = "";

	/**
	 * 获取属性值：分隔符
	 */
	public String getSegmentSeparator() {
		return segmentSeparator;
	}

	/**
	 * 设置属性值：分隔符
	 */
	public void setSegmentSeparator(String segmentSeparator) {
		this.segmentSeparator = segmentSeparator;
	}

	/**
	 * clone
	 * 
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ArchivesIDFormat clone() {
		ArchivesIDFormat item = new ArchivesIDFormat(iD, archivesTypeID,
				segmentColumnName, segmentDisplayText, segmentOrderID,
				segmentSeparator);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

}
