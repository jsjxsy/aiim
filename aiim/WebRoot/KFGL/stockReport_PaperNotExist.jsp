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
    
    <title>库房实物档案不在架信息</title>
    
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
 	 <center>实物档案不在架情况</center>
 	 <center>
  	<table width="80%"  style="font-size: 12px;border-collapse:collapse;" border="1" bordercolor="black" cellspacing="0">
  		<thead>
  			<tr height="20px">
  				<th>序号</th>
	  			<th>档案条码</th>
	  			<th>档案分类</th>
	  			<th>档号</th>
	  			<th>题名</th>
	  			<th>盒条码</th>
	  			<th>馆藏位置</th>
  			</tr>  			
  		</thead>
  		<tbody>
  			<s:iterator value="#request.stockReportPaperNotExists" status="rowstatus">
  				<tr height="20px;">
  					<td align="center"><s:property value="#rowstatus.index+1" /></td>
  					<td><s:property value="archivesBarcode" /></td>
  					<td><s:property value="tag" /></td>
  					<td><s:property value="archivesID" /></td>
  					<td><s:property value="title" /></td>
  					<td ><s:property value="archivesBoxBarcode" /></td>
  					<td ><s:property value="storeAddressFullName" /></td>
  				</tr>
  			
  			</s:iterator>
  		</tbody>
  	</table>
  	</center>
  </body>
</html>
