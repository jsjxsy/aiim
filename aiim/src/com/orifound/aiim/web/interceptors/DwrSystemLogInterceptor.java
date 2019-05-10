package com.orifound.aiim.web.interceptors;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.ISystemLogManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.LogInfo;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.web.util.WebCommonUtil;

@Aspect
public class DwrSystemLogInterceptor {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ISystemLogManageService systemLogManageService;

	static Log logger = LogFactory.getLog(DwrAuthInterceptor.class);

	@Pointcut("execution(* com.orifound.aiim.web.struts.*DWR.*(..)))")
	public void DWRSystemLogClassPointcut() {
		// System.out.println("�е�ִ�У�");
	}

	@Pointcut("execution(* com.orifound.aiim.web.struts.*.*DWR(..)))")
	public void DWRSystemLogMethodPointcut() {
		// System.out.println("�е�ִ�У�");
	}

	@Around("DWRSystemLogClassPointcut() || DWRSystemLogMethodPointcut()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		HttpSession session = null;
		HttpServletRequest request = null;
		UserInfo userInfo = null;
		String uri = "";
		LogInfo logInfo = null;

		MethodSignature joinPointObject = (MethodSignature) pjp.getSignature();// ��ȡ���ӵ�ķ���ǩ������
		Method method = joinPointObject.getMethod();// ���ӵ����ķ���
		Object[] objs = pjp.getArgs();// ��÷�����������

		for (Object obj : objs) {
			if (obj instanceof HttpSession) {
				session = (HttpSession) obj;
				userInfo = (UserInfo) session.getAttribute("userInfo");// ��ȡ�û�����
			}
			if (obj instanceof HttpServletRequest) {
				request = (HttpServletRequest) obj;
				uri = request.getRequestURI();// ��ȡ�û�����
			}
		}

		FunctionalDescription functionalDescription = method.getAnnotation(FunctionalDescription.class);
		if (null == session) {
			throw new Exception("�����µ�½��", null);
		} else if (null != functionalDescription) {
			
			logInfo = new LogInfo();// ����LogInfo����
			logInfo.setUserID(userInfo.getUserID());
			logInfo.setUserName(userInfo.getUserName());
			logInfo.setRealName(userInfo.getRealName());
			logInfo.setOperateTime(new Date());
			logInfo.setURI(uri);
			logInfo.setFeatureName(functionalDescription.FeatureName());
			logInfo.setOperateContent(functionalDescription.OperateContent());
			logInfo.setIP(WebCommonUtil.getIpAddr(request));

			// ����ҵ���߼�д��־
			ErrInfo pErrInfo = new ErrInfo();
			if (systemLogManageService.addLog(logInfo, pErrInfo) == false) {
				throw new Exception(pErrInfo.toString());
			}
		}
		return pjp.proceed();
	}
}
