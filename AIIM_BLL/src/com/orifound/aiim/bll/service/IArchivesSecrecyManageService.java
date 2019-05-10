/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案密级管理服务的接口定义
 *
 */
public interface IArchivesSecrecyManageService
{

	/**
	 * 添加一个新的档案密级
	 * @param archivesSecrecy 新添加的档案密级信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveArchivesSecrecy(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo);

	/**
	 * 删除指定的档案密级
	 * @param archivesSecrecy 要删除的档案密级，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteArchivesSecrecy(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo);

	/**
	 * 修改指定的档案密级
	 * @param archivesSecrecy 修改后的档案密级信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateArchivesSecrecy(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo);

	/**
	 * 查找所有的档案密级信息
	 * @param archivesSecrecys 返回查找成功的档案密级集合，以密级编号作为关键字
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesSecrecys(LinkedHashMap<Integer,ArchivesSecrecy> archivesSecrecys, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找档案密级信息
	 * @param pID 指定的唯一标识
	 * @param archivesSecrecy 返回查找成功的档案密级信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesSecrecyByID(int pID, ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo);

	/**
	 * 查询公开密级的档案密级数据字典信息
	 * @param archivesSecrecy 要更新的档案密级
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByOpenSecrecy(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo);
}
