/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesFonds;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 全宗管理服务的接口定义
 *
 */
public interface IArchivesFondsManageService
{
	
	/**
	 * 查找所有的档案全宗信息
	 * @param archivesFondss 返回查找成功的档案全宗集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesFondss(List<ArchivesFonds> archivesFondss,ErrInfo pErrInfo);
	
	/**
	 * 根据全宗号查找档案全宗信息
	 * @param pID 指定的全宗数字编号
	 * @param archivesFonds 返回查找成功的档案全宗信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesFondsByID(int pID,ArchivesFonds archivesFonds,ErrInfo pErrInfo);
	
	/**
	 * 添加一个新的档案全宗
	 * @param archivesFonds 新添加的档案全宗信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveArchivesFonds(ArchivesFonds archivesFonds,ErrInfo pErrInfo);
	
	/**
	 * 删除指定的档案全宗
	 * @param archivesFonds 要删除的档案全宗，其全宗号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteArchivesFonds(ArchivesFonds archivesFonds,ErrInfo pErrInfo);
	
	/**
	 * 修改指定的档案全宗
	 * @param archivesFonds 修改后的档案全宗信息，其全宗号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateArchivesFonds(ArchivesFonds archivesFonds,ErrInfo pErrInfo);
	
	
	
	
}
