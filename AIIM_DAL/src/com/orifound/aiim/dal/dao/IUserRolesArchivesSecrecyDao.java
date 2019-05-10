/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.UserRolesArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * UserRolesArchivesSecrecy表的DAO接口定义
 *
 */
public interface IUserRolesArchivesSecrecyDao {

	/**
	 * Dao接口定义：添加UserRolesArchivesSecrecy
	 * @param pUserRolesArchivesSecrecy 要添加的UserRolesArchivesSecrecy
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的UserRolesArchivesSecrecy
	 * @param pUserRolesArchivesSecrecy 要删除的UserRolesArchivesSecrecy
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的UserRolesArchivesSecrecy
	 * @param pUserRolesArchivesSecrecy 要更新的UserRolesArchivesSecrecy
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的UserRolesArchivesSecrecy
	 * @param pUserRolesArchivesSecrecys 返回查找成功的UserRolesArchivesSecrecy集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<UserRolesArchivesSecrecy> pUserRolesArchivesSecrecys, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找UserRolesArchivesSecrecy
	 * @param pID 指定的唯一标识
	 * @param pUserRolesArchivesSecrecy 返回查找成功的UserRolesArchivesSecrecy
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo);
	
	/**
	 * 根据角色编号查找用户角色的档案密级权限
	 * @param pRoleID 角色编号
	 * @param pUserRolesArchivesSecrecys 返回查找成功的UserRolesArchivesSecrecy集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByRoleID(int pRoleID, List<UserRolesArchivesSecrecy> pUserRolesArchivesSecrecys, ErrInfo pErrInfo);
	
	/**
	 * 根据角色编号查找用户角色的档案密级权限
	 * @param pRoleID 角色编号
	 * @param pUserRolesArchivesSecrecys 返回查找成功的UserRolesArchivesSecrecy集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteByRoleID(int pRoleID, ErrInfo pErrInfo);

}
