package com.orifound.aiim.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.orifound.aiim.entity.ErrInfo;

public class ElementUCLTag extends TagSupport {
	private static final long serialVersionUID = 1L;

	static Log logger = LogFactory.getLog(ElementUCLTag.class);

	private String uclKey;

	public String getUclKey() {
		return uclKey;
	}

	public void setUclKey(String uclKey) {
		this.uclKey = uclKey;
	}

	@Override
	public int doStartTag() throws JspException {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		int returnValue = 0;
		
		try {
			pErrPos = 1;
			
			if(pageContext.getSession() == null){
				pFlag = false;
				pErrInfo.getContent().append("登陆超时，请重新登陆！");
			}
			
			if(pFlag){
				if(pageContext.getSession().getAttribute("userInfo") == null){
					pFlag = false;
					pErrInfo.getContent().append("登陆超时，请重新登陆！");
				}
			}
			
			if(pFlag){
				if(uclKey == null){
					returnValue = EVAL_BODY_INCLUDE;
				}else{
					if(pageContext.getSession().getAttribute(uclKey) == null){
						returnValue = SKIP_BODY;
					}else{
						returnValue = EVAL_BODY_INCLUDE;
					}
				}
			}
		} catch (Exception e) {
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.getContent());
				}
				HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
				request.setAttribute("pErrInfo", pErrInfo);
				try {
					pageContext.forward("/error.jsp");
				} catch (Exception e) {
					e.printStackTrace();
					throw new JspException();
				} 
			}
		}
		return returnValue;
	}

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}
}
