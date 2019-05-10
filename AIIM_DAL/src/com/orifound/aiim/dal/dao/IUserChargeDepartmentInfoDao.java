/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.UserChargeDepartmentInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 业务专员负责的部门信息表的DAO接口定义
 *
 */
public interface IUserChargeDepartmentInfoDao
{

	/**
	 * Dao接口定义：添加业务专员负责的部门
	 * @param userChargeDepartmentInfo 要添加的业务专员负责的部门
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(UserChargeDepartmentInfo userChargeDepartmentInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的业务专员负责的部门
	 * @param userChargeDepartmentInfo 要删除的业务专员负责的部门
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(UserChargeDepartmentInfo userChargeDepartmentInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的业务专员负责的部门
	 * @param userChargeDepartmentInfo 要更新的业务专员负责的部门
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(UserChargeDepartmentInfo userChargeDepartmentInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找指定业务专员所负责的部门
	 * @param pUserID 指定的用户编号
	 * @param userChargeDepartmentInfo 返回查找成功的业务专员负责的部门，以部门编号作为集合关键字
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByUserID(int pUserID, LinkedHashMap<Integer,UserChargeDepartmentInfo> userChargeDepartmentInfos, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：查找所有没有人负责的部门
	 * @param userChargeDepartmentInfos 返回查找成功的业务专员负责的部门，以部门编号作为集合关键字
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllUnchargeDepartment(List<UserChargeDepartmentInfo> userChargeDepartmentInfos, ErrInfo pErrInfo);

}
