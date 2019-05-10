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
<title>学生卷内文件信息</title>
<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="js/print.js" charset="GBK"></script>
<link rel="stylesheet" type="text/css" href="css/print.css" />
</script>
 </head>
<body>
<div class="noprint" align="center">
	<input type="button" onclick="printPage()" value="打印">
</div>

 <table align="center" id="tablePrint" class="tablePrint" style="font-size: 12px;width: 600px;" cellspacing="1px" cellpadding="0px" border="1">
	    <tr align="left">
			<td>学号：<s:property value="#request.studentInfo.studentId"/></td>
			<td>姓名：<s:property value="#request.studentInfo.studentName"/></td>
        </tr>
	 </table>
	 <table align="center" id="tablePrint" class="tablePrint" style="font-size: 12px;width: 600px;" cellspacing="1px" cellpadding="0px" border="1">
		 <thead>
		          <tr>
		          	<th>有档</th>
					<th>名称 </th>
			      <tr>
			</thead>
		<tbody id="printPage" >
		<s:iterator var="studentDocsInfo" value="#request.studentInfo.studentDocsInfos">
			<tr>
			<td width="80">
					<s:if test="existsFlag">
						<input type="checkbox" name="studentDocs" checked="checked"/>
					</s:if>
					<s:else>
					 	<input type="checkbox" name="studentDocs" />
					</s:else>
	              
		    </td>
		    <td align="left">
		    <s:property value="docName"/>
		    </td>
        </tr>
		</s:iterator>
        </tbody>
	 </table>
  </body>
</html>
