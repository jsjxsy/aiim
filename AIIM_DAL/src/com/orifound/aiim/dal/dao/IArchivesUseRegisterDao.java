package com.orifound.aiim.dal.dao;
	import java.util.List;
	import com.orifound.aiim.entity.ArchivesUseRegister;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;

	/**
	 * �������õǼǱ��DAO�ӿڶ���
	 *
	 */
	public interface IArchivesUseRegisterDao {

		/**
		 * Dao�ӿڶ��壺��ӵ������õǼ���Ϣ
		 * @param  archivesUseRegisterҪ��ӵĵ������õǼ���Ϣ
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean add(ArchivesUseRegister archivesUseRegister, ErrInfo pErrInfo);

		/**
		 * Dao�ӿڶ��壺ɾ��ָ���ĵ������õǼ���Ϣ
		 * @param  archivesUseRegisterҪɾ���ĵ������õǼ���Ϣ ����ID���븳ֵ
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean delete(ArchivesUseRegister archivesUseRegister, ErrInfo pErrInfo);

		/**
		 * Dao�ӿڶ��壺����ָ���ĵ������õǼ���Ϣ
		 * @param  archivesUseRegisterҪ���µĵ������õǼ���Ϣ
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean update(ArchivesUseRegister archivesUseRegister, ErrInfo pErrInfo);

		/**
		 * Dao�ӿڶ��壺����������ѯ�������õǼ���Ϣ
		 * @param querySQL ���ڲ�ѯSQL��������AND��ͷ
		 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
		 * @param archivesUseRegisters ���ز��ҳɹ��ĵ������õǼ���Ϣ����
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean findByConditions(String querySQL,DataPageInfo dataPageInfo,List<ArchivesUseRegister> archivesUseRegisters, ErrInfo pErrInfo);

		/**
		 * Dao�ӿڶ��壺����Ψһ��ʶ���ҵ������õǼ���Ϣ		
		 * @param  archivesUseRegister ���ز��ҳɹ��ĵ������õǼ���Ϣ����ID���븳ֵ
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean findByID( ArchivesUseRegister archivesUseRegister, ErrInfo pErrInfo);
		
		/**
		 * Dao�ӿڶ��壺��ѯ���й������õǼ���Ϣ<br>���а����û���Ϣ
		 * @param archivesUseRegisters 
		 * @param pErrInfo
		 * @return
		 */
		boolean findAllExpiredRegister(DataPageInfo dataPageInfo, List<ArchivesUseRegister> archivesUseRegisters, ErrInfo pErrInfo);

		/**
		 * Dao�ӿڶ��壺��ѯ���н�Ҫ�������õǼ���Ϣ<br>���а����û���Ϣ
		 * @param daysNum ����Ԥ��Ҫ��ǰ������
		 * @param dataPageInfo
		 * @param archivesUseRegisters
		 * @param pErrInfo
		 * @return
		 */
		boolean findAllExpiringRegister(int daysNum,DataPageInfo dataPageInfo, List<ArchivesUseRegister> archivesUseRegisters, ErrInfo pErrInfo);

	}
