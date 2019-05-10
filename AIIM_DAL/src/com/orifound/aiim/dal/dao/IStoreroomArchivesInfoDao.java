package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StoreroomArchivesInfo;


/**
 * �ⷿ������Ϣ�� ��DAO�ӿڶ���
 *
 */
public interface IStoreroomArchivesInfoDao {

	/**
	 * Dao�ӿڶ��壺��ӿⷿ������Ϣ
	 * @param storeroomArchivesInfo Ҫ��ӵĵ����ⷿλ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ŀⷿ������Ϣ
	 * @param storeroomArchivesInfo Ҫɾ���Ŀⷿ������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���Ŀⷿ������Ϣ
	 * @param storeroomArchivesInfo Ҫ���µĿⷿ������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĿⷿ������Ϣ
	 * @param storeroomArchivesInfos ���ز��ҳɹ��Ŀⷿ������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<StoreroomArchivesInfo> storeroomArchivesInfos, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺���ݵ�����������ҿⷿ������Ϣ
	 * @param NBXH ָ����Ψһ��ʶ
	 * @param storeroomArchivesInfo ���ز��ҳɹ��Ŀⷿ������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByBarcode(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);
	
	
	/**
	 * Dao�ӿڶ��壺���ݵ��������ź�NBXH���ҿⷿ������Ϣ
	 * @param NBXH ָ����Ψһ��ʶ
	 * @param storeroomArchivesInfo ���ز��ҳɹ��Ŀⷿ������Ϣ�����������ź�NBXH���븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByNBXH(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);
	 
	/**
	 * Dao�ӿڶ��壺����ָ��NBXH�ĵ����ݲ�״̬
	 * @param storeroomArchivesInfo Ҫ���µĿⷿ������Ϣ archivesBarcode,storeStatus���븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateStoreStatusByArchivesBarcode(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺��ӿⷿ������Ϣ
	 * @param storeroomArchivesInfo Ҫ��ӵĵ����ⷿλ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean add(String barcode,  ArchivesType archivesType, ErrInfo pErrInfo);

	/**
	 * ���ݺ�������ҿⷿ������Ϣ
	 * @param archivesBoxBarcode ����������
	 * @param storeroomArchivesInfos ���صĿⷿ������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByBoxBarcode(String archivesBoxBarcode, List<StoreroomArchivesInfo> storeroomArchivesInfos, ErrInfo pErrInfo);

	/**
	 * ���µ������ں�����
	 * @param archivesBarcodes
	 * @param archivesBoxBarcode
	 * @param pErrInfo
	 * @return
	 */
	boolean updateArchivesBoxBarcode(List<String> archivesBarcodes, String archivesBoxBarcode, ErrInfo pErrInfo);
	
	/**
	 * ��ѯ�ⷿ������Ϣ
	 * @param NBXH
	 * @param archivesTypeID
	 * @param storeroomArchivesInfo
	 * @param pErrInfo
	 * @return
	 */
	boolean find(int NBXH, int archivesTypeID,StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);

	/**
	 * ���µ���������
	 * @param barcode
	 * @param nbxh
	 * @param archivesType
	 * @param pErrInfo
	 * @return
	 */
	boolean updateBarCode(String barcode, int nbxh, ArchivesType archivesType, ErrInfo pErrInfo);
	
	/**
	 * ���ݲ�ѯ������ѯ�ⷿ������Ϣ
	 * @param whereSQL WHERE����
	 * @param storeroomArchivesInfos ���صĿⷿ������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return
	 */
	boolean findByCondition(String whereSQL ,List<StoreroomArchivesInfo> storeroomArchivesInfos ,ErrInfo pErrInfo);
}
