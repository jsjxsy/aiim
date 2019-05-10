/**
 * 
 */
package com.orifound.aiim.dal.dao;

import com.orifound.aiim.entity.AttachedFileUseRequestPassInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;

/**
 * 原文利用申请通过信息表DAO接口
 *
 */
public interface IAttachedFileUseRequestPassInfoDao
{
		/**
		 * 查找指定的用户档案原文申请通过信息存在的有效数量
		 * @param pUserID 申请用户的编号
		 * @param pArchivesTypeID 档案分类编号
		 * @param pNBXH 档案的内部序号
		 * @param pCount 返回查找成功的总数量
		 * @param pErrInfo 返回处理失败的错误信息
		 * @return 处理成功返回true，否则返回false
		 */
		boolean findCountByRequestPassInfo(int pUserID,int pArchivesTypeID,int pNBXH,IntegerEx pCount,ErrInfo pErrInfo);

	
		/**
		 * Dao接口定义：添加原文申请详细信息
		 * @param attachedFileUseRequestPassInfo 要添加的原文申请详细信息
		 * @param pErrInfo 返回处理失败的错误信息
		 * @return 处理成功返回true，否则返回false
		 */
		boolean add(AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo, ErrInfo pErrInfo);

		/**
		 * Dao接口定义：删除指定的原文申请详细信息
		 * @param attachedFileUseRequestPassInfo 要删除的原文申请详细信息
		 * @param pErrInfo 返回处理失败的错误信息
		 * @return 处理成功返回true，否则返回false
		 */
		boolean delete(AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo, ErrInfo pErrInfo);

		
		
		/**
		 * Dao接口定义：根据唯一标识查找原文申请详细信息
		 * @param attachedFileUseRequestPassInfo 返回查找成功的原文申请详细信息
		 * @param pErrInfo 返回处理失败的错误信息
		 * @return 处理成功返回true，否则返回false
		 */
		boolean findByID(AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo, ErrInfo pErrInfo);

	

}
