/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.UserRolesArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * UserRolesArchivesType表的DAO接口定义
 *
 */
public interface IUserRolesArchivesTypeDao {

	/**
	 * Dao接口定义：添加UserRolesArchivesType
	 * @param pUserRolesArchivesType 要添加的UserRolesArchivesType
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的UserRolesArchivesType
	 * @param pUserRolesArchivesType 要删除的UserRolesArchivesType
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的UserRolesArchivesType
	 * @param pUserRolesArchivesType 要更新的UserRolesArchivesType
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的UserRolesArchivesType
	 * @param pUserRolesArchivesTypes 返回查找成功的UserRolesArchivesType集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<UserRolesArchivesType> pUserRolesArchivesTypes, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找UserRolesArchivesType
	 * @param pID 指定的唯一标识
	 * @param pUserRolesArchivesType 返回查找成功的UserRolesArchivesType
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找UserRolesArchivesType集合
	 * @param pRoleID 指定角色的唯一标识
	 * @param pUserRolesArchivesType 返回查找成功的UserRolesArchivesType
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByRoleID(int pRoleID, List<UserRolesArchivesType> pUserRolesArchivesTypes, ErrInfo pErrInfo);
	
	/**
	 * 删除指定角色下所有的UserRolesArchivesType集合
	 * @param pRoleID 指定角色的唯一标识
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteByRoleID(int pRoleID, ErrInfo pErrInfo) ;
}

