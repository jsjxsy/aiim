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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>统计报表结果--档案归档情况</title>
<script type="text/javascript" src="js/print.js" charset="GBK"></script>
<link rel="stylesheet" type="text/css" href="css/print.css" />
</head>
<body>
<div class="noprint" align="center">
	<input type="button" onclick="printPage()" value="打印">
</div>
<h3 id="catalogTitle" align="center"><s:property value="#request.reportResultArchivesSaveds[0].reportTitle"/></h3>
<table id="tablePrint" class="tablePrint" style="font-size: 12px;width: 600px;" cellspacing="1px" cellpadding="0px" border="1">						
	<thead>
		<tr>
			<th>一级类目名称 </th>
			<th>总卷数</th>
			<th>总件数</th>
		</tr>
	</thead>
	<tbody id="printPage">
	<s:iterator var="reportResultArchivesSaved" value="#request.reportResultArchivesSaveds">
		<tr style="font-size: <s:property value="#request.reportPrintSettings[0].tableFontSize"/>px;">
			<td height="<s:property value="#request.reportPrintSettings[0].tableRowHeight"/>">
			<s:property value="archivesTypeName"/></td>
			<td><s:property value="columeCount"/></td>
			<td><s:property value="pieceCount"/></td>
		</tr>		
	</s:iterator>
	</tbody>
 </table>
</body>
</html>
