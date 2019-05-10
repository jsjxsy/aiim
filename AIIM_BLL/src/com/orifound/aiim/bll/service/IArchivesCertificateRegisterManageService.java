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
 * 档案出证登记管理服务的接口定义
 *
 */
public interface IArchivesCertificateRegisterManageService {

	/**
	 * 添加一个新的档案出证登记
	 * @param pArchivesCertificateRegister 新添加的档案出证登记信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveArchivesCertificateRegister(ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo);

	/**
	 * 删除指定的档案出证登记
	 * @param pArchivesCertificateRegister 要删除的档案出证登记，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteArchivesCertificateRegister(ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo);

	/**
	 * 修改指定的档案出证登记
	 * @param pArchivesCertificateRegister 修改后的档案出证登记信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateArchivesCertificateRegister(ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo);

	/**
	 * 查找所有的档案出证登记信息
	 * @param pArchivesCertificateRegisters 返回查找成功的档案出证登记集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesCertificateRegisters(List<ArchivesCertificateRegister> pArchivesCertificateRegisters, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找档案出证登记信息
	 * @param pID 指定的唯一标识
	 * @param pArchivesCertificateRegister 返回查找成功的档案出证登记信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesCertificateRegisterByID(int pID, ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo);

	/**
	 * 查找符合查询条件的经办人的档案出证登记信息集合
	 * @param dateQuerycondition 查询条件
	 * @param pArchivesCertificateRegisters 返回查找成功的档案出证登记集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesCertificateRegistersByCondition(DateQuerycondition dateQuerycondition,List<ArchivesCertificateRegister> pArchivesCertificateRegisters, ErrInfo pErrInfo);
	
	/**
	 * 查找符合查询条件的利用人的档案出证登记信息集合， 分页显示
	 * @param pManagerUserID 经办人编号
	 * @param dateQuerycondition 查询条件
	 * @param dataPageInfo 查询分页集合
	 * @param pArchivesCertificateRegisters 返回查找成功的档案出证登记集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesCertificateRegistersByCondition(int pManagerUserID,DateQuerycondition dateQuerycondition,DataPageInfo dataPageInfo,List<ArchivesCertificateRegister> pArchivesCertificateRegisters, ErrInfo pErrInfo);
}

