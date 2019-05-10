<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>item_student</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
body{
	font-size:14px;
	background-color:#f9f9f9;
}
</style>

</head>

<body>
<!-- 
<input type="image" ID="imgSave" src="images/image_save.gif"
	onmouseover="changeImage(this,'image_save2.gif')"
	onmouseout="changeImage(this,'image_save.gif')" onclick="clickSave()" />
	 -->
	
<fieldset style="width: 100%; margin-top: 10px;margin-left: 5px; margin-right: 5px; ">
	<legend>学生基本信息</legend>
	<table cellpadding="0" cellspacing="0" style="margin-left:8px;">
		<tr height="30px">
			<td width="50px" >学号</td>
			<td width="250px;"><input type="text" /></td>
			<td  width="50px">姓名</td>
			<td ><input type="text" /></td>			
		</tr>
		<tr height="30px">
			<td width="50px" >年度</td>
			<td width="250px;"><input type="text" /></td>
			<td  width="50px">专业</td>
			<td ><input type="text" /></td>			
		</tr>
	</table>
</fieldset>
<fieldset style="width: 100%; margin-top: 10px;margin-left: 5px; margin-right: 5px; ">
	<legend>已有卷内文件</legend>
	<table cellpadding="0" cellspacing="0" style="margin-left:8px;">
		<tr height="30px">
			<td width="320px;"><input type="checkbox" id="type1"> <label for="type1">入党志原书</label></td>
			
			<td width="320px;"><input type="checkbox" id="type2"> <label for="type2">成绩单</label></td>			
		</tr>
		<tr height="30px">
			<td width="340px;"><input type="checkbox" id="type3"> <label for="type3">测试卷内目录项1</label></td>
			
			<td width="340px;"><input type="checkbox" id="type4"> <label for="type4">测试卷内目录项2</label></td>			
		</tr>
		<tr height="30px">
			<td width="320px;"><input type="checkbox" id="type5"> <label for="type5">测试卷内目录项3</label></td>
			
			<td width="320px;"><input type="checkbox" id="type6"> <label for="type6">测试卷内目录项4</label></td>			
		</tr>
		<tr height="30px">
			<td width="320px;"><input type="checkbox" id="type7"> <label for="type7">测试卷内目录项5</label></td>
			
			<td width="320px;"><input type="checkbox" id="type8"> <label for="type8">测试卷内目录项6</label></td>			
		</tr>
		
	</table>
</fieldset>
<center>
	<input type="button" value="&nbsp;保存&nbsp;"><input type="button" value="&nbsp;关闭&nbsp;" onclick="window.close()">
</center>

</body>

</html>
