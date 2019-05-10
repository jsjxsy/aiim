package com.orifound.aiim.dal.dao;

import java.util.List;
import com.orifound.aiim.entity.ArchivesUsePersonInfo;
import com.orifound.aiim.entity.ErrInfo;



/**
 * 档案利用人信息表的DAO接口定义
 *
 */
public interface IArchivesUsePersonInfoDao {

	/**
	 * Dao接口定义：添加档案利用人信息
	 * @param archivesUsePersonInfo 要添加的档案利用人信息 <br>添加成功后的ID值赋给该对象
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean add(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的档案利用人信息
	 * @param archivesUsePersonInfo 要删除的档案利用人信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的档案利用人信息
	 * @param archivesUsePersonInfo 要更新的档案利用人信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的档案利用人信息
	 * @param archivesUsePersonInfo 返回查找成功的档案利用人信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ArchivesUsePersonInfo> archivesUsePersonInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找档案利用人信息
	 * @param pID 指定的唯一标识
	 * @param archivesUsePersonInfo 返回查找成功的档案利用人信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：根据唯名字查找档案利用人信息表
	 * @param pID 指定的唯一标识
	 * @param archivesUsePersonInfos 返回查找成功的档案利用人信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByIDCardNo(String IDCardNo,List<ArchivesUsePersonInfo> archivesUsePersonInfos, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯名字查找档案利用人信息表
	 * @param pID 指定的唯一标识
	 * @param archivesUsePersonInfos 返回查找成功的档案利用人信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByName(String name,List<ArchivesUsePersonInfo> archivesUsePersonInfos, ErrInfo pErrInfo);
}

