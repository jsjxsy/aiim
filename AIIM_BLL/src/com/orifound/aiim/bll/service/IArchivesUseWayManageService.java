package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ArchivesUseWay;
import com.orifound.aiim.entity.ErrInfo;
/**
 * 档案利用方式字典表管理服务接口
 * @author Administrator
 *
 */
public interface IArchivesUseWayManageService {
	/**
	 * 查找所有的ArchivesUseWay
	 * @param archivesUseWays 返回查找成功的ArchivesUseWay集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllArchivesUseWay(List<ArchivesUseWay> archivesUseWays, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找ArchivesUseWay
	 * @param pID 指定的唯一标识
	 * @param archivesUseWay 返回查找成功的ArchivesUseWay
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesUseWayByID(ArchivesUseWay archivesUseWay, ErrInfo pErrInfo);
}
