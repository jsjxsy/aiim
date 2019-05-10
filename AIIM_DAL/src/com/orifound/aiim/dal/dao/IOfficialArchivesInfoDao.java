/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumOfficialArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesInfo;
import com.orifound.aiim.entity.OfficialArchivesInfoQueryCondition;
import com.orifound.aiim.entity.OfficialArchivesType;

/**
 * 公文档案信息表的DAO接口定义
 *
 */
public interface IOfficialArchivesInfoDao {

	/**
	 * Dao接口定义：添加公文档案信息
	 * @param officialArchivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param officialArchivesInfo 要添加的公文档案信息
	 * @param officialArchivesInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(OfficialArchivesType officialArchivesType,OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的公文档案信息
	 * @param officialArchivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param officialArchivesInfo 要删除的公文档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(OfficialArchivesType officialArchivesType,OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：删除指定的公文档案信息
	 * @param officialArchivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param officialArchivesInfo 要删除的公文档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteParentAndChild(OfficialArchivesType officialArchivesType,OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo);

	
	/**
	 * Dao接口定义：更新指定的公文档案信息
	 * @param officialArchivesInfo 要更新的公文档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：更新指定的公文档案信息
	 * @param officialArchivesInfo 要更新的公文档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(OfficialArchivesType officialArchiesType,OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：查找所有的公文档案信息
	 * @param officialArchivesInfos 返回查找成功的公文档案信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(OfficialArchivesType offcialArchivesType,List<OfficialArchivesInfo> officialArchivesInfos, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找公文档案信息
	 * @param pID 指定的唯一标识
	 * @param officialArchivesInfo 返回查找成功的公文档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo);
	
	/**
	 * 根据唯一标识查找公文档案登记表 信息
	 * @param pNBXH 指定的唯一标识
	 * @param offcialArchivesType  指定文件所属的公文档案分类，其公文档案分类编号属性必须赋值
	 * @param officialArchivesInfo 返回查找成功的公文档案登记表 信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findByNBXH(int pNBXH, OfficialArchivesType offcialArchivesType, OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo);
	/**
	 * 
	 * @param enumOfficialArchivesInfoTableType
	 * @param officialArchivesType
	 * @param officialArchivesInfoQueryConditions
	 * @param dataPageInfo
	 * @param archivesInfos
	 * @param pErrInfo
	 * @return
	 */
	public boolean find(EnumOfficialArchivesInfoTableType enumOfficialArchivesInfoTableType, OfficialArchivesType officialArchivesType,
			List<OfficialArchivesInfoQueryCondition> officialArchivesInfoQueryConditions, DataPageInfo dataPageInfo, List<OfficialArchivesInfo> archivesInfos, ErrInfo pErrInfo);
	
   /**
    * 
    * @param officialArchivesType
    * @param officialArchivesInfos
    * @param pErrInfo
    * @return
    */
	public boolean batchDelOfficialArchives(OfficialArchivesType officialArchivesType,final List<OfficialArchivesInfo> officialArchivesInfos, ErrInfo pErrInfo);
	
	
}

