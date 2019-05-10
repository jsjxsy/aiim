/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.UserChargeDepartmentInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 业务专员负责部门管理服务的接口定义
 *
 */
public interface IUserChargeDepartmentInfoManageService
{

	/**
	 * 添加一个新的业务专员负责部门
	 * @param userChargeDepartmentInfo 新添加的业务专员负责部门信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveUserChargeDepartmentInfo(UserChargeDepartmentInfo userChargeDepartmentInfo, ErrInfo pErrInfo);

	/**
	 * 删除指定的业务专员负责部门
	 * @param userChargeDepartmentInfo 要删除的业务专员负责部门，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteUserChargeDepartmentInfo(UserChargeDepartmentInfo userChargeDepartmentInfo, ErrInfo pErrInfo);

	/**
	 * 修改指定的业务专员负责部门
	 * @param userChargeDepartmentInfo 修改后的业务专员负责部门信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateUserChargeDepartmentInfo(UserChargeDepartmentInfo userChargeDepartmentInfo, ErrInfo pErrInfo);

	/**
	 * 查找指定业务专员所负责的部门信息
	 * @param pUserID 指定的用户编号
	 * @param userChargeDepartmentInfo 返回查找成功的业务专员负责部门信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserChargeDepartmentInfosByUserID(int pUserID, LinkedHashMap<Integer,UserChargeDepartmentInfo> userChargeDepartmentInfos, ErrInfo pErrInfo);

	/**
	 * 查找指定业务专员未负责的部门信息
	 * @param userChargeDepartmentInfo 返回查找成功的业务专员负责部门信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllUserUnChargeDepartmentInfos(List<UserChargeDepartmentInfo> userChargeDepartmentInfos, ErrInfo pErrInfo);
}
