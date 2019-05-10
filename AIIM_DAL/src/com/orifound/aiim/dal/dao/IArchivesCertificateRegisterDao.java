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
 * 档案出证登记表的DAO接口定义
 *
 */
public interface IArchivesCertificateRegisterDao {

	/**
	 * Dao接口定义：添加档案出证登记
	 * @param pArchivesCertificateRegister 要添加的档案出证登记
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的档案出证登记
	 * @param pArchivesCertificateRegister 要删除的档案出证登记
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的档案出证登记
	 * @param pArchivesCertificateRegister 要更新的档案出证登记
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的档案出证登记
	 * @param pArchivesCertificateRegisters 返回查找成功的档案出证登记集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ArchivesCertificateRegister> pArchivesCertificateRegisters, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找档案出证登记
	 * @param pID 指定的唯一标识
	 * @param pArchivesCertificateRegister 返回查找成功的档案出证登记
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo);
	
	/**
	 * 查询符合条件的经办人的档案出证登记信息集合
	 * @param dateQuerycondition 查询条件
	 * @param pArchivesCertificateRegister 返回查找成功的档案出证登记
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByCondition(DateQuerycondition dateQuerycondition,List<ArchivesCertificateRegister> pArchivesCertificateRegisters, ErrInfo pErrInfo);

	/**
	 * 查询符合条件的利用人的档案出证登记信息集合
	 * @param pManagerUserID经办人编号
	 * @param dateQuerycondition 查询条件
	 * @param dataPageInfo 分页集合
	 * @param pArchivesCertificateRegister 返回查找成功的档案出证登记
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByCondition(int pManagerUserID,DateQuerycondition dateQuerycondition,DataPageInfo dataPageInfo,List<ArchivesCertificateRegister> pArchivesCertificateRegisters, ErrInfo pErrInfo);
}
