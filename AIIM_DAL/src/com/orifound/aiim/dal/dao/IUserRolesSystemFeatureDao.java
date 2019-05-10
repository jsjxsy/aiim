/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.UserRolesSystemFeature;
import com.orifound.aiim.entity.ErrInfo;

/**
 * UserRolesSystemFeature表的DAO接口定义
 *
 */
public interface IUserRolesSystemFeatureDao {

	/**
	 * Dao接口定义：添加UserRolesSystemFeature
	 * @param pUserRolesSystemFeature 要添加的UserRolesSystemFeature
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的UserRolesSystemFeature
	 * @param pUserRolesSystemFeature 要删除的UserRolesSystemFeature
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的UserRolesSystemFeature
	 * @param pUserRolesSystemFeature 要更新的UserRolesSystemFeature
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的UserRolesSystemFeature
	 * @param pUserRolesSystemFeatures 返回查找成功的UserRolesSystemFeature集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<UserRolesSystemFeature> pUserRolesSystemFeatures, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找UserRolesSystemFeature
	 * @param pID 指定的唯一标识
	 * @param pUserRolesSystemFeature 返回查找成功的UserRolesSystemFeature
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：根据角色唯一标识查找UserRolesSystemFeature
	 * @param pRoleID 用户角色
	 * @param userRolesMenus 返回的角色系统功能菜单集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findByRoleID(int pRoleID,List<UserRolesSystemFeature> pUserRolesSystemFeatures, ErrInfo pErrInfo);
	
	/**
	 *  Dao接口定义：删除指定角色下所有的serRolesSystemFeature
	 * @param pRoleID 角色编号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteByRoleID(int pRoleID, ErrInfo pErrInfo);

}
