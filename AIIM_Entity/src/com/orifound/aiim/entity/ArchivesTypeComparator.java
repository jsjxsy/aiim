/**
 * 
 */
package com.orifound.aiim.entity;

import java.util.*;

/**
 * ��������Ĵ�С�Ƚ���������
 * 
 */
public class ArchivesTypeComparator implements Comparator<ArchivesType> {

	@Override
	public int compare(ArchivesType o1, ArchivesType o2) {
		int companResult;

		if (o1.getID() == o2.getID()) {
			companResult = 0;
		} else if (o1.getID() > o2.getID()) {
			companResult = 1;
		} else {
			companResult = -1;
		}

		return companResult;
	}

}
