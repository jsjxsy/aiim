/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.UserChargeUserInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 用户代工信息表的DAO接口定义
 *
 */
public interface IUserChargeUserInfoDao
{

	/**
	 * Dao接口定义：添加用户代工信息
	 * @param userChargeUserInfo 要添加的用户代工信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(UserChargeUserInfo userChargeUserInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的用户代工信息
	 * @param userChargeUserInfo 要删除的用户代工信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(UserChargeUserInfo userChargeUserInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的用户代工信息
	 * @param userChargeUserInfo 要更新的用户代工信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(UserChargeUserInfo userChargeUserInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找指定用户的所有代工信息
	 * @param pUserID 指定的用户编号
	 * @param userChargeUserInfos 返回查找成功的用户代工信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByUserID(int pUserID, List<UserChargeUserInfo> userChargeUserInfos, ErrInfo pErrInfo);

}
