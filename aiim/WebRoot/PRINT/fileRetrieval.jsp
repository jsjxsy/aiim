<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/JXGL/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>调卷单</title>
    
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
  <div class="noprint" align="center">
	<input type="button" onclick="printPage()" value="打印">
</div>
<h3 id="catalogTitle" align="center">调卷单</h3>
<table id="tablePrint" class="tablePrint" style="font-size: 12px;width: 600px;" cellspacing="1px" cellpadding="0px" border="1">
  	<thead>
	  <tr>
	  	<th>档号</th>
	  	<th>题名</th>
	  	<th>盒条码</th>
	  	<th>位置</th>
	  	<th>馆藏状态</th>
	  </tr>
  	</thead>
  	<tbody id="printPage">
  		<s:iterator value="#request.archivesInfos">
	 		<tr>
	  			<td><s:property value="archivesID"/></td>
	  			<td><s:property value="title"/></td>
	  			<td><s:property value="archivesID"/></td>
	  			<td><s:property value="archivesID"/></td>
	  			<td><s:property value="archivesID"/></td>
		  	</tr>
	  	</s:iterator>
  	</tbody>
  </table>
  </body>
</html>
