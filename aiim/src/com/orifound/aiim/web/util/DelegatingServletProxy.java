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
 * ϵͳ��ʼ��������
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
		//��ô����Servlet����
		this.targetBean = getServletName();
		//���servlet
		getServletBean();
		//ִ��servlet init ����
		proxy.init(getServletConfig());
	}
	
	/**
	 * �õ�Spring���õ�bean
	 */
	private void getServletBean() {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		this.proxy = (Servlet)wac.getBean(targetBean);
	}
}
