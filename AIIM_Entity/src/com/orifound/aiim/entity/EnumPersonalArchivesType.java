package com.orifound.aiim.entity;

/**
 * ���µ���ö������
 * 
 */
public enum EnumPersonalArchivesType
{

//		0����ʾ�������µ���
//		1����ʾ��ְ������
//		2����ʾ��ʿ�󵵰�
//		3����ʾ��ʿ������
//		4����ʾ˶ʿ������
//		5����ʾ����������
	
	/**
	 * ʲôҲ���ǵ�ö�ٳ�Ա��δ֪��ö�٣�
	 */
	NONE(-1),

	// ��ʾ�������µ���
	�����µ���(0),
	
	/**
	 * ��ʾ��ְ������
	 */
	��ְ������(1),
	
	/**
	 * ��ʾ��ʿ�󵵰�
	 */
	��ʿ�󵵰�(2),
	
	/**
	 * ��ʾ��ʿ������
	 */
	��ʿ������(3),
	
	/**
	 * ��ʾ˶ʿ������
	 */
	˶ʿ������(4),
	
	/**
	 * ��ʾ����������
	 */
	����������(5),


	/**
	 * ��ʾѧ�����µ�������
	 */
	ѧ�����µ�������(10),
	
	/**
	 * ��ʾ��ְ�����µ�������
	 */
	��ְ�����µ�������(11);
	
	/**
	 * ���캯��
	 * @param enumValue ö��ֵ
	 */
	private EnumPersonalArchivesType(int enumValue)
	{
		this.enumValue = enumValue;
	}

	/**
	 * ö��ֵ
	 */
	private int enumValue;

	/**
	 * ��ȡ����ֵ��ö��ֵ
	 * @return ö��ֵ
	 */
	public int getEnumValue()
	{
		return enumValue;
	}

	/**
	 * ����ö��ֵ��ȡö�ٳ�Ա
	 * @param enumValue ö��ֵ
	 * @return ��Ӧ��ö��ֵ��ö�ٳ�Ա����
	 */
	public static EnumPersonalArchivesType getEnumElement(int enumValue)
	{
		EnumPersonalArchivesType pEnumElement = EnumPersonalArchivesType.NONE;

		for (EnumPersonalArchivesType item : EnumPersonalArchivesType.values())
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
