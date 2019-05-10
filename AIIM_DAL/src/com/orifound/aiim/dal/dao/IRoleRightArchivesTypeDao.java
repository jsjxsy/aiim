/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.RoleRightArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 角色档案分类授权表的DAO接口定义
 *
 */
public interface IRoleRightArchivesTypeDao
{

	/**
	 * Dao接口定义：添加角色档案分类授权
	 * @param roleRightArchivesType 要添加的角色档案分类授权
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(RoleRightArchivesType roleRightArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的角色档案分类授权
	 * @param roleRightArchivesType 要删除的角色档案分类授权
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(RoleRightArchivesType roleRightArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的角色档案分类授权
	 * @param roleRightArchivesType 要更新的角色档案分类授权
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(RoleRightArchivesType roleRightArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找指定角色的档案分类授权
	 * @param pRoleID 指定的角色编号数组
	 * @param userRightArchivesType 返回查找成功的角色档案分类授权集合，以档案分类编号作为集合键
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByRoleID(int[] pRoleID, LinkedHashMap<Integer,ArchivesType> roleRightArchivesTypes, ErrInfo pErrInfo);

}
