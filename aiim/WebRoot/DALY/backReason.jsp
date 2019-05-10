<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>输入原因</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
function sure(){
	if(document.getElementById('backReason').value.trim()==""){
		alert('请输入原因!');
		return false;
	}	
	window.returnValue = document.getElementById('backReason').value;	
	window.close();
}
</script>
  </head>
  
  <body>
  
  
  <div style="font-size:14px;margin-top: 5px;margin-left: 2px;">打回原因:  </div>				
  			 <textarea rows="5" cols="35" style="margin-left: 2px;" id="backReason" name="backReason">
  			 </textarea>
  			 <center>
  			 	<input type="button" value="确定" onclick="sure()" >
  			 </center>
  	
   
  </body>
</html>
