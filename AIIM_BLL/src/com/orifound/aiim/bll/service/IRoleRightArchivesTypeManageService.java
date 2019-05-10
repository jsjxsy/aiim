/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRightArchivesType;
import com.orifound.aiim.entity.UserRole;

/**
 * 角色档案分类授权管理服务接口
 *
 */
public interface IRoleRightArchivesTypeManageService
{
	/**
	 * 为指定角色添加档案分类权限<br>
	 * 适用于一次添加一个档案分类权限
	 * @param userRole 用户角色信息，其编号字段必须赋值
	 * @param userRightArchivesType 角色的档案分类权限，其档案分类编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean saveRightArchivesTypeForRole(UserRole userRole,UserRightArchivesType userRightArchivesType,ErrInfo pErrInfo);
	
	/**
	 * 为指定角色添加档案分类权限
	 * 适用于一次添加多个档案分类权限
	 * @param userRole 用户角色信息，其编号字段必须赋值
	 * @param userRightArchivesTypes 角色的档案分类权限集合，其成员对象的档案分类编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean saveRightArchivesTypesForRole(UserRole userRole,List<UserRightArchivesType> userRightArchivesTypes,ErrInfo pErrInfo);
	
	/**
	 * 为指定角色移除档案分类权限<br>
	 * 适用于一次移除多个档案分类权限
	 * @param userRole 用户角色信息，其编号字段必须赋值
	 * @param userRightArchivesTypes 角色的档案分类权限集合，其成员对象的档案分类编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean deleteRightArchivesTypesForRole(UserRole userRole,List<UserRightArchivesType> userRightArchivesTypes,ErrInfo pErrInfo);
	
	/**
	 * 获取角色的档案分类权限
	 * @param pRoleID 指定的角色编号数组
	 * @param archivestypes 返回对该角色直接授权的档案分类集合（非树状结构），以档案分类编号作为集合关键字
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findRightArchivesTypeByRolesID(int[] pRoleID,LinkedHashMap<Integer,ArchivesType> archivestypes,ErrInfo pErrInfo);
	
}
