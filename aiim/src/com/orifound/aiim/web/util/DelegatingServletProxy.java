package com.orifound.aiim.web.util;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * 系统初始化代理类
 * @author Administrator
 *
 */
public class DelegatingServletProxy extends GenericServlet  {

	private static final long serialVersionUID = 1L;
	private String targetBean;
	private Servlet proxy;
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		proxy.service(request, response);
	}

	@Override
	public void init() throws ServletException {
		//获得代理的Servlet类名
		this.targetBean = getServletName();
		//获得servlet
		getServletBean();
		//执行servlet init 方法
		proxy.init(getServletConfig());
	}
	
	/**
	 * 得到Spring配置的bean
	 */
	private void getServletBean() {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		this.proxy = (Servlet)wac.getBean(targetBean);
	}
}
