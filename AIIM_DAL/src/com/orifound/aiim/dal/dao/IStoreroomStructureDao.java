	package com.orifound.aiim.dal.dao;

	import java.util.List;



	import com.orifound.aiim.entity.StoreroomStructure;
	import com.orifound.aiim.entity.ErrInfo;

	/**
	 * �ⷿ�ṹ��Ϣ���DAO�ӿڶ���
	 *
	 */
	public interface IStoreroomStructureDao {

		/**
		 * Dao�ӿڶ��壺��ӿⷿ�ṹ��Ϣ
		 * @param storeroomStructure Ҫ��ӵĿⷿ�ṹ��Ϣ
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean addStoreroomStructure(StoreroomStructure storeroomStructure, ErrInfo pErrInfo);
		
		/**
		 * Dao�ӿڶ��壺���¿ⷿ�ṹ��Ϣ<br>
		 * @param storeroomStructure Ҫ���µĿⷿ�ṹ��Ϣ���������Ա��븳ֵ
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean updateStoreroomStructure(StoreroomStructure storeroomStructure,ErrInfo pErrInfo);
		
		/**
		 * ͨ�������������ײ��豸�����ÿռ�
		 * @param storeAddressBarcode
		 * @param pErrInfo
		 * @return
		 */
		boolean updateUsedSizeByBarcode(String storeAddressBarcode,ErrInfo pErrInfo);
				
		/**
		 * ͨ���豸��Ÿ������ÿռ�
		 * @param storeAddressID
		 * @param pErrInfo
		 * @return
		 */
		boolean updateUsedSizeByID(int storeAddressID,ErrInfo pErrInfo );
		
		/**
		 * Dao�ӿڶ��壺�������еĿⷿ��Ϣ
		 * @param storeRooms ���ز��ҳɹ��Ŀⷿ��Ϣ
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean findStorerooms(List<StoreroomStructure> storeRooms,ErrInfo pErrInfo);

		/**
		 * Dao�ӿڶ��壺���ݿⷿ�豸��������ҿⷿ�ṹ��ϸ��Ϣ<br>
		 * ע�⣬�÷�����������ָ����ŵĿⷿ�ṹ��Ϣ���������ơ�����ȣ������¼��ⷿ�ṹ������в�ѯ
		 * @param storeroomStructure ���ز��ҳɹ��Ŀⷿ�ṹ��Ϣ,��������븳ֵ
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean findByBarcode(StoreroomStructure storeroomStructure,ErrInfo pErrInfo);
		
		
		
		/**
		 * Dao�ӿڶ��壺���ݿⷿ�ṹ��Ų��ҿⷿ�ṹ��ϸ��Ϣ<br>
		 * ע�⣬�÷�����������ָ����ŵĿⷿ�ṹ��Ϣ���������ơ�����ȣ������¼��ⷿ�ṹ������в�ѯ
		 * @param storeroomStructure ���ز��ҳɹ��Ŀⷿ�ṹ��Ϣ,ID���븳ֵ
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean findByID(StoreroomStructure storeroomStructure,ErrInfo pErrInfo);
		

		/**
		 * ���ݿⷿ�ṹ��Ų������¼��ⷿ�ṹ<br>
		 * ע�⣬�÷�������������һ���ⷿ�ṹ�����¼��Ĳ�����в�ѯ
		 * @param storeroomStructureID ָ���Ŀⷿ�ṹ���
		 * @param children ���ز��ҳɹ����¼��ⷿ�ṹ
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean findChildrenByID(int storeroomStructureID,List<StoreroomStructure> children,ErrInfo pErrInfo);
		
		
		

	}

