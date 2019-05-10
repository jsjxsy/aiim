/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ArchivesCertificateRegister;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.DateQuerycondition;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ������֤�ǼǱ��DAO�ӿڶ���
 *
 */
public interface IArchivesCertificateRegisterDao {

	/**
	 * Dao�ӿڶ��壺��ӵ�����֤�Ǽ�
	 * @param pArchivesCertificateRegister Ҫ��ӵĵ�����֤�Ǽ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���ĵ�����֤�Ǽ�
	 * @param pArchivesCertificateRegister Ҫɾ���ĵ�����֤�Ǽ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���ĵ�����֤�Ǽ�
	 * @param pArchivesCertificateRegister Ҫ���µĵ�����֤�Ǽ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĵ�����֤�Ǽ�
	 * @param pArchivesCertificateRegisters ���ز��ҳɹ��ĵ�����֤�ǼǼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ArchivesCertificateRegister> pArchivesCertificateRegisters, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҵ�����֤�Ǽ�
	 * @param pID ָ����Ψһ��ʶ
	 * @param pArchivesCertificateRegister ���ز��ҳɹ��ĵ�����֤�Ǽ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo);
	
	/**
	 * ��ѯ���������ľ����˵ĵ�����֤�Ǽ���Ϣ����
	 * @param dateQuerycondition ��ѯ����
	 * @param pArchivesCertificateRegister ���ز��ҳɹ��ĵ�����֤�Ǽ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByCondition(DateQuerycondition dateQuerycondition,List<ArchivesCertificateRegister> pArchivesCertificateRegisters, ErrInfo pErrInfo);

	/**
	 * ��ѯ���������������˵ĵ�����֤�Ǽ���Ϣ����
	 * @param pManagerUserID�����˱��
	 * @param dateQuerycondition ��ѯ����
	 * @param dataPageInfo ��ҳ����
	 * @param pArchivesCertificateRegister ���ز��ҳɹ��ĵ�����֤�Ǽ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByCondition(int pManagerUserID,DateQuerycondition dateQuerycondition,DataPageInfo dataPageInfo,List<ArchivesCertificateRegister> pArchivesCertificateRegisters, ErrInfo pErrInfo);
}
