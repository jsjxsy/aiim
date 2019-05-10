/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * 档案分类数据项的比较器类型枚举<br>
 * 著录次序,查询次序,列表显示次序
 *
 */
public enum EnumArchivesTypeDataItemComparatorType
{
	/**
	 * 继续著录时的显示次序
	 */
	著录次序,
	
	/**
	 * 作为著录查询条件时的显示次序
	 */
	著录查询次序,
	
	/**
	 * 作为利用查询条件时的显示次序
	 */
	利用查询次序,
	
	/**
	 * 查询结果列表中的显示次序
	 */
	列表显示次序;
}
