package com.orifound.aiim.web.struts;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 获得HttpServletRequest对象
	 * @return HttpServletRequest
	 */
	protected HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}

	/**
	 * 获得HttpServletResponse对象
	 * @return HttpServletResponse
	 */
	protected HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	
	/**
	 * 获得HttpSession对象
	 * @param flag 如果当前session不存在是否重新创建session
	 * @return HttpSession
	 */
	protected HttpSession getSession(boolean flag){
		return this.getRequest().getSession(flag);
	}
	
	/**
	 * 获得response输出对象
	 * @return PrintWriter
	 * @throws IOException
	 */
	protected PrintWriter getOut() throws IOException {
		HttpServletResponse response = this.getResponse();
		return response.getWriter();
	}
	
	/**
	 * 使用response输出流输出信息
	 * @return PrintWriter
	 * @throws IOException
	 */
	protected void print(String info) throws IOException{
		HttpServletResponse response = this.getResponse();
		response.getWriter().print(info);
	}
	
	/**
	 * 获取参数值
	 * @param parameterName
	 * @return
	 */
	protected String getParameterForString(String parameterName){
		return this.getRequest().getParameter(parameterName);
	}
	
	/**
	 * 获取参数值
	 * @param parameterName
	 * @return
	 */
	protected int getParameterForInt(String parameterName) throws NumberFormatException{
		if (this.getRequest().getParameter(parameterName) == null || this.getRequest().getParameter(parameterName) == "") {
			return 0;
		}
		return Integer.parseInt(this.getRequest().getParameter(parameterName));
	}
	
	/**
	 * 获取参数值
	 * @param parameterName
	 * @return
	 */
	protected String[] getParameterValues(String parameterName) throws NumberFormatException{
		return this.getRequest().getParameterValues(parameterName);
	}
	
	/**
	 * 获得session的值
	 * @param <T>
	 * @param attrName
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T>T getSessionAttr(String attrName,Class<T> clazz){
		return (T)this.getSession(false).getAttribute(attrName);
	}
	
	/**
	 * 往request里面设置值
	 * @param key
	 * @param obj
	 */
	protected void setAttribute(String key,Object obj){
		this.getRequest().setAttribute(key, obj);
	}
}
