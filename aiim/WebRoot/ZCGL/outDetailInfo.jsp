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
    
    <title>转出详细信息</title>
    
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
	<legend>转出地址信息</legend>
	<table cellpadding="0" cellspacing="0" style="margin-left:8px;font-size:12px">
		<tr height="30px">
			<td width="50px" >转递单位</td>
			<td width="500px;"><input type="text"  style="width: 500px;"/></td>				
		</tr>
		<tr height="30px">
			<td width="50px" >详细地址</td>
			<td width="500px;"><input type="text" style="width: 500px;" /></td>				
		</tr>
	</table>
</fieldset>
<fieldset style="width: 100%; margin-top: 10px;margin-left: 5px; margin-right: 5px; ">
	<legend>人员列表</legend>	
	<div style="overflow: auto; width: 100%;height: 220px;">
	<table style="width: 100%;font-size:12px;border: #104da6 1px solid; " cellpadding="0" cellspacing="1px;">
		<thead>
			<tr class="tableHead">
				<th style="width: 30px;">序号</th>
				<th>学号</th>
				<th>姓名</th>
				<th>专业</th>	
			</tr>
		</thead>
		<tbody>
			<tr bgcolor="#eef5ff" height="20px;">
				<td align="center">1</td>
				<td>64040454</td>
				<td>陈涛</td>
				<td>计算机</td>	
			</tr>
			<tr bgcolor="#eef5ff" height="20px;">
				<td align="center">1</td>
				<td>64040454</td>
				<td>陈涛</td>
				<td>计算机</td>			
			</tr>
			<tr bgcolor="#eef5ff" height="20px;">
				<td align="center">1</td>
				<td>64040454</td>
				<td>陈涛</td>
				<td>计算机</td>
			</tr>
		</tbody>
	</table>
	</div>
</fieldset>

<center><input type="button" value="EMS转递"/><input type="button" value="机要转递"/></center>
</body>

</html>
