<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<title>消息</title>
<style type="text/css">
body{
	font-size:14px;
}
</style>
<script type="text/javascript">
function doDeal(){

	var url = window.dialogArguments
	//2：利用申请通过，当利用申请审批通过后，利用人会收到该消息，并且要求传递两个参数
	if(<s:property value="#request.messageType.msgTypeID"/> == 2){
		url = "<%=basePath%><s:property value="#request.messageType.dealURI"/>?archivesTypeId=<s:property value="#request.systemMessage.archivesTypeID"/>&NBXH=<s:property value="#request.systemMessage.NBXH"/>";
		}else{
		url = "<%=basePath%><s:property value="#request.messageType.dealURI"/>"
		}
	window.returnValue = url;
	window.close();
}

 function winClose(){
	window.returnValue = 1;
	window.close();
}
</script>
</head>

<body>
<fieldset style="width: 449px;margin:10px;">
	<table style="margin:10px;">
		<tr style="height:30px;">
			<td style="width:60px;">
				标题
			</td>
			<td>
				<input type="text" style="width: 350px"  value="<s:property value="#request.systemMessage.msgTitle"/>" readonly="readonly"/>
			</td>
		</tr>
		<tr  style="height:30px;">
			<td>
				发言时间
			</td>
			<td>
				<input type="text" style="width: 350px" value="<s:date name="#request.systemMessage.sendTime" format="yyyy-MM-dd"/>" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td>
				详细内容
			</td>
			<td>
				<textarea style="width: 350px; height: 118px;" readonly="readonly"><s:property value="#request.systemMessage.msgContent"/>
				</textarea>
			</td>
		</tr>
	</table>
</fieldset>
<table width="100%">
			<tr align="center">
				<td><input type="button" name="deal" value="处理" onclick="doDeal()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" name="close" value="关闭" onclick="winClose()"/></td>
			</tr>
</table>
</body>

</html>
