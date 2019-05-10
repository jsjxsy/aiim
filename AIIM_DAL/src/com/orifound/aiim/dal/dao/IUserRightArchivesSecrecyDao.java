/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.UserRightArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 用户档案密级授权表的DAO接口定义
 *
 */
public interface IUserRightArchivesSecrecyDao
{

	/**
	 * Dao接口定义：添加用户档案密级授权
	 * @param userRightArchivesSecrecy 要添加的用户档案密级授权
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(UserRightArchivesSecrecy userRightArchivesSecrecy, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的用户档案密级授权
	 * @param userRightArchivesSecrecy 要删除的用户档案密级授权
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(UserRightArchivesSecrecy userRightArchivesSecrecy, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找指定用户的档案密级授权
	 * @param pUserID 指定的用户编号
	 * @param userRightArchivesSecrecys 返回查找成功的用户档案密级授权集合，以密级编号作为集合键
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByUserID(int pUserID,Map<Integer,ArchivesSecrecy> userRightArchivesSecrecys, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：查找指定用户的档案密级授权
	 * @param pUserID 指定的用户编号
	 * @param userRightArchivesSecrecys 返回查找成功的用户档案密级授权集合，以密级编号作为集合键
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByUserID(int pUserID, List<UserRightArchivesSecrecy> userRightArchivesSecrecys, ErrInfo pErrInfo);
	
	/**
	 * 删除指定用户下所有的档案密级权限
	 * @param pUserID 指定的用户编号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteByUserID(int pUserID, ErrInfo pErrInfo);
}
