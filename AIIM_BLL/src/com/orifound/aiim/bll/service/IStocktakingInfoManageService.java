package com.orifound.aiim.bll.service;
import java.util.List;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.StocktakingInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 库房盘点工作信息管理服务的接口定义
 *
 */
public interface IStocktakingInfoManageService {

	/**
	 * 添加一个新的库房盘点工作信息
	 * @param stocktakingInfo 新添加的库房盘点工作信息信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean addStocktakingInfo(StocktakingInfo stocktakingInfo, ErrInfo pErrInfo);

	/**
	 * 删除指定的库房盘点工作信息
	 * @param stocktakingInfo 要删除的库房盘点工作信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteStocktakingInfo(StocktakingInfo stocktakingInfo, ErrInfo pErrInfo);

	/**
	 * 修改指定的库房盘点工作的盘点状态：将"未盘点"更新为"已盘点"
	 * @param stocktakingInfo 修改后的库房盘点工作信息信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateStocktakingInfo(StocktakingInfo stocktakingInfo, ErrInfo pErrInfo);

	/**
	 * 查找所有的库房盘点工作信息信息
	 * @param stocktakingInfos 返回查找成功的库房盘点工作信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findStocktakingInfos(List<StocktakingInfo> stocktakingInfos,DataPageInfo dataPageInfo, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找库房盘点工作信息信息
	 * @param pID 指定的唯一标识
	 * @param stocktakingInfo 返回查找成功的库房盘点工作信息信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findStocktakingInfoByID(StocktakingInfo stocktakingInfo, ErrInfo pErrInfo);

}
