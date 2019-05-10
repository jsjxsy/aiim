/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.UserRole;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 角色信息字典表管理服务的接口定义
 *
 */
public interface IUserRoleManageService {

	/**
	 * 添加一个新的角色信息字典表
	 * @param userRole 新添加的角色信息字典表信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveUserRole(UserRole userRole, ErrInfo pErrInfo);

	/**
	 * 删除指定的角色信息字典表
	 * @param userRole 要删除的角色信息字典表，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteUserRole(UserRole userRole, ErrInfo pErrInfo);

	/**
	 * 修改指定的角色信息字典表
	 * @param userRole 修改后的角色信息字典表信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateUserRole(UserRole userRole, ErrInfo pErrInfo);

	/**
	 * 查找所有的角色信息字典表信息
	 * @param userRoles 返回查找成功的角色信息字典表集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserRoles(List<UserRole> userRoles,
			ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找角色信息字典表信息
	 * @param pID 指定的唯一标识
	 * @param userRole 返回查找成功的角色信息字典表信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserRoleByID(int pID, UserRole userRole,
			ErrInfo pErrInfo);

	
	/**
	 * 根据系统固有角色标志查找用户角色
	 * @param systemRolesFlag 系统固有角色标志
	 * @param userRoles 返回查找成功的用户角色
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserRolesBySystemRolesFlag(int systemRolesFlag, List<UserRole> userRoles,ErrInfo pErrInfo);

	   
	/**
	 * 查找不属于指定用户的所有角色信息
	 * @param pRoleID 指定的角色编号
	 * @param userRoles 返回查找成功的用户所属角色信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	 boolean findRoleInfosNotInUserID(int pUserID, List<UserRole> userRoles, ErrInfo pErrInfo);



}
