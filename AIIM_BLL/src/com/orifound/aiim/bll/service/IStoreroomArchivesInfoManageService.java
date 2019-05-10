package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StoreroomArchivesInfo;
/**
 * �ⷿ������Ϣ�Ĺ������ӿ�
 * @author Administrator
 *
 */
public interface IStoreroomArchivesInfoManageService {
	/**
	 * ��ӵ����ⷿλ����Ϣ
	 * @param storeroomArchivesInfo Ҫ��ӵĵ����ⷿλ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���ĵ����ⷿλ����Ϣ
	 * @param storeroomArchivesInfo Ҫɾ���ĵ����ⷿλ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);

	/**
	 * ����ָ���ĵ����ⷿλ����Ϣ
	 * @param storeroomArchivesInfo Ҫ���µĵ����ⷿλ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);

	/**
	 * �������еĵ����ⷿλ����Ϣ
	 * @param storeroomArchivesInfos ���ز��ҳɹ��ĵ����ⷿλ����Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<StoreroomArchivesInfo> storeroomArchivesInfos, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҵ����ⷿλ����Ϣ
	 * @param storeroomArchivesInfo ���ز��ҳɹ��ĵ����ⷿλ����Ϣ,archivesBarcode���븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByBarcode(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);
	
	/**
	 * ���ݵ��������ź�NBXH���ҵ����ⷿλ����Ϣ
	 * @param storeroomArchivesInfo ���ز��ҳɹ��ĵ����ⷿλ����Ϣ,���������ź�NBXH���븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findStoreroomArchivesInfoByNBXH(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);
	
	 
	/**
	 * ����ָ��NBXH�ĵ����ݲ�״̬
	 * @param storeroomArchivesInfo Ҫ���µĵ����ⷿλ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateStoreStatusByArchivesBarcode(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);
	
	/**
	 * ���µ���������
	 * @param archivesBarcodes Ҫ���µĵ�������
	 * @param archivesBoxBarcode ����������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateArchivesBoxBarcode(List<String> archivesBarcodes, String archivesBoxBarcode, ErrInfo pErrInfo);

	/**
	 * ���ݺ�������ҵ�����Ϣ
	 * @param archivesBoxBarcode ����������
	 * @param storeroomArchivesInfos ���صĿⷿ������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByBoxBarcode(String archivesBoxBarcode, List<StoreroomArchivesInfo> storeroomArchivesInfos, ErrInfo pErrInfo);
}
