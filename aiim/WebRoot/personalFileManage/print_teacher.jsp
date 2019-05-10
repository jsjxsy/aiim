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
<title>打印教师卷内材料目录</title>
<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/print.js" charset="GBK"></script>
<link rel="stylesheet" type="text/css" href="css/print.css" />
 </head>
<body>
<div class="noprint" align="center">
	<input type="button" onclick="printPage()" value="打印">
</div>
<h3 id="catalogTitle" align="center"></h3>
 	  <table align="center" id="tablePrint" class="tablePrint" style="font-size: 12px;width: 600px;" cellspacing="1px" cellpadding="0px" border="1">
 	   <tr>
		<td align="left">职工编号：<s:property value="#request.teacherDocsInfos[0].gzh"/></td>
		<td align="left">姓名：<s:property value="#request.teacherDocsInfos[0].xm"/></td>
		</tr>
		</table>
  <table align="center" id="tablePrint" class="tablePrint" style="font-size: 12px;width: 600px;" cellspacing="1px" cellpadding="0px" border="1">
		<thead>
	          <tr>
	          	<th>序号</th>
				<th>名称 </th>
				<th>形成日期</th>
				<th>份数</th>
				<th>页数</th>
				<th>备注</th>
		      <tr>
		</thead>
		<tbody id="printPage" >
		
		<s:iterator var="teacherDocsType" value="#request.teacherDocsTypes">
		<s:iterator var="teacherDocsInfo" value="#request.teacherDocsInfos" status="u">
				  <s:if test="#teacherDocsType.ID == docTypeID ">
				  <s:if test="#u.index>0 && #request.teacherDocsInfos[#u.index-1].docTypeID == #request.teacherDocsInfos[#u.index].docTypeID ">
				  </s:if>
				  <s:else>
					<tr>
						<td><strong><s:property value="caseType"/></strong></td>
						<td colspan="5" align="left"><strong><s:property value="name"/></strong></td>
					</tr>
				</s:else>
				<tr>
					<td align="right"><s:property value="orderID"/></td>
					<td align="left"><s:property value="docName"/></td>
					<td align="left"><s:property value="formationDate"/></td>
					<td><s:property value="copys"/></td>
					<td><s:property value="pages"/></td>
					<td align="left"><s:property value="remark"/></td>
				</tr>
				
				<s:iterator  begin="1" end="blankLines">
				<tr>
					<td height="15"></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				</s:iterator>
				</s:if>
		</s:iterator>
		</s:iterator>
	   </tbody>	
	</table>
  </body>
</html>
