/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * ��Ԫ���ʽ������ö������<br>
 * ����,����,����
 * 
 */
public enum EnumOperatorType {
	
	/**
	 * ʲôҲ���ǵ�ö�ٳ�Ա��δ֪��ö�٣�
	 */
	NONE(-1),
	
	/**
	 * ���ڲ���<br>
	 * �����ھ�ȷ��ѯ
	 */
	����(0),
	
	/**
	 * ��������<br>
	 * ������ģ����ѯ
	 */
	����(1),
	
	/**
	 * ���ڲ���<br>
	 * �����ڷ�Χ��ѯ
	 */
	����(2);
	
	/**
	 * ���캯��
	 * @param enumValue ö��ֵ
	 */
	private EnumOperatorType(int enumValue){
		this.enumValue=enumValue;
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
	public static EnumOperatorType getEnumElement(int enumValue) {
		EnumOperatorType pEnumElement=EnumOperatorType.NONE;
		
		for (EnumOperatorType item : EnumOperatorType.values()) {
			if (item.getEnumValue()==enumValue)
			{
				pEnumElement=item;
				break;
			}
		}
		
		return pEnumElement;
	}
}

