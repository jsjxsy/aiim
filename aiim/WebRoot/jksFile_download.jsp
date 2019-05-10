<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>密钥库文件下载</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <table>
	  <thead>
	  	<tr>
	  		<th>部门名称</th>
	  		<th>密钥库文件</th>
	  	</tr>
	  </thead>
	  <tbody>
		<tr>
	  		<td>${userInfo.departmentName}</td>
	  		<td><a href="<%=basePath%>servlet/JKSDownloadServlet">下载</a></td>
	  	</tr>
	  </tbody>
  	
  </table>
  </body>
</html>
