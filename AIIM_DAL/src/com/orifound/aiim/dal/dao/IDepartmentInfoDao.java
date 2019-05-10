/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.DepartmentInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 部门信息表的DAO接口定义
 *
 */
public interface IDepartmentInfoDao
{

	/**
	 * Dao接口定义：添加部门
	 * @param departmentInfo 要添加的部门
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(DepartmentInfo departmentInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的部门
	 * @param departmentInfo 要删除的部门
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(DepartmentInfo departmentInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的部门
	 * @param departmentInfo 要更新的部门
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(DepartmentInfo departmentInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的部门
	 * @param departmentInfos 返回查找成功的部门集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<DepartmentInfo> departmentInfos, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据部门编号查找部门
	 * @param pID 指定的部门编号
	 * @param departmentInfo 返回查找成功的部门
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, DepartmentInfo departmentInfo, ErrInfo pErrInfo);

}
