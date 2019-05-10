/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 目录打印dao接口定义
 *
 */
public interface ICatalogPrintDao {
	
	/**
	 * 案卷目录打印 查询档案信息
	 * @param depaermentName	打印案卷目录的部门名称DXBM(档案形成部门)、YWZD(业务指导室)、DAGL(档案管理室)
	 * @param paperTransferBatNo	移交批次号
	 * @param archivesType 			档案类型
	 * @param archivesInfos 返回档案信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesinfoForTransferCatalog(String depaermentName, String paperTransferBatNo,ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo);
	
	/**
	 * 盒内目录 打印查询
	 * @param boxBarcode 	盒条码
	 * @param storeStatus   馆藏状态
	 * @param archivesType 	档案类型
	 * @param archivesInfos 返回档案信息集合
	 * @param pErrInfo 		返回处理失败的错误信息
	 * @return 				处理成功返回true，否则返回false
	 */
	boolean findArchivesinfoForBoxCatalog(String boxBarcode, int storeStatus, ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo);
	
	/**
 	 * 封皮打印查询	根据内部序号查找档案信息
 	 * @param pNBXH 指定的内部序号
 	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
 	 * @param archivesInfo 返回查找成功的档案信息
 	 * @param pErrInfo 返回处理失败的错误信息
 	 * @return 处理成功返回true，否则返回false
 	 */
 	boolean findArchivesinfoForEnelope(int pNBXH, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo);
}
