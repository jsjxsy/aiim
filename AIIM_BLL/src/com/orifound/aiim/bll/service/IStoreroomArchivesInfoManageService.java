package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StoreroomArchivesInfo;
/**
 * 库房档案信息的管理服务接口
 * @author Administrator
 *
 */
public interface IStoreroomArchivesInfoManageService {
	/**
	 * 添加档案库房位置信息
	 * @param storeroomArchivesInfo 要添加的档案库房位置信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);

	/**
	 * 删除指定的档案库房位置信息
	 * @param storeroomArchivesInfo 要删除的档案库房位置信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);

	/**
	 * 更新指定的档案库房位置信息
	 * @param storeroomArchivesInfo 要更新的档案库房位置信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);

	/**
	 * 查找所有的档案库房位置信息
	 * @param storeroomArchivesInfos 返回查找成功的档案库房位置信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<StoreroomArchivesInfo> storeroomArchivesInfos, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找档案库房位置信息
	 * @param storeroomArchivesInfo 返回查找成功的档案库房位置信息,archivesBarcode必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByBarcode(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);
	
	/**
	 * 根据档案分类编号和NBXH查找档案库房位置信息
	 * @param storeroomArchivesInfo 返回查找成功的档案库房位置信息,档案分类编号和NBXH必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findStoreroomArchivesInfoByNBXH(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);
	
	 
	/**
	 * 更新指定NBXH的档案馆藏状态
	 * @param storeroomArchivesInfo 要更新的档案库房位置信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateStoreStatusByArchivesBarcode(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);
	
	/**
	 * 更新档案盒条码
	 * @param archivesBarcodes 要更新的档案条码
	 * @param archivesBoxBarcode 档案盒条码
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateArchivesBoxBarcode(List<String> archivesBarcodes, String archivesBoxBarcode, ErrInfo pErrInfo);

	/**
	 * 根据盒条码查找档案信息
	 * @param archivesBoxBarcode 档案盒条码
	 * @param storeroomArchivesInfos 返回的库房档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByBoxBarcode(String archivesBoxBarcode, List<StoreroomArchivesInfo> storeroomArchivesInfos, ErrInfo pErrInfo);
}
