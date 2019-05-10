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
    
    <title>系统中不在架档案</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="overflow: scroll;">
 	 <center>系统中档案不在架情况</center>
 	 <center>
  	<table width="80%"  style="font-size: 12px;border-collapse:collapse;" border="1" bordercolor="black" cellspacing="0">
  		<thead>
  			<tr height="20px">
  				<th>序号</th>
	  			<th>档案条码</th>
	  			<th>盒条码</th>
	  			<th>馆藏位置</th>
	  			<th>馆藏状态</th>
  			</tr>  			
  		</thead>
  		<tbody>
  			<s:iterator value="#request.stockReportSystemNotExists" status="rowstatus">
  				<tr height="20px;">
  					<td align="center"><s:property value="#rowstatus.index+1" /></td>
  					<td><s:property value="archivesBarcode" /></td>
  					<td ><s:property value="archivesBoxBarcode" /></td>
  					<td ><s:property value="storeAddressFullName" /></td>
  					<td ><s:property value="storeStatus" /></td>
  				</tr>
  			</s:iterator>
  		</tbody>
  	</table>
  	</center>
  </body>
</html>
