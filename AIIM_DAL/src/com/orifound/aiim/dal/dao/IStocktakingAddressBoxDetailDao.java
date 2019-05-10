package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.StocktakingAddressBoxDetail;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 库房盘点的设备位置与档案盒详细表的DAO接口定义
 *
 */
public interface IStocktakingAddressBoxDetailDao {

	/**
	 * Dao接口定义：添加库房盘点的设备位置与档案盒详细
	 * @param stocktakingAddressBoxDetail 要添加的库房盘点的设备位置与档案盒详细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean add(StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的库房盘点的设备位置与档案盒详细
	 * @param stocktakingAddressBoxDetail 要删除的库房盘点的设备位置与档案盒详细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的库房盘点的设备位置与档案盒详细
	 * @param stocktakingAddressBoxDetail 要更新的库房盘点的设备位置与档案盒详细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的库房盘点的设备位置与档案盒详细
	 * @param stocktakingAddressBoxDetails 返回查找成功的库房盘点的设备位置与档案盒详细集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<StocktakingAddressBoxDetail> stocktakingAddressBoxDetails, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找库房盘点的设备位置与档案盒详细
	 * @param pID 指定的唯一标识
	 * @param stocktakingAddressBoxDetail 返回查找成功的库房盘点的设备位置与档案盒详细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo);

}
