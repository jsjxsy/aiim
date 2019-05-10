/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.ArchivesCertificateInfo;
import com.orifound.aiim.entity.ArchivesCertificateRegister;
import com.orifound.aiim.entity.CertificateType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ������֤��ϸ����������Ľӿڶ���
 *
 */
public interface IArchivesCertificateInfoManageService {

	/**
	 * ��֤�շѵǼ�
	 * @param certificateRegister Ҫ��ӵĵ����Ǽ���Ϣ
	 * @param archivesCertificateInfos Ҫ��ӵĵ�����֤��ϸ��Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveArchivesCertificateInfo(ArchivesCertificateRegister certificateRegister, List<ArchivesCertificateInfo> archivesCertificateInfos, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���ĵ�����֤��ϸ���
	 * @param archivesCertificateInfo Ҫɾ���ĵ�����֤��ϸ�������Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteArchivesCertificateInfo(ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���ĵ�����֤��ϸ���
	 * @param archivesCertificateInfo �޸ĺ�ĵ�����֤��ϸ�����Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateArchivesCertificateInfo(ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo);
	
	/**
	 * ����ָ���ĵ�����֤��Ϣѧ��
	 * @param archivesCertificateInfo Ҫ���µĵ�����֤��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateArchivesCertificateInfoXH(ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo);

	/**
	 * �������еĵ�����֤��ϸ�����Ϣ
	 * @param archivesCertificateInfos ���ز��ҳɹ��ĵ�����֤��ϸ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesCertificateInfos(List<ArchivesCertificateInfo> archivesCertificateInfos,
			ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҵ�����֤��ϸ�����Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param archivesCertificateInfo ���ز��ҳɹ��ĵ�����֤��ϸ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesCertificateInfoByID(int pID, ArchivesCertificateInfo archivesCertificateInfo,
			ErrInfo pErrInfo);
	
	/**
	 * ��ѯ���е�����֤����
	 * @param certificateTypes ���ز��ҳɹ��ĵ�����֤������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findCertificateTypes(List<CertificateType> certificateTypes, ErrInfo pErrInfo);
	
	/**
	 * ����Ψ��֤ҵ��ǼǱ�Ų��ҵ�����֤��ϸ�����Ϣ
	 * @param pCertificateRegID ָ���ĳ�֤�ǼǱ��
	 * @param archivesCertificateInfo ���ز��ҳɹ��ĵ�����֤��ϸ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesCertificateInfoByCertificateRegID(int pCertificateRegID, ArchivesCertificateInfo archivesCertificateInfo,ErrInfo pErrInfo);
	
	
	
	/**
	 * ����������ѯ������֤�Ǽ���Ϣ
	 * @param queryString	��ѯ����:����������personName��������֤����cardId
	 * @param archivesCertificateInfos	���ص�����֤�Ǽ���Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesCertificateRegistersByQuery(Map<String, String> queryString, List<ArchivesCertificateRegister> archivesCertificateRegisters, ErrInfo pErrInfo);

	/**
	 * ���ݳ�֤�Ǽ�id���ҵ�����֤��Ϣ
	 * @param certificateRegID ��֤�Ǽ�id
	 * @param archivesCertificateInfo ���ز��ҳɹ��ĵ�����֤��Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesCertificateInfosByRegisterId(int certificateRegID, List<ArchivesCertificateInfo> archivesCertificateInfos, ErrInfo pErrInfo);
}
