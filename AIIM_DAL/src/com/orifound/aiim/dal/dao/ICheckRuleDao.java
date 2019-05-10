/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.CheckRule;
import com.orifound.aiim.entity.ErrInfo;

/**
 * DD_CheckRule表的DAO接口定义
 *
 */
public interface ICheckRuleDao
{

	/**
	 * Dao接口定义：添加校验规则
	 * @param checkRule 要添加的校验规则
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(CheckRule checkRule, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的校验规则
	 * @param checkRule 要删除的校验规则
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(CheckRule checkRule, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的校验规则
	 * @param checkRule 要更新的校验规则
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(CheckRule checkRule, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的校验规则
	 * @param checkRules 返回查找成功的校验规则集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(Map<Integer,CheckRule> checkRules, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找校验规则
	 * @param pID 指定的唯一标识
	 * @param checkRule 返回查找成功的校验规则
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, CheckRule checkRule, ErrInfo pErrInfo);

}
