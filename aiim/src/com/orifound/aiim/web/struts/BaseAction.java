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
	 * ���HttpServletRequest����
	 * @return HttpServletRequest
	 */
	protected HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}

	/**
	 * ���HttpServletResponse����
	 * @return HttpServletResponse
	 */
	protected HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	
	/**
	 * ���HttpSession����
	 * @param flag �����ǰsession�������Ƿ����´���session
	 * @return HttpSession
	 */
	protected HttpSession getSession(boolean flag){
		return this.getRequest().getSession(flag);
	}
	
	/**
	 * ���response�������
	 * @return PrintWriter
	 * @throws IOException
	 */
	protected PrintWriter getOut() throws IOException {
		HttpServletResponse response = this.getResponse();
		return response.getWriter();
	}
	
	/**
	 * ʹ��response����������Ϣ
	 * @return PrintWriter
	 * @throws IOException
	 */
	protected void print(String info) throws IOException{
		HttpServletResponse response = this.getResponse();
		response.getWriter().print(info);
	}
	
	/**
	 * ��ȡ����ֵ
	 * @param parameterName
	 * @return
	 */
	protected String getParameterForString(String parameterName){
		return this.getRequest().getParameter(parameterName);
	}
	
	/**
	 * ��ȡ����ֵ
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
	 * ��ȡ����ֵ
	 * @param parameterName
	 * @return
	 */
	protected String[] getParameterValues(String parameterName) throws NumberFormatException{
		return this.getRequest().getParameterValues(parameterName);
	}
	
	/**
	 * ���session��ֵ
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
	 * ��request��������ֵ
	 * @param key
	 * @param obj
	 */
	protected void setAttribute(String key,Object obj){
		this.getRequest().setAttribute(key, obj);
	}
}
