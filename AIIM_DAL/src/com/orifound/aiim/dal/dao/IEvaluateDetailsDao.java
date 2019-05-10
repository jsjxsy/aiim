/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateDetails;

/**
 * 考核明细表(EvaluateDetails)的DAO接口定义
 *
 */
public interface IEvaluateDetailsDao {

	/**
	 * Dao接口定义：添加考核明细信息
	 * @param EvaluateDetails 要添加的考核明细信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(EvaluateDetails evaluateDetails, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的考核明细信息
	 * @param EvaluateDetails 要删除的考核明细信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(EvaluateDetails evaluateDetails, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的考核明细信息
	 * @param EvaluateDetails 要更新的考核明细信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(EvaluateDetails evaluateDetails, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的考核明细信息
	 * @param EvaluateDetailss 返回查找成功的考核明细信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<EvaluateDetails> evaluateDetailss, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找考核明细信息
	 * @param evaluateRegID 考核登记信息ID
	 * @param evaluateDetailss 返回查找成功的考核明细信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByEvaluateRegID(int evaluateRegID, List<EvaluateDetails> evaluateDetailss, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：插入当特定度的考核明细记录
	 * @param year 考核年度
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean insertByYear(String year, ErrInfo pErrInfo);
}