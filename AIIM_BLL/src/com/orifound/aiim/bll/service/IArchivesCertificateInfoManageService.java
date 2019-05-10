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
 * 档案出证明细情况管理服务的接口定义
 *
 */
public interface IArchivesCertificateInfoManageService {

	/**
	 * 出证收费登记
	 * @param certificateRegister 要添加的档案登记信息
	 * @param archivesCertificateInfos 要添加的档案出证明细信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveArchivesCertificateInfo(ArchivesCertificateRegister certificateRegister, List<ArchivesCertificateInfo> archivesCertificateInfos, ErrInfo pErrInfo);

	/**
	 * 删除指定的档案出证明细情况
	 * @param archivesCertificateInfo 要删除的档案出证明细情况，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteArchivesCertificateInfo(ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo);

	/**
	 * 修改指定的档案出证明细情况
	 * @param archivesCertificateInfo 修改后的档案出证明细情况信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateArchivesCertificateInfo(ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo);
	
	/**
	 * 更新指定的档案出证信息学号
	 * @param archivesCertificateInfo 要更新的档案出证信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateArchivesCertificateInfoXH(ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo);

	/**
	 * 查找所有的档案出证明细情况信息
	 * @param archivesCertificateInfos 返回查找成功的档案出证明细情况集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesCertificateInfos(List<ArchivesCertificateInfo> archivesCertificateInfos,
			ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找档案出证明细情况信息
	 * @param pID 指定的唯一标识
	 * @param archivesCertificateInfo 返回查找成功的档案出证明细情况信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesCertificateInfoByID(int pID, ArchivesCertificateInfo archivesCertificateInfo,
			ErrInfo pErrInfo);
	
	/**
	 * 查询所有档案出证类型
	 * @param certificateTypes 返回查找成功的档案出证类型信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findCertificateTypes(List<CertificateType> certificateTypes, ErrInfo pErrInfo);
	
	/**
	 * 根据唯出证业务登记编号查找档案出证明细情况信息
	 * @param pCertificateRegID 指定的出证登记编号
	 * @param archivesCertificateInfo 返回查找成功的档案出证明细情况信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesCertificateInfoByCertificateRegID(int pCertificateRegID, ArchivesCertificateInfo archivesCertificateInfo,ErrInfo pErrInfo);
	
	
	
	/**
	 * 根据条件查询档案出证登记信息
	 * @param queryString	查询条件:利用人姓名personName、利用人证件号cardId
	 * @param archivesCertificateInfos	返回档案出证登记信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesCertificateRegistersByQuery(Map<String, String> queryString, List<ArchivesCertificateRegister> archivesCertificateRegisters, ErrInfo pErrInfo);

	/**
	 * 根据出证登记id查找档案出证信息
	 * @param certificateRegID 出证登记id
	 * @param archivesCertificateInfo 返回查找成功的档案出证信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesCertificateInfosByRegisterId(int certificateRegID, List<ArchivesCertificateInfo> archivesCertificateInfos, ErrInfo pErrInfo);
}
