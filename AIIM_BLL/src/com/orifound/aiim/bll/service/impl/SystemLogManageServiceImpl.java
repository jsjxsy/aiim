package com.orifound.aiim.bll.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.ISystemLogManageService;
import com.orifound.aiim.dal.dao.ISystemLogInfoDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.LogInfo;

public class SystemLogManageServiceImpl implements ISystemLogManageService {

//	@Autowired
//	private ISystemFeatureDao systemFeatureDao;
	
	@Autowired
	private ISystemLogInfoDao systemLogInfoDao;
	
	@Override
	public boolean addLog(LogInfo logInfo,ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			
			//дϵͳ��־
			if (pFlag) {
				pErrPos = 3;
				if (systemLogInfoDao.add(logInfo,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"�����־ʧ��: ");
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
	
}
