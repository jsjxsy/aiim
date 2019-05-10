/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRightArchivesType;

/**
 * 用户档案分类授权管理服务接口
 *
 */
public interface IUserRightArchivesTypeManageService
{
	/**
	 * 为指定用户添加档案分类权限<br>
	 * 适用于一次添加一个档案分类权限
	 * @param userInfo 用户信息，其编号字段必须赋值
	 * @param userRightArchivesType 用户的档案分类权限，其档案分类编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveRightArchivesTypeForUser(UserInfo userInfo,UserRightArchivesType userRightArchivesType,ErrInfo pErrInfo);
	
	/**
	 * 为指定用户添加档案分类权限<br>
	 * 适用于一次添加多个档案分类权限
	 * @param userInfo 用户信息，其编号字段必须赋值
	 * @param userRightArchivesTypes 用户的档案分类权限集合，其成员对象的档案分类编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveRightArchivesTypesForUser(UserInfo userInfo,List<UserRightArchivesType> userRightArchivesTypes,ErrInfo pErrInfo);
	
	/**
	 * 为指定用户移除档案分类权限<br>
	 * 适用于一次移除多个档案分类权限
	 * @param userInfo 用户信息，其编号字段必须赋值
	 * @param userRightArchivesTypes 用户的档案分类权限集合，其成员对象的档案分类编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteRightArchivesTypesForUser(UserInfo userInfo,List<UserRightArchivesType> userRightArchivesTypes,ErrInfo pErrInfo);
	
	/**
	 * 获取指定用户的档案分类权限
	 * @param pUserID 指定的用户编号
	 * @param archivestypes 返回对该用户直接授权的档案分类集合（非树状结构），以档案分类编号作为集合关键字
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findRightArchivesTypeByUserID(int pUserID,LinkedHashMap<Integer,ArchivesType> archivestypes,ErrInfo pErrInfo);
	
	
	/**
	 * 为指定用户移除档案分类权限<br>
	 * 适用于一次移除多个档案分类权限
	 * @param userInfo 用户信息，其编号字段必须赋值
	 * @param userRightArchivesType 用户的档案分类权限，其成员对象的档案分类编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean deleteRightArchivesTypeForUser(UserInfo userInfo, UserRightArchivesType userRightArchivesTypes, ErrInfo pErrInfo);
	
	
	/**
	 * 为指定用户移除所有的档案分类权限<br>
	 * @param UserID 用户编号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean deleteRightArchivesTypeByUserID(int UserID, ErrInfo pErrInfo);
	
	
}
