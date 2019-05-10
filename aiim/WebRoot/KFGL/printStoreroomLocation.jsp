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
    
    <title>打印调卷单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/print.js" charset="GBK"></script>
	<link rel="stylesheet" type="text/css" href="css/print.css" />
	
<script type="text/javascript">
		$(function(){
			//设置每页打印高度
			var pageHeight = new Number(800);
			//分页打印设置
			paginationPrint(pageHeight);
		});
</script>

  </head>
  
  <body>
  <table>
  <thead>
  	<tr>
  		<th>档号</th>
  		<th>题名</th>
  		<th>盒条形码</th>
  		<th>盒所在位置</th>
  		<th>馆藏状态</th>
  	</tr>
  </thead>
    <s:iterator value="#request.storeroomArchivesInfos" status="rowstatus">	
		<tr>
			<td>
				<s:property value="archivesID" />
			</td>
			<td>
				<s:property value="title" />
			</td>
			<td>
				<s:property value="archivesBoxBarcode" />
			</td>
			<td>
				<s:property value="tag" />
			</td>
			<td>
				<s:property value="storeStatus" />
			</td>
		</tr>
	</s:iterator>
	 </table>
  </body>
</html>
