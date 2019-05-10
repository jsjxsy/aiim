package com.orifound.aiim.entity;

/**
 * 系统定义角色枚举类
 * <p>0：表示不属于系统固有用户角色</p>
 * <p>1：表示匿名用户角色</p>
 * <p>2：表示档案兼职人员角色</p>
 * <p>3：表示业务指导专员角色</p>
 */
public enum EnumSystemRole {

	/**
	 * 什么也不是的枚举成员（未知的枚举）
	 */
	NONE(-1),
	
	/**
	 * 不属于系统固有用户角色
	 */
	不属于系统固有用户角色(0),
	
	/**
	 * 匿名用户角色
	 */
	匿名用户角色(1),
	
	/**
	 * 档案兼职人员角色
	 */
	档案兼职人员角色(2),
	
	/**
	 * 业务指导专员角色
	 */
	业务指导专员角色(3),
	
	/**
	 * 档案管理室专员角色
	 */
	档案管理室专员角色(4);

	/**
	 * 构造函数
	 * @param enumValue 枚举值
	 */
	private EnumSystemRole(int enumValue) {
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
	public int getEnumValue() {
		return enumValue;
	}

	/**
	 * 根据枚举值获取枚举成员
	 * @param enumValue 枚举值
	 * @return 对应该枚举值的枚举成员对象
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