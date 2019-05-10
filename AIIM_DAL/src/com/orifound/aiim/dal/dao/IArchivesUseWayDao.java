package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.ArchivesUseWay;
import com.orifound.aiim.entity.ErrInfo;

/**
 * TableName表的DAO接口定义
 *
 */
public interface IArchivesUseWayDao {

	/**
	 * Dao接口定义：查找所有的ArchivesUseWay
	 * @param archivesUseWays 返回查找成功的ArchivesUseWay集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ArchivesUseWay> pArchivesUseWays, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找ArchivesUseWay
	 * @param pID 指定的唯一标识
	 * @param archivesUseWay 返回查找成功的ArchivesUseWay
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ArchivesUseWay pArchivesUseWay, ErrInfo pErrInfo);

}
