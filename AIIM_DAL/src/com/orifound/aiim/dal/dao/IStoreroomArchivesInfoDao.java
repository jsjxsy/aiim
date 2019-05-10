package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StoreroomArchivesInfo;


/**
 * 库房档案信息表 的DAO接口定义
 *
 */
public interface IStoreroomArchivesInfoDao {

	/**
	 * Dao接口定义：添加库房档案信息
	 * @param storeroomArchivesInfo 要添加的档案库房位置信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的库房档案信息
	 * @param storeroomArchivesInfo 要删除的库房档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的库房档案信息
	 * @param storeroomArchivesInfo 要更新的库房档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的库房档案信息
	 * @param storeroomArchivesInfos 返回查找成功的库房档案信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<StoreroomArchivesInfo> storeroomArchivesInfos, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据档案条形码查找库房档案信息
	 * @param NBXH 指定的唯一标识
	 * @param storeroomArchivesInfo 返回查找成功的库房档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByBarcode(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);
	
	
	/**
	 * Dao接口定义：根据档案分类编号和NBXH查找库房档案信息
	 * @param NBXH 指定的唯一标识
	 * @param storeroomArchivesInfo 返回查找成功的库房档案信息，档案分类编号和NBXH必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByNBXH(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);
	 
	/**
	 * Dao接口定义：更新指定NBXH的档案馆藏状态
	 * @param storeroomArchivesInfo 要更新的库房档案信息 archivesBarcode,storeStatus必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateStoreStatusByArchivesBarcode(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：添加库房档案信息
	 * @param storeroomArchivesInfo 要添加的档案库房位置信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean add(String barcode,  ArchivesType archivesType, ErrInfo pErrInfo);

	/**
	 * 根据盒条码查找库房档案信息
	 * @param archivesBoxBarcode 档案盒条码
	 * @param storeroomArchivesInfos 返回的库房档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByBoxBarcode(String archivesBoxBarcode, List<StoreroomArchivesInfo> storeroomArchivesInfos, ErrInfo pErrInfo);

	/**
	 * 更新档案所在盒条码
	 * @param archivesBarcodes
	 * @param archivesBoxBarcode
	 * @param pErrInfo
	 * @return
	 */
	boolean updateArchivesBoxBarcode(List<String> archivesBarcodes, String archivesBoxBarcode, ErrInfo pErrInfo);
	
	/**
	 * 查询库房档案信息
	 * @param NBXH
	 * @param archivesTypeID
	 * @param storeroomArchivesInfo
	 * @param pErrInfo
	 * @return
	 */
	boolean find(int NBXH, int archivesTypeID,StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo);

	/**
	 * 更新档案的条码
	 * @param barcode
	 * @param nbxh
	 * @param archivesType
	 * @param pErrInfo
	 * @return
	 */
	boolean updateBarCode(String barcode, int nbxh, ArchivesType archivesType, ErrInfo pErrInfo);
	
	/**
	 * 根据查询条件查询库房档案信息
	 * @param whereSQL WHERE条件
	 * @param storeroomArchivesInfos 返回的库房档案信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return
	 */
	boolean findByCondition(String whereSQL ,List<StoreroomArchivesInfo> storeroomArchivesInfos ,ErrInfo pErrInfo);
}
