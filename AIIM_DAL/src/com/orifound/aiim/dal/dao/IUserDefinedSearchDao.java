package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserDefinedSearch;

/**
 * 用户自定义条件查询数据访问接口
 * @author Administrator
 *
 */
public interface IUserDefinedSearchDao {	

	/**
	 * Dao接口定义：添加用户自定义条件查询对象
	 * @param pUserDefinedSeach 要添加的UserDefinedSeach
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean addUserDefinedSearchs( UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的用户自定义条件查询对象
	 * @param pUserDefinedSeach 要删除的UserDefinedSeach
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo);



	/**
	 * Dao接口定义：查找指定用户的所有用户自定义条件查询对象
	 * @param userDefinedSearchs 返回查找成功的用户自定义条件查询对象集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserDefinedSearchsByUserID(int userID,List<UserDefinedSearch> userDefinedSearchs, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找用户自定义条件查询对象
	 * @param pID 指定的唯一标识
	 * @param pUserDefinedSeach 返回查找成功的UserDefinedSeach
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserDefinedSearchByID(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo);

	
	/**
	 * Dao接口定义：检查自定义条件查询名称是否已经存在
	 * 当该名称存在时就将该对象返回，如不存在则将其ID赋0
	 * @param pID 指定的唯一标识
	 * @param pUserDefinedSeach 返回查找成功的UserDefinedSeach
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean checkQueryNameExist(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo);
}
