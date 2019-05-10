/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.Config;
import com.orifound.aiim.entity.ErrInfo;;

/**
 * 系统配置管理服务的接口定义
 *
 */
public interface IConfigManageService {

	/**
	 * 添加一个配置项
	 * @param pConfig 要添加到配置项
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveConfig(Config pConfig,ErrInfo pErrInfo);
	
	/**
	 * 删除指定配置项
	 * @param pConfig 要删除的配置项
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteConfig(Config pConfig,ErrInfo pErrInfo);
	
	/**
	 * 更新指定配置项
	 * @param pConfig 要更新的配置项
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateConfig(Config pConfig,ErrInfo pErrInfo);
	
	/**
	 * 获取指定配置项
	 * @param pConfigType 要查找的配置项类型
	 * @param pConfigs 查找成功返回的配置项集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findConfigByConfigType(String pConfigType,List<Config> pConfigs,ErrInfo pErrInfo);
}
