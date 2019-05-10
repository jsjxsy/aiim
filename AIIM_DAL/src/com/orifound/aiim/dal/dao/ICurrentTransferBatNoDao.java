package com.orifound.aiim.dal.dao;

import com.orifound.aiim.entity.ErrInfo;

import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.PaperTransferBatch;

/**
 * ��ǰ�ƽ�������Ϣ���DAO�ӿڶ���
 *
 */
public interface ICurrentTransferBatNoDao {

	/**
	 * ���ҵ�ǰ�û������ĵ�ǰ�����κ�
	 * @param userID ��ǰ�û����
	 * @param paperTransferBatch Ҫ���ص�������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findCurrentTransferBatNo(int userID, PaperTransferBatch paperTransferBatch, ErrInfo pErrInfo);

	/**
	 * ���µ�ǰ������ŵ����ֵ
	 * @param userID ��ǰ�û����
	 * @param currentNo ������ĵ�ǰ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(int userID, IntegerEx currentNo, ErrInfo pErrInfo);

	/**
	 * ���ҵ�ǰ��������ŵ����ֵ
	 * @param currentNo Ҫ���صĵ�ǰ������
	*  @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findCurrentNo(IntegerEx currentNo, ErrInfo pErrInfo);

}
