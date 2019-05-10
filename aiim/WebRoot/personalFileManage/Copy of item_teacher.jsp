<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>著录教职工卷内材料</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	<SCRIPT type="text/javascript">
	 //得到表格的一行
	 function getRow(table,array){
	    var $ths = table.children("thead").children().children();
	    var $tr = $("<tr></tr>");
	    for(var i=0;i<$ths.length;i++){
	       $tr.append("<td>"+array[i]+"</td>");
	    }
	    return $tr;
	 }
	</SCRIPT>
	
	
	
	
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
	<legend>教职工基本信息</legend>
	<table cellpadding="0" cellspacing="0" style="margin-left:8px;font-size:12px">
		<tr height="30px">
			<td width="50px" >工资号</td>
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
	<table style="width: 100%;" >
		<tr>
			<td width="50px;"><span style="font-size:12px">文件类型</span></td>
			<td ><select style="width:158px;">
				<option value="1">个人基本信息表</option>
				<option value="2">工作总结</option>
				<option value="3">工资表</option>
				</select>
			</td>
		</tr>
		<tr>
			<td><span style="font-size:12px;">题名</span></td>
			<td><input type="text"  name="title" style="width: 300px;"><input type="button" value="添加"></td>
			
		</tr>
		
	</table>

	<div style="overflow: auto; width: 100%;height: 220px;">
	<table style="width: 100%;font-size:12px;border: #104da6 1px solid; " cellpadding="0" cellspacing="1px;">
		<thead>
			<tr class="tableHead">
				<th style="width: 30px;">序号</th>
				<th>文件类型</th>
				<th>题名</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<tr  bgcolor="#eef5ff" height="20px;">
				<td align="center">1</td>
				<td>个人基本信息表</td>
				<td>个人基本信息主表</td>
				<td align="center" style="width:40px;"><a href="javascript:;">删除</a></td>
			</tr>
			<tr  bgcolor="#eef5ff" height="20px;">
				<td align="center">2</td>
				<td>工作总结</td>
				<td>2007年工作总结</td>
				<td align="center" style="width:40px;"><a href="javascript:;">删除</a></td>
			</tr>
			<tr  bgcolor="#eef5ff" height="20px;">
				<td align="center">3</td>
				<td>工作总结</td>
				<td>2008年工作总结</td>
				<td align="center" style="width:40px;"><a href="javascript:;">删除</a></td>
			</tr>
			<tr  bgcolor="#eef5ff" height="20px;">
				<td align="center">4</td>
				<td>工作总结</td>
				<td>2009年工作总结</td>
				<td align="center" style="width:40px;"><a href="javascript:;">删除</a></td>
			</tr>
			<tr  bgcolor="#eef5ff" height="20px;">
				<td align="center">2</td>
				<td>工作总结</td>
				<td>2007年工作总结</td>
				<td align="center" style="width:40px;"><a href="javascript:;">删除</a></td>
			</tr>
			<tr  bgcolor="#eef5ff" height="20px;">
				<td align="center">3</td>
				<td>工作总结</td>
				<td>2008年工作总结</td>
				<td align="center" style="width:40px;"><a href="javascript:;">删除</a></td>
			</tr>
			<tr  bgcolor="#eef5ff" height="20px;">
				<td align="center">4</td>
				<td>工作总结</td>
				<td>2009年工作总结</td>
				<td align="center" style="width:40px;"><a href="javascript:;">删除</a></td>
			</tr>
		</tbody>
	</table>
	</div>
</fieldset>
</body>

</html>
