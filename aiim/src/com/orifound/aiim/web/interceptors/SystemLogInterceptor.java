package com.orifound.aiim.web.interceptors;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.orifound.aiim.bll.service.ISystemLogManageService;
import com.orifound.aiim.bll.service.impl.SystemLogManageServiceImpl;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.LogInfo;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.web.util.WebCommonUtil;

/**
 * ϵͳ��־������
 * @author Administrator
 *
 */
public class SystemLogInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 9000154173059237418L;

	static Log logger = LogFactory.getLog(SystemLogInterceptor.class);
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		ISystemLogManageService systemLogManageService = new SystemLogManageServiceImpl();
		
		String value = "";
		try {
			ActionContext context = invocation.getInvocationContext();
			HttpServletRequest request= (HttpServletRequest) context.get(StrutsStatics.HTTP_REQUEST); 
			UserInfo userInfo = (UserInfo) context.getSession().get("userInfo");
			FunctionalDescription functionalDescription = null;
			LogInfo logInfo = null;

			pErrPos = 1;
			
			if(null == userInfo){
				pErrInfo.getContent().append("�õ��û�Ϊ�գ�");
				pFlag = false;
			}
			
			if(pFlag){
				pErrPos = 2;
				ActionProxy proxy = invocation.getProxy();
				String methodName = proxy.getMethod();
				Object action = proxy.getAction();
				Method method = action.getClass().getMethod(methodName);
				
				if(null != method){
					functionalDescription = method.getAnnotation(FunctionalDescription.class);
				}	
			}
			
			if(pFlag){
				pErrPos = 3;
				if(null != functionalDescription){
					logInfo = new LogInfo();//����LogInfo����
					logInfo.setUserID(userInfo.getUserID());
					logInfo.setUserName(userInfo.getUserName());
					logInfo.setRealName(userInfo.getRealName());
					logInfo.setOperateTime(new Date());
					logInfo.setURI(request.getRequestURI());
					logInfo.setFeatureName(functionalDescription.FeatureName());
					logInfo.setOperateContent(functionalDescription.OperateContent());
					logInfo.setIP(WebCommonUtil.getIpAddr(request));
					
					//����ҵ���߼�д��־
					if(systemLogManageService.addLog(logInfo,pErrInfo) == false){
						throw new Exception(pErrInfo.toString());
					}
				}else{
					value = invocation.invoke();
				}
			}
		} catch (Exception e) {
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.getContent().toString());
				}
				
				ServletActionContext.getRequest().setAttribute("pErrInfo", pErrInfo);
				value = "error";
			}
		}
		return value;
	}

}
