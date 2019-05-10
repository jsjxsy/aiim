package com.orifound.aiim.dal.dao;

import java.util.List;


import com.orifound.aiim.entity.ArchivesUseOutInfo;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 实物档案利用出去明细表的DAO接口定义
 *
 */
public interface IArchivesUseOutInfoDao {

	/**
	 * Dao接口定义：添加实物档案利用出去明细
	 * @param archivesUseOutInfo 要添加的实物档案利用出去明细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean add(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);
	
	
	/**
	 * Dao接口定义：归还实物档案
	 * @param archivesUseOutInfo 要添加的实物档案利用出去明细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean returnArchivesByBarcode(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的实物档案利用出去明细
	 * @param archivesUseOutInfo 要删除的实物档案利用出去明细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的实物档案利用出去明细
	 * @param archivesUseOutInfo 要更新的实物档案利用出去明细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateShouldReturnDate(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：根据条件查找实物档案利用出去明细<br>包括登记信息和利用人信息
	 * @param archivesUseOutInfos 返回查找成功的实物档案利用出去明细集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByQueryCondition(String querySQL,DataPageInfo dataPageInfo, List<ArchivesUseOutInfo> archivesUseOutInfos, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：根据利用登记编号查询所有实物档案利用出去明细
	 * @param archivesUseOutInfos 返回查找成功的实物档案利用出去明细集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByRegisterID(int registerID,List<ArchivesUseOutInfo> archivesUseOutInfos, ErrInfo pErrInfo);
	

	/**
	 * Dao接口定义：根据唯一标识查找实物档案利用出去明细
	 * @param pID 指定的唯一标识
	 * @param archivesUseOutInfo 返回查找成功的实物档案利用出去明细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID( ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：根据档案条形码查找实物档案利用出去明细
	 * @param pID 指定的唯一标识
	 * @param archivesUseOutInfo 返回查找成功的实物档案利用出去明细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByArchivesBarcode( ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);

}
