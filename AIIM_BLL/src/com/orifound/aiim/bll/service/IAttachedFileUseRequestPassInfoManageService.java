package com.orifound.aiim.bll.service;

import com.orifound.aiim.entity.AttachedFileUseRequestPassInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;

public interface IAttachedFileUseRequestPassInfoManageService {

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
		 * ���ԭ��������ϸ��Ϣ
		 * @param attachedFileUseRequestPassInfo Ҫ��ӵ�ԭ��������ϸ��Ϣ
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean addAttachedFileUseRequestPassInfo(AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo, ErrInfo pErrInfo);

		/**
		 * ɾ��ָ����ԭ��������ϸ��Ϣ
		 * @param attachedFileUseRequestPassInfo Ҫɾ����ԭ��������ϸ��Ϣ
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean deleteAttachedFileUseRequestPassInfo(AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo, ErrInfo pErrInfo);

		
		
		/**
		 * ����Ψһ��ʶ����ԭ��������ϸ��Ϣ
		 * @param attachedFileUseRequestPassInfo ���ز��ҳɹ���ԭ��������ϸ��Ϣ
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean findAttachedFileUseRequestPassInfoByID(AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo, ErrInfo pErrInfo);

}
