<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";			
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>middle1.jsp</title>
		<style type="text/css">
        <!--
         body {
	         margin-left: 0px;
	         margin-top: 0px;
	         margin-right: 0px;
	         margin-bottom: 0px;
         }
         -->

         .navPoint {
	         COLOR: white;
	         CURSOR: hand;
	         FONT-FAMILY: Webdings;
	         FONT-SIZE: 9pt
         }
         .border
         {
            height:100%;
            table-layout: fixed; 
            border-bottom: 2px #bbbbbb solid;
            border-left: 2px #bbbbbb solid;
            border-right: 2px #bbbbbb solid;
         }
        </style>
	</head>
    <%
      String url = "";
      Enumeration parameterNames = request.getParameterNames();
      while(parameterNames.hasMoreElements()){
         String parameterName = (String)parameterNames.nextElement();
         if(parameterName.equals("url")){
            url = request.getParameter("url");
         }
         if(parameterName.equals("url") != true){
            url += "?"+parameterName+"="+request.getParameter(parameterName); 
         }
      }
    %>
	<body style="overflow: hidden">
	    <c:if test="${sessionScope.userInfo == null}">
	       <c:redirect url="/loginOvertime.jsp"></c:redirect>
	    </c:if>
		<table id="tb" width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="border" >
			<tr>
				<td width="100%" align="center" valign="top">
					<iframe id="right" name="right" height="100%" width="100%" frameborder="0" scrolling="yes" src="<%=url%>">
						浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。
					</iframe>
				</td>
			</tr>
		</table>
	</body>
</html>
