/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumArchivesInfoType;
import com.orifound.aiim.entity.EnumOfficialArchivesInfoTableType;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesInfo;
import com.orifound.aiim.entity.OfficialArchivesInfoQueryCondition;
import com.orifound.aiim.entity.OfficialArchivesType;

/**
 * 公文档案登记表 管理服务的接口定义
 *
 */
public interface IOfficialArchivesInfoManageService {

	/**
	 * 添加一个新的公文档案登记表 
	 * @param userID 著录人员的编号
	 * @param archivesType 所属档案分类，其档案分类编号属性必须赋值
	 * @param enumArchivesInfoType 指定档案信息类型（文件、案卷或卷内文件）
	 * @param officialArchivesInfo 新添加的公文档案登记表 信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveOfficialArchivesInfo(int userID, OfficialArchivesType officialArchivesType,EnumOfficialArchivesInfoTableType enumOfficialArchivesInfoType,OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo);

	/**
	 * 删除指定的公文档案登记表 
	 * @param officialArchivesType 指定档案所属的档案分类，其档案分类编号属性必须赋值
	 * @param enumOfficialArchivesInfoType 指定档案的归档类型（文件级、案卷级或卷内文件）
	 * @param officialArchivesInfo 要删除的公文档案登记表 ，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteOfficialArchivesInfo(OfficialArchivesType officialArchivesType,EnumOfficialArchivesInfoTableType enumOfficialArchivesInfoTableType,OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo);
	
	/**
	 * 删除指定的公文档案登记表 
	 * @param officialArchivesType 指定档案所属的档案分类，其档案分类编号属性必须赋值
	 * @param enumOfficialArchivesInfoType 指定档案的归档类型（文件级、案卷级或卷内文件）
	 * @param officialArchivesInfo 要删除的公文档案登记表 ，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteOfficialArchivesInfos(OfficialArchivesType officialArchivesType,EnumOfficialArchivesInfoTableType enumOfficialArchivesInfoTableType,List<OfficialArchivesInfo> officialArchivesInfos, ErrInfo pErrInfo);

	
	/**
	 * 修改指定的公文档案登记表 
	 * @param officialArchivesInfo 修改后的公文档案登记表 信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateOfficialArchivesInfo(OfficialArchivesType officialArchivesType,EnumOfficialArchivesInfoTableType enumOfficalArchivesInfoTableType,OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo);

	/**
	 * 查找所有的公文档案登记表 信息
	 * @param officialArchivesInfos 返回查找成功的公文档案登记表 集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findOfficialArchivesInfos(OfficialArchivesType officialArchivesType,EnumOfficialArchivesInfoTableType enumOfficialArchivesInfoTableType,List<OfficialArchivesInfoQueryCondition> officialArchivesInfoQueryConditions, DataPageInfo dataPageInfo,List<OfficialArchivesInfo> officialArchivesInfos,
			ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找公文档案登记表 信息
	 * @param pID 指定的唯一标识
	 * @param officialArchivesInfo 返回查找成功的公文档案登记表 信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findOfficialArchivesInfoByID(int pID, OfficialArchivesInfo officialArchivesInfo,
			ErrInfo pErrInfo);
	/**
	 * 根据唯一标识查找公文档案登记表 信息
	 * @param pNBXH 指定的唯一标识
	 * @param offcialArchivesType  指定文件所属的公文档案分类，其公文档案分类编号属性必须赋值
	 * @param officialArchivesInfo 返回查找成功的公文档案登记表 信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findOfficialArchivesInfoByNBXH(int pNBXH, OfficialArchivesType offcialArchivesType,OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo);
	
	/**
	 * 
	 * @param offcialArchivesType
	 * @param officialArchivesInfo
	 * @param pErrInfo
	 * @return
	 */
	boolean findAll(OfficialArchivesType officialArchivesType,List<OfficialArchivesInfo> officialArchivesInfos,ErrInfo pErrInfo);
	

}
