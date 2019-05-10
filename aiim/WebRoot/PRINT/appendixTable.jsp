<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>备考表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/print.js" charset="GBK"></script>
	<link rel="stylesheet" type="text/css" href="css/print.css" />
<style type="text/css">
	body{font-size: 18px;text-align: center;}
	.appendixTitle{font-size:30px;font-weight: bold;letter-spacing: 10px;}
	/*div边框*/
	.divFrame{width:600px; height:800px;margin-top:20px;visibility:visible;border:thin 1px solid;}
	/*说明*/
	.introduction{margin-left:8px; margin-top:20px; visibility:visible;text-align: left;}
	/*底部*/
	.appendixBottom{margin-top:720px; visibility:visible;text-align: left;}
</style>
  </head>
  
  <body>
  <div class="noprint" align="center"><input type="button" onclick="printPage()" value="打印"></div>
  <div class="appendixTitle">备考表</div>
  <div class="divFrame">
  	<div class="introduction">说明：</div>
  	<div class="appendixBottom">
	  	<span style="margin-left: 8px;">立卷人：</span>
	  	<span style="margin-left: 140px;">检查人：</span>
	  	<span style="margin-left: 140px;letter-spacing:40px;">年月日</span>
  	</div>
  </div>
  </body>
</html>
