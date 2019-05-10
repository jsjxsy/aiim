/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.UserRolesSystemFeature;
import com.orifound.aiim.entity.ErrInfo;

/**
 * UserRolesSystemFeature管理服务的接口定义
 *
 */
public interface IUserRolesSystemFeatureManageService {

	/**
	 * 添加一个新的UserRolesSystemFeature
	 * @param pUserRolesSystemFeature 新添加的UserRolesSystemFeature信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveUserRolesSystemFeature(UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo);
	
	/**
	 * 添加多个新的UserRolesSystemFeature
	 * @param pUserRolesSystemFeatures 新添加的UserRolesSystemFeature信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveUserRolesSystemFeatures(List<UserRolesSystemFeature> pUserRolesSystemFeatures, ErrInfo pErrInfo);

	/**
	 * 删除指定的UserRolesSystemFeature
	 * @param pUserRolesSystemFeature 要删除的UserRolesSystemFeature，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteUserRolesSystemFeature(UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo);

	/**
	 * 修改指定的UserRolesSystemFeature
	 * @param pUserRolesSystemFeature 修改后的UserRolesSystemFeature信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateUserRolesSystemFeature(UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo);

	/**
	 * 查找所有的UserRolesSystemFeature信息
	 * @param pUserRolesSystemFeatures 返回查找成功的UserRolesSystemFeature集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserRolesSystemFeatures(List<UserRolesSystemFeature> pUserRolesSystemFeatures, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找UserRolesSystemFeature信息
	 * @param pID 指定的唯一标识
	 * @param pUserRolesSystemFeature 返回查找成功的UserRolesSystemFeature信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserRolesSystemFeatureByID(int pID, UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo);

	/**
	 * 根据角色唯一标识查找UserRolesSystemFeature信息
	 * @param pRoleID 指定的角色唯一标识
	 * @param pUserRolesSystemFeature 返回查找成功的UserRolesSystemFeature信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserRolesSystemFeatureByRoleID(int pRoleID,List<UserRolesSystemFeature> pUserRolesSystemFeatures, ErrInfo pErrInfo);
	
	/**
	 * 删除指定角色下所有的UserRolesSystemFeature信息
	 * @param pRoleID 指定的角色唯一标识
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteUserRolesSystemFeaturesByRoleID(int  pRoleID, ErrInfo pErrInfo);
}
