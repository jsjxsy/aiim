﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>统计报表结果--工作情况 </title>
<script type="text/javascript" src="js/print.js" charset="GBK"></script>
<link rel="stylesheet" type="text/css" href="css/print.css" />
 </head>
<body>
<div class="noprint" align="center">
	<input type="button" onclick="printPage()" value="打印">
</div>
<h3 id="catalogTitle" align="center"><s:property value="#request.reportResultWorkProcedures[0].reportTitle"/></h3>
  <table id="tablePrint" class="tablePrint" style="font-size: 12px;width: 600px;" cellspacing="1px" cellpadding="0px" border="1">
		<thead>
			<tr>
				<th>真实姓名 </th>								
				<th>著录条目数  </th>
				<th>著录提交送审条目数</th>
				<th>实物档案馆外移交数量 </th>
				<th>业务指导室接收审核数量</th>
				<th>实物档案馆内移交数量 </th>
				<th>档案管理室接收审核数量 </th>
			</tr>
		</thead>
		<tbody>
		<s:iterator var="reportResultWorkProcedure" value="#request.reportResultWorkProcedures">
			<tr  style="font-size: <s:property value="#request.reportPrintSettings[0].tableFontSize"/>px;">
				<td height="<s:property value="#request.reportPrintSettings[0].tableRowHeight"/>">
				<s:property value="realName"/></td>
				<td><s:property value="inputCount"/></td>
				<td><s:property value="inputSubmitCount"/></td>
				<td><s:property value="paperTrans1Count"/></td>
				<td><s:property value="paperCheck1Count"/></td>
				<td><s:property value="PaperTrans2Count"/></td>
				<td><s:property value="paperCheck2Count"/></td>
			</tr>		
		</s:iterator>
		</tbody>
  </table>
</body>
</html>
