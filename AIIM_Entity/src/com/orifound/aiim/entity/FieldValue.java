/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * �ֶ�ֵ������
 *
 */
public class FieldValue extends ArchivesTypeDataItem
{
	/**
	 * ���캯��
	 * @param archivesTypeDataItem ��������������
	 */
	public FieldValue(ArchivesTypeDataItem archivesTypeDataItem)
	{
		super.cloneFrom(archivesTypeDataItem);
	}
	
	/**
	 * ��Ա�����ڼ����еĹؼ���
	 */
	private Object keyInCol=null;

	/**
	 * ��ȡ����ֵ����Ա�����ڼ����еĹؼ���
	 * @return ��Ա�����ڼ����еĹؼ���
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}

	/**
	 * ��������ֵ����Ա�����ڼ����еĹؼ���
	 * @param keyInCol ��Ա�����ڼ����еĹؼ���
	 */
	public void setKeyInCol(Object keyInCol)
	{
		this.keyInCol = keyInCol;
	}

	/**
	 * ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	private Object tag=null;

	/**
	 * ��ȡ����ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * @return ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public Object getTag()
	{
		return tag;
	}

	/**
	 * ��������ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * @param tag ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}
	
	/**
	 * ������ֶΣ���ֵ
	 */
	private String value = null;

	/**
	 * ��������ֵ��������ֶΣ���ֵ
	 * @param value ������ֶΣ���ֵ
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * ��ȡ����ֵ��������ֶΣ���ֵ
	 * @return ������ֶΣ���ֵ
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public FieldValue clone()
	{
		FieldValue item = new FieldValue(this);
		item.setValue(value);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}
	
	/**
	* ��ָ�������¡��������������ֵ
	* @param fieldValue ָ���Ķ���Դ
	*/
	public void cloneFrom(FieldValue fieldValue)
	{
		super.cloneFrom(fieldValue);
		this.value=fieldValue.getValue();
		this.keyInCol=fieldValue.getKeyInCol();
		this.tag=fieldValue.getTag();
	}
}
