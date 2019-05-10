/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ArchivesCertificateRegister;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.DateQuerycondition;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ������֤�Ǽǹ������Ľӿڶ���
 *
 */
public interface IArchivesCertificateRegisterManageService {

	/**
	 * ���һ���µĵ�����֤�Ǽ�
	 * @param pArchivesCertificateRegister ����ӵĵ�����֤�Ǽ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveArchivesCertificateRegister(ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���ĵ�����֤�Ǽ�
	 * @param pArchivesCertificateRegister Ҫɾ���ĵ�����֤�Ǽǣ���Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteArchivesCertificateRegister(ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���ĵ�����֤�Ǽ�
	 * @param pArchivesCertificateRegister �޸ĺ�ĵ�����֤�Ǽ���Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateArchivesCertificateRegister(ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo);

	/**
	 * �������еĵ�����֤�Ǽ���Ϣ
	 * @param pArchivesCertificateRegisters ���ز��ҳɹ��ĵ�����֤�ǼǼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesCertificateRegisters(List<ArchivesCertificateRegister> pArchivesCertificateRegisters, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҵ�����֤�Ǽ���Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param pArchivesCertificateRegister ���ز��ҳɹ��ĵ�����֤�Ǽ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesCertificateRegisterByID(int pID, ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo);

	/**
	 * ���ҷ��ϲ�ѯ�����ľ����˵ĵ�����֤�Ǽ���Ϣ����
	 * @param dateQuerycondition ��ѯ����
	 * @param pArchivesCertificateRegisters ���ز��ҳɹ��ĵ�����֤�ǼǼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesCertificateRegistersByCondition(DateQuerycondition dateQuerycondition,List<ArchivesCertificateRegister> pArchivesCertificateRegisters, ErrInfo pErrInfo);
	
	/**
	 * ���ҷ��ϲ�ѯ�����������˵ĵ�����֤�Ǽ���Ϣ���ϣ� ��ҳ��ʾ
	 * @param pManagerUserID �����˱��
	 * @param dateQuerycondition ��ѯ����
	 * @param dataPageInfo ��ѯ��ҳ����
	 * @param pArchivesCertificateRegisters ���ز��ҳɹ��ĵ�����֤�ǼǼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesCertificateRegistersByCondition(int pManagerUserID,DateQuerycondition dateQuerycondition,DataPageInfo dataPageInfo,List<ArchivesCertificateRegister> pArchivesCertificateRegisters, ErrInfo pErrInfo);
}

