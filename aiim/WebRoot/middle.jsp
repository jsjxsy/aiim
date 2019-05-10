<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";	
	pageContext.setAttribute("path",basePath);			
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>middle.jsp</title>
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
		<script type="text/javascript">
			function getTree(){
		        window.left.location="<%=url%>";
			 }
		     
		     //隐藏显示左边
			function switchSysBar(){ 
                var ssrc=document.getElementById("img1").src;
				if (ssrc=="${path}images/main_55.gif")
				{ 
					document.getElementById("img1").src="${path}images/main_55_1.gif";
					document.getElementById("frmTitle").style.display="none" ;
				} 
				else
				{ 
					document.getElementById("img1").src="${path}images/main_55.gif";
					document.getElementById("frmTitle").style.display="" ;
				} 
			} 
        </script>
	</head>

	<body style="overflow: hidden" onload="getTree()">
	   <c:if test="${sessionScope.userInfo == null}">
	     <c:redirect url="/loginOvertime.jsp"></c:redirect>
	  </c:if>
		<table id="tb" width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="border" >
			<tr>
				<td width="180px" id="frmTitle" align="center"valign="top">
				   <iframe id="left" name="left" height="100%" width="100%" frameborder="0" src="" marginheight="2px" marginwidth="1px">
		                                    浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。
		           </iframe>
				</td>
				<td  style="width: 4px;" valign="middle" bgcolor="#bbbbbb" onclick="switchSysBar()">
					<SPAN class="navPoint" id="switchPoint" title="关闭/打开左栏">
					  <img src="images/main_55.gif" name="img1" width="4px" height="40px" id="img1">
					</SPAN>
				</td>
				<td width="100%" align="center" valign="top">
					<iframe id="right" name="right" height="100%" width="100%" frameborder="0" scrolling="yes" src="blank.html">
						浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。
					</iframe>
				</td>
			</tr>
		</table>
	</body>
</html>
