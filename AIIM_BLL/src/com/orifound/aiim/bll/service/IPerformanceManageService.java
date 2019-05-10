/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;



/**
 * 绩效管理服务的接口定义
 * @author tyb
 */
public interface IPerformanceManageService {
	/**
	 * 监测档案形成部门、业务指导室的人员特定时间段内著录档案的数量<br>
	 * @param recordCondition map集合保存:人员真实姓名-著录数量 键值对
	 * @param userIds 人员id集合
	 * @param beginTime 检测开始时间	
	 * @param endTime	检测结束时间
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	 boolean findRecordCondition(Map<String, Integer> recordCondition, List<Integer> userIds, String beginTime, String endTime, ErrInfo pErrInfo);
	
	 /**
	 * 监测业务指导室人员特定时间段内审核、待审核档案的数量<br>
	 * @param recordAudit 封装成Map<String, Integer[]>保存人员审核档案数量,String保存人员真实姓名,Integer[0]保存已审核、Integer[1]待审核
	 * @param userIds 人员id集合
	 * @param beginTime 检测开始时间
	 * @param endTime	检测结束时间
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	 boolean findRecordAuditCondition(Map<String, Integer[]> recordAudit, List<Integer> userIds, String beginTime, String endTime, ErrInfo pErrInfo);
	
	 /**
	 * 查询所有具有业务指导室角色的人员信息集合
	 * @param userInfos 查找成功后返回的用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findBusinessGuids(List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	/**
	 * 查询业务指导室指导的所有档案兼职人员
	 * @param businessGuidIds 业务指导室人员id集合
	 * @param userIds 档案兼职人员Id集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findPartTimePersons(List<Integer> businessGuidIds, List<Integer> userIds, ErrInfo pErrInfo);
	
	/**
	 * 绩效管理->入库工作量查询
	 * @param inStorageCondition 封装成Map<String, Integer>保存档案管理室入库工作情况
	 * @param beginTime 监测开始时间
	 * @param endTime 监测结束时间
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return boolean 处理成功返回true，否则返回false
	 */
	boolean findInStorageCondition(Map<String, Integer> inStorageCondition, String beginTime, String endTime, ErrInfo pErrInfo);
	
	/**
	 * 查询所有具有档案管理室角色的人员信息集合
	 * @param userInfos 查找成功后返回的用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesinfoManagers(List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	/**
	 * 绩效管理->入库情况查询 档案管理室接收情况
	 * @param counts  counts[0]未接收数、counts[1]总接收数
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return boolean 处理成功返回true，否则返回false
	 */
	boolean receiverCondition(List<Integer> counts, ErrInfo pErrInfo);
	
}
