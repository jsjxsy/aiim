/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.UserRole;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 角色信息字典表的DAO接口定义
 *
 */
public interface IUserRoleDao {

	/**
	 * Dao接口定义：添加用户角色
	 * @param userRole 要添加的用户角色
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(UserRole userRole, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的用户角色
	 * @param userRole 要删除的用户角色
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(UserRole userRole, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的用户角色
	 * @param userRole 要更新的用户角色
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(UserRole userRole, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的用户角色
	 * @param userRoles 返回查找成功的用户角色集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<UserRole> userRoles, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找用户角色
	 * @param pID 指定的唯一标识
	 * @param userRole 返回查找成功的用户角色
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, UserRole userRole, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据系统固有角色标志查找用户角色
	 * @param systemRolesFlag 系统固有角色标志
	 * @param userRoles 返回查找成功的用户角色
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findBySystemRolesFlag(int systemRolesFlag, List<UserRole> userRoles,ErrInfo pErrInfo);

	
	/**
	 * Dao接口定义：获取不属于指定用户的所有角色信息
	 * @param pRoleID
	 * @param userRolesInfos
	 * @param pErrInfo
	 * @return
	 */
	boolean findRolesNotInUserID(int pUserID, List<UserRole> userRoles, ErrInfo pErrInfo);


}
