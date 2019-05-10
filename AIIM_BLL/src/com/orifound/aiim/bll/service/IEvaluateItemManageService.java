/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.EvaluateItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 考核项管理服务的接口定义
 *
 */
public interface IEvaluateItemManageService {

	/**
	 * 添加一个新的考核项
	 * @param evaluateItem 新添加的考核项信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveEvaluateItem(EvaluateItem evaluateItem, ErrInfo pErrInfo);

	/**
	 * 删除指定的考核项
	 * @param evaluateItem 要删除的考核项，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteEvaluateItem(EvaluateItem evaluateItem, ErrInfo pErrInfo);

	/**
	 * 修改指定的考核项
	 * @param evaluateItem 修改后的考核项信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateEvaluateItem(EvaluateItem evaluateItem, ErrInfo pErrInfo);

	/**
	 * 查找所有的考核项信息
	 * @param evaluateItems 返回查找成功的考核项集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findEvaluateItems(List<EvaluateItem> evaluateItems,
			ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找考核项信息
	 * @param pID 指定的唯一标识
	 * @param evaluateItem 返回查找成功的考核项信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findEvaluateItemByID(int pID, EvaluateItem evaluateItem,
			ErrInfo pErrInfo);

}
