package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.StocktakingInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 库房盘点工作信息表的DAO接口定义
 *
 */
public interface IStocktakingInfoDao {

	/**
	 * Dao接口定义：添加库房盘点工作信息
	 * @param stocktakingInfo 要添加的库房盘点工作信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean add(StocktakingInfo stocktakingInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的库房盘点工作信息
	 * @param stocktakingInfo 要删除的库房盘点工作信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(StocktakingInfo stocktakingInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的库房盘点状态
	 * @param stocktakingInfo 要更新的库房盘点工作信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(StocktakingInfo stocktakingInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的库房盘点工作信息
	 * @param stocktakingInfos 返回查找成功的库房盘点工作信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<StocktakingInfo> stocktakingInfos,DataPageInfo dataPageInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找库房盘点工作信息
	 * @param stocktakingInfo 返回查找成功的库房盘点工作信息,ID必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID( StocktakingInfo stocktakingInfo, ErrInfo pErrInfo);

}
