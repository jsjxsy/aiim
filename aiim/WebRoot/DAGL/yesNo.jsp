<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上传方式选择</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
function offLine(){
	window.returnValue=1; //离线传输
	window.close();
}

function onLine(){
	window.returnValue=0; //在线传输
	window.close();
}
</script>
</head>


<body style=" margin:10px; font-size:14px;">

	该文件已超过6M，建议使用&quot;离线上传&quot;，&quot;离线上传&quot;请点击&quot;离线&quot;，&quot;在线上传&quot;请点击&quot;在线&quot;。<br/><br/>
<center>	<input type="button"  value="离线" onclick="offLine();"/><input type="button" value="在线" style="margin-left:8px;" onclick="onLine();"/></center>


</body>

</html>
