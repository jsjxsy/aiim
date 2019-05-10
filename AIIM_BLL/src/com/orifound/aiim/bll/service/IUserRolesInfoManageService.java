/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesInfo;

/**
 * 用户所属角色管理服务的接口定义
 *
 */
public interface IUserRolesInfoManageService
{

	/**
	 * 添加一个新的用户所属角色
	 * @param userRolesInfo 新添加的用户所属角色信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveUserRolesInfo(UserRolesInfo userRolesInfo, ErrInfo pErrInfo);

	/**
	 * 删除指定的用户所属角色
	 * @param userRolesInfo 要删除的用户所属角色，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteUserRolesInfo(UserRolesInfo userRolesInfo, ErrInfo pErrInfo);

	/**
	 * 查找指定用户所属的角色信息
	 * @param pUserID 指定的用户编号
	 * @param userRolesInfo 返回查找成功的用户所属角色信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserRolesInfosByUserID(int pUserID,List<UserRolesInfo> userRolesInfos, ErrInfo pErrInfo);
	
	/**
	 * 差所所有角色
	 * @param pUserID
	 * @param userRolesInfos
	 * @param pErrInfo
	 * @return
	 */
	boolean findUserRolesInfos(List<UserRolesInfo> userRolesInfos, ErrInfo pErrInfo);
	
	/**
	 * 修改指定的橘色信息
	 * @param userRolesInfo
	 * @param pErrInfo
	 * @return
	 */
	boolean findUserRoleByID(int pID,UserRolesInfo userRolesInfo, ErrInfo pErrInfo);
	
	/**
	 * 
	 * 查找指定角色拥有的用户用户信息
	 * @param pUserID 指定的角色编号
	 * @param userRolesInfo 返回查找成功的用户所属角色信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserRolesInfosByRoleID(int pRoleID,List<UserRolesInfo> userRolesInfos, ErrInfo pErrInfo);
	
	
}
