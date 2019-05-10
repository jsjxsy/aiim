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
			//��֤����
			pErrPos = 1;
			
			//����ҵ���߼��õ���ݵ���Ϣ
			if (pFlag) {
				emsInfos = new ArrayList<EMS>();
				pErrPos = 2;
				if (studentFileManageService.getEMSinfos(ids,emsInfos,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���ҿ�ݵ���Ϣʧ�ܣ� ");
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
			//���پֲ�����
			throwable = null;
		}
		return emsInfos;
	}
}
