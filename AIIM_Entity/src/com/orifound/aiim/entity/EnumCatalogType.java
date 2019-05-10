package com.orifound.aiim.entity;

/**
 * Ŀ¼��ӡģ������ö����
 * 
 */
public enum EnumCatalogType
{
	
//	1����ʾ����Ŀ¼
//	2����ʾ����Ŀ¼
//	3����ʾ����Ŀ¼
//	4����ʾ����Ŀ¼


	/**
	 * ʲôҲ���ǵ�ö�ٳ�Ա��δ֪��ö�٣�
	 */
	NONE(-1) {
		@Override
		public String getName() {
			return "";
		}
	},

	/**
	 * ��ʾ����Ŀ¼
	 */
	����Ŀ¼(1) {
		@Override
		public String getName() {
			return "����Ŀ¼";
		}
	},
	
	/**
	 * ��ʾ����Ŀ¼
	 */
	����Ŀ¼(2) {
		@Override
		public String getName() {
			return "����Ŀ¼";
		}
	},
	
	/**
	 * ��ʾ����Ŀ¼
	 */
	����Ŀ¼(3) {
		@Override
		public String getName() {
			return "����Ŀ¼";
		}
	},
	
	/**
	 * ��ʾ����Ŀ¼
	 */
	����Ŀ¼(4) {
		@Override
		public String getName() {
			return "����Ŀ¼";
		}
	};
	
	/**
	 * ���캯��
	 * @param enumValue ö��ֵ
	 */
	private EnumCatalogType(int enumValue)
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
	
	public abstract String getName();

	/**
	 * ����ö��ֵ��ȡö�ٳ�Ա
	 * @param enumValue ö��ֵ
	 * @return ��Ӧ��ö��ֵ��ö�ٳ�Ա����
	 */
	public static EnumCatalogType getEnumElement(int enumValue)
	{
		EnumCatalogType pEnumElement = EnumCatalogType.NONE;

		for (EnumCatalogType item : EnumCatalogType.values())
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
