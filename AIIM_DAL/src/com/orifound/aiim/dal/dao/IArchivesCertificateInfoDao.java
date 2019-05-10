/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.ArchivesCertificateInfo;
import com.orifound.aiim.entity.ArchivesCertificateRegister;
import com.orifound.aiim.entity.CertificateType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ������֤��Ϣ���DAO�ӿڶ���
 *
 */
public interface IArchivesCertificateInfoDao {

	/**
	 * Dao�ӿڶ��壺��֤�շѵǼ�
	 * @param certificateRegister Ҫ��ӵĵ����Ǽ���Ϣ
	 * @param archivesCertificateInfos Ҫ��ӵĵ�����֤��ϸ��Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ArchivesCertificateRegister certificateRegister, List<ArchivesCertificateInfo> archivesCertificateInfos, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���ĵ�����֤��Ϣ
	 * @param archivesCertificateInfo Ҫɾ���ĵ�����֤��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���ĵ�����֤��Ϣ
	 * @param archivesCertificateInfo Ҫ���µĵ�����֤��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺����ָ���ĵ�����֤��Ϣѧ��
	 * @param archivesCertificateInfo Ҫ���µĵ�����֤��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateXH(ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĵ�����֤��Ϣ
	 * @param archivesCertificateInfos ���ز��ҳɹ��ĵ�����֤��Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ArchivesCertificateInfo> archivesCertificateInfos, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҵ�����֤��Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param archivesCertificateInfo ���ز��ҳɹ��ĵ�����֤��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺���ݳ�֤�Ǽ�id���ҵ�����֤��Ϣ
	 * @param certificateRegID ��֤�Ǽ�id
	 * @param archivesCertificateInfos ���ز��ҳɹ��ĵ�����֤��Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByRegisterId(int certificateRegID, List<ArchivesCertificateInfo> archivesCertificateInfos, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺��ѯ���е�����֤����
	 * @param certificateTypes ���ز��ҳɹ��ĵ�����֤������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findCertificateTypes(List<CertificateType> certificateTypes, ErrInfo pErrInfo);

	
	/**
	 * Dao�ӿڶ��壺���ݳ�֤�ǼǱ�Ų��ҵ�����֤��Ϣ
	 * @param pCertificateRegID ��֤�ǼǱ��
	 * @param archivesCertificateInfo ���ز��ҳɹ��ĵ�����֤��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByCertificateRegID(int pCertificateRegID,ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo);

	
	/**
	 * ����������ѯ������֤�Ǽ���Ϣ
	 * @param queryString	��ѯ����:����������personName��������֤����cardId
	 * @param archivesCertificateInfos	���ص�����֤�Ǽ���Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesCertificateRegistersByQuery(Map<String, String> queryString, List<ArchivesCertificateRegister> archivesCertificateRegisters, ErrInfo pErrInfo);

}
