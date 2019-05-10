/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.Duty;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 职务信息数据字典管理服务的接口定义
 *
 */
public interface IDutyManageService {

	/**
	 * 添加一个新的职务信息数据字典
	 * @param duty 新添加的职务信息数据字典信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveDuty(Duty duty, ErrInfo pErrInfo);

	/**
	 * 删除指定的职务信息数据字典
	 * @param duty 要删除的职务信息数据字典，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteDuty(Duty duty, ErrInfo pErrInfo);

	/**
	 * 修改指定的职务信息数据字典
	 * @param duty 修改后的职务信息数据字典信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateDuty(Duty duty, ErrInfo pErrInfo);

	/**
	 * 查找所有的职务信息数据字典信息
	 * @param dutys 返回查找成功的职务信息数据字典集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findDutys(List<Duty> dutys,
			ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找职务信息数据字典信息
	 * @param pID 指定的唯一标识
	 * @param duty 返回查找成功的职务信息数据字典信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findDutyByID(int pID, Duty duty,
			ErrInfo pErrInfo);

}
