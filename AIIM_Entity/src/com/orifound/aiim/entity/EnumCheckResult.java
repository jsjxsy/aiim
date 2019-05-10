/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * 审核结果枚举类
 * 适用于著录审核、接收审核和利用申请的审批
 * 
 */
public enum EnumCheckResult {

	/**
	 * 尚未审核
	 */
	尚未审核(0),

	/**
	 * 审核通过
	 */
	业务指导室审核通过(1),
	
	/**
	 * 审核打回
	 */
	业务指导室审核打回(2),

	/**
	 * 审核通过
	 */
	档案管理室审核通过(3),
	
	/**
	 * 审核打回
	 */
	档案管理室审核打回(4);

	/**
	 * 构造函数
	 * @param enumValue 枚举值
	 */
	private EnumCheckResult(int enumValue) {
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
	public static EnumCheckResult getEnumElement(int enumValue) {
		EnumCheckResult pEnumElement = EnumCheckResult.尚未审核;

		for (EnumCheckResult item : EnumCheckResult.values()) {
			if (item.getEnumValue() == enumValue) {
				pEnumElement = item;
				break;
			}
		}

		return pEnumElement;
	}
}