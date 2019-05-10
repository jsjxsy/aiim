/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRightArchivesSecrecy;

/**
 * 用户档案密级授权管理服务接口
 *
 */
public interface IUserRightArchivesSecrecyManageService
{
	/**
	 * 为指定用户添加档案密级权限<br>
	 * 适用于一次添加多个档案密级权限
	 * @param userInfo 用户信息，其编号字段必须赋值
	 * @param userRightArchivesSecrecies 用户的档案密级权限集合，其成员对象的档案密级编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveRightArchivesSecrecysForUser(UserInfo userInfo,List<UserRightArchivesSecrecy> userRightArchivesSecrecies,ErrInfo pErrInfo);
	
	/**
	 * 为指定用户移除档案密级权限<br>
	 * 适用于一次移除多个档案密级权限
	 * @param userInfo 用户信息，其编号字段必须赋值
	 * @param userRightArchivesSecrecies 用户的档案密级权限集合，其成员对象的档案密级编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteRightArchivesSecrecysForUser(UserInfo userInfo,List<UserRightArchivesSecrecy> userRightArchivesSecrecies,ErrInfo pErrInfo);
	
	/**
	 * 获取指定用户的档案密级权限
	 * @param pUserID 指定的用户编号
	 * @param archivesSecrecys 返回对该用户直接授权的档案密级信息集合，以密级编号作为集合关键字
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findRightArchivesSecrecysByUserID(int pUserID,Map<Integer,ArchivesSecrecy> archivesSecrecys,ErrInfo pErrInfo);
	
	/**
	 * 获取指定用户的档案密级权限集合
	 * @param pUserID  指定的用户编号
	 * @param userRightArchivesSecrecys 返回对该用户直接授权的档案密级信息集合，以密级编号作为集合关键字
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findRightArchivesSecrecysByUserID(int pUserID,List<UserRightArchivesSecrecy> userRightArchivesSecrecys, ErrInfo pErrInfo);
	
	/**
	 * 删除指定用户下所有的档案密级权限
	 * @param pUserID  指定的用户编号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean deleteUserRightArchivesSecrecyByUserID(int pUserID, ErrInfo pErrInfo);
}
