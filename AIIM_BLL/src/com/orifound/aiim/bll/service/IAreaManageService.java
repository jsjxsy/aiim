package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.Area;
import com.orifound.aiim.entity.ErrInfo;

public interface IAreaManageService {
	/**
	 * �������е�Area
	 * @param areas ���ز��ҳɹ���Area����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllArea(List<Area> pAreas, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ����Area
	 * @param pID ָ����Ψһ��ʶ
	 * @param area ���ز��ҳɹ���Area
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByIDArea(int pID, Area pArea, ErrInfo pErrInfo);
}
