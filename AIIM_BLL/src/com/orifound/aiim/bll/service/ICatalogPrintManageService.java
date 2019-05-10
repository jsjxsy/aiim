package com.orifound.aiim.bll.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.CatalogDataItem;
import com.orifound.aiim.entity.EnumCatalogType;
import com.orifound.aiim.entity.EnumCheckResult;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesInfo;
import com.orifound.aiim.entity.OfficialArchivesType;
import com.orifound.aiim.entity.PaperTransferBatchesDetail;

/**
 * 目录打印管理服务类
 *
 */
public interface ICatalogPrintManageService {
	/**
	 * 根据目录打印模板类型查询打印显示的数据项集合
	 * @param catalogType 目录打印模板类型
	 * @param catalogDataItems 返回显示数据项集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findDataItemByCatalogType(ArchivesType archivesType, EnumCatalogType catalogType, LinkedHashMap<String, CatalogDataItem> catalogDataItems, ErrInfo pErrInfo);
	
	/**
	 * 案卷目录	打印查询档案信息
	 * @param depaermentName	打印案卷目录的部门名称DXBM(档案形成部门)、YWZD(业务指导室)、DAGL(档案管理室)
	 * @param paperTransferBatNo	移交批次号
	 * @param archivesType 	档案类型
	 * @param archivesInfos 返回档案信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesinfoForTransferCatalog(String depaermentName, String paperTransferBatNo,ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo);
	
	/**
	 * 移交清单 	打印查询
	 * @param paperTransferBatNo 指定的移交批次
	 * @param archivesType 指定的档案分类
	 * @param paperTransferBatchesDetails 返回查找成功后的档案移交明细清单
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesinfoForTransferList(String paperTransferBatNo,ArchivesType archivesType,List<PaperTransferBatchesDetail> paperTransferBatchesDetails,List<EnumCheckResult> enumCheckResults, ErrInfo pErrInfo);
	
	/**
	 * 盒内目录 	打印查询
	 * @param boxBarcode 	盒条码
	 * @param archivesType 	档案类型
	 * @param archivesInfos 返回档案信息集合
	 * @param pErrInfo 		返回处理失败的错误信息
	 * @return 				处理成功返回true，否则返回false
	 */
	boolean findArchivesinfoForBoxCatalog(String boxBarcode, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo);
	
	/**
	 * 卷内目录 打印查询
	 * @param archivesType 	档案类型
	 * @param archivesInfo	档案信息(包含是否归档信息：是否有档号)
	 * @param archivesInfos 返回档案信息集合
	 * @param pErrInfo 		返回处理失败的错误信息
	 * @return 				处理成功返回true，否则返回false
	 */
	boolean findArchivesinfoForFileCatalog(ArchivesType archivesType, ArchivesInfo archivesInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo);
	
	//调卷单
	boolean findArchivesinfoForFileRetrieval();
	
	/**
 	 * 封皮打印查询	根据内部序号查找档案信息
 	 * @param pNBXH 指定的内部序号
 	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
 	 * @param archivesInfo 返回查找成功的档案信息
 	 * @param pErrInfo 返回处理失败的错误信息
 	 * @return 处理成功返回true，否则返回false
 	 */
 	boolean findArchivesinfoForEnelope(int pNBXH, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo);
	
	/**
	 * 公文目录 打印查询
	 * @param officialArchivesTypeID 	公文类型id
	 * @param archivesInfos 			返回档案信息集合
	 * @param pErrInfo 					返回处理失败的错误信息
	 * @return 							处理成功返回true，否则返回false
	 */
	boolean findArchivesinfoForofficialArchivesInfoCatalog(OfficialArchivesType officialArchivesType, List<OfficialArchivesInfo> officialArchivesInfos, ErrInfo pErrInfo);
}
