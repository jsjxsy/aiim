package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IStudentFileManageService;
import com.orifound.aiim.entity.EMS;
import com.orifound.aiim.entity.ErrInfo;

public class StudentFileManageDWR{
	
	static Log logger = LogFactory.getLog(StudentFileManageDWR.class);
	
	@Autowired
	private IStudentFileManageService studentFileManageService;
	
	public List<EMS> getEMSInfos(int [] ids,HttpSession session) throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		List<EMS> emsInfos = null;
		try {
			//验证参数
			pErrPos = 1;
			
			//调用业务逻辑得到快递单信息
			if (pFlag) {
				emsInfos = new ArrayList<EMS>();
				pErrPos = 2;
				if (studentFileManageService.getEMSinfos(ids,emsInfos,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"查找快递单信息失败： ");
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
					
					throw new Exception(pErrInfo.toString());
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				logger.error(pErrInfo.toString());
				if (pErrInfo.getException() != null) {
					throw new Exception(pErrInfo.toString());
				}else{
					throw new Exception(pErrInfo.toShortString());
				}
			}
			//销毁局部变量
			throwable = null;
		}
		return emsInfos;
	}
}
