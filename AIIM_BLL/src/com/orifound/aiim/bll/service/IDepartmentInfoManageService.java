/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.DepartmentInfo;
import com.orifound.aiim.entity.ErrInfo;


/**
 * 部门信息管理服务的接口定义
 *
 */
public interface IDepartmentInfoManageService {

	/**
	 * 根据部门编号查找部门信息
	 * @param departmentID 部门编号
	 * @param departmentInfo 返回查找成功的部门信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findDepartmentInfoByID(int departmentID,DepartmentInfo departmentInfo,ErrInfo pErrInfo);
	
	/**
	 * 查找系统中所有部门信息
	 * @param departmentInfos 返回查找成功的所有部门信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findDepartmentInfos(List<DepartmentInfo> departmentInfos,ErrInfo pErrInfo);
	
	/**
	 * 添加一个部门
	 * @param departmentInfo 要添加的部门信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveDepartmentInfo(DepartmentInfo departmentInfo,ErrInfo pErrInfo);
	
	/**
	 * 更新指定部门信息
	 * @param departmentInfo 要更新的部门信息，其部门编号属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateDepartmentInfo(DepartmentInfo departmentInfo,ErrInfo pErrInfo);
	
	/**
	 * 删除指定部门信息
	 * @param departmentInfo 要删除的部门信息，其部门编号属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteDepartmentInfo(DepartmentInfo departmentInfo,ErrInfo pErrInfo);
	
	/**
	 * 鉴定管理->存毁鉴定登记：查找系统中所有档案形成部门信息
	 * @param departmentInfos 返回查找成功的所有部门信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findFormationDepartments(List<DepartmentInfo> formationDepartments,ErrInfo pErrInfo);
	
}
