/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * ������Ϣ�Ĺ鵵����ö��<br>
 * �ļ������������������������ļ�
 */
public enum EnumArchivesInfoType {
	/**
	 * ʲôҲ���ǵ�ö�ٳ�Ա��δ֪��ö�٣�
	 */
	NONE(-1),
	
	/**
	 * �ļ���������Ϣ
	 */
	�ļ�������(0),
	
	/**
	 * ����������Ϣ
	 */
	��������(1),
	
	/**
	 * �����ļ���Ϣ
	 */
	�����ļ�(2);
	
	private EnumArchivesInfoType(int enumValue){
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
	public static EnumArchivesInfoType getEnumElement(int enumValue) {
		EnumArchivesInfoType enumArchivesInfoType=EnumArchivesInfoType.NONE;
		
		for (EnumArchivesInfoType item : EnumArchivesInfoType.values()) {
			if (item.getEnumValue()==enumValue)
			{
				enumArchivesInfoType=item;
				break;
			}
		}
		
		return enumArchivesInfoType;
	}
}
