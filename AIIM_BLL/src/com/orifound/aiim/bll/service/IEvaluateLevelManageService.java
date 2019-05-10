/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.EvaluateLevel;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 考核评分等级管理服务的接口定义
 *
 */
public interface IEvaluateLevelManageService {

	/**
	 * 添加一个新的考核评分等级
	 * @param evaluateLevel 新添加的考核评分等级信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveEvaluateLevel(EvaluateLevel evaluateLevel, ErrInfo pErrInfo);

	/**
	 * 删除指定的考核评分等级
	 * @param evaluateLevel 要删除的考核评分等级，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteEvaluateLevel(EvaluateLevel evaluateLevel, ErrInfo pErrInfo);

	/**
	 * 修改指定的考核评分等级
	 * @param evaluateLevel 修改后的考核评分等级信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateEvaluateLevel(EvaluateLevel evaluateLevel, ErrInfo pErrInfo);

	/**
	 * 查找所有的考核评分等级信息
	 * @param evaluateLevels 返回查找成功的考核评分等级集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findEvaluateLevels(List<EvaluateLevel> evaluateLevels,
			ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找考核评分等级信息
	 * @param pID 指定的唯一标识
	 * @param evaluateLevel 返回查找成功的考核评分等级信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findEvaluateLevelByID(int pID, EvaluateLevel evaluateLevel,
			ErrInfo pErrInfo);

}