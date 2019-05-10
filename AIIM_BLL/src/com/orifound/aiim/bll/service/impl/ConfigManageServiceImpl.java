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
 * ϵͳ���ù������ʵ����
 *
 */
public class ConfigManageServiceImpl implements IConfigManageService {
	
	/**
	 * ���캯��
	 */
	public ConfigManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public ConfigManageServiceImpl(IConfigDao configDao)
	{
		this.configDao = configDao;
	}
	
	/**
	 * Config������ݷ��ʶ���
	 */
	private IConfigDao configDao=null;
	
	/**
	 * ��ȡ����ֵ��Config������ݷ��ʶ���
	 * @return Config������ݷ��ʶ���
	 */
	public IConfigDao getConfigDao() {
		return configDao;
	}
	/**
	 * ��������ֵ��Config������ݷ��ʶ���
	 * @param configDao Config������ݷ��ʶ���
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
			pErrInfo.getContent().insert(0, "����������ʧ��: ");
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
			//��ʼ���� 1...
			pErrPos = 1;
			

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (configDao.findByConfigType(pConfigType, pConfigs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ������ʧ��");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
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
