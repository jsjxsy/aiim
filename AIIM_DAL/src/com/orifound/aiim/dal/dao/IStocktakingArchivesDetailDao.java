package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.StocktakingArchivesDetail;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 库房盘点的档案盒与档案卷详细表的DAO接口定义
 *
 */
public interface IStocktakingArchivesDetailDao {

	/**
	 * Dao接口定义：添加库房盘点的档案盒与档案卷详细
	 * @param stocktakingArchivesDetail 要添加的库房盘点的档案盒与档案卷详细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean add(StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的库房盘点的档案盒与档案卷详细
	 * @param stocktakingArchivesDetail 要删除的库房盘点的档案盒与档案卷详细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的库房盘点的档案盒与档案卷详细
	 * @param stocktakingArchivesDetail 要更新的库房盘点的档案盒与档案卷详细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的库房盘点的档案盒与档案卷详细
	 * @param stocktakingArchivesDetails 返回查找成功的库房盘点的档案盒与档案卷详细集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<StocktakingArchivesDetail> stocktakingArchivesDetails, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找库房盘点的档案盒与档案卷详细
	 * @param pID 指定的唯一标识
	 * @param stocktakingArchivesDetail 返回查找成功的库房盘点的档案盒与档案卷详细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo);

}
