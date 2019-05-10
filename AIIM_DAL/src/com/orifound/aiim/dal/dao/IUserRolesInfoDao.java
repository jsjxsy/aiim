/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.UserRolesInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 用户角色信息表的DAO接口定义
 *
 */
public interface IUserRolesInfoDao
{

	/**
	 * Dao接口定义：添加用户所属角色定义
	 * @param userRolesInfo 要添加的用户所属角色定义
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(UserRolesInfo userRolesInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的用户所属角色定义
	 * @param userRolesInfo 要删除的用户所属角色定义
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(UserRolesInfo userRolesInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找指定用户所属的角色定义
	 * @param pUserID 指定的用户编号
	 * @param userRolesInfo 返回查找成功的用户所属角色集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByUserID(int pUserID, List<UserRolesInfo> userRolesInfos, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：查找指定角色所包含的用户信息
	 * @param pRoleID 指定的角色编号
	 * @param userRolesInfo 返回查找成功的用户所属角色集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByRoleID(int pRoleID, List<UserRolesInfo> userRolesInfos, ErrInfo pErrInfo);
	
	/**
	 *  Dao接口定义：查找所有角色定义
	 * @param userRolesInfos 返回查找成功的用户所属角色集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return
	 */
	boolean findAll(List<UserRolesInfo> userRolesInfos, ErrInfo pErrInfo);
	
	/**
	 * 
	 * @param pID
	 * @param userRolesInfo
	 * @param pErrInfo
	 * @return
	 */
	boolean findByID(int pID,UserRolesInfo userRolesInfo,ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：修改角色定义
	 * @param userRolesInfo 返回查找成功的用户所属角色集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return
	 */
	boolean updateUserRole(UserRolesInfo userRolesInfo, ErrInfo pErrInfo);
	
}

