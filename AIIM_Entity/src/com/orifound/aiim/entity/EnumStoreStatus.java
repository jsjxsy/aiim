/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * 档案库房存储状态枚举类
 * 
 * 
 */
public enum EnumStoreStatus {
//	1：表示当前实物档案可被利用（如可以在阅览室查档或借出等）；
//	2：表示当前实物档案正在查档利用中；
//	3：表示当前实物档案正在借阅利用中；
//	4：表示当前实物档案被销毁了；
	
	/**
	 * 什么也不是的枚举成员（未知的枚举）
	 */
	NONE(-1),	
	
	/**
	 * 表示当前实物档案可被利用（如可以在阅览室查档或借出等）
	 */
	可被利用(1),

	/**
	 * 表示当前实物档案正在查档利用中
	 */
	查档利用中(2),
	
	/**
	 * 表示当前实物档案正在借阅利用中
	 */
	借阅利用中(3),
	
	/**
	 * 表示当前实物档案被销毁了
	 */
	已销毁(4);

	/**
	 * 构造函数
	 * @param enumValue 枚举值
	 */
	private EnumStoreStatus(int enumValue) {
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
	public static EnumStoreStatus getEnumElement(int enumValue) {
		EnumStoreStatus pEnumElement = EnumStoreStatus.可被利用;

		for (EnumStoreStatus item : EnumStoreStatus.values()) {
			if (item.getEnumValue() == enumValue) {
				pEnumElement = item;
				break;
			}
		}

		return pEnumElement;
	}
}

