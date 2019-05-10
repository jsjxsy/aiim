/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.RoleRightArchivesSecrecy;
import com.orifound.aiim.entity.UserRole;

/**
 * 角色档案密级授权管理服务接口
 *
 */
public interface IRoleRightArchivesSecrecyManageService
{
	/**
	 * 为指定角色添加档案密级权限<br>
	 * 适用于一次添加多个密级权限
	 * @param userRole 用户角色信息，其编号字段必须赋值
	 * @param roleRightArchivesSecrecies 角色的档案密级权限集合，其成员对象的档案密级编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean saveRightArchivesSecrecysForRole(UserRole userRole,List<RoleRightArchivesSecrecy> roleRightArchivesSecrecies,ErrInfo pErrInfo);

	/**
	 * 为指定角色移除档案密级权限<br>
	 * 适用于一次移除多个密级权限
	 * @param userRole 用户角色信息，其编号字段必须赋值
	 * @param roleRightArchivesSecrecies 角色的档案密级权限集合，其成员对象的档案密级编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean deleteRightArchivesSecrecysForRole(UserRole userRole,List<RoleRightArchivesSecrecy> roleRightArchivesSecrecies,ErrInfo pErrInfo);
	
	/**
	 * 获取角色的档案密级权限
	 * @param pRoleID 指定的角色编号数组
	 * @param archivesSecrecys 返回对该角色直接授权的档案密级信息集合，以密级编号作为集合的关键字
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findRightArchivesSecrecysByRolesID(int[] pRoleID,Map<Integer,ArchivesSecrecy> archivesSecrecys,ErrInfo pErrInfo);
	
}
