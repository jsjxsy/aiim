package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.EMS;
import com.orifound.aiim.entity.ErrInfo;

/**
 * EMS��ϢDAO
 * @author Administrator
 *
 */
public interface IEMSTemplateDao {

	/**
	 * ����ת������ѯ��ݵ���Ϣ
	 * @param ids ת�����������
	 * @param emsInfos ���صĿ�ݵ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findEMSinfos(int[] ids, List<EMS> emsInfos, ErrInfo pErrInfo);

}
