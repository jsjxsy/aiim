package com.orifound.aiim.entity;

/**
 * ���ĵ���������Ϣ������ö��
 * 
 */
public enum EnumOfficialArchivesInfoTableType
{
	
//	1�����ĵ����ǼǱ�
//	2�����ĵ���ԭ����Ϣ��

	/**
	 * ʲôҲ���ǵ�ö�ٳ�Ա��δ֪��ö�٣�
	 */
	NONE(-1),

	// ��������ö�ٳ�Ա
	
	/**
	 * ���ĵ����ǼǱ�
	 */
	���ĵ����ǼǱ�(1),
	
	/**
	 * ���ĵ���ԭ����Ϣ��
	 */
	���ĵ���ԭ����Ϣ��(2);

	/**
	 * ���캯��
	 * @param enumValue ö��ֵ
	 */
	private EnumOfficialArchivesInfoTableType(int enumValue)
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
	public static EnumOfficialArchivesInfoTableType getEnumElement(int enumValue)
	{
		EnumOfficialArchivesInfoTableType pEnumElement = EnumOfficialArchivesInfoTableType.NONE;

		for (EnumOfficialArchivesInfoTableType item : EnumOfficialArchivesInfoTableType.values())
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
