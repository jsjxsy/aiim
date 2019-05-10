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
 * 权限拦截器
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
				pErrInfo.getContent().append("得到用户为空！");
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
					if (uclKey.equals(auth)) {// 从用户权限集合中查找是否有此权限
						value = invocation.invoke();
					} else {
						pFlag = false;
						pErrInfo.getContent().append("对不起，您没有权限！");
					}
				}else{
					value = invocation.invoke();
				}
			}
		} catch (Exception e) {
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
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
