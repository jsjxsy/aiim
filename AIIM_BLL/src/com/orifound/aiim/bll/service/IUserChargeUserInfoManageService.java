/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.UserChargeUserInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 用户代工信息管理服务的接口定义
 *
 */
public interface IUserChargeUserInfoManageService
{

	/**
	 * 添加一个新的用户代工信息
	 * @param userChargeUserInfo 新添加的用户代工信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveUserChargeUserInfo(UserChargeUserInfo userChargeUserInfo, ErrInfo pErrInfo);

	/**
	 * 删除指定的用户代工信息
	 * @param userChargeUserInfo 要删除的用户代工信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteUserChargeUserInfo(UserChargeUserInfo userChargeUserInfo, ErrInfo pErrInfo);

	/**
	 * 修改指定的用户代工信息
	 * @param userChargeUserInfo 修改后的用户代工信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateUserChargeUserInfo(UserChargeUserInfo userChargeUserInfo, ErrInfo pErrInfo);
	
	/**
	 * 查找指定用户的所有代工信息
	 * @param pUserID 指定的用户编号
	 * @param userChargeUserInfo 返回查找成功的用户代工信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserChargeUserInfosByUserID(int pUserID, List<UserChargeUserInfo> userChargeUserInfos, ErrInfo pErrInfo);

}
