<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader("Expires",0);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>文件签名Applet</title>
    <meta http-equiv="Expires" CONTENT="0">
	<meta http-equiv="Cache-Control" CONTENT="no-store">
	<meta http-equiv="Pragma" CONTENT="no-cache">
	
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script type="text/javascript">
	function testApplet() {
		var fileSign = document.getElementById('signApplet');
		var returnValue = fileSign.appletTest();
		alert('returnValue='+returnValue);
	}

	//文件上传
	function signFile() {
		var fileSign = document.getElementById('signApplet');
		//内部序号
		var nbxh = 1;
		//客户端文件路径
		var filePathOnClient = "D:\\jks\\签名文件.pdf";

<%--var filePathOnClient = "D:\\jks\\dag.txt";--%>
		//服务器端文件路径
		var filePathOnServier = "D:\\jks\\upload\\";
		//部门名称
		var departmentName = "教务处";
		//webservice地址
		var serviceURL = 'http://localhost/FileRemoteWebService/services/FileRemoteManageService';
		
		//文件上传保存路径(以"\"结尾)
		var originalfileDir = 'D:\\jks\\upload\\';
		var returnValue = fileSign.signFile(nbxh, filePathOnClient, filePathOnServier, departmentName, serviceURL);
		alert('returnValue='+returnValue);

		var jsons = eval('(' + returnValue + ')');
		alert(jsons.errorMessage);
	}


</script>
</head>
  
  <body>
    Applet文件签名测试 <br>
    <input type="button" onclick="testApplet()" value="Applet测试">
    <input type="button" onclick="signFile()" value="文件签名">
    <APPLET id="signApplet" CODE = "com.orifound.aiim.util.FileSignerApplet"
  		JAVA_CODEBASE="../appletSign" ARCHIVE = "appletSign.jar" WIDTH="100" HEIGHT="100">
  </body>
</html>