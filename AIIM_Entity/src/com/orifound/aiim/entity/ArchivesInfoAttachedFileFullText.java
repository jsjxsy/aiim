package com.orifound.aiim.entity;

/**
 * ����ԭ�ĵ����ļ�ȫ����Ϣ���ʵ����
 */
public class ArchivesInfoAttachedFileFullText {
	/**
	 * ���캯��
	 */
	public ArchivesInfoAttachedFileFullText() {
	}

	/**
	 * ���ֶβ����Ĺ��캯��
	 * 
	 * @param attachedFileID
	 *            ԭ�ĵ����ļ����
	 * @param fullText
	 *            ȫ������
	 * @param remark
	 *            ��ע
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
	 * ԭ�ĵ����ļ����
	 */
	private int attachedFileID = 0;

	/**
	 * ��ȡ����ֵ��ԭ�ĵ����ļ����
	 */
	public int getAttachedFileID() {
		return attachedFileID;
	}

	/**
	 * ��������ֵ��ԭ�ĵ����ļ����
	 */
	public void setAttachedFileID(int attachedFileID) {
		this.attachedFileID = attachedFileID;
	}

	/**
	 * ȫ������
	 */
	private String fullText = "";

	/**
	 * ��ȡ����ֵ��ȫ������
	 */
	public String getFullText() {
		return fullText;
	}

	/**
	 * ��������ֵ��ȫ������
	 */
	public void setFullText(String fullText) {
		this.fullText = fullText;
	}

	/**
	 * ��ע
	 */
	private String remark = "";

	/**
	 * ��ȡ����ֵ����ע
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * ��������ֵ����ע
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * clone
	 * 
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ArchivesInfoAttachedFileFullText clone() {
		ArchivesInfoAttachedFileFullText item = new ArchivesInfoAttachedFileFullText(
				attachedFileID, fullText, remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

}
