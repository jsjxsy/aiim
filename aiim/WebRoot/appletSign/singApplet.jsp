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
	//Applet测试方法
	function testApplet() {
		var fileSign = document.getElementById('signApplet');
		var returnValue = fileSign.testApplet();
		alert('returnValue='+returnValue);
	}

	//检测文件大小是否超过最大值
	function checkFileSize() {
		var originalfile = "D:\\jks\\dag1.txt";
		var fileSign = document.getElementById('signApplet');
		var returnValue = fileSign.checkFileSize(originalfile);
		alert('returnValue='+returnValue);
	}

	//Applet 进行文件签名测试
	function signFile() {
		//签名文件
		var originalfile = "D:\\jks\\dag1.txt";
		//密钥库键
		var keyAlias = "档案馆";
		//签名生成文件
		var signedDataFile = "D:\jks\\dag2.sgn";
		var fileSign = document.getElementById('signApplet');
		var handleResult = fileSign.signFile(originalfile, keyAlias, signedDataFile);
	 	alert('handleResult='+handleResult);
	 	if('success' == handleResult) {
		 	alert('文件签名成功！');
	 	}
	}
</script>
</head>
  <body>
    Applet文件签名测试<br>
    <input type="button" onclick="signFile()" value="签名测试">
    <input type="button" onclick="testApplet()" value="签名Jar测试">
    <input type="button" onclick="checkFileSize()" value="文件大小检测">
<object id="signApplet"
    classid = "clsid:8AD9C840-044E-11D1-B3E9-00805F499D93"
    codebase = "<%=basePath%>appletSign/jinstall-6u10-windows-i586.cab#Version=6,0,0,32"
    WIDTH = "0" HEIGHT = "0" >
    <PARAM NAME = CODE VALUE = "com.orifound.aiim.util.FileSignerApplet" >
    <PARAM NAME = ARCHIVE VALUE = "appletSign.jar" >
    <param name = "type" value = "application/x-java-applet;version=1.6">
    <param name = "scriptable" value = "false">

    <comment>
	<embed id="signApplet"
            type = "application/x-java-applet;version=1.6" \
            CODE = "com.orifound.aiim.util.FileSignerApplet" \
            ARCHIVE = "appletSign.jar" \
            WIDTH = "0" \
            HEIGHT = "0"
	    scriptable = false
	    pluginspage = "<%=basePath%>appletSign/jre_download.jsp">
	    <noembed>
        </noembed>
	</embed>
    </comment>
</object>
</body>