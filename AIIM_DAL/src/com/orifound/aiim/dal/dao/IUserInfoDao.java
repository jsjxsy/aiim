/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.*;

/**
 * UserInfo表的DAO接口定义
 *
 */
public interface IUserInfoDao {

	/**
	 * DAO接口定义：添加一个新用户
	 * @param userInfo 要添加的用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(UserInfo userInfo,ErrInfo pErrInfo);
	
	/**
	 * DAO接口定义：删除指定用户
	 * @param userInfo 要删除的用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(UserInfo userInfo,ErrInfo pErrInfo);
	
	
	/**
	 * DAO接口定义：更新指定用户
	 * @param userInfo 要更新的用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(UserInfo userInfo,ErrInfo pErrInfo);
	

	/**
	 * DAO接口定义：查找所有用户
	 * @param userInfos 查找成功后返回的用户信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<UserInfo> userInfos, ErrInfo pErrInfo);

	/**
	 * DAO接口定义：根据用户名查找指定用户
	 * @param userName 用户名
	 * @param userInfo 查找成功后返回的用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByUserName(String userName,UserInfo userInfo, ErrInfo pErrInfo);
	
	/**
	 * DAO接口定义：根据用户真实姓名查找指定用户
	 * @param realName 用户名
	 * @param userInfos 查找成功后返回的用户信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByRealName(String realName,List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	/**
	 * DAO接口定义：根据证件查找指定用户
	 * @param userName 用户名
	 * @param userInfo 查找成功后返回的用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByIDCard(int iDCardTypeID,String iDCardNo,UserInfo userInfo, ErrInfo pErrInfo);
	 

	/**
	 * DAO接口定义：根据部门编号查找指定用户的
	 * @param departmentID 部门编号
	 * @param userInfos 查找成功后返回的用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByDepartmentID(int departmentID,List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	/**
	 * DAO接口定义：查找系统中匿名用户
	 * @param userInfo 查找成功后返回的匿名用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAnonymous(UserInfo userInfo, ErrInfo pErrInfo);
	
	
	/**
	 * DAO接口定义：查询指定系统定义角色的人员信息集合
	 * @param userInfos 查找成功后返回的用户信息
	 * @param systemRole 指定的系统定义角色
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findBusinessGuids(List<UserInfo> userInfos, EnumSystemRole systemRole, ErrInfo pErrInfo);
	
	/**
	 * DAO接口定义：查询业务指导室指导的所有档案兼职人员
	 * @param businessGuidIds 业务指导室人员id集合
	 * @param userIds 档案兼职人员Id集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findPartTimePersons(List<Integer> businessGuidIds, List<Integer> userIds, ErrInfo pErrInfo);
	
	/**
	 * DAO接口定义：查询所有档案管理部门的人员（除去馆长、副馆长）
	 * @param userInfos 查找成功后返回的用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllArchivesManagers(List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：查询任务接收人集合
	 * @param userInfos 返回的用户信息集合 
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findTaskPersons(List<UserInfo> userInfos, ErrInfo pErrInfo);

	
	/**
	 * 查询所有符合条件的人员
	 * @dataPageInfo 分页集合
	 * @param userInfoQueryCondition 查询条件
	 * @param userInfos 返回任务接收人集合 
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserInfosByCondition(Map<String,Object> userInfoQueryCondition,DataPageInfo dataPageInfo, List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	/**
	 * 
	 * @param pID
	 * @param userInfo 返回的用户信息 
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserInfoByUserID(int pID,UserInfo userInfo, ErrInfo pErrInfo);

	
	/**
	 * 查询所有具有档案管理室角色的人员信息集合
	 * @param userInfos 查找成功后返回的用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesinfoManagers(List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	/**
	 * 亘古用户ID查询所有被代理的用户信息
	 * @param pID
	 * @param userInfos 查找成功后返回的用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserChargeUserInfoByUserID(int pID,List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	
	/**
	 * 查询所有被代理的用户信息
	 * @param userInfos 查找成功后返回的用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllUserUnchargeUserInfo(Map<String, Object> userInfoQueryCondition,DataPageInfo dataPageInfo,List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	/**
	 *根据查询条件查询出代理信息 
	 * @param userInfoQueryCondition 查询条件
	 * @param dataPageInfo 分页集合
	 * @param userInfos 查找成功后返回的用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUserProxyInfosByCondition(Map<String, Object> userInfoQueryCondition, DataPageInfo dataPageInfo, List<UserInfo> userInfos, ErrInfo pErrInfo); 
	
	/**
	 * 修改密码
	 * @param userInfos 查找成功后返回的用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updatePassword(UserInfo userInfo, ErrInfo pErrInfo);
	
	/**
	 * 检查用户名是否唯一
	 * @param userInfos 用于查找的用户信息
	 * @param exists 检查用户名已经存在个数
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
    boolean checkUserNameExists(UserInfo userInfo,IntegerEx exists, ErrInfo pErrInfo);
	
	/**
	 * 检查证件号是否唯一
	 * @param userInfos 用于查找的用户信息
	 * @param exists 检查证件号已经存在个数
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean checkIDCardNoExists(UserInfo userInfo,IntegerEx exists, ErrInfo pErrInfo);
	
	/**
	 * 查询未加入指定角色的用户
	 * @param pRoleID 角色编号
	 * @param dataPageInfo 分页集合
	 * @param userInfoQueryCondition 查询条件
	 * @param userInfos 返回任务接收人集合 
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findUseresNotInRoleID(int pRoleID,Map<String,Object> userInfoQueryCondition,DataPageInfo dataPageInfo, List<UserInfo> userInfos, ErrInfo pErrInfo);
	

}