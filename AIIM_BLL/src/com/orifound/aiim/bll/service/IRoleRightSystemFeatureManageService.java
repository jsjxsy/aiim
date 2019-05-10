/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.RoleRightSystemFeature;
import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.UserRole;

/**
 * 角色系统功能授权管理服务接口
 *
 */
public interface IRoleRightSystemFeatureManageService
{
	/**
	 * 为指定角色添加系统功能权限<br>
	 * 适用于一次添加一个系统功能权限
	 * @param userRole 用户角色信息，其编号字段必须赋值
	 * @param roleRightSystemFeature 角色的系统功能权限，其系统功能编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean saveRightSystemFeatureForRole(UserRole userRole,RoleRightSystemFeature roleRightSystemFeature,ErrInfo pErrInfo);
	
	/**
	 * 为指定角色添加系统功能权限<br>
	 * 适用于一次添加多个系统功能权限
	 * @param userRole 用户角色信息，其编号字段必须赋值
	 * @param roleRightSystemFeatures 角色的系统功能权限集合，其成员对象的系统功能编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean saveRightSystemFeaturesForRole(UserRole userRole,List<RoleRightSystemFeature> roleRightSystemFeatures,ErrInfo pErrInfo);
	
	/**
	 * 为指定角色移除系统功能权限<br>
	 * 适用于一次移除一个系统功能权限
	 * @param userRole 用户角色信息，其编号字段必须赋值
	 * @param roleRightSystemFeature 角色的系统功能权限，其系统功能编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean deleteRightSystemFeatureForRole(UserRole userRole,RoleRightSystemFeature roleRightSystemFeature,ErrInfo pErrInfo);
	
	/**
	 * 为指定角色移除系统功能权限<br>
	 * 适用于一次移除多个系统功能权限
	 * @param userRole 用户角色信息，其编号字段必须赋值
	 * @param roleRightSystemFeatures 角色的系统功能权限集合，其成员对象的系统功能编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean deleteRightSystemFeaturesForRole(UserRole userRole,List<RoleRightSystemFeature> roleRightSystemFeatures,ErrInfo pErrInfo);
	
	/**
	 * 获取指定角色的系统功能菜单权限<br>
	 * @param pRoleID 指定的角色编号数组
	 * @param systemFeatures 返回对该角色直接授权的顶层系统功能菜单集合（一级菜单项集合），以UclKey为集合关键字，下级功能菜单项可通过ChildSystemFeatures属性访问
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findRightSystemFeatureMenusByRolesID(int[] pRoleID,LinkedHashMap<String,SystemFeature> systemFeatures,ErrInfo pErrInfo);
	
	/**
	 * 获取指定角色的系统功能权限（UCL:用户访问控制列表）
	 * @param pRoleID 指定的角色编号数组
	 * @param systemFeatures 返回对该角色直接授权的系统功能集合，以UclKey为集合关键字，注意该功能集合是平面型的（没有层次结构），大小功能全部都在该集合中
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findRightSystemFeaturesByRolesID(int[] pRoleID,Map<String,SystemFeature> systemFeatures,ErrInfo pErrInfo);
	
}
