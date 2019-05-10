<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>请输入编号</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script type="text/javascript">
function sure(){
	window.returnValue = document.getElementById('backReason').value;
	window.close();
}
</script>
  </head>
  
  <body>
  <br/>
  
  
  <div style="font-size:14px;margin-top: 5px;margin-left: 2px;">转递编号: <input type="text" id="backReason" name="backReason"> <input type="button" value="确定" onclick="sure()" > </div>				
  
  </body>
</html>
