/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * ����������ö����<br>
 * ���������ͣ�1��ʾ�ⷿ�豸�����룬2��ʾ�����룬3��ʾ�������루�ļ����򰸾��������룩��
 */
public enum EnumBarcodeType {

	/**
	 * ʲôҲ���ǵ�ö�ٳ�Ա��δ֪��ö�٣�
	 */
	NONE(-1),

	/**
	 * �ⷿ�豸������
	 */
	�ⷿ�豸������(1),
	
	/**
	 * ����������
	 */
	������������(2),
	
	/**
	 * ���������루�ļ����򰸾��������룩
	 */
	����������(3);

	/**
	 * ���캯��
	 * @param enumValue ö��ֵ
	 */
	private EnumBarcodeType(int enumValue) {
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
	public int getEnumValue() {
		return enumValue;
	}

	/**
	 * ����ö��ֵ��ȡö�ٳ�Ա
	 * @param enumValue ö��ֵ
	 * @return ��Ӧ��ö��ֵ��ö�ٳ�Ա����
	 */
	public static EnumBarcodeType getEnumElement(int enumValue) {
		EnumBarcodeType pEnumElement = EnumBarcodeType.NONE;

		for (EnumBarcodeType item : EnumBarcodeType.values()) {
			if (item.getEnumValue() == enumValue) {
				pEnumElement = item;
				break;
			}
		}

		return pEnumElement;
	}
}
