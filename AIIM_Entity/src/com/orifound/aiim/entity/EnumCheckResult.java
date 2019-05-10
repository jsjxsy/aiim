/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * ��˽��ö����
 * ��������¼��ˡ�������˺��������������
 * 
 */
public enum EnumCheckResult {

	/**
	 * ��δ���
	 */
	��δ���(0),

	/**
	 * ���ͨ��
	 */
	ҵ��ָ�������ͨ��(1),
	
	/**
	 * ��˴��
	 */
	ҵ��ָ������˴��(2),

	/**
	 * ���ͨ��
	 */
	�������������ͨ��(3),
	
	/**
	 * ��˴��
	 */
	������������˴��(4);

	/**
	 * ���캯��
	 * @param enumValue ö��ֵ
	 */
	private EnumCheckResult(int enumValue) {
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
	public static EnumCheckResult getEnumElement(int enumValue) {
		EnumCheckResult pEnumElement = EnumCheckResult.��δ���;

		for (EnumCheckResult item : EnumCheckResult.values()) {
			if (item.getEnumValue() == enumValue) {
				pEnumElement = item;
				break;
			}
		}

		return pEnumElement;
	}
}