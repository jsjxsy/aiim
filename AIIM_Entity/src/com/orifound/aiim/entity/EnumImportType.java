package com.orifound.aiim.entity;

/**
 * ���µ�����������
 * @author Administrator
 *
 */
public enum EnumImportType {

//	1����ʾ���������ְ������������Ϣ
	//
//		2����ʾ�������벩ʿ�󵵰�������Ϣ
	//
//		3����ʾ�������벩ʿ������������Ϣ
	//
//		4����ʾ��������˶ʿ������������Ϣ
	//
//		5����ʾ�������뱾��������������Ϣ
	//
//		6����ʾ�������²�ʿ����ҵȥ����Ϣ
	//
//		7����ʾ��������˶ʿ����ҵȥ����Ϣ
	//
//		8����ʾ�������±�������ҵȥ����Ϣ
	
	NONE(-1),
	
	��ְ������������Ϣ(1),
	
	��ʿ�󵵰�������Ϣ(2),
	
	��ʿ������������Ϣ(3),
	
	˶ʿ������������Ϣ(4),
	
	����������������Ϣ(5),
	
	��ʿ����ҵȥ����Ϣ(6),
	
	˶ʿ����ҵȥ����Ϣ(7),
	
	��������ҵȥ����Ϣ(8);
	


	
	/**
	 * ö��ֵ
	 */
	int enumValue;
	
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
	 * ���캯��
	 * 
	 * @param enumValue
	 *            ö��ֵ
	 */
	private EnumImportType(int enumValue)
	{
		this.enumValue = enumValue;
	}

	/**
	 * ����ö��ֵ��ȡö�ٳ�Ա
	 * 
	 * @param enumValue
	 *            ö��ֵ
	 * @return ��Ӧ��ö��ֵ��ö�ٳ�Ա����
	 */
	public static EnumImportType getEnumElement(int enumValue)
	{
		EnumImportType pEnumElement = EnumImportType.NONE;

		for (EnumImportType item : EnumImportType.values())
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
