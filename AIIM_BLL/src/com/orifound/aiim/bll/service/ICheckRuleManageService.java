/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.CheckRule;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 校验规则管理服务的接口定义
 *
 */
public interface ICheckRuleManageService
{

	/**
	 * 添加一个新的校验规则
	 * @param checkRule 新添加的校验规则信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveCheckRule(CheckRule checkRule, ErrInfo pErrInfo);

	/**
	 * 删除指定的校验规则
	 * @param checkRule 要删除的校验规则，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteCheckRule(CheckRule checkRule, ErrInfo pErrInfo);

	/**
	 * 修改指定的校验规则
	 * @param checkRule 修改后的校验规则信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateCheckRule(CheckRule checkRule, ErrInfo pErrInfo);

	/**
	 * 查找所有的校验规则信息
	 * @param checkRules 返回查找成功的校验规则集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findCheckRules(Map<Integer,CheckRule> checkRules, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找校验规则信息
	 * @param pID 指定的唯一标识
	 * @param checkRule 返回查找成功的校验规则信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findCheckRuleByID(int pID, CheckRule checkRule, ErrInfo pErrInfo);

}
