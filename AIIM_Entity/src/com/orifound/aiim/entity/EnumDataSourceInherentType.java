package com.orifound.aiim.entity;

/**
 * ����Դ��������ö��<br>
 * �����ܼ�,��������,����ȫ��,�����γɲ���,�������ֵȵ�
 * 
 */
public enum EnumDataSourceInherentType
{

//	1����ʾ�����ܼ�
//	2����ʾ��������
//	3����ʾ������Ϣ
//	4����ʾ�������֣����ĵ����࣬��֪ͨ������ȵ�
//	5����ʾ����ȫ��
	
	/**
	 * ʲôҲ���ǵ�ö�ٳ�Ա��δ֪��ö�٣�
	 */
	NONE(-1),

	/**
	 * �����Ļ��ܼ�������Դ
	 */
	�����ܼ�(1),
	
	/**
	 * �����ı�����������Դ
	 */
	��������(2),
	
	/**
	 * �����γɲ�������Դ
	 */
	�����γɲ���(3),
	
	/**
	 * ������������Դ�����ĵ����࣬��֪ͨ�������
	 */
	��������(4),
	
	/**
	 * ����ȫ������Դ
	 */
	����ȫ��(5);
	
	/**
	 * ���캯��
	 * @param enumValue ö��ֵ
	 */
	private EnumDataSourceInherentType(int enumValue)
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
	public static EnumDataSourceInherentType getEnumElement(int enumValue)
	{
		EnumDataSourceInherentType pEnumElement = EnumDataSourceInherentType.NONE;

		for (EnumDataSourceInherentType item : EnumDataSourceInherentType.values())
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
