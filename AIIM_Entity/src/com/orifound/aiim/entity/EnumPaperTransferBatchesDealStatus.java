/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * ʵ�ﵵ���ƽ����εĴ���״̬ö��
 *
 */
public enum EnumPaperTransferBatchesDealStatus {
	
//	0��δȷ���ƽ�
//	1��ȷ���ƽ�
//	2�����յǼ����
//	3������������
	
	/**
	 * ʲôҲ���ǵ�ö�ٳ�Ա��δ֪��ö�٣�
	 */
	NONE(-1),

	/**
	 * δȷ���ƽ�
	 */
	δȷ���ƽ�(0),
	
	/**
	 * ȷ���ƽ�
	 */
	ȷ���ƽ�(1),
	
	/**
	 * ���յǼ����
	 */
	���յǼ����(2),
	
	/**
	 *����������
	 */
	����������(3),
	
	/**
	 *����������
	 */
	��ӵ��ƽ��嵥(4);
	/**
	 * ���캯��
	 * @param enumValue ö��ֵ
	 */
	 private EnumPaperTransferBatchesDealStatus(int enumValue)
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
	public static EnumPaperTransferBatchesDealStatus getEnumElement(int enumValue)
	{
		EnumPaperTransferBatchesDealStatus pEnumElement = EnumPaperTransferBatchesDealStatus.NONE;

		for (EnumPaperTransferBatchesDealStatus item : EnumPaperTransferBatchesDealStatus.values())
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
