package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.StoreAddressInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案上架位置信息管理服务的接口定义
 *
 */
public interface IStoreAddressInfoManageService {

	/**
	 * 添加一个新的档案上架位置信息
	 * @param  storeAddressInfo新添加的档案上架位置信息信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean addStoreAddressInfo(StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo);

	/**
	 * 删除指定的档案上架位置信息
	 * @param  要删除的档案上架位置信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteStoreAddressInfo(StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo);

	/**
	 * 修改指定的档案上架位置信息
	 * @param  修改后的档案上架位置信息信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateStoreAddressInfo(StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo);

	/**
	 * 查找所有的档案上架位置信息信息
	 * @param s 返回查找成功的档案上架位置信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findStoreAddressInfos(List<StoreAddressInfo> storeAddressInfo, ErrInfo pErrInfo);

	/**
	 * 根据盒条形码查找档案上架位置信息信息
	 * @param pID 指定的唯一标识
	 * @param  返回查找成功的档案上架位置信息信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findStoreAddressInfoByBoxBarcode( StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo);

}

