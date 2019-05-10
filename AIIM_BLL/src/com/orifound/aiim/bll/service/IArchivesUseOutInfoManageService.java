package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ArchivesUseInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesUseOutInfo;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 实物档案利用出去明细管理服务的接口定义
 *
 */
public interface IArchivesUseOutInfoManageService {

	/**
	 * 添加一个新的实物档案利用出去明细
	 * @param EntityName 新添加的Entity信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean addArchivesUseOutInfo(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);

	/**
	 * 删除指定的实物档案利用出去明细
	 * @param EntityName 要删除的实物档案利用出去明细，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteArchivesUseOutInfo(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);

	/**
	 * 更新档案应归还时间
	 * @param EntityName 修改后的实物档案利用出去明细信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateShouldReturnDate(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);
	/**
	 * 
	 * @param EntityNames 返回查找成功的Entity集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */

	/**
	 * 根据条件查询实物档案利用出去明细信息
	 * @param archivesUseInfoQueryCondition 查询条件对象
	 * @param dataPageInfo 翻页信息
	 * @param archivesUseOutInfos 返回查找成功的Entity集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return
	 */
	boolean findArchivesUseOutInfosByQueryCondition(ArchivesUseInfoQueryCondition archivesUseInfoQueryCondition, DataPageInfo dataPageInfo, List<ArchivesUseOutInfo> archivesUseOutInfos, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找实物档案利用出去明细信息
	 * @param archivesUseOutInfo 返回查找成功的档案利用出去明细信息,其档案条形码（ArchivesBarcode）必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesUseOutInfoByID( ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);
	
	
	/**
	 * Dao接口定义：根据档案条形码查找实物档案利用出去明细
	 * @param archivesUseOutInfo 返回查找成功的实物档案利用出去明细,其档案条形码（ArchivesBarcode）必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesUseOutInfoByArchivesBarcode( ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);
	
	/**
	 * 根据利用登记编号查找所有实物档案利用出去明细信息
	 * @param EntityNames 返回查找成功的Entity集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesUseOutInfosByRegisterID(int registerID,List<ArchivesUseOutInfo> archivesUseOutInfos, ErrInfo pErrInfo);
	
	
	/**
	 * Dao接口定义：根据档案条形码归还档案
	 * @param archivesUseOutInfo 返回查找成功的实物档案利用出去明细,其档案条形码（ArchivesBarcode）必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean returnArchivesByBarcode( ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);

	
//	boolean findArchivesUseOutInfos(
//			ArchivesUseInfoQueryCondition archivesUseInfoQueryCondition,
//			DataPageInfo dataPageInfo, List<ArchivesUseOutInfo> archivesUseInfos,
//			ErrInfo pErrInfo);
	
	
}
