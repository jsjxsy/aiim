package com.orifound.aiim.web.interceptors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.web.interceptors.ParseAuthName;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Ȩ��������
 * 
 * @author Administrator
 * 
 */
public class AuthInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	static Log logger = LogFactory.getLog(AuthInterceptor.class);
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		String value = "";
		try {
			ActionContext context = invocation.getInvocationContext();
			UserInfo user = (UserInfo) context.getSession().get("userInfo");
			String uclKey = (String) context.getSession().get("UCLKey");
			String auth = null;

			pErrPos = 1;
			
			if(null == user){
				pErrInfo.getContent().append("�õ��û�Ϊ�գ�");
				pFlag = false;
			}
			
			if(pFlag){
				pErrPos = 2;
				ActionProxy proxy = invocation.getProxy();
				String methodName = proxy.getMethod();
				Object action = proxy.getAction();
				auth = ParseAuthName.parseAuthentication(action.getClass(),methodName);	
			}
			
			if(pFlag){
				pErrPos = 3;
				if(null != auth){
					if (uclKey.equals(auth)) {// ���û�Ȩ�޼����в����Ƿ��д�Ȩ��
						value = invocation.invoke();
					} else {
						pFlag = false;
						pErrInfo.getContent().append("�Բ�����û��Ȩ�ޣ�");
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
