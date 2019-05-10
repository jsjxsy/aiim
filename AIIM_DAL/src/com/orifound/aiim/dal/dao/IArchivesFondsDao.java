/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.ArchivesFonds;
import com.orifound.aiim.entity.ErrInfo;

/**
 * DD_ArchivesFonds表的DAO接口定义
 *
 */
public interface IArchivesFondsDao
{
	/**
	 * Dao接口定义：添加档案全宗
	 * @param pArchivesFonds 要添加的档案全宗
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ArchivesFonds pArchivesFonds, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的档案全宗
	 * @param pArchivesFonds 要删除的档案全宗
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ArchivesFonds pArchivesFonds, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的档案全宗
	 * @param pArchivesFonds 要更新的档案全宗
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ArchivesFonds pArchivesFonds, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的档案全宗
	 * @param pArchivesFondss 返回查找成功的档案全宗集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ArchivesFonds> pArchivesFondss, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据全宗号查找档案全宗
	 * @param pID 指定的全宗数字编号
	 * @param pArchivesFonds 返回查找成功的档案全宗
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID,ArchivesFonds pArchivesFonds, ErrInfo pErrInfo);
	
}

