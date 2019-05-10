package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.EMS;
import com.orifound.aiim.entity.ErrInfo;

/**
 * EMS信息DAO
 * @author Administrator
 *
 */
public interface IEMSTemplateDao {

	/**
	 * 更具转出单查询快递单信息
	 * @param ids 转出单编号数组
	 * @param emsInfos 返回的快递单信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findEMSinfos(int[] ids, List<EMS> emsInfos, ErrInfo pErrInfo);

}
