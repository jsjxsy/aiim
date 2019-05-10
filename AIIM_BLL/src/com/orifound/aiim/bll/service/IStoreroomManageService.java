/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StoreroomArchivesInfo;
import com.orifound.aiim.entity.StoreroomStructure;

/**
 * �ⷿ�������Ľӿڶ���
 *
 */
public interface IStoreroomManageService {
		
	
	/**
	 * �������еĿⷿ��Ϣ
	 * @param storeRooms ���ز��ҳɹ��Ŀⷿ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findStorerooms(List<StoreroomStructure> storeRooms,ErrInfo pErrInfo);
	
	/**
	 * ���ݿⷿ�ṹ��Ų������¼��ⷿ�ṹ<br>
	 * ע�⣬�÷�������������һ���ⷿ�ṹ�����¼��Ĳ�����в�ѯ
	 * @param storeroomStructureID ָ���Ŀⷿ�ṹ���
	 * @param children ���ز��ҳɹ����¼��ⷿ�ṹ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findStoreroomStructureChildrenByID(int storeroomStructureID,List<StoreroomStructure> children,ErrInfo pErrInfo);
	
	/**
	 * ���ݿⷿ�ṹ��Ų��ҿⷿ�ṹ��ϸ��Ϣ<br>
	 * ע�⣬�÷�����������ָ����ŵĿⷿ�ṹ��Ϣ���������ơ�����ȣ������¼��ⷿ�ṹ������в�ѯ
	 * @param storeroomStructureID ָ���Ŀⷿ�ṹ���
	 * @param storeroomStructure ���ز��ҳɹ��Ŀⷿ�ṹ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findStoreroomStructureByID(int storeroomStructureID,StoreroomStructure storeroomStructure,ErrInfo pErrInfo);
	
	
	/**
	 * ���¿ⷿ�ṹ��Ϣ<br>
	 * @param storeroomStructure Ҫ���µĿⷿ�ṹ��Ϣ���������Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateStoreroomStructure(StoreroomStructure storeroomStructure,ErrInfo pErrInfo);
	
	/**
	 * ���¿ⷿ�豸�����ÿռ�
	 * @param storeAddressBarcode �豸������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateStoreroomStructureUsedSizeByBarcode(String storeAddressBarcode,ErrInfo pErrInfo); 

	
	/**
	 * �Ǽ�ʵ�ﵵ���ڿⷿ�е��ϼ�λ��
	 * @param archivesBoxBarcode �����е�������
	 * @param storeAddressBarcode ���λ�õ�������
	 * @param storeAddressInfo ���صǼǳɹ���ĵ����ϼ�λ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean registerStoreAddress(String archivesBoxBarcode,String storeAddressBarcode,ErrInfo pErrInfo);
	
	/**
	 * λ����״̬��ѯ<br>
	 * ��������ָ�������Ŀⷿ������Ϣ���������ϼ�λ����ⷿ״̬��Ϣ
	 * @param storeroomArchivesInfoQueryCondition ��ѯ����
	 * @param storeroomArchivesInfos ���ز��ҳɹ��Ŀⷿ������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findStoreroomArchivesInfosByCondition(String whereSQL,List<StoreroomArchivesInfo> storeroomArchivesInfos,ErrInfo pErrInfo);

}



