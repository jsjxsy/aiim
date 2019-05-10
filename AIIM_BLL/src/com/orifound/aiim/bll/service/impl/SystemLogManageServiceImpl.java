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
			//开始处理 1...
			pErrPos = 1;
			
			//写系统日志
			if (pFlag) {
				pErrPos = 3;
				if (systemLogInfoDao.add(logInfo,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"添加日志失败: ");
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
	
}
