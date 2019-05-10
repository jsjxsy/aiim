package com.orifound.aiim.bll.service;

import java.util.List;
import com.orifound.aiim.entity.ArchivesUsePurpose;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案利用目的业务逻辑接口
 * @author Administrator
 *
 */
public interface IArchivesUsePurposeManageService {
	
	/**
	 * 查找所有的ArchivesUsePurose
	 * @param archivesUsePurposes 返回查找成功的ArchivesUsePurose集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllArchivesUsePurpose(List<ArchivesUsePurpose> archivesUsePurposes, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找ArchivesUsePurose
	 * @param pID 指定的唯一标识
	 * @param archivesUsePurpose 返回查找成功的ArchivesUsePurose
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID( ArchivesUsePurpose archivesUsePurpose, ErrInfo pErrInfo);

}
