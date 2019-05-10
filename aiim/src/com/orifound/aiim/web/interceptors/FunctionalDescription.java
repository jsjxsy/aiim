package com.orifound.aiim.web.interceptors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能描述注解
 * @author Administrator
 *
 */
@Retention(RetentionPolicy.RUNTIME)//指定该注解是在运行期进行
@Target({ElementType.METHOD})//指定该注解要在方法上使用
public @interface FunctionalDescription {
	String FeatureName() default "";
	String OperateContent() default "";
}
