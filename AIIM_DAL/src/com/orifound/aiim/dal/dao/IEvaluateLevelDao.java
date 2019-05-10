/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.EvaluateLevel;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 考核等级字典表(DD_EvaluateLevel)的DAO接口定义
 *
 */
public interface IEvaluateLevelDao {

	/**
	 * Dao接口定义：添加考核等级字典信息
	 * @param evaluateLevel 要添加的考核等级字典信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(EvaluateLevel evaluateLevel, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的考核等级字典信息
	 * @param evaluateLevel 要删除的考核等级字典信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(EvaluateLevel evaluateLevel, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的考核等级字典信息
	 * @param evaluateLevel 要更新的考核等级字典信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(EvaluateLevel evaluateLevel, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的考核等级字典信息
	 * @param evaluateLevels 返回查找成功的考核等级字典信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<EvaluateLevel> evaluateLevels, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找考核等级字典信息
	 * @param pID 指定的唯一标识
	 * @param evaluateLevel 返回查找成功的考核等级字典信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, EvaluateLevel evaluateLevel, ErrInfo pErrInfo);

}
