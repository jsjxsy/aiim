/**
 * 
 */
package com.orifound.aiim.bll.service;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemInitializer;

/**
 * 系统初始化服务的接口定义
 *
 */
public interface ISystemInitializeService {

	/**
	 * 系统初始化，读取系统中的各种配置及字典信息。
	 * @param systemInitializer 初始化成功后返回的系统初始化器
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean initialize(SystemInitializer systemInitializer,ErrInfo pErrInfo);
}
