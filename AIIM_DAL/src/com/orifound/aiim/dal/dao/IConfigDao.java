/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.Config;
import com.orifound.aiim.entity.ErrInfo;

/**
 * Config表的Dao接口定义
 *
 */
public interface IConfigDao {

	/**
	 * Dao接口定义：添加一个配置项
	 * @param config 要添加的配置项
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(Config config,ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：更新指定配置项
	 * @param config 要更新的配置项
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(Config config,ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：删除指定配置项
	 * @param config 要删除的配置项
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(Config config,ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：根据配置类型查找所有配置项
	 * @param pConfigType 配置类型
	 * @param pConfigs 查找成功后返回的配置项集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByConfigType(String pConfigType,List<Config> pConfigs,ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：根据配置ID查找配置信息
	 * @param pID 配置信息的唯一编号
	 * @param config 返回查找成功的配置项信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID,Config config,ErrInfo pErrInfo);
}
