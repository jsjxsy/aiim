/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.UserRightArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 用户档案分类授权表的DAO接口定义
 *
 */
public interface IUserRightArchivesTypeDao
{

	/**
	 * Dao接口定义：添加用户档案分类授权
	 * @param userRightArchivesType 要添加的用户档案分类授权
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(UserRightArchivesType userRightArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的用户档案分类授权
	 * @param userRightArchivesType 要删除的用户档案分类授权
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(UserRightArchivesType userRightArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的用户档案分类授权
	 * @param userRightArchivesType 要更新的用户档案分类授权
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(UserRightArchivesType userRightArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找指定用户的档案分类授权
	 * @param pUserID 指定的用户编号
	 * @param userRightArchivesType 返回查找成功的用户档案分类授权集合，以档案分类编号作为集合键
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByUserID(int pUserID, LinkedHashMap<Integer,ArchivesType> userRightArchivesTypes, ErrInfo pErrInfo);
	
	/**
	 * 删除指定用户下的所有userRightArchivesTypes信息
	 * @param pUserID 指定的用户编号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean deleteByUserID(int pUserID, ErrInfo pErrInfo);
}
