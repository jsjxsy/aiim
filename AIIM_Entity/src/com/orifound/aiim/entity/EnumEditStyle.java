package com.orifound.aiim.entity;

/**
 * �༭����ö�ٶ���<br>
 * �༭��񣺱�ʾ���������û������Ͻ��б༭ʱ�Ľ�������
 */
public enum EnumEditStyle
{

//	1���ı���
//	2���ı���
//	3�������б��
	
	/**
	 * ʲôҲ���ǵ�ö�ٳ�Ա��δ֪��ö�٣�
	 */
	NONE(-1),

	/**
	 * �ı��򣬵��б༭���
	 */
	�ı���(1),
	
	/**
	 * �ı��򣬶��б༭���
	 */
	�ı���(2),
	
	/**
	 * �����б�򣬶�ѡһ�б�ѡ��
	 */
	�����б��(3);

	/**
	 * ���캯��
	 * 
	 * @param enumValue
	 *            ö��ֵ
	 */
	private EnumEditStyle(int enumValue)
	{
		this.enumValue = enumValue;
	}

	/**
	 * ö��ֵ
	 */
	private int enumValue;

	/**
	 * ��ȡ����ֵ��ö��ֵ
	 * 
	 * @return ö��ֵ
	 */
	public int getEnumValue()
	{
		return enumValue;
	}

	/**
	 * ����ö��ֵ��ȡö�ٳ�Ա
	 * 
	 * @param enumValue
	 *            ö��ֵ
	 * @return ��Ӧ��ö��ֵ��ö�ٳ�Ա����
	 */
	public static EnumEditStyle getEnumElement(int enumValue)
	{
		EnumEditStyle pEnumElement = EnumEditStyle.NONE;

		for (EnumEditStyle item : EnumEditStyle.values())
		{
			if (item.getEnumValue() == enumValue)
			{
				pEnumElement = item;
				break;
			}
		}

		return pEnumElement;
	}
}