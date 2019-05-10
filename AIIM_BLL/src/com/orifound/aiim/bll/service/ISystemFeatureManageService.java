/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.Map;

import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.ErrInfo;

/**
 * SystemFeature管理服务的接口定义
 *
 */
public interface ISystemFeatureManageService {

	/**
	 * 添加一个新的SystemFeature
	 * @param pSystemFeature 新添加的SystemFeature信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveSystemFeature(SystemFeature pSystemFeature, ErrInfo pErrInfo);

	/**
	 * 删除指定的SystemFeature
	 * @param pSystemFeature 要删除的SystemFeature，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteSystemFeature(SystemFeature pSystemFeature, ErrInfo pErrInfo);

	/**
	 * 修改指定的SystemFeature
	 * @param pSystemFeature 修改后的SystemFeature信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateSystemFeature(SystemFeature pSystemFeature, ErrInfo pErrInfo);

	/**
	 * 查找所有的SystemFeature信息
	 * @param pSystemFeatures 返回查找成功的SystemFeature集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findSystemFeatures(Map<String,SystemFeature> pSystemFeatures, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找SystemFeature信息
	 * @param pID 指定的唯一标识
	 * @param pSystemFeature 返回查找成功的SystemFeature信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findSystemFeatureByID(int pID, SystemFeature pSystemFeature, ErrInfo pErrInfo);

}

