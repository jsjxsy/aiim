package com.orifound.aiim.web.interceptors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 
/**
 * �û�����UCLKeyע��
 * @author Administrator
 *
 */
@Retention(RetentionPolicy.RUNTIME)//ָ����ע�����������ڽ���
@Target({ElementType.METHOD})//ָ����ע��Ҫ�ڷ�����ʹ��
public @interface UCLkey {
	 String value() default "";
}
