package com.orifound.aiim.entity;

import java.util.*;

/**
 * ���������µ�������Ĵ�С�Ƚ���������
 */
public class ArchivesTypeDataItemComparator implements
		Comparator<ArchivesTypeDataItem> {

	/**
	 * �Ƚ�������
	 */
	private EnumArchivesTypeDataItemComparatorType comparatorType;

	/**
	 * ��������ֵ���Ƚ�������
	 * @param comparatorType �Ƚ�������
	 */
	public void setPropertyName(EnumArchivesTypeDataItemComparatorType comparatorType)
	{
		this.comparatorType = comparatorType;
	}

	/**
	 * ��ȡ����ֵ���Ƚ�������
	 * @return �Ƚ�������
	 */
	public EnumArchivesTypeDataItemComparatorType getPropertyName()
	{
		return comparatorType;
	}

	
	/**
	 * ���ֶεĹ��캯��
	 * @param comparatorType �Ƚ�������
	 */
	public ArchivesTypeDataItemComparator(EnumArchivesTypeDataItemComparatorType comparatorType)
	{
		this.comparatorType=comparatorType;
	}
	
	@Override
	public int compare(ArchivesTypeDataItem o1, ArchivesTypeDataItem o2) {
		int compareResult=0;

		if (comparatorType==EnumArchivesTypeDataItemComparatorType.��¼����)
		{
			if (o1.getInputOrderID() == o2.getInputOrderID()) {
				compareResult = 0;
			} else if (o1.getInputOrderID() > o2.getInputOrderID()) {
				compareResult = 1;
			} else {
				compareResult = -1;
			}
		}
		else if (comparatorType==EnumArchivesTypeDataItemComparatorType.��¼��ѯ����)
		{
			if (o1.getInputQueryOrderID() == o2.getInputQueryOrderID()) {
				compareResult = 0;
			} else if (o1.getInputQueryOrderID() > o2.getInputQueryOrderID()) {
				compareResult = 1;
			} else {
				compareResult = -1;
			}
		}
		else if (comparatorType==EnumArchivesTypeDataItemComparatorType.���ò�ѯ����)
		{
			if (o1.getUseQueryOrderID() == o2.getUseQueryOrderID()) {
				compareResult = 0;
			} else if (o1.getUseQueryOrderID() > o2.getUseQueryOrderID()) {
				compareResult = 1;
			} else {
				compareResult = -1;
			}
		}
		else if (comparatorType==EnumArchivesTypeDataItemComparatorType.�б���ʾ����)
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
