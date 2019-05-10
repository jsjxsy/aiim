/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRightSystemFeature;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 用户的系统功能授权表的DAO接口定义
 *
 */
public interface IUserRightSystemFeatureDao
{

	/**
	 * Dao接口定义：添加用户系统功能授权
	 * @param userRightSystemFeature 要添加的用户系统功能授权
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(UserRightSystemFeature userRightSystemFeature, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：添加用户系统功能授权
	 * @param userRightSystemFeature 要添加的用户系统功能授权
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveUserRightSystemFeatureByUserID(UserInfo userInfo,UserRightSystemFeature userRightSystemFeature, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：删除指定的用户系统功能授权
	 * @param userRightSystemFeature 要删除的用户系统功能授权
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(UserRightSystemFeature userRightSystemFeature, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找指定用户的系统功能授权
	 * @param pUserID 指定的用户编号
	 * @param userRightSystemFeatures 返回查找成功的用户系统功能授权集合，平面结构的系统功能集合，大小功能都在该集合中，以UclKey作为集合键
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByUserID(int pUserID,Map<String,SystemFeature> userRightSystemFeatures, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找指定用户的系统功能菜单授权
	 * @param pUserID 指定的用户编号
	 * @param userRightMenus 返回查找成功的用户系统一级功能菜单授权集合，树状结构的系统功能菜单集合，可访问下级菜单项，以UclKey作为集合键
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findMenusByUserID(int pUserID,LinkedHashMap<String,SystemFeature> userRightMenus, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：删除指定用户和指定系统功能的用户系统功能授权
	 * @param userInfo指定的用户
	 * @param userRightSystemFeature 要删除指定的系统功能
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteUserRightSystemFeatureByUserIDAndFeatureID(UserInfo userInfo,UserRightSystemFeature userRightSystemFeature, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：删除指定用户下所有的系统功能的用户系统功能授权
	 * @param pUserID 用户编号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean deleteUserRightSystemFeatureByUserID(int pUserID, ErrInfo pErrInfo);

	
}
