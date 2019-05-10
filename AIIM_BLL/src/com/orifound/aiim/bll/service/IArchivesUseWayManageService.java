package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ArchivesUseWay;
import com.orifound.aiim.entity.ErrInfo;
/**
 * �������÷�ʽ�ֵ��������ӿ�
 * @author Administrator
 *
 */
public interface IArchivesUseWayManageService {
	/**
	 * �������е�ArchivesUseWay
	 * @param archivesUseWays ���ز��ҳɹ���ArchivesUseWay����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllArchivesUseWay(List<ArchivesUseWay> archivesUseWays, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ����ArchivesUseWay
	 * @param pID ָ����Ψһ��ʶ
	 * @param archivesUseWay ���ز��ҳɹ���ArchivesUseWay
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesUseWayByID(ArchivesUseWay archivesUseWay, ErrInfo pErrInfo);
}
