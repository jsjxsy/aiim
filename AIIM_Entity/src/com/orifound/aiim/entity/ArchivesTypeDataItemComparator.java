package com.orifound.aiim.entity;

import java.util.*;

/**
 * 档案分类下的数据项的大小比较器定义类
 */
public class ArchivesTypeDataItemComparator implements
		Comparator<ArchivesTypeDataItem> {

	/**
	 * 比较器类型
	 */
	private EnumArchivesTypeDataItemComparatorType comparatorType;

	/**
	 * 设置属性值：比较器类型
	 * @param comparatorType 比较器类型
	 */
	public void setPropertyName(EnumArchivesTypeDataItemComparatorType comparatorType)
	{
		this.comparatorType = comparatorType;
	}

	/**
	 * 获取属性值：比较器类型
	 * @return 比较器类型
	 */
	public EnumArchivesTypeDataItemComparatorType getPropertyName()
	{
		return comparatorType;
	}

	
	/**
	 * 带字段的构造函数
	 * @param comparatorType 比较器类型
	 */
	public ArchivesTypeDataItemComparator(EnumArchivesTypeDataItemComparatorType comparatorType)
	{
		this.comparatorType=comparatorType;
	}
	
	@Override
	public int compare(ArchivesTypeDataItem o1, ArchivesTypeDataItem o2) {
		int compareResult=0;

		if (comparatorType==EnumArchivesTypeDataItemComparatorType.著录次序)
		{
			if (o1.getInputOrderID() == o2.getInputOrderID()) {
				compareResult = 0;
			} else if (o1.getInputOrderID() > o2.getInputOrderID()) {
				compareResult = 1;
			} else {
				compareResult = -1;
			}
		}
		else if (comparatorType==EnumArchivesTypeDataItemComparatorType.著录查询次序)
		{
			if (o1.getInputQueryOrderID() == o2.getInputQueryOrderID()) {
				compareResult = 0;
			} else if (o1.getInputQueryOrderID() > o2.getInputQueryOrderID()) {
				compareResult = 1;
			} else {
				compareResult = -1;
			}
		}
		else if (comparatorType==EnumArchivesTypeDataItemComparatorType.利用查询次序)
		{
			if (o1.getUseQueryOrderID() == o2.getUseQueryOrderID()) {
				compareResult = 0;
			} else if (o1.getUseQueryOrderID() > o2.getUseQueryOrderID()) {
				compareResult = 1;
			} else {
				compareResult = -1;
			}
		}
		else if (comparatorType==EnumArchivesTypeDataItemComparatorType.列表显示次序)
		{
			if (o1.getListDisplayOrderID() == o2.getListDisplayOrderID()) {
				compareResult = 0;
			} else if (o1.getListDisplayOrderID() > o2.getListDisplayOrderID()) {
				compareResult = 1;
			} else {
				compareResult = -1;
			}
		}

		return compareResult;
	}

}
