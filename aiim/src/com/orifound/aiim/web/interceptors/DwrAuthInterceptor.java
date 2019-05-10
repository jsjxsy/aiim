package com.orifound.aiim.web.interceptors;

import java.lang.reflect.Method;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Ȩ��������
 * 
 * @author Administrator
 * 
 */
@Aspect
public class DwrAuthInterceptor {

	private static final long serialVersionUID = 1L;

	static Log logger = LogFactory.getLog(DwrAuthInterceptor.class);

	@Pointcut("execution(* com.orifound.aiim.web.struts.*DWR.*(..)))")
	public void DWRClassPointcut() {
		//System.out.println("�е�ִ�У�");
	}
	
	@Pointcut("execution(* com.orifound.aiim.web.struts.*.*DWR(..)))")
	public void DWRMethodPointcut() {
		//System.out.println("�е�ִ�У�");
	}

	@Around("DWRMethodPointcut() || DWRClassPointcut()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
		// ��ȡ���ӵ�ķ���ǩ������
		MethodSignature joinPointObject = (MethodSignature) pjp.getSignature();

		// ���ӵ����ķ���
		Method method = joinPointObject.getMethod();

		Object [] objs = pjp.getArgs();
		
		HttpSession session = null;
		//Class<?>[] parameterTypes = joinPointObject.getParameterTypes();
		for (Object obj : objs) {
			//System.out.println(obj);
			if(obj instanceof HttpSession){
				session = (HttpSession)obj;
				//System.out.println("DWR������HttpSession��"+session);
			}
		}
		
		UCLkey uclKey = method.getAnnotation(UCLkey.class);
		if (session == null) {
			//throw new ApplicationException("�����µ�½��", null);
			throw new Exception("�����µ�½��", null);
		}else if (uclKey != null) {
			//if(uclKey.equals(session.getAttribute("")))
			//throw new ApplicationException("�Բ�����û��Ȩ�ޣ�", null);
			throw new Exception("�Բ�����û��Ȩ�ޣ�", null);
		}
		
		// ���ӵ㷽��������
		 //String name = method.getName();
		 //Class<?>[] parameterTypes = method.getParameterTypes();
		// ��ȡ���ӵ����ڵ�Ŀ�����
		 //Object target = pjp.getTarget();
		// ��ȡĿ�귽��
		 //method = target.getClass().getMethod(name, parameterTypes);
		// ����@AroundPointCut��ע�Ͷ���
		// AroundPointCut joinPoint =
		// method.getAnnotation(AroundPointCut.class);
		// if (!joinPoint.accessRead()) {
		// throw new ApplicationException("û��Ȩ�ޣ�", null);
		// }
		return pjp.proceed();
	}
}
