/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案密级字典表的DAO接口定义
 *
 */
public interface IArchivesSecrecyDao
{

	/**
	 * Dao接口定义：添加档案密级
	 * @param archivesSecrecy 要添加的档案密级
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的档案密级
	 * @param archivesSecrecy 要删除的档案密级
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的档案密级
	 * @param archivesSecrecy 要更新的档案密级
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的档案密级
	 * @param archivesSecrecys 返回查找成功的档案密级集合，以密级编号作为关键字
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(LinkedHashMap<Integer,ArchivesSecrecy> archivesSecrecys, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找档案密级
	 * @param pID 指定的唯一标识
	 * @param archivesSecrecy 返回查找成功的档案密级
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查询公开密级的档案密级数据字典信息
	 * @param archivesSecrecy 要更新的档案密级
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByOpenSecrecy(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo);
}
