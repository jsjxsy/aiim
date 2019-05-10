/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.DepartmentInfo;
import com.orifound.aiim.entity.EnumSystemRole;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.UserInfo;

/**
 * 用户信息管理服务的接口定义
 *
 */
public interface IUserInfoManageService {

	/**
	 * 登录系统
	 * @param userInfo 当前登录的用户（用户名和密码属性必须赋值）
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean login(UserInfo userInfo,ErrInfo pErrInfo);
	
	/**
	 * 匿名登录系统
	 * @param userInfo 返回登录成功的匿名用户
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean loginWithAnonymous(UserInfo userInfo,ErrInfo pErrInfo);
	
	/**
	 * 退出系统
	 * @param userInfo 当前登录的用户
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean logout(UserInfo userInfo,ErrInfo pErrInfo);
	
	/**
	 * 获取匿名用户
	 * @param userInfo 获取成功返回的匿名用户
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAnonymousUser(UserInfo userInfo,ErrInfo pErrInfo);
	
	/**
	 * 根据用户编号查找用户信息
	 * @param pUserID 证件号码
	 * @param userInfo 返回查找成功的用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserByUserID(Integer pUserID,UserInfo userInfo,ErrInfo pErrInfo);
	
	/**
	 * 根据证件号码查找用户信息
	 * @param pIDCardNo 证件号码
	 * @param userInfo 返回查找成功的用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserByIDCardNo(String pIDCardNo,UserInfo userInfo,ErrInfo pErrInfo);
	
	/**
	 * 根据用户真实姓名查找用户信息
	 * @param pIDCardNo 用户真实姓名
	 * @param userInfo 返回查找成功的用户信息集合，有可能相同姓名的用户存在多个
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserByRealName(String realName,List<UserInfo> userInfos,ErrInfo pErrInfo);
	
	/**
	 * 查找系统中所有用户信息
	 * @param userInfos 返回查找成功的所有系统用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUsers(List<UserInfo> userInfos,ErrInfo pErrInfo);
	
	/**
	 * 查找指定部门下的所有用户信息
	 * @param departmentID 部门编号
	 * @param userInfos 返回指定部门下的所有用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUsersByDepartmentID(int departmentID,List<UserInfo> userInfos,ErrInfo pErrInfo);
	
	/**
	 * 添加一个系统用户
	 * 
	 * @param userInfo 要添加的用户
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveUserInfo(UserInfo userInfo,ErrInfo pErrInfo);
	
	/**
	 * 删除指定的系统用户
	 * 
	 * @param userInfo 要删除的用户，其用户编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteUserInfo(UserInfo userInfo,ErrInfo pErrInfo);
	
	/**
	 * 更新指定的系统用户信息
	 * 
	 * @param userInfo 要更新的用户信息，其用户编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateUserInfo(UserInfo userInfo,ErrInfo pErrInfo);
	
	/**
	 * 添加指定用户到指定部门<br>
	 * 适用于一次添加一个用户
	 * @param userInfo 用户信息
	 * @param departmentInfo 部门信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean addUserToDeparment(UserInfo userInfo,DepartmentInfo departmentInfo,ErrInfo pErrInfo);
	
	/**
	 * 添加指定用户到指定部门<br>
	 * 适用于一次添加多个用户
	 * @param userInfos 用户信息集合
	 * @param departmentInfo 部门信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean addUsersToDeparment(List<UserInfo> userInfos,DepartmentInfo departmentInfo,ErrInfo pErrInfo);
	
	/**
	 * 指定用户所负责的部门<br>
	 * 适用于一次指定一个部门
	 * @param userInfo 用户信息，其用户编号字段必须赋值
	 * @param departmentInfo 部门信息，其部门编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveUserChargeDepartment(UserInfo userInfo,DepartmentInfo departmentInfo,ErrInfo pErrInfo);
	
	/**
	 * 指定用户所负责的部门<br>
	 * 适用于一次指定多个部门
	 * @param userInfo 用户信息，其用户编号字段必须赋值
	 * @param departmentInfos 部门信息集合，其成员对象的部门编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveUserChargeDepartments(UserInfo userInfo,List<DepartmentInfo> departmentInfos,ErrInfo pErrInfo);
	
	/**
	 * 取消用户与部门之间的负责关系<br>
	 * 适用于一次取消一个部门
	 * @param userInfo 用户信息，其用户编号字段必须赋值
	 * @param departmentInfo 部门信息，其部门编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteUserChargeDepartment(UserInfo userInfo,DepartmentInfo departmentInfo,ErrInfo pErrInfo);
	
	/**
	 * 取消用户与部门之间的负责关系<br>
	 * 适用于一次取消多个部门
	 * @param userInfo 用户信息，其用户编号字段必须赋值
	 * @param departmentInfos 部门信息集合，其成员对象的部门编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteUserChargeDepartments(UserInfo userInfo,List<DepartmentInfo> departmentInfos,ErrInfo pErrInfo);
	
	/**
	 * 查询任务接收人集合
	 * @param userInfos 返回任务接收人集合 
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findTaskPersons(List<UserInfo> userInfos, ErrInfo pErrInfo);

	
	/**
	 * 查询所有符合条件的人员
	 * @param userInfoQueryCondition 查询条件
	 * @param userInfos 返回任务接收人集合 
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserInfosByCondition(Map<String,Object> userInfoQueryCondition,DataPageInfo dataPageInfo,List<UserInfo> userInfos,ErrInfo pErrInfo);


	/**
	 * 根据系统角色查找用户信息
	 * @param esnumSystemRole 用户角色
	 * @param userInfos 返回的用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserBySystemRole(EnumSystemRole esnumSystemRole, List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	/**
	 * 
	 * @param pID
	 * @param userInfos 返回的用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserChargeUserInfosByUserID(int pID,List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	
	/**
	 * 查找所有未代理的用户信息
	 * @param userInfos 返回的用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllUserUnchargeUserInfosByUserID(Map<String, Object> userInfoQueryCondition,DataPageInfo dataPageInfo,List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	/**
	 * 查询所有符合条件的代理人员
	 * @param userInfoQueryCondition 查询条件
	 * @param userInfos 返回任务接收人集合 
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserProxyInfosByCondition(Map<String,Object> userInfoQueryCondition,DataPageInfo dataPageInfo,List<UserInfo> userInfos,ErrInfo pErrInfo);
	
	/**
	 * 修改密码
	 * @param userInfos 返回任务接收人集合 
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	 boolean modifyPassword(UserInfo userInfo, ErrInfo pErrInfo);
	
	/**
	 * 检查用户名是否唯一
	 * @param userInfos 用于查找的用户信息
	 * @param int 检查用户名已经存在返回true，否侧返回false
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
    boolean checkUserNameExists(UserInfo userInfo,IntegerEx exists, ErrInfo pErrInfo);
	
	/**
	 * 检查证件号是否唯一
	 * @param userInfos 用于查找的用户信息
	 * @param exists 检查证件号已经存在返回true，否侧返回false
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean checkIDCardNoExists(UserInfo userInfo,IntegerEx exists, ErrInfo pErrInfo);

	/**
	 * 查询未加入指定角色的用户
	 * @param pRoleID 角色编号
	 * @param userInfoQueryCondition 查询条件
	 * @param userInfos 返回任务接收人集合 
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUseresNotInRoleID(int pRoleID, Map<String,Object> userInfoQueryCondition, DataPageInfo dataPageInfo,List<UserInfo> userInfos , ErrInfo pErrInfo);
}
