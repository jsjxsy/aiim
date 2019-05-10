package com.orifound.aiim.dal.dao;

import java.util.*;
import com.orifound.aiim.entity.Area;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ������DAO�ӿڶ���
 *
 */
public interface IAreaDao {

	/**
	 * Dao�ӿڶ��壺�������е�Area
	 * @param areas ���ز��ҳɹ���Area����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllArea(List<Area> pAreas, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����Area
	 * @param pID ָ����Ψһ��ʶ
	 * @param area ���ز��ҳɹ���Area
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAreaByID(int pID, Area pArea, ErrInfo pErrInfo);

}
