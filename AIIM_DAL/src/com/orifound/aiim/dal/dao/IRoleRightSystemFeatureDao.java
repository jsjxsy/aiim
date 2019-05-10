/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.RoleRightSystemFeature;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;

/**
 * 角色系统功能权限表的DAO接口定义
 *
 */
public interface IRoleRightSystemFeatureDao
{

	/**
	 * Dao接口定义：添加角色的系统功能授权
	 * @param roleRightSystemFeature 要添加的角色的系统功能授权
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(RoleRightSystemFeature roleRightSystemFeature, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的角色的系统功能授权
	 * @param roleRightSystemFeature 要删除的角色的系统功能授权
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(RoleRightSystemFeature roleRightSystemFeature, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找指定角色的系统功能授权
	 * @param pRoleID 指定的角色编号数组
	 * @param roleRightSystemFeature 返回查找成功的角色系统功能授权，平面结构的系统功能集合，大小功能都在该集合中，以UclKey作为集合键
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByRoleID(int[] pRoleID, Map<String,SystemFeature> roleRightSystemFeatures, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：查找指定角色的系统功能菜单授权
	 * @param pRoleID 指定的角色编号数组
	 * @param roleRightMenus 返回查找成功的角色系统一级功能菜单授权集合，树状结构的系统功能菜单集合，可访问下级菜单项，以UclKey作为集合键
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findMenusByRoleID(int[] pRoleID,LinkedHashMap<String,SystemFeature> roleRightMenus, ErrInfo pErrInfo);

}
