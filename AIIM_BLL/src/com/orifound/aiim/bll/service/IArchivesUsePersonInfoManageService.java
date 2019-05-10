/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;
import com.orifound.aiim.entity.ArchivesUsePersonInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案利用人信息管理与服务接口定义
 * @author Administrator
 *
 */
public interface IArchivesUsePersonInfoManageService {
	/**
	 * 添加档案利用人信息
	 * @param archivesUsePersonInfo 要添加的档案利用人信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean add(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo);

	/**
	 *删除指定的档案利用人信息
	 * @param archivesUsePersonInfo 要删除的档案利用人信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo);

	/**
	 * 更新指定的档案利用人信息
	 * @param archivesUsePersonInfo 要更新的档案利用人信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo);

	/**
	 * 查找所有的档案利用人信息
	 * @param archivesUsePersonInfo 返回查找成功的档案利用人信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ArchivesUsePersonInfo> archivesUsePersonInfo, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找档案利用人信息
	 * @param pID 指定的唯一标识
	 * @param archivesUsePersonInfo 返回查找成功的档案利用人信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo);
	
	/**
	 * 根据唯名字查找档案利用人信息表<br>
	 * 输入为空时查询所有利用人信息
	 * @param pID 指定的唯一标识
	 * @param archivesUsePersonInfos 返回查找成功的档案利用人信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByIDCardNo(String IDCardNo,List<ArchivesUsePersonInfo> archivesUsePersonInfos, ErrInfo pErrInfo);

	/**
	 * 根据唯名字查找档案利用人信息表<br>
	 * 输入为空时查询所有利用人信息
	 * @param pID 指定的唯一标识
	 * @param archivesUsePersonInfos 返回查找成功的档案利用人信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByName(String name,List<ArchivesUsePersonInfo> archivesUsePersonInfos, ErrInfo pErrInfo);
}
