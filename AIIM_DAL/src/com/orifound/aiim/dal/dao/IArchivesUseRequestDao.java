package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ArchivesUseRequest;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案利用申请单信息表的DAO接口定义
 *
 */
public interface IArchivesUseRequestDao {

	/**
	 * Dao接口定义：添加档案利用申请单信息
	 * @param archivesUseRequest 要添加的档案利用申请单信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean add(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的档案利用申请单信息
	 * @param archivesUseRequest 要删除的档案利用申请单信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo);

	

	/**
	 * Dao接口定义：根据查询条件查找在线档案利用申请单信息
	 * @param archivesUseRequests 返回查找成功的档案利用申请单信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByQueryCondition(String querySQL,DataPageInfo dataPageInfo , List<ArchivesUseRequest> archivesUseRequests, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找档案利用申请单信息
	 * @param archivesUseRequest 返回查找成功的档案利用申请单信息 ,ID必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo);

}
