/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRightSystemFeature;

/**
 * 用户系统功能授权管理服务接口
 *
 */
public interface IUserRightSystemFeatureManageService
{
	/**
	 * 为指定用户添加系统功能权限<br>
	 * 适用于一次添加一个系统功能权限
	 * @param userInfo 用户信息，其编号字段必须赋值
	 * @param userRightSystemFeature 用户的系统功能权限，其系统功能编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveRightSystemFeatureForUser(UserInfo userInfo,UserRightSystemFeature userRightSystemFeature,ErrInfo pErrInfo);
	
	/**
	 * 为指定用户添加系统功能权限<br>
	 * 适用于一次添加多个系统功能权限
	 * @param userInfo 用户信息，其编号字段必须赋值
	 * @param userRightSystemFeatures 用户的系统功能权限集合，其成员对象的系统功能编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveRightSystemFeaturesForUser(UserInfo userInfo,List<UserRightSystemFeature> userRightSystemFeatures,ErrInfo pErrInfo);
	
	/**
	 * 为指定用户移除系统功能权限
	 * @param userInfo 用户信息，其编号字段必须赋值
	 * @param userRightSystemFeature 用户的系统功能权限，其系统功能编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteRightSystemFeatureForUser(UserInfo userInfo,UserRightSystemFeature userRightSystemFeature,ErrInfo pErrInfo);
	
	/**
	 * 为指定用户移除系统功能权限
	 * @param userInfo 用户信息，其编号字段必须赋值
	 * @param userRightSystemFeatures 用户的系统功能权限集合，其成员对象的系统功能编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteRightSystemFeaturesForUser(UserInfo userInfo,List<UserRightSystemFeature> userRightSystemFeatures,ErrInfo pErrInfo);
	
	/**
	 * 获取用户直接授权的系统功能菜单权限<br>
	 * 返回的菜单功能集合具备树状结构
	 * @param pUserID 指定的用户编号
	 * @param systemFeatures 返回对该用户直接授权的顶层系统功能菜单集合（一级菜单项集合），以UclKey为集合关键字，下级功能菜单项可通过ChildSystemFeatures属性访问
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findRightSystemFeatureMenusByUserID(int pUserID,LinkedHashMap<String,SystemFeature> systemFeatures,ErrInfo pErrInfo);
	
	/**
	 * 获取用户直接授权的系统功能权限（UCL:用户访问控制列表）
	 * @param pUserID 指定的用户编号
	 * @param systemFeatures 返回对该用户直接授权的系统功能集合，以UclKey为集合关键字，注意该功能集合是平面型的（没有层次结构），大小功能全部都在该集合中
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findRightSystemFeaturesByUserID(int pUserID,Map<String,SystemFeature> systemFeatures,ErrInfo pErrInfo);
	
	/**
	 * 获取所有的授权的系统功能菜单权限<br>
	 * 返回的菜单功能集合具备树状结构
	 * @param pUserID 指定的用户编号
	 * @param systemFeatures 返回对该用户直接授权的顶层系统功能菜单集合（一级菜单项集合），以UclKey为集合关键字，下级功能菜单项可通过ChildSystemFeatures属性访问
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findAllSystemFeature(LinkedHashMap<String,SystemFeature> systemFeatures,ErrInfo pErrInfo);
	
	/**
	 * 为指定用户移除系统功能权限
	 * @param pUserID 用户编号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteRightSystemFeaturesByUserID(int pUserID,ErrInfo pErrInfo);
	
}
