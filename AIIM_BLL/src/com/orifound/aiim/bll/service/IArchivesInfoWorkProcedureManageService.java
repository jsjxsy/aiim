/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesInfoWorkProcedure;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.EnumArchivesInfoType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案归档过程记录管理服务的接口定义
 *
 */
public interface IArchivesInfoWorkProcedureManageService
{

	/**
	 * 添加一个新的档案归档过程记录
	 * @param archivesWorkProcedure 新添加的档案归档过程记录信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveArchivesInfoWorkProcedure(ArchivesInfoWorkProcedure archivesInfoWorkProcedure, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找档案归档过程记录信息
	 * @param archivesType 所属档案分类，其档案分类编号属性必须赋值
	 * @param pNBXH 指定的内部序号
	 * @param archivesInfoWorkProcedures 返回查找成功的档案归档过程记录信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesInfoWorkProcedureByNBXH(ArchivesType archivesType,int pNBXH, List<ArchivesInfoWorkProcedure> archivesInfoWorkProcedures, ErrInfo pErrInfo);
	
	
	/**
	 * 删除指定的档案归档过程记录信息<br>
	 * 如果是案卷档案，则其卷内文件对应的档案归档过程记录信息也一并删除
	 * @param enumArchivesInfoType 档案信息分类，是案卷还是文件
	 * @param archivesWorkProcedure 要删除的档案归档过程记录信息，其档案分类编号、内部序号属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteArchivesInfoWorkProcedure(EnumArchivesInfoType enumArchivesInfoType, ArchivesInfoWorkProcedure archivesInfoWorkProcedure, ErrInfo pErrInfo);
}
