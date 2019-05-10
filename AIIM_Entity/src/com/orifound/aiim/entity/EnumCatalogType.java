package com.orifound.aiim.entity;

/**
 * 目录打印模板类型枚举类
 * 
 */
public enum EnumCatalogType
{
	
//	1：表示案卷目录
//	2：表示盒内目录
//	3：表示卷内目录
//	4：表示公文目录


	/**
	 * 什么也不是的枚举成员（未知的枚举）
	 */
	NONE(-1) {
		@Override
		public String getName() {
			return "";
		}
	},

	/**
	 * 表示案卷目录
	 */
	案卷目录(1) {
		@Override
		public String getName() {
			return "案卷目录";
		}
	},
	
	/**
	 * 表示盒内目录
	 */
	盒内目录(2) {
		@Override
		public String getName() {
			return "盒内目录";
		}
	},
	
	/**
	 * 表示卷内目录
	 */
	卷内目录(3) {
		@Override
		public String getName() {
			return "卷内目录";
		}
	},
	
	/**
	 * 表示公文目录
	 */
	公文目录(4) {
		@Override
		public String getName() {
			return "公文目录";
		}
	};
	
	/**
	 * 构造函数
	 * @param enumValue 枚举值
	 */
	private EnumCatalogType(int enumValue)
	{
		this.enumValue = enumValue;
	}

	/**
	 * 枚举值
	 */
	private int enumValue;

	/**
	 * 获取属性值：枚举值
	 * @return 枚举值
	 */
	public int getEnumValue()
	{
		return enumValue;
	}
	
	public abstract String getName();

	/**
	 * 根据枚举值获取枚举成员
	 * @param enumValue 枚举值
	 * @return 对应该枚举值的枚举成员对象
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
