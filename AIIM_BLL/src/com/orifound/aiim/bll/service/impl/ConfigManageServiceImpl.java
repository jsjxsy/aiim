/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IConfigManageService;
import com.orifound.aiim.dal.dao.IConfigDao;
import com.orifound.aiim.entity.Config;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 系统配置管理服务实现类
 *
 */
public class ConfigManageServiceImpl implements IConfigManageService {
	
	/**
	 * 构造函数
	 */
	public ConfigManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public ConfigManageServiceImpl(IConfigDao configDao)
	{
		this.configDao = configDao;
	}
	
	/**
	 * Config表的数据访问对象
	 */
	private IConfigDao configDao=null;
	
	/**
	 * 获取属性值：Config表的数据访问对象
	 * @return Config表的数据访问对象
	 */
	public IConfigDao getConfigDao() {
		return configDao;
	}
	/**
	 * 设置属性值：Config表的数据访问对象
	 * @param configDao Config表的数据访问对象
	 */
	public void setConfigDao(IConfigDao configDao) {
		this.configDao = configDao;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IConfigService#AddConfig(com.orifound.aiim.entity.Config, java.lang.StringBuilder)
	 */
	@Override
	public boolean saveConfig(Config pConfig, ErrInfo pErrInfo) {

		boolean pFlag=true;
		
		if (configDao.save(pConfig, pErrInfo)==false){
			pFlag=false;
			pErrInfo.getContent().insert(0, "保存配置项失败: ");
		}
			
		
		return pFlag;
	}
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IConfigService#deleteConfig(com.orifound.aiim.entity.Config, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteConfig(Config pConfig, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IConfigService#findConfigByConfigType(java.lang.String, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findConfigByConfigType(String pConfigType,List<Config> pConfigs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (configDao.findByConfigType(pConfigType, pConfigs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询配置项失败");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IConfigService#updateConfig(com.orifound.aiim.entity.Config, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateConfig(Config pConfig, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return true;
	}

}
