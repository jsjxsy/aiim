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
 * 权限拦截器
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
		//System.out.println("切点执行！");
	}
	
	@Pointcut("execution(* com.orifound.aiim.web.struts.*.*DWR(..)))")
	public void DWRMethodPointcut() {
		//System.out.println("切点执行！");
	}

	@Around("DWRMethodPointcut() || DWRClassPointcut()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
		// 获取连接点的方法签名对象
		MethodSignature joinPointObject = (MethodSignature) pjp.getSignature();

		// 连接点对象的方法
		Method method = joinPointObject.getMethod();

		Object [] objs = pjp.getArgs();
		
		HttpSession session = null;
		//Class<?>[] parameterTypes = joinPointObject.getParameterTypes();
		for (Object obj : objs) {
			//System.out.println(obj);
			if(obj instanceof HttpSession){
				session = (HttpSession)obj;
				//System.out.println("DWR拦截器HttpSession："+session);
			}
		}
		
		UCLkey uclKey = method.getAnnotation(UCLkey.class);
		if (session == null) {
			//throw new ApplicationException("请重新登陆！", null);
			throw new Exception("请重新登陆！", null);
		}else if (uclKey != null) {
			//if(uclKey.equals(session.getAttribute("")))
			//throw new ApplicationException("对不起，您没有权限！", null);
			throw new Exception("对不起，您没有权限！", null);
		}
		
		// 连接点方法方法名
		 //String name = method.getName();
		 //Class<?>[] parameterTypes = method.getParameterTypes();
		// 获取连接点所在的目标对象
		 //Object target = pjp.getTarget();
		// 获取目标方法
		 //method = target.getClass().getMethod(name, parameterTypes);
		// 返回@AroundPointCut的注释对象
		// AroundPointCut joinPoint =
		// method.getAnnotation(AroundPointCut.class);
		// if (!joinPoint.accessRead()) {
		// throw new ApplicationException("没有权限！", null);
		// }
		return pjp.proceed();
	}
}
