/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * ������չ������<br>
 * ��������Ϊ�����Ĳ������ͣ���ʵ�ֲ��������ĵ�ַ����
 *
 */
public class IntegerEx
{
	/**
	 * ���캯��
	 */
	public IntegerEx()
	{
	}

	/**
	 * ���캯��
	 * @param value ������ֵ
	 */
	public IntegerEx(int value)
	{
		this.value=value;
	}
	
	/**
	 * ����ֵ
	 */
	private int value = 0;

	/**
	 * ��������ֵ������ֵ
	 * @param value ����ֵ
	 */
	public void setValue(int value)
	{
		this.value = value;
	}

	/**
	 * ��ȡ����ֵ������ֵ
	 * @return ����ֵ
	 */
	public int getValue()
	{
		return value;
	}

	

}
