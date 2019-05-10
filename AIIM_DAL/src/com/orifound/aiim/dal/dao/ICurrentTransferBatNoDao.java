package com.orifound.aiim.dal.dao;

import com.orifound.aiim.entity.ErrInfo;

import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.PaperTransferBatch;

/**
 * 当前移交批次信息表的DAO接口定义
 *
 */
public interface ICurrentTransferBatNoDao {

	/**
	 * 查找当前用户创建的当前的批次号
	 * @param userID 当前用户编号
	 * @param paperTransferBatch 要返回的批次信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findCurrentTransferBatNo(int userID, PaperTransferBatch paperTransferBatch, ErrInfo pErrInfo);

	/**
	 * 更新当前批次序号的最大值
	 * @param userID 当前用户编号
	 * @param currentNo 计算出的当前最大的序号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(int userID, IntegerEx currentNo, ErrInfo pErrInfo);

	/**
	 * 查找当前的批次序号的最大值
	 * @param currentNo 要返回的当前最大序号
	*  @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findCurrentNo(IntegerEx currentNo, ErrInfo pErrInfo);

}
