/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;


/**
 * 系统用户权限初始化服务接口
 *
 */
public interface ISystemUserRightInitializeService 
{
	
	/**
	 * 获取指定用户具备的系统功能菜单权限<br>
	 * 包括该用户所属角色所具备的系统功能菜单权限，是用户权限和角色权限的合集，返回的菜单功能集合具备树状结构
	 * @param pUserID 用户编号
	 * @param pRolesID 用户所属角色的编号数组
	 * @param systemFeatureMenus 返回对该用户直接授权的顶层系统功能菜单集合（一级菜单项集合），下级功能菜单项可通过ChildSystemFeatures属性访问
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findRightSystemFeatureMenusByUserID(int pUserID,int[] pRolesID, LinkedHashMap<String,SystemFeature> systemFeatureMenus,ErrInfo pErrInfo);
	
	/**
	 * 获取指定用户具备的系统功能权限（UCL:用户访问控制列表）<br>
	 * 包括该用户所属角色所具备的系统功能权限，是用户权限和角色权限的合集
	 * @param pUserID 用户编号
	 * @param pRolesID 用户所属角色的编号数组
	 * @param systemFeatures 返回该用户具备的系统功能权限集合，以UclKey为集合关键字，注意该功能集合是平面型的（没有层次结构），大小功能全部都在该集合中
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findRightSystemFeaturesByUserID(int pUserID,int[] pRolesID,Map<String,SystemFeature> systemFeatures,ErrInfo pErrInfo);
	
	/**
	 * 获取指定用户具备的档案资源权限<br>
	 * 包括该用户所属角色所具备的档案资源权限，是用户权限和角色权限的合集
	 * @param pUserID 用户编号
	 * @param pRolesID 用户所属角色的编号数组
	 * @param archivesTypes 返回该用户具备的档案资源权限集合，以档案分类编号作为集合键，具备树状结构，可访问下属档案分类
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findRightArchivesTypesByUserID(int pUserID,int[] pRolesID,LinkedHashMap<Integer,ArchivesType> archivesTypes,ErrInfo pErrInfo);
	
	
	/**
	 * 获取指定用户具备的档案密级权限
	 * @param pUserID 用户编号
	 * @param pRolesID 用户所属角色的编号数组
	 * @param archivesSecrecys 返回对该用户直接授权的档案密级信息集合，以密级编号作为集合关键字
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findRightArchivesSecrecysByUserID(int pUserID,int[] pRolesID,Map<Integer,ArchivesSecrecy> archivesSecrecys,ErrInfo pErrInfo);
	
	
}
