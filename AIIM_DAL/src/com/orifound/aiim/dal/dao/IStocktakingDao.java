package com.orifound.aiim.dal.dao;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StocktakingAddressBoxDetail;
import com.orifound.aiim.entity.StocktakingArchivesDetail;

/**
 * 库房盘点Dao
 * @author Administrator
 *
 */
public interface IStocktakingDao {
	
	/**
	 * Dao接口定义：添加库房盘点的设备位置与档案盒详细
	 * @param stocktakingAddressBoxDetail 要添加的库房盘点的设备位置与档案盒详细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean addAddressBoxDetail(StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：更新库房盘点的设备位置与档案盒详细
	 * @param stocktakingAddressBoxDetail 要添加的库房盘点的设备位置与档案盒详细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateAddressBoxDetail(StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：判断档案盒条形码是否已经存在
	 * @param stocktakingAddressBoxDetail 要添加的库房盘点的设备位置与档案盒详细,archivesBoxBarcode必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 存在则返回true，不存在则返回false
	 */
	boolean checkAddressBoxDetailExist(StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：添加库房盘点的档案盒与档案卷详细
	 * @param stocktakingArchivesDetail 要添加的库房盘点的档案盒与档案卷详细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean addArchivesDetail(StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：更新库房盘点的档案盒与档案卷详细
	 * @param stocktakingArchivesDetail 要添加的库房盘点的档案盒与档案卷详细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateArchivesDetail(StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：判断档案卷条形码是否存在
	 * @param stocktakingArchivesDetail 要添加的库房盘点的档案盒与档案卷详细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 存在则返回true，不存在则返回false
	 */
	boolean checkArchivesDetailExist(StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo);
	
	
	
	
}
