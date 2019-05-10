/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.ErrInfo;

/**
 * SystemFeature表的DAO接口定义
 *
 */
public interface ISystemFeatureDao {

	/**
	 * Dao接口定义：添加系统功能配置表
	 * @param pSystemFeature 要添加的系统功能配置表
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(SystemFeature pSystemFeature, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的系统功能配置表
	 * @param pSystemFeature 要删除的系统功能配置表
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(SystemFeature pSystemFeature, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的系统功能配置表
	 * @param pSystemFeature 要更新的系统功能配置表
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(SystemFeature pSystemFeature, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的系统功能配置表
	 * @param pSystemFeatures 返回查找成功的系统功能配置表集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<SystemFeature> pSystemFeatures, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找系统功能配置表
	 * @param pID 指定的唯一标识
	 * @param pSystemFeature 返回查找成功的系统功能配置表
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, SystemFeature pSystemFeature, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：查找所有顶层系统功能
	 * @param systemFeatures  返回顶层系统功能
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllSystemFeature(Map<String,SystemFeature>systemFeatures, ErrInfo pErrInfo);

	/**
	 * 按名称查找系统功能对象
	 * @param systemFeature
	 * @param pErrInfo
	 * @return
	 */
	boolean findByUCLKey(SystemFeature systemFeature, ErrInfo pErrInfo);

}
