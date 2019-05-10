package com.orifound.aiim.web.util;

import java.io.IOException;
import javax.servlet.*;
/**
 * �ַ������������
 * @author ���
 *
 */
public class EncodingFilter implements Filter {

    private FilterConfig filterConfig = null;
    private String encoding = null;

     public void destroy() {
         filterConfig = null; 
         encoding = null;
     }


    public void doFilter(ServletRequest request, ServletResponse response,
             FilterChain filterChain) throws IOException, ServletException {
        if (request.getCharacterEncoding() == null) {
            if (encoding != null) {
                request.setCharacterEncoding(encoding);
                response.setContentType("text/html;charset="+encoding);
                response.setCharacterEncoding(encoding);
             }
            //���ݹ�����
             filterChain.doFilter(request, response);
         }
     }


    public void init(FilterConfig filterConfig) throws ServletException {
    	//������ò���
        this.filterConfig = filterConfig;
        this.encoding = this.filterConfig.getInitParameter("encoding");
     }
}
