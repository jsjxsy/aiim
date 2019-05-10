/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.UserRolesArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * UserRolesArchivesType管理服务的接口定义
 *
 */
public interface IUserRolesArchivesTypeManageService {

	/**
	 * 添加一个新的UserRolesArchivesType
	 * @param pUserRolesArchivesType 新添加的UserRolesArchivesType信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveUserRolesArchivesType(UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo);

	/**
	 * 删除指定的UserRolesArchivesType
	 * @param pUserRolesArchivesType 要删除的UserRolesArchivesType，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteUserRolesArchivesType(UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo);

	/**
	 * 修改指定的UserRolesArchivesType
	 * @param pUserRolesArchivesType 修改后的UserRolesArchivesType信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateUserRolesArchivesType(UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo);

	/**
	 * 查找所有的UserRolesArchivesType信息
	 * @param pUserRolesArchivesTypes 返回查找成功的UserRolesArchivesType集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserRolesArchivesTypes(List<UserRolesArchivesType> pUserRolesArchivesTypes, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找UserRolesArchivesType信息
	 * @param pID 指定的唯一标识
	 * @param pUserRolesArchivesType 返回查找成功的UserRolesArchivesType信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserRolesArchivesTypeByID(int pID, UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找UserRolesArchivesType信息
	 * @param RoleID 指定的角色唯一标识
	 * @param pUserRolesArchivesType 返回查找成功的UserRolesArchivesType信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserRolesArchivesTypeByRoleID(int RoleID,List<UserRolesArchivesType> pUserRolesArchivesTypes, ErrInfo pErrInfo);
	
	/**
	 * 删除指定角色下所有的档案分类资源
	 * @param RoleID 指定的角色唯一标识
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteUserRolesArchivesTypeByRoleID(int RoleID, ErrInfo pErrInfo);
	
	/**
	 * 添加多个新的UserRolesArchivesType
	 * @param pUserRolesArchivesTypes 要添加的UserRolesArchivesType集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean saveUserRolesArchivesTypes(List<UserRolesArchivesType> pUserRolesArchivesTypes, ErrInfo pErrInfo);
}
