package com.orifound.aiim.web.interceptors;

import java.lang.reflect.Method;

public class ParseAuthName {
	public static String parseAuthentication(Class<?> clazz, String methodName,Class<?>... parameterTypes) throws NoSuchMethodException {
		//���ݷ�������ȡ�÷�����������򷵻�
		Method method = clazz.getMethod(methodName, parameterTypes);
 
		if (null != method) {
			UCLkey uclkey = method.getAnnotation(UCLkey.class);
			if (null != uclkey) {
				return uclkey.value();
			}
		}
		return null;
	}
}
