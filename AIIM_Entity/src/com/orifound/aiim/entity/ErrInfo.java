/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * ������Ϣ������
 * 
 */
public class ErrInfo {

	/**
	 * ������Ϣ����ϸ����
	 */
	private StringBuilder content = new StringBuilder();

	/**
	 * ���¸ô��������쳣��Ϣ
	 */
	private Exception exception=null;

	/**
	 * ���캯��
	 */
	public ErrInfo() {
		
	}

	/**
	 * @return ��ȡ����ֵ��������Ϣ����ϸ����
	 */
	public StringBuilder getContent() {
		return content;
	}

	/**
	 * @param exception
	 *            ��������ֵ�����¸ô��������쳣��Ϣ
	 */
	public void setException(Exception exception) {
		this.exception = exception;
	}

	/**
	 * @return ��ȡ����ֵ�����¸ô��������쳣��Ϣ
	 */
	public Exception getException() {
		return exception;
	}

	@Override
	public String toString() {
		
		//����д����SQL�����ƴ���ڴ���������
		if (badSQL.isEmpty()==false){
			content.append("�����SQL��䣺");
			content.append(badSQL);
		}
		
		return content.toString();
	}

	/**
	 * ��ȡ��ײ������Ϣ
	 * 
	 * @return ������Ϣ����ĩβ��ͷ������ַ���
	 */
	public String toShortString() {
		String shortString;

		int index = 0;
		index = content.lastIndexOf("-->");

		if (index > 0) {
			shortString = content.toString().substring(index + 3);
		} else {
			shortString = content.toString();
		}

		return shortString;
	}
	
	/**
	 * ִ�г����SQL���
	 */
	private String badSQL = "";

	/**
	 * ��������ֵ��ִ�г����SQL���
	 * @param badSQL ִ�г����SQL���
	 */
	public void setBadSQL(String badSQL) {
		this.badSQL = badSQL;
	}

	/**
	 * ��ȡ����ֵ��ִ�г����SQL���
	 * @return ִ�г����SQL���
	 */
	public String getBadSQL() {
		return badSQL;
	}

	/**
	 * �����ǰ������Ϣ
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public void clear() {
		content.delete(0, content.length());
		exception=null;
		badSQL="";
	}

	

}
