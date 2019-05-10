/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.ArchivesInfoWorkProcedure;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案工作过程记录表的DAO接口定义
 *
 */
public interface IArchivesInfoWorkProcedureDao
{

	/**
	 * Dao接口定义：添加档案工作过程信息
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param pEntity 要添加的档案工作过程信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ArchivesType archivesType,ArchivesInfoWorkProcedure archivesInfoWorkProcedure, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找指定内部序号档案的所有工作过程信息
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param pNBXH 指定的内部序号
	 * @param archivesInfoWorkProcedures 返回查找成功的档案工作过程信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByNBXH(ArchivesType archivesType,int pNBXH, List<ArchivesInfoWorkProcedure> archivesInfoWorkProcedures, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的文件级档案的归档过程记录信息
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param archivesWorkProcedure 要删除的档案归档过程记录信息，其内部序号属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteForSingleArchives(ArchivesType archivesType,ArchivesInfoWorkProcedure archivesInfoWorkProcedure, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：删除指定的案卷级档案归档过程记录信息<br>
	 * 其卷内文件对应的档案归档过程记录信息也一并删除
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param archivesWorkProcedure 要删除的档案归档过程记录信息，其内部序号属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteForParentArchives(ArchivesType archivesType,ArchivesInfoWorkProcedure archivesInfoWorkProcedure, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：监测档案形成部门、业务指导室的人员特定时间段内著录档案的数量<br>
	 * @param recordCondition map集合保存:人员真实姓名-著录数量 键值对
	 * @param userIds 人员id集合
	 * @param beginTime 检测开始时间	
	 * @param endTime	检测结束时间
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	 boolean recordCondition(Map<String, Integer> recordCondition, List<Integer> userIds, String beginTime, String endTime, ErrInfo pErrInfo);
	 
	 /**
	 * Dao接口定义：监测业务指导室人员特定时间段内审核、待审核档案的数量<br>
	 * @param recordAudit 封装成Map<String, Integer[]>保存人员审核档案数量,String保存人员真实姓名,Integer[0]保存已审核、Integer[1]待审核
	 * @param userIds 人员id集合
	 * @param beginTime 检测开始时间
	 * @param endTime	检测结束时间
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	 boolean recordAuditCondition(Map<String, Integer[]> recordAudit, List<Integer> userIds, String beginTime, String endTime, ErrInfo pErrInfo);
	 
	 /**
	 * 绩效管理->入库工作量查询
	 * @param inStorageCondition 封装成Map<String, Integer>保存档案管理室入库工作情况
	 * @param beginTime 监测开始时间
	 * @param endTime 监测结束时间
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return boolean 处理成功返回true，否则返回false
	 */
	boolean findInStorageCondition(Map<String, Integer> inStorageCondition, List<Integer> userIds, String beginTime, String endTime, ErrInfo pErrInfo);
}
