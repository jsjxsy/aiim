package com.orifound.aiim.web.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.orifound.aiim.bll.service.ISystemInitializeService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemInitializer;
/**
 * ϵͳ�ֵ����ݳ�ʼ��
 * @author ���
 *
 */
@Component("initialServlet")
public class SystemInitializeServlet extends HttpServlet{
	
	static Log logger = LogFactory.getLog(SystemInitializeServlet.class);

	private static final long serialVersionUID = 1L;

	private ISystemInitializeService systemInitializeService;
	
	public ISystemInitializeService getSystemInitializeService() {
		return systemInitializeService;
	}

	public void setSystemInitializeService(
			ISystemInitializeService systemInitializeService) {
		this.systemInitializeService = systemInitializeService;
	}

	public void destroy() {	
		
	}
	
	public void init() throws ServletException {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		try {
			System.out.println("-----------ϵͳ��ʼ����-----------------");
			
			pErrPos = 1;
			SystemInitializer systemInitializer = SystemInitializer.getInstance();
			
			pFlag = systemInitializeService.initialize(systemInitializer, pErrInfo);
			if(pFlag){
				pErrPos = 2;
				ServletContext application = this.getServletContext();
				application.setAttribute("systemInitializer", systemInitializer);		
			}
			
			if(pFlag){
				System.out.println("-----------ϵͳ��ʼ���ɹ�---------");
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;

				logger.error(pErrInfo.getContent().toString());

				System.out.println("ϵͳ��ʼ��ʧ��:"+pErrInfo.getContent().toString());
			}
		}
	}
}
