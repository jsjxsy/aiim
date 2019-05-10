package com.orifound.aiim.web.util;

import java.io.IOException;
import javax.servlet.*;
/**
 * 字符集编码过滤器
 * @author 杨丰
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
            //传递过滤器
             filterChain.doFilter(request, response);
         }
     }


    public void init(FilterConfig filterConfig) throws ServletException {
    	//获得配置参数
        this.filterConfig = filterConfig;
        this.encoding = this.filterConfig.getInitParameter("encoding");
     }
}
