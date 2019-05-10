package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.ArchivesUsePurpose;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案利用目的表的DAO接口定义
 *
 */
public interface IArchivesUsePurposeDao {
	
	/**
	 * Dao接口定义：查找所有的ArchivesUsePurose
	 * @param archivesUsePurposes 返回查找成功的ArchivesUsePurose集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ArchivesUsePurpose> pArchivesUsePurposes, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找ArchivesUsePurose
	 * @param pID 指定的唯一标识
	 * @param archivesUsePurpose 返回查找成功的ArchivesUsePurose
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(ArchivesUsePurpose pArchivesUsePurpose, ErrInfo pErrInfo);

}
