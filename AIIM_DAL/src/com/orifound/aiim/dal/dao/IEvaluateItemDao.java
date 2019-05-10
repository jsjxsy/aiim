/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.EvaluateItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 绩效考评项目字典表 (DD_EvaluateItem)的DAO接口定义
 *
 */
public interface IEvaluateItemDao {

	/**
	 * Dao接口定义：添加考核项目字典信息
	 * @param EvaluateItem 要添加的考核项目字典信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(EvaluateItem EvaluateItem, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的考核项目字典信息
	 * @param EvaluateItem 要删除的考核项目字典信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(EvaluateItem EvaluateItem, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的考核项目字典信息
	 * @param EvaluateItem 要更新的考核项目字典信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(EvaluateItem EvaluateItem, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的考核项目字典信息
	 * @param EvaluateItems 返回查找成功的考核项目字典信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<EvaluateItem> EvaluateItems, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找考核项目字典信息
	 * @param pID 指定的唯一标识
	 * @param EvaluateItem 返回查找成功的考核项目字典信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, EvaluateItem EvaluateItem, ErrInfo pErrInfo);

}
