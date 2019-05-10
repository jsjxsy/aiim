/**
 * 
 */
package com.orifound.aiim.dal.dao;

import com.orifound.aiim.entity.AttachedFileUseRequestPassInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;

/**
 * ԭ����������ͨ����Ϣ��DAO�ӿ�
 *
 */
public interface IAttachedFileUseRequestPassInfoDao
{
		/**
		 * ����ָ�����û�����ԭ������ͨ����Ϣ���ڵ���Ч����
		 * @param pUserID �����û��ı��
		 * @param pArchivesTypeID ����������
		 * @param pNBXH �������ڲ����
		 * @param pCount ���ز��ҳɹ���������
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean findCountByRequestPassInfo(int pUserID,int pArchivesTypeID,int pNBXH,IntegerEx pCount,ErrInfo pErrInfo);

	
		/**
		 * Dao�ӿڶ��壺���ԭ��������ϸ��Ϣ
		 * @param attachedFileUseRequestPassInfo Ҫ��ӵ�ԭ��������ϸ��Ϣ
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean add(AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo, ErrInfo pErrInfo);

		/**
		 * Dao�ӿڶ��壺ɾ��ָ����ԭ��������ϸ��Ϣ
		 * @param attachedFileUseRequestPassInfo Ҫɾ����ԭ��������ϸ��Ϣ
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean delete(AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo, ErrInfo pErrInfo);

		
		
		/**
		 * Dao�ӿڶ��壺����Ψһ��ʶ����ԭ��������ϸ��Ϣ
		 * @param attachedFileUseRequestPassInfo ���ز��ҳɹ���ԭ��������ϸ��Ϣ
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean findByID(AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo, ErrInfo pErrInfo);

	

}
