/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.RoleRightArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 角色档案密级授权表的DAO接口定义
 *
 */
public interface IRoleRightArchivesSecrecyDao
{

	/**
	 * Dao接口定义：添加角色档案密级授权
	 * @param roleRightArchivesSecrecy 要添加的角色档案密级授权
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(RoleRightArchivesSecrecy roleRightArchivesSecrecy, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的角色档案密级授权
	 * @param roleRightArchivesSecrecy 要删除的角色档案密级授权
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(RoleRightArchivesSecrecy roleRightArchivesSecrecy, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找指定角色的档案密级授权
	 * @param pRoleID 指定的角色编号数组
	 * @param roleRightArchivesSecrecys 返回查找成功的角色档案密级授权集合，以密级编号作为集合键
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByRoleID(int[] pRoleID,Map<Integer,ArchivesSecrecy> roleRightArchivesSecrecys, ErrInfo pErrInfo);


}
