/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeCountInfo;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.ArchivesTypeEx;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * 档案归档信息的管理服务接口
 *
 */
public interface IArchivesInfoQueryService {

	/**
	 * 分类查询<br>
	 * 适用于指定一种底层档案分类和若干简单查询条件的简单查询和高级查询
	 * @param archivesTypes 指定的底层档案分类集合，注：当点击最末节点时其中只有一个分类
	 * @param archivesInfoQueryConditions 查询条件
	 * @param dataPageInfo 数据分页显示信息
	 * @param archivesInfos 返回查找成功后的档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	//boolean queryClassified(UserInfo userInfo, List<ArchivesType> archivesTypes,List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,DataPageInfo dataPageInfo,List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * 分类查询<br>
	 * 适用于指定一种底层档案分类和若干简单查询条件的简单查询和高级查询
	 * @param archivesTypes 指定的底层档案分类集合，注：当点击最末节点时其中只有一个分类
	 * @param archivesInfoQueryConditions 查询条件
	 * @param dataPageInfo 数据分页显示信息
	 * @param archivesInfos 返回查找成功后的档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean queryClassified(UserInfo userInfo, List<ArchivesType> archivesTypes,String archivesInfoQueryConditions_SQL,DataPageInfo dataPageInfo,List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);

	
	
	
	
	/**
	 * 查找多个档案分类共同拥有的可被查询的数据项<br>
	 * 适用于综合查询中的高级查询功能
	 * @param archivesTypes 多个底层档案分类
	 * @param archivesTypeDataItems 返回查找成功的数据项集合，以字段名作为集合的键
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findQueryDataItems(List<ArchivesType> archivesTypes,LinkedHashMap<String, ArchivesTypeDataItem> archivesTypeDataItems,ErrInfo pErrInfo);
	
	/**
	 * 根据内部序号查找指定分类档案信息<br>
	 * 注意：该方法是对档案归档信息表数据进行查询，跟工作表无关
	 * @param archivesType 指定档案分类
	 * @param NBXH 指定要查找的档案内部序号
	 * @param archivesInfo 查询成功后返回的档案
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesInfoByNBXH(ArchivesType archivesType,int NBXH,ArchivesInfo archivesInfo,ErrInfo pErrInfo);
	
	/**
	 * 根据档案条码查找档案信息<br>
	 * 注意：该方法是对档案归档信息表数据进行查询，跟工作表无关
	 * @param archivesBarcode 指定档案条形码，可以是案卷条码或卷内文件条码
	 * @param archivesInfo 返回查找成功的档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesInfoByBarcode(String archivesBarcode,ArchivesInfo archivesInfo,ErrInfo pErrInfo);
	
	/**
	 * 跨门类查询 <br>
	 * 根据查询条件查询给定档案分类的数量信息
	 * @param archivesTypes 指定要跨门类查询的多个底层档案分类
	 * @param archivesInfoQueryConditions 查询条件
	 * @param archivesTypeCountInfos 返回查询成功后的档案分类的数量统计信息
	 * @param querySQL  返回用于查询的SQL语句
	 * @param pErrInfo
	 * @return
	 */
	boolean queryCrossClassified(UserInfo userInfo,List<ArchivesType> archivesTypes,List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,List<ArchivesTypeCountInfo> archivesTypeCountInfos,StringBuilder querySQL,ErrInfo pErrInfo);
	
	
	/**
	 * 跨门类查询中的关键字检索<br>确
	 * 根据关键字查询给定档案分类中的多个底层档案分类的数量信息
	 * @param userInfo 用户信息
	 * @param archivesTypes 档案分类信息集合
	 * @param keyWord 关键字
	 * @param archivesTypeCountInfos 返回查询成功后的档案分类的数量统计信息
	 * @param pErrInfo
	 * @return
	 */
	public boolean queryCrossClassifiedByKeyWord(UserInfo userInfo, List<ArchivesType> archivesTypes,String keyWord ,List<ArchivesTypeCountInfo> archivesTypeCountInfos,ErrInfo pErrInfo);
	
	/**
	 * 根据内部序号查找指定已归档表中案卷的卷内文件信息
	 * @param pNBXH 指定案卷的内部序号
	 * @param archivesType 指定案卷所属的档案分类，其档案分类编号属性必须赋值
	 * @param archivesInfos 返回查找成功后的卷内文件集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findChildArchivesInfosByNBXH(int pNBXH,ArchivesType archivesType, List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
}
