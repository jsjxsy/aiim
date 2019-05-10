package com.orifound.aiim.entity;

/**
 * ������Ϣ�����͵�ö�ٶ���
 * 
 */
public enum EnumArchivesInfoTableType
{

//	1�������鵵������
//	2�������鵵���̱�
//	3�������鵵��Ϣ��
//	4������ԭ����Ϣ��
//	5������ȫ����Ϣ��
	
	/**
	 * ʲôҲ���ǵ�ö�ٳ�Ա��δ֪��ö�٣�
	 */
	NONE(-1),

	/**
	 * �����鵵������ArchivesInfoWorking_TypeCode
	 */
	�����鵵������(1),
	
	/**
	 * �����鵵���̱�ArchivesInfoWorkProcedure_TypeCode
	 */
	�����鵵���̱�(2),
	
	/**
	 * �����鵵��Ϣ��ArchivesInfoSaved_TypeCode
	 */
	�����鵵��Ϣ��(3),
	
	/**
	 * ����ԭ����Ϣ��ArchivesInfoAttachedFile_TypeCode
	 */
	����ԭ����Ϣ��(4),
	
	/**
	 * ����ȫ����Ϣ��ArchivesInfoAttachedFileFullText_TypeCode
	 */
	����ȫ����Ϣ��(5);
	

	/**
	 * ���캯��
	 * @param enumValue ö��ֵ
	 */
	private EnumArchivesInfoTableType(int enumValue)
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
	public static EnumArchivesInfoTableType getEnumElement(int enumValue)
	{
		EnumArchivesInfoTableType pEnumElement = EnumArchivesInfoTableType.NONE;

		for (EnumArchivesInfoTableType item : EnumArchivesInfoTableType.values())
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
