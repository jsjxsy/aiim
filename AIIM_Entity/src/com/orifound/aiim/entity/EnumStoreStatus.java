/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * �����ⷿ�洢״̬ö����
 * 
 * 
 */
public enum EnumStoreStatus {
//	1����ʾ��ǰʵ�ﵵ���ɱ����ã�������������Ҳ鵵�����ȣ���
//	2����ʾ��ǰʵ�ﵵ�����ڲ鵵�����У�
//	3����ʾ��ǰʵ�ﵵ�����ڽ��������У�
//	4����ʾ��ǰʵ�ﵵ���������ˣ�
	
	/**
	 * ʲôҲ���ǵ�ö�ٳ�Ա��δ֪��ö�٣�
	 */
	NONE(-1),	
	
	/**
	 * ��ʾ��ǰʵ�ﵵ���ɱ����ã�������������Ҳ鵵�����ȣ�
	 */
	�ɱ�����(1),

	/**
	 * ��ʾ��ǰʵ�ﵵ�����ڲ鵵������
	 */
	�鵵������(2),
	
	/**
	 * ��ʾ��ǰʵ�ﵵ�����ڽ���������
	 */
	����������(3),
	
	/**
	 * ��ʾ��ǰʵ�ﵵ����������
	 */
	������(4);

	/**
	 * ���캯��
	 * @param enumValue ö��ֵ
	 */
	private EnumStoreStatus(int enumValue) {
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
	public static EnumStoreStatus getEnumElement(int enumValue) {
		EnumStoreStatus pEnumElement = EnumStoreStatus.�ɱ�����;

		for (EnumStoreStatus item : EnumStoreStatus.values()) {
			if (item.getEnumValue() == enumValue) {
				pEnumElement = item;
				break;
			}
		}

		return pEnumElement;
	}
}

