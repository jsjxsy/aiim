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
 * 档案出证信息表的DAO接口定义
 *
 */
public interface IArchivesCertificateInfoDao {

	/**
	 * Dao接口定义：出证收费登记
	 * @param certificateRegister 要添加的档案登记信息
	 * @param archivesCertificateInfos 要添加的档案出证明细信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ArchivesCertificateRegister certificateRegister, List<ArchivesCertificateInfo> archivesCertificateInfos, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的档案出证信息
	 * @param archivesCertificateInfo 要删除的档案出证信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的档案出证信息
	 * @param archivesCertificateInfo 要更新的档案出证信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：更新指定的档案出证信息学号
	 * @param archivesCertificateInfo 要更新的档案出证信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateXH(ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的档案出证信息
	 * @param archivesCertificateInfos 返回查找成功的档案出证信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ArchivesCertificateInfo> archivesCertificateInfos, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找档案出证信息
	 * @param pID 指定的唯一标识
	 * @param archivesCertificateInfo 返回查找成功的档案出证信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：根据出证登记id查找档案出证信息
	 * @param certificateRegID 出证登记id
	 * @param archivesCertificateInfos 返回查找成功的档案出证信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByRegisterId(int certificateRegID, List<ArchivesCertificateInfo> archivesCertificateInfos, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查询所有档案出证类型
	 * @param certificateTypes 返回查找成功的档案出证类型信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findCertificateTypes(List<CertificateType> certificateTypes, ErrInfo pErrInfo);

	
	/**
	 * Dao接口定义：根据出证登记编号查找档案出证信息
	 * @param pCertificateRegID 出证登记编号
	 * @param archivesCertificateInfo 返回查找成功的档案出证信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByCertificateRegID(int pCertificateRegID,ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo);

	
	/**
	 * 根据条件查询档案出证登记信息
	 * @param queryString	查询条件:利用人姓名personName、利用人证件号cardId
	 * @param archivesCertificateInfos	返回档案出证登记信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesCertificateRegistersByQuery(Map<String, String> queryString, List<ArchivesCertificateRegister> archivesCertificateRegisters, ErrInfo pErrInfo);

}
