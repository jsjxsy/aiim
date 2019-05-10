/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.UserRolesArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * UserRolesArchivesSecrecy管理服务的接口定义
 *
 */
public interface IUserRolesArchivesSecrecyManageService {

	/**
	 * 添加一个新的UserRolesArchivesSecrecy
	 * @param pUserRolesArchivesSecrecy 新添加的UserRolesArchivesSecrecy信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveUserRolesArchivesSecrecy(UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo);

	/**
	 * 删除指定的UserRolesArchivesSecrecy
	 * @param pUserRolesArchivesSecrecy 要删除的UserRolesArchivesSecrecy，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteUserRolesArchivesSecrecy(UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo);

	/**
	 * 修改指定的UserRolesArchivesSecrecy
	 * @param pUserRolesArchivesSecrecy 修改后的UserRolesArchivesSecrecy信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateUserRolesArchivesSecrecy(UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo);

	/**
	 * 查找所有的UserRolesArchivesSecrecy信息
	 * @param pUserRolesArchivesSecrecys 返回查找成功的UserRolesArchivesSecrecy集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserRolesArchivesSecrecys(List<UserRolesArchivesSecrecy> pUserRolesArchivesSecrecys, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找UserRolesArchivesSecrecy信息
	 * @param pID 指定的唯一标识
	 * @param pUserRolesArchivesSecrecy 返回查找成功的UserRolesArchivesSecrecy信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserRolesArchivesSecrecyByID(int pID, UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo);
	
	/**
	 * 根据角色唯一编号查找用户角色信息档案密级信息
	 * @param pRoleID 角色唯一编号
	 * @param pUserRolesArchivesSecrecys 返回查找成功的UserRolesArchivesSecrecy集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserRolesArchivesSecrecyByRoleID(int pRoleID, List<UserRolesArchivesSecrecy> pUserRolesArchivesSecrecys, ErrInfo pErrInfo); 
	
	/**
	 * 删除指定角色下所有的角色档案密级权限
	 * @param pRoleID 角色唯一编号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteUserRolesArchivesSecrecyByRoleID(int pRoleID,ErrInfo pErrInfo);
	
	/**
	 * 添加多个新的UserRolesArchivesSecrecy
	 * @param pUserRolesArchivesSecrecys 新添加的UserRolesArchivesSecrecy信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveUserRolesArchivesSecrecyS(List<UserRolesArchivesSecrecy> pUserRolesArchivesSecrecys, ErrInfo pErrInfo);
}
