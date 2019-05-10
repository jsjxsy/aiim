package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.StoreAddressInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案上架位置信息表的DAO接口定义
 *
 */
public interface IStoreAddressInfoDao {

	/**
	 * Dao接口定义：添加档案上架位置信息
	 * @param storeAddressInfo 要添加的档案上架位置信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean add(StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的档案上架位置信息
	 * @param storeAddressInfo 要删除的档案上架位置信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的档案上架位置信息
	 * @param storeAddressInfo 要更新的档案上架位置信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找设备条形码查找档案上架位置信息集合
	 * @param storeAddressInfos 返回查找成功的档案上架位置信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByStoreAddressID(int storeAddressID,List<StoreAddressInfo> storeAddressInfos, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据盒条形码查找档案上架位置信息
	 * @param pID 指定的唯一标识
	 * @param storeAddressInfo 返回查找成功的档案上架位置信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByBoxBarcode(StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo);
	
	/**
	 * 判断盒条形码存在
	 * @param archivesBoxBarcode 盒条形码
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 存在返回true,否则返回false
	 */
	boolean checkBoxBarcodeExist(String archivesBoxBarcode,ErrInfo pErrInfo);

}

