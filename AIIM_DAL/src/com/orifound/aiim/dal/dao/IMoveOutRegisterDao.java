package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.MoveOutInfo;

/**
 * ת���ǼǱ�DAO�ӿ�
 * @author Administrator
 *
 */
public interface IMoveOutRegisterDao {

	/**
	 * ���ת������Ϣ
	 * @param moveOutInfo Ҫ��ӵ�ת���� 
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean addMoveOutInfo(MoveOutInfo moveOutInfo, ErrInfo pErrInfo);

	/**
	 * ���ݹ�˾���Ʋ���ת������Ϣ
	 * @param companyName ��˾����
	 * @param moveOutInfo ��ѯ�ɹ����ص�ת������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findMoveOutInfoByCompanyName(String companyName,ArchivesType archivesType, MoveOutInfo moveOutInfo, ErrInfo pErrInfo);

	/**
	 * ���µ�ǰת�����е���������
	 * @param id Ҫ���µ�ת������ID
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateTotalArchives(int id, ErrInfo pErrInfo);

	/**
	 * ��ѯָ��״̬��ָ��ת����ʽ��ת������Ϣ
	 * @param moveOutWay ת����ʽ
	 * @param moveOutFlag ת��״̬
	 * @param moveOutInfos ���ص�ת������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findMoveOutInfoByMoveOutWay(int moveOutWay, boolean moveOutFlag,ArchivesType archivesType,DataPageInfo dataPageInfo,int minNum ,int maxNum,List<MoveOutInfo> moveOutInfos, ErrInfo pErrInfo);

	/**
	 * ����ID����ת������Ϣ
	 * @param id ת�������
	 * @param moveOutInfo ���ص�ת������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findMoveOutInfoById(int id, MoveOutInfo moveOutInfo, ErrInfo pErrInfo);

	/**
	 * ����ת����ת����ʽ
	 * @param id ת�������
	 * @param moveOutWay ת����ʽ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateMoveOutWay(int id, int moveOutWay, ErrInfo pErrInfo);

	/**
	 * ����ת�������к�
	 * @param id ת�������
	 * @param sN ת�������к�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateSN(int id, String sN, ErrInfo pErrInfo);
}
