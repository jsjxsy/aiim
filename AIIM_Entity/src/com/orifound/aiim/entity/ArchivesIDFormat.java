package com.orifound.aiim.entity;

/**
 * ������ʽ��Ϣ���ʵ����
 */
public class ArchivesIDFormat {
	/**
	 * ���캯��
	 */
	public ArchivesIDFormat() {
	}
 
	/**
	 * ���ֶβ����Ĺ��캯��
	 * 
	 * @param iD
	 *            ���
	 * @param archivesTypeID
	 *            ����������
	 * @param segmentColumnName
	 *            �ֶ�����
	 * @param segmentDisplayText
	 *            ��ʾ����
	 * @param segmentOrderID
	 *            �Ⱥ����
	 * @param segmentSeparator
	 *            �ָ���
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
	 * ��Ա�����ڼ����еĹؼ���
	 */
	private Object keyInCol = null;

	/**
	 * ��ȡ����ֵ����Ա�����ڼ����еĹؼ���
	 */
	public Object getKeyInCol() {
		return keyInCol;
	}

	/**
	 * ��������ֵ����Ա�����ڼ����еĹؼ���
	 */
	public void setKeyInCol(Object keyInCol) {
		this.keyInCol = keyInCol;
	}

	/**
	 * ���Ӷ��󣬿�����������һЩ������Ϣ
	 */
	private Object tag = null;

	/**
	 * ��ȡ����ֵ�����Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public Object getTag() {
		return tag;
	}

	/**
	 * ��������ֵ�����Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public void setTag(Object tag) {
		this.tag = tag;
	}

	/**
	 * ���
	 */
	private int iD = 0;

	/**
	 * ��ȡ����ֵ�����
	 */
	public int getID() {
		return iD;
	}

	/**
	 * ��������ֵ�����
	 */
	public void setID(int iD) {
		this.iD = iD;
	}

	/**
	 * ����������
	 */
	private int archivesTypeID = 0;

	/**
	 * ��ȡ����ֵ������������
	 */
	public int getArchivesTypeID() {
		return archivesTypeID;
	}

	/**
	 * ��������ֵ������������
	 */
	public void setArchivesTypeID(int archivesTypeID) {
		this.archivesTypeID = archivesTypeID;
	}

	/**
	 * �ֶ�����
	 */
	private String segmentColumnName = "";

	/**
	 * ��ȡ����ֵ���ֶ�����
	 */
	public String getSegmentColumnName() {
		return segmentColumnName;
	}

	/**
	 * ��������ֵ���ֶ�����
	 */
	public void setSegmentColumnName(String segmentColumnName) {
		this.segmentColumnName = segmentColumnName;
	}

	/**
	 * ��ʾ����
	 */
	private String segmentDisplayText = "";

	/**
	 * ��ȡ����ֵ����ʾ����
	 */
	public String getSegmentDisplayText() {
		return segmentDisplayText;
	}

	/**
	 * ��������ֵ����ʾ����
	 */
	public void setSegmentDisplayText(String segmentDisplayText) {
		this.segmentDisplayText = segmentDisplayText;
	}

	/**
	 * �Ⱥ����
	 */
	private int segmentOrderID = 0;

	/**
	 * ��ȡ����ֵ���Ⱥ����
	 */
	public int getSegmentOrderID() {
		return segmentOrderID;
	}

	/**
	 * ��������ֵ���Ⱥ����
	 */
	public void setSegmentOrderID(int segmentOrderID) {
		this.segmentOrderID = segmentOrderID;
	}

	/**
	 * �ָ���
	 */
	private String segmentSeparator = "";

	/**
	 * ��ȡ����ֵ���ָ���
	 */
	public String getSegmentSeparator() {
		return segmentSeparator;
	}

	/**
	 * ��������ֵ���ָ���
	 */
	public void setSegmentSeparator(String segmentSeparator) {
		this.segmentSeparator = segmentSeparator;
	}

	/**
	 * clone
	 * 
	 * @return ��¡��ǰ����ʵ����õ����¶���
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
