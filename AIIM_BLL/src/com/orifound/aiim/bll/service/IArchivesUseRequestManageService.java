package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ArchivesUseRequest;
import com.orifound.aiim.entity.ArchivesUseRequestQueryCondition;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
/**
 * 档案在线利用申请单信息业务逻辑接口
 * @author Administrator
 *
 */
public interface IArchivesUseRequestManageService {
	/**
	 * 添加档案利用申请单信息
	 * @param archivesUseRequest 要添加的档案利用申请单信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean addArchivesUseRequest(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo);

	/**
	 * 删除指定的在线档案利用申请单信息
	 * @param archivesUseRequest 要删除的档案利用申请单信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteArchivesUseRequest(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo);


	/**
	 * 根据条件查询在线档案利用申请单信息
	 * @param archivesUseRequests 返回查找成功的档案利用申请单信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesUseRequestsByCondition(ArchivesUseRequestQueryCondition archivesUseRequestQueryCondition, DataPageInfo dataPageInfo, List<ArchivesUseRequest> archivesUseRequests, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找在线档案利用申请单信息
	 * @param archivesUseRequest 返回查找成功的档案利用申请单信息 ,ID必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesUseRequestByID(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo);
}
