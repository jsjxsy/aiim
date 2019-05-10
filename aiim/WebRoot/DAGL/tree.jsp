<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>test Tree</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="../js/dojo.js"></script>
	<script type="text/javascript">
		dojo.require("dijit.form.TextBox");
		dojo.require("dijit.form.Button");
		function init()
		{
			dojo.connect(dijit.byId("mybutton").domNode,"onclick","login");			
		}
		function login()
		{
			
		}
	</script>

  </head>
  
  <body>
    This is my JSP page. <br>
  </body>
</html>
