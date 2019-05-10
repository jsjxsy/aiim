package com.orifound.aiim.web.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginFilter implements Filter {
	@SuppressWarnings("unused")
	private FilterConfig filterConfig = null;

	@Override
	public void destroy() {
		this.filterConfig = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest hRequest = (HttpServletRequest)request;
		HttpServletResponse hResponse = (HttpServletResponse)response;
		HttpSession session = hRequest.getSession(false);
		
		//String path = hRequest.getContextPath();

		
		String url = hRequest.getRequestURI();
		System.out.println("ÓÃ»§µÇÂ½À¹½Ø£º"+url);
		if(!url.contains("/user")){
			if(session != null){
	        	if(session.getAttribute("userInfo") != null){
	        		chain.doFilter(request, response);
	        	}else{
	        		hResponse.sendRedirect("/aiim/loginOvertime.jsp");
	        	}
	        } 
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;

	}

}
