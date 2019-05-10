package com.orifound.aiim.entity;

/**
 * ϵͳ�����ɫö����
 * <p>0����ʾ������ϵͳ�����û���ɫ</p>
 * <p>1����ʾ�����û���ɫ</p>
 * <p>2����ʾ������ְ��Ա��ɫ</p>
 * <p>3����ʾҵ��ָ��רԱ��ɫ</p>
 */
public enum EnumSystemRole {

	/**
	 * ʲôҲ���ǵ�ö�ٳ�Ա��δ֪��ö�٣�
	 */
	NONE(-1),
	
	/**
	 * ������ϵͳ�����û���ɫ
	 */
	������ϵͳ�����û���ɫ(0),
	
	/**
	 * �����û���ɫ
	 */
	�����û���ɫ(1),
	
	/**
	 * ������ְ��Ա��ɫ
	 */
	������ְ��Ա��ɫ(2),
	
	/**
	 * ҵ��ָ��רԱ��ɫ
	 */
	ҵ��ָ��רԱ��ɫ(3),
	
	/**
	 * ����������רԱ��ɫ
	 */
	����������רԱ��ɫ(4);

	/**
	 * ���캯��
	 * @param enumValue ö��ֵ
	 */
	private EnumSystemRole(int enumValue) {
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
	public static EnumSystemRole getEnumElement(int enumValue) {
		EnumSystemRole pEnumElement = EnumSystemRole.NONE;

		for (EnumSystemRole item : EnumSystemRole.values()) {
			if (item.getEnumValue() == enumValue) {
				pEnumElement = item;
				break;
			}
		}

		return pEnumElement;
	}
}