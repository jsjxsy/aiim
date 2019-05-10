package com.orifound.aiim.bll.service;

	import java.util.List;

	import com.orifound.aiim.entity.UserDefinedSearch;
	import com.orifound.aiim.entity.ErrInfo;

	/**
	 * 用户自定义条件管理服务的接口定义
	 *
	 */
	public interface IUserDefinedSearchManageService {

		/**
		 * 添加一个新的用户自定义条件
		 * @param userDefinedSearch 新添加的用户自定义条件信息
		 * @param pErrInfo 返回处理失败的错误信息
		 * @return 处理成功返回true，否则返回false
		 */
		boolean addUserDefinedSearch(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo);

		/**
		 * 删除指定的用户自定义条件
		 * @param userDefinedSearch 要删除的用户自定义条件，其唯一标识字段必须赋值
		 * @param pErrInfo 返回处理失败的错误信息
		 * @return 处理成功返回true，否则返回false
		 */
		boolean deleteUserDefinedSearch(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo);
	

		/**
		 * 查询指定用户的所有自定义条件信息
		 * @param userDefinedSearchs 返回查找成功的用户自定义条件集合
		 * @param pErrInfo 返回处理失败的错误信息
		 * @return 处理成功返回true，否则返回false
		 */
		boolean findUserDefinedSearchsByUserID(int userID,List<UserDefinedSearch> userDefinedSearchs, ErrInfo pErrInfo);

		/**
		 * 根据唯一标识查找用户自定义条件信息
		 * @param pID 指定的唯一标识
		 * @param userDefinedSearch 返回查找成功的用户自定义条件信息
		 * @param pErrInfo 返回处理失败的错误信息
		 * @return 处理成功返回true，否则返回false
		 */
		boolean findUserDefinedSearchByID(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo);
		
		/**
		 * 检查自定义条件查询名称是否已经存在<br>
		 * 当该名称存在时就将该对象返回，如不存在则将其ID赋0
		 * @param userDefinedSearch 用户自定义条件查询对象：name属性必需赋值
		 * @param pErrInfo 返回处理失败的错误信息
		 * @return 处理成功返回true，否则返回false
		 */
		boolean checkQueryNameExist(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo);

	}

